package kibow.games.casinohills.sicbo.components;

import kibow.games.casinohills.sicbo.screen.GameEntity;

import android.app.Activity;
import android.os.AsyncTask;

public class TimoutCheckAsyns extends AsyncTask<Object, Integer, String> {
	boolean isRunning = false;
	public long currentTime = 0;

	@Override
	protected void onPreExecute() {
		isRunning = true;
	}

	@Override
	protected String doInBackground(Object... params) {
		// TODO Auto-generated method stub
		Activity activity = (Activity) params[0];
		while (isRunning) {
			currentTime = System.currentTimeMillis();
			if (currentTime - GameEntity.getInstance().userComponent.actionTime > 5000) {
				isRunning = false;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(String param) {
		GameEntity.getInstance().exitGameTimeOut();
	}
}
