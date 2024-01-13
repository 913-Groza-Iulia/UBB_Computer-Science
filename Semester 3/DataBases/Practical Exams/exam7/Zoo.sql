CREATE DATABASE Zoo

USE Zoo
GO

CREATE TABLE Zoos(
  ZooID INT PRIMARY KEY	 NOT NULL,
  Administrator VARCHAR(100),
  ZooName VARCHAR(100),
);

CREATE TABLE Animals(
  AnimalID INT PRIMARY KEY NOT NULL,
  AnimalName VARCHAR(100),
  DateOfBirth DATE,
  ZooID INT,
  FOREIGN KEY(ZooID) REFERENCES Zoos(ZooID) ON DELETE CASCADE
);

CREATE TABLE Foods(
  FoodID INT PRIMARY KEY NOT NULL,
  FoodName VARCHAR(100)
);

CREATE TABLE Visitors(
   VisitorID INT PRIMARY KEY NOT NULL,
   VisitorName VARCHAR(100),
   Age INT
);

CREATE TABLE Visits(
   VisitID INT PRIMARY KEY NOT NULL,
   DayOfVisit VARCHAR(50),
   PaidPrice INT,
   VisitorID INT,
   VisitorNumber INT,
   ZooID INT,
   FOREIGN KEY (VisitorID) REFERENCES Visitors(VisitorID) ON DELETE CASCADE,
   FOREIGN KEY (ZooID) REFERENCES Zoos(ZooID) ON DELETE CASCADE
);

CREATE TABLE FoodQuota(
   AnimalID INT,
   FoodID INT,
   DailyQuota INT, 
   PRIMARY KEY (AnimalID, FoodID),
   FOREIGN KEY (AnimalID) REFERENCES Animals(AnimalID) ON DELETE CASCADE,
   FOREIGN KEY (FoodID) REFERENCES Foods(FoodID) ON DELETE CASCADE
);

INSERT INTO Zoos(ZooID, Administrator, ZooName) VALUES
(1, 'John Doe', 'Central Zoo'),
(2, 'Jane Smith', 'City Wildlife Park'),
(3, 'Robert Johnson', 'Safari Adventure Park'),
(4, 'Emily White', 'Nature Haven Zoo'),
(5, 'Michael Brown', 'Wild Wonder Zoo');
SELECT * FROM Zoos

INSERT INTO Animals(AnimalID, AnimalName, DateOfBirth, ZooID) VALUES
(1, 'Lion', '2015-02-10', 1),
(2, 'Elephant', '2010-07-22', 1),
(3, 'Giraffe', '2018-05-15', 2),
(4, 'Penguin', '2017-11-30', 3),
(5, 'Kangaroo', '2019-09-05', 4);
SELECT * FROM Animals

INSERT INTO Foods(FoodID, FoodName) VALUES
(1, 'Meat'),
(2, 'Hay'),
(3, 'Fish'),
(4, 'Fruits'),
(5, 'Vegetables');
SELECT * FROM Foods

INSERT INTO FoodQuota(AnimalID, FoodID, DailyQuota) VALUES
(1, 1, 5),
(1, 2, 10),
(2, 1, 8),
(2, 2, 15),
(3, 3, 5);
SELECT * FROM FoodQuota

INSERT INTO Visitors(VisitorID, VisitorName, Age) VALUES
(1, 'Alice Johnson', 25),
(2, 'Bob Smith', 30),
(3, 'Emma White', 22),
(4, 'Daniel Brown', 28),
(5, 'Olivia Davis', 35);
SELECT * FROM Visitors

INSERT INTO Visits(VisitID, DayOfVisit, PaidPrice, VisitorID, VisitorNumber,ZooID) VALUES
(1, '2022-01-15', 20.50, 1, 07549934, 1),
(2, '2022-02-20', 15.75, 2, 07335239, 2),
(3, '2022-03-10', 30.00, 3, 07657392, 3),
(4, '2022-04-05', 25.50, 4, 07892516, 4),
(5, '2022-05-12', 18.25, 5, 07283493, 5);
SELECT * FROM Visits

go
--implement a stored procedure that receives an animal and deletes all the data about the food quotas for that animal. 
CREATE OR ALTER PROCEDURE deleteAnimal(@animalID INT) AS
  IF NOT EXISTS(SELECT 1 FROM Animals WHERE AnimalID=@animalID) BEGIN
     PRINT 'The animal is not in the zoo'
  END
  DELETE FROM FoodQuota WHERE AnimalID=@animalID

EXEC deleteAnimal 1
SELECT * FROM FoodQuota
GO

--create a view that shows the ids of the zoos with the smallest number of visits.
CREATE VIEW ZoosWithSmallestVisits AS
   SELECT Z.ZooID, COUNT(V.VisitID) AS VisitCount
   FROM Zoos Z
   INNER JOIN Visits V ON Z.ZooID=V.ZooID
   GROUP BY Z.ZooID
   HAVING COUNT(V.VisitID) = (SELECT TOP 1 COUNT(VisitID) AS VisitCount
                              FROM Visits
							  GROUP BY ZooID
							  ORDER BY VisitCount)
GO

INSERT INTO Visits VALUES (6,'2023-09-08',18.99,3,07853683,4)
SELECT * FROM ZoosWithSmallestVisits

--implement a function that lists the ids of the visitors who went to zoos that have at least N animals, where N>=1 is a function parameter.
CREATE FUNCTION zooWithNanimals(@N INT)
RETURNS TABLE
AS
RETURN
   SELECT V.VisitorID
   FROM Visitors V
   INNER JOIN Visits VI ON V.VisitorID=VI.VisitorID
   INNER JOIN Zoos Z ON VI.ZooID=Z.ZooID
   JOIN Animals A ON Z.ZooID=A.ZooID
   GROUP BY V.VisitorID
   HAVING COUNT(A.AnimalID) >=@N
GO

SELECT * FROM zooWithNanimals(2)