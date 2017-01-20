package net.fulcrum.Pages.iOS;

import net.fulcrum.Drivers.*;
import net.fulcrum.Util.WaitTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.*;

public class iOSMainPage {

    protected WebElement element;
    protected iOSMobilityDriver driver;

    protected static By backButton = By.name("Back");

    public void clickBackButton () {
        driver.click(backButton);
    }

}