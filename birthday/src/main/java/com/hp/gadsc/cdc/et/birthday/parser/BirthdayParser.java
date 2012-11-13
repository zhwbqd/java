package com.hp.gadsc.cdc.et.birthday.parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.hp.gadsc.cdc.et.birthday.bean.Employee;
import com.hp.gadsc.cdc.et.birthday.util.Constants;
import com.hp.gadsc.cdc.et.birthday.util.PropertyReader;

public class BirthdayParser extends AbstractXslParser {

	public BirthdayParser(String fileName, String sheetName) {
		super(fileName, sheetName);
	}

	public BirthdayParser() {
		this(PropertyReader.readValue(Constants.BIRTHDAY_XLS), PropertyReader
				.readValue(Constants.BIRTHDAY_MAIN_SHEET));

	}

	public Collection<Employee> findBirthdayEmployeesForMonth(int monthValue) {
		Collection<HSSFRow> rows = filterRowsByColumnValue(
				Constants.BIRTHDAY_MONTH_COLUMN,
				new Integer(monthValue).toString());
		Collection<Employee> employees = new ArrayList<Employee>();
		for (HSSFRow row : rows) {
			Employee em = new Employee();
			em.setName(row.getCell(Constants.BIRTHDAY_EMPLOYEE_NAME_COLUMN)
					.toString());
			em.setEmail(row.getCell(Constants.BIRTHDAY_EMPLOYEE_EMAIL_COLUMN)
					.toString());
			em.setBirthMonth(new Double(row.getCell(
					Constants.BIRTHDAY_MONTH_COLUMN).toString()).intValue());
			em.setBirthDay(new Double(row
					.getCell(Constants.BIRTHDAY_DAY_COLUMN).toString())
					.intValue());
			em.setManagerName(row.getCell(
					Constants.BIRTHDAY_MANAGER_NAME_COLUMN).toString());
			em.setLastTs(row.getCell(Constants.BIRTHDAY_LAST_GIFT_TS_COLUMN) == null ? null
					: row.getCell(Constants.BIRTHDAY_LAST_GIFT_TS_COLUMN)
							.getDateCellValue());

			employees.add(em);
		}
		return employees;
	}

	public int updateEmployeeGiftTs(Employee employee) {
		HSSFRow employeeRow = findFirstRowByColumnValue(
				Constants.BIRTHDAY_EMPLOYEE_EMAIL_COLUMN, employee.getEmail());
		if (employeeRow != null) {

			FileOutputStream fos;
			try {
				HSSFCell cell = employeeRow
						.createCell(Constants.BIRTHDAY_LAST_GIFT_TS_COLUMN);
				HSSFCellStyle style = _workBook.createCellStyle();
				style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
				cell.setCellStyle(style);
				cell.setCellValue(new Date());
				fos = new FileOutputStream(
						PropertyReader.readValue(Constants.BIRTHDAY_XLS));
				_workBook.write(fos);
				fos.close();
				return 1;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
		}
		return -1;

	}
}
