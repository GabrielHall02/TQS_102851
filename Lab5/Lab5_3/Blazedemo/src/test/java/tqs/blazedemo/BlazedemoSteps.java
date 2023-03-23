package tqs.blazedemo;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BlazedemoSteps {
    private WebDriver driver;

    @Given("Blazedemo home page")
    public void blazedemo_home_page() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("https://blazedemo.com/");
    }

    @When("I select the flights from {string} to {string}")
    public void i_select_the_flights_from_to(String from, String to) {
        driver.findElement(By.name("fromPort")).click();
        WebElement fromDropdown = driver.findElement(By.name("fromPort"));
        fromDropdown.findElement(By.xpath("//option[. = '" + from + "']")).click();
        driver.findElement(By.name("toPort")).click();
        WebElement dropdown = driver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = '" + to + "']")).click();
    }

    @And("I click on the find flights button")
    public void i_click_on_the_find_flights_button() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I select the flight number {int}")
    public void i_select_the_flight_number(Integer flightNumber) {
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+flightNumber+"]/td[1]/input"));
        element.click();
    }

    // And I fill in the passenger Name with "Gabriel Abreu"
    @And("I fill in the passenger Name with {string}")
    public void i_fill_in_the_passenger_Name_with(String name) {
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys(name);
    }
    // And I fill in the address with "Rua 123"
    @And("I fill in the address with {string}")
    public void i_fill_in_the_address_with(String address){
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys(address);
    }
    // And I fill in the city with "Cidade"
    @And("I fill in the city with {string}")
    public void i_fill_in_the_city_with(String city){
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys(city);
    }
    // And I fill in the state with "Estado"
    @And("I fill in the state with {string}")
    public void i_fill_in_the_state_with(String state){
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys(state);
    }
    // And I fill in the zip code with "123"
    @And("I fill in the zip code with {string}")
    public void i_fill_in_the_zip_code_with(String zipCode){
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys(zipCode);
    }
    // And I fill in the credit card number with "123456789"
    @And("I fill in the credit card number with {string}")
    public void i_fill_in_the_credit_card_number_with(String creditCardNumber){
        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber);
    }
    // And I fill in the credit card name with "Nome"
    @And("I fill in the credit card name with {string}")
    public void i_fill_in_the_credit_card_name_with(String creditCardName){
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys(creditCardName);
    }
    // And I click Purchase Flight button
    @And("I click Purchase Flight button")
    public void i_click_Purchase_Flight_button(){
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
    // Then I should see the confirmation page
    @Then("I should see the confirmation page with the header {string}")
    public void i_should_see_the_confirmation_page(String header){
        WebElement element = driver.findElement(By.xpath("/html/body/div[2]/div/h1"));
        assertThat(element.getText()).isEqualTo(header);
    }
    // Then The title of the page should be "BlazeDemo Confirmation"
    @Then("The title of the page should be {string}")
    public void the_title_of_the_page_should_be(String title){
        assertThat(driver.getTitle()).isEqualTo(title);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}
