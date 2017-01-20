package net.fulcrum.Pages.Web.Test;

import java.util.concurrent.TimeUnit;
import java.util.Properties;

import net.fulcrum.Pages.Web.*;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Web.AdvancedInventory.*;
import net.fulcrum.Pages.Web.CenterPoint.*;
import net.fulcrum.Pages.Web.Requisitions.*;
import net.fulcrum.Pages.Web.QueryBuilder.*;
import net.fulcrum.testrail.TestRailListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;
import org.apache.log4j.*;

@Listeners(TestRailListener.class)
public class MainTest {


    protected static WebDriver wd;
    protected static WebBrowserDriver driver;
    protected static String url;
    protected static String applicationURL;

    protected static Logger log = Logger.getLogger("Maintest.class");

    protected static String title = "CATS CenterPoint:";
    protected String application = "cats";

    //Determine if local or remote build
    protected boolean local = Boolean.valueOf(System.getProperty("local"));
    protected String localOS;

    //Default Values
    protected String userName = String.valueOf(System.getProperty("userName"));
    protected String passWord = String.valueOf(System.getProperty("passWord"));
    protected String environment = String.valueOf(System.getProperty("environment"));

    //URLs for XO, VZW, and Core environments
    protected static String xoURL = "https://172.16.32.29:8443/";
    protected static String coreURL = "https://172.16.32.25/";
    protected static String core74URL = "https://172.16.32.21:8443/";
    protected static String vzwURL = "http://172.16.0.121:8001/";

    //Declare application pages
    protected static MenuPage menu;
    protected static LoginPage login;
    protected static CenterPointHomePage centerPoint;
    protected static ForgotPasswordPage forgotPassword;
    protected static RequisitionsHomePage requisitionsHome;
    protected static RequisitionsPage requisitions;
    protected static RequisitionsAddTypePage requisitionsAddType;
    protected static RequisitionsTypesPage requisitionsTypes;
    protected static RequisitionsCreatePage reqsCreate;
    protected static RequisitionsAlternateApproverPage reqsAlternateApprover;
    protected static RequisitionsKitsPage reqsKits;
    protected static AdvancedInventoryHomePage advanced;

    protected DesiredCapabilities capabilities;

    @BeforeClass
    public void beforeClass() throws Exception {
        PropertyConfigurator.configure("src/test/resources/log4j.properties");

        if (local) {
            localOS = String.valueOf(System.getProperty("os"));

            //Set support drivers for Chrome and Firefox
            if (localOS.equalsIgnoreCase("Windows"))  {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
            }

            DesiredCapabilities capability = DesiredCapabilities.firefox();
            capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            wd = new ChromeDriver();
            userName = String.valueOf(System.getProperty("userName"));
            passWord = String.valueOf(System.getProperty("passWord"));
            environment = String.valueOf(System.getProperty("environment"));
        }

        if (environment.equalsIgnoreCase("XO")) {
            url = xoURL;
        } else if (environment.equalsIgnoreCase("CORE")) {
            url = coreURL;
        } else if (environment.equalsIgnoreCase("VZW")) {
            url = vzwURL;
        } else {
            url = core74URL;
        }

        driver = new WebBrowserDriver(wd);

        //Set application pages
        menu = new MenuPage(driver);
        login = new LoginPage(driver, url);
        forgotPassword = new ForgotPasswordPage(driver);

        wd.manage().window().maximize();
        //wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        //wd.close();
        //wd.quit();
    }

    public void refreshPage() {
        wd.manage().window().maximize();
    }

    public void verifyText(String text) {
        driver.verifyTextExists(text);
    }

    public void verifyText(int num) {
        driver.verifyTextExists(String.valueOf(num));
    }

}
