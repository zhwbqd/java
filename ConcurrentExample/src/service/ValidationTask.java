package service;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

import service.mock.MockNodeValidator;

/**
 * 执行验证的任务类
 * 
 * @author DigitalSonic
 */
public class ValidationTask implements Callable<Node> {
	private static Logger logger = Logger.getLogger("ValidationTask");

	private String wsdl;

	/**
	 * 构造方法，传入节点的WSDL
	 */
	public ValidationTask(String wsdl) {
		this.wsdl = wsdl;
	}

	/**
	 * 执行针对某个节点的验证<br/>
	 * 如果正有别的线程在执行同一节点的验证则等待其结果，不重复执行验证
	 */
	@Override
	public Node call() throws Exception {
		Node node = ValidationService.NODE_MAP.get(wsdl);
		Lock lock = null;
		logger.info("开始验证节点：" + wsdl);
		if (node != null) {
			lock = node.getLock();
			if (lock.tryLock()) {
				// 当前没有其他线程验证该节点
				logger.info("当前没有其他线程验证节点" + node.getName() + "[" + wsdl + "]");
				try {
					Node result = MockNodeValidator.validateNode(wsdl);
					mergeNode(result, node);
				} finally {
					lock.unlock();
				}
			} else {
				// 当前有别的线程正在验证该节点，等待结果
				logger.info("当前有别的线程正在验证节点" + node.getName() + "[" + wsdl + "]，等待结果");
				lock.lock();
				lock.unlock();
			}
		} else {
			// 从未进行过验证，这种情况应该只出现在系统启动初期
			// 这时是在做初始化，不应该有冲突发生
			logger.info("首次验证节点：" + wsdl);
			node = MockNodeValidator.validateNode(wsdl);
			ValidationService.NODE_MAP.put(wsdl, node);
		}
		logger.info("节点" + node.getName() + "[" + wsdl + "]验证结束，验证结果：" + node.getResult());
		return node;
	}

	/**
	 * 将src的内容合并进dest节点中，不进行深度拷贝
	 */
	private Node mergeNode(Node src, Node dest) {
		dest.setName(src.getName());
		dest.setWsdl(src.getWsdl());
		dest.setDependencies(src.getDependencies());
		dest.setResult(src.getResult());
		return dest;
	}
}
