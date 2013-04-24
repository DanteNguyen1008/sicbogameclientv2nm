package kibow.games.casinohills.sicbo.components;

import java.util.ArrayList;
import java.util.List;

import kibow.games.casinohills.sicbo.components.AbItemComponent.ItemType;
import kibow.games.casinohills.sicbo.screen.GameEntity;
import kibow.games.casinohills.sicbo.screen.GameScene;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.util.color.Color;

import android.graphics.Typeface;

public class PlayAnimationComponent implements IAnimationListener {

	GameScene scene;
	// public ItemComponent background;
	public Entity diceEntity;
	public Entity textEntity;
	public List<TextComponent> displayTextList;
	public ArrayList<AnimationComponent> animatedItemList;
	ArrayList<RectangleLine> rectWinList;
	public boolean showBackgroundResult = false;

	public PlayAnimationComponent(GameScene scene) {
		this.scene = scene;
		diceEntity = new Entity();
		textEntity = new Entity();
		rectWinList = new ArrayList<RectangleLine>();

	}

	public void loadText() {
		displayTextList = new ArrayList<TextComponent>();
		Font mChangableFont = FontFactory.create(scene.getEngine()
				.getFontManager(), scene.getEngine().getTextureManager(), 512,
				512, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 32,
				Color.WHITE_ABGR_PACKED_INT);
		mChangableFont.load();
		Font smallFont = FontFactory.create(scene.getEngine().getFontManager(),
				scene.getEngine().getTextureManager(), 512, 512,
				Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 25,
				Color.WHITE_ABGR_PACKED_INT);
		smallFont.load();
		// 300 50
		displayTextList.add(new TextComponent(1, 512, 512, "",
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT, scene
						.getActivity(), ItemType.TEXT, 0.6f, Color.BLACK,
				mChangableFont));
		// 200 100
		displayTextList.add(new TextComponent(2, 512, 512, "",
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT, scene
						.getActivity(), ItemType.TEXT, 0.6f, Color.BLACK,
				smallFont));
		// 200 150
		displayTextList.add(new TextComponent(3, 512, 512, "",
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT, scene
						.getActivity(), ItemType.TEXT, 0.6f, Color.BLACK,
				smallFont));
	}

	public void loadResource() {
		// TODO Auto-generated method stub
		// Load background

		/*
		 * background = new ItemComponent(1, 401, 250, "resultbg.png", -800,
		 * -480, scene.getEngine().getTextureManager(), scene.getActivity(),
		 * scene.getEngine(), ItemType.NORMAL_ITEM);
		 */
		loadText();

		// Load animation component
		animatedItemList = new ArrayList<AnimationComponent>();

		// Load dice
		// Left dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_LEFT, scene.getScene(), null));

		// Middle dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_MIDDLE, scene.getScene(), null));

		// Right dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getActivity(),
				ItemType.DICE_RIGHT, scene.getScene(), null));

	}

	public void loadAniamtionScene() {
		// attach all to background
		for (int i = 0; i < animatedItemList.size(); i++) {
			/*
			 * background.sprite.attachChild(
			 * animatedItemList.get(i).animatedSprite);
			 */
			// scene.getScene().attachChild(animatedItemList.get(i).animatedSprite);
			diceEntity.attachChild(animatedItemList.get(i).animatedSprite);

			if (i < 3) {
				textEntity.attachChild(displayTextList.get(i).text);
			}

		}

	}

	public void removeRectWin() {
		int rectWinSize = rectWinList.size();
		for (int i = 0; i < rectWinSize; i++) {
			rectWinList.get(i).removeRect();
		}
		rectWinList.clear();
	}

	void setEntityTextPosition() {
		textEntity.getChildByIndex(0).setPosition(
				GameEntity.CAMERA_WIDTH - 3 * GameEntity.CAMERA_WIDTH / 5 + 50,
				-10);
		textEntity.getChildByIndex(1).setPosition(
				GameEntity.CAMERA_WIDTH - 3 * GameEntity.CAMERA_WIDTH / 5, 20);
		textEntity.getChildByIndex(2).setPosition(
				GameEntity.CAMERA_WIDTH - 3 * GameEntity.CAMERA_WIDTH / 5, 40);
	}

	public void resetEntityPosition() {
		int size = scene.patternList.size();
		int size1 = scene.buttonList.size();
		int coinDragSize = scene.dragList.size();

		for (int i = 0; i < size; i++)

		{
			if (i < 4) {
				scene.itemList.get(i).sprite.setPosition(
						scene.itemList.get(i).sprite.getX(),
						scene.itemList.get(i).sprite.getY() - 70);
				if (i < 2) {
					scene.textList.get(i).text.setPosition(
							scene.textList.get(i).text.getX(),
							scene.textList.get(i).text.getY() - 70);
				}
			}

			scene.patternList.get(i).sprite.setPosition(
					scene.patternList.get(i).sprite.getX(),

					scene.patternList.get(i).sprite.getY() - 70);
			for (int m = 0; m < scene.patternList.get(i).coinList.size(); m++) {
				scene.patternList.get(i).coinList.get(m).sprite
						.setPosition(
								scene.patternList.get(i).coinList.get(m).sprite
										.getX(),
								scene.patternList.get(i).coinList.get(m).sprite
										.getY() - 70);
			}

		}

		for (int j = 0; j < size1; j++) {
			if (j >= 4) {
				if (j == 4) {
					scene.buttonList.get(j).tiledSprite.setPosition(
							scene.buttonList.get(j).tiledSprite.getX(),
							scene.buttonList.get(j).tiledSprite.getY() + 50);
				} else if (j == 5) {
					scene.buttonList.get(j).tiledSprite.setPosition(
							scene.buttonList.get(j).tiledSprite.getX(),
							scene.buttonList.get(j).tiledSprite.getY() - 160);
				}

			} else {
				scene.buttonList.get(j).tiledSprite.setPosition(
						scene.buttonList.get(j).tiledSprite.getX(),
						scene.buttonList.get(j).tiledSprite.getY() - 50);
			}

		}
		for (int n = 0; n < coinDragSize; n++) {
			scene.dragList.get(n).sprite.setPosition(scene.dragList.get(n)
					.getPositionX(), scene.dragList.get(n).getPositionY());
			scene.dragList.get(n).tempDrag.setPosition(
					scene.dragList.get(n).tempDrag.getX(),
					scene.dragList.get(n).tempDrag.getY() - 50);
		}

		scene.hudItem.sprite.setPosition(scene.hudItem.getPositionX(),
				scene.hudItem.getPositionY());

		// Resume runable text
		// scene.runableText.text.registerEntityModifier(scene.loopEntityModifier);
	}

	void changeEntityPosition() {
		int size = scene.patternList.size();
		int size1 = scene.buttonList.size();
		int coinDragSize = scene.dragList.size();

		for (int i = 0; i < size; i++)

		{
			if (i < 4) {
				scene.itemList.get(i).sprite.setPosition(
						scene.itemList.get(i).sprite.getX(),
						scene.itemList.get(i).sprite.getY() + 70);
				if (i < 2) {
					scene.textList.get(i).text.setPosition(
							scene.textList.get(i).text.getX(),
							scene.textList.get(i).text.getY() + 70);
				}
			}

			scene.patternList.get(i).sprite.setPosition(
					scene.patternList.get(i).sprite.getX(),

					scene.patternList.get(i).sprite.getY() + 70);
			for (int m = 0; m < scene.patternList.get(i).coinList.size(); m++) {
				scene.patternList.get(i).coinList.get(m).sprite
						.setPosition(
								scene.patternList.get(i).coinList.get(m).sprite
										.getX(),
								scene.patternList.get(i).coinList.get(m).sprite
										.getY() + 70);
			}

		}

		for (int j = 0; j < size1; j++) {
			if (j >= 4) {
				if (j == 4) {
					scene.buttonList.get(j).tiledSprite.setPosition(
							scene.buttonList.get(j).tiledSprite.getX(),

							scene.buttonList.get(j).tiledSprite.getY() - 50);
				} else if (j == 5) {
					scene.buttonList.get(j).tiledSprite.setPosition(
							scene.buttonList.get(j).tiledSprite.getX(),
							scene.buttonList.get(j).tiledSprite.getY() + 160);
				}

			} else {
				scene.buttonList.get(j).tiledSprite.setPosition(
						scene.buttonList.get(j).tiledSprite.getX(),
						scene.buttonList.get(j).tiledSprite.getY() + 50);
			}

		}
		for (int n = 0; n < coinDragSize; n++) {
			scene.dragList.get(n).sprite.setPosition(scene.dragList.get(n)
					.getPositionX(), scene.dragList.get(n).getPositionY() + 50);

			scene.dragList.get(n).tempDrag.setPosition(
					scene.dragList.get(n).tempDrag.getX(),
					scene.dragList.get(n).tempDrag.getY() + 50);
		}

		scene.hudItem.sprite.setPosition(scene.hudItem.getPositionX(),
				scene.hudItem.getPositionY() + 100);
		// Stop and change position of runnable text
		// scene.runableText.text
		// .unregisterEntityModifier(scene.loopEntityModifier);
		// scene.runableText.text.setPosition(scene.runableText.getPositionX(),
		// scene.runableText.getPositionY() + 100);
	}

	public void playAnimation() {
		GameEntity.getInstance().isAnimationRunning = true;
		for (int i = 0; i < animatedItemList.size(); i++) {
			if (animatedItemList.get(i).getiItemType() == ItemType.DICE_MIDDLE
					&& animatedItemList.get(i).getiID() == GameEntity
							.getInstance().currentGame.dice2) {
				animatedItemList.get(i).animatedSprite.animate(200, 0, this);
				animatedItemList.get(i).animatedSprite.setPosition(160, 110);
			} else if (animatedItemList.get(i).getiItemType() == ItemType.DICE_LEFT
					&& animatedItemList.get(i).getiID() == GameEntity
							.getInstance().currentGame.dice1) {
				animatedItemList.get(i).animatedSprite.animate(200, false);

				animatedItemList.get(i).animatedSprite.setPosition(46, 110);
			} else if (animatedItemList.get(i).getiItemType() == ItemType.DICE_RIGHT
					&& animatedItemList.get(i).getiID() == GameEntity
							.getInstance().currentGame.dice3) {
				animatedItemList.get(i).animatedSprite.animate(200, false);

				animatedItemList.get(i).animatedSprite.setPosition(276, 110);
			}
		}
		// scene.getScene().attachChild(diceEntity);
		// diceEntity.setPosition(50,-80);

		/*
		 * background.sprite.setPosition(199, 100);
		 * background.sprite.setZIndex(999);
		 * background.sprite.getParent().sortChildren();
		 */
		showBackgroundResult = true;

		GameEntity.getInstance().sceneManager.gameScene.enableAllTouch();
	}

	public void stopAnimation() {
		/*
		 * background.sprite.setPosition(-GameEntity.CAMERA_WIDTH,
		 * -GameEntity.CAMERA_HEIGHT);
		 */
		GameEntity.getInstance().mSensorListener.registerShake();
		hideResultText();
		resetCharacter();
	}

	/*
	 * Update character front of pattern and make animation (change tile index)
	 */
	private void updateCharacter() {
		scene.characterBoy.changeSprite(CharacterComponent.CHAR_STATUS_WIN1);
		scene.characterGirl.changeSprite(CharacterComponent.CHAR_STATUS_WIN1);

		scene.characterBoy.tiledSprite.setZIndex(99999);
		scene.characterGirl.tiledSprite.setZIndex(99999);
		scene.getScene().sortChildren();
	}

	/*
	 * Update character after get result
	 */

	private void updateCharacterAfterResult(boolean isWin) {
		if (isWin) {
			scene.characterBoy
					.changeSprite(CharacterComponent.CHAR_STATUS_WIN2);
			scene.characterGirl
					.changeSprite(CharacterComponent.CHAR_STATUS_WIN2);
		} else {
			scene.characterBoy
					.changeSprite(CharacterComponent.CHAR_STATUS_LOSE1);
			scene.characterGirl
					.changeSprite(CharacterComponent.CHAR_STATUS_LOSE1);
		}

	}

	/*
	 * Reset character to normal position and tile
	 */
	private void resetCharacter() {
		scene.characterBoy.changeSprite(CharacterComponent.CHAR_STATUS_NORMAL);
		scene.characterGirl.changeSprite(CharacterComponent.CHAR_STATUS_NORMAL);

		scene.characterBoy.tiledSprite.setZIndex(-1);
		scene.characterGirl.tiledSprite.setZIndex(-1);
		scene.background.sprite.setZIndex(-2);
		scene.getScene().sortChildren();
	}

	@Override
	public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
			int pInitialLoopCount) {
		// TODO Auto-generated method stub
		changeEntityPosition();
		updateCharacter();
		diceEntity.setScale(0.8f);
		diceEntity.setPosition(60, -80);
	}

	@Override
	public void onAnimationFrameChanged(AnimatedSprite pAnimatedSprite,
			int pOldFrameIndex, int pNewFrameIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationLoopFinished(AnimatedSprite pAnimatedSprite,
			int pRemainingLoopCount, int pInitialLoopCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationFinished(AnimatedSprite pAnimatedSprite) {
		// TODO Auto-generated method stub
		displayResultText();

		scene.disableAllTouch();

		setEntityTextPosition();

	}

	private void hideResultText() {
		GameEntity.getInstance().isResultDisplay = false;
		for (int i = 0; i < animatedItemList.size(); i++) {
			animatedItemList.get(i).animatedSprite.setPosition(
					-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT);
		}

		for (int i = 0; i < displayTextList.size(); i++) {
			if (displayTextList.get(i).getiID() == 1) {
				displayTextList.get(i).text.setPosition(
						-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT);
			}
			if (displayTextList.get(i).getiID() == 2) {
				displayTextList.get(i).text.setPosition(
						-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT);
			}
			if (displayTextList.get(i).getiID() == 3) {
				displayTextList.get(i).text.setPosition(
						-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT);
			}
		}

		displayWinPatterns(false);

	}

	private void displayWinPatterns(boolean isDisplay) {
		// Display win pattern.

		for (int i = 0; i < scene.patternList.size(); i++) {
			for (int j = 0; j < GameEntity.getInstance().currentGame.winPatterns
					.size(); j++) {
				if (scene.patternList.get(i).patternType == GameEntity
						.getInstance().currentGame.winPatterns.get(j)) {
					if (isDisplay) {
						rectWinList.add(new RectangleLine(scene.getScene(),
								scene.patternList.get(i).sprite.getX(),
								scene.patternList.get(i).sprite.getY(),
								scene.patternList.get(i).sprite.getWidth(),
								scene.patternList.get(i).sprite.getHeight(),
								scene.getVertextBuffer));
						GameEntity.getInstance().createFireWork(
								scene.patternList.get(i).getPositionX()
										+ scene.patternList.get(i).getiWidth()
										/ 2,
								scene.patternList.get(i).getPositionY() + 70
										+ scene.patternList.get(i).getiHeight()
										/ 2, 32, 32, Color.RED, 20, 2);
						GameEntity.getInstance().createFireWork(
								scene.patternList.get(i).getPositionX()
										+ scene.patternList.get(i).getiWidth()
										/ 2,
								scene.patternList.get(i).getPositionY() + 70
										+ scene.patternList.get(i).getiHeight()
										/ 2, 32, 32, Color.YELLOW, 20, 2);
					} else {
						scene.patternList.get(i).sprite.setAlpha(1f);

					}

				}
			}
		}

	}

	private void displayWinInLosePatterns(boolean isDisplay) {
		// Display win pattern.
		int size = GameEntity.getInstance().currentGame.winPatterns.size();

		if (GameEntity.getInstance().currentGame.winPatterns.size() != 0) {
			for (int i = 0; i < scene.patternList.size(); i++) {
				for (int j = 0; j < size; j++) {
					if (scene.patternList.get(i).patternType == GameEntity
							.getInstance().currentGame.winPatterns.get(j)) {
						if (isDisplay) {
							rectWinList
									.add(new RectangleLine(scene.getScene(),
											scene.patternList.get(i).sprite
													.getX(), scene.patternList
													.get(i).sprite.getY(),
											scene.patternList.get(i).sprite
													.getWidth(),
											scene.patternList.get(i).sprite
													.getHeight(),
											scene.getVertextBuffer));

							GameEntity.getInstance().createFireWork(
									scene.patternList.get(i).getPositionX()
											+ scene.patternList.get(i)
													.getiWidth() / 2,
									scene.patternList.get(i).getPositionY()
											+ 70
											+ scene.patternList.get(i)
													.getiHeight() / 2, 32, 32,
									Color.RED, 20, 2);
							GameEntity.getInstance().createFireWork(
									scene.patternList.get(i).getPositionX()
											+ scene.patternList.get(i)
													.getiWidth() / 2,
									scene.patternList.get(i).getPositionY()
											+ 70
											+ scene.patternList.get(i)
													.getiHeight() / 2, 32, 32,
									Color.YELLOW, 20, 2);

						} else {
							scene.patternList.get(i).sprite.setAlpha(1f);

						}

					}
				}
			}
		}
	}

	private void displayResultText() {

		GameEntity.getInstance().isResultDisplay = true;
		// TODO Auto-generated method stub
		String resultString = "You lose";

		String totalBetString = "Total money you bet : "
				+ GameEntity.getInstance().currentGame.totalBetAmount / 100;
		String totalWinString = "Total money you win : "
				+ ((GameEntity.getInstance().currentGame.totalWinAmount > 0) ? GameEntity
						.getInstance().currentGame.totalWinAmount / 100 : 0);
		if (GameEntity.getInstance().currentGame.isWin) {
			// Win
			scene.playWinSound(true);
			resultString = "You win";
			displayWinPatterns(true);
			// displayFireWork();
		} else {
			// Lose
			displayWinInLosePatterns(true);
			scene.playLoseSound(true);
		}
		updateCharacterAfterResult(GameEntity.getInstance().currentGame.isWin);

		for (int i = 0; i < displayTextList.size(); i++) {
			if (displayTextList.get(i).getiID() == 1) {
				displayTextList.get(i).text.setText(resultString);
				displayTextList.get(i).text.setPosition(136, 5);
			}
			if (displayTextList.get(i).getiID() == 2) {
				displayTextList.get(i).text.setText(totalBetString);
				displayTextList.get(i).text.setPosition(46, 40);
			}
			if (displayTextList.get(i).getiID() == 3) {
				displayTextList.get(i).text.setText(totalWinString);
				displayTextList.get(i).text.setPosition(46, 70);
			}
		}
		GameEntity.getInstance().isAnimationRunning = false;
	}
}
