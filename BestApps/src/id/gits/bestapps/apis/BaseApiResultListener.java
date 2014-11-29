package id.gits.bestapps.apis;

public interface BaseApiResultListener {
	public void onApiPreCall();

	public void onApiResultError(String errorMessage);
}
