package menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import objects.Item;
import main.Main;
import main.World;

import com.henagongames.game.Menu;
import com.henagongames.image.Sprite;
import com.henagongames.image.Transparency;
import com.henagongames.tools.Particle;
import com.henagongames.tools.Tools;

public class GameMenu implements Menu{

	public static Image image = World.makeWorldImage(), heart[];
	public static boolean on;
	
	public void reset(){
		
	}
	public GameMenu(){
		heart = Sprite.getSprites(getClass().getResource("/images/heart.png"), 0, 0, 8, 8, 2, 2);
		heart = Sprite.resize(heart, 32, 32, Sprite.EDIT_COARSE);
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < 250; i++){
			g.drawImage(Main.textures[Main.getStateAt(Main.player.getX()/32, Main.player.getY()/32, 15, i)],
					Main.getStateX(Main.player.getX()/32, Main.player.getY()/32, 15, i)*32-Main.sX,
					Main.getStateY(Main.player.getX()/32, Main.player.getY()/32, 15, i)*32-Main.sY, null);
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
			Main.enemies.get(i).draw(g);
			if(Main.enemies.get(i).isDead()){
				Main.enemies.remove(i);
				break;
			}
		}
		drawLighting(g);
		g.drawImage(Main.boxImage[0], 354, 10, null);
		int x = 10;
		for(int i = 0; i < Main.player.getFull(); i++){
			g.drawImage(heart[0], x, 10, null);
			x += 32;
		}
		if(Main.player.hasHalf()){
			g.drawImage(heart[1], x, 10, null);
		}
		if(on){
			g.drawImage(image, 0, 0, null);
			System.out.println(on);
		}
	}
	
	public void drawLighting(Graphics g){
		int x, y, trans, pV1 = 100;
		g.setColor(new Color(Main.player.getRed(), 0, 0));
		for(int i = 0; i < 729; i++){
			x = (i%27)*16-Main.sX%16; y = (i/27)*16-Main.sY%16-16;
			trans = (int) Tools.getDistance(x+Main.sX, y+Main.sY, Main.player.getX()+14, Main.player.getY()+16);
			if(trans/1.5 <= 100){
				Transparency.set(g, trans/1.5);
				pV1 = (int) (trans/1.5);
			}else{
				Transparency.set(g, 100);
				pV1 = 100;
			}
			for(int j = 0; j < Main.enemies.size(); j++){
				if(Main.enemies.get(j).getLit() != 100){
					int eX = Main.enemies.get(j).getX()+16, eY = Main.enemies.get(j).getY()+16;
					trans = (int) Tools.getDistance(x+Main.sX, y+Main.sY, eX, eY);
					if(pV1 > trans/Main.enemies.get(j).getLit() && trans/Main.enemies.get(j).getLit() < 100){
						Transparency.set(g, trans/Main.enemies.get(j).getLit());
						pV1 = (int) (trans/Main.enemies.get(j).getLit());
					}
				}
			}
			g.fillRect(x, y, 16, 16);
		}
		Transparency.reset(g);
	}
	
}
