package TestRunner;

import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
//import org.junit.Test;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    Utils utils=new Utils();
    @Test(priority=9,description = "User gives valid credentials and login is successful")
    public void doLoginWithValidCreds() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        utils=new Utils();
        utils.getUserCreds(0);
//        driver.get("http://automationpractice.com/");
        boolean isLogOutFound=loginPage.doLoginWithValidCreds(utils.getEmail(), utils.getPassword());
        Assert.assertEquals(isLogOutFound,true);
        Allure.description("After giving valid credintials of the user, user will be able to successfully login " +
                "and after login logout button will be displayed");
    }
    @Test(priority = 1,description = "Registered Email but incorrect Password Given")
    public void doLoginWithInvalidCreds() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        utils.getUserCreds(1);
        driver.get("http://automationpractice.com/");
        String validationMessage=loginPage.doLoginWithInvalidCreds(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(validationMessage.contains("failed"));
        Allure.description("User has given registered email but incorrect password");
    }
    @Test(priority = 2,description = "User tries to login with blank email")
    public void doLoginWithblankEmail() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        String validationMessage=loginPage.doLoginWithblankEmail("", utils.getPassword());
        Assert.assertTrue(validationMessage.contains("email address required"));
        Allure.description("User has given valid password but no email address is given,the email address field is kept blank.Should give 'email required message' ");
    }
    @Test(priority = 3,description = "User tries to login with blank password")
    public void doLoginWithblankPass() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        utils.getUserCreds(4);
        String validationMessage=loginPage.doLoginWithblankPass(utils.getEmail(), "");
        Assert.assertTrue(validationMessage.contains("Password is required"));
        Allure.description("User has given valid email but the password is kept as blank," +
                "Should give 'Password required Message'");
    }
    @Test(priority=4,description = "User tries to login with invalid email")
    public void doLoginWithInvalidEmail() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        utils.getUserCreds(4);
        String invalidEmail=utils.getEmail().substring(0,5)+" "+utils.getEmail().substring(5);
        String validationMessage=loginPage.doLoginWithInvalidEmail(invalidEmail,utils.getPassword());
        Assert.assertTrue(validationMessage.contains("Invalid email address"));
        Allure.description("If user does some mistake inputting email e.g: an initial extra space in between the email, " +
                "then the user will prompted as invalid email being given");
    }
    @Test(priority = 5,description = "User tries to login with incorrect email but correct password")
    public void doLoginWithIncorrectEmailCorrectPass() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        utils.getUserCreds(2);
        String validationMessage=loginPage.doLoginWithIncorrectEmailCorrectPass(utils.getEmail(),utils.getPassword());
        Assert.assertTrue(validationMessage.contains("Authentication failed"));
        Allure.description("User tries to login with unauthorized email but valid email and correct password" +
                "User will not be allowed to login and 'Authentication failed' will be prompted");
    }
    @Test(priority = 6,description = "Forgot Password link is there or not")
    public void forgotPasswordLinkVisibleOrNot() throws InterruptedException {
        loginPage=new LoginPage(driver);
        boolean forgotPasswordVisibility=loginPage.forgotPasswordLinkVisibleOrNot();
        Assert.assertTrue(forgotPasswordVisibility);
        Assert.assertTrue(loginPage.linkForgotPassword.isEnabled());
        Allure.description("Checking whether forgot password option is available to the users or not");
    }
    @Test(priority = 7,description = "Retrived Password button visible or not")
    public void retrievePasswordButtonVisibleOrNot() throws InterruptedException {
        loginPage=new LoginPage(driver);
        boolean retrievePasswordVisibility= loginPage.retrievePasswordButtonVisibleOrNot();
        Assert.assertTrue(retrievePasswordVisibility);
        Allure.description("Checking whether retrieve password button is visible or not");
    }
    @Test(priority = 8,description = "Retrieving password by giving registered email and back to login page")
    public void retrievingPassword() throws IOException, ParseException, InterruptedException {
        loginPage=new LoginPage(driver);
        Assert.assertTrue(loginPage.btnBackToLogin.isDisplayed());
        utils.getUserCreds(0);
        loginPage.retrievingPassword(utils.getEmail());
        Allure.description("The password is retrieved by giving registered email and after retrieving password user " +
                "will click back to successfully login again.");
    }
    @Test(priority = 10,description = "Session maintained after successful login")
    public void sessionMaintainedAfterLogin() throws InterruptedException, IOException, ParseException {
        loginPage=new LoginPage(driver);
        boolean sessionMaintainance= loginPage.sessionMaintainedAfterLogin();
        Assert.assertTrue(sessionMaintainance);
        Allure.description("After successful login into the system,if the user clicks on a button e.g:T-shirts here" +
                "and open it in a new tab,the user should still be logged in,logout button should be displayed" +
                "user can close it and choose to get back to the previous tab and still be logged in.");
    }
    @Test(priority = 11,description = "When changing password of the user, user gives incorrect current password")
    public void incorectPasswordChange() throws InterruptedException, IOException, ParseException {
//        driver.get("http://automationpractice.com/");
        loginPage=new LoginPage(driver);
        String currentPassword= utils.getPassword()+utils.generateRandomNumber(1,400);
        String newPassword= utils.getPassword();
        String incorrectMessage=loginPage.incorectPasswordChange(currentPassword,newPassword);
        Assert.assertTrue(incorrectMessage.contains("The password you entered is incorrect"));
        Allure.description("While the user after logging in wants to change the password, if the current password is incorrect," +
                "the system will give an error and prompt the user of password being incorrect");
    }
    @Test(priority = 12,description = "When changing password of the user, user gives correct current password.")
    public void validPasswordChange() throws InterruptedException, IOException, ParseException {
        loginPage=new LoginPage(driver);
        String currentPassword= utils.getPassword();
        String newPassword= utils.getPassword();
        String correctMessage=loginPage.validPasswordChange(currentPassword,newPassword);
        System.out.println(correctMessage);
        Assert.assertTrue(correctMessage.contains("successfully updated"));
        Allure.description("While changing the password of the user,if user gives correct current password and sets an appropriate new password," +
                "the password will be successfully updated and the user can get back to the account");
    }
    @Test(priority = 13,description = "SignOut button will be clicked,SignIn button should be displayed")
    public void signOutPerformed() throws InterruptedException {
        loginPage =new LoginPage(driver);
        boolean signInDisplayedOrNot=loginPage.signOutPerformed();
        Assert.assertTrue(signInDisplayedOrNot);
        Allure.description("If the user signs out,signin button should be displayed and home page is being shown.");
    }

}
