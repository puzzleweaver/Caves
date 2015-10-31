package objects;

import java.awt.Graphics;
import java.awt.Image;

import main.Main;

import com.henagongames.game.Element;
import com.henagongames.image.Sprite;
import com.henagongames.tools.Tools;

public abstract class Enemy extends Element{

	public static boolean dead;
	public static final int ENEMY_SKULL = 0, ENEMY_GOLEM = 1, ENEMY_FIGHTER = 2;
	protected int timer;
	
	public Enemy(int x, int y) {
		super(x, y);
		loadImage();
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public abstract void hurt();
	
	public abstract void loadImage();
	public abstract double getLit();
	
	public boolean isDead(){
		return dead;
	}
	
}
