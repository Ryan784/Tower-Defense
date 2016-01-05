package GameStates;

import java.awt.Font;
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

public class ChooseDifficulty extends BasicGameState{

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
	public ChooseDifficulty(int ID)
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
		//System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE)); //TEST
		//MAKING THE BACKGROUND
		background = new Image("Images/background.jpg");
		background = background.getScaledCopy(Variable.windowSizeX, Variable.windowSizeY);
		//MAKING THE FONTS
		font = new Font("Copperplate Gothic Bold", Font.BOLD, 35);
		t = new TrueTypeFont(font, false);
		
		//MAKING BUTTONS
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Easy")/2), windowSizeY2-75,"Easy",font,Variable.playState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Medium")/2), windowSizeY2-25,"Medium",font,Variable.playState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Hard")/2), windowSizeY2+25,"Hard",font,Variable.playState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Go Back")/2), windowSizeY2+125,"Go Back",font,Variable.menuState));
	}	
	
	/*
	 * RENDERS THE GRAPHICS, CALLED CONSTANTLY
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		//DRAWING THE BACKGROUND AND THE MAIN MENU
		g.drawImage(background, 0, 0);
		
		
		g.setColor(new Color(75,75,75,200));
		g.fillRect(windowSizeX2-(getTextWidth("Difficulty?")/2), windowSizeY2-250, getTextWidth("Difficulty?"), getTextHeight("Difficulty?"));
		g.setColor(Color.black);
		g.drawRect(windowSizeX2-(getTextWidth("Difficulty?")/2), windowSizeY2-250, getTextWidth("Difficulty?"), getTextHeight("Difficulty?"));
		g.setColor(Color.magenta); //testing
		t.drawString(windowSizeX2-(getTextWidth("Difficulty?")/2), windowSizeY2-250, "Difficulty?", new Color(0,227,4));
		
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
					game.enterState(b.getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
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
