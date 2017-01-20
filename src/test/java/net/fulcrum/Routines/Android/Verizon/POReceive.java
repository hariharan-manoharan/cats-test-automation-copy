/**
 * POReceive.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class POReceive extends VerizonRoutine {

    private final String routine = "PO Receive";
    private final String trxType = "PORECEIVE";
    private final String loopField = "Part Code";

    private String shipper = "UPS";
    private String trackingNumber = "1242414";
    private String containerSize = "Box";
    private String toLDC;
    private String partCode;
    private boolean checkAsset = false;
    private String assetCode;
    private String containerCode;
    private String notes = "CTA: " + routine;
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

    public POReceive (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (String poCode, String lineNumber) {
        executeRoutine(poCode, lineNumber, null, 1);
    }

    public void execute (String poCode, String lineNumber, String assetCode) {
        executeRoutine(poCode, lineNumber, assetCode, 1);
    }
    public void execute (String poCode, String lineNumber, int qty) {
        executeRoutine(poCode, lineNumber, null, qty);
    }
        
    private void executeRoutine (String poCode, String lineNumber, String assetCode, int qty) {


        toLDC = oracleDriver.getPOLDC(poCode, lineNumber);
        partCode = oracleDriver.getPOPart(poCode, lineNumber);
        checkAsset = oracleDriver.isAsset(partCode);
        containerCode = "VZC-" + poCode;

        int totalLines = oracleDriver.getTotalLines(poCode, partCode);

        if (!isInitial && !last) {
            reset();
        }

        //Check if initial PO Receive transaction
        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true);
            nextField();
            poCode(true, poCode);
            nextField();
            shipper(false, shipper);
            nextField();
            trackingNumber(false, trackingNumber);
            nextField();
            containerLabel(false, containerCode);
            nextField();
            containerSize(false, containerSize);
            nextField();
        }

        partCode(false, partCode);
        nextField();

        //Check if part code exists on more than one PO Line
        if (totalLines > 1) {
            clickOk();
            lineNumber(false, lineNumber);
        }

        nextField();

        //Check if receiving a serialized item
        if (checkAsset) {
            assetCode(true, assetCode);
            nextField();
            nextField();
        //Check if specified qty is more than 1 or default qty
        } else if (qty != 1) {
            quantity(false, qty);
            nextField();
        } else {
            nextField();
        }

        notes(false, routine);
        nextField();
        verifyLoopfield(false, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }

    public void routineComplete () {
        last = verifyRoutineDetail("Part Code", 60, false);
    }
}
