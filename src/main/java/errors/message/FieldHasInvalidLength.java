package main.java.errors.message;

public class FieldHasInvalidLength extends Message {
	public FieldHasInvalidLength(String field, int minimumLength, int maximumLength) {
		super(String.format("%s length has to be from %d to %d", field, minimumLength, maximumLength));
	}
}
