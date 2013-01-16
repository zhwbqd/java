package zhwb.study.aopschema.log.service;

import org.aspectj.lang.JoinPoint;

public interface MySecurityManager {

	public void checkSecurity(JoinPoint jp);
}

