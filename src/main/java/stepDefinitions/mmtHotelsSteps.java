package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import pages.FlightSearchPage;
import pages.HotelSearchPage;

public class mmtHotelsSteps {
    HotelSearchPage hotelSearchPage;
    CommonPage commonPage;

    public mmtHotelsSteps(HotelSearchPage hotelSearchPage) {
        this.hotelSearchPage = new HotelSearchPage();
    }


    @When("User selects {string} in city field")
    public void selectCity(String city) throws InterruptedException {
        hotelSearchPage.selectCity(city);
    }

    @And("User selects {string} in {string} field for stay")
    public void userSelectsDate(String date, String field) throws InterruptedException {
        if(field.equalsIgnoreCase("checkin"))
            hotelSearchPage.selectCheckInDate(date);
        else
            hotelSearchPage.selectCheckOutDate(date);
    }

    @And("User selects {string} in {string} field for Rooms & Guests")
    public void userSelectsRoomsGuests(String count, String type) throws InterruptedException {
        hotelSearchPage.selectsRoomsGuests(count, type);
    }

    @Then("User clicks on apply button")
    public void clickBtn() throws InterruptedException {
        hotelSearchPage.clickApplyBtn();
    }
}
