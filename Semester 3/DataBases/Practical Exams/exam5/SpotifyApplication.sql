CREATE DATABASE SpotifyApp

USE SpotifyApp
GO

CREATE TABLE Songs(
    SongID INT PRIMARY KEY NOT NULL,
	SongName VARCHAR(60),
	SongMinutes DECIMAL(4, 2) CHECK (SongMinutes > 0 AND SongMinutes < 20),
	ReleaseDate DATE
);

CREATE TABLE Artists(
   ArtistID INT PRIMARY KEY NOT NULL,
   ArtistName VARCHAR(100),
   Origins VARCHAR(50),
   DateOfBirth DATE
);

CREATE TABLE Accounts(
  AccountID INT PRIMARY KEY NOT NULL,
  Username VARCHAR(90) UNIQUE,
  EmailAddress VARCHAR(150),
);

CREATE TABLE Playlists(
   PlaylistID INT PRIMARY KEY NOT NULL,
   PlaylistName VARCHAR(100),
   ArtistID INT,
   AccountID INT,
   FOREIGN KEY(ArtistID) REFERENCES Artists(ArtistID) ON DELETE CASCADE,
   FOREIGN KEY(AccountID) REFERENCES Accounts(AccountID) ON DELETE CASCADE
);

CREATE TABLE SongsAndPlaylists(
  SongID INT,
  PlaylistID INT,
  PRIMARY KEY (SongID, PlaylistID),
  FOREIGN KEY(SongID) REFERENCES Songs(SongID) ON DELETE CASCADE,
  FOREIGN KEY(PlaylistID) REFERENCES Playlists(PlaylistID) ON DELETE CASCADE

);

CREATE TABLE SongsArtists(
  ArtistID INT, 
  SongID INT,
  IsMainArtist BIT,
  PRIMARY KEY(ArtistID, SongID),
  FOREIGN KEY(ArtistID) REFERENCES Artists(ArtistID) ON DELETE CASCADE,
  FOREIGN KEY(SongID) REFERENCES Songs(SongID) ON DELETE CASCADE
);

INSERT INTO Songs VALUES
(1, 'Song 1', 4.5, '2022-01-01'),
(2, 'Song 2', 3.2, '2022-02-15'),
(3, 'Song 3', 5.8, '2022-03-20');
SELECT * FROM Songs

INSERT INTO Artists VALUES 
(1, 'Artist 1', 'USA', '1990-05-10'),
(2, 'Artist 2', 'UK', '1985-12-08'),
(3, 'Artist 3', 'Canada', '1995-02-28');
SELECT * FROM Artists;

INSERT INTO Accounts  VALUES
(1, 'user1', 'user1@example.com'),
(2, 'user2', 'user2@example.com'),
(3, 'user3', 'user3@example.com');
SELECT * FROM Accounts;

INSERT INTO Playlists VALUES
(1, 'Playlist 1', 1, 1),
(2, 'Playlist 2', 2, 2),
(3, 'Playlist 3', 3, 3);
SELECT * FROM Playlists;

INSERT INTO SongsArtists VALUES
(1, 1, 1),
(1, 2, 0),
(2, 2, 1),
(2, 3, 0),
(3, 1, 1),
(3, 3, 0);
SELECT * FROM SongsArtists;

INSERT INTO SongsAndPlaylists VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2),
(3, 1),
(3, 3);
SELECT * FROM SongsAndPlaylists

go
-- implement a stored procedure that receives details of an account and stores the account in the database.
CREATE OR ALTER PROCEDURE storeAccount
   @accountID INT,
   @username VARCHAR(90),
   @email VARCHAR(150)
AS
    IF EXISTS (SELECT 1 FROM Accounts WHERE AccountID=@accountID AND Username=@username AND EmailAddress=@email) BEGIN
	  PRINT 'The account is already registered in the database'
	END

	INSERT INTO Accounts VALUES (@accountID,@username,@email)

EXEC storeAccount 4,'user4','user4@example.com'
SELECT * FROM Accounts

--already registered
EXEC storeAccount 3,'user3','user3@example.com'

-- create a view that shows the names of the playlists that contain the most songs.
CREATE OR ALTER VIEW playlistsWithMostSongs AS
  SELECT P.PlaylistName,COUNT(SP.SongID) AS NumberOfSongs
  FROM Playlists P
  INNER JOIN SongsAndPlaylists SP ON P.PlaylistID=SP.PlaylistID
  GROUP BY P.PlaylistName
  HAVING COUNT(SP.SongID) = (SELECT TOP 1 COUNT(SongID)
                             FROM SongsAndPlaylists
							 GROUP BY PlaylistID
							 ORDER BY COUNT(SongID) DESC)

SELECT * FROM playlistsWithMostSongs

-- implement a function that returns the number of artists that have created more than R playlists that have a total duration greater than T, where R and T are function parameters.
CREATE FUNCTION numberOfArtists(@R INT, @T DECIMAL(4,2)) 
RETURNS INT
AS
BEGIN
        DECLARE @numberOfArtists INT                        
		SELECT @NumberOfArtists = COUNT(DISTINCT sa.ArtistID)
                                  FROM SongsAndPlaylists sp
                                  INNER JOIN Playlists p ON sp.PlaylistID = p.PlaylistID
								  INNER JOIN Songs s ON sp.SongID = s.SongID
								  INNER JOIN SongsArtists sa ON s.SongID = sa.SongID AND sa.IsMainArtist = 1
								  GROUP BY sa.ArtistID
								  HAVING COUNT(DISTINCT p.PlaylistID) > @R AND SUM(s.SongMinutes) > @T;
        RETURN @NumberOfArtists
END

SELECT dbo.numberOfArtists(1, 1.8) AS ArtistsCount