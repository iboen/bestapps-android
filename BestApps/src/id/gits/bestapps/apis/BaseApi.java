package id.gits.bestapps.apis;

import id.gits.bestapps.apis.OAuthApi.ApiResultListener;
import android.content.Context;

public class BaseApi {
	protected Context context;
	protected String errorMessage;
	public ApiResultListener resultListener;

	public BaseApi(Context ctx) {
		this.context = ctx;
	}
}
