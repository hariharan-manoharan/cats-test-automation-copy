/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;


public class AuditClose extends VerizonRoutine {

    private static final String routine = "Audit Close";
    private final String loopField = "Location Detail Code";
    private final String trxType = "AUDITCLOSE";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AuditClose (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute (String assetCode) {
        executeRoutine(assetCode);
    }

    public void executeRoutine (String LDC) {
        ldc(true, LDC);
        nextField();
        locationBU(true);
        nextField();
        confirmClose(true, "Y");
        notes(false, "CTA Android: " + routine);
        verifyLoopfield(true, loopField);
        verifyTransaction(true, trxType);
    }
}

