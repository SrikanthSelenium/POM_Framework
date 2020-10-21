package myProject.practest.test.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import myProject.practest.test.helper.resource.ResourceHelper;

public class ExcelHelper {

	private Logger log = Logger.getLogger(ExcelHelper.class);
	static String path = ResourceHelper.getResourcePath("src/main/resources/configfile/testData.xlsx");

	// public static void main(String arg[]) throws IOException { // Object
	// data[][] = getExcelData(path, "Sheet1"); // System.out.println(data); //
	// ExcelHelper.updateResults(path, "Sheet1", "Login", "PASS"); //
	// ExcelHelper.updateResults(path, "Sheet1", "Username", "PASS"); //
	// ExcelHelper.updateResults(path, "Sheet1", "Password", "FAIL"); //
	// ExcelHelper.updateResults(path, "Sheet1", "Address", "PASS");
	// }

	/*
	 * @DataProvider(name = "testdata") public Object[][] testdata() throws
	 * IOException { Object data[][] = getExcelData(path, "loginData"); return data;
	 * 
	 * }
	 * 
	 * @Test(dataProvider = "testdata") public void readData(String StartLoginTest,
	 * String UserName, String Password, String runMode)
	 * 
	 * { System.out.println(UserName + "-------------------" + Password); }
	 */

	/**
	 * @author : srikanth
	 * 
	 * @methods used : getExcelData(param1,param2)
	 * 
	 * @parameters : String excelLocation, String sheetName
	 * 
	 */

	@SuppressWarnings({ "resource", "deprecation" })
	@Test
	public static Object[][] getExcelData(String excelLocation, String sheetName) throws IOException {
		String data[][] = null;
		try {

			XSSFWorkbook workbook;
			XSSFSheet sheet;
			XSSFRow row;
			XSSFCell cell;
			FileInputStream fis = new FileInputStream(excelLocation);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int totalRow = sheet.getPhysicalNumberOfRows() - 1;
			int totalColumn = sheet.getRow(0).getLastCellNum();
			data = new String[totalRow][totalColumn];
			for (int i = 0; i < totalRow; i++) {
				row = sheet.getRow(i+1);
				for (int j = 0; j < totalColumn; j++) {
					if (row == null)
						data[i][j] = "";
					else {
						cell = row.getCell(j);
						if (cell == null)
							data[i][j] = "";
						else {
							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								data[i][j] = cell.getStringCellValue();
							//	System.out.println(data[i][j]);
							} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
								data[i][j] = Double.toString(cell.getNumericCellValue());
							//	System.out.println(data[i][j]);
							}
						}
					}
				}
			}
		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return (data);

	}
	
	public static Hashtable<String, ArrayList<String>> getExcelAsDataTable(String excelFilePath, String SheetName) throws FilloException {
		//Create Fillo object
		Fillo excelFile=new Fillo();
		//Create connection object
		Connection objDriver= excelFile.getConnection(excelFilePath);
		//Form Query
		String query = "SELECT * FROM "+SheetName;
		//Execute query
		Recordset rs = objDriver.executeQuery(query);
		//Create Hashtable which will store the final set of key-value pairs
		Hashtable<String, ArrayList<String>> excelKV = new Hashtable<String, ArrayList<String>>();		
		ArrayList<String> columnNames = rs.getFieldNames();//new ArrayList<String>();
		ArrayList<String> columVal = new ArrayList<String>();
		ArrayList<ArrayList<String>> columVa2 = new ArrayList<ArrayList<String>>();
		String strColName;
		for (int j = 0; j < columnNames.size(); j++) {
			strColName = columnNames.get(j);
			rs = objDriver.executeQuery("select "+strColName+" from "+SheetName);
	//		columVal = new ArrayList<String>();
			while (rs.next()) {
				//Check if column is not empty only then add to the HashTable
				if(rs.getField(strColName).length()>0) {				

					columVal.add(rs.getField(strColName));						

				}
			} 

		//	columVa2.add(columVal);
			//Add Key-Value to the hashtable where Key is a String value is an ArrayList
			excelKV.put(strColName, columVal);

		}
		//Return the hashmap
		return excelKV;
	}

	/*
	 * @author : srikanth
	 * 
	 * @methods used : updateResults(param1,param2,param3,param4)
	 * 
	 * @parameters : String excelLocation, String sheetName
	 * 
	 */

	public static void updateResults(String excelLocation, String sheetName, String testCaseName, String testStatus)
			throws IOException {

		FileInputStream fis = new FileInputStream(excelLocation);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);

		int totalRows = sheet.getLastRowNum() + 1;

		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			String cell = row.getCell(0).getStringCellValue();
			if (cell.contains(testCaseName)) {
				row.createCell(1).setCellValue(testStatus);
				// fis.close();
				FileOutputStream fos = new FileOutputStream(new File(excelLocation));
				workbook.write(fos);
				fos.close();
				break;
			}
		}

	}
}
