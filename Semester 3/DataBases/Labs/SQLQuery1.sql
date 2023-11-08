CREATE DATABASE Circ;
GO

USE Circ;

CREATE TABLE Performers(
PerformerID INT PRIMARY KEY,
FirstName VARCHAR(50),
LastName VARCHAR(50),
DateOfBirth DATE,
Specialty VARCHAR(50)
);

CREATE TABLE Acts(
ActID INT PRIMARY KEY,
ActName VARCHAR(50),
ActType VARCHAR(50),
PerformerID INT,
FOREIGN KEY (PerformerID) REFERENCES Performers(PerformerID)
);

CREATE TABLE Animals(
AnimalID INT PRIMARY KEY,
AnimalName VARCHAR(50),
AnimalSPecie VARCHAR(50),
AnimalAge INT,
);

CREATE TABLE AnimalFood(
FoodID INT PRIMARY KEY,
FoodName VARCHAR(50),
FoodType VARCHAR(50),
FoodQuantity VARCHAR(50),
FoodCost INT,
AnimalID INT,
FOREIGN KEY (AnimalID) REFERENCES Animals(AnimalID)
);

CREATE TABLE AnimalCare(
CareID INT PRIMARY KEY,
CareDate DATE,
CareType VARCHAR(100),
CareCost INT,
AnimalID INT,
FOREIGN KEY(AnimalID) REFERENCES Animals(AnimalID)
);

CREATE TABLE Shows(
ShowID INT PRIMARY KEY,
ShowDate DATE,
ShowTime TIME,
TicketSales INT,
ActID INT,
FOREIGN KEY (ActID) REFERENCES Acts(ActID)
);

CREATE TABLE Expenses(
ExpenseID INT PRIMARY KEY,
ExpenseCategory VARCHAR(60),
ExpenseAmount INT,
ShowID INT,
FOREIGN KEY(ShowID) REFERENCES Shows(ShowID)
);

CREATE TABLE Tickets(
TicketID INT PRIMARY KEY,
SeatNumber VARCHAR(15),
PurchaseDate DATE,
CustomerName VARCHAR(50),
CustomerEmail VARCHAR(100),
TicketPrice DECIMAL(10,2),
ShowID INT,
FOREIGN KEY(ShowID) REFERENCES Shows(ShowID)
);

CREATE TABLE Feedback(
FeedBackID INT PRIMARY KEY,
CustomerName VARCHAR(50),
FeedBackText TEXT,
FeedBackRating DECIMAL (3,2),
ShowID INT,
FOREIGN KEY(ShowID) REFERENCES Shows(ShowID)
);

CREATE TABLE Locations(
LocationID INT PRIMARY KEY,
LocName VARCHAR(100),
LocCapacity INT,
LocAdress VARCHAR(100),
ContactPhone INT,
);

CREATE TABLE ShowLocation(
ShowID INT,
LocationID INT,
PRIMARY KEY(ShowID,LocationID),
FOREIGN KEY(ShowID) REFERENCES Shows(ShowID),
FOREIGN KEY(LocationID) REFERENCES Locations(LocationID)
);


