package ShoppingCenter.Controllers;

import junit.framework.TestCase;

public class RegisterControllerTest extends TestCase {
    RegisterController register = new RegisterController();
    public void testValidate_password() { //if password has at least 8 characters
        String test_password = "12345678";
        boolean output = register.validate_password(test_password);
        assertTrue(output);
    }

    public void testValidate_name() { //if name doesn't have any digit
        String test_name = "Andrei";
        boolean output = register.validate_name(test_name);
        assertTrue(output);
    }
}