package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.iOS.IOSRoutine;

public class ExpressStock extends IOSRoutine {

    private String routine = "Express Stock";
    private String projectCode = "PROJECT-CTA-EXP";
    private String poCode = "PO-CTA-EXP";
    private String poLine = "1.0";


    private boolean isInitial = true;

    public ExpressStock (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    private void reset() {
       // mobilityDriver.clickByName("To LDC");
        isInitial = true;
    }

    public void execute (String toLDC, String partCode, String assetCode, int qty) {
        executeRoutine(toLDC, partCode, assetCode, qty);
    }

    public void execute (String toLDC, String partCode, int qty) {
        executeRoutine(toLDC, partCode, null, qty);
    }

    public void executeRoutine (String toLDC, String partCode, String assetCode, int qty) {

        last = true;

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            toLDC(true, toLDC);
            toLocationBU(true);
            nextField();
            nextField();
        }

        partCode(true, partCode);

        if (assetCode != null) {
            assetCode (true, assetCode);
            nextField();
        } else {
            qty(true, qty);
        }

        projectCode(projectCode);
        poCode(false, poCode);
        poLine(poLine);
        populateNotes(true, "CTA iOS: " + routine);
    }

}
