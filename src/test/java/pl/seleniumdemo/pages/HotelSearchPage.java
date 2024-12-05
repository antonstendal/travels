package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchHotelSpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchHotelInput;

    @FindBy(name = "checkin")
    private WebElement hotelCheckInInput;

    @FindBy(name = "checkout")
    private WebElement hotelCheckOutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusButton;

    @FindBy(id = "adultMinusBtn")
    private WebElement adultMinusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusButton;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    @FindBy(css = "li[id='li_myaccount']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[contains(text(), ' Sign Up')]")
    private List<WebElement> signUpLink;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void setCity(String cityName) {
        searchHotelSpan.click();
        searchHotelInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates(String checkIn, String checkOut) {
        hotelCheckInInput.sendKeys(checkIn);
        hotelCheckOutInput.sendKeys(checkOut);
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        addTraveller(adultPlusButton, adultsToAdd);
        addTraveller(childPlusButton, childToAdd);
        if (adultsToAdd <= 2) {
            for (int i = 2; i > adultsToAdd; i--) {
                adultMinusBtn.click();
            }
        }
    }

    public void addTraveller(WebElement travellerBtn, int numberOfTravers) {
        for (int i = 0; i < numberOfTravers; i++) {
            travellerBtn.click();
        }
    }

    public void performSearch() {
        searchButton.click();
    }

    public void openSignUpForm() {
        myAccountLink.stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        signUpLink.get(1).click();
    }
}
