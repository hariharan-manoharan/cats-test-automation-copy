/**
 * InventoryUnpick.java
 *
 * Java Class Object to perform an Inventory Unpick for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class InventoryUnpick extends VerizonRoutine {

    private final String routine = "Inventory Unpick";
    private final String trxType = "INVENTORY_UNPICK";
    private final String loopField = "Select Unpick Item";

    private String notes = "CTA Android: " + routine;
    private boolean isInitial = true;

    public InventoryUnpick (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void execute (boolean batched, String mrCode, String lineNumber, String toLDC,  String containerCode,
                         String barcode, int qty) {
        serialized = false;
        executeRoutine(batched, mrCode, lineNumber, toLDC, containerCode, barcode, qty);
    }

    public void execute (boolean batched, String mrCode, String toLDC, String containerCode,
                         String barcode) {
        serialized = true;
        executeRoutine(batched, mrCode, null, toLDC, containerCode, barcode, 1);
    }

    private void executeRoutine (boolean batched, String mrCode, String lineNumber, String toLDC, String containerCode,
                                 String barcode, int qty) {

        int count;
        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            materialsRequest(true, mrCode);
            nextField();
        }

        unpickItem(barcode);
        nextField();

        if (!serialized) {
            count = oracleDriver.getTotalMRLines(mrCode, barcode, containerCode);

            if (count > 1) {
                lineNumber(true, lineNumber);
                nextField();
            }
        }

        toLDC(true, toLDC);
        nextField();

        if (!serialized) {
            unpickQty(qty);
            nextField();
        }

        notes(false, notes);
        nextField();
        verifyLoopfield(true, loopField);
        verifyTransaction(batched, trxType);
    }

    public void unpickItem (String item) {
        routineDetail("Select Unpick Item", item, true);
    }

    public void unpickQty (int qty) {
        routineDetail("Quantity To Unpick", "" + qty, true);
    }


    public void unpickFromContainerLabel (String container) {
        routineDetail("Unpick From Container Label", container, true);
    }

}
