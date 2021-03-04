package main.java.gui;

import main.java.database.Database;
import main.java.models.User;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginMenu extends JFrame {
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton mainMenuButton;
	private JPanel mainPanel;
	
	private final JFrame PREVIOUS_FRAME;
	
	public LoginMenu(JFrame PREVIOUS_FRAME) {
		super("Minesweeper | Login Menu");
		
		this.PREVIOUS_FRAME = PREVIOUS_FRAME;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		
		initListeners();
	}
	
	private void initListeners() {
		initWindowListeners();
		initUsernameTextFieldListeners();
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
	
	private void initUsernameTextFieldListeners() {
		usernameTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				
				if (usernameTextField.getText().equals("Username"))
					usernameTextField.setText("");
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				super.focusLost(e);
				
				if (usernameTextField.getText().isEmpty())
					usernameTextField.setText("Username");
			}
		});
	}
	
	private void initRegisterButtonListeners() {
		loginButton.addActionListener(
				e -> {
					String username = usernameTextField.getText();
					String password = String.valueOf(passwordField.getPassword());
					
					User user = Database.getUserByUsername(username);
					
					if (user != null && user.password.equals(password)) {
						Dashboard dashboard = new Dashboard(PREVIOUS_FRAME);
						dashboard.setVisible(true);
						this.dispose();
					}
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
