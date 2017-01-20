/**
 * CaseBreak.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import java.util.List;

import net.fulcrum.Drivers.*;
import org.openqa.selenium.By;
import net.fulcrum.Pages.Android.Routines;


public class CaseBreak extends VerizonRoutine {

    private final String routine = "Case Break";
    private final String trxType = "CASE_BREAK";
    private final String loopField = "Container Item";

    private String projectCode = "PROJECT-CTA-EXP";
    private String poCode = "PO-CTA-EXP";
    private String lineNumber = "1.0";

	protected static By containerItems = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[17]/android.view.View[1]/android.view.View[1]"); 
	protected static By containercodes = By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[5]/android.view.View[1]");

    private String partType;

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

    public CaseBreak (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines) {
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void execute (boolean batched, String toLDC, String containerCode, String containerItem) {
        executeRoutine(batched, toLDC, containerCode, containerItem, 1);
    }

    public void execute (boolean batched, String toLDC, String containerCode, String containerItem, int qty) {
        executeRoutine(batched, toLDC, containerCode, containerItem, qty);
    }

    public void executeRoutine (boolean batched, String toLDC, String containerCode, String containerItem, int qty) {

        last = true;

        partType = resolveBarcode(containerItem);

        if (!isInitial && !result) {
            reset();
        }

        if (isInitial) {
            isInitial = false;
            containerCode(true, containerCode);
            nextField();
        }

        containerItem(true, containerItem);
        nextField();

        if (isPart(partType)) {
            poCode (false, poCode);
            nextField();
            lineNumber(false, lineNumber);
            nextField();
            projectCode(false, projectCode);
            nextField();
            quantity(true, qty);
            nextField();
        }

        notes(false, routine);
        nextField();
        verifyLoopfield(true, loopField);

        if (!batched) {
            verifyTransaction(true, trxType);
        }
    }
}

