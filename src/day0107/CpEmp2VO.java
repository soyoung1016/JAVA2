package day0107;

public class CpEmp2VO {
	private int empno, sal;
	private String ename;
	
	public CpEmp2VO() {
		
	}//CpEmp2VO

	public CpEmp2VO(int empno, int sal, String ename) {
		this.empno = empno;
		this.sal = sal;
		this.ename = ename;
	}//CpEmp2VO

	public int getEmpno() {
		return empno;
	}

	public int getSal() {
		return sal;
	}

	public String getEname() {
		return ename;
	}
	
}//class
