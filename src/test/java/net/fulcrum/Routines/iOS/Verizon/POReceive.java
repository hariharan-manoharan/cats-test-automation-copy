package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;
import org.openqa.selenium.By;


public class POReceive extends IOSRoutine {

    private final String routine = "PO Receive";
    private final String notes = "CTA: " + routine;

    private String shipper = "UPS";
    private String trackingNumber = "1242414";
    private String containerSize = "Box";
    private String toLDC;
    private String partCode;
    private boolean checkAsset = false;
    private String assetCode;
    private String containerCode;
    private boolean isInitial = true;

    public POReceive (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
        mobilityDriver.resetRoutine(routine);
        isInitial = true;
    }

    public void execute (String poCode, String lineNumber) {
        executeRoutine(poCode, lineNumber, null, 1);
    }

    public void execute (String poCode, String lineNumber, String assetCode) {
        executeRoutine(poCode, lineNumber, assetCode, 1);
    }
    public void execute (String poCode, String lineNumber, int qty) {
        executeRoutine(poCode, lineNumber, null, qty);
    }

    private void executeRoutine (String poCode, String lineNumber, String assetCode, int qty) {


        toLDC = oracleDriver.getPOLDC(poCode, lineNumber);
        partCode = oracleDriver.getPOPart(poCode, lineNumber);
        checkAsset = oracleDriver.isAsset(partCode);
        containerCode = "VZC-" + poCode;

        int totalLines = oracleDriver.getTotalLines(poCode, partCode);

        if (!isInitial && !last && !result) {
            reset();
        }

        //Check if initial PO Receive transaction
        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            toLocationBU(true);
            nextField();
            poCode(true, poCode);
            shipper(shipper);
            trackingNumber(false, trackingNumber);
            containerLabel(false, containerCode);
            containerSize(containerSize);
        }

        partCode(false, partCode);

        //Check if part code exists on more than one PO Line
        if (totalLines > 1) {
            clickOk();
            lineNumber(false, lineNumber);
        } else {
            nextField();
        }

        //Check if receiving a serialized item
        if (checkAsset) {
            assetCode(true, assetCode);
            nextField();

            //Check if specified qty is more than 1 or default qty
        } else {
            quantity(false, qty);
        }

        populateNotes(false, routine);
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("Enter Part Code", 60);
    }

}
