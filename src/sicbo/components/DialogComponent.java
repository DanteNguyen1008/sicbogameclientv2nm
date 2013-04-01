package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.HorizontalAlign;

import com.example.sicbogameexample.GameEntity;
import com.example.sicbogameexample.MyScene;

import android.content.Context;

public class DialogComponent extends AbItemComponent {
	Sprite btnYes;
	Sprite btnNo;
	Sprite btnOK;
	Sprite loading;
	Text dialogText;

	public DialogComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType, int childWidth,
			int childHeight, String childURL, int childPositionX,
			int childPositionY, Font mFont) {
		super(width, childWidth, childHeight, background, childPositionX,
				childPositionY, textureManager, context, engine, itemType);

		// Background
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, background, 0, 0);

		setSprite(new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()));

		atlastBig.load();

		// child
		switch (itemType) {
		case CONFIRM_DIALOG:
			BitmapTextureAtlas atlastChild = new BitmapTextureAtlas(
					textureManager, childWidth, childHeight,
					TextureOptions.BILINEAR);

			ITextureRegion atlasRegionChild = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(atlastChild, context, childURL, 0, 0);

			btnOK = new Sprite(childPositionX, childPositionY,
					atlasRegionChild, engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						disableDialog(GameEntity.getInstance().sceneManager.gameScene);
						break;
					}
					return true;
				}
			};

			atlastChild.load();

			dialogText = new Text(0, height / 2, mFont, "", 100,
					new TextOptions(HorizontalAlign.CENTER),
					engine.getVertexBufferObjectManager());
			// dialogText.setScale(0.7f);
			break;
		case CONFIRM_ERROR:
			BitmapTextureAtlas erroratlastChild = new BitmapTextureAtlas(
					textureManager, childWidth, childHeight,
					TextureOptions.BILINEAR);

			ITextureRegion erroratlasRegionChild = BitmapTextureAtlasTextureRegionFactory
					.createFromAsset(erroratlastChild, context, childURL, 0, 0);

			btnOK = new Sprite(childPositionX, childPositionY,
					erroratlasRegionChild,
					engine.getVertexBufferObjectManager()) {
				@Override
				public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
						float X, float Y) {
					switch (pSceneTouchEvent.getAction()) {
					case TouchEvent.ACTION_DOWN:
						this.setScale(1.2f);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						disableDialog(GameEntity.getInstance().sceneManager.gameScene);

						break;
					}
					return true;
				}
			};

			erroratlastChild.load();

			dialogText = new Text(0, height / 2, mFont, "", 100,
					new TextOptions(HorizontalAlign.CENTER),
					engine.getVertexBufferObjectManager());
			// dialogText.setScale(0.7f);
			break;
		default:
			break;
		}

		getSprite().attachChild(btnOK);
		getSprite().attachChild(dialogText);
	}

	public DialogComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType, int btnYesWidth,
			int btnYesHeight, int btnNoWidth, int btnNoHeight,
			String btnYesBackground, String btnNoBackground,
			String dialogContent, int btnYesPosX, int btnYesPosY,
			int btnNoPosX, int btnNoPosY, Font mFont) {
		super(btnYesWidth, btnNoWidth, btnNoHeight, btnNoBackground, positionX,
				positionY, textureManager, context, engine, itemType);

		// Background
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, background, 0, 0);

		setSprite(new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()));

		atlastBig.load();

		// Yes no Button
		BitmapTextureAtlas atlastYesButton = new BitmapTextureAtlas(
				textureManager, btnYesWidth, btnYesHeight,
				TextureOptions.BILINEAR);

		BitmapTextureAtlas atlastNoButton = new BitmapTextureAtlas(
				textureManager, btnNoWidth, btnNoHeight,
				TextureOptions.BILINEAR);

		ITextureRegion atlasRegionYesButton = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastYesButton, context, btnYesBackground, 0,
						0);

		btnYes = new Sprite(btnYesPosX, btnYesPosY, atlasRegionYesButton,
				engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(1.2f);
					break;
				case TouchEvent.ACTION_MOVE:

					break;
				case TouchEvent.ACTION_UP:
					this.setScale(1f);
					// disableDialog(ConfigClass.sceneManager.gameScene);
					GameEntity.getInstance().sceneManager.gameScene
							.onBackButtonPress(false);
					GameEntity.getInstance().exitGame();
					break;
				}
				return true;
			}
		};

		ITextureRegion atlasRegionNoButton = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastNoButton, context, btnNoBackground, 0, 0);

		btnNo = new Sprite(btnNoPosX, btnNoPosY, atlasRegionNoButton,
				engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(1.2f);
					break;
				case TouchEvent.ACTION_MOVE:

					break;
				case TouchEvent.ACTION_UP:
					this.setScale(1f);
					disableDialog(GameEntity.getInstance().sceneManager.gameScene);
					GameEntity.getInstance().sceneManager.gameScene
							.onBackButtonPress(false);
					break;
				}
				return true;
			}
		};

		atlastYesButton.load();
		atlastNoButton.load();

		dialogText = new Text(0, height / 2, mFont, "", 100, new TextOptions(
				HorizontalAlign.CENTER), engine.getVertexBufferObjectManager());
		getSprite().attachChild(btnYes);
		getSprite().attachChild(btnNo);
		getSprite().attachChild(dialogText);
	}

	public void disableDialog(MyScene scene) {

		getSprite().setPosition(-800, -480);
		getSprite().setZIndex(0);
		getSprite().getParent().sortChildren();
		scene.enableAllTouch();

		switch (getiItemType()) {
		case CONFIRM_ERROR:
			scene.getScene().unregisterTouchArea(btnOK);
			// action
			GameEntity.getInstance().exitGameTimeOut();
			break;
		case CONFIRM_DIALOG:
			scene.getScene().unregisterTouchArea(btnOK);
			break;
		case YESNO_DIALOG:
			scene.getScene().unregisterTouchArea(btnYes);
			scene.getScene().unregisterTouchArea(btnNo);
			break;
		default:
			break;
		}
	}

	public void displayDialog(MyScene scene, String dialogContent,
			int positionX, int positionY) {
		dialogText.setPosition(positionX, positionY);
		dialogText.setText(dialogContent);

		switch (getiItemType()) {
		case CONFIRM_ERROR:
			scene.getScene().registerTouchArea(btnOK);
			break;
		case CONFIRM_DIALOG:
			scene.getScene().registerTouchArea(btnOK);
			break;
		case YESNO_DIALOG:
			scene.getScene().registerTouchArea(btnYes);
			scene.getScene().registerTouchArea(btnNo);
			break;
		default:
			break;
		}
		getSprite().setPosition(0, 0);
		getSprite().setZIndex(999);
		getSprite().getParent().sortChildren();
		scene.disableAllTouch();
	}
}
