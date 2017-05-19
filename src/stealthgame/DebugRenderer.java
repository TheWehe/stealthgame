package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class DebugRenderer {
	public static void renderAABB(Graphics gfx, AABB aabb)
	{
		Rectangle r = new Rectangle(aabb.getUpperLeft().x, aabb.getUpperLeft().y, aabb.getSize().x, aabb.getSize().y);
		gfx.setColor(Color.blue);
		gfx.draw(r);
	}
	
	public static void renderCross(Graphics gfx, Vector2f point)
	{
		Line l1 = new Line(point.x - 10, point.y - 10, point.x + 10, point.y + 10);
		Line l2 = new Line(point.x + 10, point.y - 10, point.x - 10, point.y + 10);
		
		gfx.setColor(Color.red);
		gfx.setLineWidth(2);
		gfx.draw(l1);
		gfx.draw(l2);
	}
	
	public static void renderLineSegment(Graphics gfx, Vector2f a, Vector2f b)
	{
		gfx.setColor(Color.magenta);
		gfx.setLineWidth(2);
		gfx.draw(new Line(a, b));
	}
	
	public static void renderRay(Graphics gfx, MathUtil.Ray r)
	{
		renderLineSegment(gfx, r.pos, r.pos.copy().add(r.dir.scale(r.dist)));
	}
	
	public static void renderRayCastResult(Graphics gfx, World.RaycastResult result)
	{
		renderRay(gfx, result.ray);
		
		for(int i = 0; i < result.hits.size(); i++)
		{
			renderCross(gfx, result.hits.get(i).pos);
		}
	}
}
