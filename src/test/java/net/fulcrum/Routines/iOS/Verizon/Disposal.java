package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class Disposal extends IOSRoutine {

    private final String routine = "Disposal";
    private final String notes = "CTA iOS: " + routine;

    private String vendor = "1001 42ND STREET LLC.0000111929";
    private String shipper = "UPS";
    private String trackingNumber = "245252";
    private String itemType;


    private boolean isInitial = true;

    private void reset() {
        mobilityDriver.clickByName("Container Label");
    }

    public void setInitial () {
        isInitial = true;
    }

    public Disposal (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine (String toLDC, String fromLDC, String retirementBatch, String barcode)
    {


        itemType = resolveBarcode(barcode);

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            toLocationBU(true);
            nextField();
            vendor(true, vendor);
            shipper(true, shipper);
            trackingNumber(true, trackingNumber);
            retirementBatch(false, retirementBatch);
            clickOk();
            if (barcode.equalsIgnoreCase("Y")) {
                disposeEntireRetirementBatch("Y");
            } else {
                disposeEntireRetirementBatch("N");
            }
        }

        barcode(false, barcode);

        if (itemType.equalsIgnoreCase("PART")) {

            fromLDC(true, fromLDC);
            fromLocationBU(true);
            nextField();
            //poCode;
            nextField();
            //lineNumber();
            nextField();
            //project
            nextField();
            qty(1);
        }
        disposalReasonCode("S");
        populateNotes(notes);
        routineComplete();

    }

    private void routineComplete () {
        result = verifyRoutineDetail("Enter Barcode", 60);
    }

    private void disposeEntireRetirementBatch(String yesNo){
        routineDetail("Enter Dispose Entire Retirement Batch", yesNo);
    }

    private void disposalReasonCode(String code){
        routineDetail("*Enter Disposal Reason Code", code);
    }

    private void qty(int qty){
        routineDetail("*Enter Quantity", "" + qty);
    }

    private void parentContainer() {
        verifyRoutineDetail("Parents of this Container");
    }

    private void containerContents() {
        verifyRoutineDetail("Container Contents");
    }

}

