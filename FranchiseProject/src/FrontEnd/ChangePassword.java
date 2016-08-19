package FrontEnd;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ChangePassword{

	private GridPane gridPane;
	private Label currentPasswordLabel;
	private PasswordField currentPasswordField;
	private Label newPassword1Label;
	private PasswordField newPassword1Field;
	private Label newPassword2Label;
	private PasswordField newPassword2Field;
	private Button submitButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private ScrollPane scrollPane;


	public void start() throws Exception {

		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.TOP_CENTER);

		title = new Label("Change Password");
		title.setStyle("-fx-font-size: 17px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 3);

		currentPasswordLabel = new Label("Current Passsword: ");
		gridPane.add(currentPasswordLabel, 3, 5);

		currentPasswordField = new PasswordField();
		currentPasswordField.setMaxWidth(175);
		gridPane.add(currentPasswordField, 4, 5);

		newPassword1Label = new Label("New Password: ");
		gridPane.add(newPassword1Label, 3, 6);

		newPassword1Field = new PasswordField();
		newPassword1Field.setMaxWidth(175);
		gridPane.add(newPassword1Field, 4, 6);

		newPassword2Label = new Label("New Password: ");
		gridPane.add(newPassword2Label, 3, 7);

		newPassword2Field = new PasswordField();
		newPassword2Field.setMaxWidth(175);
		gridPane.add(newPassword2Field, 4, 7);

		submitButton = new Button("  OK  ");
		submitButton.setId("submitButton");

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(submitButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 4,8);
		scrollPane = new ScrollPane(gridPane);
	}

	public String getPassword(){
		
		return currentPasswordField.getText();
	}
	public String getPassword1(){
		
		return newPassword1Field.getText();
	}
	public String getPassword2(){
		
		return newPassword2Field.getText();
	}
	public ScrollPane getScrollPane(){
		return scrollPane;
	}
	public Button getButton(){
		return submitButton;
	}
	public void setFieldsToNull(){
		
		currentPasswordField.setText("");
		newPassword1Field.setText("");
		newPassword2Field.setText("");
	}

}
