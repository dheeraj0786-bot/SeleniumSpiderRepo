package com.qa.test;

import com.qa.appConstants.MyAppConstants;
import com.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accSetup() {
        accountPage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
    }

    @Test
    public void accountPageTitleTest() {
        Assert.assertEquals(accountPage.getAccPageTitle(), MyAppConstants.ACCOUNTS_PAGE_TITLE);
    }
    @Test
    public void accountPageUrlTest() {
        Assert.assertTrue(accountPage.getAccPageUrl().contains(MyAppConstants.ACC_PAGE_URL_FRACTION));
    }

    @Test
    public void accountPageHasLogoutLinkTest() {
        Assert.assertTrue(accountPage.isLogoutLinkExist());
    }

    @Test
    public void accountPageHasMyAccountTest() {
        Assert.assertTrue(accountPage.isMyAccountLinkExist());
    }

    @Test
    public void getAccountPageHeadersTest() {
        List<String> actualAccountHearderList = accountPage.getAccountPageHeaders();
        System.out.println(actualAccountHearderList);
    }

    @Test
    public void searchTest() {
        accountPage.doSearch("Macbook");
    }
}
