package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;



public class CaseBreak extends IOSRoutine {

    private final String routine = "Case Break";

    private String projectCode = "PROJECT-CTA-EXP";
    private String poCode = "PO-CTA-EXP";
    private String lineNumber = "1.0";

    private String partType;

    private boolean isInitial = true;

    private void reset() {
        mobilityDriver.clickByName("Container Code");
        isInitial = true;
    }

    public CaseBreak(iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void execute(String toLDC, String containerCode, String containerItem) {
        executeRoutine(toLDC, containerCode, containerItem, 1);
    }

    public void execute(String toLDC, String containerCode, String containerItem, int qty) {
        executeRoutine(toLDC, containerCode, containerItem, qty);
    }

    public void executeRoutine(String toLDC, String containerCode, String containerItem, int qty) {

        last = true;

        partType = resolveBarcode(containerItem);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            containerCode(true, containerCode);
        }

        containerItem(true, containerItem);

        if (isPart(partType)) {
            poCode(false, poCode);
            lineNumber(false, lineNumber);
            projectCode(false, projectCode);
            qty(true, qty);
        }

        populateNotes(routine);
        routineComplete();
    }

    public void routineComplete() {
        result = verifyRoutineDetail("*Enter Container Item", 60);
    }
}



