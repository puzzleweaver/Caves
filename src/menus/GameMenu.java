package menus;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import main.Input;
import main.Main;
import main.World;
import objects.Enemy;
import objects.Inventory;
import objects.Item;
import objects.Player;
import objects.Simple;
import objects.enemies.Burr;
import objects.enemies.Golem;
import objects.enemies.Mushroom;
import objects.enemies.Skull;
import res.Sprite;

public class GameMenu implements Menu {

	public ArrayList<Item> objects = new ArrayList<Item>();
	public ArrayList<Enemy> enemies  = new ArrayList<Enemy>();
	public Inventory inventory = new Inventory();
	public Player player;
	public int sX, sY;
	
	public GameMenu() {
		
		player = new Player(World.w*16, World.h*16);
		enemies.add(new Skull((int)player.x, (int)player.y-100));
		enemies.add(new Burr((int)player.x, (int)player.y));
		enemies.add(new Golem((int)player.x, (int)player.y));
		enemies.add(new Mushroom((int)player.x, (int)player.y));
		
	}
	
	public void update() {

		Item d;
		for(int i = 0; i < objects.size(); i++) {
			d = objects.get(i);
			d.update();
			if(d.done() > 0){
				inventory.add(d);
				objects.remove(i);
				i--;
			}
		}
		
		player.update();
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
			if(enemies.get(i).dead){
				enemies.remove(i);
				break;
			}
		}
		
		if(Input.mouseButtonPressed(0)){
			double x = (Input.mouseX()+sX)/(Main.scale*8), y = (Input.mouseY()+sY)/(Main.scale*8);
			if(Simple.pointRect(Input.mouseX(), Input.mouseY(), Main.w-Main.scale*13, Main.scale, Main.scale*12, Main.scale*12)){
				Main.menu = Main.pauseMenu;
			}else if(World.state[(int)x][(int)y] == 33){
				for(int i = 0; i < Main.r.nextInt(5)+2; i++){
					objects.add(new Item((int)x*32, (int)y*32, Inventory.chestSpawn()));
				}
				World.state[(int)x][(int)y] = 32;
			}
		}
		
		scroll();
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		// render terrain
		int x, y;
		int begI = (int) (-Main.w/16.0/Main.scale-2.0), endI = -begI,
				begJ = (int) (-Main.h/16.0/Main.scale-2.0), endJ = -begJ+1;
		for(int i = begI; i < endI; i++){
			for(int j = begJ; j < endJ; j++) {
				x = ((int)(player.x/32+i)*32);
				y = ((int)(player.y/32+j)*32);
				g.drawImage(Sprite.textures[World.state[(int)player.x/32+i][(int)player.y/32+j]], (int) (x*0.25*Main.scale)-sX, (int) (y*0.25*Main.scale)-sY);
			}
		}
		g.setColor(Color.white);
		
		// render items in the overworld
		for(int i = 0; i < objects.size() && i >= 0; i++){
			Item d = objects.get(i);
			d.render(g);
		}
		
		// render entities
		player.render(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).render(g);
		}
		
		drawLighting(g);
		
		// draw player health and pause button
		g.drawImage(Sprite.boxImage[0], Main.w-(int)(Main.scale*13), (int)Main.scale);
		x = 10;
		for(int i = 0; i < player.getFull(); i++){
			g.drawImage(Sprite.heart[0], x, 10, null);
			x += 32;
		}
		if(player.hasHalf()){
			g.drawImage(Sprite.heart[1], x, 10, null);
		}
		
	}
	
	public void drawLighting(Graphics g){
//		
//		double d;
//		Enemy e;
//		
//		for(int i = -1; i < Main.w*0.5/Main.scale+3; i++) {
//			for(int j = -1; j < Main.h*0.5/Main.scale+3; j++) {
//				
//				// calculate lit entity closest to the shading cell
//				d = (i*Main.scale*4-sX%(Main.scale*8)-Main.w/2)*(i*Main.scale*4-sX%(Main.scale*8)-Main.w/2) + (j*Main.scale*4-sY%(Main.scale*8)-Main.h/2)*(j*Main.scale*4-sY%(Main.scale*8)-Main.h/2);
//				
//				// set color and draw shading cell
//				g.setColor(new Color(0, 0, 0, (int) Math.min(255, 0.005*d)));
//				g.fillRect((int) (i*Main.scale*4-sX%(Main.scale*8)), (int) (j*Main.scale*4-sY%(Main.scale*8)), (int) (Main.scale*4), (int)(Main.scale*4));
//			}
//		}
//		g.setColor(new Color(255, 255, 255, 255));
		
	}
	
	public void scroll(){
		
		if(player.x*0.25*Main.scale+4*Main.scale-sX >= Main.w/2){
			sX = (int)(player.x*0.25*Main.scale+4*Main.scale-Main.w/2);
		}else if(player.x*0.25*Main.scale+4*Main.scale-sX <= Main.w/2){
			sX = (int)(player.x*0.25*Main.scale+4*Main.scale-Main.w/2);
		}
		
		if(player.y*0.25*Main.scale+4*Main.scale-sY >= Main.h/2){
			sY = (int)(player.y*0.25*Main.scale+4*Main.scale-Main.h/2);
		}else if(player.y*0.25*Main.scale+4*Main.scale-sY <= Main.h/2){
			sY = (int)(player.y*0.25*Main.scale+4*Main.scale-Main.h/2);
		}
		
	}
	
}
