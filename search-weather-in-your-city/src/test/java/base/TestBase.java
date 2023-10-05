package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

public class TestBase {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    @Parameters({"browser", "isHeadless"})
    public void openBrowserByChrome(@Optional("chrome") String browser, @Optional("isHeadless") String isHeadless) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
            if (isHeadless.equals("true"))
                openHeadlessChrome();
        } else {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
            if (isHeadless.equals("true"))
                openHeadlessFirefox();
        }
        driver.get().manage().window().maximize();
    }

    public void openHeadlessChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver.set(new ChromeDriver(options));
    }

    public void openHeadlessFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver.set(new FirefoxDriver(options));
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
    }
}
