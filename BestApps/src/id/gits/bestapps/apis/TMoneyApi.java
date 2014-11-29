package id.gits.bestapps.apis;

import id.gits.bestapps.apis.OAuthApi.ApiResultListener;
import id.gits.bestapps.utils.Constants;
import id.gits.bestapps.utils.Utils;
import android.content.Context;
import android.os.AsyncTask;

public class TMoneyApi extends BaseApi {
	private String URL;

	private TheAsyncTask theAsyncTask;

	private String data;

	public TMoneyApi(Context ctx, ApiResultListener resListener) {
		super(ctx);
		this.resultListener = resListener;
	}

	public void callApi(String data) {
		URL = Constants.ApiUrl.TMONEY_URL;
		this.data = data;
		if (theAsyncTask != null)
			theAsyncTask.cancel(true);
		theAsyncTask = new TheAsyncTask();
		theAsyncTask.execute();
	}

	private class TheAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected void onPreExecute() {
			resultListener.onApiPreCall();
		}

		@Override
		protected String doInBackground(String... params) {

			String res;
			try {
				res = Utils.postToHTTP(URL, data, context);
			} catch (Exception e) {
				return null;
			}
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				resultListener.onApiResultOk(result);
			} else
				resultListener.onApiResultError("Error");
		}
	}
}
