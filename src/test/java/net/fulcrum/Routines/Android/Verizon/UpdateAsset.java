/**
 * UpdateAsset.java
 *
 * Java Class Object to perform a Update Asset for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class UpdateAsset extends VerizonRoutine {

    private String routine = "Update Asset";
    private final String trxType = "UPDATE_ASSET";
    private final String loopField = "Barcode";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }


    public UpdateAsset (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (boolean batched, String assetCode, String toLDC) {
        executeRoutine(batched, assetCode, toLDC);
    }

    public void executeRoutine (boolean batched, String assetCode, String toLDC) {
        barcode(true, assetCode);
        nextField();
        ldc(true, toLDC);
        nextField();
        locationBU(false);
        nextField();
        nextField();
        nextField();
        nextField();
        notes(false, routine);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }
}
