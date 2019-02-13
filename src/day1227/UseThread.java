package day1227;

/**
 * Thread class를 상속받아서 Thread 사용
 * @author owner
 */
//1. Thread 상속
public class UseThread extends Thread{
	//2. run method Override
	@Override
	public void run() {
		for(int i=0; i<1000; i++) {
			System.out.println("run i---------->"+i);
		}//end for
	}//run

	public void test() {
		for(int i=0; i<1000; i++) {
			System.out.println("test----------->"+i);
		}	
	}
	
	public static void main(String[] args) {
		//4. 객체생성
		UseThread ut = new UseThread();
		//5. 부모클래스인 Thread가 가지고 있는 start() 호출
		ut.start(); //start() 호출하면 start()안에서 -> run() 호출
		ut.test();
	}//main

}//class
