package net.fulcrum.Pages.Android;

        import net.fulcrum.Drivers.AndroidMobilityDriver;
        import org.openqa.selenium.By;

public class Routines extends AndroidPageMain {

    protected Menu menu;

    public Routines (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    public void selectRoutine(String routine) {
        driver.verifyTextView("Routines");
        driver.ad.scrollToExact(routine);
        driver.clickTextView(routine);
    }

}
