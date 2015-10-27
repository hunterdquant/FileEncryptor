import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEncryptor extends Encryptor{
	
	private File inputFile;
	private String outputFileName;
	
	public FileEncryptor(File inputFile, String outputFileName) {
		this.inputFile = inputFile;
		this.outputFileName =  outputFileName;
	}
	
	public FileEncryptor(File inputFile, String outputFileName, byte key) {
		this.inputFile = inputFile;
		this.outputFileName =  outputFileName;
		setEncryptionKey(key);
	}
	
	public void textEncrypt() {
		try {
			BufferedReader in = new BufferedReader(
								new FileReader(inputFile.getAbsolutePath()));
			BufferedWriter out = new BufferedWriter(
								 new FileWriter(outputFileName));
			setEncryptionKey((byte)(inputFile.length()%128));
			int c;
			while ((c = in.read()) != -1) {
				out.write((c+4)^getEncryptionKey());
			}
			in.close();
			out.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Your file could not be found.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public void textDecrypt() {
		try {
			BufferedReader in = new BufferedReader(
								new FileReader(inputFile.getAbsolutePath()));
			BufferedWriter out = new BufferedWriter(
								 new FileWriter(outputFileName));
			int c;
			while ((c = in.read()) != -1) {
				out.write((c ^ getEncryptionKey()) - 4);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Your file could not be found.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public String toString() {
		return "FileEncryptor";
	}
}
