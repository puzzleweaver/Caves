package objects;


import org.newdawn.slick.Graphics;

import res.Sprite;
import main.Main;

public class Item extends Element {
	
	int timer = 0, ID = 0;
	private boolean done, caught;
	
	public Item(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		vs = -5; hs = Main.r.nextInt(9)-4;
	}
	
	public void update(){
		timer++;
		if(!Main.gameMenu.inventory.full()){
			int dist = (int) Math.hypot(y-Main.gameMenu.player.y, x-Main.gameMenu.player.x);
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
		if(Main.gameMenu.inventory.full()){
			caught = false;
		}
		if(!caught){
			for(int i = 0; i < 250; i++){
				if(Main.getStateAt((int)x/32, (int)y/32, 15, i) <= 15){
					int nX = Main.getStateX((int)x/32, (int)y/32, 15, i)*32,
							nY = Main.getStateY((int)x/32, (int)y/32, 15, i)*32;
					if(Simple.rectRect(x, y, 32, 32, nX, nY, 32, 32)
							) {
						y = nY-32;
						vs = 0;
					}
				}
			}
		}
		if(timer == 10000 || caught && Math.hypot(y-Main.gameMenu.player.y, x-Main.gameMenu.player.x) <= 10){
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
	
	public void render(Graphics g){
		g.drawImage(Sprite.items[ID], (int)x - Main.gameMenu.sX, (int)y - Main.gameMenu.sY);
	}
	
}
