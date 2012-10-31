package com.hp.it.sonar.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import com.hp.it.sonar.access.PortalAccessor;
import com.hp.it.sonar.access.ProjectAccessor;
import com.hp.it.sonar.access.RuleAccessor;
import com.hp.it.sonar.access.impl.PortalDataAccessor;
import com.hp.it.sonar.access.impl.ProjectDataAccessor;
import com.hp.it.sonar.access.impl.RuleDataAccessor;
import com.hp.it.sonar.bean.Project;
import com.hp.it.sonar.bean.Violation;
import com.hp.it.sonar.service.IViolationService;

public class ViolationService implements IViolationService
{
	ProjectAccessor projectDao;

	RuleAccessor ruleDao;

	PortalAccessor portalDao;

	public ViolationService(DataSource ds, String portalURL)
	{
		initizlize(ds, portalURL);
	}

	private void initizlize(DataSource ds, String portalURL)
	{
		projectDao = new ProjectDataAccessor(ds);
		ruleDao = new RuleDataAccessor(ds);
		portalDao = new PortalDataAccessor(portalURL);
		portalDao.setProjectDao(projectDao);
		portalDao.setRuleDao(ruleDao);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.service.impl.IViolationService#retrieveViolationSummary
	 * (java.lang.String, java.lang.String, int)
	 */
	public Map<String, String> retrieveViolationSummary(String groupId, String artifactId, int period)
	{
		Map retval = portalDao.retrieveViolationChangeSummary(groupId, artifactId, period);
		Map sortedMap = new TreeMap(new SeverityComp());
		sortedMap.putAll(retval);
		return sortedMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.service.impl.IViolationService#retrieveViolationDetails
	 * (java.lang.String, java.lang.String, int, java.lang.String)
	 */
	public Map<String, Collection<Violation>> retrieveViolationDetails(String groupId, String artifactId, int period,
			String violationPriority)
	{
		Map<String, Collection<Violation>> violationDetails = new TreeMap<String, Collection<Violation>>(
				new SeverityComp());

		Collection<Violation> blockers = retrieveViolationDetailsByPriority(groupId, artifactId, period, "BLOCKER");
		violationDetails.put("BLOCKER", blockers);
		if (!"BLOCKER".equalsIgnoreCase(violationPriority))
		{
			Collection<Violation> criticals = retrieveViolationDetailsByPriority(groupId, artifactId, period,
					"CRITICAL");
			violationDetails.put("CRITICAL", criticals);
			if (!"CRITICAL".equalsIgnoreCase(violationPriority))
			{
				Collection<Violation> majors = retrieveViolationDetailsByPriority(groupId, artifactId, period, "MAJOR");
				violationDetails.put("MAJOR", majors);
				if (!"MAJOR".equalsIgnoreCase(violationPriority))
				{
					Collection<Violation> minors = retrieveViolationDetailsByPriority(groupId, artifactId, period,
							"MINOR");
					violationDetails.put("MINOR", minors);
					if (!"MINOR".equalsIgnoreCase(violationPriority))
					{
						Collection<Violation> infos = retrieveViolationDetailsByPriority(groupId, artifactId, period,
								"INFO");
						violationDetails.put("INFO", infos);
					}
				}
			}
		}
		return violationDetails;
	}

	public Collection<Violation> retrieveViolationDetailsByPriority(String groupId, String artifactId, int period,
			String violationPriority)
	{
		Project project = projectDao.getProject(groupId, artifactId);
		Collection<Violation> violations = new ArrayList<Violation>();
		recursive(project, violations, period, violationPriority);
		return violations;
	}

	private void recursive(Project project, Collection<Violation> violations, int period, String violationPriority)
	{
		if (!"PRJ".equalsIgnoreCase(project.getScope()))
		{
			// Not a project , return
			return;
		}

		if ("BRC".equalsIgnoreCase(project.getQualifier()))
		{
			// URL SEARCH
			portalDao.retrieveRecentChange(project, violations, period, violationPriority);
		} else if ("TRK".equalsIgnoreCase(project.getQualifier()))
		{
			Collection<Project> childs = projectDao.getProjectsByRootId(project.getId());

			boolean flag = false;
			for (Project p : childs)
			{
				if (!p.getScope().equalsIgnoreCase("PRJ"))
				{
					flag = true;
					break;
				}
			}
			if (flag)
			{
				portalDao.retrieveRecentChange(project, violations, period, violationPriority);
			} else
			{
				for (Project p : childs)
				{
					// TODO we can add some project filter here
					recursive(p, violations, period, violationPriority);
				}
			}
		}
	}

	public static class SeverityComp implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			if ("BLOCKER".equalsIgnoreCase(o1.toString()) && !"BLOCKER".equalsIgnoreCase(o2.toString()))
			{
				return -1;
			} else if ("BLOCKER".equalsIgnoreCase(o2.toString()) && !"BLOCKER".equalsIgnoreCase(o1.toString()))
			{
				return 1;
			} else if ("BLOCKER".equalsIgnoreCase(o2.toString()) && "BLOCKER".equalsIgnoreCase(o1.toString()))
			{
				return 0;
			}

			if ("CRITICAL".equalsIgnoreCase(o1.toString()) && !"CRITICAL".equalsIgnoreCase(o2.toString()))
			{
				return -1;
			} else if ("CRITICAL".equalsIgnoreCase(o2.toString()) && !"CRITICAL".equalsIgnoreCase(o1.toString()))
			{
				return 1;
			} else if ("CRITICAL".equalsIgnoreCase(o2.toString()) && "CRITICAL".equalsIgnoreCase(o1.toString()))
			{
				return 0;
			}

			if ("MAJOR".equalsIgnoreCase(o1.toString()) && !"MAJOR".equalsIgnoreCase(o2.toString()))
			{
				return -1;
			} else if ("MAJOR".equalsIgnoreCase(o2.toString()) && !"MAJOR".equalsIgnoreCase(o1.toString()))
			{
				return 1;
			} else if ("MAJOR".equalsIgnoreCase(o2.toString()) && "MAJOR".equalsIgnoreCase(o1.toString()))
			{
				return 0;
			}

			if ("MINOR".equalsIgnoreCase(o1.toString()) && !"MINOR".equalsIgnoreCase(o2.toString()))
			{
				return -1;
			} else if ("MINOR".equalsIgnoreCase(o2.toString()) && !"MINOR".equalsIgnoreCase(o1.toString()))
			{
				return 1;
			} else if ("MINOR".equalsIgnoreCase(o2.toString()) && "MINOR".equalsIgnoreCase(o1.toString()))
			{
				return 0;
			}

			if ("INFO".equalsIgnoreCase(o1.toString()) && !"INFO".equalsIgnoreCase(o2.toString()))
			{
				return -1;
			} else if ("INFO".equalsIgnoreCase(o2.toString()) && !"INFO".equalsIgnoreCase(o1.toString()))
			{
				return 1;
			} else if ("INFO".equalsIgnoreCase(o2.toString()) && "INFO".equalsIgnoreCase(o1.toString()))
			{
				return 0;
			}
			return 0;
		}
	}
}
