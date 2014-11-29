package id.gits.bestapps;

import id.gits.bestapps.apis.OAuthApi;
import id.gits.bestapps.apis.SendEmailApi;
import id.gits.bestapps.apis.SendSmsApi;
import id.gits.bestapps.apis.TMoneyApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

public class WebViewActivity extends ActionBarActivity {

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
		webView.loadDataWithBaseURL("http://gits.co.id", data, "text/html",
				"utf-8", null);
	}

	public static void startThisActivity(Context ctx, String data) {
		Intent intent = new Intent(ctx, WebViewActivity.class);
		intent.putExtra("data", data);
		ctx.startActivity(intent);
	}
}
