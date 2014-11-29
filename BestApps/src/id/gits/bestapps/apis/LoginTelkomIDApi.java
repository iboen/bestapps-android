package id.gits.bestapps.apis;

import id.gits.bestapps.utils.Constants;
import id.gits.bestapps.utils.SPManager;

import java.io.UnsupportedEncodingException;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import android.content.Context;
import android.os.AsyncTask;

public class LoginTelkomIDApi extends BaseApi {
	private TheAsyncTask theAsyncTask;

	private ApiResultListener resultListener;

	private OAuthConsumer mConsumer;
	private OAuthProvider mProvider;

	// private String mCallbackUrl = "http://gits.co.id";

	private void init() throws UnsupportedEncodingException {
		mConsumer = new CommonsHttpOAuthConsumer(Constants.CONSUMER_KEY,
				Constants.CONSUMER_SECRET);
		mProvider = new CommonsHttpOAuthProvider(
				Constants.ApiUrl.REQUESTTOKEN_URL,
				Constants.ApiUrl.ACCESSTOKEN_URL, "");
		mProvider.setOAuth10a(true);
		// mCallbackUrl = (mCallbackUrl == null ? OAuth.OUT_OF_BAND :
		// mCallbackUrl);
	}

	/**
	 * Listener for API result
	 */
	public interface ApiResultListener extends BaseApiResultListener {
		public void onApiResultOk(String result);
	}

	public LoginTelkomIDApi(Context ctx, ApiResultListener resListener) {
		super(ctx);
		this.resultListener = resListener;
	}

	public void callApi() {
		if (theAsyncTask != null)
			theAsyncTask.cancel(true);
		theAsyncTask = new TheAsyncTask();
		theAsyncTask.execute();
	}

	class TheAsyncTask extends AsyncTask<Void, Void, String> {
		@Override
		protected void onPreExecute() {
			resultListener.onApiPreCall();

		}

		private String getRequestToken() throws OAuthMessageSignerException,
				OAuthNotAuthorizedException, OAuthExpectationFailedException,
				OAuthCommunicationException {
			String authUrl = mProvider.retrieveRequestToken(mConsumer,
					OAuth.OUT_OF_BAND);
			return authUrl;
		}

		private void getAccessToken() throws OAuthMessageSignerException,
				OAuthNotAuthorizedException, OAuthExpectationFailedException,
				OAuthCommunicationException {
			mProvider.retrieveAccessToken(mConsumer, "1");
		}

		@Override
		protected String doInBackground(Void... params) {
			String token = null;
			try {
				init();
				getRequestToken();
				getAccessToken();

				// save token
				SPManager.saveString(context,
						Constants.PREF.MY_ACCESS_TOKEN_KEY,
						mConsumer.getToken());
				SPManager.saveString(context,
						Constants.PREF.MY_ACCESS_TOKEN_SECRET,
						mConsumer.getTokenSecret());
				token = mConsumer.getToken() + " - "
						+ mConsumer.getTokenSecret();
			} catch (final Exception e) {
				e.printStackTrace();
				errorMessage = e.getMessage();
			}
			return token;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				resultListener.onApiResultOk(result);
			} else {
				resultListener.onApiResultError(errorMessage);
			}
		}
	}

}
