package net.fulcrum.Drivers;

import net.fulcrum.Util.*;
import io.appium.java_client.ios.IOSDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class iOSMobilityDriver {
	
	protected IOSDriver id;
    private WebElement element;

    protected final By nextButton = By.name("down blue 30");
    protected final By previousButton = By.name("up blue 30");
    protected final By textField = By.xpath("//UIATextField[1]");
    protected final By menuButton = By.name("menu lines 30");
    protected final By mainMenu = By.name("Main Menu");
    protected final By validationFile = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIAButton[1]");
    protected final By validationTitle = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIANavigationBar[1]/UIAStaticText[1]");

	public iOSMobilityDriver(IOSDriver id)
	{
		this.id = id;
	}

    //Navigate to next routine field
    public boolean clickNextRoutineField() {
        try {
            element = WaitTypes.getWhenVisible(id, nextButton, 30);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find next button");
            return false;
        }
        return true;
    }

    //Navigate to previous routine field
    public boolean clickPreviousRoutineField() {
        try {
            element = WaitTypes.getWhenVisible(id, previousButton, 30);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find next button");
            return false;
        }
        return true;
    }

    public boolean waitForRoutineDetail(String field) {
        try {
            element = WaitTypes.getWhenVisible(id, By.name(field), 30);
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot field: " + field);
            return false;
        }
        return true;
    }

    public boolean waitForRoutineDetail(String field, int duration) {
        try {
            element = WaitTypes.getWhenVisible(id, By.name(field), duration);
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot field: " + field);
            return false;
        }
        return true;
    }

    //Send text to current Mobility routine field
    public boolean sendTextRoutineDetail (String field, String text) {
        try {

            System.out.println("Sending routine detail: " + field + " value: " + text);
            element = WaitTypes.getWhenVisible(id, By.name(field), 30);
            element = WaitTypes.getWhenVisible(id, textField, 30);
            element.click();
            element.sendKeys(text);
            element = id.findElement(By.name("Done"));   //Navigates to next field
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find field " + field + " to send text: " + text);
            return false;
        }
        return true;
    }

    public void clickByName (String name) {
        try {
            element = WaitTypes.getWhenVisible(id, By.name(name), 30);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object named " + name );

        }
    }

    public void navigateToMainMenu () {
        try {
            element = WaitTypes.getWhenVisible(id, menuButton, 30);
            element.click();
            clickMainMenu();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find menu button");

        }
    }

    public void selectRoutine(String routine) {
        By find = By.name(routine);
        try {
            element = WaitTypes.getWhenVisible(id, find, 30);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find menu button");

        }
    }

    public void resetRoutine(String routine) {
        navigateToMainMenu();
        selectRoutine(routine);
    }

    //Select Yes for Messages from Mobility
    public void clickYes () {
        clickByName("Yes");
    }

    public void clickMainMenu () {
        id.tap(1, 200, 75, 1);   //Use tap function because there are multiple instances of "Main Menu" name
    }

    public void clickOk () {
        dismissAlert();
    }

    public void dismissAlert() {
        id.switchTo().alert().dismiss();
    }

    public void click(By object) {
        try {
            element = WaitTypes.getWhenVisible(id, object, 30);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");

        }
    }

    public void click(By object, int duration) {
        try {
            element = WaitTypes.getWhenVisible(id, object, duration);
            element.click();
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");

        }
    }

    public void sendText(By object, String text) {
        try {
            element = WaitTypes.getWhenVisible(id, object, 30);
            element.click();
            element.sendKeys(text);
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object");

        }
    }

    public boolean validateText(String text) {
        try {
            element = WaitTypes.getWhenVisible(id, By.name(text), 30);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickValidations() {
        click(validationFile, 15);
    }

    public String getValidationTitle() {
        element = id.findElement(validationTitle);
        return element.getText();
    }

}
