Feature: validating ADD book and getting details
@AddBook @regression
Scenario: verify whether the book is added perfectly
   
    Given add a book by giving payload with details name, isbn, aisle and author
    
    When user calls "addBook" with "POST" HTTP request
    
    Then the API call is success with status code 200
    
    And "Msg" in response body is "successfully added"
    
   
    
    
   

       
      

    
    

    
 
    


