package com.hp.it.mail.bean;

import java.util.Date;
import java.util.Set;

public class EMail
{

	private Set<String> bccList;

	private Set<String> ccList;

	private String content;

	private Date date;

	private String from;

	private String subject;

	private Set<String> toList;

	public Set<String> getBccList()
	{
		return bccList;
	}

	public Set<String> getCcList()
	{
		return ccList;
	}

	public String getContent()
	{
		return content;
	}

	public Date getDate()
	{
		return date;
	}

	public String getFrom()
	{
		return from;
	}

	public String getSubject()
	{
		return subject;
	}

	public Set<String> getToList()
	{
		return toList;
	}

	public void setBccList(Set<String> bccList)
	{
		this.bccList = bccList;
	}

	public void setCcList(Set<String> ccList)
	{
		this.ccList = ccList;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setToList(Set<String> toList)
	{
		this.toList = toList;
	}

}
