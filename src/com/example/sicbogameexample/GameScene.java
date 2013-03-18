package com.example.sicbogameexample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import sicbo.components.AnimationComponent;
import sicbo.components.BetComponent;
import sicbo.components.ButtonComponent;
import sicbo.components.CoinComponent;
import sicbo.components.DialogComponent;
import sicbo.components.DragComponent;
import sicbo.components.HistoryComponent;
import sicbo.components.ItemComponent;
import sicbo.components.PatternComponent;
import sicbo.components.PlayAnimationComponent;
import sicbo.components.TextComponent;
import sicbo.components.AbItemComponent.ItemType;
import sicbo_networks.ConnectionHandler;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sicbogameexample.GameEntity.GameAction;
import com.example.sicbogameexample.SceneManager.SceneType;

public class GameScene extends MyScene {

	public ItemComponent background;
	public DialogComponent confirmDialog;
	public DialogComponent yesnoDialog;
	public PlayAnimationComponent playAnimationComponent;

	// item list
	public ArrayList<ItemComponent> itemList;
	public ArrayList<ButtonComponent> buttonList;
	public ArrayList<PatternComponent> patternList;
	public ArrayList<DragComponent> dragList;
	public ArrayList<TextComponent> textList;
	public ArrayList<BetComponent> betList;

	// DialogComponent loadingDialog;

	public GameScene(Engine engine, Camera camera, BaseGameActivity activity) {
		super(engine, camera, activity);
		// TODO Auto-generated constructor stub
		playAnimationComponent = new PlayAnimationComponent(this);
		itemList = new ArrayList<ItemComponent>();
		buttonList = new ArrayList<ButtonComponent>();
		patternList = new ArrayList<PatternComponent>();
		dragList = new ArrayList<DragComponent>();
		textList = new ArrayList<TextComponent>();
		betList = new ArrayList<BetComponent>();
	}

	private void loadDialog(Font mFont) {
		// 0 0
		confirmDialog = new DialogComponent(1, 800, 480,
				"dialogbackground.png", -800, -480, getEngine()
						.getTextureManager(), getActivity(), getEngine(),
				ItemType.CONFIRM_DIALOG, 118, 38, "btnconfirm.png",
				GameEntity.CAMERA_WIDTH / 2 - 118 / 2, 300, mFont);
		// 0 0
		yesnoDialog = new DialogComponent(2, 800, 480, "dialogbackground.png",
				-800, -480, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.YESNO_DIALOG, 97, 38, 97, 38,
				"btnyes.png", "btnno.png", "", 250, 350, 380, 350, mFont);
	}

	private void loadText() {
		Font mChangableFont = FontFactory.create(getEngine().getFontManager(),
				getEngine().getTextureManager(), 512, 512,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32,
				Color.WHITE_ABGR_PACKED_INT);
		mChangableFont.load();
		textList.add(new TextComponent(1, 512, 512, GameEntity.userComponent
				.getBalance() + "", 281, 380, getEngine().getTextureManager(),
				getActivity(), getEngine(), ItemType.TEXT, 1, Color.WHITE,
				mChangableFont));
		/*
		 * textList.add(new TextComponent(2, 170, 20, "Max bet's 100 Zenny",
		 * -60, 395, getEngine().getTextureManager(), getActivity(),
		 * getEngine(), ItemType.TEXT, 0.5f, Color.WHITE, mChangableFont));
		 */
		textList.add(new TextComponent(3, 100, 23, GameEntity.betAmountRemain
				+ "", 612, 380, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TEXT, 1, Color.WHITE, mChangableFont));

		loadDialog(mChangableFont);
	}

	private void loadResourceItemList() {
		// background
		background = new ItemComponent(1, 800, 480, "background.jpg", 0, 0,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM);

		// Double 1 : 11
		itemList.add(new ItemComponent(1, 365, 15, "doublesign.png", 220, 77,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM));

		// Single 1 : 1 - 1 : 2 - 1 : 3
		itemList.add(new ItemComponent(1, 365, 15, "numbersign.png", 220, 239,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM));

		// triple 1 : 150 - 1 : 24 - 1 : 150
		itemList.add(new ItemComponent(1, 610, 15, "triplesign.png", 97, 363,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM));

		// balance and remain
		itemList.add(new ItemComponent(1, 610, 38, "balancesign.png", 97, 379,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM));

	}

	private void loadResourcePatternList() {
		// big small
		// Pattern
		patternList
				.add(new PatternComponent(2, 122, 81, "big.png", 97, 11,
						getEngine().getTextureManager(), getActivity(),
						getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
						GameEntity.PatternType.Big));

		patternList.add(new PatternComponent(3, 121, 81, "small.png", 586, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Small));

		// Double
		patternList.add(new PatternComponent(1, 60, 66, "double1.png", 220, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double1));
		patternList.add(new PatternComponent(1, 60, 66, "double2.png", 281, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double2));
		patternList.add(new PatternComponent(1, 60, 66, "double3.png", 342, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double3));
		patternList.add(new PatternComponent(1, 60, 66, "double4.png", 403, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double4));
		patternList.add(new PatternComponent(1, 60, 66, "double5.png", 464, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double5));
		patternList.add(new PatternComponent(1, 60, 66, "double6.png", 525, 11,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Double6));

		// Tree dice total
		patternList.add(new PatternComponent(7, 60, 80, "total4.png", 97, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice4));

		patternList.add(new PatternComponent(7, 60, 80, "total5.png", 159, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice5));

		patternList.add(new PatternComponent(7, 60, 80, "total6.png", 220, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice6));

		patternList.add(new PatternComponent(7, 60, 80, "total7.png", 281, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice7));

		patternList.add(new PatternComponent(7, 60, 80, "total8.png", 342, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice8));

		patternList.add(new PatternComponent(7, 60, 80, "total9.png", 403, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice9));

		patternList.add(new PatternComponent(7, 60, 80, "total10.png", 464, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice10));

		patternList.add(new PatternComponent(7, 60, 80, "total11.png", 525, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice11));

		patternList.add(new PatternComponent(7, 60, 80, "total12.png", 586, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice12));

		patternList.add(new PatternComponent(7, 60, 80, "total13.png", 647, 93,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice13));

		patternList.add(new PatternComponent(7, 60, 80, "total14.png", 97, 174,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice14));

		patternList.add(new PatternComponent(7, 60, 80, "total15.png", 159,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice15));

		patternList.add(new PatternComponent(7, 60, 80, "total16.png", 586,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice16));

		patternList.add(new PatternComponent(7, 60, 80, "total17.png", 647,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.ThreeDice17));

		// Single dice
		patternList.add(new PatternComponent(7, 60, 65, "number1.png", 220,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice1));
		patternList.add(new PatternComponent(7, 60, 65, "number2.png", 281,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice2));
		patternList.add(new PatternComponent(7, 60, 65, "number3.png", 342,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice3));
		patternList.add(new PatternComponent(7, 60, 65, "number4.png", 403,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice4));
		patternList.add(new PatternComponent(7, 60, 65, "number5.png", 464,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice5));
		patternList.add(new PatternComponent(7, 60, 65, "number6.png", 525,
				174, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.SingleDice6));

		// triple
		patternList.add(new PatternComponent(1, 60, 108, "triple1.png", 97,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple1));
		patternList.add(new PatternComponent(1, 60, 108, "triple2.png", 159,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple2));
		patternList.add(new PatternComponent(1, 60, 108, "triple3.png", 220,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple3));

		// All triple
		patternList.add(new PatternComponent(1, 243, 108, "alltriple.png", 281,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.AllTriple));

		// triple
		patternList.add(new PatternComponent(1, 60, 108, "triple4.png", 525,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple4));
		patternList.add(new PatternComponent(1, 60, 108, "triple5.png", 586,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple5));
		patternList.add(new PatternComponent(1, 60, 108, "triple6.png", 647,
				255, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TOUCHABLE_ITEM, getScene(),
				GameEntity.PatternType.Triple6));

	}

	private void loadResourceDragList() {
		dragList.add(new DragComponent(1, 31, 31, "coin-1.png", 459, 445,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.GRAG_ITEM, getScene(), CoinComponent.COINTID_1));
		dragList.add(new DragComponent(2, 31, 31, "coin-5.png", 497, 445,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.GRAG_ITEM, getScene(), CoinComponent.COINTID_5));
		dragList.add(new DragComponent(3, 31, 31, "coin-10.png", 535, 445,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.GRAG_ITEM, getScene(), CoinComponent.COINTID_10));
		dragList.add(new DragComponent(4, 31, 31, "coin-25.png", 572, 445,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.GRAG_ITEM, getScene(), CoinComponent.COINTID_25));
		dragList.add(new DragComponent(5, 31, 31, "coin-100.png", 610, 445,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.GRAG_ITEM, getScene(), CoinComponent.COINTID_100));
	}

	private void loadResourceButtonList() {
		// Button - Bottom

		buttonList.add(new ButtonComponent(59, 129, 46, 1, 1, "startbtn.png",
				671, 434, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_ROLL, getScene(),
				SceneType.ANIMATION));
		/*
		 * buttonList.add(new ButtonComponent(59, 139, 30, 2, 1, "btnexit.png",
		 * 725, 445, getEngine().getTextureManager(), getActivity(),
		 * getEngine(), ItemType.BUTTON_EXIT, getScene(), SceneType.ANIMATION));
		 */
		buttonList.add(new ButtonComponent(59, 129, 46, 1, 1, "resetbtn.png",
				277, 434, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_CLEAR, getScene(),
				SceneType.ANIMATION));
		buttonList.add(new ButtonComponent(59, 129, 46, 1, 1, "rebetbtn.png",
				142, 434, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_REBET, getScene(),
				SceneType.ANIMATION));
		buttonList.add(new ButtonComponent(59, 124, 47, 1, 1, "historybtn.png",
				0, 434, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_HISTORY, getScene(),
				SceneType.ANIMATION));

		// Button - top
		buttonList.add(new ButtonComponent(59, 160, 40, 4, 1, "btnsound.png",
				750, 3, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_SOUND, getScene(),
				SceneType.ANIMATION));

	}

	@Override
	public void loadResource() {
		// TODO Auto-generated method stub
		getScene().registerUpdateHandler(new FPSLogger());
		// getScene().setBackground(new Background(1f, 1f, 1f));
		getScene().setBackgroundEnabled(false);
		getScene().setOnAreaTouchTraversalFrontToBack();

		loadResourceItemList();
		loadResourceDragList();
		loadResourcePatternList();
		loadResourceButtonList();
		loadText();
		playAnimationComponent.loadResource();
	}

	@Override
	public void loadScene() {
		// TODO Auto-generated method stub
		// load background
		getScene().attachChild(background.getSprite());

		// load Game pattern
		for (int i = 0; i < patternList.size(); i++) {
			// itemList.get(i).setScale(1.5f);
			getScene().registerTouchArea(patternList.get(i).getSprite());
			getScene().attachChild(patternList.get(i).getSprite());
		}

		// load Game item
		for (int i = 0; i < itemList.size(); i++) {
			getScene().attachChild(itemList.get(i).getSprite());
		}

		// load game coin drag
		for (int i = 0; i < dragList.size(); i++) {
			getScene().registerTouchArea(dragList.get(i).getSprite());
			getScene().attachChild(dragList.get(i).getSprite());
			getScene().attachChild(dragList.get(i).tempDrag);
		}

		// load button
		for (int i = 0; i < buttonList.size(); i++) {
			// itemList.get(i).setScale(1.5f);
			getScene().registerTouchArea(buttonList.get(i).tiledSprite);
			getScene().attachChild(buttonList.get(i).tiledSprite);
		}

		// LoadText

		for (int i = 0; i < textList.size(); i++) {
			getScene().attachChild(textList.get(i).text);
		}

		// load animation
		playAnimationComponent.loadAniamtionScene();

		getScene().attachChild(playAnimationComponent.background.getSprite());
		// load Dialog
		getScene().attachChild(confirmDialog.getSprite());
		getScene().attachChild(yesnoDialog.getSprite());

		getScene().setTouchAreaBindingOnActionMoveEnabled(true);
		getScene().setTouchAreaBindingOnActionDownEnabled(true);
	}

	@Override
	public void disableAllTouch() {
		for (int i = 0; i < patternList.size(); i++) {
			getScene().unregisterTouchArea(patternList.get(i).getSprite());
			for (int j = 0; j < patternList.get(i).coinList.size(); j++) {
				getScene().unregisterTouchArea(
						patternList.get(i).coinList.get(j).getSprite());
			}
		}
		for (int i = 0; i < dragList.size(); i++) {
			getScene().unregisterTouchArea(dragList.get(i).getSprite());
		}
		for (int i = 0; i < buttonList.size(); i++) {
			getScene().unregisterTouchArea(buttonList.get(i).tiledSprite);
		}

	}

	@Override
	public void enableAllTouch() {

		for (int i = 0; i < patternList.size(); i++) {
			getScene().registerTouchArea(patternList.get(i).getSprite());
			if (GameEntity.gameAction != GameAction.RESET) {
				for (int j = 0; j < patternList.get(i).coinList.size(); j++) {
					getScene().registerTouchArea(
							patternList.get(i).coinList.get(j).getSprite());
				}
			}

		}

		for (int i = 0; i < dragList.size(); i++) {
			getScene().registerTouchArea(dragList.get(i).getSprite());
		}
		for (int i = 0; i < buttonList.size(); i++) {
			// itemList.get(i).setScale(1.5f);
			getScene().registerTouchArea(buttonList.get(i).tiledSprite);
		}
		/*
		 * for (int i = 0; i < coinList.size(); i++) {
		 * getScene().registerTouchArea(coinList.get(i).getSprite()); }
		 */
	}

	@Override
	public void unLoadScene() {
		// TODO Auto-generated method stub
		getScene().detachChildren();
	}

	public void startGame() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		disableAllTouch();
		int paramsSize = betList.size();
		String[] paramsName1 = new String[paramsSize];
		String[] paramsValue1 = new String[paramsSize];
		String[] paramsName2 = new String[paramsSize];
		String[] paramsValue2 = new String[paramsSize];

		for (int i = 0; i < paramsSize; i++) {
			paramsName1[i] = "betspots";
			paramsValue1[i] = betList.get(i).betPatternID + "";
			paramsName2[i] = "betamounts";
			paramsValue2[i] = betList.get(i).betAmount + "";
		}

		String[] paramsName = new String[paramsName1.length
				+ paramsName2.length];
		System.arraycopy(paramsName1, 0, paramsName, 0, paramsName2.length);
		System.arraycopy(paramsName2, 0, paramsName, paramsName1.length,
				paramsName2.length);

		String[] paramsValue = new String[paramsValue1.length
				+ paramsValue2.length];
		System.arraycopy(paramsValue1, 0, paramsValue, 0, paramsValue2.length);
		System.arraycopy(paramsValue2, 0, paramsValue, paramsValue1.length,
				paramsValue2.length);

		Object[] params = { GameEntity.connectionHandler, getActivity(),
				GameEntity.STARTGAME_TASK, paramsName, paramsValue };
		disableAllTouch();
		connectionAsync.execute(params);
	}

	public void viewHistory() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		Object[] params = { GameEntity.connectionHandler, getActivity(),
				GameEntity.VIEW_HISTORY, null, null };
		connectionAsync.execute(params);
	}

	public void exitGame() {
		ConnectionAsync connectionAsync = new ConnectionAsync();
		Object[] params = { GameEntity.connectionHandler, getActivity(),
				GameEntity.SIGNOUT_TASK, null, null };
		connectionAsync.execute(params);
		GameEntity.betAmountRemain = GameEntity.REMAIN_FIXED;
		// GameEntity.sceneManager.animationScene.unLoadScene();
		unLoadScene();
		getActivity().finish();
	}

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

				if (GameEntity.connectionHandler.getTaskID().equals("res_play")) {
					// move to animation scene
					if (result.getBoolean("is_success")) {
						GameEntity.currentGame.setGame(
								result.getBoolean("iswin"),
								result.getInt("dice1"), result.getInt("dice2"),
								result.getInt("dice3"),
								result.getDouble("current_balance"),
								result.getDouble("totalbetamount"),
								result.getDouble("totalwinamount"));
						GameEntity.userComponent.balance.balance = GameEntity.currentGame.newBalance;
						// GameEntity.sceneManager.setScene(SceneType.ANIMATION);
						GameEntity.sceneManager.gameScene.playAnimationComponent
								.playAnimation();

					} else {
						Log.d("Bet error", "Something wrong???");
					}
				} else if (GameEntity.connectionHandler.getTaskID().equals(
						"res_history")) {

					for (int i = 0; i < result.getInt("historyamount"); i++) {
						GameEntity.userComponent.historyList
								.add(new HistoryComponent(result.getJSONObject(
										i + "").getBoolean("iswin"), result
										.getJSONObject(i + "").getString(
												"betdate"), result
										.getJSONObject(i + "").getDouble(
												"balance")));
					}

					Intent intent = new Intent(activity,
							ViewHistoryActivity.class);
					activity.startActivity(intent);
				} else if (GameEntity.connectionHandler.getTaskID().equals(
						"res_signout")) {

				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
