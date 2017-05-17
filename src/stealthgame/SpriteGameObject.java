package stealthgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public abstract class SpriteGameObject extends GameObject {
	private Image image;
	
	public SpriteGameObject(String n, boolean d, Vector2f p, float a, Image img)
	{
		super(n, d, p, a);
		image = img;
	}
	
	@Override
	public void render()
	{
		image.setRotation(angle);
		image.drawCentered(position.x, position.y);
	}
}
