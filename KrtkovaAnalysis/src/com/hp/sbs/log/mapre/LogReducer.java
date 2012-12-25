package com.hp.sbs.log.mapre;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogReducer extends Reducer<Text, MapWritable, Text, Text> {
	String serverIP = "";
	String appID = "";
	String moment = "";

	protected void reduce(Text key, Iterable<MapWritable> values, Context context) throws IOException,
			InterruptedException {
		int sum = 0;
		int count = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		Map<String, Integer> treeMapServer = new TreeMap<String, Integer>();
		Map<String, Integer> treeMapApp = new TreeMap<String, Integer>();
		for (MapWritable info : values) {
			// duration calculate
			IntWritable duration = (IntWritable) info.get(new Text("duration"));
			sum += duration.get();
			max = Math.max(max, duration.get());
			min = Math.min(min, duration.get());
			// serverIP
			listAndStandard(treeMapServer, (Text) info.get(new Text("serverIP")));
			// AppID
			listAndStandard(treeMapApp, (Text) info.get(new Text("AppID")));
			// TODO:moment
			// listAndStandard((Text) info.get(new Text("moment")), treeMap);
			// call number
			count++;
		}
		countAndDisplay(treeMapServer);
		countAndDisplay(treeMapApp);
		int average = sum / count;
		Text txt = new Text();
		txt.set(count + ", " + average + ", " + max + ", " + min + "," + serverIP + "," + appID);
		context.write(key, txt);
		serverIP = "";
		appID = "";
	}

	private void countAndDisplay(Map<String, Integer> treeMap) {
		for (Entry<String, Integer> iterable_element : treeMap.entrySet()) {
			if (iterable_element.getKey() != null) {
				if (iterable_element.getKey().matches(
						"(.|\n)*((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))")) {
					serverIP += iterable_element.getKey() + ":" + iterable_element.getValue() + ";";
				} else {
					appID += iterable_element.getKey() + ":" + iterable_element.getValue() + ";";
				}
			}
		}
	}

	private void listAndStandard(Map<String, Integer> treeMap, Text text) {
		if (!treeMap.containsKey(text.toString())) {
			treeMap.put(text.toString(), new Integer(1));
		} else {
			Integer i = treeMap.get(text.toString()) + 1;
			treeMap.put(text.toString(), i);
		}

	}

}
