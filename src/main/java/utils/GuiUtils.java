package main.java.utils;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatRoundBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.function.Consumer;

public class GuiUtils {
	public static void init() {
		FlatLightLaf.install();
		UIManager.put("Component.arc", 100);
	}
	
	public static void makeMenuButton(JButton button) {
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		setRoundCorners(button);
	}
	
	public static void setRoundCorners(JButton button) {
		button.setBorder(new FlatRoundBorder());
	}
	
	public static void addWindowClosingListener(JFrame frame, Consumer<WindowEvent> callback) {
		frame.addWindowListener(
				new EmptyWindowListener() {
					@Override
					public void windowClosing(WindowEvent e) {
						callback.accept(e);
					}
				}
		);
	}
}

class EmptyWindowListener implements WindowListener {
	@Override
	public void windowOpened(WindowEvent e) {
	
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
	
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
