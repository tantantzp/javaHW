
public class Singleton2 {
	
private static int numExist = 0;
private static Singleton2[] instance = new Singleton2[2];
private int id;

protected Singleton2(int _id){
    id = _id;
}

public void showID(){
	System.out.println("The id of this instance is " + id);
}


public static Singleton2 create() {
    if (numExist < 2) {
		instance[numExist] = new Singleton2(numExist ++);
		System.out.println("A new Singleton2 was created");
		return instance[numExist - 1];
	}
	else {
		System.out.println(" Cannot create another one!");
		return null;
	}
}

public static Singleton2 get(int _id) {
	if (_id >= 2 || instance[_id] == null) {
		System.out.println("You haven't create the instance of id " + _id);
		return null;
	}
	else {
		return instance[_id];
	}
}
	
}
