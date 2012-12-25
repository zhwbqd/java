import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

public class Main {

	/**
	 * Description goes here.
	 * 
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Class clz = Main.class;
		Method method = clz.getMethod("method", List.class);
		for (Type t : method.getGenericParameterTypes()) {
			for (Type p : ((ParameterizedType) t).getActualTypeArguments()) {
				sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl pti;
				pti = (sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl) p;
				System.out.print(pti);
			}
		}
	}

	public void method(List<ServiceCreditInfo> l) {

	}

	public void method(ArrayList<ServiceCreditInfo> l) {

	}

}
