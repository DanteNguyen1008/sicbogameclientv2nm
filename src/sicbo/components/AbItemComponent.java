package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureManager;

import android.content.Context;

public abstract class AbItemComponent {

	public AbItemComponent(int id, int width, int height, String background,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType) {
		setiID(id);
		setiWidth(width);
		setiHeight(height);
		setStrBackgroud(background);
		setPositionX(positionX);
		setPositionY(positionY);
		setEngine(engine);
		setContext(context);
		setiItemType(itemType);
	}

	private int iID;
	private String strBackgroud;
	private ItemType iItemType;

	public ItemType getiItemType() {
		return iItemType;
	}

	public void setiItemType(ItemType iItemType) {
		this.iItemType = iItemType;
	}

	public enum ItemType {
		NORMAL_ITEM, TOUCHABLE_ITEM, GRAG_ITEM, BUTTON_ROLL, BUTTON_CLEAR, BUTTON_REBET, BUTTON_HISTORY, BUTTON_NEXT, BUTTON_SOUND, BUTTON_EXIT, DICE_MIDDLE, DICE_LEFT, DICE_RIGHT, TEXT, YESNO_DIALOG, LOADING_DIALOG, CONFIRM_DIALOG, WIN_ANIMATION, LOSE_ANIMATION, DICE
	}

	public String getStrBackgroud() {
		return strBackgroud;
	}

	public void setStrBackgroud(String strBackgroud) {
		this.strBackgroud = strBackgroud;
	}

	public int getiID() {
		return iID;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public int getiWidth() {
		return iWidth;
	}

	public void setiWidth(int iWidth) {
		this.iWidth = iWidth;
	}

	public int getiHeight() {
		return iHeight;
	}

	public void setiHeight(int iHeight) {
		this.iHeight = iHeight;
	}

	public float getPositionX() {
		return positionX;
	}

	public void setPositionX(float positionX) {
		this.positionX = positionX;
	}

	public float getPositionY() {
		return positionY;
	}

	public void setPositionY(float positionY) {
		this.positionY = positionY;
	}

	private Sprite sprite;
	private int iWidth;
	private int iHeight;
	private float positionX;
	private float positionY;
	Engine engine;

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	Context context;

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
