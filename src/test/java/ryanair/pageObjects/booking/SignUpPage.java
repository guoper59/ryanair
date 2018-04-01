package ryanair.pageObjects.booking;

import org.openqa.selenium.By;
import ryanair.pageObjects.BasePage;

import java.util.Random;

public class SignUpPage extends BasePage {
    private By login = By.xpath("//button[contains(@ui-sref,'login')]");
    private By submit = By.xpath("//button[contains(@type,'submit') and contains(@class,'core-btn-primary')]");
    private By email = By.xpath("//div[@class='form-field']//input[contains(@name,'emailAddress')]");
    private By password = By.xpath("//input[contains(@name,'password')]");

    public SignUpPage() {
        //Call constructor to setup webdriver
        super();
    }

    public void clickLogin() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.clickElement(this.login);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();

    }

    public void login() {
        this.waitForPageToLoad();
        this.waitForBodyToLoad();
        this.waitForElementToBeEnabled(email);
        //TODO: This could be also in a JSON properties file
        this.typeText(email,"alfonso.fuertes@gmail.com");
        this.waitForElementToBeEnabled(password);
        this.typeText(password,"Snacheski59");
        this.clickElement(this.submit);
        this.waitForPageToLoad();
        this.waitForBodyToLoad();

    }

}
