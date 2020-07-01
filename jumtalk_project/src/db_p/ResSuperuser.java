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
		jButton1 = new JButton("�Ϲ�ȸ�� ����Ʈ");
		jButton1.addActionListener(this);
		jButton2 = new JButton("������ȸ�� ����Ʈ");
		jButton2.addActionListener(this);
		jButton3 = new JButton("���Խ��� ����Ʈ");
		jButton3.addActionListener(this);
		jButton4 = new JButton("�� ����Ʈ");
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
		add(jb = new JButton("����"));
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
		add(jb = new JButton("����"));
		jb.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new SellerReviseInfor(sellUser.id);

	}
}

class ReviseInfor extends JFrame implements ActionListener { // �������� ���� ������

	JLabel profile; // ������
	JLabel photo; // ����
	JLabel helloMsg; // �λ縻
	JLabel pw; // ��й�ȣ
	JLabel phoneNum; // ��ȭ��ȣ
	JLabel email; // �̸���
	JLabel address; // �ּ�
	JLabel cardNum; // ī���ȣ
	JLabel pwHint; // ��й�ȣ ��Ʈ
	JLabel hintAnswer; // ��й�ȣ ��Ʈ ��
	ArrayList<JLabel> information; // �������� ����Ʈ
	JButton reviseGo; // �������� ���� �Ϸ� ��ư
	JButton back; // �������� ���� ������ ��ư
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

	public ReviseInfor(String userID) { // �������� ������

		setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ
		this.userID = userID;
		setBounds(700, 100, 500, 700);
		setLayout(null);

		// �������� �� ����
		profile = new JLabel("�� ������");
		photo = new JLabel("���� ����");
		helloMsg = new JLabel("�λ縻 ����");
		pw = new JLabel("PW ����");
		email = new JLabel("�̸��� ����");
		phoneNum = new JLabel("��ȭ��ȣ ����");
		address = new JLabel("�ּ� ����");
		cardNum = new JLabel("ī���ȣ ����");
		pwHint = new JLabel("PW��Ʈ ����");
		hintAnswer = new JLabel("��Ʈ �� ����");
		photoButton = new JButton("���� ����");
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

		// �������� ����Ʈ�� �������� �� ���
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

		pwhint = new JComboBox(passWordHint_S);

		// �����Ϸ� ��ư
		reviseGo = new JButton("���� �Ϸ�");
		// �������� ���� ������ ������ ��ư
		back = new JButton("������");

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

		for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
			information.get(i).setBounds(10, i * 60, 100, 50);
		}

		reviseGo.addActionListener(this); // �����Ϸ� ��ư
		back.addActionListener(this); // ������ ��ư
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

		add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
		add(back); // �������� ���� �����ӿ� ������ ��ư �߰�
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
			add(jl); // �������� ���� �����ӿ� �������� �� �߰�
		}

		setVisible(true);

	}

	ProfileInOut pfio = ProfileInOut.getprofileInout();

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
			// �������� ���� ������ �������
			setVisible(false);
		} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
			// �����Ϸ� �˾� ����� ������ �������

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
				JOptionPane.showMessageDialog(null, "�����Ϸ�");
				setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "��й�ȣ ��Ʈ�� �������ּ���");
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
				JOptionPane.showMessageDialog(null, "jpg���ϸ� �����մϴ�");
			}
		}

	}

	class FileReviseFrame extends JFrame {

		FileDialog fd;

		public FileReviseFrame() {
			fd = new FileDialog(this, "���Ͽ���", FileDialog.LOAD);
			fd.setLocation(510, 210);
			fd.setVisible(true);

		}
	}

}

class SellerReviseInfor extends JFrame implements ActionListener { // ������ ���� ���� ������ (�Һ��� ���� ������ ���)

	JLabel profile; // ������
	JLabel photo; // ����
	JLabel helloMsg; // �λ縻
	JLabel pw; // ��й�ȣ
	JLabel phoneNum; // ��ȭ��ȣ
	JLabel email; // �̸���
	JLabel address; // �ּ�
	JLabel cardNum; // ī���ȣ
	JLabel pwHint; // ��й�ȣ ��Ʈ
	Vector<String> passWordHint_S = new Vector<String>();
	JComboBox pwhint;
	JLabel hintAnswer; // ��й�ȣ ��Ʈ ��
	JLabel workplaceName; // ������ ����� �̸�
	JLabel workplaceAddress; // ������ ����� �ּ�
	ArrayList<JLabel> information; // �������� ����Ʈ
	JButton reviseGo; // �������� ���� �Ϸ� ��ư
	JButton back; // �������� ���� ������ ��ư
	JButton photoButton;

	JLabel photoFileName;

	JTextField helloText; // �λ縻 �����Է�
	JPasswordField pwField; // ��й�ȣ �����Է�
	JTextField phoneNumText; // ����ȣ �����Է�
	JTextField emailText; // �̸��� �����Է�
	JTextField addressText; // �ּ� �����Է�
	JTextField cardNumText; // ī���ȣ �����Է�
	JTextField pwHintText; // ��й�ȣ ��Ʈ �����Է�
	JTextField hintAnswerText; // ��Ʈ �� �����Է�
	JTextField workNameText; //
	JTextField workAddressText;
	ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();

	String userID;
	ProfileInOut pfio = ProfileInOut.getprofileInout();

	public SellerReviseInfor(String userID) { // �������� ������
		this.userID = userID;
		setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ

		setBounds(700, 100, 500, 800);
		setLayout(null);

		// �������� �� ����
		profile = new JLabel("�� ������");
		photo = new JLabel("���� ����");
		helloMsg = new JLabel("�λ縻 ����");
		pw = new JLabel("PW ����");
		email = new JLabel("�̸��� ����");
		phoneNum = new JLabel("��ȭ��ȣ ����");
		address = new JLabel("�ּ� ����");
		cardNum = new JLabel("���¹�ȣ ����");
		pwHint = new JLabel("PW��Ʈ ����");
		hintAnswer = new JLabel("��Ʈ �� ����");
		workplaceName = new JLabel("����� �̸�");
		workplaceAddress = new JLabel("����� �ּ�");

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

		photoButton = new JButton("���� ����");
		photoButton.addActionListener(this);
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

		pwhint = new JComboBox(passWordHint_S);

		// �������� ����Ʈ�� �������� �� ���
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

		// �����Ϸ� ��ư
		reviseGo = new JButton("���� �Ϸ�");
		// �������� ���� ������ ������ ��ư
		back = new JButton("������");

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

		for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
			information.get(i).setBounds(10, i * 60, 100, 50);
		}

		reviseGo.addActionListener(this); // �����Ϸ� ��ư
		back.addActionListener(this); // ������ ��ư

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

		add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
		add(back); // �������� ���� �����ӿ� ������ ��ư �߰�

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
			add(jl); // �������� ���� �����ӿ� �������� �� �߰�
		}

		setVisible(true);

	}

	FileReviseFrame fileReviseFrame;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
			// �������� ���� ������ �������
			setVisible(false);
		} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
			// �����Ϸ� �˾� ����� ������ �������
			System.out.println("������");
			if (!(helloText.getText().equals(""))) {
				System.out.println("������1");
				UserDB.setPROFILE_TEXT(userID, helloText.getText());
			}
			if (!(pwField.getText().equals(""))) {
				System.out.println("������2");
				UserDB.setPW(userID, pwField.getText());
			}
			if (!(phoneNumText.getText().equals(""))) {
				System.out.println("������3");
				UserDB.setPHONE(userID, phoneNumText.getText());
			}
			if (!(emailText.getText().equals(""))) {
				System.out.println("������4");
				UserDB.setEMAIL(userID, emailText.getText());
			}
			if (!(addressText.getText().equals(""))) {
				System.out.println("������5");
				UserDB.setADDRESS(userID, addressText.getText());
			}
			if (!(cardNumText.getText().equals(""))) {
				System.out.println("������6");
				UserDB.setCARDNUMBER(userID, cardNumText.getText());
			}
			if (!(pwHintText.getText().equals(""))) {
				System.out.println("������7");
				UserDB.setPWHINT(userID, pwHintText.getText());
			}
			if (!(hintAnswerText.getText().equals(""))) {
				System.out.println("������8");
				System.out.println(hintAnswerText.getText());
				UserDB.setPWRES(userID, hintAnswerText.getText());
			}
			if (!(workNameText.getText().equals(""))) {
				System.out.println("������9");
				System.out.println(workNameText.getText());
				UserDB.setBUSINESSNAME(userID, workNameText.getText());
			}
			if (!(workAddressText.getText().equals(""))) {
				System.out.println("������10");
				System.out.println(workAddressText.getText() + userID);
				UserDB.setBUSINESSADDRESS(userID, workAddressText.getText());
			}
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
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
				JOptionPane.showMessageDialog(null, "jpg���ϸ� �����մϴ�");
			}

		}

	}

	class FileReviseFrame extends JFrame {

		FileDialog fd;

		public FileReviseFrame() {

			fd = new FileDialog(this, "���Ͽ���", FileDialog.LOAD);
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
