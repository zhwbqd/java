package com.hp.sbs.log.tester;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class TestLogAnalysis {
	public static void main(String[] args) {
		// System.out.print("aaa" + System.getProperty("line.separator"));
		// System.out.print("aaa" + System.getProperty("line.separator"));

		// String line = System.getProperty("line.separator");
		// String str = "first" + line + "second" + line + "third" + line +
		// "fourth";
		// System.out.println(str);
		//
		// System.out.println(str.substring(str.indexOf(line) + line.length()));
		StringSplit();
	}

	public static void StringSplit() {
		String log = "2012-04-05 03:38:30.819 [sbs_security_plugin] [a70da221-13b7-4dc3-8748-856bb28612ad] [TP-Processor21] INFO  c.h.i.s.p.t.s.PartnerTransportServer - || Server: g4t1784g.houston.hp.com  16.210.5.78 | Service Category: Partner | Service Method: retrieveSingleCompositePartnerProfileWithAccreditationsByHPPassportId | Application Id: sbs_security_plugin | Status Code: 200 | Duration: 40ms ||";
		String logg = log.substring(log.indexOf("||"));
		String date = log.substring(0, log.indexOf("[") - 1);
		Map<String, String> info = new HashMap<String, String>();
		for (String str : logg.split("\\|")) {
			if (str.length() != 0) {
				info.put(str.substring(1, str.indexOf(":")), str.substring(str.indexOf(":") + 2).trim());
			}
		}
		String serviceName = info.get("Service Category");
		String methodName = info.get("Service Method");
		String server = info.get("Server");
		String AppId = info.get("Application Id");
		Pattern pattern = Pattern
				.compile("(.|\n)*((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))");
		Integer duration = new Integer(info.get("Duration").substring(0, info.get("Duration").indexOf("ms")));
		System.out.println(date + "\r\n" + AppId + "\r\n" + server + "\r\n" + serviceName + "\r\n" + methodName
				+ "\r\n" + duration + "\r\n");
		Map<Integer, Integer> map = new TreeMap();
		map.put(2, 1);
		map.put(1, 1);
		map.put(1, map.get(1) + 1);
		System.out.print(map.toString());
	}
}
