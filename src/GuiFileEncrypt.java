/**
 * 
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * A graphical user interface for file encryption.
 */

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GuiFileEncrypt extends Application {

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		TextField inputKey = new TextField();
		Text message = new Text();
		Text outputKey = new Text(); 
			
		primaryStage.setTitle("GUI File Encryptor");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));		
		
		Button eButton = new Button("Encrypt");
		eButton.setOnAction( event -> {
			File inputFile = getInputFile();
			File outputFile = getOutputFile();
			if (inputFile != null && outputFile != null) {
				FileEncryptor fe = new FileEncryptor(inputFile, outputFile.getAbsolutePath());
				fe.textEncrypt();
				outputKey.setText("" + fe.getEncryptionKey());
				message.setText("Your message has been encrypted!");
			} else {
				message.setText("You failed to properly select files.");
			}
		});
		
		Button dButton = new Button("Decrypt");
		dButton.setOnAction( event -> {
			
			try {
				byte key = Byte.parseByte(inputKey.getText());
				File inputFile = getInputFile();
				File outputFile = getOutputFile();
				if (inputFile != null && outputFile != null) {
					FileEncryptor fe = new FileEncryptor(inputFile, outputFile.getAbsolutePath(), key);
					fe.textDecrypt();
					message.setText("Your message has been decrypted!");
				} else {
					message.setText("You failed to properly select files.");
				}
			} catch (NumberFormatException nfe) {
				message.setText("You entered an invalid key!");
			}
		});
		grid.add(eButton, 0, 0);
		grid.add(dButton, 0, 1);
		grid.add(outputKey, 2, 0);
		grid.add(inputKey, 2, 1);
		grid.add(message, 0, 2);
		grid.add(new Text("Key:"), 1, 0);
		grid.add(new Text("Key:"), 1, 1);
		GridPane.setColumnSpan(message, 3);
		
		Scene scene = new Scene(grid, 400, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public File getInputFile() {
		Stage stage = new Stage();
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select a file to be encrypted/decrypted.");
		File inputFile = chooser.showOpenDialog(stage);
		return inputFile;
	}
	
	public File getOutputFile() {
		Stage stage = new Stage();
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select a file to save the encrypted/decrypted output.");
		File inputFile = chooser.showSaveDialog(stage);
		return inputFile;
	}
}