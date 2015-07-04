
public class Test {
	public static void main(String[] args) {
		System.out.println("test Singleton: ");
		
		Singleton sgl1_1 = Singleton.create();
		Singleton sgl1_2 = Singleton.create();
		sgl1_2 = Singleton.get();
		
		System.out.println("test Singleton2: ");
		
        Singleton2 sgl2_1 = Singleton2.create();
		sgl2_1.showID();
		Singleton2 sgl2_2 = Singleton2.create();
		sgl2_2.showID();
		Singleton2 sgl2_3 = Singleton2.create();
		sgl2_3 = Singleton2.get(1);
		sgl2_3.showID();
		
		System.out.println("test Singleton3: ");
		
		Singleton3.setNum(2);
        Singleton3 sgl3_1 = Singleton3.create();
		sgl3_1.showID();
		Singleton3 sgl3_2 = Singleton3.create();
		sgl3_2.showID();
		Singleton3 sgl3_3 = Singleton3.create();
		sgl3_3 = Singleton3.get(1);
		sgl3_3.showID();

	}
}