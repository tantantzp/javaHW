
public class Singleton {
	
private static int numExist = 0;
private static Singleton instance;


protected Singleton(){
  
}

public static Singleton create() {
    if (numExist < 1) {
		instance = new Singleton();
		numExist ++;
		System.out.println("A new Singleton was created");
		return instance;
	}
	else {
		System.out.println(" Cannot create another one!");
		return null;
	}
}

public static Singleton get() {
	if (numExist == 0) {
		System.out.println("You haven't create a instance" );
		return null;
	}
	else {
		return instance;
	}
}
	
}