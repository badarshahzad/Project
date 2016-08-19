package FrontEnd;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AddMobiles{

	private GridPane gridPane;
	private Label nameLabel;
	private TextField nameField;
	private Label RSOPriceLabel;
	private TextField RSOPriceField;
	private Label realPriceLabel;
	private TextField realPriceField;
	private Label franchisePriceLabel;
	private TextField franchisePriceField;
	private Button submitButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private ScrollPane scroll;


	public void start() {

		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.TOP_CENTER);

		
		title = new Label("Add Mobile");
		title.setStyle("-fx-font-size: 20px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 1);

		nameLabel = new Label("Name: ");
		gridPane.add(nameLabel, 3, 2);

		nameField = new TextField();
		gridPane.add(nameField, 4, 2);
		
		realPriceLabel = new Label("Real Price: ");
		gridPane.add(realPriceLabel, 3, 3);

		realPriceField = new TextField();
		gridPane.add(realPriceField, 4, 3);

		RSOPriceLabel = new Label("RSO Price: ");
		gridPane.add(RSOPriceLabel, 3, 4);

		RSOPriceField = new TextField();
		gridPane.add(RSOPriceField, 4, 4);

		franchisePriceLabel = new Label("Franchise Price: ");
		gridPane.add(franchisePriceLabel, 3, 5);

		franchisePriceField = new TextField();
		gridPane.add(franchisePriceField, 4, 5);

		submitButton = new Button("  ADD  ");
		submitButton.setId("submitButton");
		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getCode() == KeyCode.ENTER)
					addMobile();
				
			}
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				addMobile();
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
	
	public void addMobile(){
		
		if(validateInput()==true){
		dataBase.addMobiles(nameField.getText(),
				RSOPriceField.getText(), realPriceField.getText(), franchisePriceField.getText());
		nameField.setText("");
		RSOPriceField.setText("");
		realPriceField.setText("");
		franchisePriceField.setText("");
		JOptionPane.showMessageDialog(null, "Mobile Record Has Been Added");
		}

	}
	
	
	public boolean validateInput(){
		
		if(nameField.getText().matches("") || nameField.getText().matches("[ ]*")){
			JOptionPane.showMessageDialog(null,
					"Mobile Name is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(nameField.getText().matches("[0-9]*[A-Za-z ]*[0-9]*")==false){
			JOptionPane.showMessageDialog(null,
					"Mobile Name is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(RSOPriceField.getText().matches("[0-9]+")==false && RSOPriceField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null,
					"RSO Price is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(realPriceField.getText().matches("[0-9]+")==false && realPriceField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null,
					"Real Price is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if(franchisePriceField.getText().matches("[0-9]+")==false && franchisePriceField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null,
					"Franchise Price is Not Valid!", "Warning Message",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}

	public ScrollPane getScroll() {
		return scroll;
	}

}
