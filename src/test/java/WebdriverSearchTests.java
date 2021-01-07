import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WebdriverSearchTests {

    //1. open browser
    //2. navigate to the google.com page
    //3. type the query
    //4. submit the query
    //5. verify the result page
    //we will use google and yahoo search engines


    WebDriver driver;

    @Test
    public void testYahooSearchInEdge() {
        //yahoo search engine has a 'search button' which is clickable
        //but used submit method of WebElement

//        To install run the following in an elevated command prompt:
//        DISM.exe /Online /Add-Capability /CapabilityName:Microsoft.WebDriver~~~~0.0.1.0

        //no need to uncomment this line, edge is starting on its own
        //System.setProperty("webdriver.edge.driver", "src/test/resources/edgedriver/MicrosoftWebDriver.exe");

        //unfortunately doesn't work
        openEdgeBrowser();
        performSearch(
                "http://yahoo.com",
                "//input[@name='p']",
                "Portnov computer school",
                "//a[contains(text(), 'Portnov Computer School - Software')]",
                "Search in Yahoo search is successfull (Edge browser).",
                "Search in Yahoo search is failed (Edge browser).");
    }

    @Test
    public void testGoogleSearchInEdge() {
        //google search engine does not have 'search button'
        //used submit method of WebElement

        //To install run the following in an elevated command prompt:
        //DISM.exe /Online /Add-Capability /CapabilityName:Microsoft.WebDriver~~~~0.0.1.0

        //no need to uncomment this line, edge is starting on its own
        //System.setProperty("webdriver.edge.driver", "src/test/resources/edgedriver/MicrosoftWebDriver.exe");

        //unfortunately doesn't work
        openEdgeBrowser();
        performSearch("https://www.google.com",
                "//input[@name='q']",
                "Portnov computer school",
                "//span[contains(text(), 'Portnov Computer School - YouTube')]",
                "Search in Google search is successfull (Edge browser).",
                "Search in Google search is failed (Edge browser).");
    }

    @Test
    public void testYahooSearchInFirefox() {
        //yahoo search engine has a 'search button' which is clickable
        //but used submit method of WebElement

        System.setProperty("webdriver.gecko.driver", "src/test/resources/firefoxdriver/geckodriver.exe");
        openFirefoxBrowser();
        performSearch(
                "http://yahoo.com",
                "//input[@name='p']",
                "Portnov computer school",
                "//a[contains(text(), 'Portnov Computer School - Software')]",
                "Search in Yahoo search is successfull (Firefox browser).",
                "Search in Yahoo search is failed (Firefox browser).");
    }

    @Test
    public void testGoogleSearchInFirefox() {
        //google search engine does not have 'search button'
        //used submit method of WebElement

        System.setProperty("webdriver.gecko.driver", "src/test/resources/firefoxdriver/geckodriver.exe");
        openChromeBrowser();
        performSearch("https://www.google.com",
                "//input[@name='q']",
                "Portnov computer school",
                "//span[contains(text(), 'Portnov Computer School - YouTube')]",
                "Search in Google search is successfull (Firefox browser).",
                "Search in Google search is failed (Firefox browser).");
    }

    @Test
    public void testYahooSearchInChrome() {
        //yahoo search engine has a 'search button' which is clickable
        //but used submit method of WebElement

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openChromeBrowser();
        performSearch(
                "http://yahoo.com",
                "//input[@name='p']",
                "Portnov computer school",
                "//a[contains(text(), 'Portnov Computer School - Software')]",
                "Search in Yahoo search is successfull (Chrome browser).",
                "Search in Yahoo search is failed (Chrome browser).");
    }

    @Test
    public void testGoogleSearchInChrome() {
        //google search engine does not have 'search button'
        //used submit method of WebElement

        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/windows32/chromedriver.exe");
        openChromeBrowser();
        performSearch("https://www.google.com",
                "//input[@name='q']",
                "Portnov computer school",
                "//span[contains(text(), 'Portnov Computer School - YouTube')]",
                "Search in Google search is successfull (Chrome browser).",
                "Search in Google search is failed (Chrome browser).");
    }

    private void performSearch(String searchEngineString,
                               String searchFieldXPath,
                               String whatToFind,
                               String objectXPath,
                               String successMessage,
                               String failedMessage) {

        navigateToSearchEngine(searchEngineString);
        typeAndSubmitQuery(searchFieldXPath, whatToFind);
        boolean linkIsFound = verifyResultPage(objectXPath);

        if (linkIsFound) {
            System.out.println(successMessage);
        } else {
            System.out.println(failedMessage);
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

    private void openFirefoxBrowser() {
        driver = new FirefoxDriver();
    }

    private void openEdgeBrowser() {
        driver = new EdgeDriver();
    }
}