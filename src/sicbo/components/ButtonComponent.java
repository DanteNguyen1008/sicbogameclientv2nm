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

import com.example.sicbogameexample.GameEntity;
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
						boolean isBet = false;
						for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList
								.size(); i++) {
							if (GameEntity.sceneManager.gameScene.patternList
									.get(i).coinList.size() > 0
									&& !GameEntity.gameAction
											.equals(GameEntity.GameAction.RESET)) {
								for (int j = 0; j < GameEntity.sceneManager.gameScene.patternList
										.get(i).coinList.size(); j++) {
									GameEntity.sceneManager.gameScene.betList
											.add(new BetComponent(
													GameEntity.sceneManager.gameScene.patternList
															.get(i).coinList
															.get(j).pattern.patternType
															.getValue(),
													GameEntity.sceneManager.gameScene.patternList
															.get(i).coinList
															.get(j).getCoinID()));
								}
								isBet = true;
							}
						}

						if (!isBet) {

							GameEntity.sceneManager.gameScene.confirmDialog
									.displayDialog(
											GameEntity.sceneManager.gameScene,
											"You must bet before start game",
											170, 200);

						} else {
							sortBetList();
							GameEntity.sceneManager.gameScene.startGame();
						}

						break;
					}
					return true;
				}

				private int sortBetList() {
					// TODO Auto-generated method stub
					for (int i = 0; i < GameEntity.sceneManager.gameScene.betList
							.size(); i++) {
						for (int j = 0; j < GameEntity.sceneManager.gameScene.betList
								.size(); j++) {
							if (GameEntity.sceneManager.gameScene.betList
									.get(i).betPatternID == GameEntity.sceneManager.gameScene.betList
									.get(j).betPatternID
									&& i != j) {
								GameEntity.sceneManager.gameScene.betList
										.get(i).betAmount += GameEntity.sceneManager.gameScene.betList
										.get(j).betAmount;
								GameEntity.sceneManager.gameScene.betList
										.remove(j);
								j--;
							}
						}
					}
					return GameEntity.sceneManager.gameScene.betList.size();
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
						clearAllBet();
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
						reBet();
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
						GameEntity.sceneManager.gameScene.viewHistory();
						GameEntity.sceneManager.gameScene.getActivity()
								.finish();
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
						// Clear game scene
						updateAfterBet();

						// GameEntity.sceneManager.setScene(ButtonComponent.this.nextScene);
						GameEntity.sceneManager.gameScene.playAnimationComponent
								.stopAnimation();
						GameEntity.sceneManager.gameScene.enableAllTouch();
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
						this.setCurrentTileIndex(0);
						break;
					case TouchEvent.ACTION_MOVE:

						break;
					case TouchEvent.ACTION_UP:
						this.setScale(1f);
						this.setCurrentTileIndex(0);
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
						// ConfigClass.sceneManager.gameScene.getActivity()
						// .finish();
						GameEntity.sceneManager.gameScene.yesnoDialog
								.displayDialog(
										GameEntity.sceneManager.gameScene,
										"Do you want to exit?", 200, 300);
						// ConfigClass.sceneManager.gameScene.exitGame();

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

	private void updateAfterBet() {
		// clearAllBet();

		for (int j = 0; j < GameEntity.sceneManager.gameScene.patternList
				.size(); j++) {
			for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList
					.get(j).coinList.size(); i++) {

				GameEntity.sceneManager.gameScene.patternList.get(j).coinList
						.get(i).removeCoin();
				GameEntity.sceneManager.gameScene.getScene()
						.unregisterTouchArea(
								GameEntity.sceneManager.gameScene.patternList
										.get(j).coinList.get(i).getSprite());

			}
		}

		for (int i = 0; i < GameEntity.sceneManager.gameScene.textList.size(); i++) {
			if (GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 1) {
				GameEntity.sceneManager.gameScene.textList.get(i)
						.updateBalance(UserComponent.UserAction.UPDATE_BALANCE,
								GameEntity.userComponent.getBalance());
			} else if (GameEntity.sceneManager.gameScene.textList.get(i)
					.getiID() == 3) {
				GameEntity.sceneManager.gameScene.textList.get(i)
						.updateBetRemain(GameEntity.REMAIN_FIXED);
			}
		}

		GameEntity.sceneManager.gameScene.betList.clear();
		GameEntity.gameAction = GameEntity.GameAction.RESET;
	}

	private void clearAllBet() {
		// TODO Auto-generated method stub
		if (GameEntity.gameAction.equals(GameEntity.GameAction.REBET)
				|| GameEntity.gameAction.equals(GameEntity.GameAction.BETING)) {
			double amoutUpdate = 0;
			for (int j = 0; j < GameEntity.sceneManager.gameScene.patternList
					.size(); j++) {
				for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList
						.get(j).coinList.size(); i++) {
					GameEntity.sceneManager.gameScene.patternList.get(j).coinList
							.get(i).removeCoin();
					amoutUpdate += GameEntity.sceneManager.gameScene.patternList
							.get(j).coinList.get(i).getCoinID();
					GameEntity.sceneManager.gameScene
							.getScene()
							.unregisterTouchArea(
									GameEntity.sceneManager.gameScene.patternList
											.get(j).coinList.get(i).getSprite());
				}
			}

			for (int i = 0; i < GameEntity.sceneManager.gameScene.textList
					.size(); i++) {
				if (GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 1) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.updateBalance(
									UserComponent.UserAction.INCREASE_BALANCE,
									amoutUpdate);
				} else if (GameEntity.sceneManager.gameScene.textList.get(i)
						.getiID() == 3) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.updateBetRemain(GameEntity.REMAIN_FIXED);
				}
			}
			GameEntity.gameAction = GameEntity.GameAction.RESET;
		}
	}

	private void reBet() {
		if (GameEntity.gameAction.equals(GameEntity.GameAction.RESET)) {
			double amoutUpdate = 0;
			for (int j = 0; j < GameEntity.sceneManager.gameScene.patternList
					.size(); j++) {
				for (int i = 0; i < GameEntity.sceneManager.gameScene.patternList
						.get(j).coinList.size(); i++) {
					GameEntity.sceneManager.gameScene.patternList.get(j).coinList
							.get(i).reBuildCoin();
					amoutUpdate += GameEntity.sceneManager.gameScene.patternList
							.get(j).coinList.get(i).getCoinID();
					GameEntity.sceneManager.gameScene
							.getScene()
							.registerTouchArea(
									GameEntity.sceneManager.gameScene.patternList
											.get(j).coinList.get(i).getSprite());
				}
			}

			for (int i = 0; i < GameEntity.sceneManager.gameScene.textList
					.size(); i++) {
				if (GameEntity.sceneManager.gameScene.textList.get(i).getiID() == 1) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.updateBalance(
									UserComponent.UserAction.DECREASE_BALANCE,
									amoutUpdate);
				} else if (GameEntity.sceneManager.gameScene.textList.get(i)
						.getiID() == 3) {
					GameEntity.sceneManager.gameScene.textList.get(i)
							.decreaseBetRemain(amoutUpdate);
				}
			}
			GameEntity.gameAction = GameEntity.GameAction.REBET;
		}
	}

	public Engine engine;

	public Context context;

	public Scene scene;

	public SceneType nextScene;

	public TiledSprite tiledSprite;

	/*
	 * private void specifyBetOnItem(CoinComponent coin) { for (int i = 0; i <
	 * GameEntity.sceneManager.gameScene.patternList.size(); i++) { if
	 * (GameEntity.sceneManager.gameScene.patternList.get(i).getiItemType() ==
	 * ItemType.TOUCHABLE_ITEM) { if
	 * ((GameEntity.sceneManager.gameScene.patternList.get(i).getPositionX() <
	 * coin .getPositionX() && coin.getPositionX() <
	 * (GameEntity.sceneManager.gameScene.patternList .get(i).getPositionX() +
	 * GameEntity.sceneManager.gameScene.patternList.get(i) .getiWidth())) &&
	 * (GameEntity.sceneManager.gameScene.patternList.get(i).getPositionY() <
	 * coin .getPositionY() && coin.getPositionY() <
	 * (GameEntity.sceneManager.gameScene.patternList .get(i).getPositionY() +
	 * GameEntity.sceneManager.gameScene.patternList .get(i).getiHeight()))) {
	 * GameEntity.sceneManager.gameScene.betList.add(new BetComponent(
	 * GameEntity.sceneManager.gameScene.patternList.get(i).patternType
	 * .getValue(), (double) coin.getCoinID())); } } }
	 * 
	 * }
	 */
}
