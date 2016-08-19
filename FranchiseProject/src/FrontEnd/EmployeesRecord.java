package FrontEnd;

import java.util.ArrayList;

import BusinessLogic.PlayWithDataBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EmployeesRecord{

	private ScrollPane scroll;
	private Label label;
	@SuppressWarnings("rawtypes")
	private TableColumn nameCol;
	private TableView<Person> table;
	@SuppressWarnings("rawtypes")
	private TableColumn CNICCol;
	@SuppressWarnings("rawtypes")
	private TableColumn phoneNumberCol;
	@SuppressWarnings("rawtypes")
	private TableColumn statusCol;
	private PlayWithDataBase dataBase = new PlayWithDataBase();

	private VBox vbox;

	private final ObservableList<Person> data = FXCollections.observableArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// @Override
	public void start(String name){

		scroll = new ScrollPane(vbox);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);


		populateObservableArray(name);

		label = new Label("Employee Record");
		label.setFont(new Font("Arial", 30));

		table = new TableView<>();
		table.setEditable(true);


		nameCol = new TableColumn("Name");
		nameCol.setMinWidth(150);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		CNICCol = new TableColumn("CNIC Number");
		CNICCol.setMinWidth(150);
		CNICCol.setCellValueFactory(new PropertyValueFactory<>("CNIC"));

		phoneNumberCol = new TableColumn("Phone Number");
		phoneNumberCol.setMinWidth(150);
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		statusCol = new TableColumn("Status");
		statusCol.setMinWidth(50);
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

		table.setItems(data);
		table.getColumns().addAll(nameCol, CNICCol, phoneNumberCol, statusCol);
		
		table.setPrefSize(650, 600);
		scroll.setContent(table);

	}

	public static class Person {

		private final SimpleStringProperty name;
		private final SimpleStringProperty CNIC;
		private final SimpleStringProperty phoneNumber;
		private final SimpleStringProperty status;

		private Person(String name, String CNIC, String phoneNumber, String status) {

			this.name = new SimpleStringProperty(name);
			this.CNIC = new SimpleStringProperty(CNIC);
			this.phoneNumber = new SimpleStringProperty(phoneNumber);
			this.status = new SimpleStringProperty(status);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String Name) {
			name.set(Name);
		}

		public String getCNIC() {
			return CNIC.get();
		}

		public void setCNIC(String CNIC) {
			this.CNIC.set(CNIC);
		}

		public String getPhoneNumber() {
			return phoneNumber.get();
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber.set(phoneNumber);
		}

		public String getStatus() {
			return status.get();
		}

		public void setStatus(String status) {
			this.status.set(status);
		}
	}
	
	public ScrollPane getScroll(){
		return scroll;
	}

	@SuppressWarnings("unchecked")
	public void populateObservableArray(String name) {

		ArrayList<String> record = dataBase.getEmployeeRecord();
		
			for (int row = 0; row < record.size(); row++) {

				String rowData = record.get(row);
				String[] dataString = rowData.split(",");
				if(name.equals("All"))
					data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3]));
				else if(dataString[0].equals(name))
					data.add(new Person(dataString[0], dataString[1], dataString[2], dataString[3]));

			}
	}
	
}