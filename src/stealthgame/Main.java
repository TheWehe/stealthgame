// TODO: level aus xml datei laden

package stealthgame;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame
{
	public Main(String gamename)
	{
		super(gamename);
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.addState(new GameState_MainMenu(this));
		this.addState(new GameState_InGame(this));
		this.addState(new GameState_InGameMenu(this));
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