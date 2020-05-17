package ShoppingCenter.Controllers;


import ShoppingCenter.Model.Product;
import ShoppingCenter.Model.Store;
import ShoppingCenter.Services.UserService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RemoveProductController {

    public Label RemoveMessage;
    public Label SuccessMessage;
    @FXML
    ChoiceBox<String> ch;
    public void initialize()
    {
        List<String> temp = new ArrayList<>();
        for(Store store : UserService.stores) {
            if (store.getName().equals(UserService.getCurrent_store())) {
                for (Product product : store.getProducts()) {
                    temp.add(product.getName());
                }
            }
        }
        ch.getItems().addAll(FXCollections.observableArrayList(temp));
    }


    public void handleRemoveProductAction() {
        for(Store store : UserService.stores) {
            if (store.getName().equals(UserService.getCurrent_store())) {
                store.getProducts().removeIf(product -> product.getName().equals(ch.getValue()));
            }
        }

        if(ch.getValue()==null) {
            SuccessMessage.setText("Select a product!");
        }
        else
        {
            UserService.persistStores();
            SuccessMessage.setText("Product removed!");
        }
    }

    public void handleBackToProductsAction() {
        try {
            Stage stage = (Stage) RemoveMessage.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_manager.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
