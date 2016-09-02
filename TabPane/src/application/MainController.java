package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainController {

	@FXML
	private ScrollPane scrollPane;
	@FXML
	private  HBox subTabPaneAppendData;
	//	@FXML
	//	private  HBox subTabPaneRecord;
	//	@FXML
	//	private  HBox subTabPaneSetting;


	@FXML
	private Pane appendDataTab;
	@FXML
	private Pane addEmployeeTab;
	@FXML
	private Pane addEntryTab;
	@FXML
	private Pane addMobileTab;
	@FXML
	private Pane addStockTab;


	@FXML
	private Pane recordTab;
	@FXML
	private Pane settingTab;
	@FXML
	private Pane searchTab;
	@FXML
	private Pane helpTab;

	@FXML
	private void initialize() {
		subTabPaneAppendData.setVisible(true);
		//		subTabPaneRecord.setVisible(false);
		//		subTabPaneSetting.setVisible(false);

		appendDataTab.setStyle("-fx-background-color:darkgray");
		addEntryTab.setStyle("-fx-background-color:darkgray");
		addEmployeeTab.setStyle("-fx-background-color:lightgray");
		addStockTab.setStyle("-fx-background-color:lightgray");
		addMobileTab.setStyle("-fx-background-color:lightgray");

		searchTab.setStyle("-fx-background-color:lightgray");
		settingTab.setStyle("-fx-background-color:lightgray");
		recordTab.setStyle("-fx-background-color:lightgray");
		helpTab.setStyle("-fx-background-color:lightgray");
		
		try {
			scrollPane.setContent(FXMLLoader.load(getClass().getResource("/application/AddEntryLayout.fxml")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void appendDataOnClicked() throws IOException {
		subTabPaneAppendData.setVisible(true);
		//		subTabPaneRecord.setVisible(false);
		//		subTabPaneSetting.setVisible(false);

		appendDataTab.setStyle("-fx-background-color:darkgray");
		searchTab.setStyle("-fx-background-color:lightgray");
		settingTab.setStyle("-fx-background-color:lightgray");
		recordTab.setStyle("-fx-background-color:lightgray");
		helpTab.setStyle("-fx-background-color:lightgray");
	}

	public void recordOnClicked() throws IOException {

		subTabPaneAppendData.setVisible(false);
		//		subTabPaneRecord.setVisible(true);
		//		subTabPaneSetting.setVisible(false);

		appendDataTab.setStyle("-fx-background-color:lightgray");
		searchTab.setStyle("-fx-background-color:lightgray");
		settingTab.setStyle("-fx-background-color:lightgray");
		recordTab.setStyle("-fx-background-color:darkgray");
		helpTab.setStyle("-fx-background-color:lightgray");
	}

	public void searchOnClicked() {
		subTabPaneAppendData.setVisible(false);
		//		subTabPaneRecord.setVisible(false);
		//		subTabPaneSetting.setVisible(false);

		appendDataTab.setStyle("-fx-background-color:lightgray");
		searchTab.setStyle("-fx-background-color:darkgray");
		settingTab.setStyle("-fx-background-color:lightgray");
		recordTab.setStyle("-fx-background-color:lightgray");
		helpTab.setStyle("-fx-background-color:lightgray");
	}

	public void settingOnClicked() {
		subTabPaneAppendData.setVisible(false);
		//		subTabPaneRecord.setVisible(false);
		//		subTabPaneSetting.setVisible(true);

		appendDataTab.setStyle("-fx-background-color:lightgray");
		searchTab.setStyle("-fx-background-color:lightgray");
		settingTab.setStyle("-fx-background-color:darkgray");
		recordTab.setStyle("-fx-background-color:lightgray");
		helpTab.setStyle("-fx-background-color:lightgray");
	}

	public void helpOnClicked() {
		subTabPaneAppendData.setVisible(false);
		//		subTabPaneRecord.setVisible(false);
		//		subTabPaneSetting.setVisible(false);

		appendDataTab.setStyle("-fx-background-color:lightgray");
		searchTab.setStyle("-fx-background-color:lightgray");
		settingTab.setStyle("-fx-background-color:lightgray");
		recordTab.setStyle("-fx-background-color:lightgray");
		helpTab.setStyle("-fx-background-color:darkgray");
	}

	@FXML
	public void addEntryOnClicked(){

		addEntryTab.setStyle("-fx-background-color:darkgray");
		addEmployeeTab.setStyle("-fx-background-color:lightgray");
		addStockTab.setStyle("-fx-background-color:lightgray");
		addMobileTab.setStyle("-fx-background-color:lightgray");
	}

	@FXML
	public void addEmployeeOnClicked(){

		addEntryTab.setStyle("-fx-background-color:lightgray");
		addEmployeeTab.setStyle("-fx-background-color:darkgray");
		addStockTab.setStyle("-fx-background-color:lightgray");
		addMobileTab.setStyle("-fx-background-color:lightgray");
	}

	@FXML
	public void addMobileOnClicked(){

		addEntryTab.setStyle("-fx-background-color:lightgray");
		addEmployeeTab.setStyle("-fx-background-color:lightgray");
		addStockTab.setStyle("-fx-background-color:lightgray");
		addMobileTab.setStyle("-fx-background-color:darkgray");
	}

	@FXML
	public void addStockOnClicked(){

		addEntryTab.setStyle("-fx-background-color:lightgray");
		addEmployeeTab.setStyle("-fx-background-color:lightgray");
		addStockTab.setStyle("-fx-background-color:darkgray");
		addMobileTab.setStyle("-fx-background-color:lightgray");
	}


}
