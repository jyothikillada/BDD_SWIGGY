#Author: jyothi.killada@gmail.com
Feature: Automate Swiggy Application

  @sanity
  Scenario Outline: Order Item in Swiggy
    Given User Launch Browser
    And open Swiggy "https://www.swiggy.com/"
    When Set location "Bengaluru"
    And Selects first location from suggested location in suggestion-box
    And Click Search
    And User Enters Item "<item>"
    And User Adds first suggested Item in cateogory to cart
    And User Clicks Add Item
    And User Clicks Cart
    Then User should be able to redirect Checkout page

    Examples: 
      | item     |
      | Birayani |
      | Pizaa    |
