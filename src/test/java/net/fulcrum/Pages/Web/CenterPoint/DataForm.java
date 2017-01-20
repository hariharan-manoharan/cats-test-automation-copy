package net.fulcrum.Pages.Web.CenterPoint;

import junit.framework.Assert;
import net.fulcrum.Util.WaitTypes;
import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


public class DataForm extends CenterPointHomePage {

    public DataForm(WebBrowserDriver driver) {
        super(driver);
    }

    protected By resultsTab = By.id("tab-idResults-handle");
    protected By editTab = By.id("tab-idEdit-handle");
    protected By searchTab = By.id("tab-idSearch-handle");

    //Search Tab Elements
    protected By searchTabClearButton = By.xpath("//div[@id='tab-idSearch']/div/div/div");
    protected By searchTabSearchButton = By.xpath("//div[@id='tab-idSearch']/div/div[2]/div");

    //Results Tab Elements
    protected By resultsTabClearButton = By.xpath("//div[@id='tab-idResults']/div/div/div");
    protected By resultsTabPreviousButton = By.xpath("//div[@id='tab-idResults']/div/div[7]/div");
    protected By resultsTabNextButton = By.xpath("//div[@id='tab-idResults']/div/div[7]/div[2]");
    protected By resultsTabRefreshButton = By.xpath("//div[@id='tab-idResults']/div/div[5]/div");

    //Edit Tab Elements
    protected By editTabClearButton = By.xpath("//div[@id='tab-idEdit']/div/div/div");
    protected By editTabSaveButton = By.xpath("//div[@id='tab-idEdit']/div/div[3]/div");
    protected By editTabPreviousButton = By.xpath("//div[@id='tab-idEdit']/div/div[5]/div");
    protected By editTabNextButton = By.xpath("//div[@id='tab-idEdit']/div/div[5]/div[2]");

    //Search Tab Columns
    protected By searchDescription = By.name("DESCRIPTION");
    protected By searchCodeComponent = By.name("CODECOMPONENT");

    //Edit Tab Columns
    protected By editDescription = By.xpath("(//input[@name='DESCRIPTION'])[2]");
    protected By editCodeComponent = By.xpath("(//input[@name='CODECOMPONENT'])[2]");

    public void clickEditTab() {
        driver.click(editTab);
    }

    public void clickSearchTab() {
        driver.click(searchTab);
    }

    public void clickResultsTab() {
        driver.click(resultsTab);
    }

    public void enterField(String field, String value) {
        try {
            driver.sendText(By.name(field), value);
        } catch (NoSuchElementException ex) {
            System.out.println("Error: Cannot find object to select " + field + " dataform");

        }
    }

    public void clickSearchButton() {
        driver.click(searchTabSearchButton);
    }

    public void clickResultsTabNextButton() {
        driver.click(resultsTabNextButton);
    }

    public void clearResults() {
        driver.click(resultsTabClearButton);
    }

    public void clickWebReports() {
        driver.click(webReportsFolder);
    }


}
