package day0114;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * �����ڰ� �������� ������ �����ϴ� ����
 * @author owner
 */
@SuppressWarnings("serial")
public class FileServer extends JFrame implements Runnable, ActionListener{//Runnable�� ���ѹݺ����� ������ ���� ������ ���� �� �ְ� ���ش�.
	
	private DefaultListModel<String> dlmFileList;
	private JButton btnStartServer;
	private Thread threadServer;
	private ServerSocket socketServerFile;
	
	
	public FileServer() {
		super("���� ����");
		dlmFileList = new DefaultListModel<String>();
		JList<String> listFile = new JList<String>(dlmFileList);	//List�� �� �ִ� ����� defaultList ���� JList�� �ֱ�
		btnStartServer = new JButton("��������");
		
		JScrollPane jsp = new JScrollPane(listFile);
		jsp.setBorder(new TitledBorder("���ϸ��"));
		
		JPanel panel = new JPanel();
		panel.add(btnStartServer);
		
		add("Center", jsp);
		add("South", panel);
		
		btnStartServer.addActionListener(this);
		
		setBounds(100,100,400,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//FileServer

	@Override
	public void run() {
		try {
			Socket socketClient = null;
			DataInputStream dis = null;
			StringBuilder fileName = new StringBuilder();
			int dataCnt = 0;
			FileOutputStream fos = null;
			File file = null;
			
			byte[] readData = null;
			int dataLen = 0;
			try {
				while(true) {
					//������ ������ ������.
					socketClient = socketServerFile.accept();
					
					//2. Ŭ���̾�Ʈ�� �����ϴ� ���ϸ� ����
					dis = new DataInputStream(socketClient.getInputStream());
					fileName.delete(0, fileName.length());	//���ϸ��� ������ �̸��� ��ĥ���� ����. ����� �ٽ� �޾Ƽ�...�׷�����...
					fileName.append(dis.readUTF());
					
					//���ϸ��� ��ġ�� �ʵ��� ����
					fileName.insert(fileName.lastIndexOf("."), "_".concat(String.valueOf(System.currentTimeMillis())));
					dlmFileList.addElement(fileName.toString());
					
					//4. Ŭ���̾�Ʈ�� �����ϴ� �迭�� ����(�о���� Ƚ��)�� �޴´�.
					dataCnt = dis.readInt();
//					dlmFileList.addElement(String.valueOf(dataCnt));
//					System.out.println("����" +dataCnt);
					
					//6. ����Ƚ����ŭ �о�鿩 ���Ϸ� ����Ѵ�.
					file = new File("C:/dev/workspace/javase_prj2/src/day0114/image/"+fileName);
					
					fos = new FileOutputStream(file);
					readData = new byte[512];
					
					while(dataCnt>0) {
						dataLen = dis.read(readData); //Ŭ���̾�Ʈ�� �����ϴ� �迭�� ũ�⸸ŭ�� �޾Ƽ� 
						fos.write(readData,0,dataLen); //���Ͽ� ���
						fos.flush();
						dataCnt--;
					}//end if
					
				}//end while
			} finally {
				if(fos != null) {
					fos.close();
				}//end if
				if(dis != null) {
					dis.close();
				}//end if
				if(socketClient != null) {
					socketClient.close();
				}//end if
			}//end finally
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
	}//run
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(threadServer == null) {
			try {
				socketServerFile = new ServerSocket(10000);
				dlmFileList.addElement("������ 10000��Ʈ�� ����Ǿ����ϴ�.");
				threadServer = new Thread(this);
				threadServer.start();
			} catch (IOException se) {
				JOptionPane.showMessageDialog(this, "��Ʈ�� �̹� ������Դϴ�.");
				se.printStackTrace();
			}//end catch
		} else {
			JOptionPane.showMessageDialog(this, "������ �̹� ���� ���Դϴ�.");
		}//end else
	}//actionPerformed
	
	public static void main(String[] args) {
		new FileServer();
	}

}//class
