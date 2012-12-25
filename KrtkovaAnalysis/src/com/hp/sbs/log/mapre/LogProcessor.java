package com.hp.sbs.log.mapre;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.hp.sbs.log.service.MultipleOutputFormat;

public class LogProcessor {
	public static class SBSOutputFormat extends MultipleOutputFormat<Text, Text> {
		@Override
		protected String generateFileNameForKeyValue(Text key, Text value, Configuration conf) {
			String keyLine = key.toString();
			String nextline = System.getProperty("line.separator");
			String str = keyLine.substring(keyLine.indexOf(nextline) + nextline.length());
			String fileName = str.substring(0, str.indexOf(","));
			if (fileName.length() > 0) {
				return fileName + ".csv";
			}
			return "other.txt";
		}
	}

	public static void main(String[] args) throws Throwable {
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (otherArgs.length != 2) {
			System.err.println("Usage: LogProcessor <in> <out>");
			System.exit(2);
		}
		Job job = new Job(conf, "Log Processor");
		job.setJarByClass(LogProcessor.class);
		job.setMapperClass(LogMapper.class);
		job.setReducerClass(LogReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MapWritable.class);
		job.setOutputFormatClass(SBSOutputFormat.class); // multiply output

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
