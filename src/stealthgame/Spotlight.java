package stealthgame;

import org.newdawn.slick.geom.Vector2f;

public class Spotlight extends SpriteGameObject {
	private float movementSpeed = 150;
	private Route route;
	
	public Spotlight(String name, Vector2f p, Route r) {
		super(name, true, p, 0, ImageManager.getInstance().get("/assets/Spotlight.png"));
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
}
