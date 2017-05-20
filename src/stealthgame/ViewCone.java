package stealthgame;

import org.newdawn.slick.geom.Vector2f;

public class ViewCone {
	private float degrees;
	private float length;
	private Vector2f position;
	private Vector2f direction;
	
	public ViewCone(float deg, float len)
	{
		degrees = deg;
		length = len;
	}
	
	public void setPosition(Vector2f p)
	{
		position = p.copy();
	}
	
	public void setDirection(Vector2f d)
	{
		direction = d.copy();
	}
	
	public boolean contains(Vector2f target)
	{
		float targetAngle = (float) (Math.acos(target.copy().sub(position).getNormal().dot(direction)) * (180 / Math.PI));
		if(Math.abs(targetAngle) > degrees / 2) return false;
		if(target.distance(position) > length) return false;
		return true;
	}
}
