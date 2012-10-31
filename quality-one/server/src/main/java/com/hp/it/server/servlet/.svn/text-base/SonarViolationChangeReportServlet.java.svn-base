package com.hp.it.server.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.tmatesoft.svn.core.SVNException;

import com.hp.it.encrypt.EncryptUtil;
import com.hp.it.mail.bean.EMail;
import com.hp.it.mail.service.IMailService;
import com.hp.it.mail.service.impl.MailService;
import com.hp.it.report.service.IReportService;
import com.hp.it.report.service.impl.ReportService;
import com.hp.it.server.configuration.ArtifactReportConstant;
import com.hp.it.server.configuration.GobalConstant;
import com.hp.it.server.context.DataSourcePoolContext;
import com.hp.it.server.context.ProjectContext;
import com.hp.it.sonar.bean.Project;
import com.hp.it.sonar.bean.Violation;
import com.hp.it.sonar.service.IViolationService;
import com.hp.it.sonar.service.impl.ViolationService;
import com.hp.it.version.bean.LogEntry;
import com.hp.it.version.service.IVersionService;
import com.hp.it.version.service.impl.VersionService;

public class SonarViolationChangeReportServlet extends AbstractReportServlet {

	private static Logger logger = Logger.getLogger(SonarViolationChangeReportServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String groupId = req.getParameter("groupId");
		String artifactId = req.getParameter("artifactId");
		int periodNum = 0;
		String violationPriority = null;
		String additionalRecipients = null;

		if (req.getParameter("period") != null) {
			periodNum = Integer.valueOf(req.getParameter("period"));
		}

		if (req.getParameter("priority") != null) {
			violationPriority = req.getParameter("violationPriority");
			violationPriority = violationPriority.toUpperCase();
		}

		if (req.getParameter("AdditionalRecipients") != null) {
			additionalRecipients = req.getParameter("AdditionalRecipients");
		}

		ReportTask task = new ReportTask(groupId, artifactId, periodNum, violationPriority, additionalRecipients);

		if (req.getParameter("async") != null && "true".equalsIgnoreCase(req.getParameter("async"))) {
			ThreadPoolExecutor threadPool = (ThreadPoolExecutor) this.getApplicationContext().getBean("threadPool");
			threadPool.execute(task);
		} else {
			String retval = task.generateAndSendEmail();
			pw.print(retval);
		}

		pw.close();
	}

	static class ReportTask implements Runnable {

		private String groupId;
		private String artifactId;
		private int period;
		private String violationPriority;
		private String AdditionalRecipients;

		public ReportTask(String groupId, String artifactId, int period, String violationPriority,
				String AdditionalRecipients) {
			this.groupId = groupId;
			this.artifactId = artifactId;
			this.period = period;
			this.violationPriority = violationPriority;
			this.AdditionalRecipients = AdditionalRecipients;
		}

		public void run() {
			generateAndSendEmail();
		}

		public String generateAndSendEmail() {
			Properties properties = ProjectContext.getProperties(groupId + ":" + artifactId);

			if (properties == null) {
				return "properties not found";
			}
			if (period == 0) {
				period = Integer.valueOf(properties.getProperty(ArtifactReportConstant.DEFAULT_PERIOD));
			}
			if (violationPriority == null || "".equalsIgnoreCase(violationPriority)) {
				violationPriority = properties.getProperty(ArtifactReportConstant.DEFAULT_PRIORITY);
			}

			DataSource dataSource = DataSourcePoolContext.getDataSource(groupId + ":" + artifactId);

			IViolationService violationService = new ViolationService(dataSource,
					properties.getProperty(ArtifactReportConstant.PORTAL_URL));
			Map<String, String> violationSummary = violationService.retrieveViolationSummary(groupId, artifactId,
					period);

			Map<String, Collection<Violation>> violationDetails = violationService.retrieveViolationDetails(groupId,
					artifactId, period, violationPriority);

			IVersionService versionService = new VersionService();
			Map<String, LogEntry> versionDetails = new HashMap<String, LogEntry>();
			try {
				EncryptUtil encryptUtil = new EncryptUtil();
				String privateKeyFilePath = System.getProperty("user.home") + File.separator + GobalConstant.HOME_DIR
						+ File.separator + GobalConstant.WORKSPACE + File.separator + groupId + "$" + artifactId
						+ File.separator + GobalConstant.PRIVATE_KEY;

				versionService.initialize(
						groupId,
						artifactId,
						properties.getProperty(ArtifactReportConstant.VER_URL),
						properties.getProperty(ArtifactReportConstant.VER_USER),
						properties.getProperty(ArtifactReportConstant.VER_PWD) == null
								|| properties.getProperty(ArtifactReportConstant.VER_PWD).length() == 0 ? properties
								.getProperty(ArtifactReportConstant.VER_PWD) : encryptUtil.decrypt(properties
								.getProperty(ArtifactReportConstant.VER_PWD)),
						Boolean.valueOf(properties.getProperty(ArtifactReportConstant.VER_PRV)),
						privateKeyFilePath,
						properties.getProperty(ArtifactReportConstant.VER_PPWD) == null
								|| properties.getProperty(ArtifactReportConstant.VER_PWD).length() == 0 ? properties
								.getProperty(ArtifactReportConstant.VER_PPWD) : encryptUtil.decrypt(properties
								.getProperty(ArtifactReportConstant.VER_PPWD)));

				Set<String> qNameSet = new HashSet<String>();
				for (Collection<Violation> eachPriority : violationDetails.values()) {
					for (Violation eachViolation : eachPriority) {
						for (Project project : eachViolation.getProjects()) {
							qNameSet.add(project.getLongName());
						}
					}
				}

				versionDetails = versionService.findLatestLogEntriesByQualifiedClassNames(qNameSet);

			} catch (SVNException e) {
				e.printStackTrace();
			}

			IReportService reportService = new ReportService();
			Map<String, String> reportSummary = new HashMap<String, String>();
			reportSummary.put("kee", groupId + ":" + artifactId);
			reportSummary.put("reportDatetime", new Date().toString());
			reportSummary.put("violationPriority", violationPriority);
			reportSummary.put("headVersion", String.valueOf(versionService.getLatestVersion()));
			reportSummary.put("portalURL", properties.getProperty(ArtifactReportConstant.PORTAL_URL));
			reportSummary.put("period", String.valueOf(period));

			VelocityEngine ve = new VelocityEngine();
			try {
				Properties p = new Properties();
				p.setProperty("file.resource.loader.path", System.getProperty("user.home") + File.separator
						+ GobalConstant.HOME_DIR + File.separator + GobalConstant.TEMPLATE_DIR);
				ve.init(p);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Template template = null;
			try {
				template = ve.getTemplate("violationChangeReport.vm");
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			} catch (ParseErrorException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			String content = reportService.generateViolationChangeReport(template, reportSummary, violationSummary,
					violationDetails, versionDetails);

			/*
			 * Sending the email;
			 */
			Date reportDate = new Date();
			IMailService mailService = new MailService();
			mailService.setSmtp("smtp3.hp.com");
			EMail mail = new EMail();
			mail.setFrom("no-reply@hp.com");
			Set<String> to = new HashSet<String>();
			String emailList = properties.getProperty(ArtifactReportConstant.DEFAULT_EMAIL_LIST);

			for (String email : emailList.split(",")) {
				to.add(email);
			}

			String periodDec = "";
			if (period == 1) {
				periodDec = "SincePrevious";
			} else if (period == 2) {
				periodDec = "Weekly";
			} else if (period == 3) {
				periodDec = "Monthly";
			}

			mail.setDate(reportDate);
			mail.setSubject(artifactId + " violation change report  [" + periodDec + "]" + "  [" + reportDate + "]");
			mail.setToList(to);

			Set<String> cc = new HashSet<String>();
			if (AdditionalRecipients != null) {
				for (String email : AdditionalRecipients.split(",")) {
					to.add(email);
				}
				mail.setCcList(cc);
			}

			/**/
			if (mail.getToList().size() != 0 || mail.getCcList().size() != 0) {
				mail.setContent(content);
				mailService.sendMail(mail);
			}
			return content;
		}
	}
}
