package vuecontroller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.database.DatabaseConnection;
import model.database.dao.UtilisateurDAO;
import model.utilisateurs.Utilisateur;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectButton.setDisable(true);
        passwordButton.setDisable(true);

        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                connectButton.setDisable(true);
                passwordButton.setDisable(true);
            } else {
                connectButton.setDisable(false);
                passwordButton.setDisable(false);
            }
        });
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button connectButton;

    @FXML
    private Button passwordButton;

    @FXML
    public void handleConnect() {
        usernameField.setText(usernameField.getText().trim());
        String username = usernameField.getText();
        String password = passwordField.getText();

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(DatabaseConnection.getInstance());
        Utilisateur utilisateur = utilisateurDAO.getByUsername(username);

        if (utilisateur == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Utilisateur introuvable");
            alert.setHeaderText("L'utilisateur " + username + " est introuvable");
            alert.setContentText("L'utilisateur n'a pas été trouvé. Veuillez saisir un nom d'utilisateur existant" +
                    " ou créez un nouveau compte utilisateur depuis un compte administrateur.");
            alert.showAndWait();
        } else {
            if (!utilisateur.getPassword().equals(password)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mot de passe incorrect");
                alert.setHeaderText("Mot de passe incorrect");
                alert.setContentText("Le mot de passe que vous avez saisi est incorrect. Si vous avez oublié votre" +
                        " mot de passe, veuillez cliquer sur <mot de passe oublié>.");
                alert.showAndWait();
            } else {
                if (utilisateur.getType() == Utilisateur.Type.ADMINISTRATEUR) {
                    // administrateur

                } else {
                    // mode invité
                    // tenir compte des droits

                }
            }
        }
    }

    @FXML
    public void handleForgotPassword() {
        usernameField.setText(usernameField.getText().trim());
        String username = usernameField.getText();

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO(DatabaseConnection.getInstance());
        Utilisateur utilisateur = utilisateurDAO.getByUsername(username);

        Alert alert;
        if (utilisateur == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Utilisateur introuvable");
            alert.setHeaderText("L'utilisateur " + username + " est introuvable");
            alert.setContentText("L'utilisateur n'a pas été trouvé. Veuillez saisir un nom d'utilisateur existant" +
                    " ou créez un nouveau compte utilisateur depuis un compte administrateur.");
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mot de passe oublié");
            alert.setHeaderText("Mot de passe de l'utilisateur " + username + " a été oublié");
            alert.setContentText("Afin de récupérer votre mot de passe, un mail de récupération a été envoyé à " +
                    " l'adresse mail liée au compte. Une autre façon de procéder est de se conecter sur un " +
                    " compte administrateur afin d'y modifier le mot de passe.");
        }
        alert.showAndWait();
    }

}