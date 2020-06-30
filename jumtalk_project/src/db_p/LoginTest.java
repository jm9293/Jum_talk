package db_p;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.*;



/*--------------------필수사항-----------------------------
 
1. 식별 할 수 있는 번호 기입!!!!

2.회원들이 가입한 정보를 (ID,PW,이름 등등..)  클래스로 일괄처리 to Sting 으로 return 해서 DB에 보낼것 

 */

/*--------------------해야할 것--------------------------

1. 회원가입 인적사항 창을 비워뒀을때 - '@@@은 필수 입력 사항입니다' 창 띄우기  --o
2. 아이디 중복확인 버튼을 눌렀을때 - '사용 가능한 아이디 입니다.'--기본 메세지창 , '이미 사용중인 아이디 입니다' --경고 메세지창  --o
3. JCombox index 0번째 적용안되게 만들기.
4. 새로운 비밀번호 입력창에서 아이디 / 아이디+비밀번호 힌트 답 이 맞지 않을 경우 '정보가 일치하지 않습니다.' --경고 메세지창  --o
5. 로그인 id와 pw가 일치하지 않을경우 '가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.' -- 경고 메세지창   --o
6. 이메일 직접입력 텍스트 안띄우게 해놓기. --o
7. 회원가입 -> 비밀번호 ,비밀번호 확인 두개가 일치하지않으면 회원가입 확인 눌렀을때 '비밀번호를 다시 확인 해주세요' 창 띄우기. ---o
8. 전화번호 ,카드번호 텍스트필드 글자수 제한!!!!!
9. 


*/

/*   DB-----------------------------------------------
 
 //      if(!UserDB.getID("kkk23").equals("")) {
//         System.out.println("아이디 중복입니다");
//      }else{
//         System.out.println("사용가능한 아이디");
//         
//      };
      
//      UserDB.signupNOMALUSER("kkkk123","1234", "이슬아", "19970729", "010-7514-0915", "kkk123@naveer.com", "경기도 안산", "1111-2222-3333-4444", "ㅇㅇㅇ", "ㅇㅇㅇ", 0);
//      UserDB.signupSELLERUSER("", pw, name, birthYYYYMMDD, phone, email, address, pwhint, pwres, businessname, businessaddress, banknum, coin)
      
   }
 
 UserDB.signupNOMALUSER(id, pw, name, birthYYYYMMDD, phone,email,address, cardnumber, pwhint, pwres, coin);
 
 */

//로그인 창
class Login extends JFrame implements ActionListener {

   Signup signUp;
//   String userID = "admin";
   // 아이디 ,비밀번호 텍스트창
   JTextField idtxt;
   JPasswordField pwtxt;

   // 라벨
   JLabel idLabel; // 아이디
   JLabel pwLabel; // 비밀번호

   // 버튼
   JButton login_bt; // 로그인
   JButton idSearch_bt; // 아이디찾기
   JButton pwSearch_bt; // 비밀번호 찾기
   JButton signUp_Bt; // 회원가입

   // 액션리스너
   IDsearch idSearchFrame; // 아이디찾기 프레임클래스
   PWsearch pwSearchFrame; // 비번찾기 프레임클래스
   MemberChoice memberChoiceFrame; // 일반,점술 회원 구분 프레임 클래스

   public Login() {
	   
	   
	   
	 
	   try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
	   
      setTitle("Login");
      setBounds(600, 100, 515, 800);
      setResizable(false);
      setLayout(null);

      
      // 아이디
      idLabel = new JLabel("아이디");
      idLabel.setBounds(60, 400, 100, 50);
      idLabel.setFont(new Font("굴림체", Font.BOLD, 15));
      add(idLabel);

      // 아이디 입력 칸
      idtxt = new JTextField();
      idtxt.setBounds(130, 400, 200, 50);
      add(idtxt);
//   idtxt.setOpaque(false);  //텍스트 상자 투명하게 만들기
//   idtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //뒷배경 투명하게 만들기 (테두리 삭제)

      // 패스워드
      pwLabel = new JLabel("비밀번호");
      pwLabel.setBounds(60, 470, 300, 50);
      pwLabel.setFont(new Font("굴림체", Font.BOLD, 15));
      add(pwLabel);

      // 패스워드 입력
      pwtxt = new JPasswordField();
      pwtxt.setBounds(130, 470, 200, 50);
      add(pwtxt);

      // 로그인
      login_bt = new JButton("로그인");
      login_bt.setBounds(350, 407, 100, 100);
      add(login_bt);
      login_bt.addActionListener(this);

      // id찾기 --액션
      idSearch_bt = new JButton("아이디 찾기");
      idSearch_bt.setBounds(60, 560, 120, 40);
      add(idSearch_bt);
      idSearch_bt.addActionListener(this);

      // pw찾기 --액션
      pwSearch_bt = new JButton("비밀번호 찾기");
      pwSearch_bt.setBounds(190, 560, 120, 40);
      add(pwSearch_bt);
      pwSearch_bt.addActionListener(this);

      // 회원가입 --액션
      signUp_Bt = new JButton("회원가입");
      signUp_Bt.setBounds(320, 560, 130, 40);
      add(signUp_Bt);
      signUp_Bt.addActionListener(this);

      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(idSearch_bt)) { // 아이디찾기 버튼 눌렀을때
         idSearchFrame = new IDsearch();

      } else if (e.getSource().equals(pwSearch_bt)) { // 비밀번호 찾기 버튼 눌렀을때
         pwSearchFrame = new PWsearch();

      } else if (e.getSource().equals(signUp_Bt)) { // 회원가입 버튼 눌렀을때 -> 일반,점술회원 구분프레임 띄우기
         memberChoiceFrame = new MemberChoice();

      } else if (e.getSource().equals(login_bt)) { // 로그인 버튼 눌렀을때
         if (idtxt.getText().isEmpty() || pwtxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력해주세요.  ","로그인 오류", JOptionPane.WARNING_MESSAGE);
         } else if (UserDB.getPW(idtxt.getText()).equals(pwtxt.getText())) {
            JOptionPane.showMessageDialog(null, "            로그인 성공! \n점톡에 오신 것을 환영합니다 ♥");
            setVisible(false);
            new Mainframe(idtxt.getText());

         } else {
            JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 맞지 않습니다.");

         }
      }

   }
   // 아이디찾기

   class IDsearch extends JFrame implements ActionListener {

      // 이름/전화번호 라벨
      JLabel idName_s;
      JLabel idPhone_s;

      // 이름/전화번호 텍스트창
      JTextField idName_t;
      JTextField idPhone_t;
      JTextField idPhone_t2;

      // 아이디찾기 /취소
      JButton idChk_s;
      JButton idCancellation;

      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      


      // 아이디 찾기 프레임 클래스
      ResID resIDFrame;

      public IDsearch() {
         setTitle("아이디 찾기");
         setBounds(600, 230, 500, 600);
         setLayout(null);

         // 이름 입력창
         idName_s = new JLabel("이름");
         idName_s.setBounds(225, 110, 100, 100);
         add(idName_s);
         idName_t = new JTextField();
         idName_t.setBounds(165, 190, 155, 40);
         add(idName_t);

         // 전화번호 입력창

         idPhone_s = new JLabel("전화번호");
         idPhone_s.setBounds(210, 260, 160, 40);
         add(idPhone_s);
         phoneNumber = new Vector<String>();
         phoneNumber.add("010");
         phoneNumber.add("011");
         phoneNumber.add("016");
         phoneNumber.add("017");
         phoneNumber.add("018");
         phoneNumber.add("019");

         pnBox = new JComboBox<String>(phoneNumber);
         pnBox.setBounds(70, 310, 90, 40);
         pnBox.setSelectedItem("010");
         add(pnBox);

         // 핸드폰번호 짝대기
         idPhone_s = new JLabel("─");
         idPhone_s.setBounds(169, 315, 30, 30);
         add(idPhone_s);
         idPhone_s = new JLabel("─");
         idPhone_s.setBounds(287, 315, 30, 30);
         add(idPhone_s);

         // 핸드폰번호 중간, 뒷자리
         idPhone_t = new JTextField();
         idPhone_t.setBounds(190, 310, 90, 40);
         add(idPhone_t);
         idPhone_t2 = new JTextField();
         idPhone_t2.setBounds(310, 310, 90, 40);
         add(idPhone_t2);

         // 아이디찾기 버튼
         idChk_s = new JButton("아이디 찾기");
         idChk_s.setBounds(100, 430, 120, 50);
         add(idChk_s);
         idChk_s.addActionListener(this);

         // 취소버튼
         idCancellation = new JButton("취소");
         idCancellation.setBounds(280, 430, 100, 50);
         add(idCancellation);
         idCancellation.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(idChk_s)) { // 아이디찾기 버튼 눌렀을때
               
            resIDFrame = new ResID();
            
            setVisible(false);


         } else if (e.getSource().equals(idCancellation)) { // 취소 버튼 눌렀을때

            setVisible(false);

         }

      }

      // 찾을 아이디 창 띄우기
      class ResID extends JFrame implements ActionListener {

         Signup signup;

         JLabel idMessage;
         JLabel resID; // 아이디
         JLabel idLabel; // 찾는 아이디 라벨

         JButton newIDChk;
         JButton newpwChk;
         
         PWsearch pwsearch;

         String userNAME = idName_t.getText();
         String userPHONE = pnBox.getSelectedItem() + "-" + idPhone_t.getText() + "-" + idPhone_t2.getText();

         public ResID() {

            setTitle("아이디 찾기");
            setBounds(600, 300, 500, 350);
            setLayout(null);

            // 아이디 찾았을경우
            idMessage = new JLabel("회원님의 아이디는 ");
            idMessage.setBounds(100, 40, 300, 200);
            add(idMessage);
            
            //아이디찾기 --> 이름과 전화번호가 일치하지 않을경우 
            if(UserDB.searchID(userNAME, userPHONE).equals("")) {
               
               JOptionPane.showMessageDialog(null, "일치하지 않은 정보입니다.  ", "아이디 찾기", JOptionPane.ERROR_MESSAGE);
               
               
            // 일치하면 ResID클래스 (아이디 찾기) 창 띄움 !!
            }else {
               
               resID = new JLabel(UserDB.searchID(userNAME, userPHONE));
               resID.setBounds(230, 40, 280, 200);
               add(resID);
               
               setVisible(true);

            }

            idLabel = new JLabel("입니다.");
            idLabel.setBounds(300, 40, 300, 200);
            add(idLabel);

            // 로그인하기 버튼
            newIDChk = new JButton("로그인 하기");
            newIDChk.setBounds(60, 220, 130, 40);
            add(newIDChk);
            newIDChk.addActionListener(this);
            
            //아이디찾기 끝나면  비밀번호 찾기 
            newpwChk = new JButton("비밀번호 찾기");
            newpwChk.setBounds(300, 220, 130, 40);
            add(newpwChk);
            newpwChk.addActionListener(this);
         }

         @Override
         public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(newIDChk)) { // 로그인 버튼 눌렀을때
               
               setVisible(false);
               
               
            }else if(e.getSource().equals(newpwChk)) {
               
               pwsearch = new PWsearch();
               
               setVisible(false);
               
            }

         }

      }

   }

   // 비밀번호 찾기

   class PWsearch extends JFrame implements ActionListener {

      JLabel idSearch; // 아이디 입력
      JLabel pwHint_S; // 비밀번호 힌트
      JLabel pwAnswer_S; // 비밀번호 답

      JTextField idSearch_t; // 아이디 텍스트창
      JTextField pwSearch_t; // 비밀번호 텍스트창
      Vector<String> passWordHint_S; // 비밀번호 힌트
      JComboBox<String> hintBox_S; // 비밀번호 힌트 comboBox

      JButton passWordChk; // 비밀번호 찾기
      JButton pwCancellation; // 취소 버튼

      // 액션리스너
      PassWordFind passWordFind; // 비밀번호 알려주는 창
      ArrayList<JTextField> textFieldList_2; // 텍스트창이 입력됐을때 추가해주는 리스트 (회원가입 텍스트필드에 입력이 안되면 add안함)
                                    // 회원가입 빈칸여부를 확인해주는 리스트 --갯수 확인하는 곳에서만 써야함

      public PWsearch() {
         setTitle("비밀번호 찾기");
         setBounds(600, 230, 500, 600);
         setLayout(null);

         // 아이디 입력칸
         idSearch = new JLabel("아이디");
         idSearch.setBounds(225, 70, 100, 100);
         add(idSearch);
         idSearch_t = new JTextField();
         idSearch_t.setBounds(165, 150, 155, 40);
         add(idSearch_t);

         // 비밀번호 힌트 칸
         pwHint_S = new JLabel("비밀번호 힌트");
         pwHint_S.setBounds(205, 220, 100, 30);
         add(pwHint_S);

         passWordHint_S = new Vector<String>();
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

         hintBox_S = new JComboBox<String>(passWordHint_S);
         hintBox_S.setBounds(130, 260, 230, 40);
//         hintBox_S.setSelectedItem("원하는 질문을 선택하세요."); // 다음으로 고정
         add(hintBox_S);

         // 비밀번호 힌트 답 입력 칸
         pwAnswer_S = new JLabel("비밀번호 힌트 답");
         pwAnswer_S.setBounds(200, 320, 230, 40);
         add(pwAnswer_S);

         pwSearch_t = new JTextField();
         pwSearch_t.setBounds(130, 370, 240, 40);
         add(pwSearch_t);

         // 비밀번호 찾기
         passWordChk = new JButton("비밀번호 찾기");
         passWordChk.setBounds(100, 470, 120, 50);
         add(passWordChk);
         passWordChk.addActionListener(this);

         // 취소버튼
         pwCancellation = new JButton("취소");
         pwCancellation.setBounds(280, 470, 100, 50);
         add(pwCancellation);
         pwCancellation.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(passWordChk)) { // 비밀번호찾기 버튼 눌렀을때

            passWordFind = new PassWordFind();

            setVisible(false);

         } else if (e.getSource().equals(pwCancellation)) { // 취소 버튼 눌렀을때
            setVisible(false);

         }

      }

      // 비밀번호 찾기

      class PassWordFind extends JFrame implements ActionListener {

         JLabel pwMessage;
         JLabel resPW;
         JLabel pwLabel;

         // 확인 취소
         JButton newPassWordChk; // 확인
         JButton newPassWordCan; // 취소

         Signup signup;
         
         
         String userID = idSearch_t.getText();
         String userPWHINT = hintBox_S.getSelectedItem().toString();
         String userPWRES = pwSearch_t.getText();

         public PassWordFind() {

            setTitle("비밀번호 찾기");
            setBounds(600, 300, 500, 350);
            setLayout(null);

            // 비밀번호 찾기
            pwMessage = new JLabel("회원님의 비밀번호는 ");
            pwMessage.setBounds(100, 40, 300, 200);
            add(pwMessage);
            
            
            //아이디와, 비밀번호 힌트, 답이 일치하지 않을 경우 
            if(UserDB.searchPW(userID, userPWHINT, userPWRES).equals("")) {
               JOptionPane.showMessageDialog(null, "일치하지 않은 정보입니다.", "비밀번호 찾기", JOptionPane.ERROR_MESSAGE);
               
            // 아이디, 비밀번호 힌트, 답 이 일치 할 경우!! --> passWordFind클래스 창 띄우기!!   
            }else {
               
               resPW = new JLabel(UserDB.searchPW(userID, userPWHINT, userPWRES));
               resPW.setBounds(230, 40, 280, 200);
               add(resPW);
               setVisible(true);
            }
               

            pwLabel = new JLabel("입니다.");
            pwLabel.setBounds(300, 40, 300, 200);
            add(pwLabel);

            // 확인 취소 버튼
            newPassWordChk = new JButton("확인");
            newPassWordChk.setBounds(90, 280, 150, 40);
            add(newPassWordChk);
            newPassWordChk.addActionListener(this);

            newPassWordCan = new JButton("취소");
            newPassWordCan.setBounds(350, 280, 150, 40);
            add(newPassWordCan);
            newPassWordCan.addActionListener(this);

         }

         @Override
         public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(newPassWordChk)) { // 확인 버튼 눌렀을때

               setVisible(false);

            } else if (e.getSource().equals(newPassWordCan)) { // 취소 버튼 눌렀을때
               setVisible(false);

            }

         }

      }

   }

   // 일반회원 , 사업자회원 회원가입 버튼창
   class MemberChoice extends JFrame implements ActionListener {

      JButton memberG; // 일반회원 버튼
      JButton memberB; // 점술회원 버튼

      // 액션리스너
      Signup signupFrame; // 일반 회원가입 프레임 클래스
      SignUp_p signUp_p_Frame; // 점술 회원가입 프레임 클래스

      public MemberChoice() {

         setTitle("회원가입 ");
         setBounds(610, 300, 500, 500);
         setLayout(null);

         memberG = new JButton("일반회원");
         memberG.setBounds(60, 180, 100, 70);
         add(memberG);
         memberG.addActionListener(this);

         memberB = new JButton("점술회원");
         memberB.setBounds(300, 180, 100, 70);
         add(memberB);
         memberB.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(memberG)) { // 일반회원 버튼 눌렀을때
            signupFrame = new Signup();
            setVisible(false);

         } else if (e.getSource().equals(memberB)) { // 점술회원 버튼 눌렀을때
            signUp_p_Frame = new SignUp_p();
            setVisible(false);

         }

      }

   }

   // 일반회원 회원가입

   class Signup extends JFrame implements ActionListener {

      boolean idChkBl = false; // 아이디 중복체크
//         boolean pwChkBl = true;   //비밀번호랑 비밀번호확인이랑 맞는지 비교

      // 라벨
      JLabel id_L; // 아이디
      JLabel pw_L; // 비밀번호
      JLabel passWord_L; // 비밀번호 확인
      JLabel pwHint_L; // 비밀번호 힌트
      JLabel pwHintAnswer_L; // 비밀번호 힌트 답
      JLabel name_L; // 이름
      JLabel gender_L; // 성별
      JLabel birthDate_L; // 생년월일
      JLabel phoneNumber_L; // 휴대폰번호
      JLabel email_L; // 이메일
      JLabel address_L; // 주소
      JLabel cardNumber_L; // 카드번호

      // 라벨끼리 묶음
      ArrayList<JLabel> information;

      // 버튼
      JButton check; // 확인
      JButton cancellation; // 취소

      // 어레이리스트에 텍스트필드를 추가하고 빈칸일 경우 다시 입력하라는 창 띄우기.
      ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
      ArrayList<JTextField> textFieldList_2;

      // 텍스트 창
      JTextField id_t; // 아이디
      JPasswordField pw_t; // 비밀번호
      JPasswordField passWord; // 비밀번호 확인
      JTextField pwHintAnswer; // 비밀번호 힌트 답
      JTextField name_t; // 이름
      JTextField pnNum1; // 핸드폰번호 가운데
      JTextField pnNum2; // 핸드폰번호 뒷자리
      JTextField email_1; // 이메일 앞자리
      JTextField email_2; // 이메일 뒷자리
      JTextField address_t; // 주소

      // 아이디 중복확인 버튼
      JButton idCheck;

      // 성별
      ButtonGroup gender_bt = new ButtonGroup();
      JRadioButton man_bt; // 남자버튼
      JRadioButton woman_bt; // 여자버튼

      // 비밀번호 힌트
      Vector<String> passWordHint;
      JComboBox<String> hintBox;

      // 년도
      Vector<String> year;
      JComboBox<String> yearBox;
      JLabel yearLabel;

      // 월
      Vector<String> month;
      JComboBox<String> monthBox;
      JLabel monthLabel;

      // 일
      Vector<String> day;
      JComboBox<String> dayBox;
      JLabel dayLabel;

      // 휴대폰번호 앞
      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      JLabel pnLabel;
      JLabel pnLabe2;

      // 휴대폰번호 통신사
//         ButtonGroup mobile_bt = new ButtonGroup();
//         JCheckBox mobileCompany1;
//         JCheckBox mobileCompany2;
//         JCheckBox mobileCompany3;

      // 이메일
      Vector<String> email_V; // 이메일 종류
      JComboBox<String> emailbox;

      // 카드번호 텍스트창
      JButton cardCompany; // 카드이름 버튼
      JTextField cardNum_1;
      JTextField cardNum_2;
      JTextField cardNum_3;
      JTextField cardNum_4;
      JLabel cardNum_L1;
      JLabel cardNum_L2;
      JLabel cardNum_L3;

      // 액션리스너
//         CardChoice cardChoiceFrame; //카드선택 프레임클래스

      public Signup() {

         setTitle("일반 회원정보 입력 ");
         setBounds(600, 0, 750, 1000);
         setLayout(null);

         information = new ArrayList<JLabel>();

         // 아이디
         id_L = new JLabel("아이디 ");
//         id_L.setBounds(20,90,50,30);  
         information.add(id_L);

         // 아이디 텍스트창
         id_t = new JTextField();
         id_t.setBounds(140, 93, 200, 40);
         add(id_t);
         textFieldList.add(id_t);

         // 아이디 중복확인 버튼
         idCheck = new JButton("중복확인");
         idCheck.setBounds(360, 95, 110, 35);
         add(idCheck);
         idCheck.addActionListener(this);

         // 비밀번호
         pw_L = new JLabel("비밀번호 ");
//         pw_L.setBounds(20,145,100,30);  
         information.add(pw_L);

         // 비밀번호 텍스트창
         pw_t = new JPasswordField();
         pw_t.setBounds(140, 148, 200, 40);
         add(pw_t);

         // 비밀번호 확인
         passWord_L = new JLabel("비밀번호 확인");
//         passWord_L.setBounds(20,200,100,30);  
         information.add(passWord_L);

         // 비밀번호 확인 텍스트창
         passWord = new JPasswordField();
         passWord.setBounds(140, 203, 200, 40);
         add(passWord);

         // 비밀번호 힌트
         pwHint_L = new JLabel("비밀번호 힌트");
//         pwHint_L.setBounds(20,255,100,30);  
         information.add(pwHint_L);

         // 비밀번호 힌트 내용
         passWordHint = new Vector<String>();
         passWordHint.add("원하는 질문을 선택하세요.");
         passWordHint.add("가장 기억에 남는 장소는?");
         passWordHint.add("나의 보물 1호는?");
         passWordHint.add("본인의 출생지는?");
         passWordHint.add("내가 존경하는 인물은?");
         passWordHint.add("나의 좌우명은?");
         passWordHint.add("가장 감명깊게 본 영화는?");
         passWordHint.add("내가 좋아하는 뮤지션은?");
         passWordHint.add("인상깊게 읽은 책 제목은?");
         passWordHint.add("나의 노래방 애창곡은?");

         hintBox = new JComboBox<String>(passWordHint);
         hintBox.setBounds(140, 260, 230, 40);
         // box.setSelectedIndex(2);
//         hintBox.setSelectedItem("원하는 질문을 선택하세요."); // 다음으로 고정
         add(hintBox);

         // 힌트 답
         pwHintAnswer_L = new JLabel("비밀번호 힌트 답");
//         pwHintAnswer_L.setBounds(20,310,100,30);  
         information.add(pwHintAnswer_L);

         // 힌트 답 텍스트창
         pwHintAnswer = new JTextField();
         pwHintAnswer.setBounds(140, 315, 230, 40);
         add(pwHintAnswer);
         textFieldList.add(pwHintAnswer);

         // 이름
         name_L = new JLabel("이름 ");
//         name_L.setBounds(20,365,100,30);  
         information.add(name_L);

         // 이름 텍스트창
         name_t = new JTextField();
         name_t.setBounds(140, 368, 200, 40);
         add(name_t);
         textFieldList.add(name_t);

         // 성별
         gender_L = new JLabel("성별 ");
//         gender_L.setBounds(20,420,100,30);  
         information.add(gender_L);

         // 성별 버튼
         man_bt = new JRadioButton("남자");
         man_bt.setBounds(140, 425, 80, 35);
         gender_bt.add(man_bt);
         add(man_bt);
         man_bt.addActionListener(this);

         woman_bt = new JRadioButton("여자");
         woman_bt.setBounds(270, 425, 80, 35);
         gender_bt.add(woman_bt);
         add(woman_bt);
         woman_bt.addActionListener(this);

         // 생년월일
         birthDate_L = new JLabel("생년월일 ");
//         birthDate_L.setBounds(20,475,100,30);  
         information.add(birthDate_L);

         // 년도
         year = new Vector<String>();
         year.add("년도");
         for (int i = 2018; i >= 1940; i--) {
            year.add(i + "");
         }
         yearLabel = new JLabel("년");
         yearLabel.setBounds(250, 486, 30, 30);
         add(yearLabel);

         yearBox = new JComboBox<String>(year);
         yearBox.setBounds(140, 480, 100, 35);
         yearBox.setSelectedItem("년도");
         add(yearBox);

         // 월
         month = new Vector<String>();
         month.add("월");

         for (int i = 1; i <= 12; i++) {
            if (i < 10) {
               month.add("0" + i);
            } else {
               month.add(i + "");

            }
         }

         monthBox = new JComboBox<String>(month);
         monthBox.setBounds(280, 480, 100, 35);
         monthBox.setSelectedItem("월");
         add(monthBox);

         monthLabel = new JLabel("월");
         monthLabel.setBounds(390, 486, 30, 30);
         add(monthLabel);

         // 일
         day = new Vector<String>();
         day.add("일");
         for (int i = 1; i <= 31; i++) {
            if (i < 10) {
               day.add("0" + i);

            } else {
               day.add(i + "");
            }

         }

         dayBox = new JComboBox<String>(day);
         dayBox.setBounds(420, 480, 100, 35);
         dayBox.setSelectedItem("일");
         add(dayBox);

         dayLabel = new JLabel("일");
         dayLabel.setBounds(530, 486, 30, 30);
         add(dayLabel);
         
         
         // 전화번호
         phoneNumber_L = new JLabel("전화번호");
//         phoneNumber_L.setBounds(20,540,100,30);  
         information.add(phoneNumber_L);

         // 핸드폰번호 앞자리
         phoneNumber = new Vector<String>();
         phoneNumber.add("010");
         phoneNumber.add("011");
         phoneNumber.add("016");
         phoneNumber.add("017");
         phoneNumber.add("018");
         phoneNumber.add("019");

         pnBox = new JComboBox<String>(phoneNumber);
         pnBox.setBounds(140, 535, 90, 40);
         pnBox.setSelectedItem("010");
         add(pnBox);

         // 핸드폰번호 짝대기
         pnLabel = new JLabel("─");
         pnLabel.setBounds(240, 540, 30, 30);
         add(pnLabel);
         pnLabe2 = new JLabel("─");
         pnLabe2.setBounds(365, 540, 30, 30);
         add(pnLabe2);

         // 핸드폰번호 중간, 뒷자리
         pnNum1 = new JTextField();
         pnNum1.setBounds(265, 535, 90, 40);
         add(pnNum1);
         textFieldList.add(pnNum1);
         pnNum2 = new JTextField();
         pnNum2.setBounds(390, 535, 90, 40);
         add(pnNum2);
         textFieldList.add(pnNum2);

         
         // 핸드폰 통신사 선택
//         mobileCompany1 = new JCheckBox("LGU+");
//         mobileCompany2 = new JCheckBox("KT");
//         mobileCompany3 = new JCheckBox("SKT");
         //
//         mobileCompany1.setBounds(500, 535, 60, 40);
//         mobileCompany2.setBounds(570, 535, 40, 40);
//         mobileCompany3.setBounds(630, 535, 60, 40);
         //
         //
//         mobile_bt.add(mobileCompany1);
//         mobile_bt.add(mobileCompany2);
//         mobile_bt.add(mobileCompany3);
//         add(mobileCompany1);
//         add(mobileCompany2);
//         add(mobileCompany3);

         // 이메일
         email_L = new JLabel("E-mail");
//         email_L.setBounds(20,595,100,30);  
         information.add(email_L);

         // 이메일 텍스트창
         email_1 = new JTextField();
         email_1.setBounds(140, 590, 170, 40);
         add(email_1);
         textFieldList.add(email_1);

         JLabel e1 = new JLabel(" @ ");

         e1.setBounds(320, 593, 120, 35);
         add(e1);

         // 이메일 두번째 텍스트창
         email_2 = new JTextField();
         email_2.setBounds(350, 590, 170, 40);
         add(email_2);
         textFieldList.add(email_2);

         email_V = new Vector<String>(); // email창 목록 생성
         email_V.add("직접입력");
         email_V.add("naver.com");
         email_V.add("nate.com");
         email_V.add("hanmail.net");
         email_V.add("yahoo.co.kr");
         email_V.add("daum.net");
         email_V.add("gmail.com");

         // 이메일 주소
         emailbox = new JComboBox<String>(email_V);
         emailbox.setBounds(540, 590, 160, 35);
         emailbox.setSelectedItem("직접입력"); // 다음으로 고정
         add(emailbox);
         emailbox.addActionListener(this);

         // 카드번호
         cardNumber_L = new JLabel("카드번호");
//         cardNumber_L.setBounds(20,650,100,30);  
         information.add(cardNumber_L);

         // 카드선택 버튼
//         cardCompany = new JButton("카드선택");
//         cardCompany.setBounds(140,650,100,35);
//         add(cardCompany);
//         cardCompany.addActionListener(this);

         // 카드 번호 입력창
         cardNum_1 = new JTextField();
         cardNum_2 = new JTextField();
         cardNum_3 = new JTextField();
         cardNum_4 = new JTextField();
         textFieldList.add(cardNum_1);
         textFieldList.add(cardNum_2);
         textFieldList.add(cardNum_3);
         textFieldList.add(cardNum_4);

         // 카드 번호
         cardNum_L1 = new JLabel("─");
         cardNum_L2 = new JLabel("─");
         cardNum_L3 = new JLabel("─");

         cardNum_1.setBounds(140, 650, 70, 35);
         cardNum_2.setBounds(240, 650, 70, 35);
         cardNum_3.setBounds(340, 650, 70, 35);
         cardNum_4.setBounds(440, 650, 70, 35);

         cardNum_L1.setBounds(215, 650, 20, 30);
         cardNum_L2.setBounds(315, 650, 20, 30);
         cardNum_L3.setBounds(415, 650, 20, 30);

         add(cardNum_1);
         add(cardNum_2);
         add(cardNum_3);
         add(cardNum_4);
         add(cardNum_L1);
         add(cardNum_L2);
         add(cardNum_L3);

         // 주소
         address_L = new JLabel("주소 ");
//         address_L.setBounds(20,705,100,30);  
         information.add(address_L);

         // 주소 텍스트창
         address_t = new JTextField();
         address_t.setBounds(140, 700, 400, 40);
         add(address_t);
         textFieldList.add(address_t);

         // 확인 취소 버튼
         check = new JButton("확인");
         check.setBounds(130, 820, 200, 50);
         add(check);
         check.addActionListener(this);

         cancellation = new JButton("취소");
         cancellation.setBounds(410, 820, 200, 50);
         add(cancellation);
         cancellation.addActionListener(this);

         // 라벨 간격 맞추기
         for (int i = 0; i < information.size(); i++) {
            information.get(i).setBounds(20, i * 55 + 100, 100, 30);

         }

         for (JLabel jL : information) {
            add(jL);
         }

//         textFieldList.add(id_t);
//         textFieldList.add(pwHintAnswer);
//         textFieldList.add(name_t);
//         textFieldList.add(email_1);
//         textFieldList.add(email_2);
//         textFieldList.add(cardNum_1);
//         textFieldList.add(cardNum_2);
//         textFieldList.add(cardNum_3);
//         textFieldList.add(cardNum_4);
//         textFieldList.add(address_t);
//         textFieldList.add(pnNum1);
//         textFieldList.add(pnNum2);

//         System.out.println(textFieldList.size());
         setVisible(true);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         textFieldList_2 = new ArrayList<JTextField>();

         for (int i = 0; i < textFieldList.size(); i++) {
            if (textFieldList.get(i).getText().length() > 0) {
               textFieldList_2.add(textFieldList.get(i));
            }
         }

         // DB연동
         String id = id_t.getText();
         String pw = pw_t.getText();
         String pwChkStr = passWord.getText();
         String name = name_t.getText();
         String gender = "";
         String phone = pnBox.getSelectedItem() + "-" + pnNum1.getText() + "-" + pnNum2.getText();
         String birthYYYYMMDD = yearBox.getSelectedItem().toString() + monthBox.getSelectedItem().toString()
               + dayBox.getSelectedItem().toString();
         String email = email_1.getText() + "@" + email_2.getText();
         String address = address_t.getText();
         String cardnumber = cardNum_1.getText() + "-" + cardNum_2.getText() + "-" + cardNum_3.getText() + "-"
               + cardNum_4.getText();
         String pwhint = hintBox.getSelectedItem().toString();
         String pwres = pwHintAnswer.getText();
         int coin = 0;
         boolean hintChk = hintBox.getSelectedIndex()==0;
         boolean dayChk = yearBox.getSelectedIndex() == 0 || monthBox.getSelectedIndex() == 0 
               || dayBox.getSelectedIndex() == 0;
         
         
         
         // 확인버튼 눌렀을때

//            if(e.getSource().equals(check)) {
//               //텍스트 필드를 다 입력하지 않았을때 
//               }else if(cnt==0) {
//                  JOptionPane.showMessageDialog(null, "회원정보를 입력해주세요");   
//               
//               
//               }else if(!(pw_t.getText().equals(passWord.getText()))){
//                  JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.","오류", JOptionPane.WARNING_MESSAGE);
//                  pwChkBl =true;
//                  
//               }else if(idChkBl == true && pwChkBl == true && cnt >0 ) {
//                  UserDB.signupNOMALUSER(id, pw, name, gender, birthYYYYMMDD, phone,email,address, cardnumber, pwhint, pwres, coin);
//                  JOptionPane.showMessageDialog(null, "가입완료");
//                  setVisible(false);
//                  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//               }

         // 스크롤에 있는 이메일을 누르면 이메일 두번째 창에 바로 입력되는 액션 리스너
         if (e.getSource().equals(emailbox)) {
            
            //직접입력 외에 이메일 입력했을때
            if (!((String) emailbox.getSelectedItem()).equals("직접입력")) {
               email_2.setText((String) emailbox.getSelectedItem());  //텍스트창에 이메일을 띄움
               //직접입력일 경우 텍스트창을 비워둠.
            } else {
               email_2.setText("");
            }

         }

         // 1) 취소버튼 눌렀을때 ------------------------------------
         if (e.getSource().equals(cancellation)) {

            setVisible(false); // 창닫기

            // 2) 아이디 중복확인 버튼 눌렀을때----------------------------------------
         } else if (e.getSource().equals(idCheck)) {

            // 아이디텍스트 창이 비어있을때 -- 아이디 입력해주세요
            if (id_t.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.","회원가입 ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;
               
               // 사용자가 입력한 아이디와 UserDB에 id와 같으면 -- 중복임을 알림
            } else if (id_t.getText().equals(UserDB.getID(id))) {

               JOptionPane.showMessageDialog(null, " 이미 사용중인 아이디입니다. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;

               // 나머지가 다 아니라면 사용가능 !!
            } else {
               idChkBl = true;
               JOptionPane.showMessageDialog(null, " 사용가능한 아이디입니다. ");
            }

            // 3) 확인 버튼 눌렀을때 ------------------------------------
         } else if (e.getSource().equals(check)) {
            System.out.println("체크!");

            // 텍스트필드창 갯수 확인
//            System.out.println(textFieldList.size()); 
            System.out.println(textFieldList_2.size());

            // 성별을 눌렀을때 남자,여자 확인
            if (man_bt.isSelected()) {
               System.out.println("남자");
               gender = "남자";

            } else if (woman_bt.isSelected()) {
               System.out.println("여자");
               gender = "여자";

               // 사용자 비밀번호와 사용자 비밀번호 확인이 같지 않을때 -- 다시 확인해달라는 창을 띄움.
            }
            if (!(pw_t.getText().equals(passWord.getText()))) {

               JOptionPane.showMessageDialog(null, " 비밀번호를 다시 확인해주세요. ", "회원가입 ", JOptionPane.WARNING_MESSAGE);

               // 텍스트필드 창 갯수가 12개보다 적게 입력했을때 --- 회원정보 입력하라는 창 띄움.
            } else if (textFieldList_2.size() < 12) {
               System.out.println("회원정보");
               JOptionPane.showMessageDialog(null, " 회원정보를 입력해주세요. ","회원가입", JOptionPane.WARNING_MESSAGE);

               // 아이디 중복체크를 하지않고 회원가입하는것을 방지 -- 중복확인을 누르지 않고 회원가입확인을 누르면 중복확인을 해달라는 창을 띄움.
            } else if (idChkBl == false) {

               JOptionPane.showMessageDialog(null, " 중복확인을 해주세요.","회원가입 ", JOptionPane.WARNING_MESSAGE);

               //비밀번호 힌트를 선택 안했을 때 true
            } else if(hintChk == true) {   
               JOptionPane.showMessageDialog(null, " 비밀번호 힌트를 선택해주세요. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               //생년월일을 선택 안했을 때  true
            }else if(dayChk == true) {  
               JOptionPane.showMessageDialog(null, " 생년월일을 선택해주세요. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               //성별을 선택 안했을 때 true
            }else if(gender == "") {
               JOptionPane.showMessageDialog(null, " 성별을 선택해주세요. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               
            }
            // 다 오류가 아닐경우 가입조건에 충족되어 가입완료 창을 띄움.
            else {
               System.out.println(textFieldList.size());
               UserDB.signupNOMALUSER(id, pw, name, gender, birthYYYYMMDD, phone, email, address, cardnumber,
                     pwhint, pwres, coin);
               UserDB.setFORTUNE_TIME(id, "20100101");
               JOptionPane.showMessageDialog(null, " 회원가입 성공 ! ");
               setVisible(false);
//               setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
         }

//             if(idChkBl == true && pwChkBl == true && textFieldList_2.size() == 12)
      }
   }

   // 카드이름 선택 창
//      class CardChoice extends JFrame implements ActionListener{
//         
//         Signup signup;
//         
//         //카드이름 버튼 패널
//         JPanel cardPanel;
//         
//         //'이용하실 카드를 선택해 주세요' 라벨
//         JLabel cardChoice_L;
//         //'그 외의 카드는 기타카드에서 선택해주세요'
//         JLabel cardChoice_L1;
//         
//         //카드이름 버튼
//         ArrayList<JButton> cardName;
//         
//         //기타카드 
//         Vector<String> otherCardName;
//         JComboBox<String> otherCardName2;
//         
//         //확인 ,취소
//         JButton cardNumChk; //확인
//         JButton cardNumCan; //취소
//         
//         
//         public CardChoice() {
//            
//            setTitle("카드 선택");
//            setBounds(610, 300, 500,500);
//            setLayout(null);
//            
//            //라벨 
//            cardChoice_L = new JLabel("이용하실 카드사를 선택해 주세요.");
//            cardChoice_L.setBounds(155, 40, 300, 50);
//            add(cardChoice_L);
//            cardChoice_L1 = new JLabel("그 외의 카드는 기타카드에서 선택하세요.");
//            cardChoice_L1.setBounds(30, 280, 300, 50);
//            add(cardChoice_L1);
//            //카드사 버튼
//            cardName = new ArrayList<JButton>();
//            for (JButton cardArr : cardName) {
//               cardArr.addActionListener(this);
//            }
//            //카드사 버튼 패널
//            cardPanel = new JPanel();
//            cardPanel.setBounds(65, 80, 360, 200);
//         
//            
//            cardPanel.add(new JButton("삼성"));
//            cardPanel.add(new JButton("현대 "));
//            cardPanel.add(new JButton("신한"));
//            cardPanel.add(new JButton("NH채움"));
//            cardPanel.add(new JButton("BC"));
//            cardPanel.add(new JButton("롯데"));
//            cardPanel.add(new JButton("KB국민"));
//            cardPanel.add(new JButton("하나"));
//            cardPanel.add(new JButton("한국씨티"));
////            cardPanel.add(new JButton("하나(외환)"));
//            add(cardPanel);
//            
//            otherCardName = new Vector<String>();
//            otherCardName.add("-그외의카드-");
//            otherCardName.add("우리");
//            otherCardName.add("수협");
//            otherCardName.add("광주");
//            otherCardName.add("제주");
//            otherCardName.add("전북");
//            otherCardName.add("신협");
//            otherCardName.add("우체국");
//            otherCardName.add("해외VISA");
//            otherCardName.add("해외MASTER");
//            
//            otherCardName2 = new JComboBox<String>(otherCardName);
//            otherCardName2.setBounds(280,286,150,35);
//            //box.setSelectedIndex(2);
//            otherCardName2.setSelectedItem("-그외의 카드-");  
//            add(otherCardName2);
//
//            cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
////            setResizable(false);
//
//            for (JButton jBu : cardName) {
//               
//               add(jBu);
//            }
//            
//            // 확인 
//            cardNumChk = new JButton("확인");
//            cardNumChk.setBounds(100, 380, 100, 40);
//            add(cardNumChk);
//            cardNumChk.addActionListener(this);
//            
//            //취소
//            cardNumCan = new JButton("취소");
//            cardNumCan.setBounds(280, 380, 100, 40);
//            add(cardNumCan);
//            cardNumCan.addActionListener(this);
//            
//            
//            
//            setVisible(true);
//         }
//
//
//         @Override
//         public void actionPerformed(ActionEvent e) {
//            
//            
//            
//            String cardKind = "";
//               
//               for (JButton cards : cardName) {
//                  
//                  if(e.getSource().equals(cards)) {
//                     cardKind = cards.getText();
//                     System.out.println(cardKind);
//                     setVisible(false);
//                  //확인버튼 눌렀을때
//                  }else if(e.getSource().equals(cardNumChk)) {
//                     setVisible(false);
//
//                  
//               
//               }      
//         
//            //확인버튼 눌렀을때
//            if(e.getSource().equals(cardNumChk)) {
//            
//            //취소버튼 눌렀을때
//            }else if(e.getSource().equals(cardNumCan)) {
//               setVisible(false);
//                  
//            }
//            
//            
//               }
//         
//
//      }
//

   // 사업자 회원가입
   class SignUp_p extends JFrame implements ActionListener {

      boolean idChkBl = false; // 아이디 중복체크

      // 라벨
      JLabel id_L; // 아이디
      JLabel pw_L; // 비밀번호
      JLabel passWord_L; // 비밀번호 확인
      JLabel pwHint_L; // 비밀번호 힌트
      JLabel pwHintAnswer_L; // 비밀번호 힌트 답
      JLabel name_L; // 이름
      JLabel gender_L; // 성별
      JLabel birthDate_L; // 생년월일
      JLabel phoneNumber_L; // 휴대폰번호
      JLabel address_L; // 주소
      JLabel email_L; // 이메일
      JLabel business_L; // 사업장명
      JLabel businessPlace_L; // 사업장 주소

      // 라벨끼리 묶음
      ArrayList<JLabel> information;

      // 버튼
      JButton check; // 확인
      JButton cancellation; // 취소

      // 어레이리스트에 텍스트필드를 추가하고 빈칸일 경우 다시 입력하라는 창 띄우기.
      ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
      ArrayList<JTextField> textFieldList_2;

      // 텍스트 창
      JTextField id_t; // 아이디
      JPasswordField pw_t; // 비밀번호
      JPasswordField passWord; // 비밀번호 확인
      JTextField pwHintAnswer; // 비밀번호 힌트 답
      JTextField name_t; // 이름
      JTextField pnNum1; // 핸드폰번호 가운데
      JTextField pnNum2; // 핸드폰번호 뒷자리
      JTextField address_t; // 주소
      JTextField email_1; // 이메일 앞자리
      JTextField email_2; // 이메일 뒷자리
      JTextField business_1; // 사업장명
      JTextField business_2; // 사업장주소

      // 아이디 중복확인 버튼
      JButton idCheck;

      ButtonGroup gender_bt = new ButtonGroup();// 성별
      JRadioButton man_bt; // 남자버튼
      JRadioButton woman_bt; // 여자버튼

      // 비밀번호 힌트
      Vector<String> passWordHint;
      JComboBox<String> hintBox;

      // 년도
      Vector<String> year;
      JComboBox<String> yearBox;
      JLabel yearLabel;

      // 월
      Vector<String> month;
      JComboBox<String> monthBox;
      JLabel monthLabel;

      // 일
      Vector<String> day;
      JComboBox<String> dayBox;
      JLabel dayLabel;

      // 휴대폰번호 앞
      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      JLabel pnLabel;
      JLabel pnLabe2;

      // 휴대폰번호 통신사
//         ButtonGroup mobile_bt = new ButtonGroup();
//         JCheckBox mobileCompany1;
//         JCheckBox mobileCompany2;
//         JCheckBox mobileCompany3;

      // 이메일
      Vector<String> email_V; // 이메일 종류
      JComboBox<String> emailbox;

      // 카드번호 텍스트창
      JButton cardCompany; // 카드이름 버튼
      JTextField cardNum_1;
      JTextField cardNum_2;
      JTextField cardNum_3;
      JTextField cardNum_4;
      JLabel cardNum_L1;
      JLabel cardNum_L2;
      JLabel cardNum_L3;

      // 계좌번호
      JLabel bankNumber;
      JTextField bankNumber_T;

      // 은행선택
      Vector<String> bankChoice;
      JComboBox<String> bankBox;

      public SignUp_p() {

         setTitle("사업자 회원정보 기입 ");
         setBounds(600, 0, 750, 1020);
         setLayout(null);

         information = new ArrayList<JLabel>();

         // 아이디
         id_L = new JLabel("아이디 ");
//         id_L.setBounds(20,90,50,30);  
         information.add(id_L);

         // 아이디 텍스트창
         id_t = new JTextField();
         id_t.setBounds(140, 93, 200, 40);
         add(id_t);
         textFieldList.add(id_t);

         // 아이디 중복확인 버튼
         idCheck = new JButton("중복확인");
         idCheck.setBounds(360, 95, 110, 35);
         add(idCheck);
         idCheck.addActionListener(this);

         // 비밀번호
         pw_L = new JLabel("비밀번호 ");
//         pw_L.setBounds(20,145,100,30);  
         information.add(pw_L);

         // 비밀번호 텍스트창
         pw_t = new JPasswordField();
         pw_t.setBounds(140, 148, 200, 40);
         add(pw_t);

         // 비밀번호 확인
         passWord_L = new JLabel("비밀번호 확인");
//         passWord_L.setBounds(20,200,100,30);  
         information.add(passWord_L);

         // 비밀번호 확인 텍스트창
         passWord = new JPasswordField();
         passWord.setBounds(140, 203, 200, 40);
         add(passWord);

         // 비밀번호 힌트
         pwHint_L = new JLabel("비밀번호 힌트");
//         pwHint_L.setBounds(20,255,100,30);  
         information.add(pwHint_L);

         // 비밀번호 힌트 내용
         passWordHint = new Vector<String>();
         passWordHint.add("원하는 질문을 선택하세요.");
         passWordHint.add("가장 기억에 남는 장소는?");
         passWordHint.add("나의 보물 1호는?");
         passWordHint.add("본인의 출생지는?");
         passWordHint.add("내가 존경하는 인물은?");
         passWordHint.add("나의 좌우명은?");
         passWordHint.add("가장 감명깊게 본 영화는?");
         passWordHint.add("내가 좋아하는 뮤지션은?");
         passWordHint.add("인상깊게 읽은 책 제목은?");
         passWordHint.add("나의 노래방 애창곡은?");

         hintBox = new JComboBox<String>(passWordHint);
         hintBox.setBounds(140, 260, 230, 40);
         // box.setSelectedIndex(2);
//         hintBox.setSelectedItem("원하는 질문을 선택하세요."); // 다음으로 고정
         add(hintBox);

         // 힌트 답
         pwHintAnswer_L = new JLabel("비밀번호 힌트 답");
//         pwHintAnswer_L.setBounds(20,310,100,30);  
         information.add(pwHintAnswer_L);

         // 힌트 답 텍스트창
         pwHintAnswer = new JTextField();
         pwHintAnswer.setBounds(140, 315, 230, 40);
         add(pwHintAnswer);
         textFieldList.add(pwHintAnswer);

         // 이름
         name_L = new JLabel("이름 ");
//         name_L.setBounds(20,365,100,30);  
         information.add(name_L);

         // 이름 텍스트창
         name_t = new JTextField();
         name_t.setBounds(140, 368, 200, 40);
         add(name_t);
         textFieldList.add(name_t);

         // 성별
         gender_L = new JLabel("성별 ");
//         gender_L.setBounds(20,420,100,30);  
         information.add(gender_L);

         // 성별 버튼
         man_bt = new JRadioButton("남자");
         man_bt.setBounds(140, 425, 80, 35);
         gender_bt.add(man_bt);
         add(man_bt);
         man_bt.addActionListener(this);

         woman_bt = new JRadioButton("여자");
         woman_bt.setBounds(270, 425, 80, 35);
         gender_bt.add(woman_bt);
         add(woman_bt);
         woman_bt.addActionListener(this);

         // 생년월일
         birthDate_L = new JLabel("생년월일 ");
//         birthDate_L.setBounds(20,475,100,30);  
         information.add(birthDate_L);

         // 년도
         year = new Vector<String>();
         year.add("년도");
         for (int i = 2018; i >= 1940; i--) {
            year.add(i + "");
         }
         yearLabel = new JLabel("년");
         yearLabel.setBounds(250, 486, 30, 30);
         add(yearLabel);

         yearBox = new JComboBox<String>(year);
         yearBox.setBounds(140, 480, 100, 35);
         yearBox.setSelectedItem("년도");
         add(yearBox);

         // 월
         month = new Vector<String>();
         month.add("월");
         for (int i = 1; i <= 12; i++) {
            if (i < 10) {
               month.add("0" + i);
            } else {
               month.add(i + "");

            }
         }

         monthBox = new JComboBox<String>(month);
         monthBox.setBounds(280, 480, 100, 35);
         monthBox.setSelectedItem("월");
         add(monthBox);

         monthLabel = new JLabel("월");
         monthLabel.setBounds(390, 486, 30, 30);
         add(monthLabel);

         // 일
         day = new Vector<String>();
         day.add("일");

         for (int i = 1; i <= 31; i++) {
            if (i < 10) {
               day.add("0" + i);

            } else {
               day.add(i + "");

            }
         }

         dayBox = new JComboBox<String>(day);
         dayBox.setBounds(420, 480, 100, 35);
         dayBox.setSelectedItem("일");
         add(dayBox);

         dayLabel = new JLabel("일");
         dayLabel.setBounds(530, 486, 30, 30);
         add(dayLabel);

         // 전화번호
         phoneNumber_L = new JLabel("전화번호");
//         phoneNumber_L.setBounds(20,540,100,30);  
         information.add(phoneNumber_L);

         // 핸드폰번호 앞자리
         phoneNumber = new Vector<String>();
         phoneNumber.add("010");
         phoneNumber.add("011");
         phoneNumber.add("016");
         phoneNumber.add("017");
         phoneNumber.add("018");
         phoneNumber.add("019");

         pnBox = new JComboBox<String>(phoneNumber);
         pnBox.setBounds(140, 535, 90, 40);
         pnBox.setSelectedItem("010");
         add(pnBox);

         // 핸드폰번호 짝대기
         pnLabel = new JLabel("─");
         pnLabel.setBounds(240, 540, 30, 30);
         add(pnLabel);
         pnLabe2 = new JLabel("─");
         pnLabe2.setBounds(365, 540, 30, 30);
         add(pnLabe2);

         // 핸드폰번호 중간, 뒷자리
         pnNum1 = new JTextField();
         pnNum1.setBounds(265, 535, 90, 40);
         add(pnNum1);
         textFieldList.add(pnNum1);
         pnNum2 = new JTextField();
         pnNum2.setBounds(390, 535, 90, 40);
         add(pnNum2);
         textFieldList.add(pnNum2);

//         //핸드폰 통신사 선택
//         mobileCompany1 = new JCheckBox("LGU+");
//         mobileCompany2 = new JCheckBox("KT");
//         mobileCompany3 = new JCheckBox("SKT");
         //
//         mobileCompany1.setBounds(500, 535, 60, 40);
//         mobileCompany2.setBounds(570, 535, 40, 40);
//         mobileCompany3.setBounds(630, 535, 60, 40);
         //
         //
//         mobile_bt.add(mobileCompany1);
//         mobile_bt.add(mobileCompany2);
//         mobile_bt.add(mobileCompany3);
//         add(mobileCompany1);
//         add(mobileCompany2);
//         add(mobileCompany3);

         // 이메일
         email_L = new JLabel("E-mail");
//         email_L.setBounds(20,595,100,30);  
         information.add(email_L);

         // 이메일 텍스트창
         email_1 = new JTextField();
         email_1.setBounds(140, 590, 170, 40);
         add(email_1);
         textFieldList.add(email_1);

         JLabel e1 = new JLabel(" @ ");

         e1.setBounds(320, 593, 120, 35);
         add(e1);

         // 이메일 두번째 텍스트창
         email_2 = new JTextField();
         email_2.setBounds(350, 590, 170, 40);
         add(email_2);
         textFieldList.add(email_2);

         email_V = new Vector<String>(); // email창 목록 생성
         email_V.add("직접입력");
         email_V.add("naver.com");
         email_V.add("nate.com");
         email_V.add("hanmail.net");
         email_V.add("yahoo.co.kr");
         email_V.add("daum.net");
         email_V.add("gmail.com");

         // 이메일 주소
         emailbox = new JComboBox<String>(email_V);
         emailbox.setBounds(540, 590, 160, 35);
         emailbox.setSelectedItem("직접입력"); // 다음으로 고정
         add(emailbox);
         emailbox.addActionListener(this);

         // 주소
         address_L = new JLabel("주소");
         information.add(address_L);

         // 주소 텍스트창
         address_t = new JTextField();
         address_t.setBounds(140, 642, 360, 40);
         add(address_t);
         textFieldList.add(address_t);

         // 사업장명
         business_L = new JLabel("사업장명");
//         cardNumber_L.setBounds(20,650,100,30);  
         information.add(business_L);

         // 사업명장 입력창
         business_1 = new JTextField();
         business_1.setBounds(140, 698, 300, 40);
         add(business_1);
         textFieldList.add(business_1);

         // 사업장 주소
         businessPlace_L = new JLabel("사업장 주소");
         information.add(businessPlace_L);

         // 사업장주소 텍스트창
         business_2 = new JTextField();
         business_2.setBounds(140, 753, 360, 40);
         add(business_2);
         textFieldList.add(business_2);

         // 계좌번호
         bankNumber = new JLabel("계좌번호");
         bankNumber_T = new JTextField();
         information.add(bankNumber);

         // 은행 선택

         bankChoice = new Vector<String>();
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
         bankBox.setBounds(140, 813, 100, 35);
//         bankBox.setSelectedItem("-은행선택-"); // 다음으로 고정
         add(bankBox);

         // 계좌번호 입력창
         bankNumber_T = new JTextField();
         bankNumber_T.setBounds(250, 812, 250, 40);
         add(bankNumber_T);
         textFieldList.add(bankNumber_T);

         // 확인 취소 버튼
         check = new JButton("확인");
         check.setBounds(130, 890, 200, 50);
         add(check);
         check.addActionListener(this);

         cancellation = new JButton("취소");
         cancellation.setBounds(410, 890, 200, 50);
         add(cancellation);
         cancellation.addActionListener(this);

         // 라벨 간격 맞추기
         for (int i = 0; i < information.size(); i++) {
            information.get(i).setBounds(20, i * 55 + 100, 100, 30);

         }

         for (JLabel jL : information) {
            add(jL);
         }

         setVisible(true);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         textFieldList_2 = new ArrayList<JTextField>();

         for (int i = 0; i < textFieldList.size(); i++) {
            if (textFieldList.get(i).getText().length() > 0) {
               textFieldList_2.add(textFieldList.get(i));
            }
         }

         // DB연동
         String id = id_t.getText();
         String pw = pw_t.getText();
         String pwChkStr = passWord.getText();
         String name = name_t.getText();
         String gender = "";
         String birthYYYYMMDD = yearBox.getSelectedItem().toString() + monthBox.getSelectedItem().toString()
               + dayBox.getSelectedItem().toString();
         String phone = pnBox.getSelectedItem() + "-" + pnNum1.getText() + "-" + pnNum2.getText();
         String email = email_1.getText() + "@" + email_2.getText();
         String address = address_t.getText();
         String pwhint = hintBox.getSelectedItem().toString();
         String pwres = pwHintAnswer.getText();
         String businessname = business_1.getText();
         String businessaddress = business_2.getText();
         String banknum = bankNumber_T.getText();
         int coin = 0;
         boolean hintChk = hintBox.getSelectedIndex()==0;
         boolean bankChk = bankBox.getSelectedIndex()==0;
         boolean dayChk = yearBox.getSelectedIndex() == 0 || monthBox.getSelectedIndex() == 0 
               || dayBox.getSelectedIndex() == 0;
         
         
         
         
         
         
         
         // 스크롤에 있는 이메일을 누르면 이메일 두번째 창에 바로 입력되는 액션 리스너
         if (e.getSource().equals(emailbox)) { //이메일을 골랐을때
            
            //직접입력 외에 이메일 입력했을때
            if (!((String) emailbox.getSelectedItem()).equals("직접입력")) {  
               email_2.setText((String) emailbox.getSelectedItem());  //텍스트창에 이메일 띄움
               
            } else { //직접 입력일 경우 텍스트창 비우기
               email_2.setText("");
            }

         }

         // 1) 취소버튼 눌렀을때 ------------------------------------
         if (e.getSource().equals(cancellation)) {

            setVisible(false); // 창닫기

            // 2) 아이디 중복확인 버튼 눌렀을때----------------------------------------
         } else if (e.getSource().equals(idCheck)) {

            // 아이디텍스트 창이 비어있을때 -- 아이디 입력해주세요
            if (id_t.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, " 아이디를 입력해주세요. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;
               // 사용자가 입력한 아이디와 UserDB에 id와 같으면 -- 중복임을 알림
            } else if (id_t.getText().equals(UserDB.getID(id))) {

               JOptionPane.showMessageDialog(null, " 이미 사용중인 아이디입니다. ","회원가입 ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;

               // 나머지가 다 아니라면 사용가능 !!
            } else {
               idChkBl = true;
               JOptionPane.showMessageDialog(null, " 사용가능한 아이디입니다. ");
            }

            // 3) 확인 버튼 눌렀을때 ------------------------------------
         } else if (e.getSource().equals(check)) {
            System.out.println("체크!");

            // 텍스트필드창 갯수 확인
//            System.out.println(textFieldList.size()); 
            System.out.println(textFieldList_2.size());

            // 성별을 눌렀을때 남자,여자 확인
            if (man_bt.isSelected()) {
               System.out.println("남자");
               gender = "남자";

            } else if (woman_bt.isSelected()) {
               System.out.println("여자");
               gender = "여자";

               // 사용자 비밀번호와 사용자 비밀번호 확인이 같지 않을때 -- 다시 확인해달라는 창을 띄움.
            }
            if (!(pw_t.getText().equals(passWord.getText()))) {

               JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.", "회원가입", JOptionPane.WARNING_MESSAGE);

               // 텍스트필드 창 갯수가 12개보다 적게 입력했을때 --- 회원정보 입력하라는 창 띄움.
            } else if (textFieldList_2.size() < 11) {
               System.out.println("회원정보");
               JOptionPane.showMessageDialog(null, "회원정보를 입력해주세요.");

               // 아이디 중복체크를 하지않고 회원가입하는것을 방지 -- 중복확인을 누르지 않고 회원가입확인을 누르면 중복확인을 해달라는 창을 띄움.
            } else if (idChkBl == false) {

               JOptionPane.showMessageDialog(null, "중복확인을 해주세요.");

            } else if(hintChk == true) {   //비밀번호 힌트를 선택 안했을 때 true
            JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해주세요");
            
            //비밀번호 힌트를 선택 안했을 때 true
            } else if(hintChk == true) {   
               JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해주세요");
               //생년월일을 선택 안했을 때  true
            }else if(bankChk == true){
               JOptionPane.showMessageDialog(null, "은행을 선택해 주세요.");
               
            
            }   else if(dayChk == true) {  
               JOptionPane.showMessageDialog(null, "생년월일을 선택해주세요.");
               //성별을 선택 안했을 때 true
            }else if(gender == "") {
               JOptionPane.showMessageDialog(null, "성별을 선택해주세요.");
               
            
               // 다 오류가 아닐경우 가입조건에 충족되어 가입완료 창을 띄움.
            } else {
               System.out.println(textFieldList.size());
               UserDB.signupSELLERUSER(id, pwChkStr, name, gender, birthYYYYMMDD, phone, email, address, pwhint,
                     pwres, businessname, businessaddress, banknum, coin);
               MenuDB.makeMENU(id);
               JOptionPane.showMessageDialog(null, "가입완료");
               setVisible(false);

//               setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
         }
      }
            
//             if(idChkBl == true && pwChkBl == true && textFieldList_2.size() == 12)
   }
}

//         // 스크롤에 있는 이메일을 누르면 이메일 두번째 창에 바로 입력되는 액션 리스너
//         if (e.getSource().equals(emailbox)) {
//            if (!((String) emailbox.getSelectedItem()).equals("직접입력")) {
//               email_2.setText((String) emailbox.getSelectedItem());
//            } else {
//               email_2.setText("");
//            }
//
//         }
//
//         // 취소버튼 눌렀을때
//         if (e.getSource().equals(cancellation)) {
//            setVisible(false);
//
//            // 아이디 중복확인 버튼 눌렀을때
//         } else if (e.getSource().equals(idCheck)) {
//
//            if (id_t.getText().isEmpty()) {
//               JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
//
//            } else if (id_t.getText().equals(UserDB.getID(id))) {
//
//               JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
//
//            } else {
//               JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
//
//            }
//         }
//
//         // 확인버튼 눌렀을때 -> 여자 남자 구별!
//         if (e.getSource().equals(check)) {
//            if (man_bt.isSelected()) {
//               gender = "남자";
//
//            } else if (woman_bt.isSelected()) {
//               gender = "여자";
//
//            }
//
//            // 비밀번호가 맞지 않을때
//         } else if (!(pw_t.getText().equals(passWord.getText()))) {
//            JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요.", "오류", JOptionPane.WARNING_MESSAGE);
//         } else {
//            UserDB.signupSELLERUSER(id, pw, name, gender, birthYYYYMMDD, phone, email, address, pwhint, pwres,
//                  businessname, businessaddress, banknum, coin);
//            JOptionPane.showMessageDialog(null, "           회원가입 완료 \n점톡에 오신것을 환영합니다.");
//            setVisible(true);
//
//         }
//      }
//
//   }
//}

public class LoginTest {

   public static void main(String[] args) {

      new Login(); // 로그인
//      new MemberChoice(); //회원 분류 창
//      new Signup(); //일반 회원가입 창
//      new SignUp_p(); //사업자 회원가입 창
//      new IDsearch();  //아이디 찾기
//      new PWsearch();  //비밀번호 찾기
//      new CardChoice(); //카드사 선택
//      new NewPassWord(); //새로운 비밀번호 입력 창

   }

}