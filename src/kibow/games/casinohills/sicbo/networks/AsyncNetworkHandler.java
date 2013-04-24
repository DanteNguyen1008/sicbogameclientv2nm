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
		/*
		 * sceneManager.gameScene.getActivity().runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub pd =
		 * ProgressDialog.show( sceneManager.gameScene.getActivity(),
		 * "Loading data..", "Please wait....", true, false); } });
		 */
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
			/*
			 * activity.runOnUiThread(new Runnable() {
			 * 
			 * @Override public void run() { // TODO Auto-generated method stub
			 * // displayConfirmDialog("Connection time out, data corrupted", //
			 * 170, 200); Toast.makeText(activity,
			 * "Connection time out, data corrupted!",
			 * Toast.LENGTH_LONG).show(); sceneManager.gameScene.unLoadScene();
			 * sceneManager = null; activity.finish(); } });
			 */
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
			/*
			 * sceneManager.gameScene.getActivity().runOnUiThread( new
			 * Runnable() {
			 * 
			 * @Override public void run() { // TODO Auto-generated method stub
			 * pd.dismiss(); } });
			 */
			// {"response":{"task_title":"res_bet",
			// "data":{"success":true,"result":{"dice":[6,3,6],"win":["big"],
			// "point":{"win":2,"current":11200}}},"status":true}}
			// dataList = connectionHandler.parseData(responseName);
			dstNetworkHandler.onNetwokrHandle(connectionHandler.getResult(),
					connectionHandler, activity);
			/*
			 * JSONObject result = connectionHandler.getResult(); if
			 * (connectionHandler.getTaskID().equals("res_bet")) { // move to
			 * animation scene if (result.getBoolean("success")) {
			 * onReceiveStartGame(result); } else { Log.d("Bet error",
			 * "Something wrong???"); }
			 * 
			 * } else if (connectionHandler.getTaskID().equals(
			 * "res_view_history")) { onReceiveViewHistory(result, activity);
			 * 
			 * } else if (connectionHandler.getTaskID().equals("res_log_out")) {
			 * onReceiveSignout(); } else if
			 * (connectionHandler.getTaskID().equals( "res_session_expire")) {
			 * onSessionExpire(); }
			 */

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
