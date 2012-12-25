package com.hp.gadsc.cdc.et.birthday.parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;

import com.hp.gadsc.cdc.et.birthday.bean.Employee;
import com.hp.gadsc.cdc.et.birthday.bean.Gift;
import com.hp.gadsc.cdc.et.birthday.util.Constants;
import com.hp.gadsc.cdc.et.birthday.util.PropertyReader;

public class GiftParser extends AbstractXslParser {

	public GiftParser(String fileName, String sheetName) {
		super(fileName, sheetName);
		// TODO Auto-generated constructor stub
	}

	public GiftParser() {
		this(PropertyReader.readValue(Constants.GIFT_XLS), PropertyReader
				.readValue(Constants.GIFT_MAIN_SHEET));
	}

	public Gift pickGiftForEmployee(Employee employee) {
		HSSFRow giftRow = findFirstRowByColumnValue(
				Constants.GIFT_EMPLOYEE_EMAIL_COLUMN, "");
		if (giftRow != null) {

			FileOutputStream fos;
			try {
				giftRow.getCell(Constants.GIFT_EMPLOYEE_EMAIL_COLUMN)
						.setCellValue(employee.getEmail());
				fos = new FileOutputStream(
						PropertyReader.readValue(Constants.GIFT_XLS));
				_workBook.write(fos);
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			Gift gift = new Gift();
			gift.setCardId(giftRow.getCell(Constants.GIFT_CARD_ID_COLUMN)
					.toString());
			// if password are 4 digit, it's converted to double by excel... so
			// need the following conversion
			String password = giftRow.getCell(Constants.GIFT_PASSWORD_COLUMN)
					.toString();
			if (password.indexOf('.') > 0) {
				password = password.substring(0, password.indexOf('.'));
			}
			gift.setPassword(password);
			gift.setMonetaryValue(giftRow.getCell(
					Constants.GIFT_MONETARY_VALUE_COLUMN).toString());
			gift.setStartDt(giftRow.getCell(Constants.GIFT_START_DT_COLUMN)
					.getDateCellValue());
			gift.setEndDt(giftRow.getCell(Constants.GIFT_END_DT_COLUMN)
					.getDateCellValue());
			gift.setSerialNum(String.valueOf((long) giftRow.getCell(
					Constants.GIFT_SERIAL_NUM_COLUMN).getNumericCellValue()));
			gift.setEmployeeEmail(giftRow.getCell(
					Constants.GIFT_EMPLOYEE_EMAIL_COLUMN).toString());
			return gift;

		}
		return null;
	}

}
