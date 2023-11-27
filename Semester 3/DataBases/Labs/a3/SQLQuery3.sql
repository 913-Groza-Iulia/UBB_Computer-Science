USE Circ;
GO

--modify the type of a column;
GO
CREATE OR ALTER PROCEDURE changePerformersFirstName100 AS
BEGIN
    ALTER TABLE Performers
	ALTER COLUMN FirstName VARCHAR(100)
    PRINT 'ALTER TABLE Performers FirstName FROM varchar50 TO varchar100';
END
EXEC changePerformersFirstName100;

GO
CREATE OR ALTER PROCEDURE changePerformersFirstName50 AS
BEGIN
    ALTER TABLE Performers
	ALTER COLUMN FirstName VARCHAR(50)
    PRINT 'ALTER TABLE Performers FirstName FROM varchar100 TO varchar50';
END
EXEC changePerformersFirstName50;

-- add / remove a column;
GO
CREATE OR ALTER PROCEDURE addVetName AS
	ALTER TABLE AnimalCare ADD VetName VARCHAR(40);
EXEC addVetName;

GO
CREATE OR ALTER PROCEDURE removeVetName AS
	ALTER TABLE AnimalCare DROP COLUMN VetName;
EXEC removeVetName;

-- add / remove a DEFAULT constraint;
GO
CREATE OR ALTER PROCEDURE addDefaultActType AS
     ALTER TABLE Acts ADD CONSTRAINT TYPE_DEFAULT DEFAULT('Comedy') FOR ActType;
EXEC addDefaultActType;

GO
CREATE OR ALTER PROCEDURE removeDefaultActType AS
     ALTER TABLE Acts DROP CONSTRAINT TYPE_DEFAULT; 
EXEC removeDefaultActType;

-- create / drop a table;
GO
CREATE PROCEDURE addSponsors AS
   create table [dbo].[Sponsors] (
   SponsorID INT NOT NULL,
   Name VARCHAR(100) NOT NULL,
   ContactPhone INT NOT NULL,
   ContactEmail VARCHAR(70),
   constraint SPONSORS_PRIMARY_KEY primary key(SponsorID)
   );
EXEC addSponsors;

GO
CREATE OR ALTER PROCEDURE removeSponsors AS
   DROP TABLE [dbo].[Sponsors];
EXEC removeSponsors;

-- add / remove a primary key;
GO
CREATE PROCEDURE addNamePrimaryKeySponsors AS
  ALTER TABLE Sponsors
     DROP CONSTRAINT SPONSORS_PRIMARY_KEY
  ALTER TABLE Sponsors
     ADD CONSTRAINT NAME_PRIMARY_KEY PRIMARY KEY(SponsorID, Name)
EXEC addNamePrimaryKeySponsors;

GO
CREATE PROCEDURE removeNamePrimaryKeySponsors AS
  ALTER TABLE Sponsors
     DROP CONSTRAINT NAME_PRIMARY_KEY
  ALTER TABLE Sponsors
     ADD CONSTRAINT SPONSORS_PRIMARY_KEY PRIMARY KEY(SponsorID)
EXEC removeNamePrimaryKeySponsors;

-- add / remove a candidate key;
GO
CREATE OR ALTER PROCEDURE addCandidateKeyTickets AS
     ALTER TABLE Tickets 
	 ADD CONSTRAINT TICKETS_CANDIDATE_KEY UNIQUE(CustomerName,CustomerEmail)
EXEC addCandidateKeyTickets;

GO
CREATE OR ALTER PROCEDURE removeCandidateKeyTickets AS
     ALTER TABLE Tickets 
	 DROP CONSTRAINT TICKETS_CANDIDATE_KEY
EXEC removeCandidateKeyTickets;

-- add / remove a foreign key;
GO
CREATE OR ALTER PROCEDURE addForeignKeyShows AS
    ALTER TABLE Shows
	ADD CONSTRAINT SHOWS_FOREIGN_KEY FOREIGN KEY (ActID) REFERENCES Acts(ActID)
EXEC addForeignKeyShows;

GO
CREATE OR ALTER PROCEDURE removeForeignKeyShows AS
    ALTER TABLE Shows
	DROP CONSTRAINT SHOWS_FOREIGN_KEY
EXEC removeForeignKeyShows;

-- a new table that holds the current version of the database schema
CREATE TABLE VersionTable(
     version INT
)

INSERT INTO VersionTable VALUES
(0) --initial version

CREATE TABLE ProcedureTable(
   initial_version INT,
   final_version INT,
   procedure_name VARCHAR(MAX),
   PRIMARY KEY (initial_version, final_version)
   )

INSERT INTO ProcedureTable VALUES
(1,2,'changePerformersFirstName100'),
(2,1,'changePerformersFirstName50'),
(2,3,'addVetName'),
(3,2,'removeVetName'),
(3,4,'addDefaultActType'),
(4,3,'removeDefaultActType'),
(4,5,'addSponsors'),
(5,4,'removeSponsors'),
(5,6,'addNamePrimaryKeySponsors'),
(6,5,'removeNamePrimaryKeySponsors'),
(6,7,'addCandidateKeyTickets'),
(7,6,'removeCandidateKeyTickets'),
(7,8,'addForeignKeyShows'),
(8,7,'removeForeignKeyShows')

GO
CREATE PROCEDURE gotoVersion(@newVersion INT) AS
	DECLARE @curr int;
	DECLARE @procedureName varchar(255);
	SELECT @curr=version FROM VersionTable;
	IF @newVersion > (SELECT max(final_version) FROM ProcedureTable)
		RAISERROR ('Version does not exist', 10, 1);
	
	IF @newVersion < (SELECT min(initial_version) FROM ProcedureTable)
		RAISERROR ('Version does not exist', 10, 1);
	
	WHILE @curr < @newVersion 
	 BEGIN
		SELECT @procedureName=procedure_name FROM ProcedureTable WHERE initial_version=@curr AND final_version=@curr+1;
		EXEC (@procedureName);
		PRINT('Executing ' + @procedureName);
		SET @curr=@curr+1;
		UPDATE VersionTable SET version=@newVersion;
	 END
	
	WHILE @curr > @newVersion 
	 BEGIN
		SELECT @procedureName=procedure_name FROM ProcedureTable WHERE initial_version=@curr AND final_version=@curr-1;
		EXEC (@procedureName);
		PRINT('Executing ' + @procedureName);
		SET @curr=@curr-1;
		UPDATE VersionTable SET version=@newVersion;
	 END;

EXEC gotoVersion 1;

SELECT * FROM versionTable;
DROP TABLE VersionTable;