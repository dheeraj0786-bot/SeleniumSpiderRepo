package com.qa.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvUtil {

    public static final String CSV_FILE_PATH = "./src/test/resources/testdata/";
    public static Object[][] getDataFromCsv(String csvFileName) {
         String csvFile = CSV_FILE_PATH+ csvFileName+".csv";
         Object[][] data;
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new FileReader(csvFile));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String[]> allData;
        try {
            allData = csvReader. readAll();
            data = new Object[allData.size()][];
            for(int i = 0; i<allData.size();i++) {
                data[i] = allData.get(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
