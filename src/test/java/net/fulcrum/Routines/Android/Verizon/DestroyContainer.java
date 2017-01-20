/**
 * DestroyContainer.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import net.fulcrum.Pages.Android.Routines;


public class DestroyContainer extends VerizonRoutine {

    private String routine = "Destroy Container";
    private final String loopField = "Container Label";

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

    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[8]/android.view.View[1]")
	protected WebElement containerItems;

    public DestroyContainer (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void executeRoutine (boolean batched, String containerCode, String item)
    {
        last = true;
        containerLabelLookup(true, containerCode);
        nextField();
        ellipsis(false, item);
        nextField();
        clickYes();
        verifyLoopfield(true, loopField);
    }
}
