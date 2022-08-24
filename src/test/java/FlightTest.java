import org.testng.annotations.Test;

public class FlightTest extends BaseTest{

    @Test
    public void testFlights() {
        homePage.selectOriginFlight();
        homePage.selectDestinationFlight();
        homePage.selectOutboundDate();
        homePage.selectIncomingDate();
        homePage.selectPassengers();

        var flightsPage = homePage.clickSearchButton();
        flightsPage.selectOutboundFlight();

        var passengersPage = flightsPage.selectIncomingFlight();
        passengersPage.getPassengers();
        passengersPage.setPersonalInformation();
        passengersPage.clickTermsCheckbox();

        var paymentPage = passengersPage.clickContinueButton();
    }
}
