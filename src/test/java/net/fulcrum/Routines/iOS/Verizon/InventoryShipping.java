package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;


public class InventoryShipping extends IOSRoutine {

    private final String routine = "Inventory Shipping";

    private String shipper = "UPS";
    private String trackingNumber = "1242414";
    private String containerSize = "Box";
    private String toLDC;
    private String partCode;
    private boolean checkAsset = false;
    private String assetCode;
    private String containerCode;
    private String notes = "CTA iOS: " + routine;
    private boolean isInitial = true;

    public InventoryShipping (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
       // mobilityDriver.clickByName("To LDC");
        isInitial = true;
    }

    public void execute (String mrCode, String lineNumber, String toLDC, String containerCode) {
        executeRoutine(mrCode, lineNumber, toLDC, containerCode);
    }

    private void executeRoutine (String mrCode, String lineNumber, String toLDC, String containerCode) {


        last = mobilityDriver.waitForRoutineDetail(routine);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            materialsRequest(true, mrCode);
            shipToLDC(toLDC);
            shipToLocationBU();
            nextField();
            carrier(false, shipper);
            trackingNumber(false, trackingNumber);
        }

        barcode(true, containerCode);
        generatePackingSlip("N");
        populateNotes(false, notes);
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("*Enter Barcode", 60);
    }


    private void shipToLDC (String toLDC) {
        routineDetail("*Enter Ship To LDC", toLDC);
    }


    private void shipToLocationBU () {
        verifyRoutineDetail("*Enter Ship To Location BU");
    }


    private void generatePackingSlip(String answer) {
        routineDetail("*Enter Generate Packing Slip?", answer);
    }

}
