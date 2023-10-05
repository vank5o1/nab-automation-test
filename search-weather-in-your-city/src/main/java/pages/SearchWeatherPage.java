package pages;

import base.PageObjectBase;
import com.epam.reportportal.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
public class SearchWeatherPage extends PageObjectBase {
    public SearchWeatherPage(WebDriver Idriver) {
        super(Idriver);
    }
    private int count;

    @FindBy(xpath = "//div[@class='container']//h2[contains(text(),'Weather in your city')]")
    private WebElement pageTitle;

    @FindBy(xpath="//*[@id='searchform']//input")
    private WebElement searchForm;

    @FindBy(xpath = "//*[@id='searchform']/button")
    private WebElement btnSearch;

    @FindBy(xpath = "//*[@id='forecast_list_ul']/table")
    private WebElement searchResultTable;

    @FindBy(tagName = "tr")
    private List<WebElement> searchResultItem;

    @FindBy(xpath = "//*[@id='forecast_list_ul']/div[@class='alert alert-warning' and contains(text(),'Not found')]")
    private WebElement notFound;

    @FindBy(xpath = "//div[@class='container']//h3[contains(text(),'Search engine is very flexible. How it works:')]")
    private WebElement guide;

    @FindBy(xpath = "//*[@id='forecast_list_ul']/table//a[starts-with(@href, '/city')]")
    private WebElement resultName;

    @FindBy(css = "#forecast_list_ul > table span.badge.badge-info")
    private WebElement resultTemperature;

    @FindBy(xpath = "//*[@id='forecast_list_ul']/table//p[contains(text(),'Geo coords')]")
    private WebElement resultGeoCoords;

    @Step("Verify Weather in your city screen")
    public SearchWeatherPage verifyWeatherInYourCityScreen(String searchValue) {
        isDisplayed(pageTitle,30);
        isDisplayed(searchForm,30);
        isDisplayed(btnSearch,30);
        Assert.assertEquals(searchForm.getAttribute("value"), searchValue);
        isDisplayed(guide,30);
        return this;
    }

    @Step("Verify Weather in your city has result")
    public SearchWeatherPage verifyWeatherInYourCityHasResult() {
        isDisplayed(searchResultTable,30);
        count = searchResultItem.size();
        assert count >0;
        return this;
    }

    @Step("Verify the 1st returned result")
    public SearchWeatherPage verifyThe1stReturnedResult(String searchValue) {
        isDisplayed(resultName,30);
        isDisplayed(resultTemperature,30);
        isDisplayed(resultGeoCoords,30);
        String cityName = getTheReturnedCityName();
        Assert.assertTrue(cityName.contains(searchValue));
        return this;
    }

    @Step("Go to details of the 1st result")
    public SearchWeatherPage goToThe1stResultDetails(){
        click(resultName);
        sleep(10);
        return this;
    }

    @Step("Get the returned City Name")
    public String getTheReturnedCityName() {
        return resultName.getText(); }

    @Step("Get the returned Temperate")
    public String getTheReturnedTemp() {
        return resultTemperature.getText(); }
}
