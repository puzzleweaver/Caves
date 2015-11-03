package res;

import org.newdawn.slick.Image;

import main.Main;

public class Sprite {

	public static Image[] heart, player, marq, pauseNum, boxImage, items, textures, playButton,
			enemies;
	public static Image pauseMenu, title, mainMenuBackground;
	
	public static void init() {
		heart = Sprite.getSprites("res/heart.png", 8, 8, 2, 2);
		player = Sprite.getSprites("res/person.png", 8, 8, 238, 6);
		pauseMenu = Sprite.getSprites("res/pauseMenu.png", 100, 242, 1, 1)[0];
		pauseNum = Sprite.getSprites("res/nums.png", 3, 5, 10, 10);
		marq = Sprite.getSprites("res/marquee.png", 14, 14, 4, 4);
		boxImage = Sprite.getSprites("res/box.png", 12, 12, 2, 2);
		items = Sprite.getSprites("res/items.png", 8, 8, 35, 6);
		textures = Sprite.getSprites("res/oWImages.png", 8, 8, 38, 8);
		enemies = Sprite.getSprites("res/enemies.png", 8, 8, 48, 8);
		mainMenuBackground = Sprite.getSprites("res/oWImages.png", 16, 16, 1, 1)[0];
	}

	private static Image[] getSprites(String ref, int w, int h, int numSprites, int perRow) {
		Image img = null;
		try {
			img = new Image(ref);
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("CRITICAL IMAGE LOADING ERROR");
		}
		img.setFilter(Image.FILTER_NEAREST);
		Image[] imgs = new Image[numSprites];
		for(int i = 0; i < numSprites; i++) {
			imgs[i] = img.getSubImage((i%perRow)*w, (i/perRow)*h, w, h).getScaledCopy((int) (Main.scale*w), (int) (Main.scale*h));
		}
		return imgs;
	}
	
	private static Image[] getSpritesNoScale(String ref, int w, int h, int numSprites, int perRow) {
		Image img = null;
		try {
			img = new Image(ref);
		}catch(Exception e) {
			e.printStackTrace();
			System.err.println("CRITICAL IMAGE LOADING ERROR");
		}
		img.setFilter(Image.FILTER_NEAREST);
		Image[] imgs = new Image[numSprites];
		for(int i = 0; i < numSprites; i++) {
			imgs[i] = img.getSubImage((i%perRow)*w, (i/perRow)*h, w, h);
		}
		return imgs;
	}

}
