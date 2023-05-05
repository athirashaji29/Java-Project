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


![jav 3](https://user-images.githubusercontent.com/118505212/236127021-67d06569-e11b-4538-a9c0-3c22956f511b.png)
![jav2](https://user-images.githubusercontent.com/118505212/236127498-931519d0-ee5d-45d0-9d9d-a5ed0c601f0e.png)

