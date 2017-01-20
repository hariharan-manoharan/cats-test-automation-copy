package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;



public class Transfer extends IOSRoutine {

    private final String routine = "Transfer";
    private final String notes = "CTA iOS: " + routine;


    private String partType;
    private String projectCode = "PROJECT-CTA-EXP";
    private String poCode = "PO-CTA-EXP";
    private String poLine = "1.0";


    private boolean isInitial = true;


    public Transfer (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;

    }

    private void reset() {
        mobilityDriver.clickByName("To LDC");
        isInitial = true;
    }

    public void executeRoutine (String barcode, String fromLDC, String toLDC, int qty) {

        last = true;

        partType = resolveBarcode(barcode);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            nextField();
        }

        barcode(true, barcode);

        if (isPart(partType)) {
            fromLDC(true, fromLDC);
            fromLocationBU(true);
            nextField();
            poCode(false, poCode);
            lineNumber(false, poLine);
            projectCode(false, projectCode);
            qty(true, qty);
        }

        populateNotes(false, notes);
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("*Enter Barcode", 60);
    }

}
