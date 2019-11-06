package myems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    private DbConnection conn;

    @FXML
    public void checkLogin(ActionEvent e) throws IOException {
        String userInput = usernameField.getText();
        String username = "'" + userInput + "'";
        String passDb = Employee.getDbPass(conn.getConnection(), username);

        String passInput = passwordField.getText();

        if (passInput.equals(passDb)) {
            Parent adminParent = FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
            Scene adminScene = new Scene(adminParent);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(adminScene);
            window.show();
        } else {
            Alert message = new Alert(AlertType.ERROR);
            message.setTitle("Login Attempt");
            message.setHeaderText("Result:");
            message.setContentText("You have entered an invalid username or password");
            message.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = new DbConnection();
        conn.getConnection();
        usernameField.setText("");
        passwordField.setText("");
    }
}
