# nab-automation-test

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Make sure you have installed all of the following prerequisites on your development machine:
* JDK 8 - [Download & install JDK 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)
* Maven - [Download & Install Maven](http://maven.apache.org/)
* ChromeDriver - [Download ChromeDriver](https://chromedriver.chromium.org/). Make sure the driver and chrome browser version are the same.
* GeckoDriver - [Download GeckoDriver](https://github.com/mozilla/geckodriver/releases)
* JAVA_HOME System variable - [Setup & Verification](https://mkyong.com/java/how-to-set-java_home-on-windows-10/)
* MAVEN_HOME System variable - [Setup & Verification](https://mkyong.com/maven/how-to-install-maven-in-windows/)

### Test Configuration

#### Driver configuration
* Windows: Copy chromedriver.exe and geckodriver.exe to `src/main/resources/driver`.
* Mac: Copy chromedriver and geckodriver.exe to `/Users/driver`. 
* Create new driver folder if it does not exist.

### Run Test

#### Test Configuration
From your terminal/command prompt, change directory to folder where you clone the project.
```
mvn clean test
or:
mvn clean test -DsuiteXmlFile=test-suite.xml
```

#### Execute test with browser
You can select the browser to perform the test by changing the parameter at `test-suite.xml` and update`value`.
```
<parameter name="browser" value="chrome"/>
<parameter name="isHeadless" value="false"/>
```

#### Execute by multiple thread
If you have multiple test cases or multiple classes and you want to run them at the same time. Go to `test-suite.xml` and update:
```
<test name="Suite test" parallel="methods" thread-count="1">
```
* Parallel can be methods, tests, classes or instances.
* Thread-count is number of instances running to execute multiple tests simultaneously or in parallel.

#### Reporting
- You can generate a report using one of the following command:

```
mvn allure:serve
```
Report will be generated into temp folder. Web server with results will start.
- Or
```
mvn allure:report
```
Report will be generated tо directory: `target/site/allure-maven/index.html`
  
