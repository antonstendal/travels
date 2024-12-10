package pl.seleniumdemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException {
        driver = DriverFactory.getDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/en");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
