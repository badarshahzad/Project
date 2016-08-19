package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

public class MobilesData{

	private ScrollPane scroll;
	private Label label;
	@SuppressWarnings("rawtypes")
	private TableColumn nameCol;
	private TableView<Mobile> table;
	@SuppressWarnings("rawtypes")
	private TableColumn RSOPriceCol;
	@SuppressWarnings("rawtypes")
	private TableColumn realPriceCol;
	@SuppressWarnings("rawtypes")
	private TableColumn franchisePriceCol;
	private PlayWithDataBase dataBase = new PlayWithDataBase();


	private final ObservableList<Mobile> data = FXCollections.observableArrayList();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(String value){

		scroll = new ScrollPane();
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);


		populateObservableArray(value);

		label = new Label("Employee Record");
		label.setFont(new Font("Arial", 30));

		table = new TableView<>();
		table.setEditable(true);


		nameCol = new TableColumn("Name");
		nameCol.setMinWidth(150);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

		RSOPriceCol = new TableColumn("RSO Price");
		RSOPriceCol.setMinWidth(150);
		RSOPriceCol.setCellValueFactory(new PropertyValueFactory<>("RSOPrice"));

		realPriceCol = new TableColumn("Real Price");
		realPriceCol.setMinWidth(150);
		realPriceCol.setCellValueFactory(new PropertyValueFactory<>("realPrice"));

		franchisePriceCol = new TableColumn("Franchise Price");
		franchisePriceCol.setMinWidth(150);
		franchisePriceCol.setCellValueFactory(new PropertyValueFactory<>("franchisePrice"));

		table.setItems(data);
		table.getColumns().addAll(nameCol,realPriceCol, RSOPriceCol, franchisePriceCol);
		
		table.setPrefSize(650, 600);
		scroll.setContent(table);
	}
	
	@SuppressWarnings("rawtypes")
	public void deleteMobile(){
		

		TableView table = (TableView)scroll.getContent();
		Mobile mobile = (Mobile) table.getSelectionModel().getSelectedItem();
		if(mobile==null){
			JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select A Row Before Any Action");
		}
		else{

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogueResult =    JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);

			if(dialogueResult == JOptionPane.YES_OPTION){
				dataBase.deleteMobile(mobile.getName());
			}
		}
	}


	public static class Mobile {

		private final SimpleStringProperty name;
		private final SimpleStringProperty RSOPrice;
		private final SimpleStringProperty realPrice;
		private final SimpleStringProperty franchisePrice;

		private Mobile(String name, String realPrice, String RSOPrice, String franchisePrice) {

			this.name = new SimpleStringProperty(name);
			this.RSOPrice = new SimpleStringProperty(RSOPrice);
			this.realPrice = new SimpleStringProperty(realPrice);
			this.franchisePrice = new SimpleStringProperty(franchisePrice);
		}

		public String getName() {
			return name.get();
		}

		public void setName(String Name) {
			name.set(Name);
		}

		public String getRSOPrice() {
			return RSOPrice.get();
		}

		public void setRSOPrice(String RSOPrice) {
			this.RSOPrice.set(RSOPrice);
		}

		public String getRealPrice() {
			return realPrice.get();
		}

		public void setRealPrice(String realPrice) {
			this.realPrice.set(realPrice);
		}

		public String getFranchisePrice() {
			return franchisePrice.get();
		}

		public void setFranchisePrice(String franchisePrice) {
			this.franchisePrice.set(franchisePrice);
		}
	}

	public ScrollPane getScrollPane(){
		return scroll;
	}

	@SuppressWarnings("unchecked")
	public void populateObservableArray(String value) {

		ArrayList<String> record = dataBase.getMobilesRecord();

		for (int row = 0; row < record.size(); row++) {

			String rowData = record.get(row);
			String[] dataString = rowData.split(",");
			if(value.equals("All"))
				data.add(new Mobile(dataString[0], dataString[1], dataString[2], dataString[3]));
			else if(value.equals(dataString[0]))
				data.add(new Mobile(dataString[0], dataString[1], dataString[2], dataString[3]));
		}
	}

}