package db_p;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Test2 extends Thread{

	String userID;
	ArrayList<Chatlist2> chatlists;
	ArrayList<Chatlist2> chatlists2;
	
		
		public Test2(int kind, String userID) {
			
		this.userID = userID;
		start();
//		ChatListDB.getCHATLIST(userID);
			
		}
		
		@Override
		public void run() {
		
			while(true) {
				
				try {
					chatlists = ReservationDB.getRESERVATION(userID);
//					chatlists2 = chatlists;
					
					System.out.println(chatlists);
					System.out.println(chatlists2);
//					System.out.println(chatlists.equals(chatlists2));
//					System.out.println(ReservationDB.getRESERVATION(userID).get(0).sell_id);
					
					sleep(10000);
					System.out.println("ddd");
					chatlists2 = ReservationDB.getRESERVATION(userID);
					
					if(chatlists.size() != chatlists2.size()) {
						System.out.println("������ �߰� �Ǿ ���� �޶��");
						
						 int tt = chatlists2.size()-chatlists.size();
						 int cnt = tt;
						 System.out.println("tt�� ��"+tt);
							
						 
						 for (int i = 0; i < tt; i++) {
							System.out.println("�������Խ��ϴ�~");
							JFrame reschk = new JFrame();
							System.out.println(chatlists2.size());
							String str = chatlists2.get(chatlists2.size()-cnt).chattimestr + chatlists2.get(chatlists2.size()-cnt).chatmenu+"�� ����Ǿ����ϴ�\n Ȯ�����ּ��� !";
							cnt++;
							System.out.println(str+"\nstr�־����~");
							JLabel reschk2 = new JLabel(str);
							System.out.println("�󺧿� �ھҾ��~");
							reschk.add(reschk2);
							reschk.setBounds(100, 100, 500, 200);
							
							
							
							reschk.setVisible(true);
							 
						}
//							System.out.println(chatlists.get(i) == chatlists2.get(i));
							
						
						
						
//						JOptionPane.showMessageDialog(null, "������ �߰��Ǿ����~! Ȯ�����ּ���!");
					}
					Date dd = new Date();
					ArrayList<Chatlist> aa = ChatListDB.getCHATLIST(userID);
					for (Chatlist chatlist : aa) {
						if((((chatlist.chattime.getTime()-dd.getTime())/1000))/60==4) {
							System.out.println("������?");
							JOptionPane.showMessageDialog(null, "ä�ý��� 5���� �Դϴ�. ä���� �غ����ּ���"
							+new SimpleDateFormat("hh:mm:ss").format(new Date()));
						}
						
					}
					
					
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
			
		}
		
	
	
	public static void main(String[] args) {
//		Test2 tt = new Test2(1,"������");	
//		tt.start();
		
	}

}
