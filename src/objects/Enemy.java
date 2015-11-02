package objects;

import org.newdawn.slick.Graphics;

public abstract class Enemy extends Element {
	
	public boolean dead;
	public static final int ENEMY_SKULL = 0, ENEMY_GOLEM = 1, ENEMY_FIGHTER = 2;
	protected int timer;
	
	public Enemy(int x, int y) {
		super(x, y);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	public abstract void hurt();
	
	public abstract double getLit();
	
}
