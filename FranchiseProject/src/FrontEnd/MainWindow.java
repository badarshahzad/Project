package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application{

	private TabPane tabPane, customerTabPane, settingTabPane, searchTabPane, recordTabPane;
	private Tab homeTab;
	private Tab customerTab,  customerTab1, customerTab2, customerTab3, customerTab4;
	private Tab recordTab, recordTab1, recordTab2, recordTab3;
	private Tab settingTab, settingTab1, settingTab2, settingTab3;
	private Tab helpTab;
	private Tab searchTab, searchTab1, searchTab2, searchTab3;
	private Tab logoutTab;
	private Button buttonLogIn;
	private Button changePasswordButton;
	private final Stage stage = new Stage();
	private Scene scene;
	private LogIn logIn = new LogIn();
	SingleSelectionModel<Tab> selection;
	String leftORRight = "";

	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(final Stage primaryStage){

		primaryStage.setTitle("Telenor Tawlk Shawlk");
		tabPane = new TabPane();
		tabPane.setId("tabPane");
		scene = new Scene(tabPane, 1400, 800);
		selection = tabPane.getSelectionModel();

		homeTab = new Tab();
		homeTab.setClosable(false);

		Image imageHome = new Image(getClass().getResourceAsStream("home.png"));
		Label labelHome = new Label("Log In", new ImageView(imageHome));
		homeTab.setGraphic(labelHome);

		customerTab = new Tab();
		customerTab.setClosable(false);
		customerTab.setId("tab");

		Image imageCus = new Image(getClass().getResourceAsStream("Append.png"));
		Label labelCus = new Label("Append Data", new ImageView(imageCus));
		customerTab.setGraphic(labelCus);


		customerTabPane = new TabPane();
		customerTab.setContent(customerTabPane);

		customerTab1 = new Tab();
		customerTab1.setClosable(false);
		customerTab1.setId("tab");

		Image imageCustomerTab1 = new Image(getClass().getResourceAsStream("addEntry.png"));
		Label labelCustomerTab1 = new Label("Add Entry", new ImageView(imageCustomerTab1));
		customerTab1.setGraphic(labelCustomerTab1);

		customerTab2 = new Tab();
		customerTab2.setClosable(false);

		Image imageCustomerTab2 = new Image(getClass().getResourceAsStream("addEmployee.png"));
		Label labelCustomerTab2 = new Label("Add Employee", new ImageView(imageCustomerTab2));
		customerTab2.setGraphic(labelCustomerTab2);

		customerTab3 = new Tab();
		customerTab3.setClosable(false);

		Image imageCustomerTab3 = new Image(getClass().getResourceAsStream("AddMobiles.png"));
		Label labelCustomerTab3 = new Label("Add Mobile", new ImageView(imageCustomerTab3));
		customerTab3.setGraphic(labelCustomerTab3);

		customerTab4 = new Tab();
		customerTab4.setClosable(false);

		Image imageCustomerTab4 = new Image(getClass().getResourceAsStream("Stock.png"));
		Label labelCustomerTab4 = new Label("  Stock  ", new ImageView(imageCustomerTab4));
		customerTab4.setGraphic(labelCustomerTab4);

		customerTabPane.getTabs().addAll(customerTab1, customerTab2, customerTab3, customerTab4);


		recordTab = new Tab("");
		recordTab.setClosable(false);

		recordTabPane = new TabPane();
		recordTab.setContent(recordTabPane);

		Image imageRecord = new Image(getClass().getResourceAsStream("record.png"));
		Label labelRecord = new Label("Record", new ImageView(imageRecord));
		recordTab.setGraphic(labelRecord);

		recordTab1 = new Tab();
		recordTab1.setClosable(false);

		Image imageRecordTab1 = new Image(getClass().getResourceAsStream("EmployeesRecord.png"));
		Label labelRecordTab1 = new Label("Entries Record", new ImageView(imageRecordTab1));
		recordTab1.setGraphic(labelRecordTab1);

		recordTab2 = new Tab();
		recordTab2.setClosable(false);

		Image imageRecordTab2 = new Image(getClass().getResourceAsStream("MobilesRecord.png"));
		Label labelRecordTab2 = new Label("Mobiles Record", new ImageView(imageRecordTab2));
		recordTab2.setGraphic(labelRecordTab2);

		recordTab3 = new Tab();
		recordTab3.setClosable(false);

		Image imageRecordTab3 = new Image(getClass().getResourceAsStream("EmployeeRecord.png"));
		Label labelRecordTab3 = new Label("Employees Record", new ImageView(imageRecordTab3));
		recordTab3.setGraphic(labelRecordTab3);

		recordTabPane.getTabs().addAll(recordTab1, recordTab2, recordTab3);

		//Setting Tab.................

		settingTab = new Tab();
		settingTab.setClosable(false);

		Image imageSetting = new Image(getClass().getResourceAsStream("setting.png"));
		Label labelSetting = new Label("Setting", new ImageView(imageSetting));
		settingTab.setGraphic(labelSetting);

		settingTabPane = new TabPane();
		settingTab.setContent(settingTabPane);

		settingTab1 = new Tab();
		settingTab1.setClosable(false);
		settingTabPane.getTabs().add(settingTab1);

		Image imageSettingPassword = new Image(getClass().getResourceAsStream("changePassword.png"));
		Label labelSettingPassword = new Label("Change Password", new ImageView(imageSettingPassword));
		settingTab1.setGraphic(labelSettingPassword);

		settingTab2 = new Tab();
		settingTab2.setClosable(false);
		settingTabPane.getTabs().add(settingTab2);

		Image imageModification = new Image(getClass().getResourceAsStream("modification.png"));
		Label labelModification = new Label("Modify Entry", new ImageView(imageModification));
		settingTab2.setGraphic(labelModification);

		settingTab3 = new Tab();
		settingTab3.setClosable(false);
		settingTabPane.getTabs().add(settingTab3);

		Image imagePrice = new Image(getClass().getResourceAsStream("price.png"));
		Label labelPrice = new Label("Change Price", new ImageView(imagePrice));
		settingTab3.setGraphic(labelPrice);

		//Setting Tab...............

		helpTab = new Tab();
		helpTab.setClosable(false);

		Image imageHelp = new Image(getClass().getResourceAsStream("Help.png"));
		Label labelHelp = new Label("Help", new ImageView(imageHelp));
		helpTab.setGraphic(labelHelp);

		searchTab = new Tab();
		searchTab.setClosable(false);

		Image imageSearch = new Image(getClass().getResourceAsStream("Search.png"));
		Label labelSearch = new Label("Search", new ImageView(imageSearch));
		searchTab.setGraphic(labelSearch);

		searchTabPane = new TabPane();

		searchTab1 = new Tab();
		searchTab1.setClosable(false);

		Image imageSearching1 = new Image(getClass().getResourceAsStream("SearchEntry.png"));
		Label labelSearching1 = new Label("Search Entry", new ImageView(imageSearching1));
		searchTab1.setGraphic(labelSearching1);

		searchTab2 = new Tab();
		searchTab2.setClosable(false);


		Image imageSearching2 = new Image(getClass().getResourceAsStream("SearchEmployee.png"));
		Label labelSearching2 = new Label("Search Employee", new ImageView(imageSearching2));
		searchTab2.setGraphic(labelSearching2);

		searchTab3 = new Tab();
		searchTab3.setClosable(false);


		Image imageSearching3 = new Image(getClass().getResourceAsStream("SearchMobile.png"));
		Label labelSearching3 = new Label("Search Mobiles", new ImageView(imageSearching3));
		searchTab3.setGraphic(labelSearching3);

		searchTab.setContent(searchTabPane);
		searchTabPane.getTabs().addAll(searchTab1, searchTab2, searchTab3);

		logoutTab = new Tab();
		logoutTab.setId("logout");
		logoutTab.setClosable(false);
		Image imageLogout = new Image(getClass().getResourceAsStream("logout.png"));
		Label labelLogout = new Label("Logout", new ImageView(imageLogout));
		logoutTab.setGraphic(labelLogout);

		tabPane.getTabs().addAll(homeTab, customerTab, recordTab, settingTab, searchTab, helpTab, logoutTab);
		tabPane.setSide(Side.TOP);


		try {
			logIn.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		homeTab.setContent(logIn.getScrollPane());
		tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(tabPane.getSelectionModel().getSelectedItem()==homeTab){

					if(event.getCode() == KeyCode.RIGHT){
						handleCustomerTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						logOutTab();
					}

				}

				else if(tabPane.getSelectionModel().getSelectedItem()==helpTab){

					if(event.getCode() == KeyCode.RIGHT){
						logOutTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						searchTab();
					}
				}

				else if(tabPane.getSelectionModel().getSelectedItem()==logoutTab){
					if(event.getCode() == KeyCode.RIGHT){
						homeTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						helpTab();
					}
				}

				else if(tabPane.getSelectionModel().getSelectedItem()==customerTab){
					if(event.getCode() == KeyCode.RIGHT){
						recordTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						homeTab();
					}
				}

				else if(tabPane.getSelectionModel().getSelectedItem()==recordTab){
					if(event.getCode() == KeyCode.RIGHT){
						settingTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						handleCustomerTab();
					}
				}

				else if(tabPane.getSelectionModel().getSelectedItem()==settingTab){
					if(event.getCode() == KeyCode.RIGHT){
						searchTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						recordTab();
					}
				}

				else if(tabPane.getSelectionModel().getSelectedItem()==searchTab){
					if(event.getCode() == KeyCode.RIGHT){
						helpTab();
					}
					else if(event.getCode() == KeyCode.LEFT){
						settingTab();
					}
				}
			}
		});

		tabPane.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event){
				// TODO Auto-generated method stub
			            
				if(tabPane.getSelectionModel().getSelectedItem()==homeTab){
					homeTab();
				}
				else if(tabPane.getSelectionModel().getSelectedItem()==customerTab){
					handleCustomerTab();
				}
				else if(tabPane.getSelectionModel().getSelectedItem()==recordTab){
					recordTab();
				}
				else if(tabPane.getSelectionModel().getSelectedItem()==settingTab){
					settingTab();
				}
				else if(tabPane.getSelectionModel().getSelectedItem()==searchTab){
					searchTab();
				}
			}
		});

		buttonLogIn = logIn.getButton();
		buttonLogIn.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode()==KeyCode.ENTER){
					signIn();
				}

			}
		});
		buttonLogIn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {

				signIn();
			}

		});

		//						customerTab.setDisable(true);
		//						recordTab.setDisable(true);
		//						settingTab.setDisable(true);
		//						searchTab.setDisable(true);
		//						helpTab.setDisable(true);
		//						logoutTab.setDisable(true);

		scene.getStylesheets().add(MainWindow.class.getResource("LogIn.css").toExternalForm());
		scene.getStylesheets().add(MainWindow.class.getResource("AddEmployee.css").toExternalForm());
		scene.getStylesheets().add(MainWindow.class.getResource("AddEntry.css").toExternalForm());
		scene.getStylesheets().add(MainWindow.class.getResource("EmployeeRecord.css").toExternalForm());
		scene.getStylesheets().add(MainWindow.class.getResource("Home.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void homeTab(){

	}	

	public void handleCustomerTab(){

		customerTab1.setContent(null);
		customerTab2.setContent(null);
		customerTab3.setContent(null);
		customerTab4.setContent(null);

		if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab1)
			handleCustomerTab1();
		else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab2)
			handleCustomerTab2();
		else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab3)
			handleCustomerTab3();
		else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab4)
			handleCustomerTab4();

		customerTabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab1){

					if(event.getCode() == KeyCode.RIGHT)
						handleCustomerTab2();
					else if(event.getCode() == KeyCode.LEFT)
						handleCustomerTab4();
				}

				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab2){

					if(event.getCode() == KeyCode.RIGHT)
						handleCustomerTab3();
					else if(event.getCode() == KeyCode.LEFT)
						handleCustomerTab1();
				}

				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab3){

					if(event.getCode() == KeyCode.RIGHT)
						handleCustomerTab4();
					else if(event.getCode() == KeyCode.LEFT)
						handleCustomerTab2();
				}

				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab4){

					if(event.getCode() == KeyCode.RIGHT)
						handleCustomerTab1();
					else if(event.getCode() == KeyCode.LEFT)
						handleCustomerTab3();

				}
				
			}

		});
		customerTabPane.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub

				if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab2){

					handleCustomerTab2();
				}
				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab1){

					handleCustomerTab1();
				}

				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab3){

					handleCustomerTab3();
				}

				else if(customerTabPane.getSelectionModel().getSelectedItem() == customerTab4){

					handleCustomerTab4();
				}
			}

		});

	}

			
	public void handleCustomerTab1(){
		customerTab2.setContent(null);
		customerTab3.setContent(null);
		customerTab4.setContent(null);

		if(customerTab1.getContent()==null){
			AddEntry addEntry = new AddEntry();
			try {
				addEntry.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ScrollPane scroll = new ScrollPane();
			scroll = addEntry.getScrollPane();
			customerTab1.setContent(scroll);
		}

	}

	public void handleCustomerTab2(){
		if(customerTab2.getContent()==null){

			customerTab1.setContent(null);
			customerTab3.setContent(null);
			customerTab4.setContent(null);

			AddEmployee addEmployee = new AddEmployee();
			try {
				addEmployee.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			customerTab2.setContent(addEmployee.getScroll());
		}
	}		

	public void handleCustomerTab3(){
		customerTab2.setContent(null);
		customerTab1.setContent(null);
		customerTab4.setContent(null);

		if(customerTab3.getContent()==null){
			AddMobiles addMobile = new AddMobiles();
			try {
				addMobile.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			customerTab3.setContent(addMobile.getScroll());
		}
	}

	public void handleCustomerTab4(){
		customerTab2.setContent(null);
		customerTab3.setContent(null);
		customerTab1.setContent(null);

		if(customerTab4.getContent()==null){
			Stock stock = new Stock();
			try {
				stock.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ScrollPane scroll = new ScrollPane();
			scroll = stock.getScrollPane();
			customerTab4.setContent(scroll);
		}
	}
	
	public void recordTab(){

		if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab1)
			recordTab1();
		else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab2)
			recordTab2();
		else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab3)
			recordTab3();

		recordTabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab1){

					if(event.getCode() == KeyCode.RIGHT)
						recordTab2();
					else if(event.getCode() == KeyCode.LEFT)
						recordTab3();

				}
				else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab2){

					if(event.getCode() == KeyCode.RIGHT)
						recordTab3();
					else if(event.getCode() == KeyCode.LEFT)
						recordTab1();

				}
				else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab3){

					if(event.getCode() == KeyCode.RIGHT)
						recordTab1();
					else if(event.getCode() == KeyCode.LEFT)
						recordTab2();
				}
			}

		});

		recordTabPane.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab1){
					recordTab1();
				}
				else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab2){
					recordTab2();
				}
				else if(recordTabPane.getSelectionModel().getSelectedItem() == recordTab3){
					recordTab3();
				}
			}

		});
	}

	public void recordTab1(){

		EntriesRecord record = new EntriesRecord();
		record.start("All", "All", "All");
		recordTab1.setContent(record.getScrollPane());

	}

	public void recordTab2(){
		MobilesData record = new MobilesData();
		record.start("All");
		recordTab2.setContent(record.getScrollPane());
	}

	public void recordTab3(){
		EmployeesRecord record = new EmployeesRecord();
		record.start("All");
		recordTab3.setContent(record.getScroll());
	}

	public void settingTab(){

		settingTab1.setContent(null);
		settingTab2.setContent(null);
		settingTab3.setContent(null);

		if(settingTabPane.getSelectionModel().getSelectedItem() == settingTab1)
			settingTab1();
		else if(settingTabPane.getSelectionModel().getSelectedItem() == settingTab2)
			settingTab2();
		else if(settingTabPane.getSelectionModel().getSelectedItem() == settingTab3)
			settingTab3();

		settingTabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab2){

					if(event.getCode() == KeyCode.RIGHT)
						settingTab3();
					else if(event.getCode() == KeyCode.LEFT)
						settingTab1();

				}
				else if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab1){

					if(event.getCode() == KeyCode.RIGHT)
						settingTab2();
					else if(event.getCode() == KeyCode.LEFT)
						settingTab3();
				}
				else if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab3){

					if(event.getCode() == KeyCode.RIGHT)
						settingTab1();
					else if(event.getCode() == KeyCode.LEFT)
						settingTab2();

				}

			}

		});

		settingTabPane.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab2){
					settingTab2();
				}
				else if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab1){
					settingTab1();
				}
				else if(settingTabPane.getSelectionModel().getSelectedItem()==settingTab3){
					settingTab3();
				}

			}

		});
	}

	public void settingTab1(){

		settingTab2.setContent(null);
		settingTab3.setContent(null);
		if(settingTab1.getContent()==null){
			ChangePassword changePassword = new ChangePassword();
			try {
				changePassword.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			settingTab1.setContent(changePassword.getScrollPane());

			changePasswordButton = changePassword.getButton();
			changePasswordButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@SuppressWarnings("unchecked")
				@Override
				public void handle(KeyEvent event) {

					if(event.getCode() == KeyCode.ENTER){

						PlayWithDataBase dataBase = new PlayWithDataBase();
						String userName = logIn.getUserNameField().getText();
						String password = changePassword.getPassword();
						String password1 = changePassword.getPassword1();
						String password2 = changePassword.getPassword2();

						ArrayList<String> dataList = dataBase.checkUserNameAndPassword();

						if(password1.equals(password2)){

							for(int i=0; i<dataList.size(); i++){

								String[] data = dataList.get(i).split(",");

								if(userName.equals(data[0])){

									if(password.equals(data[1])){

										dataBase.changePassword(userName, password1);
										JOptionPane.showMessageDialog(null, "Password has been changed");
										changePassword.setFieldsToNull();
									}
									else
										JOptionPane.showMessageDialog(null, "Sorry!\n Your password is wrong");
								}

							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Sorry!\nThe given passwords do not match");
						}
					}
				}
			});
			changePasswordButton.setOnAction(new EventHandler<ActionEvent>(){

				@SuppressWarnings("unchecked")
				@Override
				public void handle(ActionEvent arg0) {

					PlayWithDataBase dataBase = new PlayWithDataBase();
					String userName = logIn.getUserNameField().getText();
					String password = changePassword.getPassword();
					String password1 = changePassword.getPassword1();
					String password2 = changePassword.getPassword2();

					ArrayList<String> dataList = dataBase.checkUserNameAndPassword();

					if(password1.equals(password2)){

						for(int i=0; i<dataList.size(); i++){

							String[] data = dataList.get(i).split(",");

							if(userName.equals(data[0])){

								if(password.equals(data[1])){

									dataBase.changePassword(userName, password1);
									JOptionPane.showMessageDialog(null, "Password has been changed");
									changePassword.setFieldsToNull();
								}
								else
									JOptionPane.showMessageDialog(null, "Sorry!\n Your password is wrong");
							}

						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Sorry!\nThe given passwords do not match");
					}
				}
			});
		}
	}

	public void settingTab2(){

		settingTab1.setContent(null);
		settingTab3.setContent(null);

		if(settingTab2.getContent()==null){
			final ModifyEntry modifyEntry = new ModifyEntry(); 
			try {
				modifyEntry.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			settingTab2.setContent(modifyEntry.getScrollPane());
		}
	}

	public void settingTab3(){

		settingTab2.setContent(null);
		settingTab1.setContent(null);

		ChangePrice changePrice = new ChangePrice();
		try {
			changePrice.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		settingTab3.setContent(changePrice.getScroll());
	}

	public void searchTab(){


		searchTab1.setContent(null);
		searchTab2.setContent(null);
		searchTab3.setContent(null);

		if(searchTabPane.getSelectionModel().getSelectedItem() == searchTab1)
			searchTab1();
		else if(searchTabPane.getSelectionModel().getSelectedItem() == searchTab2)
			searchTab2();
		else if(searchTabPane.getSelectionModel().getSelectedItem() == searchTab3)
			searchTab3();

		searchTabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {


				if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab1){

					if(event.getCode() == KeyCode.RIGHT)
						searchTab2();
					else if(event.getCode() == KeyCode.LEFT)
						searchTab3();
				}

				else if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab2){

					if(event.getCode() == KeyCode.RIGHT)
						searchTab3();
					else if(event.getCode() == KeyCode.LEFT)
						searchTab1();
				}

				else if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab3){

					if(event.getCode() == KeyCode.RIGHT)
						searchTab1();
					else if(event.getCode() == KeyCode.LEFT)
						searchTab2();
				}
			}

		});

		searchTabPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {

				if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab1){
					searchTab1();
				}
				else if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab2){
					searchTab2();
				}
				else if(searchTabPane.getSelectionModel().getSelectedItem()==searchTab3){
					searchTab3();
				}
			}
		});
	}

	public void searchTab1(){
		searchTab2.setContent(null);
		if(searchTab1.getContent()==null){
			SearchEntries searchEntry = new SearchEntries();
			try {
				searchEntry.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searchTab1.setContent(searchEntry.getScrollPane());
		}
	}

	public void searchTab2(){
		searchTab1.setContent(null);
		if(searchTab2.getContent() == null){
			SearchEmployee searchEmployee = new SearchEmployee();
			try {
				searchEmployee.start(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			searchTab2.setContent(searchEmployee.getScrollPane());
		}

	}

	public void searchTab3(){

		SearchMobile searchMobile = new SearchMobile();
		searchMobile.start("");
		searchTab3.setContent(searchMobile.getScrollPane());

	}

	public void helpTab(){

	}

	public void logOutTab(){

		homeTab.setDisable(false);
		logoutTab.setDisable(true);
		settingTab.setDisable(true);
		helpTab.setDisable(true);
		recordTab.setDisable(true);
		searchTab.setDisable(true);
		customerTab.setDisable(true);

		logIn.setUserNmamedField();
		logIn.setUserPasswordField();

	}


	@SuppressWarnings("unchecked")
	public void signIn(){


		boolean login = false;
		PlayWithDataBase dataBase = new PlayWithDataBase();
		ArrayList<String> dataList = dataBase.checkUserNameAndPassword();
		String userName = logIn.getUserNameField().getText();
		String password = logIn.getUserPasswordField().getText();

		for(int i=0; i<dataList.size(); i++){

			String[] data = dataList.get(i).split(",");

			if(userName.equals(data[0])){

				if(password.equals(data[1])){
					login = true;
					customerTab.setDisable(false);
					recordTab.setDisable(false);
					settingTab.setDisable(false);
					searchTab.setDisable(false);
					helpTab.setDisable(false);
					logoutTab.setDisable(false);
					homeTab.setDisable(true);
				}
			}

		}

		if(login==false)
			JOptionPane.showMessageDialog(null, "Sorry!\nUser Name or Password in incorrect");
	}


	public void setSearchTab2(VBox root){
		searchTab2.setContent(root);
	}

}