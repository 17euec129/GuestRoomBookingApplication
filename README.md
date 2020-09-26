# GuestRoomBookingApplication

Welcome to Guest room booking application
To access our api calls, kindly follow the below procedure

# Pre-requestie
OS : Linux or Ubuntu
Database : MysqlVersion > 5.1
Softwares : gradle & gradlew, Postman
Make sure Internet connected for first time run to download gradle dependency files

This REST Api Application was build on Spring BOOT bundled with JPA

## Step 1 :

Clone or download this repository

## Step 2 :

Run db_schema.sql file or execute the code line by line in mysql command line

## Step 3 : 
Add your database username, password and dbname in application.properties file under
build/resources/main folder

## Step 4 :

Run below command
cd guest-room-app
./gradlew bootRun

## Step 5 :

Kindly wait for few minutes to download Gradle dependency file
Once you see, Completed initialization msg in console
Hit and try the available below API Calls 


# REST API CALL LIST

# 1	Register Account	
Method Type :  POST
URL         :  http://localhost:8080/register	
DATA JSON   :
{
        "name" : "SANKAR",
        "username" : "sankar",
        "emailId" : "sankar@gmail.com",
        "mobileNumber" : 9080706050,
        "password" : ""sankar",
        "userType" : "HouseOwner"
}

Note : userType can be "HouseOwner" | "Customer"

# 2	Login	
Method Type :  GET
URL         :  http://localhost:8080/login	
DATA JSON   :
{
        "username" : "sankar",
        "password" : "sankar"
}

# 3	Logout	
Method Type :  GET
URL         : http://localhost:8080/logout

# 4	Add room
Method Type :  POST
URL         :  http://localhost:8080/addRoom	
DATA JSON   :
{
        "name" : "Room1",
        "address" : "ABC Street, Thendral Nagar, Sattur",
        "floorSize" : "240x320ft",
        "numberOfBeds" : 3,
        "maximumDays" : 10,
        "amountPerDay" : 800.00
}
# 5	Get All Room
Method Type :  GET
URL         : http://localhost:8080/room
RESPONSE SAMPLE :
[
  {
      "name" : "Room1",
      "address" : "ABC Street, Thendral Nagar, Sattur",
      "floorSize" : "240x320ft",
      "numberOfBeds" : 3,
      "maximumDays" : 10,
      "amountPerDay" : 800.00
  },
  {
      "name" : "Room2",
      "address" : "ABC Street, Thendral Nagar, Sattur",
      "floorSize" : "240x320ft",
      "numberOfBeds" : 3,
      "maximumDays" : 8,
      "amountPerDay" : 800.00
  }
]

# 6	Get one Room by ID
Method Type :  GET
URL         : http://localhost:8080/room/{id}
RESPONSE SAMPLE :
{
    "name" : "Room1",
    "address" : "ABC Street, Thendral Nagar, Sattur",
    "floorSize" : "240x320ft",
    "numberOfBeds" : 3,
    "maximumDays" : 10,
    "amountPerDay" : 800.00
}

# 7	Book room
Method Type :  POST
URL         :  http://localhost:8080/bookRoom	
DATA JSON   :
{
      "roomId" : 1,
      "startDate" : "15/10/2020",
      "endDate" : "20/10/2020"
}

