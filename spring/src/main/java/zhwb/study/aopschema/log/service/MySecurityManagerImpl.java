package zhwb.study.aopschema.log.service;

import org.aspectj.lang.JoinPoint;



public class MySecurityManagerImpl implements MySecurityManager {
	public void checkSecurity(JoinPoint jp) {
		System.out.println("JP getKind:" + jp.getKind());
		System.out.println("JP getSignature:" + jp.getSignature().getName());
		System.out.println("JP getClass:" + jp.getClass().getName());
		for (Object obj : jp.getArgs()) {
			System.out.println("JP getArgs: " + obj);
		}
		System.out.println("User security Check");
	}

}
