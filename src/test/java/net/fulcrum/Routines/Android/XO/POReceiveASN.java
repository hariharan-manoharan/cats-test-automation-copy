package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class POReceiveASN extends XORoutine {

    private final String routine = "PO Receive ASN";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public POReceiveASN(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String poCode,String location, String bin, String barcode) {
        executeRoutine(poCode,location, bin, barcode, -1);
    }

    public void execute(String poCode,String location, String bin, String barcode, int qty) {
        executeRoutine(poCode,location, bin, barcode, qty);
    }

    public void executeRoutine(String poCode, String location, String bin, String barcode, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            poCode(true, poCode);
            nextField();
            location(true, location);
            nextField();
            bin(true, bin);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (qty > 0) {
            quantity(true, qty);
            nextField();
        }

        verifyLoopfield(true, loopField);
    }
}
