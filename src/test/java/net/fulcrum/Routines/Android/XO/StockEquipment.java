package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Pages.Android.*;

public class StockEquipment extends XORoutine {

    private String routine = "Stock Equipment";
    private final String loopField = "Item/Mfg. Part #";
    private String condition = "Good";

    private boolean isInitial = true;

    public StockEquipment (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String toLocation, String toStatus, String toBin, String partCode, String barcodeType, String assetCode) {
        executeRoutine(toLocation, toStatus, toBin, partCode, barcodeType, assetCode, 0);
    }

    public void execute (String toLocation, String toStatus, String toBin, String partCode, String barcodeType, int qty) {
        executeRoutine(toLocation, toStatus, toBin, partCode, barcodeType, null, qty);
    }

    public void executeRoutine (String toLocation, String toStatus, String toBin, String partCode, String barcodeType,
                                String assetCode, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLocation(true, toLocation);
            nextField();
            toStatus(true, toStatus);
            nextField();

            if (toBin != null) {
                toBin(true, toBin);
                nextField();
            }
        }

        itemMfgPart(true, partCode);
        nextField();

        if (assetCode != null) {
            assetCode(true, assetCode);
            nextField();
            serial(true, "S" + assetCode);
        } else {
            if (barcodeType != null) {
                barcodeType(false, barcodeType);
                nextField();
            }
            quantity(true, qty);
        }

        nextField();
        condition(true, condition);
        nextField();
        notes(true, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
    }

}
