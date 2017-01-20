package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;


public class InventoryPicking extends IOSRoutine {

    private final String routine = "Inventory Picking";

    private String containerSize = "Box";
    private String notes = "CTA iOS: " + routine;
    private boolean isInitial = true;
    private String itemType;

    public InventoryPicking (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        mobilityDriver.clickByName("To LDC");
        isInitial = true;

    }

    public void setInitial () {
        isInitial = true;
    }

    public void execute (String mrCode, String lineNumber, String toLDC, String fromLDC, String containerCode,
                         String barcode, int qty) {
        executeRoutine(mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, qty);
    }

    private void executeRoutine (String mrCode, String lineNumber, String toLDC, String fromLDC, String containerCode,
                                 String barcode, int qty) {

       // last = mobilityDriver.waitForRoutineDetail(routine);

        last = true;
        itemType = resolveBarcode(barcode);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            System.out.println("Initial Transaction for " + routine);
            isInitial = false;
            materialsRequest(true, mrCode);
            generatePickList(true, "N");
            toLDC(true, toLDC);
            toLocationBU(true);
            nextField();
            pickToContainerLabel(containerCode);
            pickToContainerSize(containerSize);
        }

        barcode(true, barcode);

        if (itemType.equalsIgnoreCase("PART")) {
            lineNumber(true, lineNumber);
            quantityToPickLabel(qty);
            fromLDC(true, fromLDC);
            fromLocationBU(true);
            nextField();
        }

        generatePackingSlip("N");
        populateNotes(false, notes);
        routineComplete();
    }

    public void routineComplete () {
        System.out.println("Verifying routine complete");
        result = verifyRoutineDetail("*Enter Barcode", 60);
    }


    private void quantityToPickLabel (int qty) {
        routineDetail("*Enter Quantity To Pick", "" + qty);
    }


    private void pickToContainerLabel (String containerLabel) {
        routineDetail("Enter Pick To Container Label", containerLabel);
    }

    private void pickToContainerSize (String containerSize) {
        routineDetail("Enter Pick To Container Size", containerSize);
    }


    private void generatePackingSlip(String answer) {
        routineDetail("Enter Generate Packing Slip?", answer);
    }



}

