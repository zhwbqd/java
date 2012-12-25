package com.hp.sbs.log.chart;

import java.awt.Color;
import java.awt.Paint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.jCharts.chartData.ChartDataException;
import org.jCharts.properties.PropertyException;

import au.com.bytecode.opencsv.CSVParser;

public class Demo {

	public static void main(String args[]) throws ChartDataException, PropertyException, IOException {
		String path = "C:\\hadoop-0.20.2\\SBS-ANALYSIS";
		File filePath = new File(path);
		if (filePath.isDirectory()) {
			Filtername filter = new Filtername();
			File[] files = filePath.listFiles(filter);
			for (File file : files) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				int lineNumber = 0;
				ArrayList<String> labelList = new ArrayList<String>();
				ArrayList<Paint> paintList = new ArrayList<Paint>();
				ArrayList<Double> dataList = new ArrayList<Double>();
				ArrayList<Double> bardataList = new ArrayList<Double>();
				Random r = new Random();
				ArrayList<Color> colorList = new ArrayList<Color>();
				for (int i = 0; i < 50; i++) {
					colorList.add(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
				}
				String tempString = null;
				String title = null;
				while ((tempString = reader.readLine()) != null) {
					CSVParser parser = new CSVParser();
					if (!tempString.contains("ServiceName")) {
						String[] data = parser.parseLine(tempString);
						title = data[0];
						labelList.add(data[1]);
						dataList.add(Double.valueOf(data[2]));
						bardataList.add(Double.valueOf(data[3]));
						paintList.add(colorList.get(lineNumber));
						lineNumber++;
					}
				}
				String[] labels = new String[labelList.size()];
				for (int i = 0; i < labels.length; i++) {
					labels[i] = labelList.get(i);
				}
				Paint[] paints = new Paint[paintList.size()];
				for (int i = 0; i < paints.length; i++) {
					paints[i] = paintList.get(i);
				}
				double[] pieData = new double[dataList.size()];
				for (int i = 0; i < pieData.length; i++) {
					pieData[i] = dataList.get(i);
				}
				double[][] chartData = new double[1][bardataList.size()];
				for (double[] ds : chartData) {
					for (int i = 0; i < ds.length; i++) {
						ds[i] = bardataList.get(i);

					}
				}
				new PieChart(labels, title, paints, pieData, path);
				new BarChart(chartData, labels, title, path);
			}
		} else {
			System.exit(-1);
		}
	}
}
