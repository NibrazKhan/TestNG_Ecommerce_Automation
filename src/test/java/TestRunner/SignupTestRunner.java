package TestRunner;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SignupPage;
import setup.Setup;
import utils.Utils;
import java.io.IOException;
import java.util.List;

public class SignupTestRunner extends Setup {
    //positive test case
    Utils utils=new Utils();
    String email;
    String password;
    String mobile;
    public void basicInfo(){
        String emailUserAcc="testautouser"+utils.generateRandomNumber(1000,9999);
        email=emailUserAcc+"@test.com";
        password=utils.generateRandomPassword(8);
        mobile="175"+utils.generateRandomNumber(1000000,9999999);
    }
    @Test(priority=1,description = "Cannot Create Account with already registered email")
    public void alreadyRegisteredEmail() throws IOException, ParseException, InterruptedException {
        driver.get("http://automationpractice.com/");
        SignupPage signupPage=new SignupPage(driver);
        utils.getUserCreds(0);
        String errorAlreadyregisteredMsg=signupPage.alreadyRegisteredEmail(utils.getEmail());
        Assert.assertTrue(errorAlreadyregisteredMsg.contains("already been registered"));
        Thread.sleep(1500);
        Allure.description("Already been registered email cannot be used for creating new user and an " +
                "error message will be given and user will not be taken to the next step and form fillup."
        );
    }
    @Test(priority = 2,description = "After giving valid email,user will be redirecrted to registration form")
    public void validEmailandRegisterForm() throws InterruptedException {
//        driver.get("http://automationpractice.com/");
        SignupPage signupPage=new SignupPage(driver);
        basicInfo();
        signupPage.validEmailandRegisterForm(email);
        //after logging with valid email
        Assert.assertTrue(signupPage.txtcreateAccount.getText().contains("CREATE AN ACCOUNT"));
        Assert.assertTrue(signupPage.regButton.isDisplayed());
        Allure.description("After giving valid email, user should be redirected to the registration form containing Create Account and register button");
    }

    @Test(priority =3,description = "User press register button without inputting any data")
    public void blankRegistration() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        signupPage.blankRegistration();
        Assert.assertTrue(signupPage.totalErrors.getText().contains("8 errors"));
        Allure.description("If the user does not input any data in the registration form ,total 8 errors will be given, that is," +
                "the mandatory fields must be filled up for successful registration");
    }
    @Test(priority = 4,description = "Firstname and lastname cannot include any digit at last")
    public void invalidFirstandLastName() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        List<String> invalidName=signupPage.invalidFirstandLastName();
        String invalidFirstName=invalidName.get(0);
        String invalidLastName=invalidName.get(1);
        Assert.assertTrue(invalidFirstName.contains("firstname is invalid"));
        Assert.assertTrue(invalidLastName.contains("lastname is invalid"));
        Allure.description("User cannot give firstname and last name containing digits, should contain only alphabets," +
                "numbers can be in between the alphabets.");
    }
    @Test(priority = 5,description = "Invalid Password Given,must be minimum 5 length")
    public void invalidPasswordCheck() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
//        System.out.println(signupPage.invalidPasswordCheck());
        Assert.assertTrue(signupPage.invalidPasswordCheck().contains("passwd is invalid"));
        Allure.description("Password should be atleast 5 character length . Otherwise user won't be able to sign up.");
    }
    @Test(priority=6,description = "Zip/postal code must not be more or less than five digits")
    public void invalidZipCode() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        String errorZipCode=signupPage.invalidZipCode();
        Assert.assertTrue(errorZipCode.contains("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
        Allure.description("Zip/postal code must not be greater or less than five digits and must follow this format:00000 " +
                "else user won't be able to sign up");
    }
    @Test(priority = 7,description = "Mandatory Fields Must be filled up for successful sign up")
    public void mandatoryFieldsShouldFillUp() throws InterruptedException, IOException, ParseException {
        SignupPage signupPage=new SignupPage(driver);
        basicInfo();
        String errorPhoneNumber=signupPage.mandatoryFieldsShouldFillUp(email,mobile);
        Assert.assertTrue(errorPhoneNumber.contains("at least one phone number"));
        Allure.description("All the mandatory fields in the form must be filled up,if one is missed atleast for example:mobile number is not given," +
                "then an error message will be thrown to the user 'You must register at least one phone number' and the user will be unsuccessfull logging in. ");

    }
    @Test(priority = 8,description = "After successful login,user is redirected to the landing page")
    public void LandingPage() throws InterruptedException {
        SignupPage signupPage=new SignupPage(driver);
        Assert.assertTrue(signupPage.pageHeading.getText().contains("MY ACCOUNT"));
        Assert.assertTrue(signupPage.btnLogOut.isDisplayed());
        Assert.assertTrue(signupPage.btnHome.isDisplayed());
        System.out.println(signupPage.userAccountName.getText());
        Thread.sleep(1400);
        Assert.assertTrue(signupPage.userAccountName.getText().contains("user"));
        signupPage.signOutAfterLanding();
        Allure.description("User will be taken to the landing page containing 'MY ACCOUNT' as the page heading, " +
                "LogOut button should be displayed " +
                "Home Button in the bottom should be there" +
                "User Account Name should be visible in the upper right corner");
    }
    //whole successful signup of user
    @Test(priority = 9,description = "Successful registration and redirection to the user page")
    public void doSignup() throws InterruptedException, IOException, ParseException {
        driver.get("http://automationpractice.com/");
        SignupPage signupPage=new SignupPage(driver);
        basicInfo();
        signupPage.doRegistration(email,password,mobile);
        utils.writeUserInfo(email,password);
//        System.out.println(signupPage.pageHeading.getText());
        Assert.assertTrue(signupPage.pageHeading.getText().contains("MY ACCOUNT"));
        Assert.assertTrue(signupPage.btnLogOut.isDisplayed());
        Assert.assertTrue(signupPage.btnHome.isDisplayed());
        Assert.assertTrue(signupPage.userAccountName.getText().contains(signupPage.firstName));
        Allure.description("Successfully registration will be done if valid email and all the credentials in the registration form is properly given," +
                "After registration user will be redirected to dashboard page containing 'MY ACCOUNT' as page heading,LogOut and Profile Account name button" +
                "in th top right.Home button will be there below to go back to home.");
    }

}
