package GameStates;

import Maps.*;
import World.*;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Point;
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
import Game.Enemy;
import Game.Inventory;
import Variables.Variable;

public class PlayState extends BasicGameState{

	/*
	 * VARIABLES
	 */
	public static int  ID;
	private int windowSizeX2, windowSizeY2;
	private boolean paused = false;
	private Image background, grass, stone, tower, inv; //THE BACKGROUND
	private Font font, font2, font3, font4, font5; //FONT STUF
	private Tile[][] tiles;
	private int tileSize = 32, tilesLength = 16, towerSelectedID, counter = 0, health, money;
	private Random ran = new Random();
	private Point startTilePoint, endTilePoint;
	private String enemyDirection;
	private TrueTypeFont t, t2, t3, t4, t5; //FONT STUFF
	private LinkedList<Buttons> buttons = new LinkedList<Buttons>(); //ARAY OF BUTTONS
	private LinkedList<Inventory> inventory = new LinkedList<Inventory>(); //ARAY OF BUTTONS
	private LinkedList<CustomButton> customButtons = new LinkedList<CustomButton>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	private Tile[] enemyPath;
	
	/*
	 * CONSTRUCTOR
	 */
	public PlayState(int ID)
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
		health = 100;
		money = 500;
		System.out.println(GL11.glGetInteger(GL11.GL_MAX_TEXTURE_SIZE)); //TEST
		//MAKING THE BACKGROUND
		background = new Image("Images/background2.jpg");
		grass = new Image("Images/grass.jpg");
		stone = new Image("Images/stone.jpg");
		tower = new Image("Images/tower.png");
		//inv = new Image("Images/inventoryTowers.png");
		
		background = background.getScaledCopy(800, 600);
		grass = grass.getScaledCopy(tileSize, tileSize);
		stone = stone.getScaledCopy(tileSize, tileSize);
		tower = tower.getScaledCopy(tileSize, tileSize);

		//MAKING THE FONTS
		font = new Font("Copperplate Gothic Bold", Font.BOLD, 35);
		font2 = new Font("Copperplate Gothic Bold", Font.PLAIN, 15);
		font3 = new Font("Copperplate Gothic Bold", Font.PLAIN, 12);
		font4 = new Font("Serif", Font.PLAIN, 16);
		font5 = new Font("Times New Roman", Font.PLAIN, 15);
		t = new TrueTypeFont(font, false);
		t2 = new TrueTypeFont(font2, false);
		t3 = new TrueTypeFont(font3, false);
		t4 = new TrueTypeFont(font4, false);
		t5 = new TrueTypeFont(font5, false);
		//MAKING BUTTONS
		buttons.add(new Buttons((windowSizeX2-(getTextWidth("Quit")/2))/2, 10,"Quit",font,1));
		buttons.add(new Buttons(((windowSizeX2-(getTextWidth("Pause")/2))/2)*3, 10,"Pause",font,77));
		
		//INVENTORY TOWERS
		for (int i=0; i < 9; i++)
		{
			int tileX = ((Variable.windowSizeX/2)-((tilesLength/2)*tileSize)) + (i*59);
			
			inventory.add(new Inventory((((Variable.windowSizeX/2)-((tilesLength/2)*tileSize)) + (i*59)) + 1, Variable.windowSizeY-61,tower,58,font,"Default Tower " + i,"A default tower " + i, i));
			System.out.println(i + ": X: " + tileX);
		}
		
		customButtons.add(new CustomButton(673,73,113,70,"Buy", font2, Color.green, Color.gray, 3));
		
		String file = "C:/Users/Ryan/workspace/Tower Defense 2/bin/Maps/map1";
		try {
			tiles = getMap(file);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		enemyPath = getPath(); // gets the tilepath for enemies
		
		//enemies.add(new Enemy(tiles[startTilePoint.getX()][startTilePoint.getY()].getX() + 8, tiles[startTilePoint.getX()][startTilePoint.getY()].getY() + 8, 16, 16, 6, Color.red));
	}//END OF INIT	
	
	
	//GET THE ENEMIES TILE PATHING, UNNECCESARILY COMPLICATED
	public Tile[] getPath()
	{
		Tile[] temp = new Tile[enemyPath.length];
		System.out.println("Tile Length: " + enemyPath.length);
		Tile start = tiles[startTilePoint.getX()][startTilePoint.getY()];
		//System.out.println("Start Tile X: " + startTile.getX() + " | Start Tile Y: " + startTile.getY());
		Tile end = tiles[endTilePoint.getX()][endTilePoint.getY()];
		int tempTileX = 0;
		int tempTileY = 0;
		int tempX = 0;
		int tempY = 0;
		
		temp[0] = start;
		//System.out.println("StartTileX: " + startTilePoint.getX() + " | StartTileY: "+ startTilePoint.getY());
		temp[temp.length-1] = end;
		
		for (int i=0; i < temp.length; i++) //ITERATE THROUGH THE TILEPATH ARRAY
		{
			if (i != 0 && i != temp.length-1) // DO NOT RUN IF I == START OR END TILE
			{
				
				//GETTING POSITION IN ARRAY OF PARENT TILE
				Tile parentTile = temp[i-1];
				for (int x=0; x < tiles.length; x++)
				{
					for (int y=0; y < tiles.length; y++)
					{
						if (parentTile == tiles[x][y])
						{
							tempTileX = x; 
							tempTileY = y;
							break;
						}
					}
				}
				//System.out.println("");
				//System.out.println("Parent Tile X: " + tempTileX + " | Parent Tile Y: " + tempTileY);
				
				//CYCLE THROUGH A 3X3 RADIUS OF TILES, WITH PARENT TILE IN THE CENTER (0,0)
				/*for (int x=-1; x <= 1; x++)
				{
					for (int y=-1; y <= 1; y++)
					{
						
						//boolean foundTile = false;
						tempX = tempTileX + x;
						tempY = tempTileY + y;
						//System.out.println("Actual X: " + tempTileX + " | Actual Y: " + tempTileY);
						if (tempX >= 0 && tempY >= 0)
						{
							if(tiles[tempX][tempY].getType() == 1)
							{
								//System.out.println("Current Tile X: " + tempX + " | Current Tile Y: " + tempY + " | Current Type: " );
								if (i < 2)
								{
									if (tiles[tempX][tempY] != parentTile)
									{
										System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
										temp[i] = tiles[tempX][tempY];
										break;
									}
								}
								else
								{
									if (tiles[tempX][tempY] != temp[i-2] && tiles[tempX][tempY] != parentTile)
									{
										System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
										temp[i] = tiles[tempX][tempY];
										break;
									}
								}// end of else
							}//end of i.type == 1
							
						}//end of if tempX > 0 && tempY > 0
					}
				}*/
				System.out.println("Previous Tile: Tile[" + tempTileX + "][" + tempTileY + "]");
				System.out.println(i);
				tempX = tempTileX;
				tempY = tempTileY;
				for (int k=-1; k < 2; k = k + 2)
				{
					//System.out.println(k);
					if (tempX+k >= 0 && tempX+k < tilesLength)
					{
						int temp2 = tempX+k;
						//System.out.println("X >= 0");
						System.out.println("tile[" + temp2 + "][" + tempY + "].getType() == " + tiles[temp2][tempY].getType());
						if (tiles[tempX+k][tempY].getType() == 1)
						{
							
							//System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
							if (i >= 2)
							{
								if (tiles[tempX+k][tempY] != temp[i-2])
								{
									//System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
									temp[i] = tiles[tempX+k][tempY];
									System.out.println("tile[" + temp2 + "][" + tempY + "] != temp[i-2]");
									//System.out.println("Previous Tile: Tile[" + temp2 + "][" + tempY + "]");
									break;
								}
								else
								{
									System.out.println("tile[" + temp2 + "][" + tempY + "] == temp[i-2]");
								}
							}
							else
							{
								temp[i] = tiles[tempX+k][tempY];
								
								//System.out.println("Previous Tile: Tile[" + temp2 + "][" + tempY + "]");
								break;
							}
						}
					}
					if (tempY+k >= 0 && tempY+k < tilesLength)
					{
						int temp2 = tempY+k;
						//System.out.println("Y >= 0");
						System.out.println("tile[" + tempX + "][" + temp2 + "].getType() == " + tiles[tempX][temp2].getType());
						if (tiles[tempX][tempY+k].getType() == 1)
						{
							//System.out.println("Y + 1 == tileType 1");
							//System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
							//if (tiles[tempX][tempY+k].getType() == 1)
							//{
								if (i >= 2)
								{
									if (tiles[tempX][tempY+k] != temp[i-2] )
									{
										//System.out.println("Current Tile: " + i + " | Current X: " + tempX + " | Current Y: " + tempY);
										temp[i] = tiles[tempX][tempY+k];
										System.out.println("tile[" + tempX + "][" + temp2 + "] != temp[i-2]");
										//System.out.println("Previous Tile: Tile[" + tempX + "][" + temp2 + "]");
										break;
									}
									else
									{
										System.out.println("tile[" + tempX + "][" + temp2 + "] == temp[i-2]");
									}
								}
								else
								{
									temp[i] = tiles[tempX][tempY+k];
									
									//System.out.println("Previous Tile: Tile[" + tempX + "][" + temp2 + "]");
									break;
								}
							//}
						}
					}
				}

				System.out.println("");
				
				
			}
			
		}//END OF CYCLING THROUGH PATHTILE ARRAY
		
		
		for (int i=0; i < temp.length; i++)
		{
			for (int x=0; x < tiles.length; x++)
			{
				for (int y=0; y < tiles.length; y++)
				{
					if (temp[i] == tiles[x][y])
					{
						System.out.println("Current Tile X: " + x + " | Current Tile Y: " + y + " | Current Type: " );
					}
				}
			}
			//System.out.println("X: " + temp[i].getX() + " | Y:" + temp[i].getY());
		}
		return temp;
	}//END OF GETPATH
	
	
	
	/*
	 * RENDERS THE GRAPHICS, CALLED CONSTANTLY
	 */
	public void drawInfoBox(Graphics g)
	{
		g.setColor(Color.yellow);
		g.drawString("Money: " + money, 670, 45);
		g.setColor(Color.blue);
		g.drawRect(670, 70, 120, 500);
		g.drawLine(673, 743, 785, 743);
		
		g.setColor(Color.white);
		t2.drawString(675, 150, "Selected: " + towerSelectedID);
		g.drawImage(inventory.get(towerSelectedID).getImage(), 675, 175);
		t4.drawString(675, 220, inventory.get(towerSelectedID).getName());
		t4.drawString(675, 240, inventory.get(towerSelectedID).getDesc());
	}
	
	
	public void drawInventoryBox(Graphics g)
	{
		g.setColor(Color.red);
		g.drawRect((Variable.windowSizeX/2)-((tilesLength/2)*tileSize), ((Variable.windowSizeY/2)+((tilesLength/2)*tileSize))+5, 535, 64);
	}
	
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		Input input = container.getInput();
		//g.drawImage(inv,200,Variable.windowSizeY-65);
		
		g.setLineWidth(6);
		
		drawInventoryBox(g);
		drawInfoBox(g);
		
		g.setLineWidth(1);
		g.setColor(Color.red);
		String temp = "Health: " + health;
		g.drawString(temp, windowSizeX2-50, 10);
		
		for (int x=0; x < tiles.length; x++)
		{
			for (int y=0; y < tiles.length; y++)
			{
				//int temp = x+y;
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
				//g.setColor(Color.cyan);
				
			}
		}
		/*for (int x=0; x < tiles.length; x++)
		{
			for (int y=0; y < tiles.length; y++)
			{
				//if (tiles[x][y].getType() == 1)
					t5.drawString(tiles[x][y].getX(), tiles[x][y].getY(), x + " " + y,Color.cyan);
			}
		}*/
		
		for (int i=0; i < enemyPath.length-1; i++)
		{
			g.setColor(Color.cyan);
			g.drawLine(enemyPath[i].getX()+(tileSize/2), enemyPath[i].getY()+(tileSize/2), enemyPath[i+1].getX()+(tileSize/2), enemyPath[i+1].getY()+(tileSize/2));
		}
		
		//DRAWING EACH BUTTON
		for (Buttons b : buttons)
		{
			b.draw(g);
		}
		
		for(CustomButton b : customButtons)
		{
			b.draw(g);
		}
		
		for (Inventory i : inventory)
		{
			i.draw(g);
		}
		
		for (Inventory i : inventory)
		{
			if (i.containsPoint(input.getMouseX(), input.getMouseY()))
			{
				drawToolTip(g, input.getMouseX(), input.getMouseY(), "Tower: " + i.getID());
			}
		}
		
		for (Enemy e : enemies)
		{
			e.draw(g);
			t5.drawString(e.getCenterX(), e.getCenterY(), "" + e.getSpeed());
		}
		
		if (paused)
		{
			g.setColor(new Color(255,255,255,50));
			g.fillRect(0, 0, Variable.windowSizeX, Variable.windowSizeY);
			g.setColor(Color.blue);
			g.setColor(Color.magenta); //testing
			t.drawString(windowSizeX2-(getTextWidth("PAUSED")/2), windowSizeY2-150, "PAUSED", new Color(0,227,4));
		}
		//END OF DRAWING THE BUTTONS
	}//END OF RENDER

	
	/*
	 * UPDATES, CALLED ONSTANTLY
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{
		Input input = container.getInput();
		
		counter += delta;
		
		
		for (Buttons b : buttons)
		{
			if (b.containsPoint(input.getMouseX(), input.getMouseY()))
			{
				b.setHighlighted(true);
				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					if (b.getID() == 77)
					{
						paused = !paused;
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
		
		if (!paused)
		{
			
			if (counter > 1)
			{

				int	randomSpeed = ran.nextInt(15)+1;
				
				enemies.add(new Enemy(tiles[startTilePoint.getX()][startTilePoint.getY()].getX() + 8, tiles[startTilePoint.getX()][startTilePoint.getY()].getY() + 8, 16, 16, randomSpeed, new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255))));
				counter = 0;
				//System.out.println("Speed for [" + enemies.get + "] == " + randomSpeed);
			}
			
			for (Enemy e : enemies)
			{
				for (int i=0; i < enemyPath.length-1; i++)
				{
					if (enemyPath[i].containsFully(e.getX(), e.getY(), e.getWidth()))
					{
						e.setTile(enemyPath[i+1]);
					//	System.out.println("Current X: " + e.getX() + " | Current Y: " + e.getY());
					//	System.out.println("Moving to X: " + enemyPath[i+1].getX() + " | Y: " + enemyPath[i+1].getY());
					}
				}
			}
			
			for (Enemy e : enemies)
			{
				e.move();
				//System.out.println("Speed for [" +  + "] == " + e.g);
			}
			
			for (Inventory i : inventory)
			{
				if (i.containsPoint(input.getMouseX(), input.getMouseY()))
				{
					i.setHighlighted(true);
					//drawToolTip(g, input.getMouseX(), input.getMouseY(), "Tower: " + i.getID(), font3);
					if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
					{
						towerSelectedID = i.getID();
					}
				}
				else
				{
					i.setHighlighted(false);
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
							
						}
					}
				}
				else
				{
					b.setHighlighted(false);
				}
			}
			
			for (int i=0; i < enemies.size(); i++)
			{
				if (tiles[endTilePoint.getX()][endTilePoint.getY()].contains(enemies.get(i).getX(), enemies.get(i).getY()))
				{
					enemies.remove(i);
					health--;
				}
			}
		}
	}//END OF UPDATE
	
	
	public void drawToolTip(Graphics g, int x, int y, String text)
	{
		int width =  (int)font3.getStringBounds(text, Variable.frc).getWidth() + 2;
		int height =  (int)font3.getStringBounds(text, Variable.frc).getHeight() + 2;
		y = y - height;
		g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.orange);
		g.drawRect(x, y, width, height);
		t3.drawString(x, y, text);
	}
		
	

	public Tile[][] getMap(String file) throws NumberFormatException, IOException
	{
		
		Tile[][] tiles = new Tile[tilesLength][tilesLength];
		int tempInt = 0;
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
					//tempInt++;
					break;
				case 2:
					color = Color.blue;
					//startTilePoint = new Point(j,i);
					//tempInt++;
					break;
				case 3:
					color = Color.magenta;
					//endTilePoint = new Point(j,i);
					//tempInt++;
					break;
				default:
					color = color.green;
					break;
				}
				tiles[j][i] = new Tile(j*tileSize + (windowSizeX2-((tileSize*tilesLength)/2)), i*tileSize+ (windowSizeY2-((tileSize*tilesLength)/2)), tileSize, Integer.parseInt(temp2), color);	
				//System.out.print(Integer.parseInt(temp2));
			}
				//System.out.println("");
			i++;
		}//END OF GETTING BOARD
		
		
		for (int x=0; x < tiles.length; x++)
		{
			for (int y=0; y < tiles.length; y++)
			{
				switch (tiles[x][y].getType())
				{
					case 1:
						tempInt++;
						break;
					case 2:
						startTilePoint = new Point(x,y);
						tempInt++;
						break;
					case 3:
						endTilePoint = new Point(x,y);
						tempInt++;
						break;
				}
				//System.out.println("Number of Enemy Tiles: " + tempInt);
			}
		}
		
		enemyPath = new Tile[tempInt];
		//System.out.println("Number of Enemy Tiles: " + tempInt);

		if(tiles[startTilePoint.getX()+1][startTilePoint.getY()].getType() == 1)
		{
			enemyDirection = "right";
		}
		else if(tiles[startTilePoint.getX()][startTilePoint.getY()+1].getType() == 1)
		{
			enemyDirection = "down";
		}
		else if(tiles[startTilePoint.getX()][startTilePoint.getY()-1].getType() == 1)
		{
			enemyDirection = "up";
		}
		else if(tiles[startTilePoint.getX()-1][startTilePoint.getY()].getType() == 1)
		{
			enemyDirection = "left";
		}
		
		return tiles;
	}//END OF GETPATH
	
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

//ENEMY MOVEMENT
		/*try
		{
			for (int x=0; x < tiles.length; x++)
			{
				for (int y=0; y < tiles.length; y++)
				{
					for (Enemy e : enemies)
					{
						if(tiles[x][y].getBoundingBox().contains(e.getX(), e.getY()))
						{
							if (e.isDirRight() && tiles[x+1][y].getType() != 1)
							{
								if (tiles[x][y+1].getType() == 1)
								{
									e.resetDir();
									e.setDirDown(true);
								}
								else if (tiles[x][y-1].getType() == 1)
								{
									e.resetDir();
									e.setDirUp(true);
								}
							}
							else if(e.isDirLeft()&& tiles[x-1][y].getType() != 1)
							{
								if (tiles[x][y+1].getType() == 1)
								{
									e.resetDir();
									e.setDirDown(true);
								}
								else if (tiles[x][y-1].getType() == 1)
								{
									e.resetDir();
									e.setDirUp(true);
								}
							}
							else if(e.isDirDown()&& tiles[x][y+1].getType() != 1)
							{
								if (tiles[x+1][y].getType() == 1)
								{
									e.resetDir();
									e.setDirRight(true);
								}
								else if (tiles[x-1][y].getType() == 1)
								{
									e.resetDir();
									e.setDirLeft(true);
								}
							}
							else if(e.isDirUp()&& tiles[x][y-1].getType() != 1)
							{
								if (tiles[x+1][y].getType() == 1)
								{
									e.resetDir();
									e.setDirRight(true);
								}
								else if (tiles[x-1][y].getType() == 1)
								{
									e.resetDir();
									e.setDirLeft(true);
								}
							}
							
						}
					}
				}
			}
		}
		catch(Exception e){};*/
		//END OF ENEMY MOVEMENT, BLEH
