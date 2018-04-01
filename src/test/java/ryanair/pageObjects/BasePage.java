package ryanair.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected static WebDriver driver = null;
    protected String userDirectory = System.getProperty("user.dir");
    protected String defaultDateFormat;
    protected String urlHomePage;
    protected String urlCulture;
    protected String urlLanguage;


    static Integer DEFAULT_WAIT_RETRY = 1000;
    static Integer DEFAULT_WAIT_LIMIT = 40;

    public BasePage() {
        //Constructor will use config values once config is implemented

        //If driver is null instantiate it
        if (this.driver == null) {
            //need to have an array list of drivers, one per test per thread. need to figure this out.
            System.setProperty("webdriver.gecko.driver", userDirectory + "\\drivers\\geckodriver.exe");
            this.driver = new FirefoxDriver();
            //Maximize
            this.driver.manage().window().maximize();
        }
        //TODO: Add the following info to data table or a config file
        this.defaultDateFormat = "dd/MM/yyyy";
        this.urlHomePage = "www.ryanair.com";
        this.urlCulture = "ie";
        this.urlLanguage = "en";
    }

    protected void finalize() throws Throwable {
        this.driver.quit();
    }

    protected boolean isElementDisplayedAndEnabled(By by) {
        List<WebElement> webElements = this.driver.findElements(by);
        int elementCount = webElements.size();
        if (elementCount != 0) {
            for (int i = 0; i < elementCount; i++) {
                //checking first matching element that is enabled and displayed
                if (webElements.get(i).isEnabled() && webElements.get(i).isDisplayed()) {
                    return true;
                }
            }

        }
        return false;
    }

    protected boolean isElementDisplayedAndEnabled(WebElement element) {
        if (element.isEnabled() && element.isDisplayed()) {
                    return true;

        }
        return false;
    }

    public boolean isElementDisplayed(By by) {
        List<WebElement> webElements = this.driver.findElements(by);
        int elementCount = webElements.size();
        if (elementCount != 0) {
            for (int i = 0; i < elementCount; i++) {
                //checking first matching element that is displayed
                if (webElements.get(i).isDisplayed()) {
                    return true;
                }
            }

        }
        return false;
    }

    protected boolean isElementEnabled(WebElement element) {
        if (element.isEnabled()) {
            return true;
        }
        return false;
    }
    protected boolean isElementEnabled(By by) {
        List<WebElement> webElements = this.driver.findElements(by);
        int elementCount = webElements.size();
        if (elementCount != 0) {
            for (int i = 0; i < elementCount; i++) {
                //checking first matching element that is enabled
                if (webElements.get(i).isEnabled()) {
                    return true;
                }
            }

        }
        return false;
    }

    public void waitWhileSpinning() {
        Integer count = 0;
        try {
            while (isElementDisplayed(By.className("plane-spinner")) == true && count < 5) {
                try {
                    Thread.sleep(DEFAULT_WAIT_LIMIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (StaleElementReferenceException s){
            s.printStackTrace();
            waitForPageToLoad();
        }
    }


    public void waitForElementToBeEnabled(WebElement element) {
        Integer count = 0;
        while (isElementEnabled(element) == false && count < 5) {
            try {
                Thread.sleep(DEFAULT_WAIT_LIMIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void waitForElementToBeEnabled(By by) {
        Integer count = 0;
        while (isElementEnabled(by) == false && count < 5) {
            try {
                Thread.sleep(DEFAULT_WAIT_LIMIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected boolean isTextPresent(String text) {
        try {
            return this.driver.getPageSource().contains(text);
        } catch (JavascriptException e) {
            return false;
        }
    }

    public void waitForElementToBeDisplayed(By by) {
        Integer count = 0;
        while (isElementDisplayed(by) == false && count < 26) {
            try {
                Thread.sleep(DEFAULT_WAIT_LIMIT);
                 count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void waitForPageToLoad() {

        int retries = 26; //Loop 26 times
        String waitJS = "return document.readyState";
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        waitForBodyToLoad();
        for (int i = 0; i < retries; i++) {
            if (js.executeScript(waitJS).equals("complete")) {
                try {
                    this.driver.wait(1000);
                } catch (Exception ex) {
                }

                return;
            }
        }

    }


    protected void waitForBodyToLoad() {
        int retries = 26; //Loop 26 times
        for (int i = 0; i < retries; i++) {
            WebElement elm = this.driver.findElement(By.tagName("body"));

            if (!elm.getAttribute("class").contains("tpl-homepage homepage")) {
                try {
                    this.driver.wait(1000);
                } catch (Exception ex) {
                }

                return;
            }
        }
    }

    protected String getDate() {
        return getDate(this.defaultDateFormat);
    }

    protected String getDate(String dateFormat) {
        Date currentDate = new Date();
        return getDateTime(dateFormat, currentDate);
    }

    protected String getDateTime(String dateFormat, Date date) {
        return "";
    }


    protected void clickElement(By by) {
        WebElement element = this.driver.findElement(by);
        element.click();
    }

    protected void waitUntilListBeLoaded(By by) {
        Integer count = 0;
        List<WebElement> elements = this.driver.findElements(by);
        while ( elements.size() == 0 && count < 5) {
            try {
                Thread.sleep(DEFAULT_WAIT_LIMIT);
                elements = this.driver.findElements(by);
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    protected void clickRandomElementOfTheList(By by) {
        this.waitUntilListBeLoaded(by);
        List<WebElement> elements = this.driver.findElements(by);
        Random seed = new Random();
        if (elements.size() > 1) {
            int num = seed.nextInt(elements.size() - 1);
            //If element is not displayed or enabled get another one of the list
            while ( !( elements.get(num).isDisplayed() ) && !( elements.get(num).isEnabled() && !(this.isElementDisplayedAndEnabled(elements.get(num)))) )
                num = seed.nextInt(elements.size() - 1);
            elements.get(num).click();
            //elements.remove(num);
        } else
            elements.get(0).click();
    }

    protected void clickDeterminatedElementOfTheList(By by, int num) {
        this.waitUntilListBeLoaded(by);
        List<WebElement> elements = this.driver.findElements(by);
        elements.get(num).click();

    }


    protected void clickElementOfTheList(By by, int index) {
        List<WebElement> elements = this.driver.findElements(by);
        if (elements.size() > 1) {
            waitForElementToBeEnabled(elements.get(index));
            elements.get(index).click();
        } else
            elements.get(0).click();
    }

    protected void typeTextOnElementOfTheList(By by, int index, String text) {
        List<WebElement> elements = this.driver.findElements(by);
        if (elements.size() > 1) {
            typeTextOnElement(elements.get(index-1),text);
        } else
            typeTextOnElement(elements.get(0),text);
    }


    protected void selectDropdownOfTheListByText(By by, int index, String text) {
        List<WebElement> elements = this.driver.findElements(by);
        if (elements.size() > 1 && elements.size() > (index-1) ) {
            selectDropdownByText(elements.get(index-1),text);
        }else
            selectDropdownByText(elements.get(0),text);

    }

    protected void lostFocus(By by) {
        WebElement element = this.driver.findElement(by);
        element.click();
    }


    //Enter text in input
    protected void typeText(By by, String text) {
        WebElement element = this.driver.findElement(by);
        element.sendKeys(text);
    }

    //Enter text in input
    protected void typeTextOnElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    //Clear text in input
    protected void clearText(By by) {
        WebElement element = this.driver.findElement(by);
        element.clear();
    }


    protected boolean isElementChecked(By by) {
        return this.driver.findElement(by).isSelected();
    }

    protected String getValueFromInput(By by) {
        WebElement formInput = this.driver.findElement(by);
        return formInput.getText().toString();
    }

    protected String getValueFromAttribute(By by, String attribute) {
        WebElement formInput = this.driver.findElement(by);
        return formInput.getAttribute(attribute);
    }


    protected void selectDropdownByText(By by, String text)
    {
        Select select = new Select(this.driver.findElement(by));
        if(select.isMultiple())
        {
            select.deselectAll();
        }
        select.selectByVisibleText(text);
    }

    protected void selectDropdownByText(WebElement element, String text)
    {
        Select select = new Select(element);
        if(select.isMultiple())
        {
            select.deselectAll();
        }
        select.selectByVisibleText(text);
    }

    public void quit()  {
        this.driver.quit();
    }
}
