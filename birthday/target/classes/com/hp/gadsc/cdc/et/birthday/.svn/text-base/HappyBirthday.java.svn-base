/**
 * 
 */
package com.hp.gadsc.cdc.et.birthday;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.hp.gadsc.cdc.et.birthday.bean.Employee;
import com.hp.gadsc.cdc.et.birthday.bean.Gift;
import com.hp.gadsc.cdc.et.birthday.bean.Manager;
import com.hp.gadsc.cdc.et.birthday.email.EmailGenerator;
import com.hp.gadsc.cdc.et.birthday.parser.BirthdayParser;
import com.hp.gadsc.cdc.et.birthday.parser.GiftParser;
import com.hp.gadsc.cdc.et.birthday.parser.ManagerParser;
import com.hp.gadsc.cdc.et.birthday.util.Constants;

/**
 * @author songw
 * 
 */
public class HappyBirthday {
	private static BirthdayParser _bParser = null;
	private static ManagerParser _mParser = null;
	private static GiftParser _gParser = null;

	/**
	 * java EmailGenerator Enter Birthday Month(1~12):
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0) {
			String propertyFilePath = args[args.length - 1];
			System.setProperty(Constants.PROPERTY_FILE, propertyFilePath);
			System.out.println("Properties file set:"
					+ System.getProperty(Constants.PROPERTY_FILE));
		} else {
			System.out.println("Properties file not set!");
			return;
		}

		_bParser = new BirthdayParser();
		_mParser = new ManagerParser();
		_gParser = new GiftParser();
		/* Get month value from user input */
		System.out.println("###########################################");
		System.out.println("Please do close all Excel files before continue");
		System.out.print("Enter Birthday Month(1~12):");
		String monthString = getCommandFromKeyboard();
		int month;
		try {
			month = Integer.valueOf(monthString);
			if ((month > 12) || (month < 0)) {
				return;
			}
		} catch (NumberFormatException e) {
			System.out.print("Month value not valid! Stop.");
			return;
		}

		/* Find all the employees for notify */
		try {

			Collection<Employee> employees = _bParser
					.findBirthdayEmployeesForMonth(month);
			Collection<Employee> validEmployees = new ArrayList<Employee>();
			for (Employee employee : employees) {
				if (employee.getLastTs() != null) {
					Date checkPoint = new Date(employee.getLastTs().getTime());
					checkPoint.setYear(checkPoint.getYear() + 1);
					checkPoint.setMonth(checkPoint.getMonth() - 1);

					if ((new Date().before(checkPoint))) {
						System.out
								.print("Employee "
										+ employee.getName()
										+ " was last sent gift on "
										+ employee.getLastTs()
										+ ". less than 1 year.Send again? (Y/N, default Y)");
						String yesOrNo = getCommandFromKeyboard();
						if ("N".equals(yesOrNo.trim().toUpperCase())) {

						} else {
							validEmployees.add(employee);
						}
					} else {
						validEmployees.add(employee);
					}
				} else {
					validEmployees.add(employee);
				}
			}
			/* Loop the employee collection to pick gift */
			for (Employee validEmployee : validEmployees) {
				Gift gift = _gParser.pickGiftForEmployee(validEmployee);
				if (gift != null) {
					_bParser.updateEmployeeGiftTs(validEmployee);
					System.out.println("Gift Serial Number "
							+ gift.getSerialNum() + " bond to Employee:"
							+ validEmployee.getEmail());

					/* Generate email */
					Manager manager = _mParser
							.findManagerForEmployee(validEmployee);
					EmailGenerator.sendManagerReminder(manager, validEmployee,
							gift);
				} else {
					System.out
							.println("!!!ALERT!!! No gift available for Employee:"
									+ validEmployee.getEmail());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

	}

	private static String getCommandFromKeyboard() {
		String input = null;
		try {
			byte[] b = new byte[1024];
			int result = System.in.read(b);
			if (result < 0) {
				return null;
			}
			input = new String(b).substring(0, new String(b).indexOf('\r'));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return input;
	}

}
