package sicbo.components;

import java.util.List;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.util.debug.Debug;

import android.content.Context;

public class AnimationComponent extends AbItemComponent {
	Scene scene;
	List<TextComponent> texts;

	public AnimationComponent(int id, int width, int height, int animatedWidth,
			int animatedHeight, int animatedColum, int animatedRow,
			String background, float positionX, float positionY,
			TextureManager textureManager, Context context, Engine engine,
			ItemType itemType, Scene scene, List<TextComponent> texts) {
		super(id, width, height, background, positionX, positionY,
				textureManager, context, engine, itemType);
		this.animatedWidth = animatedWidth;
		this.animatedHeight = animatedHeight;
		this.animatedColum = animatedColum;
		this.animatedRow = animatedRow;
		this.scene = scene;
		this.texts = texts;
		BuildableBitmapTextureAtlas mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(
				textureManager, getiWidth(), getiHeight(),
				TextureOptions.NEAREST);
		TiledTextureRegion mDiceTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(mBitmapTextureAtlas, context,
						getStrBackgroud(), animatedColum, animatedRow);

		try {
			mBitmapTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 0, 0));
			mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		animatedSprite = new AnimatedSprite(getPositionX(), getPositionY(),
				mDiceTextureRegion, engine.getVertexBufferObjectManager());
		/*
		 * long time = (long) 50; if (itemType == ItemType.DICE_MIDDLE) {
		 * animatedSprite.animate(time, 0, new IAnimationListener() {
		 * 
		 * @Override public void onAnimationStarted(AnimatedSprite
		 * pAnimatedSprite, int pInitialLoopCount) { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void onAnimationLoopFinished( AnimatedSprite
		 * pAnimatedSprite, int pRemainingLoopCount, int pInitialLoopCount) { //
		 * TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void onAnimationFrameChanged( AnimatedSprite
		 * pAnimatedSprite, int pOldFrameIndex, int pNewFrameIndex) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void onAnimationFinished(AnimatedSprite
		 * pAnimatedSprite) { // TODO Auto-generated method stub
		 * displayResultText(); }
		 * 
		 * private void displayResultText() { // TODO Auto-generated method stub
		 * for (int i = 0; i < AnimationComponent.this.texts.size(); i++) {
		 * AnimationComponent.this.scene
		 * .attachChild(AnimationComponent.this.texts .get(i).text); }
		 * AnimationComponent.this.scene
		 * .registerTouchArea(GameEntity.sceneManager
		 * .gameScene.buttonAnimatedList .get(0).tiledSprite);
		 * AnimationComponent.this.scene
		 * .attachChild(GameEntity.sceneManager.gameScene
		 * .buttonAnimatedList.get(0).tiledSprite); } }); } else {
		 * animatedSprite.animate(50, false); }
		 */
	}

	public int animatedColum;

	public int animatedRow;

	public AnimatedSprite animatedSprite;

	public int animatedWidth;

	public int animatedHeight;
}
