package com.example.sicbogameexample;

import java.util.concurrent.ExecutionException;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import sicbo.components.GameComponent;

public class SceneManager {
	public enum SceneType {
		GAME, ANIMATION, HISTORY, HELP
	}

	private SceneType currentScene;
	private BaseGameActivity activity;
	private Engine engine;
	private Camera camera;
	private ProgressDialog pd = null;

	public GameScene gameScene;

	// public AnimationScene animationScene;

	public SceneManager(BaseGameActivity activity, Engine engine, Camera camera) {
		this.activity = activity;
		this.engine = engine;
		this.camera = camera;
		GameEntity.currentGame = new GameComponent();
		gameScene = new GameScene(engine, camera, activity);
	}

	public SceneType getCurrentScene() {
		return currentScene;
	}

	public void setCurrentScene(SceneType currentScene) {
		this.currentScene = currentScene;
	}

	public boolean setGameScene() {

		LoadingAsync loading = new LoadingAsync();
		try {
			return loading.execute().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void setScene(SceneType nextScene) {
		// Clear current scene
		switch (getCurrentScene()) {
		case ANIMATION:
			// animationScene.unLoadScene();
			break;
		case GAME:
			// May be clear all bet
			break;
		default:
			break;
		}

		setCurrentScene(nextScene);

		switch (nextScene) {
		case GAME:
			engine.setScene(gameScene.getScene());
			break;
		case ANIMATION:
			// animationScene.loadResource();
			// animationScene.loadScene();
			// animationScene.setScene(animationScene.getScene());

			break;
		case HISTORY:

			break;
		case HELP:

			break;
		default:
			break;
		}
	}

	class LoadingAsync extends AsyncTask<Object, String, Boolean> {
		@Override
		protected void onPreExecute() {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					pd = ProgressDialog.show(activity, "Loading data..",
							"Please wait....", true, false);
				}
			});

		}
		@Override
		protected Boolean doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			gameScene.loadResource();
			gameScene.loadScene();
			return true;
		}

		@Override
		protected void onPostExecute(Boolean _result) {
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					pd.dismiss();
				}
			});

		}
	}
}
