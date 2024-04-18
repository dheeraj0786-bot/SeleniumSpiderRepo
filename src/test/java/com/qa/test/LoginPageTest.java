package com.qa.test;

import com.qa.appConstants.MyAppConstants;
import com.qa.base.BaseTest;
import com.qa.errors.AppError;
import com.qa.listeners.ExtentReportListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Epic("Epic number 100: Design open cart login page")
@Story("Story number 10: Design login page feature for open cart")
@Feature("Feature no 200: Add login feature")
//@Listeners(ExtentReportListener.class)
public class LoginPageTest extends BaseTest {
@Description("Checking login page title")
@Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void loginPageTitleTest() {
        Assert.assertEquals(loginPage.getLoginPageTitle(), MyAppConstants.LOGIN_PAGE_TITLE, AppError.PAGE_TITLE_NOT_FOUND);
    }
    @Description("Checking login page url")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 2)
    public void loginPageUrlTest() {
        Assert.assertTrue(loginPage.getLoginPageUrl().contains(MyAppConstants.LOGIN_PAGE_URL_FRACTION),AppError.PAGE_URL_NOT_FOUND);
    }
    @Description("Checking login page forgot password link")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void loginPageHasForgottenPasswordLinkTest() {
        Assert.assertTrue(loginPage.isForgottenPasswordExist());
    }

    @Description("Checking user is able to login")
    @Severity(SeverityLevel.BLOCKER)
    @Test(priority = 4)
    public void loginTest() {
        accountPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        Assert.assertEquals(accountPage.getAccPageTitle(),MyAppConstants.ACCOUNTS_PAGE_TITLE);
    }
}
