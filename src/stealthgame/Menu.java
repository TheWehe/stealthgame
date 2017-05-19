package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Menu {
	private ArrayList<MenuPart> parts;
	private Image background = null;
	

	public Menu() 
	{
		this.parts = new ArrayList<MenuPart>();
	}
	
	public Menu(Image background)
	{
		this.parts = new ArrayList<MenuPart>();
		
		this.background = background;
	}
	
	
	
	public void update()
	{
		for(int i = 0 ; i < this.parts.size() ; i++)
		{
			MenuPart p = (MenuPart) this.parts.get(i);
			p.update();
		}
	}
	
	public void render(Graphics c)
	{
		if(this.background != null) this.background.draw();
		
		for(int i = 0 ; i < this.parts.size() ; i++)
		{
			MenuPart p = (MenuPart) this.parts.get(i);
			p.render(c);
		}
	}
	
	
	
	public void addPart(MenuPart p)
	{
		this.parts.add(p);
	}
	
	public void remove(MenuPart p)
	{
		this.parts.remove(p);
	}
	
	public boolean hasPart(MenuPart p)
	{
		for(int i = 0 ; i < this.parts.size(); i++) 
		{
			MenuPart cp = (MenuPart) this.parts.get(i);
			
			if(cp == p)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPart(String name)
	{
		for(int i = 0 ; i < this.parts.size(); i++) 
		{
			MenuPart cp = (MenuPart) this.parts.get(i);
			
			if(cp.getName() == name)
			{
				return true;
			}
		}
		return false;
	}
	
	public MenuPart getPart(String name)
	{
		for(int i = 0 ; i < this.parts.size(); i++) 
		{
			MenuPart mp = (MenuPart) this.parts.get(i);
			
			if(mp.getName() == name)
			{
				return mp;
			}
		}
		
		return null;
	}

}
