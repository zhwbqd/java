package com.hp.it.server.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
import com.hp.it.server.configuration.AggregateReportConstant;
import com.hp.it.server.configuration.ArtifactReportConstant;
import com.hp.it.server.configuration.GobalConstant;
import com.hp.it.server.context.DataSourcePoolContext;
import com.hp.it.server.context.ProjectContext;
import com.hp.it.sonar.bean.Project;
import com.hp.it.sonar.bean.Violation;
import com.hp.it.sonar.service.IViolationService;
import com.hp.it.sonar.service.impl.ViolationService;
import com.hp.it.sonar.service.impl.ViolationService.SeverityComp;
import com.hp.it.version.bean.LogEntry;
import com.hp.it.version.service.IVersionService;
import com.hp.it.version.service.impl.VersionService;

public class AggregateViolationChangeReportServlet extends AbstractReportServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String appContextPath = null;
		try {
			appContextPath = "http://" + InetAddress.getLocalHost().getCanonicalHostName() + ":" + req.getLocalPort()
					+ this.getServletContext().getContextPath();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String aggregateName = req.getParameter("Aggregate");
		if (aggregateName == null) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		Properties properties = ProjectContext.getProperties(aggregateName);

		int periodNum = Integer.valueOf(properties.getProperty(AggregateReportConstant.DEFAULT_PERIOD));
		if (req.getParameter("period") != null) {
			periodNum = Integer.valueOf(req.getParameter("period"));
		}
		String violationPriority = properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY);
		if (req.getParameter("priority") != null) {
			violationPriority = req.getParameter("violationPriority");
			violationPriority = violationPriority.toUpperCase();
		}

		AggregateReportTask task = new AggregateReportTask(aggregateName, periodNum, violationPriority, appContextPath);
		String content = task.generateAndSendEmail();
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			pw.print(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
		// ThreadPoolExecutor threadPool = (ThreadPoolExecutor)
		// applicationContext.getBean("threadPool");
		// threadPool.execute(task);
	}

	static class AggregateReportTask implements Runnable {
		private String kee;

		private int period;

		private String violationPriority;

		private Map<String, String> reportSummary;

		private Map<String, Map<String, String>> violationSummary;

		Map<String, Collection<Violation>> violationDetails;

		Map<String, LogEntry> versionDetails;

		public AggregateReportTask(String kee, int period, String violationPriority, String appContextPath) {
			this.kee = kee;
			this.period = period;
			this.violationPriority = violationPriority;
			reportSummary = new HashMap<String, String>();
			reportSummary.put("violationPeriod", String.valueOf(this.period));
			reportSummary.put("violationPriority", this.violationPriority);
			reportSummary.put("AppContextPath", appContextPath);
			reportSummary.put("reportDatetime", new Date().toString());
			violationSummary = new HashMap<String, Map<String, String>>();
			violationDetails = new TreeMap<String, Collection<Violation>>(new SeverityComp());
			if (violationPriority.equalsIgnoreCase("Blocker")) {
				violationDetails.put("Blocker", new ArrayList<Violation>());
			} else if (violationPriority.equalsIgnoreCase("Critical")) {
				violationDetails.put("Blocker", new ArrayList<Violation>());
				violationDetails.put("Critical", new ArrayList<Violation>());

			} else if (violationPriority.equalsIgnoreCase("Major")) {
				violationDetails.put("Blocker", new ArrayList<Violation>());
				violationDetails.put("Critical", new ArrayList<Violation>());
				violationDetails.put("Major", new ArrayList<Violation>());

			} else if (violationPriority.equalsIgnoreCase("Minor")) {
				violationDetails.put("Blocker", new ArrayList<Violation>());
				violationDetails.put("Critical", new ArrayList<Violation>());
				violationDetails.put("Major", new ArrayList<Violation>());
				violationDetails.put("Minor", new ArrayList<Violation>());

			} else if (violationPriority.equalsIgnoreCase("Info")) {
				violationDetails.put("Blocker", new ArrayList<Violation>());
				violationDetails.put("Critical", new ArrayList<Violation>());
				violationDetails.put("Major", new ArrayList<Violation>());
				violationDetails.put("Minor", new ArrayList<Violation>());
				violationDetails.put("Info", new ArrayList<Violation>());
			}

			versionDetails = new HashMap<String, LogEntry>();
		}

		public void run() {
			generateAndSendEmail();
		}

		public String generateAndSendEmail() {
			Properties properties = ProjectContext.getProperties(kee);
			if (properties == null) {
				// TODO
				// properties not found
				return null;
			}
			if (period == 0) {
				period = Integer.valueOf(properties.getProperty(AggregateReportConstant.DEFAULT_PERIOD));
			}
			if (violationPriority == null || "".equalsIgnoreCase(violationPriority)) {
				violationPriority = properties.getProperty(AggregateReportConstant.DEFAULT_PRIORITY);
			}
			retrieveAggregateViolationData(properties.getProperty(AggregateReportConstant.AGGREGATE_PROJECTS), period,
					violationPriority);

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
				template = ve.getTemplate("aggregateChangeReport.vm");
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			} catch (ParseErrorException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			IReportService reportService = new ReportService();
			String content = reportService.generateAggregateViolationChangeReport(template, reportSummary,
					violationSummary, violationDetails, versionDetails, ProjectContext.getContext());

			Date reportDate = new Date();
			IMailService mailService = new MailService();
			mailService.setSmtp("smtp3.hp.com");
			EMail mail = new EMail();
			mail.setFrom("no-reply@hp.com");
			Set<String> to = new HashSet<String>();
			String emailList = properties.getProperty(AggregateReportConstant.DEFAULT_EMAIL_LIST);

			for (String email : emailList.split(",")) {
				to.add(email);
			}

			mail.setDate(reportDate);
			mail.setSubject(kee + " violation change report " + reportDate);
			mail.setToList(to);

			// Set<String> cc = new HashSet<String>();
			// if (AdditionalRecipients != null)
			// {
			// for (String email : AdditionalRecipients.split(","))
			// {
			// to.add(email);
			// }
			// mail.setCcList(cc);
			// }

			mail.setContent(content);
			// mailService.sendMail(mail);
			return content;
		}

		private void retrieveAggregateViolationData(String aggregateProjects, int period, String violationPriority) {
			for (String projectKee : aggregateProjects.split("\\*")) {
				/*
				 * ViolationSumary retrieve
				 */
				Map<String, Map<String, String>> violationSummary = retrieveViolationSummary(projectKee, period,
						violationPriority);
				mergeViolationSummary(this.violationSummary, violationSummary);
				/*
				 * Violation details retrieve
				 */
				// Retrieve the violation information
				Map<String, Collection<Violation>> artifactViolationDetails = retrieveViolationsByPriority(projectKee,
						period, violationPriority);
				// Merge the violation details
				mergeViolationDetails(this.violationDetails, artifactViolationDetails);
				/*
				 * Version data info
				 */
				Map<String, LogEntry> versionDetails = retrieveVersionDetails(projectKee, artifactViolationDetails);
				mergeVersionDetails(this.versionDetails, versionDetails);
				// Merge the violation data info
			}
		}

		private Map<String, LogEntry> retrieveVersionDetails(String projectKee,
				Map<String, Collection<Violation>> artifactViolationDetails) {
			EncryptUtil encryptUtil = new EncryptUtil();
			Properties properties = ProjectContext.getProperties(projectKee);
			String[] id = projectKee.split(":");

			IVersionService versionService = new VersionService();
			Map<String, LogEntry> versionDetails = new HashMap<String, LogEntry>();
			try {
				String privateKeyFilePath = System.getProperty("user.home") + File.separator + GobalConstant.HOME_DIR
						+ File.separator + GobalConstant.WORKSPACE + File.separator + id[0] + "$" + id[1]
						+ File.separator + GobalConstant.PRIVATE_KEY;

				versionService.initialize(
						id[0],
						id[1],
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
				for (Collection<Violation> eachPriority : artifactViolationDetails.values()) {
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
			return versionDetails;
		}

		private Map<String, Map<String, String>> retrieveViolationSummary(String projectKee, int period2,
				String violationPriority2) {
			String[] id = projectKee.split(":");
			Properties properties = ProjectContext.getProperties(projectKee);

			DataSource dataSource = DataSourcePoolContext.getDataSource(id[0] + ":" + id[1]);

			IViolationService violationService = new ViolationService(dataSource,
					properties.getProperty(ArtifactReportConstant.PORTAL_URL));
			Map<String, String> violationSummary = violationService.retrieveViolationSummary(id[0], id[1], period);
			Map<String, Map<String, String>> retval = new TreeMap<String, Map<String, String>>();
			retval.put(projectKee, violationSummary);
			return retval;
		}

		private Map<String, Collection<Violation>> retrieveViolationsByPriority(String kee, int period,
				String violationPriority) {
			Properties properties = ProjectContext.getProperties(kee);

			String[] id = kee.split(":");

			DataSource cp = DataSourcePoolContext.getDataSource(id[0] + ":" + id[1]);

			IViolationService violationService = new ViolationService(cp,
					properties.getProperty(ArtifactReportConstant.PORTAL_URL));

			Map<String, Collection<Violation>> violationDetails = violationService.retrieveViolationDetails(id[0],
					id[1], period, violationPriority);
			return violationDetails;
		}

		private void mergeVersionDetails(Map<String, LogEntry> aggregateVersionDetails,
				Map<String, LogEntry> artifactVersionDetails) {
			aggregateVersionDetails.putAll(artifactVersionDetails);
		}

		private void mergeViolationSummary(Map<String, Map<String, String>> aggregateViolationSummay,
				Map<String, Map<String, String>> artifactViolationSummary) {
			aggregateViolationSummay.putAll(artifactViolationSummary);
		}

		/**
		 * 
		 * @param aggregate
		 * @param eachOne
		 */
		private void mergeViolationDetails(Map<String, Collection<Violation>> aggregate,
				Map<String, Collection<Violation>> eachOne) {
			for (Entry<String, Collection<Violation>> entry : aggregate.entrySet()) {
				Collection<Violation> artifactSeverityViolationList = eachOne.get(entry.getKey());
				Collection<Violation> aggregateSeverityViolationList = entry.getValue();

				if (artifactSeverityViolationList != null && artifactSeverityViolationList.size() != 0) {
					for (Violation violation : artifactSeverityViolationList) {
						if (aggregateSeverityViolationList.contains(violation)) {
							for (Violation original : aggregateSeverityViolationList) {
								if (original.equals(violation)) {
									original.setDelta(original.getDelta() + violation.getDelta());
									original.getProjects().addAll(violation.getProjects());
								}
							}
						} else {
							aggregateSeverityViolationList.add(violation);
						}

					}
				}
			}
		}
	}

}
