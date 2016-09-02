package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainControler implements Initializable {

	@FXML
	TreeView<String> treeview;
	Image icon = new Image(getClass().getResourceAsStream("/img/images.png"));

	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
		TreeItem<String> help = new TreeItem<>("Help", new ImageView(icon));
		TreeItem<String> AddEntry = new TreeItem<>("Add Entry", new ImageView(icon));
		TreeItem<String> AddMobile = new TreeItem<>("Add Mobile", new ImageView(icon));
		TreeItem<String> AddEmployee = new TreeItem<>("Add Employee", new ImageView(icon));
		TreeItem<String> ChangePassword = new TreeItem<>("Change Password", new ImageView(icon));
		TreeItem<String> ChangePrice = new TreeItem<>("Change Price", new ImageView(icon));
		TreeItem<String> EmployeesRecord = new TreeItem<>("Employees Record", new ImageView(icon));
		TreeItem<String> EntriesRecord = new TreeItem<>("Entries Record", new ImageView(icon));
		TreeItem<String> LogIn = new TreeItem<>("Log In", new ImageView(icon));
		TreeItem<String> MainWindow = new TreeItem<>("Main Window", new ImageView(icon));
		TreeItem<String> MobilesData = new TreeItem<>("Mobiles Data", new ImageView(icon));
		TreeItem<String> ModifyEntry = new TreeItem<>("Modify Entry", new ImageView(icon));
		TreeItem<String> SearchEntries = new TreeItem<>("Search Entries", new ImageView(icon));
		TreeItem<String> SearchEmployee = new TreeItem<>("Search Employee", new ImageView(icon));
		TreeItem<String> SearchMobiles = new TreeItem<>("Search Mobiles", new ImageView(icon));
		TreeItem<String> Stock = new TreeItem<>("Stock", new ImageView(icon));
		TreeItem<String> AboutSoftware = new TreeItem<>("About Software", new ImageView(icon));
		TreeItem<String> Contact = new TreeItem<>("Contact", new ImageView(icon));

		help.getChildren().addAll(AddEntry, AddMobile, AddEmployee, ChangePassword, ChangePrice, EmployeesRecord,
				EntriesRecord, LogIn, MainWindow, MobilesData, ModifyEntry, SearchEntries, SearchEmployee,
				SearchMobiles, Stock, AboutSoftware, Contact);
		help.setExpanded(true);

		treeview.setRoot(help);

		// help details when gui start ...
		area.setText("This is Help section in which we give you information about our Software");
		area.setEditable(false);
		imview.setImage(Help);
		
	}

	@FXML
	private ImageView imview = new ImageView();
	
	@FXML
	private Image Help= new Image(getClass().getResourceAsStream("/img/help.png"));
	private Image AddEntry = new Image(getClass().getResourceAsStream("/img/addEntry.png"));
	private Image AddMobile = new Image(getClass().getResourceAsStream("/img/addMobile.png"));
	private Image AddEmployee = new Image(getClass().getResourceAsStream("/img/addEmployee.png"));
	private Image ChangePassword = new Image(getClass().getResourceAsStream("/img/changePassword.png"));
	private Image ChangePrice = new Image(getClass().getResourceAsStream("/img/changeprice.png"));
	private Image EmployeesRecord = new Image(getClass().getResourceAsStream("/img/employeeRecord.png"));
	private Image LogIn = new Image(getClass().getResourceAsStream("/img/login.png"));
	private Image MainWindow = new Image(getClass().getResourceAsStream("/img/maingwindow.png"));
	private Image MobilesData = new Image(getClass().getResourceAsStream("/img/mobileRecord.png"));
	private Image ModifyEntry = new Image(getClass().getResourceAsStream("/img/modifyentry.png"));
	private Image SearchEntries = new Image(getClass().getResourceAsStream("/img/searchspecific.png"));
	private Image SearchEmployee = new Image(getClass().getResourceAsStream("/img/searchEmployee.png"));
	private Image SearchMobiles = new Image(getClass().getResourceAsStream("/img/searchMobile.png"));
	private Image Stock = new Image(getClass().getResourceAsStream("/img/stock.png"));
	private Image AboutSoftware = new Image(getClass().getResourceAsStream("/img/aboutSoftware.png"));
	private Image Contact = new Image(getClass().getResourceAsStream("/img/contactUs.png"));
	
	
	@FXML
	private String message;
	@FXML
	public TextArea area = new TextArea();
	@FXML
    public void handle(ActionEvent event) {
        MenuItem mItem = (MenuItem) event.getSource();
        String side = mItem.getText();
        if ("Help".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Help);
			
		}
        if ("Add Entry".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddEntry);
			
		}
		
        if ("Add Mobile".equalsIgnoreCase(side)) {
        	area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddMobile);
		}
        if ("Add Employee".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddEmployee);
		}
        if ("Change Password".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ChangePassword);
		}
        if ("Change Price".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ChangePrice);
		}
        if ("Employees Record".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(EmployeesRecord);
		}
        if ("Log In".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(LogIn);
		}
        if ("Main Window".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(MainWindow);
		}
        if ("Mobiles Data".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(MobilesData);
		}
        if ("Modify Entry".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ModifyEntry);
		}
        if ("Search Entries".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchEntries);
		}
        if ("Search Employee".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchEmployee);
		}
        if ("Search Mobiles".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchMobiles);
		}
        if ("Stock".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Stock);
		}
        if ("About Software".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AboutSoftware);
		}
        if ("Contact".equalsIgnoreCase(side)) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Contact);
		}

    }
	
	public void mousClick(MouseEvent mouseEvent) {
		

		TreeItem<String> item = treeview.getSelectionModel().getSelectedItem();

		if (item.getValue().equals("Help")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Help);
			
		}
		if (item.getValue().equals("Add Entry")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddEntry);
			
		}
		
		if (item.getValue().equals("Add Mobile")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddMobile);
		}
		if (item.getValue().equals("Add Employee")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AddEmployee);
		}
		if (item.getValue().equals("Change Password")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ChangePassword);
		}
		if (item.getValue().equals("Change Price")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ChangePrice);
		}
		if (item.getValue().equals("Employees Record")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(EmployeesRecord);
		}
		if (item.getValue().equals("LogIn")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(LogIn);
		}
		if (item.getValue().equals("Main Window")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(MainWindow);
		}
		if (item.getValue().equals("Mobiles Data")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(MobilesData);
		}
		if (item.getValue().equals("Modify Entry")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(ModifyEntry);
		}
		if (item.getValue().equals("Search Entries")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchEntries);
		}
		if (item.getValue().equals("Search Employee")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchEmployee);
		}
		if (item.getValue().equals("Search Mobiles")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(SearchMobiles);
		}
		if (item.getValue().equals("Stock")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Stock);
		}
		if (item.getValue().equals("About Software")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(AboutSoftware);
		}
		if (item.getValue().equals("Contact")) {
			area.setText("This is Help section in which we give you information about our Software");
			area.setEditable(false);
			imview.setImage(Contact);
		}

	}

}
