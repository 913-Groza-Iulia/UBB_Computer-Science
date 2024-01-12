CREATE DATABASE ConfectioneryStore

USE ConfectioneryStore
GO

CREATE TABLE Cakes(
   CakeID INT PRIMARY KEY NOT NULL,
   CakeName VARCHAR(90),
   Shape VARCHAR(50),
   CakeWeight INT,
   CakePrice DECIMAL(10,2),
   TypeID INT,
   FOREIGN KEY(TypeID) REFERENCES CakeTypes(TypeID) ON DELETE CASCADE 
);

CREATE TABLE Orders(
   OrderID INT PRIMARY KEY NOT NULL,
   OrderDate DATE
);

CREATE TABLE CakeTypes(
   TypeID INT PRIMARY KEY NOT NULL,
   TypeName VARCHAR(90),
   TypeDescription VARCHAR(130),
);

CREATE TABLE Chefs(
   ChefID INT PRIMARY KEY NOT NULL,
   ChefName VARCHAR(80),
   Gender VARCHAR(10),
   DateOfBirth DATE
);

CREATE TABLE CakeOrders(
   CakeID INT,
   OrderID INT,
   PRIMARY KEY(CakeID, OrderID),
   OrderedPieces INT,
   FOREIGN KEY(CakeID) REFERENCES Cakes(CakeID) ON DELETE CASCADE,
   FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) ON DELETE CASCADE
);

CREATE TABLE CakeChefs(
  CakeID INT,
  ChefID INT,
  PRIMARY KEY(CakeID, ChefID),
  FOREIGN KEY(CakeID) REFERENCES Cakes(CakeID) ON DELETE CASCADE,
  FOREIGN KEY(ChefID) REFERENCES Chefs(ChefID) ON DELETE CASCADE
);

INSERT INTO CakeTypes (TypeID, TypeName, TypeDescription) VALUES
(1, 'Chocolate', 'Delicious chocolate cakes'),
(2, 'Fruit', 'Fresh and fruity cakes'),
(3, 'Cheesecake', 'Creamy cheesecakes'),
(4, 'Red Velvet', 'Classic red velvet cakes'),
(5, 'Vanilla', 'Simple and classic vanilla cakes'),
(6, 'Cupcake', 'Individual-sized cupcakes');
SELECT * FROM CakeTypes

INSERT INTO Chefs (ChefID, ChefName, Gender, DateOfBirth) VALUES
(1, 'Alice Johnson', 'Female', '1980-05-15'),
(2, 'Bob Smith', 'Male', '1975-08-20'),
(3, 'Emma White', 'Female', '1990-02-10'),
(4, 'Daniel Brown', 'Male', '1988-11-05'),
(5, 'Olivia Davis', 'Female', '1985-07-25'),
(6, 'Michael Johnson', 'Male', '1992-04-12');
SELECT * FROM Chefs

INSERT INTO Cakes (CakeID, CakeName, Shape, CakeWeight, CakePrice, TypeID) VALUES
(1, 'Dark Chocolate Cake', 'Round', 800, 35.99, 1),
(2, 'Strawberry Shortcake', 'Square', 700, 28.50, 2),
(3, 'Classic Cheesecake', 'Round', 900, 40.00, 3),
(4, 'Red Velvet Delight', 'Heart', 750, 32.75, 4),
(5, 'Vanilla Celebration', 'Rectangle', 850, 38.25, 5),
(6, 'Rainbow Cupcakes', 'Cupcake', 100, 2.99, 6);
SELECT * FROM Cakes

INSERT INTO Orders (OrderID, OrderDate) VALUES
(1, '2022-01-10'),
(2, '2022-02-15'),
(3, '2022-03-20'),
(4, '2022-04-25'),
(5, '2022-05-01'),
(6, '2022-06-05');
SELECT * FROM Orders

INSERT INTO CakeOrders (CakeID, OrderID, OrderedPieces) VALUES
(1, 1, 2),
(2, 1, 1),
(3, 2, 3),
(4, 2, 2),
(5, 3, 1),
(6, 3, 12);
SELECT * FROM CakeOrders

INSERT INTO CakeChefs (CakeID, ChefID) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6);
SELECT * FROM CakeChefs

GO
-- implement a stored procedure that receives an order ID, a cake name, and a positive number P representing the number of ordered pieces, 
--and adds the cake to the order. If the cake is already on the order, the number of ordered pieces is set to P.
CREATE OR ALTER PROCEDURE addCake 
   @orderid INT,
   @cakename VARCHAR(90), 
   @P INT 
AS
  DECLARE @cakeID INT;
  SET @cakeID=(SELECT CakeID FROM Cakes WHERE CakeName=@cakename)

   IF EXISTS(SELECT 1 FROM CakeOrders WHERE OrderID=@orderid AND CakeID=@cakeID AND OrderedPieces=@P) BEGIN
      PRINT 'The cake is already added in the orders'
   END
  
  IF EXISTS(SELECT 1 FROM CakeOrders WHERE OrderID=@orderid AND CakeID=@cakeID) BEGIN
    UPDATE CakeOrders SET OrderedPieces=@P WHERE OrderID=@orderid AND CakeID=@cakeID
  END

  INSERT INTO CakeOrders(OrderID,CakeID,OrderedPieces) VALUES (@orderid,@cakeID,@P)
GO

--if the cake is already in the orders
EXEC addCake 1,'Strawberry Shortcake',4
SELECT * FROM CakeOrders

--if the cake needs to be added to the orders
INSERT INTO Cakes (CakeID, CakeName, Shape, CakeWeight, CakePrice, TypeID) VALUES
(7, 'Lava Cake', 'Round', 500, 19.99, 1);
EXEC addCake 4,'Lava Cake',3

-- implement a function that lists the names of the chefs who are specialized in the preparation of all the cakes
CREATE FUNCTION nameOfChefs()
RETURNS TABLE
AS
RETURN
(
    SELECT DISTINCT C.ChefName
    FROM Chefs C
    WHERE NOT EXISTS (
        SELECT CT.CakeID
        FROM Cakes CT
        WHERE NOT EXISTS (
             SELECT CS.ChefID
             FROM CakeChefs CS
             WHERE CS.ChefID = C.ChefID AND CS.CakeID = CT.CakeID
            )));

INSERT INTO CakeChefs (CakeID, ChefID) VALUES
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7,1); 
SELECT * FROM CakeChefs

SELECT * FROM nameOfChefs()


