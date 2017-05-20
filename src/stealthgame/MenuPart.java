package stealthgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public abstract class MenuPart {
	private String name;
	private Shape shape;
	private Vector2f position;
	private MenuListener listener;
	
	public MenuPart(String n, Shape s, Vector2f p)
	{
		name = n;
		shape = s;
		position = p.copy();
		listener = null;
	}
	
	public void mouseDown(Vector2f place) {}
	public void mouseUp(Vector2f place) {}
	public void mouseDownInside() {}
	public void mouseUpInside() {}
	public void update(float delta) {}
	
	public abstract void render(Graphics gfx);
	
	public String getName()
	{
		return name;
	}
	
	public void setListener(MenuListener l)
	{
		listener = l;
	}
	
	public MenuListener getListener()
	{
		return listener;
	}
	
	public Shape getShape()
	{
		return shape;
	}
	
	public Vector2f getPosition()
	{
		return position;
	}
}
