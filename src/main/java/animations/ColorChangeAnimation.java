package main.java.animations;

import main.java.config.AnimationConfig;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class ColorChangeAnimation extends Thread {
	private static final Lock LOCK = new ReentrantLock();
	
	private final Color ORIGINAL_COLOR;
	private final Color PULSE_COLOR;
	private final Consumer<Color> STEP_CALLBACK;
	private final Runnable END_CALLBACK;
	private final int HALF_DURATION;
	
	private int differenceRed;
	private int differenceGreen;
	private int differenceBlue;
	
	public ColorChangeAnimation(Color originalColor, Color pulseColor, Consumer<Color> stepCallback) {
		this(originalColor, pulseColor, stepCallback, null, AnimationConfig.ERROR_BACKGROUND_ANIMATION_DEFAULT_HALF_DURATION);
	}
	
	public ColorChangeAnimation(Color originalColor, Color pulseColor, Consumer<Color> stepCallback, Runnable endCallback) {
		this(originalColor, pulseColor, stepCallback, endCallback, AnimationConfig.ERROR_BACKGROUND_ANIMATION_DEFAULT_HALF_DURATION);
	}
	
	public ColorChangeAnimation(Color originalColor, Color pulseColor, Consumer<Color> stepCallback, Runnable endCallback, int halfDuration) {
		this.ORIGINAL_COLOR = originalColor;
		this.PULSE_COLOR = pulseColor;
		this.STEP_CALLBACK = stepCallback;
		this.END_CALLBACK = endCallback;
		this.HALF_DURATION = halfDuration;
	}
	
	@Override
	public void run() {
		differenceRed = PULSE_COLOR.getRed() - ORIGINAL_COLOR.getRed();
		differenceGreen = PULSE_COLOR.getGreen() - ORIGINAL_COLOR.getGreen();
		differenceBlue = PULSE_COLOR.getBlue() - ORIGINAL_COLOR.getBlue();
		
		changeColor(false);
		STEP_CALLBACK.accept(PULSE_COLOR);
		changeColor(true);
		
		END_CALLBACK.run();
	}
	
	private void changeColor(boolean reverse) {
		try {
			for (int i = 1; i <= HALF_DURATION; i++) {
				var percentage = 1.0 * i / HALF_DURATION;
				var red = (int) (percentage * differenceRed) * (reverse ? -1 : 1);
				var green = (int) (percentage * differenceGreen) * (reverse ? -1 : 1);
				var blue = (int) (percentage * differenceBlue) * (reverse ? -1 : 1);
				
				if (reverse) {
					red += PULSE_COLOR.getRed();
					green += PULSE_COLOR.getGreen();
					blue += PULSE_COLOR.getBlue();
				} else {
					red += ORIGINAL_COLOR.getRed();
					green += ORIGINAL_COLOR.getGreen();
					blue += ORIGINAL_COLOR.getBlue();
				}
				
				try {
					STEP_CALLBACK.accept(new Color(red, green, blue));
				} catch (IllegalArgumentException exception) {
					System.out.printf("rgb(%d, %d, %d)%n", red, green, blue);
				}
				
				sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
