package day0226;

public class Person {
	private String name;
	public Person(String name) {
		this.name=name;
		System.out.println("신입사원 "+name+"님 입사하셨습니다.");
	}//Person
	
	@Override
	public void finalize() { //객체가 Garbage Collector에 의해 소멸 될 때 호출 된다.
		System.out.println(name+"님이 퇴사하였습니다.");
	}//finalize

	public String getName() {
		return name;
	}
	
}//class
