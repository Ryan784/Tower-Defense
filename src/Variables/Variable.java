package Variables;

import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import org.newdawn.slick.Image;

public class Variable {

	/*
	 * VARIABLES, OBVIOUSLY
	 */
	
	public static final int menuState = 1; // the ID (int) of the Menu state
	public static final int settingsState = 2; // the ID of Settings state
	public static final int ChooseDifficulty = 3;
	public static final int playState = 4;
	public static final int editLevelState = 5;
	public static final int windowSizeX = 800,windowSizeY = 650;
	public static  AffineTransform affineTransform = new AffineTransform();
	public static FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
	
}
