package stealthgame;

import org.newdawn.slick.geom.Vector2f;

public class Player extends SpriteGameObject {
	private float movementSpeed = 200;
	private Vector2f velocity;
	
	public Player(String name, Vector2f pos) {
		super(name, true, pos, 0, ImageManager.getInstance().get("/assets/Mexikaner.png"));
		super.aabb = new AABB(super.position, new Vector2f(30, 30));
		velocity = new Vector2f();
	}
	
	@Override
	public void update(float delta)
	{	
		super.move(velocity.scale(delta));
		if(velocity.lengthSquared() != 0) super.setAngle((float)velocity.getTheta() + 90);
		velocity.set(0, 0);
		
		super.aabb.setCenter(super.position);
	}
	
	@Override
	public void postUpdate()
	{
		super.world.raycast(new MathUtil.Ray(super.position, super.getDirection(), 400), this);
	}
	
	public void moveUp()
	{
		velocity.y -= movementSpeed;
	}
	
	public void moveDown()
	{
		velocity.y += movementSpeed;
	}
	
	public void moveLeft()
	{
		velocity.x -= movementSpeed;
	}
	
	public void moveRight()
	{
		velocity.x += movementSpeed;
	}
}
