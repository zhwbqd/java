package com.hp.it.sonar.bean;

public class Project
{
	private int id;

	private String name;

	private String description;

	private String scope;

	private String qualifier;

	private String kee;

	private int rootId;

	private String longName;

	private int violationDelta;

	public int getViolationDelta()
	{
		return violationDelta;
	}

	public void setViolationDelta(int violationDelta)
	{
		this.violationDelta = violationDelta;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getScope()
	{
		return scope;
	}

	public void setScope(String scope)
	{
		this.scope = scope;
	}

	public String getQualifier()
	{
		return qualifier;
	}

	public void setQualifier(String qualifier)
	{
		this.qualifier = qualifier;
	}

	public String getKee()
	{
		return kee;
	}

	public void setKee(String kee)
	{
		this.kee = kee;
	}

	public int getRootId()
	{
		return rootId;
	}

	public void setRootId(int rootId)
	{
		this.rootId = rootId;
	}

	public String getLongName()
	{
		return longName;
	}

	public void setLongName(String longName)
	{
		this.longName = longName;
	}

}
