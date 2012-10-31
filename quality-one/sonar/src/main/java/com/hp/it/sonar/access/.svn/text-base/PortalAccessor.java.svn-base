package com.hp.it.sonar.access;

import java.util.Collection;
import java.util.Map;

import com.hp.it.sonar.bean.Project;
import com.hp.it.sonar.bean.Violation;

public interface PortalAccessor
{
	public Map<String, String> retrieveViolationChangeSummary(String group, String artifact, int period);

	public void retrieveRecentChange(Project root, Collection<Violation> violations, int period,
			String violationPriority);

	public void setProjectDao(ProjectAccessor projectDao);

	public void setRuleDao(RuleAccessor ruleDao);
}