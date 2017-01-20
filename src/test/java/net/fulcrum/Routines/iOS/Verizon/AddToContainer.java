package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Routines.iOS.IOSRoutine;


public class AddToContainer extends IOSRoutine {

    private final String routine = "Add To Container";
    private final String notes = "CTA iOS: " + routine;

    private String containerSize = "Box";
    private String partType;
    private String projectCode = "PROJECT-CTA-EXP";
    private String poCode = "PO-CTA-EXP";
    private String lineNumber = "1.0";

    public boolean isInitial = true;

    public AddToContainer(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        isInitial = true;
    }

    public void execute(String toLDC, String fromLDC, String container, String barcode) {
        executeRoutine(toLDC, fromLDC, container, barcode, 1);
    }

    public void execute(String toLDC, String fromLDC, String container, String barcode, int qty) {
        executeRoutine(toLDC, fromLDC, container, barcode, qty);
    }

    public void executeRoutine(String toLDC, String fromLDC, String container, String barcode, int qty) {

        last = true;

        partType = resolveBarcode(barcode);

        if (!isInitial && !last) {
            reset();
        }

        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            containerLabel(true, container);
            containerSize(containerSize);
            toLDC(true, toLDC);
            toLocationBU(true);
            nextField();
        }

        barcode(true, barcode);

        if (isPart(partType)) {
            fromLDC(true, fromLDC);
            fromLocationBU(true);
            nextField();
            poCode(false, poCode);
            //nextField();
            lineNumber(false, lineNumber);
            //nextField();
            projectCode(false, projectCode);
            //nextField();
            qty(true, qty);
        }

        populateNotes(notes);
        routineComplete();

    }

    public void routineComplete() {
        result = verifyRoutineDetail("*Enter Barcode", 60);
    }
}
