// TODO: bild für eine wache machen und integrieren (auch richtige rotation einbauen)

package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Guard extends GameObject {
	private float movementSpeed = 100;
	private Route route;
	
	public Guard(String n, Vector2f pos, Route r) {
		super(n, true, pos, 0);
		
		route = r;
	}
	
	@Override
	public void update(float delta)
	{
		Vector2f movementDir = route.getCurTarget().copy().sub(super.position).getNormal();
		super.angle = (float) movementDir.getTheta();
		super.move(movementDir.scale(movementSpeed * delta));
		
		route.compareCurPosition(super.position);
	}
	
	@Override
	public void render(Graphics gfx)
	{
		Rectangle s = new Rectangle(super.position.x - 20, 
				super.position.y - 20, 
				30, 30);
		
		gfx.setColor(Color.green);
		gfx.fill(s);
	}
}
