package ShoppingCenter.Controllers;

import ShoppingCenter.Exceptions.ProductAlreadyExistsException;
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

public class EditProductController {
    @FXML
    public Text Message;
    @FXML
    public TextField nameField;
    @FXML
    public TextField priceField;
    @FXML
    public TextField discountField;
    @FXML
    public TextField quantityField;

    public void handleFinishButtonAction() {
        String name = nameField.getText();
        String price = priceField.getText();
        String promotion = discountField.getText();
        String quantity = quantityField.getText();
        int found = 0;

        double price_init = 0;
        int discount = 0;

        if (name.isEmpty()) {
            Message.setText("Need to introduce a name!");
            return;
        }
        String store = UserService.getCurrent_store();
        for (Store st : stores)
        {
            if (st.getName().equals(store))
            {
                for (Product p : st.getProducts())
                {
                    if (Objects.equals(p.getName(), name))
                    {
                        found = 1;
                        if (price.isEmpty())
                        {
                            price_init = p.getPrice();
                            if (!promotion.isEmpty())
                            {
                                discount = Integer.parseInt(promotion);
                                price_init = price_init - (price_init * discount) / 100;
                                p.setPrice(price_init);
                                p.setPromotion((double) discount);
                            }
                        }
                        if (!price.isEmpty())
                        {
                            price_init = Double.parseDouble(price);
                            if (!promotion.isEmpty())
                            {
                                discount = Integer.parseInt(promotion);
                                price_init = price_init - (price_init * discount) / 100;
                                p.setPrice(price_init);
                                p.setPromotion((double) discount);
                            }
                        }
                        if (quantity.isEmpty())
                        {
                            p.setQuantity(p.getQuantity());
                        }
                        if (!quantity.isEmpty())
                        {
                          p.setQuantity(Integer.valueOf(quantity));
                        }
                            UserService.persistStores();
                            Message.setText("Successfully modified!");
                    }

                }

            }
        }

        if(found == 0)
        {
            Message.setText("This product don't exist in the store!");
        }
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
