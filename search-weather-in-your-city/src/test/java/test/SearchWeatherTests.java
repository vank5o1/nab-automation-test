package test;

import base.Constants;
import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchWeatherPage;
import pages.SearchWeatherDetailsPage;
import utilities.Utils;

public class SearchWeatherTests extends TestBase {

    @Test(description ="Search weather in your city successfully")
    public void TC001_search_weather_in_your_city_successfully() {
        HomePage HomePage = new HomePage(super.driver);
        SearchWeatherPage SearchWeatherPage = new SearchWeatherPage(super.driver);
        SearchWeatherDetailsPage SearchWeatherDetailsPage = new SearchWeatherDetailsPage(super.driver);
        HomePage.openWebsite(Constants.OPEN_WEATHER_URl);
        HomePage.allowAllCookies()
                .searchWeatherOnHomePage(Constants.SEARCH_KEY);
        SearchWeatherPage.verifyWeatherInYourCityScreen(Constants.SEARCH_KEY)
                .verifyWeatherInYourCityHasResult()
                .verifyThe1stReturnedResult(Constants.SEARCH_KEY);
        String getTemp = SearchWeatherPage.getTheReturnedTemp();
        String getNow = Utils.getTimeNow("MMM dd, hh:mma");
        SearchWeatherPage.goTo1stResultDetails();
        SearchWeatherDetailsPage.verifyWeatherDetailsScreen(getNow,Constants.SEARCH_KEY,getTemp);
    }
}
