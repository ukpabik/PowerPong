package powerups;

public class PowerUp {
	
	//GUI SIZE
	public static int objectSize = 1;
	public static final int MAX_OBJECT_SIZE = 3;
	
	//STATIC METHOD FOR CHANGING GUI SIZE
	public static void changeGUISize(int size) {
		
		objectSize += size;
		if (objectSize < 1) {
			objectSize = 1;
		}
		else if (objectSize > MAX_OBJECT_SIZE) {
			objectSize = MAX_OBJECT_SIZE;
		}
	}
}
