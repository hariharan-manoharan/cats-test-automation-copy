package net.fulcrum.Pages.Web.QueryBuilder;

import net.fulcrum.Drivers.*;
import junit.framework.Assert;
import org.openqa.selenium.*;

public class QueryBuilderHomePage  {

    protected WebBrowserDriver driver;
    protected WebElement element;
    
    private final By availableViewsText = By.cssSelector("h1.ng-scope");
    private final By schedulesTab = By.linkText("Schedules");
    private final By queriesTab = By.linkText("Queries");
    private final By historyTab = By.linkText("History");
    private final By inventoryViewLink = By.linkText("Inventory");
    private final By assetAgingViewLink = By.linkText("Asset Aging");
    private final By assetsViewLink = By.linkText("Assets");
    private final By purchaseOrderReceiptsViewLink = By.linkText("Purchase Order Receipts");
    private final By runButton = By.linkText("Run");
    private final By newFilterButton = By.linkText("New Filter");
    private final By downloadQueryResultsLink = By.linkText("Download query results");
    private final By publicCheckbox = By.id("public-checkbox");
    private final By privateCheckbox = By.id("private-checkbox");
    private final By favoriteCheckbox = By.id("favorite-checkbox");
    
    public QueryBuilderHomePage (WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Query Builder", getTitle);
    }

    public void clickSchedulesTab() {
        driver.click(schedulesTab);
    }

    public void clickQueriesTab() {
        driver.click(queriesTab);
    }

    public void clickHistoryTab() {
        driver.click(historyTab);
    }

    public void clickAssetsViewLink() {
        driver.click(assetsViewLink);
    }

    public void clickInventoryViewLink() {
        driver.click(inventoryViewLink);
    }

    public void clickAssetAgingViewLink() {
        driver.click(assetAgingViewLink);
    }

    public void clickPurchaseOrdersViewLink() {
        driver.click(purchaseOrderReceiptsViewLink);
    }

    public void validateAvailableViewsTitle() {
        element = driver.getElement(By.name("Available Views"));
        Assert.assertEquals(element.getText(), "Locations");
    }

    public void validatePage () {
        validateAvailableViewsTitle();
    }

    public void clickPublicCheckbox() {
        driver.click(publicCheckbox);
    }

    public void clickPrivateCheckbox() {
        driver.click(privateCheckbox);
    }

    public void clickFavoriteCheckbox() {
        driver.click(favoriteCheckbox);
    }

    public void clickDownloadQueryResultsLink() {
        driver.click(downloadQueryResultsLink);
    }

    public void clickNewFilterButton() {
        driver.click(newFilterButton);
    }

    public void clickRunBtton() {
        driver.click(runButton);
    }


}
