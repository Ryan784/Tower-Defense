package GUI;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import Variables.Variable;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public class Buttons {

	private int x,y,width,height, textWidth, textHeight, ID;
	private String text;
	private Rectangle bounds;
	private Font font;
	private TrueTypeFont t;
	private boolean highlighted;
	
	public Buttons(int x, int y, String text, Font font, int ID)
	{
		this.x = x;
		this.y = y;
		//this.width = width;
		//this.height = height;
		this.text = text;
		this.font = font;
		this.ID = ID;
		t = new TrueTypeFont(font, false);
		textWidth = (int)font.getStringBounds(text, Variable.frc).getWidth();
		textHeight = (int) font.getStringBounds(text, Variable.frc).getHeight();
		highlighted = false;
		bounds = new Rectangle(x,y,textWidth,textHeight);
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
			g.fillRect(x, y, textWidth+1, textHeight);
		}
		else
		{
			g.setColor(new Color(100,100,100,255));
			g.fillRect(x, y, textWidth+1, textHeight);
		}
		g.setColor(Color.black);
		g.drawRect(x, y, textWidth+1, textHeight);
		g.setColor(Color.magenta); //testing
		t.drawString(x, y, text, new Color(0,227,200));
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
