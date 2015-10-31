package objects;

public class Simple {
	
	public static boolean rectRect(double x, double y, double w, double h, double x2, double y2, double w2, double h2) {
		return x < x2 + w2 && x + w > x2 && y < y2 + h2 && y + h > y2;
	}
	
}
