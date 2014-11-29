package id.gits.bestapps.daos;

public class TokenUser {
	private String key;
	private String secret;

	/**
	 * @return the token
	 */
	public String getToken() {
		return key;
	}

	/**
	 * @return the token_secret
	 */
	public String getToken_secret() {
		return secret;
	}

}