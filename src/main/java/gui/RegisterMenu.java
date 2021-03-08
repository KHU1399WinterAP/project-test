package main.java.gui;

import main.java.animations.ColorChangeAnimation;
import main.java.database.Database;
import main.java.errors.GuiError;
import main.java.models.User;
import main.java.utils.GuiUtils;
import main.java.utils.GuiValidation;

import javax.swing.*;

import java.awt.*;
import java.util.function.Consumer;

import static main.java.config.GuiConfig.COLOR_DANGER;
import static main.java.config.GuiConfig.COLOR_GRAY_10;

public class RegisterMenu extends JFrame {
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JButton mainMenuButton;
	private JPanel mainPanel;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	private JPanel buttonsPanel;
	private JLabel usernameErrorLabel;
	private JLabel passwordErrorLabel;
	
	private final JFrame MAIN_MENU_FRAME;
	
	public RegisterMenu(JFrame mainMenuFrame) {
		super("Minesweeper | Register Menu");
		
		this.MAIN_MENU_FRAME = mainMenuFrame;
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		
		initComponentProperties();
		initListeners();
	}
	
	private void initComponentProperties() {
		usernameTextField.setBorder(null);
		passwordField.setBorder(null);
		
		GuiUtils.makeMenuButton(registerButton);
		GuiUtils.makeMenuButton(mainMenuButton);
	}
	
	private void initListeners() {
		initWindowListeners();
		initRegisterButtonListeners();
		initMainMenuButtonListeners();
	}
	
	private void initWindowListeners() {
		GuiUtils.addWindowClosingListener(this, e -> closeWindow());
	}
	
	private void initRegisterButtonListeners() {
		registerButton.addActionListener(
				e -> {
					var username = usernameTextField.getText();
					var password = String.valueOf(passwordField.getPassword());
					
					var usernameError = GuiValidation.validateUsername(username);
					var passwordError = GuiValidation.validatePassword(password);
					
					if (usernameError == null && passwordError == null) {
						Database.insertIntoUser(new User(username, password));
						closeWindow();
					} else {
						runMainPanelBackgroundColorAnimation();
						updateErrorLabel(usernameError, usernameErrorLabel);
						updateErrorLabel(passwordError, passwordErrorLabel);
					}
				}
		);
	}
	
	private void initMainMenuButtonListeners() {
		mainMenuButton.addActionListener(e -> closeWindow());
	}
	
	private void runMainPanelBackgroundColorAnimation() {
		Consumer<Color> stepCallback = (color) -> mainPanel.setBackground(color);
		Runnable endCallback = () -> mainPanel.setBackground(COLOR_GRAY_10);
		new ColorChangeAnimation(mainPanel.getBackground(), COLOR_DANGER, stepCallback, endCallback).start();
	}
	
	private void updateErrorLabel(GuiError error, JLabel errorLabel) {
		if (error != null)
			errorLabel.setText(error.getMessage());
		else
			errorLabel.setText("");
	}
	
	private void closeWindow() {
		this.dispose();
		MAIN_MENU_FRAME.setVisible(true);
	}
}
