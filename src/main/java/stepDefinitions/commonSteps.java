package stepDefinitions;

import io.cucumber.java.en.Given;
import pages.CommonPage;
import pages.FlightSearchPage;

public class commonSteps {

    CommonPage commonPage;

    public commonSteps(CommonPage commonPage) {
        this.commonPage = new CommonPage();
    }

    @Given("User is on {string} website")
    public void navigateToSite(String string) throws InterruptedException {
        commonPage.launchUrl();

    }

    @Given("User selects {string} from Menu options")
    public void navigateToTab(String menuOption) throws InterruptedException {
        commonPage.clickMenu(menuOption);
    }
}
