USE CIRC

--a table with no foreign key and a single-column primary key 
--a view with a SELECT statement operating on one table

EXEC addToTables 'Animals'

--a view with a SELECT statement operating on one table
CREATE OR ALTER VIEW animalsWithAgeGreaterThan5 AS
   SELECT A.AnimalID, A.AnimalName, A.AnimalSPecie, A.AnimalAge
   FROM Animals A
   WHERE A.AnimalAge > 5

EXEC addToViews 'animalsWithAgeGreaterThan5'
EXEC addToTests 'Test1'
EXEC connectTableToTest 'Animals', 'Test1', 20, 1
EXEC connectViewToTest 'animalsWithAgeGreaterThan5', 'Test1'


CREATE OR ALTER PROCEDURE populateTableAnimals(@rows INT) AS
    WHILE @rows > 0 BEGIN
        INSERT INTO Animals(AnimalID, AnimalName, AnimalAge, AnimalSPecie)  VALUES (@rows, 'Testing', floor(rand()*10), 'Specie2')
        SET @rows = @rows-1
    END

SELECT * FROM Animals

EXECUTE runTest 'Test1'

SELECT * FROM Views

SELECT * FROM Tables

SELECT * FROM Tests

SELECT * FROM TestTables

SELECT * FROM TestViews

SELECT * FROM TestRuns

SELECT * FROM TestRunTables

SELECT * FROM TestRunViews
