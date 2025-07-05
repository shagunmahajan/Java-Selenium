package stepDefinitions;
import io.cucumber.java.en.*;
import pages.CommonPage;
import pages.FlightSearchPage;

public class mmtFlightsSteps {
    FlightSearchPage flightSearchPage;
    CommonPage commonPage;

    public mmtFlightsSteps(FlightSearchPage flightSearchPage) {
        this.flightSearchPage = new FlightSearchPage();
        this.commonPage = new CommonPage();
    }

    @Then("User close login modal")
    public void closeModal() throws InterruptedException {
        flightSearchPage.closeModal();
    }
    @Then("User selects {string} as trip type")
    public void selectTripType(String type) throws InterruptedException {
        commonPage.clickOption(type);
    }
    @When("User selects {string} in from field")
    public void selectFromCity(String city) throws InterruptedException {
        flightSearchPage.selectFromCityField(city);
    }
    @When("User selects {string} in to field")
    public void selectToCity(String city) throws InterruptedException {
        flightSearchPage.selectToCityField(city);
    }

    @And("User selects {string} in {string} field")
    public void userSelectsDateField(String date, String field) throws InterruptedException {
        if(field.equalsIgnoreCase("depart"))
            flightSearchPage.selectDepartDate(date);
        else
            flightSearchPage.selectReturnDate(date);
    }
    @Then("User clicks on search button")
    public void clickBtn() throws InterruptedException {
        flightSearchPage.clickSearchBtn();
    }
}
