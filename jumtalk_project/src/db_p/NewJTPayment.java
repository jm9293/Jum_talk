package db_p;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;



class BuyerFrame extends JPanel implements ActionListener {

   String userID;
   FortunePanel fortunepanel;
   ChargePanel chargepanel;
   JLabel haveCoin;

   public BuyerFrame(String userID) {
      this.userID =  userID;
      
      setLayout(null);
      // ��ư ���� �� ��ư �׷� ����� - �г� ����ġ�� ��ư
      ArrayList<JButton> ckjbt = new ArrayList<JButton>();
      String[] CKBT = { "������ ��Ű", "���� ����" };
      int[][] CKBTBounds = { { 20, 40, 120, 40 }, { 140, 40, 120, 40 } };
      ButtonGroup BTG = new ButtonGroup();
      for (int i = 0; i < CKBT.length; i++) {
         ckjbt.add(new JButton(CKBT[i]));
         ckjbt.get(i).setBounds(CKBTBounds[i][0], CKBTBounds[i][1], CKBTBounds[i][2], CKBTBounds[i][3]);
         add(ckjbt.get(i));
         ckjbt.get(i).addActionListener(this);
         BTG.add(ckjbt.get(i));
      }

      // ���� �ִ� ������ ���� ��
      haveCoin = new JLabel();
      haveCoin.setBounds(320, 40, 200, 40);
      haveCoin.setText("���� ���� : " + UserDB.getCOIN(userID) + "��");
      add(haveCoin);

      // �ΰ��� �г� (�̳�Ŭ����) �߰� //
      add(fortunepanel = new FortunePanel());
      add(chargepanel = new ChargePanel());

      setVisible(true);
      
   }

   // BuyerFrame ActionEvent
   @Override
   public void actionPerformed(ActionEvent e) {
      JButton Bt = (JButton) e.getSource();
      if (Bt.getText().equals("������ ��Ű")) {
         fortunepanel.setVisible(true);
         chargepanel.setVisible(false);
      } else {
         fortunepanel.setVisible(false);
         chargepanel.setVisible(true);
      }
   }

   /// ������ ���� ��Ű �г� - ���� 1�� �г�
   class FortunePanel extends JPanel implements ActionListener {

      JTextField clickResult;
      BuyerFrame buyerFrame;
      int bonus = (int) (Math.random() * 5 + 1);
      JButton CKClickBT;
      ImageIcon beforeCK = new ImageIcon("icon\\aaa.png");
      ImageIcon afterCK = new ImageIcon("icon\\bbb.png");
      String time;

      // �� �ð� ��Ÿ���� �޼ҵ� - ������ ��Ű Ŭ�� �� �ʿ� //
      String times() {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
         time = simple.format(cal.getTime());

         return time;
      }

      public FortunePanel() {
         setBounds(0, 120, 480, 500);
         setLayout(null);

         // ������ ��Ű ��ư
         CKClickBT = new JButton(beforeCK);
         CKClickBT.setBounds(20, 20, 250, 250);
         CKClickBT.addActionListener(this);
         add(CKClickBT);

         // ������ �� ��
         JLabel res = new JLabel("������ �� ��");
         res.setBounds(20, 350, 100, 50);
         add(res);
         clickResult = new JTextField();
         clickResult.setLayout(null);
         clickResult.setBounds(20, 400, 450, 30);
         add(clickResult);
         clickResult.disable();

         // �Ϸ� �ѹ��� ���� �� �ְ� - ���� �ð��� �� ���� ��� ��ư ��Ȱ��ȭ
         if (UserDB.getFORTUNE_TIME(userID).equals(times())) {
            CKClickBT.setEnabled(false);
            CKClickBT.setIcon(afterCK);
         }
         setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {

         // ������ ��Ű - �ݾ� ���� DB���� �޾ƿ���
         clickResult.setText(TodayFortuneDB.getFORTUNEMESSAGE((int) (Math.random() * 10 + 1)));
         // Ŭ�� �� ��ư �̹��� �ٲٱ�
         CKClickBT.setIcon(afterCK);
         // Ŭ�� �� ���� ���� ������Ʈ -> �� + DB
         haveCoin.setText("���� ���� : " + (UserDB.getCOIN(userID) + bonus) + "��");
         UserDB.setCOIN(userID, UserDB.getCOIN(userID) + bonus);
         // ��Ű ��ư Ŭ�� �� ��Ȱ��ȭ
         CKClickBT.setEnabled(false);
         // ��ư Ŭ���� �ð� DB�� ������//
         UserDB.setFORTUNE_TIME(userID, times());
         // ���ʽ� ���� DB�� ������ - ������ �����ϱ� ���� + ������ �ջ��� ���� ���ʽ� ��Ű ���� �з�//
         MoneyLogDB.saveMONEYLOG(userID, bonus * 100, bonus, 2);
         // Ŭ�� �� ���ʽ��� ���� ���� �ð������� Ȯ�ν�Ű��
         JOptionPane.showMessageDialog(this, "���� " + bonus + "���� �߰��� ������ϴ�.");
      }

   }

   // ���� ���� �г� - ���� 2�� �г�
   class ChargePanel extends JPanel implements ActionListener {

      JTable MtoCList;
      JScrollPane MtoCPan;
      ArrayList<JRadioButton> res = new ArrayList<JRadioButton>();

      public ChargePanel() {
         setBounds(0, 120, 490, 510);
         setLayout(null);
         ButtonGroup bg = new ButtonGroup();

         // ���� ���� ���� ��ư ���� //
         String[] label = { "10,000 ��      ��    100 ����", "20,000 ��      ��    200 ����", "30,000 ��      ��    300 ����",
               "50,000 ��      ��    500 ����", "100,000 ��    ��    1000 ����" };
         int[][] loken = { { 30, 10, 250, 30 }, { 30, 50, 250, 30 }, { 30, 90, 250, 30 }, { 30, 130, 250, 30 },
               { 30, 170, 250, 30 } };
         for (int i = 0; i < label.length; i++) {
            res.add(new JRadioButton(label[i]));
            res.get(i).setBounds(loken[i][0], loken[i][1], loken[i][2], loken[i][3]);
            add(res.get(i));
            bg.add(res.get(i));
         }

         // ���� ��û ���� ����Ʈ - ������ ���� ��
         String[][] data = MoneyLogDB.getMONEYLOG(userID, 0);
         String[] ListInfo = { "���� �ݾ�", "������ ���� ����", "�ð�" };
         if (data == null) {
            String[][] nodata = new String[1][3];
            MtoCList = new JTable(nodata, ListInfo);
            MtoCPan = new JScrollPane(MtoCList);
            MtoCPan.setBounds(15, 250, 450, 150);
            add(MtoCPan);
         } else {
            MtoCList = new JTable(data, ListInfo);
            MtoCPan = new JScrollPane(MtoCList);
            MtoCPan.setBounds(15, 250, 450, 150);
            add(MtoCPan);
         }

         // ���� �Ϸ� ��ư //
         JButton finBt = new JButton("���� �Ϸ�");
         finBt.setBounds(350, 440, 120, 50);
         finBt.addActionListener(this);
         add(finBt);

         setVisible(false);
      }

      // �ٸ� �г� ���� ���� �� ���ΰ�ħ ���� ������Ʈ�� ���� �޼ҵ�
      void refresh() {
         haveCoin.setText("���� ���� : " + (UserDB.getCOIN(userID)) + "��");
      }

      @Override
      public void actionPerformed(ActionEvent e) {

         int[] price = { 100, 200, 300, 500, 1000 };

         for (int i = 0; i < res.size(); i++) {
            if (res.get(i).isSelected()) {
               int coinnum = UserDB.getCOIN(userID) + price[i];
               UserDB.setCOIN(userID, coinnum);
               MoneyLogDB.saveMONEYLOG(userID, price[i] * 100, price[i], 0);

               String[][] data = MoneyLogDB.getMONEYLOG(userID, 0);
               String[] ListInfo = { "���� �ݾ�", "������ ���� ����", "�ð�" };
               chargepanel.remove(MtoCPan);
               MtoCList = new JTable(data, ListInfo);
               MtoCPan = new JScrollPane(MtoCList);
               MtoCPan.setBounds(15, 250, 450, 150);
               chargepanel.add(MtoCPan);

               chargepanel.repaint();

               break;
            }
         }
         refresh();

         JOptionPane.showMessageDialog(this, "�Է��Ͻ� ī���ȣ " + UserDB.getCARDNUMBER(userID) + "�� �����Ǿ����ϴ�.");
      }
   }
}

class SellerFrame extends JPanel implements ActionListener {

   String userID;
   JTable CtoMList;
   JScrollPane CtoMListPan;
   JButton changeCtoMBt;
   JLabel haveCoinNumLB, getMoneyLB;
   int nowMyCoin;

   public SellerFrame(String userID) {
      this.userID =  userID;
      
      setLayout(null);
      // ������ ������ ���� ������ ���� �����ִ� �г� + ��
      JPanel nowHaveCoinPan = new JPanel();
      nowHaveCoinPan.setLayout(null);
      nowHaveCoinPan.setBounds(20, 70, 200, 200);
      nowHaveCoinPan.setBackground(Color.lightGray);
      add(nowHaveCoinPan);
      haveCoinNumLB = new JLabel();
      haveCoinNumLB.setBounds(10, 50, 200, 100);
      
      nowMyCoin = UserDB.getCOIN(userID);
      haveCoinNumLB.setText("ȯ�� ������ ���� ���� : " + nowMyCoin + "��");
      nowHaveCoinPan.add(haveCoinNumLB);

      // �������� �����ִ� ������ ȯ������ �� ���� �� �ִ� �ݾ��� �����ִ� �г� + ��
      JPanel getMoneyPan = new JPanel();
      getMoneyPan.setLayout(null);
      getMoneyPan.setBounds(260, 70, 200, 200);
      getMoneyPan.setBackground(Color.lightGray);
      add(getMoneyPan);
      getMoneyLB = new JLabel();
      getMoneyLB.setBounds(10, 50, 200, 100);
      getMoneyLB.setText("ȯ�� ������ ���� ���� : " + nowMyCoin * 70 + "��");
      getMoneyPan.add(getMoneyLB);

      // ȯ�� ��û ��ư
      changeCtoMBt = new JButton("ȯ�� ��û");
      changeCtoMBt.addActionListener(this);
      changeCtoMBt.setBounds(300, 300, 100, 70);
      add(changeCtoMBt);

      // ȯ�� ��û ����Ʈ
      String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
      if (data == null) {
         String[][] data1 = new String[1][3];
         String[] ListInfo = { "ȯ�� �ݾ�", "ȯ���� ���� ����", "�ð�" };
         CtoMList = new JTable(data1, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);
      } else {
         String[] ListInfo = { "ȯ�� �ݾ�", "ȯ���� ���� ����", "�ð�" };
         CtoMList = new JTable(data, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);
      }

      setVisible(true);
   
   }

   void refresh() {
      haveCoinNumLB.setText("ȯ�� ������ ���� ���� : " + Integer.toString(nowMyCoin) + "��");
      getMoneyLB.setText("��� ������ �ݾ� : " + Integer.toString(nowMyCoin * 70) + "��");
      String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      JButton bt = (JButton) e.getSource();

      if (bt.getText().equals("ȯ�� ��û")) {
         int coinnum = UserDB.getCOIN(userID);
         if (coinnum == 0) {
            JOptionPane.showMessageDialog(this, "������ �����ϴ�.");
            return;
         }
         nowMyCoin = 0;
         haveCoinNumLB.setText("ȯ�� ������ ���� ���� : " + Integer.toString(nowMyCoin) + "��");
         UserDB.setCOIN(userID, nowMyCoin);
         getMoneyLB.setText("��� ������ �ݾ� : " + Integer.toString(nowMyCoin * 70) + "��");

         UserDB.setCOIN(userID, 0);
         MoneyLogDB.saveMONEYLOG(userID, coinnum * 70, coinnum, 1);

         String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
         String[] ListInfo = { "ȯ�� �ݾ�", "ȯ���� ���� ����", "�ð�" };
         remove(CtoMListPan);
         CtoMList = new JTable(data, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);

         revalidate();
         CtoMListPan.repaint();
         JOptionPane.showMessageDialog(this, "�Է��Ͻ� ���¹�ȣ " + UserDB.getBANKNUMBER(userID) + "�� �ԱݵǾ����ϴ�.");

      }
      refresh();
   }
}

class EditerFrame extends JPanel implements ActionListener {

   String userID;
   JTable btc, stm;
   JScrollPane buyerToCoin, sellToMoney;
   JLabel salesIn, netIn;
   int totprice = 0;
   int totbonus = 0;
   String[] ListInfo = { "ID", "���� �ݾ�", "������ ���� ����", "�ð�" };
   String[] ListInfoS = { "ID", "ȯ�� �ݾ�", "ȯ���� ���� ����", "�ð�" };

   public EditerFrame(String userID) {
      this.userID =  userID;
      
      setLayout(null);
      JButton ns = new JButton("���ΰ�ħ");
      DecimalFormat df = new DecimalFormat("###");
      JPanel salesPan = new JPanel();
      JPanel netgainPan = new JPanel();
      ArrayList<JLabel> jlb = new ArrayList<JLabel>();

      // JLabel ����
      String[] labelName = { "COL", "MOL", "sales", "netgain" };
      String[] labelText = { "���� ���� ������ ����Ʈ", "���� ȯ�� ������ ����Ʈ", "�����", "������(����� 30% - ���ʽ� ����)" };
      int[][] labelBounds = { { 30, 15, 220, 50 }, { 30, 220, 420, 50 }, { 30, 425, 200, 50 },
            { 270, 425, 200, 50 } };
      for (int i = 0; i < labelName.length; i++) {
         jlb.add(new JLabel(labelName[i]));
         jlb.get(i).setBounds(labelBounds[i][0], labelBounds[i][1], labelBounds[i][2], labelBounds[i][3]);
         jlb.get(i).setText(labelText[i]);
         add(jlb.get(i));
      }

      // ��� ������ ����Ʈ
      String[][] dataB = MoneyLogDB.getMONEYLOG(0);
      btc = new JTable(dataB, ListInfo);
      buyerToCoin = new JScrollPane(btc);
      buyerToCoin.setBounds(15, 70, 450, 150);
      add(buyerToCoin);
      if (btc == null) {
         btc = new JTable(new String[1][4], ListInfo);
      }

      // ��� ȯ���� ����Ʈ
      String[][] dataS = MoneyLogDB.getMONEYLOG(1);
      stm = new JTable(dataS, ListInfoS);
      sellToMoney = new JScrollPane(stm);
      sellToMoney.setBounds(15, 270, 450, 150);
      add(sellToMoney);
      if (stm == null) {
         stm = new JTable(new String[1][4], ListInfo);
      }

      // ���ΰ�ħ ��ư
      ns.addActionListener(this);
      ns.setBounds(260, 15, 100, 40);
      add(ns);

      // �� ���� �ݾ� -- ��� �Һ��ڵ��� ���� �ݾ�(��) ++
      salesIn = new JLabel();
      salesIn.setBounds(50, 25, 140, 50);
      for (int i = 0; i < dataB.length; i++) {
         try {
            totprice += (long) df.parse(dataB[i][1]);
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      salesIn.setText(totprice + "��");

      // �� ���� �ݾ� �г� - (�ݾ�)
      salesPan.setLayout(null);
      salesPan.add(salesIn);
      salesPan.setBounds(30, 485, 200, 100);
      salesPan.setBackground(Color.LIGHT_GRAY);
      add(salesPan);

      // �� ���ʽ� ���� ���ϱ� - ������ �� �ʿ�
      String[][] dataC = MoneyLogDB.getMONEYLOG(2);
      for (int i = 0; i < dataC.length; i++) {
         try {
            totbonus += (long) df.parse(dataC[i][1]);
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      // �� ������ �ݾ� --> (�� ���� *30%) - (���ʽ� ���� �ݾ�*70%) (��) ++
      netIn = new JLabel();
      netIn.setBounds(50, 25, 140, 50);
      netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " ��");

      // �� ������ �ݾ� �г�
      netgainPan.setLayout(null);
      netgainPan.add(netIn);
      netgainPan.setBounds(260, 485, 200, 100);
      netgainPan.setBackground(Color.LIGHT_GRAY);
      add(netgainPan);

      setVisible(true);
      
   }

   void refresh() {
      salesIn.setText(totprice + "��");
      netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " ��");
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      JButton bt = (JButton) e.getSource();
      String[][] dataB = MoneyLogDB.getMONEYLOG(0);
      String[][] dataS = MoneyLogDB.getMONEYLOG(1);

      // ���ΰ�ħ ��ư ������ ������Ʈ
      if (bt.getText().equals("���ΰ�ħ")) {
         // ���� ���� ���ε� ���� + ����Ʈ
         remove(buyerToCoin);
         btc = new JTable(dataB, ListInfo);
         btc.setLayout(null);
         buyerToCoin = new JScrollPane(btc);
         buyerToCoin.setBounds(15, 70, 450, 150);
         add(buyerToCoin);
         buyerToCoin.repaint();

         // ȯ�� ���� ���ε� ���� + ����Ʈ
         remove(sellToMoney);
         stm = new JTable(dataS, ListInfoS);
         sellToMoney = new JScrollPane(stm);
         totprice = 0;
         DecimalFormat df = new DecimalFormat("###");
         for (int i = 0; i < dataB.length; i++) {
            try {
               totprice += (long) df.parse(dataB[i][1]);
            } catch (ParseException f) {
               // TODO Auto-generated catch block
               f.printStackTrace();
            }
         }

         // ���ʽ��� ���� ���ε� ���� + ����Ʈ
         String[][] dataC = MoneyLogDB.getMONEYLOG(2);
         for (int i = 0; i < dataC.length; i++) {
            try {
               totbonus += (long) df.parse(dataC[i][1]);
            } catch (ParseException f) {
               // TODO Auto-generated catch block
               f.printStackTrace();
            }
         }
         sellToMoney.setBounds(15, 270, 450, 150);
         add(sellToMoney);
         sellToMoney.repaint();
         salesIn.setText(totprice + "��");
         netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " ��");
         repaint();
         revalidate();
      }
   }
}

public class NewJTPayment {


}