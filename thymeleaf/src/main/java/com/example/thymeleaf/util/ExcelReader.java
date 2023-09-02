package com.example.thymeleaf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.example.thymeleaf.model.Employee;

@Component
public class ExcelReader {

	public List<Employee> readExcel() throws IOException {
		List<Employee> employees = new ArrayList<>();
		File file = new File("D://employees.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();
			while (rows.hasNext()) {

				Row row = rows.next();
				int index = row.getRowNum();
				if (index != 0) {
					Employee emp = new Employee();
					Iterator<Cell> cells = row.iterator();
					while (cells.hasNext()) {
						Cell cell = cells.next();						
						if (cell.getColumnIndex() == 0) {
							emp.setName(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == 1) {
							int empid = (int) cell.getNumericCellValue();
							emp.setId(String.valueOf(empid));
						} else {
							
							emp.setDepartment(cell.getStringCellValue());
						}
					}
					employees.add(emp);
				}
				else {
					System.out.println("skipping header");
				}
			}
		}

		return employees;
	}
}
