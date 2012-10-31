package com.hp.sbs.command.loadtest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReportHelper {

	private String placeHolder = ":$$REPORT_URL";
	private String templateReport = "report.html";

	public String generateReport(String reportURL) {

		StringBuffer buffer = new StringBuffer();

		InputStream stream = null;

		try {
			stream = new FileInputStream(templateReport);
		} catch (FileNotFoundException e) {
			stream = this.getClass().getClassLoader()
					.getResourceAsStream(templateReport);
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;

		try {

			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}

			br.close();
			stream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String report = buffer.toString();
		int pos = report.indexOf(placeHolder);
		report = report.substring(0, pos) + reportURL
				+ report.substring(pos + placeHolder.length());

		return report;

	}
}
