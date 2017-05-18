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
		radius = center.sub(upperLeft).length();
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
		
		float minimalDistance = Float.MAX_VALUE;
		Vector2f penetrationVector = new Vector2f();
		
		// projektion auf x-achse
		
		// a links von b
		if(upperRight.x < other.upperLeft.x)
		{
			float d = other.upperLeft.x - upperRight.x;
			
			if (d < minimalDistance)
			{
				penetrationVector.x = 1;
				penetrationVector.y = 0;
			}
		}
		// a rechts von b
		else if(upperLeft.x > other.upperRight.x)
		{
			float d = other.upperRight.x - upperLeft.x;
			
			if (d < minimalDistance)
			{
				penetrationVector.x = -1;
				penetrationVector.y = 0;
			}
		}
		// a exakt über/unter b (oder innerhalb/identisch)
		else
		{
			// ...
		}
		
		
		// projektion auf y-achse
		
		// a über b
		if(lowerLeft.y < other.upperLeft.y)
		{
			float d = other.upperLeft.y - lowerLeft.y;
			
			if (d < minimalDistance)
			{
				penetrationVector.x = 0;
				penetrationVector.y = 1;
			}
		}
		// a unter b
		else if(upperLeft.y > other.lowerLeft.y)
		{
			float d = other.lowerLeft.y - upperLeft.y;
			
			if (d < minimalDistance)
			{
				penetrationVector.x = 0;
				penetrationVector.y = -1;
			}
		}
		// innerhalb/identisch
		else
		{
			return null;
		}
		
		return penetrationVector.scale(minimalDistance);
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
