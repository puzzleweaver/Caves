package menus;

import java.awt.Font;
import java.io.InputStream;

import main.Input;
import main.Main;
import main.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import res.Sprite;

public class MainMenu implements Menu {
	
	private int sX = 0, sY = 0;
	String name = "";
	
	// these should go in that variables class when we get a round tuit
	TrueTypeFont fippsTTF = null;
	TrueTypeFont fippsTTFBig = null;
	
	private int bgWorld[][], bgWorldW, bgWorldH;
	
	public MainMenu() {
		name = NameGenerator.newName();
		bgWorld = World.loadMainMenuWorld(bgWorldW = 80, bgWorldH = 80);
		InputStream inputStream = ResourceLoader.getResourceAsStream("res/Fipps-Regular.ttf");
		Font fipps;
		try {
			fipps = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			fippsTTF = new TrueTypeFont(fipps.deriveFont(18f), false);
			fippsTTFBig = new TrueTypeFont(fipps.deriveFont(24f), false);
		} catch (Exception e) {}
	}
	
	public void render(Graphics g) {
		//scrolling world background
		g.setColor(Color.white);
		int dsX = (int) (0.125*sX/Main.scale), dsY = (int) (0.125*sY/Main.scale);
		for(int i = -1; i <= Main.w*0.125/Main.scale+1; i++) {
			for(int j = -1; j <= Main.h*0.125/Main.scale+1; j++) {
				g.drawImage(Sprite.textures[bgWorld[(i%bgWorldW+dsX+bgWorldW)%bgWorldW][(j%bgWorldH+dsY+bgWorldH)%bgWorldH]], (int) (Main.scale*8*i-sX%(Main.scale*8)), (int) (Main.scale*8*j-sY%(Main.scale*8)));
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
		//scroll background
		sX++;
		sY++;
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
