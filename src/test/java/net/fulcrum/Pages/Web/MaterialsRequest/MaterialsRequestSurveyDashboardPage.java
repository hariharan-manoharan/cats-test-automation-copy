package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


public class MaterialsRequestSurveyDashboardPage extends MaterialsRequestHomePage {


    //Header
    private final String pageHeader = "EM Survey Dashboard";
    private final By headerObject = By.cssSelector("//h1");

    //Messages
    private final By noResultsObject = By.xpath("//h3");
    private final String noSurveyResults = "No survey results are available.";


    public MaterialsRequestSurveyDashboardPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void verifyTitle() {
        driver.assertText(headerObject, pageHeader);
    }

    public void verifyNoResults() {
        driver.assertText(noResultsObject, noSurveyResults);
    }

}
