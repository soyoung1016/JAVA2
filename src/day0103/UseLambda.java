package day0103;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UseLambda {

	public static void main(String[] args) {
//		TestLambda tl=()-> new Date().toString(); //return ���� ����
//		TestLambda tl=()-> { return new Date().toString(); }; //return ���. {}�ʿ�
		//abstract method ���� �ڵ尡 ����(������)�� ��쿡�� {}�� �ݵ�� ���
		TestLambda tl=()-> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(int i=0; i<10; i++) {
				System.out.println("i = "+i);
			}//end for
			
			return sdf.format(new Date());
		};
		System.out.println(tl.toDay());
		
		//�޼ҵ�, Ŭ����, ���� �� �ʿ����.
		//�߻�޼ҵ�� �������̵� �� �ʿ䵵 ����, ��~~
	}//main

}//class
