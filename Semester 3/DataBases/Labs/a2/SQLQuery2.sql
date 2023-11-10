USE Circ;
GO

INSERT INTO Performers(PerformerID, FirstName, LastName, DateOfBirth, Specialty) VALUES 
('1', 'Mickey', 'Mouse', '1995-05-15', 'Contorsionist'),
('2', 'John', 'Cena', '1977-04-23', 'Juggler'),
('3', 'Woman', 'WithABeard', '1989-12-19', 'Acrobat'),
('4', 'Florin', 'Salam', '2000-10-06', 'Magician'),
('5', 'Benny', 'Banana', '1993-07-18', 'Clown'),
('6', 'Hannah', 'Montana', '1997-04-26', 'Hypnotist'),
('7', 'Leonardo', 'DiCaprio', '1999-01-13', 'Fire Breathing Expert'),
('8', 'Jack', 'Sparrow', '1997-08-29', 'Lion Trainer'),
('9', 'Catalin', 'Bord', '1990-02-28', 'Tiger Trainer'),
('10', 'Celine', 'Dion', '2000-06-17', 'Birds Trainer'),
('11', 'Happy', 'Slappy', '1997-03-02', 'Contorsionist'),
('12', 'Grandma', 'Clown', '1963-08-31', 'Clown'), 
('13', 'Ovidiu', 'Groza', '1987-06-22', 'Tiger Trainer');
SELECT * FROM Performers;


INSERT INTO Animals(AnimalID, AnimalName, AnimalAge, AnimalSPecie) VALUES 
('1', 'Cici', '5', 'Elephant'),
('2', 'Shere Khan', '10', 'Tiger'),
('3', 'Carot', '8', 'Parot'),
('4', 'Marcel', '2', 'Monkey'),
('5', 'Nala', '4', 'Lion'),
('6', 'Dumbo', '6', 'Elephant'),
('7', 'LongNeck', '3', 'Giraffe'),
('8', 'Sammy', '9', 'Seal'),
('9', 'Yogi', '1', 'Baby Bear'),
('10', 'Benny', '6', 'Bear');

INSERT INTO Animals(AnimalID, AnimalName, AnimalAge, AnimalSPecie) VALUES 
('11', 'Mimi', '4', 'Parot'),
('12', 'Remus', '8', 'Tiger'),
('13', 'King', '6', 'Lion'),
('14', 'Sammy', '5', 'Parot');
SELECT * FROM Animals;


INSERT INTO AnimalFood(FoodID, FoodName, FoodType, FoodQuantity, FoodCost, AnimalID) VALUES
('1', 'Hay', 'Grass', '5 kg', '4', '1'),
('2', 'Bananas', 'Fruit', '7 kg', '6', '4'),
('3', 'Steak', 'Meat', '15 kg', '10', '2'),
('4', 'Seeds', 'Grains', '8 kg', '5', '3'),
('5', 'Chicken', 'Poultry', '10 kg', '8', '2'),
('6', 'Berries', 'Fruit', '4 kg', '4', '9'),
('7', 'Fish', 'Meat', '24 kg', '11', '10'),
('8', 'Squids', 'Meat', '12 kg', '7', '8'),
('9', 'Leaves', 'Idk', '6 kg', '9', '7'),
('10', 'Steak', 'Meat', '22 kg', '14', '5');
SELECT * FROM AnimalFood;

INSERT INTO AnimalFood(FoodID, FoodName, FoodType, FoodQuantity, FoodCost, AnimalID) VALUES --violates referential integrity constraints
('11', 'Hay', 'Grass', '5 kg', '4', '34');


INSERT INTO Locations(LocationID, LocName, LocAdress, LocCapacity, ContactPhone) VALUES
('1', 'Cluj Arena', 'Crisului 19', '25000', '07305561'),
('2', 'BT Arena', 'Idk 3', '20000', '07563972'),
('3', 'Wonderland', 'Brasovului 6', '7000', '07294760'),
('4', 'Star Events', 'Vladut Voievodul 7', '5000', '078402947'),
('5', 'Piata Marasti', 'Piata Marasti 3', '3000', '07257304'),
('6', 'Oktober Fest', 'Festivalului 12', '17000', '07465240'),
('7', 'Untold', 'Parcul Central 1', '26400', '07975253'),
('8', 'Lux Divina', '13 Decembrie 96', '4800', '07590246'),
('9', 'Club Mood', 'Piata Sfatului 14', '26000', '07150498'),
('10', 'Stardust Pavilion', 'Cevaului 84', '10000', '07068364');
SELECT * FROM Locations;

UPDATE Locations SET LocCapacity = LocCapacity+400 Where LocName LIKE '_%a';
SELECT * FROM Locations;

UPDATE AnimalFood SET FoodCost = FoodCost+1 WHERE (FoodName = 'Hay' AND FoodType = 'Grass') OR (FoodName = 'Berries' AND FoodType = 'Fruit');
SELECT * FROM AnimalFood;

UPDATE Animals SET AnimalAge = 7 WHERE AnimalAge BETWEEN 7 AND 9;
SELECT * FROM Animals;

DELETE FROM Performers WHERE PerformerID IN (9);
SELECT * FROM Performers;

DELETE FROM Locations WHERE LocName = 'Piata Marasti';
SELECT * FROM Locations;

INSERT INTO Acts(ActID, ActName, ActType, PerformerID) VALUES
('1', 'Funny Clown Show', 'Comedy', '12'),
('2', 'Lion Tamer Performance', 'Animal Show', '8'),
('3', 'Tiger Show', 'Animal Show', '13'),
('4', 'Daring Acrobatics', 'Acrobatics', '3'),
('5', 'Inferno Fury', 'Fire Breathing', '7'),
('6', 'Magic Extravaganza', 'Magic', '4'),
('7', 'Hypnotic Illusions', 'Hypnosis', '6'),
('8', 'Twisted Wonders', 'Contorsionism', '11'),
('9', 'Extreme Flexibility Showcase', 'Contorsionism', '1'),
('10', 'Juggling Extravaganza', 'Juggler', '2'),
('11', 'Parrot Paradise', 'Animal Show', '10');
SELECT * FROM Acts;

INSERT INTO Shows (ShowID, ShowDate, ShowTime, TicketSales, ActID) VALUES
('1', '2023-11-01', '15:00', 1000, '1'),
('2', '2023-11-05', '18:30', 800, '2'),
('3', '2023-11-10', '20:00', 1200, '3'),
('4', '2023-11-15', '14:30', 1500, '4'),
('5', '2023-11-20', '19:00', 900, '5'),
('6', '2023-11-25', '16:15', 1100, '6'),
('7', '2023-11-30', '17:45', 1300, '7'),
('8', '2023-12-05', '14:00', 1000, '8'),
('9', '2023-12-10', '20:30', 800, '9'),
('10', '2023-12-15', '19:15', 1200, '10');
SELECT * FROM Shows;

INSERT INTO AnimalCare (CareID, CareDate, CareType, CareCost, AnimalID) VALUES
('1', '2023-11-05', 'Veterinary Checkup', 200, '2'),
('2', '2023-11-12', 'Feeding and Grooming', 150, '5'),
('3', '2023-11-18', 'Health Inspection', 180, '3'),
('4', '2023-11-25', 'Dental Cleaning', 250, '4'),
('5', '2023-12-02', 'Training and Exercise', 120, '8'),
('6', '2023-10-21', 'Dental Cleaning', 170, '6'),
('7', '2023-11-22', 'Dental Cleaning', 175, '10');
SELECT * FROM AnimalCare;

INSERT INTO Tickets (TicketID, SeatNumber, PurchaseDate, CustomerName, CustomerEmail, TicketPrice, ShowID) VALUES
('1', 'A101', '2023-11-01', 'Alice Johnson', 'alice@example.com', 25.00, '1'),
('2', 'B204', '2023-11-05', 'Bob Smith', 'bob@example.com', 20.00, '2'),
('3', 'C305', '2023-11-10', 'Charlie Brown', 'charlie@example.com', 30.00, '3'),
('4', 'D402', '2023-11-15', 'David Davis', 'david@example.com', 35.00, '4'),
('5', 'E501', '2023-11-20', 'Eva Wilson', 'eva@example.com', 22.50, '5'),
('6', 'A605', '2023-11-25', 'Frank Garcia', 'frank@example.com', 18.50, '6'),
('7', 'G708', '2023-11-30', 'Eve Robinson', 'eve@example.com', 27.50, '7'),
('8', 'H801', '2023-12-05', 'Henry Lee', 'henry@example.com', 21.00, '8'),
('9', 'I906', '2023-12-10', 'Emerson Moore', 'emerson@example.com', 24.50, '9'),
('10', 'C1001', '2023-12-15', 'James Taylor', 'james@example.com', 28.00, '10');
SELECT * FROM Tickets;

INSERT INTO Expenses (ExpenseID, ExpenseCategory, ExpenseAmount, ShowID) VALUES
('1', 'Venue Rental', 5000, '1'),
('2', 'Props and Decor', 1200, '2'),
('3', 'Advertising', 800, '3'),
('4', 'Costumes', 1500, '4'),
('5', 'Sound and Lighting', 2000, '5'),
('6', 'Catering', 600, '6'),
('7', 'Transportation', 900, '7'),
('8', 'Insurance', 750, '8'),
('9', 'Staff Salaries', 3500, '9'),
('10', 'Miscellaneous', 300, '10'),
('11', 'Venue Rental', 4800, '8'),
('12', 'Catering', 4800, '4');
SELECT * FROM Expenses;

INSERT INTO Feedback (FeedBackID, CustomerName, FeedBackText, FeedBackRating, ShowID) VALUES
('1', 'Alice Johnson', 'Great show! I loved it!', 4.5, '1'),
('2', 'Bob Smith', 'The performance was amazing!', 4.8, '2'),
('3', 'Charlie Brown', 'The circus exceeded my expectations!', 4.7, '3'),
('4', 'David Davis', 'The acrobatics were mind-blowing!', 4.9, '4'),
('5', 'Eva Wilson', 'Lion taming was fantastic!', 4.6, '5'),
('6', 'Frank Garcia', 'Hypnotic illusions were unbelievable!', 4.4, '6'),
('7', 'Eve Robinson', 'The fire breathing act was thrilling!', 4.3, '7'),
('8', 'Henry Lee', 'Magic extravaganza was magical!', 4.8, '8'),
('9', 'Emerson Moore', 'The flexibility showcase was impressive!', 4.7, '9'),
('10', 'James Taylor', 'Parrot show was fun and entertaining!', 4.5, '10'),
('11', 'Maria Johnson', 'The circus was fantastic!', 4.9, '2'),
('12', 'Samuel White', 'The circus was a great family experience!', 4.7, '5'),
('13', 'Olivia Brown', 'The animal shows were delightful!', 4.6, '3');
SELECT * FROM Feedback;

INSERT INTO ShowLocation (ShowID, LocationID) VALUES
('1', '1'), 
('2', '7'), 
('5', '6'), 
('3', '3'),
('7', '1'), 
('8', '2'), 
('9', '4'),
('4', '8'),
('6', '9');
SELECT * FROM ShowLocation;

--a----------------------------------------------------------------------------------------------------------------------
SELECT A.AnimalID --display the animals who are older than 5 yo or who where dental cleaned
FROM Animals A
WHERE A.AnimalAge > 5 UNION ALL SELECT A.AnimalID
                                FROM AnimalCare A
								WHERE A.CareType='Dental Cleaning';

SELECT A.FoodName, A.FoodType --display the food for animals that cost less than 5 or those whose quantity is 15 kg or whose animalID greater than 6
FROM AnimalFood A
WHERE A.FoodCost <=5  UNION SELECT A1.FoodName, A1.FoodType
                            FROM AnimalFood A1
							WHERE A1.FoodQuantity = '15 kg' OR A1.AnimalID > 6;

--b------------------------------------------------------------------------------------------------------------------------
SELECT L.LocName, L.LocCapacity --display the locations who can host more than 10.000 people or those whose name starts with 'S'
FROM Locations L
WHERE L.LocCapacity <11000 INTERSECT SELECT L.LocName, L.LocCapacity
                                     FROM Locations L
									 WHERE L.LocName LIKE 'S%';

SELECT  DISTINCT P.FirstName, P.LastName --display the performers whose speciality is clowning or contorsionism
From Performers P
WHERE P.Specialty IN ('Clown', 'Contorsionist');

--c-------------------------------------------------------------------------------------------------------------------------------
SELECT A.ActName, A.ActType --display the animal shows whose performer number is greater than 10
FROM Acts A
WHERE A.ActType='Animal Show' EXCEPT SELECT A.ActName, A.ActType
                                     FROM Acts A
									 WHERE A.PerformerID < 10; 

SELECT S.ShowID --display the shows that doesn't spend money on revenue rentals or for catering
FROM Shows S
WHERE S.ShowID NOT IN (SELECT E.ShowID
                       FROM Expenses E
					   WHERE (E.ExpenseCategory='Venue Rental') OR (E.ExpenseCategory='Catering'));

--d-----------------------------------------------------------------------------------------------------------------------------
SELECT S.ShowID, S.ShowDate, T.CustomerName, T.PurchaseDate, E.ExpenseAmount --display the details about each show (the customers who bought tickets, the purchase date, and the total expenses used)
FROM  Shows S
INNER JOIN Tickets T ON S.ShowID=T.ShowID
INNER JOIN Expenses E ON T.ShowID=E.ShowID; 

SELECT P.PerformerID, P.FirstName, P.LastName, A.ActName, A.ActType,A.ActID --display the performers and the acts they perform in 
FROM Performers P
LEFT JOIN Acts A ON P.PerformerID=A.PerformerID;

SELECT A.AnimalID, A.AnimalName, A.AnimalSPecie, A.AnimalAge, AF.FoodName, AF.FoodType, AF.FoodQuantity --display what each animal eat 
FROM Animals A
RIGHT JOIN AnimalFood AF ON A.AnimalID = AF.AnimalID;

CREATE TABLE PerformerAct(
  PerformerID INT,
  ActID INT,
  PRIMARY KEY (PerformerID, ActID),
  FOREIGN KEY (PerformerID) REFERENCES Performers(PerformerID),
  FOREIGN KEY (ActID) REFERENCES Acts(ActID)
);

INSERT INTO PerformerAct(PerformerID, ActID) VALUES
('1', '9'), 
('2', '10'), 
('3', '4'), 
('4', '6'),
('5', '1'), 
('6', '7'), 
('7', '5'),
('8', '2'),
('10', '11'), 
('11', '8'),
('12', '1'),
('13', '3');
SELECT * FROM PerformerAct;

SELECT P.FirstName+' '+P.LastName AS 'FullNames', A.ActName, S.ShowID ,S.ShowDate, L.LocName--display the details (performers, acts, location, date) of all the shows
FROM Performers P
FULL JOIN PerformerAct PA ON P.PerformerID = PA.PerformerID
FULL JOIN Acts A ON PA.ActID = A.ActID
FULL JOIN Shows S ON A.ActID = S.ActID
FULL JOIN ShowLocation SL ON S.ShowID = SL.ShowID
FULL JOIN Locations L ON SL.LocationID = L.LocationID;

--e---------------------------------------------------------------------------------------------------------------------------------
SELECT DISTINCT P.PerformerID, P.FirstName+' '+ P.LastName AS 'FullNames' --display the performers which perform at shows with expenses greater than 1500
FROM Performers P
WHERE P.PerformerID IN (SELECT E.ShowID
                        FROM Expenses E
						WHERE E.ExpenseAmount> 1500);

SELECT DISTINCT P.PerformerID, P.FirstName+' '+ P.LastName AS 'FullNames', P.Specialty --display the performers who are associated with shows that have expenses below or equal to 2000
FROM Performers P
WHERE P.PerformerID IN (
    SELECT S.ActID
    FROM Shows S
    WHERE S.ShowID NOT IN (
        SELECT E.ShowID
        FROM Expenses E
        WHERE E.ExpenseAmount > 2000 ));

--f------------------------------------------------------------------------------------------------------------------------------------
SELECT * --display all the animals that are older than Nala
FROM Animals A
WHERE EXISTS (SELECT A2.AnimalAge
              FROM Animals A2
			  WHERE A2.AnimalName = 'Nala' AND A.AnimalAge > A2.AnimalAge);

SELECT T.TicketID, T.CustomerName --display all the customers who have provided feedback for a show
FROM Tickets T
WHERE EXISTS (SELECT *
              FROM Feedback F
			  WHERE F.CustomerName = T.CustomerName);
--g--------------------------------------------------------------------------------------------------------------------------------------
SELECT Q.ShowID,Q.LocationID --display the shows and locations that they are being held at, where the show number is between 5 and 9
FROM (SELECT *
      FROM ShowLocation S
	  WHERE S.ShowID BETWEEN 5 AND 9)Q;

SELECT Q.ShowID --display the shows which category of expenses end with 'g' 
FROM (SELECT *
      FROM Expenses E
	  WHERE E.ExpenseCategory LIKE '_%G')Q
WHERE Q.ShowID IN (SELECT S.ShowID
                   FROM Shows S);
				   
--h----------------------------------------------------------------------------------------------------------------------------------------
SELECT A.FoodType, COUNT(*) AS 'COUNT' --display the food types that were given to animals more than once 
FROM AnimalFood A
GROUP BY A.FoodType
HAVING COUNT(*)>1

SELECT TOP 4 F.FeedBackRating, COUNT(F.CustomerName) AS 'TotCountOfCustomers' --display the rating for the feedback together with the number of customers that gave each rating
FROM Feedback F
GROUP BY F.FeedBackRating
ORDER BY F.FeedBackRating;

SELECT P.Specialty, AVG(A.AnimalAge) AS AvgAnimalAge --display the average animal age for each specialty of performers that work with animals compared to the overall average animal age 
FROM Performers P
INNER JOIN Acts ON P.PerformerID = Acts.PerformerID
INNER JOIN Shows ON Acts.ActID = Shows.ActID
INNER JOIN Animals A ON Shows.ShowID = A.AnimalID
GROUP BY P.Specialty
HAVING AVG(A.AnimalAge) > (SELECT AVG(AnimalAge)
                           FROM Animals);

SELECT S.ShowID, AVG(T.TicketPrice) AS AvgTicketPrice --calculate the overall avg tickets sales for the shows with expenses higher than 2.000, then display shows with the average ticket sales higher than it
FROM Shows S
INNER JOIN Tickets T ON S.ShowID = T.ShowID
GROUP BY S.ShowID
HAVING AVG(T.TicketPrice) > (SELECT AVG(TicketPrice)
                             FROM Tickets
							 WHERE ShowID NOT IN (SELECT ShowID
                                                  FROM Expenses
                                                  WHERE ExpenseAmount > 2000));

--i-------------------------------------------------------------------------------------------------------------------------------------
SELECT A.AnimalID, A.AnimalSPecie, A.AnimalAge --display all animals that are older than all the parots
FROM Animals A
WHERE A.AnimalAge > ALL(SELECT A2.AnimalAge
                        FROM Animals A2
						WHERE A2.AnimalSPecie='Parot');

SELECT A.AnimalID, A.AnimalSPecie, A.AnimalAge --with AGGREGATION OPERATOR MAX 
FROM Animals A
WHERE A.AnimalAge > (SELECT MAX(A2.AnimalAge)
                     FROM Animals A2
				     WHERE A2.AnimalSPecie='Parot');


SELECT E.ExpenseID, E.ShowID, E.ExpenseCategory, E.ExpenseAmount --display the expenses that are not used for venue rentals
FROM Expenses E
WHERE E.ExpenseAmount <> ALL(SELECT E1.ExpenseAmount
                            FROM Expenses E1
							WHERE E1.ExpenseCategory='Venue Rental');

SELECT E.ExpenseID, E.ShowID, E.ExpenseAmount -- with NOT IN 
FROM Expenses E
WHERE E.ExpenseAmount NOT IN(SELECT E1.ExpenseAmount
                            FROM Expenses E1
							WHERE E1.ExpenseCategory='Venue Rental');


SELECT T.TicketID, T.TicketPrice, T.SeatNumber --display tickets who were more expensive than at least one ticket of price greater than 22
FROM Tickets T
WHERE T.TicketPrice > ANY(SELECT T.TicketPrice
                          FROM Tickets T
						  WHERE T.TicketPrice>22);

SELECT T.TicketID, T.TicketPrice, T.SeatNumber --with AGGREGATION OPERATOR MIN
FROM Tickets T
WHERE T.TicketPrice > (SELECT MIN(T.TicketPrice)
                          FROM Tickets T
						  WHERE T.TicketPrice>22);

SELECT F.FeedBackID, F.CustomerName, F.FeedBackRating --display feedback rating that is equal to at least one of the ratings greater than 4.7 from other feedback ratings
FROM Feedback F
WHERE F.FeedBackRating = ANY(SELECT F.FeedBackRating
                             FROM Feedback F
							 WHERE F.FeedBackRating>4.7);

SELECT F.FeedBackID, F.CustomerName, F.FeedBackRating -- with IN 
FROM Feedback F
WHERE F.FeedBackRating IN (SELECT F.FeedBackRating
                             FROM Feedback F
							 WHERE F.FeedBackRating>4.7);