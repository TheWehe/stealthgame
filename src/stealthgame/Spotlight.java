package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Spotlight extends SpriteGameObject {
	private float movementSpeed = 150;
	private Route route;

	
	public Spotlight(String name, Vector2f p, Route r, Image img) {
		super(name, true, p, 0, img);
		
		this.route = r;
	}
	
	
	@Override
	public void update(float delta)
	{
		Vector2f movementDir = route.getCurTarget().copy().sub(super.position).getNormal();
		super.angle = (float) movementDir.getTheta();
		super.move(movementDir.scale(movementSpeed * delta));
		
		this.route.compareCurPosition(super.position);
	}
	
	@Override
	public void render(Graphics gfx)
	{
		Circle s = new Circle(super.position.x, 
				super.position.y, 
				100, 100);
		
		gfx.setColor(Color.red);
		gfx.fill(s);
	}

}
