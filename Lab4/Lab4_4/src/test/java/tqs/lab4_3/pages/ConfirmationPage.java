package tqs.lab4_3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ConfirmationPage {

    private final WebDriver driver;

    @FindBy(xpath = "/html/body/div[2]/div/h1")
    private WebElement confirmationHeader;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTabTitle() {
        return driver.getTitle();
    }

    public String get_confirmation_h1()
    {
        return confirmationHeader.getText();
    }


}
