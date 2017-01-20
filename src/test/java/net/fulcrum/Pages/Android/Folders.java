package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;

public class Folders extends AndroidPageMain {


    public Folders (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    public void selectFolder(String folder) {
        driver.verifyTextView("Routines");
        driver.ad.scrollToExact(folder);
        driver.clickTextView(folder);
        verifyFolderName(folder);
    }

    public void verifyFolderName(String folder) {
        driver.verifyTextView(folder);
    }

}
