CREATE DATABASE RugbyWorldCup

USE RugbyWorldCup
GO

CREATE TABLE Cities(
  CityID INT PRIMARY KEY NOT NULL,
  CityName VARCHAR(100) UNIQUE
);

CREATE TABLE Stadiums(
  StadiumID INT PRIMARY KEY NOT NULL,
  StadiumName VARCHAR(100),
  CityID INT,
  FOREIGN KEY(CityID) REFERENCES Cities(CityID) ON DELETE CASCADE
);

CREATE TABLE NationalTeams(
   TeamID INT PRIMARY KEY NOT NULL,
   Country VARCHAR(100) UNIQUE
);

CREATE TABLE Players(
  PlayerID INT PRIMARY KEY NOT NULL,
  PlayerName VARCHAR(100),
  DateOfBirth DATE,
  Nationality VARCHAR(100),
  Position VARCHAR(100),
  isCaptain BIT,
  NationalTeamID INT,
  FOREIGN KEY(NationalTeamID) REFERENCES NationalTeams(TeamID) ON DELETE CASCADE
);

CREATE TABLE Coaches(
    CoachID INT PRIMARY KEY NOT NULL,
	CoachName VARCHAR(100),
	Nationality VARCHAR(100),
	TeamID INT,
    FOREIGN KEY(TeamID) REFERENCES NationalTeams(TeamID) ON DELETE CASCADE,
);

CREATE TABLE Games(
  GameID INT PRIMARY KEY NOT NULL,
  GameDate DATE,
  StadiumID INT,
  Team1ID INT,
  Team2ID INT,
  FOREIGN KEY(StadiumID) REFERENCES Stadiums(StadiumID) ON DELETE CASCADE,
  FOREIGN KEY(Team1ID) REFERENCES NationalTeams(TeamID) ON DELETE NO ACTION,
  FOREIGN KEY(Team2ID) REFERENCES NationalTeams(TeamID) ON DELETE NO ACTION,
  FinalScoreTeam1 INT,
  FinalScoreTeam2 INT,
  WinnerID INT,
  FOREIGN KEY (WinnerID) REFERENCES NationalTeams(TeamID) ON DELETE NO ACTION,
  OvertimeOrNot BIT,
);

INSERT INTO Cities (CityID, CityName) VALUES
(1, 'Paris'),
(2, 'Auckland'),
(3, 'Tokyo');

INSERT INTO Stadiums (StadiumID, StadiumName, CityID) VALUES
(1, 'Stade de France', 1),
(2, 'Eden Park', 2),
(3, 'Tokyo Stadium', 3),
(4, 'Cluj Arena', 1);

INSERT INTO NationalTeams (TeamID, Country) VALUES
(1, 'France'),
(2, 'New Zealand'),
(3, 'Japan');

INSERT INTO Players (PlayerID, PlayerName, DateOfBirth, Nationality, Position, IsCaptain, NationalTeamID) VALUES
(1, 'Player1', '1990-01-15', 'French', 'Prop', 1, 1),
(2, 'Player2', '1988-05-20', 'New Zealander', 'Fly-half', 0, 2),
(3, 'Player3', '1995-09-10', 'Japanese', 'Scrum-half', 1, 3);

INSERT INTO Coaches (CoachID, CoachName, Nationality, TeamID) VALUES
(1, 'Coach1', 'French', 1),
(2, 'Coach2', 'New Zealander', 2),
(3, 'Coach3', 'Japanese', 3);

INSERT INTO Games (GameID, GameDate, Team1ID, Team2ID, StadiumID, FinalScoreTeam1, FinalScoreTeam2, WinnerID, OvertimeOrNot) VALUES
(1, '2023-09-10', 1, 2, 1, 28, 24, 1, 0),
(2, '2023-09-15', 2, 3, 2, 35, 12, 2, 0),
(3, '2023-09-20', 1, 3, 3, 21, 18, 1, 1);

INSERT INTO Games (GameID, GameDate, Team1ID, Team2ID, StadiumID, FinalScoreTeam1, FinalScoreTeam2, WinnerID, OvertimeOrNot) VALUES
(4, '2023-07-11', 2, 3, 4, 20, 30, 2, 1),
(5, '2023-08-10', 1, 2, 4, 28, 24, 1, 1);

SELECT * FROM Games

-- implement a stored procedure that receives the details of a game and stores the game in the database. 
--If the two teams already played against each other on the same date, then the final score is updated.
CREATE OR ALTER PROCEDURE storeGames 
   @gameID INT,
   @gamedate DATE,
   @Team1ID INT, 
   @Team2ID INT, 
   @StadiumID INT, 
   @FinalScoreTeam1 INT, 
   @FinalScoreTeam2 INT, 
   @WinnerID INT, 
   @OvertimeOrNot BIT
AS
    IF EXISTS (SELECT 1 FROM Games WHERE (Team1ID = @Team1ID AND Team2ID = @Team2ID OR Team1ID = @Team2ID AND Team2ID = @Team1ID) AND GameDate=@gamedate) BEGIN
	   UPDATE Games SET  
	   FinalScoreTeam1 = @FinalScoreTeam1,
       FinalScoreTeam2 = @FinalScoreTeam2,
       WinnerID = @WinnerID,
       OvertimeOrNot = @OvertimeOrNot
       WHERE (Team1ID = @Team1ID AND Team2ID = @Team2ID OR Team1ID = @Team2ID AND Team2ID = @Team1ID) AND GameDate = @gamedate; 
	END
	ELSE 
	   BEGIN
	       INSERT INTO Games(GameID,GameDate,Team1ID,Team2ID,StadiumID,FinalScoreTeam1,FinalScoreTeam2,WinnerID,OvertimeOrNot) VALUES (@gameID,@gamedate,@Team1ID,@Team2ID,@StadiumID,@FinalScoreTeam1,@FinalScoreTeam2,@WinnerID,@OvertimeOrNot)
	   END

-- create a view that shows the names of the stadiums where all games played were decided in
--overtime.
CREATE OR ALTER VIEW stadiumsWithGamesInOverTime AS
    SELECT St.StadiumName, G.OvertimeOrNot
	FROM Stadiums St
	INNER JOIN Games G ON St.StadiumID=G.StadiumID
	GROUP BY St.StadiumName,G.OvertimeOrNot
	HAVING G.OvertimeOrNot=1

SELECT * FROM stadiumsWithGamesInOverTime
go
-- implement a function that returns the number of teams that won all the games played on a stadium S with a score difference greater than R. where S and Rare function parameters.
CREATE FUNCTION numberOfTeams(@S INT,@R INT)
RETURNS INT
AS
BEGIN
    DECLARE @numberTeams INT
	SELECT @numberTeams = COUNT(DISTINCT WinnerID) 
	                      FROM Games 
						  WHERE StadiumID=@S 
						  AND ABS(FinalScoreTeam1-FinalScoreTeam2) > @R
	RETURN @numberTeams
END

SELECT [dbo].[numberOfTeams](4,2) AS NrOfTeams

