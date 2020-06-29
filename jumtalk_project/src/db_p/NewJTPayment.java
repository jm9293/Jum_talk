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
      // 버튼 생성 및 버튼 그룹 만들기 - 패널 스위치용 버튼
      ArrayList<JButton> ckjbt = new ArrayList<JButton>();
      String[] CKBT = { "오늘의 쿠키", "코인 충전" };
      int[][] CKBTBounds = { { 20, 40, 120, 40 }, { 140, 40, 120, 40 } };
      ButtonGroup BTG = new ButtonGroup();
      for (int i = 0; i < CKBT.length; i++) {
         ckjbt.add(new JButton(CKBT[i]));
         ckjbt.get(i).setBounds(CKBTBounds[i][0], CKBTBounds[i][1], CKBTBounds[i][2], CKBTBounds[i][3]);
         add(ckjbt.get(i));
         ckjbt.get(i).addActionListener(this);
         BTG.add(ckjbt.get(i));
      }

      // 갖고 있는 코인의 갯수 라벨
      haveCoin = new JLabel();
      haveCoin.setBounds(320, 40, 200, 40);
      haveCoin.setText("코인 갯수 : " + UserDB.getCOIN(userID) + "개");
      add(haveCoin);

      // 두개의 패널 (이너클래스) 추가 //
      add(fortunepanel = new FortunePanel());
      add(chargepanel = new ChargePanel());

      setVisible(true);
      
   }

   // BuyerFrame ActionEvent
   @Override
   public void actionPerformed(ActionEvent e) {
      JButton Bt = (JButton) e.getSource();
      if (Bt.getText().equals("오늘의 쿠키")) {
         fortunepanel.setVisible(true);
         chargepanel.setVisible(false);
      } else {
         fortunepanel.setVisible(false);
         chargepanel.setVisible(true);
      }
   }

   /// 오늘의 포춘 쿠키 패널 - 스윙 1번 패널
   class FortunePanel extends JPanel implements ActionListener {

      JTextField clickResult;
      BuyerFrame buyerFrame;
      int bonus = (int) (Math.random() * 5 + 1);
      JButton CKClickBT;
      ImageIcon beforeCK = new ImageIcon("icon\\aaa.png");
      ImageIcon afterCK = new ImageIcon("icon\\bbb.png");
      String time;

      // 현 시간 나타내는 메소드 - 오늘의 쿠키 클릭 시 필요 //
      String times() {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
         time = simple.format(cal.getTime());

         return time;
      }

      public FortunePanel() {
         setBounds(0, 120, 480, 500);
         setLayout(null);

         // 오늘의 쿠키 버튼
         CKClickBT = new JButton(beforeCK);
         CKClickBT.setBounds(20, 20, 250, 250);
         CKClickBT.addActionListener(this);
         add(CKClickBT);

         // 오늘의 한 줄
         JLabel res = new JLabel("오늘의 한 줄");
         res.setBounds(20, 350, 100, 50);
         add(res);
         clickResult = new JTextField();
         clickResult.setLayout(null);
         clickResult.setBounds(20, 400, 450, 30);
         add(clickResult);
         clickResult.disable();

         // 하루 한번만 누를 수 있게 - 누른 시간이 그 날일 경우 버튼 비활성화
         if (UserDB.getFORTUNE_TIME(userID).equals(times())) {
            CKClickBT.setEnabled(false);
            CKClickBT.setIcon(afterCK);
         }
         setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {

         // 오늘의 쿠키 - 격언 정보 DB에서 받아오기
         clickResult.setText(TodayFortuneDB.getFORTUNEMESSAGE((int) (Math.random() * 10 + 1)));
         // 클릭 후 버튼 이미지 바꾸기
         CKClickBT.setIcon(afterCK);
         // 클릭 후 코인 갯수 업데이트 -> 라벨 + DB
         haveCoin.setText("코인 갯수 : " + (UserDB.getCOIN(userID) + bonus) + "개");
         UserDB.setCOIN(userID, UserDB.getCOIN(userID) + bonus);
         // 쿠키 버튼 클릭 후 비활겅화
         CKClickBT.setEnabled(false);
         // 버튼 클릭한 시간 DB로 보내기//
         UserDB.setFORTUNE_TIME(userID, times());
         // 보너스 갯수 DB로 보내기 - 충전과 구분하기 위해 + 순수익 합산을 위한 보너스 쿠키 갯수 분류//
         MoneyLogDB.saveMONEYLOG(userID, bonus * 100, bonus, 2);
         // 클릭 후 보너스로 얻은 갯수 시각적으로 확인시키기
         JOptionPane.showMessageDialog(this, "코인 " + bonus + "개를 추가로 얻었습니다.");
      }

   }

   // 코인 충전 패널 - 스윙 2번 패널
   class ChargePanel extends JPanel implements ActionListener {

      JTable MtoCList;
      JScrollPane MtoCPan;
      ArrayList<JRadioButton> res = new ArrayList<JRadioButton>();

      public ChargePanel() {
         setBounds(0, 120, 490, 510);
         setLayout(null);
         ButtonGroup bg = new ButtonGroup();

         // 가격 결정 라디오 버튼 생성 //
         String[] label = { "10,000 원      →    100 코인", "20,000 원      →    200 코인", "30,000 원      →    300 코인",
               "50,000 원      →    500 코인", "100,000 원    →    1000 코인" };
         int[][] loken = { { 30, 10, 250, 30 }, { 30, 50, 250, 30 }, { 30, 90, 250, 30 }, { 30, 130, 250, 30 },
               { 30, 170, 250, 30 } };
         for (int i = 0; i < label.length; i++) {
            res.add(new JRadioButton(label[i]));
            res.get(i).setBounds(loken[i][0], loken[i][1], loken[i][2], loken[i][3]);
            add(res.get(i));
            bg.add(res.get(i));
         }

         // 충전 요청 내역 리스트 - 충전자 개인 것
         String[][] data = MoneyLogDB.getMONEYLOG(userID, 0);
         String[] ListInfo = { "충전 금액", "충전한 코인 갯수", "시간" };
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

         // 결제 완료 버튼 //
         JButton finBt = new JButton("결제 완료");
         finBt.setBounds(350, 440, 120, 50);
         finBt.addActionListener(this);
         add(finBt);

         setVisible(false);
      }

      // 다른 패널 갔다 왔을 시 새로고침 없이 업데이트를 위한 메소드
      void refresh() {
         haveCoin.setText("코인 갯수 : " + (UserDB.getCOIN(userID)) + "개");
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
               String[] ListInfo = { "충전 금액", "충전한 코인 갯수", "시간" };
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

         JOptionPane.showMessageDialog(this, "입력하신 카드번호 " + UserDB.getCARDNUMBER(userID) + "로 결제되었습니다.");
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
      // 점술가 본인이 얻은 코인의 수를 보여주는 패널 + 라벨
      JPanel nowHaveCoinPan = new JPanel();
      nowHaveCoinPan.setLayout(null);
      nowHaveCoinPan.setBounds(20, 70, 200, 200);
      nowHaveCoinPan.setBackground(Color.lightGray);
      add(nowHaveCoinPan);
      haveCoinNumLB = new JLabel();
      haveCoinNumLB.setBounds(10, 50, 200, 100);
      
      nowMyCoin = UserDB.getCOIN(userID);
      haveCoinNumLB.setText("환전 가능한 코인 갯수 : " + nowMyCoin + "개");
      nowHaveCoinPan.add(haveCoinNumLB);

      // 점술가가 갖고있는 코인을 환전했을 시 얻을 수 있는 금액을 보여주는 패널 + 라벨
      JPanel getMoneyPan = new JPanel();
      getMoneyPan.setLayout(null);
      getMoneyPan.setBounds(260, 70, 200, 200);
      getMoneyPan.setBackground(Color.lightGray);
      add(getMoneyPan);
      getMoneyLB = new JLabel();
      getMoneyLB.setBounds(10, 50, 200, 100);
      getMoneyLB.setText("환전 가능한 코인 갯수 : " + nowMyCoin * 70 + "원");
      getMoneyPan.add(getMoneyLB);

      // 환전 요청 버튼
      changeCtoMBt = new JButton("환전 요청");
      changeCtoMBt.addActionListener(this);
      changeCtoMBt.setBounds(300, 300, 100, 70);
      add(changeCtoMBt);

      // 환전 요청 리스트
      String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
      if (data == null) {
         String[][] data1 = new String[1][3];
         String[] ListInfo = { "환전 금액", "환전한 코인 갯수", "시간" };
         CtoMList = new JTable(data1, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);
      } else {
         String[] ListInfo = { "환전 금액", "환전한 코인 갯수", "시간" };
         CtoMList = new JTable(data, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);
      }

      setVisible(true);
   
   }

   void refresh() {
      haveCoinNumLB.setText("환전 가능한 코인 갯수 : " + Integer.toString(nowMyCoin) + "개");
      getMoneyLB.setText("출금 가능한 금액 : " + Integer.toString(nowMyCoin * 70) + "원");
      String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      JButton bt = (JButton) e.getSource();

      if (bt.getText().equals("환전 요청")) {
         int coinnum = UserDB.getCOIN(userID);
         if (coinnum == 0) {
            JOptionPane.showMessageDialog(this, "코인이 없습니다.");
            return;
         }
         nowMyCoin = 0;
         haveCoinNumLB.setText("환전 가능한 코인 갯수 : " + Integer.toString(nowMyCoin) + "개");
         UserDB.setCOIN(userID, nowMyCoin);
         getMoneyLB.setText("출금 가능한 금액 : " + Integer.toString(nowMyCoin * 70) + "원");

         UserDB.setCOIN(userID, 0);
         MoneyLogDB.saveMONEYLOG(userID, coinnum * 70, coinnum, 1);

         String[][] data = MoneyLogDB.getMONEYLOG(userID, 1);
         String[] ListInfo = { "환전 금액", "환전한 코인 갯수", "시간" };
         remove(CtoMListPan);
         CtoMList = new JTable(data, ListInfo);
         CtoMListPan = new JScrollPane(CtoMList);
         CtoMList.setLayout(null);
         CtoMListPan.setBounds(15, 400, 450, 150);
         add(CtoMListPan);

         revalidate();
         CtoMListPan.repaint();
         JOptionPane.showMessageDialog(this, "입력하신 계좌번호 " + UserDB.getBANKNUMBER(userID) + "로 입금되었습니다.");

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
   String[] ListInfo = { "ID", "충전 금액", "충전한 코인 갯수", "시간" };
   String[] ListInfoS = { "ID", "환전 금액", "환전한 코인 갯수", "시간" };

   public EditerFrame(String userID) {
      this.userID =  userID;
      
      setLayout(null);
      JButton ns = new JButton("새로고침");
      DecimalFormat df = new DecimalFormat("###");
      JPanel salesPan = new JPanel();
      JPanel netgainPan = new JPanel();
      ArrayList<JLabel> jlb = new ArrayList<JLabel>();

      // JLabel 모음
      String[] labelName = { "COL", "MOL", "sales", "netgain" };
      String[] labelText = { "코인 충전 구매자 리스트", "코인 환전 점술가 리스트", "매출액", "순수익(매출액 30% - 보너스 코인)" };
      int[][] labelBounds = { { 30, 15, 220, 50 }, { 30, 220, 420, 50 }, { 30, 425, 200, 50 },
            { 270, 425, 200, 50 } };
      for (int i = 0; i < labelName.length; i++) {
         jlb.add(new JLabel(labelName[i]));
         jlb.get(i).setBounds(labelBounds[i][0], labelBounds[i][1], labelBounds[i][2], labelBounds[i][3]);
         jlb.get(i).setText(labelText[i]);
         add(jlb.get(i));
      }

      // 모든 충전자 리스트
      String[][] dataB = MoneyLogDB.getMONEYLOG(0);
      btc = new JTable(dataB, ListInfo);
      buyerToCoin = new JScrollPane(btc);
      buyerToCoin.setBounds(15, 70, 450, 150);
      add(buyerToCoin);
      if (btc == null) {
         btc = new JTable(new String[1][4], ListInfo);
      }

      // 모든 환전자 리스트
      String[][] dataS = MoneyLogDB.getMONEYLOG(1);
      stm = new JTable(dataS, ListInfoS);
      sellToMoney = new JScrollPane(stm);
      sellToMoney.setBounds(15, 270, 450, 150);
      add(sellToMoney);
      if (stm == null) {
         stm = new JTable(new String[1][4], ListInfo);
      }

      // 새로고침 버튼
      ns.addActionListener(this);
      ns.setBounds(260, 15, 100, 40);
      add(ns);

      // 총 매출 금액 -- 모든 소비자들의 충전 금액(원) ++
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
      salesIn.setText(totprice + "원");

      // 총 매출 금액 패널 - (금액)
      salesPan.setLayout(null);
      salesPan.add(salesIn);
      salesPan.setBounds(30, 485, 200, 100);
      salesPan.setBackground(Color.LIGHT_GRAY);
      add(salesPan);

      // 총 보너스 갯수 구하기 - 순수익 때 필요
      String[][] dataC = MoneyLogDB.getMONEYLOG(2);
      for (int i = 0; i < dataC.length; i++) {
         try {
            totbonus += (long) df.parse(dataC[i][1]);
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }

      // 총 순수익 금액 --> (총 매출 *30%) - (보너스 코인 금액*70%) (원) ++
      netIn = new JLabel();
      netIn.setBounds(50, 25, 140, 50);
      netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " 원");

      // 총 순수익 금액 패널
      netgainPan.setLayout(null);
      netgainPan.add(netIn);
      netgainPan.setBounds(260, 485, 200, 100);
      netgainPan.setBackground(Color.LIGHT_GRAY);
      add(netgainPan);

      setVisible(true);
      
   }

   void refresh() {
      salesIn.setText(totprice + "원");
      netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " 원");
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      JButton bt = (JButton) e.getSource();
      String[][] dataB = MoneyLogDB.getMONEYLOG(0);
      String[][] dataS = MoneyLogDB.getMONEYLOG(1);

      // 새로고침 버튼 누르면 업데이트
      if (bt.getText().equals("새로고침")) {
         // 충전 해준 코인들 총합 + 리스트
         remove(buyerToCoin);
         btc = new JTable(dataB, ListInfo);
         btc.setLayout(null);
         buyerToCoin = new JScrollPane(btc);
         buyerToCoin.setBounds(15, 70, 450, 150);
         add(buyerToCoin);
         buyerToCoin.repaint();

         // 환전 해준 코인들 총합 + 리스트
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

         // 보너스로 받은 코인들 총합 + 리스트
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
         salesIn.setText(totprice + "원");
         netIn.setText((int) (totprice * 0.3 - totbonus * 0.7) + " 원");
         repaint();
         revalidate();
      }
   }
}

public class NewJTPayment {


}