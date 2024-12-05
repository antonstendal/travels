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

        String lastName = "Sheiko";
        int randomNumber = (int) (Math.random() * 10000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)

                .openSignUpForm()
                .setFirstName("Anton")
                .setLastName("Sheiko")
                .setTelNumber("1234567890")
                .setEmail("tester" + randomNumber + "@tester.pl")
                .setPassword("test123")
                .setConfirmPassword("test123")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Anton Sheiko");
    }


    @Test
    public void signUpEmptyFieldsTest() {

        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.signUp();

        List<String> listAlert = signUpPage.getErrors();

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
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Anton")
                .setLastName("Sheiko")
                .setTelNumber("1234567890")
                .setEmail("email")
                .setPassword("test123")
                .setConfirmPassword("test123");
        signUpPage.signUp();

        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
}
