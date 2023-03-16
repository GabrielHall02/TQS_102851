package tqs.lab4_3;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

import tqs.lab4_3.pages.ChooseFlightPage;
import tqs.lab4_3.pages.ConfirmationPage;
import tqs.lab4_3.pages.HomePage;
import tqs.lab4_3.pages.PurchasePage;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SeleniumJupiter.class)
public class BlazePageTest {

    private HomePage homePage;
    private ChooseFlightPage ChooseFlightPage;
    private PurchasePage purchasePage;
    private ConfirmationPage confirmationPage;
    private FirefoxDriver driver;

    @BeforeEach
    public void setUp(FirefoxDriver driver) {
        this.driver = driver;
        driver.get("https://blazedemo.com/");
        homePage = new HomePage(driver);
        ChooseFlightPage = new ChooseFlightPage(driver);
        purchasePage = new PurchasePage(driver);
        confirmationPage = new ConfirmationPage(driver);
    }

    @Test
    public void myfirsttest() {
        homePage.selectOrigin("Boston");
        homePage.selectDestination("New York");
        homePage.submit();
        ChooseFlightPage.chooseFlight();
        purchasePage.fillForm("Gabriel Abreu", "Rua 123", "Cidade", "Estado", "123", "123456789", "Nome");
        purchasePage.submit();
        assertThat(confirmationPage.get_confirmation_h1(), is("Thank you for your purchase today!"));
        assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
    }

}
