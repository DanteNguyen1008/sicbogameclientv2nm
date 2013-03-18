package sicbo.components;

import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.pool.GenericPool;

import com.example.sicbogameexample.GameEntity;

public class CoinSprite extends GenericPool<Sprite> {
	
	int height;
	int width;
	int coinID;
	CoinComponent coinComponent;
	public CoinSprite(float positionX, float positionY, int width, int height, int coinID,
			ITextureRegion atlasRegionBig, VertexBufferObjectManager vbo, CoinComponent coinComponent) {
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
		return new Sprite(positionX, positionY, atlasRegionBig, vbo)
		{
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float X, float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setScale(1.5f);
					break;
				case TouchEvent.ACTION_MOVE:
					
					break;
				case TouchEvent.ACTION_UP:
					GameEntity.sceneManager.gameScene.getScene().unregisterTouchArea(this);
					this.detachSelf();
					for (int i = 0; i < GameEntity.sceneManager.gameScene.textList.size(); i++) {
						if (GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 1) {
							GameEntity.sceneManager.gameScene.textList.get(i).updateBalance(UserComponent.UserAction.INCREASE_BALANCE,coinID);
						}else if(GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 3)
						{
							GameEntity.sceneManager.gameScene.textList.get(i).increaseBetRemain(coinID);
						}
					}
					coinComponent.deleteItSeft();
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

}
