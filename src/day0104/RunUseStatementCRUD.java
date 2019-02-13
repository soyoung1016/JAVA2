package day0104;

import java.awt.Font;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class RunUseStatementCRUD {
	private UseStatementCRUD us_crud;
	
	public RunUseStatementCRUD() {
		us_crud = new UseStatementCRUD();
	}//RunUseStatementCRUD
	
	public void addCpDept() {
		String tempData = JOptionPane.showInputDialog("�μ����� �߰�\n�Է� ��)�μ���ȣ,�μ���,��ġ");
		if(tempData != null && !tempData.equals("")) {
			String[] data = tempData.split(",");
			if(data.length != 3) {
				JOptionPane.showMessageDialog(null, "�Է������� Ȯ���� �ּ���.");
				return;
			}//end if
			
			int deptno = 0;
			String dname = "";
			String loc = "";
			
			try {
				deptno = Integer.parseInt(data[0]);
				dname = data[1];
				loc = data[2];
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "�μ���ȣ�� ���� �Դϴ�.");
				return;
			}//end catch
			
			//ó���� �Էµ����͸� VO�� �����ϰ�
			CpDeptVO cdvo = new CpDeptVO(deptno, dname, loc);
			//VO�� ���� DB�� insert�Ѵ�.
			try {
				us_crud.insertCpDept(cdvo); //�߰�����
				JOptionPane.showMessageDialog(null, deptno+"�� �μ����� �߰�");
			} catch (SQLException se) {
				String errMsg="";
				switch (se.getErrorCode()) {
				case 1 : errMsg = deptno+"�� �μ��� �̹� �����մϴ�.";
					break;
				case 1438 : errMsg = "�μ���ȣ�� ���ڸ� �Դϴ�.";
					break;
				case 12899 : errMsg = "�μ����̳� ��ġ�� ���ڼ��� �ʹ� ��ϴ�.";
					break;
				default : errMsg = "�˼��մϴ�. �߸��� �����Դϴ�.";
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg);
				se.printStackTrace();
			}//end catch
		}//end if
		
	}//addCpDept
	
	public void modifyCpDept() {
		String tempData = JOptionPane.showInputDialog("�μ����� ����\n�μ���ȣ�� ��ġ�ϴ� �μ���� ��ġ�� �����մϴ�\n�Է� ��)�μ���ȣ,�μ���,��ġ");
		if(tempData != null && !tempData.equals("")) {
			String[] data = tempData.split(",");
			if(data.length != 3) {
				JOptionPane.showMessageDialog(null, "�Է������� Ȯ���� �ּ���.");
				return;
			}//end if
			
			int deptno = 0;
			String dname = "";
			String loc = "";
			
			try {
				deptno = Integer.parseInt(data[0]);
				dname = data[1];
				loc = data[2];
			} catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "�μ���ȣ�� ���� �Դϴ�.");
				return;
			}//end catch
			
			//ó���� �Էµ����͸� VO�� �����ϰ�
			CpDeptVO cdvo = new CpDeptVO(deptno, dname, loc);
			//VO�� ���� DB�� update�Ѵ�.
			try {
				String msg = "";
				if(us_crud.updateCpDept(cdvo)) { //����� ���ڵ尡 ����
					msg = deptno+"�� �μ��� ������ �����Ͽ����ϴ�.";
				} else { //����� ���ڵ尡 �������� ����
					msg = deptno+"�� �μ��� �������� �ʽ��ϴ�.";
				}//end else
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (SQLException se) {//����
				String errMsg="";
				switch (se.getErrorCode()) {
				case 12899 : errMsg = "�μ����̳� ��ġ�� ���ڼ��� �ʹ� ��ϴ�.";
					break;
				default : errMsg = "�˼��մϴ�. �߸��� �����Դϴ�.";
				}//end switch
				JOptionPane.showMessageDialog(null, errMsg);
				se.printStackTrace();
			}//end catch
		}//end if		
		
	}//modifyCpDept
	
	public void removeCpDept() {
		String inputData = JOptionPane.showInputDialog("�μ�����\n������ �μ���ȣ�� �Է����ּ���!");
		if(inputData != null && !inputData.equals("")) {
			int deptno = 0;
			try {
				deptno = Integer.parseInt(inputData);
			}catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "�μ���ȣ�� �����̾�� �մϴ�.");
				return;
			}//end catch
			
			try {
				//if else�� ������ else�� ���°� �ʱⰪ���� �����ؼ� else�� ���� �ʰ� �ڵ带 ���δ�.
				String msg = deptno + "�� �μ��� �������� �ʽ��ϴ�.";
				if(us_crud.deleteCpDept(deptno)) {
					msg = deptno+"�� �μ� ������ �����Ͽ����ϴ�.";
				}//end if
				JOptionPane.showMessageDialog(null, msg);
				
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, "�˼��մϴ�. ������ �߻��Ͽ����ϴ�.");
				se.printStackTrace();
			}//end catch
		}//end if
		
	}//removeCpDept
	
	public void searchAllCpDept() {
		
		StringBuilder viewCpDept = new StringBuilder();
		viewCpDept.append("---------------------------------------------------------------------------------\n")
				  .append("��ȣ\t�μ���ȣ\t�μ���\t��ġ\n")
				  .append("---------------------------------------------------------------------------------\n");
		
		int rowCount = 0;
		
		try {
			//DB���� ��ȸ�� ��� �ޱ�
			List<CpDeptVO> list = us_crud.selectAllCpDept();
			CpDeptVO cdv = null;
			
			rowCount = list.size();
			for(int i = 0; i<list.size(); i++) {
				cdv = list.get(i);
				viewCpDept.append(i+1).append("\t")
						  .append(cdv.getDeptno()).append("\t")
						  .append(cdv.getDname()).append("\t")
						  .append(cdv.getLoc()).append("\n");
			}//end for
			
			if(list.isEmpty()) { //list.size() == 0 ���� �ᵵ ��
				viewCpDept.append("�Էµ� �μ������� �������� �ʽ��ϴ�.\n");
			}//end if
			
		} catch (SQLException se) {
			viewCpDept.append("DBMS���� ������ �߻��Ͽ����ϴ�\n");
			se.printStackTrace();
		}//end catch
		
		viewCpDept.append("---------------------------------------------------------------------------------\n")
				  .append("\t�� ").append(rowCount).append("���� �μ������� ��ȸ�Ǿ����ϴ�.");
		
		JTextArea jta = new JTextArea(20, 50);
		jta.setEditable(false);
		jta.setText(viewCpDept.toString()); //������� ��µ����͸� T.A�� �����Ѵ�.
		
		JScrollPane jsp = new JScrollPane(jta);
		jsp.setBorder(new TitledBorder("��ü �μ� ��ȸ ���"));
		JOptionPane.showMessageDialog(null, jsp);
		
	}//searchAllCpDept
	
	public void searchOneCpDept() {
		String inputData = JOptionPane.showInputDialog(null, "��ȸ�� �μ��� �μ���ȣ�� �Է����ּ���", "�μ���ȸ", JOptionPane.QUESTION_MESSAGE);
		if(inputData != null && !inputData.equals("")) {
			try {
				int deptno = Integer.parseInt(inputData);
				//�μ���ȣ�� �Է��Ͽ� �μ���ȣ�� �ش��ϴ� �μ������� ��ȸ
				//��ȸ�� �μ��� �ִٸ� ������ ��ü�� ��ȯ�ǰ� ��ȸ�� �μ��� ���ٸ� null�� ��ȯ�ȴ�.
				OneCpDeptVO ocd_vo = us_crud.selectCpDept(deptno);
				
				
				StringBuilder viewData = new StringBuilder();
				viewData.append("�μ��� : ").append(ocd_vo.getDname())
						.append(", ��ġ : ").append(ocd_vo.getLoc());
				
				JLabel lbl = new JLabel(viewData.toString());
				lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
				
					
				JOptionPane.showMessageDialog(null, lbl);
			} catch(NullPointerException npe) {
				//�����ϴ� �μ���ȣ�� ����
				StringBuilder allDeptno = new StringBuilder();
				
				try {
					List<Integer> list = us_crud.selectAllCpDeptNo();
					for(int i=0; i<list.size(); i++) {
						allDeptno.append(list.get(i)).append(" ");
					}//end for
				} catch (SQLException se) {
					se.printStackTrace();
				}//end catch
				
				JOptionPane.showMessageDialog(null, inputData+" �μ��� �������� �ʽ��ϴ�.\n�����ϴ� �μ� ��ȣ : "+allDeptno+"�� �Դϴ�.");
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "�μ���ȣ�� �������·� �Է��ϼž� �մϴ�.");
			} catch (SQLException se) {
				JOptionPane.showMessageDialog(null, "�������� ������ �߻��Ͽ����ϴ�.");
				se.printStackTrace();
			}//end catch
		}//end if
		
	}//searchOneCpDept
	
	public static void main(String[] args) {
		RunUseStatementCRUD rus_crud = new RunUseStatementCRUD();
		
		boolean exitFlag = false;
		String inputMenu = "";
		do {
			inputMenu = JOptionPane.showInputDialog("�޴�����\n1.�μ��߰� 2.�μ����� 3.�μ����� 4.��ü�μ���ȸ 5.Ư���μ���ȸ 6.����");
			
			if(inputMenu != null) {
				switch(inputMenu) {
				case "1" :
					rus_crud.addCpDept();
					break;
				case "2" :
					rus_crud.modifyCpDept();
					break;
				case "3" :
					rus_crud.removeCpDept();
					break;
				case "4" :
					rus_crud.searchAllCpDept();
					break;
				case "5" :
					rus_crud.searchOneCpDept();
					break;
				case "6" :
					exitFlag = true;
					break;
				default :
					JOptionPane.showMessageDialog(null, inputMenu+"�� �����Ǵ� ���񽺰� �ƴմϴ�.");
					break;
				}//end switch
			} else {
				exitFlag = true;
			}//end else
			
		}while(!exitFlag);
		
		JOptionPane.showMessageDialog(null, "����� �ּż� �����մϴ�.");
	}//main

}//class
