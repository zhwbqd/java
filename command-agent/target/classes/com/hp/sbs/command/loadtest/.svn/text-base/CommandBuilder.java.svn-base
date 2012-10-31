package com.hp.sbs.command.loadtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandBuilder {

	private CommandParams params;

	public CommandBuilder(String archiveName) {

		Properties properties = new Properties();
		InputStream inStream = null;
		try {
			inStream = new FileInputStream("loadtest.properties");
		} catch (FileNotFoundException e) {
			inStream = this.getClass().getClassLoader()
					.getResourceAsStream("loadtest.properties");
		}
		try {
			properties.load(inStream);
			inStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String controllerPath = properties.getProperty("controllerPath");
		String analysisPath = properties.getProperty("analysisPath");
		String scenario = properties.getProperty("scenario");
		String resultLocation = properties.getProperty("resultLocation");
		String templateName = properties.getProperty("templateName");
		String expireMonth = properties.getProperty("expireMonth");
		String reportAddress = properties.getProperty("reportAddress");
		
		params = new CommandParams();
		params.setControllerPath(controllerPath);
		params.setAnalysisPath(analysisPath);
		params.setTestPath(scenario);
		params.setResultLocation(resultLocation);
		params.setResultCleanName(archiveName);
		params.setResultPath(resultLocation + "\\" + archiveName + "\\"
				+ archiveName + ".lrr");
		params.setTemplateName(templateName);
		params.setExpireMonth(expireMonth);
		params.setReportAddress(reportAddress);
	}

	public CommandParams getCommandParams()
	{
	    return params;
	}
	
	public String buildControllerCommand() {

		String controllerCommand = params.getControllerPath() + " -TestPath "
				+ params.getTestPath() + " -ResultLocation "
				+ params.getResultLocation() + " -ResultCleanName "
				+ params.getResultCleanName() + " -Run";

		return controllerCommand;

	}

	public String buildAnalysisCommand() {

		String analysisCommand = params.getAnalysisPath() + " -ResultPath "
				+ params.getResultPath() + " -TemplateName "
				+ params.getTemplateName();

		return analysisCommand;

	}

}
