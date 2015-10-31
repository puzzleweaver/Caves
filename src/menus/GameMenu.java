package menus;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.svg.Gradient;
import org.newdawn.slick.svg.RadialGradientFill;

import images.Sprite;
import main.Input;
import main.Main;
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
//			}else if(state[x][y] == 33){
//				for(int i = 0; i < r.nextInt(5)+2; i++){
//					objects.add(new Item(x*32, y*32, Inventory.chestSpawn()));
//				}
//				state[x][y] = 32;
			}
		}
		Main.scroll();
	}
	
	public void render(Graphics g){
		for(int i = 0; i < Main.w*Main.h/33/16; i++){
			g.drawImage(Sprite.textures[Main.getStateAt(Main.player.getX()/32, Main.player.getY()/32, Main.w/31, i)],
					Main.getStateX(Main.player.getX()/32, Main.player.getY()/32, Main.w/31, i)*32-Main.sX,
					Main.getStateY(Main.player.getX()/32, Main.player.getY()/32, Main.w/31, i)*32-Main.sY, null);
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
			if(Main.enemies.get(i).isDead()){
				Main.enemies.remove(i);
				break;
			}
		}
		drawLighting(g);
		g.drawImage(Sprite.boxImage[0], 354, 10);
		int x = 10;
		for(int i = 0; i < Main.player.getFull(); i++){
			g.drawImage(Sprite.heart[0], x, 10, null);
			x += 32;
		}
		if(Main.player.hasHalf()){
			g.drawImage(Sprite.heart[1], x, 10, null);
		}
	}
	
	public void drawLighting(Graphics g){
		// do some-thang
	}
	
}
