package stealthgame;

import java.util.ArrayList;
import org.newdawn.slick.geom.Vector2f;

public class Route {
	private ArrayList<Vector2f> points;
	private int curTarget;
	private float precision;
	private boolean ran;
	
	
	public Route(float p, boolean ran)
	{
		points = new ArrayList<Vector2f>();
		precision = p;
		curTarget = 0;
		
		this.ran = ran;
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
			if(!this.ran)
			{
				curTarget++;
			
				if(curTarget >= points.size()) curTarget = 0;
			}else 
			{
				curTarget = (int)(Math.random() * this.points.size()); 
			}
		}
	}
}
