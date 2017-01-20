package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class LocationTypes extends DataForm {

    public LocationTypes(WebBrowserDriver driver) {
        super(driver);
    }


    public void searchDescription(String desc) {
        driver.sendText(searchDescription, desc);
    }

    public void searchCity(String code) {
        driver.sendText(searchCodeComponent, code);
    }

    public void editDescription(String desc) {
        driver.sendText(editDescription, desc);
    }

    public void editCodeComponent(String code){
        driver.sendText(editCodeComponent, code);
    }



}
