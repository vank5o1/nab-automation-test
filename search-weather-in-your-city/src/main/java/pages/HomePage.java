package pages;

import base.PageObjectBase;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends PageObjectBase {
    public HomePage(WebDriver Idriver) {
        super(Idriver);
    }

    @FindBy(xpath = "//*[@id='stick-footer-panel']//button[contains(text(),'Allow all')]")
    private WebElement allowAllCookies;

    @FindBy(xpath = "//*[@id='desktop-menu']/form[@role='search' and @action='/find']/input[@type='text']")
    private WebElement searchBox_home;

    @Step("Allow all cookies")
    public HomePage allowAllCookies(){
        click(allowAllCookies);
        return this;
    }

    @Step("Search weather with a city")
    public HomePage searchWeatherOnHomePage(String value) {
        sendKeys(searchBox_home,value);
        sendKey(searchBox_home, Keys.ENTER);
        return this;
    }
}
