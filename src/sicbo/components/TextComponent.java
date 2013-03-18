package sicbo.components;

import org.andengine.engine.Engine;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.example.sicbogameexample.GameEntity;


import android.content.Context;

public class TextComponent extends AbItemComponent {
	public TextComponent(int id, int width, int height, String content,
			float positionX, float positionY, TextureManager textureManager,
			Context context, Engine engine, ItemType itemType, float textSize,
			Color textColor, Font mFont) {
		super(id, width, height, "", positionX, positionY, textureManager, context, engine, itemType);
		//mFont.load();
		text = new Text(positionX, positionY, mFont, content,100,new TextOptions(
				HorizontalAlign.LEFT), engine.getVertexBufferObjectManager());
		text.setColor(textColor);
		text.setScale(textSize);
	}

	public Text text;

	public void increaseBetRemain(double amount)
	{
		GameEntity.betAmountRemain += amount;
		this.text.setText(GameEntity.betAmountRemain+"");
	}
	
	public void decreaseBetRemain(double amount)
	{
		GameEntity.betAmountRemain -= amount;
		this.text.setText(GameEntity.betAmountRemain+"");
	}
	
	public void updateBetRemain(double amount)
	{
		GameEntity.betAmountRemain = amount;
		this.text.setText(GameEntity.betAmountRemain+"");
	}
	
	public void updateBalance(UserComponent.UserAction action, double amount)
	{
		this.text.setText(GameEntity.userComponent.updateBalance(action, amount)+ "");
	}

}
