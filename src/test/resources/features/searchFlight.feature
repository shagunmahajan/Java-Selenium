@test
Feature: Search a flight on MMT site

Scenario: Validate user is able to search a flight from delhi to bangalore
  Given User is on "MMT" website
  And User close login modal
  And User selects "One Way" as trip type
  When User selects "Delhi" in from field
  And User selects "Bengaluru" in to field
  And User selects "Jul 29 2025" in "depart" field
  And User clicks on search button

