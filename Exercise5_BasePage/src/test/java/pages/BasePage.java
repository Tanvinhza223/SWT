package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitforVisibility(By locator){
        return
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //click safe
    protected void click(By locator){
        waitforVisibility(locator).click();
    }
    //send keys safe
    protected void type(By locator, String text) {
        WebElement element = waitforVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }
    //get text safe
    protected String getTextSafe(By locator) {
        return waitforVisibility(locator).getText();
    }
    //navigate to url
    public void navigateTo(String url) {
        driver.get(url);
    }
    //check if element is displayed
    protected boolean isElementVisible(By locator) {
        try {
            return waitforVisibility(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    protected void scrollIntoView(WebElement el) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center', inline:'center'});", el);
        } catch (Exception ignored) {
            // fallback bằng Actions nếu JS thất bại
            try {
                new Actions(driver).moveToElement(el).perform();
            } catch (Exception ignore2) { /* no-op */ }
        }
    }

    protected void scrollIntoView(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        scrollIntoView(el);
    }
}
