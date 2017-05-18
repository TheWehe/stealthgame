package stealthgame;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import org.newdawn.slick.Input;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.KeyControl;

public class Main extends BasicGame implements InputProviderListener
{
	private World world;
	
	private Player p;
	
	private InputProvider iProvider;
	
	private Command walkup = new BasicCommand("walkup");
	private Command walkdown = new BasicCommand("walkdown");
	private Command walkleft = new BasicCommand("walkleft");
	private Command walkright = new BasicCommand("walkright");
	
	private boolean[] inputs = new boolean[4];
	
	
	public Main(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		world = new World();
		
		iProvider = new InputProvider(gc.getInput());
		iProvider.addListener((InputProviderListener)this);
		
		iProvider.bindCommand(new KeyControl(Input.KEY_W), walkup);
		iProvider.bindCommand(new KeyControl(Input.KEY_A), walkleft);
		iProvider.bindCommand(new KeyControl(Input.KEY_S), walkdown);
		iProvider.bindCommand(new KeyControl(Input.KEY_D), walkright);
		
		p = new Player(new Vector2f(200, 200));
		
		world.addGameObject(p);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException
	{
		this.updateInput((float)i / 1000.f);
		world.update((float)i / 1000.f);
		world.postUpdate();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		world.render();
	}
	
	
	
	private void updateInput(float delta)
	{
		if(this.inputs[0])
		{
			this.p.moveUP(delta);
		}
		if(this.inputs[1])
		{
			this.p.moveRIGHT(delta);
		}
		if(this.inputs[2])
		{
			this.p.moveDOWN(delta);
		}
		if(this.inputs[3])
		{
			this.p.moveLEFT(delta);
		}
	}
	

	
	//input:
	public void controlPressed(Command c)
	{
		if(c == this.walkup)
		{
			this.inputs[0] = true;
		}
		if(c == this.walkright)
		{
			this.inputs[1] = true;
		}
		if(c == this.walkdown)
		{
			this.inputs[2] = true;
		}
		if(c == this.walkleft)
		{
			this.inputs[3] = true;
		}
	}
	
	public void controlReleased(Command c)
	{
		if(c == this.walkup)
		{
			this.inputs[0] = false;
			this.p.stop();
		}
		if(c == this.walkright)
		{
			this.inputs[1] = false;
			this.p.stop();
		}
		if(c == this.walkdown)
		{
			this.inputs[2] = false;
			this.p.stop();
		}
		if(c == this.walkleft)
		{
			this.inputs[3] = false;
			this.p.stop();
		}
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