## Project
The NasaApiTestFramework project tests the Sound REST API published by NASA for public consumption. The API documentation is available at https://api.nasa.gov/api.html#sounds.

## Pre-requisites
1. Java
2. Maven

## Usage
1. To run the tests, go to project-base-directory and use command "mvn clean install"
2. To run the tests and generate an html report, go to project-base-directory use command "mvn clean site". The reports will be generated at project-base-directory/target/site/surefire-report.html

## Test Cases

### Testing the parameter 'q' (Search text to filter results)
1. testDefaultSearchString - If parameter q is not set in the GET request, it should default to "" and return the results accordingly.
   Expected Result - The returned results should not have any text filter applied.

2. testEmptySearchString - Set the parameter q to "".
   Expected Result - The returned results should not have any text filter applied.

3. testSpecialCharsSearchString - Set the parameter q to a string that includes special characters such as '?', '&', '=', '/', etc. since these are a part of the request URI.
   Expected Result - Relevant results should be returned. Request should not encounter any error.

4. testValidSearchString - Set the parameter q to a string that returns some valid results.
   Expected Result - Relevant results should be returned

### Testing the parameter 'limit' (int value that specifies the maximum number of results to be returned by API)
1. testDefaultLimit - If parameter 'limit' is not set, it defaults to a value of 10.
   Expected Result - A list of 10 results should be returned

2. testLimitSetToZero - Set 'limit' to 0
   Expected Result - An empty list should be returned

3. testLimitSetToOne - Set 'limit' to 1
   Expected Result - Only 1 sound result should be returned.

4. testLimitSetToNegativeInteger - Set 'limit' to a negative integer
   Expected Result - The API should handle the scenario and return an error.

5. testLimitSetToHighestInteger - Set 'limit' to INTEGER.MAX_VALUE. This is to test edge case
   Expected Result - The size of results returned should be less than equal to INTEGER.MAX_VALUE

6. testLimitSetToLowestInteger - Set 'limit' to INTEGER.MIN_VALUE. This is to test edge case
   Expected Result - The API should handle the scenario and return an error.

7. testLimitSetToEmpty - Set 'limit' to ""
   Expected Result - The API should handle the scenario and return an error.

8. testLimitSetToString - Set 'limit' to some string value
   Expected Result - The API should handle the scenario and return an error.

### Testing the parameter api_key (a unique key that a user can sign-up for, to use the API. Together with user's IP address, it is used to restrict number of API calls user can make)
1. testDefaultApiKey - If parameter 'api_key' is not set, it defaults to DEMO_KEY
   Expected Result - The API should return results

2. testEmptyApiKey - Set the 'api_key' to ""
   Expected Result - The API should return HTTP code 403.

3. testValidApiKey - Set a valid 'api_key'
   Expected Result - The API should return results

4. testInvalidApiKey - Set an invalid 'api_key'
   Expected Result - The API should return HTTP code 403.

5. testDemoApiKey - Set 'api_key' to 'DEMO_KEY'
   Expected Result - The API should return results

6. testDemoApiKeyHourlyLimit - Make more than 30 API calls using DEMO_KEY
   Expected Result - The API should return HTTP code 503 for next 1 hour and then resume working properly

7. testValidApiKeyHourlyLimit - Make more than 1000 API calls using a valid 'api_key'
   Expected Result - The API should return HTTP code 503 for next 1 hour and then resume working properly

### Other tests
1. Test for all possible errors that the API returns