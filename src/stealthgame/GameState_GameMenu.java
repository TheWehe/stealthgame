package stealthgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_GameMenu extends BasicGameState {
	public static final int ID = 1;
	
	private Main game;
	
	private Input input;
	

	public GameState_GameMenu(Main game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException
	{
		input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_R)) this.game.enterState(0);
	}
	
	@Override
	public int getID()
	{
		return GameState_GameMenu.ID;
	}

}
