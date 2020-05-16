package ShoppingCenter.Controllers;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BuyProductControllerTest {

    @Test
    public void validate_quantity() {
        String quantity = "12";
        BuyProductController product = new BuyProductController();
        boolean output = product.validate_quantity(quantity);
        assertTrue(output);
    }
}