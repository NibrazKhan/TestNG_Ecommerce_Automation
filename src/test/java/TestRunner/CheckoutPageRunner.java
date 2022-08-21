package TestRunner;

import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.SearchFunctionality;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckoutPageRunner extends Setup {
    CartPage cartPage;
    Utils utils = new Utils();
    SearchFunctionality searchFunctionality;
    LoginPage loginPage;
    CheckoutPage checkoutPage;

    @Test(priority = 1, description = "Proceed To CheckOut Button being Displayed and clicked")
    public void proceedToCheckOutButton() throws InterruptedException {
        cartPage = new CartPage(driver);
        driver.get("http://automationpractice.com/");
        cartPage.productEssentialCheck();
        boolean productAddedToCart = cartPage.successfullyAddedMessage();
        boolean proceedToCheckOut = cartPage.verifyProceedToCheckOutButton();
        Assert.assertTrue(productAddedToCart);
        Assert.assertTrue(proceedToCheckOut);
        Allure.description("After searching a dress, adding to cart being operated correctly or not is verified," +
                "Proceed to checkout button is present or not is verified and clicked.");
    }

    @Test(priority = 2, description = "Authentication Should be required to proceed to CheckOut Process")
    public void signInPrompted() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        loginPage = new LoginPage(driver);
        Assert.assertTrue(checkoutPage.pageHeading.getText().contains("SUMMARY"));
        String authentication = checkoutPage.signInPrompted();
        Assert.assertTrue(authentication.contains("AUTHENTICATION"));
        Assert.assertTrue(loginPage.btnSubmit.isDisplayed());
        Allure.description("After the summary page, if the user is not authenticated or logged in , after clicking Proceed to CheckOut button, " +
                "should prompt appropriate credentials to log into the account to proceed to the next steps of Checkout." +
                "Authentication page must contain sign in button.");
    }

    @Test(priority = 3, description = "Should give valid signin details,and proceed to Address Page")
    public void AddressCheckoutPage() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPage(driver);
        checkoutPage = new CheckoutPage(driver);
        utils.getUserCreds(0);
        boolean signOutButton = loginPage.doLoginWithValidCreds(utils.getEmail(), utils.getPassword());
        Assert.assertTrue(signOutButton);
        String addressHeading = checkoutPage.AddressCheckoutPage();
        Assert.assertTrue(addressHeading.contains("ADDRESSES"));
        Assert.assertTrue(checkoutPage.btnAddNewAddress.get(1).isDisplayed());
        Allure.description("After logging in signout button should be displayed,and taken to address page containing 'Addresses' heading and add a new address button.");

    }

    @Test(priority = 4, description = "Delivery Address and billing address should contain update option")
    public void addressupdateButton() {
        checkoutPage = new CheckoutPage(driver);
        List<Boolean> updateButtons = checkoutPage.addressUpdateButton();
        Assert.assertTrue(updateButtons.get(0));
        Assert.assertTrue(updateButtons.get(1));
        Allure.description("Billing address and Delivery address should contain Update Option below them");
    }

    @Test(priority = 5, description = "Address Update Button Clicked and taken to a address change form")
    public void updateButtonClicked() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String addressSubHeading = checkoutPage.updateButtonClicked();
        System.out.println("Address SubHeading: " + addressSubHeading);
        Assert.assertTrue(addressSubHeading.contains("YOUR ADDRESSES"));
        Assert.assertTrue(checkoutPage.btnSubmitAddress.isDisplayed());
        Allure.description("After clicking billing address update button,it should be taken to a address update form page where there will be 'Your Addresses' subheading" +
                "and a Save button below the form");
    }

    @Test(priority = 6, description = "Address is changed and updated")
    public void addressUpdated() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String updatedAddress = checkoutPage.addressUpdated();
        System.out.println("Updated Address: " + updatedAddress);
        Assert.assertTrue(updatedAddress.contains("Ramna"));
        Allure.description("Address is given a new value in the address input and Save is clicked and address update is verified");
    }

    @Test(priority = 7, description = "Should proceed to Shipping Page containing Terms of service")
    public void shippingPage() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String termsOfService = checkoutPage.shippingPage();
        System.out.println("PageHeading: " + checkoutPage.pageHeading.getText());
        Assert.assertTrue(checkoutPage.pageHeading.getText().contains("SHIPPING"));
        System.out.println("Terms :" + termsOfService);
        Assert.assertTrue(termsOfService.contains("Terms"));
        Allure.description("After proceeding to checkout further, it should be headed to Shipping Page containing Terms of Service.");
    }

    @Test(priority = 8, description = "Terms of Service is Unchecked")
    public void termsOfServiceUnchecked() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String msgAgreeTermsofService = checkoutPage.termsOfServiceUnchecked();
        Assert.assertTrue(msgAgreeTermsofService.contains("must agree "));
        Allure.description("Without agreeing terms of service user cannot proceed to payment,will give prompt to agree it before continuing.");
    }

    @Test(priority = 9, description = "Terms of service checked and proceeded to payment")
    public void paymentPage() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String msgPaymentChoose = checkoutPage.paymentPage();
        Assert.assertTrue(msgPaymentChoose.contains("PAYMENT METHOD"));
        Allure.description("After checking the terms of service, Payment Checkout Page will appear which will have subheading of choosing paymemnt options");
    }

    @Test(priority = 10, description = "Available Payment Options")
    public void paymentOptions() {
        checkoutPage = new CheckoutPage(driver);
        List<Boolean> paymentops = new ArrayList<>();
        paymentops = checkoutPage.paymentOptions();
        Assert.assertTrue(paymentops.get(0));
        Assert.assertTrue(paymentops.get(1));
        Allure.description("Two payment options should be available in the payment page:bankWire and Cheque");
    }

    @Test(priority = 11, description = "Confirm Order button is checked")
    public void confirmOrderButtonCheck() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.confirmOrderButtonCheck());
        Assert.assertTrue(checkoutPage.addressSubheading.getText().contains("BANK-WIRE"));
        Allure.description("After choosing payment by bankwire,Confirm order button is verified whether it is present or not and " +
                "whether correct payment option is selected or not.");
    }

    @Test(priority = 12, description = "Confirming order verification")
    public void confirmOrder() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        Assert.assertTrue(checkoutPage.pageHeading.getText().contains("ORDER SUMMARY"));
        String msgOrderComplete = checkoutPage.confirmOrder();
        //Order confirmation heading check
        Assert.assertTrue(checkoutPage.pageHeading.getText().contains("ORDER CONFIRMATION"));
        //confirmation message check
        Assert.assertTrue(msgOrderComplete.contains("complete"));
        Allure.description("After seeing order summary , when user hits confirm order button, the user will be taken to a page containing Order confirmation and will" +
                "show a message of Order being Complete.");
    }

    @Test(priority = 13, description = "Order History")
    public void orderHistory() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String headingOrderHistory = checkoutPage.orderHistory();
        System.out.println("Order hst: " + headingOrderHistory);
        Assert.assertTrue(headingOrderHistory.contains("ORDER HISTORY"));
        Allure.description("After confirmation when user hits back to orders , the user will be taken to page containing the order history and the details of the order.");
    }

    @Test(priority = 14, description = "Cart Empty After Confirming Order")
    public void cartEmptyAfterOrderConfirm() throws InterruptedException {
        checkoutPage = new CheckoutPage(driver);
        String cartEmpty = checkoutPage.cartEmptyAfterOrderConfirm();
        System.out.println(cartEmpty);
        Assert.assertTrue(cartEmpty.contains("(empty)"));
        Allure.description("After successful order confirmation,the cart will be empty as the order is placed.");
    }


}
