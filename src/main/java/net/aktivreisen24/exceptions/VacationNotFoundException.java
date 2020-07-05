package net.aktivreisen24.exceptions;

public class VacationNotFoundException extends RuntimeException {

	public VacationNotFoundException() {
		super();
	}

	public VacationNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public VacationNotFoundException(final String message) {
		super(message);
	}

	public VacationNotFoundException(final Throwable cause) {
		super(cause);
	}
}
