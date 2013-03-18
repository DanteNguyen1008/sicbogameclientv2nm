package com.example.sicbogameexample;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;

import android.content.Intent;

public class SplashScreenAcivity extends BaseGameActivity implements
		ITimerCallback {

	private BitmapTextureAtlas splashTextureAtlas;
	private ITextureRegion splashTextureRegion;
	private Sprite splash;

	private Camera camera;

	@Override
	public EngineOptions onCreateEngineOptions() {
		// TODO Auto-generated method stub
		camera = new Camera(0, 0, GameEntity.CAMERA_WIDTH,
				GameEntity.CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(),
				camera);
		engineOptions.getTouchOptions().setNeedsMultiTouch(true);

		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws Exception {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),
				256, 256, TextureOptions.DEFAULT);
		splashTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(splashTextureAtlas, this, "splash.png", 0, 0);
		splashTextureAtlas.load();
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
		// TODO Auto-generated method stub
		initSplashScene();

		pOnCreateSceneCallback.onCreateSceneFinished(GameEntity.splashScene);
	}

	public void loadScenes() {
		//loadGameScene();
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		// TODO Auto-generated method stub
		mEngine.registerUpdateHandler(new TimerHandler(1f, this));

		pOnPopulateSceneCallback.onPopulateSceneFinished();
	}

	// ===========================================================
	// INITIALIZIE
	// ===========================================================

	private void initSplashScene() {
		GameEntity.splashScene = new Scene();
		splash = new Sprite(0, 0, splashTextureRegion,
				mEngine.getVertexBufferObjectManager()) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};

		splash.setScale(1.5f);
		splash.setPosition(
				(GameEntity.CAMERA_WIDTH - splash.getWidth()) * 0.5f,
				(GameEntity.CAMERA_HEIGHT - splash.getHeight()) * 0.5f);
		GameEntity.splashScene.registerTouchArea(splash);
		GameEntity.splashScene.attachChild(splash);
	}

	@Override
	public void onTimePassed(TimerHandler pTimerHandler) {
		// TODO Auto-generated method stub
		mEngine.unregisterUpdateHandler(pTimerHandler);
		//loadResources();
		//loadScenes();
		splash.detachSelf();
		Intent intent = new Intent(this, LoginScreen.class);
		//Intent intent = new Intent(this, SicBoGameActivity.class);
		this.startActivity(intent);
		finish();
	}

}
