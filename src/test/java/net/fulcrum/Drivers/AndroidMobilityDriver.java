/**
 * AndroidMobilityDriver.java
 *
 * Java Class Wrapper Object for Appium AndroidDriver functions for Mobility Routines
 *
 */

package net.fulcrum.Drivers;

import net.fulcrum.Util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.interactions.*;
import io.appium.java_client.android.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

public class AndroidMobilityDriver {

    static Logger log = Logger.getLogger(AndroidMobilityDriver.class.getName());

    public boolean success;
    protected Hashtable < String, String > xPathTable = new Hashtable < String, String > ();
    public AndroidDriver ad;

    private WebElement element;
    private int y;
    private int startY;
    private int endY;
    private int x;
    private int startX;
    private int endX;
    private Random rand = new Random();

    private By nextButton = By.id("net.fulcrum.mobility:id/next");
    private By previousButton = By.id("net.fulcrum.mobility:id/previous");
    private By yesButton = By.xpath(".//android.widget.Button[@text='Yes']");
    private By noButton = By.xpath(".//android.widget.Button[@text='No']");
    private By okButton = By.xpath(".//android.widget.Button[@text='Ok']");
    private By cancelButton = By.name("Cancel");
    private By textField = By.xpath("//android.widget.EditText[1]");
    private By backRoutinesButton = By.xpath(".//android.widget.Button[@content-desc='Back']");
    private By menu = By.xpath("//android.view.View[1]/android.widget.FrameLayout[1]/android.view.View[1]/android.widget.LinearLayout[1]");
    private By validationList = By.xpath(".//android.view.View[@content-desc='\uE028']");

    protected String failMsg;

    public AndroidMobilityDriver(AndroidDriver ad) {
        this.ad = ad;
    }

    /**
     * Wait Actions
     */

    public void waitForElement(final By by, int waitTime) {
        WebDriverWait wait = new WebDriverWait(ad, 45);
        for (int attempt = 0; attempt < waitTime; attempt++) {
            try {
                ad.findElement(by);
                break;
            } catch (Exception e) {
                ad.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public boolean waitForRoutineDetail(String field) {
        return waitForRoutineDetail(field, 45);
    }

    public boolean waitForRoutineDetail(String field, int duration) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(By.xpath(".//android.view.View[@content-desc='" + field + "']"));
            log.info("Waiting for Routine Detail: " + field);
        } catch (NoSuchElementException ex) {   //Verify this is the correct exception
            failMsg = "Error: Cannot field: " + field;
            System.out.println(failMsg);
            log.error(failMsg);
            return false;
        }
        return true;
    }

    /**
     * Keyboard Actions
     */

    public void hideKeyboard() {
        try {
            ad.hideKeyboard();
            log.info("Hiding Keyboard");
        } catch (Exception e) {
            failMsg = "Keyboard not present";
            System.out.println(failMsg);
            log.error(failMsg);
        }
    }

    public void sendEmptyString() {
        try {
            Actions actions = new Actions(ad);
            actions.sendKeys("").perform();
            log.info("Sending Empty String");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Send empty string";
            System.out.println(failMsg);
            log.error(failMsg);

        }
    }

    public void sendKeys(By object, String text) {
        try {
            ad.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
            element = findElement(object);
            element.click();
            Actions actions = new Actions(ad);
            actions.sendKeys(text).perform();
            log.info("Sending Key: " + text);
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object: " + object;
            System.out.println(failMsg);
            log.error(failMsg);
        }
    }

    public void sendKeys(By object, String text, int duration) {
        try {
            ad.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
            element = findElement(object);
            element.click();
            Actions actions = new Actions(ad);
            actions.sendKeys(text).perform();
            log.info("Sending Key: " + text);
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object: " + object;
            System.out.println(failMsg);
            log.error(failMsg);
        }
    }

    //Send text to current Mobility routine field
    public boolean sendTextRoutineDetail(String field, String text) {
        try {
            waitForRoutineDetail(field);
            Actions actions = new Actions(ad);
            actions.sendKeys(text).perform();
            log.info("Sending Routine Detail: " + field.substring(0, field.length() - 2) + ", Value: " + text);
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find field \" + field + \" to send text: \" + text";
	        reportError();
            return false;
        }
        return true;
    }

    /**
     * Click Actions
     */

    //Navigate to next routine field
    public boolean clickNextRoutineField() {
        try {
            element = WaitTypes.getWhenVisible(ad, nextButton, 45);
            element.click();
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find next button";
	        reportError();
            return false;
        }
        return true;
    }

    //Navigate to previous routine field
    public boolean clickPreviousRoutineField() {
        try {
            element = WaitTypes.getWhenVisible(ad, previousButton, 15);
            element.click();
            log.info("Clicking Previous Routine Field");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to navigate through previous routine field";
	        reportError();
            return false;
        }
        return true;
    }

    public void clickByName(String name) {
        try {
            ad.scrollToExact(name);
            ad.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
            element = findElement(By.name(name));
            element.click();
            log.info("Clicking By Name" + name);
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object named " + name;
	        reportError();
        }
    }

    public void click(By object) {
        click(object, 45);
    }

    public void click(By object, int duration) {
        try {
            ad.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
            element = findElement(object);
            element.click();
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object: " + object;
	        reportError();
        }
    }

    public void clickTextView (String name) {
        verifyTextView(name);
        click(By.xpath(".//android.widget.TextView[@text='" + name + "']"), 30);
        log.info("Clicking Text View: " + name);
    }


    public void clickContentDescViewView (String name) {
        click(By.xpath(".//android.view.View[@content-desc='" + name + "']"), 30);
        log.info("Clicking Content Desc View View: " + name);
    }

    public void clickValidationFile() {
        click(validationList);
        log.info("Clicking Validation File");
    }


    //Select Yes for Messages from Mobility
    public boolean clickYes() {
        try {
            verifyMessageHeader();
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(yesButton);
            element.click();
            log.info("Clicking Yes");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click Yes";
	        reportError();
            return false;
        }
        return true;
    }

    public boolean confirmYes() {
        try {
            verifyTextView("Confirm");
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(yesButton);
            element.click();
            log.info("Confirming Yes");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click Yes";
	        reportError();
            return false;
        }
        return true;
    }

    public void verifyValidationOverrideHeader() {
        try {
            verifyTextView("Validation Override");
            log.info("Verifying Validation Override Header");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find Valdiation Override message header";
	        reportError();
        }
    }

    public void verifyMessageHeader() {
        try {
            verifyTextView("Mobility");
            log.info("Verifying Message Header");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find Mobility message header";
	        reportError();
        }
    }

    //Select No for Messages from Mobility
    public boolean clickNo() {
        try {
            verifyMessageHeader();
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(noButton);
            element.click();
            log.info("Clicking No");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click No";
	        reportError();
            return false;
        }
        return true;
    }

    //Confirm No for Messages from Mobility
    public boolean confirmNo() {
        try {
            verifyTextView("Confirm");
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(noButton);
            element.click();
            log.info("Confirming No");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to confirm No";
	        reportError();
            return false;
        }
        return true;
    }

    public boolean validationYes() {
        try {
            verifyTextView("Validation Override");
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(yesButton);
            element.click();
            log.info("Clicking Validation Override Yes");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to Validation Override Yes";
	        reportError();
            return false;
        }
        return true;
    }
    public boolean validationNo() {
        try {
            verifyTextView("Validation Override");
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(noButton);
            element.click();
            log.info("Clicking Validation Override No");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to Validation Override No";
	        reportError();
            return false;
        }
        return true;
    }

    public boolean clickOk() {
        try {
            verifyMessageHeader();
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(okButton);
            element.click();
            log.info("Clicking Ok");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click Ok";
	        reportError();
            return false;
        }
        return true;
    }

    //Select Cancel for Messages from Mobility
    public boolean clickCancel() {
        try {
            ad.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = findElement(cancelButton);
            element.click();
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click Cancel";
	        reportError();
            return false;
        }
        return true;
    }

    public void clickMenu() {
        click(menu, 15);
    }

    public boolean clickBackRoutines() {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(backRoutinesButton);
            element.click();
            log.info("Clicking Back Routine Button");
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot find object to click Back Routines";
	        reportError();
            return false;
        }
        return true;
    }

    public void clickOutOfFocus() {
        ad.tap(1, 1000, 1000, 1);
    }

    /**
     * Verify Actions
     */

    public boolean verifyTextView(String text) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(By.xpath(".//android.widget.TextView[@text='" + text + "']"));
            log.info("Verifying Text View: " + text);
            return true;
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot text: " + text;
	        reportError();
            return false;
        }
    }

    public boolean verifyEditText(String text) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(By.xpath(".//android.widget.EditText[@text='" + text + "']"));
            log.info("Verifying Edit Text: " + text);
            return true;
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot text: " + text;
	        reportError();
            return false;
        }
    }

    public boolean verifyViewView(String text) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(By.xpath(".//android.view.View[@content-desc='" + text + "']"));
            log.info("Verifying View View: " + text);
            return true;
        } catch (NoSuchElementException ex) {
            failMsg = "Error: Cannot text: " + text;
	        reportError();
            return false;
        }
    }


    public boolean verifyTextByName(String text) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = findElement(By.name(text));
            log.info("Verifying Text By Name: " + text);
            return true;
        } catch (NoSuchElementException e) {
            failMsg = "Error: Cannot find text: " + text;
	        reportError();
            return false;
        }
    }

    /**
     * Find Actions
     */

    public WebElement findElement(By object) {
        try {
            ad.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            element = ad.findElement(object);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Error: Cannot find object: " + object);
        }
        return element;
    }

    /**
     * Clear Actions
     */

    public void clearField() {
        try {
            element = ad.findElement(textField);
            element.clear();
            log.info("Clearing field");
        } catch (java.util.NoSuchElementException ex) {
            failMsg = "Error: Cannot find text object to clear value";
	        reportError();
        }
    }

    public void clearField(int x) {
        try {
            element = ad.findElement(textField);
            for (int i = 0; i <= x; i++) {
                element.clear();
            }
            log.info("Clearing field");
        } catch (java.util.NoSuchElementException ex) {
            failMsg = "Error: Cannot find text object to clear value";
	        reportError();
        }
    }

    /**
     * Swipe Actions
     */

    //Perform a swipe up on emulator
    public void swipeDown() {
        for (int i = 0; i < 2; i++) {
            Dimension size = ad.manage().window().getSize();
            endY = (int)(size.height * .5);
            startY = (int)(size.height * .2);
            x = (size.width) / 2;
            ad.swipe(x, startY, x, endY, 1000);
        }
        log.info("Swiping Down");
    }

    public void swipeDown(int time) {
        for (int i = 0; i < time; i++) {
            Dimension size = ad.manage().window().getSize();
            endY = (int)(size.height * .3);
            startY = (int)(size.height * .2);
            x = (size.width) / 2;
            ad.swipe(x, startY, x, endY, 1000);
        }
        log.info("Swiping Down");
    }

    //Perform a swipe up on emulator
    public void swipeRight() {
        for (int i = 0; i < 2; i++) {
            Dimension size = ad.manage().window().getSize();
            startX = (int)(size.width * .5);
            endX = (int)(size.width * .2);
            y = (size.height) / 2;
            ad.swipe(startX, y, endX, y, 2000);
        }
        log.info("Swiping Right");

    }

    public int getRandomInt() {
        return rand.nextInt(1000) + 1;
    }

    public int getRandomInt(int bound) {
        return rand.nextInt(bound) + 1;
    }

    public String getNewAssetCode() {
        return "ASSET" + getRandomInt(10000000);
    }
    
    public void reportError() {
        System.out.println(failMsg);
        log.error(failMsg);
    }
}