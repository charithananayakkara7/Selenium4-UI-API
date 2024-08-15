package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.page.BasePage;

public class ChargePointPage extends BasePage {
    private final String serialNumField = "input[name='input-serial-number']";
    private final String addBtn = "button[class='addButton']";
    private final String latestRemoveBtn = ".list >li:last-of-type > button[class='list-button']";
    private final String latestSerialNumValue = ".list >li:last-of-type > div[class='list-text']";

    public ChargePointPage(WebDriver driver, int TIME_OUT) {
        super(driver, TIME_OUT);
    }

    public void addSerialNumber(String serialnumber) throws Exception {
        By serialNumberField = getLocator(serialNumField, BY_TYPE.BY_CSSSELECTOR);
        clear(serialNumberField);
        type(serialNumberField, serialnumber);
    }

    public void clickOnAddBtn() throws Exception {
        By addSerialBtn = getLocator(addBtn, BY_TYPE.BY_CSSSELECTOR);
        click(addSerialBtn, 10);
    }

    //Two solutions possible find elements or dynamic CSS selector comparing with input data in here implementation with second option 
    public String verifyNewData(String expectedSerialNumber) throws Exception {
        By latestSerialNumLocator = getLocator(latestSerialNumValue, BY_TYPE.BY_CSSSELECTOR);
        String actualSerialNumber = getElementText(latestSerialNumLocator,3);
        return actualSerialNumber;
      }

    public void removeData() throws Exception {
        By removeDataBtn = getLocator(latestRemoveBtn , BY_TYPE.BY_CSSSELECTOR);
        click(removeDataBtn, 10);
    }

}
