package main.java.app;

import main.java.config.LanguageConfig;
import main.java.gui.MainMenu;
import main.java.utils.GuiUtils;

public class Main {
	public static void main(String[] args) {
		init();
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.setVisible(true);
	}
	
	private static void init() {
		GuiUtils.init();
		LanguageConfig.init();
	}
}
