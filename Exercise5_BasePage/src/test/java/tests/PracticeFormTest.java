package tests;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.FormPage;

import static java.sql.DriverManager.getDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(OrderAnnotation.class)
public class PracticeFormTest extends BaseTest { // BaseTest của bạn phải cấp sẵn "driver"

    static WebDriverWait wait;
    private FormPage form;


    @BeforeEach
    void setUpEach() {// giả định BaseTest có getter getDriver(); nếu không, dùng protected field
        form = new FormPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test
    @Order(1)
    @DisplayName("Happy path: điền đầy đủ và Submit")
    void fillAllAndSubmit() throws Exception {

        form.open("https://demoqa.com/automation-practice-form");

        form.typeFirstName("Minh")
                .typeLastName("Nguyen")
                .typeEmail("minh.nguyen@example.com")
                .selectGender("Male")
                .typeMobile("0987654321");
        form.submit();
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getSuccessLocator()));
        assertTrue(success.getText().contains("Thanks for submitting the form"));
    }
    @Test
    @Order(2)
    @DisplayName(" điền thiếu thông tin ")
    void error() throws Exception {

        form.open("https://demoqa.com/automation-practice-form");

        form.typeFirstName("Minh")
                .typeLastName("Nguyen")
                .typeEmail("minh.nguyen@example.com")
                .typeMobile("0987654321");
        form.submit();
        WebElement error  = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getErrorLocator()));
        assertTrue(error.isDisplayed());
    }

    @ParameterizedTest(name = "CSV Inline: {0} {1} {2} {3} {4} -> {5}")
    @Order(3)
    @CsvSource({
            // first, last, email, gender, mobile, expected
            "Minh, Nguyen, minh.nguyen@example.com, Male, 0987654321, success",
            "Minh, Nguyen, , , 0987654321, error",                 // thiếu gender
            "'', Nguyen, a@b.com, Male, 0987654321, error",        // thiếu first
            "Minh, '', a@b.com, Male, 0987654321, error",          // thiếu last
            "Minh, Nguyen, a@b.com, Male, 12345, error",           // mobile < 10
            "Minh, Nguyen, not-an-email, Male, 0987654321, error"  // email sai pattern
    })
    void testFormCsvInline(String first, String last, String email, String gender, String mobile, String expected) {
        form.open("https://demoqa.com/automation-practice-form");

        first  = (first  == null) ? "" : first.trim();
        last   = (last   == null) ? "" : last.trim();
        email  = (email  == null) ? "" : email.trim();
        gender = (gender == null) ? "" : gender.trim();
        mobile = (mobile == null) ? "" : mobile.trim();

        if (!first.isEmpty())  form.typeFirstName(first);
        if (!last.isEmpty())   form.typeLastName(last);
        if (!email.isEmpty())  form.typeEmail(email);
        if (!gender.isEmpty()) form.selectGender(gender);
        if (!mobile.isEmpty()) form.typeMobile(mobile);

        form.submit();

        if ("success".equalsIgnoreCase(expected)) {
            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getSuccessLocator()));
            assertTrue(success.getText().contains("Thanks for submitting the form"));
        } else {
            WebElement error  = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getErrorLocator()));
            assertTrue(error.isDisplayed());
        }
    }

    @ParameterizedTest(name = "CSV File: {0} {1} {2} {3} {4} -> {5}")
    @Order(4)
    @CsvFileSource(resources = "/practice-form-data.csv", numLinesToSkip = 1)
    void testFormFromCSV(String first, String last, String email, String gender, String mobile, String expected) {
        form.open("https://demoqa.com/automation-practice-form");

        first  = (first  == null) ? "" : first.trim();
        last   = (last   == null) ? "" : last.trim();
        email  = (email  == null) ? "" : email.trim();
        gender = (gender == null) ? "" : gender.trim();
        mobile = (mobile == null) ? "" : mobile.trim();

        if (!first.isEmpty())  form.typeFirstName(first);
        if (!last.isEmpty())   form.typeLastName(last);
        if (!email.isEmpty())  form.typeEmail(email);
        if (!gender.isEmpty()) form.selectGender(gender);
        if (!mobile.isEmpty()) form.typeMobile(mobile);

        form.submit();

        if ("success".equalsIgnoreCase(expected)) {
            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getSuccessLocator()));
            assertTrue(success.getText().contains("Thanks for submitting the form"));
        } else {
            WebElement error  = wait.until(ExpectedConditions.visibilityOfElementLocated(form.getErrorLocator()));
            assertTrue(error.isDisplayed());
        }
    }

}