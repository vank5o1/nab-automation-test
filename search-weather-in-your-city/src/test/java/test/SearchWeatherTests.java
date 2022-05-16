package test;

import base.Constants;
import base.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchWeatherPage;
import pages.SearchWeatherDetailsPage;
import utilities.Utils;

public class SearchWeatherTests extends TestBase {
    private HomePage homePage;
    private SearchWeatherPage searchWeatherPage;
    private SearchWeatherDetailsPage searchWeatherDetailsPage;

    @BeforeMethod
    public void initializePage()  {
        homePage = new HomePage(super.getDriver());
        searchWeatherPage = new SearchWeatherPage(super.getDriver());
        searchWeatherDetailsPage = new SearchWeatherDetailsPage(super.getDriver());
    }

    @Test(description ="Search weather in your city successfully and view details screen")
    public void TC001_user_can_search_weather_in_your_city_successfully_and_view_details() {
        homePage.openWebsite(Constants.OPEN_WEATHER_URl);
        homePage.searchWeatherOnHomePage(Constants.SEARCH_KEY);
        searchWeatherPage.verifyWeatherInYourCityScreen(Constants.SEARCH_KEY)
                .verifyWeatherInYourCityHasResult()
                .verifyThe1stReturnedResult(Constants.SEARCH_KEY);
        String getTemp = searchWeatherPage.getTheReturnedTemp();
        String getNow = Utils.getTimeNow("MMM dd, hh:mma");
        searchWeatherPage.goToThe1stResultDetails();
        searchWeatherDetailsPage.verifyWeatherDetailsScreen(getNow,Constants.SEARCH_KEY,getTemp);
    }
}
