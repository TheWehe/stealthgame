package stealthgame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

public class Route {
	private ArrayList<Vector2f> points;
	private int curTarget;
	private float precision;
	private boolean random;
	private Random generator;
	
	public Route(ArrayList<Vector2f> ps, float p, boolean ran)
	{
		points = ps;
		precision = p;
		random = ran;
		
		if(random)
		{
			generator = new Random();
			curTarget = generator.nextInt(points.size());
		}
		else
		{
			generator = null;
			curTarget = 0;
		}
	}
	
	public Vector2f getCurTarget()
	{
		return points.get(curTarget);
	}
	
	public void compareCurPosition(Vector2f curPos)
	{
		if(curPos.distance(getCurTarget()) < precision)
		{
			if(random)
			{
				curTarget = generator.nextInt(points.size());
			}
			else 
			{
				curTarget++;
				if(curTarget >= points.size()) curTarget = 0;
			}
		}
	}
}
