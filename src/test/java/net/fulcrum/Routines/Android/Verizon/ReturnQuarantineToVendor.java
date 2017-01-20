/**
 * ReturnQuarantineToVendor.java
 *
 * Java Class Object to perform a Return Quarantine To Vendor for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import net.fulcrum.Pages.Android.Routines;

public class ReturnQuarantineToVendor extends VerizonRoutine {

	private final String routine = "Return Quarantine To Vendor";
    private final String trxType = "QUARANTINERETURN";
    private final String loopField = "Quarantine Label";


    private String shipper = "FEDEX";
    private String trackingNumber = "1233456";
    private String vendorRMA = "VENDORRMA123";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

	public ReturnQuarantineToVendor (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;

    }
	
	public void executeRoutine(boolean batched, String quarantineLabel, String partCode, int qty) {
        if (!isInitial && !last) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
        }

        quarantineLabel(true, quarantineLabel);
        nextField();
		quarantinePartCode(true, partCode);
		nextField();
        poCode(false, "");
        nextField();
        quarantineQuantity(true, String.valueOf(qty));
		nextField();
        shipper(false, shipper);
		nextField();
        trackingNumber(false, trackingNumber);
		nextField();
        vendorRMA(false, vendorRMA);
		nextField();
		notes(true, routine);
		nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
	}
}
