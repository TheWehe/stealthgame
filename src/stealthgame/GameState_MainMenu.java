package stealthgame;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState_MainMenu extends BasicGameState implements MenuListener {
	public static final int ID = 0;
	private Main game;

	private GameContainer container;
	private Menu menu;

	public GameState_MainMenu(Main game) {
		this.game = game;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		container = gc;
		
		menu = new Menu(this);
		menu.add(new Button("Spielen", new Vector2f(125, 550), ImageManager.getInstance().get("/assets/button1.png")));
		menu.add(new Button("Verlassen", new Vector2f(625, 550), ImageManager.getInstance().get("/assets/button1.png")));
	}
	
	@Override
	public void buttonPressed(String name) {
		switch(name)
		{
		case "Spielen":
			game.enterState(GameState_InGame.ID);
			break;
		case "Verlassen":
			container.exit();
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
		g.setBackground(new Color(10, 215, 66));
		
		menu.render(g);
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
}
