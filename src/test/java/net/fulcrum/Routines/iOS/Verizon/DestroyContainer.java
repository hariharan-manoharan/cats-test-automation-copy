package net.fulcrum.Routines.iOS.Verizon;

import net.fulcrum.Drivers.*;
import net.fulcrum.Routines.Android.AndroidRoutine;
import net.fulcrum.Routines.iOS.IOSRoutine;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DestroyContainer extends IOSRoutine {

    private String routine = "Destroy Container";


    private boolean isInitial = true;

    private void reset() {
        mobilityDriver.clickByName("To LDC");
    }

    @FindBy(xpath = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[8]/android.view.View[1]")
    protected WebElement containerItems;

    public DestroyContainer (iOSMobilityDriver mobilityDriver, OracleDriver oracleDriver){
        this.mobilityDriver = mobilityDriver;
        this.oracleDriver = oracleDriver;
    }

    public void executeRoutine (String containerCode, String item)
    {
        last = true;
        containerLabel(true, containerCode);
        ellipsis(item);
        clickYes();
        routineComplete();
    }

    public void routineComplete () {
        result = verifyRoutineDetail("*Enter Container Label", 60);
    }

    public void validateContainerItems (String containerCode, int count)
    {
        containerLabel(containerCode);
        nextField();
        containerItems.click();
        //driver.ard.waitforElement(By.name("PARTCODE"), 30);
        List<WebElement> elements;
        //elements = driver.ad.findElements(By.xpath("//android.widget.GridView"));
        //int c = elements.size();
        //System.out.println("count is : " +c);
        // Assert.assertEquals(c, count);
    }
}
