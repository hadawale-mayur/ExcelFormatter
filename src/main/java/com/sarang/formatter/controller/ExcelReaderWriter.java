package com.sarang.formatter.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelReaderWriter {
	
	public static void main(String[] args) throws Exception
	  {
	    FileInputStream fileInputStream = new FileInputStream(new java.io.File("C:\\Users\\Mayur\\Downloads\\sarang\\BHARAT FEB.xls"));
	    HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
	    HSSFSheet subTotalSheet = workbook.createSheet("Employee Data");
	    HSSFSheet sheet = workbook.getSheetAt(0);
	    Iterator<Row> rowIterator = sheet.iterator();
	    int count = 1;
	    String customerName = "";
	    double invoiceNo = -1.0D;
	    String invoiceDate = "";
	    


	    Row subTotalSheetHeaderRow = subTotalSheet.createRow(0);
	    subTotalSheetHeaderRow.createCell(0).setCellValue("Customer name");
	    subTotalSheetHeaderRow.createCell(1).setCellValue("Inv No.");
	    subTotalSheetHeaderRow.createCell(2).setCellValue("Inv Date");
	    subTotalSheetHeaderRow.createCell(3).setCellValue("Value");
	    


	    while (rowIterator.hasNext())
	    {
	      Row row = (Row)rowIterator.next();
	      Cell customerNameCell = row.getCell(2);
	      String temp = row.getCell(6).getStringCellValue();
	      if (customerNameCell.getStringCellValue() != null)
	      {
	        if ((!"Customer name".equalsIgnoreCase(customerNameCell.getStringCellValue())) && 
	          (!customerNameCell.getStringCellValue().trim().equals("")))
	        {
	          if (customerNameCell.getStringCellValue().trim().startsWith("(")) {
	            customerName = customerName + customerNameCell.getStringCellValue();
	          } else
	            customerName = customerNameCell.getStringCellValue();
	        }
	      }
	      if (row.getCell(4).getCellType() == 0) {
	        invoiceNo = row.getCell(4).getNumericCellValue();
	        invoiceDate = row.getCell(5).getStringCellValue();
	      }
	      



	      if ("Sub Total".equalsIgnoreCase(temp)) {
	        int cellCount = 0;
	        Row subTotalSheetRow = subTotalSheet.createRow(count++);
	        subTotalSheetRow.createCell(cellCount++).setCellValue(customerName);
	        subTotalSheetRow.createCell(cellCount++).setCellValue(invoiceNo);
	        subTotalSheetRow.createCell(cellCount++).setCellValue(invoiceDate);
	        subTotalSheetRow.createCell(cellCount++).setCellValue(row.getCell(12).getNumericCellValue());
	      }
	    }
	    
	    FileOutputStream out = new FileOutputStream(new java.io.File("C:\\Users\\Mayur\\Downloads\\sarang\\BHARAT FEB_DEMO.xls"));
	    workbook.write(out);
	    out.close();
	    System.out.println("Completed Successfully");
	  }

}
