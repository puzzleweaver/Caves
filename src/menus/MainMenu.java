package menus;

import images.Sprite;
import main.Input;
import main.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class MainMenu implements Menu {
	
	int offset = 0;
	String name = "";
	
	public MainMenu() {
		name = NameGenerator.newName();
	}
	
	public void render(Graphics g) {
		//scrolling background of stone
		for(int i = 0; i <= Main.w/32+1; i++) {
			for(int j = 0; j <= Main.h/32+1; j++) {
				Image stoneTex = Sprite.textures[0]; //change this however necessary to make it look smoother
				g.drawImage(stoneTex, i*32-offset, j*32-offset);
			}
		}
		//title image
		g.drawImage(Sprite.title, Main.w/2-Sprite.title.getWidth()/2, Main.h/4-Sprite.title.getHeight()/2);
		//play button
		g.drawImage(Sprite.playButton[isPlayButtonHovered() ? 1 : 0], Main.w/2-Sprite.title.getWidth()/2, Main.h/2-Sprite.title.getHeight()/2);
		//name
		g.setColor(Color.black);
		Font font = g.getFont();
		g.drawString("Name:", Main.w/2 - (font.getWidth("Name:") / 2), 400);
		g.setColor(Color.white);
		g.fillRect(200, 430, Main.w-400, 20);
		g.setColor(Color.black);
		g.setLineWidth(4);
		g.drawRect(200, 430, Main.w-400, 20);
		g.drawString(name, Main.w/2 - (font.getWidth(name) / 2), 430);
		//generate button
		g.setColor(isGenerateButtonHovered() ? new Color(0, 102, 153) : new Color(0, 75, 112));
		g.fillRect(Main.w/2-50, 460, 100, 20);
		g.setColor(Color.black);
		g.drawRect(Main.w/2-50, 460, 100, 20);
		g.drawString("Generate", Main.w/2 - (font.getWidth("Generate") / 2), 460);
	}
	
	public void update() {
		offset++;
		offset %= 32;
		if(Input.mouseButtonPressed(0)) {
			if(isPlayButtonHovered())
				Main.menu = Main.gameMenu;
			else if(isGenerateButtonHovered())
				name = NameGenerator.newName();
		}
	}
	
	private boolean isPlayButtonHovered() {
		int mx = Input.mouseX();
		int my = Input.mouseY();
		int bx = Main.w/2 - Sprite.title.getWidth()/2;
		int by = Main.h/2 - Sprite.title.getHeight()/2;
		return mx >= bx && mx <= bx+192 && my >= by && my <= by+64;
	}
	private boolean isGenerateButtonHovered() {
		int mx = Input.mouseX();
		int my = Input.mouseY();
		int bx = Main.w/2 - 50;
		int by = 460;
		return mx >= bx && mx <= bx+100 && my >= by && my <= by+20;
	}
	
}
