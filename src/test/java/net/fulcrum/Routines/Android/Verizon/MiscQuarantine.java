/**
 * MiscQuarantine.java
 *
 * Java Class Object to perform a Misc Quarantine for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Pages.Android.Routines;

public class MiscQuarantine extends VerizonRoutine {

	private final String routine = "Misc Quarantine";
	private final String trxType = "MISC_QUARANTINE";
	private final String loopField = "Quarantine Label";

	private String quarantineToLDC;
	private String quarantineReason = "NO PO";
	private String notes = "TEST";

	public void reset() {
		clickBackRoutines();
		selectRoutine();
		isInitial = true;
		result = true;
	}

	public void selectRoutine() {
		routines.selectRoutine(routine);
	}

	public MiscQuarantine (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
		this.mobilityDriver = mobilityDriver;
		this.oracleDriver = oracleDriver;
        this.routines = routines;
	}

	public void executeRoutine (String quarantineToLDC, String quarantineLabel, String partCode, int qty) {

		if (!isInitial && !last) {
			reset();
		}

		if (isInitial) {
			isInitial = false;
			quarantineToLDC(true, quarantineToLDC);
			nextField();
			nextField();
		}

		quarantineLabel(true, quarantineLabel);
		nextField();
		nextField();
		nextField();
		partCode(true, partCode);
		nextField();
		quantity(true, qty);
		nextField();
		quarantineReason(true, quarantineReason);
		nextField();
		notes(true, routine);
		nextField();
		verifyLoopfield(true, loopField);

		if (!batched) {
			verifyTransaction(false, trxType);
		}
	}

	private void quarantineToLDC(Boolean required, String value) {
		routineDetail("Quarantine To LDC", value, required);
	}
}
