package FrontEnd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EntriesRecord {

	ScrollPane scroll;
	private Label label;
	@SuppressWarnings("rawtypes")
	private TableColumn serNoCol;
	@SuppressWarnings("rawtypes")
	private TableColumn dateCol;
	@SuppressWarnings("rawtypes")
	private TableColumn nameCol;
	private TableView<Person> table;
	@SuppressWarnings("rawtypes")
	private TableColumn HLRCol;
	@SuppressWarnings("rawtypes")
	private TableColumn HLRPriceCol;
	@SuppressWarnings("rawtypes")
	private TableColumn HLRProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn simCol;
	@SuppressWarnings("rawtypes")
	private TableColumn simPriceCol;
	@SuppressWarnings("rawtypes")
	private TableColumn simProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn easyLoadCol;
	@SuppressWarnings("rawtypes")
	private TableColumn easyLoadProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn easyPaisaCol;
	@SuppressWarnings("rawtypes")
	private TableColumn easyPaisaProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn easyPaisaReturnCol;
	@SuppressWarnings("rawtypes")
	private TableColumn cashCol;
	@SuppressWarnings("rawtypes")
	private TableColumn mobileNameCol;
	@SuppressWarnings("rawtypes")
	private TableColumn numberOfMobileCol;
	@SuppressWarnings("rawtypes")
	private TableColumn priceOfMobileCol;
	@SuppressWarnings("rawtypes")
	private TableColumn mobilesProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn totalCol;
	@SuppressWarnings("rawtypes")
	private TableColumn totalProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn cardCol;
	@SuppressWarnings("rawtypes")
	private TableColumn cardPriceCol;
	@SuppressWarnings("rawtypes")
	private TableColumn cardProfitCol;
	@SuppressWarnings("rawtypes")
	private TableColumn expensesCol;
	private PlayWithDataBase dataBase = new PlayWithDataBase();

	private VBox vbox = new VBox();
	private Button deleteButton = new Button("Delete");

	private final ObservableList<Person> data = FXCollections.observableArrayList();

	double totalHLR = 0.0, totalHLRPrice = 0.0, totalHLRProfit = 0.0;
	double totalSim = 0.0, totalSimPrice = 0.0, totalSimProfit = 0.0;
	double totalCard = 0.0, totalCardPrice = 0.0, totalCardProfit = 0.0;
	double totalEasyLoad = 0.0,  totalEasyLoadProfit = 0.0;
	double totalEasyPaisa = 0.0, totalEasyPaisaProfit = 0.0;
	double totalEasyPaisaReturn = 0, totalCash = 0;
	double totalMobiles = 0.0, totalMobilesPrice = 0.0, totalMobilesProfit = 0.0;
	double totalExpenses = 0.0, totalTotal = 0.0, totalProfit = 0.0;



	@SuppressWarnings({ "rawtypes", "unchecked" })
	// @Override
	public void start(String value, String startDate, String endDate) {

		scroll = new ScrollPane();
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);

		// stage.setTitle("Employee Record");
		// stage.setWidth(650);
		// stage.setHeight(600);

		populateObservableArray(value, startDate, endDate);

		label = new Label("Employee Record");
		label.setFont(new Font("Arial", 30));

		table = new TableView<>();
		table.setEditable(true);

		serNoCol = new TableColumn("Entry No.");
		serNoCol.setMinWidth(100);
		serNoCol.setCellValueFactory(new PropertyValueFactory<>("serNo"));

		dateCol = new TableColumn("Date.");
		dateCol.setMinWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

		nameCol = new TableColumn("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		HLRCol = new TableColumn("HLR");
		HLRCol.setMinWidth(100);
		HLRCol.setCellValueFactory(new PropertyValueFactory<>("HLR"));

		HLRPriceCol = new TableColumn("HLR Price");
		HLRPriceCol.setMinWidth(100);
		HLRPriceCol.setCellValueFactory(new PropertyValueFactory<>("HLRPrice"));

		HLRProfitCol = new TableColumn("HLR Profit");
		HLRProfitCol.setMinWidth(100);
		HLRProfitCol.setCellValueFactory(new PropertyValueFactory<>("HLRProfit"));

		simCol = new TableColumn("Sim");
		simCol.setMinWidth(100);
		simCol.setCellValueFactory(new PropertyValueFactory<>("sim"));

		simPriceCol = new TableColumn("Sim Price");
		simPriceCol.setMinWidth(100);
		simPriceCol.setCellValueFactory(new PropertyValueFactory<>("simPrice"));

		simProfitCol = new TableColumn("Sim Profit");
		simProfitCol.setMinWidth(100);
		simProfitCol.setCellValueFactory(new PropertyValueFactory<>("simProfit"));

		cardCol = new TableColumn("Card");
		cardCol.setMinWidth(100);
		cardCol.setCellValueFactory(new PropertyValueFactory<>("card"));

		cardPriceCol = new TableColumn("Card Price");
		cardPriceCol.setMinWidth(100);
		cardPriceCol.setCellValueFactory(new PropertyValueFactory<>("cardPrice"));

		cardProfitCol = new TableColumn("Card Profit");
		cardProfitCol.setMinWidth(100);
		cardProfitCol.setCellValueFactory(new PropertyValueFactory<>("cardProfit"));

		easyLoadCol = new TableColumn("Easy Load");
		easyLoadCol.setMinWidth(100);
		easyLoadCol.setCellValueFactory(new PropertyValueFactory<>("easyLoad"));

		easyLoadProfitCol = new TableColumn("Easy Load Profit");
		easyLoadProfitCol.setMinWidth(150);
		easyLoadProfitCol.setCellValueFactory(new PropertyValueFactory<>("easyLoadProfit"));

		easyPaisaCol = new TableColumn("Easy Paisa");
		easyPaisaCol.setMinWidth(100);
		easyPaisaCol.setCellValueFactory(new PropertyValueFactory<>("easyPaisa"));

		easyPaisaProfitCol = new TableColumn("Easy Paisa Profit");
		easyPaisaProfitCol.setMinWidth(150);
		easyPaisaProfitCol.setCellValueFactory(new PropertyValueFactory<>("easyPaisaProfit"));

		easyPaisaReturnCol = new TableColumn("Easy Paisa Return");
		easyPaisaReturnCol.setMinWidth(150);
		easyPaisaReturnCol.setCellValueFactory(new PropertyValueFactory<>("easyPaisaReturn"));

		cashCol = new TableColumn("Cash");
		cashCol.setMinWidth(100);
		cashCol.setCellValueFactory(new PropertyValueFactory<>("cash"));

		mobileNameCol = new TableColumn("Mobile Name");
		mobileNameCol.setMinWidth(300);
		mobileNameCol.setCellValueFactory(new PropertyValueFactory<>("mobileName"));

		numberOfMobileCol = new TableColumn("Number Of Mobile");
		numberOfMobileCol.setMinWidth(150);
		numberOfMobileCol.setCellValueFactory(new PropertyValueFactory<>("no"));

		priceOfMobileCol = new TableColumn("Price Of Mobile");
		priceOfMobileCol.setMinWidth(250);
		priceOfMobileCol.setCellValueFactory(new PropertyValueFactory<>("salePrice"));

		mobilesProfitCol = new TableColumn("Mobiles Profit");
		mobilesProfitCol.setMinWidth(150);
		mobilesProfitCol.setCellValueFactory(new PropertyValueFactory<>("mobilesProfit"));

		expensesCol = new TableColumn("Expenses");
		expensesCol.setMinWidth(150);
		expensesCol.setCellValueFactory(new PropertyValueFactory<>("expenses"));

		totalCol = new TableColumn("Total");
		totalCol.setMinWidth(150);
		totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

		totalProfitCol = new TableColumn("Total Profit");
		totalProfitCol.setMinWidth(150);
		totalProfitCol.setCellValueFactory(new PropertyValueFactory<>("totalProfit"));

		table.setItems(data);
		table.getColumns().addAll(serNoCol, dateCol, nameCol, HLRCol, HLRPriceCol, HLRProfitCol, simCol, simPriceCol, simProfitCol, cardCol,
				cardPriceCol, cardProfitCol, easyLoadCol, easyLoadProfitCol, easyPaisaCol, easyPaisaProfitCol, easyPaisaReturnCol, cashCol, mobileNameCol, numberOfMobileCol,
				priceOfMobileCol, mobilesProfitCol, expensesCol, totalCol, totalProfitCol);

		deleteButton.setMinSize(100, 30);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TableView table = (TableView)scroll.getContent();
				Person person = (Person) table.getSelectionModel().getSelectedItem();
				if(person==null){
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select A Row Before Any Action");
				}
				else{

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogueResult =    JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);

					if(dialogueResult == JOptionPane.YES_OPTION){
						dataBase.deleteEntry(person.getSerNo());
						start("All", "All", "All");
					}

				}
			}
		});

		table.setPrefSize(650, 600);
		scroll.setContent(table);

		vbox.getChildren().add(scroll);
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_RIGHT);
		hbox.setPadding(new Insets(20, 20, 10, 10));
		hbox.getChildren().add(deleteButton);
		//vbox.getChildren().add(hbox);
	}

	public void deletePerson(Person person){
		data.remove(person);
	}

	public static class Person {

		private final SimpleStringProperty serNo;
		private final SimpleStringProperty date;
		private final SimpleStringProperty name;
		private final SimpleStringProperty HLR;
		private final SimpleStringProperty HLRPrice;
		private final SimpleStringProperty HLRProfit;
		private final SimpleStringProperty sim;
		private final SimpleStringProperty simPrice;
		private final SimpleStringProperty simProfit;
		private final SimpleStringProperty card;
		private final SimpleStringProperty cardPrice;
		private final SimpleStringProperty cardProfit;
		private final SimpleStringProperty mobileName;
		private final SimpleStringProperty mobilesProfit;
		private final SimpleStringProperty salePrice;
		private final SimpleStringProperty no;
		private final SimpleStringProperty easyLoad;
		private final SimpleStringProperty easyLoadProfit;
		private final SimpleStringProperty easyPaisa;
		private final SimpleStringProperty easyPaisaProfit;
		private final SimpleStringProperty easyPaisaReturn;
		private final SimpleStringProperty cash;
		private final SimpleStringProperty expenses;
		private final SimpleStringProperty total;
		private final SimpleStringProperty totalProfit;

		private Person(String serNo, String date, String name, String HLR, String HLRPrice, String HLRProfit, String sim, String simPrice, String simProfit,
				String card, String cardPrice, String cardProfit, String easyLoad, String easyLoadProfit, String easyPaisa, String easyPaisaProfit, String easyPaisaReturn, String cash,
				String mobileName, String no, String salePrice, String mobilesProfit, String expenses, String total, String totalProfit) {

			this.serNo = new SimpleStringProperty(serNo);
			this.date = new SimpleStringProperty(date);
			this.name = new SimpleStringProperty(name);
			this.HLR = new SimpleStringProperty(HLR);
			this.HLRPrice = new SimpleStringProperty(HLRPrice);
			this.HLRProfit = new SimpleStringProperty(HLRProfit);
			this.sim = new SimpleStringProperty(sim);
			this.simPrice = new SimpleStringProperty(simPrice);
			this.simProfit = new SimpleStringProperty(simProfit);
			this.card = new SimpleStringProperty(card);
			this.cardPrice = new SimpleStringProperty(cardPrice);
			this.cardProfit = new SimpleStringProperty(cardProfit);
			this.mobileName = new SimpleStringProperty(mobileName);
			this.mobilesProfit = new SimpleStringProperty(mobilesProfit);
			this.salePrice = new SimpleStringProperty(salePrice);
			this.no = new SimpleStringProperty(no);
			this.easyLoad = new SimpleStringProperty(easyLoad);
			this.easyLoadProfit = new SimpleStringProperty(easyLoadProfit);
			this.easyPaisa = new SimpleStringProperty(easyPaisa);
			this.easyPaisaProfit = new SimpleStringProperty(easyPaisaProfit);
			this.easyPaisaReturn = new SimpleStringProperty(easyPaisaReturn);
			this.cash = new SimpleStringProperty(cash);
			this.expenses = new SimpleStringProperty(expenses);
			this.total = new SimpleStringProperty(total);
			this.totalProfit = new SimpleStringProperty(totalProfit);
		}

		public String getSerNo() {
			return serNo.get();
		}

		public void setSerNo(String fName) {
			serNo.set(fName);
		}

		public String getDate() {
			return date.get();
		}

		public void setDate(String date) {
			this.date.set(date);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String Name) {
			name.set(Name);
		}

		public String getHLR() {
			return HLR.get();
		}

		public void setHLR(String HLR) {
			this.HLR.set(HLR);
		}

		public String getHLRPrice() {
			return HLRPrice.get();
		}

		public String getHLRProfit() {
			return HLRProfit.get();
		}

		public void setHLRPrfoit(String HLRProfit) {
			this.HLRProfit.set(HLRProfit);
		}

		public void setHLRPrice(String HLRPrice) {
			this.HLRPrice.set(HLRPrice);
		}

		public String getSim() {
			return sim.get();
		}

		public void setSim(String sim) {
			this.sim.set(sim);
		}

		public String getSimPrice() {
			return simPrice.get();
		}

		public void setSimPrfoit(String simProfit) {
			this.simProfit.set(simProfit);
		}

		public String getSimProfit() {
			return simProfit.get();
		}

		public void setSimPrice(String simPrice) {
			this.simPrice.set(simPrice);
		}

		public String getCard() {
			return card.get();
		}

		public void setCard(String card) {
			this.card.set(card);
		}

		public String getCardPrice() {
			return cardPrice.get();
		}

		public String getCardProfit() {
			return cardProfit.get();
		}

		public void setCardProfit(String cardProfit) {
			this.cardProfit.set(cardProfit);
		}

		public void setCardPrice(String cardPrice) {
			this.cardPrice.set(cardPrice);
		}

		public String getMobileName() {
			return mobileName.get();
		}

		public void setMobileName(String mobileName) {
			this.mobileName.set(mobileName);
		}

		public String getSalePrice() {
			return salePrice.get();
		}

		public void setSalePrice(String salePrice) {
			this.salePrice.set(salePrice);
		}

		public String getMobilesProfit() {
			return mobilesProfit.get();
		}

		public void setMobilesProfit(String mobilesProfit) {
			this.mobilesProfit.set(mobilesProfit);
		}

		public String getNo() {
			return no.get();
		}

		public void setNo(String no) {
			this.no.set(no);
		}

		public String getEasyLoad() {
			return easyLoad.get();
		}

		public String getEasyLoadProfit() {
			return easyLoadProfit.get();
		}

		public void setEasyLoad(String easyLoad) {
			this.easyLoad.set(easyLoad);
		}

		public void setEasyLoadProfit(String easyLoadProfit) {
			this.easyLoadProfit.set(easyLoadProfit);
		}

		public String getEasyPaisa() {
			return easyPaisa.get();
		}

		public void setEasyPaisa(String easyPaisa) {
			this.easyPaisa.set(easyPaisa);
		}

		public String getEasyPaisaProfit() {
			return easyPaisaProfit.get();
		}

		public void setEasyPaisaProfit(String easyPaisaProfit) {
			this.easyPaisaProfit.set(easyPaisaProfit);
		}

		public String getEasyPaisaReturn() {
			return easyPaisaReturn.get();
		}

		public void setEasyPaisaReturn(String easyPaisaReturn) {
			this.easyPaisaReturn.set(easyPaisaReturn);
		}

		public String getExpenses() {
			return expenses.get();
		}

		public void setExpenses(String expenses) {
			this.expenses.set(expenses);
		}

		public String getTotal() {
			return total.get();
		}

		public void setTotal(String total) {
			this.total.set(total);
		}

		public String getTotalProfit() {
			return totalProfit.get();
		}

		public void setTotalProfit(String totalProfit) {
			this.totalProfit.set(totalProfit);
		}

		public String getCash() {
			return cash.get();
		}

		public void setCash(String cash) {
			this.cash.set(cash);
		}
	}


	public VBox getVBox(){
		return vbox;
	}

	public ScrollPane getScrollPane(){
		return scroll;
	}



	private ArrayList<String> dataForSheet;

	@SuppressWarnings("unchecked")
	public void populateObservableArray(String value, String startDate, String endDate) {

		dataForSheet = new ArrayList<String>();
		String totalData = "Entry Number," + "Date," + "Name," + "HLR," +"HLR Price," +
				"HLR Profit," + "Sim," + "Sim Price," + "Sim Profit," + "Card," + "Card Price," +
				"Card Profit," + "Easy Load," + "Easy Load Profit," + "Easy Paisa," + "Easy Paisa Profit," +
				"Easy Paisa Return," + "Cash Return," + "All Mobiles," + "Numbers Of Cell," + 
				"Cells Price,"+ "Mobiles Profit," + "Expenses," + "Total," + "Total Profit";
		dataForSheet.add(totalData);

		ArrayList<String> record = dataBase.getEntriesRecord();
		if (value.equals("All") && startDate.equals("All") && endDate.equals("All")) {

			for (int row = 0; row < record.size(); row++) {	

				String rowData = record.get(row);
				dataForSheet.add(rowData);
				String[] dataString = rowData.split(",");

				data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3], dataString[4],
						dataString[5], dataString[6], dataString[7], dataString[8], dataString[9], dataString[10],
						dataString[11], dataString[12], dataString[13], dataString[14], dataString[15], dataString[16],
						dataString[17] , dataString[18], dataString[19], dataString[20], dataString[21], dataString[22],
						dataString[23], dataString[24]));

			}
		}

		else if (startDate.equals("") && endDate.equals("")) {

			for (int row = 0; row < record.size(); row++) {
				String rowData = record.get(row);

				String[] dataString = rowData.split(",");
				value = value.trim();
				dataString[2] = dataString[2].trim();

				if (value.equals("All")) {

					dataForSheet.add(rowData);
					calculateTotal(dataString);
					data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3], dataString[4],
							dataString[5], dataString[6], dataString[7], dataString[8], dataString[9], dataString[10],
							dataString[11], dataString[12], dataString[13], dataString[14], dataString[15], dataString[16],
							dataString[17] , dataString[18], dataString[19], dataString[20], dataString[21], dataString[22],
							dataString[23], dataString[24]));
				}
				else if (value.equals(dataString[2])) {

					dataForSheet.add(rowData);
					calculateTotal(dataString);
					data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3], dataString[4],
							dataString[5], dataString[6], dataString[7], dataString[8], dataString[9], dataString[10],
							dataString[11], dataString[12], dataString[13], dataString[14], dataString[15], dataString[16],
							dataString[17] , dataString[18], dataString[19], dataString[20], dataString[21], dataString[22], 
							dataString[23], dataString[24]));
				}
			}

			data.add(new Person("", "", "Total Calculations", String.valueOf(totalHLR), String.valueOf(totalHLRPrice),
					String.valueOf(totalHLRProfit), String.valueOf(totalSim), String.valueOf(totalSimPrice),
					String.valueOf(totalSimProfit), String.valueOf(totalCard), String.valueOf(totalCardPrice),
					String.valueOf(totalCardProfit), String.valueOf(totalEasyLoad), String.valueOf(totalEasyLoadProfit),
					String.valueOf(totalEasyPaisa), String.valueOf(totalEasyPaisaProfit),
					String.valueOf(totalEasyPaisaReturn), String.valueOf(totalCash), "All Mobiles", String.valueOf(totalMobiles), 
					String.valueOf(totalMobilesPrice), String.valueOf(totalMobilesProfit), 
					String.valueOf(totalExpenses), String.valueOf(totalTotal), String.valueOf(totalProfit)));

			totalData = " ," + " ," + "Total Calculation," + String.valueOf(totalHLR)+"," + String.valueOf(totalHLRPrice)+"," +
					String.valueOf(totalHLRProfit)+"," + String.valueOf(totalSim)+"," + String.valueOf(totalSimPrice)+"," +
					String.valueOf(totalSimProfit)+"," + String.valueOf(totalCard)+"," + String.valueOf(totalCardPrice)+"," +
					String.valueOf(totalCardProfit)+"," + String.valueOf(totalEasyLoad)+"," + String.valueOf(totalEasyLoadProfit)+"," +
					String.valueOf(totalEasyPaisa)+"," + String.valueOf(totalEasyPaisaProfit)+"," +
					String.valueOf(totalEasyPaisaReturn)+"," + String.valueOf(totalCash)+"," + "All Mobiles"+"," + String.valueOf(totalMobiles)+"," + 
					String.valueOf(totalMobilesPrice) +","+ String.valueOf(totalMobilesProfit)+"," + 
					String.valueOf(totalExpenses)+"," + String.valueOf(totalTotal)+"," + String.valueOf(totalProfit);
			dataForSheet.add(totalData);

		}

		else {

			for (int row = 0; row < record.size(); row++) {

				String rowData = record.get(row);
				String[] dataString = rowData.split(",");
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
				Date date = new Date();
				Date date1 = null;
				Date date2 = null;
				try {
					String stringDate1 = startDate;
					date1 = format.parse(stringDate1);
					String stringDate2 = endDate;
					date2 = format.parse(stringDate2);
					String stringDate = dataString[1];
					date = format.parse(stringDate);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				value = value.trim();
				dataString[2] = dataString[2].trim();

				if (value.equals("All")) {
					if (date.compareTo(date1) >= 0) {
						if (date.compareTo(date2) <= 0) {
							dataForSheet.add(rowData);
							calculateTotal(dataString);
							data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3], dataString[4],
									dataString[5], dataString[6], dataString[7], dataString[8], dataString[9], dataString[10],
									dataString[11], dataString[12], dataString[13], dataString[14], dataString[15], dataString[16],
									dataString[17] , dataString[18], dataString[19], dataString[20], dataString[21], dataString[22],
									dataString[23], dataString[24]));
						}
					}
				}

				else if (value.equals(dataString[2])) {
					if (date.compareTo(date1) >= 0) {
						if (date.compareTo(date2) <= 0) {
							dataForSheet.add(rowData);
							calculateTotal(dataString);
							data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3], dataString[4],
									dataString[5], dataString[6], dataString[7], dataString[8], dataString[9], dataString[10],
									dataString[11], dataString[12], dataString[13], dataString[14], dataString[15], dataString[16],
									dataString[17] , dataString[18], dataString[19], dataString[20], dataString[21], dataString[22], 
									dataString[23], dataString[24]));
						}
					}
				}
			}

			data.add(new Person("", "", "Total Calculations", String.valueOf(totalHLR), String.valueOf(totalHLRPrice),
					String.valueOf(totalHLRProfit), String.valueOf(totalSim), String.valueOf(totalSimPrice),
					String.valueOf(totalSimProfit), String.valueOf(totalCard), String.valueOf(totalCardPrice),
					String.valueOf(totalCardProfit), String.valueOf(totalEasyLoad), String.valueOf(totalEasyLoadProfit),
					String.valueOf(totalEasyPaisa), String.valueOf(totalEasyPaisaProfit), String.valueOf(totalHLR),
					String.valueOf(totalEasyPaisaReturn), String.valueOf(totalCash), "All Mobiles", 
					String.valueOf(totalMobilesPrice), String.valueOf(totalMobilesProfit), 
					String.valueOf(totalExpenses), String.valueOf(totalTotal), String.valueOf(totalProfit)));

			totalData = " ," + " ," + "Total Calculation," + String.valueOf(totalHLR)+"," + String.valueOf(totalHLRPrice)+"," +
					String.valueOf(totalHLRProfit)+"," + String.valueOf(totalSim)+"," + String.valueOf(totalSimPrice)+"," +
					String.valueOf(totalSimProfit)+"," + String.valueOf(totalCard)+"," + String.valueOf(totalCardPrice)+"," +
					String.valueOf(totalCardProfit)+"," + String.valueOf(totalEasyLoad)+"," + String.valueOf(totalEasyLoadProfit)+"," +
					String.valueOf(totalEasyPaisa)+"," + String.valueOf(totalEasyPaisaProfit)+"," +
					String.valueOf(totalEasyPaisaReturn)+"," + String.valueOf(totalCash)+"," + "All Mobiles"+"," + String.valueOf(totalMobiles)+"," + 
					String.valueOf(totalMobilesPrice) +","+ String.valueOf(totalMobilesProfit)+"," + 
					String.valueOf(totalExpenses)+"," + String.valueOf(totalTotal)+"," + String.valueOf(totalProfit);
			dataForSheet.add(totalData);

		}
	}

	public ArrayList<String> getDataForSheet(){

		return dataForSheet;
	}

	public void calculateTotal(String[] dataString){

		totalHLR += Double.parseDouble(dataString[3]);
		totalHLRPrice += Double.parseDouble(dataString[4]);
		totalHLRProfit += Double.parseDouble(dataString[5]);

		totalSim += Double.parseDouble(dataString[6]);
		totalSimPrice += Double.parseDouble(dataString[7]);
		totalSimProfit += Double.parseDouble(dataString[8]);

		totalCard += Double.parseDouble(dataString[9]);
		totalCardPrice += Double.parseDouble(dataString[10]);
		totalCardProfit += Double.parseDouble(dataString[11]);

		totalEasyLoad += Double.parseDouble(dataString[12]);
		totalEasyLoadProfit += Double.parseDouble(dataString[13]);

		totalEasyPaisa += Double.parseDouble(dataString[14]);
		totalEasyPaisaProfit += Double.parseDouble(dataString[15]);

		totalEasyPaisaReturn += Double.parseDouble(dataString[16]);
		totalCash += Double.parseDouble(dataString[17]);

		totalMobiles += Double.parseDouble(dataString[19]);
		String[] prices = dataString[20].split("-");
		for(int i=0; i<prices.length; i++){

			totalMobilesPrice += Double.parseDouble(prices[i]);					
		}
		totalMobilesProfit += Double.parseDouble(dataString[21]);
		totalExpenses += Double.parseDouble(dataString[22]);
		totalTotal += Double.parseDouble(dataString[23]);
		totalProfit += Double.parseDouble(dataString[24]);

	}

}