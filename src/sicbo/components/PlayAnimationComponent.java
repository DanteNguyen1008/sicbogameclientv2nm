package sicbo.components;

import java.util.ArrayList;
import java.util.List;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.AnimatedSprite.IAnimationListener;
import org.andengine.entity.sprite.Sprite;
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

		// 300 50
		displayTextList.add(new TextComponent(1, 512, 512, "", -800, -480,
				scene.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1, Color.WHITE,
				mChangableFont));
		// 200 100
		displayTextList.add(new TextComponent(2, 512, 512, "", -800, -480,
				scene.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1, Color.WHITE,
				mChangableFont));
		// 200 150
		displayTextList.add(new TextComponent(3, 512, 512, "", -800, -480,
				scene.getEngine().getTextureManager(), scene.getActivity(),
				scene.getEngine(), ItemType.TEXT, 1, Color.WHITE,
				mChangableFont));
	}

	public void loadResource() {
		// TODO Auto-generated method stub
		// Load background

		background = new ItemComponent(1, 800, 480, "dialogbackground.png",
				-800, -480, scene.getEngine().getTextureManager(),
				scene.getActivity(), scene.getEngine(), ItemType.NORMAL_ITEM);
		loadText();
		buttonAnimatedList = new ArrayList<ButtonComponent>();
		buttonAnimatedList.add(new ButtonComponent(59, 203, 50, 2, 1,
				"btnTiledNext.png", -800, -480, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.BUTTON_NEXT, scene.getScene(),
				SceneType.GAME));

		// Load animation component
		animatedItemList = new ArrayList<AnimationComponent>();

		//Load dice
		//Left dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1,
				6, "dice1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1,
				6, "dice2.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1,
				6, "dice3.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1,
				6, "dice4.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1,
				6, "dice5.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1,
				6, "dice6.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_LEFT, scene.getScene(),
				null));
		
		//Middle dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1,
				6, "dice1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1,
				6, "dice2.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1,
				6, "dice3.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1,
				6, "dice4.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1,
				6, "dice5.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1,
				6, "dice6.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_MIDDLE, scene.getScene(),
				null));
		
		//Right dice
		animatedItemList.add(new AnimationComponent(1, 84, 426, 64, 64, 1,
				6, "dice1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 84, 426, 64, 64, 1,
				6, "dice2.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 84, 426, 64, 64, 1,
				6, "dice3.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 84, 426, 64, 64, 1,
				6, "dice4.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(5, 84, 426, 64, 64, 1,
				6, "dice5.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(6, 84, 426, 64, 64, 1,
				6, "dice6.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.DICE_RIGHT, scene.getScene(),
				null));

		// Win animation
		animatedItemList.add(new AnimationComponent(1, 238, 237, 64, 64, 3, 3,
				"ef_red_boom_1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.WIN_ANIMATION, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(2, 238, 237, 64, 64, 3, 3,
				"ef_blue_boom_1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.WIN_ANIMATION, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(3, 238, 237, 64, 64, 3, 3,
				"ef_red_boom_1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.WIN_ANIMATION, scene.getScene(),
				null));
		animatedItemList.add(new AnimationComponent(4, 238, 237, 64, 64, 3, 3,
				"ef_blue_boom_1.png", -500, -500, scene.getEngine()
						.getTextureManager(), scene.getActivity(), scene
						.getEngine(), ItemType.WIN_ANIMATION, scene.getScene(),
				null));
	}

	public void loadAniamtionScene() {
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

		for (int i = 0; i < animatedItemList.size(); i++) {
			if (animatedItemList.get(i).getiItemType() == ItemType.DICE_MIDDLE
					&& animatedItemList.get(i).getiID() == GameEntity.currentGame.dice2) {
				animatedItemList.get(i).animatedSprite.animate(200, 0, this);
				animatedItemList.get(i).animatedSprite.setPosition(330, 200);
			} else if (animatedItemList.get(i).getiItemType() == ItemType.DICE_LEFT
					&& animatedItemList.get(i).getiID() == GameEntity.currentGame.dice1) {
				animatedItemList.get(i).animatedSprite.animate(200, false);

				animatedItemList.get(i).animatedSprite.setPosition(200, 200);
			} else if (animatedItemList.get(i).getiItemType() == ItemType.DICE_RIGHT
					&& animatedItemList.get(i).getiID() == GameEntity.currentGame.dice3) {
				animatedItemList.get(i).animatedSprite.animate(200, false);

				animatedItemList.get(i).animatedSprite.setPosition(470, 200);
			}
		}
		background.getSprite().setPosition(0, 0);
		background.getSprite().setZIndex(999);
		background.getSprite().getParent().sortChildren();
		
		//Display win pattern.
		for (int i = 0; i < scene.patternList.size(); i++) {
			
		}
	}

	public void stopAnimation() {
		background.getSprite().setPosition(-800, -480);
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

		for (int i = 0; i < animatedItemList.size(); i++) {
			animatedItemList.get(i).animatedSprite.setPosition(-500, -500);
		}

		for (int i = 0; i < displayTextList.size(); i++) {
			if (displayTextList.get(i).getiID() == 1) {
				displayTextList.get(i).text.setPosition(-500, -500);
			}
			if (displayTextList.get(i).getiID() == 2) {
				displayTextList.get(i).text.setPosition(-500, -500);
			}
			if (displayTextList.get(i).getiID() == 3) {
				displayTextList.get(i).text.setPosition(-500, -500);
			}
		}
		buttonAnimatedList.get(0).tiledSprite.setPosition(-800, -480);

		scene.getScene().unregisterTouchArea(
				buttonAnimatedList.get(0).tiledSprite);
		background.getSprite().setZIndex(0);
		background.getSprite().getParent().sortChildren();
	}

	private void displayResultText() {
		// TODO Auto-generated method stub
		String resultString = "You lose";
		String totalBetString = "Total money you bet : "
				+ GameEntity.currentGame.totalBetAmount;
		String totalWinString = "Total money you get : "
				+ GameEntity.currentGame.totalWinAmount;
		if (GameEntity.currentGame.isWin) {
			// Win
			resultString = "You win";
			for (int i = 0; i < animatedItemList.size(); i++) {
				if (animatedItemList.get(i).getiItemType() == ItemType.WIN_ANIMATION) {
					
					animatedItemList.get(i).animatedSprite.animate(100, true);
					if (animatedItemList.get(i).getiID() == 1) {
						animatedItemList.get(i).setPositionX(100);
						animatedItemList.get(i).setPositionY(100);
					}
					if (animatedItemList.get(i).getiID() == 2) {
						animatedItemList.get(i).setPositionX(150);
						animatedItemList.get(i).setPositionY(30);
					}
					if (animatedItemList.get(i).getiID() == 3) {
						animatedItemList.get(i).setPositionX(500);
						animatedItemList.get(i).setPositionY(30);
					}
					if (animatedItemList.get(i).getiID() == 4) {
						animatedItemList.get(i).setPositionX(600);
						animatedItemList.get(i).setPositionY(100);
					}
					animatedItemList.get(i).animatedSprite.setPosition(animatedItemList.get(i).getPositionX(),
							animatedItemList.get(i).getPositionY());
				}
			}
		} else {
			// Lose
		}

		for (int i = 0; i < displayTextList.size(); i++) {
			if (displayTextList.get(i).getiID() == 1) {
				displayTextList.get(i).text.setText(resultString);
				displayTextList.get(i).text.setPosition(300, 50);
			}
			if (displayTextList.get(i).getiID() == 2) {
				displayTextList.get(i).text.setText(totalBetString);
				displayTextList.get(i).text.setPosition(200, 100);
			}
			if (displayTextList.get(i).getiID() == 3) {
				displayTextList.get(i).text.setText(totalWinString);
				displayTextList.get(i).text.setPosition(200, 150);
			}
		}

		buttonAnimatedList.get(0).tiledSprite.setPosition(350, 350);
		scene.getScene().registerTouchArea(
				buttonAnimatedList.get(0).tiledSprite);

	}
}
