package FrontEnd;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchEmployee extends Application{

	private GridPane gridPane;
	private Label nameLabel;
	@SuppressWarnings("rawtypes")
	private ComboBox inputName;
	private ArrayList<String> names;
	private Button searchButton;
	private HBox hBox;
	private VBox root;
	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private Button deleteButton = new Button("Delete");
	private ScrollPane scroll = new ScrollPane();
	private ScrollPane scrollPane;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(Stage primaryStage) throws Exception {

		root = new VBox();
		gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.TOP_RIGHT);

		nameLabel = new Label("                                 Name: ");
		gridPane.add(nameLabel, 0, 1);

		names = dataBase.getEmployeesNames();
		inputName = new ComboBox();
		inputName.setMinWidth(175);
		inputName.getItems().addAll(names);
		inputName.getItems().add("All");
		gridPane.add(inputName, 1, 1);

		searchButton = new Button("  Search  ");
		searchButton.setId("submitButton");

		hBox = new HBox();
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		hBox.getChildren().addAll(searchButton);
		hBox.setSpacing(10);
		gridPane.add(hBox, 3,1);
		gridPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setPadding(new Insets(10, 10, 40, 10));
		root.getChildren().add(gridPane);
		deleteButton.setMinSize(100, 30);

		searchButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {


				EmployeesRecord record = new EmployeesRecord();

				record.start((String) inputName.getSelectionModel().getSelectedItem());
				scroll = record.getScroll();

				root.getChildren().clear();

				root.getChildren().add(gridPane);
				root.getChildren().add(scroll);
				HBox hbox = new HBox();
				hbox.setAlignment(Pos.BOTTOM_RIGHT);
				hbox.getChildren().add(deleteButton);
				root.getChildren().add(hbox);
			}

		});

		searchButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.ENTER){
					EmployeesRecord record = new EmployeesRecord();

					record.start((String) inputName.getSelectionModel().getSelectedItem());
					scroll = record.getScroll();

					root.getChildren().clear();

					root.getChildren().add(gridPane);
					root.getChildren().add(scroll);
					HBox hbox = new HBox();
					hbox.setAlignment(Pos.BOTTOM_RIGHT);
					hbox.getChildren().add(deleteButton);
					root.getChildren().add(hbox);
				}
			}

		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				TableView table = (TableView)scroll.getContent();
				EmployeesRecord.Person person = (FrontEnd.EmployeesRecord.Person) table.getSelectionModel().getSelectedItem();
				if(person==null){
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select A Row Before Any Action");
				}
				else{

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogueResult =    JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);

					if(dialogueResult == JOptionPane.YES_OPTION){
						dataBase.deleteEmployee(person.getCNIC());
						searchButton.fire();
					}
				}
			}
		});
	}

	public ScrollPane getScrollPane(){
		scrollPane = new ScrollPane(root);
		return scrollPane;
	}

}
