package db_p;

import java.awt.Dimension;

import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ResSuperuser extends JPanel implements ActionListener {
	String userID;
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;
	JButton jButton4;

	public ResSuperuser(String userID) {
		this.userID = userID;
		setLayout(new GridLayout(2, 1, 10, 10));
		jButton1 = new JButton("일반회원 리스트");
		jButton1.addActionListener(this);
		jButton2 = new JButton("점술가회원 리스트");
		jButton2.addActionListener(this);
		jButton3 = new JButton("가입승인 리스트");
		jButton3.addActionListener(this);
		jButton4 = new JButton("블랙 리스트");
		jButton4.addActionListener(this);
		add(jButton1);
		add(jButton2);
		add(jButton3);
		add(jButton4);

		setBounds(700, 200, 500, 670);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jButton1)) {
			new UserList();
		} else if (e.getSource().equals(jButton2)) {
			new Sellerlist();
		} else if (e.getSource().equals(jButton3)) {
			new SignAcceptList();
		} else if (e.getSource().equals(jButton4)) {
			new BlackList();
		}

	}

}

class UserList extends JFrame {

	public UserList() {
		setBounds(950, 200, 500, 700);
		Dimension di = new Dimension();
		ArrayList<normalUser> arr = UserDB.getNomUser();

		di.setSize(450, arr.size() * 60);
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(arr.size(), 1, 10, 10));
		for (normalUser normalUser : arr) {
			jp1.add(new Userpanel(normalUser));
		}

		jp1.setPreferredSize(di);
		jp1.setBounds(0, 0, 475, 1000);
		JScrollPane jsc = new JScrollPane(jp1);
		add(jsc);

		setVisible(true);
	}

}

class Userpanel extends JPanel implements ActionListener {
	normalUser normalUser;
	JButton jb;

	public Userpanel(normalUser normalUser) {
		this.normalUser = normalUser;
		setLayout(new GridLayout(1, 3));
		setSize(450, 50);
		add(new JLabel(normalUser.id));
		add(new JLabel(normalUser.name));
		add(jb = new JButton("수정"));
		jb.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ReviseInfor(normalUser.id);

	}
}

class Sellerlist extends JFrame {

	public Sellerlist() {
		setBounds(950, 200, 500, 700);
		Dimension di = new Dimension();
		ArrayList<sellUser> arr = UserDB.getSELLER();

		di.setSize(450, arr.size() * 60);
		JPanel jp1 = new JPanel();
		jp1.setLayout(new GridLayout(arr.size(), 1, 10, 10));
		for (sellUser sellUser : arr) {
			jp1.add(new Sellerpanel(sellUser));
		}

		jp1.setPreferredSize(di);
		jp1.setBounds(0, 0, 475, 1000);
		JScrollPane jsc = new JScrollPane(jp1);
		add(jsc);

		setVisible(true);
	}

}

class Sellerpanel extends JPanel implements ActionListener {
	sellUser sellUser;
	JButton jb;

	public Sellerpanel(sellUser sellUser) {
		this.sellUser = sellUser;
		setLayout(new GridLayout(1, 3));
		setSize(450, 50);
		add(new JLabel(sellUser.id));
		add(new JLabel(sellUser.name));
		add(jb = new JButton("수정"));
		jb.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SellerReviseInfor(sellUser.id);

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

	JTextField helloText;
	JPasswordField pwField;
	JTextField phoneNumText;
	JTextField emailText;
	JTextField addressText;
	JTextField cardNumText;
//    JTextField pwHintText;
	JTextField hintAnswerText;
	ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
	Vector<String> passWordHint_S = new Vector<String>();
	JComboBox pwhint;
	FileReviseFrame fileReviseFrame;
	String userID;

	public ReviseInfor(String userID) { // 정보수정 생성자

		setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀
		this.userID = userID;
		setBounds(700, 100, 500, 700);
		setLayout(null);

		// 개인정보 라벨 생성
		profile = new JLabel("상세 프로필");
		photo = new JLabel("사진 수정");
		helloMsg = new JLabel("인사말 수정");
		pw = new JLabel("PW 수정");
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

		photoFileName = new JLabel(ii);

		helloText = new JTextField();
		pwField = new JPasswordField();
		phoneNumText = new JTextField();
		emailText = new JTextField();
		addressText = new JTextField();
		cardNumText = new JTextField();
//       pwHintText = new JTextField();
		hintAnswerText = new JTextField();

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
		pwhint.setBounds(130, 480, 300, 50);

		photoFileName.setBounds(130, 60, 200, 50);
		photoButton.setBounds(330, 60, 100, 50);
		helloText.setBounds(130, 120, 300, 50);
		pwField.setBounds(130, 180, 300, 50);
		phoneNumText.setBounds(130, 240, 300, 50);
		emailText.setBounds(130, 300, 300, 50);
		addressText.setBounds(130, 360, 300, 50);
		cardNumText.setBounds(130, 420, 300, 50);
//       pwHintText.setBounds(130, 480, 300, 50);
		hintAnswerText.setBounds(130, 540, 300, 50);

		for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
			information.get(i).setBounds(10, i * 60, 100, 50);
		}

		reviseGo.addActionListener(this); // 수정완료 버튼
		back.addActionListener(this); // 나가기 버튼
		photoButton.addActionListener(this);

		helloText.setText(UserDB.getPROFILE_TEXT(userID));
		pwField.setText(UserDB.getPW(userID));
		phoneNumText.setText(UserDB.getPHONE(userID));
		emailText.setText(UserDB.getEMAIL(userID));
		addressText.setText(UserDB.getADDRESS(userID));
		cardNumText.setText(UserDB.getCARDNUMBER(userID));
//       pwHintText.setText(UserDB.getpw);
		hintAnswerText.setText(UserDB.getPWRES(userID));

		pwhint.setSelectedItem(UserDB.getPWHINT(userID));

		add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
		add(back); // 개인정보 수정 프레임에 나가기 버튼 추가
//       add(passWordHint_S);

		add(photoFileName);
		add(photoButton);
		add(helloText);
		add(pwField);
		add(phoneNumText);
		add(emailText);
		add(addressText);
		add(cardNumText);
//       add(pwHintText);
		add(hintAnswerText);
		add(pwhint);

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
			setVisible(false);
		} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
			// 수정완료 팝업 띄운후 프레임 사라지기

			if (!(helloText.equals(""))) {
				UserDB.setPROFILE_TEXT(userID, helloText.getText());
			}
			if (!(pwField.equals(""))) {
				UserDB.setPW(userID, pwField.getText());
			}
			if (!(phoneNumText.equals(""))) {
				UserDB.setPHONE(userID, phoneNumText.getText());
			}
			if (!(emailText.equals(""))) {
				UserDB.setEMAIL(userID, emailText.getText());
			}
			if (!(addressText.equals(""))) {
				UserDB.setADDRESS(userID, addressText.getText());
			}
			if (!(cardNumText.equals(""))) {
				UserDB.setCARDNUMBER(userID, cardNumText.getText());
			}
			if (!(pwhint.getSelectedItem().equals(""))) {
				UserDB.setPWHINT(userID, pwhint.getSelectedItem().toString());
			}
			if (!(hintAnswerText.equals(""))) {
				UserDB.setPWRES(userID, hintAnswerText.getText());
			}
			boolean pwhintChk = pwhint.getSelectedIndex() == 0;
			if (pwhintChk == false) {
				JOptionPane.showMessageDialog(null, "수정완료");
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호 힌트를 선택해주세요");
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

	class FileReviseFrame extends JFrame {

		FileDialog fd;

		public FileReviseFrame() {
			fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
			fd.setLocation(510, 210);
			fd.setVisible(true);

		}
	}

}

class SellerReviseInfor extends JFrame implements ActionListener { // 점술가 정보 수정 프레임 (소비자 수정 프레임 상속)

	JLabel profile; // 상세정보
	JLabel photo; // 사진
	JLabel helloMsg; // 인사말
	JLabel pw; // 비밀번호
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
	JPasswordField pwField; // 비밀번호 수정입력
	JTextField phoneNumText; // 폰번호 수정입력
	JTextField emailText; // 이메일 수정입력
	JTextField addressText; // 주소 수정입력
	JTextField cardNumText; // 카드번호 수정입력
	JTextField pwHintText; // 비밀번호 힌트 수정입력
	JTextField hintAnswerText; // 힌트 답 수정입력
	JTextField workNameText; //
	JTextField workAddressText;
	ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();

	String userID;
	ProfileInOut pfio = ProfileInOut.getprofileInout();

	public SellerReviseInfor(String userID) { // 정보수정 생성자
		this.userID = userID;
		setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀

		setBounds(700, 100, 500, 800);
		setLayout(null);

		// 개인정보 라벨 생성
		profile = new JLabel("상세 프로필");
		photo = new JLabel("사진 수정");
		helloMsg = new JLabel("인사말 수정");
		pw = new JLabel("PW 수정");
		email = new JLabel("이메일 수정");
		phoneNum = new JLabel("전화번호 수정");
		address = new JLabel("주소 수정");
		cardNum = new JLabel("계좌번호 수정");
		pwHint = new JLabel("PW힌트 수정");
		hintAnswer = new JLabel("힌트 답 수정");
		workplaceName = new JLabel("사업장 이름");
		workplaceAddress = new JLabel("사업장 주소");

		helloText = new JTextField();
		pwField = new JPasswordField();
		phoneNumText = new JTextField();
		emailText = new JTextField();
		addressText = new JTextField();
		cardNumText = new JTextField();
		pwHintText = new JTextField();
		hintAnswerText = new JTextField();
		workNameText = new JTextField();
		workAddressText = new JTextField();
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

		reviseGo.setBounds(370, 10, 100, 40);
		back.setBounds(10, 10, 100, 40);

		photoFileName.setBounds(130, 10, 100, 100);
		photoButton.setBounds(330, 60, 100, 50);
		helloText.setBounds(130, 120, 300, 50);
		pwField.setBounds(130, 180, 300, 50);
		phoneNumText.setBounds(130, 240, 300, 50);
		emailText.setBounds(130, 300, 300, 50);
		addressText.setBounds(130, 360, 300, 50);
		cardNumText.setBounds(130, 420, 300, 50);
		pwhint.setBounds(130, 480, 300, 50);
		hintAnswerText.setBounds(130, 540, 300, 50);
		workNameText.setBounds(130, 600, 300, 50);
		workAddressText.setBounds(130, 660, 300, 50);

		for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
			information.get(i).setBounds(10, i * 60, 100, 50);
		}

		reviseGo.addActionListener(this); // 수정완료 버튼
		back.addActionListener(this); // 나가기 버튼

		helloText.setText(UserDB.getPROFILE_TEXT(userID));
		pwField.setText(UserDB.getPW(userID));
		phoneNumText.setText(UserDB.getPHONE(userID));
		emailText.setText(UserDB.getEMAIL(userID));
		addressText.setText(UserDB.getADDRESS(userID));
		cardNumText.setText(UserDB.getBANKNUMBER(userID));
//    	      pwHintText.setText(UserDB.getpw);
		hintAnswerText.setText(UserDB.getPWRES(userID));

		pwhint.setSelectedItem(UserDB.getPWHINT(userID));
		workNameText.setText(UserDB.getBUSINESSNAME(userID));
		workAddressText.setText(UserDB.getBUSINESSADDRESS(userID));

		add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
		add(back); // 개인정보 수정 프레임에 나가기 버튼 추가

		add(helloText);
		add(pwField);
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
			setVisible(false);
		} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
			// 수정완료 팝업 띄운후 프레임 사라지기
			System.out.println("들어오니");
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
				UserDB.setCARDNUMBER(userID, cardNumText.getText());
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
			JOptionPane.showMessageDialog(null, "수정완료");
			setVisible(false);

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

	class FileReviseFrame extends JFrame {

		FileDialog fd;

		public FileReviseFrame() {

			fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
			fd.setLocation(510, 210);
			fd.setVisible(true);

		}
	}

}

class SignAcceptList extends JFrame {

	public SignAcceptList() {
		setBounds(950, 200, 500, 700);
		setLayout(null);
		
		setVisible(true);
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
}

class BlackList extends JFrame {

	public BlackList() {
		setBounds(950, 200, 500, 700);
		setLayout(null);
		
		
		setVisible(true);
	}
}
