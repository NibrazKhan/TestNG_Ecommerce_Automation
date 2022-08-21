package pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchFunctionality {
    @FindBy(id="search_query_top")
    public
    WebElement txtSearch;
    @FindBy(name="submit_search")
    public
    WebElement btnSearch;
    @FindBy(className = "heading-counter")
    WebElement lblResult;
    @FindBy(className = "alert")
    WebElement noResultFoundWarning;
    @FindBy(className = "lighter")
    public WebElement searchProductName;
    @FindBy(className = "product-name")
    public List<WebElement>productName;
    @FindBy(id = "selectProductSort")
    public WebElement sortByDropdown;
    //index->11 and 13
    @FindBy(className = "price")
    public List<WebElement> productPrice;
    WebDriver driver;
    public SearchFunctionality(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public String searchProduct(String product) throws InterruptedException {
        txtSearch.clear();
        Thread.sleep(1000);
        txtSearch.sendKeys(product);
        Thread.sleep(1000);
        btnSearch.click();
        return lblResult.getText();
    }

    public String incorrectSearchProduct(String product) throws InterruptedException {
        Thread.sleep(1000);
        txtSearch.clear();
        txtSearch.sendKeys(product);
        Thread.sleep(1000);
        btnSearch.click();
        return noResultFoundWarning.getText();
    }
    public String searchProductByEnterButton(String product) throws InterruptedException {
        txtSearch.clear();
        Thread.sleep(1000);
        txtSearch.sendKeys(product);
        Thread.sleep(1000);
        btnSearch.sendKeys(Keys.ENTER);
        return lblResult.getText();
    }
    public String correctSearchPageDisplayed() throws InterruptedException {
        Thread.sleep(1200);
        return searchProductName.getText();
    }
    public boolean sortByWorkingFine() throws InterruptedException {
        Thread.sleep(1000);
        Select select=new Select(sortByDropdown);
        Thread.sleep(1000);
        select.selectByValue("price:asc");
        Thread.sleep(1500);
        if(Double.parseDouble(productPrice.get(11).getText().substring(1))<=Double.parseDouble(productPrice.get(15).getText().substring(1))){
            return true;
        }
        return false;
    }

//    public Boolean chooseProduct(){
//        imgProduct.get(14).click();
//        return btnAddCart.isDisplayed();
//    }
//    public Boolean addToCart(){
//        btnAddCart.click();
//        return btnAddCart.isDisplayed();
//    }
//    public String checkProductSummary(){
//        btnCheckout.click();
//        return lblSummaryPage.getText();
//    }
}
