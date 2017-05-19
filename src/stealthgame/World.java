// TODO: raycast-funktion die liste mit getroffenen gos zurückgibt (sortiert nach abstand zum startpunkt)

package stealthgame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class World {
	public static class RaycastHit
	{
		public Vector2f pos;
		public GameObject go;
		
		public RaycastHit(Vector2f p, GameObject g)
		{
			pos = p;
			go = g;
		}
	}
	
	public static class RaycastResult
	{
		public MathUtil.Ray ray;
		public ArrayList<RaycastHit> hits;
		
		public RaycastResult(MathUtil.Ray r, ArrayList<RaycastHit> h)
		{
			ray = r;
			hits = h;
		}
	}
	
	private ArrayList<GameObject> staticGos;
	private ArrayList<GameObject> dynamicGos;
	private boolean debugMode;
	private ArrayList<RaycastResult> debugRaycastResults;
	
	public World()
	{
		staticGos = new ArrayList<GameObject>();
		dynamicGos = new ArrayList<GameObject>();
		debugMode = false;
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
	
	public void setDebugMode(boolean b)
	{
		if(b)
		{
			debugMode = true;
			debugRaycastResults = new ArrayList<RaycastResult>();
		}
		else
		{
			debugMode = false;
			debugRaycastResults = null;
		}
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
	
	public RaycastResult raycast(MathUtil.Ray ray, GameObject ignore)
	{
		ArrayList<RaycastHit> hits = new ArrayList<RaycastHit>();
		
		
		
		RaycastResult r = new RaycastResult(ray, hits);
		
		if(debugMode)
		{
			debugRaycastResults.add(r);
		}
		
		return r;
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
			
			if(debugMode)
			{
				if(staticGos.get(i).getAABB() != null)
				{
					DebugRenderer.renderAABB(gfx, staticGos.get(i).getAABB());
				}
			}
		}
		
		for(int i = 0; i < dynamicGos.size(); i++)
		{
			dynamicGos.get(i).render(gfx);
			
			if(debugMode)
			{
				if(dynamicGos.get(i).getAABB() != null)
				{
					DebugRenderer.renderAABB(gfx, dynamicGos.get(i).getAABB());
				}
			}
		}
		
		if(debugMode)
		{
			for(int i = 0; i < debugRaycastResults.size(); i++)
			{
				DebugRenderer.renderRayCastResult(gfx, debugRaycastResults.get(i));
			}
			
			debugRaycastResults.clear();
		}
	}
}
