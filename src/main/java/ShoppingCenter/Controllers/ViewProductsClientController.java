package ShoppingCenter.Controllers;

import ShoppingCenter.Model.Product;
import ShoppingCenter.Model.Store;
import ShoppingCenter.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.Objects;

import static ShoppingCenter.Services.UserService.stores;

public class ViewProductsClientController {

    @FXML
    public TableView<Product> storeTable;

    @FXML
    public TableColumn<Product, String> Product_name;
    public TableColumn<Product, Integer> Product_quantity;
    public TableColumn<Product, Double> Product_price;
    @FXML
    private ObservableList<Product> lines;
    public void initialize() {

        Product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Product_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Product_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        String store = UserService.getCurrent_store();
        for (Store st : stores) {
            if (st.getName().equals(store)) {
                lines = FXCollections.observableArrayList(st.getProducts());
            }
        }

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

}
