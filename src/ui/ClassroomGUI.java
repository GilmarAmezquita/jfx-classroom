package ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.Classroom;

public class ClassroomGUI {
	
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TextField txtUsernameLogIn;

    @FXML
    private PasswordField txtPasswordLogIn;
    
    @FXML
    private DatePicker txtBirthday;

    @FXML
    private TextField txtUsernameSignIn;

    @FXML
    private PasswordField txtPasswordSignIn;

    @FXML
    private TextField txtImageRoute;

    @FXML
    private ChoiceBox<String> cbFavoriteBrowser;

    @FXML
    private ToggleGroup genderSelect;
    
	private Classroom classroom;
    
	public ClassroomGUI(Classroom classroom) {
		this.classroom = classroom;
	}
	
    @FXML
    public void showScreenLogIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
    	fxmlLoader.setController(this);
    	Parent logInPane = fxmlLoader.load();
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(logInPane);
    }
    
    @FXML
    public void accountLogIn(ActionEvent event) throws IOException {
    	boolean enterAccount;
    	if(!txtUsernameLogIn.getText().isEmpty() && !txtPasswordLogIn.getText().isEmpty()){
    		enterAccount = classroom.logInAccount(txtUsernameLogIn.getText(), txtPasswordLogIn.getText());
    		if(enterAccount) {
        		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
        		fxmlLoader.setController(this);
        		Parent accountList= fxmlLoader.load();
        		mainPanel.getChildren().clear();
        		mainPanel.setCenter(accountList);
    		}else {
    			incorrectInformation();
    		}
     	}else {
     		missingInformation();
     	}
    }
    
    private void incorrectInformation() {
    	Alert incorrectInformation = new Alert(AlertType.INFORMATION);
		incorrectInformation.setTitle("Incorrect Information");
		incorrectInformation.setHeaderText(null);
		incorrectInformation.setContentText("The username and password given are incorrect");
		incorrectInformation.showAndWait();
    }
    private void missingInformation(){
    	Alert missingInformation = new Alert(AlertType.INFORMATION);
		missingInformation.setTitle("Missing Information");
		missingInformation.setHeaderText(null);
		missingInformation.setContentText("There are missing information in the form.");
		missingInformation.showAndWait();
    }

    @FXML
    public void showScreenSignUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);
    	Parent signUpPane = fxmlLoader.load();
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(signUpPane);
    	cbFavoriteBrowser.getItems().addAll("Chrome", "Opera", "FireFox", "Safari", "Edge", "Brave");
    }

    @FXML
    public void accountCreate(ActionEvent event) {
    	boolean createAcc = checkFormEmpty();
    	if(createAcc) {
    		String username = txtUsernameSignIn.getText();
    		String password = txtPasswordSignIn.getText();
    		String gender = ((RadioButton) genderSelect.getSelectedToggle()).getText();
    		String birthday = txtBirthday.getValue().toString();
    		String browser = cbFavoriteBrowser.getValue().toString();
    		classroom.createAccount(username, password, null, null, birthday, browser);
    	}
    }
    
    private boolean checkFormEmpty() {
    	boolean create = true;
    	Alert emptyAlert  =new Alert(AlertType.INFORMATION);
    	emptyAlert.setTitle("Missing Information");
    	emptyAlert.setHeaderText(null);
    	if(txtUsernameSignIn.getText().isEmpty()) {
    		create = false;
    		emptyAlert.setContentText("Username is missing");
    		emptyAlert.showAndWait();
    	}else if() {
    		
    	}
    	return create;
    }

    @FXML
    void browseImageRoute(ActionEvent event) {

    }
}
