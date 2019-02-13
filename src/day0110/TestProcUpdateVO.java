package day0110;

public class TestProcUpdateVO {
	private int empno, sal;
	private String job;
	public TestProcUpdateVO() {
		
	}//TestProcUpdateVO
	public TestProcUpdateVO(int empno, int sal, String job) {
		this.empno = empno;
		this.sal = sal;
		this.job = job;
	}
	
	public int getEmpno() {
		return empno;
	}
	
	public int getSal() {
		return sal;
	}
	
	public String getJob() {
		return job;
	}
	
}//TestProcUpdateVO
