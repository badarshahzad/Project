package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
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

public class ModifyEntry{

	private GridPane gridPane;
	private Label entryNumberLabel;
	private TextField entryNumberField;
	private Label colNameLabel;
	@SuppressWarnings("rawtypes")
	private Spinner inputColName;
	private TextField newValueField;
	ObservableList<Object> spinnerValues = FXCollections.observableArrayList("Select Column", "Name", "HLR", "Sim",
			"Card", "Easy Load", "Easy Paisa", "Easy Paisa Return", "Cash", "Mobiles", "Expenses");
	private Button submitButton;
	private HBox hBox;
	private HBox hBoxTitle;
	private Label title;
	private double totalModified;//Here
	private PlayWithDataBase dataBase = new PlayWithDataBase();

	// Setting VBOXES
	private VBox vboxLabels;
	private VBox vboxFields;
	private VBox priceLabels;
	private VBox priceFields;
	private VBox mobileLabels;
	private VBox mobileFields;
	private Label HLRFranchise, HLRRSO, HLRReal, simFranchise, simRSO, simReal, cardFranchise, cardRSO, cardReal;
	private TextField HLRField1, HLRField2, HLRField3, simField1, simField2, simField3, cardField1, cardField2,
	cardField3;
	ObservableList<Object> mobileSpinnerValues = FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "10");

	private ArrayList<String> mobilesData = new ArrayList<>();
	private ArrayList<String> allMobileNames = new ArrayList<>();

	private Object[] price = { "RSO Price", "Real Price", "Franchise Price" };

	// Setting Fields
	@SuppressWarnings("rawtypes")
	private ComboBox names;
	private Label nameLabel;
	private TextField easyLoad;
	private Label easyLoadLabel;
	private TextField easyPaisa;
	private Label easyPaisaLabel;
	private TextField easyPaisaReturn;
	private Label easyPaisaReturnLabel;
	private TextField cashReturn;
	private Label cashReturnLabel;
	private TextField expenses;
	private Label expensesLabel;
	private String value = "Select Column";
	@SuppressWarnings("rawtypes")
	ComboBox[] mobilePrices;
	@SuppressWarnings("rawtypes")
	ComboBox[] mobileNames;
	private Object[] simPrices = new String[3];
	private Object[] cardPrices = new String[3];
	private Object[] HLRPrices = new String[3];
	private String MobileNames = "";
	private String allMobilePrices = "";
	private double totalMobilePrice; //Here
	private Object[][] mobilesAllData;
	@SuppressWarnings("rawtypes")
	private Spinner mobileSpinner;
	private double mobilesNewProfit = 0; //Here
	private ScrollPane scrollPane;
	private String[] stock;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void start() {

		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.TOP_CENTER);
		getMobileData(); 
		setPrices();
		mobilesAllData = new String[mobilesData.size()][4];
		submitButton = new Button("  Change  ");

		submitButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.ENTER){
					if(validtyOfEntryNumberAndNewValue()){
						changeEntry();
						JOptionPane.showMessageDialog(null, "Entry has been modified");
						clearFields();
					}
					else
						JOptionPane.showMessageDialog(null, "Sorry!\nGiven input is not valid");
				}
			}
		});
		submitButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {

				if(validtyOfEntryNumberAndNewValue()){
					changeEntry();
					JOptionPane.showMessageDialog(null, "Entry has been modified");
					clearFields();
				}
				else
					JOptionPane.showMessageDialog(null, "Sorry!\nGiven input is not valid");
			}

		});

		submitButton.setId("submitButton");

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(submitButton);
		hBox.setSpacing(10);
//		gridPane.getChildren().remove(hBox);
//		gridPane.add(hBox, 4, 8);

		title = new Label("Modify Entry");
		title.setStyle("-fx-font-size: 20px");
		hBoxTitle = new HBox(title);
		hBoxTitle.setAlignment(Pos.CENTER);
		title.setId("title");
		gridPane.add(hBoxTitle, 4, 2);

		entryNumberLabel = new Label("Entry Number: ");
		gridPane.add(entryNumberLabel, 3, 4);

		entryNumberField = new TextField();
		entryNumberField.setMaxWidth(175);
		gridPane.add(entryNumberField, 4, 4);

		colNameLabel = new Label("Column Name: ");
		gridPane.add(colNameLabel, 3, 5);

		HLRReal = new Label("Real HLR: ");
		HLRFranchise = new Label("Franchise HLR: ");
		HLRRSO = new Label("RSO HLR: ");

		cardFranchise = new Label("Franchise Card: ");
		cardRSO = new Label("RSO Card: ");
		cardReal = new Label("Real Card: ");

		simReal = new Label("Real Sim: ");
		simFranchise = new Label("Franchise Sim: ");
		simRSO = new Label("RSO Sim: ");

		vboxLabels = new VBox(23);
		vboxFields = new VBox(10);
		priceLabels = new VBox(23);
		mobileLabels = new VBox(23);
		priceFields = new VBox(10);
		mobileFields = new VBox(10);

		inputColName = new Spinner(spinnerValues);
		stock = dataBase.getStock();


		inputColName.setOnKeyPressed(new EventHandler<KeyEvent>() {



			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(event.getCode()==KeyCode.UP)
					handleEvent("Up");
				else if(event.getCode()==KeyCode.DOWN)
					handleEvent("Down");

			}
		});

		inputColName.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				handleEvent("");

			}

			//
			//				vboxFields.getChildren().clear();
			//				vboxLabels.getChildren().clear();
			//
			//				priceLabels.getChildren().clear();
			//				mobileLabels.getChildren().clear();
			//				priceFields.getChildren().clear();
			//				mobileFields.getChildren().clear();
			//
			//				value = (String) inputColName.getValue();
			//
			//				if (value.equals("HLR")) {
			//
			//					HLRField1 = new TextField();
			//					HLRField1.setMaxWidth(175);
			//					HLRField2 = new TextField();
			//					HLRField2.setMaxWidth(175);
			//					HLRField3 = new TextField();
			//					HLRField3.setMaxWidth(175);
			//					HBox hbox = new HBox(HLRField1, new Label("   Existing HLR's:		"+stock[0]));
			//
			//					vboxLabels.getChildren().addAll(HLRReal, HLRRSO, HLRFranchise);
			//					vboxFields.getChildren().addAll(hbox, HLRField2, HLRField3);
			//				}
			//
			//				else if (value.equals("Sim")) {
			//
			//					simField1 = new TextField();
			//					simField1.setMaxWidth(175);
			//					simField2 = new TextField();
			//					simField2.setMaxWidth(175);
			//					simField3 = new TextField();
			//					simField3.setMaxWidth(175);
			//					HBox hbox = new HBox(simField1, new Label("   Existing sims:		"+stock[1]));
			//
			//					vboxLabels.getChildren().addAll(simReal, simRSO, simFranchise);
			//					vboxFields.getChildren().addAll(hbox, simField2, simField3);
			//				}
			//
			//				else if (value.equals("Card")) {
			//
			//					cardField1 = new TextField();
			//					cardField1.setMaxWidth(175);
			//					cardField2 = new TextField();
			//					cardField2.setMaxWidth(175);
			//					cardField3 = new TextField();
			//					cardField3.setMaxWidth(175);
			//					HBox hbox = new HBox(cardField1, new Label("   Existing Cards:		"+stock[2]));
			//
			//					vboxLabels.getChildren().addAll(cardReal, cardRSO, cardFranchise);
			//					vboxFields.getChildren().addAll(hbox, cardField2, cardField3);
			//				}
			//
			//				else if (value.equals("Name")) {
			//
			//					nameLabel = new Label("Name: ");
			//					names = new ComboBox();
			//					names.setMaxWidth(175);
			//					names.getItems().addAll(dataBase.getEmployeesNames());
			//
			//					vboxLabels.getChildren().add(nameLabel);
			//					vboxFields.getChildren().add(names);
			//
			//				} else if (value.equals("Mobiles")) {
			//
			//					mobileSpinner = new Spinner(mobileSpinnerValues);
			//					Label numberLabel = new Label("Mobile Numbers: ");
			//					vboxLabels.getChildren().add(numberLabel);
			//					HBox hbox = new HBox(mobileSpinner, new Label("   Existing Mobiles:		"+stock[3]));
			//					vboxFields.getChildren().add(hbox);
			//
			//					if(gridPane.getChildren().contains(hBox)){
			//						gridPane.getChildren().remove(hBox);
			//						gridPane.add(hBox, 4, 8);
			//						setMobilesName();
			//					}
			//
			//					mobileSpinner.setOnMouseClicked(new EventHandler<Event>() {
			//
			//						@Override
			//						public void handle(Event event) {
			//
			//							gridPane.getChildren().remove(hBox);
			//							gridPane.add(hBox, 6, 8);
			//							priceLabels.getChildren().clear();
			//							mobileLabels.getChildren().clear();
			//							priceFields.getChildren().clear();
			//							mobileFields.getChildren().clear();
			//
			//							int size = Integer.parseInt((String) mobileSpinner.getValue());
			//							if(size==0){
			//								if(gridPane.getChildren().contains(hBox)){
			//									gridPane.getChildren().remove(hBox);
			//									gridPane.add(hBox, 4, 8);
			//									setMobilesName();
			//								}
			//							}
			//							mobilePrices = new ComboBox[size];
			//							mobileNames = new ComboBox[size];
			//							for (int i = 0; i < size; i++) {
			//
			//								mobilePrices[i] = new ComboBox();
			//								mobileNames[i] = new ComboBox();
			//
			//								mobileFields.getChildren().add(mobileNames[i]);
			//
			//								priceFields.getChildren().add(mobilePrices[i]);
			//								mobilePrices[i].getItems().addAll(price);
			//								mobileNames[i].setMinWidth(175);
			//								mobilePrices[i].setMinWidth(175);
			//								mobileLabels.getChildren().add(new Label("Mobile No." + (i + 1)));
			//								mobileNames[i].getItems().addAll(allMobileNames);
			//								priceLabels.getChildren().add(new Label("Mobile Price"));
			//							}
			//						}
			//					});
			//
			//				}
			//
			//				else if (value.equals("Easy Load")) {
			//
			//					easyLoad = new TextField();
			//					easyLoadLabel = new Label("Easy Load");
			//					vboxLabels.getChildren().add(easyLoadLabel);
			//					HBox hbox = new HBox(easyLoad, new Label("   Existing Easy Load:		"+stock[4]));
			//					vboxFields.getChildren().add(hbox);
			//				}
			//
			//				else if (value.equals("Easy Paisa")) {
			//
			//					easyPaisa = new TextField();
			//					easyPaisaLabel = new Label("Easy Paisa");
			//					vboxLabels.getChildren().add(easyPaisaLabel);
			//					HBox hbox = new HBox(easyPaisa, new Label("   Existing Easy Paisa:		"+stock[5]));
			//					vboxFields.getChildren().add(hbox);
			////					vboxFields.getChildren().add(easyPaisa);
			//				}
			//
			//				else if (value.equals("Easy Paisa Return")) {
			//
			//					easyPaisaReturn = new TextField();
			//					easyPaisaReturnLabel = new Label("Easy Paisa Return");
			//					vboxLabels.getChildren().add(easyPaisaReturnLabel);
			//					vboxFields.getChildren().add(easyPaisaReturn);
			//				}
			//
			//				else if (value.equals("Cash")) {
			//
			//					cashReturn = new TextField();
			//					cashReturnLabel = new Label("Cash Return");
			//					vboxLabels.getChildren().add(cashReturnLabel);
			//					HBox hbox = new HBox(cashReturn, new Label("   Existing Cash:		"+stock[6]));
			//					vboxFields.getChildren().add(hbox);
			////					vboxFields.getChildren().add(cashReturn);
			//
			//					if(gridPane.getChildren().contains(hBox)){
			//						gridPane.getChildren().remove(hBox);
			//						gridPane.add(hBox, 4, 8);
			//						setMobilesName();
			//					}
			//				}
			//
			//				else if (value.equals("Expenses")) {
			//
			//					expenses = new TextField();
			//					expensesLabel = new Label("Expenses: ");
			//					vboxLabels.getChildren().add(expensesLabel);
			//					vboxFields.getChildren().add(expenses);
			//
			//					if(gridPane.getChildren().contains(hBox)){
			//						gridPane.getChildren().remove(hBox);
			//						gridPane.add(hBox, 4, 8);
			//						setMobilesName();
			//					}
			//				}
			//
			//			}

		});

		inputColName.setMinWidth(175);
		gridPane.add(inputColName, 4, 5);

//		gridPane.add(mobileLabels, 3, 7);
//		gridPane.add(mobileFields, 4, 7);
//		gridPane.add(priceLabels, 5, 7);
//		gridPane.add(priceFields, 6, 7);
		gridPane.add(vboxLabels, 3, 6);
		gridPane.add(vboxFields, 4, 6);

		scrollPane = new ScrollPane(gridPane);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleEvent(String key){



		vboxFields.getChildren().clear();
		vboxLabels.getChildren().clear();

		priceLabels.getChildren().clear();
		mobileLabels.getChildren().clear();
		priceFields.getChildren().clear();
		mobileFields.getChildren().clear();

		value = (String) inputColName.getValue();
		int index = spinnerValues.indexOf(value);
		if(key.equals("Up")){
			if(index==10)
				value = (String) spinnerValues.get(index);
			else
				value = (String) spinnerValues.get(index + 1);
		}

		else if(key.equals("Down")){
			if(index==0)
				value = (String) spinnerValues.get(index);
			else
				value = (String) spinnerValues.get(index - 1);
		}

		if (value.equals("HLR")) {

			HLRField1 = new TextField();
			HLRField1.setMaxWidth(175);
			HLRField2 = new TextField();
			HLRField2.setMaxWidth(175);
			HLRField3 = new TextField();
			HLRField3.setMaxWidth(175);
			HBox hbox = new HBox(HLRField1, new Label("   Existing HLR's:		"+stock[0]));

			vboxLabels.getChildren().addAll(HLRReal, HLRRSO, HLRFranchise);
			vboxFields.getChildren().addAll(hbox, HLRField2, HLRField3);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
		}

		else if (value.equals("Sim")) {

			simField1 = new TextField();
			simField1.setMaxWidth(175);
			simField2 = new TextField();
			simField2.setMaxWidth(175);
			simField3 = new TextField();
			simField3.setMaxWidth(175);
			HBox hbox = new HBox(simField1, new Label("   Existing sims:		"+stock[1]));

			vboxLabels.getChildren().addAll(simReal, simRSO, simFranchise);
			vboxFields.getChildren().addAll(hbox, simField2, simField3);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
		}

		else if (value.equals("Card")) {

			cardField1 = new TextField();
			cardField1.setMaxWidth(175);
			cardField2 = new TextField();
			cardField2.setMaxWidth(175);
			cardField3 = new TextField();
			cardField3.setMaxWidth(175);
			HBox hbox = new HBox(cardField1, new Label("   Existing Cards:		"+stock[2]));

			vboxLabels.getChildren().addAll(cardReal, cardRSO, cardFranchise);
			vboxFields.getChildren().addAll(hbox, cardField2, cardField3);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
		}

		else if (value.equals("Name")) {

			nameLabel = new Label("Name: ");
			names = new ComboBox();
			names.setMaxWidth(175);
			names.getItems().addAll(dataBase.getEmployeesNames());

			vboxLabels.getChildren().add(nameLabel);
			vboxFields.getChildren().add(names);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}

		} else if (value.equals("Mobiles")) {

			mobileSpinner = new Spinner(mobileSpinnerValues);
			Label numberLabel = new Label("Mobile Numbers: ");
			vboxLabels.getChildren().add(numberLabel);
			HBox hbox = new HBox(mobileSpinner, new Label("   Existing Mobiles:		"+stock[3]));
			vboxFields.getChildren().add(hbox);

//			if(gridPane.getChildren().contains(hBox)){
//				gridPane.getChildren().remove(hBox);
//				gridPane.add(hBox, 4, 8);
//				setMobilesName();
//			}

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
				}

				//
				//					gridPane.getChildren().remove(hBox);
				//					gridPane.add(hBox, 6, 8);
				//					priceLabels.getChildren().clear();
				//					mobileLabels.getChildren().clear();
				//					priceFields.getChildren().clear();
				//					mobileFields.getChildren().clear();
				//
				//					int size = Integer.parseInt((String) mobileSpinner.getValue());
				//					if(size==0){
				//						if(gridPane.getChildren().contains(hBox)){
				//							gridPane.getChildren().remove(hBox);
				//							gridPane.add(hBox, 4, 8);
				//							setMobilesName();
				//						}
				//					}
				//					mobilePrices = new ComboBox[size];
				//					mobileNames = new ComboBox[size];
				//					for (int i = 0; i < size; i++) {
				//
				//						mobilePrices[i] = new ComboBox();
				//						mobileNames[i] = new ComboBox();
				//
				//						mobileFields.getChildren().add(mobileNames[i]);
				//
				//						priceFields.getChildren().add(mobilePrices[i]);
				//						mobilePrices[i].getItems().addAll(price);
				//						mobileNames[i].setMinWidth(175);
				//						mobilePrices[i].setMinWidth(175);
				//						mobileLabels.getChildren().add(new Label("Mobile No." + (i + 1)));
				//						mobileNames[i].getItems().addAll(allMobileNames);
				//						priceLabels.getChildren().add(new Label("Mobile Price"));
				//					}
				//				}
			});

		}

		else if (value.equals("Easy Load")) {

			easyLoad = new TextField();
			easyLoadLabel = new Label("Easy Load");
			vboxLabels.getChildren().add(easyLoadLabel);
			HBox hbox = new HBox(easyLoad, new Label("   Existing Easy Load:		"+stock[4]));
			vboxFields.getChildren().add(hbox);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
		}

		else if (value.equals("Easy Paisa")) {

			easyPaisa = new TextField();
			easyPaisaLabel = new Label("Easy Paisa");
			vboxLabels.getChildren().add(easyPaisaLabel);
			HBox hbox = new HBox(easyPaisa, new Label("   Existing Easy Paisa:		"+stock[5]));
			vboxFields.getChildren().add(hbox);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
			//			vboxFields.getChildren().add(easyPaisa);
		}

		else if (value.equals("Easy Paisa Return")) {

			easyPaisaReturn = new TextField();
			easyPaisaReturnLabel = new Label("Easy Paisa Return");
			vboxLabels.getChildren().add(easyPaisaReturnLabel);
			vboxFields.getChildren().add(easyPaisaReturn);
			if(!gridPane.getChildren().contains(hBox)){
				gridPane.add(hBox, 4, 8);
			}
		}

		else if (value.equals("Cash")) {

			cashReturn = new TextField();
			cashReturnLabel = new Label("Cash Return");
			vboxLabels.getChildren().add(cashReturnLabel);
			HBox hbox = new HBox(cashReturn, new Label("   Existing Cash:		"+stock[6]));
			vboxFields.getChildren().add(hbox);
			//			vboxFields.getChildren().add(cashReturn);

			if(gridPane.getChildren().contains(hBox)){
				gridPane.getChildren().remove(hBox);
				gridPane.add(hBox, 4, 8);
				setMobilesName();
			}
		}

		else if (value.equals("Expenses")) {

			expenses = new TextField();
			expensesLabel = new Label("Expenses: ");
			vboxLabels.getChildren().add(expensesLabel);
			vboxFields.getChildren().add(expenses);

			if(gridPane.getChildren().contains(hBox)){
				gridPane.getChildren().remove(hBox);
				gridPane.add(hBox, 4, 8);
				setMobilesName();
			}
		}


	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void handleMobileEvent(String key){


//		gridPane.getChildren().remove(hBox);
//		gridPane.add(hBox, 6, 8);
		priceLabels.getChildren().clear();
		mobileLabels.getChildren().clear();
		priceFields.getChildren().clear();
		mobileFields.getChildren().clear();
		
		gridPane.getChildren().remove(mobileLabels);
		gridPane.getChildren().remove(priceLabels);
		gridPane.getChildren().remove(mobileFields);
		gridPane.getChildren().remove(priceFields);

		//		value = (String) inputColName.getValue();
		//		int index = spinnerValues.indexOf(value);
		//		if(key.equals("Up")){

		//		
		//		else if(key.equals("Down")){
		//			


		String spinnervalue = (String) mobileSpinner.getValue();
		int index = mobileSpinnerValues.indexOf(spinnervalue);

		int size = 0;
		if(key.equals("Up")){
			if(index==10)
				size = Integer.parseInt((String) mobileSpinnerValues.get(index));
			else
				size = Integer.parseInt((String) mobileSpinnerValues.get(index+1));
		}

		else if(key.equals("Down")){
			if(index==0)
				size = Integer.parseInt((String) mobileSpinnerValues.get(index));
			else
				size = Integer.parseInt((String) mobileSpinnerValues.get(index-1));
		}
		else
			size = Integer.parseInt((String) mobileSpinner.getValue());




//		if(size==0){
//			if(gridPane.getChildren().contains(hBox)){
				gridPane.getChildren().remove(hBox);
//				gridPane.add(hBox, 4, 8);
//				setMobilesName();
//			}
//		}
		mobilePrices = new ComboBox[size];
		mobileNames = new ComboBox[size];
		for (int i = 0; i < size; i++) {

			mobilePrices[i] = new ComboBox();
			mobileNames[i] = new ComboBox();

			mobileFields.getChildren().add(mobileNames[i]);

			priceFields.getChildren().add(mobilePrices[i]);
			mobilePrices[i].getItems().addAll(price);
			mobileNames[i].setMinWidth(175);
			mobilePrices[i].setMinWidth(175);
			mobileLabels.getChildren().add(new Label("Mobile No." + (i + 1)));
			mobileNames[i].getItems().addAll(allMobileNames);
			priceLabels.getChildren().add(new Label("Mobile Price"));
		}
		
		gridPane.add(mobileLabels, 3, 7);
		gridPane.add(mobileFields, 4, 7);
		gridPane.add(priceLabels, 5, 7);
		gridPane.add(priceFields, 6, 7);
		
//		if(size > 0){
			if(gridPane.getChildren().contains(hBox)){
				gridPane.getChildren().remove(hBox);
				gridPane.add(hBox, 6, 8);
//				setMobilesName();
			}
			else
				gridPane.add(hBox, 6, 8);
//		}

	}

	public boolean validtyOfEntryNumberAndNewValue(){


		if(entryNumberField.getText().isEmpty())
			return false;
		else if(Integer.parseInt(entryNumberField.getText())> Integer.parseInt(dataBase.getRowCount()))
			return false;

		if(value.equals("Name") && names.getSelectionModel().isEmpty())
			return false;
		else if(value.equals("HLR")&&(HLRField1.getText().isEmpty() || HLRField2.getText().isEmpty() || HLRField3.getText().isEmpty()))
			return false;
		else if(value.equals("Sim") && (simField1.getText().isEmpty() || simField2.getText().isEmpty() || simField3.getText().isEmpty()))
			return false;
		else if(value.equals("Card") && (cardField1.getText().isEmpty() || cardField2.getText().isEmpty() || cardField3.getText().isEmpty()))
			return false;
		else if(value.equals("Easy Load") && easyLoad.getText().isEmpty())
			return false;
		else if(value.equals("Easy Paisa") && easyPaisa.getText().isEmpty())
			return false;
		else if(value.equals("Easy Paisa Return") && easyPaisaReturn.getText().isEmpty())
			return false;
		else if(value.equals("Cash") && cashReturn.getText().isEmpty())
			return false;
		else if(value.equals("Mobiles")){

			int size = Integer.parseInt((String)mobileSpinner.getValue());
			if(size>0){
				for(int i=0; i<size; i++){

					if(mobileNames[i].getSelectionModel().isEmpty() || mobilePrices[i].getSelectionModel().isEmpty()){
						return false;
					}
				}
			}
		}
		else if(value.equals("Expenses") && expenses.getText().isEmpty())
			return false;
		else if(value.equals("Select Column")){
			return false;
		}
		return true;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public Button getButton() {
		return submitButton;
	}

	public String getEntryNumber() {
		return entryNumberField.getText();
	}

	@SuppressWarnings("unchecked")
	public void getMobileData() {

		mobilesData = dataBase.getMobileData();
	}

	public void setMobilesName() {

		allMobileNames = new ArrayList<>();
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

	public void getMobilePriceAndNames() {

		MobileNames = "";
		allMobilePrices = "";
		totalMobilePrice = 0;
		int size;
		if (mobileNames == null)
			size = 0;
		else
			size = mobileNames.length;

		if (size == 0) {
			MobileNames = "'"+"'";
			allMobilePrices = "'"+"'";
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
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][2]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][1];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][1]);
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][2]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}

					if (priceType.equals("Real Price")) {

						if (allMobilePrices.equals("")) {
							allMobilePrices = (String) mobilesAllData[row][1];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][1]);
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][1]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][2];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][2]);
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][1]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}

					if (priceType.equals("Franchise Price")) {

						if (allMobilePrices.equals("")) {
							allMobilePrices = (String) mobilesAllData[row][3];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][3]);
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][3]) - Double.parseDouble((String) mobilesAllData[row][1]);
						} else {
							allMobilePrices += "-" + (String) mobilesAllData[row][3];
							totalMobilePrice += Double.parseDouble((String) mobilesAllData[row][3]);
							mobilesNewProfit += Double.parseDouble((String) mobilesAllData[row][3]) - Double.parseDouble((String) mobilesAllData[row][1]);
						}
					}
					break;
				}
			}

		}

	}

	@SuppressWarnings("unused")
	public void changeEntry(){

		String[] data =  dataBase.getASpecificRow(entryNumberField.getText()).split(",");

		String query = null, stockQuery = "";

		if (value.equals("Sim")) {

			double oldSimPrice = Double.parseDouble(data[7]);
			System.out.println("Old Sim Price is: "+ oldSimPrice);
			double total = Double.parseDouble(data[23]);
			System.out.println("old total is: "+ total);
			total-=oldSimPrice;
			
			double totalSimPrice = 0; double totalSims = 0; double newSimProfit = 0;
			totalSims += Double.parseDouble(simField1.getText());
			totalSimPrice += Double.parseDouble((String) simPrices[1]) * Double.parseDouble(simField1.getText());
			newSimProfit += (Double.parseDouble((String) simPrices[1]) - Double.parseDouble((String) simPrices[1])) * Double.parseDouble(simField1.getText());

			totalSims += Double.parseDouble(simField2.getText());
			totalSimPrice += Double.parseDouble((String) simPrices[0]) * Double.parseDouble(simField2.getText());
			newSimProfit += (Double.parseDouble((String) simPrices[0]) - Double.parseDouble((String) simPrices[1])) * Double.parseDouble(simField2.getText());

			totalSims += Double.parseDouble(simField3.getText());
			totalSimPrice += Double.parseDouble((String) simPrices[2]) * Double.parseDouble(simField3.getText());
			newSimProfit += (Double.parseDouble((String) simPrices[2]) - Double.parseDouble((String) simPrices[1])) * Double.parseDouble(simField3.getText());
			System.out.println("New total sim Price: "+totalSimPrice);
			total+=totalSimPrice;
			System.out.println("New total is: " + total);

			double simProfit  = Double.parseDouble(data[8]);
			double totalProfit = Double.parseDouble(data[24]);
			totalProfit-=simProfit;
			totalProfit+=newSimProfit;
			double oldSimStock = Double.parseDouble(stock[1]);
			double copyOldSimStock = oldSimStock;
			double oldSim = Double.parseDouble(data[6]);
			oldSimStock += oldSim;

			if(totalSims>oldSimStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough sims in your stock.");
			}
			else{
				double newSimsStock = oldSimStock - totalSims;

				stockQuery = "UPDATE Stock set Sim = "+'"'+newSimsStock+'"'+" where Sim = "+'"'+copyOldSimStock+'"'+";";

				query = "UPDATE telenorTable SET Sim="+'"'+totalSims+'"'+",SimPrice="+'"'+totalSimPrice+'"'+",Total="+'"'+total+'"'+",SimProfit="+'"'+newSimProfit+'"'+",TotalProfit="+'"'+totalProfit+'"'+" where EntryNo="+
						'"'+entryNumberField.getText()+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0; 

		}


		else if (value.equals("Name")) {

			query = "UPDATE telenorTable set Name="+'"'+names.getSelectionModel().getSelectedItem()+'"'+" where EntryNo="+
					'"'+entryNumberField.getText()+'"'+";";
		}

		else if(value.equals("HLR")){

			double oldHLRPrice = Double.parseDouble(data[4]);
			double total = Double.parseDouble(data[23]);
			total-=oldHLRPrice; double newHLRProfit = 0;

			double totalHLRPrice = 0; double totalHLR = 0;
			totalHLR += Double.parseDouble(HLRField1.getText());
			totalHLRPrice += Double.parseDouble((String) HLRPrices[1]) * Double.parseDouble(HLRField1.getText());
			newHLRProfit += (Double.parseDouble((String) HLRPrices[1]) - Double.parseDouble((String) HLRPrices[1])) * Double.parseDouble(HLRField1.getText());

			totalHLR += Double.parseDouble(HLRField2.getText());
			totalHLRPrice += Double.parseDouble((String) HLRPrices[0]) * Double.parseDouble(HLRField2.getText());
			newHLRProfit += (Double.parseDouble((String) HLRPrices[0]) - Double.parseDouble((String) HLRPrices[2])) * Double.parseDouble(HLRField2.getText());

			totalHLR += Double.parseDouble(HLRField3.getText());
			totalHLRPrice += Double.parseDouble((String) HLRPrices[2]) * Double.parseDouble(HLRField3.getText());
			newHLRProfit += (Double.parseDouble((String) HLRPrices[2]) - Double.parseDouble((String) HLRPrices[1])) * Double.parseDouble(HLRField3.getText());

			total+=totalHLRPrice;
			double totalProfit = Double.parseDouble(data[24]), HLRProfit = Double.parseDouble(data[5]);
			totalProfit-=HLRProfit;
			totalProfit+=newHLRProfit;

			double oldHLRStock = Double.parseDouble(stock[0]);
			double copyOldHLRStock = oldHLRStock;
			double oldHLR = Double.parseDouble(data[3]);

			oldHLRStock += oldHLR;

			if(totalHLR > oldHLRStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough HLR in your stock.");
			}
			else{
				double newHLRStock = oldHLRStock - totalHLR;

				stockQuery = "UPDATE Stock set HLR = "+'"'+newHLRStock+'"'+" where HLR = "+'"'+copyOldHLRStock+'"'+";";

				query = "UPDATE telenorTable SET HLR="+'"'+totalHLR+'"'+",HLRPrice="+'"'+totalHLRPrice+'"'+",Total="+'"'+total+'"'+",HLRProfit="+'"'+newHLRProfit+'"'+",TotalProfit="+'"'+totalProfit+'"'+" where EntryNo="+

					'"'+entryNumberField.getText()+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}

		else if(value.equals("Card")){

			double oldCardPrice = Double.parseDouble(data[10]);
			double total = Double.parseDouble(data[23]), newCardProfit = 0;
			total-=oldCardPrice;
			double totalCardPrice = 0; double totalCard = 0;
			totalCard += Double.parseDouble(cardField1.getText());
			totalCardPrice += Double.parseDouble((String) cardPrices[1]) * Double.parseDouble(cardField1.getText());
			newCardProfit += (Double.parseDouble((String) cardPrices[1]) - Double.parseDouble((String) cardPrices[1])) * Double.parseDouble(cardField1.getText());

			totalCard += Double.parseDouble(cardField2.getText());
			totalCardPrice += Double.parseDouble((String) cardPrices[0]) * Double.parseDouble(cardField2.getText());
			newCardProfit += (Double.parseDouble((String) cardPrices[0]) - Double.parseDouble((String) cardPrices[1])) * Double.parseDouble(cardField2.getText());

			totalCard += Double.parseDouble(cardField3.getText());
			totalCardPrice += Double.parseDouble((String) cardPrices[2]) * Double.parseDouble(cardField3.getText());
			newCardProfit += (Double.parseDouble((String) cardPrices[2]) - Double.parseDouble((String) cardPrices[1])) * Double.parseDouble(cardField3.getText());

			total+=totalCardPrice;
			double totalProfit = Double.parseDouble(data[24]), cardProfit = Double.parseDouble(data[11]);
			totalProfit-=cardProfit;
			totalProfit+=newCardProfit;

			double oldCardStock = Double.parseDouble(stock[2]);
			double copyOldCardStock = oldCardStock;
			double oldCard = Double.parseDouble(data[9]);
			oldCardStock += oldCard;

			if(totalCard > oldCardStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough card in your stock.");
			}
			else{
				double newCardStock = oldCardStock - totalCard;

				stockQuery = "UPDATE Stock set Card = "+'"'+newCardStock+'"'+" where Card = "+'"'+copyOldCardStock+'"'+";";

				query = "UPDATE telenorTable SET card="+'"'+totalCard+'"'+",CardPrice="+'"'+totalCardPrice+'"'+",Total="+'"'+total+'"'+",CardProfit="+'"'+newCardProfit+'"'+",TotalProfit="+'"'+totalProfit+'"'+" where EntryNo="+
						'"'+entryNumberField.getText()+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}
		else if(value.equals("Mobiles")){

			getMobilePriceAndNames();
			double total = Double.parseDouble(data[23]);
			double mobilesProfit = Double.parseDouble(data[21]);
			double totalProfit = Double.parseDouble(data[24]);

			String[] oldMobilePricesArray = data[20].split("-");

			double oldMoileTotalPrice = 0;
			for(int i=0; i<oldMobilePricesArray.length; i++){
				oldMoileTotalPrice+=Double.parseDouble(oldMobilePricesArray[i]);
			}

			total-=oldMoileTotalPrice;
			total+=totalMobilePrice;
			totalProfit -= mobilesProfit;
			totalProfit += mobilesNewProfit;

			double oldMobileStock = Double.parseDouble(stock[3]);
			double copyOldMobileStock = oldMobileStock;
			double oldMobile = Double.parseDouble(data[19]);
			oldMobileStock += oldMobile;

			if(Double.parseDouble((String) mobileSpinner.getValue()) > oldMobileStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough Mobiles in your stock.");
			}
			else{
				double newMobileStock = oldMobileStock - Double.parseDouble((String) mobileSpinner.getValue());

				stockQuery = "UPDATE Stock set NumberOfMobiles = "+'"'+newMobileStock+'"'+" where NumberOfMobiles = "+'"'+copyOldMobileStock+'"'+";";

				query = "UPDATE telenorTable set Cell_Name="+'"'+MobileNames+'"'+", No_OF_Cell="+'"'+mobileSpinner.getValue()+'"'+", Cell_Price="+
						'"'+allMobilePrices+'"'+", Total="+'"'+total+'"'+", MobilesProfit="+'"'+mobilesNewProfit+'"'+", TotalProfit="+'"'+totalProfit+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";
			}


			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}

		else if(value.equals("Easy Load")){

			double total = Double.parseDouble(data[23]);
			double easyLoadProfit = Double.parseDouble(data[13]), easyLoadNewProfit = 0;
			double totalProfit = Double.parseDouble(String.valueOf(data[24]));

			total-=Double.parseDouble(data[12]);
			total+=Double.parseDouble(easyLoad.getText());
			totalProfit -= easyLoadProfit;
			easyLoadNewProfit = 3/100.0 * Double.parseDouble(easyLoad.getText());
			totalProfit += easyLoadNewProfit;
			query = "UPDATE telenorTable set E_load="+'"'+easyLoad.getText()+'"'+", E_L_Profit="+'"'+easyLoadNewProfit+'"'+", TotalProfit="+'"'+totalProfit+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";

			double oldEasyLoadStock = Double.parseDouble(stock[4]);
			double copyOldEasyLoadStock = oldEasyLoadStock;
			double oldEasyLoad = Double.parseDouble(data[12]);
			oldEasyLoadStock += oldEasyLoad;

			if(Double.parseDouble(easyLoad.getText()) > oldEasyLoadStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough Easy Load in your stock.");
			}
			else{
				double newEasyLoadStock = oldEasyLoadStock - Double.parseDouble(easyLoad.getText());

				stockQuery = "UPDATE Stock set EasyLoad = "+'"'+newEasyLoadStock+'"'+" where EasyLoad = "+'"'+copyOldEasyLoadStock+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}

		else if(value.equals("Easy Paisa")){

			double total = Double.parseDouble(data[23]);
			total-=Double.parseDouble(data[14]);
			total+=Double.parseDouble(easyPaisa.getText());

			//Formula For Easy Paisa Profit ....... ??????
			query = "UPDATE telenorTable set E_Paisa="+'"'+easyPaisa.getText()+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";

			double oldEasyPaisaStock = Double.parseDouble(stock[5]);
			double copyOldEasyPaisaStock = oldEasyPaisaStock;
			double oldEasyPaisa = Double.parseDouble(data[14]);
			oldEasyPaisaStock += oldEasyPaisa;

			if(Double.parseDouble(easyPaisa.getText()) > oldEasyPaisaStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough Easy Paisa in your stock.");
			}
			else{
				double newEasyPaisaStock = oldEasyPaisaStock - Double.parseDouble(easyPaisa.getText());

				stockQuery = "UPDATE Stock set EasyPaisa = "+'"'+newEasyPaisaStock+'"'+" where EasyPaisa = "+'"'+copyOldEasyPaisaStock+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}

		else if(value.equals("Easy Paisa Return")){

			double total = Double.parseDouble(data[23]);
			total-=Double.parseDouble(data[16]);
			total+=Double.parseDouble(easyPaisaReturn.getText());

			query = "UPDATE telenorTable set E_P_Return="+'"'+easyPaisaReturn.getText()+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";
		}

		else if(value.equals("Cash")){

			double total = Double.parseDouble(data[23]);
			total-=Double.parseDouble(data[17]);
			total+=Double.parseDouble(cashReturn.getText());

			query = "UPDATE telenorTable set Cash="+'"'+cashReturn.getText()+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";

			double oldCashStock = Double.parseDouble(stock[6]);
			double copyOldCashStock = oldCashStock;
			double oldCash = Double.parseDouble(data[17]);
			oldCashStock += oldCash;

			if( Double.parseDouble(cashReturn.getText()) > oldCashStock){
				stockQuery = "";
				JOptionPane.showMessageDialog(null, "Sorry!\nYou donot have enough Cash in your stock.");
			}
			else{
				double newCashStock = oldCashStock - Double.parseDouble(cashReturn.getText());

				stockQuery = "UPDATE Stock set Cash = "+'"'+newCashStock+'"'+" where Cash = "+'"'+copyOldCashStock+'"'+";";
			}

			mobilesNewProfit = 0; 
			totalModified = 0;
			totalMobilePrice = 0;
		}
		else if(value.equals("Expenses")){

			double total = Double.parseDouble(data[23]);
			total-=Double.parseDouble(data[22]);
			total+=Double.parseDouble(expenses.getText());

			query = "UPDATE telenorTable set Expenses="+'"'+expenses.getText()+'"'+" where EntryNo="+'"'+entryNumberField.getText()+'"'+";";
		}
		if(!stockQuery.equals("")){
			dataBase.changeEntry(query);
			dataBase.changeEntry(stockQuery);
			stock = dataBase.getStock();
		}
	}


	public void setPrices() {

		String[][] prices = dataBase.getPrices();
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


	public double getTotal() {

		return totalModified;
	}

	public String getNewValue() {
		return newValueField.getText();
	}

	@SuppressWarnings("unchecked")
	public void clearFields(){

		priceLabels.getChildren().clear();
		priceFields.getChildren().clear();
		mobileLabels.getChildren().clear();
		mobileFields.getChildren().clear();

		if(names!=null)
			names.setValue("");
		if(easyLoad!=null)
			easyLoad.setText("");
		if(easyPaisa!=null)
			easyPaisa.setText("");
		if(easyPaisaReturn!=null)
			easyPaisaReturn.setText("");
		if(cashReturn!=null)
			cashReturn.setText("");
		if(expenses!=null)
			expenses.setText("");
		entryNumberField.setText("");
		inputColName.getEditor().setText("Select Column");
	}
}
