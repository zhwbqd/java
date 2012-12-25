package com.hp.sbs.krtkova.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class LoadFile {
	static String path = "C:\\hadoop-0.20.2\\SBSOUT";
	static File[] files;

	public static List<String> loadService() {
		List<String> serviceNames = new ArrayList<String>();
		File filePath = new File(path);
		if (filePath.isDirectory()) {
			Filtername filter = new Filtername();
			files = filePath.listFiles(filter);
			for (File serviceName : files) {
				String service = serviceName.getName().toString();
				serviceNames.add(service.substring(0, serviceName.getName().toString().lastIndexOf(".csv")));
			}
		} else {
			System.exit(-1);
		}
		return serviceNames;
	}

	/**
	 * Invoke Times Pie Chart Data Prepare
	 * 
	 * @param serviceName
	 * @return
	 * @throws IOException
	 */
	public DefaultPieDataset generatePieDataSet(String serviceName) throws IOException {
		for (File fileName : files) {
			if (fileName.getName().contains(serviceName)) {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String tempString = null;
				DefaultPieDataset pieDataSet = new DefaultPieDataset();
				while ((tempString = reader.readLine()) != null) {
					CSVParser parser = new CSVParser();
					if (!tempString.contains("ServiceName")) {
						String[] data = parser.parseLine(tempString);
						pieDataSet.setValue(data[1], Double.valueOf(data[2]));
					}
				}
				return pieDataSet;
			}
		}
		DefaultPieDataset pieDataSet = new DefaultPieDataset();
		pieDataSet.setValue("Apple", 100);
		pieDataSet.setValue("Pear", 200);
		pieDataSet.setValue("Grape", 365);
		pieDataSet.setValue("Banana", 385);
		pieDataSet.setValue("Lychee", 512);
		return pieDataSet;
	}

	public DefaultCategoryDataset generateDefaultCategoryDataset(String serviceName) throws NumberFormatException,
			IOException {
		for (File fileName : files) {
			if (fileName.getName().contains(serviceName)) {
				BufferedReader reader = new BufferedReader(new FileReader(fileName));
				String tempString = null;
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				while ((tempString = reader.readLine()) != null) {
					CSVParser parser = new CSVParser();
					if (!tempString.contains("ServiceName")) {
						String[] data = parser.parseLine(tempString);
						dataset.setValue(Double.valueOf(data[5]), "Min", data[1]);
						dataset.setValue(Double.valueOf(data[3]), "Avg", data[1]);
						dataset.setValue(Double.valueOf(data[4]), "Max", data[1]);
					}
				}
				return dataset;
			}
		}
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "北京", "苹果");
		dataset.addValue(100, "上海", "苹果");
		dataset.addValue(100, "广州", "苹果");
		dataset.addValue(200, "北京", "梨子");
		dataset.addValue(200, "上海", "梨子");
		dataset.addValue(200, "广州", "梨子");
		dataset.addValue(300, "北京", "葡萄");
		dataset.addValue(300, "上海", "葡萄");
		dataset.addValue(300, "广州", "葡萄");
		dataset.addValue(400, "北京", "香蕉");
		dataset.addValue(400, "上海", "香蕉");
		dataset.addValue(400, "广州", "香蕉");
		dataset.addValue(500, "北京", "荔枝");
		dataset.addValue(500, "上海", "荔枝");
		dataset.addValue(500, "广州", "荔枝");
		return dataset;
	}
}
