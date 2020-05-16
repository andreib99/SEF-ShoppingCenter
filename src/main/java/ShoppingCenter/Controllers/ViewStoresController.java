package ShoppingCenter.Controllers;

import ShoppingCenter.Model.Manager;
import ShoppingCenter.Model.Store;
import ShoppingCenter.Services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

import static ShoppingCenter.Services.UserService.*;


public class ViewStoresController {
    @FXML
    public TableView<Manager> storeTable;
    public Text ProductField;
    @FXML
    public Text Message;
    @FXML
    TextField StoreField;
    @FXML
    public TableColumn<Manager, String> Store_name;
    public TableColumn<Manager, String> Manager_name;
    public TableColumn<Manager, String> Manager_number;
    @FXML
    public void initialize() {
        Message.setText(UserService.getCurrent_client());
        Store_name.setCellValueFactory(new PropertyValueFactory<>("Store_name"));
        Manager_name.setCellValueFactory(new PropertyValueFactory<>("username"));
        Manager_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        storeTable.setItems(lines);
    }

    private ObservableList<Manager> lines = FXCollections.observableArrayList(managers);

    public void handleLoginButtonAction()
    {
        try {
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login2.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void handleEditButtonAction()
    {
        try {
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("edit_profile.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleViewProductsButtonAction() {

        try {
            String store_name = StoreField.getText();
            if(store_name.isEmpty())
            {
                ProductField.setText("Enter a store!");
                return;
            }
            boolean ok = false;
            for (Store st : stores) {
                if (st.getName().equals(store_name)) {
                    ok = true;
                    break;

                }
            }
            if(ok)
            {
                UserService.setCurrent_store(store_name);
            }
            else
            {
                ProductField.setText("Invalid store!");
                return;
            }

            if(getCurrent_client().equals("")) {
                Stage stage = (Stage) storeTable.getScene().getWindow();
                Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_manager.fxml")));
                Scene scene = new Scene(store);
                stage.setScene(scene);
            }
            else
            {
                Stage stage = (Stage) storeTable.getScene().getWindow();
                Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_client.fxml")));
                Scene scene = new Scene(store);
                stage.setScene(scene);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleViewOrdersButtonAction() {
        try{
            Stage stage = (Stage) storeTable.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_orders_client.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
            }catch (Exception e){
                e.printStackTrace();
             }
     }
}
