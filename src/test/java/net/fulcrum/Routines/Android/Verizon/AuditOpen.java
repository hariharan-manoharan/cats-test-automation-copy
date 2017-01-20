/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;

public class AuditOpen extends VerizonRoutine {

    private final String routine = "Audit Open";
    private final String trxType = "AUDITOPEN";
    private final String loopField = "Location Detail Code";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AuditOpen (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (String assetCode) {
        executeRoutine(assetCode);
    }

    public void executeRoutine (String LDC) {
        ldc(true, LDC);
        nextField();
        locationBU(true);
        nextField();
        type(true, "AUDIT");
        nextField();
        confirmOpen(true, "Y");
        nextField();
        verifyLoopfield(true, loopField);
        verifyTransaction(true, trxType);
    }
}
