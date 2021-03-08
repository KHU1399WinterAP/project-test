package main.java.gui;

import main.java.animations.ColorChangeAnimation;
import main.java.config.LanguageConfig;
import main.java.database.Database;
import main.java.enums.LanguageKey;
import main.java.models.User;
import main.java.utils.GuiUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;
import java.util.function.Consumer;

import static main.java.config.GuiConfig.*;

public class LoginMenu extends JFrame {
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton mainMenuButton;
	private JPanel mainPanel;
	private JPanel usernamePanel;
	private JPanel passwordPanel;
	private JPanel buttonsPanel;
	
	private final JFrame MAIN_MENU_FRAME;
	
	public LoginMenu(JFrame mainMenuFrame) {
		super("Minesweeper | Login Menu");
		
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
		
		GuiUtils.makeMenuButton(loginButton);
		GuiUtils.makeMenuButton(mainMenuButton);
	}
	
	private void initListeners() {
		initWindowListeners();
		initLoginButtonListeners();
		initMainMenuButtonListeners();
	}
	
	private void initWindowListeners() {
		GuiUtils.addWindowClosingListener(this, e -> closeWindow());
	}
	
	private void initLoginButtonListeners() {
		loginButton.addActionListener(
				e -> {
					String username = usernameTextField.getText();
					String password = String.valueOf(passwordField.getPassword());
					
					if (!username.isBlank()) {
						User user = Database.getUserByUsername(username);
						
						if (user != null && user.password.equals(password)) {
							Dashboard dashboard = new Dashboard(MAIN_MENU_FRAME);
							dashboard.setVisible(true);
							
							this.dispose();
							return;
						}
					}
					
					runMainPanelBackgroundColorAnimation();
//					runLoginButtonBackgroundColorAnimation();
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
	
	private void runLoginButtonBackgroundColorAnimation() {
		Consumer<Color> stepCallback = (color) -> loginButton.setBackground(color);
		
		Runnable endCallback = () -> {
			loginButton.setBackground(COLOR_PRIMARY);
			loginButton.setForeground(COLOR_GRAY_10);
			loginButton.setText(LanguageConfig.getWord("login"));
		};
		
		loginButton.setForeground(COLOR_DANGER);
		loginButton.setText(LanguageConfig.getWord("error"));
		
		new ColorChangeAnimation(COLOR_GRAY_98, COLOR_DANGER, stepCallback, endCallback).start();
	}
	
	private void closeWindow() {
		this.dispose();
		MAIN_MENU_FRAME.setVisible(true);
	}
}
