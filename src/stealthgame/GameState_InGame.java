package stealthgame;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_InGame extends BasicGameState {
	public static final int ID = 1;
	
	private Input input;
	private World world;
	private Player player;

	public GameState_InGame(Main game)
	{
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		input = gc.getInput();
		
		world = new World();
		
		Crate crate1 = new Crate("Crate1", new Vector2f(300, 300), new Vector2f(400, 400));
		world.addGameObject(crate1);
		Crate crate2 = new Crate("Crate2", new Vector2f(900, 550), new Vector2f(100, 150));
		world.addGameObject(crate2);
		
		player = new Player("Player", new Vector2f(800, 300));
		world.addGameObject(player);
		
		ArrayList<Vector2f> guard1RoutePoints = new ArrayList<Vector2f>();
		guard1RoutePoints.add(new Vector2f(550, 550));
		guard1RoutePoints.add(new Vector2f(550, 50));
		guard1RoutePoints.add(new Vector2f(50, 50));
		guard1RoutePoints.add(new Vector2f(50, 550));
		Guard guard1 = new Guard("Guard1", new Vector2f(900, 740), new Route(guard1RoutePoints, 1, false));
		world.addGameObject(guard1);
		
		ArrayList<Vector2f> spotlightRoutePoints = new ArrayList<Vector2f>();
		spotlightRoutePoints.add(new Vector2f(125, 100));
		spotlightRoutePoints.add(new Vector2f(850, 100));
		spotlightRoutePoints.add(new Vector2f(100, 530));
		spotlightRoutePoints.add(new Vector2f(790, 600));
		spotlightRoutePoints.add(new Vector2f(440, 350));
		Spotlight spotlight1 = new Spotlight("Spotlight1", new Vector2f(500, 350), new Route(spotlightRoutePoints, 2, true));
		world.addGameObject(spotlight1);
		Spotlight spotlight2 = new Spotlight("Spotlight2", new Vector2f(350, 500), new Route(spotlightRoutePoints, 2, true));
		world.addGameObject(spotlight2);
		
		world.start();
		world.setDebugMode(true);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{	
		g.setBackground(Color.lightGray);
		
		world.render(g);
		
		g.setColor(Color.black);
		g.drawString(Integer.toString(gc.getFPS()), 10, 10);
	}
	
	@Override 
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException
	{
		if(input.isKeyDown(Input.KEY_W)) player.moveUp();
		if(input.isKeyDown(Input.KEY_A)) player.moveLeft();
		if(input.isKeyDown(Input.KEY_S)) player.moveDown();
		if(input.isKeyDown(Input.KEY_D)) player.moveRight();
		
		world.update(arg2 / 1000.f);
		world.postUpdate();
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
