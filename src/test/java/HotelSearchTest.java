import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {
        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match']")).click();
        driver.findElement(By.name("checkin")).sendKeys("17/04/2024");
        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();

        driver.findElement(By.xpath("//button[text()=' Search']")).click();
        List<String> hotelNames = driver.findElements(By.xpath("//h4//b"))
                .stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

        hotelNames.forEach(System.out::println);
        
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));
    }

    @Test
    public void hotelSearchWithoutCityCountryTest() {
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
