package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import java.util.ArrayList;

public class MaterialsRequestRoleAssignmentPage extends MaterialsRequestModuleSettingsPage {

    private List<String> activeSecurityProfiles = new ArrayList<String>();
    private List<String> inactiveSecurityProfiles = new ArrayList<String>();
    private List<String> roles = new ArrayList<String>();
    private List<WebElement> roleEntries = new ArrayList<WebElement>();

    //Dropdowns
    private final By emRoleDropdown = By.xpath("//select[@id='emRoleName']");
    private final By securityProfilesDropdown = By.xpath("//select[@id='securityProfileName']");

    //Search field
    private final By filterUsersSearchField = By.xpath("//input[@id='unsubscriber']");

    //Buttons
    private final By saveButton = By.xpath("//button[2]");
    private final By deleteButton = By.xpath("//div[5]/div/button");
    private final By noButton = By.xpath("//div[3]/button");
    private final By yesButton = By.xpath("//div[3]/button[2]");
    //Columns
    private final By emRoleNameColumn= By.xpath("//table[@id='roles']/thead/tr/th[2]");
    private final By catsSecurityProfileNameColumn = By.xpath("//table[@id='roles']/thead/tr/th[3]");
    private final By addedByColumn = By.xpath("//table[@id='roles']/thead/tr/th[4]");

    //Messages
    private final By messageObject = By.xpath("//div[@id='emroles']/div/div/div");
    private final String adminRemoveMessage = "The Admin Role to Admin Security Profile association cannot be removed.";

    public MaterialsRequestRoleAssignmentPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        activeSecurityProfiles.add("ADMIN");
        activeSecurityProfiles.add("AREA COORDINATOR");
        activeSecurityProfiles.add("AREA MANAGER");
        activeSecurityProfiles.add("FULCRUM");
        activeSecurityProfiles.add("REGIONAL COORDINATOR");
        activeSecurityProfiles.add("REGIONAL MANAGER");
        activeSecurityProfiles.add("SUPER USER");
        activeSecurityProfiles.add("WAREHOUSE MANAGER");
        activeSecurityProfiles.add("EQUIPMENT ENGINEER");
        activeSecurityProfiles.add("FIELD TECHNICIAN");
        activeSecurityProfiles.add("SWITCH TECHNICIAN");
        activeSecurityProfiles.add("WAREHOUSE USER");

        inactiveSecurityProfiles.add("FIXED ASSETS");
        inactiveSecurityProfiles.add("REPORTS USER");
        inactiveSecurityProfiles.add("Z_CATS_USR");
        inactiveSecurityProfiles.add("HQ SUPER USER");
        inactiveSecurityProfiles.add("PSFT ITEM MASTER");
        inactiveSecurityProfiles.add("SECURITY ADMIN");

        roles.add("ADMIN");
        roles.add("APPROVER");
        roles.add("REQUESTOR");
        roles.add("REVIEWER");
    }

    //Navigate
    public void navigate() {
        clickAdmin();
        clickRoleAssignment();
    }

    //Click objects
    public void clickSave() {
        driver.click(By.xpath("//button[2]"));
    }

    public void clickDelete() {
        driver.click(deleteButton);
    }

    public void clickYes() {
        driver.click(yesButton);
    }

    public void clickNo() {
        driver.click(noButton);
    }

    public void clickCheckbox(int num) {
        driver.clickCheckbox(num);
    }

    //Get
    public List<String> getActiveSecurityProfiles() {
        return activeSecurityProfiles;
    }

    public By getDeleteButton() {
        return deleteButton;
    }

    public By getSaveButton() {
        return saveButton;
    }

    //Search
    public void enterSearchFilter(String filter) {
        driver.sendText(filterUsersSearchField, filter);
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(emRoleNameColumn, "EM Role Name");
        driver.assertText(catsSecurityProfileNameColumn, "CATS Security Profile Name");
        driver.assertText(addedByColumn, "Added By");
    }

    public void verifyActiveSecurityProfiles() {
        for (int i = 0; i < activeSecurityProfiles.size(); i++) {
            driver.verifyTextExists(activeSecurityProfiles.get(i));
        }
    }

    public void verifyRoles() {
        for (int i = 0; i < roles.size(); i++) {
            verifyDropdownRole(roles.get(i));
        }
    }

    public void verifyInactiveProfiles() {
        for (int i = 0; i < inactiveSecurityProfiles.size(); i++) {
            verifyDropdownProfile(inactiveSecurityProfiles.get(i));
        }
    }

    public void verifyAdminRemoveMessage() {
        driver.assertText(messageObject, adminRemoveMessage);
    }

    public boolean  verifyDefaultRoles() {
        HashMap<String, String> defaultRoles = new HashMap<String, String>();
        boolean flag = true;

        defaultRoles.put("ADMIN", "ADMIN");
        defaultRoles.put("APPROVER", "AREA COORDINATOR");
        defaultRoles.put("APPROVER", "AREA MANAGER");
        defaultRoles.put("APPROVER", "REGIONAL COORDINATOR");
        defaultRoles.put("APPROVER", "REGIONAL MANAGER");
        defaultRoles.put("APPROVER", "WAREHOUSE MANAGER");
        defaultRoles.put("REQUESTOR", "EQUIPMENT ENGINEER");

        for (String pair : defaultRoles.keySet()) {
            if (!verifyProfileRoleCombination(pair, defaultRoles.get(pair))) {
                flag = false;
                System.out.println("Unable to find role combination: " + pair + " (Role) and "
                        + defaultRoles.get(pair) + " (Profile).");
            }
        }

        return flag;
    }

    //Actions
    public boolean deleteProfileEntry(String profile) {
        roleEntries = getDefaultRoles();
        int index = driver.findTableValueIndex(roleEntries, profile, 2, 4);
        System.out.println("Index:" + index);

        if (index == 0) {
            driver.click(By.xpath("//table[@id='roles']/tbody/tr/td/input"));
        } else if (index >= 1) {
            driver.click(By.xpath("//table[@id='roles']/tbody/tr[" + index + "]/td/input"));
        } else {
            System.out.println("Role not found");
            return false;
        }

        clickDelete();
        clickYes();
        return true;

    }

    public boolean verifyProfileRoleCombination(String role, String profile) {
        roleEntries = driver.wd.findElements(By.xpath("//table[@id='roles']/tbody/tr/td"));
        int index = driver.findTableValuesIndex(roleEntries, role, profile, 1, 1, 4);

        if (index >= 0) {
            return true;
        }
        return false;
    }

    public boolean verifyDropdownRole(String role) {
        return driver.verifyDropdownList(emRoleDropdown, role);
    }

    public boolean verifyDropdownProfile(String profile) {
        return driver.verifyDropdownList(securityProfilesDropdown, profile);
    }

    public List<WebElement> getDefaultRoles() {
        return driver.wd.findElements(By.xpath("//table[@id='roles']/tbody/tr/td"));
    }

    public void addProfileRole(String profile, String role) {
        selectSecurityProfiles(profile);
        selectEMRole(role);
        clickSave();
        verifyProfileRoleCombination(role, profile);
    }

    //Select dropdown
    public void selectEMRole(String emRole) {
        driver.selectDropDownValue(emRoleDropdown, emRole);
    }

    public void selectSecurityProfiles(String activeSecurityProfiles) {
        driver.selectDropDownValue(securityProfilesDropdown, activeSecurityProfiles);
    }
}
