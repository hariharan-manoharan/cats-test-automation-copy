/**
 * Disposal.java
 *
 * Java Class Object to perform a Disposal transaction for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import java.util.List;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.fulcrum.Routines.Android.AndroidRoutine;

public class Disposal extends VerizonRoutine {

    private String routine = "Disposal";
    private final String trxType = "DISPOSAL";
    private final String loopField = "Barcode";

    private String vendor = "1001 42ND STREET LLC.0000111929";
    private String shipper = "UPS";
    private String trackingNumber = "245252";
    private String itemType;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public Disposal (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine (String toLDC, String fromLDC, String retirementBatch, String barcode) {
        itemType = resolveBarcode(barcode);
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true);
            nextField();
            vendor(true, vendor);
            nextField();
             shipper(true, shipper);
            nextField();
            trackingNumber(true, trackingNumber);
            nextField();
            retirementBatch(false, retirementBatch);
            nextField();
            clickOk();
            if (barcode.equalsIgnoreCase("Y")) {
                disposeEntireRetirementBatch("Y");
            } else {
                disposeEntireRetirementBatch("N");
            }
            nextField();
        }

        barcode(false, barcode);
        nextField();

        if (itemType.equalsIgnoreCase("PART")) {

            fromLDC(true, fromLDC);
            nextField();
            fromLocationBU(true);
            nextField();
            //poCode;
            nextField();
            //lineNumber();
            nextField();
            //swipeDown();
            //project
            nextField();
            quantity(true, 1);
            nextField();
        }
        disposalReasonCode("S");
        nextField();
        notes(false, "CTA " + routine);
        nextField();
        verifyLoopfield(false, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }

    }

    private void disposeEntireRetirementBatch(String yesNo){
        routineDetail("Dispose Entire Retirement Batch", yesNo, false);
    }

    private void disposalReasonCode(String code){
        routineDetail("Disposal Reason Code", code, true);
    }

    private void parentContainer() {
        verifyRoutineDetail("Parents of this Container", false);
    }

    private void containerContents() {
        verifyRoutineDetail("Container Contents", false);
    }

}
