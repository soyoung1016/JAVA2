package day0107;

/**
 * 사용자 정의 예외처리 클래스<br>
 * Exception(Runtime Exception)을 상속 받는다
 * @author owner
 */
@SuppressWarnings("serial")
public class LoginException extends Exception {
	
	public LoginException() {
		this("로그인 실패");
	}//LoginException
	
	public LoginException(String msg) {
		super(msg); //예외 처리 객체를 사용하여 예외 메세지를 출력할 수 있다.
	}//LoginException
	
}//class
