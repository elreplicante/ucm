package tp.pr5.commands.exceptions;

public class CommandExecutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor without parameters (no message is given)
	 */
	public CommandExecutionException() {
		super();
	}

	/**
	 * The exception thrown is created with a problem message.
	 * 
	 * @param arg0
	 *            User-friendly string that explains the error.
	 */
	public CommandExecutionException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor to create the exception with a nested cause and an error
	 * message.
	 * 
	 * @param arg0
	 *            Nested exception
	 * @param arg1
	 *            Nested cause
	 */
	public CommandExecutionException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Constructor to create the exception with a nested cause.
	 * 
	 * @param arg0
	 *            Nested exception
	 */
	public CommandExecutionException(Throwable arg0) {
		super(arg0);
	}

}
