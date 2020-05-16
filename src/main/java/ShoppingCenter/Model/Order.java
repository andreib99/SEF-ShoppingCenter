package ShoppingCenter.Model;

import java.util.ArrayList;


public class Order {
    String Client_name;
    String Store_name;
    String Date;
    ArrayList<BoughtProduct> Bought_products;

    public Order()
    {

    }

    public Order(String Client_name, String Store_name, String Date)
    {
        Bought_products = new ArrayList<>();
        this.Client_name = Client_name;
        this.Store_name = Store_name;
        this.Date = Date;
    }

    public String getClient_name() {
        return Client_name;
    }

    public void setClient_name(String client_name) {
        Client_name = client_name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStore_name() {
        return Store_name;
    }

    public void setStore_name(String store_name) {
        Store_name = store_name;
    }

    public ArrayList<BoughtProduct> getBought_products() {
        return Bought_products;
    }

    public void setBought_products(ArrayList<BoughtProduct> bought_products) {
        Bought_products = bought_products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Client_name='" + Client_name + '\'' +
                ", Store_name='" + Store_name + '\'' +
                ", Date='" + Date + '\'' +
                ", Bought_products=" + Bought_products +
                '}';
    }

}
