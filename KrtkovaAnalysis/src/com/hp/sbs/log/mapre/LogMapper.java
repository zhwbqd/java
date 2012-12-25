package com.hp.sbs.log.mapre;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hp.sbs.log.service.LogParser;

public class LogMapper extends Mapper<LongWritable, Text, Text, MapWritable> {
	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String log = value.toString();
		if (log == null || log.length() == 0)
			return;
		if (log.contains("||") && log.endsWith("||")) {
			Map<String, String> info = new HashMap<String, String>();
			info = LogParser.parser(log);
			// key
			String serviceName = info.get("Service Category");
			String methodName = info.get("Service Method");
			Text record = new Text();
			record.set(generateTitle().append(serviceName).append(", " + methodName).toString());
			// vaule:collect useful data
			// String date = log.substring(0, log.indexOf("[") - 1);
			Integer duration = new Integer(info.get("Duration").substring(0, info.get("Duration").indexOf("ms")));
			String serverIP = info.get("Server");
			String AppID = info.get("Application Id");
			MapWritable valueMap = new MapWritable();
			// TODO:
			// valueMap.put(new Text("moment"), new Text(date));
			valueMap.put(new Text("duration"), new IntWritable(duration));
			valueMap.put(new Text("serverIP"), new Text(serverIP));
			valueMap.put(new Text("AppID"), new Text(AppID));
			context.write(record, valueMap); // duration
		}
	}

	private static StringBuffer generateTitle() {
		String title = "ServiceName, MethodName, Invoke Times, Average Duration, Max Duration, Min Duration, ServerIP Distribution, ApplicationID";
		String nextline = System.getProperty("line.separator");
		return new StringBuffer(title + nextline);

	}
}
