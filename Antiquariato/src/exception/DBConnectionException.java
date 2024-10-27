package exception;

public class DBConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7572798746955882991L;

	public DBConnectionException() {}
	
	public DBConnectionException(String msg) {
		super(msg); 
	}
}