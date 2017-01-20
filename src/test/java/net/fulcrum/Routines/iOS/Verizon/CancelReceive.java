package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;


public class CancelReceive extends IOSRoutine {

    private final String routine = "PO Cancel";

    private String reason = "DUP-PO";
    private String notes = "CTA: " + routine;
    private static boolean isInitial = true;


    public CancelReceive (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        mobilityDriver.clickByName("PO #");
        last = true;
        isInitial = true;
    }

    public void execute (String poCode, String lineNumber, String assetCode) {
        executeRoutine(poCode, lineNumber, assetCode, 1);
    }

    public void execute (String poCode, String lineNumber, int qty) {
        executeRoutine(poCode, lineNumber, null, qty);
    }

    private void executeRoutine (String poCode, String lineNumber, String assetCode, int qty) {

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
        }

        lineNumber(true, lineNumber);

        if (serialized) {
            assetCode (true, assetCode);
        } else {
            quantity(true, qty);
            fromContainerCode("VZC-" + poCode);
            fromLDC(true, "CNS236520");
            fromLocationBU(true);
            nextField();
        }

        cancelReason (reason);
        populateNotes(true, notes);
        routineComplete();

    }

    public void routineComplete () {
        last = verifyRoutineDetail("*Enter Line Number", 120);
    }

    private void cancelReason(String reason) {
        routineDetail("*Enter Cancel Reason", reason);
    }


}
