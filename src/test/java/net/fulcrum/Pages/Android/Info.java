package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;

public class Info extends AndroidPageMain  {

    protected By helpButton = By.xpath(".//android.widget.Button[@text='Help']");
    protected By doneButton =  By.xpath(".//android.widget.Button[@text='Done']");
    protected By gpsButton = By.xpath(".//android.widget.Button[@text='Force GPS Refresh']");
    protected By backButton = By.xpath(".//android.widget.TextView[@text='Info']");
    protected By catsText = By.xpath(".//android.widget.TextView[@text='CATS']");
    protected By mobilityText = By.xpath(".//android.widget.TextView[@text='Mobility']");
    protected By fulcrumText = By.xpath(".//android.widget.TextView[@text='Fulcrum']");
    protected By gpsText = By.xpath(".//android.widget.TextView[@text='GPS Info']");
    protected By latitudeText = By.xpath(".//android.widget.TextView[@text='Latitude: ']");
    protected By longitudeText = By.xpath(".//android.widget.TextView[@text='Longitude: ']");
    protected By accuracyText = By.xpath(".//android.widget.TextView[@text='Accuracy: ']");

    public Info (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    public void verifyPage() {
        driver.waitForElement(helpButton, 30);
        driver.waitForElement(doneButton, 30);
        driver.waitForElement(gpsButton, 30);
        driver.waitForElement(backButton, 30);
        driver.waitForElement(catsText, 30);
        driver.waitForElement(mobilityText, 30);
        driver.waitForElement(fulcrumText, 30);
        driver.waitForElement(gpsText, 30);
    }
    public void clickHelp() {
        driver.click(helpButton);
    }

    public void clickDone() {
        driver.click(doneButton);
    }

    public void clickGPSRefresh() {
        driver.click(gpsButton);
        driver.waitForElement(latitudeText, 60);
        driver.waitForElement(longitudeText, 30);
        driver.waitForElement(accuracyText, 30);
    }

    public void clickBackButton() {
        driver.click(backButton);
    }

}
