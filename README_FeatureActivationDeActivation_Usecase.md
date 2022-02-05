# Vehicle Feature Activation/DeActivation

In Today's world, vehicles are enabled with a lot of in-built features and some features are subscription based.
Owner of the vehicle has an option to subscribe/unsubscribe the features they need.

# Use cases

We want to create below APIs as part of this user story

1) API to enable owner of a vehicle to enable/disable feature (PUT /api/update-feature)

   Steps:

   	1) Validate if the VIN is valid by calling the API {Host}/api/v1/isValidVehicle/{VIN}
   	2) Validate if the given feature is supported in the vehicle by calling the API {Host}/api/v1/isValidFeature/{VIN}/{FTRCode}
   	3) Check if the given feature is already enabled/disabled in the vehicle then throw an Error saying "Feature is already enabled/disabled in the vehicle" (based on the input)
   	4) If no, enable/disable the given feature and update the status in Audit table.


2) API to list the features enabled in the given vehicle (GET /api/list-enabled-features/<VIN>)

   Steps:

       1) Validate if the VIN is valid by calling the API {Host}/api/v1/isValidVehicle/{VIN}
       2) Check the list of features enabled in the vehicle and return
       4) If no features are enabled, return "No features are enabled for the vehicle"


3) API to list the history of the features (GET /api/get-feature-history/{VIN})

   Steps:

   	1) Validate if the VIN is valid by calling the API {Host}/api/v1/isValidVehicle/{VIN}
   	2) Return the history of features enabled/disabled for  the vehicle.


# Creating Spring boot Application

# Spring Initializr

You can use this pre-initialized project and click Generate to download a ZIP file. This project is configured to fit the examples in this tutorial.
To manually initialize the project:
1) Navigate to https://start.spring.io. This service pulls in all the dependencies you need for an application and does most of the setup for you.
2) Choose either Gradle or Maven and the language you want to use. This guide assumes that you chose Java. 
3) Click Dependencies and select Spring Web.
4) Click Generate. 
5) Download the resulting ZIP file, which is an archive of a web application that is configured with your choices.
      If your IDE has the Spring Initializr integration, you can complete this process from your IDE.
      You can also fork the project from Github and open it in your IDE or other editor.

# Intellij 

1) From the main menu, select File | New | Project.
2) In the left pane of the New Project wizard, select Spring Initializr.
3) Specify a name for the project: spring-boot-tutorial. 
   From the Project SDK list, select Download JDK and download the latest version of Oracle OpenJDK (as of writing this tutorial, it was version 16.0.2). 

Select the latest Java version.

![img_3.png](img_3.png)

Click Next to continue.

4) Select the Spring Web dependency under Web. This dependency is required for any web application that uses Spring MVC.

![img_2.png](img_2.png)

Click Finish to generate and set up the project.

# Java Setup

Check if Java installed in the machine

```
java -version
openjdk version "1.8.0_292"
OpenJDK Runtime Environment Corretto-8.292.10.1 (build 1.8.0_292-b10)
OpenJDK 64-Bit Server VM Corretto-8.292.10.1 (build 25.292-b10, mixed mode)
```


# Maven Setup

Check if maven installed in the machine

```
mvn -v
Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T14:33:14-04:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```

## Useful Maven commands

The project makes use of Gradle and uses the Gradle wrapper to help you out carrying some common tasks such as building
the project or running it.

### Clean Project 

cleans the maven project by deleting the target directory.

```console
$ ./mvn clean
```

### Compile Source

Compiles the project, runs the test and then creates an executable JAR file

```console
$ ./mvn compiler:compile
```
###  Package 

Builds the maven project and packages them into a JAR, WAR, etc.

```console
$ ./mvn package
```

### Install

Builds the maven project and installs the project files (JAR, WAR, pom.xml, etc) to the local repository.

```console
$ ./mvn package
```

### Run Test cases

Run the unit test cases in application

```console
$ ./mvn test
```

### Start Application

To Start the Spring boot application

```console
$ ./mvn spring-boot:run
```

## APIs to implement

### 1) List Vehicle features

Endpoint

```text
GET /api/list-enabled-features/<VIN>
```
Parameters

| Parameter      | Description                              |
| -------------- | ---------------------------------------- |
|    `VIN`       | Vehicle Identification Number			|

Retrieving readings using CURL

```console
$ curl "http://localhost:8080/api/list-enabled-features/1FM111222333ABCDE"
``

Example output

```json
[
  {
    "activatedDate": "2020-11-29T08:00:00Z",
    "featureCode": "RLUL",
	"featureDesc":"Remote Lock/Unlock"
  },
  {
    "activatedDate": "2020-11-29T08:00:00Z",
    "featureCode": "RSS",
	"featureDesc":"Remote Start/Stop"
  },
 
]
```

### 2) Update (Enable/Disable) Vehicle feature

Endpoint

```text
PUT /api/update-feature
```

Example of request body

```json
{
  "VIN": <VIN>,
  "features": [
    {
      "ftrCode": <ftr_code>,
      "enable": "Y<enable_flag>"
    },
	{
      "ftrCode": <ftr_code>,
      "enable": "<enable_flag>"
    }
  ]
}
```
Example request

```json
{
  "VIN": "WDD2906611A004817",
  "features": [
    {
      "ftrCode": "RLUL",
      "enable": "Y"
    },
	{  
      "ftrCode": "RSS",
      "enable": "N"
    }
  ]
}
```
Parameters

| Parameter      | Description                                           |
| -------------- | ----------------------------------------------------- |
| `VIN`          | Vehicle Identification Number                         |
| `ftr_code`     | Feature code                                          |
| `enable_flag`  | Y for enable and N for disable                        |

Example response

```json
{
  "VIN": "WDD2906611A004817",
  "features": [
    {
      "ftrCode": "RLUL", 
       "statusCode": "0",
      "detail": "Success"
    },
	{  
      "ftrCode": "RSS", 
      "statusCode": "10",
      "detail": "Feature already enabled"
    }
  ]
}
```
Possible status Codes

| Status Code | Status                              |
|-------------|-------------------------------------|
| 0           | Success                             |
| 10          | Feature is already enabled          |
| 20          | Feature is already disabled         |
| 30          | Feature is not supported by vehicle |
| 40          | Invalid Feature Code                |
   



### 3) Get Feature History

Endpoint

```text
GET /api/get-feature-history/{VIN}
```

Example response

```json
[
   {

      "featureCode": "RLUL",
      "featureDesc": "Remote Lock/Unlock",
      "activatedDate": "2020-11-29T08:00:00Z",
      "DeActivatedDate": null
   },
   {
      "featureCode": "RSS",
      "featureDesc": "Remote Start/Stop",
      "activatedDate": "2020-11-29T08:00:00Z",
      "DeActivatedDate": "2020-11-29T08:00:00Z"
   }
]
```

Note:
	
Start the "Vehicle-data-provider" application as a separate instance and call the supporting APIs required.	
	
	
# Guidelines

1) Follow the Java coding standards
2) Use H2 in memory DB for storing the data
3) Write unit testcases for all the classes/methods
4) Write comments wherever required
5) Use logger
