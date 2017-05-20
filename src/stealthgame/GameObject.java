package stealthgame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class GameObject {
	protected Vector2f position;
	protected float angle;
	protected World world;
	protected String name;
	protected boolean dynamic;
	protected AABB aabb;
	
	public GameObject(String n, boolean d, Vector2f p, float a)
	{
		name = n;
		dynamic = d;
		position = p.copy();
		angle = a;
		aabb = null;
	}
	
	public void start() {}
	public void update(float delta) {}
	public void postUpdate() {}
	public void render(Graphics gfx) {}
	
	void setWorld(World w)
	{
		world = w;
	}
	
	String getName()
	{
		return name;
	}
	
	boolean isDynamic()
	{
		return dynamic;
	}
	
	public void move(Vector2f v)
	{
		position.add(v);
	}
	
	public void setPosition(Vector2f p)
	{
		position = p;
	}
	
	public Vector2f getPosition()
	{
		return position;
	}
	
	public void rotate(float a)
	{
		angle += a;
	}
	
	public void setAngle(float a)
	{
		angle = a;
	}
	
	public float getAngle()
	{
		return angle;
	}
	
	public Vector2f getDirection()
	{
		return new Vector2f((float)Math.sin(angle * (Math.PI / 180)), (float)-Math.cos(angle * (Math.PI / 180)));
	}
	
	public AABB getAABB()
	{
		return aabb;
	}
}
