package com.qa.pages;

import com.qa.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.utils.ElementsUtil;

public class SearchResultsPage {

    // Page class/Page Library/Page Object
    private WebDriver driver;
    private ElementsUtil eleUtil;

    // 1. Private By Locators

    private By searchProducts = By.cssSelector("div.product-thumb");

    // 2. Public Page Class Const...
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementsUtil(driver);
    }

    public int getSearchProductCount() {
        return eleUtil.waitForElementsVisible(searchProducts, 10).size();
    }

    public ProductInfoPage selectProduct(String productName) {
        Log.info("searching for product: " + productName);
        eleUtil.waitForElementVisible(By.linkText(productName), 10).click();
        return new ProductInfoPage(driver);
    }
}
