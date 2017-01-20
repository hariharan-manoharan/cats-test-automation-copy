/**
 * AssetInquiry.java
 *
 * Java Class Object to perform an Asset Inquiry for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;


public class SiteRefreshMissing extends VerizonRoutine {
    private static final String routine = "Site Refresh Missing";
    private final String trxType = "SITE_REFRESH_MISSING";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public SiteRefreshMissing (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }


    public void execute (boolean batched, String LDC, String barcode) {
        executeRoutine(batched, LDC, barcode);
    }

    public void executeRoutine (Boolean batched, String LDC, String barcode) {
        auditLocation(true, LDC);
        nextField();
        locationBU(true);
        nextField();
        missingBarcode(true, barcode);
        nextField();
        notes(false, "CTA Android: " + routine);
        nextField();

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }
}
