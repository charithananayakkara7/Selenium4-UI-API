package BaseTestLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import base.page.BasePage;
import common.BrowserSetup;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    public WebDriver driver;
    public BasePage Basepage;
    public Properties prop;
    public static final int TIME_OUT = 25;

    public void initializeBrowser() {
        if (driver != null) {
            logger.info("Browser already initialized");
            return;
        }
        loadProperties();
        int timeout = customizedTimeout(prop.getProperty("TIME_OUT"));
        String browserName = prop.getProperty("browser");

        // to check browser name setup
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name not specified in properties file");
        }

        // to set up the browser
        driver = BrowserSetup.setBrowserInstance(browserName);
        if (driver == null) {
            throw new RuntimeException("Failed to initialize the browser");
        }
        // to Pass driver to the base page class
        Basepage = new BasePage(driver, timeout);
        logger.info("Using {} browser", browserName);

        String url = prop.getProperty("url");
        if (url == null) {
            throw new IllegalArgumentException("URL not specified in properties file");
        }

        driver.get(url);
        driver.manage().window().maximize();
    }

    // using to load the prop file
    private void loadProperties() {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/uitest.local.properties")) {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            logger.error("Properties file not found at path: src/main/resources/uitest.local.properties", e);
            throw new RuntimeException("Properties file not found", e);
        } catch (IOException e) {
            logger.error("Error loading properties file from path: src/main/resources/uitest.local.properties", e);
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    private int customizedTimeout(String timeoutString) {
        if (timeoutString == null) {
            throw new IllegalArgumentException("Timeout value not specified in properties file");
        }
        try {
            return Integer.parseInt(timeoutString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid timeout value specified in properties file: " + timeoutString,e);
        }
    }

    public void browserClose() {
        if (driver != null) {
            try {
                driver.close();
                logger.info("Browser closed successfully");
            } catch (Exception e) {
                logger.error("Error while closing the browser", e);
            }
        }
    }
}
