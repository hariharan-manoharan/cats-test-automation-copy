package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;

public class ExpressStock extends VerizonRoutine {

    private final String routine = "Express Stock";
    private final String trxType = "EXPRESSSTOCK";

    private boolean isInitial = true;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine("Express Stock");
    }

    public ExpressStock (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (boolean batched, String toLDC, String partCode, String assetCode, String projectCode,
                         String poCode, String poLine) {
        serialized = true;
        executeRoutine(batched, toLDC, partCode, assetCode, 1, projectCode, poCode, poLine);
    }

    public void execute (boolean batched, String toLDC, String partCode, int qty, String projectCode, String poCode,
                         String poLine){
        serialized = false;
        executeRoutine(batched, toLDC, partCode, null, qty, projectCode, poCode, poLine);
    }

    public void executeRoutine (boolean batched, String toLDC, String partCode, String assetCode, int qty, String projectCode,
        String poCode, String poLine) {

        last = true;

        String locationBU = oracleDriver.getLocationBU(toLDC);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true, "");
            toLocationBULookup(true, locationBU);
        }

        partCodeLookup(true, partCode);

        if (serialized) {
            assetCode (true, assetCode);
            nextField();
        } else {
            quantity(true, qty);
        }

        nextField();
        projectCode(false, projectCode);
        nextField();
        poCode(false, poCode);
        nextField();
        poLine(false, poLine);
        nextField();
        notes(true, "CTA: " + routine);
        nextField();
        verifyLoopfield();

        if (!batched) {
            verifyTransaction(false, toLDC, partCode, assetCode, qty);
        }
    }

    public void verifyLoopfield() {
        result = verifyRoutineDetail("Part Code", 60, true);
    }

    public void verifyTransaction(boolean batched, String toLDC, String partCode, String assetCode, int qty) {

        if (batched) {
            oracleDriver.verifyCustomTransaction(true, trxType);
        } else {
            customTransactionID = oracleDriver.verifyExpressStock(batched, partCode, toLDC, assetCode, qty);

            if (customTransactionID == 0) {
                result = false;
            }

            if (serialized && assetCode != null && result) {
                String assetLocation = oracleDriver.getAssetLocation(assetCode);
                if (!assetLocation.equals(toLDC)) {
                    result = false;
                }
            }
        }
    }
}
