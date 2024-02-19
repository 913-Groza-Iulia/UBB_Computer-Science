USE Circ

--a table with no foreign key and a single-column primary key + a table with a foreign key and a single-column primary key + a table with a multi-column primary key
--a view with a SELECT statement that has a GROUP BY clause and operates on at least 2 tables
EXEC addToTables 'ShowLocation'
EXEC addToTables 'Shows'
EXEC addToTables 'Locations'

--a view with a SELECT statement that has a GROUP BY clause and operates on at least 2 tables
GO
CREATE OR ALTER VIEW averageAnimalAge AS
   SELECT P.Specialty, AVG(A.AnimalAge) AS AvgAnimalAge 
   FROM Performers P
   INNER JOIN Acts ON P.PerformerID = Acts.PerformerID
   INNER JOIN Shows ON Acts.ActID = Shows.ActID
   INNER JOIN Animals A ON Shows.ShowID = A.AnimalID
   GROUP BY P.Specialty
   HAVING AVG(A.AnimalAge) > (SELECT AVG(AnimalAge)
                           FROM Animals);

GO
CREATE OR ALTER PROCEDURE populateTablePerformers(@rows INT) AS
    WHILE @rows > 0 BEGIN
        INSERT INTO Performers(PerformerID, FirstName, LastName, DateOfBirth, Specialty)  VALUES (@rows, 'Name', 'Surname', floor(rand()*100), 'Specialty')
        SET @rows = @rows-1
    END

GO
CREATE OR ALTER PROCEDURE populateTableActs(@rows INT) AS
    WHILE @rows > 0 BEGIN
        INSERT INTO Acts(ActID, ActName, ActType, PerformerID)  VALUES (@rows, 'ActName', 'ActType', (select top 1 PerformerID from Performers order by newid()))
        SET @rows = @rows-1
    END

GO
CREATE OR ALTER PROCEDURE populateTableShowLocation(@rows INT) AS
   DECLARE @sid INT
   DECLARE @lid INT

   WHILE @rows > 0 BEGIN
     SET @sid = (SELECT TOP 1 ShowID FROM Shows order by newid())
	 SET @lid = (SELECT TOP 1 LocationID FROM Locations order by newid())
	 WHILE EXISTS (SELECT * FROM ShowLocation WHERE ShowID = @sid AND LocationID = @lid) BEGIN
	      SET @sid = (SELECT TOP 1 ShowID FROM Shows order by newid())
	      SET @lid = (SELECT TOP 1 LocationID FROM Locations order by newid())
	 END
	 INSERT INTO ShowLocation (ShowID, LocationID) VALUES (@sid, @lid)
	 SET @rows = @rows - 1
   END
   


EXEC addToViews 'averageAnimalAge'
EXEC addToTests 'Test3'
EXEC connectTableToTest 'ShowLocation', 'Test3', 100, 3
EXEC connectTableToTest '', 'Test3', 100, 2
EXEC connectTableToTest '', 'Test3', 100, 1
EXEC connectViewToTest 'averageAnimalAge', 'Test3'

EXEC runTest 'Test3'

SELECT * FROM Views

SELECT * FROM Tables

SELECT * FROM Tests

SELECT * FROM TestTables

SELECT * FROM TestViews

SELECT * FROM TestRuns

SELECT * FROM TestRunTables

SELECT * FROM TestRunViews

