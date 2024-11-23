import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;

public class SignUpTest {

    @Test
    public void signUp() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/en");

        driver.findElements(By.cssSelector("li[id='li_myaccount']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[contains(text(), ' Sign Up')]")).get(1).click();

        int randomNumber = (int) (Math.random()*1000);
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
        Assert.assertEquals(heading.getText(),"Hi, Anton Sheiko");
    }
}
