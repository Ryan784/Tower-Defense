package GameStates;
import java.awt.Font;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import GUI.CustomButton;
import Variables.Variable;
import World.Tile;


public class EditLevelState extends BasicGameState{

	/*
	 * VARIABLES
	 */
	public static int  ID;
	private int tilesLength = 16;
	private Tile[][] tiles;
	private boolean mouseDown = false;
	private Image grass, stone;
	private int tileSize = 32;
	private boolean addStone = false, erase = false;
	private int windowSizeX2, windowSizeY2, row, collumn;
	private Font font1, font2; //FONT STUF
	private TrueTypeFont t, t2; //FONT STUFF
	private LinkedList<Buttons> buttons = new LinkedList<Buttons>(); //ARAY OF BUTTONS
	private LinkedList<CustomButton> customButtons = new LinkedList<CustomButton>();
	
	/*
	 * CONSTRUCTOR
	 */
	public EditLevelState(int ID)
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
		//background = new Image("Images/background.jpg");
		//background = background.getScaledCopy(800, 600);
		//MAKING THE FONTS
		font1 = new Font("Copperplate Gothic Bold", Font.BOLD, 35);
		font2 = new Font("Copperplate Gothic Bold", Font.BOLD, 15);
		t = new TrueTypeFont(font1, false);
		t2 = new TrueTypeFont(font2, false);
		grass = new Image("Images/grass.jpg");
		stone = new Image("Images/stone.jpg");
		grass = grass.getScaledCopy(tileSize, tileSize);
		stone = stone.getScaledCopy(tileSize, tileSize);
		
		//MAKING BUTTONS
		buttons.add(new Buttons(windowSizeX2-(getTextWidth("Back")/2), 10,"Back",font1,Variable.settingsState));
		customButtons.add(new CustomButton(10,50,100,100,"Add Stone", font2, Color.green, Color.gray, 1));
		customButtons.add(new CustomButton(10,175,100,100,"Erase", font2, Color.red, Color.gray, 2));
		customButtons.add(new CustomButton(10,300,100,100,"Print", font2, Color.cyan, Color.gray, 3));
		
		String file = "C:/Users/15RSeifert/workspace/Tower Defense 2/bin/Maps/mapTemplate";
		try {
			tiles = getMap(file);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/*
	 * RENDERS THE GRAPHICS, CALLED CONSTANTLY
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		
		for (int x=0; x < tiles.length; x++)
		{
			for (int y=0; y < tiles.length; y++)
			{
				switch (tiles[x][y].getType())
				{
					case 0:
						tiles[x][y].draw(g, grass);
						break;
					case 1:
						tiles[x][y].draw(g, stone);
						break;
					default:
						tiles[x][y].draw(g);
						break;
				}
			}
		}
		
		//DRAWING THE BACKGROUND AND THE MAIN MENU
		//g.drawImage(background, 0, 0);
		//HARDCODING THE MAIN MENU
	//	g.setColor(new Color(75,75,75,200));
		//g.fillRect(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, getTextWidth("Settings"), getTextHeight("Main Menu"));
		//g.setColor(Color.black);
		//g.drawRect(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, getTextWidth("Settings"), getTextHeight("Main Menu"));
		//g.setColor(Color.magenta); //testing
		//t.drawString(windowSizeX2-(getTextWidth("Settings")/2), windowSizeY2-250, "Settings", new Color(0,227,4));
		//END OF HARD CODING MAIN MENU AND BACKGROUND
		
		//DRAWING EACH BUTTON
		for (Buttons b : buttons)
		{
			b.draw(g);
		}
		
		for(CustomButton b : customButtons)
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
	
		/*if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
			mouseDown = true;
		}
		else
		{
			if (mouseDown)
				mouseDown = false;
		}
		
		if(!mouseDown)
		{
			for (int x=0; x < tiles.length; x++)
			{
				for (int y=0; y < tiles.length; y++)
				{
					if(tiles[x][y].isChanged())
						tiles[x][y].setChanged(false);
				}
			}
		}*/
		
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
		
		for (CustomButton b : customButtons)
		{
			if (b.containsPoint(input.getMouseX(), input.getMouseY()))
			{
				b.setHighlighted(true);
				//System.out.println("Clicked");
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					//System.out.println("Clicked");
					if (b.getID() == 1)
					{
						erase = false;
						addStone = true;
					}
					else if (b.getID() == 2)
					{
						addStone = false;
						erase = true;
					}
					else if(b.getID() == 3)
					{
						System.out.println("---NEW LEVEL---");
						for (int x=0; x < tiles.length; x++)
						{
							for (int y=0; y < tiles.length; y++)
							{
								System.out.print(tiles[y][x].getType());
							}
							System.out.println();
						}
						System.out.println("---END OF---");
					}
				}
			}
			else
			{
				if (b.getID() == 1 && addStone)
				{
					b.setHighlighted(true);
				}
				else if (b.getID() == 2 && erase)
				{
					b.setHighlighted(true);
				}
				else
				{
					b.setHighlighted(false);
				}
			}
		}
		
		for (int x=0; x < tiles.length; x++)
		{
			for (int y=0; y < tiles.length; y++)
			{
				if(tiles[x][y].getBoundingBox().contains(new Point(input.getMouseX(), input.getMouseY())))
				{
					if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
					{
						
						if (addStone)
						{
							if(tiles[x][y].getType()==0)
								tiles[x][y].setType(1);
						}
						else if(erase)
						{
							if(tiles[x][y].getType()==1)
								tiles[x][y].setType(0);
						}
					}
					if (input.isKeyPressed(Input.KEY_1))
					{
						if(tiles[x][y].getType()==2)
							tiles[x][y].setType(0);
						else
							tiles[x][y].setType(2);
					}
					else if(input.isKeyPressed(Input.KEY_2))
					{
						if(tiles[x][y].getType()==3)
							tiles[x][y].setType(0);
						else
							tiles[x][y].setType(3);
					}
				}
			}
		}
			//game.enterState(Settings.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.white));
	}
		

	public Tile[][] getMap(String file) throws NumberFormatException, IOException
	{
		Tile[][] tiles = new Tile[tilesLength][tilesLength];
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		Color color = Color.red;
		int i = 0;
		String line, temp2;
		char temp;
		while ((line = br.readLine()) != null)
		{
			//System.out.println(line);
			for (int j=0; j < line.length(); j++)
			{
				//System.out.print(line.charAt(j));
				temp = line.charAt(j);
				temp2 = Character.toString(temp);
				switch (Integer.parseInt(temp2))
				{
				case 0:
					color = Color.green;
					break;
				case 1:
					color = Color.gray;
					break;
				case 2:
					color = Color.blue;
					break;
				case 3:
					color = Color.magenta;
					break;
				default:
					color = color.green;
					break;
				}
				tiles[j][i] = new Tile(j*tileSize + (windowSizeX2-((tileSize*tilesLength)/2)), i*tileSize+ (windowSizeY2-((tileSize*tilesLength)/2)), tileSize, Integer.parseInt(temp2), color);	
				System.out.print(Integer.parseInt(temp2));
			}
				System.out.println("");
			i++;
		}
		
		return tiles;
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
		return (int)font1.getStringBounds(text, Variable.frc).getWidth();
	}
	
	//METHOD FOR GETTING HEIGHT OF A STRING IN PIXELS
	public int getTextHeight(String text)
	{
		return (int) font1.getStringBounds(text, Variable.frc).getHeight();
	}

}
