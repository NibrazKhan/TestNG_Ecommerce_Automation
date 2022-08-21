package TestRunner;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchFunctionality;
import setup.Setup;

public class SearchFunctionalityRunner extends Setup {
    SearchFunctionality searchFunctionality;
    String productName="dress";
    //can add test case title in description parameter of test
    @Test(priority = 4,description = "User can search product")
    public void searchProduct() throws InterruptedException {
        searchFunctionality =new SearchFunctionality(driver);
        String res= searchFunctionality.searchProduct(productName);
        Assert.assertTrue(res.contains("results have been found"));
        Allure.description("User can search product successfully by giving valid input");
    }
    @Test(priority = 5,description = "User can search product by pressing the Enter Button")
    public void searchProductByEnterButton() throws InterruptedException {
        searchFunctionality =new SearchFunctionality(driver);
        String res= searchFunctionality.searchProduct(productName);
        Assert.assertTrue(res.contains("results have been found"));
        Allure.description("Verifying that after giving valid data to the search field,user can get data by entering Enter key.");
    }
    @Test(priority = 1,description = "Search with empty field/input")
    public void emptySearchProduct() throws InterruptedException {
        driver.get("http://automationpractice.com/");
        searchFunctionality=new SearchFunctionality(driver);
        String response=searchFunctionality.incorrectSearchProduct("");
        Assert.assertTrue(response.contains("Please enter a search keyword"));
        Allure.description("User will be shown a warning prompting to give valid input to the search field and not to search keeping it blank");
    }
    @Test(priority = 2,description = "Search with invalid data")
    public void invalidSearchProduct() throws InterruptedException {
        searchFunctionality=new SearchFunctionality(driver);
        String response=searchFunctionality.incorrectSearchProduct("skjdfhsdkjfh");
        Assert.assertTrue(response.contains("No results were found"));
        Allure.description("No result found will be shown if user inputs invalid keyword");
    }
    @Test(priority = 3,description = "Search with numeric data")
    public void numericSearchProduct() throws InterruptedException {
        searchFunctionality=new SearchFunctionality(driver);
        String response=searchFunctionality.incorrectSearchProduct("12345");
        Assert.assertTrue(response.contains("No results were found"));
        Allure.description("If user inputs numeric keywords into the search field, no results will be found.");
    }
    @Test(priority=6,description = "Correct Search Page is displayed")
    public void correctSearchPageDisplayed() throws InterruptedException {
        String response=searchFunctionality.correctSearchPageDisplayed();
        System.out.println(response);
        Assert.assertTrue(response.contains("\"DRESS\""));
        System.out.println(searchFunctionality.productName.get(10).getText());
        Assert.assertTrue(searchFunctionality.productName.get(10).getText().contains("Dress"));
        Allure.description("After giving valid keyword for searching a product in the search field,correct page should be displayed containing the correct keyword" +
                "and atleast one of the product image should contain the keyword that was being used to search.");
    }
    @Test(priority = 7,description = "Sort by Dropdown working correctly")
    public void sortByWorkingFine() throws InterruptedException {
        searchFunctionality=new SearchFunctionality(driver);
        boolean priceComparison=searchFunctionality.sortByWorkingFine();
        System.out.println(priceComparison);
        Assert.assertTrue(priceComparison);
        Allure.description("Verifying that sort by product dropdown is working correctly or not,e.g: Sorting the product in ascending order of price" +
                "will have products starting from price low to high, so two consecutive products are compared.");
    }

}
