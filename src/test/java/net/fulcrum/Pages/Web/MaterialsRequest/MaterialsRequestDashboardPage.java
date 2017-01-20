package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class MaterialsRequestDashboardPage extends MaterialsRequestHomePage {

    private final String urlExtension = "/#/dashboard";
    //Header
    private final String pageHeader = "My Dashboard";
    private final By headerObject = By.xpath("//h1");

    //Section headers
    private final String ordersHeader = "Orders (25 Most Current)";
    private final String cartHeader = "Cart (First 10 Lines)";
    private final String myOrdersHeader = "My Orders (10 most Current)";
    private final String ordersToApproveHeader = "Orders to Approve (10 Most Current)";

    //Section header objects
    private final By ordersHeaderObject = By.xpath("//div[@id='page-wrapper']/div[2]/div/div/div");
    private final By cartHeaderObject = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div");
    private final By myOrdersHeaderObject = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div[2]/div/div");
    private final By ordersToApproveHeaderObject = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div[3]/div/div");

    //Buttons
    private final By cartButton = By.xpath("//a[contains(text(),'View Cart')]");
    private final By viewMyOrdersButton = By.xpath("//a[contains(text(),'View My Orders')]");
    private final By ordersToApproveButton = By.xpath("//a[contains(text(),'View Orders to Approve')]");

    //Orders Columns
    private final By orderCodeColumn = By.xpath("//table[@id='orderIds']/thead/tr/th");
    private final By shipByColumn = By.xpath("//table[@id='orderIds']/thead/tr/th[2]");
    private final By requestingLocationColumn = By.xpath("//table[@id='orderIds']/thead/tr/th[3]");
    private final By fromLocationColumn = By.xpath("//table[@id='orderIds']/thead/tr/th[4]");
    private final By statusColumn = By.xpath("//table[@id='orderIds']/thead/tr/th[5]");

    //My Orders Columns
    private final By myOrdersOrderCodeColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div[2]/div/div[2]/div/" +
            "div/div/table/thead/tr/th");
    private final By myOrdersShipByColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div[2]/div/div[2]/div/" +
            "div/div/table/thead/tr/th[2]");
    private final By myOrdersStatusColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div[2]/div/div[2]/div/" +
            "div/div/table/thead/tr/th[3]");

    //Orders Columns
    private final By ordersToApproveOrderCodeColumn = By.xpath("//div[@id='page-wrapper']/div[2]/" +
            "div[2]/div[3]/div/div[2]/div/div/div/table/thead/tr/th");
    private final By ordersToApproveShipByColumn = By.xpath("//div[@id='page-wrapper']/div[2]/" +
            "div[2]/div[3]/div/div[2]/div/div/div/table/thead/tr/th[2]");
    private final By ordersToApproveRequestingLocationColumn = By.xpath("//div[@id='page-wrapper']/div[2]/" +
            "div[2]/div[3]/div/div[2]/div/div/div/table/thead/tr/th[3]");

    //Cart Columns
    private final By partCodeColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/div/" +
            "table/thead/tr/th");
    private final By costColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/div/" +
            "table/thead/tr/th[2]");
    private final By requestedColumn = By.xpath("//div[@id='page-wrapper']/div[2]/div[2]/div/div/div[2]/div/div/div/" +
            "table/thead/tr/th[3]");

    public MaterialsRequestDashboardPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void refresh(String currentURL) {
        String refresh = currentURL + urlExtension;
        driver.navigate(refresh);
       // verifyHeader();
    }

    //Validation
    public void verifyPage() {
        verifyCartTable();
        verifyMyOrdersCartTable();
        verifyOrdersTable();
        verifyOrdersToApproveTable();
        verifyHeader();
    }

    public void verifyOrdersTable() {
        driver.assertText(ordersHeaderObject, ordersHeader);
        driver.assertText(orderCodeColumn, "Order Code");
        driver.assertText(shipByColumn, "Ship By");
        driver.assertText(requestingLocationColumn, "Requesting Location");
        driver.assertText(fromLocationColumn, "From Location");
        driver.assertText(statusColumn, "Status");
    }

    public void verifyCartTable() {
        driver.assertText(cartHeaderObject, cartHeader);
        driver.assertText(partCodeColumn, "Part Code");
        driver.assertText(costColumn, "Cost");
        driver.assertText(requestedColumn, "Requested");
    }

    public void verifyMyOrdersCartTable() {
        driver.assertText(myOrdersHeaderObject, myOrdersHeader);
        driver.assertText(myOrdersOrderCodeColumn, "Order Code");
        driver.assertText(myOrdersShipByColumn, "Ship By");
        driver.assertText(myOrdersStatusColumn, "Status");
    }

    public void verifyOrdersToApproveTable() {
        driver.assertText(ordersToApproveHeaderObject, ordersToApproveHeader);
        driver.assertText(ordersToApproveOrderCodeColumn, "Order Code");
        driver.assertText(ordersToApproveShipByColumn, "Ship By");
        driver.assertText(ordersToApproveRequestingLocationColumn, "Requesting Location");
    }

    public void verifyHeader() {
        driver.verifyTextExists(pageHeader);
    }

    //Click objects
    public void clickCart() {
        driver.click(cartButton);
    }

    public void clickViewMyOrders() {
        driver.click(viewMyOrdersButton);
    }

    public void clickOrdersToAppove() {
        driver.click(ordersToApproveButton);
    }
}
