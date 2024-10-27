package exception;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 147916530351708750L;

	public DAOException() {}
	
	public DAOException(String msg) {
		super(msg);
	}
}