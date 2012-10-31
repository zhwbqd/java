package com.hp.gadsc.cdc.et.birthday.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	private static Properties _props = new Properties();

	public PropertyReader() {

	}

	public static String readValue(String key) {

		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					System.getProperty(Constants.PROPERTY_FILE)));

			_props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _props.getProperty(key);

	}

}
