package day0104;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Statement 객체를 사용하여 CRUD를 구현하는 클래스
 * CRUD(Create Read Update Delete)
 * @author owner
 */
public class UseStatementCRUD {
	
	/**
	 * VO를 입력받아 VO의 값을 CP_DEPT테이블에 추가
	 * @param cdvo 부서번호, 부서명, 위치를 가진 VO
	 * @throws SQLException
	 */
	public void insertCpDept(CpDeptVO cdvo) throws SQLException{
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		
		try {
		//2. Connection 얻기
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id = "scott";
			String pwd = "tiger";
			
			con = DriverManager.getConnection(url, id, pwd);
			
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
			
		//4. 쿼리수행 후 결과 얻기
//			String insertCpdept = "insert into cp_dept(deptno, dname, loc) values("+
//					cdvo.getDeptno()+",'"+cdvo.getDname()+"','"+cdvo.getLoc()+"')";
			//오라클의 문자열은 홀따옴표로 묶어야한다. 홀따옴표 주의!!!!
			
			//긴 문자열은 String보다는 StringBuilder가 더 낫다
			StringBuilder insertCpDept = new StringBuilder();
			insertCpDept.append("insert into cp_dept(deptno, dname, loc) values(")
						.append(cdvo.getDeptno()).append(",'").append(cdvo.getDname()).append("','")
						.append(cdvo.getLoc()).append("')");
			
			//execute는 String만 받는데 insertCpDept는 StringBuilder이기 때문에 toString을 사용해준다.
			int cnt = stmt.executeUpdate(insertCpDept.toString());
			System.out.println(cnt);
			
		}finally {
			if(stmt != null) {
				stmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		//5. 연결 끊기
		}//end finally
	}//insertCpDept
	
	public boolean updateCpDept(CpDeptVO cdvo) throws SQLException{
		boolean flag = false;
		
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		try {
		//2. Connection 얻기
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id ="scott";
			String pwd ="tiger";
			con = DriverManager.getConnection(url, id, pwd);
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리 수행 후 결과 얻기
			StringBuilder updateCpDept = new StringBuilder();
			updateCpDept.append("update cp_dept set ")
						.append("dname ='").append(cdvo.getDname()).append("',")
						.append("loc='").append(cdvo.getLoc()).append("' ")
						.append("where deptno=").append(cdvo.getDeptno());
			
			int cnt = stmt.executeUpdate(updateCpDept.toString());
			
			if(cnt != 0) { //바뀐게 하나 이상 있다는 뜻, 근데 어차피 부서번호가 pk라서 한 행만 변경된다.
				flag = true;
			}//end if
			
		} finally {
		//5. 연결 끊기
			if(stmt != null) {
				stmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		
		return flag;
	}//updateCpDept
	
	public boolean deleteCpDept(int deptno) throws SQLException{
		boolean flag = false;
		//1. 드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		Connection con = null;
		Statement stmt = null;
		
		try {
		//2. Connection 얻기
			String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id ="scott";
			String pwd ="tiger";
			
			con = DriverManager.getConnection(url, id, pwd);
			
		//3. 쿼리문 생성객체얻기
			stmt = con.createStatement();
		//4. 쿼리 수행 후 결과 얻기
			StringBuilder deleteCpDept = new StringBuilder();
			deleteCpDept.append("delete from cp_dept where deptno=").append(deptno);
			
			int cnt = stmt.executeUpdate(deleteCpDept.toString());
			if(cnt == 1) { // cnt != 0이라고 해도 무관
				flag = true;
			}//end if
			
		} finally {
		//5. 연결끊기
			if(stmt != null) {
				stmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		} //end finally
		
		return flag;
	}//deleteCpDept
	
	public List<CpDeptVO> selectAllCpDept() throws SQLException {
		List<CpDeptVO> list = new ArrayList<CpDeptVO>();
		
		//1. Driver 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id = "scott";
			String pwd = "tiger";
			
			con = DriverManager.getConnection(url, id, pwd);
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			String selectCpDept = "select deptno,dname,loc from cp_dept";
			rs=stmt.executeQuery(selectCpDept);
			CpDeptVO cdvo = null;
			
			while(rs.next()) { //조회된 레코드가 존재한다면
				//컬럼의 인덱스로 조회는 비추
//				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				
				//컬럼명으로 조회, 이 방법을 추천
//				System.out.println(rs.getInt("deptno")+" "+rs.getString("dname")+" "+rs.getString("loc"));
				
				//조회 결과를 VO에 저장
				cdvo = new CpDeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				
				//같은 이름으로 생성된 cdvo 객체를 사라지지 않도록 관리하기 위해 List에 추가
				list.add(cdvo);

			}//end while
		} finally {
		//5. 연결 끊기
			if(rs != null) {
				rs.close();
			}//end if
			if(stmt != null) {
				stmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		} //end finally
		return list;
		
	}//selectAllCpDept
	
	public OneCpDeptVO selectCpDept(int deptno) throws SQLException {
		OneCpDeptVO ocdvo = null;
		
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			StringBuilder selectCpDept = new StringBuilder();
			selectCpDept.append("select dname,loc from cp_dept where deptno=").append(deptno);
			
			rs=stmt.executeQuery(selectCpDept.toString());	//rs는 커서의 제어권!
			if(rs.next()) {
				ocdvo = new OneCpDeptVO(rs.getString("dname"), rs.getString("loc"));
			}
			
		} finally {
		//5. 연결끊기	//연결끊기는 뒤에서부터!!
			if(rs != null) {
				rs.close();
			}//end if
			if(rs != null) {
				rs.close();
			}//end if
			if(rs != null) {
				rs.close();
			}//end if
		}//end finally
		
		return ocdvo;
	}//selectCpDept
	
	/**
	 * CP_DEPT 테이블의 모든 부서번호를 조회
	 * @return
	 * @throws SQLException
	 */
	public List<Integer> selectAllCpDeptNo() throws SQLException{
		List<Integer> list = new ArrayList<Integer>();
			
		//1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}//end catch
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		//2. Connection 얻기
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
		//3. 쿼리문 생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 수행 후 결과 얻기
			String selectAllCpDeptNo = "select deptno from cp_dept";
			
			rs = stmt.executeQuery(selectAllCpDeptNo);
			
			while(rs.next()) { //조회된 레코드가 존재한다면
				list.add(rs.getInt("deptno"));
			}//end while
			
		} finally {
		//5. 연결끊기
			if(rs != null) {
				rs.close();
			}//end if
			if(stmt != null) {
				stmt.close();
			}//end if
			if(con != null) {
				con.close();
			}//end if
		}//end finally
		return list;
	}//selectAllCpDeptNo
	
}//class
