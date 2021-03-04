package main.java.gui;

import main.java.database.Database;
import main.java.models.User;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RegisterMenu extends JFrame {
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JButton mainMenuButton;
	private JPanel mainPanel;
	
	private final JFrame PREVIOUS_FRAME;
	
	public RegisterMenu(JFrame PREVIOUS_FRAME) {
		super("Minesweeper | Register Menu");
		
		this.PREVIOUS_FRAME = PREVIOUS_FRAME;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
	}
	
	private void initListeners() {
		initWindowListeners();
		initRegisterButtonListeners();
		initMainMenuButtonListeners();
	}
	
	private void initWindowListeners() {
		this.addWindowListener(
				new WindowListener() {
					@Override
					public void windowOpened(WindowEvent e) {
					
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						closeWindow();
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
					
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
					
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
					
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
					
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
					
					}
				}
		);
	}
	
	private void initRegisterButtonListeners() {
		registerButton.addActionListener(
				e -> {
					User user = new User(usernameTextField.getText(), passwordField.getPassword());
					System.out.println(user);
					
					Database.insertIntoUser(user);
					
					closeWindow();
				}
		);
	}
	
	private void initMainMenuButtonListeners() {
		mainMenuButton.addActionListener(e -> closeWindow());
	}
	
	private void closeWindow() {
		this.dispose();
		PREVIOUS_FRAME.setVisible(true);
	}
}
