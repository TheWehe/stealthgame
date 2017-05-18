// TODO: das ganze programm zustandsbasiert machen; z.b.: hauptmenü, spiel, abspann
// TODO: level aus xml datei laden

package stealthgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Input;

public class Main extends BasicGame
{
	private Input input;
	private Image playerImage;
	private World world;
	private Player player;
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		input = gc.getInput();
		
		playerImage = new Image("/assets/Mexikaner.png");
		
		world = new World();
		
		player = new Player("Player", new Vector2f(100, 200), playerImage);
		world.addGameObject(player);
		
		Crate crate1 = new Crate("Crate1", new Vector2f(450, 400), new Vector2f(250, 250));
		world.addGameObject(crate1);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException
	{
		if(input.isKeyDown(Input.KEY_W)) player.moveUp();
		if(input.isKeyDown(Input.KEY_A)) player.moveLeft();
		if(input.isKeyDown(Input.KEY_S)) player.moveDown();
		if(input.isKeyDown(Input.KEY_D)) player.moveRight();
		
		world.update((float)i / 1000.f);
		world.postUpdate();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		world.render(g);
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Stealth game"));
			appgc.setDisplayMode(1024, 768, false);
			appgc.start();
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}