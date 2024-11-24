import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {


        driver.findElements(By.cssSelector("li[id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[contains(text(), ' Sign Up')]")).get(1).click();

        int randomNumber = (int) (Math.random() * 1000);
        String uniqueEmail = String.valueOf(randomNumber);

        String lastName = "Sheiko";
        driver.findElement(By.name("firstname")).sendKeys("Anton");
        driver.findElement(By.name("lastname")).sendKeys("Sheiko");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("email")).sendKeys("qwerty" + uniqueEmail + "@gmail.com");
        driver.findElement(By.name("password")).sendKeys("test123");
        driver.findElement(By.name("confirmpassword")).sendKeys("test123");
        driver.findElement(By.xpath("//button[contains(text(), ' Sign Up')]")).click();

        WebElement heading = driver.findElement(By.cssSelector("h3[class='RTL']"));
        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Anton Sheiko");
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
