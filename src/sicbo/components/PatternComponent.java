package sicbo.components;

import java.util.ArrayList;

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
import android.util.Log;

public class PatternComponent extends AbItemComponent {

	public ArrayList<CoinComponent> coinList;
	private long systemTime1;
	private long systemTime2;

	public PatternComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType, Scene scene,
			GameEntity.PatternType patternType) {
		super(id, width, height, background, positionX, positionY,
				textureManager, context, engine, itemType);
		this.scene = scene;
		this.patternType = patternType;
		coinList = new ArrayList<CoinComponent>();
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, background, 0, 0);
		setSprite(new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float X,
					float Y) {
				switch (pSceneTouchEvent.getAction()) {
				case TouchEvent.ACTION_DOWN:
					this.setAlpha(0.5f);
<<<<<<< HEAD
					if(GameEntity.getInstance().sceneManager.gameScene.
		            		playAnimationComponent.showBackgroundResult==true)
		            {
		            	GameEntity.getInstance().sceneManager.gameScene
						.buttonPlaySound();
				GameEntity.getInstance().updateAfterBet();
				GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent
						.stopAnimation();
				GameEntity.getInstance().sceneManager.gameScene.
        		playAnimationComponent.resetEntityPosition();
				GameEntity.getInstance().sceneManager.gameScene.
        		playAnimationComponent.removeRectWin();
				GameEntity.getInstance().sceneManager.gameScene.
        		playAnimationComponent.showBackgroundResult=false;
				
		            }
					GameEntity.getInstance().bet(getPositionX() + getiWidth() / 2
							- GameEntity.miniCoiWidth / 2, getPositionY()
							+ getiHeight() / 2 - GameEntity.miniCoinHeight / 2, PatternComponent.this);
					
=======
					if(System.currentTimeMillis() - systemTime1 < 300)
					{
						bet();
					}
>>>>>>> upstream/master
					break;
				case TouchEvent.ACTION_MOVE:
					Log.d("Touch Event", "Touch to ID : " + getiID());
					break;
				case TouchEvent.ACTION_UP:
					systemTime1 = System.currentTimeMillis();
					this.setAlpha(1f);
					break;
				}
				return true;
			}
		});
		atlastBig.load();
	}

	public GameEntity.PatternType patternType;
	public Engine engine;

	public Context context;
	public Scene scene;

	public void sortCoinList(CoinComponent coin) {

		for (int i = 0; i < coinList.size(); i++) {
			if (coinList.get(i).getPositionY() < coin.getPositionY()) {
				coinList.get(i)
						.getSprite()
						.setPosition(coinList.get(i).getPositionX(),
								coinList.get(i).getPositionY() + 5);
				coinList.get(i)
						.setPositionY(coinList.get(i).getPositionY() + 5);
			}
		}
		coinList.remove(coin);
	}
	
	
	public void bet()
	{
		if (GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent.showBackgroundResult == true) {
			GameEntity.getInstance().sceneManager.gameScene
					.buttonPlaySound();
			GameEntity.getInstance().updateAfterBet();
			GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent
					.stopAnimation();
			GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent.showBackgroundResult = false;
		}
		GameEntity.getInstance().bet(
				getPositionX() + getiWidth() / 2
						- GameEntity.miniCoiWidth / 2,
				getPositionY() + getiHeight() / 2
						- GameEntity.miniCoinHeight / 2,
				PatternComponent.this);
	}
	
}
