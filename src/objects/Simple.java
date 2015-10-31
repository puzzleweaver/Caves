package objects;

public class Simple {
	
	public static boolean rectRect(double x, double y, double w, double h, double x2, double y2, double w2, double h2) {
		return x < x2 + w2 && x + w > x2 && y < y2 + h2 && y + h > y2;
	}
	
	public static boolean pointRect(double x, double y, double x1, double y1, double w1, double h1){
		if(x > x1 && x < x1+w1 && y > y1 && y < y1+h1){
			return true;
		}
		return false;
	}
	
}
