package GUI;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import Variables.Variable;

import java.awt.Font;
import java.awt.font.FontRenderContext;

public class CustomButton {

	private int x,y,width,height, textWidth, textHeight;
	private String text;
	private Rectangle bounds;
	private Font font;
	private TrueTypeFont t;
	private int ID;

	private boolean highlighted;
	private Color color, color2;
	
	public CustomButton(int x, int y, int width, int height, String text, Font font, Color color, Color color2, int ID)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.font = font;
		this.color = color;
		this.color2 = color2;
		this.ID = ID;
		t = new TrueTypeFont(font, false);
		textWidth = (int)font.getStringBounds(text, Variable.frc).getWidth();
		textHeight = (int) font.getStringBounds(text, Variable.frc).getHeight();
		highlighted = false;
		bounds = new Rectangle(x,y,width,height);
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		ID = ID;
	}
	
	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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
		g.setColor(color);
		if (!highlighted)
		{
			g.fillRect(x, y, width, height);
		}
		else
		{
			g.setColor(color2);
			g.fillRect(x, y, width, height);
		}
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		//g.setColor(Color.magenta); //testing
		t.drawString(x + ((width/2) - (textWidth/2)), y + ((height/2) - (textHeight/2)), text, Color.black);
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