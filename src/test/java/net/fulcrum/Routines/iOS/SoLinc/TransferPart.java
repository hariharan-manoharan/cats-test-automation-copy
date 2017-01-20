package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class TransferPart extends IOSRoutine {

    private final String routine = "Transfer Part";

    private boolean isInitial = true;

    public TransferPart(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String fromLDC, String toLDC, String partCode, int qty ) {
        executeRoutine(fromLDC, toLDC, partCode, qty);
    }

    public void executeRoutine (String fromLDC, String toLDC, String partCode, int qty ) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            fromLocationDetailCode(true, fromLDC);
            nextField();
            toLocationDetailCode(true, toLDC);
            nextField();
        }

        partCode(true, partCode);
        nextField();
        qty(true, qty);
        nextField();
        populateNotes(true, "CTA iOS: " + routine);
        nextField();
    }

}
