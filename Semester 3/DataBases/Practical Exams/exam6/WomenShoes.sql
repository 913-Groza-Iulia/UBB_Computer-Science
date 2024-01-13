CREATE DATABASE WomenShoes
USE WomenShoes
GO

CREATE TABLE PresentationShops(
   Pid INT PRIMARY KEY NOT NULL,
   PName VARCHAR(50),
   City VARCHAR(60)
);

CREATE TABLE Women(
  WomanId INT PRIMARY KEY NOT NULL,
  WName VARCHAR(100),
  Spendings INT
);

CREATE TABLE ShoeModels(
   ModelId INT PRIMARY KEY NOT NULL,
   ModelName VARCHAR(50),
   SEASON VARCHAR(50)
);

CREATE TABLE Shoes(
   ShoeId INT PRIMARY KEY NOT NULL,
   Price INT,
   ModelId INT,
   FOREIGN KEY(ModelId) REFERENCES ShoeModels(ModelId) ON DELETE CASCADE	
);

CREATE TABLE  ShoePresentationShops(
   ShoeId INT,
   Pid INT,
   AvailableShoes INT,
   PRIMARY KEY(ShoeId, Pid),
   FOREIGN KEY(ShoeId) REFERENCES Shoes(ShoeId) ON DELETE CASCADE,
   FOREIGN KEY(Pid) REFERENCES PresentationShops(Pid) ON DELETE CASCADE
);

CREATE TABLE WomenShoes(
   WomanId INT,
   ShoeId INT,
   NrShoes INT,
   AmountSpent INT,
   PRIMARY KEY(WomanId, ShoeId),
   FOREIGN KEY(WomanId) REFERENCES Women(WomanId) ON DELETE CASCADE,
   FOREIGN KEY(ShoeId) REFERENCES Shoes(ShoeId) ON DELETE CASCADE
);

INSERT INTO Women VALUES (1,'Woman 1',300),(2,'Woman 2',150),(3,'Woman 3',400),(4,'Woman 4',200);
SELECT * FROM Women

INSERT INTO PresentationShops VALUES (1,'Shop 1', 'Brasov'),(2,'Shop 2','Cluj'),(3,'Shop 3','Deva');
SELECT * FROM PresentationShops

INSERT INTO ShoeModels VALUES (1, 'Model 1','Spring'),(2, 'Model 2','Summer'),(3, 'Model 3','Winter'),(4, 'Model 4','Autumn')
SELECT * FROM ShoeModels

INSERT INTO Shoes VALUES (1,90,1),(2,50,2),(3,85,3),(4,66,4);
SELECT * FROM Shoes

INSERT INTO WomenShoes VALUES (1,1,2,180),(2,2,4,300),(3,3,6,500),(4,4,1,66);
SELECT * FROM WomenShoes

INSERT INTO ShoePresentationShops VALUES (1,2,10),(2,3,5),(3,1,15),(4,1,10);
SELECT * FROM ShoePresentationShops
 go

--Create a stored procedure that receives a shoe, a presentation shop and the number of shoes and adds the shoe to the presentation shop.
CREATE OR ALTER PROCEDURE addShoe
   @shoeID INT, 
   @pid INT, 
   @nrShoes INT
AS
    IF NOT EXISTS (SELECT 1 FROM Shoes WHERE ShoeId = @shoeID) OR NOT EXISTS (SELECT 1 FROM PresentationShops WHERE Pid = @pid) BEGIN
	  PRINT 'Invalid shoe or presentation shop'
	END
    IF @nrShoes <= 0 BEGIN
	   PRINT 'Invalid number of shoes'
	END
	INSERT INTO ShoePresentationShops(ShoeId,Pid, AvailableShoes) VALUES (@shoeID, @pid,@nrShoes)
GO

EXEC addShoe 3,2,15
SELECT * FROM ShoePresentationShops

go
-- create a view that shows the women that bought at least 2 shoes from a given shoe model
CREATE VIEW view1 AS
   SELECT W.WomanId, W.WName, COUNT(WS.ShoeID) AS NumShoesBought
   FROM Women W JOIN WomenShoes WS ON W.WomanID = WS.WomanID
   JOIN Shoes S ON WS.ShoeID = S.ShoeID
   JOIN ShoeModels WM ON S.ModelID = WM.ModelID
   GROUP BY W.WomanID, W.WName, WM.ModelName
   HAVING COUNT(WS.ShoeID) >= 2;
GO

SELECT * FROM view1

-- create a function that lists the shoes that can be found in at least T presentation shops, where T>=1 is a function parameter. 
CREATE FUNCTION shoesFoundInTshops (@T INT)
RETURNS TABLE
AS
RETURN(
      SELECT S.ShoeId, SM.ModelId,SM.ModelName
	  FROM Shoes S
	  INNER JOIN ShoePresentationShops SS ON S.ShoeId=SS.ShoeId
	  INNER JOIN PresentationShops P ON SS.Pid=P.Pid
	  INNER JOIN ShoeModels AS SM ON S.ModelId=SM.ModelId
	  GROUP BY S.ShoeId,SM.ModelId,SM.ModelName
	  HAVING COUNT( DISTINCT SS.Pid) >= @T
)

SELECT * FROM shoesFoundInTshops(2)
