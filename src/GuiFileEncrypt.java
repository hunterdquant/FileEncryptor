/**
 * 
 * @author Hunter Quant <quanthd@clarkson.edu>
 *
 * A graphical user interface for file encryption.
 */

import javafx.geometry.Insets;
import java.io.File;

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

	TextField inputKey = new TextField();
	Text message = new Text();
	Text outputKey = new Text(); 
		
	@Override
	public void start(Stage primaryStage) {
		
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
			FileEncryptor fe = new FileEncryptor(inputFile, outputFile.getAbsolutePath());
			fe.textEncrypt();
			outputKey.setText("" + fe.getEncryptionKey());
			message.setText("Your message has been encrypted!");
		});
		
		Button dButton = new Button("Decrypt");
		dButton.setOnAction( event -> {
			
			try {
				byte key = Byte.parseByte(inputKey.getText());
				File inputFile = getInputFile();
				File outputFile = getOutputFile();
				FileEncryptor fe = new FileEncryptor(inputFile, outputFile.getAbsolutePath(), key);
				fe.textDecrypt();
				message.setText("Your message has been decrypted!");
			} catch (NumberFormatException nfe) {
				message.setText("You entered an invalid key!");
			}
		});
		grid.add(eButton, 0, 0);
		grid.add(dButton, 0, 1);
		grid.add(outputKey, 2, 0);
		grid.add(inputKey, 1, 1);
		grid.add(message, 0, 2);
		grid.add(new Text("Key: "), 1, 0);
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
		File inputFile = chooser.showOpenDialog(stage);
		return inputFile;
	}
}