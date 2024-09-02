# What It Does
This project aims to deliver a test framework for the [Spartan API](https://spartaacademyapi20240530152521.azurewebsites.net/swagger/index.html). It covers a range of endpoints including all CRUD operations, tests also cover happy and sad paths. Please refer to the project board for further information: 

# Setup Notes
Clone this repo to your IDE and install dependencies listed in pom.xml.

Note: a `config.properties` file is excluded from the resources folder for security reasons. Please contact the contributors to fetch a copy.

Here are the fields that should be in your config.properties:

api_url

username

password

# Enpoints Tested

The endpoints that have been tested are listed bellow with their functions:

/Auth/login - Authorise login


/api/Courses - Get all courses

/api/Courses/{id} - Get Specific course


/api/Spartans - Get all spartans

/api/Spartans - Create a Spartan

/api/Spartans/{id} - Get specifc spartan

/api/Spartans/{id} - Delete spartan

/api/Spartans/{id} - Update spartan


# Test Results

Overall 17/23 tests passed for the 8 endpoints.
