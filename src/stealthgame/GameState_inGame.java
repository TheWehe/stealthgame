package stealthgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_inGame extends BasicGameState {
	public static final int ID = 0;
	
	private Main game;
	
	private Input input;
	private Image playerImage;
	private World world;
	private Player player;
	

	public GameState_inGame(Main game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
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
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		world.render(g);
	}
	
	@Override 
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException
	{
		if(input.isKeyDown(Input.KEY_W)) player.moveUp();
		if(input.isKeyDown(Input.KEY_A)) player.moveLeft();
		if(input.isKeyDown(Input.KEY_S)) player.moveDown();
		if(input.isKeyDown(Input.KEY_D)) player.moveRight();
		if(input.isKeyDown(Input.KEY_ESCAPE)) this.game.enterState(1);
		
		world.update((float)arg2 / 1000.f);
		world.postUpdate();
	}
	
	@Override
	public int getID()
	{
		return GameState_inGame.ID;
	}

}
