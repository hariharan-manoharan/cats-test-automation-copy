package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


public class MaterialsRequestViewSurveyPage extends MaterialsRequestHomePage {

    //Header
    private final String pageHeader = "View Survey: ORDER QUALITY SURVEY";
    private final By headerObject = By.cssSelector("//h4");

    //Links
    private final By questionsAnswersLink = By.xpath("//a[contains(text(),'Questions & Answers')]");

    //Fields
    private final By surveyNameField = By.xpath("//div/label");
    private final By descriptionField = By.xpath("//div[5]/div/label");
    private final By surveyTypeField = By.xpath("//div[8]/div");
    private final By startDateField = By.xpath("//div[2]/label");
    private final By endDateField = By.xpath("//div[3]/label");
    private final By addedByField = By.xpath("//div[4]/div/div/label");
    private final By addedOnField = By.xpath("//div[4]/div/div[2]/label");
    private final By modifiedByField = By.xpath("//div[7]/div/div/label");
    private final By modifiedOnField = By.xpath("//div[7]/div/div[2]/label");
    private final By rateField = By.xpath("//b");
    private final String rateMessage = "RATE OVERALL FACILITY ITEM/PROCESS QUALITY (OPTIONAL):";

    //Buttons
    private final By closeButton = By.xpath("//div[3]/button");

    public MaterialsRequestViewSurveyPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyFields() {
        driver.assertText(surveyNameField, "Survey Name");
        driver.assertText(descriptionField, "Description");
        driver.assertText(surveyTypeField, "Survey Type");
        driver.assertText(startDateField, "Start Date");
        driver.assertText(endDateField, "End Date");
        driver.assertText(addedByField, "Added By");
        driver.assertText(addedOnField, "Added On");
        driver.assertText(modifiedByField, "Modified By");
        driver.assertText(modifiedOnField, "Modified On");
    }

    public void verifyTitle() {
        driver.assertText(headerObject, pageHeader);
    }

    //Click
    public void clickCloseButton(){
        driver.click(closeButton);
    }

}
