Feature: Verifying Get and Post Operation

  Scenario: Verifying Get Operation
    When I perform "GET" operation for "posts"
    Then I should be able to see title as "qui est esse"
    
   Scenario: Verift Post operation
   When I perform "Post" operation with body for "comments"
   Then I sould be able to get status code as "201"
