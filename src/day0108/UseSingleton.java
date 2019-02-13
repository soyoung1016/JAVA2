package day0108;

public class UseSingleton {

	public static void main(String[] args) {
		//Singleton Pattern으로 만들어진 객체 사용
//		Singleton s = new Singleton(); //생성자가 private 이므로 클래스 외부에서 객체화가 되지 않는다.
		
		Singleton s = Singleton.getInstance();
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		Singleton s3 = Singleton.getInstance();
		
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		//처음엔 다 다른 주소였는데 singleton? 사용하니까 다 같은 주소.. Singleton.java에서 if문 추가하니까..
		
	}//main
	
}//class
