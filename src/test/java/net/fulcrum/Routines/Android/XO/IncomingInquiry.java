package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class IncomingInquiry extends XORoutine {

    private final String routine = "Incoming Inquiry";
    private final String validOffersMsg = "THIS ITEM HAS VALID VENDOR REPAIR OFFERS.  TAP MAGNIFYING GLASS TO VIEW " +
            "VALID VENDOR REPAIR OFFERS FOR THIS ITEM. SEND ITEM TO VENDOR FOR REPLACEMENT";
    private final String noOffersMsg = "NO WARRANTY OR VENDOR REPAIR OFFERS FOUND.";

    public IncomingInquiry(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String location, String partCode, String incoming, String transferLines) {
        executeRoutine(location, partCode, incoming, transferLines);
    }

    public void executeRoutine (String location, String partCode, String incoming, String transferLines) {
        last = true;
        location(true, location);
        nextField();

        if (partCode != null) {
            partCode(false, partCode);
        }

        nextField();
        clickPreviousRoutineDetail("Incoming");
        searchClickLookup(incoming);
        searchClickLookup(transferLines);
    }

}
