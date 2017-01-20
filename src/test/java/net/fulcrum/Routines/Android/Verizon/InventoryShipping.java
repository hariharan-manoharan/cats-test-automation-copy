/**
 * InventoryShippingTest.java
 *
 * Java Class Object to performs an Inventory Shipping transaction for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class InventoryShipping extends VerizonRoutine {

    private final String routine = "Inventory Shipping";
    private final String trxType = "INVENTORY_SHIP";
    private final String loopField = "Barcode";

    private String shipper = "UPS";
    private String trackingNumber = "1242414";
    private String notes = "CTA: " + routine;
    private boolean isInitial = true;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public InventoryShipping (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (boolean batched, String mrCode, String toLDC, String containerCode) {
        executeRoutine(batched, mrCode, toLDC, containerCode);
    }

    private void executeRoutine (boolean batched, String mrCode, String toLDC, String containerCode) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            materialsRequest(true, mrCode);
            nextField();
            shipToLDC(toLDC);
            nextField();
            shipToLocationBU();
            nextField();
            carrier(false, shipper);
            nextField();
            trackingNumber(false, trackingNumber);
            nextField();
        }

        barcode(true, containerCode);
        nextField();
        generatePackingSlip("N");
        nextField();
        notes(false, notes);
        nextField();
        verifyLoopfield(true, loopField);
        verifyTransaction(batched, trxType);
    }

    public void shipToLDC (String toLDC) {
        routineDetail("Enter Ship To LDC (*) :", toLDC);
    }

    public void shipToLocationBU () {
        verifyRoutineDetail("Ship To Location BU", true);
    }

    public void shipToLocationBU(String BU) {
        routineDetail("Ship To Location BU", BU, true);
    }

    public void generatePackingSlip(String answer) {
        routineDetail("Enter Generate Packing Slip? (*) :", answer);
    }

}
