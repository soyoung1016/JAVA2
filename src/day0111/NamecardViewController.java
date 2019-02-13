package day0111;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NamecardViewController extends WindowAdapter implements ActionListener {
	private NamecardView nv;
	private NamecardDAO n_dao;
	private String imgPath;
	
	public NamecardViewController(NamecardView nv) {
		this.nv = nv;
		n_dao = NamecardDAO.getInstance(); //�ѹ��� ��������� ������ �߰��� �׾������ ������ �ȴ�.
		setAllNamecard(); //���̺� �����͸� �߰��Ѵ�.
		imgPath = "";
	}//NamecardViewController
	
	@Override
	public void windowClosing(WindowEvent we) {

	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == nv.getJbtAdd()) {
			addNamecard();
		}//end if
		if(ae.getSource() == nv.getJbtImg()) {
			selectFile();
		}//end if
	}//actionPerformed
	
	private void selectFile() {
		FileDialog fdOpen = new FileDialog(nv, "�̹�������", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String path = fdOpen.getDirectory();
		String name = fdOpen.getFile();
		if(path != null) {
			imgPath = path+"/"+name;
			JLabel jl = nv.getJlPreview();
			jl.setIcon(new ImageIcon(imgPath));
		}//end if
		
	}//selectFile
	
	private void addNamecard() {
		JTextField jtfName = nv.getJtfName();
		JTextField jtfAddr = nv.getJtfAddr();
		
		String name = jtfName.getText().trim();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(nv, "�̸��� �ʼ� �Է��Դϴ�.");
			jtfName.requestFocus();
			return;
		}//end if
		
		String addr = jtfAddr.getText().trim();
		if(addr.equals("")) {
			JOptionPane.showMessageDialog(nv, "�ּҴ� �ʼ� �Է��Դϴ�.");
			jtfAddr.requestFocus();
			return;
		}//end if
		
		boolean insertFlag = false;
		
		if(imgPath.equals("")) {//�̹����� �������� �ʾ��� ��
			insertFlag = JOptionPane.showConfirmDialog(nv, "�⺻ �̹����� ����Ͻðڽ��ϱ�?") == JOptionPane.OK_OPTION;
			if(insertFlag) {
				imgPath = "no_image.png";
			}//end if
		} else { //�̹����� �������� ��
			insertFlag = true;
		}//end else
		
		if(insertFlag) {
		//DB�� �߰�
			//����� ���ϸ����� ������ ��ġ�� ���ε带 ������ ��
			StringBuilder filePath = new StringBuilder();
			filePath.append(System.currentTimeMillis()).append(imgPath.substring(imgPath.lastIndexOf(".")));
			
			try {
				if(!imgPath.equals("no_image.png")) {//�⺻ �̹����� �ƴ϶�� ���ε� ����
					uploadImg(filePath.toString());
				}//end if
			//DB�� �߰��Ѵ�.
				NamecardVO nvo = new NamecardVO(name, addr, !imgPath.equals("no_image.png")?filePath.toString():"no_image.png");
				n_dao.insertNamecard(nvo);
				JOptionPane.showMessageDialog(nv, "������ �߰��Ǿ����ϴ�.");
				
				jtfName.setText("");
				jtfAddr.setText("");
				nv.getJlPreview().setIcon(new ImageIcon("C:/dev/workspace/javase_prj2/src/day0111/upload/no_image.png"));
				jtfName.requestFocus();
				
				setAllNamecard();//�߰��� ������ �������ش�.
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(nv, "���� �߰��� ���� �߻�");
				
				e.printStackTrace();
			} catch(IOException ie) {
				JOptionPane.showMessageDialog(nv, "�̹��� ���ε� �� ���� �߻�");
				ie.printStackTrace();
			}//end catch
			imgPath = "";
		}//end if
		
	}//addNamecard
	
	/**
	 * ��ȸ�� ���������� JTable�� �߰�
	 */
	private void setAllNamecard() {
		try {
			List<NamecardVO> list = n_dao.selectNamecard();
			
			DefaultTableModel dtm = nv.getDtmNamecard();
			dtm.removeRow(0); //�����͸� ����ϱ����� �ʱ�ȭ
			
			Object[] rowData = null;
			
			NamecardVO nvo = null;
			for(int i =0; i<list.size(); i++) {//��ȸ�� ���ڵ� ����ŭ �ݺ�
				nvo = list.get(i);
				rowData = new Object[4];
				rowData[0] = new Integer(i+1);
				rowData[1] = new ImageIcon("C:/dev/workspace/javase_prj2/src/day0111/upload/"+nvo.getImg());
				rowData[2] = nvo.getName();
				rowData[3] = nvo.getAddr();
				
				dtm.addRow(rowData);
			}//end for
			
		} catch (SQLException se) {
			JOptionPane.showMessageDialog(nv, "DB�� ������ �߻��Ͽ����ϴ�.");
			se.printStackTrace();
		}//end catch
	}//setAllNamecard
	
	private boolean uploadImg(String filePath) throws IOException{
		boolean flag = false;
		
		//���������� �����Ͽ� ������ ��ġ�� �����Ѵ�.
		FileInputStream fis = new FileInputStream(imgPath);
		FileOutputStream fos = new FileOutputStream("C:/dev/workspace/javase_prj2/src/day0111/upload/"+filePath);
		
		try {
			//���� �о���̱�
			byte[] readData = new byte[512];
			int byteSize = 0;
			
			while( (byteSize = fis.read(readData)) != -1) {
				fos.write(readData, 0, byteSize);
				fos.flush();
			}//end while
			
		}finally {
			if(fos != null) {
				fos.close();
			}//end if
			if(fis != null) {
				fis.close();
			}//end if
		}//end finally
		return flag;
	}//uploadImg
	
}//class
