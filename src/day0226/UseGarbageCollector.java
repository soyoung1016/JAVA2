package day0226;

/**
 * Garbage Collector를 호출하여 객체를 소멸시키기(메모리의 여유공간 확보)
 * @author owner
 */
public class UseGarbageCollector {

	public static void main(String[] args) {
		Person p = new Person("이재찬 ");
		p = new Person("김정윤");
		p = new Person("정택성");
		p = new Person("공선의");
		p = new Person("이재현");
		
		System.gc();
		System.out.println("남은 사원 : "+p.getName());
	}//main

}//class
