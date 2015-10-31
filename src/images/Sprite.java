package images;

import org.newdawn.slick.Image;

public class Sprite {

	public static Image[] getSprites(String ref, int w, int h, int numSprites, int perRow, int nw, int nh) {
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
