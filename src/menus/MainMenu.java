package menus;

import java.awt.Font;
import java.io.InputStream;

import main.Input;
import main.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import res.Sprite;

public class MainMenu implements Menu {
	
	int offsetX = 0, offsetY = 0;
	String name = "";
	
	TrueTypeFont fippsTTF = null;
	TrueTypeFont fippsTTFBig = null;
	
	public MainMenu() {
		name = NameGenerator.newName();
		InputStream inputStream = ResourceLoader.getResourceAsStream("res/Fipps-Regular.ttf");
		Font fipps;
		try {
			fipps = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			fippsTTF = new TrueTypeFont(fipps.deriveFont(18f), false);
			fippsTTFBig = new TrueTypeFont(fipps.deriveFont(24f), false);
		} catch (Exception e) {}
	}
	
	public void render(Graphics g) {
		//scrolling background of stone
		int imgWidth = Sprite.mainMenuBackground.getWidth();
		int imgHeight = Sprite.mainMenuBackground.getHeight();
		for(int i = 0; i <= Main.w/imgWidth+1; i++) {
			for(int j = 0; j <= Main.h/imgHeight+1; j++) {
				g.drawImage(Sprite.mainMenuBackground, i*imgWidth-offsetX, j*imgHeight-offsetY);
			}
		}
		//title
		//g.drawImage(Sprite.title, Main.w/2-Sprite.title.getWidth()/2, Main.h/4-Sprite.title.getHeight()/2);
		g.setFont(fippsTTFBig);
		g.setColor(Color.black);
		g.drawString("Caves", Main.w/2 - fippsTTFBig.getWidth("Caves")/2, Main.h/4);
		//play button
		Color buttonColor = new Color(0, 102, 153).darker(0.25f);
		g.setColor(isPlayButtonHovered() ? buttonColor : buttonColor.darker(0.5f));
		g.drawString("Play", Main.w/2 - fippsTTFBig.getWidth("Play")/2, Main.h/2);
		//name
		g.setFont(fippsTTF);
		g.setColor(Color.black);
		g.drawString("Name:", Main.w/2 - fippsTTF.getWidth("Name:")/2, 3*Main.h/4);
		g.setColor(Color.green.darker(0.75f));
		g.drawString(name, Main.w/2 - (fippsTTF.getWidth(name) / 2), 3*Main.h/4 + 30);
		//generate button
		g.setColor(isGenerateButtonHovered() ? buttonColor : buttonColor.darker(0.5f));
		g.drawString("Generate", Main.w/2 - fippsTTF.getWidth("Generate")/2, 3*Main.h/4 + 60);
	}
	
	public void update() {
		//scroll stone background
		offsetX++;
		offsetY++;
		int imgWidth = Sprite.mainMenuBackground.getWidth();
		int imgHeight = Sprite.mainMenuBackground.getHeight();
		offsetX %= imgWidth;
		offsetY %= imgHeight;
		//react to buttons being pressed
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
		int bx = Main.w/2 - fippsTTFBig.getWidth("Play")/2;
		int by = Main.h/2;
		return mx >= bx && mx <= bx+fippsTTFBig.getWidth("Play") && my >= by && my <= by+fippsTTFBig.getHeight("Play");
	}
	private boolean isGenerateButtonHovered() {
		int mx = Input.mouseX();
		int my = Input.mouseY();
		int bx = Main.w/2 - fippsTTF.getWidth("Generate")/2;
		int by = 3*Main.h/4 + 60;
		return mx >= bx && mx <= bx+fippsTTF.getWidth("Generate") && my >= by && my <= by+fippsTTF.getHeight("Generate");
	}
	
}
