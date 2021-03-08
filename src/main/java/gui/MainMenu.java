package main.java.gui;

import main.java.utils.GuiUtils;

import javax.swing.*;

public class MainMenu extends JFrame {
	private JPanel mainPanel;
	private JPanel rightPanel;
	private JButton registerButton;
	private JButton loginButton;
	private JPanel leftPanel;
	
	public MainMenu() {
		super("Minesweeper | Main Menu");
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		pack();
		setLocationRelativeTo(null);
		
		initComponentProperties();
		initListeners();
	}
	
	private void initComponentProperties() {
		GuiUtils.makeMenuButton(registerButton);
		GuiUtils.makeMenuButton(loginButton);
	}
	
	private void initListeners() {
		initRegisterButtonListeners();
		initLoginButtonListeners();
	}
	
	private void initRegisterButtonListeners() {
		registerButton.addActionListener(
				e -> {
					RegisterMenu registerMenu = new RegisterMenu(this);
					this.setVisible(false);
					registerMenu.setVisible(true);
				}
		);
	}
	
	private void initLoginButtonListeners() {
		loginButton.addActionListener(
				e -> {
					LoginMenu loginMenu = new LoginMenu(this);
					this.setVisible(false);
					loginMenu.setVisible(true);
				}
		);
	}
}
