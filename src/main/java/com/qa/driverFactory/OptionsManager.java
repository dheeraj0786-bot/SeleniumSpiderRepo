package com.qa.driverFactory;

import com.qa.logger.Log;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    private final Properties prop;

    public OptionsManager(Properties prop) {
        this.prop =prop;
    }

    public ChromeOptions getChromeOption() {
        chromeOptions = new ChromeOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
            Log.info("Running Chrome in headless mode");
            chromeOptions.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
            Log.info("Running chrome in incognito mode");
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOption() {
        firefoxOptions = new FirefoxOptions();
            if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
                Log.info("Running firefox in headless mode");
                firefoxOptions.addArguments("--headless");
            }
            if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
                Log.info("Running firefox in incognito mode");
                firefoxOptions.addArguments("--incognito");
            }
            return firefoxOptions;
    }

    public EdgeOptions getEdgeOption() {
            edgeOptions = new EdgeOptions();
            if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
                Log.info("Running edge in headless mode");
                edgeOptions.addArguments("--headless");
            }
            if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
                Log.info("Running edge in incognito mode");
                edgeOptions.addArguments("--inprivate");
            }
            return edgeOptions;
    }
}
