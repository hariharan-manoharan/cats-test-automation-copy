package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class AdvanceShippingNotice extends XORoutine {

    private final String routine = "Advance Shipping Notice";
    private final String loopField = "PO #";
    private final String shipper = "UPS";

    private boolean isInitial = true;

    public AdvanceShippingNotice(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String poCode, String lineNumber, String assetCode) {
        executeRoutine(poCode, lineNumber, assetCode, -1);
    }

    public void execute(String poCode, String lineNumber, int qty) {
        executeRoutine(poCode, lineNumber, null, qty);
    }

    public void executeRoutine(String poCode, String lineNumber, String item, int qty) {

        last = true;
        setSerialized(qty);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            shipper(true, shipper);
            nextField();
            trackingNumber(true);
            nextField();
            packSlip();
            nextField();
        }

        poCode(true, poCode);
        nextField();
        line(true, lineNumber);
        nextField();

        if (!serialized) {
            quantity(false, qty);
        } else {
            assetCode(false, item);
            nextField();
            serial(true, "S" + item);
            nextField();
            clei(false);
        }

        nextField();
        verifyLoopfield(true, loopField);
    }

    public void packSlip() {
        String slip = "SLIP" + mobilityDriver.getRandomInt(10000);
        routineDetail("Packslip #", slip, false);
    }
}
