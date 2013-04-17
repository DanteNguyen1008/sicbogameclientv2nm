package com.example.sicbogameexample;

import java.util.ArrayList;

import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;

import sicbo.components.AbItemComponent.ItemType;
import sicbo.components.BetComponent;
import sicbo.components.ButtonComponent;
import sicbo.components.CoinComponent;
import sicbo.components.DialogComponent;
import sicbo.components.DragComponent;
import sicbo.components.ItemComponent;
import sicbo.components.MSComponent;
import sicbo.components.MSComponent.MStype;
import sicbo.components.MyMenuScene;
import sicbo.components.ParticleSystemComponent;
import sicbo.components.PatternComponent;
import sicbo.components.PlayAnimationComponent;
import sicbo.components.ShakeEventListener.OnShakeListener;
import sicbo.components.TextComponent;
import android.graphics.Typeface;
import android.util.Log;

import com.example.sicbogameexample.GameEntity.GameAction;
import com.example.sicbogameexample.SceneManager.SceneType;

public class GameScene extends MyScene implements OnShakeListener ,IOnSceneTouchListener{

	public ItemComponent background;
	public DialogComponent confirmDialog;
	public DialogComponent confirmErrorDialog;
	public DialogComponent yesnoDialog;
	public PlayAnimationComponent playAnimationComponent;

	// item list
	public ArrayList<ItemComponent> itemList;
	public ArrayList<ButtonComponent> buttonList;
	public ArrayList<PatternComponent> patternList;
	public ArrayList<DragComponent> dragList;
	public ArrayList<TextComponent> textList;
	public ArrayList<BetComponent> betList;
	public ArrayList<ParticleSystemComponent> fireworkList;

	// Music and Sound
	public MSComponent backgroundMusic;
	public MSComponent betSound;
	public MSComponent releaseBetSound;
	public MSComponent winSound;
	public MSComponent loseSound;

	// DialogComponent loadingDialog;
	// Menu
	MyMenuScene menuScene;

	TextComponent runableText;
	 public VertexBufferObjectManager getVertextBuffer;

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
		fireworkList = new ArrayList<ParticleSystemComponent>();
		getVertextBuffer=activity.getVertexBufferObjectManager();
		GameEntity.getInstance().mSensorListener.setOnShakeListener(this);

	}

	private void loadRunableText() {
		Font mSmallFont = FontFactory.create(getEngine().getFontManager(),
				getEngine().getTextureManager(), 512, 512,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 22,
				Color.WHITE_ABGR_PACKED_INT);
		mSmallFont.load();
		runableText = new TextComponent(1, 800, 30,
				GameEntity.getInstance().runableTextContent,
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT,
				getActivity().getTextureManager(), getActivity(), getEngine(),
				ItemType.TEXT, 1, Color.BLUE, mSmallFont);

		MoveModifier move = new MoveModifier(20, 800, -800, 415, 415);
		LoopEntityModifier loopEntityModifier = new LoopEntityModifier(move);
		runableText.text.registerEntityModifier(loopEntityModifier);
	}

	private void loadDialog(Font mFont) {
		// 0 0
		confirmDialog = new DialogComponent(1, 800, 480,
				"dialogbackground.png", -800, -480, getEngine()
						.getTextureManager(), getActivity(), getEngine(),
				ItemType.CONFIRM_DIALOG, 118, 38, "btnconfirm.png",
				GameEntity.CAMERA_WIDTH / 2 - 118 / 2, 300, mFont);
		
		confirmErrorDialog = new DialogComponent(1, 800, 480,
				"dialogbackground.png", -800, -480, getEngine()
						.getTextureManager(), getActivity(), getEngine(),
				ItemType.CONFIRM_ERROR, 118, 38, "btnconfirm.png",
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
		textList.add(new TextComponent(1, 512, 512,
				GameEntity.getInstance().userComponent.getBalance() + "", 281,
				380, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.TEXT, 1, Color.WHITE, mChangableFont));
		textList.add(new TextComponent(3, 100, 23,
				GameEntity.getInstance().betAmountRemain + "", 612, 380,
				getEngine().getTextureManager(), getActivity(), getEngine(),
				ItemType.TEXT, 1, Color.WHITE, mChangableFont));

		loadDialog(mChangableFont);
	}

	private void loadMenuScene() {
		menuScene = new MyMenuScene(1, 800, 480, "dialogbackground.png", 0, 0,
				getActivity().getTextureManager(), getActivity(), getActivity()
						.getEngine(), ItemType.NORMAL_ITEM);
		menuScene.addItem(new ButtonComponent(1, 200, 36, 1, 1, "resume.jpg",
				300, 50, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.MENU_RESUME, getScene(), SceneType.GAME));
		menuScene
				.addItem(new ButtonComponent(1, 200, 36, 1, 1, "profile.jpg",
						300, 120, getEngine().getTextureManager(),
						getActivity(), getEngine(), ItemType.MENU_PROFILE,
						getScene(), SceneType.GAME));
		menuScene.addItem(new ButtonComponent(1, 200, 36, 1, 1, "help.jpg",
				300, 190, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.MENU_HELP, getScene(), SceneType.GAME));
		menuScene.addItem(new ButtonComponent(1, 200, 36, 1, 1, "logout.jpg",
				300, 260, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.MENU_LOGOUT, getScene(), SceneType.GAME));
		menuScene.addItem(new ButtonComponent(1, 200, 36, 1, 1, "exit.jpg",
				300, 330, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.MENU_EXIT, getScene(), SceneType.GAME));

	}

	private void loadMusicAndSound() {
		SoundFactory.setAssetBasePath("mfx/");
		MusicFactory.setAssetBasePath("mfx/");
		backgroundMusic = new MSComponent(1, "themesong.mp3", MStype.MUSIC,
				getEngine(), getActivity(), true);
		betSound = new MSComponent(2, "betcoin.wav", MStype.SOUND, getEngine(),
				getActivity());
		releaseBetSound = new MSComponent(3, "pickcoin.mp3", MStype.SOUND,
				getEngine(), getActivity());
		winSound = new MSComponent(4, "cheer.mp3", MStype.SOUND, getEngine(),
				getActivity());
		loseSound = new MSComponent(5, "fail.mp3", MStype.SOUND, getEngine(),
				getActivity());
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
		buttonList.add(new ButtonComponent(59, 50, 50, 1, 1, "menubtn.png",
				745, 320, getEngine().getTextureManager(), getActivity(),
				getEngine(), ItemType.BUTTON_MENU, getScene(),
				SceneType.ANIMATION));

	}

	public void loadFireworkResource() {
		fireworkList.add(new ParticleSystemComponent(1, 32, 32,
				"particle_point.png", -800, -480, getEngine()
						.getTextureManager(), getActivity(), getEngine(),
				ItemType.NORMAL_ITEM, Color.RED, 20, 2, getScene()));
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
		loadMusicAndSound();
		playAnimationComponent.loadResource();
		loadMenuScene();
		loadRunableText();
	}

	@Override
	public void loadScene() {
		// TODO Auto-generated method stub
		// load background
		getScene().setOnSceneTouchListener(this);
	
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
		getScene().attachChild(playAnimationComponent.diceEntity);
		getScene().attachChild(playAnimationComponent.textEntity);

		//getScene().attachChild(playAnimationComponent.background.getSprite());
		// load Dialog
		getScene().attachChild(confirmDialog.getSprite());
		getScene().attachChild(confirmErrorDialog.getSprite());
		getScene().attachChild(yesnoDialog.getSprite());

		getScene().setTouchAreaBindingOnActionMoveEnabled(true);
		getScene().setTouchAreaBindingOnActionDownEnabled(true);

		// add partical
		// getScene().attachChild(particleSystem);
		// menuScene.attachMenu(getScene());

		getScene().attachChild(menuScene.getSprite());
		menuScene.registerTouch(getScene());
		getScene().attachChild(runableText.text);

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
			if (GameEntity.getInstance().gameAction != GameAction.RESET) {
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
			getScene().registerTouchArea(buttonList.get(i).tiledSprite);
		}
	}

	@Override
	public void unLoadScene() {
		// TODO Auto-generated method stub
		if (!backgroundMusic.music.isReleased())
			backgroundMusic.music.release();
		getScene().detachChildren();
	}

	int shakeCount = 0;

	@Override
	public void onShake() {
		// TODO Auto-generated method stub
		if (shakeCount >= 3) {
			GameEntity.getInstance().startGame();
			shakeCount = 0;
		}
		shakeCount++;
	}

	public void buttonPlaySound() {
		if (releaseBetSound != null)
			releaseBetSound.play();
	}

	public void playWinSound(boolean isPlay) {

		winSound.play();

	}

	public void playLoseSound(boolean isPlay) {
		if (loseSound != null) {
			if (isPlay)
				loseSound.play();
			else
				loseSound.stop();
		}
	}

	public void displayMenu() {
		disableAllTouch();
		GameEntity.getInstance().isMenuDisplay = true;
		GameEntity.getInstance().mSensorListener.stopRegisterShake();
		menuScene.displayMenu();
		if (GameEntity.getInstance().isResultDisplay) {
			GameEntity.getInstance().updateAfterBet();
			playAnimationComponent.stopAnimation();
		}

	}

	public void hideMenu() {
		enableAllTouch();
		GameEntity.getInstance().isMenuDisplay = false;
		GameEntity.getInstance().mSensorListener.registerShake();
		menuScene.hideMenu();
	}

	public void onBackButtonPress(boolean isDisplay) {
		if (isDisplay) {
			GameEntity.getInstance().displayYesNoDialog("Do you want to exit?",
					200, 300);
			GameEntity.getInstance().mSensorListener.stopRegisterShake();
			GameEntity.getInstance().isBackPress = true;
		} else {
			GameEntity.getInstance().mSensorListener.registerShake();
			GameEntity.getInstance().isBackPress = false;
		}
	}


	@Override
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		
    if(pSceneTouchEvent.isActionUp()) {
            if(GameEntity.getInstance().sceneManager.gameScene.
            		playAnimationComponent.showBackgroundResult==true)
            {
            	GameEntity.getInstance().sceneManager.gameScene
				.buttonPlaySound();
		GameEntity.getInstance().updateAfterBet();
		GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent
				.stopAnimation();
		GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent.removeRectWin();
		GameEntity.getInstance().sceneManager.gameScene.playAnimationComponent.resetEntityPosition();
		GameEntity.getInstance().sceneManager.gameScene.
		playAnimationComponent.showBackgroundResult=false;
            }
    }
   
    return true;
		
	}
	
}
