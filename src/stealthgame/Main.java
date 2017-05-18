// TODO: das ganze programm zustandsbasiert machen; z.b.: hauptmenü, spiel, abspann
// TODO: level aus xml datei laden

package stealthgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
		
		Crate crate1 = new Crate("Crate1", new Vector2f(300, 300), new Vector2f(400, 400));
		world.addGameObject(crate1);
		Crate crate2 = new Crate("Crate2", new Vector2f(900, 550), new Vector2f(100, 150));
		world.addGameObject(crate2);
		
		player = new Player("Player", new Vector2f(800, 300), playerImage);
		world.addGameObject(player);
		
		Route route1 = new Route(1);
		route1.addPoint(new Vector2f(550, 550));
		route1.addPoint(new Vector2f(550, 50));
		route1.addPoint(new Vector2f(50, 50));
		route1.addPoint(new Vector2f(50, 550));
		Guard guard1 = new Guard("Guard1", new Vector2f(900, 740), route1);
		world.addGameObject(guard1);
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