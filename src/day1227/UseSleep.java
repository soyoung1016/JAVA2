package day1227;

import java.util.Random;

/**
 * running 상태에서 block상태로 상태 이전(생명주기)
 * @author owner
 */
public class UseSleep implements Runnable{

	@Override
	public void run() {
		System.out.println("loading");
		Random r = new Random();
		for(int i=0; i<15; i++) {
			System.out.println(" . ");
			try {
				Thread.sleep(100*r.nextInt(10)+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end for
		System.out.println("finish");
//		for(int i =1; i<10; i++) {
//			System.out.println("2 * "+i+" = " + (2*i));
//			
//			try {
//				Thread.sleep(500); //block상태
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}//end catch
//		}//end for
	}//run
	
	public static void main(String[] args) {
		//클래스 객체화
		UseSleep us = new UseSleep();
		//Thread와 has a
		Thread t = new Thread(us);
		//start()
		t.start();
	}//main

}//class
