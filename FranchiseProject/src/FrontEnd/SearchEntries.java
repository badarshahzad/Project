package FrontEnd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import FrontEnd.EntriesRecord.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchEntries{

	private GridPane gridPane;
	private Label nameLabel;
	@SuppressWarnings("rawtypes")
	private ComboBox inputName;
	private ArrayList<String> names;
	private Button searchButton;
	private HBox hBox;
	private VBox root;
	private DatePicker pickDate1;
	private DatePicker pickDate2;
	private Label startDate;
	private Label endDate;
	private Button deleteButton = new Button("Delete");
	private Button makeExcelSheet = new Button("Make Sheet");
	private HBox hBoxDeleteButton;
	private ScrollPane scroll;
	private RadioButton radioButton1 = new RadioButton("Search Specific Entries");
	private RadioButton radioButton2 = new RadioButton("Search All Entries");
	private ToggleGroup buttonsGroup = new ToggleGroup();
	private ScrollPane scrollPane; 

	private PlayWithDataBase dataBase = new PlayWithDataBase();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(){

		root = new VBox();
		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.TOP_RIGHT);
		radioButton1.setSelected(true);

		gridPane.add(radioButton1, 0, 1);
		gridPane.add(radioButton2, 1, 1);
		radioButton1.setToggleGroup(buttonsGroup);
		radioButton2.setToggleGroup(buttonsGroup);
		radioButton1.setStyle("-fx-font-weight: bold");
		radioButton2.setStyle("-fx-font-weight: bold");



		nameLabel = new Label("Name: ");
		gridPane.add(nameLabel, 0, 2);

		names = dataBase.getEmployeesNames();
		inputName = new ComboBox();
		inputName.setMinWidth(175);
		inputName.getItems().addAll(names);
		inputName.getItems().addAll("All");
		inputName.setMinWidth(195);
		gridPane.add(inputName, 1, 2);

		date();

		searchButton = new Button("  Search  ");
		searchButton.setId("submitButton");

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(searchButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 1, 5);
		gridPane.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(gridPane);

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TableView table = (TableView)scroll.getContent();
				EntriesRecord.Person person = (Person) table.getSelectionModel().getSelectedItem();
				if(person==null){
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select A Row Before Any Action");
				}
				else{

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogueResult =    JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);

					if(dialogueResult == JOptionPane.YES_OPTION){
						dataBase.deleteEntry(person.getSerNo());
						searchButton.fire();
					}
				}
			}
		});

		radioButton2.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if(event.getCode() == KeyCode.LEFT){
					date();
					gridPane.getChildren().remove(hBox);
					gridPane.add(hBox, 1, 5);
					if(root.getChildren().contains(scroll))
						root.getChildren().remove(scroll);
					if(root.getChildren().contains(hBoxDeleteButton))
						root.getChildren().remove(hBoxDeleteButton);
				}
			}
		});

		radioButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				gridPane.getChildren().remove(startDate);
				gridPane.getChildren().remove(endDate);
				gridPane.getChildren().remove(pickDate1);
				gridPane.getChildren().remove(pickDate2);
				gridPane.getChildren().remove(hBox);
				gridPane.add(hBox, 1, 3);
				if(root.getChildren().contains(scroll))
					root.getChildren().remove(scroll);
				if(root.getChildren().contains(hBoxDeleteButton))
					root.getChildren().remove(hBoxDeleteButton);
			}
		});

		radioButton1.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(event.getCode() == KeyCode.RIGHT){
					gridPane.getChildren().remove(startDate);
					gridPane.getChildren().remove(endDate);
					gridPane.getChildren().remove(pickDate1);
					gridPane.getChildren().remove(pickDate2);
					gridPane.getChildren().remove(hBox);
					gridPane.add(hBox, 1, 3);
					if(root.getChildren().contains(scroll))
						root.getChildren().remove(scroll);
					if(root.getChildren().contains(hBoxDeleteButton))
						root.getChildren().remove(hBoxDeleteButton);
				}
			}

		});
		radioButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				date();
				gridPane.getChildren().remove(hBox);
				gridPane.add(hBox, 1, 5);
				if(root.getChildren().contains(scroll))
					root.getChildren().remove(scroll);
				if(root.getChildren().contains(hBoxDeleteButton))
					root.getChildren().remove(hBoxDeleteButton);
			}
		});

		searchButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub

				if(event.getCode() == KeyCode.ENTER){

					if(validation()){
						String name = (String) inputName.getValue();

						EntriesRecord record = new EntriesRecord();
						if(radioButton1.isSelected())
							record.start(name, (String)pickDate1.getEditor().getText(), (String)pickDate2.getEditor().getText());
						else if(radioButton2.isSelected())
							record.start(name, "", "");

						scroll = record.scroll;

						root.getChildren().clear();

						root.getChildren().add(gridPane);
						root.getChildren().add(scroll);
						hBoxDeleteButton = new HBox();
						hBoxDeleteButton.setSpacing(5);
						hBoxDeleteButton.getChildren().add(makeExcelSheet);

						makeExcelSheet.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								ArrayList<String> dataForSheet = record.getDataForSheet();

								//								for(int i=0; i<dataForSheet.size(); i++){

								Filing filing = new Filing();
								filing.start(dataForSheet, (String) inputName.getSelectionModel().getSelectedItem());
								//								}
							}

						});

						hBoxDeleteButton.getChildren().add(deleteButton);
						hBoxDeleteButton.setAlignment(Pos.BOTTOM_RIGHT);
						root.getChildren().add(hBoxDeleteButton);

					}
					else
						JOptionPane.showMessageDialog(null, "Sorry!\nThe given input is wrong");
				}

			}
		});
		searchButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {

				if(validation()){
					String name = (String) inputName.getValue();

					EntriesRecord record = new EntriesRecord();
					if(radioButton1.isSelected())
						record.start(name, (String)pickDate1.getEditor().getText(), (String)pickDate2.getEditor().getText());
					else if(radioButton2.isSelected())
						record.start(name, "", "");

					scroll = record.scroll;

					root.getChildren().clear();

					root.getChildren().add(gridPane);
					root.getChildren().add(scroll);
					hBoxDeleteButton = new HBox();
					hBoxDeleteButton.setSpacing(5);
					hBoxDeleteButton.getChildren().add(makeExcelSheet);

					makeExcelSheet.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							// TODO Auto-generated method stub
							ArrayList<String> dataForSheet = record.getDataForSheet();

							//							for(int i=0; i<dataForSheet.size(); i++){

							Filing filing = new Filing();
							filing.start(dataForSheet, (String) inputName.getSelectionModel().getSelectedItem());
							//							}
						}

					});

					hBoxDeleteButton.getChildren().add(deleteButton);
					hBoxDeleteButton.setAlignment(Pos.BOTTOM_RIGHT);
					root.getChildren().add(hBoxDeleteButton);

				}
				else
					JOptionPane.showMessageDialog(null, "Sorry!\nThe given input is wrong");
			}

		});

		searchButton.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.ENTER){
					if(validation()){
						String name = (String) inputName.getValue();

						EntriesRecord record = new EntriesRecord();
						if(radioButton1.isSelected())
							record.start(name, (String)pickDate1.getEditor().getText(), (String)pickDate2.getEditor().getText());
						else if(radioButton2.isSelected())
							record.start(name, "", "");

						scroll = record.scroll;

						root.getChildren().clear();

						root.getChildren().add(gridPane);
						root.getChildren().add(scroll);
						hBoxDeleteButton = new HBox();
						hBoxDeleteButton.setSpacing(5);
						hBoxDeleteButton.getChildren().add(makeExcelSheet);

						makeExcelSheet.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								ArrayList<String> dataForSheet = record.getDataForSheet();

								//							for(int i=0; i<dataForSheet.size(); i++){

								Filing filing = new Filing();
								filing.start(dataForSheet, (String) inputName.getSelectionModel().getSelectedItem());
								//							}
							}

						});

						hBoxDeleteButton.getChildren().add(deleteButton);
						hBoxDeleteButton.setAlignment(Pos.BOTTOM_RIGHT);
						root.getChildren().add(hBoxDeleteButton);

					}
					else
						JOptionPane.showMessageDialog(null, "Sorry!\nThe given input is wrong");
				}
			}

		});
	}

	public boolean validation(){


		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Date date1 = null; Date date2 = null;
		if(radioButton1.isSelected()){
			if(pickDate1.getEditor().getText().equals("Select Start Date") || pickDate2.getEditor().getText().equals("Select End Date")){
				return false;
			}
		}
		if(radioButton1.isSelected()){
			if(pickDate1.getEditor().getText().equals("") || pickDate2.getEditor().getText().equals("")){
				return false;
			}
		}

		if(radioButton1.isSelected()){
			try {
				String stringDate1 = pickDate1.getEditor().getText();
				date1 = format.parse(stringDate1);
				String stringDate2 = pickDate2.getEditor().getText();
				date2 = format.parse(stringDate2);
				String stringDate = format.format(date);
				date = format.parse(stringDate);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(inputName.getSelectionModel().isEmpty())
			return false;

		if(radioButton1.isSelected()){
			if(pickDate1.getEditor().getText().isEmpty() || pickDate2.getEditor().getText().isEmpty()){
				return false;
			}
			if(date1.compareTo(date2)>0){
				return false;
			}

			if(date1.compareTo(date)>0 || date2.compareTo(date)>0){
				return false;
			}
		}

		return true;

	}

	public ScrollPane getScrollPane(){
		scrollPane = new ScrollPane(root);
		return scrollPane;
	}

	public void date() {

		pickDate1 = new DatePicker();
		pickDate1.getEditor().setText("Select Start Date");
		pickDate2 = new DatePicker();
		pickDate2.getEditor().setText("Select End Date");
		startDate = new Label("Start Date");
		endDate = new Label("End Date");

		gridPane.add(startDate, 0, 3);
		gridPane.add(pickDate1, 1, 3);
		gridPane.add(endDate, 0, 4);
		gridPane.add(pickDate2, 1, 4);
	}

}
