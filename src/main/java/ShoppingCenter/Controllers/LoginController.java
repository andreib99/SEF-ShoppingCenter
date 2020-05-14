package ShoppingCenter.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.util.Objects;
import ShoppingCenter.Services.UserService;

import static ShoppingCenter.Services.UserService.verifyClient;
import static ShoppingCenter.Services.UserService.verifyManager;

public class LoginController {

    @FXML
    private Text LoginMessage;
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
    public void handleRegisterButtonAction()
    {

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
        try {
            if (Role.equals("Client")) {
                if (verifyClient(username, password)) {
                    LoginMessage.setText("Login successfully!");
                    return;
                }
            }
            if (Role.equals("Manager")) {
                if (verifyManager(username, password)) {
                    LoginMessage.setText("Login successfully!");
                    return;
                }
            }
        } catch (Exception e) {
            LoginMessage.setText("Login failed!");
        }
    }

}
