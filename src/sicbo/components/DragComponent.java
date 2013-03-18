package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.example.sicbogameexample.GameEntity;

import android.content.Context;

public class DragComponent extends AbItemComponent {
	public DragComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType, Scene scene,
			int coinID) {
		super(coinID, width, height, background, positionX, positionY,
				textureManager, context, engine, itemType);
		this.scene = scene;
		setCoinID(coinID);
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, background, 0, 0);
		tempDrag = (new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()));
		setSprite(new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()) {
			boolean mGrabbed = false;

			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mGrabbed = true;
					this.setScale(1.5f);
					break;
				case TouchEvent.ACTION_MOVE:
					if (mGrabbed) {
						this.setScale(1.5f);
						tempDrag.setPosition(pSceneTouchEvent.getX()
								- getiWidth() / 2, pSceneTouchEvent.getY()
								- getiHeight() / 2);
					}
					break;
				case TouchEvent.ACTION_UP:
					if (mGrabbed) {
						mGrabbed = false;
						this.setScale(1.2f);
						dropTempDrag();
						GameEntity.currentCoint = getCoinID();
						specifyDragOnItem(pSceneTouchEvent.getX() - getiWidth()
								/ 2,
								pSceneTouchEvent.getY() - getiHeight() / 2,
								pSceneTouchEvent, TouchEvent.ACTION_DOWN);
					}
					break;
				}
				return true;
			}
		});
		this.getSprite().setScale(1.2f);

		atlastBig.load();
	}

	private void specifyDragOnItem(float X, float Y,
			TouchEvent pSceneTouchEvent, int touchEventType) {
		for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList
				.size(); i++) {
			if (GameEntity.sceneManager.gameScene.patternList.get(i)
					.getiItemType() == ItemType.TOUCHABLE_ITEM) {
				if ((GameEntity.sceneManager.gameScene.patternList.get(i)
						.getPositionX() < X && X < (GameEntity.sceneManager.gameScene.patternList
						.get(i).getPositionX() + GameEntity.sceneManager.gameScene.patternList
						.get(i).getiWidth()))
						&& (GameEntity.sceneManager.gameScene.patternList
								.get(i).getPositionY() < Y && Y < (GameEntity.sceneManager.gameScene.patternList
								.get(i).getPositionY() + GameEntity.sceneManager.gameScene.patternList
								.get(i).getiHeight()))) {
					GameEntity.sceneManager.gameScene.patternList
							.get(i)
							.getSprite()
							.onAreaTouched(
									TouchEvent.obtain(X, Y, touchEventType, 0,
											pSceneTouchEvent.getMotionEvent()),
									X, Y);
					GameEntity.sceneManager.gameScene.patternList
					.get(i)
					.getSprite()
					.onAreaTouched(
							TouchEvent.obtain(X, Y,TouchEvent.ACTION_UP, 0,
									pSceneTouchEvent.getMotionEvent()),
							X, Y);
				}
			}
		}
	}

	private int coinID;

	public int getCoinID() {
		return coinID;
	}

	public void setCoinID(int coinID) {
		this.coinID = coinID;
	}

	public Scene scene;

	public Sprite tempDrag;

	private void dropTempDrag() {

		tempDrag.setPosition(getPositionX(), getPositionY());
	}
}
