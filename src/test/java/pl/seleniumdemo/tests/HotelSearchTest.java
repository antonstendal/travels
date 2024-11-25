package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("25/11/2024","27/11/2024");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

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
