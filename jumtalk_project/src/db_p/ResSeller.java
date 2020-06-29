package db_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Savepoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;



public class ResSeller extends JPanel {   // 나중에 JPanel로 바꿀거고 마지막에 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 이거지워주면됌

   String userID;
   int x=600;
   int y=200; // 좌표 여분 절대값
   ProfileInOut pfio = ProfileInOut.getprofileInout();
   public ResSeller(String userID) {
      this.userID = userID;
      
      
      setLayout(null);
      setBounds(x, y, 500, 670);
      ImageIcon ii = new ImageIcon(pfio.download(userID));
      Image img = ii.getImage();
      img =img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
      ii = new ImageIcon(img);

      JLabel jl1= new JLabel(ii);
      jl1.setBounds(10, 70, 200, 200);
      add(jl1);
      JLabel jl2= new JLabel("이름 : "+UserDB.getNAME(userID));
      jl2.setBounds(280, 70, 200, 60);
      add(jl2);
      JLabel jl3= new JLabel("전화번호 : "+UserDB.getPHONE(userID));
      jl3.setBounds(280, 140, 200, 60);
      add(jl3);
      setVisible(true);
      JLabel jl5= new JLabel("평점 : "+ReviewDB.getAVGPOINT(userID));
      jl5.setBounds(280, 200, 200, 60);
      add(jl5);
      JLabel jl4= new JLabel("프로필 메세지");
      jl4.setBounds(280, 230, 200, 60);
      add(jl4);
      
      JTextArea jt1 = new JTextArea(UserDB.getPROFILE_TEXT(userID));
      jt1.setBounds(230, 280, 200, 100);
      add(jt1);
      repaint();
      Selectpanel jp1= new Selectpanel(userID);
      
      add(jp1);
      setVisible(true);
      
   }
         

}
class Selectpanel extends JPanel implements ActionListener{
	String userID;
	
	public Selectpanel(String userID) {
		 this.userID = userID;
		 setBounds(5, 390, 475, 230);
	     
	 
	     setLayout(new GridLayout(3,1,10,10));
	     String[] arr = {"예약관리","예약메뉴표관리","본인리뷰보기"};
	     for (int i = 0; i < arr.length; i++) {
	    	 JButton jb ;
			add(jb = new JButton(arr[i]));
			jb.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		if(jb.getText().equals("예약관리")){
			new SelectMenuAct(userID, userID, 700, 200, 0);
		}else if(jb.getText().equals("예약메뉴표관리")) {
			new DetailFrame2(userID, userID, 700, 200);
		}else if(jb.getText().equals("본인리뷰보기")) {
			new SellReviewAct(userID, userID, 700, 200);
		}
		
	}
}




class SellerListPane2 extends JPanel implements ActionListener{
   String userID;
   String sellerID;
   int x;
   int y;
   
   public SellerListPane2(String userID, String sellerID, int x, int y) { // 로그인한일반유저아이디, 전체점술가아이디중 이 패널의주인
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      
      setLayout(new GridLayout(2,0));
      
      JLabel lblSellerId = new JLabel(sellerID);
      lblSellerId.setOpaque(true);
      lblSellerId.setBounds(0, 0, 100, 50);
      JButton btnDetail = new JButton("상세프로필");
      btnDetail.addActionListener(this);
      
      add(lblSellerId);
      add(btnDetail);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) { // 상세프로필
      
      new DetailFrame2(userID, sellerID, x, y);
   }
}


class DetailFrame2 extends JFrame implements ActionListener{ // 상세프로필
   String userID;
   String sellerID;
   int x;
   int y;
   ProfileInOut pfio;
   
   JButton btnFace;
   JButton btnSaju;
   JButton btnLove;
   JButton btnNewYear;
   JButton btnCompany;
   String[] arr;
   public DetailFrame2(String userID, String sellerID, int x, int y) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      this.pfio = ProfileInOut.getprofileInout();
      
      setBounds(x+100, y, 500, 670);
      
      JPanel jp = new JPanel();
      jp.setLayout(null);
      JPanel jp2 = new JPanel(new GridLayout(5,0));
      
      
      
     
      
     
      arr = MenuDB.getMENU(sellerID);
      btnFace = new JButton(arr[0].split(" ")[0]+arr[0].split(" ")[1]);
      btnFace.addActionListener(this);
      btnSaju = new JButton(arr[1].split(" ")[0]+arr[1].split(" ")[1]);
      btnSaju.addActionListener(this);
      btnLove = new JButton(arr[2].split(" ")[0]+arr[2].split(" ")[1]);
      btnLove.addActionListener(this);
      btnNewYear = new JButton(arr[3].split(" ")[0]+arr[3].split(" ")[1]);
      btnNewYear.addActionListener(this);
      btnCompany = new JButton(arr[4].split(" ")[0]+arr[4].split(" ")[1]);
      btnCompany.addActionListener(this);
            
      
      jp2.add(btnFace);
      jp2.add(btnSaju);
      jp2.add(btnLove);
      jp2.add(btnNewYear);
      jp2.add(btnCompany);
      
     
      add(jp2);
      
      setVisible(true);
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e) { 
     
      
      if(e.getSource().equals(btnFace)) 
    	 new Sellmenuset(userID, arr[0].split(" ")[0], arr[0].split(" ")[1], 0,this);
      else if(e.getSource().equals(btnSaju))
    	 new Sellmenuset(userID, arr[1].split(" ")[0], arr[1].split(" ")[1], 1,this);
      else if(e.getSource().equals(btnLove))
    	 new Sellmenuset(userID, arr[2].split(" ")[0], arr[2].split(" ")[1], 2,this);
      else if(e.getSource().equals(btnNewYear))
    	 new Sellmenuset(userID, arr[3].split(" ")[0], arr[3].split(" ")[1], 3,this);
      else if(e.getSource().equals(btnCompany))
         new Sellmenuset(userID, arr[4].split(" ")[0], arr[4].split(" ")[1], 4,this);
      
      
   }
}

class Sellmenuset extends JFrame implements ActionListener{
	String userID ;
	String manu;
	int price;
	int kind;
	JTextField jf1;
	JTextField jf2;
	JFrame jf;
	public Sellmenuset(String userID, String manu, String price, int kind,JFrame jf) {
		super();
		this.userID = userID;
		this.manu = manu;
		this.jf = jf;
		this.price = Integer.parseInt(price);
		this.kind = kind;
		setBounds(800, 200, 300, 400);
		setLayout(new GridLayout(5,1,10,10));
		JLabel jl1 = new JLabel("메뉴명");
		jf1= new JTextField(manu);
		JLabel jl2 = new JLabel("금액");
		jf2= new JTextField(price+"");
		JButton jb1 = new JButton("저장");
		jb1.addActionListener(this);
		add(jl1);
		add(jf1);
		add(jl2);
		add(jf2);
		add(jb1);
		
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuDB.setMENU(userID, kind+1, jf1.getText()+" "+jf2.getText());
		JOptionPane.showMessageDialog(null, "저장완료");
		setVisible(false);
		jf.setVisible(false);
	}
	
}


class SellReviewAct2 extends JFrame implements ActionListener{
   String userID;
   String sellerID;
   int x;
   int y;
   
   public SellReviewAct2(String userID, String sellerID, int x, int y) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      setLayout(null);
      setBounds(x+200, y, 300, 400);
      
      JPanel showReviewP = new JPanel();
      showReviewP.setLayout(null);
      showReviewP.setBounds(0, 0, 800, 600);
      JPanel showReviewP2 = new JPanel();
      showReviewP2.setLayout(new GridLayout(ReviewDB.getREVIEW(sellerID).size(),1));
      
      ArrayList<String> nomComent = new ArrayList<String>(); 
      ArrayList<Integer> nomPoint = new ArrayList<Integer>();
      
      for (Review rv : ReviewDB.getREVIEW(sellerID)) {
         nomComent.add(rv.coment);
         nomPoint.add(rv.point);
      }
      
      String str = "";
      int j = 0;
      for (String comm : nomComent) {
         str +=  comm + "\n평점 : ["
               + nomPoint.get(j) + "] " + "\n\n";
         j++;
      }
      
      JTextArea t1 = new JTextArea(str);
      t1.setBounds(0, 0, 780, 560);
      t1.setLineWrap(true);
      t1.setFont(new Font("고딕", Font.BOLD, 15));
      t1.setEditable(false);
      
      JScrollPane scp = new JScrollPane(t1);      
      scp.setBounds(0, 0, 800, 600);
      scp.setVisible(true);

      showReviewP.add(t1);
      add(showReviewP);
      setVisible(true);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }
}

class SelectMenuAct extends JFrame implements ActionListener{ // 점술메뉴를 고른 후
	   String userID;
	   String sellerID;
	   int x;
	   int y;
	   int menuChoice;
	   SelectTime st;
	   SelectMenuAct me;
	   
	   public SelectMenuAct(String userID, String sellerID, int x, int y, int menuChoice) {
	      this.userID = userID;
	      this.sellerID = sellerID;
	      this.x = x;
	      this.y = y;
	      this.menuChoice = menuChoice;
	      me = this;
	      setLayout(null);
	      setBounds(x+300, y, 500, 835);
	      
	      add(new SelectDate(userID, sellerID, x, y, menuChoice));
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	      add(st = new SelectTime(userID, sellerID, x, y, menuChoice,sdf.format(new Date())));
	      setVisible(true);
	   }
	   
	   
	   class SelectDate extends JPanel implements ActionListener{ // 날짜선택
	      
	      String userID;
	      String sellerID;
	      int x;
	      int y;
	      int menuChoice;
	      String resDaySeller;
	      Date selectDay;
	      JComboBox<Integer> dateListSeller;
	      JButton btnSave;
	      
	      public SelectDate(String userID, String sellerID, int x, int y, int menuChoice) {
	         this.userID = userID;
	         this.sellerID = sellerID;
	         this.x = x;
	         this.y = y;
	         this.menuChoice = menuChoice;
	         
	         setBounds(0, 0, 500, 50);
	         setLayout(null);
	         
	         
	         Calendar todayC = Calendar.getInstance();
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	         String day = sdf.format(todayC.getTime()).substring(8, 10); // 오늘 날짜 01~31

	         
	         JLabel lbl_year = new JLabel(todayC.get(Calendar.YEAR)+"년");
	         lbl_year.setBounds(30, 20, 70, 20);
	         JLabel lbl_month = new JLabel(((todayC.get(Calendar.MONTH))+1)+"월");   
	         lbl_month.setBounds(90, 20, 30, 20);
	         
	         Vector<Integer> dayList = new Vector<Integer>();
	         for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
	            dayList.add(i);
	         }
	         dateListSeller = new JComboBox<Integer>(dayList);
	         dateListSeller.addActionListener(this);
	         dateListSeller.setBounds(140, 20, 80, 20);

	         JLabel lbl_date = new JLabel(" 일");
	         lbl_date.setBounds(220, 20, 20, 20);

	         int rdaySeller = (int) dateListSeller.getSelectedItem(); // 점술가가 누른 예약날짜
	         resDaySeller = rdaySeller + ""; // string 캐스팅
	         
	         String aa = sdf.format(todayC.getTime());
	         
	         
	         btnSave = new JButton("저장하기");
	         btnSave.setBounds(340, 20, 100, 20);
	         btnSave.addActionListener(this);
	         
	         add(lbl_year);
	         add(lbl_month);
	         add(dateListSeller);
	         add(lbl_date);
	         add(btnSave);
	      }

	      @Override
	      public void actionPerformed(ActionEvent e) {
	         String ss="";
	         if(e.getSource().equals(dateListSeller)) {
	            JComboBox<Integer> jc = (JComboBox<Integer>) e.getSource();
	            int a = (int) jc.getSelectedItem();
	            me.remove(st);
	            Date d1 = new Date();
	            d1.setDate(a);
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            ss = sdf.format(d1);
	            me.add(st = new SelectTime(userID, sellerID, x, y, menuChoice,sdf.format(d1)));
	            me.revalidate();
	            me.repaint();
	         }else if(e.getSource().equals(btnSave)) {
	        	 st.save();
	        	
	         }
	         
	         if(e.getSource().equals(btnSave)) {
//	            ScheduleDB.setSchedule(ss, date, time, set)
	         }
	      }
	   }
	   
	   
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
	      // TODO Auto-generated method stub
	      
	   }
	}


	class SelectTime extends JPanel{ // 시간선택
	   
	   String userID;
	   String sellerID;
	   int x;
	   int y;
	   int menuChoice;
	   ArrayList<JToggleButton> btnTimeSeller;
	   boolean chk=true;
	   String [] schedule;
	   String date;
	   public SelectTime(String userID, String sellerID, int x, int y, int menuChoice,String date) {
	      this.userID = userID;
	      this.sellerID = sellerID;
	      this.x = x;
	      this.y = y;
	      this.menuChoice = menuChoice;
	      this.date = date;
	      
	      setBounds(0, 50, 500, 750);
	      setLayout(new GridLayout(24,0));
	      
	      schedule = null;
	      if(ScheduleDB.getScheduleDB(sellerID, date)[0]==null) {
	         ScheduleDB.makeSchedule(userID, date);
	         System.out.println("null");
	         schedule = ScheduleDB.getScheduleDB(sellerID, date);
	      }else {
	         schedule = ScheduleDB.getScheduleDB(sellerID, date);
	         System.out.println("nullno");
	      }
	      
	      
	      btnTimeSeller = new ArrayList<JToggleButton>();
	      
	      for (int i = 0; i < 24; i++) {
	         if(chk) {
	         JToggleButton bb;
	         if(i<10)
	            bb = new JToggleButton("0"+ i+" ~ "+"0"+(i+1)+"시");   // 나중에 subString(0,2); 해서올려주면되려나
	         else
	            bb = new JToggleButton(i+" ~ "+(i+1)+"시");
	         if((schedule[i].equals("true"))) {
	        	 bb.setSelected(true);
	         }else if((schedule[i].equals("false"))){
	        	 bb.setSelected(false);
	         }else {
	        	 bb.setEnabled(false);
	        	 bb.setText(schedule[i]+"예약완료");
	         }
	         btnTimeSeller.add(bb);
	         add(btnTimeSeller.get(i));
	         }
	      }
	      // 챗리스트, 예약처리
	      
	   }
	   
	   void save() {
		   for (JToggleButton jT : btnTimeSeller) {
			   if(!jT.isEnabled()) {
				   
			   }else if(jT.isSelected()) {
				   ScheduleDB.setSchedule(userID, date, "time"+btnTimeSeller.indexOf(jT), "true");
			   }else {
				   ScheduleDB.setSchedule(userID, date, "time"+btnTimeSeller.indexOf(jT), "false");
			   }
		   }
	   }
	}



