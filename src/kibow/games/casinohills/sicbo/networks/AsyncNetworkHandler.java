package kibow.games.casinohills.sicbo.networks;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.app.Activity;
import android.os.AsyncTask;

public class AsyncNetworkHandler extends AsyncTask<Object, String, Integer> {
	ConnectionHandler connectionHandler;
	Activity activity;
	IOnNetworkHandle dstNetworkHandler;

	@Override
	protected void onPreExecute() {
		
	}

	@Override
	protected Integer doInBackground(Object... params) {
		// TODO Auto-generated method stub
		connectionHandler = (ConnectionHandler) params[0];
		activity = (Activity) params[1];
		dstNetworkHandler = (IOnNetworkHandle) params[5];
		try {
			connectionHandler.requestToServer((String) params[2],
					(ArrayList<String>) params[3],
					(ArrayList<Object>) params[4]);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Integer value) {
		try {
			dstNetworkHandler.onNetwokrHandle(connectionHandler.getResult(),
					connectionHandler, activity);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dstNetworkHandler.onNetworkError();
		}
	}
}
