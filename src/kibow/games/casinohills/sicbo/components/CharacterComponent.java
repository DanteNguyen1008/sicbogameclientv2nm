package kibow.games.casinohills.sicbo.components;

import org.andengine.entity.sprite.TiledSprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

public class CharacterComponent extends AbItemComponent {

	public static final int CHAR_STATUS_NORMAL = 0;
	public static final int CHAR_STATUS_WIN1 = 1;
	public static final int CHAR_STATUS_WIN2 = 2;
	public static final int CHAR_STATUS_LOSE1 = 3;
	public static final int CHAR_STATUS_LOSE2 = 4;
	public static final int CHAR_STATUS_LOSE3 = 5;

	public CharacterComponent(int id, int width, int height, int colum,
			int row, String background, float positionX, float positionY,
			BaseGameActivity activity, ItemType itemType) {
		super(id, width, height, background, positionX, positionY, activity,
				itemType);
		// TODO Auto-generated constructor stub
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(
				activity.getTextureManager(), width, height,
				TextureOptions.BILINEAR);

		ITiledTextureRegion tiledTextureRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(atlastBig, activity, background, 0, 0,
						colum, row);

		tiledSprite = new TiledSprite(positionX, positionY, tiledTextureRegion,
				activity.getVertexBufferObjectManager());

		tiledSprite.setCurrentTileIndex(CHAR_STATUS_NORMAL);
		atlastBig.load();
	}

	public void changeSprite(int index) {
		tiledSprite.setCurrentTileIndex(index);
	}

	public TiledSprite tiledSprite;
}
