package net.fulcrum.Pages.iOS;

import org.openqa.selenium.*;
import net.fulcrum.Drivers.iOSMobilityDriver;

public class HelpPage extends iOSMainPage {

    protected static By helpButton = By.name("Help");
    protected static By doneButton = By.name("Done");
    protected static By cancelButton = By.name("Cancel");


    public HelpPage (iOSMobilityDriver driver) {
        this.driver = driver;
    }

    public void clickHelp() {
        driver.click(helpButton);
        driver.click(cancelButton);
    }

    public void clickDone() {
        driver.click(doneButton);
    }

    public boolean validateVersion(String version) {
        System.out.println("Validating the following Mobility Version: " + version);
        return driver.validateText(version);
    }

    public boolean validateCopyright(String copyright) {
        System.out.println("Validating the following Copyright: " + copyright);
        return driver.validateText(copyright);
    }


    public boolean validateBuildInfo (String info) {
        System.out.println("Validating the following Build Info: " + info);
        return driver.validateText(info);
    }

    public boolean validateSDK (String SDK) {
        System.out.println("Validating the following SDK: " + SDK);
        return driver.validateText(SDK);
    }

}
