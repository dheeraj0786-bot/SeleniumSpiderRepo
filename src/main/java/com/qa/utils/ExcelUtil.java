package com.qa.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtil {

    private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/openCart.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;

    public static Object[][] readDataFromExcel(String sheetName) {
        System.out.println("Sheet name is : "+sheetName);
         Object[][] data =null;
        try {
            FileInputStream fileInputStream = new FileInputStream(TEST_DATA_SHEET_PATH);
            workbook = WorkbookFactory.create(fileInputStream);
           sheet = workbook.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
           for(int row =0;row<sheet.getLastRowNum();row++) {
               for(int col =0;col<sheet.getRow(0).getLastCellNum();col++) {
                   data[row][col] = sheet.getRow(row+1).getCell(col).toString();
               }
           }
           fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
