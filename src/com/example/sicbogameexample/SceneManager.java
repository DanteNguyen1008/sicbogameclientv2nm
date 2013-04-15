package com.example.sicbogameexample;

import java.util.concurrent.ExecutionException;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import sicbo.components.GameComponent;

public class SceneManager {
	public enum SceneType {
		GAME, ANIMATION, HISTORY, HELP
	}

	private SceneType currentScene;
	private Scene splashScene;
	public BaseGameActivity activity;
	private Engine engine;
	private Camera camera;
	private ProgressDialog pd = null;
	private BitmapTextureAtlas splashTextureAtlas;
	private TextureRegion splashTextureRegion;
	public GameScene gameScene;
	// public AnimationScene animationScene;

	public SceneManager(BaseGameActivity activity, Engine engine, Camera camera) {
		this.activity = activity;
		this.engine = engine;
		this.camera = camera;
		GameEntity.getInstance().currentGame = new GameComponent();
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
	public void loadSplashSceneResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.DEFAULT);
		splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
		splashTextureAtlas.load();
	}
	public Scene createSplashScene() {
		//Create the Splash Scene and set background colour to red and add the splash logo.
		splashScene = new Scene();
		splashScene.setBackground(new Background(1, 0, 0));
		Sprite splash = new Sprite(0, 0, splashTextureRegion, activity.getVertexBufferObjectManager())
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) 
			{
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		splash.setScale(1.5f);
		splash.setPosition((camera.getWidth() - splash.getWidth()) * 0.5f, (camera.getHeight() - splash.getHeight()) * 0.5f);
		splashScene.attachChild(splash);
		
		return splashScene;
	}
	public void setScene(SceneType nextScene) {
		// Clear current scene
		switch (getCurrentScene()) {
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
