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
        String choice =this.role.getValue();
        return choice;
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

        if(username == null || username.isEmpty())
        {
            LoginMessage.setText("Please type in a username!");
            return;
        }
        if(password == null || password.isEmpty())
        {
            LoginMessage.setText("Please type in a password!");
            return;
        }
        if(Role == null ||Role.isEmpty())
        {
            LoginMessage.setText("Please select a role!");
            return;
        }
        try {

            LoginMessage.setText("Login successfully!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
