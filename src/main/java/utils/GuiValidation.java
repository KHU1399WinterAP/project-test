package main.java.utils;

import main.java.errors.GuiError;
import org.jetbrains.annotations.NotNull;

import static main.java.config.ValidationConfig.*;

public class GuiValidation {
	private static final Field USERNAME = new Field(USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH, GuiError.USERNAME_IS_EMPTY, GuiError.USERNAME_HAS_INVALID_LENGTH);
	private static final Field PASSWORD = new Field(PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH, GuiError.PASSWORD_IS_EMPTY, GuiError.PASSWORD_HAS_INVALID_LENGTH);
	
	public static GuiError validateUsername(String value) {
		return validateField(value, USERNAME);
	}
	
	public static GuiError validatePassword(String value) {
		return validateField(value, PASSWORD);
	}
	
	private static GuiError validateField(String value, Field field) {
		if (checkEmpty(value))
			return field.fieldIsEmptyError;
		
		if (checkInvalidLength(value, field.minimumLength, field.maximumLength))
			return field.fieldHasInvalidLength;
		
		return null;
	}
	
	private static boolean checkEmpty(String value) {
		return value == null || value.isEmpty();
	}
	
	private static boolean checkInvalidLength(@NotNull String value, int minimumLength, int maximumLength) {
		return minimumLength > value.length() || value.length() > maximumLength;
	}
}

class Field {
	public int minimumLength;
	public int maximumLength;
	public GuiError fieldIsEmptyError;
	public GuiError fieldHasInvalidLength;
	
	public Field(int minimumLength, int maximumLength, GuiError fieldIsEmptyError, GuiError fieldHasInvalidLength) {
		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;
		this.fieldIsEmptyError = fieldIsEmptyError;
		this.fieldHasInvalidLength = fieldHasInvalidLength;
	}
}
