package com.qa.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Testing {

    @DataProvider
    public Object[][] dataCsv() throws IOException, CsvException {
        String csvFile = "./src/test/resources/testdata/dataFrom.csv";
        CSVReader csvReader = new CSVReader(new FileReader(csvFile));
        List<String[]> datas = csvReader. readAll();
        Object[][] data = new Object[datas.size()][];

        for(int i = 0; i<datas.size();i++) {
            data[i] = datas.get(i);
        }
return data;
    }

    @Test(dataProvider = "dataCsv")
    public void getDataFromCsv(String name, String age, String city) {
        System.out.println(name+" "+age+" "+city);
    }
}
