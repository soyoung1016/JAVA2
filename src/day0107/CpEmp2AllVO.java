package day0107;

public class CpEmp2AllVO {
	private int empno, sal;
	private String name, hiredate;
	
	public CpEmp2AllVO() {
		
	}//CpEmp2AllVO

	public CpEmp2AllVO(int empno, int sal, String name, String hiredate) {
		this.empno = empno;
		this.sal = sal;
		this.name = name;
		this.hiredate = hiredate;
	}//CpEmp2AllVO

	public int getEmpno() {
		return empno;
	}

	public int getSal() {
		return sal;
	}

	public String getName() {
		return name;
	}

	public String getHiredate() {
		return hiredate;
	}

	@Override
	public String toString() {
		return "CpEmp2AllVO [empno=" + empno + ", sal=" + sal + ", name=" + name + ", hiredate=" + hiredate + "]";
	}
	
}//class
