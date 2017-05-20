package stealthgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_InGameMenu extends BasicGameState {
	public static final int ID = 2;
	private Main game;
	
	private Input input;
	private Menu menu;

	public GameState_InGameMenu(Main game) {
		this.game = game;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		input = gc.getInput();
		
		this.menu = new Menu();
		this.menu.addPart(new Button("Button", new Vector2f(300, 100), new Rectangle(300, 100, 50, 50)));
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
		if(mb.isPressed(input)) this.game.enterState(GameState_InGame.ID);
	}
	
	@Override
	public int getID()
	{
		return GameState_InGameMenu.ID;
	}
}
