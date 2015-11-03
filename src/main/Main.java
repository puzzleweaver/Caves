package main;

import images.Sprite;

import java.util.Random;

import menus.GameMenu;
import menus.MainMenu;
import menus.Menu;
import menus.PauseMenu;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame {
	
	public static Menu menu;
	public static GameMenu gameMenu;
	public static PauseMenu pauseMenu;
	public static MainMenu mainMenu;
	public static Random r = new Random();
	public static final int MENU_GAME = 0, MENU_PAUSE = 1, MENU_MAIN = 2;
//	public static int w = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
//			h = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int w = 600, h = 600;
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(w, h, false);
			app.setMinimumLogicUpdateInterval(15);
			app.setVSync(true);
			//nothing after app.start() runs
			app.start();
		}catch(SlickException e) {
			System.out.println(e);
		}
	}
	
	public Main(){
		super("YOU'RE A BLIZZARD HARRY");
	}
	
	public void init(GameContainer arg0) throws SlickException {
		Sprite.init();
		gameMenu = new GameMenu();
		pauseMenu = new PauseMenu();
		mainMenu = new MainMenu();
		menu = mainMenu;
		World.loadWorld();
	}
	
	public void update(GameContainer arg0, int arg1) throws SlickException {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			System.exit(1);
		menu.update();
	}
	
	public static void setState(int x, int y, int w, int i, int nS){
		int nX = x - w/2, nY = y - w/2;
		try{
			World.state[nX + (i%w)][nY + (i/w)] = nS;
		}catch(Exception e){
		}
	}
	public static int getStateAt(int x, int y, int w, int i){
		int nX = x - w/2, nY = y - w/2;
		try{
			return World.state[nX + (i%w)][nY + (i/w)];
		}catch(Exception e){
			return 0;
		}
	}
	public static int getStateX(int x, int y, int w, int i){
		int nX = x - w/2;
		return nX + (i%w);
	}
	public static int getStateY(int x, int y, int w, int i){
		int nY = y - w/2;
		return nY + (i/w);
	}
	
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		menu.render(g);
	}

}
