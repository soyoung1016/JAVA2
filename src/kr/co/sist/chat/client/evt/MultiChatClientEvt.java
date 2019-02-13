package kr.co.sist.chat.client.evt;

import java.awt.Dialog;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import kr.co.sist.chat.client.view.MultiChatClientView;

public class MultiChatClientEvt extends WindowAdapter implements WindowListener, ActionListener, Runnable {

	private MultiChatClientView mccv;
	private Socket client;
	private DataInputStream readStream;
	private DataOutputStream writeStream;
	private Thread threadMsg;
	private String nick;
	private TextArea taName;
	
	public MultiChatClientEvt(MultiChatClientView mccv) {
		this.mccv = mccv;
		
		
	}//MultiChatClientEvt
	
	@Override
	public void run() {
		if(readStream != null) {
			try {
				String revMsg = "";
				JScrollPane jsp = mccv.getJspTalkDisplay();
				
				while(true) {//�������� �������� �޼����� �о�鿩
					revMsg = readStream.readUTF();
					//ä��â�� �Ѹ���.
					mccv.getJtaTalkDisplay().append(revMsg+"\n");
					//��ũ�ѹٸ� ���� �Ʒ��� �̵�
					jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getMaximum());
				}//end while
			} catch(IOException ie) {
				JOptionPane.showMessageDialog(mccv, "������ ����Ǿ����ϴ�.");
				ie.printStackTrace();
			}//end catch
		}//end if
	}//run
	
	@Override
	public void windowClosed(WindowEvent we) {
		try {
			if(readStream != null) {
				readStream.close();
			}//end if
			if(writeStream != null) {
				writeStream.close();
			}//end if
			if(client != null) {
				client.close();
			}//end if
		} catch(IOException ie) {
			ie.printStackTrace();
		} finally {
			System.exit(0);
		}//end finally
	}//windowClosed
	
	public void userList() {
		Dialog duserList = new Dialog(mccv, "�����ڸ��");
		
		Label temp = new Label("������ ���");
		taName = new TextArea();
		
		JTextField temp2 = mccv.getJtfNick();
		//JTF�� �Է��� �޼����� ������� �о� ���δ�.
		String name = temp2.getText().trim();
		taName.setText(name+"�ǳ�");
		
		duserList.add(temp);
		duserList.add(taName);
		duserList.add("North", temp);
		duserList.add("Center", taName);
		
		duserList.setBounds(100,100,200,200);
		duserList.setVisible(true);
		
		duserList.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				duserList.dispose();
			}
		});
	}//userLlist
	
	public void connectToServer() throws UnknownHostException, IOException {
		if(client == null) {
			nick = mccv.getJtfNick().getText().trim();
			if(nick.equals("")) {
				JOptionPane.showMessageDialog(mccv, "��ȭ���� �Է��� �ּ���.");
				mccv.getJtfNick().requestFocus();//Ŀ�� �������ֱ�.
				return;
			}//end if
			
			String serverIp = mccv.getJtfServerIp().getText().trim();
			
			client = new Socket(serverIp, mccv.getSelectPort()); //�Է��� ip address�� ��ǻ�Ϳ� �����Ѵ�.
			
			//��Ʈ��
			readStream = new DataInputStream(client.getInputStream());
			writeStream = new DataOutputStream(client.getOutputStream());
			
			//��ȭ���� ������ ������.
			writeStream.writeUTF(nick);
			writeStream.flush();
			
			mccv.getJtaTalkDisplay().setText("������ ���� �Ͽ����ϴ�.\n��ſ� ä�� �Ǽ���.\n");
			
			//�޼��� �о���̱�
			threadMsg = new Thread(this);
			threadMsg.start();
		} else {
			JOptionPane.showMessageDialog(mccv, client.getInetAddress().getHostAddress()+"������ �̹� ������ ���� ���Դϴ�.");
		}//end else
	}//connectToServer
	
	/**
	 * ������ �޼����� ������ ��
	 * @throws IOException 
	 */
	public void sendMsg() throws IOException {
		if(writeStream != null) {
			JTextField jtf = mccv.getJtfTalk();
			//JTF�� �Է��� �޼����� ������� �о� ���δ�.
			String msg = jtf.getText().trim();
			
			if(!msg.isEmpty()) {
				//��Ʈ���� ����.
				writeStream.writeUTF("["+nick+"] "+msg);
				//������ ����
				writeStream.flush();
			}//end if
			jtf.setText("");
		}//end if
	}//sendMsg
	
	public void capture() throws IOException {
		switch(JOptionPane.showConfirmDialog(mccv, "��ȭ������ �����Ͻðڽ��ϱ�?")) {
			//��Ʈ���� ����Ͽ� ����
		case JOptionPane.OK_OPTION :
			File saveDir = new File("c:/dev/chat");
			saveDir.mkdirs();
			File saveFile = new File(saveDir.getAbsolutePath()+"java_chat["+System.currentTimeMillis()+"].dat");
			BufferedWriter bw = null;
			
			try {
				bw = new BufferedWriter(new FileWriter(saveFile));
				bw.write(mccv.getJtaTalkDisplay().getText()); //��Ʈ���� ��ȭ���� ���
				bw.flush();//��Ʈ���� ��ϵ� ������ ����
				JOptionPane.showMessageDialog(mccv, saveDir+"�� ��ȭ ������ ��ϵǾ����ϴ�.");
			} finally {
				if(bw != null) {
					bw.close();
				}//end if
			}//end finally
		}//end switch
	}//capture
	
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mccv.getJbtConnect()) {
			try {
				connectToServer();
			} catch (UnknownHostException uhe) {
				JOptionPane.showMessageDialog(mccv, "������ �� �� �����ϴ�.");
				uhe.printStackTrace();
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(mccv, "���� ����!");
				ie.printStackTrace();
			}//end catch
		}//end if
		
		if(ae.getSource() == mccv.getJbtClose()) {
			mccv.dispose();
		}//end if
		
		if(ae.getSource() == mccv.getJbtCapture()) {
			try {
				if(!mccv.getJtaTalkDisplay().getText().equals("")) {
					capture();
				} else {
					JOptionPane.showMessageDialog(mccv, "������ ��ȭ������ �����ϴ�.");
				}//end else
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(mccv, "���Ϸ� �����ϴ� �� ������ �߻��߽��ϴ�.");
				ie.printStackTrace();
			}
		}//end if
		
		//������ ���
		if(ae.getSource() == mccv.getJbtUserList()) {
			userList();
		}//end if
		
		if(ae.getSource() == mccv.getJtfTalk()) {
			try {
				sendMsg();
			} catch (IOException ie) {
				JOptionPane.showMessageDialog(mccv, "������ ����Ǿ� �޼����� ������ �� �����ϴ�.");
				ie.printStackTrace();
			}//end catch
		}//end if
	}//actionPerformed
	
}//class
