Feature: Get Aveiro Air Pollution

  Scenario: Get Aveiro Air Pollution
    Given Home page
    When I fill "Portugal" in country field
    And I fill "Aveiro" in city field
    And I click search button
    Then I should see a table with pollutants and values