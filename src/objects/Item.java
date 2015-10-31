package objects;


import main.Main;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Item extends Element {
	
	int timer = 0, ID = 0;
	private Image image;
	private boolean done, caught;
	
	public Item(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		image = Main.items[ID];
		vs = -5; hs = Main.r.nextInt(9)-4;
	}
	
	public void move(){
		timer++;
		if(!Main.inventory.full()){
			int dist = (int) Math.hypot(y-Main.player.getY(), x-Main.player.getX());
			if(dist <= 70){
				vs = 0;
				hs = 0;
				caught = true;
			}
		}
		if(hs > 0){
			hs -= .5;
		}else if(hs < 0){
			hs += .5;
		}
		if(vs < 11){
			vs+=.5;
		}
		x += hs;
		y += vs;
		checkCollisions();
	}
	
	public void checkCollisions(){
		if(Main.inventory.full()){
			caught = false;
		}
		if(!caught){
			for(int i = 0; i < 250; i++){
				if(Main.getStateAt(getX()/32, getY()/32, 15, i) <= 15){
					int nX = Main.getStateX(getX()/32, getY()/32, 15, i)*32,
							nY = Main.getStateY(getX()/32, getY()/32, 15, i)*32;
					if(Simple.rectRect(x, y, 32, 32, nX, nY, 32, 32)
							) {
						y = nY-32;
						vs = 0;
					}
				}
			}
		}
		if(timer == 10000 || caught && Math.hypot(y-Main.player.getY(), x-Main.player.getX()) <= 10){
			done = true;
		}
	}
	public int done(){
		if(done){
			if(caught){
				return 2;
			}
			return 1;
		}
		return 0;
	}
	public int getID(){
		return ID;
	}
	
	public void draw(Graphics g){
		g.drawImage(image, getX() - Main.sX, getY() - Main.sY);
	}
	
}
