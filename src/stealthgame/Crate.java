// TODO: crate klasse verallgemeinern und als obstacle darstellen, abhängig von sprite und größe

package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Crate extends GameObject {
	private Vector2f size;
	
	public Crate(String name, Vector2f pos, Vector2f s)
	{
		super(name, false, pos, 0);
		super.aabb = new AABB(pos, s);
		
		size = s;
	}
	
	@Override
	public void render(Graphics gfx)
	{
		Rectangle s = new Rectangle(super.position.x - size.x / 2, 
				super.position.y - size.y / 2, 
				size.x, size.y);
		gfx.setColor(Color.pink);
		gfx.fill(s);
	}
}
