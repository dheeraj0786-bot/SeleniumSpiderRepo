package com.qa.base;

import com.qa.driverFactory.DriverFactory;
import com.qa.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

    WebDriver driver;
    protected Properties prop;
    protected LoginPage loginPage;

    protected AccountPage accountPage;

    protected SearchResultsPage searchResultsPage;

    protected ProductInfoPage productInfoPage;
    protected RegistrationPage registerationPage;

    // Whenever we have multiple assertions in a single @Test we can use soft assert
    // so that it gives result after executing all assertion. Hard Assertion will not execute all if one fail it throw exception
    protected SoftAssert softAssert;

    DriverFactory driverFactory;

    @Step("Setup: Launching the {0} browser and loading properties")
    @Parameters({"browser"})
    @BeforeTest
    public void setUp(String browserName) {
        driverFactory = new DriverFactory();
        prop = driverFactory.initProp();
        if(browserName!=null)
            prop.setProperty("browser",browserName);
        driver = driverFactory.initDriver(prop);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @Step("Closing the browser")
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
