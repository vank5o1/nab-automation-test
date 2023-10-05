package pages;

import base.PageObjectBase;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class SearchWeatherDetailsPage extends PageObjectBase {
    public SearchWeatherDetailsPage(WebDriver Idriver) {
        super(Idriver);
    }
    private int count;

    @FindBy(xpath = "//*[@id='weather-widget']//input[@placeholder='Search city']")
    private WebElement searchCityForm;

    @FindBy(xpath = "//*[@id='weather-widget']//button[@type='submit']")
    private WebElement btnSearchCity;

    @FindBy(css = "#weather-widget span.orange-text")
    private WebElement currentTime;

    @FindBy(css = "#weather-widget h2")
    private WebElement currentCityName;

    @FindBy(xpath = "//*[@id='weather-widget']//div[@class='current-temp']/span")
    private WebElement currentTemp;

    @FindBy(css = "#weather-widget div#widget-map")
    private WebElement cityMap;

    @FindBy(xpath = "//*[@id='weather-widget']//h3[@class='mobile-padding' and contains(text(),'Hourly forecast')]")
    private WebElement hourlyForecast;

    @FindBy(xpath = "//*[@id='weather-widget']//h3[contains(text(),'8-day forecast')]")
    private WebElement eightDayForecast;

    @FindBy(xpath = "//*[@id='weather-widget']//ul[@class='day-list']/li/span")
    private List<WebElement> dayList;

    @Step("Verify details screen")
    public SearchWeatherDetailsPage verifyWeatherDetailsScreen(String getNow, String searchValue, String temp) {
        isDisplayed(searchCityForm,30);
        isDisplayed(btnSearchCity,30);
        isDisplayed(cityMap,30);
        isDisplayed(hourlyForecast,30);
        isDisplayed(eightDayForecast,30);
        verifyForecastIncludes8Days();
        isDisplayed(currentTime,30);
        isDisplayed(currentCityName,30);
        isDisplayed(currentTemp,30);
        compareTimeWithCurrentTime(getNow);
        verifyTheDisplayedCityNameContainsSearchValue(searchValue);
        //Sometimes the returned temperature values are not the same between the 2 monitors.
        //If run using the command line, it always returns fail due to the difference between `C` characters.
        verifyTemperatureIsDisplayedCorrectly(temp);
        return this;
    }

    @Step("Compare time is now")
    public SearchWeatherDetailsPage compareTimeWithCurrentTime(String getNow) {
        String now = getNow.replace("AM", "am").replace("PM","pm");
        String getCurrentTime = currentTime.getText();
        assert (now.compareTo(getCurrentTime)-1) <=0;
        assert (now.compareTo(getCurrentTime)+1) >=0;
        return this;
    }

    @Step("Compare the displayed City Name with search value")
    public SearchWeatherDetailsPage verifyTheDisplayedCityNameContainsSearchValue(String searchValue) {
        String cityName = currentCityName.getText();
        searchValue = searchValue.replace(" ", "").toLowerCase();
        searchValue = searchValue.substring(0,1).toUpperCase()+searchValue.substring(1).toLowerCase();
        Assert.assertTrue(cityName.contains(searchValue));
        return this;
    }

    @Step("Compare the displayed Temperature is matched the returned Temperature in the previous search screen")
    public SearchWeatherDetailsPage verifyTemperatureIsDisplayedCorrectly(String temp) {
        String temperature = currentTemp.getText();
        Assert.assertEquals(temperature,temp.replace(" ", "").replace("С", "C"));
        return this;
    }

    @Step("Verify 8-day forecast section includes 8 data rows")
    public SearchWeatherDetailsPage verifyForecastIncludes8Days() {
        count = dayList.size();
        assert count == 8;
        return this;
    }
}
