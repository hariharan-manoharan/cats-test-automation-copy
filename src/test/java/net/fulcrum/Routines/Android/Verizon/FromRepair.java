/**
 * ToRepair.java
 *
 * Java Class Object to perform a ToRepair for CATS NextGen Mobility for Android
 *
 */
package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class FromRepair extends VerizonRoutine {

    private final String routine = "From Repair";
    private final String trxType = "FROM_REPAIR";
    private final String loopField = "RA Line";

    private boolean checkAsset = false;
    private boolean isInitial = true;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public FromRepair(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute(boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA, String barcode, int qty) {
        executeRoutine(batched, fromLDC, toLDC,  vendorRA, catsRA, barcode, qty);
    }

    public void execute(boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA, String barcode) {
        executeRoutine(batched, fromLDC, toLDC, vendorRA, catsRA, barcode, 0);
    }

    public void executeRoutine(boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA,
                               String barcode, int qty){

        if (qty == 0) {
            checkAsset = true;
        } else {
            checkAsset = false;
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true);
            nextField();
            vendorRA(false, vendorRA);
            nextField();
            catsRA(true, catsRA);
            nextField();
        }

        raLine();
        clickValidationFile();
        clickLookupValue(barcode);
        nextField();

        if (checkAsset) {
            fixAssetCode(true, barcode);
            nextField();
            fixPartCode();
            nextField();
            serialNumber();
            nextField();
            revision("REV1");
        } else {
            fixQuantityToReceive(qty);
            nextField();
            fromLDC(true, fromLDC);
            nextField();
            fromLocationBU(true);
        }

        nextField();
        vendorRepairFindings("Fixed");
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }

    public void serialNumber() {
        verifyRoutineDetail("Serial Number", false);
    }

    private void raLine(String line) {
        routineDetail("RA Line", line, true);
    }

    private void raLine() {
        verifyRoutineDetail("RA Line", true);
    }

    private void fixPartCode() {
        verifyRoutineDetail("Fix Part Code", true);
    }

    private void fixQuantityToReceive(int qty) {
        routineDetail("Fix Quantity to Receive", String.valueOf(qty), true);
    }

    private void vendorRepairFindings(String findings) {
        routineDetail("Vendor Repair Findings", findings, false);
    }

    public void failAssetCode() {
        verifyRoutineDetail("Fail Asset Code", true);
    }

}
