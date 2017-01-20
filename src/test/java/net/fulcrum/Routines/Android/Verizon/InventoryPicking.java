/**
 * InventoryPicking.java
 *
 * Java Class Object to perform an Inventory Picking for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class InventoryPicking extends VerizonRoutine {

    private final String routine = "Inventory Picking";
    private final String trxType = "INVENTORY_PICK";
    private final String loopField = "Barcode";

    private String containerSize = "Box";
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

    public InventoryPicking (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void setInitial () {
        isInitial = true;
    }

    public void execute (boolean batched, String mrCode, String lineNumber, String toLDC, String fromLDC, String containerCode,
                         String barcode, int qty) {
        serialized = false;
        executeRoutine(batched, mrCode, lineNumber, toLDC, fromLDC, containerCode, barcode, null, qty);
    }

    public void execute (boolean batched, String mrCode, String toLDC,
                         String containerCode, String barcode, String assetCode) {
        serialized = true;
        executeRoutine(batched, mrCode, null, toLDC, null, containerCode, barcode, assetCode, 0);
    }

    private void executeRoutine (boolean batched, String mrCode, String lineNumber, String toLDC, String fromLDC,
                                 String containerCode, String barcode, String assetCode, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            materialsRequest(true, mrCode);
            nextField();
            generatePickList(true, "N");
            nextField();
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true);
            nextField();
            pickToContainerLabel(containerCode);
            nextField();
            pickToContainerSize(containerSize);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (!serialized) {
            lineNumber(true, lineNumber);
            nextField();
            quantityToPickLabel(qty);
        } else {
            assetCode(true, assetCode);
        }

        nextField();
        generatePackingSlip("N");
        nextField();

        if (!serialized) {
            fromLDC(true, fromLDC);
            nextField();
        }

        notes(false, notes);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(batched, trxType);
        }
    }

    public void quantityToPickLabel (int qty) {
        routineDetail("Quantity To Pick", String.valueOf(qty), true);
    }

    public void pickToContainerLabel (String containerLabel) {
        routineDetail("Pick To Container Label", containerLabel, false);
    }

    public void pickToContainerSize (String containerSize) {
        routineDetail("Pick To Container Size", containerSize, false);
    }

    public void generatePackingSlip(String answer) {
        routineDetail("Generate Packing Slip?", answer, false);
    }

}
