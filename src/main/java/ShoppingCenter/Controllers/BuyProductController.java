package ShoppingCenter.Controllers;


import ShoppingCenter.Model.BoughtProduct;
import ShoppingCenter.Model.Order;
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

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ShoppingCenter.Services.UserService.*;

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

    ArrayList<String> bought_products_name = new ArrayList<>();
    ArrayList<String> bought_products_quantity = new ArrayList<>();
    ArrayList<BoughtProduct> bought_products = new ArrayList<>();
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
        choiceBox.getItems().addAll(FXCollections.observableArrayList(temp));
    }

    public void handleFinishAction()
    {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        setCurrent_order(formattedDate);
        Order order = new Order(getCurrent_client(), getCurrent_store(), formattedDate);
        String current_store = UserService.getCurrent_store();
        for (Store st : stores) {
            if (st.getName().equals(current_store)) {
                for (Product p : st.getProducts()) {
                    for(BoughtProduct element : bought_products)
                    {
                        if(p.getName().equals(element.getName()))
                        {
                            p.setQuantity(p.getQuantity() - Integer.parseInt(element.getQuantity()));
                            order.getBought_products().add(element);
                        }

                    }

                }
            }
        }
        orders.add(order);
        UserService.persistOrders();
        UserService.persistStores();
        try {
            Stage stage = (Stage) RemoveMessage.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_client.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void handleBuyProductAction()
    {
      if(choiceBox.getValue() == null)
      {
          Message.setText("Choose an object!");
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
                                     return;
                                 }
                                 if(quantity_int > p.getQuantity())
                                 {
                                     Message.setText("Choose a small  quantity!");
                                     return;
                                 }
                                 else
                                 {
                                     BoughtProduct bp = new BoughtProduct(name, quantity);
                                     bought_products_name.add(name);
                                     bought_products_quantity.add(quantity);
                                    bought_products.add(bp);
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
             Message.setText(choiceBox.getValue() + " "+quantity);
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
