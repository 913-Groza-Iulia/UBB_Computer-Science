CREATE DATABASE lab5db;
GO

USE lab5db;

DROP TABLE TA
DROP TABLE TB
DROP TABLE TC

CREATE TABLE TA(
  aid INT PRIMARY KEY, 
  a2 INT UNIQUE,
  x INT
)

CREATE TABLE TB(
  bid INT PRIMARY KEY,
  b2 INT,
  x INT
)

CREATE TABLE TC (
   cid INT PRIMARY KEY,
   aid INT, 
   bid INT,
   FOREIGN KEY (aid) REFERENCES TA(aid),
   FOREIGN KEY (bid) REFERENCES TB(bid)
)

GO
CREATE OR ALTER PROCEDURE addValuesToTA(@rows INT) AS
  WHILE @rows > 0 BEGIN
     INSERT INTO TA(aid, a2, x) VALUES (@rows, @rows*10, @rows%240)
     SET @rows = @rows -1
  END

GO
CREATE OR ALTER PROCEDURE addValuesToTB(@rows INT) AS
  WHILE @rows > 0 BEGIN
     INSERT INTO TB(bid, b2, x) VALUES (@rows, @rows%2 ,@rows%70)
     SET @rows = @rows -1
  END

GO
CREATE OR ALTER PROCEDURE addValuesToTC(@rows INT) AS
  DECLARE @aid INT
  DECLARE @bid INT
  WHILE @rows > 0 BEGIN
     SET @aid = (SELECT TOP 1 aid FROM TA ORDER BY NEWID())
	 SET @bid = (SELECT TOP 1 bid FROM TB ORDER BY NEWID())
     INSERT INTO TC(cid, aid, bid) VALUES (@rows, @aid, @bid)
     SET @rows = @rows -1
  END

EXEC addValuesToTA 10000
EXEC addValuesToTB 12000
EXEC addValuesToTC 40000  
SELECT * FROM TA
SELECT * FROM TB
SELECT * FROM TC

CREATE NONCLUSTERED INDEX index1 ON TA(x);
DROP INDEX index1 ON TA;

SELECT * FROM TA ORDER BY aid; -- clustered index scan
SELECT * FROM TA WHERE aid = 10; -- clustered index seek
SELECT x FROM TA order by x; -- nonclustered index scan
SELECT aid,x FROM TA WHERE x = 100; -- nonclustered index seek
SELECT x FROM TA WHERE aid = 500; -- key lookup

GO
CREATE OR ALTER PROCEDURE where_query AS
   DECLARE @startTime DATETIME2
   DECLARE @endTime DATETIME2

   SET @startTime = SYSDATETIME()
   SELECT * FROM TB WHERE b2 = 0
   SET @endTime = SYSDATETIME()

   PRINT CONVERT(VARCHAR, @endTime)+' '+CONVERT(VARCHAR,@startTime)

EXEC where_query -- 0.07 without index 

CREATE NONCLUSTERED INDEX index2 ON TB(b2) INCLUDE (bid,x)
EXEC where_query -- 0.06 with index 

GO
CREATE OR ALTER VIEW view1 AS
   SELECT TOP 900 T1.x, T2.b2
   FROM TC T3 JOIN TA T1 ON T3.aid = T1.aid JOIN TB T2 ON T3.bid = T2.bid
   WHERE T2.b2 >= 0 AND T1.x < 160

GO
CREATE OR ALTER PROCEDURE select_view AS
    DECLARE @startTime DATETIME2
    DECLARE @endTime DATETIME2	
	SET @startTime = SYSDATETIME()
	SELECT * FROM view1
	SET @endTime = SYSDATETIME()
	PRINT CONVERT(VARCHAR, @endTime)+' '+CONVERT(VARCHAR,@startTime)

EXEC select_view    --0.01 with indexes

drop index index2 on TB
drop index index1 on TA
exec select_view    --0.05 without indexes

