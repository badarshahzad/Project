package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddEmployee extends Application {

	private GridPane gridPane;
	private Label nameLabel;
	private TextField nameField;
	private Label CNICLabel;
	private TextField CNICField;
	private Label phoneNumberLabel;
	private TextField phoneNumberField;
	private Label statusLabel;
	@SuppressWarnings("rawtypes")
	private ComboBox statusInput;
	private ArrayList<String> statusTypes;
	private Button submitButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private ScrollPane scroll;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(Stage primaryStage) throws Exception {

		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.TOP_CENTER);

		
		title = new Label("Add Employee");
		title.setStyle("-fx-font-size: 20px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 1);

		nameLabel = new Label("Name: ");
		gridPane.add(nameLabel, 3, 2);

		nameField = new TextField();
		nameField.setMinWidth(175);
		nameField.setMaxWidth(175);
		gridPane.add(nameField, 4, 2);

		CNICLabel = new Label("CNIC: ");
		gridPane.add(CNICLabel, 3, 3);

		CNICField = new TextField();
		CNICField.setMinWidth(175);
		CNICField.setMaxWidth(175);
		gridPane.add(CNICField, 4, 3);

		phoneNumberLabel = new Label("Phone Number: ");
		gridPane.add(phoneNumberLabel, 3, 4);

		phoneNumberField = new TextField();
		phoneNumberField.setMinWidth(175);
		phoneNumberField.setMaxWidth(175);
		gridPane.add(phoneNumberField, 4, 4);

		statusLabel = new Label("Status: ");
		gridPane.add(statusLabel, 3, 5);

		statusTypes = new ArrayList<>();
		statusTypes.add("RSO");
		statusTypes.add("SND");
		statusTypes.add("CRO");
		statusTypes.add("CRM");
		statusTypes.add("FS");
		statusInput = new ComboBox();
		statusInput.setMinWidth(175);
		statusInput.setMaxWidth(175);
		statusInput.getItems().addAll(statusTypes);
		gridPane.add(statusInput, 4, 5);

		submitButton = new Button("  ADD  ");
		submitButton.setId("submitButton");
		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					addEmployee();
			}
			
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				addEmployee();
			}

		});

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(submitButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 4, 6);
		gridPane.setAlignment(Pos.TOP_CENTER);
		scroll = new ScrollPane(gridPane);

	}
	
	@SuppressWarnings("unchecked")
	public void addEmployee(){
		
		if(validateInput()==true){
			dataBase.addEmployeeInDataBase(nameField.getText(),
					CNICField.getText(), phoneNumberField.getText(), (String) statusInput.getSelectionModel().getSelectedItem());
			nameField.setText("");
			CNICField.setText("");
			phoneNumberField.setText("");
			statusInput.setValue("");
			JOptionPane.showMessageDialog(null, "Employee has been added");
			}
	}
	public boolean validateInput(){
		
		if(nameField.getText().isEmpty() || nameField.getText().matches("[ ]*")){
			
			JOptionPane.showMessageDialog(null,
					"Given Name is not valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if(nameField.getText().matches("[0-9]*[A-Za-z ]*[0-9]*")==false){
			JOptionPane.showMessageDialog(null,
					"Given Name is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
			
		if(CNICField.getText().matches("[0-9]{5}[-]{1}[0-9]{7}[-]{1}[0-9]{1}")==false){
			JOptionPane.showMessageDialog(null,
					"Given CNIC is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		else if(((phoneNumberField.getText().matches("03[0-9]{9}")==false &&
				phoneNumberField.getText().matches("03[0-9]{2}[-][0-9]{7}")==false))&&
				phoneNumberField.getText().matches("[+]{1}923[0-9]{9}")==false &&
				phoneNumberField.getText().matches("[+]{1}923[-]{1}[0-9]{9}")==false){
			JOptionPane.showMessageDialog(null,
					"Given Phone Number is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	public ScrollPane getScroll() {
		return scroll;
	}

}
