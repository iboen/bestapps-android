package id.gits.bestapps.utils;

public class Constants {
	public static String STATUS_ERROR = "500";
	public static String STATUS_SUCCESS = "200";

	// TODO ganti dengan masing-masing
	public static String CONSUMER_KEY = "bestapp";
	// TODO ganti dengan masing-masing
	public static String CONSUMER_SECRET = "bestapp";

	public class ApiUrl {
		// TODO ganti dengan masing-masing
		public final static String REDIRECT_TELKOMID = "http://test.com";
		public final static String BASE_URL = "http://sandbox.appprime.net/TemanDev/rest/";
		// public final static String BASE_URL =
		// "http://118.98.97.126/TemanDev/rest/";

		public final static String REQUESTTOKEN_URL = BASE_URL
				+ "RequestToken/";
		public final static String ACCESSTOKEN_URL = BASE_URL + "AccessToken/";

		public final static String SENDSMS_URL = BASE_URL + "sendSMS/";
		public final static String SENDEMAIL_URL = BASE_URL + "sendEmail/";
		public final static String TMONEY_URL = BASE_URL + "tMoney/";
		public final static String UPOINT_TMONEY_URL = BASE_URL
				+ "uPointTmoney/";
	}

	public static class PREF {
		// public final static String MY_REQUEST_TOKEN = "my_request_token";
		public final static String MY_ACCESS_TOKEN_KEY = "my_token_secret_key";
		public final static String MY_ACCESS_TOKEN_SECRET = "my_token_secret_secret";
	}
}
