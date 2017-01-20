package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class ValidationsPage extends iOSMainPage {

    private By file;

    public ValidationsPage (iOSMobilityDriver driver) {
        this.driver = driver;
    }

    public boolean downloadFile(int fileIndex) {
        file = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[" + fileIndex + "]/UIAButton[1]");
        try {
            driver.click(file);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean clickValidationFile(int fileIndex) {
        file = By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATableView[1]/UIATableCell[" + fileIndex + "]");
        try {
            driver.click(file);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
