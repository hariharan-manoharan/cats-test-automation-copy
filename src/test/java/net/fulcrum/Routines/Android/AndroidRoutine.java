package net.fulcrum.Routines.Android;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;
import java.util.Random;

import org.openqa.selenium.*;


/**
 * Java class wrapper for all the common functions of Android routines.
 */

public class AndroidRoutine {

    public static final String platform = "Android";

    private By validationSearch = By.xpath(".//android.widget.EditText[@resource-id='android:id/input']");

    public boolean batched = false;
    public boolean serialized = true;
    public String batch = "N";
    public int customTransactionID;

    protected AndroidMobilityDriver mobilityDriver;
    protected OracleDriver oracleDriver;
    protected WebElement element;

    protected Routines routines;

    protected String notes;
    protected String detail;
    public String routine;
    private String partType;

    //Result of last transaction
    protected static boolean result = true;

    //Result of last routine detail entered
    protected static boolean last = true;

    //Result of last routine detail entered
    public static boolean isInitial = true;

    public boolean getResult() {
        return result;
    }

    public void resetResult() {
        result = true;
    }

    /**
     * Mobility Functions
     */

    public void routineDetailLookup (String fieldName, String value, boolean required) {
        if (last) {
            verifyRoutineDetail(fieldName, required);
            clickValidationFile();
            enterValidationFileSearch(value);
            clickLookupValue(value);
        }
    }

    public void searchClickLookup(String value) {
        clickValidationFile();
        enterValidationFileSearch(value);
        clickLookupValue(value);
    }

    public boolean validateValue(String value) {
        return mobilityDriver.verifyViewView(value);
    }

    public boolean verifyValidationFileContents(String value) {
        return mobilityDriver.verifyTextView(value);
    }

    public boolean verifyValidationFileContents(String value, boolean scroll) {
        mobilityDriver.swipeRight();
        mobilityDriver.swipeRight();
        return mobilityDriver.verifyTextView(value);
    }


    public void  enterValidationFileSearch(String value) {
        if (last) {
            mobilityDriver.sendKeys(validationSearch, value, 120);
        } else {
            System.out.println("Result failed: " + validationSearch + " Value: " + value);
        }
    }

    public void clickPreviousRoutineDetail(String value) {
        if (last) {
            mobilityDriver.clickContentDescViewView(value);
        } else {
            System.out.println("Result failed: " + validationSearch + " Value: " + value);
        }
    }

    public void clickLookupValue(String value) {
        mobilityDriver.clickTextView(value);
    }

    public void verifyMessage(String text) {
        mobilityDriver.verifyMessageHeader();
        if (last) {
            last = mobilityDriver.verifyTextView(text);
        }
    }

    public void verifyValidationOverride(String text, String answer) {
        mobilityDriver.verifyValidationOverrideHeader();
        if (last) {
            last = mobilityDriver.verifyTextView(text);
            if (answer.equalsIgnoreCase("Yes")) {
                clickYes();
            } else {
                clickNo();
            }
        }
    }

    public void verifyValidationOverride(String answer) {
        mobilityDriver.verifyValidationOverrideHeader();
        if (last) {
            if (answer.equalsIgnoreCase("Yes")) {
                mobilityDriver.validationYes();
            } else {
                mobilityDriver.validationNo();
            }
        }
    }

    public void verifyRoutineValue(String text) {
        last = mobilityDriver.verifyViewView(text);
    }

    public void verifyCurrentRoutineValue(String text) {
        last = mobilityDriver.verifyEditText(text);
    }

    public void verifyRoutineTitle(String title) {
        last = mobilityDriver.verifyTextView(title);
    }

    public void verifyRoutineHeader() {
        last = mobilityDriver.verifyTextView("Routines");
    }

    public void routineDetail(String fieldName, String value) {
        if (last) {
            last = mobilityDriver.sendTextRoutineDetail(fieldName, value);
        } else {
            System.out.println("Result failed: " + fieldName + " Value: " + value);
        }
    }

    public void routineDetail(String fieldName, String value, boolean required) {
        if (required) {
            detail = "Enter " + fieldName + " (*) :";
        } else {
            detail = "Enter " + fieldName + " :";
        }
        
        if (last) {
            last = mobilityDriver.sendTextRoutineDetail(detail, value);
        } else {
            System.out.println("Result failed: " + fieldName + " Value: " + value);
        }
    }

    public boolean verifyRoutineDetail(String fieldName, boolean required) {
        if (required) {
            return mobilityDriver.waitForRoutineDetail("Enter " + fieldName + " (*) :");
        }
        return mobilityDriver.waitForRoutineDetail("Enter " + fieldName + " :");
    }

    public boolean verifyRoutineDetail(String fieldName, int duration, boolean required) {
        if (required) {
            return mobilityDriver.waitForRoutineDetail("Enter " + fieldName + " (*) :", duration);
        }
        return mobilityDriver.waitForRoutineDetail("Enter " + fieldName + " :", duration);
    }
    
    
    

    public void clickOk() {
        if (last) {
            last = mobilityDriver.clickOk();
        } else {
            System.out.println("Last Appium Command failed. Cannot click OK");
        }
    }

    public void confirmMessage(String message) {
        if (last) {
            last = mobilityDriver.verifyTextView(message);
        } else {
            System.out.println("Last Appium Command failed. Cannot find message: " + message);
        }
    }

    public void clickYes() {
        if (last) {
            mobilityDriver.clickYes();
        } else {
            System.out.println("Last Appium Command failed. Cannot click Yes");
        }
    }

    public void clickNo() {
        if (last) {
            mobilityDriver.clickNo();
        } else {
            System.out.println("Last Appium Command failed. Cannot click No");
        }
    }

    public void clickBackRoutines() {
        mobilityDriver.clickBackRoutines();
    }

    public void nextField() {
        if (last) {
            mobilityDriver.clickNextRoutineField();
        } else {
            System.out.println("Last Appium Command failed. Cannot click Next");
        }
    }

    public void previousField() {
        if (last) {
            mobilityDriver.clickPreviousRoutineField();
        } else {
            System.out.println("Last Appium Command failed. Cannot click Previous");
        }
    }

    public boolean isPart(String partType) {
        if (partType.toUpperCase().equals("PART")) {
            return true;
        }
        return false;
    }

    public boolean isAsset(String partType) {
        if (partType.toUpperCase().equals("ASSET")) {
            return true;
        }
        return false;
    }

    public boolean isContainer(String partType) {
        if (partType.toUpperCase().equals("CONTAINER")) {
            return true;
        }
        return false;
    }

    public void clickValidationFile() {
        mobilityDriver.clickValidationFile();
    }

    public void selectRoutine(Menu menu, Routines routines, String routine) {
        menu.selectAdmin();
        routines.selectRoutine(routine);
    }

    public void swipeDown() {
        mobilityDriver.swipeDown();
    }

    public void swipeDown(int time) {
        mobilityDriver.swipeDown(time);
    }

    public void swipeRight() {
        mobilityDriver.swipeRight();
    }

    public void setInitial() {
        isInitial = true;
    }

    /**
     * Routine Details
     */

    public void toLDC(boolean required, String toLDC) {
        routineDetail("To LDC", toLDC, required);
    }

    public void poCode(boolean required, String poCode) {
        routineDetail("PO #", poCode, required);
    }

    public void shipper(boolean required, String shipper) {
        routineDetail("Shipper", shipper, required);
    }

    public void trackingNumber(boolean required, String trackingNumber) {
        routineDetail("Tracking #", trackingNumber, required);
    }

    public void trackingNumber(boolean required) {
        String tracking = "TRACK" + mobilityDriver.getRandomInt(10000);
        routineDetail("Tracking #", tracking, required);
    }

    public void containerLabel(boolean required, String containerCode) {
        routineDetail("Container Label", containerCode, required);
    }

    public void containerSize(boolean required, String containerSize) {
        routineDetail("Container Size", containerSize, required);
    }

    public void partCode(boolean required, String partCode) {
        routineDetail("Part Code", partCode, required);
    }
    public void assetCode(boolean required, String assetCode) {
        routineDetail("Asset Code", assetCode, required);
    }

    public void containerCode(boolean required, String containerCode) {
        routineDetail("Container Code", containerCode, required);
    }

    public void containerItem(boolean required, String item) {
        routineDetail("Container Item", item, required);
    }

    public void poLine(boolean required, String poLine) {
        routineDetail("PO Line", poLine, required);
    }

    public void lineNumber(boolean required, String lineNumber) {
        routineDetail("Line Number", lineNumber, required);
    }

    public void ldc(boolean required, String ldc){
        routineDetail("LDC", ldc, required);
    }

    public void newAssetTag(boolean required, String newAssetTag){
        routineDetail("New Asset Tag", newAssetTag, required);
    }

    public void barcode(boolean required, String barcode){
        routineDetail("Barcode", barcode, required);
    }

    public void quantity(boolean required, int qty) {
        routineDetail("Quantity", String.valueOf(qty), required);
    }

    public void notes(boolean required, String notes) {
        routineDetail("Notes", notes, required);
    }

    public void fromContainerCode(boolean required, String containerCode) {
        routineDetail("From Container Code", containerCode, required);
    }

    public void fromLDC(boolean required, String fromLDC) {
         routineDetail("From LDC", fromLDC, required);
    }

    public void vendorRA(boolean required, String vendorRA) {
        routineDetail("Vendor RA or ECN #", vendorRA, required);
    }

    public void vendorRMA(boolean required, String vendorRMA) {
        routineDetail("Vendor RMA #", vendorRMA, required);
    }

    public void vendor(boolean required, String vendor) {
        routineDetail("Vendor", vendor, required);
    }

    public void catsRA(boolean required, String catsRA) {
        routineDetail("CATS RA #", catsRA, required);
    }

    public void location(boolean required, String location) {
        routineDetail("Location", location, required);
    }

    public void toLocationBU(boolean required, String toLocationBU) {
        routineDetail("To Location BU", toLocationBU, required);
    }

    public void toLocationBU(boolean required) {
        if (required) {
            mobilityDriver.waitForRoutineDetail("Enter To Location BU (*) :");
        } else {
            mobilityDriver.waitForRoutineDetail("Enter To Location BU :");
        }
    }

    public void fromLocationBU(boolean required) {
        if (required) {
            mobilityDriver.waitForRoutineDetail("Enter From Location BU (*) :");
        } else {
            mobilityDriver.waitForRoutineDetail("Enter From Location BU (*) :");
        }
    }

    public void locationBU(boolean required) {
        if (required) {
            mobilityDriver.waitForRoutineDetail("Enter Location BU (*) :");
        } else {
            mobilityDriver.waitForRoutineDetail("Enter Location BU :");
        }
    }

    public void locationBU(boolean required, String bu) {
        routineDetail("Location BU", bu, required);
    }

    public void failReasonCode(boolean required, String failReasonCode) {
        routineDetail("Fail Reason Code", failReasonCode, required);
    }

    public void fromLocationBU(boolean required,  String fromLocationBU) {
        routineDetail("From Location BU", fromLocationBU, required);
    }

    public void fixAssetCode(boolean required, String fixAssetCode) {
        routineDetail("Fix Asset Code", fixAssetCode, required);
    }

    public void failAssetCode(boolean required, String failAssetCode) {
        routineDetail("Fail Asset Code", failAssetCode, required);
    }

    public void RALine(boolean required, String RALine) {
        routineDetail("RA Line", RALine, required);
    }

    public String resolveBarcode(String barcode) {
        partType = oracleDriver.resolveBarcode(barcode);
        return partType.trim();
    }

    public void type(boolean required, String type) {
        routineDetail("Type", type, required);
    }

    public void confirmOpen(boolean required, String type) {
        routineDetail("Confirm Open", type, required);
    }

    public void confirmCancel(boolean required, String type) {
        routineDetail("Confirm Cancel", type, required);
    }

    public void confirmClose(boolean required, String type) {
        routineDetail("Confirm Close", type, required);
    }

    public void auditLocation(boolean required, String type) {
        routineDetail("Audit Location", type, required);
    }

    public void scanBarcode(boolean required, String type) {
        routineDetail("Scan Barcode", type, required);
    }

    public void enterVerifyContainer(boolean required, String type) {
        routineDetail("Verify Container(Y/N)", type, required);
    }

    public void missingBarcode(boolean required, String type) {
        routineDetail("Verify Container(Y/N)", type, required);
    }

    public void serialNumber(String serial) {
        routineDetail("Serial Number", serial, false);
    }

    public void serialNumber(boolean required, String serial) {
        routineDetail("Serial Number", serial, required);
    }

    public void revision(String revision) {
        routineDetail("Revision", revision, false);
    }

    public void rmaNumber(boolean required, String rma) {
        routineDetail("RMA #", rma, required);
    }

    public void reason(boolean required, String reason) {
        routineDetail("Reason", reason, required);
    }

    /**
     * Misc Actions
     */

    public void testRequiredField(String field) {
        verifyRoutineDetail(field, true);
        nextField();
        verifyMessage("DEVICE: " + field + " is required.");
    }

    public void verifyLoopfield(Boolean required, String field) {
        result = verifyRoutineDetail(field, 60, required);
    }

    public void setSerialized(int qty) {
        if(qty > 0) {
            serialized = false;
        } else {
            serialized =  true;
        }
    }
    

}
