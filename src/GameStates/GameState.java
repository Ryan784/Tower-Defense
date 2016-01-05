package GameStates;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import Variables.Variable;


public class GameState extends StateBasedGame{
	
	/*
	 * VARIABLES
	 */
	//
	//public static final int menuState = 1; // the ID (int) of the Menu state
	//public static final int settingsState = 2; // the ID of Settings state
	private static AppGameContainer app; // the app
	private Variable variable = new Variable();
	//private static int windowSizeX = 800,windowSizeY = 800; //window size
	
	
	/*
	 * CONSTRUCTOR, ADDING STATES
	 */
	public GameState(String name) throws SlickException
	{
		super(name);
	}
	
	
	/*
	 * THE 'MAIN'
	 * METHOD
	 */
	public static void main(String args[])
	{
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true"); // enable on school computers
		try
		{
			
			app = new AppGameContainer(new GameState("Tower Defense 2")); //creates the app
			app.setDisplayMode(Variable.windowSizeX,Variable.windowSizeY, false);//sets the display (size)
			app.setTargetFrameRate(200); // Capping the FPS
			app.start();//starts the app (similiar to thread.start())
		}catch (SlickException e) {e.printStackTrace();}
	}


	/*
	 * STATE INITILZATION
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		this.addState(new MenuState(Variable.menuState));
		this.addState(new SettingsState(Variable.settingsState));
		this.addState(new ChooseDifficulty(Variable.ChooseDifficulty));
		this.addState(new PlayState(variable.playState));
		this.addState(new EditLevelState(Variable.editLevelState));
	}

}