package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Button extends MenuPart {
	private Image image = null;

	public Button(String name, Vector2f pos, Shape hitbox, Image image) {
		super(name, pos, hitbox);
		
		this.image = image;
	}
	
	public Button(String name, Vector2f pos, Shape hitbox) {
		super(name, pos, hitbox);
	}
	
	
	@Override
	public void update()
	{
		
	}
	
	@Override
	public void render(Graphics gfx) 
	{
		if(this.image != null)
		{
			this.image.draw(super.getPos().x, super.getPos().y);
		}else
		{
			Rectangle r = new Rectangle(super.getPos().x,
					super.getPos().y, 
					super.getHitbox().getWidth(), super.getHitbox().getHeight());
			
			gfx.setColor(Color.red);
			gfx.fill(r);
		}
	}
	
	
	public boolean isPressed(Input i)
	{
		if(super.mouseCollision(i) && i.isMousePressed(0))
		{
			return true;
		}
		
		return false;
	}

}
