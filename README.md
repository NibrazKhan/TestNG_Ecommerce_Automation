# TestNG_Ecommerce_Automation
### This is a complete project where an [E-commerce site](http://automationpractice.com/) site is automated by writing test suites by using selenium-webdriver and testng as testing framework.
The following key modules/pages are automated:
- **SignUp** 
- **Login**
- **Search**
- **Cart**
- **Checkout**</br>

Key test cases(total 51) are written for each module and test suites created including the positive and negative test cases.</br>A state-transition flow of test-cases are designed and run like a user buying a product from an e-commerce site.
</br>The test runner codes can be extracted from this [link](https://github.com/NibrazKhan/TestNG_Ecommerce_Automation/tree/main/src/test/java/TestRunner).</br>
The module test case steps code can be extracted from this [link](https://github.com/NibrazKhan/TestNG_Ecommerce_Automation/tree/main/src/test/java/TestRunner).</br>
**The test cases are written following standard test case format in this excel file:**
[Test_cases.xlsx](https://github.com/NibrazKhan/TestNG_Ecommerce_Automation/files/9394338/Test_cases.xlsx) </br>
View the excel file from this [link](https://docs.google.com/spreadsheets/d/1uAqOdIspQ-dDN8d99kBDqDSwsI-ATvWx/edit?usp=sharing&ouid=103007158823477190559&rtpof=true&sd=true).

### Technology: </br>
- Tool: Selenium Webdriver
- IDE: Intellij IDEA
- Build tool: Gradle
- Language: Java
- Testing Framework : TestNG

### Prerequisite: </br>
- Need to install jdk 11, gradle and allure
- Configure Environment variable for jdk 11, gradle and allure
- Clone this project and unzip it
- Open the project folder
- Double click on "build.gradle" and open it through IntellIJ IDEA
- Let the project build successfully
- Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command:
 ```
 gradle clean test 
 ```
- Selenium will open the browser and start automating.
- After automation to view allure report , give the following commands:
 ```
allure generate allure-results --clean -o allure-report
allure serve allure-results
 ```

**Below is my allure overview report**:

![Overview](https://user-images.githubusercontent.com/55280106/185918375-8ac0df03-ee09-4215-a6df-ecdbcf9fd5cd.png)

**Here are the suites of this project**:

![suites](https://user-images.githubusercontent.com/55280106/185918539-c40ea3e9-dd3f-4e56-b223-adcf80fcbf25.png)

**Here is the overall walkthrough of the project:** [Video](https://drive.google.com/file/d/1fx4bMzsdBhugkUjqPKAI1z9UCwFo8W_-/view?usp=sharing)</br>
**You can watch the sanity testing of Checkout module from here:** [Video](https://drive.google.com/file/d/1nsk8-EKik-BnvjvH4mSwOwV7COD7dsas/view?usp=sharing)


 
