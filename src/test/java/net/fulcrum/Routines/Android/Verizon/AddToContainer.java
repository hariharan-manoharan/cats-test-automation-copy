/**
 * AddToContainer.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;

public class AddToContainer extends VerizonRoutine {

    private final String routine = "Add To Container";
    private final String trxType = "ADD_CONTAINER";

    private String containerSize = "Box";
    private String partType;


    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AddToContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (boolean batched, String toLDC, String fromLDC, String container, String barcode) {
        executeRoutine(batched, toLDC, fromLDC, container, barcode, 1, null, null, null);
    }

    public void execute (boolean batched, String toLDC, String fromLDC, String container, String barcode, int qty) {
        executeRoutine(batched, toLDC, fromLDC, container, barcode, qty, null, null, null);
    }

    public void execute (boolean batched, String toLDC, String fromLDC, String container, String barcode, int qty,
                         String poCode, String poLine, String project) {
        executeRoutine(batched, toLDC, fromLDC, container, barcode, qty, poCode, poLine, project);
    }

    public void executeRoutine (boolean batched, String toLDC, String fromLDC, String container, String barcode, int qty,
                                String poCode, String poLine, String project) {
        last = true;
        partType = resolveBarcode(barcode);


        //Check if initial transaction
        if (isInitial) {
            isInitial = false;
            containerLabel(true, container);
            nextField();
            containerSize(false, containerSize);
            nextField();
            toLDCLookup(true, toLDC);
            toLocationBU(true);
            nextField();
        }

        barcode(true, barcode);
        nextField();

        if (isPart(partType)) {
            fromLDC(true, fromLDC);
            nextField();
            fromLocationBU(true);
            nextField();

            if (poCode != null) {
                poCode(false, poCode);
            }

            nextField();

            if (poLine != null) {
                lineNumber(false, poLine);
            }

            nextField();

            if (project != null) {
                projectCode(false, project);
            }

            nextField();
            quantity(true, qty);
            nextField();
        }

        notes(false, routine);
        nextField();
        verifyLoopfield();

        if (!batched) {
            verifyTransaction();
        }
    }

    public void verifyLoopfield() {
        result = verifyRoutineDetail("Barcode", 60, true);
    }

    public void verifyTransaction() {
        oracleDriver.verifyCustomTransaction(true, trxType);
    }
}
