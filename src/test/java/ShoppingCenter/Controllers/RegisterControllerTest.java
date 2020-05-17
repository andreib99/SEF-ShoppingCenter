package ShoppingCenter.Controllers;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class RegisterControllerTest {

    @Test
    public void testValidate_password() {
        String password = "1234";
        RegisterController register = new RegisterController();
        boolean output = register.validate_password(password);
        assertTrue(output);
    }

    @Test
    public void validate_name() {
        String name = "Alexandru";
        RegisterController register = new RegisterController();
        boolean output = register.validate_name(name);
        assertTrue(output);
    }
}