package com.example.sicbogameexample;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;

import sicbo.components.CoinComponent;
import sicbo.components.GameComponent;
import sicbo.components.UserComponent;
import sicbo_networks.ConnectionHandler;

public class GameEntity {
	public final static int CAMERA_WIDTH = 800;
	public final static int CAMERA_HEIGHT = 480;

	public final static String SIGNIN_TASK = "signin";
	public final static String SIGNUP_TASK = "signup";
	public final static String SIGNOUT_TASK = "signout";
	public final static String STARTGAME_TASK = "play_bet";
	public final static String VIEW_HISTORY = "view_history";

	public static SceneManager sceneManager;

	public static ConnectionHandler connectionHandler;

	public static int currentScreen;

	public static Scene splashScene;
	public static Scene GameScene;
	public static Scene helpScene;
	public static Scene historyScene;
	public static Scene animatedSene;

	public static EngineOptions engineOptions;

	public static GameComponent currentGame;

	public static int currentCoint = CoinComponent.COINTID_1;

	public enum GameAction {
		BETING, REBET, RESET
	}

	public static GameAction gameAction = GameAction.BETING;

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

	public static UserComponent userComponent;

	public static final double REMAIN_FIXED = 100;
	public static double betAmountRemain = REMAIN_FIXED;
	
	public final static int miniCoiWidth = 31;
	public final static int miniCoinHeight = 31;
}
