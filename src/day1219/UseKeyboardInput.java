package day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 사용자가 입력한 키보드의 값을 처리하는 System.in
 * @author owner
 */
public class UseKeyboardInput {

	public UseKeyboardInput() {
		
		System.out.println("아무키나 누르고 엔터");
		//CompileException이라서 무조건 try~catch
		
		//8bit stream과 16bit stream을 연결
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			//아스키코드가 아닌 keycord를 OS에 보내고 OS의 캐릭터셋에 입력된 keycord와 대응하는 문자를 우리에게 출력해주는것.
			
			/*입력값 중 최초 입력 문자 하나 받기*/
//			int input = System.in.read(); //read()는 차후에 진행이 있어야 아래로 진행하는 method이다.
//			System.out.println("입력값 : " +input);
			
			/*입력된 모든 문자열 받기 : 한글은 받을 수 없다.*/
//			int input = 0;
//			while(true) {
//				input = System.in.read();
////				System.out.print(input+", "); //이건 숫자출력
//				System.out.print((char)input+", "); //입력한 그대로 문자출력
//				//뒤에 자동으로 \r과 \n이 붙는다. why???
//				//한글은 출력안된다.
//				if(input==13) {
//					break;
//				}
//			}
			
			//16bit stream을 사용하여 입력데이터를 줄단위로 읽어들인다.
			String str = br.readLine();
			
			System.out.println(str);
			
			//스트림사용이 끝났으면  스트림의 연결을 끊다.
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UseKeyboardInput();
	}

}
