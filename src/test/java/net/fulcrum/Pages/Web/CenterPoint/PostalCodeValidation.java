package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class PostalCodeValidation extends DataForm {

    public PostalCodeValidation(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchPostalCode = By.name("Z.POSTALCODE");
    protected By searchCity = By.name("Z.CITY");
    protected By searchState = By.name("Z.STATE");
    protected By searchLatitude = By.name("Z.LATITUDE");
    protected By searchLongitude = By.name("Z.LONGITUDE");
    protected By searchCountry = By.name("Z.COUNTRY");
    protected By searchWorldRegion = By.name("Z.WORLDREGION");
    protected By searchLocationText = By.name("Z.LOCATIONTEXT");
    protected By searchLocation = By.name("Z.LOCATION");
    protected By searchAddedBy = By.name("Z.ADDCONTACTID");
    protected By searchModifiedBy = By.name("Z.MODIFIEDCONTACTID");

    //Edit Tab Columns
    protected By editPostalCode = By.name("POSTALCODE");
    protected By editCity = By.name("CITY");
    protected By editState = By.name("STATE");
    protected By editLatitude = By.name("LATITUDE");
    protected By editLongitude = By.name("LONGITUDE");
    protected By editWorldRegion = By.name("WORLDREGION");
    protected By editCountry = By.name("COUNTRY");
    protected By editLocationText = By.name("LOCATIONTEXT");
    protected By editLocation = By.name("LOCATION");

    public void enterSearchPostalCode(String code) {
        driver.sendText(searchPostalCode, code);
    }

    public void enterSearchCity(String city) {
        driver.sendText(searchCity, city);
    }

    public void enterSearchState(String state) {
        driver.sendText(searchState, state);
    }

    public void enterSearchLatitude(String latitude) {
        driver.sendText(searchLatitude, latitude);
    }

    public void enterSearchLongitude(String longitude) {
        driver.sendText(searchLongitude, longitude);
    }

    public void enterSearchCountry(String country) {
        driver.sendText(searchCountry, country);
    }

    public void enterSearchWorldRegion(String region) {
        driver.sendText(searchWorldRegion, region);
    }

    public void enterSearchLocationText(String locationText) {
        driver.sendText(searchLocationText, locationText);
    }

    public void enterSearchLocation(String location) {
        driver.sendText(searchLocation, location);
    }

    public void enterSearchAddedBy(String addedBy) {
        driver.sendText(searchAddedBy, addedBy);
    }

    public void enterSearchModifiedBy(String modifiedBy) {
        driver.sendText(searchModifiedBy, modifiedBy);
    }

    public void enterEditPostalcode(String code) {
        driver.sendText(editPostalCode, code);
    }

    public void enterEditCity(String city){
        driver.sendText(editCity, city);
    }

    public void enterEditState(String state) {
        driver.sendText(editState, state);
    }

    public void enterEditLatitude(String latitude) {
        driver.sendText(editLatitude, latitude);
    }

    public void enterEditLongitude(String longitude) {
        driver.sendText(editLongitude, longitude);
    }

    public void editEditWorldRegion(String region) {
        driver.sendText(editWorldRegion, region);
    }

    public void enterEditCountry(String country) {
        driver.sendText(editCountry, country);
    }

    public void enterEditLocationText(String locationText) {
        driver.sendText(editLocationText, locationText);
    }

    public void enterEditLocation(String location) {
        driver.sendText(editLocation, location);
    }


}
