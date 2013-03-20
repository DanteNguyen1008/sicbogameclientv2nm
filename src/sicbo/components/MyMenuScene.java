package sicbo.components;

import java.util.ArrayList;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import com.example.sicbogameexample.GameEntity;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

public class MyMenuScene extends AbItemComponent {
	ArrayList<ButtonComponent> itemList;
	public MyMenuScene(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType) {
		super(id, width, height, background, positionX, positionY,
				textureManager, context, engine, itemType);
		// TODO Auto-generated constructor stub
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, background, 0, 0);
		setSprite(new Sprite(positionX, positionY, atlasRegionBig,
				engine.getVertexBufferObjectManager()));
		atlastBig.load();
		hideMenu();
		itemList = new ArrayList<ButtonComponent>();
	}

	public void addItem(ButtonComponent item) {
		getSprite().attachChild(item.tiledSprite);
		itemList.add(item);
	}

	public void displayMenu() {
		getSprite().setPosition(0, 0);
	}

	public void hideMenu() {
		getSprite().setPosition(-GameEntity.CAMERA_WIDTH,
				-GameEntity.CAMERA_HEIGHT);
	}
	
	public void registerTouch(Scene parent)
	{
		for (int i = 0; i < itemList.size(); i++) {
			parent.registerTouchArea(itemList.get(i).tiledSprite);
		}
	}
	
	public void unRegisterTouch(Scene parent)
	{
		for (int i = 0; i < itemList.size(); i++) {
			parent.unregisterTouchArea(itemList.get(i).tiledSprite);
		}
	}

}
