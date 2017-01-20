/**
 * ToRepair.java
 *
 * Java Class Object to perform a ToRepair for CATS NextGen Mobility for Android
 *
 */
package net.fulcrum.Routines.Android.Verizon;
import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import org.openqa.selenium.By;
import net.fulcrum.Pages.Android.Routines;



public class ToRepair extends VerizonRoutine {

	private final String routine = "To Repair";
	private final String trxType = "TO_REPAIR";
	private final String loopField = "Barcode";


	private String vendor = "1001 42ND STREET LLC.0000111929";
	private String carrier = "UPS";
	private String trackingNumber = "SA2FA244S321";
	private String failLDC = "CNS236520";
	private String failReasonCode = "24H";
	private String fromBU = "NTIWM";
	private String failTechnician = "CATSADM CATSADM";
    private boolean checkAsset = false;

	public void reset() {
		clickBackRoutines();
		selectRoutine();
		isInitial = true;
		result = true;
	}

	public void selectRoutine() {
		routines.selectRoutine(routine);
	}

	public ToRepair (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
		this.mobilityDriver = mobilityDriver;
		this.oracleDriver = oracleDriver;
		this.routines = routines;
	}

	public void execute(Boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA, String barcode, int qty) {
		executeRoutine(batched, fromLDC, toLDC,  vendorRA, catsRA, barcode, qty);
	}

	public void execute(boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA, String barcode) {
		executeRoutine(batched, fromLDC, toLDC, vendorRA, catsRA, barcode, 0);
	}

	public void executeRoutine(boolean batched, String fromLDC, String toLDC, String vendorRA, String catsRA, String barcode, int qty){

		if (qty == 0) {
			checkAsset = true;
		} else {
			checkAsset = false;
		}

		if (isInitial) {
			isInitial = false;
			vendorRA(true, vendorRA);
			nextField();
			vendor(true, vendor);
			nextField();
			catsRA(false, catsRA);
			nextField();//popup
			toLDC(true, toLDC);
			nextField();
			toLocationBU(true);
			nextField();
			carrier(true, carrier);
			nextField();
			trackingNumber(true, trackingNumber);
			nextField();
		}
	        
		barcode(true, barcode);
		nextField();

        if (!checkAsset) {
			fromLDC(true, fromLDC);
			nextField();
			fromLocationBU(true, fromBU);
			nextField();
			quantity(true, qty);
			nextField();
		}

		failLDC(false, failLDC);
        nextField();
        failTechnician(false, failTechnician);
        nextField();
        failReasonCode(true, failReasonCode);

		nextField();
        nextField();

        notes(false, "CTA Android: " + routine);
		nextField();
		verifyLoopfield(true, loopField);

		if (!batched) {
			verifyTransaction(true, trxType);
		}
	}
}
