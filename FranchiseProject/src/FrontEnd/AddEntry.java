package FrontEnd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AddEntry extends Application {

	private GridPane gridPane;
	private Label nameLabel;
	@SuppressWarnings("rawtypes")
	private ComboBox nameField;
	private Label HLRReal, HLRRSO, HLRFranchise, existingHLR;
	private TextField HLRFieldReal, HLRFieldFranchise, HLRFieldRSO;
	private Label simReal, simRSO, simFranchise, existingSim;
	private TextField simFieldReal, simFieldFranchise, simFieldRSO;
	@SuppressWarnings("rawtypes")
	private ComboBox[] mobileNames;
	@SuppressWarnings("rawtypes")
	private ComboBox[] mobilePrices;
	private Label noLabel, existingMobiles;
	private Label cardReal, cardRSO, cardFranchise, existingCard;
	private TextField cardFieldReal, cardFieldFranchise, cardFieldRSO;
	private Label easyLoadLabel, existingEasyLoad;
	private TextField easyLoadField;
	private Label easyPaisaLabel, existingEasyPaisa;
	private TextField easyPaisaField;
	private Label easyPaisaReturnLabel;
	private TextField easyPaisaReturnField;
	private Label cashLabel, existingCash;
	private TextField cashField;
	private Label expensesLabel;
	private TextField expensesField;
	private ScrollPane scroll;
	private Button submitButton;
	private Button resetButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private Label label1, label2;

	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private String[][] prices;
	private Object[] simPrices = new String[3];
	private Object[] cardPrices = new String[3];
	private Object[] HLRPrices = new String[3];
	private Object[][] mobilesAllData;
	private ArrayList<String> mobilesData = new ArrayList<>();
	private ArrayList<String> namesOfEmployee = new ArrayList<>();
	private ArrayList<String> allMobileNames = new ArrayList<>();
	private Object[] price = { "RSO Price", "Real Price", "Franchise Price" };
	private String[] stock;

	private VBox mobileNameLabels;
	private VBox mobilePricesLabels;
	private VBox mobileNameFields;
	private VBox mobilePricesFields;
	@SuppressWarnings("rawtypes")
	private Spinner mobileSpinner;
	private String MobileNames = "", allMobilePrices = "";
	double HLR = 0, HLRPrice = 0, sim = 0, simPrice = 0, card = 0, cardPrice = 0, totalMobilePrice = 0,
			cashReturn = 0, easyPaisaReturn = 0, expenses = 0, easyPaisa = 0, easyLoad = 0, HLRProfit = 0, simProfit = 0, cardProfit = 0, easyLoadProfit = 0, easyPaisaProfit = 0, mobilesProfit=0, totalProfit=0;
	int  noOfMobile = 0;
	ObservableList<Object> spinnerValues = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "10");
	
	double oldHLR, oldCard, oldSim, oldMobilesNumbers,  oldEasyLoad, oldEasyPaisa, oldCash;
	double newHLR, newCard, newSim, newMobileNumbers,  newEasyLoad, newEasyPaisa, newCash;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Add Entry");
		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.CENTER);

		title = new Label("Add Entry");
		title.setStyle("-fx-font-size: 20px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 1);
		setPrices();
		getMobileData();
		mobilesAllData = new String[mobilesData.size()][4];
		setMobilesName();

		label1 = new Label("Entry No.");
		gridPane.add(label1, 3, 3);

		label2 = new Label(dataBase.getRowCount());
		gridPane.add(label2, 4, 3);
		
		nameLabel = new Label("Name: ");
		gridPane.add(nameLabel, 3, 4);

		nameField = new ComboBox();
		namesOfEmployee = dataBase.getEmployeesNames();
		nameField.getItems().addAll(namesOfEmployee);
		nameField.setMinWidth(175);
		gridPane.add(nameField, 4, 4);
		
		stock = dataBase.getStock();

		{
			HLRReal = new Label("Real HLR: ");
			gridPane.add(HLRReal, 3, 5);

			existingHLR = new Label("Existing HLR's:          "+stock[0]);
			gridPane.add(existingHLR, 5, 5);
			
			HLRFieldReal = new TextField();
			HLRFieldReal.setId("textField");
			gridPane.add(HLRFieldReal, 4, 5);

			HLRFranchise = new Label("Franchise HLR: ");
			gridPane.add(HLRFranchise, 3, 6);

			HLRFieldFranchise = new TextField();
			gridPane.add(HLRFieldFranchise, 4, 6);

			HLRRSO = new Label("RSO HLR: ");
			gridPane.add(HLRRSO, 3, 7);

			HLRFieldRSO = new TextField();
			gridPane.add(HLRFieldRSO, 4, 7);

		}

		{

			simReal = new Label("Real Sim: ");
			gridPane.add(simReal, 3, 8);
			
			existingSim = new Label("Existing Sims:           "+stock[1]);
			gridPane.add(existingSim, 5, 8);

			simFieldReal = new TextField();
			gridPane.add(simFieldReal, 4, 8);

			simFranchise = new Label("Franchise Sim: ");
			gridPane.add(simFranchise, 3, 9);

			simFieldFranchise = new TextField();
			gridPane.add(simFieldFranchise, 4, 9);

			simRSO = new Label("RSO Sim: ");
			gridPane.add(simRSO, 3, 10);

			simFieldRSO = new TextField();
			gridPane.add(simFieldRSO, 4, 10);

		}

		{

			cardReal = new Label("Real Card: ");
			gridPane.add(cardReal, 3, 11);
			
			existingCard = new Label("Existing Cards:          "+stock[2]);
			gridPane.add(existingCard, 5, 11);

			cardFieldReal = new TextField();
			gridPane.add(cardFieldReal, 4, 11);

			cardFranchise = new Label("Franchise Card: ");
			gridPane.add(cardFranchise, 3, 12);

			cardFieldFranchise = new TextField();
			gridPane.add(cardFieldFranchise, 4, 12);

			cardRSO = new Label("RSO Card: ");
			gridPane.add(cardRSO, 3, 13);

			cardFieldRSO = new TextField();
			gridPane.add(cardFieldRSO, 4, 13);

		}

		noLabel = new Label("Number Of Mobile.: ");
		gridPane.add(noLabel, 3, 14);

		mobileSpinner = new Spinner(spinnerValues);
		gridPane.add(mobileSpinner, 4, 14);
		
		existingMobiles = new Label("Existing Mobiles:       "+stock[3]);
		gridPane.add(existingMobiles, 5, 14);

		mobileNameLabels = new VBox(23);
		mobilePricesLabels = new VBox(23);
		mobileNameFields = new VBox(10);
		mobilePricesFields = new VBox(10);
		

		mobileSpinner.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode()==KeyCode.UP)
					handleMobileEvent("Up");
				else if(event.getCode()==KeyCode.DOWN)
					handleMobileEvent("Down");
			}


		});

		mobileSpinner.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				handleMobileEvent("");
//				mobileNameLabels.getChildren().clear();
//				mobileNameFields.getChildren().clear();
//				mobilePricesLabels.getChildren().clear();
//				mobilePricesFields.getChildren().clear();
//
//				int size = Integer.parseInt((String) mobileSpinner.getValue());
//
//				if(size>0){
//					mobilePrices = new ComboBox[size];
//					mobileNames = new ComboBox[size];
//				}
//				for (int i = 0; i < size; i++) {
//
//					mobilePrices[i] = new ComboBox();
//					mobileNames[i] = new ComboBox();
//
//					mobileNameFields.getChildren().add(mobileNames[i]);
//
//					mobilePricesFields.getChildren().add(mobilePrices[i]);
//					mobilePrices[i].getItems().addAll(price);
//					mobileNames[i].setMinWidth(175);
//					mobilePrices[i].setMinWidth(175);
//					mobileNameLabels.getChildren().add(new Label("Mobile No." + (i + 1)));
//					mobileNames[i].getItems().addAll(allMobileNames);
//					mobilePricesLabels.getChildren().add(new Label("Mobile Price"));
//				}

			}
		});

		gridPane.add(mobileNameLabels, 3, 15);
		gridPane.add(mobileNameFields, 4, 15);
		gridPane.add(mobilePricesLabels, 5, 15);
		gridPane.add(mobilePricesFields, 6, 15);

		easyLoadLabel = new Label("Easy Load: ");
		gridPane.add(easyLoadLabel, 3, 16);

		easyLoadField = new TextField();
		gridPane.add(easyLoadField, 4, 16);
		
		existingEasyLoad = new Label("Existing EasyLoad:     "+stock[4]);
		gridPane.add(existingEasyLoad, 5, 16);

		easyPaisaLabel = new Label("Easy Paisa: ");
		gridPane.add(easyPaisaLabel, 3, 17);

		easyPaisaField = new TextField();
		gridPane.add(easyPaisaField, 4, 17);
		
		existingEasyPaisa = new Label("Existing EasyPaisa:     "+stock[5]);
		gridPane.add(existingEasyPaisa, 5, 17);

		easyPaisaReturnLabel = new Label("Easy Paisa Return: ");
		gridPane.add(easyPaisaReturnLabel, 3, 18);

		easyPaisaReturnField = new TextField();
		gridPane.add(easyPaisaReturnField, 4, 18);

		cashLabel = new Label("Cash: ");
		gridPane.add(cashLabel, 3, 19);

		cashField = new TextField();
		gridPane.add(cashField, 4, 19);
		
		existingCash = new Label("Existing Cash:              "+stock[6]);
		gridPane.add(existingCash, 5, 19);

		expensesLabel = new Label("Expenses: ");
		gridPane.add(expensesLabel, 3, 20);

		expensesField = new TextField();
		gridPane.add(expensesField, 4, 20);

		submitButton = new Button("Submit");
		submitButton.setId("button");
		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					insertEntry();
			}
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				insertEntry();
			}

		});

		resetButton = new Button("Reset");
		resetButton.setId("button");
		resetButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER)
					clearFields();
			}
		});
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				clearFields();
			}

		});

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(resetButton, submitButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 6, 21);
		gridPane.setAlignment(Pos.TOP_CENTER);
		scroll = new ScrollPane(gridPane);
		scroll.setId("scroll");
	}
	
	public void insertEntry(){
		

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
		Date date = new Date();
		getMobilePriceAndNames();

		getStock();
		if(validation()==true){
			
			String calculateResult = calculateTotal();
			totalProfit = simProfit + cardProfit + HLRProfit + mobilesProfit + easyLoadProfit + easyPaisaProfit;
			dataBase.insertEntry(dataBase.getRowCount(), dateFormat
					.format(date),
					(String)nameField.getSelectionModel().getSelectedItem(),String.valueOf(HLR),
					String.valueOf(HLRPrice), String.valueOf(HLRProfit),String.valueOf(sim),
					String.valueOf(simPrice), String.valueOf(simProfit),
					String.valueOf(card),String.valueOf(cardPrice), String.valueOf(cardProfit),
					String.valueOf(easyLoad), String.valueOf(easyLoadProfit),
					String.valueOf(easyPaisa), String.valueOf(easyPaisaProfit),
					String.valueOf(easyPaisaReturn), String.valueOf(cashReturn),
					MobileNames, String.valueOf(noOfMobile), allMobilePrices, String.valueOf(mobilesProfit)
					, String.valueOf(expenses),
					calculateResult, String.valueOf(totalProfit));
			JOptionPane.showMessageDialog(null, "Entry has been submitted successfuly");
			label2.setText(String.valueOf(Integer.parseInt(label2.getText())+1));
			clearFields();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleMobileEvent(String key){

		mobileNameLabels.getChildren().clear();
		mobileNameFields.getChildren().clear();
		mobilePricesLabels.getChildren().clear();
		mobilePricesFields.getChildren().clear();

		int size = 0;
		String spinnervalue = (String) mobileSpinner.getValue();
		int index = spinnerValues.indexOf(spinnervalue);
		
		if(key.equals("Up")){
			if(index==10)
				size = Integer.parseInt((String) spinnerValues.get(index));
			else
				size = Integer.parseInt((String) spinnerValues.get(index+1));
		}

		else if(key.equals("Down")){
			if(index==0)
				size = Integer.parseInt((String) spinnerValues.get(index));
			else
				size = Integer.parseInt((String) spinnerValues.get(index-1));
		}
		else
			size = Integer.parseInt((String) mobileSpinner.getValue());
		

		if(size>0){
			mobilePrices = new ComboBox[size];
			mobileNames = new ComboBox[size];
		}
		for (int i = 0; i < size; i++) {

			mobilePrices[i] = new ComboBox();
			mobileNames[i] = new ComboBox();

			mobileNameFields.getChildren().add(mobileNames[i]);

			mobilePricesFields.getChildren().add(mobilePrices[i]);
			mobilePrices[i].getItems().addAll(price);
			mobileNames[i].setMinWidth(175);
			mobilePrices[i].setMinWidth(175);
			mobileNameLabels.getChildren().add(new Label("Mobile No." + (i + 1)));
			mobileNames[i].getItems().addAll(allMobileNames);
			mobilePricesLabels.getChildren().add(new Label("Mobile Price"));
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public boolean validation() {
		int size; String status = null;
		if (mobileNames == null){
			size = 0;
		}
		else
			size = mobileNames.length;
		
		noOfMobile = size;

		if (size > 0) {

			for (int i = 0; i < size; i++) {

				if (mobileNames[i].getSelectionModel().isEmpty()){
					mobileNames[i].setStyle("-fx-border-color: red");
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select a Mobile.");
					return false;
				}
				if(mobilePrices[i].getSelectionModel().isEmpty()){
					
					mobilePrices[i].setStyle("-fx-border-color: red");
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select a Price For Mobile.");
					return false;
				}
			}
		}

		if (nameField.getSelectionModel().isEmpty()){
			nameField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Please select a name.");
			return false;
		}
		else{
			String employeeName = (String) nameField.getSelectionModel().getSelectedItem();
			ArrayList<String> employeesRecord = dataBase.getEmployeeRecord();
			for(int i=0; i<employeesRecord.size(); i++){
				String[] data = employeesRecord.get(i).split(",");
				if(data[0].equals(employeeName)){
					status = data[3];
					break;
				}
			}
		}
		if (HLRFieldReal.getText().matches("[0-9]+") == false){
			HLRFieldReal.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Real HLR Field is empty");
//			if(status.equals(""))
			return false;
		}
		if(HLRFieldFranchise.getText().matches("[0-9]+") == false){
			
			HLRFieldFranchise.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " Franchise HLR Field is empty");
			return false;
		}
		if(HLRFieldRSO.getText().matches("[0-9]+") == false){
			HLRFieldRSO.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " RSO HLR Field is empty");
			return false;
		}
		
		if (simFieldReal.getText().matches("[0-9]+") == false){
			simFieldReal.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " Real Sim Field is empty");
			return false;
		}
		if(simFieldFranchise.getText().matches("[0-9]+") == false){
			
			simFieldFranchise.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " Franchise Sim Field is empty");
			return false;
			
		}
		if(simFieldRSO.getText().matches("[0-9]+") == false){
			
			simFieldRSO.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " RSO Sim Field is empty");
			return false;
		}
		if (cardFieldReal.getText().matches("[0-9]+") == false){
			cardFieldReal.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " Real Card Field is empty");
			return false;
		}
		if(cardFieldFranchise.getText().matches("[0-9]+") == false){

			cardFieldFranchise.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " Franchise Card Field is empty");
			return false;
		}
		if(cardFieldRSO.getText().matches("[0-9]+") == false){

			cardFieldRSO.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, " RSO Card Field is empty");
			return false;
		}
		if (easyLoadField.getText().matches("[0-9]+") == false){
			easyLoadField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Easy Load Field Field is empty");
			return false;
		}
		if (easyPaisaField.getText().matches("[0-9]+") == false){
			easyPaisaField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Easy Paisa Field is empty");
			return false;
		}
		if (easyPaisaReturnField.getText().matches("[0-9]+") == false){
			easyPaisaReturnField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Easy Paisa Return Field is empty");
			return false;
		}
		if (cashField.getText().matches("[0-9]+") == false){
			cashField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Cash Field is empty");
			return false;
		}
		if (expensesField.getText().matches("[0-9]+") == false){
			expensesField.setStyle("-fx-border-color: red");
			JOptionPane.showMessageDialog(null, "Expenses Field is empty");
			return false;
		}
		{
			
			newHLR = Double.parseDouble(HLRFieldReal.getText()) + Double.parseDouble(HLRFieldFranchise.getText()) 
						+Double.parseDouble(HLRFieldRSO.getText());
			newSim = Double.parseDouble(simFieldReal.getText()) + Double.parseDouble(simFieldFranchise.getText())+
					Double.parseDouble(simFieldRSO.getText());
			newCard = Double.parseDouble(cardFieldReal.getText()) + Double.parseDouble(cardFieldFranchise.getText())+
					Double.parseDouble(cardFieldRSO.getText());
			newEasyPaisa = Double.parseDouble(easyPaisaField.getText());
			newMobileNumbers = Double.parseDouble((String) mobileSpinner.getValue());
			newCash = Double.parseDouble(cashField.getText());
			newEasyLoad = Double.parseDouble(easyLoadField.getText());
			
			if(newHLR > oldHLR || newSim > oldSim || newCard > oldCard || newEasyPaisa > oldEasyPaisa ||
					newMobileNumbers > oldMobilesNumbers || newCash > oldCash || newEasyLoad > oldEasyLoad)
				return false;
			
		}

		return true;
	}

	public void getStock(){
		
		stock = dataBase.getStock();
		oldHLR = Double.parseDouble(stock[0]);
		oldCard = Double.parseDouble(stock[2]);
		oldSim = Double.parseDouble(stock[1]);
		oldMobilesNumbers = Double.parseDouble(stock[3]);
		oldEasyLoad = Double.parseDouble(stock[4]);
		oldEasyPaisa = Double.parseDouble(stock[5]);
		oldCash = Double.parseDouble(stock[6]);
	}
	public void getMobilePriceAndNames() {

		int size;
		MobileNames = "";
		allMobilePrices = "";

		if (mobileNames == null)
			size = 0;
		else
			size = mobileNames.length;

		if (size == 0) {
			MobileNames = "No Mobiles";
			allMobilePrices = "0";
			totalMobilePrice = 0;
			return;
		}

		else if (size > 0) {

			for (int i = 0; i < size; i++) {

				if (mobileNames[i].getSelectionModel().isEmpty() || mobilePrices[i].getSelectionModel().isEmpty())
					return;
			}
		}

		for (int i = 0; i < size; i++) {
			if (MobileNames.equals(""))
				MobileNames = (String) mobileNames[i].getSelectionModel().getSelectedItem();
			else
				MobileNames += "-" + (String) mobileNames[i].getSelectionModel().getSelectedItem();

			String name = (String) mobileNames[i].getSelectionModel().getSelectedItem();
			String priceType = (String) mobilePrices[i].getSelectionModel().getSelectedItem();

			for (int row = 0; row < mobilesAllData.length; row++) {

				if (name.equals(mobilesAllData[row][0])) {

					if (priceType.equals("RSO Price")) {

						if (allMobilePrices.equals("")) {
							allMobilePrices = (String) mobilesAllData[row][2];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][2]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][2]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][2];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][2]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][2]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}

					if (priceType.equals("Real Price")) {

						if (allMobilePrices.equals("")) {
							allMobilePrices = (String) mobilesAllData[row][1];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][1]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][1]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][1];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][1]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][1]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}

					if (priceType.equals("Franchise Price")) {

						if (allMobilePrices.equals("")) {
							allMobilePrices = (String) mobilesAllData[row][3];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][3]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][3]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][3];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][3]);
							mobilesProfit += Double.parseDouble((String) mobilesAllData[row][3]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}
					break;
				}
			}

		}

	}

	@SuppressWarnings({ "unchecked" })
	public void clearFields() {
		
		HLRFieldReal.setStyle("-fx-border-color: null");
		nameField.setStyle("-fx-border-color: null");
//		HLRFieldReal.setStyle("-fx-background-color: white");
		HLRFieldRSO.setStyle("-fx-border-color: null");
		HLRFieldFranchise.setStyle("-fx-border-color: null");
		
		simFieldFranchise.setStyle("-fx-border-color: null");
		simFieldReal.setStyle("-fx-border-color: null");
		simFieldRSO.setStyle("-fx-border-color: null");
		
		cardFieldFranchise.setStyle("-fx-border-color: null");
		cardFieldReal.setStyle("-fx-border-color: null");
		cardFieldRSO.setStyle("-fx-border-color: null");
		
		if(Integer.parseInt((String) mobileSpinner.getValue())>0){
		for (int i = 0; i < mobileNames.length; i++) {

				mobileNames[i].setStyle("-fx-border-color: null");
				mobilePrices[i].setStyle("-fx-border-color: null");
		}
		}
		
		easyLoadField.setStyle("-fx-border-color: null");
		easyPaisaField.setStyle("-fx-border-color: null");
		easyPaisaReturnField.setStyle("-fx-border-color: null");
		cashField.setStyle("-fx-border-color: null");
		expensesField.setStyle("-fx-border-color: null");
		
		nameField.setValue("");
		HLRFieldReal.setText("");
		HLRFieldFranchise.setText("");
		HLRFieldRSO.setText("");
		simFieldReal.setText("");
		simFieldFranchise.setText("");
		simFieldRSO.setText("");
		
		mobileNameLabels.getChildren().clear();
		mobileNameFields.getChildren().clear();
		mobilePricesLabels.getChildren().clear();
		mobilePricesFields.getChildren().clear();
		cardFieldReal.setText("");
		cardFieldFranchise.setText("");
		cardFieldRSO.setText("");
		easyLoadField.setText("");
		easyPaisaField.setText("");
		easyPaisaReturnField.setText("");
		cashField.setText("");
		expensesField.setText("");
		
		HLRPrice = 0;
		HLRProfit=0;
		simPrice = 0;
		simProfit = 0;
		cardPrice = 0;
		cardProfit = 0;
		totalMobilePrice = 0;
		
		HLR = 0;  sim = 0;  card = 0; cashReturn = 0; easyPaisaReturn = 0; expenses = 0; 
		easyPaisa = 0; easyLoad = 0; easyLoadProfit = 0; easyPaisaProfit = 0; totalProfit=0;
		noOfMobile = 0;
		
		mobilePrices = null;
		mobileNames = null;
		mobileSpinner.getEditor().setText("0");
		mobilesProfit = 0;

		existingHLR.setText("Existing HLR's:          "+String.valueOf(oldHLR - newHLR));
		existingSim.setText("Existing Sims:           "+String.valueOf(oldSim - newSim));
		existingCard.setText("Existing Cards:          "+String.valueOf(oldCard - newCard));
		existingMobiles.setText("Existing Mobiles:       "+String.valueOf(oldMobilesNumbers - newMobileNumbers));
		existingEasyLoad.setText("Existing EasyLoad:     "+String.valueOf(oldEasyLoad - newEasyLoad));
		existingEasyPaisa.setText("Existing EasyPaisa:     "+String.valueOf(oldEasyPaisa - newEasyPaisa));
		existingCash.setText("Existing Cash:              "+String.valueOf(oldCash - newCash));
		
		dataBase.addStock(oldHLR - newHLR, oldSim - newSim, oldCard - newCard, oldMobilesNumbers - newMobileNumbers, oldEasyLoad - newEasyLoad, oldEasyPaisa - newEasyPaisa, oldCash - newCash, oldHLR);
	}

	public void setPrices() {

		prices = dataBase.getPrices();
		for (int i = 0; i < 3; i++) {

			if (i == 0) {
				simPrices[0] = prices[i][1];
				simPrices[1] = prices[i][2];
				simPrices[2] = prices[i][3];
			} else if (i == 1) {

				cardPrices[0] = prices[i][1];
				cardPrices[1] = prices[i][2];
				cardPrices[2] = prices[i][3];
			} else if (i == 2) {

				HLRPrices[0] = prices[i][1];
				HLRPrices[1] = prices[i][2];
				HLRPrices[2] = prices[i][3];
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void getMobileData() {

		mobilesData = dataBase.getMobileData();
	}

	public void setMobilesName() {
		for (int row = 0; row < mobilesData.size(); row++) {

			String[] data = mobilesData.get(row).split(",");
			for (int col = 0; col < data.length; col++) {

				mobilesAllData[row][col] = data[col];

				if (col == 0) {
					allMobileNames.add(data[0]);
				}
			}
		}
	}

	public ScrollPane getScrollPane() {
		return scroll;
	}

	public String calculateTotal() {
		
		//calculation of profit........

//		noOfMobile = Double.parseDouble((String) mobileSpinner.getValue());
		cashReturn = Double.parseDouble(cashField.getText());
		easyLoad = Double.parseDouble(easyLoadField.getText());
		easyLoadProfit = 3/100.0 * easyLoad; 

		{
			double realHLR = Double.parseDouble(HLRFieldReal.getText());
			HLRPrice += Double.parseDouble((String) HLRPrices[1]) * Double.parseDouble(HLRFieldReal.getText());
			HLRProfit += (Double.parseDouble((String) HLRPrices[1]) - Double.parseDouble((String) HLRPrices[1])) *realHLR;
			
			double franchiseHLR = Double.parseDouble(HLRFieldFranchise.getText());
			HLRPrice += Double.parseDouble((String) HLRPrices[2]) * Double.parseDouble(HLRFieldFranchise.getText());
			HLRProfit+=(Double.parseDouble((String) HLRPrices[2]) - Double.parseDouble((String) HLRPrices[1])) *franchiseHLR;
			
			double RSOHLR = Double.parseDouble(HLRFieldRSO.getText());
			HLRPrice += Double.parseDouble((String) HLRPrices[0]) * Double.parseDouble(HLRFieldRSO.getText());
			HLRProfit+=(Double.parseDouble((String) HLRPrices[0]) - Double.parseDouble((String) HLRPrices[1])) *RSOHLR;
			
			HLR = realHLR + franchiseHLR + RSOHLR;
		}

		{
			double realSim = Double.parseDouble(simFieldReal.getText());
			simPrice = Double.parseDouble((String) simPrices[1]) * Double.parseDouble(simFieldReal.getText());
			simProfit = (Double.parseDouble((String) simPrices[1]) - Double.parseDouble((String) simPrices[1])) *realSim;
			
			double franchiseSim = Double.parseDouble(simFieldFranchise.getText());
			simPrice += Double.parseDouble((String) simPrices[2]) * Double.parseDouble(simFieldFranchise.getText());
			simProfit += (Double.parseDouble((String) simPrices[2]) - Double.parseDouble((String) simPrices[1])) *franchiseSim;
			
			double RSOSim = Double.parseDouble(simFieldRSO.getText());
			simPrice += Double.parseDouble((String) simPrices[0]) * Double.parseDouble(simFieldRSO.getText());
			simProfit += (Double.parseDouble((String) simPrices[0]) - Double.parseDouble((String) simPrices[1])) *RSOSim;
			
			sim = realSim + RSOSim + franchiseSim;
		}

		{

			double realCard = Double.parseDouble(cardFieldReal.getText());
			cardPrice = Double.parseDouble((String) cardPrices[1]) * Double.parseDouble(cardFieldReal.getText());
			cardProfit = (Double.parseDouble((String) cardPrices[1]) - Double.parseDouble((String) cardPrices[1])) *realCard;
			
			double franchiseCard = Double.parseDouble(cardFieldFranchise.getText());
			cardPrice += Double.parseDouble((String) cardPrices[2]) * Double.parseDouble(cardFieldFranchise.getText());
			cardProfit += (Double.parseDouble((String) cardPrices[2]) - Double.parseDouble((String) cardPrices[1])) *franchiseCard;
			
			double RSOCard = Double.parseDouble(cardFieldRSO.getText());
			cardPrice += Double.parseDouble((String) cardPrices[0]) * Double.parseDouble(cardFieldRSO.getText());
			cardProfit+=(Double.parseDouble((String) cardPrices[0]) - Double.parseDouble((String) cardPrices[1])) *RSOCard;
			
			card = realCard + RSOCard + franchiseCard;
		}

		easyPaisa = Double.parseDouble(easyPaisaField.getText());
		easyPaisaProfit = 30;
		easyPaisaReturn = Double.parseDouble(easyPaisaReturnField.getText());
		expenses = Double.parseDouble(expensesField.getText());

		double num1 = HLRPrice + simPrice + cardPrice + totalMobilePrice + easyPaisa + easyLoad;
		double num2 = (cashReturn) + (easyPaisaReturn) + expenses;
		double total = num1 - num2;

		return String.valueOf(total);
	}

}
