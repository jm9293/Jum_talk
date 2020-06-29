package db_p;

import java.awt.CardLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class JTalk_Reservation {
	public static void main(String[] args) {
		
		// �Ķ���� --> 0:�Ϲ�����, 1:������, 2:������ --> �α��ν� db ���� �ش���̵��� �ĺ��� �޾ƿͼ� �α��� ���� ������ ����
		new AMainFrame(2);
	}
}

// ����������Ʈ '����'�� (�α��ν��� : �Ϲݻ����, ������, ������) cardLayout()
class AMainFrame extends JFrame { // ��ü������

	// DataBase
	String userID; // �α��δܿ��� ������ �Ѿ�ÿ��� �ϴ��ӽ�
	String sellerID; // �α��δܿ��� ������ �Ѿ�ÿ���
	ArrayList<ArrayList<String>> seller; // ��ü ������
	ArrayList<ArrayList<String>> loginSeller; // ���������� ������

	// MainFrame
	JButton btn_rsv, btn_chat, btn_coin, btn_set; // �� ��ư
	CardLayout card = new CardLayout();
	BottomOutPanel bop; // card.show()

	// BottomOutPanel
	PanelRsv rsv;
	PanelChat chat;
	PanelCoin coin;
	PanelSet set;

	// �Ϲݻ���� : PanelRsv
	InRsv0 inrsv0;
	InRsv1 inrsv1;
	InRsv2 inrsv2;

	// InRsv0() --> ���� �����г� 0:�Ϲݻ����
	JButton btn;
	ArrayList<ImageIcon> imgArr;
	ArrayList<ArrayList<String>> sellerList;
	ArrayList<JButton> btnArr;

	// �Ϲݻ���� : Review
	Map<String, String> reviewMap;

	// �Ϲݻ���� : ActReview 700
	ArrayList<String> nomuserID;
	ArrayList<String> nomComent;
	ArrayList<Integer> nomPoint;
	String ttNom;

	// �Ϲݻ���� : JumsulMenu
	JButton btnFace, btnSaju, btnLove, btnNewYear, btnCompany;

	// �Ϲݻ���� : Reservation
	String userSelectTime; // 897
	String userResDay; // ����ڰ� �� ���೯¥
	int rdayUser; // ����� �� ��¥ 892
	int cntRsv;

	// �Ϲݻ���� : RsvTimeFrame() �����޴�ǥ -> ����â(����ð� �� ����Ϸ�) line 768
	JComboBox dateListD; // �Ϲݻ���ڰ� �� ���� ��¥����Ʈ 840
	ArrayList<JToggleButton> btnTime; // �Ϲݻ���ڰ� �� ����ð� ��ư 850
	String kindMenu;
	int minuscoin = 0;

	// �Ϲݻ���� : Reservation Finish
	Map<String, Integer> rsvMap = new HashMap<String, Integer>(); // Map<�����޴���, ����ð�> line 900
	JFrame rsvFin; // ������ �����Ұ��� Ȯ��â
	JButton btnFinYes;
	JButton btnFinNo;

	// ������ : InRsv1
	JButton rsvTime, rsvMenu, showReview;
	InRsvProfile irp0;

	// ������ : ����ð�����
	JFrame f1;
	JComboBox dateListSeller;
	ArrayList<JToggleButton> btnTimeSeller; // �������� ���ð�
	ArrayList<String> selectToggle; // ������ : ����ð����� , ��۹�ư ���õ� ����ð����� �������� ����
	int rdaySeller;
	String resDaySeller;

	// ������: �����޴�ǥ����(�߰�,����)
	JFrame f2;
	JLabel lblAdd1;
	JLabel lblAdd2;
	JTextArea taAdd;
	JTextArea taAdd1;
	JButton b1;
	JLabel lblMod1;
	JLabel lblMod2;
	JTextArea taMod;
	JTextArea taMod1;
	JButton b2;
	
	// �Է¹޾Ƽ� �ٲ� �޴�ǥ 497
	String changeMenuName;
	int changeMenuNum;

	// ������: ���θ��亸�� 512
	JFrame f3;

	// Inrsv2() : ������
	JButton btnNormal;
	JButton btnJeomsul;
//	JButton btnAcceptList;

	// ������ : �Ϲ�,������ ȸ������ 554
	JFrame f4, f5;
	JButton btn_ModNomInfo;
	JButton btn_ModSelInfo;
	ArrayList<JButton> modNomInfoArr;
	ArrayList<JButton> modSelInfoArr;
	
	// ������������    631
	int NomModClk;
	// ������������(������)   820
	int SelModClk;
	
	// ������ : ���Խ��θ���Ʈ
//	JFrame f6;
	
	// User identity 0:�Ϲݻ���� 1:������ 2:������
	int kind;

	public AMainFrame(int kind) 
	{
		setBounds(100, 100, 500, 800);
		setLayout(null);
		
		this.kind = kind; // �α��� ��� üũ 0:�Ϲ�, 1:������, 2:������

		sellerID = UserDB.getSELLER().get(0).id;
		userID = UserDB.getNomUser().get(0).id;
		// sellerID = UserDB.getID("�α��δܿ��� �޾ƿ� ������ ���̵�");
		// userID = UserDB.getID("�α��δܿ��� �޾ƿ� �Ϲݻ���� ���̵�");

		bop = new BottomOutPanel(kind);

		if (kind == 0) { // �Ϲݻ����
			btn_rsv = new JButton("����");
			btn_rsv.setName("����");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		} else if (kind == 1) { // ������
			btn_rsv = new JButton("����");
			btn_rsv.setName("�������");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		}

		else { // ������
			btn_rsv = new JButton("ȸ������");
			btn_rsv.setName("ȸ������");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		}

		btn_chat = new JButton("ä��");
		btn_chat.setName("ä��");
		btn_chat.setBounds(130, 10, 100, 70);
		btn_chat.addActionListener(new Act());

		btn_coin = new JButton("����");
		btn_coin.setName("����");
		btn_coin.setBounds(250, 10, 100, 70);
		btn_coin.addActionListener(new Act());

		btn_set = new JButton("����");
		btn_set.setName("����");
		btn_set.setBounds(370, 10, 100, 70);
		btn_set.addActionListener(new Act());

		add(btn_rsv);
		add(btn_chat);
		add(btn_coin);
		add(btn_set);

		add(bop);

		
		
		setVisible(false);
	}

	class Act implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// �Ǽ��� ��ư
			if (e.getSource() == btn_rsv) {
				if (btn_rsv.getName() == "����")
					card.show(bop, "����");
				else if (btn_rsv.getName() == "�������")
					card.show(bop, "�������");
				else {
					card.show(bop, "ȸ������");
				}
			} else if (e.getSource() == btn_chat) {
				card.show(bop, "ä��");
			} else if (e.getSource() == btn_coin) {
				card.show(bop, "����");
			} else if (e.getSource() == btn_set) {
				card.show(bop, "����");
			}

		}
	}

	class BottomOutPanel extends JPanel { // �ϴ� �ܺ��г�( card layout���� �ٲ� �ٱ��г�)

		public BottomOutPanel(int kind) {
			setBounds(0, 100, 500, 670);
			setLayout(card);

			rsv = new PanelRsv(kind);
			chat = new PanelChat();
			coin = new PanelCoin();
			set = new PanelSet();

			// ���⼭ card ��������
			if (kind == 0)// �Ϲ�
				add("����", rsv);
			else if (kind == 1)
				add("�������", rsv);
			else
				add("ȸ������", rsv);

			add("ä��", chat);
			add("����", coin);
			add("����", set);

			setVisible(true);
		}
	}

	class PanelRsv extends JPanel { // '����' �г�

		public PanelRsv(int kind) {
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setName("����");

			if (kind == 0) { // �Ϲݰ��϶�
				inrsv0 = new InRsv0();
				inrsv0.setBounds(0, 0, 485, 680); // ��ũ���г� ��ũ�ѹ� ���̱������ width +15
				add(inrsv0);
			} else if (kind == 1) { // �������϶�
				inrsv1 = new InRsv1();
				inrsv1.setBounds(0, 0, 485, 680);
				add(inrsv1);
			} else { // �������϶�
				inrsv2 = new InRsv2();
				inrsv2.setBounds(0, 0, 485, 680);
				add(inrsv2);
			}

		}
	}

	class InRsv0 extends JScrollPane { // '����'�г� ���� �г�(0:�Ϲݰ�ȭ��)

		public InRsv0() {
			JPanel jp = new JPanel();

			btnArr = new ArrayList<JButton>();
			imgArr = new ArrayList<ImageIcon>(); // db���� �޾ƿ� ���� arr

			sellerList = new ArrayList<ArrayList<String>>(); // db���� �޾ƿ� ������ name arr

			int aa = 0; // ī��Ʈ
			for (sellUser ss : UserDB.getSELLER()) { // ������ ȸ������ �°� �˾Ƽ�
				sellerList.add(new ArrayList<String>());
				sellerList.get(aa).add(ss.id);
				sellerList.get(aa).add(ss.name);
				sellerList.get(aa).add(ss.phone);
				sellerList.get(aa).add(ss.profile_text);
				if (aa < UserDB.getSELLER().size())
					aa++;
			}

			Dimension size = new Dimension();
			size.setSize(480, (sellerList.size() * 170) + 50);
			jp.setPreferredSize(size);
			setViewportView(jp);

			setBounds(10, 120, 500, 670);
			jp.setLayout(null);

			int cnt = 0; // horizontal ī��Ʈ
			for (int i = 0; i < sellerList.size(); i++) {
				ImageIcon imageicon = new ImageIcon("img/img01.jpg");// ImageIcon �����Ҿ����� = new ImageIcon("�̹���.jpg");

				Image img = imageicon.getImage(); // ImageIcon�� Image�� ��ȯ.

				Image imgChange = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // (����,����, "");

				ImageIcon imgIcon = new ImageIcon(imgChange); // Image�� ImageIcon ���� >> ����
				imgArr.add(imgIcon);

				JLabel lbl = new JLabel(sellerList.get(i).get(1), imgIcon, JLabel.LEFT); /// �̸� ����
				lbl.setOpaque(true);
				lbl.setBackground(Color.WHITE);
				lbl.setBounds(0, 0 + cnt, 480, 150);

				cnt += 170;

				btn = new JButton("��������");
				btn.setBounds(360, 60, 100, 30);

				// �������� �� â����(����,�󼼼Ұ�,��ȭ��ȣ,����,[���亸��],[�����޴�ǥ,����,�ð�])
				btn.addActionListener(new RsvAction());
				btnArr.add(btn); // ��ư�� Arr --->> ���� Ŭ���Ǿ�����

				lbl.add(btnArr.get(i));
				jp.add(lbl);
			}
		}

	}

	class InRsv1 extends JPanel { // '����'�г� ���� �г�(1:������ȭ��)

		public InRsv1() {
			setLayout(new GridLayout(3, 0));

			rsvTime = new JButton("���� �ð� ����");
			rsvTime.setBackground(Color.white);
			rsvTime.addActionListener(new RsvAction());
			rsvMenu = new JButton("���� �޴�ǥ ����");
			rsvMenu.setBackground(Color.white);
			rsvMenu.addActionListener(new RsvAction());
			showReview = new JButton("���� ���� ����");
			showReview.setBackground(Color.white);
			showReview.addActionListener(new RsvAction());

			add(rsvTime);
			add(rsvMenu);
			add(showReview);

		}
	}

	class InRsv2 extends JPanel { // '����'�г� ���� �г�(2:������ȭ��)

		public InRsv2() {
			setLayout(new GridLayout(2, 0));

			btnNormal = new JButton("�Ϲ� ȸ������");
			btnNormal.setBackground(Color.white);
			btnNormal.addActionListener(new RsvAction());
			btnJeomsul = new JButton("������ ȸ������");
			btnJeomsul.setBackground(Color.white);
			btnJeomsul.addActionListener(new RsvAction());
//			btnAcceptList = new JButton("���Խ��θ���Ʈ(������)");
//			btnAcceptList.setBackground(Color.white);
//			btnAcceptList.addActionListener(new RsvAction());

			add(btnNormal);
			add(btnJeomsul);
//			add(btnAcceptList);
		}
	}

	class RsvAction implements ActionListener { // '����'�г��� �α��� ������ ��ư������

		@Override
		public void actionPerformed(ActionEvent e) {

			// �Ϲݰ�
			if (kind == 0) {
				for (int i = 0; i < btnArr.size(); i++) {
					if (e.getSource().equals(btnArr.get(i))) { // ������ �ӽö� 0
						// ���⼭ Ŭ���� �������� �������� �Ķ���ͷ� ���������

						String chk = i + "";
						irp0 = new InRsvProfile(chk);
					}
				}
			}
			// ������
			else if (kind == 1) {
				if (e.getSource().equals(rsvTime)) { //// ����ð�����
					f1 = new JFrame("����ð�����â");
					f1.setBounds(400, 100, 515, 520);
					f1.setLayout(null);
					JPanel p1 = new JPanel();
					p1.setBounds(0, 0, 500, 50);
					JPanel p2 = new JPanel();
					p2.setBounds(0, 50, 500, 350);
					p2.setLayout(new GridLayout(4, 6));
					JPanel p3 = new JPanel();
					p3.setBounds(0, 400, 500, 120);
					p3.setLayout(null);

					Calendar todayC = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String day = sdf.format(todayC.getTime()).substring(8, 10); // ���� ��¥ 01~31

					Vector<Integer> dayList = new Vector<Integer>();
					for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
						dayList.add(i);
					}

					dateListSeller = new JComboBox(dayList);
					dateListSeller.setBounds(20, 20, 80, 20);

					JLabel lbl_date = new JLabel(" ��");
					lbl_date.setBounds(100, 20, 20, 20);

					rdaySeller = (int) dateListSeller.getSelectedItem(); // �������� ���� ���೯¥
					resDaySeller = rdaySeller + ""; // string ĳ����

					btnTimeSeller = new ArrayList<JToggleButton>();
					for (int i = 0; i < 24; i++) {
						JToggleButton bb = new JToggleButton(i + "");
						bb.setBackground(Color.white);
						btnTimeSeller.add(bb);
						p2.add(btnTimeSeller.get(i));
					}

					JButton btnSave = new JButton("����");
					btnSave.setBounds(200, 10, 100, 50);
					btnSave.addActionListener(new ActSaveRes());

					p1.add(dateListSeller);
					p1.add(lbl_date);
					p3.add(btnSave);
					f1.add(p1);
					f1.add(p2);
					f1.add(p3);
					f1.setVisible(true);
				} else if (e.getSource().equals(rsvMenu)) { //// ����޴����� --> �޴���ȣ�������� �޴��� ����
					f2 = new JFrame("����޴�ǥ����â");
					f2.setBounds(400, 100, 500, 250);
					f2.setLayout(null);

					//// ���⼭ db�� ���θ��� �޴�ǥ �ø��ų� ������ �ִ� �޴�ǥ �����Ҽ��ְ� db����
					lblAdd1 = new JLabel("�����޴��� : ");
					lblAdd1.setBounds(10, 30, 110, 30);
					lblAdd2 = new JLabel("�ٲ� �޴� ��ȣ : ");
					lblAdd2.setBounds(10, 70, 110, 30);
					taAdd = new JTextArea();
					taAdd.setBounds(100, 30, 350, 30);
					taAdd.setBackground(Color.white);
					taAdd1 = new JTextArea();
					taAdd1.setBounds(100, 70, 350, 30);
					taAdd1.setBackground(Color.white);

					b1 = new JButton("����޴�ǥ ����"); // ����޴�ǥ ����
					b1.setBounds(170, 150, 150, 30);
					b1.addActionListener(new ActMenuModify());

					f2.add(lblAdd1);
					f2.add(lblAdd2);
					f2.add(taAdd);
					f2.add(taAdd1);
					f2.add(b1);

					f2.setVisible(true);
				} else { //// ������ : ���θ��亸��
					f3 = new JFrame("���θ��亸��");
					f3.setBounds(400, 100, 600, 700);
					f3.setLayout(null);

					ArrayList<String> myReviewId = new ArrayList<String>();
					ArrayList<Integer> myReviewPoint = new ArrayList<Integer>();
					ArrayList<String> myReviewComment = new ArrayList<String>();
					for (Review rv : ReviewDB.getREVIEW(sellerID)) {
						myReviewId.add(rv.id);
						myReviewPoint.add(rv.point);
						myReviewComment.add(rv.coment);
					}

					String tt = "";
					int i = 0;
					for (String com : myReviewComment) {
						tt += "[" + UserDB.getNomUser().get(i).id + "] : " + com + "\n����: [" + myReviewPoint.get(i)
								+ "] " + "\n\n";
						i++;
					}

					JTextArea t1 = new JTextArea();
					t1.setText(tt);
					t1.setLineWrap(true);
					t1.setFont(new Font("���", Font.BOLD, 15));
					t1.setEnabled(false);
					JScrollPane scp = new JScrollPane(t1);
					scp.setForeground(Color.BLACK);
					scp.setBounds(0, 0, 585, 665);

					f3.add(scp);
					f3.setVisible(true);
				}
			} else { //// ������
				if (e.getSource().equals(btnNormal)) {					
					f4 = new JFrame("�Ϲ� ȸ������");
					f4.setBounds(400, 100, 500, 800);
					f4.setLayout(null);

					int cnt = 0;
					for (normalUser nm : UserDB.getNomUser()) { 
						JLabel lblMg = new JLabel();
						lblMg.setBounds(5, 5 + cnt, 490, 40);
						lblMg.setOpaque(true);
						lblMg.setBackground(Color.white);
						lblMg.setText("���̵� : " + nm.id + "     �̸� : " + nm.name + "     ��ȭ��ȣ : " + nm.phone);
						
						modNomInfoArr = new ArrayList<JButton>();
						btn_ModNomInfo = new JButton("����");
						btn_ModNomInfo.setBounds(410, 5, 60, 30);
						btn_ModNomInfo.addActionListener(new ReviseInfor());
						modNomInfoArr.add(btn_ModNomInfo);
						cnt += 50;
						lblMg.add(btn_ModNomInfo);
						f4.add(lblMg);
					}

					f4.setVisible(true);
					
				} else if (e.getSource().equals(btnJeomsul)) {
					f5 = new JFrame("������ ȸ������");
					f5.setBounds(400, 100, 500, 800);
					f5.setLayout(null);

					int cnt = 0;
					for (sellUser sell : UserDB.getSELLER()) {
						JLabel lblMg = new JLabel();
						lblMg.setBounds(5, 5 + cnt, 490, 40);
						lblMg.setOpaque(true);
						lblMg.setBackground(Color.white);
						lblMg.setText("���̵�: " + sell.id + "     �̸�: " + sell.name + "     ��ȭ��ȣ: " + sell.phone);

						modSelInfoArr = new ArrayList<JButton>();
						btn_ModSelInfo = new JButton("����");
						btn_ModSelInfo.setBounds(410, 5, 60, 30);
						btn_ModSelInfo.addActionListener(new SellerReviseInfor());
						modSelInfoArr.add(btn_ModSelInfo);
						
						cnt += 50;
						lblMg.add(btn_ModSelInfo);
						f5.add(lblMg);
					}

					f5.setVisible(true);
				} 
//				else if (e.getSource().equals(btnAcceptList)) {
//					f6 = new JFrame("���Խ��θ���Ʈ(������)");
//					f6.setBounds(400, 100, 500, 800);
//					f6.setLayout(null);
//					/////// DB ���� ���Խ��θ���Ʈ ���̺� null üũ�ؼ� ����ָ�ɵ�
//
//					f6.setVisible(true);
//				} 
			}

		}
	}

	class ReviseInfor implements ActionListener { // �������� ���� ������(����)

		JFrame jf;
		
		UserDB userDB;
		String userID;
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

		JTextField helloText;
		JPasswordField pwField;
		JTextField phoneNumText;
		JTextField emailText;
		JTextField addressText;
		JTextField cardNumText;
		JTextField pwHintText;
		JTextField hintAnswerText;
		ArrayList<JTextField> fieldArr = new ArrayList<JTextField>();

		@Override
		public void actionPerformed(ActionEvent e) {
			NomModClk = 0;
			for (int i = 0; i < modNomInfoArr.size(); i++) {
				if (e.getSource().equals(modNomInfoArr.get(i))) {
					NomModClk = i; // Ŭ���� ��ư �ε�������
				}
			}

			jf = new JFrame();
			jf.setLayout(null);
			jf.setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ
			jf.setBounds(700, 100, 500, 700);

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

			helloText = new JTextField();
			pwField = new JPasswordField();
			phoneNumText = new JTextField();
			emailText = new JTextField();
			addressText = new JTextField();
			cardNumText = new JTextField();
			pwHintText = new JTextField();
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

			// �����Ϸ� ��ư
			reviseGo = new JButton("���� �Ϸ�");
			// �������� ���� ������ ������ ��ư
			back = new JButton("������");

			reviseGo.setBounds(370, 10, 100, 40);
			back.setBounds(10, 10, 100, 40);

			helloText.setBounds(130, 120, 300, 50);
			pwField.setBounds(130, 180, 300, 50);
			phoneNumText.setBounds(130, 240, 300, 50);
			emailText.setBounds(130, 300, 300, 50);
			addressText.setBounds(130, 360, 300, 50);
			cardNumText.setBounds(130, 420, 300, 50);
			pwHintText.setBounds(130, 480, 300, 50);
			hintAnswerText.setBounds(130, 540, 300, 50);

			for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
				information.get(i).setBounds(10, i * 60, 100, 50);
			}

			reviseGo.addActionListener(new ActModifyNomInfo()); // �����Ϸ� ��ư /////////////////��ġ����
			back.addActionListener(new ActModifyNomInfo()); // ������ ��ư

			jf.add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
			jf.add(back); // �������� ���� �����ӿ� ������ ��ư �߰�

			jf.add(helloText);
			jf.add(pwField);
			jf.add(phoneNumText);
			jf.add(emailText);
			jf.add(addressText);
			jf.add(cardNumText);
			jf.add(pwHintText);
			jf.add(hintAnswerText);

			for (JLabel jl : information) {
				jf.add(jl); // �������� ���� �����ӿ� �������� �� �߰�
			}

			jf.setVisible(true);

		}
		
		class ActModifyNomInfo implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
					// �������� ���� ������ �������
					jf.setVisible(false);
				} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
					// �����Ϸ� �˾� ����� ������ �������
					String temp = UserDB.getNomUser().get(NomModClk).id;	// ��������ư(ȸ������)�� ���̵�
					
					if (!(helloText.equals(""))) {
						userDB.setPROFILE_TEXT(temp, helloText.getText());
					}
					if (!(pwField.equals(""))) {
						userDB.setPW(temp, pwField.getText());
					}
					if (!(phoneNumText.equals(""))) {
						userDB.setPHONE(temp, phoneNumText.getText());
					}
					if (!(emailText.equals(""))) {
						userDB.setEMAIL(temp, emailText.getText());
					}
					if (!(addressText.equals(""))) {
						userDB.setADDRESS(temp, addressText.getText());
					}
					if (!(cardNumText.equals(""))) {
						userDB.setCARDNUMBER(temp, cardNumText.getText());
					}
					if (!(pwHintText.equals(""))) {
						userDB.setPWHINT(temp, pwHintText.getText());
					}
					if (!(hintAnswerText.equals(""))) {
						userDB.setPWRES(temp, hintAnswerText.getText());
					}
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
					jf.setVisible(false);

				}

			}
		}
		
		
		
	}

	class SellerReviseInfor implements ActionListener { // ������ ���� ���� ������ (�Һ��� ���� ������ ���)

		JFrame jf;
		
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
		JLabel workplaceName; // ������ ����� �̸�
		JLabel workplaceAddress; // ������ ����� �ּ�
		ArrayList<JLabel> information; // �������� ����Ʈ
		JButton reviseGo; // �������� ���� �Ϸ� ��ư
		JButton back; // �������� ���� ������ ��ư

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

		UserDB userDB;

		String userID = "piano";

		
		@Override
		public void actionPerformed(ActionEvent e) {
			SelModClk = 0;
			for (int i = 0; i < modSelInfoArr.size(); i++) {
				if (e.getSource().equals(modSelInfoArr.get(i))) {
					SelModClk = i; // Ŭ���� ��ư �ε�������
				}
			}
			
			jf = new JFrame();
				jf.setLayout(null);				
				jf.setTitle("�������� ����"); // �������� ���� ������ Ÿ��Ʋ
				jf.setBounds(700, 100, 500, 800);

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

				helloText.setBounds(130, 120, 300, 50);
				pwField.setBounds(130, 180, 300, 50);
				phoneNumText.setBounds(130, 240, 300, 50);
				emailText.setBounds(130, 300, 300, 50);
				addressText.setBounds(130, 360, 300, 50);
				cardNumText.setBounds(130, 420, 300, 50);
				pwHintText.setBounds(130, 480, 300, 50);
				hintAnswerText.setBounds(130, 540, 300, 50);
				workNameText.setBounds(130, 600, 300, 50);
				workAddressText.setBounds(130, 660, 300, 50);

				for (int i = 1; i < information.size(); i++) { // �������� �� ��ġ ���(����Ʈ)
					information.get(i).setBounds(10, i * 60, 100, 50);
				}

				reviseGo.addActionListener(new ActModifySelInfo()); // �����Ϸ� ��ư
				back.addActionListener(new ActModifySelInfo()); // ������ ��ư

				jf.add(reviseGo); // �������� ���� �����ӿ� �����Ϸ� ��ư �߰�
				jf.add(back); // �������� ���� �����ӿ� ������ ��ư �߰�

				jf.add(helloText);
				jf.add(pwField);
				jf.add(phoneNumText);
				jf.add(emailText);
				jf.add(addressText);
				jf.add(cardNumText);
				jf.add(pwHintText);
				jf.add(hintAnswerText);
				jf.add(workNameText);
				jf.add(workAddressText);

				for (JLabel jl : information) {
					jf.add(jl); // �������� ���� �����ӿ� �������� �� �߰�
				}

				jf.setVisible(true);
			

		}
		
		class ActModifySelInfo implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(back)) { // ������������ �����ӿ��� ������ ��ư Ŭ��
					// �������� ���� ������ �������
					jf.setVisible(false);
				} else if (e.getSource().equals(reviseGo)) { // �������� ���� �����ӿ��� �Ϸ� ��ư Ŭ��
					// �����Ϸ� �˾� ����� ������ �������

					String temp = UserDB.getSELLER().get(SelModClk).id;	// ��������ư(ȸ������)�� ���̵�
					
					if (!(helloText.equals(""))) {
						userDB.setPROFILE_TEXT(temp, helloText.getText());
					}
					if (!(pwField.equals(""))) {
						userDB.setPW(temp, pwField.getText());
					}
					if (!(phoneNumText.equals(""))) {
						userDB.setPHONE(temp, phoneNumText.getText());
					}
					if (!(emailText.equals(""))) {
						userDB.setEMAIL(temp, emailText.getText());
					}
					if (!(addressText.equals(""))) {
						userDB.setADDRESS(temp, addressText.getText());
					}
					if (!(cardNumText.equals(""))) {
						userDB.setCARDNUMBER(temp, cardNumText.getText());
					}
					if (!(pwHintText.equals(""))) {
						userDB.setPWHINT(temp, pwHintText.getText());
					}
					if (!(hintAnswerText.equals(""))) {
						userDB.setPWRES(temp, hintAnswerText.getText());
					}
					if (!(workNameText.equals(""))) {
						userDB.setBUSINESSNAME(temp, workNameText.getText());
					}
					if (!(workAddressText.equals(""))) {
						userDB.setBUSINESSADDRESS(temp, workAddressText.getText());
					}
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
					jf.setVisible(false);

				}

			}
		}
		
		
	}

	class ActSaveRes implements ActionListener {// ������ : ������ �����ư ó�� 473

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * rdaySeller (int) resDaySeller (string) selectToggle (arrayList)
			 */
			// ��۹�ư���� ���õ� ����ð�
			selectToggle = new ArrayList<String>();
			for (int i = 0; i < btnTimeSeller.size(); i++) {
				if (btnTimeSeller.get(i).isSelected()) {
					selectToggle.add(i + "");
				}
			}

			// ����������ó���Ϸ��� �ϴ�
			// ����
			// ��
			// ��Ȯ��
			// �α���
			// userID��
			// �˾ƾ���
//			ScheduleDB.makeSchedule(userID, date);		

			JOptionPane jp = new JOptionPane(); //// ���⼭ ���� �������� �÷��ָ��
			jp.showMessageDialog(f1, "�������� ����Ǿ����ϴ�");
			f1.setVisible(false);
		}
	}

	class ActMenuModify implements ActionListener { // line 506 ������ �޴�������ư

		@Override
		public void actionPerformed(ActionEvent e) {

			// �Է¹޾Ƽ� �ٲ� �޴�ǥ
			changeMenuName = taAdd.getText(); // �ٲ� �����޴���
			changeMenuNum = Integer.parseInt(taAdd1.getText()); // �ٲ� �޴��� �ε���\

			if (MenuDB.getMENU(sellerID) != null) { // �̰͵� ��ó���Ȱǵ� �α��δܿ��� �ĺ��� ���̵� ������;� �ذᰡ����
				MenuDB.setMENU(sellerID, changeMenuNum, changeMenuName); // �޴���ȣ�� 1���ͽ���..2345
			} else {
				JOptionPane jp = new JOptionPane();
				jp.showMessageDialog(null, "������ �޴��� �����ϴ�");
			}

			JOptionPane jp = new JOptionPane(); //// ���⼭ ���� �������� �÷��ָ��
			jp.showMessageDialog(f1, "�����޴��� ����Ǿ����ϴ�");
			f2.setVisible(false);
		}
	}

	class InRsvProfile extends JFrame { // '����'�г��� ���� �г��� �������� â -- �������� ������� (0:�Ϲݰ�ȭ��)

		public InRsvProfile(String whoSeller) { // �������� ������ ������������ �Ķ���ͷ� accept
			int whoSell = Integer.parseInt(whoSeller);
			sellerID = UserDB.getSELLER().get(whoSell).id; // ���õ� ������ ���̵�� �ʱ�ȭ

			setLayout(null);
			setBounds(400, 100, 500, 800);

			JPanel jp = new JPanel();
			JPanel jp2 = new JPanel();
			jp.setLayout(new GridLayout(5, 0));
			jp.setBounds(0, 0, 485, 330);
			jp2.setLayout(new GridLayout(6, 1));
			jp2.setBounds(0, 330, 485, 430);

			JLabel lbl1 = new JLabel("[����]");
			JLabel lbl2 = new JLabel();
			lbl2.setText("[�󼼼Ұ�] : "+ UserDB.getPROFILE_TEXT(sellerID));
			JLabel lbl3 = new JLabel("��ȭ��ȣ");
			lbl3.setText("[��ȭ��ȣ] : "+ UserDB.getPHONE(sellerID));
			JLabel lbl4 = new JLabel();
			lbl4.setText("[����] : "+ ReviewDB.getAVGPOINT(sellerID) + "");

			// db���� �޾ƿ�
			// String id, String coment, String maketime, int point
			// ���̵� ���䳻�� ����ð�(�ʿ�X) ����
			nomuserID = new ArrayList<String>();
			nomComent = new ArrayList<String>();
			nomPoint = new ArrayList<Integer>();
			for (Review rv : ReviewDB.getREVIEW(sellerID)) {
				System.out.println(ReviewDB.getREVIEW(sellerID));
				nomComent.add(rv.coment);
				nomPoint.add(rv.point);
			}

			JButton btnReview = new JButton("���� ����");
			btnReview.setBackground(Color.white);
			btnReview.addActionListener(new ActReview());

			JLabel lblMenu = new JLabel("-----------------------------------------------------"
					+ "�����޴�ǥ\n--------------------------------------------------");

			String[] getMenuArr = new String[5];

			if (MenuDB.getMENU(sellerID) == null) {
				MenuDB.makeMENU(sellerID);
				getMenuArr = MenuDB.getMENU(sellerID);
			} else {
				getMenuArr = MenuDB.getMENU(sellerID);
			}

			btnFace = new JButton(getMenuArr[0]);
			btnFace.addActionListener(new ActJumsulMenu());
			btnFace.setBackground(Color.white);
			btnSaju = new JButton(getMenuArr[1]);
			btnSaju.addActionListener(new ActJumsulMenu());
			btnSaju.setBackground(Color.white);
			btnLove = new JButton(getMenuArr[2]);
			btnLove.addActionListener(new ActJumsulMenu());
			btnLove.setBackground(Color.white);
			btnNewYear = new JButton(getMenuArr[4]);
			btnNewYear.addActionListener(new ActJumsulMenu());
			btnNewYear.setBackground(Color.white);
			btnCompany = new JButton(getMenuArr[3]);
			btnCompany.addActionListener(new ActJumsulMenu());
			btnCompany.setBackground(Color.white);

			jp.add(lbl1);
			jp.add(lbl2);
			jp.add(lbl3);
			jp.add(lbl4);
			jp.add(btnReview);

			jp2.add(lblMenu);
			jp2.add(btnFace);
			jp2.add(btnSaju);
			jp2.add(btnLove);
			jp2.add(btnNewYear);
			jp2.add(btnCompany);

			add(jp);
			add(jp2);
			setVisible(true);
		}
	}

	class ActReview implements ActionListener { // ������ �������� ���亸�� ��ưŬ����!!

		@Override
		public void actionPerformed(ActionEvent e) {
			ttNom = "";
			int j = 0;
			for (String comm : nomComent) {
				ttNom += "���̵� : [" + ReviewDB.getREVIEW(sellerID).get(j).id + "]\n" + comm + "\n���� : ["
						+ nomPoint.get(j) + "] " + "\n\n";
				j++;
			}

			JFrame f1 = new JFrame("���丮��Ʈ");
			f1.setLayout(null);
			f1.setBounds(800, 100, 600, 700);

			JTextArea t1 = new JTextArea(ttNom);
			t1.setLineWrap(true);
			t1.setFont(new Font("���", Font.BOLD, 15));
			t1.setEnabled(false);
			JScrollPane scp = new JScrollPane(t1);
			scp.setBounds(0, 0, 585, 665);

			f1.add(scp);
			f1.setVisible(true);
		}
	}

	class ActJumsulMenu implements ActionListener { // �������... �� ����������

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnFace)) { // �����϶�
				cntRsv = 0;
				new RsvTimeFrame(0);
			} else if (e.getSource().equals(btnSaju)) { // ����
				cntRsv = 0;
				new RsvTimeFrame(1);
			} else if (e.getSource().equals(btnLove)) { // ����
				cntRsv = 0;
				new RsvTimeFrame(2);
			} else if (e.getSource().equals(btnNewYear)) { // �ų�
				cntRsv = 0;
				new RsvTimeFrame(3);
			} else if (e.getSource().equals(btnCompany)) { // ����
				cntRsv = 0;
				new RsvTimeFrame(4);
			}

		}
	}

	class RsvTimeFrame extends JFrame { // �����޴�ǥ -> ����â(����ð� �� ����Ϸ�)

		public RsvTimeFrame(int identity) {
			setBounds(400, 100, 515, 520);
			setLayout(null);

			if (identity == 0) { // ����
				kindMenu = "����";
				minuscoin = 500;
				reservation(kindMenu, minuscoin);
			} else if (identity == 1) { // ����
				kindMenu = "����Ǯ��";
				minuscoin = 500;
				reservation(kindMenu, minuscoin);
			} else if (identity == 2) { // ����
				kindMenu = "���ֿ";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			} else if (identity == 3) { // �ų�
				kindMenu = "�ų�";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			} else if (identity == 4) { // ����
				kindMenu = "����";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			}

			setVisible(true);
		}

		void reservation(String kindMenu, int coin) { // �������� �Ķ���Ͱ� �޾ƿ�

			// db���� �������� ���� �ź��� �ð��� int�� �޾ƿͼ� ��Ƶΰ� �� �ð��� ��ư ��Ȱ��ȭ ó��
			// int ?? ;
			JPanel p1 = new JPanel();
			p1.setBounds(0, 0, 500, 50);
			JPanel p2 = new JPanel();
			p2.setBounds(0, 50, 500, 350);
			p2.setLayout(new GridLayout(4, 6));
			JPanel p3 = new JPanel();
			p3.setBounds(0, 400, 500, 120);
			p3.setLayout(null);

			Calendar todayC = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String day = sdf.format(todayC.getTime()).substring(8, 10); // ���� ��¥ 01~31

			Vector<Integer> dayList = new Vector<Integer>(); // ���� +2 �ϱ���
			for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
				dayList.add(i);
			}

			dateListD = new JComboBox(dayList);
			dateListD.setBounds(20, 20, 80, 20);

			JLabel lbl_date = new JLabel(" ��");
			lbl_date.setBounds(100, 20, 20, 20);

			btnTime = new ArrayList<JToggleButton>();
			ButtonGroup bg = new ButtonGroup();
			for (int i = 0; i < 24; i++) {
				JToggleButton bb = new JToggleButton(i + "");
				bb.setBackground(Color.white);
				bg.add(bb);
				btnTime.add(bb);
				p2.add(btnTime.get(i));
			}

			// ��üũ�ؼ� ������ �Ҹ��ϳ����� Ż���Ҷ� ���޽� Ʈ��ιٲ��ذ͸� �½����� ��ݸ���Ŵϱ� �޽��ϱ� �̹̰���°� ������ 24����������
			JButton btnSave = new JButton("�����ϱ�");
			btnSave.setBounds(200, 10, 100, 50);
			btnSave.addActionListener(new ActRsvFinish());

			p1.add(dateListD);
			p1.add(lbl_date);
			p3.add(btnSave);
			add(p1);
			add(p2);
			add(p3);
			setVisible(true);

		}
	}

	class ActRsvFinish implements ActionListener { // ���� �����Ұ��� ������ Ȯ�ι�ư

		@Override
		public void actionPerformed(ActionEvent e) {

			rsvFin = new JFrame();
			rsvFin.setLayout(null);
			rsvFin.setBounds(700, 350, 410, 200);

			rdayUser = (int) dateListD.getSelectedItem(); // ����ڰ� �������¥
			userResDay = rdayUser + ""; // string
			System.out.println(userResDay);
			// ��۹�ư���� ���õ� ������ �ð�
			for (int i = 0; i < btnTime.size(); i++) {
				if (btnTime.get(i).isSelected()) {
					userSelectTime = (i + "");
				}
			}
			System.out.println(userSelectTime);
			rsvMap.put(userResDay, Integer.parseInt(userSelectTime)); // ���� ��¥, ����� �ð� �ʿ� ����

			for (Entry<String, Integer> ee : rsvMap.entrySet()) {
				JLabel lbl = new JLabel();
				lbl.setBounds(20, 5, 410, 20);
				lbl.setOpaque(true);
				lbl.setText("�����޴� : [" + kindMenu + "]" + "     ���೯¥ : [" + ee.getKey() + "��]" + "     ����ð� : ["
						+ ee.getValue() + "��]");
				JLabel lbl2 = new JLabel();
				lbl2.setBounds(130, 30, 410, 20);
				lbl2.setOpaque(true);
				lbl2.setText("�����Ͻðڽ��ϱ�?");
				JLabel lbl3 = new JLabel();
				lbl3.setBounds(120, 55, 410, 20);
				lbl3.setOpaque(true);
				lbl3.setText(minuscoin + " ������ �����˴ϴ�");

				rsvFin.add(lbl);
				rsvFin.add(lbl2);
				rsvFin.add(lbl3);
			}
			btnFinYes = new JButton("��");
			btnFinYes.setBounds(20, 100, 150, 50);
			btnFinYes.addActionListener(new ActFinishChk());
			btnFinNo = new JButton("�ƴϿ�");
			btnFinNo.setBounds(200, 100, 150, 50);
			btnFinNo.addActionListener(new ActFinishChk());

			rsvFin.add(btnFinYes);
			rsvFin.add(btnFinNo);
			rsvFin.setVisible(true);
		}
	}

	class ActFinishChk implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnFinYes)) {
				
//				userResDay	// ����ڰ� �������¥ string
//				userSelectTime	// ���ð� string
//				Date mon = new Date();
//				System.out.println(mon.getMonth()+1);
				String str = "2020-06-"+userResDay+" "+userSelectTime+":00";
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
								
				try {
					ChatListDB.saveCHATLIST(sellerID, userID, sdf.parse(str), kindMenu);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
								
				JOptionPane jp = new JOptionPane(); //// ���⼭ ���� �������� �÷��ָ��
				jp.showMessageDialog(rsvFin, "������ �Ϸ�Ǿ����ϴ�");
				rsvFin.setVisible(false);
			} else if (e.getSource().equals(btnFinNo)) {
				rsvFin.setVisible(false);
			}

		}
	}

	class PanelChat extends JPanel { // 'ä��' �г�

		public PanelChat() {
			setName("ä��");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.green);
			JButton aa = new JButton("����2");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

	class PanelCoin extends JPanel { // '����' �г�

		public PanelCoin() {
			setName("����");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.blue);
			JButton aa = new JButton("����3");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

	class PanelSet extends JPanel { // ���� �г�

		public PanelSet() {
			setName("����");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.cyan);
			JButton aa = new JButton("����4");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

}