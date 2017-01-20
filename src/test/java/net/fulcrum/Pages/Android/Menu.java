package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class Menu extends AndroidPageMain  {

    protected By menu = By.xpath("//android.widget.ImageView[1]");

    protected By routines = By.xpath("//android.view.ViewGroup[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[7]");
    protected By validationFiles = By.xpath("//android.view.View[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.TextView[2]");
    private By batchMode = By.xpath(".//android.widget.TextView[@text='Toggle Batch Mode']");
    private By batchRecords = By.xpath(".//android.widget.TextView[@text='Batch Records']");
    private By info = By.xpath(".//android.widget.TextView[@text='Info']");
    private By signOut = By.xpath(".//android.widget.TextView[@text='Sign out']");


    public Menu (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    //Select an option from the Menu
    public void selectMenu(){
    	try {
    		driver.click(menu);
    	} catch(NoSuchElementException e) {
    		System.out.println("Error: Cannot find object to select Menu option");
    	}
    }

    public void selectMenuOption (String selection) {
        if (selection == "Sign Out" || selection == "Profiles" || selection == "Locate Inventory" ||
                selection == "Validation Data" || selection == "Switch To Batch" || selection == "Routines" ||
                selection == "Switch To Network") {
            try {
                //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.findElement(By.name(selection)).click();
            } catch (NoSuchElementException ex) {
                System.out.println("Error: Cannot find object to select Menu option");
            }

        } else {
            System.out.println("Invalid Selection for Selecting Menu Option");
        }
    }

    public void selectAdmin() {
        selectMenu();
        driver.clickTextView("ADMIN");
    }

    public void signOut() {
        selectMenu();
        driver.click(signOut);
    }

    public void selectRoutines() {
        selectMenu();
        try {
            driver.click(routines);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to select Routines option");
        }
    }

    public void selectValidationFiles() {
        selectMenu();
        try {
            driver.click(validationFiles);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to select Validation Files option");
        }
    }

    public void selectBatchRecords() {
        selectMenu();
        try {
            driver.click(batchRecords);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to view Batch Records");
        }
    }

    public void toggleBatchMode() {
        selectMenu();
        try {
            driver.click(batchMode);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to select Toggle Batch Mode option");
        }
    }
}





