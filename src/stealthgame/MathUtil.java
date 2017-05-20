package stealthgame;

import org.newdawn.slick.geom.Vector2f;

public class MathUtil {
	public static Vector2f solveLS2(float a1, float b1, float c1, float a2, float b2, float c2)
	{
		float det2 = a1 * b2 - b1 * a2;
		
		if(Math.abs(det2) < 0.0001f)
		{
			return null;
		}
		else
		{
			return new Vector2f((c1 * b2 - b1 * c2) / det2, 
					(a1 * c2 - c1 * a2) / det2);
		}
	}
	
	public static Vector2f lineSegmentIntersection(Vector2f a, Vector2f b, Vector2f p, Vector2f q)
	{
		Vector2f r = MathUtil.solveLS2(b.x - a.x, 
				-q.x + p.x, 
				p.x - a.x, 
				b.y - a.y, 
				-q.y + p.y, 
				p.y - a.y);
		
		if(r != null)
		{
			if(r.x < 0 || r.x > 1 || r.y < 0 || r.y > 1)
			{
				return null;
			}
		}
		
		return r;
	}
	
	public static float projectPointOntoAxis(Vector2f axis, Vector2f point)
	{
		return axis.dot(point);
	}
	
	public static Vector2f projectAABBOntoAxis(Vector2f axis, AABB aabb)
	{
		float f = projectPointOntoAxis(axis, aabb.getUpperLeft());
		Vector2f r = new Vector2f(f, f);
		
		f = projectPointOntoAxis(axis, aabb.getUpperRight());
		if(f < r.x) r.x = f;
		else if(f > r.y) r.y = f;
		
		f = projectPointOntoAxis(axis, aabb.getLowerLeft());
		if(f < r.x) r.x = f;
		else if(f > r.y) r.y = f;
		
		f = projectPointOntoAxis(axis, aabb.getLowerRight());
		if(f < r.x) r.x = f;
		else if(f > r.y) r.y = f;
		
		return r;
	}
	
	public static boolean intervalContainsValue(Vector2f interval, float f)
	{
		return f >= interval.x && f <= interval.y;
	}

	public static class Ray
	{	
		public Vector2f pos;
		public Vector2f dir;
		public float dist;
		
		public Ray(Vector2f p, Vector2f d, float di)
		{
			pos = p.copy();
			dir = d.copy();
			dist = di;
		}
	}
	
	public static Vector2f raycast(Ray ray, AABB aabb)
	{
		Vector2f axis = ray.dir.copy().getPerpendicular();
		Vector2f aabbProjection = projectAABBOntoAxis(axis, aabb);
		float rayProjection = projectPointOntoAxis(axis, ray.pos);
		if(!intervalContainsValue(aabbProjection, rayProjection)) return null;
		
		Vector2f rayP1 = ray.pos.copy();
		Vector2f rayP2 = ray.pos.copy().add(ray.dir.copy().scale(ray.dist));
		float nearestParam = Float.POSITIVE_INFINITY;
		
		Vector2f comp = lineSegmentIntersection(rayP1, rayP2, aabb.getUpperLeft(), aabb.getUpperRight());
		if(comp != null)
		{
			nearestParam = comp.x;
		}
		
		comp = lineSegmentIntersection(rayP1, rayP2, aabb.getUpperRight(), aabb.getLowerRight());
		if(comp != null)
		{
			if(comp.x < nearestParam) nearestParam = comp.x;
		}
		
		comp = lineSegmentIntersection(rayP1, rayP2, aabb.getLowerRight(), aabb.getLowerLeft());
		if(comp != null)
		{
			if(comp.x < nearestParam) nearestParam = comp.x;
		}
		
		comp = lineSegmentIntersection(rayP1, rayP2, aabb.getLowerLeft(), aabb.getUpperLeft());
		if(comp != null)
		{
			if(comp.x < nearestParam) nearestParam = comp.x;
		}
		
		if(nearestParam > 1) return null;
		
		rayP1.add(rayP2.sub(rayP1.copy()).scale(nearestParam));
		return rayP1;
	}
}
