package ryanair.pageObjects.booking;

import org.openqa.selenium.By;
import ryanair.pageObjects.BasePage;

public class FaresPage extends BasePage {
    private By next = By.xpath("//button[contains(@id, 'continue')]");
    private By flights = By.xpath("//div[contains(@class,'flight-header__min-price')]/flights-table-price/div[contains(@class,'flights-table-price')]/div[contains(@class,'core-btn-primary')]");
    private By fares = By.xpath("//div[contains(@class,'flights-table-fares__fare-price-wrapper')]");

    public FaresPage() {
        //Call constructor to setup webdriver
        super();
    }

    public void selectRandomFlightAndRandomFare() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitForElementToBeDisplayed(this.flights);
        this.waitForElementToBeEnabled(this.flights);
        this.clickElementOfTheList(this.flights, 1);

        this.waitForElementToBeDisplayed(this.fares);
        this.waitForElementToBeEnabled(this.fares);
        this.clickElementOfTheList(this.fares, 1);

        this.waitForPageToLoad();
        this.waitForBodyToLoad();    }

    public void gotoNextScreen() {
        this.waitForElementToBeEnabled(this.next);
        this.clickElement(this.next);
        this.waitForPageToLoad();
    }
}
