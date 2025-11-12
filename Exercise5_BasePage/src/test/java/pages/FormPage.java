package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.Normalizer;
import java.time.Duration;

public class FormPage extends BasePage {
    private final WebDriverWait wait;


    // ===== Locators (ưu tiên ID cho ổn định) =====
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");


    private By genderRadio(String value) {
        return By.cssSelector("input[name='gender'][value='" + value + "']");
    }


    private final By mobile = By.id("userNumber");
    private final By dobInput = By.id("dateOfBirthInput");


    // Subjects (React Select multi)
    private final By subjectsInput = By.id("subjectsInput");
    private final By subjectsContainer = By.id("subjectsContainer");


    // Hobbies (click label theo text)
    private By hobbyLabel(String labelText) {
        return By.xpath("//div[@id='hobbiesWrapper']//label[normalize-space()='%s']".replace("%s", labelText));
    }


    private final By uploadPicture = By.id("uploadPicture");
    private final By currentAddress = By.id("currentAddress");


    // State/City (React Select single)
    private final By stateInput = By.id("react-select-3-input");
    private final By cityInput = By.id("react-select-4-input");


    private By formButton = By.cssSelector("button[type='submit']");
    private By successMsg = By.cssSelector(".modal-title");
    private By errorMsg = By.cssSelector(".was-validated");
    private By errorRadio =  By.cssSelector(".custom-control-input:invalid~.custom-control-label");
    public FormPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    // ===== Navigation =====
    public FormPage open(String url) {
        driver.get(url);
        return this;
    }


    // ===== Interactions =====
    public FormPage typeFirstName(String value) {
        type(firstName, value);
        return this;
    }
    public FormPage typeLastName(String value) {
        type(lastName, value);
        return this;
    }
    public FormPage selectGender(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tìm input theo value
        By inputBy = By.cssSelector("input[name='gender'][value='" + value + "']");
        WebElement inputEl = wait.until(ExpectedConditions.presenceOfElementLocated(inputBy));
        String id = inputEl.getAttribute("id");

        // Click label trước
        By labelBy = By.cssSelector("label.custom-control-label[for='" + id + "']");
        WebElement labelEl = wait.until(ExpectedConditions.elementToBeClickable(labelBy));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", labelEl);

        try {
            labelEl.click();
        } catch (Exception e) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(inputEl)).click();
            } catch (Exception e2) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();" +
                                "if(!arguments[0].checked){arguments[0].checked=true;}" +
                                "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));" +
                                "arguments[0].dispatchEvent(new Event('change', {bubbles:true}));",
                        inputEl
                );
            }
        }

        // đợi selected = true
        wait.until(d -> d.findElement(inputBy).isSelected());
        return this;
    }
    public FormPage typeMobile(String value) {
        scrollIntoView(mobile);
        type(mobile, value);
        return this;
    }
    public FormPage typeEmail(String value) {
        scrollIntoView(email);
        type(email, value);
        return this;
    }
    public FormPage submit(){
        scrollIntoView(formButton);
        click(formButton);
        return this;
    }

    public By getSuccessLocator() {
        scrollIntoView(successMsg);
        return successMsg;
    }

    public By getErrorLocator() {
        scrollIntoView(errorMsg);
        return errorMsg;
    }


}
