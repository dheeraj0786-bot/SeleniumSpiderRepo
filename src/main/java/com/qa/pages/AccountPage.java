package com.qa.pages;

import com.qa.appConstants.MyAppConstants;
import com.qa.utils.ElementsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {

    public WebDriver driver;

    private final ElementsUtil elementsUtil;

    private final By logoutLink = By.linkText("Logout");

    private final By myAccountLink = By.linkText("My Account");

    private final By headers = By.cssSelector("div#content h2");

    private final By searchInput = By.name("search");

    private final By searchIcon = By.cssSelector("div#search button");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    public String getAccPageTitle() {
        String title = elementsUtil.waitForTitleIs(MyAppConstants.ACCOUNTS_PAGE_TITLE,5);
        System.out.println("Account Page title is : "+title);
        return title;
    }

    public String getAccPageUrl() {
        String url = elementsUtil.waitForURLContains(MyAppConstants.ACC_PAGE_URL_FRACTION,5);
        System.out.println("Account Page url is : "+url);
        return url;
    }

    public boolean isLogoutLinkExist() {
        return elementsUtil.isElementDisplayed(logoutLink);
    }

    public boolean isMyAccountLinkExist() {
        return elementsUtil.isElementDisplayed(myAccountLink);
    }

    public SearchResultsPage doSearch(String product) {
        System.out.println("Search for : "+product);
        elementsUtil.doSendKeys(searchInput,product);
        elementsUtil.doClick(searchIcon);
        return new SearchResultsPage(driver);
    }

    public List<String> getAccountPageHeaders() {
        List<WebElement> headerList = elementsUtil.getElements(headers);
        List<String> headerText = new ArrayList<>();
        for(WebElement header:headerList) {
            headerText.add(header.getText());
        }
        return headerText;
    }
}
