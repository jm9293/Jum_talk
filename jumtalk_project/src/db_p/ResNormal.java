package db_p;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class ResNormal extends JPanel { // ���߿� JPanel�� �ٲܰŰ� �������� setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); �̰������ָ��

   String userID;
   int x = 600;
   int y = 200; // ��ǥ ���� ���밪

   public ResNormal(String userID) {
      this.userID = userID;

      setLayout(null);
      setBounds(x, y, 500, 670);
//         setResizable(false);
      JPanel jps = new JPanel();
      jps.setBounds(0, 0, 500, 670);
      jps.setLayout(null);
      JPanel jp = new JPanel();

      int row = UserDB.getSELLER().size();
      if (row < 5) {
         row = 5;
      }
      jp.setLayout(new GridLayout(row, 1));
      for (sellUser ss : UserDB.getSELLER()) {
         jp.add(new SellerListPane(userID, ss.id, x, y)); // �α������Ϲ��������̵�, ��ü���������̵�
      }
      for (int i = UserDB.getSELLER().size(); i < 5; i++) {
         jp.add(new JPanel());
      }

      Dimension size = new Dimension();
      size.setSize(400, (row * 100) + 50);
      jp.setPreferredSize(size);
      JScrollPane scp = new JScrollPane(jp);

      scp.setBounds(0, 0, 485, 630);
      jps.add(scp);
      scp.setVisible(true);
      add(jps);
      setVisible(true);
  
   }

   public static void main(String[] args) {
      new ResNormal("�α��ε� ���̵�?"); // ���߿� �α��δܿ��� �Ѱܹ��� ���̵�
   }

}

class SellerListPane extends JPanel implements ActionListener {
   String userID;
   String sellerID;
   int x;
   int y;

   public SellerListPane(String userID, String sellerID, int x, int y) { // �α������Ϲ��������̵�, ��ü���������̵��� �� �г�������
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;

      setLayout(new GridLayout(2, 0));

      JLabel lblSellerId = new JLabel(sellerID);
      lblSellerId.setOpaque(true);
      lblSellerId.setBounds(0, 0, 100, 50);
      JButton btnDetail = new JButton("��������");
      btnDetail.addActionListener(this);

      add(lblSellerId);
      add(btnDetail);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) { // ��������

      new DetailFrame(userID, sellerID, x, y);
   }
}

class DetailFrame extends JFrame implements ActionListener { // ��������
   String userID;
   String sellerID;
   int x;
   int y;
   ProfileInOut pfio;
   JButton btnReview;
   JButton btnFace;
   JButton btnSaju;
   JButton btnLove;
   JButton btnNewYear;
   JButton btnCompany;
   String[] getMenu;

   public DetailFrame(String userID, String sellerID, int x, int y) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      this.pfio = ProfileInOut.getprofileInout();
      setLayout(new GridLayout(2, 1));
      setBounds(x + 100, y, 500, 670);

      JPanel jp = new JPanel();
      jp.setLayout(null);
      JPanel jp2 = new JPanel(new GridLayout(6, 0));

      ImageIcon imgIcon = new ImageIcon(pfio.download(sellerID));
      Image img = imgIcon.getImage();
      img = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
      imgIcon.setImage(img);

      JLabel lblImg = new JLabel();
      lblImg.setIcon(imgIcon);
      lblImg.setBounds(80, 80, 100, 100);
      JLabel lblDetail = new JLabel("[�󼼼Ұ�] : " + UserDB.getPROFILE_TEXT(sellerID));
      lblDetail.setBounds(250, 80, 200, 20);
      JLabel lblPhone = new JLabel("[��ȭ��ȣ] : " + UserDB.getPHONE(sellerID));
      lblPhone.setBounds(250, 130, 200, 20);
      JLabel lblPoint = new JLabel("[����] : " + ReviewDB.getAVGPOINT(sellerID));
      lblPoint.setBounds(250, 180, 200, 20);

      jp.add(lblImg);
      jp.add(lblDetail);
      jp.add(lblPhone);
      jp.add(lblPoint);
      
      btnReview = new JButton("���亸��");
      btnReview.addActionListener(this);
      getMenu = MenuDB.getMENU(sellerID);
      btnFace = new JButton(getMenu[0]);
      btnFace.addActionListener(this);
      btnSaju = new JButton(getMenu[1]);
      btnSaju.addActionListener(this);
      btnLove = new JButton(getMenu[2]);
      btnLove.addActionListener(this);
      btnNewYear = new JButton(getMenu[3]);
      btnNewYear.addActionListener(this);
      btnCompany = new JButton(getMenu[4]);
      btnCompany.addActionListener(this);

      jp2.add(btnReview);
      jp2.add(btnFace);
      jp2.add(btnSaju);
      jp2.add(btnLove);
      jp2.add(btnNewYear);
      jp2.add(btnCompany);

      add(jp);
      add(jp2);

      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(btnReview))
         new SellReviewAct(userID, sellerID, x, y);

      if (e.getSource().equals(btnFace))
         new SelectMenuAct2(userID, sellerID, x, y, 0, getMenu);
      else if (e.getSource().equals(btnSaju))
         new SelectMenuAct2(userID, sellerID, x, y, 1, getMenu);
      else if (e.getSource().equals(btnLove))
         new SelectMenuAct2(userID, sellerID, x, y, 2, getMenu);
      else if (e.getSource().equals(btnNewYear))
         new SelectMenuAct2(userID, sellerID, x, y, 3, getMenu);
      else if (e.getSource().equals(btnCompany))
         new SelectMenuAct2(userID, sellerID, x, y, 4, getMenu);

   }
}

class SellReviewAct extends JFrame {
   String userID;
   String sellerID;
   int x;
   int y;

   public SellReviewAct(String userID, String sellerID, int x, int y) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      setLayout(null);
      setBounds(x + 200, y, 800, 600);

      JPanel showReviewP = new JPanel();
      showReviewP.setLayout(null);
      showReviewP.setBounds(0, 0, 800, 600);
      JPanel showReviewP2 = new JPanel();
      showReviewP2.setLayout(new GridLayout(ReviewDB.getREVIEW(sellerID).size(), 1));

      ArrayList<String> nomComent = new ArrayList<String>();
      ArrayList<Integer> nomPoint = new ArrayList<Integer>();

      for (Review rv : ReviewDB.getREVIEW(sellerID)) {
         nomComent.add(rv.coment);
         nomPoint.add(rv.point);
      }

      String str = "";
      int j = 0;
      for (String comm : nomComent) {
         str += comm + "\n���� : [" + nomPoint.get(j) + "] " + "\n\n";
         j++;
      }

      JTextArea t1 = new JTextArea(str);
      t1.setBounds(0, 0, 780, 560);
      t1.setLineWrap(true);
      t1.setFont(new Font("���", Font.BOLD, 15));
      t1.setEditable(false);

      JScrollPane scp = new JScrollPane(t1);
      scp.setBounds(0, 0, 800, 600);
      scp.setVisible(true);

      showReviewP.add(t1);
      add(showReviewP);
      setVisible(true);
   }

}

class SelectMenuAct2 extends JFrame implements ActionListener { // �����޴��� �� ��
   String userID;
   String sellerID;
   int x;
   int y;
   int menuChoice;
   SelectTime st;
   SelectMenuAct2 me;
   ArrayList<JToggleButton> btnTimeSeller;
   String[] getMenu;

   public SelectMenuAct2(String userID, String sellerID, int x, int y, int menuChoice, String[] getMenu) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      this.menuChoice = menuChoice;
      this.getMenu = getMenu;
      me = this;

      setLayout(null);
      setBounds(x + 300, y, 500, 835);

      add(new SelectDate(userID, sellerID, x, y));
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      add(st = new SelectTime(userID, sellerID, x, y, menuChoice, sdf.format(new Date())));

      setVisible(true);
   }

   class SelectDate extends JPanel implements ActionListener { // ��¥����

      String userID;
      String sellerID;
      int x;
      int y;
      String resDaySeller;
      Date selectDay;
      JComboBox<Integer> dateListSeller;
      JButton btnSave;
      String tempDate;

      public SelectDate(String userID, String sellerID, int x, int y) {
         this.userID = userID;
         this.sellerID = sellerID;
         this.x = x;
         this.y = y;

         setBounds(0, 0, 500, 50);
         setLayout(null);

         Calendar todayC = Calendar.getInstance();
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         String day = sdf.format(todayC.getTime()).substring(8, 10); // ���� ��¥ 01~31
         tempDate = sdf.format(new Date());

         JLabel lbl_year = new JLabel(todayC.get(Calendar.YEAR) + "��");
         lbl_year.setBounds(30, 20, 70, 20);
         JLabel lbl_month = new JLabel(((todayC.get(Calendar.MONTH)) + 1) + "��");
         lbl_month.setBounds(90, 20, 30, 20);

         Vector<Integer> dayList = new Vector<Integer>();
         for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
            dayList.add(i);
         }
         dateListSeller = new JComboBox<Integer>(dayList);
         dateListSeller.addActionListener(this);
         dateListSeller.setBounds(140, 20, 80, 20);

         JLabel lbl_date = new JLabel(" ��");
         lbl_date.setBounds(220, 20, 20, 20);

         int rdaySeller = (int) dateListSeller.getSelectedItem(); // �Ϲݻ���ڰ� ���� ���೯¥
         resDaySeller = rdaySeller + ""; // string ĳ����

         String aa = sdf.format(todayC.getTime());

         btnSave = new JButton("�����ϱ�");
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

         if (e.getSource().equals(dateListSeller)) {
            JComboBox<Integer> jc = (JComboBox<Integer>) e.getSource();
            int a = (int) jc.getSelectedItem();
            me.remove(st);
            Date d1 = new Date();
            d1.setDate(a);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tempDate = sdf.format(d1);
            me.add(st = new SelectTime(userID, sellerID, x, y, menuChoice, sdf.format(d1)));
            me.revalidate();
            me.repaint();
         } else if (e.getSource().equals(btnSave)) { // �����ϱ� ��ư
            new ChkFrame(userID, sellerID, x, y, btnTimeSeller, tempDate, getMenu, menuChoice, resDaySeller);

         }
      }
   }

   class SelectTime extends JPanel { // �ð�����

      String userID;
      String sellerID;
      int x;
      int y;
      int menuChoice;
      boolean chk = true;

      public SelectTime(String userID, String sellerID, int x, int y, int menuChoice, String date) {
         this.userID = userID;
         this.sellerID = sellerID;
         this.x = x;
         this.y = y;
         this.menuChoice = menuChoice;

         setBounds(0, 50, 500, 750);
         setLayout(new GridLayout(24, 0));

         String[] schedule = null;
         if (ScheduleDB.getScheduleDB(sellerID, date)[0] == null) {
            chk = false;
         } else {
            schedule = ScheduleDB.getScheduleDB(sellerID, date);
         }

         btnTimeSeller = new ArrayList<JToggleButton>();
         ButtonGroup bg = new ButtonGroup();

         for (int i = 0; i < 24; i++) {
            if (chk) {
               JToggleButton bb;
               if (i < 9)
                  bb = new JToggleButton("0" + i + " ~ " + "0" + (i + 1) + "��"); // ���߿� subString(0,2); �ؼ��÷��ָ�Ƿ���
               else
                  bb = new JToggleButton(i + " ~ " + (i + 1) + "��");

               SimpleDateFormat sdf = new SimpleDateFormat("H");
               SimpleDateFormat sdf2 = new SimpleDateFormat("d");
               String a = sdf.format(new Date());
               if (schedule[i].equals("false") || date.split("-")[2].equals(sdf2.format(new Date())) && (i <= Integer.parseInt(a))) {
                  bb.setEnabled(false);
                  bb.setText("����Ұ�");
               } else if (schedule[i].equals(userID)) {
                  bb.setEnabled(false);
                  bb.setText("���� ����ð�" + " (" + MenuDB.getMENU(sellerID)[menuChoice] + ")");
               }

               bg.add(bb);
               btnTimeSeller.add(bb);
               add(btnTimeSeller.get(i));
            } else {
               JToggleButton bb;
               bb = new JToggleButton("����Ұ�");

               bb.setEnabled(false);
               btnTimeSeller.add(bb);
               add(btnTimeSeller.get(i));
            }
         }
         // ê����Ʈ

      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub

   }
}

class ChkFrame extends JFrame implements ActionListener {

   String userID;
   String sellerID;
   int x;
   int y;
   String tempDate;
   ArrayList<JToggleButton> btnTimeSeller;
   JLabel lbl;
   JLabel lbl2;
   JButton btnChkY;
   JButton btnChkN;
   int chkTog;
   String[] getMenu;
   int menuChoice;
   String resDaySeller; // ���೯¥

   public ChkFrame(String userID, String sellerID, int x, int y, ArrayList<JToggleButton> btnTimeSeller,
         String tempDate, String[] getMenu, int menuChoice, String resDaySeller) {
      this.userID = userID;
      this.sellerID = sellerID;
      this.x = x;
      this.y = y;
      this.tempDate = tempDate;
      this.btnTimeSeller = btnTimeSeller;
      this.getMenu = getMenu;
      this.menuChoice = menuChoice;
      this.resDaySeller = resDaySeller;

      setLayout(null);
      setBounds(x + 400, y, 400, 200);
      chkTog = 0;
      for (int i = 0; i < btnTimeSeller.size(); i++) {
         if (btnTimeSeller.get(i).isSelected())
            chkTog = i;
      }
      System.out.println(Arrays.deepToString(getMenu));
      lbl = new JLabel("[" + tempDate + "]��     [" + chkTog + "]��     [" + getMenu[menuChoice].split(" ")[0]
            + "]     �����Ͻðڽ��ϱ�");
      lbl.setBounds(30, 30, 350, 30);
      lbl2 = new JLabel("[" + getMenu[menuChoice].split(" ")[1] + "] ���� �����˴ϴ�");
      lbl2.setBounds(130, 70, 350, 30);
      btnChkY = new JButton("��");
      btnChkY.setBounds(100, 120, 50, 30);
      btnChkY.addActionListener(this);
      btnChkN = new JButton("�ƴϿ�");
      btnChkN.setBounds(200, 120, 80, 30);
      btnChkN.addActionListener(this);

      add(lbl);
      add(lbl2);
      add(btnChkY);
      add(btnChkN);

      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if (e.getSource().equals(btnChkY)) {

         if (UserDB.getCOIN(userID) < (Integer.parseInt(getMenu[menuChoice].split(" ")[1]))) {
            JOptionPane.showMessageDialog(null, "������ �����մϴ�");
            setVisible(false);
         } 
         else {

            String str = "2020-06-" + resDaySeller + " " + chkTog + ":00:00";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
               // ä�ø���Ʈ ����
               ChatListDB.saveCHATLIST(sellerID, userID, sdf.parse(str), getMenu[menuChoice].split(" ")[0]);
            } catch (ParseException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            
            // ���� ��������
            UserDB.setCOIN(userID,
                  (UserDB.getCOIN(userID) - (Integer.parseInt(getMenu[menuChoice].split(" ")[1]))));
            // ������ ��������
            UserDB.setCOIN(sellerID,
                  (UserDB.getCOIN(sellerID) + (Integer.parseInt(getMenu[menuChoice].split(" ")[1]))));
            // ���� DB����
            System.out.println("time" + chkTog + "    " + tempDate);
            ScheduleDB.setSchedule(sellerID, tempDate, "time" + chkTog, userID);
            JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�");
            setVisible(false);
         }
         
      } 
      else {
         setVisible(false);
      }
      
   }
}