package TestRunner;

import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import setup.Setup;
import utils.Utils;

import java.util.List;

public class CartPageRunner extends Setup {
    CartPage cartPage;
    Utils utils=new Utils();
    Double productPrice;
    @Test(priority = 1,description = "Add to Cart button displayed and empty initially")
    public void cartButtonDisplayed() throws InterruptedException {
        driver.get("http://automationpractice.com/");
        cartPage=new CartPage(driver);
        boolean cartbtnDisplay=cartPage.cartButtonDisplayed();
        Assert.assertTrue(cartbtnDisplay);
        Assert.assertTrue(cartPage.emptyCart.getText().contains("(empty)"));
        Allure.description("After searching a product, the cart button will be displayed in the top right corner and initially,it will be empty.");
    }
    @Test(priority = 2,description = "Clicking on empty cart")
    public void emptyCartClick() throws InterruptedException {
        cartPage=new CartPage(driver);
        String msgCartEmpty= cartPage.emptyCartClick();
        Assert.assertTrue(msgCartEmpty.contains("empty"));
    }
    @Test(priority = 3,description = "Add to Cart Button should be displayed under product description")
    public void addToCartButtonUnderProduct() throws InterruptedException {
        cartPage=new CartPage(driver);
        boolean cartButtonDisplayedUnderProduct= cartPage.addToCartButtonUnderProduct();
        System.out.println(cartButtonDisplayedUnderProduct);
        Assert.assertTrue(cartButtonDisplayedUnderProduct);
        Allure.description("There should be add to cart button under the product when user will hover to the product of interest.");
    }
    @Test(priority = 4,description = "Essentials of Product checked like quantity plus button,price and dropdown")
    public void productEssentialCheck() throws InterruptedException {
//        driver.get("http://automationpractice.com/");
        cartPage=new CartPage(driver);
        cartPage.productEssentialCheck();
        System.out.println(Double.parseDouble(cartPage.priceDisplay.getText().substring(1)));
        productPrice=Double.parseDouble(cartPage.priceDisplay.getText().substring(1));
        System.out.println("Product Price: "+productPrice);
         Assert.assertTrue(cartPage.priceDisplay.getText().contains("50"));
        Allure.description("After clicking on the product, the product essentials are checked like whether the price is displayed or not" +
                "dropdown correctly working or not like size is changed to medium" +
                "quantity plus button is working or not like after hitting plus quantity should be changed to 2.");
    }
    @Test(priority = 5,description = "Successfully added icon should be displayed")
    public void successfullyAddedMessage() throws InterruptedException {
//        driver.get("http://automationpractice.com/");
        cartPage=new CartPage(driver);
//        cartPage.productEssentialCheck();
        boolean successIcon=cartPage.successfullyAddedMessage();
        Assert.assertTrue(successIcon);
        Allure.description("After clicking add to cart button , successfully added icon will be displayed.");
    }
    @Test(priority = 6,description = "ProceedToCheckOut button will be diplayed")
    public void verifyProceedToCheckOutButton() throws InterruptedException {
//        driver.get("http://automationpractice.com/");
        cartPage=new CartPage(driver);
        boolean displayProceedToCheckOutButton= cartPage.verifyProceedToCheckOutButton();
        Assert.assertTrue(displayProceedToCheckOutButton);
        Allure.description("Proceed To CheckOut button should be displayed after adding to cart and clicked");
    }
    @Test(priority = 7,description = "Verifying quantity and total product price in the cart")
    public void quantityAndPriceCheck() throws InterruptedException {
        cartPage=new CartPage(driver);
        List<String>quantityAndPriceCheck=cartPage.quantityAndPriceCheck();
        System.out.println(quantityAndPriceCheck.get(0));
        System.out.println(quantityAndPriceCheck.get(1));
        String quantity=quantityAndPriceCheck.get(0);
        String totalPrice=quantityAndPriceCheck.get(1).substring(1);
        System.out.println("Actual total Price: "+totalPrice);
        System.out.println("Expected total price: "+(productPrice*2));
        String expectedTotalPrice=Double.toString(productPrice*2);
        Assert.assertTrue(quantity.contains("2"));
        Assert.assertEquals(expectedTotalPrice,totalPrice);
        Allure.description("After adding items to the cart , the quantity of the items are verified, and as the same product quantity is 2," +
                "the total price of the product should also be doubled, the total product price is verified.");
    }
    @Test(priority = 8,description = "Removing items from the cart by clicking delete button")
    public void removingItemsFromCart() throws InterruptedException {
        cartPage=new CartPage(driver);
        String cartEmpty=cartPage.removingItemsFromCart();
        Assert.assertTrue(cartEmpty.contains("cart is empty"));
        Allure.description("If the delete button is pressed,the items added to the cart will be removed" +
                "and the cart will be empty,the empty message is verified.");
    }
}
