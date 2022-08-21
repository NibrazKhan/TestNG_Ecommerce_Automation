package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {
    @FindBy(className = "button")
    public List<WebElement> proceedToCheckOutButton;
    @FindBy(className = "page-heading")
    public WebElement pageHeading;
    @FindBy(className = "icon-chevron-right")
    public List<WebElement> proceedToRightIcon;
    @FindBy(css = "[title='Add']")
    public List<WebElement>btnAddNewAddress;
    @FindBy(css = "[title='Update']")
    public List<WebElement>btnUpdate;
    @FindBy(className = "page-subheading")
    public WebElement addressSubheading;
    @FindBy(id = "submitAddress")
    public WebElement btnSubmitAddress;
    @FindBy(className = "address_address1")
    public List<WebElement> address;
    @FindBy(className = "carrier_title")
    public List<WebElement> termsOfService;
    @FindBy(css = "[type=Submit]")
    public List<WebElement> btnProceedToCheckout;
    @FindBy(css = "[type=submit]")
    public List<WebElement> btnProceedToCheckout2;
    @FindBy(className = "fancybox-error")
    public WebElement errorMsg;
    @FindBy(css = "[title=Close]")
    public WebElement btnClose;
    @FindBy(css = "[type=Checkbox]")
    public WebElement btnAgreeTermsOfService;
    @FindBy(className = "bankwire")
    public WebElement bankWirePayment;
    @FindBy(className = "cheque")
    public WebElement chequePayment;
    @FindBy(css = "[type=submit]")
    public List<WebElement> btnConfirmOrder;
    @FindBy(className = "cheque-indent")
    public WebElement msgOrderComplete;
    @FindBy(css = "[title='Back to orders']")
    public WebElement btnBackToOrders;
    @FindBy(tagName = "h1")
    public List<WebElement> headingOrderHistory;
    @FindBy(className = "icon-chevron-left")
    public List<WebElement>btnBackToAccount;

    WebDriver driver;
    SearchFunctionality searchFunctionality;
    CartPage cartPage;
    LoginPage loginPage;
    Utils utils=new Utils();
    public CheckoutPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String signInPrompted() throws InterruptedException {
        Thread.sleep(1500);
        proceedToRightIcon.get(2).click();
        Thread.sleep(1200);
        //Should take to page requiring Authentication
        return pageHeading.getText();
    }
    public String AddressCheckoutPage() throws InterruptedException {
        Thread.sleep(1000);
        return pageHeading.getText();
    }
    public List<Boolean> addressUpdateButton(){
        List<Boolean>updateButton=new ArrayList<Boolean>();
        updateButton.add(btnUpdate.get(0).isDisplayed());
        updateButton.add(btnUpdate.get(1).isDisplayed());
        return updateButton;
    }
    public String updateButtonClicked() throws InterruptedException {
        Thread.sleep(1000);
        btnUpdate.get(0).click();
        Thread.sleep(1000);
        return addressSubheading.getText();
    }
    public String addressUpdated() throws InterruptedException {
        Thread.sleep(1000);
        SignupPage signupPage=new SignupPage(driver);
        signupPage.txtAddress1.clear();
        signupPage.txtAddress1.sendKeys("Ramna,Dhaka");
        Thread.sleep(1000);
        btnSubmitAddress.click();
        Thread.sleep(1500);
        return address.get(0).getText();
    }
    public String shippingPage() throws InterruptedException {
        Thread.sleep(1500);
        btnProceedToCheckout.get(1).click();
        Thread.sleep(1500);
        return termsOfService.get(1).getText();
    }
    public String termsOfServiceUnchecked() throws InterruptedException {
        Thread.sleep(1000);
        btnProceedToCheckout2.get(1).click();
        Thread.sleep(1000);
        return errorMsg.getText();

    }
    public String paymentPage() throws InterruptedException {
        Thread.sleep(1000);
        btnClose.click();
        Thread.sleep(1000);
        btnAgreeTermsOfService.click();
        Thread.sleep(1000);
        btnProceedToCheckout2.get(1).click();
        Thread.sleep(1200);
        return pageHeading.getText();
    }
    public List<Boolean> paymentOptions(){
        List<Boolean>paymentopts=new ArrayList<>();
        paymentopts.add(bankWirePayment.isDisplayed());
        paymentopts.add(chequePayment.isDisplayed());
        return paymentopts;
    }
    public boolean confirmOrderButtonCheck() throws InterruptedException {
        Thread.sleep(1000);
        bankWirePayment.click();
        Thread.sleep(1400);
        return btnConfirmOrder.get(1).isDisplayed();
    }
    public String confirmOrder() throws InterruptedException {
        Thread.sleep(1000);
        btnConfirmOrder.get(1).click();
        Thread.sleep(1400);
        return msgOrderComplete.getText();
    }
    public String orderHistory() throws InterruptedException {
        Thread.sleep(1000);
        btnBackToOrders.click();
        Thread.sleep(1400);
        return headingOrderHistory.get(0).getText();
    }
    public String cartEmptyAfterOrderConfirm() throws InterruptedException {
        Thread.sleep(1000);
        btnBackToAccount.get(1).click();
        Thread.sleep(1400);
        cartPage=new CartPage(driver);
        loginPage=new LoginPage(driver);
        String msgCartEmpty=cartPage.emptyCart.getText();
        Thread.sleep(1000);
        loginPage.linkLogout.click();
        Thread.sleep(1000);
        return msgCartEmpty;
    }

}
