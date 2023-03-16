package tqs.lab4_3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseFlightPage {


    @FindBy(css = "tr:nth-child(1) .btn")
    private WebElement chooseThisFlightButton;

    public ChooseFlightPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void chooseFlight() {
        chooseThisFlightButton.click();
    }
}
