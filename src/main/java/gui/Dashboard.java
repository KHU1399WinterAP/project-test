package main.java.gui;

import javax.swing.*;

public class Dashboard extends JFrame {
	private JPanel mainPanel;
	
	private final JFrame MAIN_MENU_FRAME;
	
	public Dashboard(JFrame mainMenu) {
		super("Minesweeper | Dashboard");
		
		this.MAIN_MENU_FRAME = mainMenu;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
	}
}
