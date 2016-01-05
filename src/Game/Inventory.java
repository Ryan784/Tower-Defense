package Game;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;

import Variables.Variable;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public class Inventory {

	private int x,y,width,height, textWidth, textHeight, size, ID;
	private String name, desc;
	private Image image;
	private Rectangle bounds;
	private Font font;
	private TrueTypeFont t;
	private boolean highlighted;
	
	public Inventory(int x, int y, Image image, int size, Font font, String name, String desc, int ID)
	{
		this.x = x;
		this.y = y;
		//this.width = width;
		//this.height = height;
		//this.text = text;
		this.size = size;
		this.image = image;
		this.font = font;
		this.name = name;
		this.desc = desc;
		this.ID = ID;
		t = new TrueTypeFont(font, false);
		//textWidth = (int)font.getStringBounds(text, Variable.frc).getWidth();
		//textHeight = (int) font.getStringBounds(text, Variable.frc).getHeight();
		highlighted = false;
		bounds = new Rectangle(x,y,size,size);
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public int getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	public int getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public TrueTypeFont getT() {
		return t;
	}

	public void setT(TrueTypeFont t) {
		this.t = t;
	}

	public void draw(Graphics g)
	{
		g.setColor(new Color(75,75,75,200));
		if (!highlighted)
		{
			g.fillRect(x, y, size, size);
		}
		else
		{
			g.setColor(new Color(100,100,100,255));
			g.fillRect(x, y, size, size);
		}
		g.drawImage(image, x+(size/4),y+(size/4));
		g.setColor(Color.red);
		g.drawRect(x, y, size, size);
		//g.setColor(Color.magenta); //testing
		//t.drawString(x, y, text, new Color(0,227,200));
	}
	
	public boolean containsPoint(int x, int y)
	{
		if (bounds.contains(x, y))
		{
			return true;
		}
		return false;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
