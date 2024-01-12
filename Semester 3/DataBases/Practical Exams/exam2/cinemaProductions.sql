CREATE DATABASE Cinema
USE Cinema 
GO


CREATE TABLE Company(
   CompanyID INT PRIMARY KEY NOT NULL,
   CompanyName VARCHAR(90)
);

CREATE TABLE Actors(
     ActorID INT PRIMARY KEY NOT NULL,
	 ActorName VARCHAR(90),
	 Ranking DECIMAL(10,2)
);

CREATE TABLE StageDirector(
    DirectorID INT PRIMARY KEY NOT NULL,
    DirectorName VARCHAR(50),
	Awards INT
);

CREATE TABLE Movies(
    MovieID INT PRIMARY KEY NOT NULL,
	MovieName VARCHAR(30),
	ReleaseDate DATE,
	DirectorID INT,
	CompanyID INT,
	FOREIGN KEY(CompanyID) REFERENCES Company(CompanyID) ON DELETE CASCADE,
	FOREIGN KEY(DirectorID) REFERENCES StageDirector(DirectorID) ON DELETE CASCADE
);

CREATE TABLE CinemaProductions(
    ProductionID INT PRIMARY KEY,
	Title VARCHAR(60),
	MovieID INT,
	FOREIGN KEY(MovieID) REFERENCES Movies(MovieID) ON DELETE CASCADE
);


CREATE TABLE ProductionActors(
    ProductionID INT,
    ActorID INT,
    EntryMoment DATETIME,
    PRIMARY KEY (ProductionID, ActorID),
    FOREIGN KEY (ProductionID) REFERENCES CinemaProductions(ProductionID) ON DELETE CASCADE,
    FOREIGN KEY (ActorID) REFERENCES Actors(ActorID) ON DELETE CASCADE
);

INSERT INTO Company(CompanyID, CompanyName) VALUES
(1, 'Warner Bros'),
(2, 'Paramount Pictures'),
(3, 'Universal Pictures'),
(4, 'Sony Pictures'),
(5, '20th Century Fox'),
(6, 'Walt Disney Pictures'),
(7, 'MGM'),
(8, 'Lionsgate'),
(9, 'DreamWorks'),
(10, 'Miramax');
SELECT * FROM Company

INSERT INTO StageDirector (DirectorID, DirectorName, Awards) VALUES
(1, 'Christopher Nolan', 5),
(2, 'Steven Spielberg', 7),
(3, 'Quentin Tarantino', 3),
(4, 'Martin Scorsese', 8),
(5, 'James Cameron', 4),
(6, 'Tim Burton', 2),
(7, 'Francis Ford Coppola', 6),
(8, 'Ridley Scott', 3),
(9, 'Peter Jackson', 9),
(10, 'Coen Brothers', 4);
SELECT * FROM StageDirector

INSERT INTO Movies (MovieID, MovieName, ReleaseDate, CompanyID, DirectorID) VALUES
(1, 'Inception', '2010-07-16', 1, 1),
(2, 'Jurassic Park', '1993-06-11', 2, 2),
(3, 'Pulp Fiction', '1994-05-12', 3, 3),
(4, 'The Departed', '2006-10-06', 4, 4),
(5, 'Avatar', '2009-12-18', 5, 5),
(6, 'Alice in Wonderland', '2010-03-05', 6, 6),
(7, 'The Godfather', '1972-03-24', 7, 7),
(8, 'Gladiator', '2000-05-05', 8, 8),
(9, 'The Lord of the Rings', '2003-12-17', 9, 9),
(10, 'No Country for Old Men', '2007-11-21', 10, 10);
SELECT * FROM Movies

INSERT INTO Actors (ActorID, ActorName, Ranking) VALUES
(1, 'Leonardo DiCaprio', 8),
(2, 'Tom Hanks', 7),
(3, 'John Travolta', 6),
(4, 'Jack Nicholson', 9),
(5, 'Kate Winslet', 8),
(6, 'Johnny Depp', 9),
(7, 'Marlon Brando', 10),
(8, 'Russell Crowe', 7),
(9, 'Ian McKellen', 9),
(10, 'Javier Bardem', 8);
SELECT * FROM Actors

INSERT INTO CinemaProductions(ProductionID, Title, MovieID) VALUES
(1, 'Inception Premiere', 1),
(2, 'Jurassic Park Special Screening', 2),
(3, 'Pulp Fiction Anniversary Event', 3),
(4, 'The Departed Exclusive Showing', 4),
(5, 'Avatar 3D Experience', 5),
(6, 'Alice in Wonderland Gala', 6),
(7, 'The Godfather Restoration', 7),
(8, 'Gladiator Epic Night', 8),
(9, 'LOTR Marathon', 9),
(10, 'No Country for Old Men Noir Night', 10);
SELECT * FROM CinemaProductions

INSERT INTO ProductionActors(ActorID, ProductionID, EntryMoment) VALUES
(1, 1, '2010-07-17'),
(2, 1, '2010-07-17'),
(3, 2, '1993-06-12'),
(4, 2, '1993-06-12'),
(5, 3, '1994-05-13'),
(6, 3, '1994-05-13'),
(7, 4, '2006-10-07'),
(8, 4, '2006-10-07'),
(9, 5, '2009-12-19'),
(10, 5, '2009-12-19');
SELECT * FROM ProductionActors
GO

--a)create a stored procedure that receives an actor, an entry moment and a cinema production and adds the new actor to the cinema production.
CREATE OR ALTER PROCEDURE addActor
  @actorID INT, 
  @entryMoment DATETIME,
  @productionID INT
AS
    IF NOT EXISTS(SELECT 1 FROM Actors WHERE ActorID=@actorID) BEGIN
	   PRINT 'The actor does not play in any film'
	END

	 IF NOT EXISTS(SELECT 1 FROM CinemaProductions WHERE ProductionID=@productionID) BEGIN
	   PRINT 'Th cinema production does not exists'
	END

	IF EXISTS(SELECT 1 FROM ProductionActors WHERE ActorID=@actorID AND ProductionID=@productionID) BEGIN
	   PRINT 'The actor already is in the cinema production'
	END

    INSERT INTO ProductionActors(ActorID,ProductionID,EntryMoment) VALUES (@actorID,@productionID,@entryMoment) 
GO

--add the actor to the cinema production
EXEC addActor 10,'2020-07-18',6
SELECT * FROM ProductionActors

--if the actor is already in the cinema production
EXEC addActor 10, '2009-12-19',5
GO

--b)Create a view that shows the name of the actors that appear in all cinema productions.
CREATE OR ALTER VIEW showName AS
    SELECT A.ActorName
	FROM Actors A
	WHERE NOT EXISTS(
	  SELECT C.ProductionID
	  FROM CinemaProductions C
	  WHERE NOT EXISTS(
	     SELECT PA.ActorID,PA.ProductionID 
		 FROM ProductionActors PA
		 WHERE PA.ActorID=A.ActorID AND PA.ProductionID=C.ProductionID
		 ))
GO

SELECT * FROM showName
go
--c)Create a function that returns all movies that have the release date after 2018-01-01' and have at least p productions, 
--where p is a function parameter.

CREATE FUNCTION moviesAfterReleaseDate(@P INT) 
RETURNS TABLE
AS 
RETURN
    SELECT M.MovieName,M.ReleaseDate
	FROM Movies M
	JOIN CinemaProductions P ON P.MovieID=M.MovieID
	GROUP BY M.MovieName, M.ReleaseDate
	HAVING M.ReleaseDate > '2018-01-01' AND COUNT(P.ProductionID) >= @P
GO

INSERT INTO Movies (MovieID, MovieName, ReleaseDate, CompanyID, DirectorID) VALUES
(11, 'Clueless', '2020-05-16', 1, 2),
(12, 'Holidate', '2021-10-26', 4, 1);
INSERT INTO CinemaProductions(ProductionID, Title, MovieID) VALUES
(11, 'Clueless Premiere', 11),
(12, 'Holidate Screening', 12);

SELECT * FROM moviesAfterReleaseDate(1)
