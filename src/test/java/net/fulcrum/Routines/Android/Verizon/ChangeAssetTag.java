/**
 * ChangeLabel.java
 *
 * Java Class Object to perform a ChangeLabel for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;


public class ChangeAssetTag extends VerizonRoutine {

    private String routine = "Change Asset Tag";
    private final String trxType = "CHANGE_LABEL";
    private final String loopField = "Asset Code";

    public ChangeAssetTag (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void executeRoutine (boolean batched, String assetCode, String newAssetTag)
    {
        assetCode(true, assetCode);
        nextField();
        newAssetTag(true, newAssetTag);
        nextField();
        notes(false, "CTA Android: " + routine);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }

    }
}

