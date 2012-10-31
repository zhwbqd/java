package com.hp.it.sonar.access.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import com.hp.it.sonar.access.ProjectAccessor;
import com.hp.it.sonar.bean.Project;

public class ProjectDataAccessor implements ProjectAccessor
{
	private final static String SELECT = "select * from projects p ";

	private DataSource dataSource;

	public ProjectDataAccessor(DataSource pool)
	{
		dataSource = pool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hp.it.sonar.dao.impl.ProjectAccessor#getProjectById(int)
	 */
	public Project getProjectById(int id)
	{
		Project proj = new Project();
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT + "where p.id = ?");
			ps.setInt(1, id);
			ResultSet set = ps.executeQuery();
			if (set.next())
			{
				proj.setId(set.getInt("id"));
				proj.setRootId(set.getInt("root_id"));
				proj.setScope(set.getString("scope"));
				proj.setQualifier(set.getString("qualifier"));
				proj.setKee(set.getString("kee"));
				proj.setDescription(set.getString("description"));
				proj.setName(set.getString("name"));
				proj.setLongName(set.getString("long_name"));
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
		return proj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.dao.impl.ProjectAccessor#getProject(java.lang.String,
	 * java.lang.String)
	 */
	public Project getProject(String groupId, String artifactId)
	{
		String kee = groupId.trim() + ":" + artifactId.trim();
		return getProject(kee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.dao.impl.ProjectAccessor#getProject(java.lang.String)
	 */
	public Project getProject(String kee)
	{
		Project proj = new Project();
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT + "where p.kee = ?");
			ps.setString(1, kee);
			ResultSet set = ps.executeQuery();
			if (set.next())
			{
				proj.setId(set.getInt("id"));
				proj.setRootId(set.getInt("root_id"));
				proj.setScope(set.getString("scope"));
				proj.setQualifier(set.getString("qualifier"));
				proj.setKee(set.getString("kee"));
				proj.setDescription(set.getString("description"));
				proj.setName(set.getString("name"));
				proj.setLongName(set.getString("long_name"));
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

		return proj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hp.it.sonar.dao.impl.ProjectAccessor#getProject(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Project getProject(String groupId, String artifactId, String qualifiedClassName)
	{
		Project proj = new Project();
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT + "where p.kee = ?");
			ps.setString(1, groupId.trim() + ":" + artifactId.trim() + ":" + qualifiedClassName.trim());
			ResultSet set = ps.executeQuery();
			if (set.next())
			{
				proj.setId(set.getInt("id"));
				proj.setRootId(set.getInt("root_id"));
				proj.setScope(set.getString("scope"));
				proj.setQualifier(set.getString("qualifier"));
				proj.setKee(set.getString("kee"));
				proj.setDescription(set.getString("description"));
				proj.setName(set.getString("name"));
				proj.setLongName(set.getString("long_name"));
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

		return proj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hp.it.sonar.dao.impl.ProjectAccessor#getProjectsByRootId(int)
	 */
	public Collection<Project> getProjectsByRootId(int rootId)
	{
		Collection<Project> projs = new ArrayList();
		Connection conn = null;

		try
		{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT + "where p.root_id = ?");
			ps.setInt(1, rootId);
			ResultSet set = ps.executeQuery();
			while (set.next())
			{
				Project proj = new Project();

				proj.setId(set.getInt("id"));
				proj.setRootId(set.getInt("root_id"));
				proj.setScope(set.getString("scope"));
				proj.setQualifier(set.getString("qualifier"));
				proj.setKee(set.getString("kee"));
				proj.setDescription(set.getString("description"));
				proj.setName(set.getString("name"));
				proj.setLongName(set.getString("long_name"));

				projs.add(proj);
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
		return projs;
	}

	public DataSource getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(DataSource connectionPool)
	{
		this.dataSource = connectionPool;
	}

}
