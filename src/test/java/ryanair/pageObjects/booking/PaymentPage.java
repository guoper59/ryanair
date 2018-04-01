package ryanair.pageObjects.booking;

import ryanair.pageObjects.BasePage;
import org.openqa.selenium.By;
import ryanair.util.NameGenerator;

public class PaymentPage extends BasePage {
    private By message = By.xpath("//prompt[@class='error prompt-text']");
    private By payNow = By.xpath("//button[contains(@translate, 'common.components.payment_forms.pay_now')]");
    private By name = By.xpath("//div[@class='passengers-form']//input[contains(@name,'firstName')]");
    private By surname = By.xpath("//div[@class='passengers-form']//input[contains(@name,'lastName')]");
    private By title = By.xpath("//div[@class='passengers-form']//select[contains(@name,'title')]");

    private By cardNumber = By.name("cardNumber");
    private By cardHolderName = By.name("cardHolderName");
    private By securityCode = By.name("securityCode");
    private By expiryMonth = By.name("expiryMonth");
    private By expiryYear = By.name("expiryYear");
    private By cardType = By.name("cardType");

    private By billingAddressAddressLine1 = By.id("billingAddressAddressLine1");
    private By billingAddressAddressLine2 = By.id("billingAddressAddressLine2");
    private By billingAddressCity = By.id("billingAddressCity");
    private By billingAddressPostcode = By.id("billingAddressPostcode");
    private By billingAddressCountry = By.id("billingAddressCountry");
    private By billingAddressState = By.id("billingAddressState");

    private By acceptPolicy = By.xpath("//input[contains(@name,'acceptPolicy')]");


    public PaymentPage() {
        //Call constructor to setup webdriver
        super();
    }

    public String getReturningPaymentMessage(){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitWhileSpinning();
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        return this.getValueFromAttribute(message, "ng-switch-when");
    }

    public void payWithCreditCard(String cardNum, String expirationDate, String ccv){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.typeText(cardNumber, cardNum.replaceAll(" ", ""));
        this.typeText(cardHolderName, "John Doe");
        this.typeText(securityCode, ccv);
        this.selectDropdownByText(expiryMonth, expirationDate.split("/")[0]);
        this.selectDropdownByText(expiryYear, expirationDate.split("/")[1]);
        this.selectDropdownByText(cardType,"MasterCard Debit" );
        this.clickElement(acceptPolicy);

        this.clickElement(payNow);
    }

    public void fillRandomBillingAddress(){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.typeText(billingAddressAddressLine1, "Some Street");
        this.typeText(billingAddressAddressLine2, "Alley");
        this.typeText(billingAddressCity, "Dallas");
        this.typeText(billingAddressPostcode, "75206");
        this.selectDropdownByText(billingAddressCountry,"United States of America" );
        this.selectDropdownByText(billingAddressState,"Texas" );
    }

    public void fillRandomPassengerDetails(int passengers){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        NameGenerator generator = new NameGenerator();
        for (int n = 1; n <= passengers; n++){
            this.selectDropdownOfTheListByText(title, n,"Mr");
            this.typeTextOnElementOfTheList(name, n, generator.getFirstName());
            this.typeTextOnElementOfTheList(surname, n,generator.getLastName());
        }
    }

}
