package id.gits.bestapps;

import id.gits.bestapps.apis.OAuthApi;
import id.gits.bestapps.apis.SendEmailApi;
import id.gits.bestapps.apis.SendSmsApi;
import id.gits.bestapps.apis.TMoneyApi;
import id.gits.bestapps.apis.UPointApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	ProgressDialog dialog;
	Context ctx = this;

	OAuthApi oAuthApi;
	SendSmsApi sendSmsApi;
	SendEmailApi sendEmailApi;
	TMoneyApi tMoneyApi;
	UPointApi uPointApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_accesstoken).setOnClickListener(this);
		findViewById(R.id.btn_sendsms).setOnClickListener(this);
		findViewById(R.id.btn_email).setOnClickListener(this);
		findViewById(R.id.btn_tmoney).setOnClickListener(this);
		findViewById(R.id.btn_upoint).setOnClickListener(this);
		findViewById(R.id.btn_telkomid).setOnClickListener(this);

		sendSmsApi = new SendSmsApi(ctx, oauthApiListener);
		sendEmailApi = new SendEmailApi(ctx, oauthApiListener);
		oAuthApi = new OAuthApi(this, oauthApiListener);
		tMoneyApi = new TMoneyApi(this, tmoneyApiListener);
		uPointApi = new UPointApi(this, oauthApiListener);
	}

	OAuthApi.ApiResultListener oauthApiListener = new OAuthApi.ApiResultListener() {

		@Override
		public void onApiResultError(String errorMessage) {
			dialog.cancel();
			Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onApiPreCall() {
			dialog = ProgressDialog.show(ctx, null, "Zzzzzz");
		}

		@Override
		public void onApiResultOk(String result) {
			dialog.cancel();
			Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
		}
	};

	OAuthApi.ApiResultListener tmoneyApiListener = new OAuthApi.ApiResultListener() {

		@Override
		public void onApiResultError(String errorMessage) {
			dialog.cancel();
			Toast.makeText(ctx, errorMessage, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onApiPreCall() {
			dialog = ProgressDialog.show(ctx, null, "Zzzzzz");
		}

		@Override
		public void onApiResultOk(String result) {
			dialog.cancel();

			WebViewActivity.startThisActivity(ctx, result);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_accesstoken:
			oAuthApi.callApi();
			break;
		case R.id.btn_sendsms:
			sendSmsApi
					.callApi("{\"sendSMS\":{\"pinRequestID\":\"1\",\"pinDestAddress\":\"62813205997444\",\"pinMessageBody\":\"tes send sms\",\"pinShortCode\":\"9147\"}}");
			break;
		case R.id.btn_email:
			sendEmailApi
					.callApi("{\"sendEmail\":{\"to\":\"punyaibun@gmail.com\",\"subject\":\"TEST\",\"content\":\"Ibun Keren\"}}");
			break;
		case R.id.btn_tmoney:
			tMoneyApi
					.callApi("{\"tmoney\":{\"invoiceNo\":\"DEL41143493\",\"serviceID\":\"016\",\"amount\":\"500\",\"returnURL\":\"http://devocsg.telkom.co.id:8001/wsSDP-1.0/sdp/xresponse\",\"merchantCode\":\"195158400621\"}}");
			break;
		case R.id.btn_upoint:
			uPointApi
					.callApi("{\"upoint\":{\"trxID\":\"20141007180300\",\"amount\":\"500\",\"item\":\"1\",\"callbackURL\":\"http://google.com\",\"phoneNumber\":\"081266522242\"}}");
			break;
		case R.id.btn_telkomid:
			WebViewLoginActivity.startThisActivity(ctx);
			break;

		default:
			break;
		}
	}
}
