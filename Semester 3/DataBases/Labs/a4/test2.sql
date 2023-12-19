USE CIRC

--a table with a single-column primary key and at least one foreign key and a table with a single-column primary key and no foreign keys;;
--a view with a SELECT statement operating on at least two tables
EXEC addToTables 'Acts'
EXEC addToTables 'Performers'

--a view with a SELECT statement operating on at least 2 tables
GO
CREATE OR ALTER VIEW performersAndActs AS
    SELECT P.PerformerID, P.FirstName, P.LastName, A.ActName, A.ActType,A.ActID 
    FROM Performers P
    LEFT JOIN Acts A ON P.PerformerID=A.PerformerID;

EXEC addToViews 'performersAndActs'
EXEC addToTests 'Test2'
EXEC connectTableToTest 'Acts', 'Test2', 15, 1
EXEC connectTableToTest 'Performers', 'Test2', 20, 2
EXEC connectViewToTest 'performersAndActs', 'Test2'


CREATE OR ALTER PROCEDURE populateTableActs(@rows INT) AS
    WHILE @rows > 0 BEGIN
        INSERT INTO Acts(ActID, ActName, ActType, PerformerID)  VALUES (@rows, 'ActName', 'ActType', (select top 1 PerformerID from Performers order by newid()))
        SET @rows = @rows-1
    END

CREATE OR ALTER PROCEDURE populateTablePerformers(@rows INT) AS
    WHILE @rows > 0 BEGIN
	    DECLARE @randomDays INT = CAST(RAND() * 365 * 100 AS INT)
        DECLARE @randomDOB DATE = DATEADD(DAY, -@randomDays, GETDATE())

        INSERT INTO Performers(PerformerID, FirstName, LastName, DateOfBirth, Specialty)  VALUES (@rows, 'Name', 'Surname', @randomDOB, 'Specialty')
        SET @rows = @rows-1
    END

SELECT * FROM Acts

EXECUTE runTest 'Test2'

SELECT * FROM Views

SELECT * FROM Tables

SELECT * FROM Tests

SELECT * FROM TestTables

SELECT * FROM TestViews

SELECT * FROM TestRuns

SELECT * FROM TestRunTables

SELECT * FROM TestRunViews

