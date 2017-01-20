/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;


public class Audit extends VerizonRoutine {
    private static final String routine = "Audit";
    private final String loopField = "Location Detail Code";
    private final String trxType = "AUDIT";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }


    public Audit(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute (String LDC, String barcode) {
        executeRoutine(LDC, barcode);
    }

    public void executeRoutine (String LDC, String barcode) {
        auditLocation(true, LDC);
        nextField();
        locationBU(true);
        nextField();
        scanBarcode(true, barcode);
        nextField();
        enterVerifyContainer(true, "N");
        notes(false, "CTA Android: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
        verifyTransaction(true, trxType);
    }
}
