package day1224;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

/**
 * ������ �ּҿ� ��Ʈ�� ������ ������ ������ �õ�
 * @author owner
 */
public class SimpleClient {

	public SimpleClient() throws UnknownHostException, IOException {
		//2. ������ ���� : �� ��ǻ�Ϳ� �������� localhost or 127.0.0.1(roof back)
		//�ٸ� ��ǻ�Ϳ� �� ������ ip address ���
		Socket client = null;
		//�������� �������� �޼��� �б����� ��Ʈ��
		DataInputStream dis = null;
		//������ �޼����� ������ ���� ��Ʈ��
		DataOutputStream dos= null;
		
		try {
			String ip = JOptionPane.showInputDialog("���� ip\n130,132,133,134,135,157,146,131,141,142,143,144,155,156,148,149,151,152,153");
			client = new Socket("211.63.89."+ip, 65535);
			System.out.println("������ �����Ͽ����ϴ�."+client);
			//7. ���Ͽ��� ��Ʈ�� ���
			dis = new DataInputStream(client.getInputStream());
			//8. �������� ������ �޼��� �б�
			String revMsg = dis.readUTF();
			JOptionPane.showMessageDialog(null, "������ �޼��� : "+revMsg);
			
			//9. ������ �����͸� ������ ���� ��Ʈ���� ���Ͽ������� ��´�.
			String sendMsg = "������ �������Դϴ�.";
			dos = new DataOutputStream(client.getOutputStream());
			
			//10. �غ�� �޼�����  ��Ʈ���� ���
			dos.writeUTF(sendMsg);
			
			//11. ��Ʈ���� ��ϵ� �����͸� ������(����//������ �����)�� ����
			dos.flush();
			
		} finally {
			if(dos != null) {
				dos.close();
			}
			if(dis != null) {
				dis.close();
			}
			if(client != null) {
				client.close();
			}
		}//end finally
	}//SimpleClient
	
	public static void main(String[] args) {
		try {
			new SimpleClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//main

}//class
