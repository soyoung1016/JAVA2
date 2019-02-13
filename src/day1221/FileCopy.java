package day1221;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class FileCopy extends JFrame implements ActionListener, Runnable {
	
	private JButton jb;
	private JProgressBar jpb;
	private long fileLen;
	private int cnt;
	
	public FileCopy() {
		super("���Ϻ���");
		jb = new JButton("���ϼ���");
		jpb = new JProgressBar(0,100);
		jpb.setString("��ô��");
		jpb.setValue(50);
		
		JPanel jp = new JPanel();
		jp.add(jb);
		
		add("Center", jp);
		add("South", jpb);
		
		jb.addActionListener(this);
		setBounds(100,100,500,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void run() {
		for(int i =0; i<fileLen; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				jpb.setValue((int)((cnt/(double)fileLen)*100));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog fd = new FileDialog(this, "���ϼ���", FileDialog.LOAD);
		fd.setVisible(true);
		
		String path = fd.getDirectory();
		String name = fd.getFile();
		if(path!=null) {
			File file = new File(path+name);
			try {
				copy(file);
				JOptionPane.showMessageDialog(this, file+"���� ����");
			} catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(this, "������ �������� �ʽ��ϴ�");
				fnfe.printStackTrace();
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(this, "����� �۾��� ���� �߻�");
				ie.printStackTrace();
			}
		}
		
	}

	public void copy(File file) throws FileNotFoundException, IOException {
		
		int selectValue = JOptionPane.showConfirmDialog(this, "������ �����Ͻðڽ��ϱ�?");
		
		switch(selectValue) {
		case JOptionPane.OK_OPTION :
			StringBuilder copyFileName = new StringBuilder(file.getAbsolutePath());
			copyFileName.insert(copyFileName.lastIndexOf("."), "_bak");
//			System.out.println(copyFileName);
			
			FileInputStream fis = null;
			FileOutputStream fos = null;
			
			try {
				//�������Ͽ� ��Ʈ�� ����
				fis = new FileInputStream(file);
				fos = new FileOutputStream(copyFileName.toString()); //_bak�� �� ���� ����
				//���ϰ� ����� ��Ʈ������ ���� ��´�
				int temp = 0;
				fileLen = file.length();
//				System.out.println(fileLen);
				cnt=0;
				Thread t = new Thread(this);
				
//				int i = 0;
				//HDD�� �о���̴� ũ�⸦ �����ϰ� 1byte�� �о�鿩 ���
				while((temp=fis.read()) != -1) {
					fos.write(temp);
					fos.flush();
//					jpb.setValue((int)((i/(double)fileLen)*100));
//					System.out.println(jpb.getValue());
////					//�о���� ������ _bak�� ���� ������ �����Ͽ� ���(����)
//////					System.out.print((char)temp);
					cnt++;
				}//end while
				
				//HDD�� �ѹ��� �о���̴� ũ�⸦ �״�� ���(�ӵ��� �е������� ������)
//				byte[] temp = new byte[512];
//				int len = 0;
//				while((len=fis.read(temp)) != -1) {
//					fos.write(temp,0,len);
//					fos.flush();
//				}
				
			} finally {
				if(fis!=null) {
					fis.close();
				}
				if(fos!=null) {
					fos.close();
				}
			}
			
		}
				
	}
	
	public static void main(String[] args) {
		new FileCopy();
	}

}
