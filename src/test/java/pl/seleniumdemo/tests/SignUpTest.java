package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

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
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();


        List<String> listAlert = signUpPage.getErrors();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(listAlert.contains("The Email field is required."));
        softAssert.assertTrue(listAlert.contains("The Email field is required."));
        softAssert.assertTrue(listAlert.contains("The Password field is required."));
        softAssert.assertTrue(listAlert.contains("The Password field is required."));
        softAssert.assertTrue(listAlert.contains("The First name field is required."));
        softAssert.assertTrue(listAlert.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        SignUpPage signUpPage = new SignUpPage(driver);

        signUpPage.setFirstName("Anton");
        signUpPage.setLastName("Sheiko");
        signUpPage.setTelNumber("1234123400");
        signUpPage.setEmail("email");
        signUpPage.setPassword("Test1234");
        signUpPage.setConfirmPassword("Test1234");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
}
