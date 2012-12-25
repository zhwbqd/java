package com.hp.sbs.log.service;

import java.util.HashMap;
import java.util.Map;

public class LogParser {
	private LogParser() {

	}

	public static Map<String, String> parser(String log) {
		Map<String, String> info = new HashMap<String, String>();
		for (String str : log.split("\\|")) {
			if (str.length() != 0) {
				info.put(str.substring(1, str.indexOf(":")), str.substring(str.indexOf(":") + 2).trim());
			}
		}
		return info;
	}
}
