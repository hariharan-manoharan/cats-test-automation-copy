/**
 * CancelReceive.java
 *
 * Java Class Object to perform a Cancel Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class CancelReceive extends VerizonRoutine {

    private final String routine = "PO Cancel";
    private final String trxType = "CANCELRECEIVE";
    private final String loopField = "Line Number";

    private String reason = "DUP-PO";
    private String notes = "CTA: " + routine;
    private static boolean isInitial = true;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public CancelReceive (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute (boolean batched, String poCode, String lineNumber, String assetCode) {
        executeRoutine(batched, poCode, lineNumber, assetCode, 1);
    }

    public void execute (boolean batched, String poCode, String lineNumber, int qty) {
        executeRoutine(batched, poCode, lineNumber, null, qty);
    }

    private void executeRoutine (boolean batched, String poCode, String lineNumber, String assetCode, int qty) {

        boolean serialized = false;
        if (assetCode != null) {
            serialized = true;
        }

        if (!isInitial && !last) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            poCode(true, poCode);
            nextField();
        }

        lineNumber(true, lineNumber);
        nextField();

        if (serialized) {
            assetCode (true, assetCode);
            nextField();
        } else {
            quantity(true, qty);
            nextField();
            fromContainerCode(false, "VZC-" + poCode);
            nextField();
            fromLDC(true, "CNS236520");
            nextField();
            fromLocationBU(true);
            nextField();
        }

        cancelReason(reason);
        nextField();
        notes(true, notes);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }

    }

    private void cancelReason(String reason) {
        routineDetail("Cancel Reason", reason, true);
    }


}
