package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class LocationGroups extends DataForm {

    public LocationGroups(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchDescription = By.name("DESCRIPTION");

    //Edit Tab Columns
    protected By editDescription = By.xpath("(//input[@name='DESCRIPTION'])[2]");

    public void enterDescription(String desc) {
        driver.sendText(searchDescription, desc);
    }

    public void enterEditDescription(String desc) {
        driver.sendText(editDescription, desc);
    }

}
