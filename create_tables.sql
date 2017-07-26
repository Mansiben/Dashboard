---Creating Database
USE Master


IF EXISTS (SELECT name FROM sysdatabases
WHERE name = 'Tim')
	BEGIN
		   ALTER DATABASE Tim 
            SET SINGLE_USER
            WITH ROLLBACK IMMEDIATE;
		DROP DATABASE Tim
	END	

ELSE
	BEGIN
		PRINT '.....'
	END
	CREATE DATABASE Tim

GO

IF EXISTS (SELECT NAME FROM sysobjects
WHERE NAME='DaysOfWeeks')
DROP TABLE DaysOfWeeks;

IF EXISTS (SELECT NAME FROM sysobjects
WHERE NAME = 'Advertisement_Tracking')
DROP TABLE Advertisement_Tracking;

IF EXISTS (SELECT NAME FROM sysobjects
WHERE NAME = 'Individual_Sales')
DROP TABLE Individual_Sales;

IF EXISTS (SELECT NAME FROM sysobjects
WHERE NAME='Expenses')
DROP TABLE Expenses;

IF EXISTS (SELECT NAME FROM sysobjects
WHERE NAME='Cash_Flow')
DROP TABLE Cash_Flow;


---Creating Tables

USE Tim
CREATE TABLE DaysOfWeeks
(
	dayId INT IDENTITY NOT NULL PRIMARY KEY,
	NameOfDay VARCHAR(20) 

)

CREATE TABLE Advertisement_Tracking(
Id INT IDENTITY NOT NULL PRIMARY KEY,
DayId INT,
CONSTRAINT DayId_fk
FOREIGN KEY(DayId) REFERENCES DaysOfWeeks(dayId),
cost_per_day VARCHAR(5),
number_clicks INT ,
conversation_rate VARCHAR(3)
)

CREATE TABLE Individual_Sales(
sales_Id INT IDENTITY NOT NULL PRIMARY KEY,
DayId INT,
CONSTRAINT DayId1_fk
FOREIGN KEY(DayId) REFERENCES DaysOfWeeks(dayId),
cups_Coffee INT ,
hot_Beverages INT,
cold_Beverages INT,
num_meal INT,
num_Sandwich INT,
customerName VARCHAR(20),
coffeeTypes VARCHAR(50),
size VARCHAR(50)
)

CREATE TABLE Expenses(
expense_Id INT IDENTITY NOT NULL PRIMARY KEY,
DayId INT,
CONSTRAINT DayId2_fk
FOREIGN KEY(DayId) REFERENCES DaysOfWeeks(dayId),
extra_cups INT,
num_Trucks INT,
maintenance_of_machine INT,
customerName VARCHAR(20),
salary_of_emplyoees INT,
NameofMonth VARCHAR(50),
city VARCHAR(50),
WhichYear VARCHAR(50)
)

CREATE TABLE Cash_Flow(
cash_Id INT IDENTITY NOT NULL,
DayId INT,
CONSTRAINT DayId3_fk
FOREIGN KEY(DayId) REFERENCES DaysOfWeeks(dayId),
salesID INT,
CONSTRAINT salesID_fk
FOREIGN KEY(salesID) REFERENCES Individual_Sales(sales_Id),
money_In VARCHAR(10),
money_Out VARCHAR(10),
customerName VARCHAR(20),
profit INT,
NameofMonth VARCHAR(50),
WhichYear VARCHAR(50)
)

