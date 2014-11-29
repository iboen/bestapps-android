package id.gits.bestapps.daos;

public class BaseApiDao {
	public BaseApiDao() {
	}

	public BaseApiDao(String status, String message) {
		this.error = "ERROR";
		this.message = message;
	}

	public BaseApiDao(String status, String error, String message) {
		this.error = error;
		this.message = message;
	}

	private String error;
	private String message;

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return error;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.error = description;
	}

}
