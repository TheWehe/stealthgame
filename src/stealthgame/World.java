// TODO: raycast-funktion die liste mit getroffenen gos zurückgibt (sortiert nach abstand zum startpunkt)

package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class World {
	private ArrayList<GameObject> staticGos;
	private ArrayList<GameObject> dynamicGos;
	
	public World()
	{
		staticGos = new ArrayList<GameObject>();
		dynamicGos = new ArrayList<GameObject>();
	}
	
	public void addGameObject(GameObject go)
	{
		go.setWorld(this);
		
		if(go.isDynamic())
		{
			dynamicGos.add(go);
		}
		else
		{
			staticGos.add(go);
		}
	}
	
	public void removeGameObject(GameObject go)
	{
		staticGos.remove(go);
		dynamicGos.remove(go);
	}
	
	public ArrayList<GameObject> getDynamicGameObjects()
	{
		return dynamicGos;
	}
	
	public ArrayList<GameObject> getStaticGameObjects()
	{
		return staticGos;
	}
	
	public GameObject findDynamicGameObjectByName(String name)
	{
		GameObject r = null;
		
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			if(dynamicGos.get(i).getName().equals(name))
			{
				r = dynamicGos.get(i);
			}
		}
		
		return r;
	}
	
	public GameObject findStaticGameObjectByName(String name)
	{
		GameObject r = null;
		
		for(int i = 0; i < staticGos.size(); i++)
		{
			if(staticGos.get(i).getName().equals(name))
			{
				r = staticGos.get(i);
			}
		}
		
		return r;
	}
	
	private void doPhysics()
	{
		Vector2f v;
		
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			GameObject d = dynamicGos.get(i);
			if(d.getAABB() == null) continue;
			
			for(int j = 0; j < staticGos.size(); j++)
			{
				GameObject s = staticGos.get(j);
				if(s.getAABB() == null) continue;
				
				v = d.getAABB().checkCollision(s.getAABB());
				if(v == null) continue;
				
				d.move(v.negate());
			}
		}
	}
	
	public void update(float delta)
	{
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).update(delta);
		}
		
		doPhysics();
	}
	
	public void postUpdate()
	{
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).postUpdate();
		}
	}
	
	public void render(Graphics gfx)
	{
		for(int i = 0; i < staticGos.size(); i++)
		{
			staticGos.get(i).render(gfx);
		}
		
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).render(gfx);
		}
	}
}
