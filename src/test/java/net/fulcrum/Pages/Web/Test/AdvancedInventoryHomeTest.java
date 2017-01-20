package net.fulcrum.Pages.Web.Test;

import net.fulcrum.Pages.Web.AdvancedInventory.AdvancedInventoryHomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.annotations.*;

import java.util.Random;

@Listeners(TestRailListener.class)
public class AdvancedInventoryHomeTest extends MainTest {

    protected String title = "CATS Advanced Inventory: Login";
    private final static String application = "inventory";
    private Random random = new Random();
    private String newProposal;

    private static AdvancedInventoryHomePage advanced;

    protected String userName = String.valueOf(System.getProperty("userName"));
    protected String passWord = String.valueOf(System.getProperty("passWord"));

    @BeforeClass
    public void setUp() {
        advanced = new AdvancedInventoryHomePage(driver);
        wd.get(url + application);
        newProposal = "CTA-NewProposal-" + random.nextInt(1000);
    }

    @TestRailCase(automationId = "1000")
    @Test(priority = 1)
    public void login() throws InterruptedException {
        login.login(title, userName, passWord);
    }

    @TestRailCase(automationId = "1001")
    @Test(priority = 2, enabled = true)
    public void navigateToInventory() {
        advanced.clickInventoryIcon();
    }

    @TestRailCase(automationId = "1002")
    @Test(priority = 3, enabled = true)
    public void navigateToProposals() {
        wd.get(url + application);
        advanced.clickProposalsIcon();
    }


    @TestRailCase(automationId = "1003")
    @Test(priority = 4, enabled = true)
    public void createNewProposal() {
        advanced.enterNewRecord(newProposal);
        advanced.clickCreateProposal();
    }




}
