# bookhub-server
## Description of the project

Book-hub is an app where the users can rate books. All the books are being provided by Google Books API, so the variety of those is quite extense. In addition, the user can save the book he/she likes under 3 different categories: READ (he has already read it), TBR (is planning on read it on the future) and READING (is currently reading it). When the last category is selected, the user must provided the page he/she is on, sho we can track his/her progress.

## User Stories(If available)

As a user I want to search for books so I can save/read them. 

As a user I want to see other people opinion about the book so, I can get a better idea of how is it. 

As a user I want to rate a book, actually just for sharing.

As a user I want to be able to save books, so i can look at them later.

## Technologies Used

The proyect was done in Java using Springboots. 
For the database, MySQL was used.

## Models
Our proyect has 4 models: BOOK, USER, RATING AND USERBOOK
Even though, you will see a class diagram below there are some things I would like to explain. 

The class USERBOOK represents in reality a many to many relationship between books and users. Because it is depending of two attributes Status and PagesRead it was split into a table 
with a realation one to many with Book and User. 

![bookHub drawio](https://user-images.githubusercontent.com/90968486/174147100-10123063-3642-418d-ab18-b5cb47058e61.png)

## Server routes table(Method, Route or URL, Description as columns)



## Future Work
In a future we will improve the code quality.
## Resources
TRELLO: https://trello.com/invite/b/gEJ2ohgh/b35d9deae2e779a8b9157d5887937e50/bookhub
