package stealthgame;

import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;

public class Route {
	private ArrayList<Vector2f> points;
	private int curTarget;
	private float precision;
	
	public Route(float p)
	{
		points = new ArrayList<Vector2f>();
		precision = p;
		curTarget = 0;
	}
	
	public void addPoint(Vector2f p)
	{
		points.add(p.copy());
	}
	
	public Vector2f getCurTarget()
	{
		return points.get(curTarget);
	}
	
	public void compareCurPosition(Vector2f curPos)
	{
		if(curPos.distance(getCurTarget()) < precision)
		{
			curTarget++;
			
			if(curTarget >= points.size()) curTarget = 0;
		}
	}
}
