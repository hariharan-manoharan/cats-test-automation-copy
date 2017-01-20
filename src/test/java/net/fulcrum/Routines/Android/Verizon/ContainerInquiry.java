/**
 * DestroyContainer.java
 *
 * Java Class Object to perform a PO Receive for CATS NextGen Mobility for Android
 *
 */

package net.fulcrum.Routines.Android.Verizon;

import java.util.List;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import net.fulcrum.Drivers.OracleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import net.fulcrum.Pages.Android.Routines;

public class ContainerInquiry extends VerizonRoutine {

    private String routine = "Container Inquiry";
    private final String loopField = "Container Label";

    public void reset() {
        clickBackRoutines();
        selectRoutine();
        isInitial = true;
        result = true;
    }

    public void selectRoutine() {
        routines.selectRoutine(routine);
    }

    public ContainerInquiry (AndroidMobilityDriver mobilityDriver, OracleDriver oracleDriver, Routines routines){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
        this.routines = routines;
    }

    public void executeRoutine (String containerCode)
    {
        last = true;
        containerLabel(true, containerCode);
        nextField();
        containerContents();
        nextField();
        verifyLoopfield(true, loopField);
    }

    private void parentContainer() {
        verifyRoutineDetail("Parents of this Container", false);
    }

    private void containerContents() {
        verifyRoutineDetail("Container Contents", false);
    }

}
