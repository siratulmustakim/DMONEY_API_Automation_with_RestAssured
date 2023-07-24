# Dmoney_API_Automation_with_RestAssured
This is an API automation project on Dmoney API. RestAssured is implemented here to call the APIs. Tests are performed using TestNG, where Page Object Model (POM) structure is followed. Report is prepared with Allure-reports.

## Scenario
- Do Login by admin
- Create 2 new customers and a agent
- Give 2000 tk from System account to the newly created agent
- Deposit 1500 tk to a customer from the agent account
- Withdraw 500 tk by the customer to the agent
- Send money 500 tk to another customer
- Payment 100 tk to a merchant (01686606905) by the recipient customer
- Check balance of the recipient customer

  API is used from this collection-
  https://api.postman.com/collections/1844288-143eb923-423f-4c91-a198-fe6e56d20e35?access_key=PMAT-01GJ3CC22Q0066PJWP3T0XHQ8G

## Technology and Tools
- Rest Assured
- commons-configuration
- Jackson Databind
- Lombok
- Java Faker
- TestNG
- Java
- Gradle
- intellij IDEA
- Allure-reports

## Way to run the project
- clone this project
- hit the following command into the terminal:
  
  gradle clean test
- For generating Allure report, hit:

  allure generate allure-results --clean -output

  allure serve allure-results

## Allure Report
![overview](https://github.com/siratulmustakim/DMONEY_API_Automation_with_RestAssured/assets/46200508/a4c854e0-72b8-4635-9c87-3200053e1af5)
![behav](https://github.com/siratulmustakim/DMONEY_API_Automation_with_RestAssured/assets/46200508/47355775-1502-422e-9ae1-ad4cf0d02238)
