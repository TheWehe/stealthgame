package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_MainMenu extends BasicGameState implements MenuListener {
	public static final int ID = 0;
	private Main game;

	private Menu menu;
	private Image buttonImage;

	public GameState_MainMenu(Main game) {
		this.game = game;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		buttonImage = new Image("/assets/button1.png");
		
		menu = new Menu(this);
		menu.add(new Button("Play", new Vector2f(300, 500), buttonImage));
	}
	
	@Override
	public void buttonPressed(String name) {
		switch(name)
		{
		case "Play":
			game.enterState(GameState_InGame.ID);
			break;
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		menu.mouseDown(new Vector2f(x, y));
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		menu.mouseUp(new Vector2f(x, y));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException
	{
		menu.update((float)arg2 / 1000.f);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setBackground(Color.white);
		
		menu.render(g);
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
