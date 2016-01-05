package World;



import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile {

	private int x,y,type;
	private int size;
	private Rectangle boundingBox, boundingBox2;
	private boolean hasTower = false;
	private Color color;
	private boolean changed = false;

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public Tile(int x, int y, int size, int type, Color color)
	{
		this.x = x;
		this.y = y;
		this.size = size;
		this.type = type;
		this.color = color;
		boundingBox = new Rectangle(x, y,size,size);
		boundingBox2 = new Rectangle(x+(size/3),y+(size/3),size/3,size/3);
	}
	
	public boolean contains(int xCoord, int yCoord)
	{
		if (boundingBox.contains(xCoord, yCoord))
		{
			return true;
		}
		return false;
	}
	
	public boolean containsFully(int xCoord, int yCoord, int length)
	{
		if (boundingBox.contains(xCoord, yCoord) && boundingBox.contains(xCoord + length, yCoord + length))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean containsCenter(int xCoord, int yCoord)
	{
		if (boundingBox2.contains(xCoord, yCoord))
		{
			return true;
		}
		return false;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		if (type == 2)
			g.setColor(Color.blue);
		else if (type == 3)
			g.setColor(Color.magenta);
		g.fillRect(x, y, size, size);
	}
	
	public void draw(Graphics g, Image image)
	{
		g.setColor(color);
		g.drawImage(image, x, y);
		//g.fillRect(x, y, size, size);
	}
	
	public boolean hasTower() {
		return hasTower;
	}

	public void setHasTower(boolean hasTower) {
		this.hasTower = hasTower;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public void setBoundingBox(Rectangle boundingBox) {
		this.boundingBox = boundingBox;
	}

	public int getX() {
		return x;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
