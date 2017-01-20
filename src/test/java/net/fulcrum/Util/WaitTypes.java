package net.fulcrum.Util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitTypes {

    /**
     * Get element when it is ready
     * @param driver
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement getWhenVisible(WebDriver driver, By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(locator));
        return element;
    }

    /**
     * Click the element once it is ready
     * @param driver
     * @param locator
     * @param timeout
     */
    public static void clickWhenReady(WebDriver driver, By locator, int timeout) {
        WebElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }
}
