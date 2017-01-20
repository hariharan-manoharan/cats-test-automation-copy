package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class VendorShipRepair extends XORoutine {

    private final String routine = "Vendor Ship Repair";
    private final String loopField = "RMA #";
    private final String shipMethod = "UPS 2DAY";

    private boolean isInitial = true;

    public VendorShipRepair(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void execute(String rmaNumber, String returnMFGPart) {
        executeRoutine(rmaNumber, returnMFGPart, -1);
    }



    public void execute(String rmaNumber, String returnMFGPart, int qty) {
        executeRoutine(rmaNumber, returnMFGPart, qty);
    }

    public void executeRoutine(String rmaNumber, String returnMFGPart, int qty) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        rmaNumber(true, rmaNumber);
        nextField();

        if (isInitial) {
            verifyRoutineDetail("Line #", false);
            clickValidationFile();
            clickLookupValue(returnMFGPart);
        }

        if (!serialized) {
            quantity(true, qty);
            nextField();
        }

        shipMethod(true, shipMethod);
        nextField();
        trackingNumber();
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void trackingNumber() {
        routineDetail("Tracking Number", "1KS2" + mobilityDriver.getRandomInt(10000), true);
    }

    public void receivedXOItem(String xoItem) {
        routineDetail("Received XO Item #", xoItem, true);
    }

    public void receivedMFGPart(String part) {
        routineDetail("Received MFG Part #", part, true);
    }

    public void receivedAssetCode(String asset) {
        routineDetail("Received Asset Code", asset, true);
    }

    public void returningMFGPart(String part) {
        routineDetail("Returning MFG Part #", part, true);
    }

    public void returningXOItem(String xoItem) {
        routineDetail("Returning XO Item #", xoItem, true);
    }

    public void returningAssetCode(String asset) {
        routineDetail("Returning Asset Code", asset, true);
    }

    public void returningSerial(String serial) {
        routineDetail("Returning Serial", serial, true);
    }
}
