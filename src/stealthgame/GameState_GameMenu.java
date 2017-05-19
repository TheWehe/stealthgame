package stealthgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_GameMenu extends BasicGameState {
	public static final int ID = 1;
	
	private Main game;
	
	private Input input;
	
	private Menu menu;
	

	public GameState_GameMenu(Main game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		input = gc.getInput();
		
		this.menu = new Menu();
		this.menu.addPart(new Button("Button", new Vector2f(500, 500), new Rectangle(500, 500, 50, 50)));
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		this.menu.render(g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException
	{
		Button mb = (Button)this.menu.getPart("Button");
		if(mb.isPressed(gc.getInput())) this.game.enterState(0);
	}
	
	@Override
	public int getID()
	{
		return GameState_GameMenu.ID;
	}

}
