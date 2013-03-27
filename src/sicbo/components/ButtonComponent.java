package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;

import android.content.Context;
import android.content.Intent;

import com.example.sicbogameexample.GameEntity;
import com.example.sicbogameexample.LoginScreen;
import com.example.sicbogameexample.SceneManager.SceneType;

public class ButtonComponent extends AbItemComponent {

	public ButtonComponent(int id, int width, int height, int colum, int row,
			String background, float positionX, float positionY,
			TextureManager textureManager, Context context, Engine engine,
			ItemType itemType, Scene scene, SceneType nextScene) {
		super(id, width, height, background, positionX, positionY,
				textureManager, context, engine, itemType);
		this.scene = scene;
		this.nextScene = nextScene;
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITiledTextureRegion tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(atlastBig, context, background, 0, 0,
						colum, row);

		switch (getiItemType()) {
		case BUTTON_ROLL:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().startGame();

						break;
					}
					return true;
				}

			});
			break;
		case BUTTON_CLEAR:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().buttonPlaySoudEffect();
						GameEntity.getInstance().clearBet();
						break;
					}
					return true;
				}
			});
			break;
		case BUTTON_REBET:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().rebet();
						break;
					}
					return true;
				}
			});
			break;
		case BUTTON_HISTORY:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().viewHistory();
						
						break;
					}
					return true;
				}
			});
			break;
		case BUTTON_NEXT:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().updateAfterBet();
						GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent
								.stopAnimation();
						GameEntity.getInstance().sceneManager.gameScene
								.enableAllTouch();
						GameEntity.getInstance().mSensorListener.registerShake();
						break;
					}
					return true;
				}
			});
			break;
		case BUTTON_SOUND:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						if (GameEntity.getInstance().isMusicEnable)
							this.setCurrentTileIndex(1);
						else
							this.setCurrentTileIndex(3);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().enableMusic();
						if (GameEntity.getInstance().isMusicEnable)
							this.setCurrentTileIndex(0);
						else
							this.setCurrentTileIndex(2);
						break;
					}
					return true;
				}
			});
			break;
		case BUTTON_EXIT:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						// ConfigClass.sceneManager.gameScene.getActivity()
						// .finish();
						GameEntity.getInstance().sceneManager.gameScene.yesnoDialog
								.displayDialog(
										GameEntity.getInstance().sceneManager.gameScene,
										"Do you want to exit?", 200, 300);
						// ConfigClass.sceneManager.gameScene.exitGame();

						break;
					}
					return true;
				}
			});
			break;
		case MENU_RESUME:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().sceneManager.gameScene
								.hideMenu();

						break;
					}
					return true;
				}
			});
			break;
		case MENU_HELP:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().sceneManager.gameScene
								.hideMenu();
						GameEntity.getInstance().viewHelp();
						break;
					}
					return true;
				}
			});
			break;
		case MENU_PROFILE:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().sceneManager.gameScene
								.hideMenu();
						GameEntity.getInstance().viewProfile();
						break;
					}
					return true;
				}
			});
			break;
		case MENU_EXIT:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().sceneManager.gameScene
								.hideMenu();
						GameEntity.getInstance().displayYesNoDialog(
								"Do you want to exit?", 200, 300);
						break;
					}
					return true;
				}
			});
			break;
		case MENU_LOGOUT:
			tiledSprite = (new TiledSprite(positionX, positionY,
					tiledTextureRegion, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
						GameEntity.getInstance().sceneManager.gameScene
								.buttonPlaySound();
						GameEntity.getInstance().sceneManager.gameScene
								.hideMenu();
						GameEntity.getInstance().logout();
						
						break;
					}
					return true;
				}
			});
			break;
		default:
			break;
		}

		tiledSprite.setCurrentTileIndex(0);
		atlastBig.load();
	}

	public Engine engine;

	public Context context;

	public Scene scene;

	public SceneType nextScene;

	public TiledSprite tiledSprite;
}
