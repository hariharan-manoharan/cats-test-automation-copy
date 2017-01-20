package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class VendorReceiveRepair extends XORoutine {

    private final String routine = "Vendor Receive Repair";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public VendorReceiveRepair(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String rmaNumber, String shipContainer, String barcode)  {
        executeRoutine(rmaNumber, shipContainer, barcode, -1);
    }

    public void execute(String rmaNumber, String shipContainer, String barcode, int qty){
        executeRoutine(rmaNumber, shipContainer, barcode, qty);
    }

    public void executeRoutine(String rmaNumber, String shipContainer, String barcode, int qty) {
        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            rmaNumber(true, rmaNumber);
            nextField();
            shipContainer(shipContainer);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if(!serialized) {
            quantity(true, qty);
            nextField();
        }

        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }
}
