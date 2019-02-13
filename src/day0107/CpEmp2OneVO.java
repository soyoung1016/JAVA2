package day0107;

import java.sql.Date;

public class CpEmp2OneVO {
	private int sal;
	private String ename;
	private Date hiredate; //sql.Date�� �޾ƾ��Ѵ�. util �ƴ�
	public CpEmp2OneVO() {
		
	}//CpEmp2OneVO
	
	public CpEmp2OneVO(int sal, String ename, Date hiredate) {
		this.sal = sal;
		this.ename = ename;
		this.hiredate = hiredate;
	}//CpEmp2OneVO

	public int getSal() {
		return sal;
	}

	public String getEname() {
		return ename;
	}

	public Date getHiredate() {
		return hiredate;
	}

	@Override
	public String toString() {
		return "CpEmp2OneVO [sal=" + sal + ", ename=" + ename + ", hiredate=" + hiredate + "]";
	}
	
}//class
