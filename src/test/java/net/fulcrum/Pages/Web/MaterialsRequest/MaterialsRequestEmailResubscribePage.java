package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MaterialsRequestEmailResubscribePage extends MaterialsRequestModuleSettingsPage {


    private List<WebElement> users = new ArrayList<WebElement>();

    //Search field
    private final By filterUsersSearchField = By.xpath("//input[@id='unsubscriber']");

    //Buttons
    private final By resubcribeButton = By.xpath("//div[2]/button");
    private final By resubcribeNoButton = By.xpath("//div[3]/button");
    private final By resubcribeYesButton = By.xpath("//div[3]/button[2]");

    //Columns
    private final By requestByColumn = By.xpath("//table[@id='unsubscribers']/thead/tr/th[2]");
    private final By applicationColumn = By.xpath("//table[@id='unsubscribers']/thead/tr/th[3]");
    private final By unsubscribeColumn = By.xpath("//table[@id='unsubscribers']/thead/tr/th[4]");

    //Messages
    private final String unsubscribeTitlePrompt = "Resubscribe User?";
    private final String unsubscribeMessagePrompt = "Are you sure you want to resubscribe this user for email?";
    private final By promptMessageObject = By.xpath("//div[5]/div/div/div[2]/p");
    private final By promptTitleObject = By.xpath("//div[5]/div/div/div/h3");

    public MaterialsRequestEmailResubscribePage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Get
    public By getResubcribeButton() {
        return resubcribeButton;
    }

    //Click objects
    public void clickYes() {
        driver.click(resubcribeYesButton);
    }
    public void clickNo() {
        driver.click(resubcribeNoButton);
    }

    public void clickResubscribe() {
        driver.click(resubcribeButton);
    }

    public void clickCheckbox(int num) {
        By checkBox;

        if (num > 1) {
            checkBox = By.xpath("(//input[@type='checkbox'])[" + num + "]");
        } else {
            checkBox = By.xpath("(//input[@type='checkbox'])");
        }

        driver.click(checkBox);
    }

    //Search
    public void enterSearchFilter(String filter) {
        driver.sendText(filterUsersSearchField, filter);
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(requestByColumn, "Request By");
        driver.assertText(applicationColumn, "Application");
        driver.assertText(unsubscribeColumn, "Unsubcribe");
    }

    public void verifyFirstUser(String user) {
        driver.assertText(By.xpath("//td[2]"), user);
    }

    public void verifyUnsubscribePrompt() {
        driver.assertText(promptTitleObject, unsubscribeTitlePrompt);
        driver.assertText(promptMessageObject, unsubscribeMessagePrompt);
    }

    public boolean verifyUserTable(String user) {
        users = driver.wd.findElements(By.xpath("//table[@id='unsubscribers']/tbody/tr/td"));
        int index = driver.findTableValuesIndex(users, user, "Y", 1, 2, 4);

        if (index >= 0) {
            return true;
        }
        return false;
    }

    public boolean verifyUnsubscribeY() {
        users = driver.wd.findElements(By.xpath("//table[@id='unsubscribers']/tbody/tr/td"));
        return driver.verifyTableValue(users, "EM", "Y", 2, 1, 4);
    }

    //navigation
    public void navigate() {
        clickAdmin();
        selectEMModuleSettings();
        clickEmailResubscribe();
        verifyColumns();
    }

    //Actions
    public boolean resubscribeUser(String user) {
        users = driver.wd.findElements(By.xpath("//table[@id='unsubscribers']/tbody/tr/td"));
        int index = driver.findTableValueIndex(users, user, 1, 4);

        if (index == 0) {
            driver.click(By.xpath("//input[@type='checkbox']"));
        } else if (index >= 1) {
            driver.click(By.xpath("(//input[@type='checkbox'])[" + index + "]"));
        } else {
            System.out.println("User not found");
            return false;
        }
        System.out.println("Index: " + index);

        clickResubscribe();
        clickYes();
        return true;
    }

}
