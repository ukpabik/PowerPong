package collision;

import shapes.BoundedShape;

public class ACollisionChecker implements Collision{
	public static boolean intersects(BoundedShape shape1, BoundedShape shape2) {
		int x1 = shape1.getX();
		int x2 = shape1.getX() + shape1.getWidth();
		int x3 = shape2.getX();
		int x4 = shape2.getX() + shape2.getWidth();
		
		int y1 = shape1.getY();
		int y2 = shape1.getY() + shape1.getHeight();
		int y3 = shape2.getY();
		int y4 = shape2.getY() + shape2.getHeight();
		
		//Find intersection
		int x5 = Math.max(x1, x3);
		int y5 = Math.max(y1, y3);
		int x6 = Math.min(x2, x4);
		int y6 = Math.min(y2, y4);
		
		
		//Check for overlap/intersection
		if (x5 >= x6 || y5 >= y6) {
			return false;
		}
		
		return true;
		
	}
}
