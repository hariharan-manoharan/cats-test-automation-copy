package net.fulcrum.Routines.Android.XO;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.*;

public class InventoryLookup extends XORoutine {

    private final String routine = "Inventory Lookup";
    private final String validOffersMsg = "THIS ITEM HAS VALID VENDOR REPAIR OFFERS.  TAP MAGNIFYING GLASS TO VIEW " +
            "VALID VENDOR REPAIR OFFERS FOR THIS ITEM. SEND ITEM TO VENDOR FOR REPLACEMENT";
    private final String noOffersMsg = "NO WARRANTY OR VENDOR REPAIR OFFERS FOUND.";

    public InventoryLookup(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
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

    public void execute(String barcode, String market, String location) {
        executeRoutine(barcode, market, location);
    }


    public void executeRoutine(String barcode, String market, String location) {
        last = true;
        barcode(barcode);
        nextField();
        clickValidationFile();
        clickLookupValue(market);
        clickValidationFile();
        clickLookupValue(location);
    }

    public void barcode(String barcode) {
        routineDetail("BARCODE", barcode, true);
    }

}
