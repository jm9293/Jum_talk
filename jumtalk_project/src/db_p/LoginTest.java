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



/*--------------------�ʼ�����-----------------------------
 
1. �ĺ� �� �� �ִ� ��ȣ ����!!!!

2.ȸ������ ������ ������ (ID,PW,�̸� ���..)  Ŭ������ �ϰ�ó�� to Sting ���� return �ؼ� DB�� ������ 

 */

/*--------------------�ؾ��� ��--------------------------

1. ȸ������ �������� â�� ��������� - '@@@�� �ʼ� �Է� �����Դϴ�' â ����  --o
2. ���̵� �ߺ�Ȯ�� ��ư�� �������� - '��� ������ ���̵� �Դϴ�.'--�⺻ �޼���â , '�̹� ������� ���̵� �Դϴ�' --��� �޼���â  --o
3. JCombox index 0��° ����ȵǰ� �����.
4. ���ο� ��й�ȣ �Է�â���� ���̵� / ���̵�+��й�ȣ ��Ʈ �� �� ���� ���� ��� '������ ��ġ���� �ʽ��ϴ�.' --��� �޼���â  --o
5. �α��� id�� pw�� ��ġ���� ������� '�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�.' -- ��� �޼���â   --o
6. �̸��� �����Է� �ؽ�Ʈ �ȶ��� �س���. --o
7. ȸ������ -> ��й�ȣ ,��й�ȣ Ȯ�� �ΰ��� ��ġ���������� ȸ������ Ȯ�� �������� '��й�ȣ�� �ٽ� Ȯ�� ���ּ���' â ����. ---o
8. ��ȭ��ȣ ,ī���ȣ �ؽ�Ʈ�ʵ� ���ڼ� ����!!!!!
9. 


*/

/*   DB-----------------------------------------------
 
 //      if(!UserDB.getID("kkk23").equals("")) {
//         System.out.println("���̵� �ߺ��Դϴ�");
//      }else{
//         System.out.println("��밡���� ���̵�");
//         
//      };
      
//      UserDB.signupNOMALUSER("kkkk123","1234", "�̽���", "19970729", "010-7514-0915", "kkk123@naveer.com", "��⵵ �Ȼ�", "1111-2222-3333-4444", "������", "������", 0);
//      UserDB.signupSELLERUSER("", pw, name, birthYYYYMMDD, phone, email, address, pwhint, pwres, businessname, businessaddress, banknum, coin)
      
   }
 
 UserDB.signupNOMALUSER(id, pw, name, birthYYYYMMDD, phone,email,address, cardnumber, pwhint, pwres, coin);
 
 */

//�α��� â
class Login extends JFrame implements ActionListener {

   Signup signUp;
//   String userID = "admin";
   // ���̵� ,��й�ȣ �ؽ�Ʈâ
   JTextField idtxt;
   JPasswordField pwtxt;

   // ��
   JLabel idLabel; // ���̵�
   JLabel pwLabel; // ��й�ȣ

   // ��ư
   JButton login_bt; // �α���
   JButton idSearch_bt; // ���̵�ã��
   JButton pwSearch_bt; // ��й�ȣ ã��
   JButton signUp_Bt; // ȸ������

   // �׼Ǹ�����
   IDsearch idSearchFrame; // ���̵�ã�� ������Ŭ����
   PWsearch pwSearchFrame; // ���ã�� ������Ŭ����
   MemberChoice memberChoiceFrame; // �Ϲ�,���� ȸ�� ���� ������ Ŭ����

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

      
      // ���̵�
      idLabel = new JLabel("���̵�");
      idLabel.setBounds(60, 400, 100, 50);
      idLabel.setFont(new Font("����ü", Font.BOLD, 15));
      add(idLabel);

      // ���̵� �Է� ĭ
      idtxt = new JTextField();
      idtxt.setBounds(130, 400, 200, 50);
      add(idtxt);
//   idtxt.setOpaque(false);  //�ؽ�Ʈ ���� �����ϰ� �����
//   idtxt.setBorder(javax.swing.BorderFactory.createEmptyBorder()); //�޹�� �����ϰ� ����� (�׵θ� ����)

      // �н�����
      pwLabel = new JLabel("��й�ȣ");
      pwLabel.setBounds(60, 470, 300, 50);
      pwLabel.setFont(new Font("����ü", Font.BOLD, 15));
      add(pwLabel);

      // �н����� �Է�
      pwtxt = new JPasswordField();
      pwtxt.setBounds(130, 470, 200, 50);
      add(pwtxt);

      // �α���
      login_bt = new JButton("�α���");
      login_bt.setBounds(350, 407, 100, 100);
      add(login_bt);
      login_bt.addActionListener(this);

      // idã�� --�׼�
      idSearch_bt = new JButton("���̵� ã��");
      idSearch_bt.setBounds(60, 560, 120, 40);
      add(idSearch_bt);
      idSearch_bt.addActionListener(this);

      // pwã�� --�׼�
      pwSearch_bt = new JButton("��й�ȣ ã��");
      pwSearch_bt.setBounds(190, 560, 120, 40);
      add(pwSearch_bt);
      pwSearch_bt.addActionListener(this);

      // ȸ������ --�׼�
      signUp_Bt = new JButton("ȸ������");
      signUp_Bt.setBounds(320, 560, 130, 40);
      add(signUp_Bt);
      signUp_Bt.addActionListener(this);

      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(idSearch_bt)) { // ���̵�ã�� ��ư ��������
         idSearchFrame = new IDsearch();

      } else if (e.getSource().equals(pwSearch_bt)) { // ��й�ȣ ã�� ��ư ��������
         pwSearchFrame = new PWsearch();

      } else if (e.getSource().equals(signUp_Bt)) { // ȸ������ ��ư �������� -> �Ϲ�,����ȸ�� ���������� ����
         memberChoiceFrame = new MemberChoice();

      } else if (e.getSource().equals(login_bt)) { // �α��� ��ư ��������
         if (idtxt.getText().isEmpty() || pwtxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �Է����ּ���.  ","�α��� ����", JOptionPane.WARNING_MESSAGE);
         } else if (UserDB.getPW(idtxt.getText()).equals(pwtxt.getText())) {
            JOptionPane.showMessageDialog(null, "            �α��� ����! \n���忡 ���� ���� ȯ���մϴ� ��");
            setVisible(false);
            new Mainframe(idtxt.getText());

         } else {
            JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� ���� �ʽ��ϴ�.");

         }
      }

   }
   // ���̵�ã��

   class IDsearch extends JFrame implements ActionListener {

      // �̸�/��ȭ��ȣ ��
      JLabel idName_s;
      JLabel idPhone_s;

      // �̸�/��ȭ��ȣ �ؽ�Ʈâ
      JTextField idName_t;
      JTextField idPhone_t;
      JTextField idPhone_t2;

      // ���̵�ã�� /���
      JButton idChk_s;
      JButton idCancellation;

      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      


      // ���̵� ã�� ������ Ŭ����
      ResID resIDFrame;

      public IDsearch() {
         setTitle("���̵� ã��");
         setBounds(600, 230, 500, 600);
         setLayout(null);

         // �̸� �Է�â
         idName_s = new JLabel("�̸�");
         idName_s.setBounds(225, 110, 100, 100);
         add(idName_s);
         idName_t = new JTextField();
         idName_t.setBounds(165, 190, 155, 40);
         add(idName_t);

         // ��ȭ��ȣ �Է�â

         idPhone_s = new JLabel("��ȭ��ȣ");
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

         // �ڵ�����ȣ ¦���
         idPhone_s = new JLabel("��");
         idPhone_s.setBounds(169, 315, 30, 30);
         add(idPhone_s);
         idPhone_s = new JLabel("��");
         idPhone_s.setBounds(287, 315, 30, 30);
         add(idPhone_s);

         // �ڵ�����ȣ �߰�, ���ڸ�
         idPhone_t = new JTextField();
         idPhone_t.setBounds(190, 310, 90, 40);
         add(idPhone_t);
         idPhone_t2 = new JTextField();
         idPhone_t2.setBounds(310, 310, 90, 40);
         add(idPhone_t2);

         // ���̵�ã�� ��ư
         idChk_s = new JButton("���̵� ã��");
         idChk_s.setBounds(100, 430, 120, 50);
         add(idChk_s);
         idChk_s.addActionListener(this);

         // ��ҹ�ư
         idCancellation = new JButton("���");
         idCancellation.setBounds(280, 430, 100, 50);
         add(idCancellation);
         idCancellation.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(idChk_s)) { // ���̵�ã�� ��ư ��������
               
            resIDFrame = new ResID();
            
            setVisible(false);


         } else if (e.getSource().equals(idCancellation)) { // ��� ��ư ��������

            setVisible(false);

         }

      }

      // ã�� ���̵� â ����
      class ResID extends JFrame implements ActionListener {

         Signup signup;

         JLabel idMessage;
         JLabel resID; // ���̵�
         JLabel idLabel; // ã�� ���̵� ��

         JButton newIDChk;
         JButton newpwChk;
         
         PWsearch pwsearch;

         String userNAME = idName_t.getText();
         String userPHONE = pnBox.getSelectedItem() + "-" + idPhone_t.getText() + "-" + idPhone_t2.getText();

         public ResID() {

            setTitle("���̵� ã��");
            setBounds(600, 300, 500, 350);
            setLayout(null);

            // ���̵� ã�������
            idMessage = new JLabel("ȸ������ ���̵�� ");
            idMessage.setBounds(100, 40, 300, 200);
            add(idMessage);
            
            //���̵�ã�� --> �̸��� ��ȭ��ȣ�� ��ġ���� ������� 
            if(UserDB.searchID(userNAME, userPHONE).equals("")) {
               
               JOptionPane.showMessageDialog(null, "��ġ���� ���� �����Դϴ�.  ", "���̵� ã��", JOptionPane.ERROR_MESSAGE);
               
               
            // ��ġ�ϸ� ResIDŬ���� (���̵� ã��) â ��� !!
            }else {
               
               resID = new JLabel(UserDB.searchID(userNAME, userPHONE));
               resID.setBounds(230, 40, 280, 200);
               add(resID);
               
               setVisible(true);

            }

            idLabel = new JLabel("�Դϴ�.");
            idLabel.setBounds(300, 40, 300, 200);
            add(idLabel);

            // �α����ϱ� ��ư
            newIDChk = new JButton("�α��� �ϱ�");
            newIDChk.setBounds(60, 220, 130, 40);
            add(newIDChk);
            newIDChk.addActionListener(this);
            
            //���̵�ã�� ������  ��й�ȣ ã�� 
            newpwChk = new JButton("��й�ȣ ã��");
            newpwChk.setBounds(300, 220, 130, 40);
            add(newpwChk);
            newpwChk.addActionListener(this);
         }

         @Override
         public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(newIDChk)) { // �α��� ��ư ��������
               
               setVisible(false);
               
               
            }else if(e.getSource().equals(newpwChk)) {
               
               pwsearch = new PWsearch();
               
               setVisible(false);
               
            }

         }

      }

   }

   // ��й�ȣ ã��

   class PWsearch extends JFrame implements ActionListener {

      JLabel idSearch; // ���̵� �Է�
      JLabel pwHint_S; // ��й�ȣ ��Ʈ
      JLabel pwAnswer_S; // ��й�ȣ ��

      JTextField idSearch_t; // ���̵� �ؽ�Ʈâ
      JTextField pwSearch_t; // ��й�ȣ �ؽ�Ʈâ
      Vector<String> passWordHint_S; // ��й�ȣ ��Ʈ
      JComboBox<String> hintBox_S; // ��й�ȣ ��Ʈ comboBox

      JButton passWordChk; // ��й�ȣ ã��
      JButton pwCancellation; // ��� ��ư

      // �׼Ǹ�����
      PassWordFind passWordFind; // ��й�ȣ �˷��ִ� â
      ArrayList<JTextField> textFieldList_2; // �ؽ�Ʈâ�� �Էµ����� �߰����ִ� ����Ʈ (ȸ������ �ؽ�Ʈ�ʵ忡 �Է��� �ȵǸ� add����)
                                    // ȸ������ ��ĭ���θ� Ȯ�����ִ� ����Ʈ --���� Ȯ���ϴ� �������� �����

      public PWsearch() {
         setTitle("��й�ȣ ã��");
         setBounds(600, 230, 500, 600);
         setLayout(null);

         // ���̵� �Է�ĭ
         idSearch = new JLabel("���̵�");
         idSearch.setBounds(225, 70, 100, 100);
         add(idSearch);
         idSearch_t = new JTextField();
         idSearch_t.setBounds(165, 150, 155, 40);
         add(idSearch_t);

         // ��й�ȣ ��Ʈ ĭ
         pwHint_S = new JLabel("��й�ȣ ��Ʈ");
         pwHint_S.setBounds(205, 220, 100, 30);
         add(pwHint_S);

         passWordHint_S = new Vector<String>();
         passWordHint_S.add("���ϴ� ������ �����ϼ���.");
         passWordHint_S.add("���� ��￡ ���� ��Ҵ�?");
         passWordHint_S.add("���� ���� 1ȣ��?");
         passWordHint_S.add("������ �������?");
         passWordHint_S.add("���� �����ϴ� �ι���?");
         passWordHint_S.add("���� �¿����?");
         passWordHint_S.add("���� ������ �� ��ȭ��?");
         passWordHint_S.add("���� �����ϴ� ��������?");
         passWordHint_S.add("�λ��� ���� å ������?");
         passWordHint_S.add("���� �뷡�� ��â����?");

         hintBox_S = new JComboBox<String>(passWordHint_S);
         hintBox_S.setBounds(130, 260, 230, 40);
//         hintBox_S.setSelectedItem("���ϴ� ������ �����ϼ���."); // �������� ����
         add(hintBox_S);

         // ��й�ȣ ��Ʈ �� �Է� ĭ
         pwAnswer_S = new JLabel("��й�ȣ ��Ʈ ��");
         pwAnswer_S.setBounds(200, 320, 230, 40);
         add(pwAnswer_S);

         pwSearch_t = new JTextField();
         pwSearch_t.setBounds(130, 370, 240, 40);
         add(pwSearch_t);

         // ��й�ȣ ã��
         passWordChk = new JButton("��й�ȣ ã��");
         passWordChk.setBounds(100, 470, 120, 50);
         add(passWordChk);
         passWordChk.addActionListener(this);

         // ��ҹ�ư
         pwCancellation = new JButton("���");
         pwCancellation.setBounds(280, 470, 100, 50);
         add(pwCancellation);
         pwCancellation.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(passWordChk)) { // ��й�ȣã�� ��ư ��������

            passWordFind = new PassWordFind();

            setVisible(false);

         } else if (e.getSource().equals(pwCancellation)) { // ��� ��ư ��������
            setVisible(false);

         }

      }

      // ��й�ȣ ã��

      class PassWordFind extends JFrame implements ActionListener {

         JLabel pwMessage;
         JLabel resPW;
         JLabel pwLabel;

         // Ȯ�� ���
         JButton newPassWordChk; // Ȯ��
         JButton newPassWordCan; // ���

         Signup signup;
         
         
         String userID = idSearch_t.getText();
         String userPWHINT = hintBox_S.getSelectedItem().toString();
         String userPWRES = pwSearch_t.getText();

         public PassWordFind() {

            setTitle("��й�ȣ ã��");
            setBounds(600, 300, 500, 350);
            setLayout(null);

            // ��й�ȣ ã��
            pwMessage = new JLabel("ȸ������ ��й�ȣ�� ");
            pwMessage.setBounds(100, 40, 300, 200);
            add(pwMessage);
            
            
            //���̵��, ��й�ȣ ��Ʈ, ���� ��ġ���� ���� ��� 
            if(UserDB.searchPW(userID, userPWHINT, userPWRES).equals("")) {
               JOptionPane.showMessageDialog(null, "��ġ���� ���� �����Դϴ�.", "��й�ȣ ã��", JOptionPane.ERROR_MESSAGE);
               
            // ���̵�, ��й�ȣ ��Ʈ, �� �� ��ġ �� ���!! --> passWordFindŬ���� â ����!!   
            }else {
               
               resPW = new JLabel(UserDB.searchPW(userID, userPWHINT, userPWRES));
               resPW.setBounds(230, 40, 280, 200);
               add(resPW);
               setVisible(true);
            }
               

            pwLabel = new JLabel("�Դϴ�.");
            pwLabel.setBounds(300, 40, 300, 200);
            add(pwLabel);

            // Ȯ�� ��� ��ư
            newPassWordChk = new JButton("Ȯ��");
            newPassWordChk.setBounds(90, 280, 150, 40);
            add(newPassWordChk);
            newPassWordChk.addActionListener(this);

            newPassWordCan = new JButton("���");
            newPassWordCan.setBounds(350, 280, 150, 40);
            add(newPassWordCan);
            newPassWordCan.addActionListener(this);

         }

         @Override
         public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(newPassWordChk)) { // Ȯ�� ��ư ��������

               setVisible(false);

            } else if (e.getSource().equals(newPassWordCan)) { // ��� ��ư ��������
               setVisible(false);

            }

         }

      }

   }

   // �Ϲ�ȸ�� , �����ȸ�� ȸ������ ��ưâ
   class MemberChoice extends JFrame implements ActionListener {

      JButton memberG; // �Ϲ�ȸ�� ��ư
      JButton memberB; // ����ȸ�� ��ư

      // �׼Ǹ�����
      Signup signupFrame; // �Ϲ� ȸ������ ������ Ŭ����
      SignUp_p signUp_p_Frame; // ���� ȸ������ ������ Ŭ����

      public MemberChoice() {

         setTitle("ȸ������ ");
         setBounds(610, 300, 500, 500);
         setLayout(null);

         memberG = new JButton("�Ϲ�ȸ��");
         memberG.setBounds(60, 180, 100, 70);
         add(memberG);
         memberG.addActionListener(this);

         memberB = new JButton("����ȸ��");
         memberB.setBounds(300, 180, 100, 70);
         add(memberB);
         memberB.addActionListener(this);

         setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent e) {

         if (e.getSource().equals(memberG)) { // �Ϲ�ȸ�� ��ư ��������
            signupFrame = new Signup();
            setVisible(false);

         } else if (e.getSource().equals(memberB)) { // ����ȸ�� ��ư ��������
            signUp_p_Frame = new SignUp_p();
            setVisible(false);

         }

      }

   }

   // �Ϲ�ȸ�� ȸ������

   class Signup extends JFrame implements ActionListener {

      boolean idChkBl = false; // ���̵� �ߺ�üũ
//         boolean pwChkBl = true;   //��й�ȣ�� ��й�ȣȮ���̶� �´��� ��

      // ��
      JLabel id_L; // ���̵�
      JLabel pw_L; // ��й�ȣ
      JLabel passWord_L; // ��й�ȣ Ȯ��
      JLabel pwHint_L; // ��й�ȣ ��Ʈ
      JLabel pwHintAnswer_L; // ��й�ȣ ��Ʈ ��
      JLabel name_L; // �̸�
      JLabel gender_L; // ����
      JLabel birthDate_L; // �������
      JLabel phoneNumber_L; // �޴�����ȣ
      JLabel email_L; // �̸���
      JLabel address_L; // �ּ�
      JLabel cardNumber_L; // ī���ȣ

      // �󺧳��� ����
      ArrayList<JLabel> information;

      // ��ư
      JButton check; // Ȯ��
      JButton cancellation; // ���

      // ��̸���Ʈ�� �ؽ�Ʈ�ʵ带 �߰��ϰ� ��ĭ�� ��� �ٽ� �Է��϶�� â ����.
      ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
      ArrayList<JTextField> textFieldList_2;

      // �ؽ�Ʈ â
      JTextField id_t; // ���̵�
      JPasswordField pw_t; // ��й�ȣ
      JPasswordField passWord; // ��й�ȣ Ȯ��
      JTextField pwHintAnswer; // ��й�ȣ ��Ʈ ��
      JTextField name_t; // �̸�
      JTextField pnNum1; // �ڵ�����ȣ ���
      JTextField pnNum2; // �ڵ�����ȣ ���ڸ�
      JTextField email_1; // �̸��� ���ڸ�
      JTextField email_2; // �̸��� ���ڸ�
      JTextField address_t; // �ּ�

      // ���̵� �ߺ�Ȯ�� ��ư
      JButton idCheck;

      // ����
      ButtonGroup gender_bt = new ButtonGroup();
      JRadioButton man_bt; // ���ڹ�ư
      JRadioButton woman_bt; // ���ڹ�ư

      // ��й�ȣ ��Ʈ
      Vector<String> passWordHint;
      JComboBox<String> hintBox;

      // �⵵
      Vector<String> year;
      JComboBox<String> yearBox;
      JLabel yearLabel;

      // ��
      Vector<String> month;
      JComboBox<String> monthBox;
      JLabel monthLabel;

      // ��
      Vector<String> day;
      JComboBox<String> dayBox;
      JLabel dayLabel;

      // �޴�����ȣ ��
      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      JLabel pnLabel;
      JLabel pnLabe2;

      // �޴�����ȣ ��Ż�
//         ButtonGroup mobile_bt = new ButtonGroup();
//         JCheckBox mobileCompany1;
//         JCheckBox mobileCompany2;
//         JCheckBox mobileCompany3;

      // �̸���
      Vector<String> email_V; // �̸��� ����
      JComboBox<String> emailbox;

      // ī���ȣ �ؽ�Ʈâ
      JButton cardCompany; // ī���̸� ��ư
      JTextField cardNum_1;
      JTextField cardNum_2;
      JTextField cardNum_3;
      JTextField cardNum_4;
      JLabel cardNum_L1;
      JLabel cardNum_L2;
      JLabel cardNum_L3;

      // �׼Ǹ�����
//         CardChoice cardChoiceFrame; //ī�弱�� ������Ŭ����

      public Signup() {

         setTitle("�Ϲ� ȸ������ �Է� ");
         setBounds(600, 0, 750, 1000);
         setLayout(null);

         information = new ArrayList<JLabel>();

         // ���̵�
         id_L = new JLabel("���̵� ");
//         id_L.setBounds(20,90,50,30);  
         information.add(id_L);

         // ���̵� �ؽ�Ʈâ
         id_t = new JTextField();
         id_t.setBounds(140, 93, 200, 40);
         add(id_t);
         textFieldList.add(id_t);

         // ���̵� �ߺ�Ȯ�� ��ư
         idCheck = new JButton("�ߺ�Ȯ��");
         idCheck.setBounds(360, 95, 110, 35);
         add(idCheck);
         idCheck.addActionListener(this);

         // ��й�ȣ
         pw_L = new JLabel("��й�ȣ ");
//         pw_L.setBounds(20,145,100,30);  
         information.add(pw_L);

         // ��й�ȣ �ؽ�Ʈâ
         pw_t = new JPasswordField();
         pw_t.setBounds(140, 148, 200, 40);
         add(pw_t);

         // ��й�ȣ Ȯ��
         passWord_L = new JLabel("��й�ȣ Ȯ��");
//         passWord_L.setBounds(20,200,100,30);  
         information.add(passWord_L);

         // ��й�ȣ Ȯ�� �ؽ�Ʈâ
         passWord = new JPasswordField();
         passWord.setBounds(140, 203, 200, 40);
         add(passWord);

         // ��й�ȣ ��Ʈ
         pwHint_L = new JLabel("��й�ȣ ��Ʈ");
//         pwHint_L.setBounds(20,255,100,30);  
         information.add(pwHint_L);

         // ��й�ȣ ��Ʈ ����
         passWordHint = new Vector<String>();
         passWordHint.add("���ϴ� ������ �����ϼ���.");
         passWordHint.add("���� ��￡ ���� ��Ҵ�?");
         passWordHint.add("���� ���� 1ȣ��?");
         passWordHint.add("������ �������?");
         passWordHint.add("���� �����ϴ� �ι���?");
         passWordHint.add("���� �¿����?");
         passWordHint.add("���� ������ �� ��ȭ��?");
         passWordHint.add("���� �����ϴ� ��������?");
         passWordHint.add("�λ��� ���� å ������?");
         passWordHint.add("���� �뷡�� ��â����?");

         hintBox = new JComboBox<String>(passWordHint);
         hintBox.setBounds(140, 260, 230, 40);
         // box.setSelectedIndex(2);
//         hintBox.setSelectedItem("���ϴ� ������ �����ϼ���."); // �������� ����
         add(hintBox);

         // ��Ʈ ��
         pwHintAnswer_L = new JLabel("��й�ȣ ��Ʈ ��");
//         pwHintAnswer_L.setBounds(20,310,100,30);  
         information.add(pwHintAnswer_L);

         // ��Ʈ �� �ؽ�Ʈâ
         pwHintAnswer = new JTextField();
         pwHintAnswer.setBounds(140, 315, 230, 40);
         add(pwHintAnswer);
         textFieldList.add(pwHintAnswer);

         // �̸�
         name_L = new JLabel("�̸� ");
//         name_L.setBounds(20,365,100,30);  
         information.add(name_L);

         // �̸� �ؽ�Ʈâ
         name_t = new JTextField();
         name_t.setBounds(140, 368, 200, 40);
         add(name_t);
         textFieldList.add(name_t);

         // ����
         gender_L = new JLabel("���� ");
//         gender_L.setBounds(20,420,100,30);  
         information.add(gender_L);

         // ���� ��ư
         man_bt = new JRadioButton("����");
         man_bt.setBounds(140, 425, 80, 35);
         gender_bt.add(man_bt);
         add(man_bt);
         man_bt.addActionListener(this);

         woman_bt = new JRadioButton("����");
         woman_bt.setBounds(270, 425, 80, 35);
         gender_bt.add(woman_bt);
         add(woman_bt);
         woman_bt.addActionListener(this);

         // �������
         birthDate_L = new JLabel("������� ");
//         birthDate_L.setBounds(20,475,100,30);  
         information.add(birthDate_L);

         // �⵵
         year = new Vector<String>();
         year.add("�⵵");
         for (int i = 2018; i >= 1940; i--) {
            year.add(i + "");
         }
         yearLabel = new JLabel("��");
         yearLabel.setBounds(250, 486, 30, 30);
         add(yearLabel);

         yearBox = new JComboBox<String>(year);
         yearBox.setBounds(140, 480, 100, 35);
         yearBox.setSelectedItem("�⵵");
         add(yearBox);

         // ��
         month = new Vector<String>();
         month.add("��");

         for (int i = 1; i <= 12; i++) {
            if (i < 10) {
               month.add("0" + i);
            } else {
               month.add(i + "");

            }
         }

         monthBox = new JComboBox<String>(month);
         monthBox.setBounds(280, 480, 100, 35);
         monthBox.setSelectedItem("��");
         add(monthBox);

         monthLabel = new JLabel("��");
         monthLabel.setBounds(390, 486, 30, 30);
         add(monthLabel);

         // ��
         day = new Vector<String>();
         day.add("��");
         for (int i = 1; i <= 31; i++) {
            if (i < 10) {
               day.add("0" + i);

            } else {
               day.add(i + "");
            }

         }

         dayBox = new JComboBox<String>(day);
         dayBox.setBounds(420, 480, 100, 35);
         dayBox.setSelectedItem("��");
         add(dayBox);

         dayLabel = new JLabel("��");
         dayLabel.setBounds(530, 486, 30, 30);
         add(dayLabel);
         
         
         // ��ȭ��ȣ
         phoneNumber_L = new JLabel("��ȭ��ȣ");
//         phoneNumber_L.setBounds(20,540,100,30);  
         information.add(phoneNumber_L);

         // �ڵ�����ȣ ���ڸ�
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

         // �ڵ�����ȣ ¦���
         pnLabel = new JLabel("��");
         pnLabel.setBounds(240, 540, 30, 30);
         add(pnLabel);
         pnLabe2 = new JLabel("��");
         pnLabe2.setBounds(365, 540, 30, 30);
         add(pnLabe2);

         // �ڵ�����ȣ �߰�, ���ڸ�
         pnNum1 = new JTextField();
         pnNum1.setBounds(265, 535, 90, 40);
         add(pnNum1);
         textFieldList.add(pnNum1);
         pnNum2 = new JTextField();
         pnNum2.setBounds(390, 535, 90, 40);
         add(pnNum2);
         textFieldList.add(pnNum2);

         
         // �ڵ��� ��Ż� ����
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

         // �̸���
         email_L = new JLabel("E-mail");
//         email_L.setBounds(20,595,100,30);  
         information.add(email_L);

         // �̸��� �ؽ�Ʈâ
         email_1 = new JTextField();
         email_1.setBounds(140, 590, 170, 40);
         add(email_1);
         textFieldList.add(email_1);

         JLabel e1 = new JLabel(" @ ");

         e1.setBounds(320, 593, 120, 35);
         add(e1);

         // �̸��� �ι�° �ؽ�Ʈâ
         email_2 = new JTextField();
         email_2.setBounds(350, 590, 170, 40);
         add(email_2);
         textFieldList.add(email_2);

         email_V = new Vector<String>(); // emailâ ��� ����
         email_V.add("�����Է�");
         email_V.add("naver.com");
         email_V.add("nate.com");
         email_V.add("hanmail.net");
         email_V.add("yahoo.co.kr");
         email_V.add("daum.net");
         email_V.add("gmail.com");

         // �̸��� �ּ�
         emailbox = new JComboBox<String>(email_V);
         emailbox.setBounds(540, 590, 160, 35);
         emailbox.setSelectedItem("�����Է�"); // �������� ����
         add(emailbox);
         emailbox.addActionListener(this);

         // ī���ȣ
         cardNumber_L = new JLabel("ī���ȣ");
//         cardNumber_L.setBounds(20,650,100,30);  
         information.add(cardNumber_L);

         // ī�弱�� ��ư
//         cardCompany = new JButton("ī�弱��");
//         cardCompany.setBounds(140,650,100,35);
//         add(cardCompany);
//         cardCompany.addActionListener(this);

         // ī�� ��ȣ �Է�â
         cardNum_1 = new JTextField();
         cardNum_2 = new JTextField();
         cardNum_3 = new JTextField();
         cardNum_4 = new JTextField();
         textFieldList.add(cardNum_1);
         textFieldList.add(cardNum_2);
         textFieldList.add(cardNum_3);
         textFieldList.add(cardNum_4);

         // ī�� ��ȣ
         cardNum_L1 = new JLabel("��");
         cardNum_L2 = new JLabel("��");
         cardNum_L3 = new JLabel("��");

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

         // �ּ�
         address_L = new JLabel("�ּ� ");
//         address_L.setBounds(20,705,100,30);  
         information.add(address_L);

         // �ּ� �ؽ�Ʈâ
         address_t = new JTextField();
         address_t.setBounds(140, 700, 400, 40);
         add(address_t);
         textFieldList.add(address_t);

         // Ȯ�� ��� ��ư
         check = new JButton("Ȯ��");
         check.setBounds(130, 820, 200, 50);
         add(check);
         check.addActionListener(this);

         cancellation = new JButton("���");
         cancellation.setBounds(410, 820, 200, 50);
         add(cancellation);
         cancellation.addActionListener(this);

         // �� ���� ���߱�
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

         // DB����
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
         
         
         
         // Ȯ�ι�ư ��������

//            if(e.getSource().equals(check)) {
//               //�ؽ�Ʈ �ʵ带 �� �Է����� �ʾ����� 
//               }else if(cnt==0) {
//                  JOptionPane.showMessageDialog(null, "ȸ�������� �Է����ּ���");   
//               
//               
//               }else if(!(pw_t.getText().equals(passWord.getText()))){
//                  JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ�����ּ���.","����", JOptionPane.WARNING_MESSAGE);
//                  pwChkBl =true;
//                  
//               }else if(idChkBl == true && pwChkBl == true && cnt >0 ) {
//                  UserDB.signupNOMALUSER(id, pw, name, gender, birthYYYYMMDD, phone,email,address, cardnumber, pwhint, pwres, coin);
//                  JOptionPane.showMessageDialog(null, "���ԿϷ�");
//                  setVisible(false);
//                  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//               }

         // ��ũ�ѿ� �ִ� �̸����� ������ �̸��� �ι�° â�� �ٷ� �ԷµǴ� �׼� ������
         if (e.getSource().equals(emailbox)) {
            
            //�����Է� �ܿ� �̸��� �Է�������
            if (!((String) emailbox.getSelectedItem()).equals("�����Է�")) {
               email_2.setText((String) emailbox.getSelectedItem());  //�ؽ�Ʈâ�� �̸����� ���
               //�����Է��� ��� �ؽ�Ʈâ�� �����.
            } else {
               email_2.setText("");
            }

         }

         // 1) ��ҹ�ư �������� ------------------------------------
         if (e.getSource().equals(cancellation)) {

            setVisible(false); // â�ݱ�

            // 2) ���̵� �ߺ�Ȯ�� ��ư ��������----------------------------------------
         } else if (e.getSource().equals(idCheck)) {

            // ���̵��ؽ�Ʈ â�� ��������� -- ���̵� �Է����ּ���
            if (id_t.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;
               
               // ����ڰ� �Է��� ���̵�� UserDB�� id�� ������ -- �ߺ����� �˸�
            } else if (id_t.getText().equals(UserDB.getID(id))) {

               JOptionPane.showMessageDialog(null, " �̹� ������� ���̵��Դϴ�. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;

               // �������� �� �ƴ϶�� ��밡�� !!
            } else {
               idChkBl = true;
               JOptionPane.showMessageDialog(null, " ��밡���� ���̵��Դϴ�. ");
            }

            // 3) Ȯ�� ��ư �������� ------------------------------------
         } else if (e.getSource().equals(check)) {
            System.out.println("üũ!");

            // �ؽ�Ʈ�ʵ�â ���� Ȯ��
//            System.out.println(textFieldList.size()); 
            System.out.println(textFieldList_2.size());

            // ������ �������� ����,���� Ȯ��
            if (man_bt.isSelected()) {
               System.out.println("����");
               gender = "����";

            } else if (woman_bt.isSelected()) {
               System.out.println("����");
               gender = "����";

               // ����� ��й�ȣ�� ����� ��й�ȣ Ȯ���� ���� ������ -- �ٽ� Ȯ���ش޶�� â�� ���.
            }
            if (!(pw_t.getText().equals(passWord.getText()))) {

               JOptionPane.showMessageDialog(null, " ��й�ȣ�� �ٽ� Ȯ�����ּ���. ", "ȸ������ ", JOptionPane.WARNING_MESSAGE);

               // �ؽ�Ʈ�ʵ� â ������ 12������ ���� �Է������� --- ȸ������ �Է��϶�� â ���.
            } else if (textFieldList_2.size() < 12) {
               System.out.println("ȸ������");
               JOptionPane.showMessageDialog(null, " ȸ�������� �Է����ּ���. ","ȸ������", JOptionPane.WARNING_MESSAGE);

               // ���̵� �ߺ�üũ�� �����ʰ� ȸ�������ϴ°��� ���� -- �ߺ�Ȯ���� ������ �ʰ� ȸ������Ȯ���� ������ �ߺ�Ȯ���� �ش޶�� â�� ���.
            } else if (idChkBl == false) {

               JOptionPane.showMessageDialog(null, " �ߺ�Ȯ���� ���ּ���.","ȸ������ ", JOptionPane.WARNING_MESSAGE);

               //��й�ȣ ��Ʈ�� ���� ������ �� true
            } else if(hintChk == true) {   
               JOptionPane.showMessageDialog(null, " ��й�ȣ ��Ʈ�� �������ּ���. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               //��������� ���� ������ ��  true
            }else if(dayChk == true) {  
               JOptionPane.showMessageDialog(null, " ��������� �������ּ���. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               //������ ���� ������ �� true
            }else if(gender == "") {
               JOptionPane.showMessageDialog(null, " ������ �������ּ���. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               
            }
            // �� ������ �ƴҰ�� �������ǿ� �����Ǿ� ���ԿϷ� â�� ���.
            else {
               System.out.println(textFieldList.size());
               UserDB.signupNOMALUSER(id, pw, name, gender, birthYYYYMMDD, phone, email, address, cardnumber,
                     pwhint, pwres, coin);
               UserDB.setFORTUNE_TIME(id, "20100101");
               JOptionPane.showMessageDialog(null, " ȸ������ ���� ! ");
               setVisible(false);
//               setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
         }

//             if(idChkBl == true && pwChkBl == true && textFieldList_2.size() == 12)
      }
   }

   // ī���̸� ���� â
//      class CardChoice extends JFrame implements ActionListener{
//         
//         Signup signup;
//         
//         //ī���̸� ��ư �г�
//         JPanel cardPanel;
//         
//         //'�̿��Ͻ� ī�带 ������ �ּ���' ��
//         JLabel cardChoice_L;
//         //'�� ���� ī��� ��Ÿī�忡�� �������ּ���'
//         JLabel cardChoice_L1;
//         
//         //ī���̸� ��ư
//         ArrayList<JButton> cardName;
//         
//         //��Ÿī�� 
//         Vector<String> otherCardName;
//         JComboBox<String> otherCardName2;
//         
//         //Ȯ�� ,���
//         JButton cardNumChk; //Ȯ��
//         JButton cardNumCan; //���
//         
//         
//         public CardChoice() {
//            
//            setTitle("ī�� ����");
//            setBounds(610, 300, 500,500);
//            setLayout(null);
//            
//            //�� 
//            cardChoice_L = new JLabel("�̿��Ͻ� ī��縦 ������ �ּ���.");
//            cardChoice_L.setBounds(155, 40, 300, 50);
//            add(cardChoice_L);
//            cardChoice_L1 = new JLabel("�� ���� ī��� ��Ÿī�忡�� �����ϼ���.");
//            cardChoice_L1.setBounds(30, 280, 300, 50);
//            add(cardChoice_L1);
//            //ī��� ��ư
//            cardName = new ArrayList<JButton>();
//            for (JButton cardArr : cardName) {
//               cardArr.addActionListener(this);
//            }
//            //ī��� ��ư �г�
//            cardPanel = new JPanel();
//            cardPanel.setBounds(65, 80, 360, 200);
//         
//            
//            cardPanel.add(new JButton("�Ｚ"));
//            cardPanel.add(new JButton("���� "));
//            cardPanel.add(new JButton("����"));
//            cardPanel.add(new JButton("NHä��"));
//            cardPanel.add(new JButton("BC"));
//            cardPanel.add(new JButton("�Ե�"));
//            cardPanel.add(new JButton("KB����"));
//            cardPanel.add(new JButton("�ϳ�"));
//            cardPanel.add(new JButton("�ѱ���Ƽ"));
////            cardPanel.add(new JButton("�ϳ�(��ȯ)"));
//            add(cardPanel);
//            
//            otherCardName = new Vector<String>();
//            otherCardName.add("-�׿���ī��-");
//            otherCardName.add("�츮");
//            otherCardName.add("����");
//            otherCardName.add("����");
//            otherCardName.add("����");
//            otherCardName.add("����");
//            otherCardName.add("����");
//            otherCardName.add("��ü��");
//            otherCardName.add("�ؿ�VISA");
//            otherCardName.add("�ؿ�MASTER");
//            
//            otherCardName2 = new JComboBox<String>(otherCardName);
//            otherCardName2.setBounds(280,286,150,35);
//            //box.setSelectedIndex(2);
//            otherCardName2.setSelectedItem("-�׿��� ī��-");  
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
//            // Ȯ�� 
//            cardNumChk = new JButton("Ȯ��");
//            cardNumChk.setBounds(100, 380, 100, 40);
//            add(cardNumChk);
//            cardNumChk.addActionListener(this);
//            
//            //���
//            cardNumCan = new JButton("���");
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
//                  //Ȯ�ι�ư ��������
//                  }else if(e.getSource().equals(cardNumChk)) {
//                     setVisible(false);
//
//                  
//               
//               }      
//         
//            //Ȯ�ι�ư ��������
//            if(e.getSource().equals(cardNumChk)) {
//            
//            //��ҹ�ư ��������
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

   // ����� ȸ������
   class SignUp_p extends JFrame implements ActionListener {

      boolean idChkBl = false; // ���̵� �ߺ�üũ

      // ��
      JLabel id_L; // ���̵�
      JLabel pw_L; // ��й�ȣ
      JLabel passWord_L; // ��й�ȣ Ȯ��
      JLabel pwHint_L; // ��й�ȣ ��Ʈ
      JLabel pwHintAnswer_L; // ��й�ȣ ��Ʈ ��
      JLabel name_L; // �̸�
      JLabel gender_L; // ����
      JLabel birthDate_L; // �������
      JLabel phoneNumber_L; // �޴�����ȣ
      JLabel address_L; // �ּ�
      JLabel email_L; // �̸���
      JLabel business_L; // ������
      JLabel businessPlace_L; // ����� �ּ�

      // �󺧳��� ����
      ArrayList<JLabel> information;

      // ��ư
      JButton check; // Ȯ��
      JButton cancellation; // ���

      // ��̸���Ʈ�� �ؽ�Ʈ�ʵ带 �߰��ϰ� ��ĭ�� ��� �ٽ� �Է��϶�� â ����.
      ArrayList<JTextField> textFieldList = new ArrayList<JTextField>();
      ArrayList<JTextField> textFieldList_2;

      // �ؽ�Ʈ â
      JTextField id_t; // ���̵�
      JPasswordField pw_t; // ��й�ȣ
      JPasswordField passWord; // ��й�ȣ Ȯ��
      JTextField pwHintAnswer; // ��й�ȣ ��Ʈ ��
      JTextField name_t; // �̸�
      JTextField pnNum1; // �ڵ�����ȣ ���
      JTextField pnNum2; // �ڵ�����ȣ ���ڸ�
      JTextField address_t; // �ּ�
      JTextField email_1; // �̸��� ���ڸ�
      JTextField email_2; // �̸��� ���ڸ�
      JTextField business_1; // ������
      JTextField business_2; // ������ּ�

      // ���̵� �ߺ�Ȯ�� ��ư
      JButton idCheck;

      ButtonGroup gender_bt = new ButtonGroup();// ����
      JRadioButton man_bt; // ���ڹ�ư
      JRadioButton woman_bt; // ���ڹ�ư

      // ��й�ȣ ��Ʈ
      Vector<String> passWordHint;
      JComboBox<String> hintBox;

      // �⵵
      Vector<String> year;
      JComboBox<String> yearBox;
      JLabel yearLabel;

      // ��
      Vector<String> month;
      JComboBox<String> monthBox;
      JLabel monthLabel;

      // ��
      Vector<String> day;
      JComboBox<String> dayBox;
      JLabel dayLabel;

      // �޴�����ȣ ��
      Vector<String> phoneNumber;
      JComboBox<String> pnBox;
      JLabel pnLabel;
      JLabel pnLabe2;

      // �޴�����ȣ ��Ż�
//         ButtonGroup mobile_bt = new ButtonGroup();
//         JCheckBox mobileCompany1;
//         JCheckBox mobileCompany2;
//         JCheckBox mobileCompany3;

      // �̸���
      Vector<String> email_V; // �̸��� ����
      JComboBox<String> emailbox;

      // ī���ȣ �ؽ�Ʈâ
      JButton cardCompany; // ī���̸� ��ư
      JTextField cardNum_1;
      JTextField cardNum_2;
      JTextField cardNum_3;
      JTextField cardNum_4;
      JLabel cardNum_L1;
      JLabel cardNum_L2;
      JLabel cardNum_L3;

      // ���¹�ȣ
      JLabel bankNumber;
      JTextField bankNumber_T;

      // ���༱��
      Vector<String> bankChoice;
      JComboBox<String> bankBox;

      public SignUp_p() {

         setTitle("����� ȸ������ ���� ");
         setBounds(600, 0, 750, 1020);
         setLayout(null);

         information = new ArrayList<JLabel>();

         // ���̵�
         id_L = new JLabel("���̵� ");
//         id_L.setBounds(20,90,50,30);  
         information.add(id_L);

         // ���̵� �ؽ�Ʈâ
         id_t = new JTextField();
         id_t.setBounds(140, 93, 200, 40);
         add(id_t);
         textFieldList.add(id_t);

         // ���̵� �ߺ�Ȯ�� ��ư
         idCheck = new JButton("�ߺ�Ȯ��");
         idCheck.setBounds(360, 95, 110, 35);
         add(idCheck);
         idCheck.addActionListener(this);

         // ��й�ȣ
         pw_L = new JLabel("��й�ȣ ");
//         pw_L.setBounds(20,145,100,30);  
         information.add(pw_L);

         // ��й�ȣ �ؽ�Ʈâ
         pw_t = new JPasswordField();
         pw_t.setBounds(140, 148, 200, 40);
         add(pw_t);

         // ��й�ȣ Ȯ��
         passWord_L = new JLabel("��й�ȣ Ȯ��");
//         passWord_L.setBounds(20,200,100,30);  
         information.add(passWord_L);

         // ��й�ȣ Ȯ�� �ؽ�Ʈâ
         passWord = new JPasswordField();
         passWord.setBounds(140, 203, 200, 40);
         add(passWord);

         // ��й�ȣ ��Ʈ
         pwHint_L = new JLabel("��й�ȣ ��Ʈ");
//         pwHint_L.setBounds(20,255,100,30);  
         information.add(pwHint_L);

         // ��й�ȣ ��Ʈ ����
         passWordHint = new Vector<String>();
         passWordHint.add("���ϴ� ������ �����ϼ���.");
         passWordHint.add("���� ��￡ ���� ��Ҵ�?");
         passWordHint.add("���� ���� 1ȣ��?");
         passWordHint.add("������ �������?");
         passWordHint.add("���� �����ϴ� �ι���?");
         passWordHint.add("���� �¿����?");
         passWordHint.add("���� ������ �� ��ȭ��?");
         passWordHint.add("���� �����ϴ� ��������?");
         passWordHint.add("�λ��� ���� å ������?");
         passWordHint.add("���� �뷡�� ��â����?");

         hintBox = new JComboBox<String>(passWordHint);
         hintBox.setBounds(140, 260, 230, 40);
         // box.setSelectedIndex(2);
//         hintBox.setSelectedItem("���ϴ� ������ �����ϼ���."); // �������� ����
         add(hintBox);

         // ��Ʈ ��
         pwHintAnswer_L = new JLabel("��й�ȣ ��Ʈ ��");
//         pwHintAnswer_L.setBounds(20,310,100,30);  
         information.add(pwHintAnswer_L);

         // ��Ʈ �� �ؽ�Ʈâ
         pwHintAnswer = new JTextField();
         pwHintAnswer.setBounds(140, 315, 230, 40);
         add(pwHintAnswer);
         textFieldList.add(pwHintAnswer);

         // �̸�
         name_L = new JLabel("�̸� ");
//         name_L.setBounds(20,365,100,30);  
         information.add(name_L);

         // �̸� �ؽ�Ʈâ
         name_t = new JTextField();
         name_t.setBounds(140, 368, 200, 40);
         add(name_t);
         textFieldList.add(name_t);

         // ����
         gender_L = new JLabel("���� ");
//         gender_L.setBounds(20,420,100,30);  
         information.add(gender_L);

         // ���� ��ư
         man_bt = new JRadioButton("����");
         man_bt.setBounds(140, 425, 80, 35);
         gender_bt.add(man_bt);
         add(man_bt);
         man_bt.addActionListener(this);

         woman_bt = new JRadioButton("����");
         woman_bt.setBounds(270, 425, 80, 35);
         gender_bt.add(woman_bt);
         add(woman_bt);
         woman_bt.addActionListener(this);

         // �������
         birthDate_L = new JLabel("������� ");
//         birthDate_L.setBounds(20,475,100,30);  
         information.add(birthDate_L);

         // �⵵
         year = new Vector<String>();
         year.add("�⵵");
         for (int i = 2018; i >= 1940; i--) {
            year.add(i + "");
         }
         yearLabel = new JLabel("��");
         yearLabel.setBounds(250, 486, 30, 30);
         add(yearLabel);

         yearBox = new JComboBox<String>(year);
         yearBox.setBounds(140, 480, 100, 35);
         yearBox.setSelectedItem("�⵵");
         add(yearBox);

         // ��
         month = new Vector<String>();
         month.add("��");
         for (int i = 1; i <= 12; i++) {
            if (i < 10) {
               month.add("0" + i);
            } else {
               month.add(i + "");

            }
         }

         monthBox = new JComboBox<String>(month);
         monthBox.setBounds(280, 480, 100, 35);
         monthBox.setSelectedItem("��");
         add(monthBox);

         monthLabel = new JLabel("��");
         monthLabel.setBounds(390, 486, 30, 30);
         add(monthLabel);

         // ��
         day = new Vector<String>();
         day.add("��");

         for (int i = 1; i <= 31; i++) {
            if (i < 10) {
               day.add("0" + i);

            } else {
               day.add(i + "");

            }
         }

         dayBox = new JComboBox<String>(day);
         dayBox.setBounds(420, 480, 100, 35);
         dayBox.setSelectedItem("��");
         add(dayBox);

         dayLabel = new JLabel("��");
         dayLabel.setBounds(530, 486, 30, 30);
         add(dayLabel);

         // ��ȭ��ȣ
         phoneNumber_L = new JLabel("��ȭ��ȣ");
//         phoneNumber_L.setBounds(20,540,100,30);  
         information.add(phoneNumber_L);

         // �ڵ�����ȣ ���ڸ�
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

         // �ڵ�����ȣ ¦���
         pnLabel = new JLabel("��");
         pnLabel.setBounds(240, 540, 30, 30);
         add(pnLabel);
         pnLabe2 = new JLabel("��");
         pnLabe2.setBounds(365, 540, 30, 30);
         add(pnLabe2);

         // �ڵ�����ȣ �߰�, ���ڸ�
         pnNum1 = new JTextField();
         pnNum1.setBounds(265, 535, 90, 40);
         add(pnNum1);
         textFieldList.add(pnNum1);
         pnNum2 = new JTextField();
         pnNum2.setBounds(390, 535, 90, 40);
         add(pnNum2);
         textFieldList.add(pnNum2);

//         //�ڵ��� ��Ż� ����
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

         // �̸���
         email_L = new JLabel("E-mail");
//         email_L.setBounds(20,595,100,30);  
         information.add(email_L);

         // �̸��� �ؽ�Ʈâ
         email_1 = new JTextField();
         email_1.setBounds(140, 590, 170, 40);
         add(email_1);
         textFieldList.add(email_1);

         JLabel e1 = new JLabel(" @ ");

         e1.setBounds(320, 593, 120, 35);
         add(e1);

         // �̸��� �ι�° �ؽ�Ʈâ
         email_2 = new JTextField();
         email_2.setBounds(350, 590, 170, 40);
         add(email_2);
         textFieldList.add(email_2);

         email_V = new Vector<String>(); // emailâ ��� ����
         email_V.add("�����Է�");
         email_V.add("naver.com");
         email_V.add("nate.com");
         email_V.add("hanmail.net");
         email_V.add("yahoo.co.kr");
         email_V.add("daum.net");
         email_V.add("gmail.com");

         // �̸��� �ּ�
         emailbox = new JComboBox<String>(email_V);
         emailbox.setBounds(540, 590, 160, 35);
         emailbox.setSelectedItem("�����Է�"); // �������� ����
         add(emailbox);
         emailbox.addActionListener(this);

         // �ּ�
         address_L = new JLabel("�ּ�");
         information.add(address_L);

         // �ּ� �ؽ�Ʈâ
         address_t = new JTextField();
         address_t.setBounds(140, 642, 360, 40);
         add(address_t);
         textFieldList.add(address_t);

         // ������
         business_L = new JLabel("������");
//         cardNumber_L.setBounds(20,650,100,30);  
         information.add(business_L);

         // ������� �Է�â
         business_1 = new JTextField();
         business_1.setBounds(140, 698, 300, 40);
         add(business_1);
         textFieldList.add(business_1);

         // ����� �ּ�
         businessPlace_L = new JLabel("����� �ּ�");
         information.add(businessPlace_L);

         // ������ּ� �ؽ�Ʈâ
         business_2 = new JTextField();
         business_2.setBounds(140, 753, 360, 40);
         add(business_2);
         textFieldList.add(business_2);

         // ���¹�ȣ
         bankNumber = new JLabel("���¹�ȣ");
         bankNumber_T = new JTextField();
         information.add(bankNumber);

         // ���� ����

         bankChoice = new Vector<String>();
         bankChoice.add("-���༱��-");
         bankChoice.add("�������");
         bankChoice.add("��������");
         bankChoice.add("��������");
         bankChoice.add("�������");
         bankChoice.add("�츮����");
         bankChoice.add("��Ƽ����");
         bankChoice.add("�ϳ�����");
         bankChoice.add("��������");
         bankChoice.add("�泲����");
         bankChoice.add("�뱸����");
         bankChoice.add("��������");
         bankChoice.add("�λ�����");
         bankChoice.add("��������");
         bankChoice.add("��������");
         bankChoice.add("��������");
         bankChoice.add("��������");
         bankChoice.add("�������ݰ�");
         bankChoice.add("��������");
         bankChoice.add("��������");
         bankChoice.add("��ü��");

         bankBox = new JComboBox<String>(bankChoice);
         bankBox.setBounds(140, 813, 100, 35);
//         bankBox.setSelectedItem("-���༱��-"); // �������� ����
         add(bankBox);

         // ���¹�ȣ �Է�â
         bankNumber_T = new JTextField();
         bankNumber_T.setBounds(250, 812, 250, 40);
         add(bankNumber_T);
         textFieldList.add(bankNumber_T);

         // Ȯ�� ��� ��ư
         check = new JButton("Ȯ��");
         check.setBounds(130, 890, 200, 50);
         add(check);
         check.addActionListener(this);

         cancellation = new JButton("���");
         cancellation.setBounds(410, 890, 200, 50);
         add(cancellation);
         cancellation.addActionListener(this);

         // �� ���� ���߱�
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

         // DB����
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
         
         
         
         
         
         
         
         // ��ũ�ѿ� �ִ� �̸����� ������ �̸��� �ι�° â�� �ٷ� �ԷµǴ� �׼� ������
         if (e.getSource().equals(emailbox)) { //�̸����� �������
            
            //�����Է� �ܿ� �̸��� �Է�������
            if (!((String) emailbox.getSelectedItem()).equals("�����Է�")) {  
               email_2.setText((String) emailbox.getSelectedItem());  //�ؽ�Ʈâ�� �̸��� ���
               
            } else { //���� �Է��� ��� �ؽ�Ʈâ ����
               email_2.setText("");
            }

         }

         // 1) ��ҹ�ư �������� ------------------------------------
         if (e.getSource().equals(cancellation)) {

            setVisible(false); // â�ݱ�

            // 2) ���̵� �ߺ�Ȯ�� ��ư ��������----------------------------------------
         } else if (e.getSource().equals(idCheck)) {

            // ���̵��ؽ�Ʈ â�� ��������� -- ���̵� �Է����ּ���
            if (id_t.getText().isEmpty()) {
               JOptionPane.showMessageDialog(null, " ���̵� �Է����ּ���. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;
               // ����ڰ� �Է��� ���̵�� UserDB�� id�� ������ -- �ߺ����� �˸�
            } else if (id_t.getText().equals(UserDB.getID(id))) {

               JOptionPane.showMessageDialog(null, " �̹� ������� ���̵��Դϴ�. ","ȸ������ ", JOptionPane.WARNING_MESSAGE);
               idChkBl = false;

               // �������� �� �ƴ϶�� ��밡�� !!
            } else {
               idChkBl = true;
               JOptionPane.showMessageDialog(null, " ��밡���� ���̵��Դϴ�. ");
            }

            // 3) Ȯ�� ��ư �������� ------------------------------------
         } else if (e.getSource().equals(check)) {
            System.out.println("üũ!");

            // �ؽ�Ʈ�ʵ�â ���� Ȯ��
//            System.out.println(textFieldList.size()); 
            System.out.println(textFieldList_2.size());

            // ������ �������� ����,���� Ȯ��
            if (man_bt.isSelected()) {
               System.out.println("����");
               gender = "����";

            } else if (woman_bt.isSelected()) {
               System.out.println("����");
               gender = "����";

               // ����� ��й�ȣ�� ����� ��й�ȣ Ȯ���� ���� ������ -- �ٽ� Ȯ���ش޶�� â�� ���.
            }
            if (!(pw_t.getText().equals(passWord.getText()))) {

               JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ�����ּ���.", "ȸ������", JOptionPane.WARNING_MESSAGE);

               // �ؽ�Ʈ�ʵ� â ������ 12������ ���� �Է������� --- ȸ������ �Է��϶�� â ���.
            } else if (textFieldList_2.size() < 11) {
               System.out.println("ȸ������");
               JOptionPane.showMessageDialog(null, "ȸ�������� �Է����ּ���.");

               // ���̵� �ߺ�üũ�� �����ʰ� ȸ�������ϴ°��� ���� -- �ߺ�Ȯ���� ������ �ʰ� ȸ������Ȯ���� ������ �ߺ�Ȯ���� �ش޶�� â�� ���.
            } else if (idChkBl == false) {

               JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� ���ּ���.");

            } else if(hintChk == true) {   //��й�ȣ ��Ʈ�� ���� ������ �� true
            JOptionPane.showMessageDialog(null, "��й�ȣ ��Ʈ�� �������ּ���");
            
            //��й�ȣ ��Ʈ�� ���� ������ �� true
            } else if(hintChk == true) {   
               JOptionPane.showMessageDialog(null, "��й�ȣ ��Ʈ�� �������ּ���");
               //��������� ���� ������ ��  true
            }else if(bankChk == true){
               JOptionPane.showMessageDialog(null, "������ ������ �ּ���.");
               
            
            }   else if(dayChk == true) {  
               JOptionPane.showMessageDialog(null, "��������� �������ּ���.");
               //������ ���� ������ �� true
            }else if(gender == "") {
               JOptionPane.showMessageDialog(null, "������ �������ּ���.");
               
            
               // �� ������ �ƴҰ�� �������ǿ� �����Ǿ� ���ԿϷ� â�� ���.
            } else {
               System.out.println(textFieldList.size());
               UserDB.signupSELLERUSER(id, pwChkStr, name, gender, birthYYYYMMDD, phone, email, address, pwhint,
                     pwres, businessname, businessaddress, banknum, coin);
               MenuDB.makeMENU(id);
               JOptionPane.showMessageDialog(null, "���ԿϷ�");
               setVisible(false);

//               setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }
         }
      }
            
//             if(idChkBl == true && pwChkBl == true && textFieldList_2.size() == 12)
   }
}

//         // ��ũ�ѿ� �ִ� �̸����� ������ �̸��� �ι�° â�� �ٷ� �ԷµǴ� �׼� ������
//         if (e.getSource().equals(emailbox)) {
//            if (!((String) emailbox.getSelectedItem()).equals("�����Է�")) {
//               email_2.setText((String) emailbox.getSelectedItem());
//            } else {
//               email_2.setText("");
//            }
//
//         }
//
//         // ��ҹ�ư ��������
//         if (e.getSource().equals(cancellation)) {
//            setVisible(false);
//
//            // ���̵� �ߺ�Ȯ�� ��ư ��������
//         } else if (e.getSource().equals(idCheck)) {
//
//            if (id_t.getText().isEmpty()) {
//               JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
//
//            } else if (id_t.getText().equals(UserDB.getID(id))) {
//
//               JOptionPane.showMessageDialog(null, "�̹� ������� ���̵��Դϴ�.");
//
//            } else {
//               JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
//
//            }
//         }
//
//         // Ȯ�ι�ư �������� -> ���� ���� ����!
//         if (e.getSource().equals(check)) {
//            if (man_bt.isSelected()) {
//               gender = "����";
//
//            } else if (woman_bt.isSelected()) {
//               gender = "����";
//
//            }
//
//            // ��й�ȣ�� ���� ������
//         } else if (!(pw_t.getText().equals(passWord.getText()))) {
//            JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ�����ּ���.", "����", JOptionPane.WARNING_MESSAGE);
//         } else {
//            UserDB.signupSELLERUSER(id, pw, name, gender, birthYYYYMMDD, phone, email, address, pwhint, pwres,
//                  businessname, businessaddress, banknum, coin);
//            JOptionPane.showMessageDialog(null, "           ȸ������ �Ϸ� \n���忡 ���Ű��� ȯ���մϴ�.");
//            setVisible(true);
//
//         }
//      }
//
//   }
//}

public class LoginTest {

   public static void main(String[] args) {

      new Login(); // �α���
//      new MemberChoice(); //ȸ�� �з� â
//      new Signup(); //�Ϲ� ȸ������ â
//      new SignUp_p(); //����� ȸ������ â
//      new IDsearch();  //���̵� ã��
//      new PWsearch();  //��й�ȣ ã��
//      new CardChoice(); //ī��� ����
//      new NewPassWord(); //���ο� ��й�ȣ �Է� â

   }

}