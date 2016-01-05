package GameStates;
import java.awt.Font;
import java.io.Console;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

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


public class SettingsState extends BasicGameState{

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
	public SettingsState(int ID)
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
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Back")/2), windowSizeY2-75,"Back",font,Variable.menuState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Make Level")/2), windowSizeY2-25,"Make Level",font,Variable.editLevelState));
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("SECRETS")/2), windowSizeY2+25,"SECRETS",font,100));
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
		g.fillRect(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, getTextWidth("Settings"), getTextHeight("Main Menu"));
		g.setColor(Color.black);
		g.drawRect(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, getTextWidth("Settings"), getTextHeight("Main Menu"));
		g.setColor(Color.magenta); //testing
		t.drawString(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, "Settings", new Color(0,227,4));
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
					if (b.getID() != 100)
					{
						game.enterState(b.getID(), new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
					}
					else
					{
						secretsGame();
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
		
	
	public void secretsGame()
	{
		Scanner input = new Scanner(System.in);
		clearScreen();
		print("----INTRO----");
		print("You are jerked awake by a loud crash. You remember only seeing flashes of your surroundings. The lights were firing sparks as they tried to emit light, and caused them only to flicker to life for short bursts.\n\n"
				+ "For a few moments, nothing made sense. You could see the shards of glass glistening in a puddle of water, and the large cylindrical container labeled 'XR-Specimen 001' beside you on the floor; but you can't piece together what happened. It wasn't until you saw the dead creature beside you that you figured it out.\n\n"
				+ "There was no other explanation for the curled up carcass of a facehugger. Realizing what events will unfold next, you lurch to a sitting possition - and immediately regret it. Pain shoots through your body like a bolt of lightning.");
		print("----END----\n\n");
		print("This is a survival game. Anything and everything can kill you. Every choice you make will impact you in some way.");
		print("Throughout the game, you will be presented with different ways/advantages to assist in your survival. Typing 'help' will show your current options, and more information.");
		print("Press enter to continue.");
		input.nextLine();
		print("This is the story of how you died.");
	}
	
	public void print(String s)
	{
		System.out.println(s);
	}

	public void clearScreen()
	{
		for(int i=0;i<50;i++)
		{
			System.out.println("\n");
		}
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
