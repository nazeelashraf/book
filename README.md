# :books: Book
A Spring RESTful web service for CRUD operations on books and authors.

## Setup
1. Clone the repo.
2. Configure application.properties to connect to preferred database.
3. Configure Lombok for your IDE.
4. Run the application.
5. Use postman/curl to access endpoints.

## Signing up/Logging in
1. Use the following endpoint to sign-up: POST ```http://localhost:8765/users/sign-up``` to sign up with the following request body
   ```
      {
         "username": "jack",
         "password": "sparrow"
      }
   ```
2. Login with these credentials using this endpoint: POST ```http://localhost:8765/login``` with the same request body as above to log in. The jwt token will reside in the ```Authorization``` header of the response.

```Note: For admin token, use 'admin'/'admin' to log in.```

## Endpoints:
Before using any of the below endpoints, make sure a valid jwt token is set in the ```Authorization``` header.
1. Book:
  * GET ```http://localhost:8765/books``` : gets all books ```(Admin or User)```
  * GET ```http://localhost:8765/books/{id}``` : gets book with id ```id``` ```(Admin or User)```
  * POST ```http://localhost:8765/books``` : adds a new book (see ```com.library.book.model.Book```) and author (if authorId or author fields have been defined)```(Admin only)```
  * PATCH ```http://localhost:8765/books/{id}``` : updates the author of the book with id ```id```. ```(Admin only)```
  * PUT ```http://localhost:8765/books``` : updates a book if it exists, otherwise adds a new one ```(Admin only)```
  * GET ```http://localhost:8765/search/books/{search}``` : search with search query ```search``` which can also have multiple queries separated by ```;``` (like ```harry;rothfuss```) ```(Admin or User)```
  * DELETE ```http://localhost:8765/books/{id}``` : deletes a book with id ```id```. ```(Admin only)```
2. Author:
  * GET ```http://localhost:8765/authors``` : gets all authors ```(Admin or User)```
  * GET ```http://localhost:8765/authors/{id}``` : gets author with id ```id``` ```(Admin or User)```
  * POST ```http://localhost:8765/authors``` : add an author (see ```com.library.book.model.Author```) and (optionally) a list of books. ```(Admin or User)```
  
## Task List:
  - [X] CRUD Operations
  - [X] Login and signup endpoints
  - [X] Token based (JWT) authentication
