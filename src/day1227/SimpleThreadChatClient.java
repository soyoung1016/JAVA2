package day1227;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Thread�� ����Ͽ� ���ѷ����� �޼����� �о���̴� �ڵ带
 * ȭ�� �����ΰ�, �޼����� ������ �ڵ�� ���ÿ� �����Ų��.
 * @author owner
 */
@SuppressWarnings("serial")
public class SimpleThreadChatClient extends JFrame implements Runnable, ActionListener {
	
	private JTextArea jta;
	private JTextField jtf;
	private JScrollPane jsp;
	
	private Socket client; //������ �����ϱ� ����
	private DataInputStream readStream; //������ �����͸� �б����� ��Ʈ��
	private DataOutputStream writeStream; //������ �����͸� ���������� ��Ʈ��
	
	private String serverNick; //������ ��ȭ��
	private String clientnick; //Ŭ���̾�Ʈ�� ��ȭ��
	
	public SimpleThreadChatClient() {
		super("==ä��Ŭ���̾�Ʈ==============");
		
		jta = new JTextArea();
		jta.setBorder(new TitledBorder("��ȭ����"));
		
		jtf = new JTextField();
		jtf.setBorder(new TitledBorder("��ȭ�Է�"));
		
		//��ȭ���� ����Ұ�
		jta.setEditable(false);
		jta.setLineWrap(false);
		jta.setWrapStyleWord(true);
		
		//��ũ�� ����
		jsp = new JScrollPane(jta);
		
		add("Center",jsp);
		add("South",jtf);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
			}//windowClosing
			
			@Override
			public void windowClosed(WindowEvent we) {
				try {
					close();
//					System.exit(0);
				} catch (IOException ie) {
					ie.printStackTrace();
				}//end catch
			}//windowClosed
		});
		
		setBounds(100,100,300,400);
		setVisible(true);
		jtf.requestFocus(); //Ŀ���� jtf�� ��ġ��Ų��.
		
//		try {
//			connectToServer();
//			Thread t = new Thread(this);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}//end catch
		
		
		jtf.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			sendMsg();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}//actionPerformed
	
	public void close() throws IOException {
		try {
			if(readStream != null) { readStream.close(); }//end if
			if(writeStream != null) { writeStream.close(); }//end if
		} finally {
			if(client != null) { client.close(); }//end if
		}//end finally
	}//close
	
	/**
	 * 2. ������ �����Ͽ� ������ �����ϰ� ��ȭ�� �аų� ������ ����
	 * 4. ��Ʈ���� �����Ѵ�.
	 * @throws IOException
	 */
	public void connectToServer() throws IOException {
		clientnick = JOptionPane.showInputDialog("��ȭ�� �Է�");
		//2. 
//		client = new Socket("211.63.89.155", 65535);
		client = new Socket("211.63.89.152", 35000);
		//���� 144~!~!
		//4. ��Ʈ������
		readStream = new DataInputStream(client.getInputStream());
		writeStream = new DataOutputStream(client.getOutputStream());
		
		//������ ��ȭ�� ����
		writeStream.writeUTF(clientnick);
		jta.setText("��ȭ�濡 "+clientnick+"���� �����Ͽ����ϴ�.\n");
		//������ ��ȭ���� ����
		serverNick = readStream.readUTF();
		
	}//connectToServer
	
	/**
	 * �������� �������� �޼����� ���ѷ����� �о� ���δ�.
	 */
	@Override
	public void run() {
		String revMsg = "";
		if(readStream != null) {
			try {
				while(true) {
					revMsg=readStream.readUTF();
					//��ȭâ�� �о���� �޼����� ���
					jta.append("[ "+serverNick+" ] : "+revMsg+"\n");
					//�޼��� T.A�� �߰��Ǹ� JScrollPane�� ��ũ�ѹ��� ����
					//J.S.P�� �ְ����� �������ش�
					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				}//end while
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(this, serverNick+"���� �����Ͽ����ϴ�.");
			}//end catch
		}//end if
	}//readMsg
	
	/**
	 * �ۼ��� �޼����� ������ ������ ��
	 * @throws IOException 
	 */
	public void sendMsg() throws IOException {
		//�ۼ��� �޼����� �����ͼ�
		String sendMsg = jtf.getText().trim();
		//��Ʈ���� ����ϰ�
		writeStream.writeUTF(sendMsg);
		//��Ʈ���� ������ �������� ����
		writeStream.flush();
		//�ۼ��� �޼����� ä��â�� ����Ѵ�.
		jta.append("[ "+clientnick+" ]"+sendMsg+"\n");
		//T.F�� ������ �����Ѵ�.
		jtf.setText("");
		//�޼��� T.A�� �߰��Ǹ� JScrollPane�� ��ũ�ѹ��� ����
		//J.S.P�� �ְ����� �������ش�
		jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
	}//sendMsg

	public static void main(String[] args) {
//		new SimpleThreadChatClient();
		SimpleThreadChatClient stcc = new SimpleThreadChatClient();
		try {
			stcc.connectToServer();
			//�޼����� ���ÿ� �о���ϱ� ������ Thread�� ó��
			Thread t = new Thread(stcc);
			t.start();
		} catch (IOException ie) {
			ie.printStackTrace();
		}//end catch
	}//main
}//class
