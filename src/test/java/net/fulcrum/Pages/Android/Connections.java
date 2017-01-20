package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;

public class Connections extends AndroidPageMain {

    protected By addConnectionButton = By.id("net.fulcrum.mobility:id/connections_add");
    protected By backButton = By.name("Connections, Navigate up");

    public Connections (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    public void clickAddConnection() {
        driver.click(addConnectionButton);
    }

    public boolean verifyNoConnections() {
        return driver.verifyTextByName("You have no connections");
    }

    public void clickBackButton() {
        driver.click(backButton);
    }

    public void selectConnection (String connectionName) {
        driver.clickTextView(connectionName);
    }

}
