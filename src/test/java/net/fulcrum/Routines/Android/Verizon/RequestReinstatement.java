/**
 * DestroyContainer.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class RequestReinstatement extends VerizonRoutine {

    private final String routine = "Request Reinstatement";
    private final String trxType = "REQUEST_REINSTATE";
    private final String loopField = "Barcode";

    private String vendor = "1001 42ND STREET LLC.0000111929";
    private String shipper = "UPS";
    private String trackingNumber = "245252";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public RequestReinstatement (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void executeRoutine (String toLDC, String retirementBatch, String item)
    {
        last = true;
        reinstateToLDC(toLDC);
        nextField();
        reinstateToLocationBU();
        nextField();
        reinstatementType("REINSTATE ITEM");
        nextField();
        removeFromContainer();
        nextField();
        reinstatementReasonCode("S");
        nextField();
        notes(false, "CTA: " + routine);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }

    public void routineComplete () {
        result = verifyRoutineDetail("Barcode", 60, true);
    }

    private void reinstateToLDC (String ldc){
        routineDetail("Reinstate To LDC", ldc, true);
    }

    private void reinstateToLocationBU (){
        verifyRoutineDetail("Reinstate To Location BU", true);
    }

    private void reinstatementType (String type){
        routineDetail("Reinstatement Type", type, true);
    }

    private void removeFromContainer (){
        verifyRoutineDetail("Remove From Container Code", false);
    }

    private void reinstatementReasonCode (String reason){
        routineDetail("Reinstatement Reason Code", reason, true);
    }

}
