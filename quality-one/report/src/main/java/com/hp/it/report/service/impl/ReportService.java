package com.hp.it.report.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.hp.it.report.service.IReportService;

public class ReportService implements IReportService {
	public String generateViolationChangeReport(Map reportSummary, Map violationSummary, Map violationDetails,
			Map versionDetails) {
		StringWriter w = new StringWriter();
		try {
			Properties p = new Properties();
			p.setProperty("file.resource.loader.path",
					"C:\\Workspace\\WorkingCopy\\Quality-one\\quality-one\\report\\src\\main\\resources");
			Velocity.init(p);
			VelocityContext context = new VelocityContext();

			context.put("reportSummary", reportSummary);
			context.put("violationSummary", violationSummary);
			context.put("violationDetails", violationDetails);
			context.put("versionDetails", versionDetails);

			Velocity.mergeTemplate("violationChangeReport.vm", context, w);

			/* stream out content */
			// File file = new File("C:\\Users\\zhongch\\Desktop\\file.html");
			// FileWriter fw = new FileWriter(file);
			// String temp = w.toString();
			// System.out.println(w.toString());
			// fw.write(temp);
			// fw.close();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return w.toString();
	}

	public String generateViolationChangeReport(Template template, Map reportSummary, Map violationSummary,
			Map violationDetails, Map versionDetails) {
		StringWriter w = new StringWriter();

		VelocityContext context = new VelocityContext();

		context.put("reportSummary", reportSummary);
		context.put("violationSummary", violationSummary);
		context.put("violationDetails", violationDetails);
		context.put("versionDetails", versionDetails);
		DateFormat format = DateFormat.getDateTimeInstance(3, 3);
		context.put("dateFormat", format);

		try {
			template.merge(context, w);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w.toString();
	}

	public String generateAggregateViolationChangeReport(Template template, Map reportSummary,
			Map<String, Map<String, String>> violationSummary, Map violationDetails, Map versionDetails,
			Map propertiesContext) {
		StringWriter w = new StringWriter();

		VelocityContext context = new VelocityContext();

		context.put("reportSummary", reportSummary);
		context.put("aggregateSummary", violationSummary);
		context.put("violationDetails", violationDetails);
		context.put("versionDetails", versionDetails);
		DateFormat format = DateFormat.getDateTimeInstance(3, 3);
		context.put("dateFormat", format);
		context.put("projectContext", propertiesContext);

		try {
			template.merge(context, w);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return w.toString();
	}

	public static void main(String args[]) {
		StringWriter w = new StringWriter();
		try {
			Properties p = new Properties();
			p.setProperty("file.resource.loader.path", "C:\\Users\\Berial\\Desktop\\");
			Velocity.init(p);
			VelocityContext context = new VelocityContext();
			Set imports = new HashSet();
			imports.add("org.apache.velocity.Template");
			context.put("imports", imports);
			context.put("serviceName", "ServiceAAA");
			context.put("methodName", "methodBBB");
			ArrayList<Class> className = new ArrayList();
			className.add(String.class);
			className.add(ArrayList.class);
			context.put("paramsClass", className);

			ArrayList<String> paramName = new ArrayList();
			paramName.add("str");
			paramName.add("array");
			context.put("paramName", paramName);

			Velocity.mergeTemplate("html.vm", context, w);

			/* stream out content */
			// File file = new File("C:\\Users\\zhongch\\Desktop\\file.html");
			// FileWriter fw = new FileWriter(file);
			// String temp = w.toString();
			// System.out.println(w.toString());
			// fw.write(temp);
			// fw.close();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (MethodInvocationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void storeDocumation(String content) {

	}
}
