package com.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class TestInitial {
	
	 private static SessionFactory sf  ;
	 
	public static SessionFactory getSf() {
		return sf;
	}

	public static void setSf(SessionFactory sf) {
		TestInitial.sf = sf;
	}

	static{
		
			Configuration cfg= new AnnotationConfiguration();
			sf=cfg.configure().buildSessionFactory();
			System.out.println(sf.hashCode());
			
		}
	
	}
	
	
	
	

