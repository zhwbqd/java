package com.hp.it.mail.service;

import java.util.Date;

import com.hp.it.mail.bean.EMail;

public interface IMailService
{
	public void setSmtp(String smtp);

	public void sendMail(EMail eMail);

	public void sendMail(String from, String toList, String ccList, String bccList, Date date, String subject,
			String body);
}
