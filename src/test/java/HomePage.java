import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    final static public Integer defaultTimeout = 10;
    protected long defaultWaitIntervals = 3;
    public Boolean markElementByDefault = true;


    By searchButton = By.xpath("//*[@id='searchbox-searchbutton']");
    By SearchInputLocator = By.xpath("//*[@id='gs_lc50']/*[@aria-label='חיפוש במפות Google']");//Sorry driver show google maps in hebrew
    By zoomInButton = By.xpath("//*[contains(@class,'widget-zoom-in')]");
    String searchPageIndicator = "//*[contains(@class,'section-hero-header-title-description')]//*[text()='רומא']";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    public void clickOnSearchButton() {
        try {
            System.out.println("Trying to find button");
            if (isDisplayed(searchButton, 3)) {
                System.out.println(String.format("element %s displayed", searchButton));
                clickOnItem(searchButton);
            }
        } catch (Exception e) {
            System.out.println(String.format("Unable to find button %s", searchButton));
        }
    }

    public void clickOnItem(By locator) {
        System.out.println(String.format("Trying to click on %s",locator));
        driver.findElement(locator).click();
        System.out.println(String.format("Clicked on %s",locator));
    }


    public void highlightElement(By locator) {
        WebElement elem = driver.findElement(locator);
        // draw a border around the found element
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
    }


    public Boolean isDisplayed(By locator, Integer... optionalTimeout) {
        Integer timeout = defaultTimeout;
        if (optionalTimeout != null) {
            timeout = (optionalTimeout.length > 0) ? optionalTimeout[0] : defaultTimeout;
        }
        return waitForElementVisibility(locator, timeout);
    }

    public Boolean waitForElementVisibility(By locator, Integer... timeout) {
        return this.waitForElementVisibility(locator, markElementByDefault, timeout);
    }

    public Boolean waitForElementVisibility(By locator, Boolean activateMarkElement, Integer... timeout) {
        try {
            WebElement element = waitGenerator(timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (element == null) {
                return false;
            }
            if (activateMarkElement) {
                highlightElement(locator);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebDriverWait waitGenerator(Integer... timeout) {
        return new WebDriverWait(driver, (timeout.length > 0) ? timeout[0] : defaultTimeout, defaultWaitIntervals);
    }

    public void searchForLocation(String location) {
        driver.findElement(SearchInputLocator).sendKeys(location);
    }

    public void zoomIn() {
        driver.findElement(zoomInButton).click();
    }

    public String WaitForResults() {
        WebDriverWait wait = new WebDriverWait(this.driver, 5L);
        WebElement wb1 =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchPageIndicator)));
        if (!wb1.isDisplayed()) {
            System.out.println("Not finished loading");
        }

        return wb1.getText();
    }
}
