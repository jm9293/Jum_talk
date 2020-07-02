package db_p;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;





public class Mainframe extends JFrame implements ActionListener, WindowListener{
   
	String userID;
	JPanel catePanel;
	JScrollPane catescpamel;
	int userkind;
	JButton jb1,jb2,jb3,jb4;
   public Mainframe(String userID) {
      
      super("  ����");
      
//      try {
//         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//         JFrame.setDefaultLookAndFeelDecorated(true);
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      } 
      this.userID = userID;
      
      userkind=UserDB.getUSERKIND(userID);
     
      
      if(UserDB.getUSERKIND(userID)==0) {
      setBounds(600,100,500+15,800);
      setLayout(null);
      JPanel cate = new JPanel();
      //cate.setBackground(Color.black);
      cate.setBounds(0, 0, 500, 80);
      cate.setLayout(new GridLayout(1,4,10,10));
      
      cate.add(jb1=new JButton("����"));
      jb1.setBorderPainted(false);
      cate.add(jb2=new JButton("ä��"));
      jb2.setBorderPainted(false);
      cate.add(jb3=new JButton("����"));
      jb3.setBorderPainted(false);
      cate.add(jb4=new JButton("����"));
      jb4.setBorderPainted(false);
      jb1.addActionListener(this);
      jb2.addActionListener(this);
      jb3.addActionListener(this);
      jb4.addActionListener(this);
      addWindowListener(this);
      add(cate);
      catePanel = new JPanel(); // ù��° ��
      catePanel.setBounds(0, 90, 500, 670);
      add(catePanel);
      jb1.doClick();
      } else if(UserDB.getUSERKIND(userID)==1){
          setBounds(600,100,500+15,800);
          setLayout(null);
          JPanel cate = new JPanel();
          //cate.setBackground(Color.black);
          cate.setBounds(0, 0, 500, 80);
          cate.setLayout(new GridLayout(1,4,10,10));
          JButton jb1,jb2,jb3,jb4;
          cate.add(jb1=new JButton("����"));
          jb1.setBorderPainted(false);
          cate.add(jb2=new JButton("ä��"));
          jb2.setBorderPainted(false);
          cate.add(jb3=new JButton("ȯ��"));
          jb3.setBorderPainted(false);
          cate.add(jb4=new JButton("����"));
          jb4.setBorderPainted(false);
          jb1.addActionListener(this);
          jb2.addActionListener(this);
          jb3.addActionListener(this);
          jb4.addActionListener(this);
          add(cate);
          catePanel = new JPanel(); // ù��° ��
          catePanel.setBounds(0, 90, 500, 670);
          add(catePanel);
          jb1.doClick();
      } else if(UserDB.getUSERKIND(userID)==2) {
    	  setBounds(600,100,500+15,800);
          setLayout(null);
          JPanel cate = new JPanel();
          //cate.setBackground(Color.black);
          cate.setBounds(0, 0, 500, 80);
          cate.setLayout(new GridLayout(1,3,10,10));
          
          cate.add(jb1=new JButton("ȸ������"));
          jb1.setBorderPainted(false);
//          cate.add(jb2=new JButton("ä��"));
//          jb2.setBorderPainted(false);
          cate.add(jb3=new JButton("��ȭ"));
          jb3.setBorderPainted(false);
          cate.add(jb4=new JButton("����"));
          jb4.setBorderPainted(false);
          jb1.addActionListener(this);
          jb3.addActionListener(this);
          jb4.addActionListener(this);
          add(cate);
          catePanel = new JPanel(); // ù��° ��
          catePanel.setBounds(0, 90, 500, 670);
          add(catePanel);
          jb1.doClick();
      }
      
     
      
      
      
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		if(userkind==0) {
			if(jb.getText().equals("����")) {
				remove(catePanel);
				add(catePanel = new BuyerFrame(userID));
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("����")){
				remove(catePanel);
				
				add(catePanel = new ResNormal(userID));
				catePanel.setBounds(0, 90, 500, 670);
				catePanel.setVisible(true);
				revalidate();
				repaint();
			}else if(jb.getText().equals("ä��")){
				remove(catePanel);
				add(catePanel = new JPanel());
				User_Chat_List ucl = new User_Chat_List(userID);
				catePanel.add(ucl);
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("����")){
				remove(catePanel);
				add(catePanel =new OptionPanel(userID, this));
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}
		}else if(userkind==1) {
			if(jb.getText().equals("ȯ��")) {
				remove(catePanel);
				add(catePanel = new SellerFrame(userID));
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("����")){
				remove(catePanel);
				
				add(catePanel = new ResSeller(userID));
				catePanel.setVisible(true);
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("ä��")){
				remove(catePanel);
				add(catePanel = new JPanel());
				User_Chat_List ucl = new User_Chat_List(userID);
				catePanel.add(ucl);
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("����")){
				remove(catePanel);
				add(catePanel = new OptionPanel(userID, this));
				catePanel.setBounds(0, 90, 500, 670);
				catePanel.repaint();
				revalidate();
				repaint();
			}
		}else if(userkind==2) {
			if(jb.getText().equals("��ȭ")) {
				remove(catePanel);
				add(catePanel = new EditerFrame(userID));
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}else if(jb.getText().equals("ȸ������")){
				remove(catePanel);
				
				add(catePanel = new ResSuperuser(userID,this));
				catePanel.setBounds(0, 90, 500, 670);
				catePanel.setVisible(true);
				revalidate();
				repaint();
			}else if(jb.getText().equals("����")){
				remove(catePanel);
				add(catePanel = new JumTalkOptionManager(userID));
				catePanel.setBounds(0, 90, 500, 670);
				revalidate();
				repaint();
			}
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		try {
			ProfileInOut.getprofileInout().ois.close();
			ProfileInOut.getprofileInout().oos.close();
		} catch (IOException e1) {
			// TODO �ڵ� ������ catch ���
			e1.printStackTrace();
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO �ڵ� ������ �޼ҵ� ����
		
	}
	   
}
	

		