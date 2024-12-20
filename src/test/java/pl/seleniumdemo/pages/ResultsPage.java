package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage {

    @FindBy(xpath = "//h4//b")
    private List<WebElement> hotelList;

    @FindBy(css = "div[class='itemscontainer']")
    public WebElement resultHeading;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<String> getHotelNames() {
        return hotelList.stream()
                .map(element -> element.getAttribute("textContent"))
                .collect(Collectors.toList());

    }

    public String getHeadingText() {
        return resultHeading.getText();
    }
}
