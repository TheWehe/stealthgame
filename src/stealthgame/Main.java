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
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;

public class Main extends StateBasedGame//BasicGame
{
	
	public Main(String gamename)
	{
		super(gamename);
	}
	
	
	@Override
	public void initStatesList(GameContainer gc)throws SlickException
	{
		this.addState(new GameState_inGame(this));
		this.addState(new GameState_GameMenu(this));
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