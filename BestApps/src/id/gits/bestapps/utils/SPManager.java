package id.gits.bestapps.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SPManager {
	public static void saveString(Context activity, String key, String value) {
		Log.d("SPMANAGER", "saving " + value + " for key " + key);
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(activity);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		if (editor.commit()) {
			// Log.d("SPMANAGER", "commited " + value);
		} else {
			// Log.d("SPMANAGER", "not commited");
		}
	}

	public static void saveInt(Context activity, String key, int value) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(activity);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static String getString(Context activity, String key) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(activity);
		String latihan = sharedPreferences.getString(key, null);
		return latihan;
	}

	public static int getInt(Context activity, String key) {
		SharedPreferences sharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(activity);
		int latihan = sharedPreferences.getInt(key, 0);
		return latihan;
	}

}
