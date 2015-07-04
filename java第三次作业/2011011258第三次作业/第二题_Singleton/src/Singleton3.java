
public class Singleton3 {

private static int numMost;
private static boolean isSet = false;
private static int numExist = 0;
private static Singleton3[] instance;

private int id;

protected Singleton3(int _id){
    id = _id;
}

public void showID(){
	System.out.println("The id of this instance is " + id);
}
public static void setNum(int n){
	if (n == 0) {
		System.out.println("A positive integer is needed.");
		return;
	}
	if (!isSet) {
		numMost = n;
		instance = new Singleton3[n];
		isSet = true;
		System.out.println("You can create at most " + n + " instances of Singleton3.");
	}
	else {
		System.out.println("You have set the number once!");
	}	
}
public static Singleton3 create() {
	if (!isSet) {
		System.out.println("You must first set the numMost!");
		return null;
	}
	else if (numExist < numMost) {
		instance[numExist] = new Singleton3(numExist ++);
		System.out.println("A new Singleton3 was created");
		return instance[numExist - 1];
	}
	else {
		System.out.println(" Cannot create another one!");
		return null;
	}
}

public static Singleton3 get(int _id) {
	if (!isSet) {
		System.out.println("You must first set the numMost!");
		return null;
	}
	else {
		if (_id >= numMost || instance[_id] == null) {
			System.out.println("You haven't create the instance of id " + _id);
			return null;
		}
		else {
			return instance[_id];
		}
	}
}
	
}

