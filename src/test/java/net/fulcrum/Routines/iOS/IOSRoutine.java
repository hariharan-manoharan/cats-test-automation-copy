package net.fulcrum.Routines.iOS;

import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


/**
 * Java class wrapper for all the common functions of iOS routines.
 */

public class IOSRoutine {

    protected iOSMobilityDriver mobilityDriver;
    protected OracleDriver oracleDriver;
    protected WebElement element;

    private String partType;
    protected String detail;

    //Result of last transaction
    protected static boolean result = true;

    //Result of last routine detail entered
    protected static boolean last = true;


    public boolean getResult() {
        return result;
    }

    public void routineDetail(String fieldName, String value, boolean required) {
        if (required) {
            detail = "*Enter " + fieldName;
        } else {
            detail = "Enter " + fieldName;
        }

        if (last) {
            last = mobilityDriver.sendTextRoutineDetail(detail, value);
        } else {
            System.out.println("Result failed: " + fieldName + " Value: " + value);
        }
    }

    public void routineDetail (String fieldName, String value) {
        if (last) {
            last = mobilityDriver.sendTextRoutineDetail(fieldName, value);
        } else {
            System.out.println("Result failed: " + fieldName + " Value: " + value);
        }
    }


    public boolean verifyRoutineDetail (String fieldName) {
        return mobilityDriver.waitForRoutineDetail(fieldName);
    }

    public boolean verifyRoutineDetail (String fieldName, int duration) {
        return mobilityDriver.waitForRoutineDetail(fieldName, duration);
    }

    public void nextField () {
        if (last) {
            last = mobilityDriver.clickNextRoutineField();
        } else {
            System.out.println("Last Appium Command failed. Cannot click Next");
        }
    }

    public void toLDC(Boolean required, String toLDC) {
        if (required) {
            routineDetail("*Enter To LDC", toLDC);
        } else {
            routineDetail("Enter To LDC", toLDC);
        }
    }

    public void poCode(String poCode) {
        routineDetail("Enter PO #", poCode);
    }

    public void poCode(Boolean required, String poCode) {
        if (required) {
            routineDetail("*Enter PO #", poCode);
        } else {
            routineDetail("Enter PO #", poCode);
        }
    }

    public void shipper(String shipper) {
        routineDetail("Enter Shipper", shipper);
    }

    public void shipper(Boolean required, String shipper) {
        if (required) {
            routineDetail("*Enter Shipper", shipper);
        } else {
            routineDetail("Enter Shipper #", shipper);
        }
    }

    public void trackingNumber(Boolean required, String trackingNumber) {
        if (required) {
            routineDetail("*Enter Tracking #", trackingNumber);
        } else {
            routineDetail("Enter Tracking #", trackingNumber);
        }
    }

    public void containerLabel(String containerCode) {
        routineDetail("Enter Container Label", containerCode);
    }

    public void containerLabel(Boolean required, String containerCode) {
        if (required) {
            routineDetail("*Enter Container Label", containerCode);
        } else {
            routineDetail("Enter Container Label", containerCode);
        }
    }

    public void containerSize (String containerSize) {
        routineDetail("Enter Container Size", containerSize);
    }

    public void quarantineLabel (String quarantineLabel) {
        mobilityDriver.sendTextRoutineDetail("Enter Quarantine Label: ", quarantineLabel);
    }

    public void quarantineReason (String quarantineReason) {
        mobilityDriver.sendTextRoutineDetail("Enter Quarantine ReasonL ", quarantineReason);
    }

    public void partCode (Boolean required, String partCode) {
        if (required) {
            routineDetail("*Enter Part Code", partCode);
        } else {
            routineDetail("Enter Part Code", partCode);
        }
    }

    public void partCode (String partCode) {
        routineDetail("Enter Part Code", partCode);
    }

    public void assetCode (String assetCode) {
        routineDetail("Enter Asset Code", assetCode);
    }

    public void assetCode (Boolean required, String assetCode) {
        if (required) {
            routineDetail("*Enter Asset Code", assetCode);
        } else {
            routineDetail("Enter Asset Code", assetCode);
        }
    }

    public void containerCode(Boolean required, String containerCode) {
        if (required) {
            routineDetail("*Enter Container Code", containerCode);
        } else {
            routineDetail("Enter Container Code", containerCode);
        }
    }

    public void containerItem(Boolean required, String item) {
        if (required) {
            routineDetail("*Enter Container Item", item);
        } else {
            routineDetail("Enter Container Item", item);
        }
    }

    public void poLine (String poLine) {
        routineDetail("Enter PO Line", poLine);
    }

    public void lineNumber(Boolean required, String lineNumber) {
        if (required) {
            routineDetail("*Enter Line Number", lineNumber);
        } else {
            routineDetail("Enter Line Number", lineNumber);
        }
    }

    public void lineNumber (String lineNumber) {
        routineDetail("Enter Line Number", lineNumber);
    }

    public void oldAssetCode (String oldAssetCode) {
        routineDetail("*Enter Old Asset Code ", oldAssetCode);
    }


    public void newAssetCode (String newAssetCode) {
        routineDetail("*Enter New Asset Code ", newAssetCode);
    }

    public void ldc(Boolean required, String ldc){
        if (required) {
            routineDetail("*Enter LDC", ldc);
        } else {
            routineDetail("Enter LDC", ldc);
        }
    }

    public void newAssetTag(Boolean required, String newAssetTag){
        if (required) {
            routineDetail("*Enter New Asset Tag", newAssetTag);
        } else {
            routineDetail("Enter New Asset Tag", newAssetTag);
        }
    }

    public void ellipsis(String item) {
        routineDetail("Enter Tap ellipsis to view contents", item);
    }


    public void barcode(Boolean required, String barcode){
        if (required) {
            routineDetail("*Enter Barcode", barcode);
        } else {
            routineDetail("Enter Barcode", barcode);
        }
    }


    public void retirementBatch(Boolean required, String retirementBatch){
        if (required) {
            routineDetail("*Enter Retirement Batch", retirementBatch);
        } else {
            routineDetail("Enter Retirement Batch", retirementBatch);
        }
    }

    public void qty (boolean required, int qty) {
        if (required) {
            routineDetail("*Enter Quantity", String.valueOf(qty));
        } else {
            routineDetail("Enter Quantity", String.valueOf(qty));
        }
    }

    public void quantity (boolean required, int qty) {
        if (required) {
            routineDetail("*Enter Quantity", String.valueOf(qty));
        } else {
            routineDetail("Enter Quantity", String.valueOf(qty));
        }
    }

    public void containerItem(String containerItem) {
        mobilityDriver.sendTextRoutineDetail("Enter Container Item: ", containerItem);
    }

    public void notes (String notes) {
        mobilityDriver.sendTextRoutineDetail("Enter Notes: ", notes);
    }

    public void fromContainerCode (String containerCode) {
        mobilityDriver.sendTextRoutineDetail("Enter From Container Code", containerCode);
    }

    public void fromLDC (String fromLDC) {
        routineDetail("Enter From LDC", fromLDC);
    }

    public void fromLDC (Boolean required, String fromLDC) {
        if (required) {
            routineDetail("*Enter From LDC", fromLDC);
        } else {
            routineDetail("Enter From LDC", fromLDC);
        }
    }

    public void projectCode (String projectCode) {
        mobilityDriver.sendTextRoutineDetail("Enter Project", projectCode);
    }

    public void projectCode (Boolean required, String projectCode) {
        if (required) {
            routineDetail("*Enter Project", projectCode);
        } else {
            routineDetail("Enter Project", projectCode);
        }
    }

    public void results (String results) {
        mobilityDriver.sendTextRoutineDetail("Enter Results: ", results);
    }

    public void vendorRA (String vendorRA) {
        routineDetail("Enter Vendor RA or ECN #", vendorRA);
    }

    public void vendor (boolean required, String vendor) {
        if (required) {
            routineDetail("*Enter Vendor", vendor);
        } else {
            routineDetail("Enter Vendor", vendor);
        }
    }

    public void vendor (String vendor) {
        routineDetail("Enter Vendor", vendor);
    }

    public void catsRA(String catsRA) {
        routineDetail("Enter CATS RA #", catsRA);
    }

    public void toLocationBU (String toLocationBU) {
        routineDetail("*Enter To Location BU", toLocationBU);
    }

    public void toLocationBU (boolean required) {
        if (required) {
            mobilityDriver.waitForRoutineDetail("*Enter To Location BU");
        } else {
            mobilityDriver.waitForRoutineDetail("Enter To Location BU");
        }
    }

    public void fromLocationBU () {
        mobilityDriver.waitForRoutineDetail("*Enter From Location BU");
    }

    public void locationBU (Boolean required) {
        if (required) {
            mobilityDriver.waitForRoutineDetail("*Enter Location BU");
        } else {
            mobilityDriver.waitForRoutineDetail("Enter Location BU");
        }
    }

    public void toLocationBU (Boolean required, String toLocationBU) {
        if (required) {
            routineDetail("*Enter To Location BU", toLocationBU);
        } else {
            routineDetail("Enter To Location BU", toLocationBU);
        }
    }

    public void materialsRequest (Boolean required, String mrCode) {
        if (required) {
            routineDetail("*Enter Materials Request #", mrCode);
        } else {
            routineDetail("Enter Materials Request #", mrCode);
        }
    }

    public void generatePickList (Boolean required, String answer) {
        if (required) {
            routineDetail("*Enter Generate Pick List?", answer);
        } else {
            routineDetail("Enter Generate Pick List", answer);
        }
    }


    public void carrier (String carrier) {
        routineDetail("Enter Carrier/Consignee", carrier);
    }


    public void carrier (boolean required, String carrier) {
        if (required) {
            routineDetail("*Enter Carrier/Consignee", carrier);
        } else {
            routineDetail("Enter Carrier/Consignee", carrier);
        }
    }

    public void failLDC (String failLDC) {
        routineDetail("Enter FAIL LDC", failLDC);
    }

    public void failTechnician (String failTechnician) {
        routineDetail("Enter Fail Technician", failTechnician);
    }

    public void generatePackingSlip (Boolean required, String yesNo) {
        if (required) {
            routineDetail("*Enter Generate Package Slip? Y/N", yesNo);
        } else {
            routineDetail("Enter Generate Package Slip? Y/N", yesNo);
        }
    }

    public void failReasonCode (String failReasonCode) {
        mobilityDriver.sendTextRoutineDetail("Enter Fail Reason Code : ", failReasonCode);
    }

    public void fromLocationBU (String fromLocationBU) {
        mobilityDriver.sendTextRoutineDetail("Enter From Location BU : ", fromLocationBU);
    }

    public void fromLocationBU (Boolean required) {
        if (required) {
            verifyRoutineDetail("*Enter From Location BU");
        } else {
            verifyRoutineDetail("Enter From Location BU");
        }
    }

    public void fixAssetCode (String fixAssetCode) {
        mobilityDriver.sendTextRoutineDetail("Enter Fix Asset Code : ", fixAssetCode);
    }

    public void failAssetCode (String failAssetCode) {
        mobilityDriver.sendTextRoutineDetail("Enter Fail Asset Code : ", failAssetCode);
    }

    public void RALine (String RALine) {
        mobilityDriver.sendTextRoutineDetail("Enter RA Line : ", RALine);
    }

    public void confirmBU (String result) {
        mobilityDriver.sendTextRoutineDetail("Confirm BU ", result);
    }

    public void toLocationDetailCode(boolean required, String ldc) {
        routineDetail("To Location Detail Code", ldc, required);
    }

    public void toLocation(boolean required, String location) {
        routineDetail("To Location", location, required);
    }

    public void fromLocation(boolean required, String location) {
        routineDetail("From Location", location, required);
    }

    public void fromLocationDetailCode(boolean required, String ldc) {
        routineDetail("From Location Detail Code", ldc, required);
    }

    public void assetCode(boolean required, String asset) {
        routineDetail("Asset Code", asset, required);
    }

    public void partCode(boolean required, String partCode) {
        routineDetail("Part Code", partCode, required);
    }

    public void serial(boolean required, String serial) {
        routineDetail("Serial #", serial, required);
    }

    public void serialNumber(boolean required, String serial) {
        routineDetail("Serial Number", serial, required);
    }

    public void raCode(boolean required, String raCode) {
        routineDetail("RA Code", raCode, required);
    }

    public void failAssetCode(boolean required, String assetCode) {
        routineDetail("Fail Asset Code", assetCode, required);
    }

    public void failLocationDetailCode(boolean required, String assetCode) {
        routineDetail("Fail Location Detail Code", assetCode, required);
    }

    public void replacementAssetCode (String assetCode) {
        routineDetail("Replacement Asset Code", assetCode, true);
    }

    public void rmaCode (String rmaCode) {
        routineDetail("RMA Code", rmaCode, true);
    }


    public void locationDetail(boolean required, String ldc) {
        routineDetail("Location Detail", ldc, required);
    }


    public String resolveBarcode (String barcode) {
        partType = oracleDriver.resolveBarcode(barcode);
        return partType.trim();
    }

    public boolean isPart (String partType) {
        if (partType.toUpperCase().equals("PART")) {
            return true;
        }
        return false;
    }

    public boolean isAsset (String partType) {
        if (partType.toUpperCase().equals("ASSET")) {
            return true;
        }
        return false;
    }

    public boolean isContainer (String partType) {
        if (partType.toUpperCase().equals("CONTAINER")) {
            return true;
        }
        return false;
    }


    public void populateNotes (String notes) {
        routineDetail("Enter Notes", notes);
    }

    public void populateNotes (Boolean required, String notes) {
        if (required) {
            routineDetail("*Enter Notes", notes);
        } else {
            routineDetail("Enter Notes", notes);
        }
    }


    public void clickYes () {
        if (last) {
            mobilityDriver.clickYes();
        } else {
            System.out.println("Last Appium Command failed. Cannot click Yes");
        }
    }

    public void clickOk () {
        if (last) {
            mobilityDriver.clickOk();
        } else {
            System.out.println("Last Appium Command failed. Cannot click OK");
        }
    }

}
