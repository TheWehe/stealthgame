package stealthgame;

import org.newdawn.slick.geom.Vector2f;

public class AABB {
	private Vector2f center;
	private Vector2f size;
	private Vector2f upperLeft;
	private Vector2f upperRight;
	private Vector2f lowerLeft;
	private Vector2f lowerRight;
	private float radius;
	
	private void updatePoints()
	{
		upperLeft = new Vector2f(center.x - size.x / 2.f, center.y - size.y / 2.f);
		upperRight = new Vector2f(center.x + size.x / 2.f, center.y - size.y / 2.f);
		lowerLeft = new Vector2f(center.x - size.x / 2.f, center.y + size.y / 2.f);
		lowerRight = new Vector2f(center.x + size.x / 2.f, center.y + size.y / 2.f);
		radius = center.copy().sub(upperLeft).length();
	}
	
	public AABB(Vector2f c, Vector2f s)
	{
		center = c.copy();
		size = s.copy();
		
		updatePoints();
	}
	
	public boolean intersects(AABB other)
	{
		return !(other.upperLeft.x > upperRight.x
		        || other.upperRight.x < upperLeft.x
		        || other.upperLeft.y > lowerLeft.y
		        || other.lowerLeft.y < upperLeft.y);
	}
	
	public boolean radiusIntersect(Vector2f p, float r)
	{
		return Math.pow(radius + r, 2) <= Math.pow((center.x - p.x), 2) + Math.pow((center.y - p.y), 2);
	}
	
	public Vector2f checkCollision(AABB other)
	{
		if(!intersects(other)) return null;
		
		float smallestDist = Float.POSITIVE_INFINITY;
		Vector2f penetrationDir = new Vector2f();
		
		float d = lowerLeft.y - other.upperLeft.y;
		if(d < smallestDist)
		{
			smallestDist = d;
			penetrationDir.x = 0;
			penetrationDir.y = 1;
		}
		
		d = other.lowerLeft.y - upperLeft.y;
		if(d < smallestDist)
		{
			smallestDist = d;
			penetrationDir.x = 0;
			penetrationDir.y = -1;
		}
		
		d = upperRight.x - other.upperLeft.x;
		if(d < smallestDist)
		{
			smallestDist = d;
			penetrationDir.x = 1;
			penetrationDir.y = 0;
		}
		
		d = other.upperRight.x - upperLeft.x;
		if(d < smallestDist)
		{
			smallestDist = d;
			penetrationDir.x = -1;
			penetrationDir.y = 0;
		}
		
		return penetrationDir.scale(smallestDist);
	}
	
	public void setCenter(Vector2f c)
	{
		center = c.copy();
		updatePoints();
	}
	
	public Vector2f getCenter()
	{
		return center;
	}
	
	public void setSize(Vector2f s)
	{
		size = s.copy();
		updatePoints();
	}
	
	public Vector2f getSize()
	{
		return size;
	}
	
	public Vector2f getUpperLeft()
	{
		return upperLeft;
	}
	
	public Vector2f getUpperRight()
	{
		return upperRight;
	}
	
	public Vector2f getLowerLeft()
	{
		return lowerLeft;
	}
	
	public Vector2f getLowerRight()
	{
		return lowerRight;
	}
}
