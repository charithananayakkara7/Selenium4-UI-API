package base.page;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    public WebDriver driver;
    int TIME_OUT;

    public BasePage(WebDriver driver, int TIME_OUT) {
        this.driver = driver;
        this.TIME_OUT = TIME_OUT;
    }

    public void type(By locator, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            setTimeOut(TIME_OUT);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element.sendKeys(value);
            logger.info("Waited until element visibility of" + locator + "and send the vaule = " + value);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    public By getLocator(String locator, BY_TYPE type) {
        switch (type) {
            case BY_XPATH:
                return By.xpath(locator);
            case BY_LINKTEXT:
                return By.linkText(locator);
            case BY_ID:
                return By.id(locator);
            case BY_CSSSELECTOR:
                return By.cssSelector(locator);
            case BY_CLASSNAME:
                return By.className(locator);
            case BY_NAME:
                return By.name(locator);
            case BY_PARTIALLINKTEXT:
                return By.partialLinkText(locator);
            case BY_TAGNAME:
                return By.tagName(locator);

        }
        throw new IllegalArgumentException(
                "Please provide correct locator type");
    }

    public void clear(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            element.clear();
            logger.info("Clear the element value " + locator);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void click(By locator, int CONFIG_TIME_OUT) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(CONFIG_TIME_OUT));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void waitElementClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            setTimeOut(TIME_OUT);
            logger.info("Waited until element visibility " + locator);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    public void waitElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            logger.info("Waited until element visibility " + locator);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    public String getElementText(By locator, int CONFIG_TIME_OUT) {
        String text = "";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(CONFIG_TIME_OUT));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logger.info("Waited until element was visible: " + locator);
            text = element.getText();
            logger.info("Retrieved text from element: " + text);
        } catch (TimeoutException e) {
            logger.error("Timed out waiting for element to be visible: " + locator, e);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: " + locator, e);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving text from element: " + locator, e);
        }
        return text;
    }

    public String getPageTitle() {
        String title = "";
        try {
            title = driver.getTitle();
            logger.info("Retrieved page title: " + title);
        } catch (Exception e) {
            logger.error("An error occurred while retrieving the page title.", e);
        }
        return title;
    }

    private void setTimeOut(long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutInSeconds));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeoutInSeconds));
    }

    public enum BY_TYPE {
        BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME, BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME
    }

}
