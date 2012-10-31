package com.hp.it.report.service;

import java.util.Map;

import org.apache.velocity.Template;

public interface IReportService
{
	public String generateViolationChangeReport(Map reportSummary, Map violationSummary, Map violationDetails,
			Map versionDetails);

	public String generateViolationChangeReport(Template template, Map reportSummary, Map violationSummary,
			Map violationDetails, Map versionDetails);

	public String generateAggregateViolationChangeReport(Template template, Map reportSummary,
			Map<String, Map<String, String>> violationSummary, Map violationDetails, Map versionDetails, Map propertiesContext);

	public void storeDocumation(String content);
}
