package main.java.errors.message;

public class FieldIsEmptyMessage extends Message {
	public FieldIsEmptyMessage(String field) {
		super(String.format("%s cannot be empty.", field));
	}
}
