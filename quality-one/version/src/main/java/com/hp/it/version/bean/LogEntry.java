package com.hp.it.version.bean;

import java.util.Date;

public class LogEntry
{
	private String name;

	private String author;

	private Date date;

	private String message;

	private String path;

	private long reversion;

	public long getReversion()
	{
		return reversion;
	}

	public void setReversion(long reversion)
	{
		this.reversion = reversion;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
