package day0108;

public class UseSingleton {

	public static void main(String[] args) {
		//Singleton Pattern���� ������� ��ü ���
//		Singleton s = new Singleton(); //�����ڰ� private �̹Ƿ� Ŭ���� �ܺο��� ��üȭ�� ���� �ʴ´�.
		
		Singleton s = Singleton.getInstance();
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		Singleton s3 = Singleton.getInstance();
		
		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		//ó���� �� �ٸ� �ּҿ��µ� singleton? ����ϴϱ� �� ���� �ּ�.. Singleton.java���� if�� �߰��ϴϱ�..
		
	}//main
	
}//class
