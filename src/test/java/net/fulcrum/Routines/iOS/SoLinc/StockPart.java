package net.fulcrum.Routines.iOS.SoLinc;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class StockPart extends IOSRoutine {

    private final String routine = "Stock Part";

    private boolean isInitial = true;

    public StockPart(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute (String toLDC, String partCode, int qty) {
        executeRoutine(toLDC, partCode, qty);
    }

    public void executeRoutine (String toLDC, String partCode, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
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
