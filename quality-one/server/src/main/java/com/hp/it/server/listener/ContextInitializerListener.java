package com.hp.it.server.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hp.it.server.configuration.AggregateReportConstant;
import com.hp.it.server.configuration.ArtifactReportConstant;
import com.hp.it.server.configuration.GobalConstant;
import com.hp.it.server.context.ProjectContext;

public class ContextInitializerListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent sce)
	{
		/* workspace directory */
		File workspace = new File(System.getProperty("user.home") + File.separator + GobalConstant.HOME_DIR
				+ File.separator + GobalConstant.WORKSPACE);
		if (!workspace.exists())
		{
			workspace.mkdirs();
		}

		if (workspace.isDirectory())
		{
			for (File projectDir : workspace.listFiles())
			{
				if (!projectDir.isDirectory())
				{
					continue;
				}

				String[] aggregate = projectDir.getName().split("\\$");
				if (aggregate.length != 0 && aggregate[0].equalsIgnoreCase("aggregate"))
				{
					for (File file : projectDir.listFiles())
					{

						if (file.isFile() && file.getName().equalsIgnoreCase(GobalConstant.CONFIG_PROPERTIES))
						{
							try
							{
								Properties properties = new Properties();
								FileInputStream fis = new FileInputStream(file);
								properties.load(fis);
								ProjectContext.setProperties(
										(properties.getProperty(AggregateReportConstant.AGGREGATE)), properties);
								fis.close();
							} catch (FileNotFoundException e)
							{
								e.printStackTrace();
							} catch (IOException e)
							{
								e.printStackTrace();
							}
						}
					}
					continue;
				}

				for (File file : projectDir.listFiles())
				{
					if (file.isFile() && file.getName().equalsIgnoreCase(GobalConstant.CONFIG_PROPERTIES))
					{
						// String extension =
						// fileName.substring(fileName.lastIndexOf(".") + 1);
						try
						{
							Properties properties = new Properties();
							FileInputStream fis = new FileInputStream(file);
							properties.load(fis);
							ProjectContext.setProperties((properties.getProperty(ArtifactReportConstant.KEE)),
									properties);
							fis.close();
						} catch (FileNotFoundException e)
						{
							e.printStackTrace();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}

			}
		}

		/* template directory */
		String templateDirPath = System.getProperty("user.home") + File.separator + GobalConstant.HOME_DIR
				+ File.separator + GobalConstant.TEMPLATE_DIR;
		File templateDir = new File(templateDirPath);
		if (!templateDir.exists())
		{
			templateDir.mkdir();
		}
		/* copy the template file to working directory */

		copyTemplateFile("violationChangeReport.vm",templateDirPath);
		copyTemplateFile("aggregateChangeReport.vm",templateDirPath);

	}

	public void contextDestroyed(ServletContextEvent sce)
	{

	}

	private void install()
	{

	}
	
	private void copyTemplateFile(String filename,String dest)
	{
		try
		{
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
			FileOutputStream fos = new FileOutputStream(dest+ File.separator +filename);
			do
			{
				byte[] by = new byte[1024];
				is.read(by, 0, 1024);
				fos.write(by);
			} while (is.available() > 0);
			is.close();
			fos.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
