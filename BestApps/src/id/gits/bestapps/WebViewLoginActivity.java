package id.gits.bestapps;

import id.gits.bestapps.apis.OAuthApi;
import id.gits.bestapps.apis.SendEmailApi;
import id.gits.bestapps.apis.SendSmsApi;
import id.gits.bestapps.apis.TMoneyApi;
import id.gits.bestapps.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewLoginActivity extends ActionBarActivity {

	ProgressDialog dialog;
	Context ctx = this;

	OAuthApi oAuthApi;
	SendSmsApi sendSmsApi;
	SendEmailApi sendEmailApi;
	TMoneyApi tMoneyApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_web);

		String data = getIntent().getStringExtra("data");
		WebView webView = (WebView) findViewById(R.id.webview);

		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new MyWebViewClient());

		try {
			String url = "http://sandbox.appprime.net/TelkomID_OAuth2/oauth/authorize?client_id="
					+ Constants.CONSUMER_KEY
					+ "&response_type=code&redirect_uri="
					+ URLEncoder.encode(Constants.ApiUrl.REDIRECT_TELKOMID,
							"utf-8") + "&scope=scope_read";
			webView.loadUrl(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Uri uri = Uri.parse(url);
			if (uri.toString().contains(Constants.ApiUrl.REDIRECT_TELKOMID)) {
				// ambil code
				String code = uri.getQueryParameter("code");
				Toast.makeText(ctx, code, Toast.LENGTH_LONG).show();
				// TODO process next oauth step
				finish();
			}
			return false;
		}
	}

	public static void startThisActivity(Context ctx) {
		Intent intent = new Intent(ctx, WebViewLoginActivity.class);
		// intent.putExtra("data", data);
		ctx.startActivity(intent);
	}
}
