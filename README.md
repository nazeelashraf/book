# :books: Book
A Spring RESTful web service for CRUD operations on books and authors.

## Setup
1. Clone the repo.
2. Configure application.properties to connect to preferred database.
3. Configure Lombok for your IDE.
4. Run the application.
5. Use postman/curl to access endpoints.

## Endpoints:
1. Book:
  * GET ```http://localhost:8765/books``` : gets all books
  * GET ```http://localhost:8765/books/{id}``` : gets book with id ```id```
  * POST ```http://localhost:8765/books``` : adds a new book (see ```com.library.book.model.Book```) and author (if authorId or author fields have been defined)
  * PUT ```http://localhost:8765/books``` : updates a book if it exists, otherwise adds a new one
  * GET ```http://localhost:8765/search/books/{search}``` : search with search query ```search``` which can also have multiple queries separated by ```;``` (like ```harry;rothfuss```)
2. Author:
  * GET ```http://localhost:8765/authors``` : gets all authors
  * GET ```http://localhost:8765/authors/{id}``` : gets author with id ```id```
  * POST ```http://localhost:8765/authors``` : add an author (see ```com.library.book.model.Author```) and (optionally) a list of books.
  
## Task List:
  - [X] CRUD Operations
  - [ ] Login and signup endpoints
  - [ ] Token based (JWT) authentication
