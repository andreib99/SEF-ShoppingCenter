package ShoppingCenter.Controllers;

import ShoppingCenter.Model.Order;
import ShoppingCenter.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ShoppingCenter.Services.UserService.getCurrent_client;
import static ShoppingCenter.Services.UserService.orders;

public class ViewOrdersClientController {
    public TableView<Order> storeTable;
    public Text Message;
    public Text ClientField;
    public TableColumn<Order, String> Client_name;
    public TableColumn<Order, String> Store_name;
    public TableColumn<Order, String> Date;
    private final ArrayList<Order> temp = new ArrayList<>();
    private final ArrayList<String> dates = new ArrayList<>();
    public ChoiceBox<String> ChoiceboxText;

    public void initialize()
    {
        for(Order order : orders)
        {
            if(order.getClient_name().equals(UserService.getCurrent_client()))
            {
                temp.add(order);
                dates.add(order.getDate());
            }
        }
        ChoiceboxText.getItems().addAll(FXCollections.observableArrayList(dates));
        Client_name.setCellValueFactory(new PropertyValueFactory<>("Client_name"));
        Store_name.setCellValueFactory(new PropertyValueFactory<>("Store_name"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        final ObservableList<Order> lines = FXCollections.observableArrayList(temp);
        storeTable.setItems(lines);
    }


    public void handleViewStoresButtonAction() {
        try {
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_stores.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleViewProductsButtonAction() {
        try {
            UserService.setCurrent_order(ChoiceboxText.getValue());
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_bought_products.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
