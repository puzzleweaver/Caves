package objects;

import images.Sprite;
import main.Main;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends Element {
	
	private int timer, frame;
	private boolean moveRight, moveLeft, faceRight, inAir;
	public static double health = 6;
	public static int c, wall;
	
	public Player(int x, int y) {
		super(x, y);
	}
	
	public void reset(){
		moveRight = false;
		moveLeft = false;
		inAir = true;
		wall = 0;
		if(c > 0){
			c-=5;
		}
	}
	public void move(){
		if(vs < 10){
			vs+=.5;
		}
		y += vs;
		checkCollisions();
		if(moveRight && hs < 4){
			hs++;
		}else if(moveLeft && hs > -4){
			hs--;
		}
		if(hs > 0){
			hs -= .5;
			if(hs < .5){
				hs = 0;
			}
		}else if(hs < 0){
			if(hs > -.5){
				hs = 0;
			}
			hs += .5;
		}
		x += hs;
		changeFrame();
	}
	
	public void checkCollisions(){ 
		for(int i = 0; c == 0 && i < Main.enemies.size(); i++){
			Enemy d = Main.enemies.get(i);
			if(Simple.rectRect(d.getX(), d.getY(), 32, 32, x, y, 32, 32)){
				c = 255;
				health -= .5;
				d.hurt();
			}
		}
		for(int i = 0; i < 225; i++){
			if(Main.getStateAt(getX()/32, getY()/32, 15, i) <= 15){
				int nX = Main.getStateX(getX()/32, getY()/32, 15, i)*32,
						nY = Main.getStateY(getX()/32, getY()/32, 15, i)*32;
				if(Simple.rectRect(x, y+vs, 28, 32, nX+1, nY, 30, 32)){
					vs = 0;
					if(y >= nY){
						y = nY+32;
					}else {
						inAir = false;
						y = nY-32;
					}
				}if(Simple.rectRect(x+hs, y, 28, 32, nX, nY+1, 32, 30)){
					if(vs > 0) {
						vs = 0;
						inAir = false;
					}
					if(hs < 0){
						x = nX+32;
						wall = 1;
					}else if(hs > 0){
						x = nX-28;
						wall = -1;
					}
					hs = 0;
				}
			}
		}
	}
	
	public void changeFrame(){
		timer++;
		if(timer == 27){
			timer = 0;
		}
		if(!inAir){
			if(moveRight){
				frame = 6;
			}else if(moveLeft){
				frame = 9;
			}else if(faceRight){
				frame = 0;
			}else{
				frame = 3;
			}
		}else{
			timer = 0;
			if(faceRight){
				frame = 12;
			}else{
				frame = 13;
			}
		}
		frame += timer/9;
	}
	
	public void jump(){
		if(!inAir){
			vs = -9;
			y--;
			inAir = true;
			hs = wall*5;
			wall = 0;
		}
	}
	public void moveLeft(){
		moveLeft = true;
		moveRight = false;
		faceRight = false;
	}
	public void moveRight(){
		moveRight = true;
		moveLeft = false;
		faceRight = true;
	}
	
	public void render(Graphics g){
		g.drawImage(Sprite.player[frame], getX()-Main.sX, getY()-Main.sY, null);
		if(Main.inventory.gear[0] != -1){
			g.drawImage(Sprite.player[Main.inventory.getGarment(Inventory.EQUIP_HEAD)*14 + frame], getX()-Main.sX, getY()-Main.sY, null);
		}if(Main.inventory.gear[2] != -1){
			g.drawImage(Sprite.player[Main.inventory.getGarment(Inventory.EQUIP_TORSO)*14 + frame], getX()-Main.sX, getY()-Main.sY, null);
		}if(Main.inventory.gear[3] != -1){
			g.drawImage(Sprite.player[Main.inventory.getGarment(Inventory.EQUIP_FEET)*14 + frame], getX()-Main.sX, getY()-Main.sY, null);
		}if(Main.inventory.gear[1] != -1){
			g.drawImage(Sprite.player[Main.inventory.getGarment(Inventory.EQUIP_SHIELD)*14 + frame], getX()-Main.sX, getY()-Main.sY, null);
		}
	}
	
	public int getFull(){
		return (int) Math.floor(health);
	}
	public boolean hasHalf(){
		if(health - getFull() == .5){
			return true;
		}
		return false;
	}
	public int getKind(int index){
		if(health <= index){
			return 2;
		}else if(health%5 > 3){
			return 0;
		}
		return 1;
	}
	public int getRed(){
		return c;
	}
	
}
