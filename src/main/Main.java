package main;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Random;

import objects.*;

import com.henagongames.game.*;
import com.henagongames.geometry.Simple;
import com.henagongames.image.Sprite;
import com.henagongames.input.Mouse;
import com.henagongames.tools.Tools;

import objects.enemies.*;
import menus.*;

public class Main extends GameApplet implements MouseWheelListener{

	public static Inventory inventory = new Inventory();
	public static int state[][] = new int[1000][2000];
	public static Image textures[] = new Image[36], parts[] = new Image[6],
			items[], boxImage[];
	public static int sX, sY, menu;
	public static Random r = new Random();
	public static Player player;
	public static ArrayList<Item> objects = new ArrayList<Item>();
	public static ArrayList<Enemy> enemies  = new ArrayList<Enemy>();
	public static final int MENU_GAME = 0, MENU_PAUSE = 1, MENU_MAIN = 2;
	private boolean released = true;

	protected void begin(){
		loadImages();
		player = new Player(400*32, 400*32);
		setMenu(new GameMenu());
		World.loadWorld(GameMenu.image);
		setAppletSize(400, 400);
		addMouseWheelListener(this);
		menu = MENU_GAME;
		startTimer(15);
		enemies.add(new Skull(player.getX(), player.getY()));
		enemies.add(new Burr(player.getX(), player.getY()+288));
		enemies.add(new Golem(player.getX(), player.getY()+200));
	}
	public void loadImages(){
		boxImage = Sprite.getSprites(getClass().getResource("/images/box.png"), 0, 0, 12, 12, 2, 2);
		boxImage = Sprite.resize(boxImage, 36, 36, Sprite.EDIT_COARSE);
		items = Sprite.getSprites(getClass().getResource("/images/items.png"), 0, 0, 8, 8, 35, 6);
		items = Sprite.resize(items, 32, 32, Sprite.EDIT_COARSE);
		textures = Sprite.getSprites(getClass().getResource("/images/oWImages.png"), 0, 0, 8, 8, 38, 8);
		textures = Sprite.resize(textures, 32, 32, Sprite.EDIT_COARSE);
		parts = Sprite.getSprites(getClass().getResource("/images/parts.png"), 0, 0, 4, 4, parts.length, 4);
		parts = Sprite.resize(parts, 8, 8, Sprite.EDIT_COARSE);
	}

	protected void gameLoop(){
		if(!mousePressed(Mouse.BUTTON_1)){
			released = true;
		}
		switch(menu){
		case MENU_PAUSE:
			if(released && mousePressed(Mouse.BUTTON_1)){
				if(Simple.pointRect(mouseX(), mouseY(), 20, 20, 152, 48)){
					menu = MENU_GAME;
					PauseMenu.selected = -1;
					setMenu(new GameMenu());
					released = false;
				}else{
					for(int i = 0; i < 98; i++){
						if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 28 + (i%7)*52, 80 + (i/7)*52, 48, 48)){
							if(PauseMenu.selected == -1){
								PauseMenu.selected = i;
							}else{
								inventory.swap(PauseMenu.selected, i);
								PauseMenu.selected = -1;
							}
							released = false;
							break;
						}
					}
					if(PauseMenu.selected != -1){
						if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 176, 800, 48, 48)){
							inventory.equip(Inventory.EQUIP_HEAD, inventory.item[PauseMenu.selected]);
						}else if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 176, 852, 48, 48)){
							inventory.equip(Inventory.EQUIP_TORSO, inventory.item[PauseMenu.selected]);
						}else if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 124, 852, 48, 48)){
							inventory.equip(Inventory.EQUIP_SHIELD, inventory.item[PauseMenu.selected]);
						}else if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 228, 852, 48, 48)){
							inventory.equip(Inventory.EQUIP_MELEE, inventory.item[PauseMenu.selected]);
						}else if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 176, 904, 48, 48)){
							inventory.equip(Inventory.EQUIP_FEET, inventory.item[PauseMenu.selected]);
						}else if(Simple.pointRect(mouseX(), mouseY()+PauseMenu.sY, 176, 20, 48, 48)){
							inventory.trash(PauseMenu.selected);
						}
					}
				}
			}
			break;
		case MENU_GAME:
			player.reset();
			if(keyPressed(KeyEvent.VK_RIGHT) || keyPressed(KeyEvent.VK_D)){
				player.moveRight();
			}if(keyPressed(KeyEvent.VK_LEFT) || keyPressed(KeyEvent.VK_A)){
				player.moveLeft();
			}
			player.move();
			if(keyPressed(KeyEvent.VK_UP) || keyPressed(KeyEvent.VK_W)){
				player.jump();
			}
			if(keyPressed(KeyEvent.VK_2)){
				GameMenu.on = true;
			}else{
				GameMenu.on = false;
			}
			if(released && mousePressed(Mouse.BUTTON_1)){
				int x = (mouseX()+sX)/32, y = (mouseY()+sY)/32;
				if(Simple.pointRect(mouseX(), mouseY(), 354, 10, 36, 36)){
					menu = MENU_PAUSE;
					setMenu(new PauseMenu());
				}else if(state[x][y] == 33){
					for(int i = 0; i < r.nextInt(5)+2; i++){
						objects.add(new Item(x*32, y*32, Inventory.chestSpawn()));
					}
					state[x][y] = 32;
				}
				released = false;
			}
			scroll();
			break;
		}
	}
	
	public static void setState(int x, int y, int w, int i, int nS){
		int nX = x - w/2, nY = y - w/2;
		try{
			state[nX + (i%w)][nY + (i/w)] = nS;
		}catch(Exception e){
		}
	}
	public static int getStateAt(int x, int y, int w, int i){
		int nX = x - w/2, nY = y - w/2;
		try{
			return state[nX + (i%w)][nY + (i/w)];
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
	
	private void scroll(){
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
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(menu == MENU_PAUSE && PauseMenu.sY + 142*e.getWheelRotation() >= 0 && PauseMenu.sY + 142*e.getWheelRotation() <= 568){
			PauseMenu.sY += 142*e.getWheelRotation();
		}
	}
	
}
