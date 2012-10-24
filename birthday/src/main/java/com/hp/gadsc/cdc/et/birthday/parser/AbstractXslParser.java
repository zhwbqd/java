package com.hp.gadsc.cdc.et.birthday.parser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class AbstractXslParser {
	protected HSSFWorkbook _workBook = null;
	protected HSSFSheet _sheet = null;

	public AbstractXslParser(String fileName, String sheetName) {
		super();
		try {
			_workBook = new HSSFWorkbook(new FileInputStream(fileName));
			_sheet = _workBook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellContentAsString(int iRow, short iColumn) {
		HSSFRow row = _sheet.getRow(iRow);
		if (row != null) {
			HSSFCell cell = row.getCell(iColumn);

			if (cell != null) {
				System.out.print(cell.toString());
				// switch (cell.getCellType()) {
				// case HSSFCell.CELL_TYPE_STRING:
				// System.out.print(cell.getRichStringCellValue().getString());
				// break;
				// case HSSFCell.CELL_TYPE_NUMERIC:
				// System.out.print(cell.getNumericCellValue());
				// break;
				// case HSSFCell.CELL_TYPE_BOOLEAN:
				// System.out.print(cell.getBooleanCellValue());
				// break;
				// case HSSFCell.CELL_TYPE_FORMULA:
				// System.out.print(cell.getCellFormula());
				// break;
				// default:
				// System.out.print("");
				// }
				System.out.print("\t");
				return cell.toString();
			} else {
				System.out.print("\t");
			}
		}
		return "";

	}

	public Collection<HSSFRow> filterRowsByColumnValue(short iColumn,
			String value) {
		Collection<HSSFRow> result = new ArrayList<HSSFRow>();
		for (int i = 0; i <= _sheet.getLastRowNum(); i++) {
			HSSFRow aRow = _sheet.getRow(i);
			HSSFCell aCell = aRow.getCell(iColumn);
			switch (aCell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				if (aCell.getRichStringCellValue().getString().equals(value)) {
					result.add(aRow);
				}
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (aCell.getNumericCellValue() == new Double(value)
						.doubleValue()) {
					result.add(aRow);
				}
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				System.out.print(aCell.getBooleanCellValue());
				if (aCell.getBooleanCellValue() == new Boolean(value)
						.booleanValue()) {
					result.add(aRow);
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				// TODO: compare formula....
				break;
			default:
				if (aCell.toString().equals(value)) {
					result.add(aRow);
				}
			}

		}
		return result;
	}

	public HSSFRow findFirstRowByColumnValue(short iColumn, String value) {

		for (int i = 0; i <= _sheet.getLastRowNum(); i++) {
			HSSFRow aRow = _sheet.getRow(i);
			HSSFCell aCell = aRow.getCell(iColumn);
			switch (aCell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				if (aCell.getRichStringCellValue().getString().equals(value)) {
					return aRow;
				}
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (aCell.getNumericCellValue() == new Double(value)
						.doubleValue()) {
					return aRow;
				}
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				System.out.print(aCell.getBooleanCellValue());
				if (aCell.getBooleanCellValue() == new Boolean(value)
						.booleanValue()) {
					return aRow;
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				// TODO: compare formula....
			default:
				if (aCell.toString().equals(value)) {
					return aRow;
				}
			}

		}
		return null;
	}

	public int getRowNumber() {
		return _sheet.getLastRowNum();
	}
}
