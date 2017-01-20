package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class MaterialsRequestEmailUnsubscribePage extends MaterialsRequestHomePage {

    //Buttons
    private final By yesButton = By.xpath("//div[5]/a/button");
    private final By noButton = By.xpath("//a[2]/button");

    //Header
    private final String pageHeader = "Email Unsubscribe";

    //Messages
    private final String unsubcribedMessage = "You have already unsubscribed from Excess Management emails. " +
            "Please contact the CATS SysAdmin team to re-subscribe.";
    private final String emailUnsubscribeMessage = "Are you sure you want to unsubscribe from all future Excess Management emails?";

    //Columns
    private final By requestedByColumnObject = By.xpath("//th/label");
    private final By applicationColumnObject = By.xpath("//th[2]/label");
    private final By unsubscribeColumnObject = By.xpath("//th[3]/label");
    private final By unsubscribeDateColumnObject = By.xpath("//th[4]/label");

    public MaterialsRequestEmailUnsubscribePage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyPrompt() {
        driver.verifyTextExists(emailUnsubscribeMessage);
    }

    public void verifyHeader() {
        driver.verifyTextExists(pageHeader);
    }

    public void verifyUnsubcribedMessage() {
        driver.verifyTextExists(unsubcribedMessage);
    }

    public void verifyColumns() {
        driver.assertText(requestedByColumnObject, "Requested By");
        driver.assertText(applicationColumnObject, "Application");
        driver.assertText(unsubscribeColumnObject, "Unsubscribe");
        driver.assertText(unsubscribeDateColumnObject, "Unsubscribe Date");
    }

    //Click objects
    public void clickYes() {
        driver.click(yesButton);
    }

    public void clickNo() {
        driver.click(noButton);
    }

    public void navigate() {
       // clickOrdersSettings();
        clickEmailUnsubscribe();
        verifyHeader();
    }

}
