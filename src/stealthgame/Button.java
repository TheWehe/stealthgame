package stealthgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Button extends MenuPart {
	private Image image;
	private boolean currentlyDown;
	
	public Button(String name, Vector2f pos, Image img) {
		super(name, new Rectangle(pos.x, pos.y, img.getWidth(), img.getHeight()), pos);
		image = img;
		currentlyDown = false;
	}
	
	@Override
	public void mouseUp(Vector2f place) {
		currentlyDown = false;
	}

	@Override
	public void mouseDownInside() {
		currentlyDown = true;
	}

	@Override
	public void mouseUpInside() {
		if(currentlyDown)
		{
			super.getListener().buttonPressed(super.getName());
		}
	}

	@Override
	public void render(Graphics gfx) {
		if(currentlyDown)
		{
			Image scaled = image.getScaledCopy(0.95f);
			float diffW = image.getWidth() - scaled.getWidth();
			float diffH = image.getHeight() - scaled.getHeight();
			float x = super.getPosition().x + scaled.getWidth() / 2 + diffW / 2;
			float y = super.getPosition().y + scaled.getHeight() / 2 + diffH / 2;
			scaled.drawCentered(x, y);
		}
		else
		{
			gfx.drawImage(image, super.getPosition().x, super.getPosition().y);
		}
	}
}
