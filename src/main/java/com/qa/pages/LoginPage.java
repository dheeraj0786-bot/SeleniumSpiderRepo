package com.qa.pages;

import com.qa.appConstants.MyAppConstants;
import com.qa.logger.Log;
import com.qa.utils.ElementsUtil;
import com.qa.utils.TimeUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    public WebDriver driver;

    private final ElementsUtil elementsUtil;

    private final By emailId = By.id("input-email");
    private final By password = By.id("input-password");
    private final By loginButton = By.xpath("//input[@value='Login']");
    private final By forgotPwdLink = By.linkText("Forgotten Password");
    private final By registerLink = By.linkText("Register");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementsUtil = new ElementsUtil(driver);
    }

    @Step("Get the title of login page....")
    public String getLoginPageTitle() {
        String title = elementsUtil.waitForTitleIs(MyAppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME_FIVE_SECONDS);
        Log.info("Login Page title is : " + title);
        return title;
    }

    @Step("Get the url of login page....")
    public String getLoginPageUrl() {
        String url = elementsUtil.waitForURLContains(MyAppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME_FIVE_SECONDS);
        Log.info("Login Page url is : " + url);
        return url;
    }

    @Step("Check if forgot password link exist...")
    public Boolean isForgottenPasswordExist() {
        return elementsUtil.isElementDisplayed(forgotPwdLink);
    }

    @Step("Login with Username : {0} and Password : {1}")
    public AccountPage login(String username, String pwd) {
        Log.info("User credentials " + username + " : " + pwd);
        elementsUtil.waitForElementVisible(emailId, TimeUtil.DEFAULT_MEDIUM_TIME_FIVE_SECONDS).sendKeys(username);
        elementsUtil.doSendKeys(password, pwd);
        elementsUtil.doClick(loginButton);
        return new AccountPage(driver);
    }

    @Step("Navigating to the registration page")
    public RegistrationPage navigateToRegisterPage() {
        elementsUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME_TEN_SECONDS).click();
        return new RegistrationPage(driver);
    }
}
