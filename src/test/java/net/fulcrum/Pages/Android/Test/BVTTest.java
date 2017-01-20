package net.fulcrum.Pages.Android.Test;


import net.fulcrum.Routines.Android.Core.*;
import org.testng.Assert;
import org.testng.annotations.*;
import net.fulcrum.testrail.annotations.*;

public class BVTTest extends MainTest {


    private AssignValue assignValue;
    private Clear clear;
    private Fields fields;
    private GoTo goTo;
    private Maths math;
    private Next next;
    private Hide hide;
    private Length length;
    private EmptyString emptyString;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        assignValue = new AssignValue(mobilityDriver, oracleDriver, routines);
        clear = new Clear(mobilityDriver, oracleDriver, routines);
        fields = new Fields(mobilityDriver, oracleDriver, routines);
        goTo = new GoTo(mobilityDriver, oracleDriver, routines);
        math = new Maths(mobilityDriver, oracleDriver, routines);
        next = new Next(mobilityDriver, oracleDriver, routines);
        hide = new Hide(mobilityDriver);
        length = new Length(mobilityDriver);
        emptyString = new EmptyString(mobilityDriver);
    }

    @TestRailCase(automationId = "7008")
    @Test(priority = 4, enabled = true)
    public void signOut () {
        menu.signOut();
    }

    @TestRailCase(automationId = "7005")
    @Test(priority = 5, enabled = true)
    public void navigateToMoreInfo() {
        login.clickInfo();
        info.verifyPage();
    }

    @TestRailCase(automationId = "7006")
    @Test(priority = 6, enabled = false)
    public void clickGPSRefresh() {
        info.clickGPSRefresh();
    }

    @TestRailCase(automationId = "7004")
    @Test(priority = 7, enabled = true)
    public void clickDone() {
        info.clickDone();
    }

    @TestRailCase(automationId = "7009")
    @Test(priority = 8, enabled = true)
    public void login() {
        loginMobility(false);
    }

    @TestRailCase(automationId = "7002")
    @Test(priority = 9, enabled = true)
    public void selectProfile() {
        selectProfile(true, "ADMIN");
    }

    @TestRailCase(automationId = "7003")
    @Test(priority = 10, enabled = true)
    public void selectDifferentProfile() {
        menu.selectMenu();
        selectProfile(true, "TESTING");
    }

    @TestRailCase(automationId = "7007")
    @Test(priority = 11, enabled = false)
    public void validationFiles() {
        menu.selectValidationFiles();
        validationFiles.downloadValidationFile("LOCATIONDETAILCODE");
        validationFiles.clickBack();
    }

    @TestRailCase(automationId = "7010")
    @Test(priority = 20, enabled = true)
    public void assignValue() {
        routines.selectRoutine("Assign Value Test");
        assignValue.executeRoutine();
        Assert.assertEquals(true, assignValue.getResult());
    }

    @TestRailCase(automationId = "7011")
    @Test(priority = 21, enabled = true)
    public void clear() {
        clear.clickBackRoutines();
        routines.selectRoutine("Clear Test");
        clear.executeRoutine();
        Assert.assertEquals(true, clear.getResult());
    }

    @TestRailCase(automationId = "7018")
    @Test(priority = 22, enabled = true)
    public void fields() {
        fields.clickBackRoutines();
        routines.selectRoutine("Fields Test");
        fields.executeRoutine();
        Assert.assertEquals(true, fields.getResult());
    }

    @TestRailCase(automationId = "7012")
    @Test(priority = 23, enabled = true)
    public void goTo() {
        goTo.clickBackRoutines();
        routines.selectRoutine("Goto Test");
        goTo.executeRoutine();
        Assert.assertEquals(true, goTo.getResult());
    }
    @TestRailCase(automationId = "7013")
    @Test(priority = 24, enabled = true)
    public void math() {
        math.clickBackRoutines();
        routines.selectRoutine("Maths Test");
        math.executeRoutine();
        Assert.assertEquals(true, math.getResult());
    }

    @TestRailCase(automationId = "7014")
    @Test(priority = 25, enabled = true)
    public void next() {
        next.clickBackRoutines();
        routines.selectRoutine("Next Test");
        next.executeRoutine();
        Assert.assertEquals(true, next.getResult());
    }

    @TestRailCase(automationId = "7015")
    @Test(priority = 26, enabled = true)
    public void hide() {
        hide.clickBackRoutines();
        routines.selectRoutine("Hide Test");
        hide.executeRoutine();
        Assert.assertEquals(true, hide.getResult());
    }

    @TestRailCase(automationId = "7016")
    @Test(priority = 27, enabled = true)
    public void length() {
        length.clickBackRoutines();
        routines.selectRoutine("Length Test");
        length.executeRoutine();
        Assert.assertEquals(true, length.getResult());
    }

    @TestRailCase(automationId = "7017")
    @Test(priority = 28, enabled = true)
    public void emptyString() {
        emptyString.clickBackRoutines();
        routines.selectRoutine("Empty String Test");
        emptyString.executeRoutine();
        Assert.assertEquals(true, emptyString.getResult());
    }
}
