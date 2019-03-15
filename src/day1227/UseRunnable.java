package day1227;

/**
 * Runnable interface를 구현하여 Thread를 사용
 * @author owner
 */
//1. Runnable 구현
public class UseRunnable implements Runnable {
	
	//2. run()의 Override
	@Override
	public void run() {
		//3. Thread로 동작해야하는 코드
		for(int i=0; i<1000; i++) {
			System.out.println("run i========"+i);
		}//end for
	}//run
	
	public void test() {
		for(int i=0; i<1000; i++) {
			System.out.println("********test의 i===="+i);
		}//end for
	}//test
	
	public static void main(String[] args) {
		long st = System.currentTimeMillis();
		//4. Runnable을 구현한 클래스를 객체화
		UseRunnable ur = new UseRunnable();
		//5. Thread 객체와 Runnable을 구현한 객체를 has a 관계로 설정.
		Thread t = new Thread(ur); 
		//6. Thread에 있는 start()를 호출
		t.start(); //has a 관계가 설정되지 않으면 Thread의 run() 호출된다.
//		ur.run();
		ur.test();
		
		long et = System.currentTimeMillis();
		System.out.println((et-st) +" ms");
		
		
	}//main
}//class
