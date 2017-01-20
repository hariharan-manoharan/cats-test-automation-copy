package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


public class MaterialsRequestSurveySetupPage extends MaterialsRequestHomePage {


    //Header
    private final String pageHeader = "EM Survey Setup";
    private final By headerObject = By.cssSelector("h1.ng-scope");

    //Columns
    private final By statusColumn = By.xpath("//label");
    private final By surveyNameColumn = By.xpath("//th[2]/label");
    private final By descriptionColumn = By.xpath("//th[3]/label");
    private final By surveyTypeColumn = By.xpath("//th[4]/label");
    private final By startDateColumn = By.xpath("//th[5]/label");
    private final By endDateColumn = By.xpath("//th[6]/label");

    //Buttons
    private final By viewSurvey1Button = By.xpath("//td[8]/button");

    public MaterialsRequestSurveySetupPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(statusColumn, "Status");
        driver.assertText(surveyNameColumn, "Survey Name");
        driver.assertText(descriptionColumn, "Description");
        driver.assertText(surveyTypeColumn, "Survey Type");
        driver.assertText(startDateColumn, "Start Date");
        driver.assertText(endDateColumn, "End Date");
    }

    public void verifyTitle() {
        driver.assertText(headerObject, pageHeader);
    }

    //Click
    public void clickViewButton(){
        driver.click(viewSurvey1Button);
    }

}
