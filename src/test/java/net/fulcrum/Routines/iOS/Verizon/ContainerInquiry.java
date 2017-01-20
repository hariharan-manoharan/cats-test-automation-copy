package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Routines.iOS.IOSRoutine;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class ContainerInquiry extends IOSRoutine {

    private String routine = "Container Inquiry";


    private boolean isInitial = true;

    private void reset() {
        mobilityDriver.clickByName("Container Label");
    }


    public ContainerInquiry (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine (String containerCode)
    {
        last = true;
        containerLabel(true, containerCode);
        containerContents();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("*Enter Container Label", 60);
    }

    private void parentContainer() {
        verifyRoutineDetail("Parents of this Container");
    }

    private void containerContents() {
        verifyRoutineDetail("Enter Container Contents");
    }

}
