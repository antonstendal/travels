import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpTestHomework {

    @Test
    public void signUpEmptyFields() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/register");


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
}
