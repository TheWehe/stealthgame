package stealthgame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

public class Player extends SpriteGameObject {

	public Player(Vector2f pos) throws SlickException {
		super("Player", true, pos, 0, new Image("/assets/Mexikaner.png"));
	}
	
	
	@Override
	public void update(float delta)
	{
		
	}
}
