package net.fulcrum.Pages.iOS.Test;

import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.Properties;
import java.lang.*;
import java.io.*;

import net.fulcrum.Pages.iOS.*;
import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.Verizon.ExpressStock;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;


import io.appium.java_client.ios.IOSDriver;

@Listeners(TestRailListener.class)
public class MainTest {

    private static final String localAppiumURL = "http://0.0.0.0:4723/wd/hub";

    //Properties
    protected String username;
    protected String password;
    protected String reset =  String.valueOf(System.getProperty("reset"));
    protected String connectionName;
    protected String connectionHost;
    protected String connectionPort;
    protected String testProfile;
    protected boolean multipleProfiles;
    protected String databaseIP;
    protected boolean databaseConnection = Boolean.valueOf(System.getProperty("databaseConnection"));
    protected boolean ssl;
    protected boolean savePassword;
    protected boolean local = Boolean.valueOf(System.getProperty("local"));
    protected String apkPath = String.valueOf(System.getProperty("apkpath"));
    protected String device = String.valueOf(System.getProperty("device"));
    protected String environmentFile = "environments/" +
            String.valueOf(System.getProperty("properties")) + ".properties";

    //Objects
    protected IOSDriver iosDriver;
    protected iOSMobilityDriver mobilityDriver;
    protected OracleDriver oracleDriver;

    //Pages
    protected LoginPage login;
    protected SelectConnectionPage selectConnection;
    protected AddConnectionPage addConnection;
    protected ProfilesPage profiles;
    protected MainMenuPage mainMenu;
    protected ViewInventoryPage viewInventory;
    protected ExpressStock expressStock;
    protected HelpPage help;
    protected ValidationsPage validations;

    protected InputStream inputStream;
    protected DesiredCapabilities capabilities = DesiredCapabilities.iphone();
    protected boolean result;
    protected WebElement element;

    @BeforeSuite(alwaysRun=true)
    public void setUp() throws IOException {
        String URL_STRING;

        try {
            Properties properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(environmentFile);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Cannot find the file: " + environmentFile);
            }

            username = properties.getProperty("username");
            password = properties.getProperty("password");
            savePassword = Boolean.valueOf(properties.getProperty("savePassword"));
            connectionName = properties.getProperty("name");
            databaseIP = properties.getProperty("database");
            databaseConnection = Boolean.valueOf(properties.getProperty("databaseConnection"));
            ssl = Boolean.valueOf(properties.getProperty("iOSSSL"));
            testProfile = properties.getProperty("testProfile");
            multipleProfiles = Boolean.valueOf(properties.getProperty("multipleProfiles"));

            if (!local) {
                connectionHost = properties.getProperty("externalHost");
                connectionPort = properties.getProperty("iOSExternalPort");
            } else {
                connectionHost = properties.getProperty("host");
                connectionPort = properties.getProperty("iOSInternalPort");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("platformVersion","9.2");
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
        capabilities.setCapability("newCommandTimeout", 1200);

        URL_STRING = localAppiumURL;

        if (local) {
            capabilities.setCapability("app", apkPath);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("noReset", true);
            File appDir = new File("lib/iOS");
            File app = new File(appDir, "Mobility.app");
            capabilities.setCapability("app", app.getAbsolutePath());
        }

        try {
            if (databaseConnection) {
                oracleDriver = new OracleDriver(databaseIP);
            }

            iosDriver = new IOSDriver(new URL(URL_STRING), capabilities);
            mobilityDriver = new iOSMobilityDriver(iosDriver);
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Android Driver Invalid URL");
        }

        login = new LoginPage(mobilityDriver);
        selectConnection = new SelectConnectionPage(mobilityDriver);
        addConnection = new AddConnectionPage(mobilityDriver);
        profiles = new ProfilesPage(mobilityDriver);
        mainMenu = new MainMenuPage(mobilityDriver);
        viewInventory = new ViewInventoryPage(mobilityDriver);
        expressStock = new ExpressStock(mobilityDriver, oracleDriver);
        help = new HelpPage(mobilityDriver);
        validations = new ValidationsPage(mobilityDriver);
    }

    @TestRailCase(automationId = "6001")
    @Test(priority = 1, enabled = true)
    public void addConnection() {
        login.selectConnection();
        selectConnection.clickAdd();
        iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        addConnection.enterName(connectionName);
        addConnection.enterHost(connectionHost);
        addConnection.enterPort(connectionPort);

        if (ssl) {
            addConnection.toggleSSL();
        }

        addConnection.clickSave();
        selectConnection.clickConnection();
    }


    @TestRailCase(automationId = "6002")
    @Test(priority = 11, enabled = true)
    public void login() {
        login.enterUsername(username);
        login.enterPassword(password);
        if (savePassword) {
            login.clickRememberMe();
        }
        login.clickConnect();
    }

    @TestRailCase(automationId = "6003")
    @Test(priority = 13, enabled = true)
    public void profiles() {
        profiles.selectAdminProfile();
    }

    @AfterSuite(alwaysRun=true)
    public void tearDown()
    {
        try {
            oracleDriver.finalize();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        iosDriver.closeApp();
        iosDriver.quit();
    }
}
