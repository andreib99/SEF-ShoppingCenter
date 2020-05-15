package ShoppingCenter.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Store {
    String name;
    ArrayList<Product> products;

    public Store()
    {

    }

    public Store(String name)
    {
        this.name = name;
        products = new ArrayList<>();
        Product p = new Product("trousers", 10, 50.78, 0.0);
        products.add(p);
        Product p1 = new Product("hat", 20, 20.99, 20.0);
        products.add(p1);
        products.sort(Comparator.comparing(Product::getName));
        }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Comparator(Product p1, Product p2)
    {

    }


    @Override
    public String toString() {
        return "StoreDTO{" +
                "name='" + name + '\'' +
                ", products=" + products +
                '}';
    }

}
