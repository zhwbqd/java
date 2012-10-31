package com.hp.gadsc.cdc.et.birthday.parser;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.hp.gadsc.cdc.et.birthday.bean.Employee;
import com.hp.gadsc.cdc.et.birthday.bean.Manager;
import com.hp.gadsc.cdc.et.birthday.util.Constants;
import com.hp.gadsc.cdc.et.birthday.util.PropertyReader;

public class ManagerParser extends AbstractXslParser {

	public ManagerParser(String fileName, String sheetName) {
		super(fileName, sheetName);

	}

	public ManagerParser() {
		this(PropertyReader.readValue(Constants.MANAGER_XLS), PropertyReader
				.readValue(Constants.MANAGER_MAIN_SHEET));
	}

	public Manager findManagerForEmployee(Employee employee) {
		HSSFRow managerRow = findFirstRowByColumnValue(
				Constants.MANAGER_NAME_COLUMN, employee.getManagerName());
		if (managerRow != null) {
			Manager result = new Manager();
			result.setName(managerRow.getCell(Constants.MANAGER_NAME_COLUMN)
					.toString());
			result.setEmail(managerRow.getCell(Constants.MANAGER_EMAIL_COLUMN)
					.toString());
			return result;
		} else {
			return null;
		}
	}

}
