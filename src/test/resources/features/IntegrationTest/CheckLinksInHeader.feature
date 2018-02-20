@Int
Feature: check all the links in the header work fine

  @id_check_links
  Scenario: click all the links in the header jump to the correct url and pages

    Then I click "WHY OUTDOOR" on the header
    Then I should be on the why outdoor page
    When I click "SEARCH" on the header
    Then I should be on the search page
    When I click "CONTACT US" on the header
    Then I should be on the contact page