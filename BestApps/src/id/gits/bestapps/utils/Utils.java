/**
 * Copyright 2012 GITS Indonesia, PT.
 */
package id.gits.bestapps.utils;

import java.io.IOException;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;

/**
 * @author Ibnu Sina Wardy
 * 
 */
public class Utils {

	public static String postToHTTP(String url, String data, Context ctxForOAuth)
			throws Exception {
		String res = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json");

			httpPost.setEntity(new StringEntity(data));

			CommonsHttpOAuthConsumer consumer = new CommonsHttpOAuthConsumer(
					Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);

			String token, secret;
			token = SPManager.getString(ctxForOAuth,
					Constants.PREF.MY_ACCESS_TOKEN_KEY);
			secret = SPManager.getString(ctxForOAuth,
					Constants.PREF.MY_ACCESS_TOKEN_SECRET);
			consumer.setTokenWithSecret(token, secret);
			consumer.sign(httpPost);

			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			res = EntityUtils.toString(entity);
		} catch (Exception e) {
			if (e.getMessage() != null)
				throw new IOException(e.getMessage());
			else
				throw new IOException("Something happen, please try again");
		}
		return res;
	}
}
