package net.fulcrum.Pages.Web.CenterPoint;

import junit.framework.Assert;
import net.fulcrum.Util.WaitTypes;
import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocationDetails extends DataForm {

    public LocationDetails(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchLocationName = By.name("L.NAME");
    protected By searchLocationDetailCode = By.name("LD.LOCATIONDETAILCODE");
    protected By searchLocationStatus = By.name("LS.DESCRIPTION");
    protected By searchFloor = By.name("LD.FLOOR");
    protected By searchRoom = By.name("D.ROOM");
    protected By searchAisle = By.name("LD.PARTAISLE");
    protected By searchRow = By.name("LD.PARTROW");
    protected By searchBay = By.name("LD.PARTBAY");
    protected By searchBin = By.name("LD.PARTBIN");
    protected By searchLocationDescription = By.name("L.DESCRIPTION");
    protected By searchCodeComponent = By.name("L.CODECOMPONENT");
    protected By searchInteralLocation = By.name("L.INTERNAL");
    protected By searchCurrentActive = By.name("L.ACTIVE");
    protected By searchLocationType = By.name("LT.DESCRIPTION");
    protected By searchLocationGroup = By.name("TG.DESCRIPTION");
    protected By searchDataGroup = By.name("L.ACTIVE");
    protected By searchShelf = By.name("LD.PARTSHELF");

    //Edit Tab Columns
    protected By editName = By.name("LOCATION_NAME");
    protected By editLocationDetailCode = By.name("LOCATIONDETAILCODE");
    protected By editLocationStatus = By.name("LOCATION_STATUS");
    protected By editFloor = By.name("FLOOR");
    protected By editRoom = By.name("ROOM");
    protected By editAisle = By.name("PARTAISLE");
    protected By editRow = By.name("PARTROW");
    protected By editBay = By.name("PARTBAY");
    protected By editShelf = By.name("PARTSHELF");
    protected By editBin = By.name("PARTBIN");

    public void enterSearchLocationName(String location) {
        driver.sendText(searchLocationName, location);
    }

    public void enterSearchLocationDetailCode(String ldc) {
        driver.sendText(searchLocationDetailCode, ldc);
    }

    public void enterSearchLocationStatus(String status) {
        driver.sendText(searchLocationStatus, status);
    }

    public void enterSearchFloor(String floor) {
        driver.sendText(searchFloor, floor);
    }

    public void enterSearchRoom(String room) {
        driver.sendText(searchRoom, room);
    }

    public void enterSearchAisle(String aisle) {
        driver.sendText(searchAisle, aisle);
    }

    public void enterSearchRow(String row) {
        driver.sendText(searchRow, row);
    }

    public void enterSearchBay(String bay) {
        driver.sendText(searchBay, bay);
    }

    public void enterSearchBin(String bin) {
        driver.sendText(searchBin, bin);
    }

    public void enterSearchLocationDescription(String desc) {
        driver.sendText(searchLocationDescription, desc);
    }

    public void enterSearchCodeComponent(String component) {
        driver.sendText(searchCodeComponent, component);
    }

    public void enterSearchInternalLocation(String internal) {
        //Select dropdown
    }

    public void enterCurrentActive(String active) {
        //select dropdown
    }

    public void enterSearchLocationType(String type) {
        driver.sendText(searchLocationType, type);
    }

    public void enterSearchLocationGroup(String group) {
        driver.sendText(searchLocationGroup, group);
    }

    public void enterSearchDataGroup(String dataGroup) {
        driver.sendText(searchDataGroup, dataGroup);
    }

    public void enterSearchShelf(String shelf) {
        driver.sendText(searchShelf, shelf);
    }

    public void enterEditName(String name) {
        driver.sendText(editName, name);
    }

    public void enterEditLocationDetailCode(String ldc){
        driver.sendText(editLocationDetailCode, ldc);
    }

    public void enterEditLocationStatus(String status) {
        driver.sendText(editLocationStatus, status);
    }

    public void enterEditFloor(String floor) {
        driver.sendText(editFloor, floor);
    }

    public void enterEditRoom(String room) {
        driver.sendText(editRoom, room);
    }

    public void enterEditAisle(String aisle) {
        driver.sendText(editAisle, aisle);
    }

    public void enterEditRow(String row) {
        driver.sendText(editRow, row);
    }

    public void enterEditBay(String bay) {
        driver.sendText(editBay, bay);
    }

    public void enterEditShelf(String shelf) {
        driver.sendText(editShelf, shelf);
    }

    public void enterEditBin(String bin) {
        driver.sendText(editBin, bin);
    }
}
