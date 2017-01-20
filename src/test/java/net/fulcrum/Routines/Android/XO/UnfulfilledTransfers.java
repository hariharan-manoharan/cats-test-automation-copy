package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class UnfulfilledTransfers extends XORoutine {

    private final String routine = "Unfulfilled Transfers";
    private final String validOffersMsg = "THIS ITEM HAS VALID VENDOR REPAIR OFFERS.  TAP MAGNIFYING GLASS TO VIEW " +
            "VALID VENDOR REPAIR OFFERS FOR THIS ITEM. SEND ITEM TO VENDOR FOR REPLACEMENT";
    private final String noOffersMsg = "NO WARRANTY OR VENDOR REPAIR OFFERS FOUND.";

    public UnfulfilledTransfers(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute (String fromLocation, String toLocation, String partCode, String transfers, String lines) {
        executeRoutine(fromLocation, toLocation, partCode, transfers, lines);
    }

    public void executeRoutine (String fromLocation, String toLocation, String partCode, String transfers, String lines) {
        last = true;
        fromLocation(true, fromLocation);
        nextField();
        toLocation(false, toLocation);
        nextField();

        if (partCode != null) {
            partCode(false, partCode);
        }

        nextField();
        clickPreviousRoutineDetail("Unfulfilled Transfers");
        searchClickLookup(transfers);
        searchClickLookup(lines);
    }

}
