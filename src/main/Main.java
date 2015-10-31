package main;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import images.Sprite;
import menus.GameMenu;
import menus.Menu;
import menus.PauseMenu;
import objects.Enemy;
import objects.Inventory;
import objects.Item;
import objects.Player;

public class Main extends BasicGame {
	
	public static Inventory inventory = new Inventory();
	public static Image textures[] = new Image[36], parts[] = new Image[6],
			items[], boxImage[];
	public static int sX, sY;
	public static Menu menu, gameMenu, pauseMenu;
	public static Random r = new Random();
	public static Player player;
	public static ArrayList<Item> objects = new ArrayList<Item>();
	public static ArrayList<Enemy> enemies  = new ArrayList<Enemy>();
	public static final int MENU_GAME = 0, MENU_PAUSE = 1, MENU_MAIN = 2;

	public static void main(String[] args) {
		try{
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(1920, 1080, true);
			app.setMinimumLogicUpdateInterval(15);
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
		loadImages();
		player = new Player(400*32, 400*32);
		gameMenu = new GameMenu();
		pauseMenu = new PauseMenu();
		menu = gameMenu;
		World.loadWorld();
//		enemies.add(new Skull(player.getX(), player.getY()));
//		enemies.add(new Burr(player.getX(), player.getY()+288));
//		enemies.add(new Golem(player.getX(), player.getY()+200));
	}
	
	public void loadImages(){
		boxImage = Sprite.getSprites("images/box.png", 12, 12, 2, 2, 36, 36);
		items = Sprite.getSprites("images/items.png", 8, 8, 35, 6, 32, 32);
		textures = Sprite.getSprites("images/oWImages.png", 8, 8, 38, 8, 32, 32);
		parts = Sprite.getSprites("images/parts.png", 4, 4, parts.length, 4, 8, 8);
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
	
	public static void scroll(){
		if(player.getX()+16-sX >= 200){
			sX = player.getX()+16-200;
		}else if(player.getX()+16-sX <= 200){
			sX = player.getX()+16-200;
		}
		if(player.getY()+16-sY >= 200){
			sY = player.getY()+16-200;
		}else if(player.getY()+16-sY <= 200){
			sY = player.getY()+16-200;
		}
	}
	
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		menu.render(g);
	}

}
