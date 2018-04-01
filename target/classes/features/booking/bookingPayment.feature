@booking @payment @smoke @regression @end2end
Feature: Booking Payment
  @declined @negative
  Scenario: Booking up to a Decline payment on
    Given I make a booking from DUB to MAD on 14/09/2018 for 2 adults and 1 child
    When I pay for booking with card details 5555 5555 5555 5557, 10/2018 and 265
    Then I should get payment declined message
