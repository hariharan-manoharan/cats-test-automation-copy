package net.fulcrum.Pages.Android.Test;

import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.Pages.Android.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

import java.io.FileNotFoundException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Listeners(TestRailListener.class)
public abstract class MainTest {

    private static final String appiumVersion = "1.5.3";

    protected String localAppiumURL = "http://0.0.0.0:4723/wd/hub";
    private String androidVersion = "6.0.1";
    private final File app = new File("lib/Android/CATS-Mobility.apk");

    //Properties
    protected String customer;
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

    protected String device;
    protected String environmentFile = "environments/" +
            String.valueOf(System.getProperty("properties")) + ".properties";
    protected String deviceFile = "devices/Android/" +
            String.valueOf(System.getProperty("device")) + ".properties";
    
    //Objects
    protected AndroidDriver ad;
    protected AndroidMobilityDriver mobilityDriver;
    protected OracleDriver oracleDriver;

    //Pages
    protected ConnectionsEdit connectionsEdit;
    protected Connections connections;
    protected Login login;
    protected Menu menu;
    protected Routines routines;
    protected Folders folders;
    protected Info info;
    protected Profiles profiles;
    protected ValidationFiles validationFiles;
    protected BatchRecords batchRecords;

    //Variables
    protected String poCode;
    protected String assetCode;
    protected String assetCode2;
    protected String toLDC;
    protected String fromLDC;
    protected String toLocationBU;
    protected String fromLocationBU;
    protected String containerCode;
    protected String containerCode2;
    protected String containerCode3;

    protected InputStream inputStream;
    protected DesiredCapabilities capabilities = new  DesiredCapabilities();

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws MalformedURLException, IOException {

        String URL_STRING;

        try {
            Properties properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(environmentFile);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Cannot find the file: " + environmentFile);
            }

            customer = properties.getProperty("customer");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            savePassword = Boolean.valueOf(properties.getProperty("savePassword"));
            connectionName = properties.getProperty("name");
            databaseIP = properties.getProperty("database");
            databaseConnection = Boolean.valueOf(properties.getProperty("databaseConnection"));
            ssl = Boolean.valueOf(properties.getProperty("androidSSL"));
            testProfile = properties.getProperty("testProfile");
            multipleProfiles = Boolean.valueOf(properties.getProperty("multipleProfiles"));

            if (local) {
                connectionHost = properties.getProperty("internalHost");
                connectionPort = properties.getProperty("androidInternalPort");
            } else {

                connectionHost = properties.getProperty("externalHost");
                connectionPort = properties.getProperty("androidExternalPort");
            }

            inputStream = getClass().getClassLoader().getResourceAsStream(deviceFile);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Cannot find the file: " + deviceFile);
            }

            device = properties.getProperty("name");
            androidVersion = properties.getProperty("os");
            localAppiumURL = properties.getProperty("url");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }

        capabilities.setCapability("unicodeKeyboard", "true");
        capabilities.setCapability("platformVersion", androidVersion);
        capabilities.setCapability("newCommandTimeout", 540);
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("app", app.getAbsolutePath());

        URL_STRING = localAppiumURL;

        if (local) {
            capabilities.setCapability("noReset", true);
        }

        try {
            if (databaseConnection) {
                if (customer != null && customer.equalsIgnoreCase("XO")) {
                    oracleDriver = new OracleDriver(databaseIP, "CATSTST", "CATSTST");
                }                
                else if (customer != null && customer.equalsIgnoreCase("Airtel")) {
                    oracleDriver = new OracleDriver(databaseIP, "CATS", "CATS");
                }                
                else {
                    oracleDriver = new OracleDriver("172.16.0.148");
                }
            }

            ad = new AndroidDriver(new URL(URL_STRING), capabilities);

            mobilityDriver = new AndroidMobilityDriver(ad);
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Android Driver Invalid URL " + URL_STRING);
        }

        login = new Login(mobilityDriver);
        connectionsEdit = new ConnectionsEdit(mobilityDriver);
        connections = new Connections(mobilityDriver);
        menu = new Menu(mobilityDriver);
        routines = new Routines(mobilityDriver);
        folders = new Folders(mobilityDriver);
        info = new Info(mobilityDriver);
        profiles = new Profiles(mobilityDriver);
        validationFiles = new ValidationFiles(mobilityDriver);
        batchRecords = new BatchRecords(mobilityDriver);


        if (!local) {
            createConnection();
            selectConnectionOption();
        }
        loginMobility(true);
    }

    public void createConnection () {
        login.addConnection();
        connections.clickAddConnection();
        connectionsEdit.createConnection(connectionName, connectionHost, connectionPort, ssl);
    }

    public void selectConnectionOption () {
        connections.selectConnection(connectionName);
    }


    public void loginMobility (boolean selectProfile) {
        if (!local) {
            login.enterUsername(username);
            login.enterPassword(password);
        }

        if (savePassword) {
            login.clickRememberMe();
        }

        login.clickConnect();

        if (selectProfile) {
            selectProfile(multipleProfiles, testProfile);
        }
    }

    public void selectProfile(Boolean multipleProfiles, String profile) {
        if (multipleProfiles) {
            profiles.selectProfile(profile);
        }
    }

    public void signOut () {
        profiles.signOut();
    }

    @AfterSuite(alwaysRun=true)
    public void tearDown()
    {
        if (databaseConnection) {
            try {
                oracleDriver.finalize();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        ad.closeApp();
        ad.quit();
    }

}