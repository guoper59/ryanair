package ryanair.pageObjects.booking;

import org.openqa.selenium.By;
import ryanair.pageObjects.BasePage;

public class SeatsPage extends BasePage {
    private By spinner = By.className("plane-spinner");
    private By seats = By.xpath("//div[@class='ranimate-seat-rows']/div[@class='seat-wrapper seat-group-STANDARD']/span[@class='seat-row-seat standard']/span[@class='seat-click']/img[@class='icon-26 seat-person' and not(following-sibling::*)]/..");
    private By seatMap = By.xpath("//div[contains(@class,'seat-map-scrolling-body')]");
    private By passengers = By.xpath("//div[@class='sm-carousel__pax-circle']/core-icon");
    private By gotIt = By.xpath("//button[contains(@class,'same-seats') and contains(@class,'core-btn-primary')]");
    private By next = By.xpath("//button[contains(@class,'dialog-overlay-footer__ok-button')]");
    private By checkout = By.xpath("//span[contains(@translate,'trips.summary.buttons.btn_checkout')]/..");
    private By basketMessage = By.xpath("//div[@class='show-basket-pop ng-hide']");
    private By dismissCarHire = By.xpath("//div[contains(@class,'popup-msg__close')]");
    private By dismissPriority = By.xpath("//div[contains(@class,'priority-boarding-with-bags-popup__close-x')]");
    private By dismissSecurityFastTrack = By.xpath("//a[contains(@class,'fasttrack-popup__close core-link-inline')]");


    public SeatsPage() {
        //Call constructor to setup webdriver
        super();
    }

    public void selectSeats(int passangers) {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitForElementToBeEnabled(this.seatMap);

        //Front, Standard or On Sale seats
        for ( int i = 1; i <= passangers; i++) {
            this.waitForBodyToLoad();
            this.clickElementOfTheList(passengers, i - 1);
            this.waitForBodyToLoad();
            this.clickElementOfTheList(this.seats, 40 + i);
            this.waitForBodyToLoad();
        }
        this.waitForBodyToLoad();
    }

    public void gotIt() {
        this.waitForPageToLoad();
        this.waitForElementToBeDisplayed(this.spinner);
        this.waitWhileSpinning();
        this.waitForElementToBeDisplayed(this.gotIt);
        if ( this.isElementDisplayed(this.gotIt) && this.isElementDisplayedAndEnabled(this.gotIt) )
            this.clickElement(this.gotIt);

        this.waitForPageToLoad();
    }

    public void clickNext() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitForElementToBeDisplayed(this.next);
        this.clickElement(this.next);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

    public void confirm() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitForElementToBeDisplayed(this.next);
        this.clickElement(this.next);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

    public void checkout() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();

        this.waitForElementToBeDisplayed(this.checkout);
        this.waitForElementToBeEnabled(this.checkout);
        this.waitForElementToBeDisplayed(this.basketMessage);

        this.waitWhileSpinning();
        //TODO: I need to figure it our why I need to click twice.
        this.clickElement(this.checkout);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.clickElement(this.checkout);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

    public void dismissCarHire(){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        if ( this.isElementDisplayed(this.dismissCarHire) )
            this.clickElement(this.dismissCarHire);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

    public void dismissPrioriy(){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        if ( this.isElementDisplayed(this.dismissPriority) )
            this.clickElement(this.dismissPriority);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

    public void dismissSecurityFastTrack(){
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        if ( this.isElementDisplayed(this.dismissSecurityFastTrack) )
            this.clickElement(this.dismissSecurityFastTrack);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }

}
