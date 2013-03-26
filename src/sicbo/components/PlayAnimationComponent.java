package sicbo.components;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.util.color.Color;

import sicbo.components.AbItemComponent.ItemType;

import android.graphics.Typeface;

import com.example.sicbogameexample.GameEntity;
import com.example.sicbogameexample.GameScene;
import com.example.sicbogameexample.SceneManager.SceneType;

public class PlayAnimationComponent implements IAnimationListener {

	GameScene scene;
	public ItemComponent background;
	public List<TextComponent> displayTextList;
	public ArrayList<ButtonComponent> buttonAnimatedList;
	public ArrayList<AnimationComponent> animatedItemList;

	public PlayAnimationComponent(GameScene scene) {
		this.scene = scene;

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
						.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1, Color.WHITE,
				mChangableFont));
		// 200 100
		displayTextList.add(new TextComponent(2, 512, 512, "",
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT, scene
						.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1f, Color.WHITE, smallFont));
		// 200 150
		displayTextList.add(new TextComponent(3, 512, 512, "",
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT, scene
						.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1f, Color.WHITE, smallFont));
	}

	public void loadResource() {
		// TODO Auto-generated method stub
		// Load background

		background = new ItemComponent(1, 401, 250, "resultbg.png", -800, -480,
				scene.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.NORMAL_ITEM);
		loadText();
		buttonAnimatedList = new ArrayList<ButtonComponent>();
		buttonAnimatedList.add(new ButtonComponent(59, 203, 50, 2, 1,
				"btnTiledNext.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.BUTTON_NEXT, scene.getScene(),
				SceneType.GAME));

		// Load animation component
		animatedItemList = new ArrayList<AnimationComponent>();

		// Load dice
		// Left dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));

		// Middle dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));

		// Right dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1, 6,
				"dice1.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1, 6,
				"dice2.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1, 6,
				"dice3.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1, 6,
				"dice4.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1, 6,
				"dice5.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1, 6,
				"dice6.png", -GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
	}

	public void loadAniamtionScene() {
		// attach all to background
		for (int i = 0; i < animatedItemList.size(); i++) {
			background.getSprite().attachChild(
					animatedItemList.get(i).animatedSprite);
		}

		for (int i = 0; i < displayTextList.size(); i++) {
			background.getSprite().attachChild(displayTextList.get(i).text);
		}

		background.getSprite().attachChild(
				buttonAnimatedList.get(0).tiledSprite);
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
		background.getSprite().setPosition(199, 100);
		background.getSprite().setZIndex(999);
		background.getSprite().getParent().sortChildren();

	}

	public void stopAnimation() {
		background.getSprite().setPosition(-GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT);
		GameEntity.getInstance().mSensorListener.registerShake();
		hideResultText();
	}

	@Override
	public void onAnimationStarted(AnimatedSprite pAnimatedSprite,
			int pInitialLoopCount) {
		// TODO Auto-generated method stub

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
		buttonAnimatedList.get(0).tiledSprite.setPosition(
				-GameEntity.CAMERA_WIDTH, -GameEntity.CAMERA_HEIGHT);

		scene.getScene().unregisterTouchArea(
				buttonAnimatedList.get(0).tiledSprite);
		background.getSprite().setZIndex(0);
		background.getSprite().getParent().sortChildren();

		displayWinPatterns(false);
		GameEntity.getInstance().isAnimationRunning = false;
	}

	private void displayWinPatterns(boolean isDisplay) {
		// Display win pattern.
		for (int i = 0; i < scene.patternList.size(); i++) {
			for (int j = 0; j < GameEntity.getInstance().currentGame.winPatterns
					.size(); j++) {
				if (scene.patternList.get(i).patternType == GameEntity
						.getInstance().currentGame.winPatterns.get(j)) {
					if (isDisplay) {
						scene.patternList.get(i).getSprite().setAlpha(0.5f);
						GameEntity.getInstance().createFireWork(
								scene.patternList.get(i).getPositionX()
										+ scene.patternList.get(i).getiWidth()
										/ 2,
								scene.patternList.get(i).getPositionY()
										+ scene.patternList.get(i).getiHeight()
										/ 2, 32, 32, Color.RED, 20, 2);
						GameEntity.getInstance().createFireWork(
								scene.patternList.get(i).getPositionX()
										+ scene.patternList.get(i).getiWidth()
										/ 2,
								scene.patternList.get(i).getPositionY()
										+ scene.patternList.get(i).getiHeight()
										/ 2, 32, 32, Color.YELLOW, 20, 2);
					} else {
						scene.patternList.get(i).getSprite().setAlpha(1f);

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
				+ GameEntity.getInstance().currentGame.totalBetAmount;
		String totalWinString;
		if (GameEntity.getInstance().currentGame.isWin) {
			totalWinString = "Total money you win : "
					+ ((GameEntity.getInstance().currentGame.totalWinAmount > 0) ? GameEntity
							.getInstance().currentGame.totalWinAmount : 0);
			// Win
			scene.playWinSound(true);
			resultString = "You win";
			displayWinPatterns(true);
			// displayFireWork();
		} else {
			// Lose
			totalWinString = "Total money you lose : "
					+ GameEntity.getInstance().currentGame.totalWinAmount;
			scene.playLoseSound(true);
		}

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

		buttonAnimatedList.get(0).tiledSprite.setPosition(160, 195);
		scene.getScene().registerTouchArea(
				buttonAnimatedList.get(0).tiledSprite);

	}

	private void displayFireWork() {
		GameEntity.getInstance().createFireWork(50, 50, 32, 32, Color.RED, 20,
				3);

		GameEntity.getInstance().createFireWork(50, 50, 32, 32, Color.BLUE, 10,
				3);

		GameEntity.getInstance().createFireWork(300, 50, 32, 32, Color.GREEN,
				20, 3);

		GameEntity.getInstance().createFireWork(300, 50, 32, 32, Color.PINK,
				10, 3);

		GameEntity.getInstance().createFireWork(400, 240, 32, 32, Color.YELLOW,
				20, 3);

		GameEntity.getInstance().createFireWork(400, 240, 32, 32, Color.CYAN,
				10, 3);
	}
}
