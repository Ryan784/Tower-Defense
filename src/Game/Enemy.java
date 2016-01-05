package Game;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import World.Tile;

public class Enemy {

	private int x, y, width, height, speed;
	//private boolean dirRight=false, dirLeft=false, dirUp=false, dirDown=false;
	private Tile tile;
	private Color color;
	
	public Enemy(int x, int y, int width, int height, int speed, Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.color = color;
		
		/*if (dir.equalsIgnoreCase("up"))
		{
			dirUp = true;
		}
		else if(dir.equalsIgnoreCase("down"))
		{
			dirDown = true;
		}
		else if(dir.equalsIgnoreCase("left"))
		{
			dirLeft = true;
		}
		else if (dir.equalsIgnoreCase("right"))
		{
			dirRight = true;
		}*/
	}
	
	/*public void resetDir()
	{
		dirRight = false;
		dirLeft = false;
		dirUp = false;
		dirDown = false;
	}*/
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile)
	{
		this.tile = tile;
	}
	
	public void move()
	{
		int tempX = x + (width/2);
		int tempY = y + (height/2);
		int tileX = tile.getX() + (tile.getSize()/2);
		int tileY = tile.getY() + (tile.getSize()/2);
		
		//Rectangle tempArea = new Rectangle(tile.getX() + (tile.getSize()/4), tile.getY() + (tile.getSize()/4), tile.getSize()/4, tile.getSize()/4);
		
		if (tileX > tempX)
		{
			if (tileX <= tempX + speed)
				x = x + (tileX-tempX);
			else
				x = x + speed;
		}
		else if(tileX < tempX)
		{
			if (tileX >= tempX + speed)
				x = x - (tempX-tileX);
			else
				x = x - speed;
		}
		else if (tileY > tempY)
		{
			if (tileY <= tempY + speed)
				y = y + (tileY-tempY);
			else
				y = y + speed;
		}
		else if(tileY < tempY)
		{
			if (tileY >= tempY + speed)
				y = y - (tileY-tempY);
			else
				y = y - speed;
		}
		
	}

	public int getX() {
		return x;
	}

	public int getCenterX()
	{
		return x + (width/2);
	}
	
	public int getCenterY()
	{
		return y + (height/2);
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/*public boolean isDirRight() {
		return dirRight;
	}

	public void setDirRight(boolean dirRight) {
		this.dirRight = dirRight;
	}

	public boolean isDirLeft() {
		return dirLeft;
	}

	public void setDirLeft(boolean dirLeft) {
		this.dirLeft = dirLeft;
	}

	public boolean isDirUp() {
		return dirUp;
	}

	public void setDirUp(boolean dirUp) {
		this.dirUp = dirUp;
	}

	public boolean isDirDown() {
		return dirDown;
	}

	public void setDirDown(boolean dirDown) {
		this.dirDown = dirDown;
	}*/
}
