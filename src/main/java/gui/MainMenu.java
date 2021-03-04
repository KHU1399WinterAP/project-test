package main.java.gui;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {
	private JPanel mainPanel;
	private JPanel HeaderPanel;
	private JPanel BodyPanel;
	private JLabel HeaderLabel;
	private JButton registerButton;
	private JButton loginButton;
	private JButton exitButton;
	private JPanel footerPanel;
	
	public MainMenu() {
		super("Minesweeper | Main Menu");
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
	}
	
	private void initListeners() {
		initRegisterButtonListeners();
		initLoginButtonListeners();
		initExitButtonListeners();
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
	
	private void initExitButtonListeners() {
		exitButton.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
		exitButton.addActionListener(e -> System.out.println("Exiting ..."));
	}
}
