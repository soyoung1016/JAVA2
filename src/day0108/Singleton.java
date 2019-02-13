package day0108;

/**
 * �������� JVM���� �ϳ��� ��ü�� ��������� �ϳ��� ��ü�� ���Ǵ� Pattern.
 * @author owner
 */
public class Singleton {
	private static Singleton single;
	
	/**
	 * 1. class �ܺο��� ��üȭ�� ���� ���ϵ��� ���´�.
	 */
	private Singleton() {
		
	}//Singleton
	
	public static Singleton getInstance() {
		if(single == null) { //��ü�� �����Ǿ����� �ʴٸ� ��ü�� �����Ͽ� ��ȯ�ϰ�
			single = new Singleton(); //���� ���� ����, private static�� ���־�� �Ѵ�.
		}//end if
		//��ü�� �����Ǿ��ִٸ� ������ ��ü�� ��ȯ �ȴ�.
		return single;
	}//getInstance
	
}//class
