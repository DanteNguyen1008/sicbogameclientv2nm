package com.example.sicbogameexample;

import java.io.IOException;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.IEntityFactory;
import org.andengine.entity.particle.ParticleSystem;
import org.andengine.entity.particle.emitter.PointParticleEmitter;
import org.andengine.entity.particle.initializer.VelocityParticleInitializer;
import org.andengine.entity.particle.modifier.AlphaParticleModifier;
import org.andengine.entity.particle.modifier.RotationParticleModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo.components.AbItemComponent.ItemType;
import sicbo.components.BetComponent;
import sicbo.components.CoinComponent;
import sicbo.components.GameComponent;
import sicbo.components.HistoryComponent;
import sicbo.components.PatternComponent;
import sicbo.components.ShakeEventListener;
import sicbo.components.TimoutCheckAsyns;
import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class GameEntity {
	// Implement single ton
	private static GameEntity INSTANCE = null;


	protected GameEntity() {

	}

	public static GameEntity getInstance() {
		if (INSTANCE == null)
			INSTANCE = new GameEntity();
		return INSTANCE;
	}
	
	
	public final String runableTextContent = "Maximum bet is 100 Z - You can click back button to open menu - You can Shake phone to start game";

	// Final static fields
	public final static int CAMERA_WIDTH = 800;
	public final static int CAMERA_HEIGHT = 480;

	public final static String SIGNIN_TASK = "sign_in";
	public final static String SIGNUP_TASK = "sign_up";
	public final static String SIGNOUT_TASK = "sign_out";
	public final static String CHANGE_PASSWORD_TASK = "change_password";
	public final static String FORGOT_PASSWORD_TASK = "forgot_password";

	public final static String STARTGAME_TASK = "play_bet";

	public final static String VIEW_HISTORY = "view_bet_history";
	public static final double REMAIN_FIXED = 100;
	public final static int miniCoiWidth = 31;
	public final static int miniCoinHeight = 31;
	public SceneManager sceneManager;
	public boolean isResultDisplay = false;
	public boolean isAnimationRunning = false;
	// Scene fields
	public Scene splashScene;
	public Scene GameScene;
	public Scene helpScene;
	public Scene historyScene;
	public Scene animatedSene;

	public int currentScreen;
	public EngineOptions engineOptions;
	public GameComponent currentGame;

	// Connection field object
	public ConnectionHandler connectionHandler;
	public int currentCoint = CoinComponent.COINTID_1;
	public UserComponent userComponent;
	public double betAmountRemain = REMAIN_FIXED;
	public boolean isMusicEnable = true;

	// Enum
	public enum GameAction {
		BETING, REBET, RESET
	}

	public GameAction gameAction = GameAction.BETING;

	// Pattern ID
	public enum PatternType {
		Big(1), Small(2), Triple1(3), Triple2(4), Triple3(5), Triple4(6), Triple5(
				7), Triple6(8), Double1(9), Double2(10), Double3(11), Double4(
				12), Double5(13), Double6(14), AllTriple(15), ThreeDice4(16), ThreeDice17(
				17), ThreeDice6(18), ThreeDice15(19), ThreeDice5(20), ThreeDice16(
				21), ThreeDice7(22), ThreeDice14(23), ThreeDice8(24), ThreeDice13(
				25), ThreeDice9(26), ThreeDice12(27), SingleDice1(28), SingleDice2(
				29), SingleDice3(30), SingleDice4(31), SingleDice5(32), SingleDice6(
				33), ThreeDice10(34), ThreeDice11(35);

		private final int value;

		private PatternType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public ShakeEventListener mSensorListener;

	// Game auto action
	public void checkUserTimeout() {
		TimoutCheckAsyns checkTimeOut = new TimoutCheckAsyns();
		Object[] params = { sceneManager.gameScene.getActivity() };
		checkTimeOut.execute(params);
	}

	/**
	 * User acction
	 * 
	 * */

	/**
	 * Enable and disable music This variable will be reference in MSComponent
	 * when handle music or sound
	 */
	public void enableMusic() {
		if (!isMusicEnable) {
			isMusicEnable = true;
			sceneManager.gameScene.backgroundMusic.resume();
		} else {
			sceneManager.gameScene.backgroundMusic.pause();
			isMusicEnable = false;
		}

	}

	/**
	 * Bet function, when user click on a pattern, the method will be call
	 * called from click action in pattern
	 * 
	 * @param X
	 * @param Y
	 * @param pattern
	 */
	public void bet(float X, float Y, PatternComponent pattern) {
		if (userComponent.balance.balance - currentCoint < 0) {
			displayConfirmDialog("You do not enough money", 170, 200);
		} else if (betAmountRemain - currentCoint < 0) {
			displayConfirmDialog("You can not bet over 100 zenny", 170, 200);
		} else {
			if (gameAction.equals(GameEntity.GameAction.RESET)) {
				// GameEntity.sceneManager.gameScene.coinList.clear();
				clearAllCoinList();
			}
			Y -= 5 * pattern.coinList.size();
			CoinComponent coin = new CoinComponent(currentCoint,
					GameEntity.miniCoiWidth, GameEntity.miniCoinHeight, "", X,
					Y, pattern.getEngine().getTextureManager(),
					pattern.getContext(), pattern.getEngine(), currentCoint,
					pattern, ItemType.NORMAL_ITEM);
			pattern.scene.registerTouchArea(coin.getSprite());
			pattern.scene.attachChild(coin.getSprite());
			for (int i = 0; i < sceneManager.gameScene.textList.size(); i++) {
				if (sceneManager.gameScene.textList.get(i).getiID() == 1) {
					sceneManager.gameScene.textList.get(i).updateBalance(
							UserComponent.UserAction.DECREASE_BALANCE,
							currentCoint);
				} else if (sceneManager.gameScene.textList.get(i).getiID() == 3) {
					sceneManager.gameScene.textList.get(i).decreaseBetRemain(
							currentCoint);
				}
			}
			pattern.coinList.add(coin);
			gameAction = GameEntity.GameAction.BETING;
			if (sceneManager.gameScene.betSound != null)
				sceneManager.gameScene.betSound.play();
		}
	}

	/**
	 * Clear all coin list
	 */
	private void clearAllCoinList() {
		int listSize = sceneManager.gameScene.patternList.size();
		for (int i = 0; i < listSize; i++) {
			if (listSize > 0) {
				sceneManager.gameScene.patternList.get(i).coinList.clear();
			}
		}

	}

	public void buttonPlaySoudEffect() {
		sceneManager.gameScene.buttonPlaySound();
	}

	/**
	 * This method called when user click "clear" or "reset" button Called from
	 * Button component class - click action
	 */
	public void clearBet() {

		if (gameAction.equals(GameAction.REBET)
				|| gameAction.equals(GameAction.BETING)) {
			double amoutUpdate = 0;
			int patternListSize = sceneManager.gameScene.patternList.size();
			for (int j = 0; j < patternListSize; j++) {
				int coinListSize = sceneManager.gameScene.patternList.get(j).coinList
						.size();
				for (int i = 0; i < coinListSize; i++) {
					sceneManager.gameScene.patternList.get(j).coinList.get(i)
							.removeCoin();
					amoutUpdate += sceneManager.gameScene.patternList.get(j).coinList
							.get(i).getCoinID();
					sceneManager.gameScene.getScene().unregisterTouchArea(
							sceneManager.gameScene.patternList.get(j).coinList
									.get(i).getSprite());
				}
			}
			int textListSize = sceneManager.gameScene.textList.size();
			for (int i = 0; i < textListSize; i++) {
				if (sceneManager.gameScene.textList.get(i).getiID() == 1) {
					sceneManager.gameScene.textList.get(i).updateBalance(
							UserComponent.UserAction.INCREASE_BALANCE,
							amoutUpdate);
				} else if (sceneManager.gameScene.textList.get(i).getiID() == 3) {
					sceneManager.gameScene.textList.get(i).updateBetRemain(
							REMAIN_FIXED);
				}
			}
			gameAction = GameEntity.GameAction.RESET;
		}
	}

	/**
	 * This method called when user click "rebet" button Called from Button
	 * component class - click action
	 */
	public void rebet() {
		if (gameAction.equals(GameEntity.GameAction.RESET)) {
			double amoutUpdate = 0;
			int patternListSize = sceneManager.gameScene.patternList.size();
			for (int j = 0; j < patternListSize; j++) {
				int coinListSize = sceneManager.gameScene.patternList.get(j).coinList
						.size();
				for (int i = 0; i < coinListSize; i++) {
					sceneManager.gameScene.patternList.get(j).coinList.get(i)
							.reBuildCoin();
					amoutUpdate += sceneManager.gameScene.patternList.get(j).coinList
							.get(i).getCoinID();
					sceneManager.gameScene.getScene().registerTouchArea(
							sceneManager.gameScene.patternList.get(j).coinList
									.get(i).getSprite());
				}
			}
			int textListSize = sceneManager.gameScene.textList.size();
			for (int i = 0; i < textListSize; i++) {
				if (sceneManager.gameScene.textList.get(i).getiID() == 1) {
					sceneManager.gameScene.textList.get(i).updateBalance(
							UserComponent.UserAction.DECREASE_BALANCE,
							amoutUpdate);
				} else if (sceneManager.gameScene.textList.get(i).getiID() == 3) {
					sceneManager.gameScene.textList.get(i).decreaseBetRemain(
							amoutUpdate);
				}
			}
			gameAction = GameEntity.GameAction.REBET;
		}
	}

	/**
	 * This method will be call after user click next game button Called from
	 * button action click
	 */
	public void updateAfterBet() {
		// clearAllBet();
		int patternListSize = sceneManager.gameScene.patternList.size();
		for (int j = 0; j < patternListSize; j++) {
			int coinListSize = sceneManager.gameScene.patternList.get(j).coinList
					.size();
			for (int i = 0; i < coinListSize; i++) {

				sceneManager.gameScene.patternList.get(j).coinList.get(i)
						.removeCoin();
				sceneManager.gameScene.getScene().unregisterTouchArea(
						sceneManager.gameScene.patternList.get(j).coinList.get(
								i).getSprite());
			}
		}

		int textListSize = sceneManager.gameScene.textList.size();
		for (int i = 0; i < textListSize; i++) {
			if (sceneManager.gameScene.textList.get(i).getiID() == 1) {
				sceneManager.gameScene.textList.get(i).updateBalance(
						UserComponent.UserAction.UPDATE_BALANCE,
						userComponent.getBalance());
			} else if (sceneManager.gameScene.textList.get(i).getiID() == 3) {
				sceneManager.gameScene.textList.get(i).updateBetRemain(
						GameEntity.REMAIN_FIXED);
			}
		}

		sceneManager.gameScene.betList.clear();
		gameAction = GameEntity.GameAction.RESET;
	}

	/**
	 * When user click start game or shake phone, this method will be call
	 * Called from Button action click and Shake phone method on game scene
	 */
	public void startGame() {
		boolean isBet = false;
		int patternListSize = sceneManager.gameScene.patternList.size();
		for (int i = 0; i < patternListSize; i++) {
			if (sceneManager.gameScene.patternList.get(i).coinList.size() > 0
					&& !gameAction.equals(GameEntity.GameAction.RESET)) {
				int coinListSize = sceneManager.gameScene.patternList.get(i).coinList
						.size();
				for (int j = 0; j < coinListSize; j++) {
					sceneManager.gameScene.betList.add(new BetComponent(
							sceneManager.gameScene.patternList.get(i).coinList
									.get(j).pattern.patternType.getValue(),
							sceneManager.gameScene.patternList.get(i).coinList
									.get(j).getCoinID()));
				}
				isBet = true;
			}
		}

		if (!isBet) {
			displayConfirmDialog("You must bet before start game", 170, 200);
		} else {
			sortBetList();
			ConnectionAsync connectionAsync = new ConnectionAsync();
			sceneManager.gameScene.disableAllTouch();
			int paramsSize = sceneManager.gameScene.betList.size();
			String[] paramsName1 = new String[paramsSize];
			String[] paramsValue1 = new String[paramsSize];
			String[] paramsName2 = new String[paramsSize];
			String[] paramsValue2 = new String[paramsSize];

			for (int i = 0; i < paramsSize; i++) {
				paramsName1[i] = "betspots";
				paramsValue1[i] = sceneManager.gameScene.betList.get(i).betPatternID
						+ "";
				paramsName2[i] = "betamounts";
				paramsValue2[i] = sceneManager.gameScene.betList.get(i).betAmount
						+ "";
			}

			String[] paramsName = new String[paramsName1.length
					+ paramsName2.length];
			System.arraycopy(paramsName1, 0, paramsName, 0, paramsName2.length);
			System.arraycopy(paramsName2, 0, paramsName, paramsName1.length,
					paramsName2.length);

			String[] paramsValue = new String[paramsValue1.length
					+ paramsValue2.length];
			System.arraycopy(paramsValue1, 0, paramsValue, 0,
					paramsValue2.length);
			System.arraycopy(paramsValue2, 0, paramsValue, paramsValue1.length,
					paramsValue2.length);

			Object[] params = { connectionHandler,
					sceneManager.gameScene.getActivity(),
					GameEntity.STARTGAME_TASK, paramsName, paramsValue };
			sceneManager.gameScene.disableAllTouch();
			mSensorListener.stopRegisterShake();
			connectionAsync.execute(params);
		}

	}

	private int sortBetList() {
		// TODO Auto-generated method stub
		for (int i = 0; i < sceneManager.gameScene.betList.size(); i++) {
			for (int j = 0; j < sceneManager.gameScene.betList.size(); j++) {
				if (sceneManager.gameScene.betList.get(i).betPatternID == sceneManager.gameScene.betList
						.get(j).betPatternID && i != j) {
					sceneManager.gameScene.betList.get(i).betAmount += sceneManager.gameScene.betList
							.get(j).betAmount;
					sceneManager.gameScene.betList.remove(j);
					j--;
				}
			}
		}
		return sceneManager.gameScene.betList.size();
	}

	/**
	 * This method will be call when user click view history button Called from
	 * Button Action click - button component class
	 */
	public void viewHistory() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		Object[] params = { connectionHandler,
				sceneManager.gameScene.getActivity(), GameEntity.VIEW_HISTORY,
				null, null };
		connectionAsync.execute(params);
	}

	/**
	 * Go to profile activity
	 */
	public void viewProfile() {
		Intent intent1 = new Intent(sceneManager.gameScene.getActivity(), ProfileActivity.class);
		
		sceneManager.gameScene.getActivity().startActivity(intent1);
	}

	/**
	 * Go to help page activity
	 */
	public void viewHelp() {
        Intent intent1 = new Intent(sceneManager.gameScene.getActivity(), HelpActivity.class);
		
		sceneManager.gameScene.getActivity().startActivity(intent1);
	}

	/**
	 * This method will be call when user click exit button Called from Button
	 * Action click - button component class
	 */
	public void exitGame() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		Object[] params = { connectionHandler,
				sceneManager.gameScene.getActivity(), GameEntity.SIGNOUT_TASK,
				null, null };
		connectionAsync.execute(params);
		betAmountRemain = GameEntity.REMAIN_FIXED;
		sceneManager.gameScene.unLoadScene();
		sceneManager.activity.finish();
		sceneManager = null;
	}

	/**
	 * This method will be call when user timeout Called from GameEntity
	 * checkTimeout method
	 */
	public void exitGameTimeOut() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		Object[] params = { connectionHandler,
				sceneManager.gameScene.getActivity(), GameEntity.SIGNOUT_TASK,
				null, null };
		connectionAsync.execute(params);
		// unLoadScene();
		sceneManager.gameScene.getActivity().finish();
		sceneManager = null;
	}

	/**
	 * @author Admin this class is the portal to sent and receive request
	 *         response with server
	 */
	class ConnectionAsync extends AsyncTask<Object, String, Integer> {
		ConnectionHandler connectionHandler;
		BaseGameActivity activity;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected Integer doInBackground(Object... params) {
			// TODO Auto-generated method stub
			connectionHandler = (ConnectionHandler) params[0];
			activity = (BaseGameActivity) params[1];
			try {
				connectionHandler.requestToServer((String) params[2],
						(String[]) params[3], (Object[]) params[4]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Integer value) {
			try {
				// dataList = connectionHandler.parseData(responseName);
				JSONObject result = connectionHandler.getResult();
                String s=connectionHandler.getTaskID();
				if (connectionHandler.getTaskID().equals("res_play_bet")) {
					// move to animation scene
					if (result.getBoolean("is_success")) {
						onReceiveStartGame(result);
					} else {
						Log.d("Bet error", "Something wrong???");
					}
				} else if (connectionHandler.getTaskID().equals("res_view_history")) {
					onReceiveViewHistory(result, activity);
				} else if (connectionHandler.getTaskID().equals("res_signout")) {
					onReceiveSignout();
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param result
	 * @throws JSONException
	 *             This method called when receive response game result from
	 *             Server
	 */
	public void onReceiveStartGame(JSONObject result) throws JSONException {
		currentGame.setGame(result.getBoolean("is_win"),
				result.getInt("dice1"), result.getInt("dice2"),
				result.getInt("dice3"), result.getDouble("current_balance"),
				result.getDouble("totalbetamount"),
				result.getDouble("totalwinamount"),
				result.getString("winpatterns"));
		userComponent.balance.balance = GameEntity.getInstance().currentGame.newBalance;
		// sceneManager.setScene(SceneType.ANIMATION);
		sceneManager.gameScene.playAnimationComponent.playAnimation();
	}

	/**
	 * @param result
	 * @param activity
	 * @throws JSONException
	 *             This method called when receive history list from server
	 */
	public void onReceiveViewHistory(JSONObject result, Activity activity)
			throws JSONException {
		for (int i = 0; i < result.getInt("num_of_item"); i++) {
			userComponent.historyList.add(new HistoryComponent(result
					.getJSONObject(i + "").getBoolean("iswin"), result
					.getJSONObject(i + "").getString("betdate"), result
					.getJSONObject(i + "").getDouble("balance"),result
					.getJSONObject(i+"").getString("dices")));
					
		}

		Intent intent = new Intent(activity, ViewHistoryActivity.class);
		activity.startActivity(intent);
	}

	public void onReceiveSignout() {

	}

	// Dialog display
	// Error display
	public void displayYesNoDialog(String errorContent, int posX, int posY) {
		sceneManager.gameScene.yesnoDialog.displayDialog(
				sceneManager.gameScene, errorContent, posX, posY);
	}

	public void displayConfirmDialog(String errorContent, int posX, int posY) {
		sceneManager.gameScene.confirmDialog.displayDialog(
				sceneManager.gameScene, errorContent, posX, posY);
	}

	// test particle
	public void createFireWork(final float posX, final float posY,
			final int width, final int height, final Color color, int mNumPart,
			int mTimePart) {

		PointParticleEmitter particleEmitter = new PointParticleEmitter(posX,
				posY);

		/*
		 * IEntityFactory<Sprite> recFact = new IEntityFactory<Sprite>() {
		 * 
		 * @Override public Sprite create(float pX, float pY) {
		 * BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(
		 * sceneManager.activity.getTextureManager(), width, height,
		 * TextureOptions.BILINEAR);
		 * 
		 * ITextureRegion atlasRegionBig =
		 * BitmapTextureAtlasTextureRegionFactory .createFromAsset(atlastBig,
		 * sceneManager.activity, fireworkBG, 0, 0);
		 * 
		 * Sprite sprite = new Sprite(posX, posY, atlasRegionBig,
		 * sceneManager.activity .getEngine().getVertexBufferObjectManager());
		 * 
		 * atlastBig.load(); return sprite; }
		 * 
		 * };
		 */
		IEntityFactory<Rectangle> recFact = new IEntityFactory<Rectangle>() {

			@Override
			public Rectangle create(float pX, float pY) {
				Rectangle rect = new Rectangle(posX, posY, 10, 10,
						sceneManager.activity.getVertexBufferObjectManager());
				rect.setColor(color);
				return rect;
			}

		};
		final ParticleSystem<Rectangle> particleSystem = new ParticleSystem<Rectangle>(
				recFact, particleEmitter, 500, 500, mNumPart);

		particleSystem
				.addParticleInitializer(new VelocityParticleInitializer<Rectangle>(
						-50, 50, -50, 50));
		/*
		 * particleSystem .addParticleInitializer(new
		 * ColorParticleInitializer<Rectangle>( color));
		 */
		particleSystem
				.addParticleModifier(new AlphaParticleModifier<Rectangle>(0,
						0.6f * mTimePart, 1, 0));
		particleSystem
				.addParticleModifier(new RotationParticleModifier<Rectangle>(0,
						mTimePart, 0, 360));

		sceneManager.gameScene.getScene().attachChild(particleSystem);
		sceneManager.gameScene.getScene().registerUpdateHandler(
				new TimerHandler(mTimePart, new ITimerCallback() {
					@Override
					public void onTimePassed(final TimerHandler pTimerHandler) {
						particleSystem.detachSelf();
						sceneManager.gameScene.getScene().sortChildren();
						sceneManager.gameScene.getScene()
								.unregisterUpdateHandler(pTimerHandler);
					}
				}));

	}
}
