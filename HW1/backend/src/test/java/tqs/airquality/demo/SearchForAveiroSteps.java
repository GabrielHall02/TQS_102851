package tqs.airquality.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class SearchForAveiroSteps {
  private WebDriver driver;
  @Given("Home page")
    public void home_page() {
      WebDriverManager.firefoxdriver().setup();
      driver = new FirefoxDriver();
      driver.manage().window().setSize(new Dimension(1920, 1080));
      driver.get("http://localhost:3000");
    }

  @When("I fill {string} in country field")
  public void fill_country_field(String country) {
    driver.findElement(By.id("country-autocomplete")).click();
    driver.findElement(By.id("country-autocomplete")).sendKeys(country);
    driver.findElement(By.id("country-autocomplete-option-0")).click();
  }

  @And("I fill {string} in city field")
    public void fill_city_field(String city) {
        driver.findElement(By.id(":r5:")).click();
        driver.findElement(By.id(":r5:")).sendKeys(city);
    }

    @And("I click search button")
    public void click_search_button() {
        driver.findElement(By.cssSelector(".MuiButton-root")).click();
    }

    @Then("I should see a table with pollutants and values")
    public void see_table() {
      // Assertions here
      // Assert that driver can find MuiTable-root by css selector

      WebElement table = driver.findElement(By.cssSelector(".MuiTable-root"));
        // Assert that table is not null
      assertThat(table).isNotNull();

    }

  @AfterAll
  public void tearDown() {
    driver.quit();
  }
}
