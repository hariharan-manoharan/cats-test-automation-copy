package net.fulcrum.Drivers;

import net.fulcrum.Util.WaitTypes;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class WebBrowserDriver {

    static Logger log = Logger.getLogger(AndroidMobilityDriver.class.getName());

    public WebDriver wd;
    protected WebElement element;
    protected Select select;
    protected StringBuffer verificationErrors = new StringBuffer();

    protected String failMsg;

    public WebBrowserDriver (WebDriver wd)  {
        this.wd = wd;
    }

	public void waitForObject(final By by, int waitTime) {
    	WebDriverWait wait = new WebDriverWait(wd, 10);
        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                wd.findElement(by);
                break;
            } catch (Exception e) {
                wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void refreshHome () {
        try {
            wd.get(wd.getCurrentUrl());
        }   catch(Exception e) {
            System.out.println("Failed refreshing home");
        }

    }

    public void click(By object) {
        try {
            waitForObject(object, 45);
            element = WaitTypes.getWhenVisible(wd, object, 45);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");

        }
    }

    public void clickLink(String link) {
        try {
            By object = By.linkText(link);
            element = WaitTypes.getWhenVisible(wd, object, 45);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");
        }
    }

    public void click(By object, int duration) {
        try {
            waitForObject(object, duration);
            element = WaitTypes.getWhenVisible(wd, object, duration);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");
        }
    }

    public void clickButton(By object, int duration) {
        int count = 0;
        do {
            try {
                count++;
                WebDriverWait wait = new WebDriverWait(wd, duration);
                wait.until(ExpectedConditions.invisibilityOfElementLocated(object));
                waitForObject(object, duration);
                element.click();
            } catch (NoSuchElementException ex) {
                System.out.println("Error: Cannot find object");
            }
        } while (count < duration);
    }

    public void clickElement(WebElement element, int duration) {
        try {
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find element");
        }
    }

    public void sendText(By object, String text) {
        try {
            element = WaitTypes.getWhenVisible(wd, object, 30);
            element.clear();
            element.click();
            element.sendKeys(text);
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");

        }
    }

    public boolean validateText(String text) {
        try {
            element = WaitTypes.getWhenVisible(wd, By.name(text), 30);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getElement (By object) {
        return element = WaitTypes.getWhenVisible(wd, object, 30);
    }

    public void selectDropDownValue(By object, String value) {
        waitForObject(object, 30);
        new Select(wd.findElement(object)).selectByVisibleText(value);
    }

    public void clickAnElementByXpath(String xpath, String value) {
        WebDriverWait wait = new WebDriverWait(wd, 100);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        wd.findElement(By.xpath(xpath)).click();
    }

    public String returnURL() {
        return wd.getCurrentUrl();
    }

    public void assertText(By object, String text) {
        try {
            waitForObject(object, 45);
            Assert.assertEquals(text, wd.findElement(object).getText());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public void clickCheckbox(int num) {
        By checkBox;

        if (num > 1) {
            checkBox = By.xpath("(//input[@type='checkbox'])[" + num + "]");
        } else {
            checkBox = By.xpath("(//input[@type='checkbox'])");
        }

        click(checkBox);
    }

    public void navigate(String url) {
        wd.get(url);
    }

    public String getURL() {
        return wd.getCurrentUrl();
    }

    public String getTitle() {
        return wd.getTitle();
    }

    public WebElement findTextObject(String text) {
        return wd.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }

    public void verifyTextExists(String text) {
        Assert.assertEquals(true, wd.getPageSource().contains(text));
    }

    public boolean getTextExists(String text) {
        return wd.getPageSource().contains(text);
    }

    public int findTableValueIndex(List<WebElement> list, String value, int start, int delimiter) {
        int count = 1;
        int pointer = -1;

        for (int i = start; i < list.size(); i+=delimiter) {
            element = list.get(i);
            System.out.println("Current element: " + element.getText());

            if (value.equalsIgnoreCase(element.getText())) {
                pointer = count;
            } else {
                count++;
            }
        }
        return pointer;
    }

    public int findTableValuesIndex(List<WebElement> list, String value, String value2, int start, int offSet,
                                    int delimiter) {
        int count = 1;
        int pointer = -1;
        WebElement element2;

        for (int i = start; i < list.size(); i+=delimiter) {
            element = list.get(i);
            element2 = list.get(i + offSet);

            if (value.equalsIgnoreCase(element.getText()) && value2.equalsIgnoreCase(element2.getText())) {
                pointer = count;
            } else {
                count++;
            }
        }
        return pointer;
    }

    public boolean verifyTableValue(List<WebElement> list, String value, String value2, int start, int offSet,
                                    int delimiter) {
        WebElement element2;

        for (int i = start; i < list.size(); i+=delimiter) {
            element = list.get(i);
            element2 = list.get(i + offSet);

            if (value.equalsIgnoreCase(element.getText()) && value2.equalsIgnoreCase(element2.getText())) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyDropdownList(By object, String value) {
        element = wd.findElement(object);
        select = new Select(element);
        boolean flag = false;

        List<WebElement> list = select.getOptions();
        for (WebElement current : list) {
            if (value.equalsIgnoreCase(current.getText())) {
                flag = true;
            }
        }

        return flag;
    }

    public boolean verifyIfObjectExists(By object) {
        return wd.findElements(object).size() > 0;
    }

    public boolean verifyIfObjectIsDisplayed(By object) {
        return wd.findElement(object).isDisplayed();
    }

    public boolean verifyIfObjectIsEnabled(By object) {
        return wd.findElement(object).isEnabled();
    }
}
