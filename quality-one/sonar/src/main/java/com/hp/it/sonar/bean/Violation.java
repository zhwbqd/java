package com.hp.it.sonar.bean;

import java.util.Collection;

public class Violation
{
	private Collection<Project> projects;

	private Rule rule;

	private int delta;

	private boolean ascend;

	public Collection<Project> getProjects()
	{
		return projects;
	}

	public void setProjects(Collection<Project> projects)
	{
		this.projects = projects;
	}

	public Rule getRule()
	{
		return rule;
	}

	public void setRule(Rule rule)
	{
		this.rule = rule;
	}

	public int getDelta()
	{
		return delta;
	}

	public void setDelta(int delta)
	{
		this.delta = delta;
	}

	public boolean isAscend()
	{
		return ascend;
	}

	public void setAscend(boolean ascend)
	{
		this.ascend = ascend;
	}

}