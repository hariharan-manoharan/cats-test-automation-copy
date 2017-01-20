/**
 * Transfer.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */
package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;

public class Transfer extends VerizonRoutine {
	private final String routine = "Transfer";
	private final String trxType = "TRANSFER";
	private final String loopField = "Barcode";

	private String partCode;
	private String partType;
	private String projectCode = "PROJECT-CTA-EXP";
	private String poCode = "PO-CTA-EXP";
	private String poLine = "1.0";
	private String locationBU;

	private boolean isInitial = true;

	public void reset() {
		clickBackRoutines();
		selectRoutine();
		isInitial = true;
		result = true;
	}

	public void selectRoutine() {
		routines.selectRoutine(routine);
	}

	public Transfer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
		this.mobilityDriver = mobilityDriver;
		this.oracleDriver = oracleDriver;
		this.routines = routines;
	}

	public void executeRoutine (boolean batched, String barcode, String fromLDC, String toLDC, int qty) {

		last = true;

		partType = resolveBarcode(barcode);

		if (!isInitial && !result) {
			reset();
		}

		if (isInitial) {
			isInitial = false;
			toLDC(true, toLDC);
	    	nextField();
			locationBU = oracleDriver.getLocationBU(toLDC);
			toLocationBU(true, locationBU);
	    	nextField();
		}

		barcode(true, barcode);
		nextField();

		if (isPart(partType)) {
			fromLDC(true, fromLDC);
			nextField();
			fromLocationBU(true);
			nextField();
			poCode(false, poCode);
			nextField();
			lineNumber(false, poLine);
			nextField();
			projectCode(false, projectCode);
			nextField();
			//swipeDown(4);
			quantity(true, qty);
            nextField();
		}

		if (barcode.contentEquals("VZC")) {
			clickOk();
			confirmInterBU("Y");
		}

		notes(false, "CTA: " + routine);
		nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }

	public void confirmInterBU(String result){
		routineDetail("Confirm Inter-BU Transfer? Y/N", result, true);
	}

	public void routineComplete() {
		result = verifyRoutineDetail("Barcode", 60, true);
	}

	public void excludeReuseItems(String result) {
		routineDetail("Exclude Reuse Items From Transfer?", result, true);
	}

	public void toReuseProjectlocation(String location) {
		routineDetail("To Reuse Project Location", location, false);
	}

	public void toReuseDate(String date) {
		routineDetail("To Reuse Date (MMDDYYYY)", date, false);
	}

	public void toReuseComment(String comment) {
		routineDetail("To Reuse Comment", comment, false);
	}

	public void clearFromReuseValues(String answer) {
		routineDetail("Clear From Reuse Values? Y/N", answer, false);
	}

	public void confirmStockAsset(String answer) {
		routineDetail("Confirm Stock Asset? Y/N", answer, true);
	}

}
