package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class Triage extends XORoutine {

    private final String routine = "Triage";
    private final String validOffersMsg = "THIS ITEM HAS VALID VENDOR REPAIR OFFERS.  TAP MAGNIFYING GLASS TO VIEW " +
            "VALID VENDOR REPAIR OFFERS FOR THIS ITEM. SEND ITEM TO VENDOR FOR REPLACEMENT";
    private final String noOffersMsg = "NO WARRANTY OR VENDOR REPAIR OFFERS FOUND.";

    public Triage(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public void execute (String barcode, String type) {
        executeRoutine(barcode, type);
    }


    public void executeRoutine (String barcode, String type) {
        last = true;
        barcode(true, barcode);
        nextField();

        if (type.equalsIgnoreCase("No")) {
            verifyMessage(noOffersMsg);
        } else if (type.equalsIgnoreCase("Valid")) {
            verifyMessage(validOffersMsg);
        } else {
            mobilityDriver.verifyMessageHeader();
        }
        clickOk();
    }
}
