package day1221;

public class StringPlus {

	public static void main(String[] args) {
		String s = "c:/dev/temp/java.read.txt";
		System.out.println(s);
		
		int index = s.lastIndexOf(".");
		//lastindexOf�� ����ؾ��Ѵ�..!
		
		String sub = s.substring(0, index);
		String sub2 = s.substring(index);
		
		System.out.println(sub+"_bak"+sub2);
		
		///////////////////////////////////
		
		StringBuilder sh = new StringBuilder(s);
		System.out.println(sh.insert(index, "_bak"));
		
	}

}
