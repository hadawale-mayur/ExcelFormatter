package com.sarang.formatter.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormatterController {
	
	@RequestMapping(value="/welcome.do",method=RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("welcome");
	}
	
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/error.do",method=RequestMethod.GET)
	public ModelAndView error() {
		return new ModelAndView("error");
	}
	
	@RequestMapping(value="/format.do",method=RequestMethod.POST)
	public ResponseEntity<byte[]> format(@RequestParam("file") MultipartFile file) throws IOException {
		
		 
		 InputStream stream = new ByteArrayInputStream(file.getBytes());
		 HSSFWorkbook workbook = new HSSFWorkbook(stream);
		 HSSFSheet subTotalSheet = workbook.createSheet("Employee Data");
		 HSSFSheet sheet = workbook.getSheetAt(0);
		 Iterator<Row> rowIterator = sheet.iterator();
		 int rowCount = 0;
		 int count = 1;
		 String customerName = "";
		 double invoiceNo = -1;
		 String invoiceDate = "";
		 Row subTotalSheetHeaderRow = subTotalSheet.createRow(0);
		 subTotalSheetHeaderRow.createCell(0).setCellValue("Customer name");
		 subTotalSheetHeaderRow.createCell(1).setCellValue("Inv No.");
		 subTotalSheetHeaderRow.createCell(2).setCellValue("Inv Date");
		 subTotalSheetHeaderRow.createCell(3).setCellValue("Value");

		 while (rowIterator.hasNext()) 
        {
			Row row = rowIterator.next();
			rowCount++;
			if(rowCount <= 8) {
				continue;
			}
           
           Cell customerNameCell = row.getCell(2);
           String temp = row.getCell(6).getStringCellValue();
			if (customerNameCell.getStringCellValue() != null
					&& !"Customer name".equalsIgnoreCase(customerNameCell
							.getStringCellValue())
					&& !customerNameCell.getStringCellValue().trim().equals("")
					) {
				if(customerNameCell.getStringCellValue().trim().startsWith("(")) {
					customerName = customerName + customerNameCell.getStringCellValue();
				} else {
					customerName = customerNameCell.getStringCellValue();
				}
			}
			if(Cell.CELL_TYPE_NUMERIC==row.getCell(4).getCellType()) {
				invoiceNo = row.getCell(4).getNumericCellValue();
				invoiceDate = row.getCell(5).getStringCellValue();
			}
			if("Sub Total".equalsIgnoreCase(temp)) {
				int cellCount = 0;
				Row subTotalSheetRow = subTotalSheet.createRow(count++);
				subTotalSheetRow.createCell(cellCount++).setCellValue(customerName);
				subTotalSheetRow.createCell(cellCount++).setCellValue(invoiceNo);
				subTotalSheetRow.createCell(cellCount++).setCellValue(invoiceDate);
				subTotalSheetRow.createCell(cellCount++).setCellValue(row.getCell(12).getNumericCellValue());
			}
        }
		File formattedFile = new File(file.getOriginalFilename());
		FileOutputStream out = new FileOutputStream(formattedFile);
		workbook.write(out);
		out.close();
		FileInputStream fis = new FileInputStream(formattedFile);
		byte[] contents = IOUtils.toByteArray(fis);
		fis.close();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
	    String filename = file.getOriginalFilename();
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    formattedFile.delete();
	    return response;
	}
	

}
