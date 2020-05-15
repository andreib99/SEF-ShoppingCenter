package ShoppingCenter.Exceptions;

public class ProductAlreadyExistsException extends Exception{
    private String product_name;

    public ProductAlreadyExistsException(String product_name) {
        super(String.format("An product with the name %s already exists!", product_name));
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }
}
