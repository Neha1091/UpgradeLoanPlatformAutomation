# Upgrade loan platform automation
	Web side of this project is designed using selenium page object model framework with Java, TestNG and Maven 
	API side of this project is designed using Rest Assured, TestNG and Maven  


### Dependency:
	Java 8
	Maven
	Mac Chrome driver :https://chromedriver.storage.googleapis.com/index.html?path=95.0.4638.54/

### libraries used:
	Selenium
	TestNG
	log4j

### Steps to clone execute the tests:
```
1. git clone https://github.com/Neha1091/UpgradeLoanPlatformAutomation
2. cd UpgradeLoanPlatformAutomation
3. update TestBase class at line 45 with local chrome driver location
4. Tests can be executed from IDE by running "/upgrade-loan-platform-automation/src/test/resources/testng.xml" file.
5. Or from terminal with command : "mvn clean test"