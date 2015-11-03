package images;

import org.newdawn.slick.Image;

public class Sprite {

	public static Image[] heart, player, marq, pauseNum, boxImage, items, textures, particles, playButton,
			enemies;
	public static Image pauseMenu, title;
	
	public static void init() {
		heart = Sprite.getSprites("images/heart.png", 8, 8, 2, 2, 32, 32);
		player = Sprite.getSprites("images/person.png", 8, 8, 238, 6, 32, 32);
		pauseMenu = Sprite.getSprites("images/pauseMenu.png", 100, 242, 1, 1, 400, 968)[0];
		pauseNum = Sprite.getSprites("images/nums.png", 3, 5, 10, 10, 12, 20);
		marq = Sprite.getSprites("images/marquee.png", 14, 14, 4, 4, 56, 56);
		boxImage = Sprite.getSprites("images/box.png", 12, 12, 2, 2, 36, 36);
		items = Sprite.getSprites("images/items.png", 8, 8, 35, 6, 32, 32);
		textures = Sprite.getSprites("images/oWImages.png", 8, 8, 38, 8, 32, 32);
		particles = Sprite.getSprites("images/parts.png", 4, 4, 6, 4, 8, 8);
		enemies = Sprite.getSprites("images/enemies.png", 8, 8, 48, 8, 32, 32);
		title = Sprite.getSprites("images/title.png", 192, 64, 1, 1, 192, 64)[0];
		playButton = Sprite.getSprites("images/playButton.png", 192, 64, 2, 1, 192, 64);
	}
	
	private static Image[] getSprites(String ref, int w, int h, int numSprites, int perRow, int nw, int nh) {
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
			imgs[i] = img.getSubImage((i%perRow)*w, (i/perRow)*h, w, h).getScaledCopy(nw, nh);
		}
		return imgs;
	}

}
