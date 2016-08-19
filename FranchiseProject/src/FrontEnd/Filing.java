package FrontEnd;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class Filing{
    
	public void start(ArrayList<String> dataForSheet, String fileName) {

        
        		Stage primaryStage = new Stage();
                DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = 
                        directoryChooser.showDialog(primaryStage);
                
                if(selectedDirectory == null){
                	
                	JOptionPane.showMessageDialog(null, "Please Select a directory to save file");
                }else{
                	
                	File filing = new File(selectedDirectory.getAbsolutePath()+"/"+fileName+" Record.csv");
            		try {
            			BufferedWriter writer = new BufferedWriter(new FileWriter(filing));
            			
            			for(int i=0; i<dataForSheet.size(); i++){
            				
            				writer.write(dataForSheet.get(i)+"\n");
            			}
            			writer.close();
            			
            		} catch (IOException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                }

    }
}