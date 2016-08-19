package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChangePrice{

	private GridPane gridPane1;
	private Label realPriceLabel1;
	private Label realPriceField1;
	private Label franchisePriceLabel1;
	private Label franchisePriceField1;
	private Label RSOPriceLabel1;
	private Label RSOPriceField1;
	private Button editButton1;
	private HBox hBox1;
	private HBox hBoxTitle1;
	private Label title1;

	private GridPane gridPane3;
	private Label realPriceLabel3;
	private Label realPriceField3;
	private Label franchisePriceLabel3;
	private Label franchisePriceField3;
	private Label RSOPriceLabel3;
	private Label RSOPriceField3;
	private Button editButton3;
	private HBox hBox3;
	private HBox hBoxTitle3;
	private Label title3;

	private GridPane gridPane2;
	private Label realPriceLabel2;
	private Label realPriceField2;
	private Label franchisePriceLabel2;
	private Label franchisePriceField2;
	private Label RSOPriceLabel2;
	private Label RSOPriceField2;
	private Button editButton2;
	private HBox hBox2;
	private HBox hBoxTitle2;
	private Label title2;

	private GridPane gridPane4;
	private Label realPriceLabel4 = new Label("Real Price: ");;
	private Label realPriceField4;
	private Label franchisePriceLabel4 = new Label("Franchise Price: ");;
	private Label franchisePriceField4;
	private Label RSOPriceLabel4 = new Label("RSO Price: ");
	private Label RSOPriceField4;
	private Button editButton4;
	private HBox hBox4;
	private HBox hBoxTitle4;
	private Label title4;
	private VBox mobileLabels;
	private VBox mobilePriceLabels;
	@SuppressWarnings("rawtypes")
	private Spinner spinner;
	@SuppressWarnings("rawtypes")
	private ObservableList mobileAllNames = FXCollections.observableArrayList("Select Mobile");
	private ArrayList<String> mobilesData = new ArrayList<>();
	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private String[][] prices;

	private ScrollPane scroll;

	private VBox box = new VBox();
	private String[][] mobilesAllData;
	TextField RSOField = new TextField();
	TextField franchiseField = new TextField();
	TextField realField = new TextField();

	public void start()  {
		makeVBox();
	}

	@SuppressWarnings("unchecked")
	public void getMobileData() {

		mobilesData = dataBase.getMobileData();
		mobilesAllData = new String[mobilesData.size()][4];
	}

	@SuppressWarnings("unchecked")
	public void setMobilesName() {
		getMobileData();
		for (int row = 0; row < mobilesData.size(); row++) {

			String[] data = mobilesData.get(row).split(",");
			for (int col = 0; col < data.length; col++) {
				mobilesAllData[row][col] = data[col];
				if (col == 0) {
					mobileAllNames.add(data[0]);
				}
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void makeMobilePriceGrid() {

		gridPane4 = new GridPane();
		gridPane4.setHgap(10);
		gridPane4.setVgap(10);
		gridPane4.setAlignment(Pos.CENTER);

		title4 = new Label("Mobile Price");
		//		title4.setStyle("-fx-font-size: 20px");
		hBoxTitle4 = new HBox(title4);
		hBoxTitle4.setAlignment(Pos.CENTER);
		title4.setId("title");
		gridPane4.add(hBoxTitle4, 0, 0);

		spinner = new Spinner(mobileAllNames);
		gridPane4.add(spinner, 0, 1);

		mobileLabels = new VBox(10);
		mobilePriceLabels = new VBox(10);
		
		spinner.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				String value = (String) spinner.getValue();
				if(event.getCode() == KeyCode.UP){
					
					int index = mobileAllNames.indexOf(value);
					index+=1;
					if(index == mobileAllNames.size()){
						value = (String) mobileAllNames.get(index-1);
					}
					else{
						value = (String) mobileAllNames.get(index);
					}
				}
				
				if(event.getCode() == KeyCode.DOWN){
					
					int index = mobileAllNames.indexOf(value);
					index-=1;
					if(index < 0){
						value = (String) mobileAllNames.get(index + 1);
					}
					else{
						value = (String) mobileAllNames.get(index);
					}
				}
					
					
					
				mobileLabels.getChildren().clear();
				mobilePriceLabels.getChildren().clear();


				for (int i = 0; i < mobilesAllData.length; i++) {

					if (value.equals(mobilesAllData[i][0])) {

						mobileLabels.getChildren().add(realPriceLabel4);
						mobileLabels.getChildren().add(franchisePriceLabel4);
						mobileLabels.getChildren().add(RSOPriceLabel4);

						realPriceField4 = new Label(mobilesAllData[i][1]);
						mobilePriceLabels.getChildren().add(realPriceField4);

						franchisePriceField4 = new Label(mobilesAllData[i][3]);
						mobilePriceLabels.getChildren().add(franchisePriceField4);

						RSOPriceField4 = new Label(mobilesAllData[i][2]);
						mobilePriceLabels.getChildren().add(RSOPriceField4);
						break;

					}
				}
			}
		});

		spinner.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub

				mobileLabels.getChildren().clear();
				mobilePriceLabels.getChildren().clear();
				String value = (String) spinner.getValue();

				for (int i = 0; i < mobilesAllData.length; i++) {

					if (value.equals(mobilesAllData[i][0])) {

						mobileLabels.getChildren().add(realPriceLabel4);
						mobileLabels.getChildren().add(franchisePriceLabel4);
						mobileLabels.getChildren().add(RSOPriceLabel4);

						realPriceField4 = new Label(mobilesAllData[i][1]);
						mobilePriceLabels.getChildren().add(realPriceField4);

						franchisePriceField4 = new Label(mobilesAllData[i][3]);
						mobilePriceLabels.getChildren().add(franchisePriceField4);

						RSOPriceField4 = new Label(mobilesAllData[i][2]);
						mobilePriceLabels.getChildren().add(RSOPriceField4);
						break;

					}
				}
			}
		});

		gridPane4.add(mobileLabels, 0, 2);
		gridPane4.add(mobilePriceLabels, 1, 2);

		editButton4 = new Button("  Edit  ");
		editButton4.setId("submitButton");
		editButton4.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					changeMobilePrices();
			}
		});
		editButton4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				changeMobilePrices();
			}

		});

		hBox4 = new HBox();
		hBox4.setAlignment(Pos.BOTTOM_RIGHT);
		hBox4.getChildren().addAll(editButton4);
		hBox4.setSpacing(10);
		gridPane4.add(hBox4, 1, 3);

	}

	public void changeMobilePrices(){

		Stage stage = new Stage();
		stage.setTitle("Change Prices");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		grid.add(new Label("Real Price"), 0, 0);
		grid.add(realField, 1, 0);
		grid.add(new Label("RSO Price"), 0, 1);
		grid.add(RSOField, 1, 1);
		grid.add(new Label("Franchise Price"), 0, 2);
		grid.add(franchiseField, 1, 2);

		Button button = new Button("   Change   ");
		button.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (validityOfNewInput("Mobile") ==true && event.getCode() == KeyCode.ENTER) {
					dataBase.changePrice("Mobile", (String) spinner.getValue(), RSOField.getText(),
							franchiseField.getText(), realField.getText());
					realPriceField4.setText(realField.getText());
					RSOPriceField4.setText(RSOField.getText());
					franchisePriceField4.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				} 
			}
			
		});
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (validityOfNewInput("Mobile") ==true) {
					dataBase.changePrice("Mobile", (String) spinner.getValue(), RSOField.getText(),
							franchiseField.getText(), realField.getText());
					realPriceField4.setText(realField.getText());
					RSOPriceField4.setText(RSOField.getText());
					franchisePriceField4.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				} 

			}
		});
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(button);
		hBox.setSpacing(10);
		grid.add(hBox, 1, 3);

		Scene scene = new Scene(grid, 325, 175);
		scene.getStylesheets().add(ChangePrice.class.getResource("AddEntry.css").toExternalForm());
		stage.setTitle("Change Mobile Price");
		stage.setScene(scene);
		stage.show();
	}
	public boolean validityOfNewInput(String value) {

		if (value.equals("Mobile"))
			if (spinner.getValue().equals("Select Mobile")){
				JOptionPane.showMessageDialog(null, "Sorr!\nPlease select a Mobile.");
				return false;
			}
		if (RSOField.getText().matches("[0-9]+") == false && RSOField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null, "Sorr!\nRSO value is not valid");
			return false;
		}
		if (realField.getText().matches("[0-9]+") == false && realField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null, "Sorr!\nReal value is not valid");
			return false;
		}
		if (franchiseField.getText().matches("[0-9]+") == false && franchiseField.getText().matches("[0-9]+.[0-9]+") == false){
			JOptionPane.showMessageDialog(null, "Sorr!\nFranchise value is not valid");
			return false;
		}

		return true;
	}

	private void makeSimPriceGrid() {

		gridPane1 = new GridPane();
		gridPane1.setHgap(10);
		gridPane1.setVgap(10);
		gridPane1.setAlignment(Pos.CENTER);

		title1 = new Label("Sim Price");
		hBoxTitle1 = new HBox(title1);
		//		title1.setStyle("-fx-font-size: 20px");
		hBoxTitle1.setAlignment(Pos.CENTER);
		title1.setId("title");
		gridPane1.add(hBoxTitle1, 0, 0);

		realPriceLabel1 = new Label("Real Price: ");
		gridPane1.add(realPriceLabel1, 0, 1);

		realPriceField1 = new Label(prices[0][2]);
		gridPane1.add(realPriceField1, 1, 1);

		franchisePriceLabel1 = new Label("Franchise Price: ");
		gridPane1.add(franchisePriceLabel1, 0, 2);

		franchisePriceField1 = new Label(prices[0][3]);
		gridPane1.add(franchisePriceField1, 1, 2);

		RSOPriceLabel1 = new Label("RSO Price: ");
		gridPane1.add(RSOPriceLabel1, 0, 3);

		RSOPriceField1 = new Label(prices[0][1]);
		gridPane1.add(RSOPriceField1, 1, 3);

		editButton1 = new Button("  Edit  ");
		editButton1.setId("submitButton");
		editButton1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					changeSimPrices();
			}
		});
		editButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				changeSimPrices();
			}

		});

		hBox1 = new HBox();
		hBox1.setAlignment(Pos.BOTTOM_RIGHT);
		hBox1.getChildren().addAll(editButton1);
		hBox1.setSpacing(10);
		gridPane1.add(hBox1, 2, 4);
	}

	public void changeSimPrices(){
		

		Stage stage = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		grid.add(new Label("Real Price"), 0, 0);
		grid.add(realField, 1, 0);
		grid.add(new Label("RSO Price"), 0, 1);
		grid.add(RSOField, 1, 1);
		grid.add(new Label("Franchise Price"), 0, 2);
		grid.add(franchiseField, 1, 2);

		Button button = new Button("   Change   ");
		button.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (validityOfNewInput("Others") ==true && event.getCode() == KeyCode.ENTER){
					dataBase.changePrice("Others", "Sim", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField1.setText(realField.getText());
					RSOPriceField1.setText(RSOField.getText());
					franchisePriceField1.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}

				
			}
			
		});
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (validityOfNewInput("Others") ==true){
					dataBase.changePrice("Others", "Sim", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField1.setText(realField.getText());
					RSOPriceField1.setText(RSOField.getText());
					franchisePriceField1.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}
			}
		});
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(button);
		hBox.setSpacing(10);
		grid.add(hBox, 1, 3);

		Scene scene = new Scene(grid, 325, 175);
		scene.getStylesheets().add(ChangePrice.class.getResource("AddEntry.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Sim Price");
		stage.show();
	}
	private void makeCardPriceGrid() {

		gridPane2 = new GridPane();
		gridPane2.setHgap(10);
		gridPane2.setVgap(10);
		gridPane2.setAlignment(Pos.CENTER);

		title2 = new Label("Card Price");
		hBoxTitle2 = new HBox(title2);
		//		title2.setStyle("-fx-font-size: 20px");
		hBoxTitle2.setAlignment(Pos.CENTER);
		title2.setId("title");
		gridPane2.add(hBoxTitle2, 0, 0);

		realPriceLabel2 = new Label("Real Price: ");
		gridPane2.add(realPriceLabel2, 0, 1);

		realPriceField2 = new Label(prices[1][2]);
		gridPane2.add(realPriceField2, 1, 1);

		franchisePriceLabel2 = new Label("Franchise Price: ");
		gridPane2.add(franchisePriceLabel2, 0, 2);

		franchisePriceField2 = new Label(prices[1][3]);
		gridPane2.add(franchisePriceField2, 1, 2);

		RSOPriceLabel2 = new Label("RSO Price: ");
		gridPane2.add(RSOPriceLabel2, 0, 3);

		RSOPriceField2 = new Label(prices[1][1]);
		gridPane2.add(RSOPriceField2, 1, 3);

		editButton2 = new Button("  Edit  ");
		editButton2.setId("submitButton");
		
		editButton2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					changeCardPrices();
			}
		});
		editButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				changeCardPrices();
			}

		});

		hBox2 = new HBox();
		hBox2.setAlignment(Pos.BOTTOM_RIGHT);
		hBox2.getChildren().addAll(editButton2);
		hBox2.setSpacing(10);
		gridPane2.add(hBox2, 2, 4);
	}
	
	public void changeCardPrices(){

		Stage stage = new Stage();
		stage.setTitle("Change Card Prices");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		grid.add(new Label("Real Price"), 0, 0);
		grid.add(realField, 1, 0);
		grid.add(new Label("RSO Price"), 0, 1);
		grid.add(RSOField, 1, 1);
		grid.add(new Label("Franchise Price"), 0, 2);
		grid.add(franchiseField, 1, 2);

		Button button = new Button("   Change   ");
		button.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (validityOfNewInput("Others") ==true && event.getCode() == KeyCode.ENTER){
					dataBase.changePrice("Others", "Card", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField2.setText(realField.getText());
					RSOPriceField2.setText(RSOField.getText());
					franchisePriceField2.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}
			}
			
		});
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (validityOfNewInput("Others") ==true ){
					dataBase.changePrice("Others", "Card", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField2.setText(realField.getText());
					RSOPriceField2.setText(RSOField.getText());
					franchisePriceField2.setText(franchiseField.getText());
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}
			}
		});
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(button);
		hBox.setSpacing(10);
		grid.add(hBox, 1, 3);

		Scene scene = new Scene(grid, 325, 175);
		scene.getStylesheets().add(ChangePrice.class.getResource("AddEntry.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change Card Price");
		stage.show();
	}

	private void makeRSOPriceGrid() {

		gridPane3 = new GridPane();
		gridPane3.setHgap(10);
		gridPane3.setVgap(10);
		gridPane3.setAlignment(Pos.CENTER);

		title3 = new Label("HLR Price");
		hBoxTitle3 = new HBox(title3);
		//		title3.setStyle("-fx-font-size: 20px");
		hBoxTitle3.setAlignment(Pos.CENTER);
		title3.setId("title");
		gridPane3.add(hBoxTitle3, 0, 0);

		realPriceLabel3 = new Label("Real Price: ");
		gridPane3.add(realPriceLabel3, 0, 1);

		realPriceField3 = new Label(prices[2][2]);
		gridPane3.add(realPriceField3, 1, 1);

		franchisePriceLabel3 = new Label("Franchise Price: ");
		gridPane3.add(franchisePriceLabel3, 0, 2);

		franchisePriceField3 = new Label(prices[2][3]);
		gridPane3.add(franchisePriceField3, 1, 2);

		RSOPriceLabel3 = new Label("RSO Price: ");
		gridPane3.add(RSOPriceLabel3, 0, 3);

		RSOPriceField3 = new Label(prices[2][1]);
		gridPane3.add(RSOPriceField3, 1, 3);

		editButton3 = new Button("  Edit  ");
		editButton3.setId("submitButton");
		editButton3.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getCode() == KeyCode.ENTER)
					changeHLRPrices();
			}
		});
		editButton3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				changeHLRPrices();
			}

		});

		hBox3 = new HBox();
		hBox3.setAlignment(Pos.BOTTOM_RIGHT);
		hBox3.getChildren().addAll(editButton3);
		hBox3.setSpacing(10);
		gridPane3.add(hBox3, 2, 4);
	}
	
	public void changeHLRPrices(){
		
		Stage stage = new Stage();
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(10);

		grid.add(new Label("Real Price"), 0, 0);
		grid.add(realField, 1, 0);
		grid.add(new Label("RSO Price"), 0, 1);
		grid.add(RSOField, 1, 1);
		grid.add(new Label("Franchise Price"), 0, 2);
		grid.add(franchiseField, 1, 2);

		Button button = new Button("   Change   ");
		button.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (validityOfNewInput("Others") ==true && event.getCode() == KeyCode.ENTER){
					dataBase.changePrice("Others", "HLR", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField3.setText(realField.getText());
					RSOPriceField3.setText(RSOField.getText());
					franchisePriceField3.setText(franchiseField.getText());	
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}
				
			}
			
		});
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (validityOfNewInput("Others") ==true ){
					dataBase.changePrice("Others", "HLR", RSOField.getText(), franchiseField.getText(),
							realField.getText());
					realPriceField3.setText(realField.getText());
					RSOPriceField3.setText(RSOField.getText());
					franchisePriceField3.setText(franchiseField.getText());	
					realField.setText(""); 	RSOField.setText(""); franchiseField.setText("");
					JOptionPane.showMessageDialog(null, "Price has been changed successfuly");
					stage.close();
				}
			}
		});
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(button);
		hBox.setSpacing(10);
		grid.add(hBox, 1, 3);

		Scene scene = new Scene(grid, 325, 175);
		scene.getStylesheets().add(ChangePrice.class.getResource("AddEntry.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("Change HLR Price");
		stage.show();
	}

	public void makeVBox() {

		prices = dataBase.getPrices();
		setMobilesName();
		makeSimPriceGrid();
		makeCardPriceGrid();
		makeRSOPriceGrid();
		makeMobilePriceGrid();

		box.getChildren().addAll(gridPane4, gridPane2, gridPane3, gridPane1);
		box.setSpacing(50);
		scroll = new ScrollPane(box);
	}

	public ScrollPane getScroll() {
		return scroll;
	}

}
