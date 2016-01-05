package GameStates;

import java.awt.Font;



import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import GUI.Buttons;
import Variables.Variable;


public class MenuState extends BasicGameState{

	/*
	 * VARIABLES
	 */
	public static int  ID;
	private int windowSizeX2, windowSizeY2;
	private Image background; //THE BACKGROUND
	private Font font; //FONT STUF
	private TrueTypeFont t; //FONT STUFF
	private LinkedList<Buttons> buttons = new LinkedList<Buttons>(); //ARAY OF BUTTONS
	
	/*
	 * CONSTRUCTOR
	 */
	public MenuState(int ID)
	{
		this.ID = ID;
	}
	
	
	/*
	 * INITILZATION
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		//GETTING HALF THE WINDOW SIZE X AND Y
		windowSizeX2 = (int)(Variable.windowSizeX/2);
		windowSizeY2 = (int)(Variable.windowSizeY/2);
		System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE)); //TEST
		//MAKING THE BACKGROUND
		background = new Image("Images/background.jpg");
		background = background.getScaledCopy(Variable.windowSizeX, Variable.windowSizeY);
		//MAKING THE FONTS
		font = new Font("Copperplate Gothic Bold", Font.BOLD, 35);
		t = new TrueTypeFont(font, false);
		
		//MAKING BUTTONS
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Play")/2), windowSizeY2-75,"Play",font,Variable.ChooseDifficulty));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-25,"Settings",font,Variable.settingsState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Quit")/2), windowSizeY2+25,"Quit",font,100));
	}	
	
	/*
	 * RENDERS THE GRAPHICS, CALLED CONSTANTLY
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		//DRAWING THE BACKGROUND AND THE MAIN MENU
		g.drawImage(background, 0, 0);
		//HARDCODING THE MAIN MENU
		g.setColor(new Color(75,75,75,200));
		g.fillRect(windowSizeX2-(getTextWidth("Tower Defense")/2), windowSizeY2-250, getTextWidth("Tower Defense"), getTextHeight("Tower Defense"));
		g.setColor(Color.black);
		g.drawRect(windowSizeX2-(getTextWidth("Tower Defense")/2), windowSizeY2-250, getTextWidth("Tower Defense"), getTextHeight("Tower Defense"));
		g.setColor(Color.magenta); //testing
		t.drawString(windowSizeX2-(getTextWidth("Tower Defense")/2), windowSizeY2-250, "Tower Defense", new Color(0,227,4));
		//END OF HARD CODING MAIN MENU AND BACKGROUND
		
		//DRAWING EACH BUTTON
		for (Buttons b : buttons)
		{
			b.draw(g);
		}
		//END OF DRAWING THE BUTTONS
		
	}

	/*
	 * UPDATES, CALLED ONSTANTLY
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		for (Buttons b : buttons)
		{
			if (b.containsPoint(input.getMouseX(), input.getMouseY()))
			{
				b.setHighlighted(true);
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					if (b.getID() == 100)
					{
						container.exit();
					}
					else
					{
						game.enterState(b.getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
					}
				}
			}
			else
			{
				b.setHighlighted(false);
			}
		}
			//game.enterState(Settings.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
	}
		

	/*
	 * INITILZATION
	 */
	@Override
	public int getID() 
	{
		return ID; //returns the ID of the State
	}
	
	
	//METHOD FOR GETTING LENGTH OF A STRING IN PIXELS
	public int getTextWidth(String text)
	{
		return (int)font.getStringBounds(text, Variable.frc).getWidth();
	}
	
	//METHOD FOR GETTING HEIGHT OF A STRING IN PIXELS
	public int getTextHeight(String text)
	{
		return (int) font.getStringBounds(text, Variable.frc).getHeight();
	}

}
