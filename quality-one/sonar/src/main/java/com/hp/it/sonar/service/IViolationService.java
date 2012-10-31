package com.hp.it.sonar.service;

import java.util.Collection;
import java.util.Map;

import com.hp.it.sonar.bean.Violation;

public interface IViolationService
{
	public Map<String, String> retrieveViolationSummary(String groupId, String artifactId, int period);

	public Map<String, Collection<Violation>> retrieveViolationDetails(String groupId, String artifactId, int period,
			String violationPriority);

}