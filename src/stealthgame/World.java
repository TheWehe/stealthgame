package stealthgame;

import java.util.ArrayList;

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
	
	public void update(float delta)
	{
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).update(delta);
		}
	}
	
	public void postUpdate()
	{
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).postUpdate();
		}
	}
	
	public void render()
	{
		for(int i = 0; i < staticGos.size(); i++)
		{
			staticGos.get(i).render();
		}
		
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).render();
		}
	}
}
