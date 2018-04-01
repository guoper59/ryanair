package ryanair.pageObjects;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private By main = By.xpath("//main[@ui-view='mainView']");
    private By article = By.xpath("//article[@id='home']");
    private By smartSearch = By.xpath("//div[contains(@class,'smart-search')]");
    private By oneWay = By.xpath("//input[@id='flight-search-type-option-one-way']");

    private By from = By.xpath("//input[@aria-labelledby='label-airport-selector-from']");
    private By to =  By.xpath("//input[@aria-labelledby='label-airport-selector-to']");

    private By passengers =  By.xpath("//div[@class='dropdown-handle']");


    private By incAdults =  By.xpath("//div[@value='paxInput.adults']//button[@data-ref='core-inc-dec-increment']");
    private By incChild =  By.xpath("//div[@value='paxInput.children']//button[@data-ref='core-inc-dec-increment']");

    private By currentAdults =  By.xpath("//div[@value='paxInput.adults']//input[@ng-model='value']");
    private By currentChild =  By.xpath("//div[@value='paxInput.children']//input[@ng-model='value']");

    private By continueButton =  By.xpath("//span[contains(@translate,'continue')]/..]");

    private By ddFlyOut =  By.xpath("//input[contains(@name,'startDate')]/..//input[contains(@class,'date-input-0')]");
    private By MMFlyOut =  By.xpath("//input[contains(@name,'startDate')]/..//input[contains(@class,'date-input-1')]");
    private By YYYYFlyOut =  By.xpath("//input[contains(@name,'startDate')]/..//input[contains(@class,'date-input-2')]");

    private By letsgo = By.xpath("//button[@ng-click='searchFlights()']");


    public HomePage() {
        //Call constructor to setup webdriver
        super();
    }

    public void gotoHomePage() {
        this.driver.navigate().to("https://" + this.urlHomePage + "/" + this.urlCulture + "/" + this.urlLanguage);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
    }


    public void bookOneWay(String fromCity, String toCity, String date, int numOfAdults, int numOfChild) {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();

        this.waitForElementToBeEnabled(this.oneWay);
        this.clickElement(this.oneWay);

        this.waitForElementToBeEnabled(this.from);
        this.clickElement(this.from);

        this.clearText(from);
        this.typeText(from, fromCity);

        this.clearText(to);
        this.typeText(to, toCity);

        this.lostFocus(main);

        this.waitForPageToLoad();

        this.clearText(ddFlyOut);
        this.typeText(ddFlyOut, date.split("/")[0]);

        this.lostFocus(main);

        this.clearText(MMFlyOut);
        this.typeText(MMFlyOut, date.split("/")[1]);

        this.lostFocus(main);

        this.clearText(YYYYFlyOut);
        this.typeText(YYYYFlyOut, date.split("/")[2]);

        this.clickElement(this.passengers);

        addAdults(numOfAdults);
        addChild(numOfChild);

        this.clickElement(this.letsgo);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();

    }

    public void addAdults(int numOfAdults) {
        for ( int i = Integer.parseInt(this.getValueFromAttribute(currentAdults, "value")); i < numOfAdults; i++){
            this.clickElement(incAdults);
        }

    }

    public void addChild(int numOfChild) {
        for ( int i = Integer.parseInt(this.getValueFromAttribute(currentChild,"value")); i < numOfChild; i++) {
            this.clickElement(incChild);
        }
    }
}