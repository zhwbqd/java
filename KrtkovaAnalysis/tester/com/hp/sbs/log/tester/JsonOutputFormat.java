package com.hp.sbs.log.tester;

import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class JsonOutputFormat extends TextOutputFormat<Text, Text> {

	@Override
	public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException,
			InterruptedException {
		Configuration conf = context.getConfiguration();
		Path path = getOutputPath(context);
		FileSystem fs = path.getFileSystem(conf);
		FSDataOutputStream out = fs.create(new Path(path, context.getJobName()));
		return new JsonRecordWriter(out);
	}

	private static class JsonRecordWriter extends LineRecordWriter<Text, Text> {
		boolean firstRecord = true;

		@Override
		public synchronized void close(TaskAttemptContext context) throws IOException {
			out.writeChar('{');
			super.close(null);
		}

		@Override
		public synchronized void write(Text key, Text value) throws IOException {
			if (!firstRecord) {
				out.writeChars(",\r\n");
				firstRecord = false;
			}
			out.writeChars("\"" + key.toString() + "\":\"" + value.toString() + "\"");
		}

		public JsonRecordWriter(DataOutputStream out) throws IOException {
			super(out);
			out.writeChar('}');
		}
	}
}
