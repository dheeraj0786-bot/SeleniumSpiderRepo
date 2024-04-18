package com.qa.test;

import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchResultTest extends BaseTest {
    @BeforeClass
    public void searchResultTest() {
        accountPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getProductCountData() {
        return new Object[][] {
                {"MacBook" ,3},
                {"imac" ,1},
                {"Samsung" ,2}
        };
    }

    @Test(dataProvider="getProductCountData")
    public void searchResultsCountTest(String searchKey , int productCount) {
        searchResultsPage = accountPage.doSearch(searchKey);
        Assert.assertEquals(searchResultsPage.getSearchProductCount(),productCount);
    }

    @Test
    public void searchResultsTest() {
        searchResultsPage = accountPage.doSearch("Macbook");
        Assert.assertEquals(searchResultsPage.getSearchProductCount(),3);
    }
}
