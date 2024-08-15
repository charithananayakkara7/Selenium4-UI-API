package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserSetup {

    private static final Logger logger = LoggerFactory.getLogger(BrowserSetup.class);
    private static WebDriver driver;
    public static WebDriver setBrowserInstance(String browserName) {
        if (driver == null) {
            try {
                switch (browserName.toLowerCase()) {
                    case "chrome":
                        logger.info("Initializing ChromeDriver");
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        logger.info("Initializing FirefoxDriver");
                        driver = new FirefoxDriver();
                        break;
                    case "edge":
                        logger.info("Initializing EdgeDriver");
                        driver = new EdgeDriver();
                        break;
                    default:
                        logger.warn("No such browser name found: {}", browserName);
                }
            } catch (Exception e) {
                logger.error("Error setting up browser: {}", e.getMessage());
            }
        } else {
            logger.info("Using existing browser instance");
        }
        return driver;
    }
}
