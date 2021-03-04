package main.java.app;

import com.formdev.flatlaf.FlatLightLaf;
import main.java.gui.MainMenu;

public class Main {
	public static void main(String[] args) {
		FlatLightLaf.install();
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
	}
}
