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
import seedu.address.logic.commands.PasswordCommand;
import seedu.address.logic.parser.PasswordCommandParser;

public class SetUpScreen extends UiPart<Stage> {
    private static final String FXML = "SetUpPassword.fxml";
    private static final String PASSWORDS_DO_NOT_MATCH = "Two passwords do not match!";
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private PasswordField firstPassword;

    @FXML
    private PasswordField userInput;

    @FXML
    private Label responseDisplay;

    private final MainApp app;

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     */
    public SetUpScreen(MainApp app) {
        super(FXML);
        this.app = app;
    }

    /**
     * Constructs a new LoginScreen.
     *
     * @param app The app to have the login screen.
     * @param primaryStage The stage to run.
     */
    public SetUpScreen(MainApp app, Stage primaryStage) {
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
        if (userInput.getText().equals(firstPassword.getText())) {
            handleNewPassword();
        } else {
            responseDisplay.setText(PASSWORDS_DO_NOT_MATCH);
            firstPassword.clear();
            userInput.clear();
        }
    }

    private void handleNewPassword() {
        if (!PasswordCommandParser.passwordValidation(userInput.getText())) {
            responseDisplay.setText(PasswordCommand.CORRECT_PASSWORD_FORMAT);
            firstPassword.clear();
            userInput.clear();
            return;
        }
        try {
            app.logIn(userInput.getText());
        } catch (UnsupportedPasswordException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            responseDisplay.setText("Something went wrong, try again!");
        }
    }

    /**
     * Shows the login screen.
     */
    public void show() {
        logger.fine("Showing login page...");
        getRoot().show();
        getRoot().centerOnScreen();
        responseDisplay.setText("Please set up a new password!");
    }

}
