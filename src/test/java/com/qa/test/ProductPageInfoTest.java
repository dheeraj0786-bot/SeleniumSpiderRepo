package com.qa.test;

import com.qa.appConstants.MyAppConstants;
import com.qa.base.BaseTest;
import com.qa.utils.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductPageInfoTest extends BaseTest {

    //AAA pattern
    // A test can have only one hard assert or multiple soft assert

    @BeforeClass
    public void accSetup() {
        accountPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductSearchData() {
        return new Object[][] {
                {"MacBook" ,"MacBook Pro"},
                {"imac" ,"iMac"},
                {"Samsung" ,"Samsung SyncMaster 941BW"},
                {"Samsung" ,"Samsung Galaxy Tab 10.1"}
        };
    }

    @Test(dataProvider = "getProductSearchData")
    public void productHeaderTest(String searchKey , String productName) {
        searchResultsPage = accountPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getProductHeader(),productName);
    }

    @DataProvider
    public Object[][] getProductImagesData() {
        return new Object[][] {
                {"MacBook" ,"MacBook Pro","4"},
                {"imac" ,"iMac","3"},
                {"Samsung" ,"Samsung SyncMaster 941BW","1"},
                {"Samsung" ,"Samsung Galaxy Tab 10.1","7"}
        };
    }

    @DataProvider
    public Object[][] getProductImagesDataFromExcel() {
         return ExcelUtil.readDataFromExcel(MyAppConstants.PRODUCT_SHEET_NAME);
    }

    @Test(dataProvider = "getProductImagesData")
    public void productImageCountTest(String searchKey,String productName,String productImageCount) {
        searchResultsPage = accountPage.doSearch(searchKey);
        productInfoPage = searchResultsPage.selectProduct(productName);
        Assert.assertEquals(productInfoPage.getProductImagesCount(),Integer.parseInt(productImageCount));
    }

    @Test
    public void productInfoTest() {
        searchResultsPage = accountPage.doSearch("Macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String,String> productDetail = productInfoPage.getProductDetailsMap();
        Assert.assertEquals(productDetail.get("Brand"),"Apple");
        softAssert.assertEquals(productDetail.get("Product Code"),"Product 18");
        softAssert.assertEquals(productDetail.get("Availability"),"In Stock");
        softAssert.assertEquals(productDetail.get("productprice"),"$2,000.00");
        softAssert.assertEquals(productDetail.get("extaxprice"),"$2,000.00");
        softAssert.assertAll();
    }
}
