Feature: Verifying Get and Post Operation

  Scenario: Verifying Get Operation with PathParam
    When I perform "GET" operation for "posts" with path param key "value" and "2"
    Then I should be able to see title as "qui est esse"
    
   Scenario: Verifying Get Operation with Query Param
    When I perform "GET" operation for "posts" with QueryParm key "id" and value 2
 	Then I should be able to see ArrayList title as "qui est esse"
    
   Scenario: Verift Post operation
   When I perform "Post" operation with body for "comments"
   Then I sould be able to get status code as "201"
   
   Scenario: Check JSON to POJO conversion by Deserialization
   When  I perform "GET" operation for "comments" with path param key "value" and "2"
   And I should be able to covert JSON to POJO
   
   Scenario: Check POJO to JSON conversion by Searialization for post operation example 1
   When I perform "Post" operation for "comments" with POJO body
   |ID		|TITLE					|URL	|
   |9		|Sanyam's Family Photo	|www.google.com|
   Then I sould be able to get status code as "201"
   And should get expected body "album.json" as response
   
   #Scenario: Check POJO to JSON conversion by Searialization for post operation example 2
   #When I perform "Get" operation for "posts"
   #Then I sould be able to get status code as "200"
   #And should get expected body "posts.json" as response