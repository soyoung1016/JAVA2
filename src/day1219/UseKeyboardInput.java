package day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ����ڰ� �Է��� Ű������ ���� ó���ϴ� System.in
 * @author owner
 */
public class UseKeyboardInput {

	public UseKeyboardInput() {
		
		System.out.println("�ƹ�Ű�� ������ ����");
		//CompileException�̶� ������ try~catch
		
		//8bit stream�� 16bit stream�� ����
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			//�ƽ�Ű�ڵ尡 �ƴ� keycord�� OS�� ������ OS�� ĳ���ͼ¿� �Էµ� keycord�� �����ϴ� ���ڸ� �츮���� ������ִ°�.
			
			/*�Է°� �� ���� �Է� ���� �ϳ� �ޱ�*/
//			int input = System.in.read(); //read()�� ���Ŀ� ������ �־�� �Ʒ��� �����ϴ� method�̴�.
//			System.out.println("�Է°� : " +input);
			
			/*�Էµ� ��� ���ڿ� �ޱ� : �ѱ��� ���� �� ����.*/
//			int input = 0;
//			while(true) {
//				input = System.in.read();
////				System.out.print(input+", "); //�̰� �������
//				System.out.print((char)input+", "); //�Է��� �״�� �������
//				//�ڿ� �ڵ����� \r�� \n�� �ٴ´�. why???
//				//�ѱ��� ��¾ȵȴ�.
//				if(input==13) {
//					break;
//				}
//			}
			
			//16bit stream�� ����Ͽ� �Էµ����͸� �ٴ����� �о���δ�.
			String str = br.readLine();
			
			System.out.println(str);
			
			//��Ʈ������� ��������  ��Ʈ���� ������ ����.
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UseKeyboardInput();
	}

}
