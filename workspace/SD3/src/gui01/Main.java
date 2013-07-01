package gui01;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {		
		JDialog.setDefaultLookAndFeelDecorated(true);
		MyGUI gameGui = new MyGUI();
		gameGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameGui.setVisible(true);
		gameGui.updateScreen();		
	}	
}