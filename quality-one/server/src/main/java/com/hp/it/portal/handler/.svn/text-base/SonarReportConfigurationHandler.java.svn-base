package com.hp.it.portal.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import com.hp.it.encrypt.EncryptUtil;

import javax.servlet.http.HttpServletRequest;

import com.hp.it.server.configuration.AggregateReportConstant;
import com.hp.it.server.context.ProjectContext;

public class SonarReportConfigurationHandler {
	public boolean handle(String action, HttpServletRequest req) {
		boolean result = false;
		if ("U".equalsIgnoreCase(action)) {
			result = update(req);
		} else if ("C".equalsIgnoreCase(action)) {
			result = create(req);
		} else if ("D".equalsIgnoreCase(action)) {
			result = delete(req);
		}
		return result;
	}

	private boolean update(HttpServletRequest req) {
		if (req.getParameter("KEE") != null) {
			Properties properties = new Properties();
			properties.setProperty("KEE", req.getParameter("KEE"));
			properties.setProperty("PORTAL_URL", req.getParameter("PORTAL_URL"));
			properties.setProperty("DB_DRIVER", req.getParameter("DB_DRIVER"));
			properties.setProperty("DB_URL", req.getParameter("DB_URL"));
			properties.setProperty("DB_USER", req.getParameter("DB_USER"));
			properties.setProperty(
					"DB_PWD",
					req.getParameter("DB_PWD") == null || req.getParameter("DB_PWD").length() == 0 ? req
							.getParameter("DB_PWD") : new EncryptUtil().encrypt(req.getParameter("DB_PWD")));
			properties.setProperty("VER_URL", req.getParameter("VER_URL"));
			properties.setProperty("VER_USER", req.getParameter("VER_USER"));
			properties.setProperty(
					"VER_PWD",
					req.getParameter("VER_PWD") == null || req.getParameter("VER_PWD").length() == 0 ? req
							.getParameter("VER_PWD") : new EncryptUtil().encrypt(req.getParameter("VER_PWD")));
			properties.setProperty("VER_PRV", req.getParameter("VER_PRV"));
			properties.setProperty(
					"VER_PPWD",
					req.getParameter("VER_PPWD") == null || req.getParameter("VER_PPWD").length() == 0 ? req
							.getParameter("VER_PPWD") : new EncryptUtil().encrypt(req.getParameter("VER_PPWD")));
			properties.setProperty("DEFAULT_PRIORITY", req.getParameter("DEFAULT_PRIORITY"));
			properties.setProperty("DEFAULT_PERIOD", req.getParameter("DEFAULT_PERIOD"));
			properties.setProperty("DEFAULT_EMAIL(S)", req.getParameter("DEFAULT_EMAIL(S)"));
			File workspace = new File(System.getProperty("user.home") + File.separator + ".quality-one"
					+ File.separator + "workspace");
			if (workspace.isDirectory()) {
				for (File project : workspace.listFiles()) {
					if (!project.isDirectory()) {
						continue;
					}
					if (!project.getName().equalsIgnoreCase(req.getParameter("KEE").replace(':', '$'))) {
						continue;
					}

					for (File file : project.listFiles()) {
						if (file.isFile() && file.getName().equalsIgnoreCase("config.properties")) {
							try {
								FileOutputStream fos = new FileOutputStream(file);
								properties.store(new FileOutputStream(file), "");
								fos.close();
								ProjectContext.setProperties(req.getParameter("KEE"), properties);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} else if (req.getParameter(AggregateReportConstant.AGGREGATE) != null) {
			Properties properties = new Properties();
			properties.setProperty(AggregateReportConstant.AGGREGATE,
					req.getParameter(AggregateReportConstant.AGGREGATE));

			properties.setProperty(AggregateReportConstant.DEFAULT_PERIOD,
					req.getParameter(AggregateReportConstant.DEFAULT_PERIOD));

			properties.setProperty(AggregateReportConstant.DEFAULT_PRIORITY,
					req.getParameter(AggregateReportConstant.DEFAULT_PRIORITY));

			properties.setProperty(AggregateReportConstant.DEFAULT_EMAIL_LIST,
					req.getParameter(AggregateReportConstant.DEFAULT_EMAIL_LIST));

			properties.setProperty(AggregateReportConstant.AGGREGATE_PROJECTS,
					req.getParameter(AggregateReportConstant.AGGREGATE_PROJECTS));

			File workspace = new File(System.getProperty("user.home") + File.separator + ".quality-one"
					+ File.separator + "workspace");
			if (workspace.isDirectory()) {
				for (File project : workspace.listFiles()) {
					if (!project.isDirectory()) {
						continue;
					}
					if (!project.getName().contains(req.getParameter(AggregateReportConstant.AGGREGATE))
							|| !project.getName().contains("aggregate")) {
						continue;
					}

					for (File file : project.listFiles()) {
						if (file.isFile() && file.getName().equalsIgnoreCase("config.properties")) {
							try {
								FileOutputStream fos = new FileOutputStream(file);
								properties.store(new FileOutputStream(file), "");
								fos.close();
								ProjectContext.setProperties(req.getParameter(AggregateReportConstant.AGGREGATE),
										properties);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return true;
	}

	private boolean create(HttpServletRequest req) {
		Properties properties = new Properties();
		if (req.getParameter("KEE") != null) {
			properties.setProperty("KEE", req.getParameter("KEE"));
			properties.setProperty("PORTAL_URL", req.getParameter("PORTAL_URL"));
			properties.setProperty("DB_DRIVER", req.getParameter("DB_DRIVER"));
			properties.setProperty("DB_URL", req.getParameter("DB_URL"));
			properties.setProperty("DB_USER", req.getParameter("DB_USER"));
			properties.setProperty(
					"DB_PWD",
					req.getParameter("DB_PWD") == null || req.getParameter("DB_PWD").length() == 0 ? req
							.getParameter("DB_PWD") : new EncryptUtil().encrypt(req.getParameter("DB_PWD")));
			properties.setProperty("VER_URL", req.getParameter("VER_URL"));
			properties.setProperty("VER_USER", req.getParameter("VER_USER"));
			properties.setProperty(
					"VER_PWD",
					req.getParameter("VER_PWD") == null || req.getParameter("VER_PWD").length() == 0 ? req
							.getParameter("VER_PWD") : new EncryptUtil().encrypt(req.getParameter("VER_PWD")));
			properties.setProperty("VER_PRV", req.getParameter("VER_PRV"));
			properties.setProperty(
					"VER_PPWD",
					req.getParameter("VER_PPWD") == null || req.getParameter("VER_PPWD").length() == 0 ? req
							.getParameter("VER_PPWD") : new EncryptUtil().encrypt(req.getParameter("VER_PPWD")));
			properties.setProperty("DEFAULT_PRIORITY", req.getParameter("DEFAULT_PRIORITY"));
			properties.setProperty("DEFAULT_PERIOD", req.getParameter("DEFAULT_PERIOD"));
			properties.setProperty("DEFAULT_EMAIL(S)", req.getParameter("DEFAULT_EMAIL(S)"));
			File workspace = new File(System.getProperty("user.home") + File.separator + ".quality-one"
					+ File.separator + "workspace");
			if (workspace.isDirectory()) {
				File projectDir = new File(System.getProperty("user.home") + File.separator + ".quality-one"
						+ File.separator + "workspace" + File.separator + req.getParameter("KEE").replace(':', '$'));
				if (!projectDir.exists()) {
					projectDir.mkdir();
				}
				File configFile = new File(System.getProperty("user.home") + File.separator + ".quality-one"
						+ File.separator + "workspace" + File.separator + req.getParameter("KEE").replace(':', '$')
						+ File.separator + "config.properties");
				if (!configFile.exists()) {
					try {
						configFile.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					FileOutputStream fos = new FileOutputStream(configFile);
					properties.store(new FileOutputStream(configFile), null);
					fos.close();
					ProjectContext.setProperties(req.getParameter("KEE"), properties);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return true;
		} else if (req.getParameter(AggregateReportConstant.AGGREGATE) != null) {
			properties.setProperty(AggregateReportConstant.AGGREGATE,
					req.getParameter(AggregateReportConstant.AGGREGATE));

			properties.setProperty(AggregateReportConstant.DEFAULT_PERIOD,
					req.getParameter(AggregateReportConstant.DEFAULT_PERIOD));

			properties.setProperty(AggregateReportConstant.DEFAULT_PRIORITY,
					req.getParameter(AggregateReportConstant.DEFAULT_PRIORITY));

			properties.setProperty(AggregateReportConstant.DEFAULT_EMAIL_LIST,
					req.getParameter(AggregateReportConstant.DEFAULT_EMAIL_LIST));

			properties.setProperty(AggregateReportConstant.AGGREGATE_PROJECTS,
					req.getParameter(AggregateReportConstant.AGGREGATE_PROJECTS));

			File projectDir = new File(System.getProperty("user.home") + File.separator + ".quality-one"
					+ File.separator + "workspace" + File.separator + "aggregate$"
					+ req.getParameter(AggregateReportConstant.AGGREGATE));
			if (!projectDir.exists()) {
				projectDir.mkdir();
			}
			File configFile = new File(System.getProperty("user.home") + File.separator + ".quality-one"
					+ File.separator + "workspace" + File.separator + "aggregate$"
					+ req.getParameter(AggregateReportConstant.AGGREGATE) + File.separator + "config.properties");
			if (!configFile.exists()) {
				try {
					configFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(configFile);
				properties.store(new FileOutputStream(configFile), null);
				fos.close();
				ProjectContext.setProperties(req.getParameter(AggregateReportConstant.AGGREGATE), properties);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	private boolean delete(HttpServletRequest req) {
		if (req.getParameter("KEE") != null) {
			String kee = req.getParameter("KEE");
			File project = new File(System.getProperty("user.home") + File.separator + ".quality-one" + File.separator
					+ "workspace" + File.separator + req.getParameter("KEE").replace(':', '$'));
			if (project.exists()) {
				if (project.isDirectory()) {
					for (File file : project.listFiles()) {
						file.delete();
					}
				}
				project.delete();
			}
		} else if (req.getParameter(AggregateReportConstant.AGGREGATE) != null) {
			File project = new File(System.getProperty("user.home") + File.separator + ".quality-one" + File.separator
					+ "workspace" + File.separator + "aggregate$" + req.getParameter(AggregateReportConstant.AGGREGATE));
			if (project.exists()) {
				if (project.isDirectory()) {
					for (File file : project.listFiles()) {
						file.delete();
					}
				}
				project.delete();
			}
		}
		return true;
	}
}
