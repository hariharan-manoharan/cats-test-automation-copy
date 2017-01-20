package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import java.util.*;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;

public class MaterialsRequestOrderPage extends MaterialsRequestHomePage {

    private List<String> rejectionReasons = new ArrayList<String>();
    private Random rand = new Random();

    //Textfields
    private final By rejectionExplanationTextField = By.xpath("//input[@type='text']");

    //Buttons
    protected final By processRequestButton = By.xpath("(//button[@type='button'])[3]");
    protected final By cancelRequestButton = By.xpath("(//button[@type='button'])[2]");

    //Dropdown
    private final By rejectionReasonDropDown = By.xpath("//select");

    public MaterialsRequestOrderPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
        rejectionReasons.add("Broken");
        rejectionReasons.add("Lost");
        rejectionReasons.add("Other");
    }

    //Validation
    public void verifyColumns() {
        driver.verifyTextExists("Part Code");
        driver.verifyTextExists("Part Description");
        driver.verifyTextExists("From BU");
        driver.verifyTextExists("Requested");
        driver.verifyTextExists("Approved");
    }

    //Click objects
    public void clickCancelRequest() {
        driver.click(cancelRequestButton);
    }

    public void clickProcessRequest() {
        driver.click(processRequestButton);
    }

    //Dropdown select
    public void selectRejectionReason() {
        String reason = rejectionReasons.get(rand.nextInt(rejectionReasons.size() - 1));
        selectRejectionReason(reason);
    }

    public void selectRejectionReason(String reason) {
        driver.selectDropDownValue(rejectionReasonDropDown, reason);

        if (reason.equalsIgnoreCase("Other")) {
            enterRejectionExplanation("Explanation");
        }
    }

    //Input
    public void enterRejectionExplanation(String explanation) {
        driver.sendText(rejectionExplanationTextField, explanation);
    }

}
