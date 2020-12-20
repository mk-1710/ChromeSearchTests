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
        //yahoo search engine has a 'search button' which is clickable
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");

        openBrowser();
        navigateToYahoo();
        typeAndSubmitYahooQuery();
        //submitYahooQuery();
        verifyYahooResultPage();
    }

    @Test
    public void testGoogleSearch() {

        //google search engine does not have 'search button'
        //use
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openBrowser();
        navigateToGoogle();
        typeAndSubmitGoogleQuery();

    }

    private void verifyYahooResultPage() {
        String textXPath = "Portnov Computer School - Software";
        //String linkXPath = "//*[text(), 'Portnov Computer School - Software Testing and Software QA ...')]";
        String linkXPath = "//a[@class=' ac-algo fz-l ac-21th lh-24']";

        //WebElement findAnchor = driver.findElement(By.partialLinkText("Port"));
        WebElement findAnchor = driver.findElement(By.xpath(linkXPath));
        String textMyOne = findAnchor.getText();
        System.out.println(textMyOne);
    }

     private void typeAndSubmitYahooQuery() {
        String searchFieldXPath = "//input[@name='p']";
        String whatToFind = "Portnov computer school";

        WebElement searchField = driver.findElement(By.xpath(searchFieldXPath));
        searchField.sendKeys(whatToFind);
        searchField.submit();
    }

    private void typeAndSubmitGoogleQuery() {
        String searchFieldsXPath = "//input[@name='q']";
        String whatToFind = "Portnov computer school";

        WebElement searchField = driver.findElement(By.xpath(searchFieldsXPath));
        searchField.sendKeys(whatToFind);
        searchField.submit();
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