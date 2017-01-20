/**
 * AdvanceRepairReceive.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */
package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Routines.Android.AndroidRoutine;

public class AdvancedRepairReceive extends VerizonRoutine {

    private final String routine = "Advanced Repair Receive";

	private String vendorRA = "# T84TAA02GU";
    private String vendor = "1-800HOSTING INC.0000117175";
    private String catsRA = "RA00242942";
    private String toLDC = "CNS103383";
    private String assetCode = "";
    private int qty = 1;
    private boolean isAsset;

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public AdvancedRepairReceive(AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine (String partCode) {
        //check if part is serialized or non serialized
        isAsset = oracleDriver.isAsset(partCode);

        if (isInitial) {
            vendorRA(true, vendorRA);
            nextField();
            vendor(true, vendor);
            nextField();
            catsRA(false, catsRA);
            nextField();
            toLDC(true, toLDC);
            nextField();
            toLocationBU(true);
            nextField();
        }

        partCode(true, partCode);
        nextField();

        if (isAsset) {
            assetCode(true, assetCode);
            nextField();
            nextField();
            nextField();
        } else {
            quantity(true, qty);
        }

        nextField();
        isInitial = false;
    }
}
