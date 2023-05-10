# MovieMaven
## Movie Ticket Reservation System
### Features
A user can login or signup to the page where they can book ticket,see ticket,see information regarding running movies etc...

## DATABASE TABLES 
### Table 'movie':

The table stores information about movies.
It has columns for column id,movie name, format, show date, show time, price, and seat count where column id is the primary key.
The 'columnid' column is the primary key for this table and is set to auto-increment.

![Screenshot 2023-05-04 005034](https://user-images.githubusercontent.com/118505120/236133351-bbf131f1-8ad8-4769-9799-7719e7127b61.png)
![Screenshot 2023-05-04 005005](https://user-images.githubusercontent.com/118505120/236134266-5e733a7f-bee8-46d0-83eb-e64b771bc41b.png)


## Table 'user':

The user table stores information about the user.
It has columns for user_id,name and password.Here the user_id is set as the primary key which is unique for each user.

![Screenshot 2023-05-04 001643](https://user-images.githubusercontent.com/118505120/236134645-ae40cbf1-6310-4c37-86cf-02e762bdf166.png)

![Screenshot 2023-05-04 001604](https://user-images.githubusercontent.com/118505120/236134811-71619624-eab8-4d80-84e5-b25f9a4c8e77.png)

## Table 'customer'

The table stores information about customers who have reserved tickets for a movie.
It has columns for movie 'columnid', movie name, format, show date, show time, price, seat count, customer ID, and user ID.
The 'Cid' column is the primary key for this table and is set to auto-increment.
The 'columnid' column in this table is a foreign key that references the 'columnid' column in the 'movie' table, and is set to cascade delete and update.
The 'user_id' column in this table is a foreign key that references the 'user_id' column in the 'user' table, and is set to cascade delete and update.

![Screenshot 2023-05-04 001503](https://user-images.githubusercontent.com/118505120/236134946-1e1627fb-7d20-4354-88b3-8d10b47c9d58.png)
![Screenshot 2023-05-04 001010](https://user-images.githubusercontent.com/118505120/236135028-c02c3402-4702-445e-8a90-0979c22379f4.png)



## Code
### Implementation of login/signup:
![Screenshot 2023-05-04 104828](https://user-images.githubusercontent.com/118505694/236427275-6e79e1d7-df43-45e4-981d-ac663f821a8c.png)

It allows a user to either log in with their existing username and password or sign up with a new username and password.
First, the user is prompted to input their choice of action - login or signup. If the user chooses to log in, the program prompts the user to enter their existing username and password. 

If the login is successful, the program prints a success message and proceeds with the process function. If the login fails, the program prints an error message and resets the num variable to 0 to allow the user to try again.

 If the signup is successful, the program prints a success message and proceeds with the process function. If the signup fails, the program prints an error message and resets the num variable to 0 to allow the user to try again.




### There are two panals
#### 1.Customer Panal
#### 2.Owner Panal
![jav2](https://user-images.githubusercontent.com/118505694/236423559-b032b134-c7d3-4b71-a9c5-f3957ec2e6ba.png)


##### 1.customer panal
In the Customer Panal, there is a list of operations a customer can perform-

###### 1.Book Ticket
###### 2.Show my Ticket
###### 3.Cancel Ticket
###### 4.Check Seat
###### 5.Show Movie List
###### 6.Back
###### 7.Exit
![jav 3](https://user-images.githubusercontent.com/118505694/236423885-d383020a-078a-4158-a04a-7d89084a8186.png)

###### 1. Book Ticket
The code first calls the "ShowMovieList" method to display the list of available movies and their show timings.
The user is then prompted to enter the ID of the movie they want to watch. If the user enters 0, the method exits. If the entered ID is valid, the user is prompted to enter their name and the number of seats they want to book. The method then checks if there are enough seats available for the movie and if not, the booking is cancelled.
![Screenshot 2023-05-04 105611](https://user-images.githubusercontent.com/118505694/236427012-d6513974-63f9-422c-837e-2392d3d0bfac.png)


If there are enough seats available, the method inserts a new record into the "customer" table in the database with the movie ID, movie name, show format, date, time, price, number of seats, and customer name. It then updates the "seat" field in the "movie" table to reflect the remaining available seats. If the booking and updating are successful, the method displays a success message and shows the user their ticket details.

If any error occurs during the booking process, the method displays an error message and exits.

###### 2.Show my Ticket
The code is a Java method that shows a movie ticket for a customer identified by their unique ID number. The method retrieves the ticket information from a SQL database table called "customer" using a prepared statement with the customer ID parameter. If the customer exists, the ticket information is printed to the console and also written to a text file with the customer's name as the file name. The ticket information includes the movie name, type, date, time, seat(s), price, and unique ID. If the customer does not exist in the database, a message is displayed indicating that no booking is available. The method handles any exceptions that might occur during the process.
![jav7](https://user-images.githubusercontent.com/118505694/236426763-04257a99-5558-48df-9d21-d0496e3b3935.png)


###### 3.Cancel Ticket
This method cancels a previously booked movie ticket by taking a unique ID as input. It fetches the corresponding seat and movie details from the database, allows the user to confirm the cancellation, updates the movie seat availability, and deletes the ticket information from the customer table.

###### 4.Check Seat
This method displays the available seats for all movies in the database. It fetches the movie details from the database and prints them in a tabular format.

###### 5.Show Movie List
This function displays a list of all the movies in the database, along with their details like ID, name, format, show date and time, price, and available seats. It returns the number of movies in the list.
![jav5](https://user-images.githubusercontent.com/118505694/236424285-7a389a38-b704-4079-9515-f5043abbd058.png)


##### 2.Owner Panal
The Owner Panal has a login page so that it can be accessed only by owner

Username - anamika
Password - password

After successfully login, there is shown a list of operations that the Owner can perform-
![image](https://github.com/athirashaji29/Java-Project/assets/118505694/bef0082b-bcc9-4e49-b720-6ff41b9396fc)


###### 1.Add New Movie
###### 2.Delete Movie
###### 3.Show Movie List
###### 4.See Total Bookings
###### 5.Generate movie booking report 
###### 6.back
###### 0.Exit

###### 1.Add New Movie
This function adds a new movie to the database. It takes input from the owner for movie name, format, show date, show time, price and available seats. It then parses the input and prepares an SQL statement to insert the movie data into the database. If the insertion is successful, it prints a success message, otherwise, it prints a failure message.
![image](https://github.com/athirashaji29/Java-Project/assets/118505694/4c06ba62-1d39-4077-b21b-6b37280432fe)


###### 2.Delete Movie
This function is used to delete a movie from the movie database. It takes the movie ID as input and displays the details of the movie. The user is prompted to confirm the deletion by pressing 'd'. If the user confirms, the movie is deleted from the database, otherwise the function exits. The function uses SQL queries to select and delete the movie. If an error occurs, an exception is caught and an error message is displayed.

###### 3.Show Movie List
This function retrieves all movies from the database and displays them in a formatted table with their details such as ID, name, format, show date, show time, price, and available seats. It returns the total number of movies displayed. If any exception occurs, it will be caught and a corresponding error message will be displayed.

###### 4.See Total Bookings
This function retrieves and displays all the booking details from the customer table, along with the associated movie details from the movie table. The method also returns the total number of bookings made. The output includes the customer's name, booking ID, number of seats booked, and the movie ID.

###### 5.Generate movie booking report
This fucntion will display nunber of booking for a perticular movie 
![Screenshot 2023-05-10 115649](https://github.com/athirashaji29/Java-Project/assets/118505694/4c403359-991d-406a-8d48-e666160ee404)





