package com.org.kore.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class DataReader {
	public WebDriver dr;
	public HSSFWorkbook wb;

	public int rowCount;
	public String className;
	public String sheetName;
	public String colName;
	public String value;

	public int getRownumber(String testCaseName, String colHeader) throws Exception {
		int rownumber = 0;

		rowCount = sheet.getPhysicalNumberOfRows();

		DataFormatter formatter = new DataFormatter(); // creating formatter
														// using the default
														// locale

		for (int j = 1; j <= rowCount; j++) {
			Row row = sheet.getRow(j);
			Cell cell = row.getCell(0);
			String val = formatter.formatCellValue(cell);
			if (val.equalsIgnoreCase(testCaseName)) {
				rownumber = j;
				break;
			}

		}
		if (rownumber == 0) {
			throw new Exception("Class Entry missing in DataSheet");
		}

		getColumnNumber(colHeader);
		return rownumber;
	}

	public int getColumnNumber(String columnHeader) throws Exception {
		Row row = sheet.getRow(0);
		int columnNumber = 0;
		int isValid = 0;
		for (int j = sheet.getFirstRowNum(); j < row.getPhysicalNumberOfCells(); j++) {
			if (row.getCell(j).toString().equalsIgnoreCase(columnHeader)) {
				columnNumber = j;
				isValid = 1;
				break;
			}

		}
		if (isValid == 0) {
			throw new Exception("Enter proper column in DataSheet");
		}
		// System.out.println("column number is " + columnNumber);
		return columnNumber;

	}

	public String getValue(String SheetName, String className, String columnHeader) throws Exception {

		try {
			String fileName = "DataSheet";
			sheet = getSheet(fileName, SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			Cell cell = sheet.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				value = cell.toString();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	public String getValue(String fileName, String SheetName, String className, String columnHeader) throws Exception {

		try {
			// String fileName = "DataSheet";
			sheet = getSheet(fileName, SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			Cell cell = sheet.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				value = cell.toString();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	Workbook wb_xssf; // Declare XSSF WorkBook
	Workbook wb_hssf;// Declare HSSF WorkBook;
	Sheet sheet;
	String fileExtn;

	public String getValueWithRow(String fileName, String SheetName, int rownumber, String columnHeader)
			throws Exception {

		try {
			sheet = getSheet(fileName, SheetName);
			// wb = new HSSFWorkbook(file);

			// ws = wb.getSheet(SheetName);

			int columnNumber = getColumnNumber(columnHeader);
			Cell cell = sheet.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				value = cell.toString();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	int noofRows;

	public Sheet getSheet(String fileName, String SheetName) {
		try {
			File dataFile = new File(System.getProperty("user.dir") + "/" + fileName + ".xls");
			if (dataFile.exists()) {
				dataFile = new File(System.getProperty("user.dir") + "/" + fileName + ".xls");
			} else {
				dataFile = new File(System.getProperty("user.dir") + "/" + fileName + ".xlsx");
			}

			// System.out.println(dataFile.getAbsolutePath());
			FileInputStream file = new FileInputStream(dataFile);
			fileExtn = FilenameUtils.getExtension(dataFile.getAbsolutePath());

			if (fileExtn.equalsIgnoreCase("xlsx")) {
				wb_xssf = new XSSFWorkbook(file);

				sheet = wb_xssf.getSheet(SheetName);
			}
			if (fileExtn.equalsIgnoreCase("xls")) {

				wb_hssf = new HSSFWorkbook(file);

				sheet = wb_hssf.getSheet(SheetName);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sheet;

	}

	public int getNoOfRows(String fileName, String SheetName) throws Exception {

		try {
			sheet = getSheet(fileName, SheetName);

			noofRows = sheet.getLastRowNum();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noofRows;

	}

	public String getSetUpValue(String fileName, String SheetName, String className, String columnHeader)
			throws Exception {

		try {

			sheet = getSheet(fileName, SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			Cell cell = sheet.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				value = cell.toString();
			}
			// List<> testName = new ArrayList<>();

			for (int i = 2; i <= 3; i++) {
				for (int j = 1; j <= 3; j++) {
					Cell cell1 = sheet.getRow(i).getCell(j);
				}
			}

			// System.out.println("value is " + value);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	public String getAppProperties(String key) throws IOException {
		String value = "";
		try {

			FileInputStream fileInputStream = new FileInputStream("data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	private String getCellValueAsString(HSSFCell cell, FormulaEvaluator formulaEvaluator) {
		if ((cell == null) || (cell.getCellType() == 3)) {
			return "";
		}
		if (formulaEvaluator.evaluate(cell).getCellType() == 5) {
			// throw new FrameworkException("Error in formula within this cell!
			// Error code: " +
			// cell.getErrorCellValue());
		}
		DataFormatter dataFormatter = new DataFormatter();
		return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell(cell));
	}

	public String getNumericValue(String fileName, String SheetName, String className, String columnHeader)
			throws Exception {

		try {
			sheet = getSheet(fileName, SheetName);
			int rownumber = getRownumber(className, columnHeader);
			int columnNumber = getColumnNumber(columnHeader);
			Cell cell = sheet.getRow(rownumber).getCell(columnNumber);
			if (cell != null) {
				Long i = (long) cell.getNumericCellValue();
				value = i.toString();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

}
