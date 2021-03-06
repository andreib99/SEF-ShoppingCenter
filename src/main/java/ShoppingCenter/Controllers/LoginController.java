package ShoppingCenter.Controllers;

import ShoppingCenter.Model.Manager;
import ShoppingCenter.Services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static ShoppingCenter.Services.UserService.setCurrent_store;

public class LoginController< choice > {

    @FXML
    private Text LoginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox<String> role;

    @FXML
    public void initialize() throws IOException {
        role.getItems().addAll("Client", "Manager");
        UserService.loadClientsFromFile();
        UserService.loadManagersFromFile();
        UserService.loadStoresFromFile();
        UserService.loadOrdersFromFile();
    }

    @FXML
    private String getChoice() {
        String choice = this.role.getValue();
        return choice;
    }



    @FXML
    public void handleRegisterButtonAction() throws IOException {
        try {
            Stage stage = (Stage) LoginMessage.getScene().getWindow();
            Parent register = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
            Scene scene = new Scene(register, 600, 400);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String Role = role.getValue();

        if (username == null || username.isEmpty()) {
            LoginMessage.setText("Please type in a username!");
            return;
        }
        if (password == null || password.isEmpty()) {
            LoginMessage.setText("Please type in a password!");
            return;
        }
        if (Role == null || Role.isEmpty()) {
            LoginMessage.setText("Please select a role!");
            return;
        }

        if (getChoice().equals("Client")) {
            if (!UserService.verifyClient(username, password)) {
                LoginMessage.setText("The credentials are invalid!");
                return;
            }
        }
        if (getChoice().equals("Manager")) {
            if (!UserService.verifyManager(username, password)) {
                LoginMessage.setText("The credentials are invalid!");
                return;
            }
        }
        try {
            if (getChoice().equals("Client")) {
                UserService.setCurrent_client(usernameField.getText());
                UserService.setCurrent_manager("");
            }
            if (getChoice().equals("Manager")) {
                UserService.setCurrent_manager(usernameField.getText());
                UserService.setCurrent_client("");
            }
            LoginMessage.setText("Login successfully!");
            if (getChoice().equals("Client")) {
                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_stores.fxml")));

                Scene scene = new Scene(store);
                stage.setScene(scene);
            }
            else
            {
                for(Manager manager : UserService.managers)
                {
                    if(manager.getUsername().equals(usernameField.getText()))
                    {
                        setCurrent_store(manager.getStore_name());

                    }
                }

                Stage stage = (Stage) LoginMessage.getScene().getWindow();
                Parent Store = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view_products_manager.fxml")));
                Scene scene = new Scene(Store);
                stage.setScene(scene);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
