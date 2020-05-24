package ShoppingCenter.Controllers;

import junit.framework.TestCase;

public class AddProductControllerTest extends TestCase {
    AddProductController add = new AddProductController();
    public void testCheckProductDoesNotAlreadyExist(){
        String test_product = "Hat";
        String test_store = "Nike";
        boolean output = add.checkProductDoesNotAlreadyExist(test_store, test_product);
        assertFalse(output);
    }
}