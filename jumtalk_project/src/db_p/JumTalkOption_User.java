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
implements ActionListener, WindowListener { // ������ ���� ���� ������ (�Һ��� ���� ������ ���)

	SellerReviseInfor2 me = this;
	
	JLabel profile; // ������
	JLabel photo; // ����
	JLabel helloMsg; // �λ縻
	JLabel nowPw;
	JLabel pw; // ��й�ȣ
	JLabel pwChk;
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
	JPasswordField nowPwText;
	JPasswordField pwField; // ��й�ȣ �����Է�
	JPasswordField pwChkText;
	JTextField phoneNumText; // ����ȣ �����Է�
	JTextField emailText; // �̸��� �����Է�
	JTextField addressText; // �ּ� �����Է�
	JTextField cardNumText; // ī���ȣ �����Է�
	JTextField pwHintText; // ��й�ȣ ��Ʈ �����Է�
	JTextField hintAnswerText; // ��Ʈ �� �����Է�
	JTextField workNameText; //
	JTextField workAddressText;
	ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();
	Vector<String> bankChoice = new Vector<String>();
	JComboBox<String> bankBox;
	String userID;
	ProfileInOut pfio = ProfileInOut.getprofileInout();
	

	public SellerReviseInfor2(String userID) { // �������� ������
		this.userID = userID;
		setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ

		setBounds(700, 0, 600, 900);
		setLayout(null);
		addWindowListener(this);

		// �������� �� ����
		profile = new JLabel("�� ������");
		photo = new JLabel("���� ����");
		helloMsg = new JLabel("�λ縻 ����");
		nowPw = new JLabel("���� PW");
		pw = new JLabel("PW ����");
		pwChk = new JLabel("PW Ȯ��");
		email = new JLabel("�̸��� ����");
		phoneNum = new JLabel("��ȭ��ȣ ����");
		address = new JLabel("�ּ� ����");
		cardNum = new JLabel("���¹�ȣ ����");
		pwHint = new JLabel("PW��Ʈ ����");
		hintAnswer = new JLabel("��Ʈ �� ����");
		workplaceName = new JLabel("����� �̸�");
		workplaceAddress = new JLabel("����� �ּ�");

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

		for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
			information.get(i).setBounds(10, i * 60 + 120, 100, 50);
		}

		photo.setBounds(10, 60, 100, 50);
		helloMsg.setBounds(10, 120, 100, 50);
		nowPw.setBounds(10, 180, 100, 50);
		pw.setBounds(10, 240, 100, 50);
		pwChk.setBounds(10, 300, 100, 50);

		reviseGo.addActionListener(this); // �����Ϸ� ��ư
		back.addActionListener(this); // ������ ��ư

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

		add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
		add(back); // �������� ���� �����ӿ� ������ ��ư �߰�
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
			add(jl); // �������� ���� �����ӿ� �������� �� �߰�
		}

		setVisible(true);

	}

	FileReviseFrame fileReviseFrame;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
			// �������� ���� ������ �������
//			setVisible(false);
			dispose();
		} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
			// �����Ϸ� �˾� ����� ������ �������
			System.out.println("������");

			if (pwhint.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "��й�ȣ ��Ʈ�� ������ �ּ���");
			} else if (!(pwField.getText().equals(pwChkText.getText()))) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� �Է����ּ���");
			} else if (bankBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "������ ������ �ּ���");
			} else {
				JOptionPane.showMessageDialog(null, "�����Ϸ�");
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
					String bankStr = (String) bankBox.getSelectedItem();
					UserDB.setBANKNUMBER(userID, cardNumText.getText() + "/" + bankStr);
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
				JOptionPane.showMessageDialog(null, "jpg���ϸ� �����մϴ�");
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

		fd = new FileDialog(this, "���Ͽ���", FileDialog.LOAD);
		fd.setLocation(510, 210);
		fd.setVisible(true);
	}
}

//	class OptionAction implements ActionListener{	//������, �������� ���� ��ư �׼� ������
//		
////		JButton opt1;	//������ ��ư
////		JButton opt2;	//�������� ���� ��ư
////		JFrame help;
//		JPanel notice;	//�������� �г�
//		JPanel message;	//�޼��� �г�
//		
////		JumTalkOption jt;
//		ReviseInfor ri;	//�Һ��� �������� ���� Ŭ���� 
//		JumTalkOption_User jto;
////		Opt1_Frame opt1_F;
//		
//		public OptionAction(JPanel notice, JPanel message) {	//��������, �޼��� �г�(visible)�� ���� �Ű�����
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

class OptionPanel extends JPanel implements ActionListener { // ��, ������ư �ؿ� �г�
	JPanel optPanel; // ������, �������� ���� ��ư�� ��� �г�

	JFrame ri;
	JLabel ntcLabel; // �������� ��
	ArrayList<JButton> optButton;
	NoticePanel ntcPanel; // �������� �������� �г�
	MessagePanel msgPanel; // �������� �޼��� �г�

	JButton opt1; // ������ ������
	JButton opt2; // ������ �������� ����

	String userID;
	JFrame jumTalkOption_User;

	public OptionPanel(String userID, JFrame jp) {
		this.userID = userID;
		this.jumTalkOption_User = jp;

		optButton = new ArrayList<JButton>(); // ����, �������� ���� ��ư ����Ʈ

		optPanel = new JPanel(); // ������ư �г�
		optPanel.setBounds(0, 615, 500, 50);

		ntcPanel = new NoticePanel(); // �������� �г�
		ntcPanel.setBounds(0, 0, 500, 200);
		msgPanel = new MessagePanel(); // �޼��� �г�
		msgPanel.setBounds(0, 210, 500, 400);

		opt1 = new JButton("�α׾ƿ�"); // ������ ��ư
		opt2 = new JButton("�������� ����"); // �������� ���� ��ư

		ntcLabel = new JLabel("��������"); // �������� �� (�ӽ�)

		ntcPanel.setLayout(null); // �������� �г� ���̾ƿ�
		msgPanel.setLayout(null); // �޼��� �г� ���̾ƿ�

		ntcLabel.setBounds(220, 30, 200, 50); // �������� �� ��ġ (�ӽ�)

		optButton.add(opt1); // ��ư ����Ʈ�� ������ư ����
		optButton.add(opt2); // ��ư ����Ʈ�� �������� ���� ��ư ����

		// �������� �г�
		ntcPanel.add(ntcLabel); // �������� �гο� �������� �� �߰�(�ӽ�)

		// �����ڿ��� ����

//			oa = new OptionAction(ntcPanel,msgPanel);	//������, �������� ���� �׼� ������ ����

		ntcPanel.setVisible(true); // �׼� �����ʷ� �������� �� (�������� �г�)
		msgPanel.setVisible(true); // �׼� �����ʷ� �������� �� (�޼��� �г�)

		setLayout(null); // ������, �������� ���� ��ư �ؿ� �г�

		optPanel.setLayout(new GridLayout(1, 2)); // ������, �������� ���� ��ư �г�

		optPanel.add(opt2); // ������, �������� ���� ��ư �гο� �������� ���� ��ư �߰�
		optPanel.add(opt1); // ������, �������� ���� ��ư �гο� ������ ��ư �߰�
		add(optPanel);
		add(ntcPanel); // �������� �г� �߰�
		add(msgPanel); // �޼��� �г� �߰�
		for (JButton jb : optButton) { // ������, �������� ������ư ����Ʈ
			jb.addActionListener(this);
		}

		repaint();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(optButton.get(0))) {
			// �α��� �гη� �ٲ�
			JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ϸ�");
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

		public ReviseInfor() { // �������� ������

			setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ

			setBounds(700, 100, 600, 900);
			setLayout(null);

			// �������� �� ����
			profile = new JLabel("�� ������");
			photo = new JLabel("���� ����");
			helloMsg = new JLabel("�λ縻 ����");
			nowPw = new JLabel("���� ��й�ȣ");
			pw = new JLabel("PW ����");
			pwChk = new JLabel("pw Ȯ��");
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

//	        bankChoice.add("-���༱��-");
//	        bankChoice.add("�������");
//	        bankChoice.add("��������");
//	        bankChoice.add("��������");
//	        bankChoice.add("�������");
//	        bankChoice.add("�츮����");
//	        bankChoice.add("��Ƽ����");
//	        bankChoice.add("�ϳ�����");
//	        bankChoice.add("��������");
//	        bankChoice.add("�泲����");
//	        bankChoice.add("�뱸����");
//	        bankChoice.add("��������");
//	        bankChoice.add("�λ�����");
//	        bankChoice.add("��������");
//	        bankChoice.add("��������");
//	        bankChoice.add("��������");
//	        bankChoice.add("��������");
//	        bankChoice.add("�������ݰ�");
//	        bankChoice.add("��������");
//	        bankChoice.add("��������");
//	        bankChoice.add("��ü��");

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

			for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
				information.get(i).setBounds(10, (i * 60) + 220, 100, 50);
			}
			helloMsg.setBounds(10, 220, 100, 50);
			photo.setBounds(10, 60, 100, 100);
			nowPw.setBounds(10, 280, 100, 50);
			pw.setBounds(10, 340, 100, 50);
			pwChk.setBounds(10, 400, 100, 50);

			reviseGo.addActionListener(this); // �����Ϸ� ��ư
			back.addActionListener(this); // ������ ��ư
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

			add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
			add(back); // �������� ���� �����ӿ� ������ ��ư �߰�
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
				add(jl); // �������� ���� �����ӿ� �������� �� �߰�
			}

			setVisible(true);

		}

		ProfileInOut pfio = ProfileInOut.getprofileInout();

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
				// �������� ���� ������ �������
//				setVisible(false);
				dispose();
			} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
				// �����Ϸ� �˾� ����� ������ �������

				boolean pwhintChk = pwhint.getSelectedIndex() == 0;
				if (pwhintChk == true) {
					JOptionPane.showMessageDialog(null, "��й�ȣ ��Ʈ�� �������ּ���");
				} else if (!(pwField.getText().equals(pwChkText.getText()))) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� �Է����ּ���");
				} else {
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
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
					if (!(pwhint.getSelectedItem().equals("���ϴ� ������ �����ϼ���."))) {
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
					JOptionPane.showMessageDialog(null, "jpg���ϸ� �����մϴ�");
				}
			}

		}

	}

	class MessagePanel extends JPanel implements ActionListener { // ������_ �����ڿ��� �޼��� �г�

		JLabel msgLabel; // �޼��� ��
		JButton msgSend; // ���� �޼�����
		JButton msgGive; // ���� �޼�����
		JButton msgTextBt; // �޼��� ������
		JTextArea msgText; // �޼��� �Է�â
		SendMessage sendMessage; // ���� �޼����� ������ Ŭ����
		GiveMessage giveMessage; // ���� �޼����� ������ Ŭ����
		
		JScrollPane msgTextScroll; // �޼��� �Է� â ��ũ��

		ArrayList<JButton> msgButton; // �޼��� ������, ���� �޼�����, �����޼����� ��ư ����Ʈ

		public MessagePanel() { // �޼��� �г� ������

			msgButton = new ArrayList<JButton>(); // �޼��� ������, ����/���� �޼����� ��ư ����Ʈ

			msgLabel = new JLabel("�����ڿ��� �޼���"); // �޼��� �Է�â ��
			msgSend = new JButton("���� �޼���"); // ���� �޼����� ��ư
			msgGive = new JButton("���� �޼���"); // ���� �޼����� ��ư
			msgTextBt = new JButton("������"); // �޼��� ������ ��ư
			msgText = new JTextArea(); // �޼��� �Է� â
			msgTextScroll = new JScrollPane(msgText); // �޼��� �Է� â ��ũ��
			
			msgText.setLineWrap(true);

			msgButton.add(msgTextBt); // ��ư ����Ʈ�� �޼��� ������ ��ư �߰�
			msgButton.add(msgSend); // ��ư ����Ʈ�� ���� �޼����� �߰�
			msgButton.add(msgGive); // ��ư ����Ʈ�� ���� �޼����� �߰�

			msgLabel.setBounds(10, 50, 200, 50);
			msgSend.setBounds(30, 100, 200, 40);
			msgGive.setBounds(260, 100, 200, 40);
			msgTextScroll.setBounds(10, 180, 270, 200);
			msgTextBt.setBounds(290, 300, 100, 80);

			for (JButton jb : msgButton) {
				jb.addActionListener(this); // ��ư ����Ʈ�� �׼� ������
			}

			add(msgLabel);
			add(msgSend);
			add(msgGive);
			add(msgTextScroll);
			add(msgTextBt);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(msgButton.get(0))) { // �޼��� ������ ��ư Ŭ�� ���� ��
				if(!(msgText.getText().isEmpty())) {
				MessageDB.saveMESSAGE("admin", userID, msgText.getText());
				JOptionPane.showMessageDialog(null, "���� �Ϸ�");
				// �޼��� �Է�â �ʱ�ȭ
				msgText.setText("");
				}else {
					JOptionPane.showMessageDialog(null, "�޼����� �Է��ϼ���");
				}
			} else if (e.getSource().equals(msgButton.get(1))) { // ���� �޼����� ��ư Ŭ�� ���� ��
				sendMessage = new SendMessage();
			} else if (e.getSource().equals(msgButton.get(2))) { // ���� �޼����� ��ư Ŭ�� ���� ��
				giveMessage = new GiveMessage();
			}

		}

	}

	class SendMessage extends JFrame implements ActionListener, MouseListener { // �����޼����� ������ Ŭ����

		JTable sendList;
		JScrollPane scroll;
		JButton delete;
		
		
		String[][] arr2;

		public SendMessage() {
			setTitle("���� �޼�����");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getFROM_MESSAGE(userID);
			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
				return;
			}

			String[] arr = new String[] { "�������", "����", "�ð�" };

			sendList = new JTable(new NotEditTable(arr2, arr));
			scroll = new JScrollPane(sendList);
			delete = new JButton("����");
			


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
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
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

			writerLabel = new JLabel("������� : ");
			timeLabel = new JLabel("�����ð� : ");
			contentLabel = new JLabel("����");

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
	implements ActionListener, MouseListener { // �����޼����� ������ Ŭ����

		JTable giveList;
		JScrollPane scroll;
		JButton delete;
		String[][] arr2;

		public GiveMessage() {
			setTitle("���� �޼�����");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getTO_MESSAGE(userID);
			String[] arr = new String[] { "�������", "����", "�ð�" };
			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
				return;
			}

			giveList = new JTable(new NotEditTable(arr2, arr));
			scroll = new JScrollPane(giveList);
			delete = new JButton("����");

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
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
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

			setTitle("�����޼���");
			setBounds(600, 100, 400, 500);
			setLayout(null);

			writerLabel = new JLabel("������� : ");
			timeLabel = new JLabel("�����ð� : ");
			contentLabel = new JLabel("����");

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

	class NoticePanel extends JPanel implements ActionListener { // ������_ �������� �г�

		Vector<String> notice; // �������� (�ӽ�)
		JButton noticeGo; // ����Ȯ�� ��ư (�ӽ�)
		JComboBox noticeBox;
		NoticeFrame noticeFrame;
		MessagePanel msgPanel;
		ArrayList<Notice> notices;

		public NoticePanel() {

			notices = NoticeDB.getNOTICE();
			notice = new Vector<String>(); // ����.add�� �޺��ڽ� �߰�
			for (Notice ntc : notices) {
				notice.add(ntc.title);
			}

			noticeGo = new JButton("����Ȯ��");
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

	class NoticeFrame extends JFrame { // ���� Ȯ�� ������
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

			setTitle("��������");

			setBounds(jumTalkOption_User.getX() + jumTalkOption_User.getWidth() + 50, jumTalkOption_User.getY(), 700,
					800);
			setLayout(null);

			noticeLabel = new JLabel(notice.title);
			contentLabel = new JTextArea(notice.content);
			writerLabel = new JLabel(notice.writer);
			modifLabel = new JLabel(notice.modi_time);
			makeTimeLabel = new JLabel(notice.maketime);

			title = new JLabel("���� : ");
			makeTime = new JLabel("�ۼ��ð� : ");
			modifTime = new JLabel("�����ð� : ");
			writer = new JLabel("�ۼ��� : ");

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
