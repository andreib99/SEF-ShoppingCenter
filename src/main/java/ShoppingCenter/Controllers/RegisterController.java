package ShoppingCenter.Controllers;

import ShoppingCenter.Exceptions.StoreAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;
import ShoppingCenter.Services.UserService;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static ShoppingCenter.Services.UserService.*;

public class RegisterController {
    @FXML
    private Text LoginMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField storeField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> role;


    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Manager");
    }

    @FXML
    private String getChoice()
    {
        return this.role.getValue();
    }
    @FXML
    public void handleSigningButtonAction()
    {
        String username = usernameField.getText();
        String name = nameField.getText();
        String number = numberField.getText();
        String password = passwordField.getText();
        String address = addressField.getText();
        String store = storeField.getText();


        if(role.getValue()==null)
        {
            LoginMessage.setText("Need to select a role!");
            return;
        }

        if(!validate_password(password))
        {
            LoginMessage.setText("Password needs to be more than 3 letters!");
        }

        try {
            if(getChoice().equals("Manager")) {
                setCurrent_manager(username);
                setCurrent_client("");
                UserService.addManager(username, password, name, number, store);
            }
            if(getChoice().equals("Client")) {
                setCurrent_client(username);
                setCurrent_manager("");
                UserService.addClient(username, password , name, number, address);
            }
            LoginMessage.setText("Account created successfully!");
            if(getChoice().equals("Manager"))
            {
                setCurrent_store(store);
                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent Store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_manager.fxml")));
                Scene scene = new Scene(Store);
                stage.setScene(scene);
            }
            else
            {
                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent Store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_stores.fxml")));
                Scene scene = new Scene(Store);
                stage.setScene(scene);
            }

        } catch (UsernameAlreadyExistsException | StoreAlreadyExistsException e) {
            LoginMessage.setText(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean validate_password(String password)
    {
        if(password.length() > 3)
        {
            return true;
        }
        return false;
    }

    public boolean validate_name(String name)
    {
        for(char ch : name.toCharArray())
        {
            if(Character.isDigit(ch))
            {
                return false;
            }
        }
        return true;
    }


    public void handleLoginButtonAction()
    {
        try {
            Stage stage = (Stage) LoginMessage.getScene().getWindow();
            Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login2.fxml")));
            Scene scene = new Scene(store);
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
