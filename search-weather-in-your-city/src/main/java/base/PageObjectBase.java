package base;

import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageObjectBase {
    protected WebDriver driver;

    public PageObjectBase(WebDriver Idriver) {
        this.driver = Idriver;
        PageFactory.initElements(driver, this);
    }

    @Step("Open a website")
    public PageObjectBase openWebsite(String locator) {
        driver.get(locator);
        sleep(5);
        return this;
    }

    public static void sleep(int second) {
        try {
            int mlSecond = second * 1000;
            Thread.sleep(mlSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Wait until element to able to click")
    public boolean waitClickable(WebElement element, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver,second);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Wait until element is displayed")
    public boolean waitDisplayed(WebElement element, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, second);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element is displayed")
    public PageObjectBase isDisplayed(WebElement element, int second) {
        Assert.assertTrue(waitDisplayed(element,second));
        return this;
    }

    @Step("Click to an element")
    public PageObjectBase click(WebElement element) {
        waitClickable(element, 90);
        element.click();
        return this;
    }

    @Step("Send key to an element")
    public PageObjectBase sendKeys(WebElement element, String value) {
        waitClickable(element, 90);
        element.click();
        element.clear();
        element.sendKeys(value);
        return this;
    }

    public PageObjectBase sendKey(WebElement element, Keys value) {
        waitDisplayed(element, 90);
        element.sendKeys(value);
        return this;
    }
}

