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

public class Stock{

	private GridPane gridPane;
	private Label HLRLabel, existingHLR;
	private TextField HLRField;
	private Label simLabel, existingSim;
	private TextField simField;
	private Label noOfMobilesLabel, existingMobiles;
	private TextField noOfMobilesField;
	private Label cardLabel, existingCards;
	private TextField cardField;
	private Label easyLoadLabel, existingEasyLoad;
	private TextField easyLoadField;
	private Label easyPaisaLabel, existingEasyPaisa;
	private TextField easyPaisaField;
	private Label cashLabel, existingCash;
	private TextField cashField;
	private Button submitButton;
	private Button resetButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private ScrollPane scroll;
	double HLR, card, sim, mobilesNumbers,  easyLoad, easyPaisa, cash;
	double newHLR, newCard, newSim, newMobilesNumbers,  newEasyLoad, newEasyPaisa, newCash;
	private PlayWithDataBase dataBase = new PlayWithDataBase();

	public void start(){

		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.CENTER);

		title = new Label("Stock");
		title.setStyle("-fx-font-size: 20px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 0);
		String[] existingStock = dataBase.getStock();

		{
			HLRLabel = new Label("HLR: ");
			gridPane.add(HLRLabel, 3, 1);

			HLRField = new TextField();
			gridPane.add(HLRField, 4, 1);

			existingHLR = new Label("Existing HLR's:          "+existingStock[0]);
			gridPane.add(existingHLR, 5, 1);
		}

		{

			simLabel = new Label("Sim: ");
			gridPane.add(simLabel, 3, 2);

			simField = new TextField();
			gridPane.add(simField, 4, 2);
			
			existingSim = new Label("Existing Sims:           "+existingStock[1]);
			gridPane.add(existingSim, 5, 2);

		}

		{
			cardLabel = new Label("Card: ");
			gridPane.add(cardLabel, 3, 3);

			cardField = new TextField();
			gridPane.add(cardField, 4, 3);
			
			existingCards = new Label("Existing Cards:          "+existingStock[2]);
			gridPane.add(existingCards, 5, 3);

		}

		noOfMobilesLabel = new Label("Number Of Mobiles.: ");
		gridPane.add(noOfMobilesLabel, 3, 4);

		noOfMobilesField = new TextField();
		gridPane.add(noOfMobilesField, 4, 4);
		
		existingMobiles = new Label("Existing Mobiles:       "+existingStock[3]);
		gridPane.add(existingMobiles, 5, 4);

		easyLoadLabel = new Label("Easy Load: ");
		gridPane.add(easyLoadLabel, 3, 5);

		easyLoadField = new TextField();
		gridPane.add(easyLoadField, 4, 5);
		
		existingEasyLoad = new Label("Existing EasyLoad:     "+existingStock[4]);
		gridPane.add(existingEasyLoad, 5, 5);

		easyPaisaLabel = new Label("Easy Paisa: ");
		gridPane.add(easyPaisaLabel, 3, 6);

		easyPaisaField = new TextField();
		gridPane.add(easyPaisaField, 4, 6);
		
		existingEasyPaisa = new Label("Existing EasyPaisa:     "+existingStock[5]);
		gridPane.add(existingEasyPaisa, 5, 6);

		cashLabel = new Label("Cash: ");
		gridPane.add(cashLabel, 3, 7);

		cashField = new TextField();
		gridPane.add(cashField, 4, 7);
		
		existingCash = new Label("Existing Cash:              "+existingStock[6]);
		gridPane.add(existingCash, 5, 7);


		submitButton = new Button("Submit");
		submitButton.setId("submitButton");
		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER)
					addStock();
				JOptionPane.showMessageDialog(null, "New Stock Has Been Added");
			}

		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				addStock();
				JOptionPane.showConfirmDialog(null, "New Stock Has Been Added");
			}

		});

		resetButton = new Button("Reset");
		resetButton.setId("resetButton");
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				clearFields();
			}

		});
		resetButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.ENTER)
					clearFields();
			}

		});

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(resetButton, submitButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 6, 11);
		gridPane.setAlignment(Pos.TOP_CENTER);
		scroll = new ScrollPane(gridPane);
		scroll.setId("scroll");
	}
	
	public void addStock(){
		
		getStock();
		if(validation()){
			getValues();
			dataBase.addStock(newHLR, newSim, newCard, newMobilesNumbers, newEasyLoad, newEasyPaisa, newCash, HLR);
			
			existingSim.setText("Existing Sims:           "+String.valueOf(newSim));
			existingHLR.setText("Existing HLR's:          "+String.valueOf(newHLR));
			existingCards.setText("Existing Cards:          "+String.valueOf(newCard));
			existingMobiles.setText("Existing Mobiles:       "+String.valueOf(newMobilesNumbers));
			existingEasyLoad.setText("Existing EasyLoad:     "+String.valueOf(newEasyLoad));
			existingEasyPaisa.setText("Existing EasyPaisa:     "+String.valueOf(newEasyPaisa));
			existingCash.setText("Existing Cash:              "+String.valueOf(newCash));
			clearFields();
		}
		else
			JOptionPane.showMessageDialog(null, "Sorry!\nThere something wrong with your entry.");
	}
	
	public void clearFields(){
		
		HLRField.setText("");
		noOfMobilesField.setText("");
		cardField.setText("");
		simField.setText("");
		easyPaisaField.setText("");
		easyLoadField.setText("");
		cashField.setText("");
	}

	public void getStock(){

		String[] existingStock = dataBase.getStock();
		HLR = Double.parseDouble(existingStock[0]);
		card = Double.parseDouble(existingStock[2]);
		sim = Double.parseDouble(existingStock[1]);
		mobilesNumbers = Double.parseDouble(existingStock[3]);
		easyLoad = Double.parseDouble(existingStock[4]);
		easyPaisa = Double.parseDouble(existingStock[5]);
		cash = Double.parseDouble(existingStock[6]);
	}

	public void getValues(){
		
		newHLR = Double.parseDouble(HLRField.getText()) + HLR;
		newCard = Double.parseDouble(cardField.getText()) + card;
		newSim = Double.parseDouble(simField.getText()) + sim;
		newMobilesNumbers = Double.parseDouble(noOfMobilesField.getText()) + mobilesNumbers;
		newEasyLoad = Double.parseDouble(easyLoadField.getText()) + easyLoad;
		newEasyPaisa = Double.parseDouble(easyPaisaField.getText()) + easyPaisa;
		newCash = Double.parseDouble(cashField.getText()) + cash;
	}
	public boolean validation(){

		if(!HLRField.getText().matches("[0-9]+") || !simField.getText().matches("[0-9]+") || !cardField.getText().matches("[0-9]+") || 
				!noOfMobilesField.getText().matches("[0-9]+") || !easyLoadField.getText().matches("[0-9]+") ||
				!easyPaisaField.getText().matches("[0-9]+") || !cashField.getText().matches("[0-9]+"))
			return false;

		else
			return true;
	}

	public ScrollPane getScrollPane() {
		return scroll;
	}


}
