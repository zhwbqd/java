package com.hp.it.sonar.access.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.hp.it.sonar.access.RuleAccessor;
import com.hp.it.sonar.bean.Rule;

public class RuleDataAccessor implements RuleAccessor
{
	private static String SELECT = "select * from rules r ";

	private DataSource dataSource;

	public RuleDataAccessor(DataSource pool)
	{
		dataSource = pool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hp.it.sonar.dao.impl.RuleAccessor#getRuleById(int)
	 */
	public Rule getRuleById(int id)
	{
		Rule rule = new Rule();
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT
					+ "where r.id = ?");
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			if (set.next())
			{
				rule.setId(set.getInt("id"));
				rule.setPluginRuleKey(set.getString("plugin_rule_key"));
				rule.setPluginName(set.getString("plugin_name"));
				rule.setDescription(set.getString("description"));
				rule.setCardinality(set.getString("cardinality"));
				rule.setParentId(set.getInt("parent_id"));
				rule.setName(set.getString("name"));
				rule.setPriority(set.getInt("priority"));
			}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		} finally
		{
			try
			{
				if (conn != null || !conn.isClosed())
				{
					conn.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return rule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.dao.impl.RuleAccessor#getRuleByPluginRuleKey(java.lang
	 * .String)
	 */
	public Rule getRuleByPluginRuleKey(String pluginRuleKey)
	{
		Rule rule = new Rule();
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT
					+ "where r.plugin_rule_key = ?");
			ps.setString(1, pluginRuleKey);
			ResultSet set = ps.executeQuery();
			if (set.next())
			{
				rule.setId(set.getInt("id"));
				rule.setPluginRuleKey(set.getString("plugin_rule_key"));
				rule.setPluginName(set.getString("plugin_name"));
				rule.setDescription(set.getString("description"));
				rule.setCardinality(set.getString("cardinality"));
				rule.setParentId(set.getInt("parent_id"));
				rule.setName(set.getString("name"));
				rule.setPriority(set.getInt("priority"));
			}
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		} finally
		{
			try
			{
				if (conn != null || !conn.isClosed())
				{
					conn.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return rule;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

}