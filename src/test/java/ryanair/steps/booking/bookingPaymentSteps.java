package ryanair.steps.booking;

import ryanair.pageObjects.HomePage;
import ryanair.pageObjects.booking.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class bookingPaymentSteps {
    private HomePage homePage;
    private FaresPage faresPage;
    private SeatsPage seatsPage;
    private SignUpPage signUpPage;
    private PaymentPage paymentPage;

    public bookingPaymentSteps() {
        this.homePage = new HomePage();
        this.faresPage = new FaresPage();
        this.seatsPage = new SeatsPage();
        this.signUpPage = new SignUpPage();
        this.paymentPage = new PaymentPage();
    }

    @Given("^I make a booking from (.*) to (.*) on (.*) for (\\d+) adults and (\\d+) child$")
    public void booking(String fromCity, String toCity, String date, int numOfAdults, int numOfChildren) {
        //Go to home page
        this.homePage.gotoHomePage();
        this.homePage.bookOneWay(fromCity, toCity, date, numOfAdults, numOfChildren);

        this.faresPage.selectRandomFlightAndRandomFare();
        this.faresPage.gotoNextScreen();

        this.seatsPage.gotIt();

        this.seatsPage.selectSeats(numOfAdults + numOfChildren);
        this.seatsPage.clickNext();

        this.seatsPage.confirm();

        this.seatsPage.dismissSecurityFastTrack();

        this.seatsPage.dismissPrioriy();

        this.seatsPage.checkout();

        this.seatsPage.dismissCarHire();

        this.signUpPage.clickLogin();
        this.signUpPage.login();
        this.paymentPage.fillRandomPassengerDetails(numOfAdults + numOfChildren);
    }


    @When("^I pay for booking with card details (.*), (.*) and (.*)$")
    public void payWithCreditCard(String cardNum, String expirationDate, String ccv) {

        this.paymentPage.fillRandomBillingAddress();
        this.paymentPage.payWithCreditCard(cardNum, expirationDate, ccv);

    }

    @Then("^I should get payment (.*) message$")
    public void verifyDeclinedPaymentMessage(String message) {
        Assert.assertTrue(this.paymentPage.getReturningPaymentMessage().toLowerCase().contains(message));
        this.paymentPage.quit();
    }


}
