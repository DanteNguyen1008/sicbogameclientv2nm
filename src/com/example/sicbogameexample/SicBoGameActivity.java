package com.example.sicbogameexample;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.ui.activity.BaseGameActivity;

import sicbo.components.ShakeEventListener;
import sicbo.components.UserComponent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.example.sicbogameexample.SceneManager.SceneType;

/**
 * @author Matim Development
 * @version 1.0.0 <br>
 * <br>
 *          https://sites.google.com/site/matimdevelopment/
 */
public class SicBoGameActivity extends BaseGameActivity implements IOnSceneTouchListener{

	private Camera camera;
	public UserComponent userComponent;
	
	// shake phone object

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//createProgressDialog();
		GameEntity.getInstance().mSensorListener = new ShakeEventListener(this);
		
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub

		camera = new Camera(0, 0, GameEntity.CAMERA_WIDTH,
				GameEntity.CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(),
				camera);
		//engineOptions.getTouchOptions().setNeedsMultiTouch(true);
		engineOptions.getAudioOptions().setNeedsMusic(true);
		engineOptions.getAudioOptions().setNeedsSound(true);
		return engineOptions;
	}

	@Override
	protected void onResume() {
		super.onResume();
		GameEntity.getInstance().mSensorListener.registerShake();
		if (GameEntity.getInstance().sceneManager != null) {
			if (!GameEntity.getInstance().sceneManager.gameScene.backgroundMusic.music
					.isReleased()) {
				GameEntity.getInstance().sceneManager.gameScene.backgroundMusic
						.resume();
			} else {

			}
		}
	}

	@Override
	protected void onPause() {
		super.onStop();
		GameEntity.getInstance().mSensorListener.stopRegisterShake();
		if (GameEntity.getInstance().sceneManager != null)
			if (!GameEntity.getInstance().sceneManager.gameScene.backgroundMusic.music
					.isReleased())
				GameEntity.getInstance().sceneManager.gameScene.backgroundMusic
						.pause();

	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		//createProgressDialog();
		// TODO Auto-generated method stub
		
		//BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		GameEntity.getInstance().sceneManager = new SceneManager(this, mEngine,
				camera);
		GameEntity.getInstance().sceneManager.loadSplashSceneResources();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
		/*if (GameEntity.getInstance().sceneManager.setGameScene()) {
         
			pOnCreateResourcesCallback.onCreateResourcesFinished();
			
		
		} else {
			Exception ex = new Exception("Loading resource Error");
			ex.printStackTrace();
		}*/

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		//GameEntity.getInstance().sceneManager.setCurrentScene(SceneType.GAME);
		pOnCreateSceneCallback
				.onCreateSceneFinished(GameEntity.getInstance().sceneManager.createSplashScene()
						);
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		mEngine.registerUpdateHandler(new TimerHandler(0.5f, new ITimerCallback() 
		{
			public void onTimePassed(final TimerHandler pTimerHandler) 
			{   
				mEngine.unregisterUpdateHandler(pTimerHandler);
				GameEntity.getInstance().sceneManager.gameScene.loadResource();
				GameEntity.getInstance().sceneManager.gameScene.loadScene();
				GameEntity.getInstance().sceneManager.setCurrentScene(SceneType.GAME);
				GameEntity.getInstance().sceneManager.setScene(SceneType.GAME);

				
				
				GameEntity.getInstance().sceneManager.gameScene.backgroundMusic.play();
				GameEntity.getInstance().userComponent.actionTime = System
						.currentTimeMillis();
				
			}
		}));

		
		// GameEntity.getInstance().checkUserTimeout();
		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			onBackPressed();
		} else if (keyCode == KeyEvent.KEYCODE_MENU) {
			if (!GameEntity.getInstance().isAnimationRunning
					&& !GameEntity.getInstance().isBackPress) {
				GameEntity.getInstance().sceneManager.gameScene.displayMenu();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		if (!GameEntity.getInstance().isMenuDisplay && !GameEntity.getInstance().isAnimationRunning) {
			GameEntity.getInstance().sceneManager.gameScene.onBackButtonPress(true);
		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		return false;
	}

	

}

