package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    @FindBy(css = "[title='View my shopping cart']")
    public WebElement btnCart;
    @FindBy(css = "[title='Add to cart']")
    public List<WebElement> btnCartUnderProduct;
    @FindBy(className = "ajax_cart_no_product")
    public WebElement emptyCart;
    @FindBy(xpath = "//p[contains(text(),'Your shopping cart is empty.')]")
    public WebElement msgCartEmpty;
//    @FindBy(className = "icon-eye-open")
    @FindBy(className = "quick-view-mobile")
    public List<WebElement> eyeIcon;
    @FindBy(id = "our_price_display")
    public WebElement priceDisplay;
    @FindBy(tagName = "img")
    public List<WebElement>imgProduct;
    @FindBy(className = "button-plus")
    public WebElement btnPlus;
    @FindBy(id = "quantity_wanted")
    public WebElement quantity;
    @FindBy(id = "group_1")
    public WebElement sizeDropdown;
    @FindBy(name = "Submit")
    public WebElement btnAddToCart;
    @FindBy(tagName = "i")
   public List<WebElement> iconOk;
    @FindBy(id = "summary_products_quantity")
    public WebElement cartQuantity;
    @FindBy(id = "total_product_price_4_17_0")
    public WebElement totalProductPrice;
    @FindBy(css = "[title='Proceed to checkout']")
    public WebElement btnProceedToCheckout;
    @FindBy(css = "[title='Delete']")
    public WebElement btnDelete;

    WebDriver driver;
    SearchFunctionality searchFunctionality;
    public CartPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //test steps
    public boolean cartButtonDisplayed() throws InterruptedException {
        searchFunctionality=new SearchFunctionality(driver);
        String search=searchFunctionality.searchProduct("dress");
        return btnCart.isDisplayed();
    }
    public String emptyCartClick() throws InterruptedException {
        Thread.sleep(1000);
        btnCart.click();
        Thread.sleep(1000);
        return msgCartEmpty.getText();
    }
    public boolean addToCartButtonUnderProduct() throws InterruptedException {
        //should search the product again after clicking empty cart
        searchFunctionality=new SearchFunctionality(driver);
        String searchProduct=searchFunctionality.searchProduct("dress");
        Thread.sleep(1000);
        Actions action =new Actions(driver);
        action.moveToElement(imgProduct.get(14)).perform();
        System.out.println(btnCartUnderProduct.get(1).isDisplayed());
        return btnCartUnderProduct.get(1).isDisplayed();
    }
    public void productEssentialCheck() throws InterruptedException {
        searchFunctionality=new SearchFunctionality(driver);
        String searchProduct=searchFunctionality.searchProduct("dress");
        imgProduct.get(14).click();
        Thread.sleep(1500);
        btnPlus.click();
        Thread.sleep(1000);
        Select select=new Select(sizeDropdown);
        select.selectByValue("2");
        Thread.sleep(1000);
    }
    public boolean successfullyAddedMessage() throws InterruptedException {
        btnAddToCart.click();
        Thread.sleep(1500);
        return iconOk.get(2).isEnabled();
    }
    public boolean verifyProceedToCheckOutButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        //need to wait as it takes time to load.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[title='Proceed to checkout']")));
        boolean displayProceedToCheckOut=driver.findElement(By.cssSelector("[title='Proceed to checkout']")).isDisplayed();
        btnProceedToCheckout.click();
        return displayProceedToCheckOut;
    }
    public List<String> quantityAndPriceCheck(){
        List<String>quantityAndPrice=new ArrayList<>();
        quantityAndPrice.add(cartQuantity.getText());
        quantityAndPrice.add((totalProductPrice.getText()));
        return quantityAndPrice;
    }
    public String removingItemsFromCart() throws InterruptedException {
        Thread.sleep(1200);
        btnDelete.click();
        Thread.sleep(1200);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //need to wait as it takes time to load.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Your shopping cart is empty.')]")));
        return msgCartEmpty.getText();
    }

}
