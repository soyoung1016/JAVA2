package day1221;

import java.io.Serializable;

/**
 * 데이터를 가지고 있는 클래스로 직렬화대상 클래스
 * @author owner
 */
public class UserData implements Serializable{
	
	private static final long serialVersionUID = -3782985689772130133L;//공인인증서의 원리.. 시리얼넘버로 내가 보낸것인지 확인하는것
	
	private int age;
	private /*transient*/ double weigth;
	private /*transient*/ String name;
	
	//transient : 직렬화 방지 키워드(값이 JVM 외부로 전달되지 않는다.)
	public UserData() {

	}

	public UserData(int age, double weigth, String name) {
		this.age = age;
		this.weigth = weigth;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeigth() {
		return weigth;
	}

	public void setWeigth(double weigth) {
		this.weigth = weigth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserData [age=" + age + ", weigth=" + weigth + ", name=" + name + "]";
	}
	
	
	
}
