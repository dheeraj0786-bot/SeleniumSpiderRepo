package com.qa.driverFactory;

import com.qa.errors.AppError;
import com.qa.exceptions.BrowserException;
import com.qa.exceptions.FrameworkException;
import com.qa.logger.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    private WebDriver driver;
    private OptionsManager optionsManager;
    public static String highlight;
    Properties prop;

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public WebDriver initDriver(Properties prop) {
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);
        String browserName = prop.getProperty("browser");
        String url = prop.getProperty("url");
        System.out.println("Browser is "+browserName);
        Log.info("Browser name is "+browserName);
        switch(browserName.toLowerCase()) {
            case "chrome":
//                driver = new ChromeDriver(optionsManager.getChromeOption());
                driverThreadLocal.set(new ChromeDriver(optionsManager.getChromeOption()));
                break;
            case "firefox":
//                driver = new FirefoxDriver(optionsManager.getFirefoxOption());
                driverThreadLocal.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
                break;
            case "edge":
//                driver = new EdgeDriver(optionsManager.getEdgeOption());
                driverThreadLocal.set(new EdgeDriver(optionsManager.getEdgeOption()));
                break;
            case "safari":
//                driver = new SafariDriver();
                driverThreadLocal.set(new SafariDriver());
                break;
            default:
                Log.error("Please enter valid browser name........ "+browserName);
                throw new BrowserException("No Browser found");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(url);

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public Properties initProp() {
        prop = new Properties();
        FileInputStream fileInputStream =null;
        // To Pass the environment name pass command mvn clean install -Denv="qa"
        String environmentName = System.getProperty("env");
        System.out.println("Environment name is : "+environmentName);
        try {
            if (environmentName == null) {
                System.out.println("No Environment is passed so running on QA environment");
                fileInputStream = new FileInputStream("./src/test/resources/config/config.qa.properties");
            } else {
                switch (environmentName.toLowerCase().trim()) {
                    case "qa":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.qa.properties");
                        break;
                    case "uat":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.uat.properties");
                        break;
                    case "dev":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.dev.properties");
                        break;
                    case "staging":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.staging.properties");
                        break;
                    case "prod":
                        fileInputStream = new FileInputStream("./src/test/resources/config/config.properties");
                        break;
                    default:
                        System.out.println("Please pass the correct environment name " + environmentName);
                        throw new FrameworkException(AppError.ENVIRONMENT_NOT_FOUND + " : " + environmentName);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;
    }

    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp directory
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
                + ".png";

        File destination = new File(path);

        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
