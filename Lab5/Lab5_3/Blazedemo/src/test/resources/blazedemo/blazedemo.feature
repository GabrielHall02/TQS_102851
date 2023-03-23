Feature: Book a flight

  Scenario: Book a flight from Paris to New York
    Given Blazedemo home page
    When I select the flights from "Paris" to "New York"
    And I click on the find flights button
    And I select the flight number 2
    And I fill in the passenger Name with "Gabriel Abreu"
    And I fill in the address with "Rua 123"
    And I fill in the city with "Cidade"
    And I fill in the state with "Estado"
    And I fill in the zip code with "123"
    And I fill in the credit card number with "123456789"
    And I fill in the credit card name with "Nome"
    And I click Purchase Flight button
    Then I should see the confirmation page with the header "Thank you for your purchase today!"
    Then The title of the page should be "BlazeDemo Confirmation"
