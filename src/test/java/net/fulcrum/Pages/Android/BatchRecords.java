package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;


public class BatchRecords extends AndroidPageMain  {

    private final String noBatchRecords = "You have no batch records";

    private By sendTransactionsButton = By.xpath(".//android.widget.Button[@content-desc='Send Transactions']");
    private By deleteTransactionsButton = By.xpath(".//android.widget.Button[@content-desc='Delete Transactions']");


    public BatchRecords(AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    //Select an option from the Menu
    public void verifyTitle(){
        try {
            driver.verifyTextView("Batch Records");
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find Batch Records Title");
        }
    }

    public void clickSendTransactions() {
        try {
            driver.click(sendTransactionsButton);
            driver.clickYes();
            driver.verifyTextView(noBatchRecords);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to Send Transactions");
        }
    }

    public void clickDeleteTransactions() {
        try {
            driver.click(deleteTransactionsButton);
        } catch(NoSuchElementException e) {
            System.out.println("Error: Cannot find object to Delete Transactions");
        }
    }

    public void verifyBatchRecord(String routine) {
        try {
            driver.verifyTextView(routine);
        } catch (NoSuchElementException e) {
            System.out.println("Error: Cannot find Batch Record");
        }
    }
}





