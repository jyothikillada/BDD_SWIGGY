#Author: jyothi.killada@gmail.com
Feature: Automate Swiggy Application

  @sanity
  Scenario Outline: Order Item in Swiggy
    Given User Launch Browser
    And open Swiggy "https://www.swiggy.com/"
    When Set location "Bengaluru"
    And Selects first location from suggested location in suggestion-box
    And Click Search
    Then Search and select for an Item from excel row "<row_index>"
    And User should be able to redirect Checkout page
    
    Examples: 
     |row_index|
     |1|
     |2|
