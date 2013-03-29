package zhwb.study.juc.service.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import zhwb.study.juc.service.Node;

/**
 * 模拟执行节点验证的Mock类
 * 
 * @author DigitalSonic
 */
public class MockNodeValidator {
	public static final List<Node> ENTRIES = new ArrayList<Node>();
	private static final Map<String, Node> NODE_MAP = new HashMap<String, Node>();

	private static AtomicInteger count = new AtomicInteger(0);
	private static Logger logger = Logger.getLogger("MockNodeValidator");

	/*
	 * 构造模拟数据
	 */
	static {
		Node node0 = new Node("NODE0", "http://node0/check?wsdl"); // 入口0
		Node node1 = new Node("NODE1", "http://node1/check?wsdl");
		Node node2 = new Node("NODE2", "http://node2/check?wsdl");
		Node node3 = new Node("NODE3", "http://node3/check?wsdl");
		Node node4 = new Node("NODE4", "http://node4/check?wsdl");
		Node node5 = new Node("NODE5", "http://node5/check?wsdl");
		Node node6 = new Node("NODE6", "http://node6/check?wsdl"); // 入口1
		Node node7 = new Node("NODE7", "http://node7/check?wsdl");
		Node node8 = new Node("NODE8", "http://node8/check?wsdl");
		Node node9 = new Node("NODE9", "http://node9/check?wsdl");

		node0.setDependencies(new String[] { node1.getWsdl(), node2.getWsdl() });
		node1.setDependencies(new String[] { node3.getWsdl(), node4.getWsdl() });
		node2.setDependencies(new String[] { node5.getWsdl() });
		node6.setDependencies(new String[] { node7.getWsdl(), node8.getWsdl() });
		node7.setDependencies(new String[] { node5.getWsdl(), node9.getWsdl() });
		node8.setDependencies(new String[] { node3.getWsdl(), node4.getWsdl() });

		node2.setResult("FAILED");

		NODE_MAP.put(node0.getWsdl(), node0);
		NODE_MAP.put(node1.getWsdl(), node1);
		NODE_MAP.put(node2.getWsdl(), node2);
		NODE_MAP.put(node3.getWsdl(), node3);
		NODE_MAP.put(node4.getWsdl(), node4);
		NODE_MAP.put(node5.getWsdl(), node5);
		NODE_MAP.put(node6.getWsdl(), node6);
		NODE_MAP.put(node7.getWsdl(), node7);
		NODE_MAP.put(node8.getWsdl(), node8);
		NODE_MAP.put(node9.getWsdl(), node9);

		ENTRIES.add(node0);
		ENTRIES.add(node6);
	}

	/**
	 * 模拟执行远程验证返回节点，每次调用等待500ms
	 */
	public static Node validateNode(String wsdl) {
		Node node = cloneNode(NODE_MAP.get(wsdl));
		logger.info("验证节点" + node.getName() + "[" + node.getWsdl() + "]");
		count.getAndIncrement();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return node;
	}

	/**
	 * 获得计数器的值
	 */
	public static int getCount() {
		return count.intValue();
	}

	/**
	 * 克隆一个新的Node对象（未执行深度克隆）
	 */
	public static Node cloneNode(Node originalNode) {
		Node newNode = new Node();

		newNode.setName(originalNode.getName());
		newNode.setWsdl(originalNode.getWsdl());
		newNode.setResult(originalNode.getResult());
		newNode.setDependencies(originalNode.getDependencies());

		return newNode;
	}
}
