package FrontEnd;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class LogIn{

	private GridPane grid;
	private Text welcomeText;
	private Label userName;
	private TextField nameTextField;
	private Label password;
	private PasswordField passwordField;
	private Button signInButton;
	private HBox hBox;
	private ScrollPane scrollPane;
	
	public void start(){
		// TODO Auto-generated method stub
		
		grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setVgap(10);
		grid.setHgap(10);
		grid.setPadding(new Insets(100, 0, 0, 0));
		
		welcomeText = new Text("Welcome");
		welcomeText.setId("welcomeText");
		grid.add(welcomeText, 0, 0);
		
		userName = new Label("User Name: ");
		grid.add(userName, 0, 1);
		
		nameTextField = new TextField();
		grid.add(nameTextField, 1, 1);
		
		password = new Label("Password: ");
		password.setId("label");
		grid.add(password, 0, 2);
		
		passwordField = new PasswordField();
		grid.add(passwordField, 1, 2);
		
		signInButton = new Button("Sign in");
		signInButton.setId("SignInButton");
		
		
		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().add(signInButton);
		grid.add(hBox, 1, 4);
		scrollPane = new ScrollPane(grid);
	}
	
	public ScrollPane getScrollPane(){
		return scrollPane;
	}
	
	public Button getButton(){
		return signInButton;
	}
	
	public TextField getUserNameField(){
		
		return nameTextField;
	}
	
    public PasswordField getUserPasswordField(){
		
		return passwordField;
	}
    
    public void setUserPasswordField(){
		
		passwordField.setText("");
	}
    
    public void setUserNmamedField(){
		
		nameTextField.setText("");
	}


}

