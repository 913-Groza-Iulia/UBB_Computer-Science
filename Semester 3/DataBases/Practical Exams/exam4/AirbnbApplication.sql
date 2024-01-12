CREATE DATABASE Airbnb

USE Airbnb
GO

CREATE TABLE Customers(
  CustomerID INT PRIMARY KEY NOT NULL,
  UserName VARCHAR(90) UNIQUE,
  Nationality VARCHAR(90),
  DateOfBirth DATE
);

CREATE TABLE EmailAddresses(
    EmailID INT PRIMARY KEY NOT NULL,
    CustomerID INT,
    Email VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE
);

CREATE TABLE Properties(
   PropertyID INT PRIMARY KEY NOT NULL,
   PropertyName VARCHAR(100),
   PropDescription VARCHAR(200),
   PropAddress VARCHAR(100),
   CheckIn TIME,
   CheckOut TIME,
   NumberOfPpl INT,
   PricePerNight DECIMAL(10,2),
   FreeCancellation INT
);

CREATE TABLE Bookings(
   BookingID INT PRIMARY KEY NOT NULL,
   CustomerID INT,
   PropertyID INT,
   StartDate DATE,
   EndDate DATE,
   FOREIGN KEY(CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE,
   FOREIGN KEY(PropertyID) REFERENCES Properties(PropertyID) ON DELETE CASCADE
);

CREATE TABLE Payments(
  PaymentID INT PRIMARY KEY NOT NULL,
  Amount DECIMAL(10,2),
  DateOfPayment DATE,
  Type VARCHAR(100),
  BookingID INT,
  FOREIGN KEY(BookingID) REFERENCES Bookings(BookingID) ON DELETE CASCADE
);

INSERT INTO Customers (CustomerID, UserName, Nationality, DateOfBirth) VALUES
(1, 'john_doe', 'US', '1985-05-20'),
(2, 'alice_smith', 'UK', '1990-02-15'),
(3, 'bob_jones', 'CA', '1988-08-10'),
(4, 'emily_white', 'AU', '1995-03-25'),
(5, 'michael_brown', 'DE', '1982-11-08'),
(6, 'olivia_davis', 'FR', '1993-06-12');
SELECT * FROM Customers

INSERT INTO EmailAddresses (EmailID, CustomerID, Email) VALUES
(1, 1, 'john_doe@example.com'),
(2, 2, 'alice_smith@gmail.com'),
(3, 3, 'bob_jones@yahoo.com'),
(4, 4, 'emily_white@hotmail.com'),
(5, 5, 'michael_brown@gmail.com'),
(6, 6, 'olivia_davis@example.com');
SELECT * FROM EmailAddresses

INSERT INTO Properties (PropertyID, PropertyName, PropDescription, PropAddress, CheckIn, CheckOut, NumberOfPpl, PricePerNight, FreeCancellation) VALUES
(1, 'Cozy Cottage', 'A charming cottage in the countryside', '123 Main St, City A', '15:00', '11:00', 4, 75.00, 1),
(2, 'City View Apartment', 'Modern apartment with a great city view', '456 Oak St, City B', '14:00', '10:00', 2, 120.00, 0),
(3, 'Beach House', 'Beautiful beachfront property', '789 Beach Rd, City C', '16:00', '12:00', 6, 150.00, 1),
(4, 'Mountain Retreat', 'Secluded retreat in the mountains', '321 Pine Ln, City D', '17:00', '13:00', 8, 200.00, 0),
(5, 'Luxury Villa', 'Exclusive villa with private pool', '567 Sunset Blvd, City E', '15:30', '11:30', 10, 300.00, 1),
(6, 'Downtown Loft', 'Stylish loft in the heart of the city', '987 Market St, City F', '13:00', '09:00', 3, 90.00, 1);
SELECT * FROM Properties

INSERT INTO Bookings (BookingID, CustomerID, PropertyID, StartDate, EndDate) VALUES
(1, 1, 2, '2022-01-15', '2022-01-20'),
(2, 2, 3, '2022-02-10', '2022-02-15'),
(3, 3, 1, '2022-03-05', '2022-03-10'),
(4, 4, 4, '2022-04-20', '2022-04-25'),
(5, 5, 5, '2022-05-15', '2022-05-20'),
(6, 6, 6, '2022-06-01', '2022-06-05'),
(7, 3, 4, '2022-07-18', '2022-07-30');
SELECT * FROM Bookings

INSERT INTO Payments (PaymentID, Amount, DateOfPayment, Type, BookingID) VALUES
(1, 100.00, '2022-01-18', 'Credit Card', 1),
(2, 80.00, '2022-02-12', 'PayPal', 2),
(3, 60.00, '2022-03-08', 'Credit Card', 3),
(4, 150.00, '2022-04-22', 'Credit Card', 4),
(5, 200.00, '2022-05-18', 'PayPal', 5),
(6, 70.00, '2022-06-03', 'Credit Card', 6);
SELECT * FROM Payments

GO
-- implement a stored procedure that receives the details of one payment and field(s) that identify a booking, and adds the payment to the corresponding booking only if the total amount of the existing payments does not exceed the total price of the booking.
CREATE OR ALTER PROCEDURE addPaymentToBooking 
  @paymentID INT,
  @amount DECIMAL(10,2),
  @date DATE,
  @type VARCHAR(100),
  @bookingID INT
AS
   DECLARE @totalAmount DECIMAL(10,2)
   DECLARE @totalPriceOfBooking DECIMAL(10,2)
   SET @totalAmount=(SELECT ISNULL(SUM(Amount),0) FROM Payments WHERE BookingID=@bookingID)
   SET @totalPriceOfBooking=(SELECT P.PricePerNight*DATEDIFF(day,B.StartDate,B.EndDate) FROM Properties P JOIN Bookings B ON P.PropertyID=B.PropertyID WHERE B.BookingID=@bookingID)
   IF (@totalAmount <= @totalPriceOfBooking) BEGIN
       INSERT INTO Payments(PaymentID,Amount,DateOfPayment,Type,BookingID) VALUES (@paymentID,@amount,@date,@type,@bookingID)
   END
GO

EXEC addPaymentToBooking 8,100.00,'2022-07-17','Paypal',7
SELECT * FROM Payments

-- create a view that shows the names of the customers that have the maximum number of bookings.
CREATE OR ALTER VIEW customerThatHasTheMaxBookings AS
  SELECT TOP 1 C.UserName, COUNT(B.BookingID) AS NumberOfBookings
  FROM Customers C
  INNER JOIN Bookings B ON C.CustomerID=B.CustomerID
  GROUP BY C.UserName
  ORDER BY NumberOfBookings DESC 
GO

SELECT * FROM customerThatHasTheMaxBookings

-- implement a function that returns the number of customers that have less than R bookings paid with PayPal, where R is a function parameter.
CREATE FUNCTION customersPayingWithPayPal(@R INT)
RETURNS INT
AS
BEGIN
     DECLARE @customersCount INT
    SET @customersCount=(SELECT COUNT(C.CustomerID) AS NumOfCustomers
	                     FROM Customers C   
						 JOIN Bookings B ON C.CustomerID=B.CustomerID
						 JOIN Payments P ON B.BookingID=P.BookingID
						 WHERE P.Type LIKE 'PayPal'
						 GROUP BY C.CustomerID
						 HAVING COUNT(B.BookingID) < @R)
	RETURN @customersCount
END   

SELECT customersPayingWithPayPal(2) AS CustomersCount