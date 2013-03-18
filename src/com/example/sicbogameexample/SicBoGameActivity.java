package com.example.sicbogameexample;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.BaseGameActivity;

import sicbo.components.UserComponent;

import android.view.KeyEvent;

import com.example.sicbogameexample.SceneManager.SceneType;

/**
 * @author Matim Development
 * @version 1.0.0 <br>
 * <br>
 *          https://sites.google.com/site/matimdevelopment/
 */
public class SicBoGameActivity extends BaseGameActivity {

	private Camera camera;
	public UserComponent userComponent;

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub

		camera = new Camera(0, 0, GameEntity.CAMERA_WIDTH,
				GameEntity.CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(),
				camera);
		//engineOptions.getTouchOptions().setNeedsMultiTouch(true);

		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		GameEntity.sceneManager = new SceneManager(this, mEngine, camera);
		if (GameEntity.sceneManager.setGameScene()) {

			pOnCreateResourcesCallback.onCreateResourcesFinished();
		} else {
			Exception ex = new Exception("Loading resource Error");
			ex.printStackTrace();
		}

	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		GameEntity.sceneManager.setCurrentScene(SceneType.GAME);
		pOnCreateSceneCallback
				.onCreateSceneFinished(GameEntity.sceneManager.gameScene
						.getScene());
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		GameEntity.sceneManager.gameScene.yesnoDialog
		.displayDialog(
				GameEntity.sceneManager.gameScene,
				"Do you want to exit?", 200, 300);
	}

}
