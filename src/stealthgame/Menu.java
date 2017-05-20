package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Menu {
	private MenuListener listener;
	private ArrayList<MenuPart> parts;
	
	public Menu(MenuListener l)
	{
		listener = l;
		parts = new ArrayList<MenuPart>();
	}
	
	public void add(MenuPart part)
	{
		part.setListener(listener);
		parts.add(part);
	}
	
	public void mouseDown(Vector2f place)
	{
		MenuPart cur;
		
		for(int i = 0; i < parts.size(); i++)
		{
			cur = parts.get(i);
			
			if(cur.getShape().contains(place.x, place.y))
			{
				cur.mouseDownInside();
			}
			
			cur.mouseDown(place);
		}
	}
	
	public void mouseUp(Vector2f place)
	{
		MenuPart cur;
		
		for(int i = 0; i < parts.size(); i++)
		{
			cur = parts.get(i);
			
			if(cur.getShape().contains(place.x, place.y))
			{
				cur.mouseUpInside();
			}
			
			cur.mouseUp(place);
		}
	}
	
	public void update(float delta)
	{
		MenuPart cur;
		
		for(int i = 0; i < parts.size(); i++)
		{
			cur = parts.get(i);
			cur.update(delta);
		}
	}
	
	public void render(Graphics gfx)
	{
		for(int i = 0; i < parts.size(); i++)
		{
			parts.get(i).render(gfx);
		}
	}
}
