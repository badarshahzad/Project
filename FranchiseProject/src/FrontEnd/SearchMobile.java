package FrontEnd;


import javax.swing.JOptionPane;

import BusinessLogic.PlayWithDataBase;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchMobile{

	private ScrollPane scroll;
	private PlayWithDataBase dataBase = new PlayWithDataBase();
	private Button deleteButton = new Button("Delete");
	private VBox vbox = new VBox();
	private Button searchButton = new Button("Search");
	MobilesData mobilesData = new MobilesData();
	private HBox hBox;
	@SuppressWarnings("rawtypes")
	private ComboBox mobileNames;


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(String value){

		scroll = new ScrollPane();
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);

		mobileNames = new ComboBox();
		mobileNames.getItems().addAll(dataBase.getMobileNames());
		mobileNames.getItems().add("All");

		searchButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode() == KeyCode.ENTER){

					if(mobileNames.getSelectionModel().isEmpty())
						JOptionPane.showMessageDialog(null, "Please select a mobile");
					else{

						MobilesData mobilesData = new MobilesData();
						mobilesData.start((String) mobileNames.getSelectionModel().getSelectedItem());

						if(vbox.getChildren().contains(scroll))
							vbox.getChildren().remove(scroll);

						scroll = mobilesData.getScrollPane();
						vbox.getChildren().add(scroll);

						HBox hbox = new HBox();
						hbox.setAlignment(Pos.BOTTOM_RIGHT);
						hbox.getChildren().add(deleteButton);
						vbox.getChildren().add(hbox);

					}
				}
			}
		});

		searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if(mobileNames.getSelectionModel().isEmpty())
					JOptionPane.showMessageDialog(null, "Please select a mobile");
				else{

					MobilesData mobilesData = new MobilesData();
					mobilesData.start((String) mobileNames.getSelectionModel().getSelectedItem());

					if(vbox.getChildren().contains(scroll))
						vbox.getChildren().remove(scroll);

					scroll = mobilesData.getScrollPane();
					vbox.getChildren().add(scroll);

					HBox hbox = new HBox();
					hbox.setAlignment(Pos.BOTTOM_RIGHT);
					hbox.getChildren().add(deleteButton);
					vbox.getChildren().add(hbox);

				}
			}
		});

		deleteButton.setMinSize(100, 30);
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				TableView table = (TableView)scroll.getContent();
				MobilesData.Mobile mobile = (FrontEnd.MobilesData.Mobile) table.getSelectionModel().getSelectedItem();
				if(mobile==null){
					JOptionPane.showMessageDialog(null, "Sorry!\nPlease Select A Row Before Any Action");
				}
				else{

					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogueResult =    JOptionPane.showConfirmDialog (null, "Are you Sure?","Warning",dialogButton);

					if(dialogueResult == JOptionPane.YES_OPTION){
						dataBase.deleteMobile(mobile.getName());
						searchButton.fire();
					}
				}
			}
		});

		hBox  = new HBox();
		mobileNames.setMinWidth(175);
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(20, 10, 50, 10));
		hBox.getChildren().addAll(new Label("Mobile Name: "),  mobileNames, searchButton);
		hBox.setAlignment(Pos.TOP_CENTER);
		vbox.getChildren().add(hBox);
	}



	public ScrollPane getScrollPane(){
		ScrollPane scrollPane = new ScrollPane(vbox);
		return scrollPane;
	}
}
