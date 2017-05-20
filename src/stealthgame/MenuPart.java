package stealthgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class MenuPart {
	private String name;
	private Vector2f pos;
	private Shape hitbox;
	

	public MenuPart(String name, Vector2f pos, Shape hitbox) {
		this.name = name;
		
		this.pos = new Vector2f();
		this.pos = pos;
		
		this.hitbox = hitbox;
	}
	
	
	public String getName()
	{
		return this.name;
	}
	
	public Vector2f getPos()
	{
		return this.pos;
	}
	
	public Shape getHitbox()
	{
		return this.hitbox;
	}
	
	public boolean mouseCollision(Input i)
	{
		return this.hitbox.contains(i.getMouseX(), i.getMouseY());
	}
	
	
	public abstract void update();
	public abstract void render(Graphics gfx);

}
