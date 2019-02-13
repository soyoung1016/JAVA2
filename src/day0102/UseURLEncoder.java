package day0102;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UseURLEncoder {

	public static void main(String[] args) {
		String msg ="abc������def123"; //MS949��� �ϴ� charset
		try {
			//�Էµ� ���ڿ��� ������ ����ϴ� ������ �ڵ尪���� ����
			String encode = URLEncoder.encode(msg, "UTF-8");//MS949�� �ѱ��� ������ ����ϴ� UTF-8 ������ �ڵ尪���� ����
			System.out.println("���ڵ� : "+encode);
			
			String decode = URLDecoder.decode(encode, "UTF-8");
			System.out.println("���ڵ� : "+decode);
			
			
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}//end catch
	}//main

}//class
