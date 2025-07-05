@test
Feature: Search a Hotel on MMT site

Scenario: Validate user is able to search for a hotel in Mumbai
  Given User is on "MMT" website
  And User close login modal
  And User selects "Hotels" from Menu options
  And User selects "Mumbai" in city field
  And User selects "Jul 29 2025" in "checkin" field for stay
  And User selects "Jul 30 2025" in "checkout" field for stay
  And User selects "2" in "Rooms" field for Rooms & Guests
  And User selects "3" in "Adults" field for Rooms & Guests
  And User selects "1" in "Child" field for Rooms & Guests
  And User clicks on apply button
  And User clicks on search button
