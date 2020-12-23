import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ChromeSearchTests {

    //1. open browser
    //2. navigate to the google.com page
    //3. type the query
    //4. submit the query
    //5. verify the result page
    //we will use google and yahoo search engines


    WebDriver driver;
    @Test
    public void testYahooSearch() {
        //yahoo search engine has a 'search button' which is clickable
        //but used submit method of WebElement

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");        openChromeBrowser();
        navigateToSearchEngine("http://yahoo.com");
        typeAndSubmitQuery("//input[@name='p']", "Portnov computer school");
        boolean linkIsFound = verifyResultPage("//a[contains(text(), 'Portnov Computer School - Software')]");

        if(linkIsFound){
            System.out.println("Link in Yahoo search is successfully found!");
        }else{
            System.out.println("Link not found in Yahoo search!");
        }
        driver.close();
        Assert.assertTrue(linkIsFound);
    }

    @Test
    public void testGoogleSearch() {
        //google search engine does not have 'search button'
        //used submit method of WebElement

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openChromeBrowser();
        navigateToSearchEngine("https://www.google.com");
        typeAndSubmitQuery("//input[@name='q']", "Portnov computer school");

        boolean linkIsFound = verifyResultPage("//span[contains(text(), 'Portnov Computer School - YouTube')]");

        if(linkIsFound){
            System.out.println("Link in Google search is successfully found!");
        }else{
            System.out.println("Link not found in Google search!");
        }
        driver.close();

        Assert.assertTrue(linkIsFound);
    }

    public boolean verifyResultPage(String objectXPath) {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            WebElement linkToFind = driver.findElement(By.xpath(objectXPath));
            return true;
        }catch (Exception MyException){
            return false;
        }

    }

    private void typeAndSubmitQuery(String searchFieldXPath, String whatToFind) {

        WebElement searchField = driver.findElement(By.xpath(searchFieldXPath));
        searchField.sendKeys(whatToFind);
        searchField.submit();

    }

    private void navigateToSearchEngine(String searchEngineURL) {
        driver.get(searchEngineURL);
    }

    private void openChromeBrowser() {
        driver = new ChromeDriver();
    }
}