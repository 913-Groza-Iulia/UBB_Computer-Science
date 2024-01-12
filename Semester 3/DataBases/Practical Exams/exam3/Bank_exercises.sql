CREATE DATABASE Bank;

USE Bank;

GO

CREATE TABLE Customers(
   Cid INT PRIMARY KEY NOT NULL,
   CName VARCHAR(100),
   DateBirth DATE
);

CREATE TABLE BankAccounts(
   BAid INT PRIMARY KEY NOT NULL,
   IBAN VARCHAR(34),
   CurrentBalance INT,
   Cid INT, 
   FOREIGN KEY(Cid) REFERENCES Customers(Cid) ON DELETE CASCADE
);

CREATE TABLE Cards(
   CardNumber VARCHAR(19) PRIMARY KEY NOT NULL,
   CVV VARCHAR(3),
   BAid INT,
   FOREIGN KEY(BAid) REFERENCES BankAccounts(BAid) ON DELETE CASCADE
);

CREATE TABLE ATMs(
   ATMid INT PRIMARY KEY NOT NULL,
   Address VARCHAR(50)
);

CREATE TABLE Transactions(
   Tid INT PRIMARY KEY NOT NULL,
   ATMid INT,
   CardNumber VARCHAR(19),
   Balance INT,
   Time DATETIME,
   FOREIGN KEY(ATMid) REFERENCES ATMs(ATMid) ON DELETE CASCADE,
   FOREIGN KEY(CardNumber) REFERENCES Cards(CardNumber) ON DELETE CASCADE
);

INSERT INTO Customers(Cid, CName, DateBirth) VALUES
(1,'Gabi Mircea', '1993-01-17'),
(2, 'Dan Suciu', '1980-02-20'),
(3, 'Maluma Baby', '1985-10-09'),
(4, 'Dan Badea', '1890-11-26');
SELECT * FROM Customers

INSERT INTO BankAccounts(BAid,IBAN,CurrentBalance,Cid) VALUES
(1, 'RO54RZBR3858514268674891', 2000, 1),
(2, 'RO04RZBR1711787311512738', 10000, 1),
(3, 'RO85PORL5457812918323614', 30000, 2),
(4, 'RO30PORL3617216244955489', 500, 3),
(5, 'RO91RZBR7969869465119449', 100, 4);
SELECT * FROM BankAccounts

INSERT INTO Cards(CardNumber, CVV, BAid) VALUES
('4617423350119807', 807, 1),
('4768925113901070', 070, 1),
('4779894410036856', 856, 2),
('4632158562688451', 451, 3),
('4703414907155415', 415, 4),
('4429681220252896', 896, 5);
SELECT * FROM Cards

INSERT INTO ATMs(ATMid,Address) VALUES
(1, 'Strada Cv 23'),
(2, 'Strada Principala 156'),
(3, 'Basic street name 6'),
(4, 'Bogdan Petriceicu');
SELECT * FROM ATMs

INSERT INTO Transactions(Tid,ATMid,CardNumber, Balance, Time) VALUES
(1, 1, '4617423350119807', 200, '05-01-2020'),
(2, 1, '4768925113901070', 200, '05-01-2020'),
(3, 1, '4429681220252896', 200, '05-01-2020'),
(4, 2, '4703414907155415', 200, '05-01-2020'),
(5, 1, '4617423350119807', 400, '06-01-2020'),
(6, 2, '4768925113901070', 300, '04-01-2020');
SELECT * FROM Transactions
GO

-- Implement a stored procedure that receives a card and deletes all the transactions related to that card.
CREATE OR ALTER PROCEDURE deleteTransactions(@cardNumber VARCHAR(19)) AS
   IF NOT EXISTS(SELECT 1 FROM Cards WHERE CardNumber=@cardNumber)BEGIN
     PRINT 'The card does not exist'
   END

   DELETE FROM Transactions WHERE CardNumber LIKE @cardNumber
GO

--delete the card from transactions
EXEC deleteTransactions '4768925113901070'
SELECT * FROM Transactions

--if the card doesn't exist
EXEC deleteTransactions '5678965673456890'
GO

-- Create a view that shows the card numbers which were used in transactions at all the ATMs.
CREATE VIEW CardsUsedAtAllATMs AS
   SELECT T.CardNumber
   FROM Transactions T
   JOIN ATMs A ON T.ATMID = A.ATMID
   GROUP BY T.CardNumber
   HAVING COUNT(DISTINCT A.ATMID) = (SELECT COUNT(*) FROM ATMs);
GO
SELECT * FROM CardsUsedAtAllATMs

--Implement a function that lists the cards (number and CVV code) that have the total transactions sum greater than 2000 lei
CREATE FUNCTION listCards(@sum INT)
RETURNS TABLE
AS
RETURN
    SELECT c.CardNumber, c.CVV
	FROM Cards c
	INNER JOIN Transactions t ON c.CardNumber=t.CardNumber
	GROUP BY c.CardNumber, c.CVV
	HAVING SUM(t.Balance) > @sum
GO

SELECT * FROM listCards(400)