package com.hp.gadsc.cdc.et.birthday.email;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import com.hp.gadsc.cdc.et.birthday.bean.Employee;
import com.hp.gadsc.cdc.et.birthday.bean.Gift;
import com.hp.gadsc.cdc.et.birthday.bean.Manager;
import com.hp.gadsc.cdc.et.birthday.util.Constants;
import com.hp.gadsc.cdc.et.birthday.util.PropertyReader;

public class EmailGenerator {

	public static void sendManagerReminder(Manager manager, Employee employee,
			Gift gift) {
		/* dateString to be replaced */

		String dateString = employee.getBirthMonth() + "/"
				+ employee.getBirthDay();

		Invitation invitation = new Invitation();
		invitation.setFrom(PropertyReader.readValue(Constants.MAIL_FROM));
		invitation.setTo(manager.getEmail());
		invitation.setCc(PropertyReader.readValue(Constants.COORDINATOR_EMAIL));
		invitation.setSubject(PropertyReader
				.readValue(Constants.MAIL_MANAGER_REMINDER_SUBJECT)
				.replace("$EMPLOYEE_NAME", employee.getName())
				.replace("$DATE_STRING", dateString));

		Date startDt = new Date();
		startDt.setMonth(employee.getBirthMonth() - 1);
		startDt.setDate(employee.getBirthDay());
		startDt.setHours(0);
		startDt.setMinutes(0);
		startDt.setSeconds(0);
		// **correct for weekdays only
		{
			Calendar c = Calendar.getInstance();
			c.setTime(startDt);
			int i = c.get(Calendar.DAY_OF_WEEK);
			if (i == Calendar.SATURDAY) {
				startDt.setDate(startDt.getDate() - 1);
			} else if (i == Calendar.SUNDAY) {
				startDt.setDate(startDt.getDate() - 2);
			}
		}

		invitation.setStartDt(startDt);

		Date endDt = new Date();
		endDt.setMonth(employee.getBirthMonth() - 1);
		endDt.setDate(employee.getBirthDay());
		endDt.setHours(8);
		endDt.setMinutes(0);
		endDt.setSeconds(0);
		{
			Calendar c = Calendar.getInstance();
			c.setTime(endDt);
			int i = c.get(Calendar.DAY_OF_WEEK);
			if (i == Calendar.SATURDAY) {
				endDt.setDate(endDt.getDate() - 1);
			} else if (i == Calendar.SUNDAY) {
				endDt.setDate(endDt.getDate() - 2);
			} else {

			}
		}
		invitation.setEndDt(endDt);
		invitation.setLocation("");
		invitation.setCategory(PropertyReader
				.readValue(Constants.MAIL_MANAGER_REMINDER_CATEGORY));

		String body;
		FileInputStream fis;
		try {
			fis = new FileInputStream(
					PropertyReader
							.readValue(Constants.MAIL_MANAGER_REMINDER_TEMPLATE));
			InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

			char[] bodyByte = new char[fis.available()];
			isr.read(bodyByte);
			isr.close();
			fis.close();

			body = String.copyValueOf(bodyByte);
			body = body.replace("$DATE_STRING", dateString);
			body = body.replace("$EMPLOYEE_NAME", employee.getName());
			body = body.replace("$EMPLOYEE_EMAIL", employee.getEmail());
			body = body.replace("$CARD_ID", gift.getCardId());
			body = body.replace("$PASSWORD", gift.getPassword());
			body = body.replace("$MONETARY_VALUE", gift.getMonetaryValue());
			body = body
					.replace("$START_DT", gift.getStartDt().toLocaleString());
			body = body.replace("$END_DT", gift.getEndDt().toLocaleString());
			body = body.replace("$COORDINATOR_EMAIL",
					PropertyReader.readValue(Constants.COORDINATOR_EMAIL));
			String employeeNickName = null;
			if (employee.getName().indexOf(",") > 0) {
				employeeNickName = employee.getName().substring(
						employee.getName().indexOf(",") + 1);
			} else {
				employeeNickName = employee.getName().substring(0,
						employee.getName().lastIndexOf(" "));
			}
			body = body.replace("$EMPLOYEE_NICK_NAME", employeeNickName);

			String managerNickName = null;
			if (manager.getName().indexOf(",") > 0) {
				managerNickName = manager.getName().substring(
						manager.getName().indexOf(",") + 1);
			} else {
				managerNickName = manager.getName().substring(0,
						manager.getName().lastIndexOf(" "));
			}
			body = body.replace("$MANAGER_NICK_NAME", managerNickName);

			// System.out.println(body);

			invitation.setBody(body);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		try {
			EmailUtility.sendInvitation(invitation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
}
