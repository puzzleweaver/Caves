package menus;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import images.Sprite;
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
import objects.enemies.Skull;

public class GameMenu implements Menu {

	public Inventory inventory = new Inventory();
	public int sX, sY;
	public Player player;
	public ArrayList<Item> objects = new ArrayList<Item>();
	public ArrayList<Enemy> enemies  = new ArrayList<Enemy>();
	
	public GameMenu() {
		player = new Player(World.w*16, World.h*16);
		enemies.add(new Skull(player.getX(), player.getY()-100));
		enemies.add(new Burr(player.getX(), player.getY()+288));
		enemies.add(new Golem(player.getX(), player.getY()+200));
	}
	
	public void update() {
		player.reset();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			player.moveRight();
		}if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			player.moveLeft();
		}
		player.move();
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			player.jump();
		}
		for(int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		if(Input.mouseButtonPressed(0)){
			int x = (Input.mouseX()+sX)/32, y = (Input.mouseY()+sY)/32;
			if(Simple.pointRect(Input.mouseX(), Input.mouseY(), 354, 10, 36, 36)){
				Main.menu = Main.pauseMenu;
			}else if(World.state[x][y] == 33){
				for(int i = 0; i < Main.r.nextInt(5)+2; i++){
					objects.add(new Item(x*32, y*32, Inventory.chestSpawn()));
				}
				World.state[x][y] = 32;
			}
		}
		scroll();
	}
	
	public void render(Graphics g){
		int x, y;
		for(int i = -Main.w/64-2; i < Main.w/64+2; i++){
			for(int j = -Main.h/64-2; j < Main.h/64+2; j++) {
				x = ((player.getX())/32+i)*32;
				y = ((player.getY())/32+j)*32;
				g.drawImage(Sprite.textures[World.state[player.getX()/32+i][player.getY()/32+j]], x-sX, y-sY);
			}
		}
		for(int i = 0; i < objects.size() && i >= 0; i++){
			Item d = objects.get(i);
			d.draw(g);
			d.move();
			if(d.done() > 0){
				if(d.done() == 2){
					inventory.add(d);
				}
				objects.remove(i);
				i--;
			}
		}
		player.render(g);
		for(int i = 0; i < enemies.size(); i++){
			enemies.get(i).render(g);
			if(enemies.get(i).dead){
				enemies.remove(i);
				break;
			}
		}
		drawLighting(g);
		g.drawImage(Sprite.boxImage[0], Main.w-42, 10);
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
		double d;
		Enemy ao;
		for(int i = -1; i < Main.w/16+3; i++) {
			for(int j = -1; j < Main.h/16+3; j++) {
				d = (i*16-sX%32-Main.w/2)*(i*16-sX%32-Main.w/2) + (j*16-sY%32-Main.h/2)*(j*16-sY%32-Main.h/2);
				for(int a = 0; a < enemies.size(); a++) {
					ao = enemies.get(a);
					d = Math.min(d, 2.0*((ao.x-sX+sX%32-i*16)*(ao.x-sX+sX%32-i*16) + (ao.y-sY+sY%32-j*16)*(ao.y-sY+sY%32-j*16)));
				}
				g.setColor(new Color(0, 0, 0, (int) Math.min(255, 0.01*d)));
				g.fillRect(i*16-sX%32, j*16-sY%32, 16, 16);
			}
		}
		g.setColor(new Color(255, 255, 255, 255));
	}
	
	public void scroll(){
		if(player.getX()+16-sX >= Main.w/2){
			sX = player.getX()+16-Main.w/2;
		}else if(player.getX()+16-sX <= Main.w/2){
			sX = player.getX()+16-Main.w/2;
		}
		if(player.getY()+16-sY >= Main.h/2){
			sY = player.getY()+16-Main.h/2;
		}else if(player.getY()+16-sY <= Main.h/2){
			sY = player.getY()+16-Main.h/2;
		}
	}
	
}
