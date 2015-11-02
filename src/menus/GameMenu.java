package menus;

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
import objects.Simple;

public class GameMenu implements Menu {

	public void update() {
		Main.player.reset();
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			Main.player.moveRight();
		}if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			Main.player.moveLeft();
		}
		Main.player.move();
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			Main.player.jump();
		}
		if(Input.mouseButtonPressed(0)){
			int x = (Input.mouseX()+Main.sX)/32, y = (Input.mouseY()+Main.sY)/32;
			if(Simple.pointRect(Input.mouseX(), Input.mouseY(), 354, 10, 36, 36)){
				Main.menu = Main.pauseMenu;
			}else if(World.state[x][y] == 33){
				for(int i = 0; i < Main.r.nextInt(5)+2; i++){
					Main.objects.add(new Item(x*32, y*32, Inventory.chestSpawn()));
				}
				World.state[x][y] = 32;
			}
		}
		Main.scroll();
	}
	
	public void render(Graphics g){
		int x, y;
		for(int i = -Main.w/64-2; i < Main.w/64+2; i++){
			for(int j = -Main.h/64-2; j < Main.h/64+2; j++) {
				x = ((Main.player.getX())/32+i)*32;
				y = ((Main.player.getY())/32+j)*32;
				g.drawImage(Sprite.textures[World.state[Main.player.getX()/32+i][Main.player.getY()/32+j]], x-Main.sX, y-Main.sY);
			}
		}
		for(int i = 0; i < Main.objects.size() && i >= 0; i++){
			Item d = Main.objects.get(i);
			d.draw(g);
			d.move();
			if(d.done() > 0){
				if(d.done() == 2){
					Main.inventory.add(d);
				}
				Main.objects.remove(i);
				i--;
			}
		}
		Main.player.render(g);
		for(int i = 0; i < Main.enemies.size(); i++){
			Main.enemies.get(i).render(g);
			if(Main.enemies.get(i).dead){
				Main.enemies.remove(i);
				break;
			}
		}
		drawLighting(g);
		g.drawImage(Sprite.boxImage[0], 354, 10);
		x = 10;
		for(int i = 0; i < Main.player.getFull(); i++){
			g.drawImage(Sprite.heart[0], x, 10, null);
			x += 32;
		}
		if(Main.player.hasHalf()){
			g.drawImage(Sprite.heart[1], x, 10, null);
		}
	}
	
	public void drawLighting(Graphics g){
		double d;
		Enemy ao;
		for(int i = -1; i < Main.w/16+2; i++) {
			for(int j = -1; j < Main.h/16+3; j++) {
				d = (i*16-Main.sX%32-Main.w/2)*(i*16-Main.sX%32-Main.w/2) + (j*16-Main.sY%32-Main.h/2)*(j*16-Main.sY%32-Main.h/2);
				for(int a = 0; a < Main.enemies.size(); a++) {
					ao = Main.enemies.get(a);
					d = Math.min(d, 2.0*((ao.x-Main.sX+Main.sX%32-i*16)*(ao.x-Main.sX+Main.sX%32-i*16) + (ao.y-Main.sY+Main.sY%32-j*16)*(ao.y-Main.sY+Main.sY%32-j*16)));
				}
				g.setColor(new Color(0, 0, 0, (int) Math.min(255, 0.01*d)));
				g.fillRect(i*16-Main.sX%32, j*16-Main.sY%32, 16, 16);
			}
		}
		g.setColor(new Color(255, 255, 255, 255));
	}
	
}
