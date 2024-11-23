import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomeWorkSearchDateCheckIn {

    @Test
    public void hotelSearchWithoutCityCountry() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/en");

        //find element check in
        WebElement datesCheckIn = driver.findElement(By.cssSelector("input[name='checkin']"));
        datesCheckIn.click();
        driver.findElements(By.xpath("//td[contains(text(),'25')]")).getFirst().click();

        driver.findElement(By.cssSelector("input[name='checkout']"));
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.name("travellers")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement searchResults = driver.findElement(By.cssSelector("div[class='itemscontainer']"));


        Assert.assertEquals(searchResults.getText(), "No Results Found");
        Assert.assertTrue(searchResults.isDisplayed());
    }

}
