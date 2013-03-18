package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

import com.example.sicbogameexample.GameEntity;

import android.content.Context;

public class CoinComponent extends AbItemComponent {
	public static final int COINTID_1 = 1;
	public static final int COINTID_5 = 5;
	public static final int COINTID_10 = 10;
	public static final int COINTID_25 = 25;
	public static final int COINTID_100 = 100;

	public static final String cointName_1 = "coin-1.png";
	public static final String cointName_5 = "coin-5.png";
	public static final String cointName_10 = "coin-10.png";
	public static final String cointName_25 = "coin-25.png";
	public static final String cointName_100 = "coin-100.png";

	CoinSprite coin;
	//public int patternID;
	public PatternComponent pattern;
	

	public CoinComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, int coinID, PatternComponent pattern,ItemType itemType) {
		super(id, width, height, background, positionX, positionY, textureManager, context, engine, itemType);
		this.CoinID = coinID;
		this.pattern = pattern;
		String currentBackground = "";
		switch (this.CoinID) {
		case CoinComponent.COINTID_1:
			currentBackground = CoinComponent.cointName_1;
			break;
		case CoinComponent.COINTID_5:
			currentBackground = CoinComponent.cointName_5;
			break;
		case CoinComponent.COINTID_10:
			currentBackground = CoinComponent.cointName_10;
			break;
		case CoinComponent.COINTID_25:
			currentBackground = CoinComponent.cointName_25;
			break;
		case CoinComponent.COINTID_100:
			currentBackground = CoinComponent.cointName_100;
			break;
		default:
			break;
		}
		
		BitmapTextureAtlas atlastBig = new BitmapTextureAtlas(textureManager,
				width, height, TextureOptions.BILINEAR);

		ITextureRegion atlasRegionBig = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(atlastBig, context, currentBackground, 0, 0);

		coin = new CoinSprite(positionX, positionY,width,height,coinID, atlasRegionBig,
				engine.getVertexBufferObjectManager(), this);
		setSprite(coin.obtainPoolItem());
		atlastBig.load();
	}

	private int CoinID;

	public int getCoinID() {
		return CoinID;
	}

	public void setCoinID(int coinID) {
		CoinID = coinID;
	}

	public void removeCoin() {
		// coin.recyclePoolItem(getSprite());
		coin.onHandleRecycleItem(getSprite());
		//pattern.sortCoinList(this);
		//pattern.coinList.remove(this);
	}

	public void reBuildCoin() {
		coin.onHandleObtainItem(getSprite());
	}
	
	public void deleteItSeft()
	{
		pattern.sortCoinList(this);
		
	}	
}
