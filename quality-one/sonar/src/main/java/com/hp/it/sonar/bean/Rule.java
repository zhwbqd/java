package com.hp.it.sonar.bean;

public class Rule
{
	private long id;

	private String pluginRuleKey;

	private String pluginName;

	private String description;

	private String cardinality;

	private long parentId;

	private String name;

	private int priority;

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getPluginRuleKey()
	{
		return pluginRuleKey;
	}

	public void setPluginRuleKey(String pluginRuleKey)
	{
		this.pluginRuleKey = pluginRuleKey;
	}

	public String getPluginName()
	{
		return pluginName;
	}

	public void setPluginName(String pluginName)
	{
		this.pluginName = pluginName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCardinality()
	{
		return cardinality;
	}

	public void setCardinality(String cardinality)
	{
		this.cardinality = cardinality;
	}

	public long getParentId()
	{
		return parentId;
	}

	public void setParentId(long parentId)
	{
		this.parentId = parentId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Rule))
		{
			return false;
		}
		if (this.pluginRuleKey.equalsIgnoreCase(((Rule) obj).getPluginRuleKey())
				&& this.pluginName.equalsIgnoreCase(((Rule) obj).getPluginName()))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return pluginRuleKey.hashCode() + pluginName.hashCode();
	}
}
