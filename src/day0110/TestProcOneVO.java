package day0110;

public class TestProcOneVO {
	private int sal;
	private String ename, job, hiredate;
	
	public TestProcOneVO() {
		
	}

	public TestProcOneVO(int sal, String ename, String job, String hiredate) {
		this.sal = sal;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public String getEname() {
		return ename;
	}

	public String getJob() {
		return job;
	}

	public String getHiredate() {
		return hiredate;
	}

	@Override
	public String toString() {
		return "TestProcOneVO [sal=" + sal + ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + "]";
	}
	
}//class
