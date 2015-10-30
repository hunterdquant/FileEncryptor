# FileEncryptor
The second encryption project for CS242 at Clarkson University.
Contains TextEncryptor as well as the ability to encrypt files.
Provides a GUI interface for file encryption.

Usage:
	  To run using the GUI interface.
	    - java GuiFileEncryptor
	  To run without any arguments. Will prompt the user for input.
		- java Encrypt
	  To bypass the prompt to encrypt a message.
	    - java Encrypt -e "<message>"
	  To Bypass the prompt to decrypt a message.
	    - java Encrypt -d "<message>" <key>
	  To bypass the prompt to encrypt a file.
	    - java Encrypt -fe "<input file>" "<output file>"
	  To Bypass the prompt to decrypt a file.
	    - java Encrypt -fd "<input file>" "<output file>" <key>
