package tqs.lab4_3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class HomePage {
    private final WebDriver driver;

    @FindBy(name = "toPort")
    private WebElement toPort;

    @FindBy(name = "fromPort")
    private WebElement fromPort;

    @FindBy(css = ".btn-primary")
    private WebElement findFlights;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectDestination(String destination) {
        toPort.click();
        toPort.findElement(By.xpath("//option[. = '" + destination + "']")).click();
    }

    public void selectOrigin(String origin) {
        fromPort.click();
        fromPort.findElement(By.xpath("//option[. = '" + origin + "']")).click();
    }

    public void submit() {
        findFlights.click();
    }

}
