import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ChromeSearchTests {

    //1. open browser
    //2. navigate to the google.com page
    //3. type the query
    //4. submit the query
    //5. verify the result page


    WebDriver driver;
    @Test
    public void testYahooSearch() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openBrowser();
        navigateToYahoo();
        typeYahooQuery();
    }

    @Test
    public void testGoogleSearch() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openBrowser();
        navigateToGoogle();
        typeGoogleQuery();
        submitGoogleQuery();

    }

    private void submitGoogleQuery() {

    }

    private void typeYahooQuery() {
        String searchFieldXPath = "//input[@name='p']";
        String whatToFind = "Portnov computer school";

        WebElement searchField = driver.findElement(By.xpath(searchFieldXPath));
        searchField.sendKeys(whatToFind);
    }

    private void typeGoogleQuery() {
        String searchFieldsXPath = "//input[@name='q']";
        String whatToFind = "Portnov computer school";

        WebElement searchField = driver.findElement(By.xpath(searchFieldsXPath));
        searchField.sendKeys(whatToFind);
    }

    private void navigateToGoogle() {
        driver.get("https://www.google.com");
    }

    private void navigateToYahoo() {
        driver.get("http://yahoo.com");
    }

    private void openBrowser() {
        driver = new ChromeDriver();
    }
}