package seedu.address.ui;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.encryption.exceptions.UnsupportedPasswordException;
import seedu.address.logic.parser.PasswordCommandParser;

public class LoginScreen extends UiPart<Stage> {
    private static final String FXML = "LoginWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField userInputPassword;

    @FXML
    private Label responseDisplay;

    private final MainApp app;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     */
    public LoginScreen(MainApp app) {
        super(FXML);
        this.app = app;
    }

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     * @param primaryStage The stage to run.
     */
    public LoginScreen(MainApp app, Stage primaryStage) {
        super(FXML, primaryStage);
        this.app = app;
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void onEnter() {
        handleUserInputPassword();
    }

    private void handleUserInputPassword() {
        handlePassword();
    }

    private void handlePassword() {
        // blocks the case when / is appended to the end of password
        if (!PasswordCommandParser.isValidPassword(userInputPassword.getText())) {
            responseDisplay.setText("Wrong password, try again!");
            userInputPassword.clear();
            return;
        }
        try {
            boolean isCorrectPassword = app.logIn(userInputPassword.getText());
            // wrong password
            if (!isCorrectPassword) {
                responseDisplay.setText("Wrong password, try again!");
                userInputPassword.clear();
            }
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            // app problems
            responseDisplay.setText("Something went wrong, try again!");
            userInputPassword.clear();
        }
    }

    /**
     * Shows the login screen.
     */
    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
        responseDisplay.setText("Please enter your password!");
    }

}
