package com.qa.test;

import com.qa.appConstants.MyAppConstants;
import com.qa.utils.CsvUtil;
import com.qa.utils.ExcelUtil;
import com.qa.utils.StringUtils;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.base.BaseTest;

public class RegistrationPageTest extends BaseTest {

    @BeforeClass
    public void regSetup() {
        registerationPage = loginPage.navigateToRegisterPage();
    }

    @DataProvider
    public Object[][] getUserRegTestData() {
        return new Object[][]{
                {"gaurav", "gupta", "9876543212", "gg@123", "yes"},
                {"shilpa", "mamiidipelli", "9876543662", "shilpa@123", "no"},
                {"om", "gautam", "9876587653", "om@123", "yes"}

        };
    }

    @DataProvider
    public Object[][] getUserRegTestDataFromExcel() {
        return ExcelUtil.readDataFromExcel(MyAppConstants.REGISTER_SHEET_NAME);
    }

    @DataProvider
    public Object[][] getUserRegTestDataFromCSV() {
        return CsvUtil.getDataFromCsv(MyAppConstants.REGISTER_SHEET_NAME);
    }


    @Step("Checking For User Registration")
    @Test(dataProvider = "getUserRegTestDataFromExcel")
    public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        Assert.assertTrue(
                registerationPage.userRegister(firstName, lastName,
                        StringUtils.getRandomEmailId(),
                        telephone, password, subscribe));

    }

}
