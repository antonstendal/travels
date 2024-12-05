package pl.seleniumdemo.tests;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        int randomNumber = (int) (Math.random() * 1000);
        String uniqueEmail = String.valueOf("test" + randomNumber + "@gmail.com");

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Anton");
        signUpPage.setLastName("Sheiko");
        signUpPage.setTelNumber("1234567890");
        signUpPage.setEmail(uniqueEmail);
        signUpPage.setPassword("1234Test");
        signUpPage.setConfirmPassword("1234Test");
        signUpPage.signUp();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(("Sheiko")));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Anton Sheiko");


    }

    @Test
    public void signUpEmptyFieldsTest() {
        driver.findElements(By.cssSelector("li[id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[contains(text(), ' Sign Up')]")).get(1).click();
        driver.findElement(By.xpath("//button[contains(text(), ' Sign Up')]")).click();
        List<String> listAlert = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p")).stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(listAlert.get(0), "The Email field is required.");
        softAssert.assertTrue(listAlert.contains("The Email field is required."));
        softAssert.assertTrue(listAlert.contains("The Password field is required."));
        softAssert.assertTrue(listAlert.contains("The Password field is required."));
        softAssert.assertTrue(listAlert.contains("The First name field is required."));
        softAssert.assertTrue(listAlert.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {
        driver.findElements(By.cssSelector("li[id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[contains(text(), ' Sign Up')]")).get(1).click();


        driver.findElement(By.name("firstname")).sendKeys("Anton");
        driver.findElement(By.name("lastname")).sendKeys("Sheiko");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("email")).sendKeys("qwerty.gmail.com");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[contains(text(), ' Sign Up')]")).click();

        WebElement invalidEmail = driver.findElement(By.xpath("//div[@class='alert alert-danger']//p"));
        Assert.assertTrue(invalidEmail.getText().contains("The Email field must contain a valid email address."));
        Assert.assertEquals("The Email field must contain a valid email address.", invalidEmail.getText());
    }
}
