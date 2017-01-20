package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class InventoryUnpick extends IOSRoutine {

    private final String routine = "Inventory Unpick";


    private String notes = "CTA iOS: " + routine;
    private boolean isInitial = true;
    private String itemType;

    public InventoryUnpick (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        //mobilityDriver.clickByName("To LDC");
        isInitial = true;
    }

    public void execute (String mrCode, String lineNumber, String toLDC, String fromLDC, String containerCode,
                         String barcode, int qty) {
        executeRoutine(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, qty);
    }

    private void executeRoutine (String mrCode, String lineNumber, String toLDC, String fromLDC, String containerCode,
                                 String barcode, int qty) {



        last = mobilityDriver.waitForRoutineDetail(routine);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            materialsRequest(true, mrCode);
        }

        unpickItem(barcode);

        itemType = resolveBarcode(barcode);

        if (itemType.equalsIgnoreCase("PART")) {
            unpickFromContainerLabel(containerCode);
            fromLDC(true, fromLDC);
            fromLocationBU(true);
            nextField();
            //poCode(false, "PO-" + mrCode + "-1");
            //poLine();
            //TO LDC
            nextField();
            nextField();
            nextField();
        }

        toLDC(true, toLDC);
        toLocationBU(true);
        nextField();
        if (itemType.equalsIgnoreCase("PART")) {
            unpickQty(qty);
        }

        populateNotes(false, notes);
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("*Enter Select Unpick Item", 60);
    }

    public void populateNotes (Boolean required, String notes) {
        if (required) {
            routineDetail("*Enter Notes", notes);
        } else {
            routineDetail("Enter Notes", notes);
        }
    }

    private void unpickItem (String item) {
        routineDetail("*Enter Select Unpick Item", item);
    }

    private void unpickQty (int qty) {
        routineDetail("*Enter Quantity To Unpick", "" + qty);
    }

    private void unpickFromContainerLabel (String container) {
        routineDetail("Enter Unpick From Container Label", container);
    }

}
