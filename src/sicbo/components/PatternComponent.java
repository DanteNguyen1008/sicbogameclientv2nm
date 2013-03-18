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
					addCoin(getPositionX() + getiWidth() / 2
							- GameEntity.miniCoiWidth / 2, getPositionY()
							+ getiHeight() / 2 - GameEntity.miniCoinHeight / 2);

					break;
				case TouchEvent.ACTION_MOVE:
					Log.d("Touch Event", "Touch to ID : " + getiID());
					break;
				case TouchEvent.ACTION_UP:
					this.setAlpha(1f);
					break;
				}
				return true;
			}
		});
		atlastBig.load();
	}
	
	private void clearAllCoinList()
	{
		for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList.size(); i++) {
			if(GameEntity.sceneManager.gameScene.patternList.get(i).coinList.size() > 0)
			{
				GameEntity.sceneManager.gameScene.patternList.get(i).coinList.clear();
			}
		}
		
	}

	private void addCoin(float X, float Y) {
		if (GameEntity.userComponent.balance.balance - GameEntity.currentCoint < 0) {
			GameEntity.sceneManager.gameScene.confirmDialog.displayDialog(
					GameEntity.sceneManager.gameScene,
					"You do not enough money", 170, 200);
		} else if (GameEntity.betAmountRemain - GameEntity.currentCoint < 0) {
			GameEntity.sceneManager.gameScene.confirmDialog.displayDialog(
					GameEntity.sceneManager.gameScene,
					"You can not bet over 100 zenny", 170, 200);
		} else {
			if (GameEntity.gameAction.equals(GameEntity.GameAction.RESET)) {
				//GameEntity.sceneManager.gameScene.coinList.clear();
				clearAllCoinList();
			}
			Y -= 5 * coinList.size();
			CoinComponent coin = new CoinComponent(GameEntity.currentCoint,
					GameEntity.miniCoiWidth, GameEntity.miniCoinHeight, "", X,
					Y, getEngine().getTextureManager(), getContext(),
					getEngine(), GameEntity.currentCoint, this,
					ItemType.NORMAL_ITEM);
			scene.registerTouchArea(coin.getSprite());
			scene.attachChild(coin.getSprite());
			//GameEntity.sceneManager.gameScene.coinList.add(coin);
			for (int i = 0; i < GameEntity.sceneManager.gameScene.textList
					.size(); i++) {
				if (GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 1) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.updateBalance(
									UserComponent.UserAction.DECREASE_BALANCE,
									GameEntity.currentCoint);
				} else if (GameEntity.sceneManager.gameScene.textList.get(i)
						.getiID() == 3) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.decreaseBetRemain(GameEntity.currentCoint);
				}
			}
			coinList.add(coin);
			GameEntity.gameAction = GameEntity.GameAction.BETING;
		}
		//this.getSprite().setAlpha(1f);
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
				coinList.get(i).setPositionY(coinList.get(i).getPositionY() + 5);
			}
		}
		coinList.remove(coin);
	}
}
