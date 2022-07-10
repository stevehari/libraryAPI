Feature: validating ADD book and getting details and deleting it

@AddBook @regression
Scenario: verify whether the book is added perfectly
   
    Given add a book by giving payload with details name, isbn, aisle and author
    
    When user calls "addBook" with "POST" HTTP request
    
    Then the API call is success with status code 200
    
    And "Msg" in response body is "successfully added"


@DeleteBook  @regression
Scenario: verify whether the deleteplaceAPI is working perfectly

    Given Using the book ID delete the book
    When user calls "deleteBook" with "POST" HTTP request
    Then  "msg" in response body is "book is successfully deleted"
       


       
      

    
    

    
 
    


