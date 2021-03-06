package db_p;

//import java.awt.CardLayout;
import java.awt.Color;

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Image;
//import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.IconView;
import javax.xml.crypto.Data;

class SellerReviseInfor2 extends JFrame implements ActionListener, WindowListener { // 점술가 정보 수정 프레임 (소비자 수정 프레임 상속)

   JLabel profile; // 상세정보
   JLabel photo; // 사진
   JLabel helloMsg; // 인사말
   JLabel nowPw;
   JLabel pw; // 비밀번호
   JLabel pwChk;
   JLabel phoneNum; // 전화번호
   JLabel email; // 이메일
   JLabel address; // 주소
   JLabel cardNum; // 카드번호
   JLabel pwHint; // 비밀번호 힌트
   Vector<String> passWordHint_S = new Vector<String>();
   JComboBox pwhint;
   JLabel hintAnswer; // 비밀번호 힌트 답
   JLabel workplaceName; // 점술가 사업장 이름
   JLabel workplaceAddress; // 점술가 사업장 주소
   ArrayList<JLabel> information; // 개인정보 리스트
   JButton reviseGo; // 개인정보 수정 완료 버튼

   JButton photoButton;

   JLabel photoFileName;

   JTextField helloText; // 인사말 수정입력
   JPasswordField nowPwText;
   JPasswordField pwField; // 비밀번호 수정입력
   JPasswordField pwChkText;

   Vector<String> pnNum;// 폰번호 수정입력
   JComboBox phoneNumBox;
   JTextField phoneNumText_1;
   JTextField phoneNumText_2;
   JLabel phoneLabel; // 핸드폰 짝대기
   JLabel phoneLabel_2; // 핸드폰 짝대기

   JTextField emailText; // 이메일 수정입력
   JTextField addressText; // 주소 수정입력
   JTextField cardNumText; // 카드번호 수정입력
   JTextField pwHintText; // 비밀번호 힌트 수정입력
   JTextField hintAnswerText; // 힌트 답 수정입력
   JTextField workNameText; //
   JTextField workAddressText;
   ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
   Vector<String> bankChoice = new Vector<String>();
   JComboBox<String> bankBox;
   String userID;
   ProfileInOut pfio = ProfileInOut.getprofileInout();
   SellerSignUpChk sellerSignUpChk;
   
   

   boolean[] regularChk = new boolean[9];
   boolean signUpEnd = true;

   // 유효성검사
   String pwRegular = "^.*(?=^.{5,19}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$"; // 비밀번호 유효성 --> 6~20자 이내의 영문
                                                                  // 대소문자/숫자/특수문자 반드시 포함
   String phoneNumRegular = "\\d{3,4}$"; // 전화번호 숫자 4자 이내로
   String emailRegular = "[a-zA-Z0-9]*@[a-zA-Z.]*"; // 이메일 대소문자/숫자
   String nameRegular = "^[가-힣]*$"; // 이름 한글만 가능
   String addressRegular = "^[-a-zA-Z가-힣0-9]*$"; //
   String businessName_R = "^[a-zA-Z가-힣0-9]*$"; // 사업장명
   String businessAddress_R = "^[0-9-]*$"; // 계좌번호
   String sRegular = "\\S*"; // 공백

   String phoneDB;

   OptionPanel optionPanel;

   public SellerReviseInfor2(String userID, OptionPanel optionPanel) { // 정보수정 생성자
      this.userID = userID;
      setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀
      this.optionPanel = optionPanel;
      setBounds(700, 0, 600, 900);
      setLayout(null);
      addWindowListener(this);

      // 개인정보 라벨 생성
      profile = new JLabel("상세 프로필");
      photo = new JLabel("사진 수정");
      helloMsg = new JLabel("인사말 수정");
      nowPw = new JLabel("기존 PW");
      pw = new JLabel("PW 수정");
      pwChk = new JLabel("PW 확인");
      email = new JLabel("이메일 수정");
      phoneNum = new JLabel("전화번호 수정");
      address = new JLabel("주소 수정");
      cardNum = new JLabel("계좌번호 수정");
      pwHint = new JLabel("PW힌트 수정");
      hintAnswer = new JLabel("힌트 답 수정");
      workplaceName = new JLabel("사업장 이름");
      workplaceAddress = new JLabel("사업장 주소");

      helloText = new JTextField(); // 인사말
      nowPwText = new JPasswordField(); // 기존 비밀번호
      pwField = new JPasswordField(); // 비밀번호 변경
      pwChkText = new JPasswordField(); // 비밀번호 확인

      phoneNumBox = new JComboBox(); // 핸드폰번호 선택
      phoneNumText_1 = new JTextField(); // 두번째 핸드폰 번호 입력창
      phoneNumText_2 = new JTextField(); // 세번째 핸드폰 번호 입력창
      phoneLabel = new JLabel("-");
      phoneLabel_2 = new JLabel("-");

      emailText = new JTextField(); // 이메일 입력
      addressText = new JTextField(); // 주소 입력
      cardNumText = new JTextField(); // 카드번호 입력
      hintAnswerText = new JTextField(); // 비밀번호 힌트 답
      workNameText = new JTextField(); // 사업장명
      workAddressText = new JTextField(); // 사업장주소

      // 핸드폰번호 앞자리 ------------------------------------
      pnNum = new Vector<String>();
      pnNum.add("010");
      pnNum.add("011");
      pnNum.add("016");
      pnNum.add("017");
      pnNum.add("018");
      pnNum.add("019");

      phoneNumBox = new JComboBox<String>(pnNum);
      phoneNumBox.setBounds(140, 535, 90, 40);
      phoneNumBox.setSelectedItem("010");
      add(phoneNumBox);

      bankChoice.add("-은행선택-");
      bankChoice.add("기업은행");
      bankChoice.add("농협은행");
      bankChoice.add("신한은행");
      bankChoice.add("산업은행");
      bankChoice.add("우리은행");
      bankChoice.add("씨티은행");
      bankChoice.add("하나은행");
      bankChoice.add("광주은행");
      bankChoice.add("경남은행");
      bankChoice.add("대구은행");
      bankChoice.add("광주은행");
      bankChoice.add("부산은행");
      bankChoice.add("수협은행");
      bankChoice.add("제주은행");
      bankChoice.add("제주은행");
      bankChoice.add("전북은행");
      bankChoice.add("새마을금고");
      bankChoice.add("신협은행");
      bankChoice.add("수협은행");
      bankChoice.add("우체국");

      bankBox = new JComboBox<String>(bankChoice);

      ImageIcon ii = new ImageIcon(pfio.download(userID));
      Image img = ii.getImage();
      img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
      ii = new ImageIcon(img);
      photoFileName = new JLabel(ii);

      photoButton = new JButton("사진 수정");
      photoButton.addActionListener(this);
      passWordHint_S.add("원하는 질문을 선택하세요.");
      passWordHint_S.add("가장 기억에 남는 장소는?");
      passWordHint_S.add("나의 보물 1호는?");
      passWordHint_S.add("본인의 출생지는?");
      passWordHint_S.add("내가 존경하는 인물은?");
      passWordHint_S.add("나의 좌우명은?");
      passWordHint_S.add("가장 감명깊게 본 영화는?");
      passWordHint_S.add("내가 좋아하는 뮤지션은?");
      passWordHint_S.add("인상깊게 읽은 책 제목은?");
      passWordHint_S.add("나의 노래방 애창곡은?");

      pwhint = new JComboBox(passWordHint_S);

      // 개인정보 리스트에 개인정보 라벨 담기
      information = new ArrayList<JLabel>();
      information.add(profile);
      information.add(photo);
      information.add(helloMsg);
      information.add(pw);
      information.add(phoneNum);
      information.add(email);
      information.add(address);
      information.add(cardNum);
      information.add(pwHint);
      information.add(hintAnswer);
      information.add(workplaceName);
      information.add(workplaceAddress);

      // 수정완료 버튼
      reviseGo = new JButton("수정 완료");
      // 개인정보 수정 프레임 나가기 버튼

      reviseGo.setBounds(470, 10, 100, 40);

      photoFileName.setBounds(130, 10, 100, 100);
      photoButton.setBounds(330, 60, 100, 50);
      helloText.setBounds(130, 120, 300, 50);
      nowPwText.setBounds(130, 180, 300, 50);
      pwField.setBounds(130, 240, 300, 50);
      pwChkText.setBounds(130, 300, 300, 50);

      // 핸드폰
      phoneNumBox.setBounds(130, 360, 90, 50); // 010 ....
      phoneNumText_1.setBounds(230, 360, 90, 50); // 두번째 전화번호입력하는 창
      phoneNumText_2.setBounds(330, 360, 90, 50); // 세번째 전화번호 입력하는 창
      phoneLabel.setBounds(220, 360, 10, 50); // 짝대기
      phoneLabel_2.setBounds(320, 360, 10, 50); // 짝대기

      emailText.setBounds(130, 420, 300, 50);
      addressText.setBounds(130, 480, 300, 50);
      cardNumText.setBounds(130, 540, 300, 50);
      pwhint.setBounds(130, 600, 300, 50);
      hintAnswerText.setBounds(130, 660, 300, 50);
      workNameText.setBounds(130, 720, 300, 50);
      workAddressText.setBounds(130, 780, 300, 50);

      bankBox.setBounds(450, 540, 100, 50);

      for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
         information.get(i).setBounds(10, i * 60 + 120, 100, 50);
      }

      photo.setBounds(10, 60, 100, 50);
      helloMsg.setBounds(10, 120, 100, 50);
      nowPw.setBounds(10, 180, 100, 50);
      pw.setBounds(10, 240, 100, 50);
      pwChk.setBounds(10, 300, 100, 50);

      reviseGo.addActionListener(this); // 수정완료 버튼

      System.out.println(UserDB.getBANKNUMBER(userID));

      helloText.setText(UserDB.getPROFILE_TEXT(userID));
      nowPwText.setText(UserDB.getPW(userID));
      phoneNumBox.setSelectedItem(UserDB.getPHONE(userID).split("-")[0]);
      phoneNumText_1.setText(UserDB.getPHONE(userID).split("-")[1]);
      phoneNumText_2.setText(UserDB.getPHONE(userID).split("-")[2]);
      emailText.setText(UserDB.getEMAIL(userID));
      addressText.setText(UserDB.getADDRESS(userID));
      cardNumText.setText(UserDB.getBANKNUMBER(userID).split("/")[0]);
//             pwHintText.setText(UserDB.getpw);
      hintAnswerText.setText(UserDB.getPWRES(userID));
      bankBox.setSelectedItem(UserDB.getBANKNUMBER(userID).split("/")[1]);
      pwhint.setSelectedItem(UserDB.getPWHINT(userID));
      workNameText.setText(UserDB.getBUSINESSNAME(userID));
      workAddressText.setText(UserDB.getBUSINESSADDRESS(userID));
      
      fieldArr.add(nowPwText);
      fieldArr.add(phoneNumText_1);
      fieldArr.add(phoneNumText_2);
      fieldArr.add(emailText);
      fieldArr.add(addressText);
      fieldArr.add(cardNumText);
      fieldArr.add(hintAnswerText);
      
      add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가

      add(nowPw);
      add(pwChk);

      add(helloText);
      add(nowPwText);
      add(pwField);
      add(pwChkText);
      add(phoneNumBox);
      add(emailText);
      add(addressText);
      add(cardNumText);
      add(hintAnswerText);
      add(workNameText);
      add(workAddressText);
      add(profile);
      add(pwhint);
      add(phoneNumBox);
      add(phoneNumText_1);
      add(phoneNumText_2);
      add(phoneLabel);
      add(phoneLabel_2);

      add(photoButton);
      add(photoFileName);
      add(bankBox);

      for (JLabel jl : information) {
         add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
      }

      sellerSignUpChk = new SellerSignUpChk();
      sellerSignUpChk.start();

      setVisible(true);

   }

   FileReviseFrame fileReviseFrame;

   @Override
   public void actionPerformed(ActionEvent e) {

      int cnt = 0;
      int fieldChk = 0;

      for (boolean rgc : regularChk) {
         if (rgc == true) {
            cnt++;
         }

      }
      
      for (JTextField tf : fieldArr) {
      if(!(tf.getText().equals(""))) {
         fieldChk++;
      }
   }

      if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
         // 수정완료 팝업 띄운후 프레임 사라지기
         System.out.println("들어오니");
         // DB들어갈때
         phoneDB = phoneNumBox.getSelectedItem().toString() + "-" + phoneNumText_1.getText() + "-"
               + phoneNumText_2.getText(); // 핸드폰번호

         if (pwhint.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해 주세요");
         } else if (!(pwField.getText().equals(pwChkText.getText()))) {
            JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요");
         } else if (bankBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "은행을 선택해 주세요");
         } else if (cnt != 9) {
            System.out.println(cnt);
            JOptionPane.showMessageDialog(null, "올바른 형식이 아닙니다");
         } else if (fieldChk != 7) {
            JOptionPane.showMessageDialog(null, "빈 칸을 입력해 주세요");
         }
         else {
            JOptionPane.showMessageDialog(null, "수정완료");
            signUpEnd = false;
            System.out.println(signUpEnd);
            if (!(helloText.getText().equals(""))) {
               System.out.println("들어오니1");
               UserDB.setPROFILE_TEXT(userID, helloText.getText());
            }
            if (!(pwField.getText().equals(""))) {
               System.out.println("들어오니2");
               UserDB.setPW(userID, pwField.getText());
            }
            if (!(phoneDB.equals(""))) {
               System.out.println("들어오니3");
               UserDB.setPHONE(userID, phoneDB);
            }
            if (!(emailText.getText().equals(""))) {
               System.out.println("들어오니4");
               UserDB.setEMAIL(userID, emailText.getText());
            }
            if (!(addressText.getText().equals(""))) {
               System.out.println("들어오니5");
               UserDB.setADDRESS(userID, addressText.getText());
            }
            if (!(cardNumText.getText().equals(""))) {
               System.out.println("들어오니6");
               String bankStr = (String) bankBox.getSelectedItem();
               UserDB.setBANKNUMBER(userID, cardNumText.getText() + "/" + bankStr);
            }
            if (!(pwhint.getSelectedItem().equals(""))) {
               System.out.println("들어오니7");
               UserDB.setPWHINT(userID, pwhint.getSelectedItem().toString());
            }
            if (!(hintAnswerText.getText().equals(""))) {
               System.out.println("들어오니8");
               System.out.println(hintAnswerText.getText());
               UserDB.setPWRES(userID, hintAnswerText.getText());
            }
            if (!(workNameText.getText().equals(""))) {
               System.out.println("들어오니9");
               System.out.println(workNameText.getText());
               UserDB.setBUSINESSNAME(userID, workNameText.getText());
            }
            if (!(workAddressText.getText().equals(""))) {
               System.out.println("들어오니10");
               System.out.println(workAddressText.getText() + userID);
               UserDB.setBUSINESSADDRESS(userID, workAddressText.getText());
            }
            optionPanel.ri = null;
//            setVisible(false);
            dispose();
            System.out.println("쓰레드" + signUpEnd);
         }

      } else if (e.getSource().equals(photoButton)) {
         fileReviseFrame = new FileReviseFrame();
         String fileStr = fileReviseFrame.fd.getDirectory() + "\\" + fileReviseFrame.fd.getFile();

         if (fileStr.substring(fileStr.lastIndexOf(".") + 1).toLowerCase().equals("jpg")) {
            pfio.upload(fileReviseFrame.fd.getDirectory() + "\\" + fileReviseFrame.fd.getFile(), userID);
            ImageIcon ii = new ImageIcon(pfio.download(userID));
            Image img = ii.getImage();
            img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
            ii = new ImageIcon(img);

            photoFileName.setIcon(ii);
            repaint();
         } else if (fileStr.equals("null\\null")) {

         } else if (!fileStr.substring(fileStr.lastIndexOf(".") + 1).toLowerCase().equals("jpg")) {
            JOptionPane.showMessageDialog(null, "jpg파일만 가능합니다");
         }

      }

   }

   @Override
   public void windowOpened(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowClosing(WindowEvent e) {
      signUpEnd = false;
      optionPanel.ri = null;

   }

   @Override
   public void windowClosed(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowIconified(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowDeiconified(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowActivated(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowDeactivated(WindowEvent e) {
      // TODO Auto-generated method stub

   }

   // 유효성 검사 쓰레드
   class SellerSignUpChk extends Thread {

      @Override
      public void run() {
         while (signUpEnd) {
            // 비밀번호 유효성 검사 --> 숫자,영문, 특수문자 포함 (공백 금지) ---------------------------------
            if (((!(Pattern.matches(pwRegular, pwField.getText())))
                  || !(Pattern.matches(sRegular, pwField.getText()))) && !pwField.getText().equals("")) {
               regularChk[0] = false;
               pwField.setForeground(Color.red);
               pwField.setOpaque(true);

            } else {
               regularChk[0] = true;
               pwField.setForeground(Color.black);

               // 비밀번호 == 비밀번호 확인 일치한지 검사!!
            }
            if (!(pwField.getText().equals(pwChkText.getText()))) {
               regularChk[1] = false;
               pwChkText.setForeground(Color.red);

            } else {
               regularChk[1] = true;
               pwChkText.setForeground(Color.black);

               // 이름 유효성 검사 --> 한글만 사용가능 --------------------------------
            }
            if ((!(Pattern.matches(emailRegular, emailText.getText())))
                  || !(Pattern.matches(sRegular, emailText.getText())) && !emailText.getText().equals("")) {
               regularChk[2] = false;
               emailText.setForeground(Color.red);

            } else {
               regularChk[2] = true;
               emailText.setForeground(Color.black);

            }
            if ((!(Pattern.matches(phoneNumRegular, phoneNumText_1.getText())))
                  || !((Pattern.matches(sRegular, phoneNumText_1.getText()))
                        && !phoneNumText_1.getText().equals(""))) {
               regularChk[3] = false;
               phoneNumText_1.setForeground(Color.red);

            } else {
               regularChk[3] = true;
               phoneNumText_1.setForeground(Color.black);

            }
            if ((!(Pattern.matches(phoneNumRegular, phoneNumText_2.getText())))
                  || !((Pattern.matches(sRegular, phoneNumText_2.getText()))
                        && !phoneNumText_2.getText().equals(""))) {
               regularChk[4] = false;
               phoneNumText_2.setForeground(Color.red);

            } else {
               regularChk[4] = true;
               phoneNumText_2.setForeground(Color.black);

               // 카드번호 유효성 검사 --> 글자수 4자 제한, 숫자만 가능 ..--------------------------------
            }
            // 주소는 한글/영문/숫자만 가능!!(초성금지)
            if ((!(Pattern.matches(addressRegular, addressText.getText().trim().replaceAll(" ", ""))))
                  && !addressText.getText().equals("")) {
               regularChk[5] = false;
               addressText.setForeground(Color.red);

            } else {
               regularChk[5] = true;
               addressText.setForeground(Color.black);

            } // 사업장명
            if ((!(Pattern.matches(businessName_R, workNameText.getText().trim().replaceAll(" ", ""))))
                  && !workNameText.getText().equals("")) {
               regularChk[6] = false;
               workNameText.setForeground(Color.red);

            } else {
               regularChk[6] = true;
               workNameText.setForeground(Color.black);
            }
            // 사업장주소
            if ((!(Pattern.matches(addressRegular, workAddressText.getText().trim().replaceAll(" ", ""))))
                  && !workAddressText.getText().equals("")) {
               regularChk[7] = false;
               workAddressText.setForeground(Color.red);

            } else {
               regularChk[7] = true;
               workAddressText.setForeground(Color.black);

               // 계좌번호
            }
            if ((!(Pattern.matches(businessAddress_R, cardNumText.getText().trim().replaceAll(" ", ""))))
                  && !cardNumText.getText().equals("")) {
               regularChk[8] = false;
               cardNumText.setForeground(Color.red);

            } else {
               regularChk[8] = true;
               cardNumText.setForeground(Color.black);

            }
         }
      }
   }

}

class FileReviseFrame extends JFrame {

   FileDialog fd;

   public FileReviseFrame() {

      fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
      fd.setLocation(510, 210);
      fd.setVisible(true);
   }
}

//   class OptionAction implements ActionListener{   //고객센터, 개인정보 수정 버튼 액션 리스너
//      
////      JButton opt1;   //고객센터 버튼
////      JButton opt2;   //개인정보 수정 버튼
////      JFrame help;
//      JPanel notice;   //공지사항 패널
//      JPanel message;   //메세지 패널
//      
////      JumTalkOption jt;
//      ReviseInfor ri;   //소비자 개인정보 수정 클래스 
//      JumTalkOption_User jto;
////      Opt1_Frame opt1_F;
//      
//      public OptionAction(JPanel notice, JPanel message) {   //공지사항, 메세지 패널(visible)을 위해 매개변수
//         this.notice = notice;
//         this.message = message;
//      }
//      
//      @Override
//      public void actionPerformed(ActionEvent e) {
//         
//      
//            
//      }
//   }

class OptionPanel extends JPanel implements ActionListener { // 고객, 수정버튼 밑에 패널
   JPanel optPanel; // 고객센터, 개인정보 수정 버튼이 담긴 패널

   JFrame ri;
   JLabel ntcLabel; // 공지사항 라벨
   ArrayList<JButton> optButton;
   NoticePanel ntcPanel; // 고객센터의 공지사항 패널
   MessagePanel msgPanel; // 고객센터의 메세지 패널

   JButton opt1; // 설정의 고객센터
   JButton opt2; // 설정의 개인정보 수정

   String userID;
   JFrame jumTalkOption_User;

   public OptionPanel(String userID, JFrame jp) {
      this.userID = userID;
      this.jumTalkOption_User = jp;

      optButton = new ArrayList<JButton>(); // 설정, 개인정보 수정 버튼 리스트

      optPanel = new JPanel(); // 설정버튼 패널
      optPanel.setBounds(0, 615, 500, 50);

      ntcPanel = new NoticePanel(); // 공지사항 패널
      ntcPanel.setBounds(0, 0, 500, 200);
      msgPanel = new MessagePanel(); // 메세지 패널
      msgPanel.setBounds(0, 210, 500, 400);

      opt1 = new JButton("로그아웃"); // 고객센터 버튼
      opt2 = new JButton("개인정보 수정"); // 개인정보 수정 버튼

      ntcLabel = new JLabel("공지사항"); // 공지사항 라벨 (임시)

      ntcPanel.setLayout(null); // 공지사항 패널 레이아웃
      msgPanel.setLayout(null); // 메세지 패널 레이아웃

      ntcLabel.setBounds(220, 30, 200, 50); // 공지사항 라벨 위치 (임시)

      optButton.add(opt1); // 버튼 리스트에 설정버튼 담음
      optButton.add(opt2); // 버튼 리스트에 개인정보 수정 버튼 담음

      // 공지사항 패널
      ntcPanel.add(ntcLabel); // 공지사항 패널에 공지사항 라벨 추가(임시)

      // 관리자에게 쪽지

//         oa = new OptionAction(ntcPanel,msgPanel);   //고객센터, 개인정보 수정 액션 리스너 생성

      ntcPanel.setVisible(true); // 액션 리스너로 나오게할 것 (공지사항 패널)
      msgPanel.setVisible(true); // 액션 리스너로 나오게할 것 (메세지 패널)

      setLayout(null); // 고객센터, 개인정보 수정 버튼 밑에 패널

      optPanel.setLayout(new GridLayout(1, 2)); // 고객센터, 개인정보 수정 버튼 패널

      optPanel.add(opt2); // 고객센터, 개인정보 수정 버튼 패널에 개인정보 수정 버튼 추가
      optPanel.add(opt1); // 고객센터, 개인정보 수정 버튼 패널에 고객센터 버튼 추가
      add(optPanel);
      add(ntcPanel); // 공지사항 패널 추가
      add(msgPanel); // 메세지 패널 추가
      for (JButton jb : optButton) { // 고객센터, 개인정보 수정버튼 리스트
         jb.addActionListener(this);
      }

      repaint();
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(optButton.get(0))) {
         // 로그인 패널로 바뀜
         UserDB.setLOGINCHK(userID, "false");  
        ProfileInOut.getprofileInout().logOut(userID);
         JOptionPane.showMessageDialog(null, "로그아웃 완료");
         System.exit(0);
      } else if (e.getSource().equals(optButton.get(1))) {
         if (UserDB.getUSERKIND(userID) == 0) {
            if (ri == null) {
               ri = new ReviseInfor();
            }
         } else {
            if (ri == null) {
               ri = new SellerReviseInfor2(userID, this);
            }
         }
      }

   }

   class ReviseInfor extends JFrame implements ActionListener, WindowListener { // 개인정보 수정 프레임

      JLabel profile; // 상세정보
      JLabel photo; // 사진
      JLabel helloMsg; // 인사말
      JLabel pw; // 비밀번호
      JLabel phoneNum; // 전화번호
      JLabel phoneLabel_1;
      JLabel phoneLabel_2;
      JLabel email; // 이메일
      JLabel address; // 주소
      JLabel cardNum; // 카드번호
      JLabel cardLabel_1;
      JLabel cardLabel_2;
      JLabel cardLabel_3;
      JLabel pwHint; // 비밀번호 힌트
      JLabel hintAnswer; // 비밀번호 힌트 답
      ArrayList<JLabel> information; // 개인정보 리스트
      JButton reviseGo; // 개인정보 수정 완료 버튼

      JButton photoButton;
      JLabel photoFileName;
      JLabel nowPw;
      JLabel pwChk;

      JTextField helloText;
      JPasswordField nowPwField;
      JPasswordField pwField;
      JPasswordField pwChkText;
      Vector<String> pnNum = new Vector<String>();// 폰번호 수정입력
      JComboBox phoneNumBox;
      JTextField phoneNumText_1;
      JTextField phoneNumText_2;
      JTextField emailText;
      JTextField addressText;
      JTextField cardNumText_1;
      JTextField cardNumText_2;
      JTextField cardNumText_3;
      JTextField cardNumText_4;
//      JTextField pwHintText;
      JTextField hintAnswerText;
      ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
      Vector<String> passWordHint_S = new Vector<String>();
//        Vector<String> bankChoice = new Vector<String>();
//        JComboBox<String> bankBox;
      JComboBox pwhint;
      FileReviseFrame fileReviseFrame;

      String pwRegular = "^.*(?=^.{5,19}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$"; // 비밀번호 유효성 --> 6~20자 이내의 영문
      // 대소문자/숫자/특수문자 반드시 포함
      String phoneNumRegular = "\\d{3,4}$"; // 전화번호 숫자 4자 이내로
      String emailRegular = "^[0-9a-zA-Z@.].*"; // 이메일 대소문자/숫자
      String nameRegular = "^[가-힣]*$"; // 이름 한글만 가능
      String cardRegular = "\\d{4}$"; // 숫자
      String addressRegular = "^[-a-zA-Z가-힣0-9]*$"; // 주소
      String businessName_R = "^[a-zA-Z가-힣0-9]*$"; // 사업장명
      String businessAddress_R = "^[0-9-]*$"; // 계좌번호
      String sRegular = "\\S*"; // 공백
      String phoneDB;
      NormalSignUpChk normalSignUpChk;
      boolean[] regularChk = new boolean[10];

      boolean signUpEnd = true;

      public ReviseInfor() { // 정보수정 생성자

         setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀

         setBounds(700, 100, 500, 870);
         setLayout(null);
         addWindowListener(this);

         pnNum = new Vector<String>();
         pnNum.add("010");
         pnNum.add("011");
         pnNum.add("016");
         pnNum.add("017");
         pnNum.add("018");
         pnNum.add("019");

         // 개인정보 라벨 생성
         profile = new JLabel("상세 프로필");
         photo = new JLabel("사진 수정");
         helloMsg = new JLabel("인사말 수정");
         nowPw = new JLabel("기존 비밀번호");
         pw = new JLabel("PW 수정");
         pwChk = new JLabel("pw 확인");
         email = new JLabel("이메일 수정");
         phoneNum = new JLabel("전화번호 수정");
         address = new JLabel("주소 수정");
         cardNum = new JLabel("카드번호 수정");
         pwHint = new JLabel("PW힌트 수정");
         hintAnswer = new JLabel("힌트 답 수정");
         photoButton = new JButton("사진 수정");
         phoneLabel_1 = new JLabel("-");
         phoneLabel_2 = new JLabel("-");
         ImageIcon ii = new ImageIcon(pfio.download(userID));
         Image img = ii.getImage();
         img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
         ii = new ImageIcon(img);

//           bankChoice.add("-은행선택-");
//           bankChoice.add("기업은행");
//           bankChoice.add("농협은행");
//           bankChoice.add("신한은행");
//           bankChoice.add("산업은행");
//           bankChoice.add("우리은행");
//           bankChoice.add("씨티은행");
//           bankChoice.add("하나은행");
//           bankChoice.add("광주은행");
//           bankChoice.add("경남은행");
//           bankChoice.add("대구은행");
//           bankChoice.add("광주은행");
//           bankChoice.add("부산은행");
//           bankChoice.add("수협은행");
//           bankChoice.add("제주은행");
//           bankChoice.add("제주은행");
//           bankChoice.add("전북은행");
//           bankChoice.add("새마을금고");
//           bankChoice.add("신협은행");
//           bankChoice.add("수협은행");
//           bankChoice.add("우체국");

//           bankBox = new JComboBox<String>(bankChoice);

         photoFileName = new JLabel(ii);

         helloText = new JTextField();
         pwField = new JPasswordField();
         phoneNumBox = new JComboBox<String>(pnNum);
         phoneNumText_1 = new JTextField();
         phoneNumText_2 = new JTextField();
         emailText = new JTextField();
         addressText = new JTextField();
         cardNumText_1 = new JTextField();
         cardNumText_2 = new JTextField();
         cardNumText_3 = new JTextField();
         cardNumText_4 = new JTextField();
         cardLabel_1 = new JLabel("-");
         cardLabel_2 = new JLabel("-");
         cardLabel_3 = new JLabel("-");
//         pwHintText = new JTextField();
         hintAnswerText = new JTextField();
         nowPwField = new JPasswordField();
         pwChkText = new JPasswordField();

         // 개인정보 리스트에 개인정보 라벨 담기
         information = new ArrayList<JLabel>();
         information.add(profile);
         information.add(photo);
         information.add(helloMsg);
         information.add(pw);
         information.add(email);
         information.add(phoneNum);
         information.add(address);
         information.add(cardNum);
         information.add(pwHint);
         information.add(hintAnswer);

         passWordHint_S.add("원하는 질문을 선택하세요.");
         passWordHint_S.add("가장 기억에 남는 장소는?");
         passWordHint_S.add("나의 보물 1호는?");
         passWordHint_S.add("본인의 출생지는?");
         passWordHint_S.add("내가 존경하는 인물은?");
         passWordHint_S.add("나의 좌우명은?");
         passWordHint_S.add("가장 감명깊게 본 영화는?");
         passWordHint_S.add("내가 좋아하는 뮤지션은?");
         passWordHint_S.add("인상깊게 읽은 책 제목은?");
         passWordHint_S.add("나의 노래방 애창곡은?");

         pwhint = new JComboBox(passWordHint_S);

         // 수정완료 버튼
         reviseGo = new JButton("수정 완료");
         // 개인정보 수정 프레임 나가기 버튼

         reviseGo.setBounds(370, 10, 100, 40);

         pwhint.setBounds(130, 700, 300, 50);

         photoFileName.setBounds(100, 60, 200, 100);
         photoButton.setBounds(330, 80, 100, 50);
         helloText.setBounds(130, 220, 300, 50);
         nowPwField.setBounds(130, 280, 300, 50);
         pwField.setBounds(130, 340, 300, 50);
         pwChkText.setBounds(130, 400, 300, 50);
         emailText.setBounds(130, 460, 300, 50);
         phoneNumBox.setBounds(130, 520, 90, 50);
         phoneNumText_1.setBounds(230, 520, 90, 50);
         phoneNumText_2.setBounds(330, 520, 90, 50);
         phoneLabel_1.setBounds(220, 520, 10, 50);
         phoneLabel_2.setBounds(320, 520, 10, 50);
         addressText.setBounds(130, 580, 300, 50);
         cardNumText_1.setBounds(130, 640, 60, 50);
         cardNumText_2.setBounds(200, 640, 60, 50);
         cardNumText_3.setBounds(270, 640, 60, 50);
         cardNumText_4.setBounds(340, 640, 60, 50);
         cardLabel_1.setBounds(190, 640, 10, 50);
         cardLabel_2.setBounds(260, 640, 10, 50);
         cardLabel_3.setBounds(330, 640, 10, 50);
//         pwHintText.setBounds(130, 480, 300, 50);
         hintAnswerText.setBounds(130, 760, 300, 50);
//         bankBox.setBounds(450, 640, 100, 50);

         for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
            information.get(i).setBounds(10, (i * 60) + 220, 100, 50);
         }
         helloMsg.setBounds(10, 220, 100, 50);
         photo.setBounds(10, 60, 100, 100);
         nowPw.setBounds(10, 280, 100, 50);
         pw.setBounds(10, 340, 100, 50);
         pwChk.setBounds(10, 400, 100, 50);

         reviseGo.addActionListener(this); // 수정완료 버튼

         photoButton.addActionListener(this);

         helloText.setText(UserDB.getPROFILE_TEXT(userID));
         nowPwField.setText(UserDB.getPW(userID));
         phoneNumBox.setSelectedItem(UserDB.getPHONE(userID).split("-")[0]);
         phoneNumText_1.setText(UserDB.getPHONE(userID).split("-")[1]);
         phoneNumText_2.setText(UserDB.getPHONE(userID).split("-")[2]);
         emailText.setText(UserDB.getEMAIL(userID));
         addressText.setText(UserDB.getADDRESS(userID));
         cardNumText_1.setText(UserDB.getCARDNUMBER(userID).split("-")[0]);
         cardNumText_2.setText(UserDB.getCARDNUMBER(userID).split("-")[1]);
         cardNumText_3.setText(UserDB.getCARDNUMBER(userID).split("-")[2]);
         cardNumText_4.setText(UserDB.getCARDNUMBER(userID).split("-")[3]);
//         pwHintText.setText(UserDB.getpw);
         hintAnswerText.setText(UserDB.getPWRES(userID));

         pwhint.setSelectedItem(UserDB.getPWHINT(userID));
         
         
         fieldArr.add(nowPwField);
         fieldArr.add(phoneNumText_1);
         fieldArr.add(phoneNumText_2);
         fieldArr.add(emailText);
         fieldArr.add(addressText);
         fieldArr.add(cardNumText_1);
         fieldArr.add(cardNumText_2);
         fieldArr.add(cardNumText_3);
         fieldArr.add(cardNumText_4);
         fieldArr.add(hintAnswerText);

         add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가

//         add(passWordHint_S);
         add(nowPw);
         add(pwChk);

         add(photoFileName);
         add(photoButton);
         add(helloText);
         add(pwField);
         add(phoneNumBox);
         add(phoneLabel_1);
         add(phoneLabel_2);
         add(phoneNumText_1);
         add(phoneNumText_2);
         add(emailText);
         add(addressText);
         add(cardNumText_1);
         add(cardNumText_2);
         add(cardNumText_3);
         add(cardNumText_4);
         add(cardLabel_1);
         add(cardLabel_2);
         add(cardLabel_3);
//         add(pwHintText);
         add(hintAnswerText);
         add(pwhint);
         add(nowPwField);
         add(pwChkText);
//         add(bankBox);

         for (JLabel jl : information) {
            add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
         }

         normalSignUpChk = new NormalSignUpChk();
         normalSignUpChk.start();
         setVisible(true);

      }

      ProfileInOut pfio = ProfileInOut.getprofileInout();

      @Override
      public void actionPerformed(ActionEvent e) {

         int cnt = 0;
         int fieldChk = 0;

         for (boolean rgc : regularChk) {
            if (rgc == true) {
               cnt++;
            }

         }
         
         for (JTextField tf : fieldArr) {
         if(!(tf.getText().equals(""))) {
            fieldChk ++;
         }
      }

         if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
            // 수정완료 팝업 띄운후 프레임 사라지기

            boolean pwhintChk = pwhint.getSelectedIndex() == 0;
            phoneDB = phoneNumBox.getSelectedItem().toString() + "-" + phoneNumText_1.getText() + "-"
                  + phoneNumText_2.getText();
            String cardNumText = cardNumText_1.getText() + "-" + cardNumText_2.getText() + "-"
                  + cardNumText_3.getText() + "-" + cardNumText_4.getText();
            if (pwhintChk == true) {
               JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해주세요");
            } else if (!(pwField.getText().equals(pwChkText.getText()))) {
               JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요");
            } else if (cnt != 10) {
               JOptionPane.showMessageDialog(null, "올바른 형식이 아닙니다");
            } else if (fieldChk != 10) {
               JOptionPane.showMessageDialog(null, "빈 칸을 입력해 주세요");
            }
            else {
               JOptionPane.showMessageDialog(null, "수정완료");
               signUpEnd = false;
        
               if (!(helloText.getText().equals(""))) {
                  UserDB.setPROFILE_TEXT(userID, helloText.getText());
               }
               if (!(pwField.getText().equals(""))) {
                  UserDB.setPW(userID, pwField.getText());
               }
               if (!(phoneDB.equals(""))) {
                  UserDB.setPHONE(userID, phoneDB);
               }
               if (!(emailText.getText().equals(""))) {
                  UserDB.setEMAIL(userID, emailText.getText());
               }
               if (!(addressText.getText().equals(""))) {
                  UserDB.setADDRESS(userID, addressText.getText());
               }
               if (!(cardNumText.equals(""))) {
                  UserDB.setCARDNUMBER(userID, cardNumText);
               }
               if (!(pwhint.getSelectedItem().equals("원하는 질문을 선택하세요."))) {
                  UserDB.setPWHINT(userID, pwhint.getSelectedItem().toString());
               }
               if (!(hintAnswerText.getText().equals(""))) {
                  UserDB.setPWRES(userID, hintAnswerText.getText());
               }
//               setVisible(false);
               ri = null;
               dispose();
            }

         } else if (e.getSource().equals(photoButton)) {
            fileReviseFrame = new FileReviseFrame();
            String fileStr = fileReviseFrame.fd.getDirectory() + "\\" + fileReviseFrame.fd.getFile();

            if (fileStr.substring(fileStr.lastIndexOf(".") + 1).toLowerCase().equals("jpg")) {
               pfio.upload(fileReviseFrame.fd.getDirectory() + "\\" + fileReviseFrame.fd.getFile(), userID);
               ImageIcon ii = new ImageIcon(pfio.download(userID));
               Image img = ii.getImage();
               img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
               ii = new ImageIcon(img);

               photoFileName.setIcon(ii);
               repaint();
            } else if (fileStr.equals("null\\null")) {

            } else if (!fileStr.substring(fileStr.lastIndexOf(".") + 1).toLowerCase().equals("jpg")) {
               JOptionPane.showMessageDialog(null, "jpg파일만 가능합니다");
            }
         }

      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         signUpEnd = false;
         ri = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      // 유효성 검사 쓰레드
      class NormalSignUpChk extends Thread {

         @Override
         public void run() {
            while (signUpEnd) {
               // 비밀번호 유효성 검사 --> 숫자,영문, 특수문자 포함 (공백 금지) ---------------------------------
               if (((!(Pattern.matches(pwRegular, pwField.getText())))
                     || !(Pattern.matches(sRegular, pwField.getText()))) && !pwField.getText().equals("")) {
                  regularChk[0] = false;
                  pwField.setForeground(Color.red);
                  pwField.setOpaque(true);

               } else {
                  regularChk[0] = true;
                  pwField.setForeground(Color.black);

                  // 비밀번호 == 비밀번호 확인 일치한지 검사!!
               }
               if (!(pwField.getText().equals(pwChkText.getText()))) {
                  regularChk[1] = false;
                  pwChkText.setForeground(Color.red);

               } else {
                  regularChk[1] = true;
                  pwChkText.setForeground(Color.black);

                  // 이름 유효성 검사 --> 한글만 사용가능 --------------------------------
               }
               if ((!(Pattern.matches(emailRegular, emailText.getText())))
                     || !(Pattern.matches(sRegular, emailText.getText())) && !emailText.getText().equals("")) {
                  regularChk[2] = false;
                  emailText.setForeground(Color.red);

               } else {
                  regularChk[2] = true;
                  emailText.setForeground(Color.black);

               }
               if ((!(Pattern.matches(phoneNumRegular, phoneNumText_1.getText())))
                     || !(Pattern.matches(sRegular, phoneNumText_1.getText()))
                           && !phoneNumText_1.getText().equals("")) {
                  regularChk[3] = false;
                  phoneNumText_1.setForeground(Color.red);

               } else {
                  regularChk[3] = true;
                  phoneNumText_1.setForeground(Color.black);

               }
               if ((!(Pattern.matches(phoneNumRegular, phoneNumText_2.getText())))
                     || !(Pattern.matches(sRegular, phoneNumText_2.getText()))
                           && !phoneNumText_2.getText().equals("")) {
                  regularChk[4] = false;
                  phoneNumText_2.setForeground(Color.red);

               } else {
                  regularChk[4] = true;
                  phoneNumText_2.setForeground(Color.black);

                  // 카드번호 유효성 검사 --> 글자수 4자 제한, 숫자만 가능 ..--------------------------------
               }
               if ((!(Pattern.matches(cardRegular, cardNumText_1.getText())))
                     || !(Pattern.matches(sRegular, cardNumText_1.getText()))
                           && !cardNumText_1.getText().equals("")) {
                  regularChk[5] = false;
                  cardNumText_1.setForeground(Color.red);

               } else {
                  regularChk[5] = true;
                  cardNumText_1.setForeground(Color.black);

               }
               if ((!(Pattern.matches(cardRegular, cardNumText_2.getText())))
                     || !(Pattern.matches(sRegular, cardNumText_2.getText()))
                           && !cardNumText_2.getText().equals("")) {
                  regularChk[6] = false;
                  cardNumText_2.setForeground(Color.red);

               } else {
                  regularChk[6] = true;
                  cardNumText_2.setForeground(Color.black);

               }
               if ((!(Pattern.matches(cardRegular, cardNumText_3.getText())))
                     || !(Pattern.matches(sRegular, cardNumText_3.getText()))
                           && !cardNumText_3.getText().equals("")) {
                  regularChk[7] = false;
                  cardNumText_3.setForeground(Color.red);

               } else {
                  regularChk[7] = true;
                  cardNumText_3.setForeground(Color.black);

               }
               if ((!(Pattern.matches(cardRegular, cardNumText_4.getText())))
                     || !(Pattern.matches(sRegular, cardNumText_4.getText()))
                           && !cardNumText_4.getText().equals("")) {
                  regularChk[8] = false;
                  cardNumText_4.setForeground(Color.red);

               } else {
                  regularChk[8] = true;
                  cardNumText_4.setForeground(Color.black);

               } // 주소는 한글/영문/숫자만 가능!!(초성금지)
               if ((!(Pattern.matches(addressRegular, addressText.getText().trim().replaceAll(" ", ""))))
                     && !addressText.getText().equals("")) {
                  regularChk[9] = false;
                  addressText.setForeground(Color.red);

               } else {
                  regularChk[9] = true;
                  addressText.setForeground(Color.black);

               }
            }
         }
      }

   }

   class MessagePanel extends JPanel implements ActionListener { // 고객센터_ 관리자에게 메세지 패널

      JLabel msgLabel; // 메세지 라벨
      JButton msgSend; // 보낸 메세지함
      JButton msgGive; // 받은 메세지함
      JButton msgTextBt; // 메세지 보내기
      JTextArea msgText; // 메세지 입력창
      SendMessage sendMessage; // 보낸 메세지함 프레임 클래스
      GiveMessage giveMessage; // 받은 메세지함 프레임 클래스

      JScrollPane msgTextScroll; // 메세지 입력 창 스크롤

      ArrayList<JButton> msgButton; // 메세지 보내기, 받은 메세지함, 보낸메세지함 버튼 리스트

      public MessagePanel() { // 메세지 패널 생성자

         msgButton = new ArrayList<JButton>(); // 메세지 보내기, 받은/보낸 메세지함 버튼 리스트

         msgLabel = new JLabel("관리자에게 메세지"); // 메세지 입력창 라벨
         msgSend = new JButton("보낸 메세지"); // 보낸 메세지함 버튼
         msgGive = new JButton("받은 메세지"); // 받은 메세지함 버튼
         msgTextBt = new JButton("보내기"); // 메세지 보내기 버튼
         msgText = new JTextArea(); // 메세지 입력 창
         msgTextScroll = new JScrollPane(msgText); // 메세지 입력 창 스크롤

         msgText.setLineWrap(true);

         msgButton.add(msgTextBt); // 버튼 리스트에 메세지 보내기 버튼 추가
         msgButton.add(msgSend); // 버튼 리스트에 보낸 메세지함 추가
         msgButton.add(msgGive); // 버튼 리스트에 받은 메세지함 추가

         msgLabel.setBounds(10, 50, 200, 50);
         msgSend.setBounds(30, 100, 200, 40);
         msgGive.setBounds(260, 100, 200, 40);
         msgTextScroll.setBounds(10, 180, 270, 200);
         msgTextBt.setBounds(290, 300, 100, 80);

         for (JButton jb : msgButton) {
            jb.addActionListener(this); // 버튼 리스트에 액션 리스너
         }

         add(msgLabel);
         add(msgSend);
         add(msgGive);
         add(msgTextScroll);
         add(msgTextBt);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(msgButton.get(0))) { // 메세지 보내기 버튼 클릭 했을 때
            if (!(msgText.getText().isEmpty())) {
               MessageDB.saveMESSAGE("admin", userID, msgText.getText());
               JOptionPane.showMessageDialog(null, "전송 완료");
               // 메세지 입력창 초기화
               msgText.setText("");
            } else {
               JOptionPane.showMessageDialog(null, "메세지를 입력하세요");
            }
         } else if (e.getSource().equals(msgButton.get(1))) { // 보낸 메세지함 버튼 클릭 했을 때
            if (sendMessage == null) {
               sendMessage = new SendMessage(this);
               if (!sendMessage.chk) {
                  sendMessage = null;
               }
            }
         } else if (e.getSource().equals(msgButton.get(2))) { // 받은 메세지함 버튼 클릭 했을 때
            if (giveMessage == null) {
               giveMessage = new GiveMessage(this);
               if (!giveMessage.chk) {
                  giveMessage = null;
               }
            }
         }

      }

   }

   SendMessageClick sendMessageClick;

   class SendMessage extends JFrame implements ActionListener, MouseListener, WindowListener { // 보낸메세지함 프레임 클래스

      JTable sendList;
      JScrollPane scroll;
//      JButton delete;

      boolean chk = true;
      String[][] arr2;
      MessagePanel messagePanel;

      public SendMessage(MessagePanel messagePanel) {
         setTitle("보낸 메세지함");
         setBounds(600, 100, 515, 800);
         setLayout(null);
         this.messagePanel = messagePanel;
         arr2 = MessageDB.getFROM_MESSAGE(userID);
         if (arr2 == null) {
            JOptionPane.showMessageDialog(null, "보낸메세지함이 비었습니다.");
            chk = false;
            return;
         }

         String[] arr = new String[] { "받은사람", "내용", "시간" };

         sendList = new JTable(new NotEditTable(arr2, arr));
         scroll = new JScrollPane(sendList);
//         delete = new JButton("삭제");

         scroll.setBounds(0, 150, 500, 500);
//         delete.setBounds(390, 660, 100, 50);

         sendList.addMouseListener(this);
//         delete.addActionListener(this);
         addWindowListener(this);
//         add(delete);
         add(scroll);

         setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {

//         if (e.getSource().equals(delete)) {
//            MessageDB.deleteSendMESSAGE(userID);
//            JOptionPane.showMessageDialog(null, "삭제완료");
//            messagePanel.sendMessage = null;
//            dispose();
//         }

      }

      @Override
      public void mouseClicked(MouseEvent e) {

         if (e.getSource().equals(sendList)) {
            if (e.getClickCount() == 2) {
               System.out.println(sendMessageClick);
               if (sendMessageClick == null) {
                  sendMessageClick = new SendMessageClick();
                  sendMessageClick.writer.setText(arr2[sendList.getSelectedRow()][0]);
                  sendMessageClick.content.setText(arr2[sendList.getSelectedRow()][1]);
                  sendMessageClick.time.setText(arr2[sendList.getSelectedRow()][2]);
               }
            }
         }

      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         messagePanel.sendMessage = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

   }

   class SendMessageClick extends JFrame implements WindowListener {

      JLabel writerLabel;
      JLabel contentLabel;
      JLabel timeLabel;

      JLabel writer;
      JLabel time;
      JTextArea content;

      public SendMessageClick() {

         setBounds(600, 100, 400, 500);
         setLayout(null);
         addWindowListener(this);
         writerLabel = new JLabel("받은사람 : ");
         timeLabel = new JLabel("보낸시간 : ");
         contentLabel = new JLabel("내용");

         writer = new JLabel();
         time = new JLabel();
         content = new JTextArea();

         content.setLineWrap(true);
         content.setEditable(false);

         writerLabel.setBounds(10, 10, 70, 30);
         timeLabel.setBounds(10, 50, 70, 30);
         contentLabel.setBounds(10, 90, 50, 30);

         writer.setBounds(100, 10, 100, 30);
         time.setBounds(100, 50, 150, 30);
         content.setBounds(10, 120, 350, 300);

         add(writerLabel);
         add(timeLabel);
         add(contentLabel);
         add(writer);
         add(time);
         add(content);

         setVisible(true);

      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         sendMessageClick = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

   }

   class NotEditTable extends DefaultTableModel {

      public NotEditTable(final Object[][] rowData, final Object[] columnNames) {
         super(rowData, columnNames);
         // TODO Auto-generated constructor stub
      }

      public boolean isCellEditable(int row, int column) {
         // TODO Auto-generated method stub
         return false;
      }
   }

   GiveMessageClick giveMessageClick; // 현재 떠있는 메시지창

   class GiveMessage extends JFrame implements ActionListener, MouseListener, WindowListener { // 받은메세지함 프레임 클래스

      JTable giveList;
      JScrollPane scroll;
      JButton delete;
      String[][] arr2;
      MessagePanel messagePanel;
      boolean chk = true;

      public GiveMessage(MessagePanel messagePanel) {
         this.messagePanel = messagePanel;
         setTitle("받은 메세지함");
         setBounds(600, 100, 515, 800);
         setLayout(null);

         arr2 = MessageDB.getTO_MESSAGE(userID);
         String[] arr = new String[] { "보낸사람", "내용", "시간" };
         if (arr2 == null) {
            JOptionPane.showMessageDialog(null, "받은메세지함이 비었습니다.");
            chk = false; // 비정상적 생성
            return;
         }
         addWindowListener(this);
         giveList = new JTable(new NotEditTable(arr2, arr));
         scroll = new JScrollPane(giveList);
         delete = new JButton("삭제");

         scroll.setBounds(0, 150, 500, 500);
         delete.setBounds(390, 660, 100, 50);

         delete.addActionListener(this);

         giveList.setCellEditor(null);
         giveList.addMouseListener(this);

         add(delete);
         add(scroll);

         setVisible(true);
      }

      @Override
      public void actionPerformed(ActionEvent e) {

         MessageDB.deleteGiveMESSAGE(userID);
         JOptionPane.showMessageDialog(null, "삭제완료");
         messagePanel.giveMessage = null;
         dispose();

      }

      @Override
      public void mouseClicked(MouseEvent e) {

         if (e.getSource().equals(giveList)) {
            if (e.getClickCount() == 2) {
               if (giveMessageClick == null) {
                  giveMessageClick = new GiveMessageClick();
                  giveMessageClick.writer.setText(arr2[giveList.getSelectedRow()][0]);
                  giveMessageClick.content.setText(arr2[giveList.getSelectedRow()][1]);
                  giveMessageClick.time.setText(arr2[giveList.getSelectedRow()][2]);
               }
            }
         }

      }

      @Override
      public void mouseEntered(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseExited(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mousePressed(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void mouseReleased(MouseEvent e) {
         // TODO Auto-generated method stub

      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         messagePanel.giveMessage = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

   }

   class GiveMessageClick extends JFrame implements WindowListener {

      JLabel writerLabel;
      JLabel contentLabel;
      JLabel timeLabel;

      JLabel writer;
      JLabel time;
      JTextArea content;

      public GiveMessageClick() {

         setTitle("받은메세지");
         setBounds(600, 100, 400, 500);
         setLayout(null);

         writerLabel = new JLabel("보낸사람 : ");
         timeLabel = new JLabel("받은시간 : ");
         contentLabel = new JLabel("내용");

         writer = new JLabel();
         time = new JLabel();
         content = new JTextArea();

         content.setLineWrap(true);
         content.setEditable(false);

         writerLabel.setBounds(10, 10, 70, 30);
         timeLabel.setBounds(10, 50, 70, 30);
         contentLabel.setBounds(10, 90, 50, 30);

         writer.setBounds(100, 10, 100, 30);
         time.setBounds(100, 50, 150, 30);
         content.setBounds(10, 120, 350, 300);

         add(writerLabel);
         add(timeLabel);
         add(contentLabel);
         add(writer);
         add(time);
         add(content);
         addWindowListener(this);
         setVisible(true);

      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         giveMessageClick = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

   }

   class NoticePanel extends JPanel implements ActionListener { // 고객센터_ 공지사항 패널

      Vector<String> notice; // 공지사항 (임시)
      JButton noticeGo; // 공지확인 버튼 (임시)
      JComboBox noticeBox;
      NoticeFrame noticeFrame;
      MessagePanel msgPanel;
      ArrayList<Notice> notices;

      public NoticePanel() {

         notices = NoticeDB.getNOTICE();
         notice = new Vector<String>(); // 벡터.add로 콤보박스 추가
         for (Notice ntc : notices) {
            notice.add(ntc.title);
         }
         if (notice.isEmpty()) {
            notice.add("공지사항없음");
         }
         noticeGo = new JButton("공지확인");
         noticeGo.setBounds(400, 100, 90, 50);

         noticeBox = new JComboBox(notice);
         noticeBox.setBounds(10, 100, 370, 50);

         noticeBox.setBackground(Color.white);

         noticeGo.addActionListener(this);

         add(noticeBox);
         add(noticeGo);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         String title = (String) noticeBox.getSelectedItem();
         Notice ntc = null;
        
         for (Notice ntc1 : notices) {
           
            if (ntc1.title.equals(title)) {
               ntc = ntc1;
               break;
            }
         }
         if (noticeFrame == null) {
            if(noticeBox.getSelectedItem().equals("공지사항없음")) {
               JOptionPane.showMessageDialog(null, "공지사항이 없습니다");
            }else {
            noticeFrame = new NoticeFrame(ntc, this);
            }
         }
      }
   }

   class NoticeFrame extends JFrame implements WindowListener { // 공지 확인 프레임
      JLabel noticeLabel;
      JTextArea contentLabel;
      JLabel writerLabel;
      JLabel modifLabel;
      JLabel makeTimeLabel;

      JLabel title;
      JLabel makeTime;
      JLabel modifTime;
      JLabel writer;
      NoticePanel np;

      public NoticeFrame(Notice notice, NoticePanel np) {

         setTitle("공지사항");

         setBounds(jumTalkOption_User.getX() + jumTalkOption_User.getWidth() + 50, jumTalkOption_User.getY(), 700,
               800);
         setLayout(null);
         this.np = np;
         noticeLabel = new JLabel(notice.title);
         contentLabel = new JTextArea(notice.content);
         writerLabel = new JLabel(notice.writer);
         modifLabel = new JLabel(notice.modi_time);
         makeTimeLabel = new JLabel(notice.maketime);

         title = new JLabel("제목 : ");
         makeTime = new JLabel("작성시간 : ");
         modifTime = new JLabel("수정시간 : ");
         writer = new JLabel("작성자 : ");

         noticeLabel.setBounds(300, 70, 100, 50);
         contentLabel.setBounds(40, 160, 600, 600);
         writerLabel.setBounds(600, 100, 100, 50);
         makeTimeLabel.setBounds(80, 10, 200, 30);
         modifLabel.setBounds(80, 50, 200, 30);
         addWindowListener(this);
         title.setBounds(255, 70, 50, 50);
         makeTime.setBounds(10, 10, 100, 30);
         modifTime.setBounds(10, 50, 100, 30);
         writer.setBounds(530, 100, 100, 50);

         contentLabel.setEditable(false);
         contentLabel.setLineWrap(true);

         add(noticeLabel);
         add(contentLabel);
         add(writerLabel);
         add(modifLabel);
         add(makeTimeLabel);
         add(title);
         add(makeTime);
         add(modifTime);
         add(writer);

         setVisible(true);
      }

      @Override
      public void windowOpened(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowClosing(WindowEvent e) {
         np.noticeFrame = null;

      }

      @Override
      public void windowClosed(WindowEvent e) {

      }

      @Override
      public void windowIconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeiconified(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowActivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }

      @Override
      public void windowDeactivated(WindowEvent e) {
         // TODO 자동 생성된 메소드 스텁

      }
   }
}