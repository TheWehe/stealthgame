package stealthgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Player extends SpriteGameObject {
	private float movementSpeed = 100;
	private float vx, vy;
	
	public Player(Vector2f pos) throws SlickException {
		super("Player", true, pos, 0, new Image("/assets/Mexikaner.png"));
	}
	
	
	@Override
	public void update(float delta)
	{
		Vector2f v = new Vector2f(vx, vy);
		super.move(v);
		
		super.setAngle((float)v.getTheta()+90);
	}
	
	
	public void moveUP(float delta)
	{
		this.vy = -this.movementSpeed*delta;
	}
	
	public void moveDOWN(float delta)
	{
		this.vy = this.movementSpeed*delta;
	}
	
	public void moveLEFT(float delta)
	{
		this.vx = -this.movementSpeed*delta;
	}
	
	public void moveRIGHT(float delta)
	{
		this.vx = this.movementSpeed*delta;
	}
	
	public void stop()
	{
		this.vx = 0;
		this.vy = 0;
	}
}
