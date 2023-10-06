package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFilePath {

	public static XSSFWorkbook wb;
	public static XSSFSheet ws;


	public ExcelFilePath(String writeExcel, String sheetname) throws Throwable {
		FileInputStream fi = new FileInputStream(writeExcel);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(sheetname);
	}

	public int getRowNum() {

		return ws.getLastRowNum();
	}

	public String getCellData(int row, int column) {

		String celldata = "";
		if(ws.getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC) {
			
			int data = (int) ws.getRow(row).getCell(column).getNumericCellValue();
			celldata = String.valueOf(data);
			
		}
		else {
			
			celldata = ws.getRow(row).getCell(column).getStringCellValue();
			
		}

		return celldata;
	}

	public void setCellData(int row, int column, String status, String OutputExcel) throws Throwable {

		ws.getRow(row).createCell(column).setCellValue(status);

		if(status.equalsIgnoreCase("pass")) {

			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}

		else if (status.equalsIgnoreCase("fail")) {

			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);

		}

		else if (status.equalsIgnoreCase("blocked")) {

			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			ws.getRow(row).getCell(column).setCellStyle(style);
		}

		FileOutputStream fo = new FileOutputStream(OutputExcel);
		wb.write(fo);

	}
	
}

