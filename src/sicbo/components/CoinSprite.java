package sicbo.components;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import sicbo.components.AbItemComponent.ItemType;

import com.example.sicbogameexample.GameEntity;

public class CoinSprite extends GenericPool<Sprite> {

	int height;
	int width;
	int coinID;
	CoinComponent coinComponent;

	public CoinSprite(float positionX, float positionY, int width, int height,
			int coinID, ITextureRegion atlasRegionBig,
			VertexBufferObjectManager vbo, CoinComponent coinComponent) {
		setPositionX(positionX);
		setPositionY(positionY);
		this.atlasRegionBig = atlasRegionBig;
		this.vbo = vbo;
		this.height = height;
		this.width = width;
		this.coinID = coinID;
		this.coinComponent = coinComponent;
	}

	ITextureRegion atlasRegionBig;
	VertexBufferObjectManager vbo;

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	private float positionX;
	private float positionY;

	@Override
	protected Sprite onAllocatePoolItem() {
		// TODO Auto-generated method stub
		return new Sprite(positionX, positionY, atlasRegionBig, vbo) {
			boolean mGrabbed = false;

			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					mGrabbed = true;
					this.setScale(1.5f);

					for (int i = 0; i < GameEntity.getInstance().sceneManager.gameScene.textList
							.size(); i++) {
						if (GameEntity.getInstance().sceneManager.gameScene.textList
								.get(i).getiID() == 1) {
							GameEntity.getInstance().sceneManager.gameScene.textList
									.get(i)
									.updateBalance(
											UserComponent.UserAction.INCREASE_BALANCE,
											coinID);
						} else if (GameEntity.getInstance().sceneManager.gameScene.textList
								.get(i).getiID() == 3) {
							GameEntity.getInstance().sceneManager.gameScene.textList
									.get(i).increaseBetRemain(coinID);
						}
					}
					if (GameEntity.getInstance().sceneManager.gameScene != null)
						GameEntity.getInstance().sceneManager.gameScene.releaseBetSound
								.play();
					coinComponent.deleteItSeft();
					break;
				case TouchEvent.ACTION_MOVE:
					if (mGrabbed) {
						this.setScale(1.5f);
						this.setPosition(pSceneTouchEvent.getX() - width / 2,
								pSceneTouchEvent.getY() - height / 2);
					}
					break;
				case TouchEvent.ACTION_UP:
					if (mGrabbed) {
						mGrabbed = false;
						this.setScale(1.2f);
						GameEntity.getInstance().currentCoint = coinComponent
								.getCoinID();
						specifyDragOnItem(pSceneTouchEvent.getX(),
								pSceneTouchEvent.getY(), pSceneTouchEvent,
								TouchEvent.ACTION_DOWN);
						GameEntity.getInstance().sceneManager.gameScene
								.getScene().unregisterTouchArea(this);
						this.detachSelf();
						coinComponent.deleteItSeft();
					}
					/*
					 * GameEntity.getInstance().sceneManager.gameScene.getScene()
					 * .unregisterTouchArea(this); this.detachSelf(); for (int i
					 * = 0; i <
					 * GameEntity.getInstance().sceneManager.gameScene.textList
					 * .size(); i++) { if
					 * (GameEntity.getInstance().sceneManager.
					 * gameScene.textList.get(i).getiID() == 1) {
					 * GameEntity.getInstance
					 * ().sceneManager.gameScene.textList.get
					 * (i).updateBalance(UserComponent
					 * .UserAction.INCREASE_BALANCE,coinID); }else
					 * if(GameEntity.
					 * getInstance().sceneManager.gameScene.textList
					 * .get(i).getiID() == 3) {
					 * GameEntity.getInstance().sceneManager
					 * .gameScene.textList.get(i).increaseBetRemain(coinID); } }
					 * if(GameEntity.getInstance().sceneManager.gameScene !=
					 * null) GameEntity.getInstance().sceneManager.gameScene.
					 * releaseBetSound.play(); coinComponent.deleteItSeft();
					 */
					break;
				}
				return true;
			}
		};
	}

	@Override
	protected void onHandleRecycleItem(final Sprite coin) {
		coin.setIgnoreUpdate(true);
		coin.setVisible(false);
	}

	@Override
	protected void onHandleObtainItem(final Sprite coin) {
		coin.reset();
	}

	private void specifyDragOnItem(float X, float Y,
			TouchEvent pSceneTouchEvent, int touchEventType) {
		for (int i = 0; i < GameEntity.getInstance().sceneManager.gameScene.patternList
				.size(); i++) {
			if (GameEntity.getInstance().sceneManager.gameScene.patternList
					.get(i).getiItemType() == ItemType.TOUCHABLE_ITEM) {
				if ((GameEntity.getInstance().sceneManager.gameScene.patternList
						.get(i).getPositionX() < X && X < (GameEntity
						.getInstance().sceneManager.gameScene.patternList
						.get(i).getPositionX() + GameEntity.getInstance().sceneManager.gameScene.patternList
						.get(i).getiWidth()))
						&& (GameEntity.getInstance().sceneManager.gameScene.patternList
								.get(i).getPositionY() < Y && Y < (GameEntity
								.getInstance().sceneManager.gameScene.patternList
								.get(i).getPositionY() + GameEntity
								.getInstance().sceneManager.gameScene.patternList
								.get(i).getiHeight()))) {
					/*
					 * GameEntity.getInstance().sceneManager.gameScene.patternList
					 * .get(i) .getSprite() .onAreaTouched( TouchEvent.obtain(X,
					 * Y, touchEventType, 0, pSceneTouchEvent.getMotionEvent()),
					 * X, Y);
					 * GameEntity.getInstance().sceneManager.gameScene.patternList
					 * .get(i) .getSprite() .onAreaTouched( TouchEvent.obtain(X,
					 * Y, TouchEvent.ACTION_UP, 0,
					 * pSceneTouchEvent.getMotionEvent()), X, Y);
					 */
					GameEntity.getInstance().sceneManager.gameScene.patternList
							.get(i).bet();
				}
			}
		}
	}

}
