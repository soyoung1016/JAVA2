package day1221;

import java.io.Serializable;

/**
 * �����͸� ������ �ִ� Ŭ������ ����ȭ��� Ŭ����
 * @author owner
 */
public class UserData implements Serializable{
	
	private static final long serialVersionUID = -3782985689772130133L;//������������ ����.. �ø���ѹ��� ���� ���������� Ȯ���ϴ°�
	
	private int age;
	private /*transient*/ double weigth;
	private /*transient*/ String name;
	
	//transient : ����ȭ ���� Ű����(���� JVM �ܺη� ���޵��� �ʴ´�.)
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
