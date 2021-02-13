package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TextField txtUsernameLogIn;

    @FXML
    private PasswordField txtPasswordLogIn;
    
    @FXML
    private Label lbAccountUsername;

    @FXML
    private ImageView userAvatar;
    
    @FXML
    private TableView<UserAccount> tvAccountList;

    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String>tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;
    
    @FXML
    private TextField txtUsernameSignIn;
    
    @FXML
    private PasswordField txtPasswordSignIn;
    
    @FXML
    private TextField txtImageRoute;

    @FXML
    private ToggleGroup genderSelect;
    
    @FXML
    private CheckBox cbSoftwareEng;

    @FXML
    private CheckBox cbTelematicEng;

    @FXML
    private CheckBox cbIndustrialEng;
    
    @FXML
    private DatePicker txtBirthday;

    @FXML
    private ChoiceBox<String> cbFavoriteBrowser;

	private Classroom classroom;
    
	public ClassroomGUI(Classroom classroom){
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
    public void accountLogIn(ActionEvent event) throws IOException{
    	boolean enterAccount;
    	if(!txtUsernameLogIn.getText().isEmpty() && !txtPasswordLogIn.getText().isEmpty()){
    		enterAccount = classroom.logInAccount(txtUsernameLogIn.getText(), txtPasswordLogIn.getText());
    		if(enterAccount) {
    			showScreenAccountList(txtUsernameLogIn.getText());
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

    private void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getAccounts());
		tvAccountList.setItems(observableList);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender")); 
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
    }
    
    public void showScreenAccountList(String username)throws IOException{
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
    	fxmlLoader.setController(this);
    	Parent accountListPane = fxmlLoader.load();
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(accountListPane);
    	lbAccountUsername.setText(username);
    	Image avatar = new Image("file:///"+classroom.getUserAvatar(username),50,50,false,false);
    	userAvatar.setImage(avatar);
    	initializeTableView();
    }
    
    @FXML
    public void showScreenSignUp(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
    	fxmlLoader.setController(this);
    	Parent signUpPane = fxmlLoader.load();
    	mainPanel.getChildren().clear();
    	mainPanel.setCenter(signUpPane);
    	txtImageRoute.setEditable(false);
    	cbFavoriteBrowser.getItems().addAll("Chrome", "Opera", "FireFox", "Safari", "Edge", "Brave");
    }    
    
    @FXML
    void selectedSoftware(ActionEvent event) {
    	cbTelematicEng.setSelected(false);
    	cbIndustrialEng.setSelected(false);
    }
    
    @FXML
    void selectedTelematic(ActionEvent event) {
    	cbSoftwareEng.setSelected(false);
    	cbIndustrialEng.setSelected(false);
    }
    
    @FXML
    void selectedIndustrial(ActionEvent event) {
    	cbSoftwareEng.setSelected(false);
    	cbTelematicEng.setSelected(false);
    }

    private String verifyCareer() {
    	String career;
    	if(cbSoftwareEng.isSelected()) {
    		career = cbSoftwareEng.getText();
    	}else if(cbTelematicEng.isSelected()) {
    		career = cbTelematicEng.getText();
    	}else {
    		career = cbIndustrialEng.getText();
    	}
    	return career;
    }
    
    @FXML
    public void accountCreate(ActionEvent event) throws IOException {
    	boolean createAcc = checkForEmpty();
    	boolean created = false;
    	if(createAcc) {
    		String username = txtUsernameSignIn.getText();
    		String password = txtPasswordSignIn.getText();
    		String imageRute = txtImageRoute.getText();
    		String gender = ((RadioButton) genderSelect.getSelectedToggle()).getText();
    		String career = verifyCareer();
    		String birthday = txtBirthday.getValue().toString();
    		String browser = cbFavoriteBrowser.getValue().toString();
    		created = classroom.createAccount(username, password,imageRute, gender, career, birthday, browser);
    		alertAccount(created);
    	}
    }
    
    private boolean checkForEmpty() {
    	boolean create = true;
    	Alert emptyAlert  =new Alert(AlertType.INFORMATION);
    	emptyAlert.setTitle("Missing Information");
    	emptyAlert.setHeaderText(null);
    	if(txtUsernameSignIn.getText().isEmpty()) {
    		create = false;
    		emptyAlert.setContentText("Username is missing");
    		emptyAlert.showAndWait();
    	}else if(txtPasswordSignIn.getText().isEmpty()) {
    		create = false;
    		emptyAlert.setContentText("Password is missing");
    		emptyAlert.showAndWait();		
    	}else if(genderSelect.getSelectedToggle() ==null) {
    		create = false;
    		emptyAlert.setContentText("Gender is missing");
    		emptyAlert.showAndWait();
    	}else if(!cbSoftwareEng.isSelected() && !cbTelematicEng.isSelected() && !cbIndustrialEng.isSelected()){
    		create = false;
    		emptyAlert.setContentText("Career is missing");
    		emptyAlert.showAndWait();
    	}else if(txtBirthday.getValue() == null) {
    		create = false;
    		emptyAlert.setContentText("Birthday is missing");
    		emptyAlert.showAndWait();
    	}else if(cbFavoriteBrowser.getValue() == null) {
    		create = false;
    		emptyAlert.setContentText("Favorite Browser is missing");
    		emptyAlert.showAndWait();
    	}
    	return create;
    }
 
    private void alertAccount(boolean created) throws IOException{
    	Alert alertCreated = new Alert(AlertType.INFORMATION);
    	alertCreated.setHeaderText(null);
    	if(created) {
    		alertCreated.setTitle("Account created");
    		alertCreated.setContentText("The new account has been created");
    		showScreenLogIn(null);
    	}else {
    		alertCreated.setTitle("Validation Error");
    		alertCreated.setContentText("Username Already exist");
    		txtPasswordSignIn.setText(null);
    	}
    	alertCreated.showAndWait();
    }

    @FXML
    public void browseImageRoute(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Image");
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("Png Files", "*.png")
    			, new FileChooser.ExtensionFilter("Jpg Files","*.jpg"));
    	 File selectedFile = fileChooser.showOpenDialog(null);
    	 txtImageRoute.setText(selectedFile.getPath());
    }
    
    @FXML
    void accountLogOut(ActionEvent event) throws IOException {
    	showScreenLogIn(null);
    }
}
