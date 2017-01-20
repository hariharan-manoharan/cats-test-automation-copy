package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class ReceiveRepair extends XORoutine {

    private final String routine = "Receive Repair";
    private final String loopField = "Barcode";

    private boolean isInitial = true;

    public ReceiveRepair(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String rmaNumber, String shipContainer, String barcode, String newAsset, String serial,
                        String status, String bin){
        executeRoutine(rmaNumber, shipContainer, barcode, -1, newAsset, serial, status, bin);
    }

    public void execute(String rmaNumber, String shipContainer, String barcode, int qty, String status, String bin){
        executeRoutine(rmaNumber, shipContainer, barcode, qty, null, null, status, bin);
    }

    public void executeRoutine(String rmaNumber, String shipContainer, String barcode, int qty, String newAsset,
                               String serial, String status, String bin) {
        last = true;

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

        if(qty > 0) {
            quantity(true, qty);
        } else {
            newAssetCode(true, newAsset);
            nextField();
            serialNumber(true, serial);
        }

        nextField();
        toStatus(true, status);
        nextField();
        bin(true, bin);
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

}
