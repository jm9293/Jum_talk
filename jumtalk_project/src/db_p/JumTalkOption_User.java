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

class SellerReviseInfor2 extends JFrame
implements ActionListener, WindowListener { // 점술가 정보 수정 프레임 (소비자 수정 프레임 상속)

	SellerReviseInfor2 me = this;
	
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
	JButton back; // 개인정보 수정 나가기 버튼
	JButton photoButton;

	JLabel photoFileName;

	JTextField helloText; // 인사말 수정입력
	JPasswordField nowPwText;
	JPasswordField pwField; // 비밀번호 수정입력
	JPasswordField pwChkText;
	JTextField phoneNumText; // 폰번호 수정입력
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
	

	public SellerReviseInfor2(String userID) { // 정보수정 생성자
		this.userID = userID;
		setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀

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

		helloText = new JTextField();
		nowPwText = new JPasswordField();
		pwField = new JPasswordField();
		pwChkText = new JPasswordField();
		phoneNumText = new JTextField();
		emailText = new JTextField();
		addressText = new JTextField();
		cardNumText = new JTextField();
		pwHintText = new JTextField();
		hintAnswerText = new JTextField();
		workNameText = new JTextField();
		workAddressText = new JTextField();

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
		information.add(email);
		information.add(phoneNum);
		information.add(address);
		information.add(cardNum);
		information.add(pwHint);
		information.add(hintAnswer);
		information.add(workplaceName);
		information.add(workplaceAddress);

		// 수정완료 버튼
		reviseGo = new JButton("수정 완료");
		// 개인정보 수정 프레임 나가기 버튼
		back = new JButton("나가기");

		reviseGo.setBounds(470, 10, 100, 40);
		back.setBounds(10, 10, 100, 40);

		photoFileName.setBounds(130, 10, 100, 100);
		photoButton.setBounds(330, 60, 100, 50);
		helloText.setBounds(130, 120, 300, 50);
		nowPwText.setBounds(130, 180, 300, 50);
		pwField.setBounds(130, 240, 300, 50);
		pwChkText.setBounds(130, 300, 300, 50);
		phoneNumText.setBounds(130, 360, 300, 50);
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
		back.addActionListener(this); // 나가기 버튼

		System.out.println(UserDB.getBANKNUMBER(userID));

		helloText.setText(UserDB.getPROFILE_TEXT(userID));
		nowPwText.setText(UserDB.getPW(userID));
		phoneNumText.setText(UserDB.getPHONE(userID));
		emailText.setText(UserDB.getEMAIL(userID));
		addressText.setText(UserDB.getADDRESS(userID));
		cardNumText.setText(UserDB.getBANKNUMBER(userID).split("/")[0]);
//    	      pwHintText.setText(UserDB.getpw);
		hintAnswerText.setText(UserDB.getPWRES(userID));
		bankBox.setSelectedItem(UserDB.getBANKNUMBER(userID).split("/")[1]);
		pwhint.setSelectedItem(UserDB.getPWHINT(userID));
		workNameText.setText(UserDB.getBUSINESSNAME(userID));
		workAddressText.setText(UserDB.getBUSINESSADDRESS(userID));

		add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
		add(back); // 개인정보 수정 프레임에 나가기 버튼 추가
		add(nowPw);
		add(pwChk);

		add(helloText);
		add(nowPwText);
		add(pwField);
		add(pwChkText);
		add(phoneNumText);
		add(emailText);
		add(addressText);
		add(cardNumText);
		add(pwHintText);
		add(hintAnswerText);
		add(workNameText);
		add(workAddressText);
		add(profile);
		add(pwhint);

		add(photoButton);
		add(photoFileName);
		add(bankBox);
		

		for (JLabel jl : information) {
			add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
		}

		setVisible(true);

	}

	FileReviseFrame fileReviseFrame;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) { // 개인정보수정 프레임에서 나가기 버튼 클릭
			// 개인정보 수정 프레임 사라지기
//			setVisible(false);
			dispose();
		} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
			// 수정완료 팝업 띄운후 프레임 사라지기
			System.out.println("들어오니");

			if (pwhint.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해 주세요");
			} else if (!(pwField.getText().equals(pwChkText.getText()))) {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요");
			} else if (bankBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "은행을 선택해 주세요");
			} else {
				JOptionPane.showMessageDialog(null, "수정완료");
				if (!(helloText.getText().equals(""))) {
					System.out.println("들어오니1");
					UserDB.setPROFILE_TEXT(userID, helloText.getText());
				}
				if (!(pwField.getText().equals(""))) {
					System.out.println("들어오니2");
					UserDB.setPW(userID, pwField.getText());
				}
				if (!(phoneNumText.getText().equals(""))) {
					System.out.println("들어오니3");
					UserDB.setPHONE(userID, phoneNumText.getText());
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
				if (!(pwHintText.getText().equals(""))) {
					System.out.println("들어오니7");
					UserDB.setPWHINT(userID, pwHintText.getText());
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

//				setVisible(false);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		me = null;
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
}

class FileReviseFrame extends JFrame {

	FileDialog fd;

	public FileReviseFrame() {

		fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
		fd.setLocation(510, 210);
		fd.setVisible(true);
	}
}

//	class OptionAction implements ActionListener{	//고객센터, 개인정보 수정 버튼 액션 리스너
//		
////		JButton opt1;	//고객센터 버튼
////		JButton opt2;	//개인정보 수정 버튼
////		JFrame help;
//		JPanel notice;	//공지사항 패널
//		JPanel message;	//메세지 패널
//		
////		JumTalkOption jt;
//		ReviseInfor ri;	//소비자 개인정보 수정 클래스 
//		JumTalkOption_User jto;
////		Opt1_Frame opt1_F;
//		
//		public OptionAction(JPanel notice, JPanel message) {	//공지사항, 메세지 패널(visible)을 위해 매개변수
//			this.notice = notice;
//			this.message = message;
//		}
//		
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//		
//				
//		}
//	}

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

//			oa = new OptionAction(ntcPanel,msgPanel);	//고객센터, 개인정보 수정 액션 리스너 생성

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
			JOptionPane.showMessageDialog(null, "로그아웃 완료");
			System.exit(0);
		} else if (e.getSource().equals(optButton.get(1))) {
			if (UserDB.getUSERKIND(userID) == 0) {
				if(ri == null) {
				ri = new ReviseInfor();
				}
			} else {
				if(ri == null) {
				ri = new SellerReviseInfor2(userID);
				}
			}
		}

	}

	class ReviseInfor extends JFrame implements ActionListener { // 개인정보 수정 프레임

		JLabel profile; // 상세정보
		JLabel photo; // 사진
		JLabel helloMsg; // 인사말
		JLabel pw; // 비밀번호
		JLabel phoneNum; // 전화번호
		JLabel email; // 이메일
		JLabel address; // 주소
		JLabel cardNum; // 카드번호
		JLabel pwHint; // 비밀번호 힌트
		JLabel hintAnswer; // 비밀번호 힌트 답
		ArrayList<JLabel> information; // 개인정보 리스트
		JButton reviseGo; // 개인정보 수정 완료 버튼
		JButton back; // 개인정보 수정 나가기 버튼
		JButton photoButton;
		JLabel photoFileName;
		JLabel nowPw;
		JLabel pwChk;

		JTextField helloText;
		JPasswordField nowPwField;
		JPasswordField pwField;
		JPasswordField pwChkText;
		JTextField phoneNumText;
		JTextField emailText;
		JTextField addressText;
		JTextField cardNumText;
//		JTextField pwHintText;
		JTextField hintAnswerText;
		ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
		Vector<String> passWordHint_S = new Vector<String>();
//        Vector<String> bankChoice = new Vector<String>();
//        JComboBox<String> bankBox;
		JComboBox pwhint;
		FileReviseFrame fileReviseFrame;

		public ReviseInfor() { // 정보수정 생성자

			setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀

			setBounds(700, 100, 600, 900);
			setLayout(null);

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
			ImageIcon ii = new ImageIcon(pfio.download(userID));
			Image img = ii.getImage();
			img = img.getScaledInstance(100, 100, img.SCALE_SMOOTH);
			ii = new ImageIcon(img);

//	        bankChoice.add("-은행선택-");
//	        bankChoice.add("기업은행");
//	        bankChoice.add("농협은행");
//	        bankChoice.add("신한은행");
//	        bankChoice.add("산업은행");
//	        bankChoice.add("우리은행");
//	        bankChoice.add("씨티은행");
//	        bankChoice.add("하나은행");
//	        bankChoice.add("광주은행");
//	        bankChoice.add("경남은행");
//	        bankChoice.add("대구은행");
//	        bankChoice.add("광주은행");
//	        bankChoice.add("부산은행");
//	        bankChoice.add("수협은행");
//	        bankChoice.add("제주은행");
//	        bankChoice.add("제주은행");
//	        bankChoice.add("전북은행");
//	        bankChoice.add("새마을금고");
//	        bankChoice.add("신협은행");
//	        bankChoice.add("수협은행");
//	        bankChoice.add("우체국");

//	        bankBox = new JComboBox<String>(bankChoice);

			photoFileName = new JLabel(ii);

			helloText = new JTextField();
			pwField = new JPasswordField();
			phoneNumText = new JTextField();
			emailText = new JTextField();
			addressText = new JTextField();
			cardNumText = new JTextField();
//			pwHintText = new JTextField();
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
			back = new JButton("나가기");

			reviseGo.setBounds(370, 10, 100, 40);
			back.setBounds(10, 10, 100, 40);
			pwhint.setBounds(130, 700, 300, 50);

			photoFileName.setBounds(100, 60, 200, 100);
			photoButton.setBounds(330, 80, 100, 50);
			helloText.setBounds(130, 220, 300, 50);
			nowPwField.setBounds(130, 280, 300, 50);
			pwField.setBounds(130, 340, 300, 50);
			pwChkText.setBounds(130, 400, 300, 50);
			emailText.setBounds(130, 460, 300, 50);
			phoneNumText.setBounds(130, 520, 300, 50);
			addressText.setBounds(130, 580, 300, 50);
			cardNumText.setBounds(130, 640, 300, 50);
//			pwHintText.setBounds(130, 480, 300, 50);
			hintAnswerText.setBounds(130, 760, 300, 50);
//			bankBox.setBounds(450, 640, 100, 50);

			for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
				information.get(i).setBounds(10, (i * 60) + 220, 100, 50);
			}
			helloMsg.setBounds(10, 220, 100, 50);
			photo.setBounds(10, 60, 100, 100);
			nowPw.setBounds(10, 280, 100, 50);
			pw.setBounds(10, 340, 100, 50);
			pwChk.setBounds(10, 400, 100, 50);

			reviseGo.addActionListener(this); // 수정완료 버튼
			back.addActionListener(this); // 나가기 버튼
			photoButton.addActionListener(this);

			helloText.setText(UserDB.getPROFILE_TEXT(userID));
			nowPwField.setText(UserDB.getPW(userID));
			phoneNumText.setText(UserDB.getPHONE(userID));
			emailText.setText(UserDB.getEMAIL(userID));
			addressText.setText(UserDB.getADDRESS(userID));
			cardNumText.setText(UserDB.getCARDNUMBER(userID));
//			pwHintText.setText(UserDB.getpw);
			hintAnswerText.setText(UserDB.getPWRES(userID));

			pwhint.setSelectedItem(UserDB.getPWHINT(userID));

			add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
			add(back); // 개인정보 수정 프레임에 나가기 버튼 추가
//			add(passWordHint_S);
			add(nowPw);
			add(pwChk);

			add(photoFileName);
			add(photoButton);
			add(helloText);
			add(pwField);
			add(phoneNumText);
			add(emailText);
			add(addressText);
			add(cardNumText);
//			add(pwHintText);
			add(hintAnswerText);
			add(pwhint);
			add(nowPwField);
			add(pwChkText);
//			add(bankBox);

			for (JLabel jl : information) {
				add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
			}

			setVisible(true);

		}

		ProfileInOut pfio = ProfileInOut.getprofileInout();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(back)) { // 개인정보수정 프레임에서 나가기 버튼 클릭
				// 개인정보 수정 프레임 사라지기
//				setVisible(false);
				dispose();
			} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
				// 수정완료 팝업 띄운후 프레임 사라지기

				boolean pwhintChk = pwhint.getSelectedIndex() == 0;
				if (pwhintChk == true) {
					JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해주세요");
				} else if (!(pwField.getText().equals(pwChkText.getText()))) {
					JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요");
				} else {
					JOptionPane.showMessageDialog(null, "수정완료");
					if (!(helloText.getText().equals(""))) {
						UserDB.setPROFILE_TEXT(userID, helloText.getText());
					}
					if (!(pwField.getText().equals(""))) {
						UserDB.setPW(userID, pwField.getText());
					}
					if (!(phoneNumText.getText().equals(""))) {
						UserDB.setPHONE(userID, phoneNumText.getText());
					}
					if (!(emailText.getText().equals(""))) {
						UserDB.setEMAIL(userID, emailText.getText());
					}
					if (!(addressText.getText().equals(""))) {
						UserDB.setADDRESS(userID, addressText.getText());
					}
					if (!(cardNumText.getText().equals(""))) {
						UserDB.setCARDNUMBER(userID, cardNumText.getText());
					}
					if (!(pwhint.getSelectedItem().equals("원하는 질문을 선택하세요."))) {
						UserDB.setPWHINT(userID, pwhint.getSelectedItem().toString());
					}
					if (!(hintAnswerText.getText().equals(""))) {
						UserDB.setPWRES(userID, hintAnswerText.getText());
					}
//					setVisible(false);
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
				if(!(msgText.getText().isEmpty())) {
				MessageDB.saveMESSAGE("admin", userID, msgText.getText());
				JOptionPane.showMessageDialog(null, "전송 완료");
				// 메세지 입력창 초기화
				msgText.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "메세지를 입력하세요");
				}
			} else if (e.getSource().equals(msgButton.get(1))) { // 보낸 메세지함 버튼 클릭 했을 때
				sendMessage = new SendMessage();
			} else if (e.getSource().equals(msgButton.get(2))) { // 받은 메세지함 버튼 클릭 했을 때
				giveMessage = new GiveMessage();
			}

		}

	}

	class SendMessage extends JFrame implements ActionListener, MouseListener { // 보낸메세지함 프레임 클래스

		JTable sendList;
		JScrollPane scroll;
		JButton delete;
		
		
		String[][] arr2;

		public SendMessage() {
			setTitle("보낸 메세지함");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getFROM_MESSAGE(userID);
			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "보낸메세지함이 비었습니다.");
				return;
			}

			String[] arr = new String[] { "받은사람", "내용", "시간" };

			sendList = new JTable(new NotEditTable(arr2, arr));
			scroll = new JScrollPane(sendList);
			delete = new JButton("삭제");
			


			scroll.setBounds(0, 150, 500, 500);
			delete.setBounds(390, 660, 100, 50);
			

			sendList.addMouseListener(this);
			delete.addActionListener(this);

			add(delete);
			add(scroll);
			

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(delete)) {
			MessageDB.deleteSendMESSAGE(userID);
			JOptionPane.showMessageDialog(null, "삭제완료");
//			setVisible(false);
			dispose();
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource().equals(sendList)) {
				if (e.getClickCount() == 2) {
					SendMessageClick sendMessageClick = new SendMessageClick();
					sendMessageClick.writer.setText(arr2[sendList.getSelectedRow()][0]);
					sendMessageClick.content.setText(arr2[sendList.getSelectedRow()][1]);
					sendMessageClick.time.setText(arr2[sendList.getSelectedRow()][2]);
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

	}

	class SendMessageClick extends JFrame {

		JLabel writerLabel;
		JLabel contentLabel;
		JLabel timeLabel;

		JLabel writer;
		JLabel time;
		JTextArea content;

		public SendMessageClick() {

			setBounds(600, 100, 400, 500);
			setLayout(null);

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

	class GiveMessage extends JFrame
	implements ActionListener, MouseListener { // 받은메세지함 프레임 클래스

		JTable giveList;
		JScrollPane scroll;
		JButton delete;
		String[][] arr2;

		public GiveMessage() {
			setTitle("받은 메세지함");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getTO_MESSAGE(userID);
			String[] arr = new String[] { "보낸사람", "내용", "시간" };
			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "받은메세지함이 비었습니다.");
				return;
			}

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
//			setVisible(false);
			dispose();

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource().equals(giveList)) {
				if (e.getClickCount() == 2) {
					GiveMessageClick giveMessageClick = new GiveMessageClick();
					giveMessageClick.writer.setText(arr2[giveList.getSelectedRow()][0]);
					giveMessageClick.content.setText(arr2[giveList.getSelectedRow()][1]);
					giveMessageClick.time.setText(arr2[giveList.getSelectedRow()][2]);
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

	}

	class GiveMessageClick extends JFrame {

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

			setVisible(true);

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

			noticeFrame = new NoticeFrame(ntc);
		}
	}

	class NoticeFrame extends JFrame { // 공지 확인 프레임
		JLabel noticeLabel;
		JTextArea contentLabel;
		JLabel writerLabel;
		JLabel modifLabel;
		JLabel makeTimeLabel;

		JLabel title;
		JLabel makeTime;
		JLabel modifTime;
		JLabel writer;

		public NoticeFrame(Notice notice) {

			setTitle("공지사항");

			setBounds(jumTalkOption_User.getX() + jumTalkOption_User.getWidth() + 50, jumTalkOption_User.getY(), 700,
					800);
			setLayout(null);

			noticeLabel = new JLabel(notice.title);
			contentLabel = new JTextArea(notice.content);
			writerLabel = new JLabel(notice.writer);
			modifLabel = new JLabel(notice.modi_time);
			makeTimeLabel = new JLabel(notice.maketime);

			title = new JLabel("제목 : ");
			makeTime = new JLabel("작성시간 : ");
			modifTime = new JLabel("수정시간 : ");
			writer = new JLabel("작성자 : ");

			noticeLabel.setBounds(300, 10, 100, 50);
			contentLabel.setBounds(40, 100, 600, 600);
			writerLabel.setBounds(500, 50, 100, 50);
			makeTimeLabel.setBounds(80, 10, 200, 30);
			modifLabel.setBounds(80, 50, 200, 30);

			title.setBounds(255, 10, 50, 50);
			makeTime.setBounds(10, 10, 100, 30);
			modifTime.setBounds(10, 50, 100, 30);
			writer.setBounds(430, 50, 100, 50);

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
	}
}
