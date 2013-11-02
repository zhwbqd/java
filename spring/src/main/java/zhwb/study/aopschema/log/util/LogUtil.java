package zhwb.study.aopschema.log.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * Hello world!
 *
 */
public class LogUtil
{
	Logger logger = Logger.getLogger(LogUtil.class);

	public void logAll(JoinPoint jp){
		String methodName = jp.getSignature().getName();
		logger.info(jp.getTarget().getClass().getName() + "----" + methodName);

		Object[] args = jp.getArgs();
		for (int i = 0; i < args.length; i++) {
			logger.info("params[" + i + "]: " + args[i].toString());

		}
}

}
