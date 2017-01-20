/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;


public class AuditCancel extends VerizonRoutine {
    private static final String routine = "Audit Cancel";
    private final String loopField = "Location Detail Code";
    private final String trxType = "AUDITCANCEL";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AuditCancel (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute (String assetCode) {
        executeRoutine(assetCode);
    }

    public void executeRoutine (String LDC) {
        ldc(true, LDC);
        nextField();
        confirmCancel(true, "Y");
        nextField();
        notes(false, "CTA Android: " + routine);
        nextField();
        verifyLoopfield(true, loopField);
        verifyTransaction(true, trxType);
    }
}
