package tp.pr5.maploader.exceptions;

import java.io.IOException;

/**
 * @author repli
 * 
 */
public class WrongMapFormatException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public WrongMapFormatException() {
		super();

	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongMapFormatException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * @param message
	 */
	public WrongMapFormatException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public WrongMapFormatException(Throwable cause) {
		super(cause);

	}

}
