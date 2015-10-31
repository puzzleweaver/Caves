package menus;

import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import images.Sprite;
import main.Main;
import objects.Inventory;
import objects.Item;
import objects.Simple;

public class GameMenu implements Menu {

	public static Image heart[];
	
	public GameMenu(){
		heart = Sprite.getSprites("images/heart.png", 8, 8, 2, 2, 32, 32);
	}

	public boolean wasButtonDown;
	
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
		if(wasButtonDown && !Mouse.isButtonDown(0)) wasButtonDown = false;
		if(!wasButtonDown && Mouse.isButtonDown(0)){
			wasButtonDown = true;
			int x = (Mouse.getX()+Main.sX)/32, y = (Mouse.getY()+Main.sY)/32;
			if(Simple.pointRect(Mouse.getX(), Mouse.getY(), 100, 100, 100, 100)){
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
		for(int i = 0; i < Main.w*Main.h/32/16; i++){
			g.drawImage(Main.textures[Main.getStateAt(Main.player.getX()/32, Main.player.getY()/32, Main.w/32, i)],
					Main.getStateX(Main.player.getX()/32, Main.player.getY()/32, Main.w/32, i)*32-Main.sX,
					Main.getStateY(Main.player.getX()/32, Main.player.getY()/32, Main.w/32, i)*32-Main.sY, null);
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
		Main.player.draw(g);
		for(int i = 0; i < Main.enemies.size(); i++){
			Main.enemies.get(i).render(g);
			if(Main.enemies.get(i).isDead()){
				Main.enemies.remove(i);
				break;
			}
		}
		drawLighting(g);
		g.drawImage(Main.boxImage[0], 354, 10);
		int x = 10;
		for(int i = 0; i < Main.player.getFull(); i++){
			g.drawImage(heart[0], x, 10, null);
			x += 32;
		}
		if(Main.player.hasHalf()){
			g.drawImage(heart[1], x, 10, null);
		}
	}
	
	public void drawLighting(Graphics g){
		// this will all get reworked later under slick
//		int x, y, trans, pV1 = 100;
//		g.setColor(new Color(Main.player.getRed(), 0, 0));
//		for(int i = 0; i < 729; i++){
//			x = (i%27)*16-Main.sX%16; y = (i/27)*16-Main.sY%16-16;
//			trans = (int) Math.hypot(x+Main.sX-Main.player.getX()-14, y+Main.sY-Main.player.getY()-16);
//			if(trans/1.5 <= 100){
//				Transparency.set(g, trans/1.5);
//				pV1 = (int) (trans/1.5);
//			}else{
//				Transparency.set(g, 100);
//				pV1 = 100;
//			}
//			for(int j = 0; j < Main.enemies.size(); j++){
//				if(Main.enemies.get(j).getLit() != 100){
//					int eX = Main.enemies.get(j).getX()+16, eY = Main.enemies.get(j).getY()+16;
//					trans = (int) Tools.getDistance(x+Main.sX, y+Main.sY, eX, eY);
//					if(pV1 > trans/Main.enemies.get(j).getLit() && trans/Main.enemies.get(j).getLit() < 100){
//						Transparency.set(g, trans/Main.enemies.get(j).getLit());
//						pV1 = (int) (trans/Main.enemies.get(j).getLit());
//					}
//				}
//			}
//			g.fillRect(x, y, 16, 16);
//		}
//		Transparency.reset(g);
	}
	
}
