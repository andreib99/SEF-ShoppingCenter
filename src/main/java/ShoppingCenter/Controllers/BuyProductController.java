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
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;
import static ShoppingCenter.Services.UserService.stores;

public class BuyProductController {

    public Label RemoveMessage;
    @FXML
    public Set <String> set = new HashSet<String>();
    public ObservableList <String> observableList = FXCollections.observableArrayList();
    @FXML
    public ListView<String> listView;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public TextField quantityField;
    @FXML
    public Text Message;
    @FXML
    public void initialize()
    {
        List<String> temp = new ArrayList<>();
        for(Store store : UserService.stores) {
            if (store.getName().equals(UserService.getCurrent_store())) {
                for (Product product : store.getProducts()) {
                    temp.add(product.getName());
                    System.out.println(product.getName());
                }
            }
        }
        choiceBox.getItems().addAll(FXCollections.observableArrayList(temp));
    }

    public void handleFinishAction()
    {

    }

    public void handleBuyProductAction()
    {
      if(choiceBox.getValue() == null)
      {
          Message.setText("Choose an object!");
          return;
      }
      else
          {
              String name = choiceBox.getValue();
             String quantity = quantityField.getText();
              int quantity_int = 0;
             if (quantity.isEmpty())
            {
              Message.setText("Enter a quantity!");
              return;
            }
             try {
                  quantity_int = Integer.parseInt(quantity);
                 String store = UserService.getCurrent_store();
                 for (Store st : stores)
                 {
                     if (st.getName().equals(store))
                     {
                         for (Product p : st.getProducts())
                         {
                             if (Objects.equals(p.getName(), name))
                             {
                                 if(p.getQuantity() == 0)
                                 {
                                     Message.setText("This product is not on stock!");
                                 }
                                 if(quantity_int > p.getQuantity())
                                 {
                                     Message.setText("Choose a small  quantity!");
                                     return;
                                 }
                             }
                         }
                     }
                 }
             } catch (NumberFormatException e)
             {
                Message.setText("Enter a number!");
                return ;
             }
             String final_String = name + " " + quantity;
             set.add(final_String);
             observableList.setAll(set);
             listView.setItems(observableList);
             Message.setText(choiceBox.getValue().toString() + " "+quantity);
          }
    }

    public void handleBackToProductsAction() {
        try {
            Stage stage = (Stage) RemoveMessage.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_client.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
