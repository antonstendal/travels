package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() throws IOException {
        int randomNumber = (int) (Math.random() * 1000);
        String uniqueEmail = String.valueOf("test" + randomNumber + "@gmail.com");

        ExtentTest extentTest = extentReports.createTest("Sign up test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Anton");
        extentTest.log(Status.PASS, "Setting name done", SeleniumHelper.getScreenshot(driver));
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

        extentReports.createTest("Sign Up Empty Fields Test");
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
        extentReports.createTest("Sign Up Invalid Email Test");
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
