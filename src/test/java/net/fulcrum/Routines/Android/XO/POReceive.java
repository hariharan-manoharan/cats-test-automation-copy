package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class POReceive extends XORoutine {

    private final String routine = "PO Receive";
    private final String loopField = "XO Item #/MFG Part #";
    private final String shipper = "UPS";
    private final String trackingNumber = "080808";

    private boolean isInitial = true;

    public POReceive(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String poCode, String itemCode, String lineNumber, String location, String bin,
                        String assetCode) {
        executeRoutine(poCode, itemCode, lineNumber, location, bin, -1, assetCode);
    }

    public void execute(String poCode, String itemCode, String lineNumber, String location, String bin,
                        int qty) {
        executeRoutine(poCode, itemCode, lineNumber, location, bin, qty, null);
    }

    public void executeRoutine(String poCode, String itemCode, String lineNumber, String location, String bin,
                                int qty, String assetCode) {

        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            poCode(true, poCode);
            nextField();
            shipper(true, shipper);
            nextField();
            trackingNumber(true, trackingNumber);
            nextField();
        }

        xoItemMfgPart(true, itemCode);
        nextField();
        line(true, lineNumber);
        nextField();
        location(true, location);
        nextField();
        bin(true, bin);
        nextField();

        if (!serialized) {
            quantity(false, qty);
        } else {
            assetCode(true, assetCode);
            nextField();
            serial(true, "S" + assetCode);
        }

        nextField();

        if (isInitial) {
            clickOk();
            isInitial = false;
        }

        nextField(); //For cross-dock prompt
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

    public void crossDockDetails() {
        verifyRoutineDetail("Cross-Dock Item Demand Details", true);
    }
}
