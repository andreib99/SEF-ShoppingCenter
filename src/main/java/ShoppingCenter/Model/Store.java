package ShoppingCenter.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Store {
    String name;
    public ArrayList<Product> products;

    public Store()
    {

    }

    public Store(String name)
    {
        this.name = name;
        products = new ArrayList<>();
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
