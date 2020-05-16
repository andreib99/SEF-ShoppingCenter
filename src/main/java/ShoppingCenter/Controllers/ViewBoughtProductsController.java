package ShoppingCenter.Controllers;

import ShoppingCenter.Model.BoughtProduct;
import ShoppingCenter.Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

import static ShoppingCenter.Services.UserService.getCurrent_order;
import static ShoppingCenter.Services.UserService.orders;

public class ViewBoughtProductsController {


    public ListView<String> listview;
    public ObservableList <String> observableList = FXCollections.observableArrayList();
    public Text Date;
    public Text Store_name;
    public Text Client_name;

    public void initialize()
    {

        for(Order order : orders)
        {
            if(order.getDate().equals(getCurrent_order()))
            {
                Date.setText("Date: " + order.getDate());
                Store_name.setText("Store: " + order.getStore_name());
                Client_name.setText("Client: " + order.getClient_name());
                for(BoughtProduct bp : order.getBought_products())
                {
                    String temp = bp.getName() + "   " + bp.getQuantity();
                    observableList.add(temp);
                }
            }
        }
        listview.setItems(observableList);

    }

    public void handleViewOrderButtonAction() {
        try {
            Stage stage = (Stage) listview.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_orders_client.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
