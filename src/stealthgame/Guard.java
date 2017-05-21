// TODO: bild für eine wache machen und integrieren (auch richtige rotation einbauen)

package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Guard extends GameObject {
	private float movementSpeed = 100;
	private float viewConeDegrees = 100;
	private float viewConeLength = 300;
	private Route route;
	private ViewCone viewCone;
	private Player player;
	private boolean alerted;
	
	public Guard(String n, Vector2f pos, Route r) {
		super(n, true, pos, 0);
		
		route = r;
		viewCone = new ViewCone(viewConeDegrees, viewConeLength);
		alerted = false;
	}
	
	@Override
	public void start()
	{
		player = (Player) super.world.findDynamicGameObjectByName("Player");
	}
	
	@Override
	public void update(float delta)
	{
		if(!alerted)
		{
			Vector2f movementDir = route.getCurTarget().copy().sub(super.position).getNormal();
			super.angle = (float) movementDir.getTheta() + 90;
			super.move(movementDir.scale(movementSpeed * delta));
			route.compareCurPosition(super.position);
		}
	}
	
	@Override
	public void postUpdate()
	{
		alerted = false;
		
		viewCone.setPosition(super.getPosition());
		viewCone.setDirection(super.getDirection());
		if(viewCone.contains(player.getPosition()))
		{
			MathUtil.Ray ray = new MathUtil.Ray(getPosition(), player.getPosition().copy().sub(getPosition()).getNormal(), viewConeLength);
			World.RaycastResult r = super.world.raycast(ray, null);
			if(r.hits.get(0).go == (GameObject) player)
			{
				alerted = true;
			}
		}
	}
	
	@Override
	public void render(Graphics gfx)
	{
		Rectangle s = new Rectangle(super.position.x - 15, 
				super.position.y - 15, 
				30, 30);
		
		gfx.setColor(Color.green);
		gfx.fill(s);
		
		DebugRenderer.renderRay(gfx, new MathUtil.Ray(super.getPosition(), super.getDirection(), viewConeLength));
	}
}
