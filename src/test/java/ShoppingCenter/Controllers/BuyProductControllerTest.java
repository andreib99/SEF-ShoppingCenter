package ShoppingCenter.Controllers;

import junit.framework.TestCase;

public class BuyProductControllerTest extends TestCase {

    BuyProductController buy_product = new BuyProductController();
    public void testValidate_quantity() {
            String quantity = "10";

            boolean output = buy_product.validate_quantity(quantity);
            assertTrue(output);
    }
}