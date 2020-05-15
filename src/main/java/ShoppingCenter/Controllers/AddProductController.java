package ShoppingCenter.Controllers;


import ShoppingCenter.Model.Product;
import ShoppingCenter.Model.Store;
import ShoppingCenter.Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

import static ShoppingCenter.Services.UserService.stores;

public class AddProductController {

    @FXML
    public Text Message;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField promotionField;
    @FXML
    public TextField quantityField;

    public void handleFinishButtonAction()  {
        String name = nameField.getText();
        String price = priceField.getText();
        String promotion = promotionField.getText();
        String quantity = quantityField.getText();

        if(name.isEmpty())
        {
            Message.setText("Need to introduce a name!");
            return;
        }
        if(price.isEmpty())
        {
            Message.setText("Need to introduce a price!");
            return;
        }
        if(quantity.isEmpty())
        {
            Message.setText("Need to introduce a quantity!");
            return;
        }

        Product p = new Product(name,Integer.valueOf(quantity),Double.valueOf(price),Double.valueOf(promotion));
        String store = UserService.getCurrent_store();
        System.out.println(store);
        for (Store st : stores) {
            if (st.getName().equals(store)) {
                   st.products.add(p);
                   UserService.persistStores();

                }
            }
            Message.setText("Successfully modified!");

    }

    public void handleBackToStoreAction()
    {
        try {
            Stage stage = (Stage) Message.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_manager.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
