import org.AccountService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountServiceTest {


    AccountService service;

    @BeforeAll
    void initOnce() {
        service = new AccountService();
    }
    @DisplayName(" registration ")
    @ParameterizedTest(name = "[{index}] username={0}, password={1}, email={2} => expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void registrationFromCsv(String username, String password, String email, boolean expected) {
        boolean actual = service.registerAccount(username, password, email);
        assertEquals(expected, actual, "pass must have 8 character at least");
    }

    @Test
    @DisplayName(" Email validation quick checks")
    void emailValidation() {
        Assertions.assertTrue(service.isValidEmail("ok@test.com"));
        Assertions.assertFalse(service.isValidEmail("bad.com"));
        Assertions.assertFalse(service.isValidEmail(null));
    }

}