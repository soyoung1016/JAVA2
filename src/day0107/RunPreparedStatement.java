package day0107;

import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class RunPreparedStatement {
	
	private UsePreparedStatementDAO ups_dao;
	
	public RunPreparedStatement() {
		ups_dao = new UsePreparedStatementDAO();
		
		
		
	}//RunPreparedStatement

	public void addCpEmp2() {
		String tempData = JOptionPane.showInputDialog("사원정보 추가\n입력 예)사원번호,사원명,연봉");
		if(tempData != null && !tempData.equals("")) {
			String[] data = tempData.split(",");
			if(data.length != 3) {
				JOptionPane.showMessageDialog(null, "입력형식을 확인해 주세요.");
				return;
			}//end if
			
			int empno = 0, sal = 0;
			String ename = "";
			
			try {
				empno = Integer.parseInt(data[0]);
				ename = data[1];
				sal = Integer.parseInt(data[2]);
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호나 연봉은 숫자 입니다.");
				return;
			}//end catch
			
			//처리된 입력데이터를 VO에 설정하고
			CpEmp2VO cevo = new CpEmp2VO(empno, sal, ename);
			//VO의 값을 DB에 insert한다.
			try {
				ups_dao.insertCpEmp2(cevo); //추가성공
				JOptionPane.showMessageDialog(null, empno+"번 사원정보가 입력되었습니다.");
			} catch (SQLException se) { //예외
				String errMsg="";
				switch (se.getErrorCode()) {
				case 1438 : errMsg = "사원번호는 4자리이고 연봉은 5자리 입니다.";
					break;
				case 12899 : errMsg = "사원명이 너무 깁니다.";
					break;
				default : errMsg = "죄송합니다. 잘못된 접근입니다.";
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg);
				se.printStackTrace();
			}//end catch
		}//end if
		
	}//addCpEmp2
	
	public void modifyCpEmp2() {
		String tempData = JOptionPane.showInputDialog("사원정보 변경\n사원번호에 일치하는 사원명과 연봉을 변경합니다\n입력 예)사원번호,사원명,연봉");
		if(tempData != null && !tempData.equals("")) {
			String[] data = tempData.split(",");
			if(data.length != 3) {
				JOptionPane.showMessageDialog(null, "입력형식을 확인해 주세요.");
				return;
			}//end if
			
			int empno = 0, sal = 0;
			String ename = "";
			
			try {
				empno = Integer.parseInt(data[0].trim());
				ename = data[1].trim();
				sal = Integer.parseInt(data[2]);
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호나 연봉은 숫자 입니다.");
				return;
			}//end catch
			
			//처리된 입력데이터를 VO에 설정하고
			CpEmp2VO cevo = new CpEmp2VO(empno, sal, ename);
			//VO의 값을 DB에 update한다.
			try {
				String msg = "";
				if(ups_dao.updateCpEmp2(cevo)) { //변경된 레코드가 존재
					msg = empno+"번 사원의 정보를 변경하였습니다.";
				} else { //변경된 레코드가 존재하지 않음
					msg = empno+"번 사원은 존재하지 않습니다.";
				}//end else
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (SQLException se) {//예외
				String errMsg="";
				switch (se.getErrorCode()) {
				case 12899 : errMsg = "사원명 혹은 연봉이 범위를 초과하였습니다";
					break;
				default : errMsg = "죄송합니다. 잘못된 접근입니다.";
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg);
				se.printStackTrace();
			}//end catch
		}//end if		
	}//modifyCpEmp2
	
	public void removeCpEmp2() {
		String inputData = JOptionPane.showInputDialog("사원삭제\n삭제할 사원번호를 입력해주세요!");
		if(inputData != null && !inputData.equals("")) {
			int empno = 0;
			try {
				empno = Integer.parseInt(inputData.trim());
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호는 숫자이어야 합니다.");
				return;
			}//end catch
			
			try {
				//if else를 쓸때는 else에 들어가는걸 초기값으로 설정해서 else를 쓰지 않게 코드를 줄인다.
				String msg = empno + "번 사원은 존재하지 않습니다.";
				if(ups_dao.deleteCpEmp2(empno)) {
					msg = empno+"번 사원 정보를 삭제하였습니다.";
				}//end if
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, "죄송합니다. 문제가 발생하였습니다.");
				se.printStackTrace();
			}//end catch
		}//end if
	}//removeCpEmp2
	
	public void searchAllCpEmp2() {
		StringBuilder viewCpEmp = new StringBuilder();
		viewCpEmp.append("----------------------------------------")
				 .append("----------------------------------------")
				 .append("-----------------------------------------\n")
				 .append("번호\t사원번호\t사원명\t연봉\t입사일\n")
				 .append("----------------------------------------")
				 .append("----------------------------------------")
				 .append("-----------------------------------------\n");
		
		int rowCount = 0;
		
		try {
			//DB에서 조회한 결과 받기
			List<CpEmp2AllVO> list = ups_dao.selectAllCpEmp2();
			CpEmp2AllVO cevo = null;
			
			rowCount = list.size();
			for(int i = 0; i<list.size(); i++) {
				cevo = list.get(i);
				viewCpEmp.append(i+1).append("\t")
						 .append(cevo.getEmpno()).append("\t")
						 .append(cevo.getName()).append("\t")
						 .append(cevo.getSal()).append("\t")
					  .append(cevo.getHiredate()).append("\n");
			}//end for
			
			if(list.isEmpty()) { //list.size() == 0 으로 써도 됨
				viewCpEmp.append("입력된 사원정보가 존재하지 않습니다.\n");
			}//end if
			
		} catch (SQLException se) {
			viewCpEmp.append("DBMS에서 문제가 발생하였습니다\n");
			se.printStackTrace();
		}//end catch
		
		viewCpEmp.append("---------------------------------------------------------------------------------\n")
				  .append("\t총 ").append(rowCount).append("개의 사원정보가 조회되었습니다.");
		
		JTextArea jta = new JTextArea(20, 50);
		jta.setEditable(false);
		jta.setText(viewCpEmp.toString()); //만들어진 출력데이터를 T.A에 설정한다.
		
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBorder(new TitledBorder("전체 사원 조회 결과"));
		JOptionPane.showMessageDialog(null, jsp);
	}//searchAllCpEmp2
	
	public void searchOneCpEmp2() {
		String inputData = JOptionPane.showInputDialog(null, "조회할 사원의 사원번호를 입력해주세요", "사원조회", JOptionPane.QUESTION_MESSAGE);
		if(inputData != null && !inputData.equals("")) {
			try {
				int empno = Integer.parseInt(inputData.trim());
				//사원번호를 입력하여 사원번호에 해당하는 사원정보를 조회
				//조회한 사원이 있다면 생성된 객체(CpEmp2OneVO)가 반환되고 조회된 사원이 없다면 null이 반환된다.
				CpEmp2OneVO ceo_vo = ups_dao.selectOneCpEmp2(empno);
				
				//날짜를 원하는 형식으로 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy EEEEE");
				
				StringBuilder viewData = new StringBuilder();
				viewData.append("사원명 : ").append(ceo_vo.getEname())
						.append(", 연봉 : ").append(ceo_vo.getSal())
						.append(", 입사일 : ").append(sdf.format(ceo_vo.getHiredate()));
				
				JLabel lbl = new JLabel(viewData.toString());
				lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
					
				JOptionPane.showMessageDialog(null, lbl);
				
			} catch(NullPointerException npe) {
				JOptionPane.showMessageDialog(null, inputData+"번 사원은 존재하지 않습니다.");
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호는 정수형태로 입력하셔야 합니다.");
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, "서버에서 문제가 발생하였습니다.");
				se.printStackTrace();
			}//end catch
		}//end if
	}//searchOneCpEmp2
	
	public static void main(String[] args) {
		RunPreparedStatement rps = new RunPreparedStatement();
		
		boolean exitFlag = false;
		String inputMenu = "";
		do {
			inputMenu = JOptionPane.showInputDialog("메뉴선택\n1.사원추가 2.사원변경 3.사원삭제 4.전체사원조회 5.특정사원조회 6.종료");
			
			if(inputMenu != null) {
				switch(inputMenu) {
				case "1" :
					rps.addCpEmp2();
					break;
				case "2" :
					rps.modifyCpEmp2();
					break;
				case "3" :
					rps.removeCpEmp2();
					break;
				case "4" :
					rps.searchAllCpEmp2();
					break;
				case "5" :
					rps.searchOneCpEmp2();
					break;
				case "6" :
					exitFlag = true;
					break;
				default :
					JOptionPane.showMessageDialog(null, inputMenu+"는 제공되는 서비스가 아닙니다.");
					break;
				}//end switch
			} else {
				exitFlag = true;
			}//end else
			
		}while(!exitFlag);
		
		JOptionPane.showMessageDialog(null, "사용해 주셔서 감사합니다.");
		
	}//main

}//class
