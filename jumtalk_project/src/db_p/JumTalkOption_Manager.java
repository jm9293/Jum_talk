package db_p;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import db_p.OptionPanel.GiveMessageClick;

// ������ ���� �г�
class JumTalkOptionManager extends JPanel {

	NoticePanelManager noticePanel;
	MessagePanelManager messagePanel;
	String userID;

	public JumTalkOptionManager(String userID) {
		this.userID = userID;
		System.out.println("�������?");
		noticePanel = new NoticePanelManager();
		messagePanel = new MessagePanelManager();
		setBounds(0, 200, 515, 600);
		setBackground(Color.white);
		setLayout(new GridLayout(3, 1));
		add(new JumOption());
		add(noticePanel);
		add(messagePanel);

		setVisible(true);

	}

	class JumOption extends JPanel implements ActionListener {

		JButton opt1_Manager;
//			      JButton opt2_Manager;
//			      JButton opt3_Manager;
//			      ReviseManager reviseManager;
//			      CoinRevise coinRevise;

		public JumOption() {

			setBounds(0, 100, 515, 100);
			setBackground(Color.red);
			setLayout(new GridLayout());

			opt1_Manager = new JButton("�α׾ƿ�");
//			         opt2_Manager = new JButton("������ ���� ����");
//			         opt3_Manager = new JButton("���� ����");

			add(opt1_Manager);
//			         add(opt2_Manager);
//			         add(opt3_Manager);

			opt1_Manager.addActionListener(this);
//			         opt2_Manager.addActionListener(this);
//			         opt3_Manager.addActionListener(this);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(opt1_Manager)) {
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ϸ�");
				System.exit(0);
			}
//			         else if (e.getSource().equals(opt2_Manager)) { // �������� ���� Ŭ�� ���� ��,
//			            rm = new ReviseManager();
//			         } else if (e.getSource().equals(opt3_Manager)) {
//			            coinRevise = new CoinRevise();
//			         }

		}

	}

//	class CoinRevise extends JFrame implements ActionListener {
//
//		JLabel idLabel;
//		JLabel coinLabel;
//		JLabel coinNumLabel;
//		JLabel coinReviseLabel;
//
//		JButton idButton;
//		JButton complete;
//
//		JTextField idText;
//		JTextField coinText;
//
//		public CoinRevise() {
//			setTitle("���� ����");
//			setBounds(700, 100, 400, 350);
//			setLayout(null);
//
//			idLabel = new JLabel("ID : ");
//			coinLabel = new JLabel("���� ���� : ");
//			coinNumLabel = new JLabel();
//			idText = new JTextField();
//			idButton = new JButton("���� Ȯ��");
//			coinReviseLabel = new JLabel("���� : ");
//			coinText = new JTextField();
//			complete = new JButton("�����Ϸ�");
//
//			idLabel.setBounds(10, 10, 100, 50);
//			coinLabel.setBounds(10, 80, 100, 50);
//			coinNumLabel.setBounds(130, 80, 100, 50);
//			idText.setBounds(70, 10, 180, 50);
//			idButton.setBounds(270, 10, 100, 50);
//			coinReviseLabel.setBounds(10, 150, 100, 50);
//			coinText.setBounds(70, 150, 180, 50);
//			complete.setBounds(270, 150, 100, 50);
//
//			idButton.addActionListener(this);
//			complete.addActionListener(this);
//
//			add(idLabel);
//			add(coinLabel);
//			add(coinNumLabel);
//			add(idText);
//			add(idButton);
//			add(coinReviseLabel);
//			add(coinText);
//			add(complete);
//
//			setVisible(true);
//
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			if (e.getSource().equals(idButton)) {
//				if (idText.getText().equals(UserDB.getID(idText.getText()))) {
//					coinNumLabel.setText(UserDB.getCOIN(idText.getText()) + "��");
//				} else {
//					JOptionPane.showMessageDialog(null, "���� ���̵� �Դϴ�.");
//				}
//			} else if (e.getSource().equals(complete)) {
//				UserDB.setCOIN(idText.getText(), Integer.parseInt(coinText.getText()));
//				JOptionPane.showMessageDialog(null, "�����Ϸ�");
//				setVisible(false);
//				
//			}
//
//		}
//
//	}

	NoticeRevise ntNoticeRevise;

	// �������� �г�
	class NoticePanelManager extends JPanel implements ActionListener {

		JLabel noticeLabel;
		JButton noticeWrite;
		JButton noticeRevise;

		public NoticePanelManager() {

			setLayout(null);

			noticeLabel = new JLabel("�������� ����");
			noticeWrite = new JButton("�������� �ۼ�");
			noticeRevise = new JButton("�������� ����");

			noticeWrite.addActionListener(this);
			noticeRevise.addActionListener(this);

			noticeLabel.setBounds(0, 0, 100, 30);
			noticeWrite.setBounds(0, 50, 243, 200);
			noticeRevise.setBounds(253, 50, 243, 200);

			add(noticeLabel);
			add(noticeWrite);
			add(noticeRevise);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(noticeWrite)) {// ���� ���� �ۼ�
				new NoticeWrite();
			} else if (e.getSource().equals(noticeRevise)) {
				ntNoticeRevise = new NoticeRevise();
			}

		}
	}

	// �޼��� �г�
	class MessagePanelManager extends JPanel implements ActionListener {

		GiveMessageManager give;
		SendMessageManager send;
		JTextArea messageText;
		JTextArea toMessage;
		JScrollPane scroll;

		JButton sendMessage;
		JButton giveMessage;
		JButton complete;

		JLabel messageLabel;
		JLabel toLabel;
		JLabel content;

		public MessagePanelManager() {

			setLayout(null);

			messageText = new JTextArea();
			scroll = new JScrollPane(messageText);
			toMessage = new JTextArea();
			sendMessage = new JButton("���� �޼�����");
			giveMessage = new JButton("���� �޼�����");
			complete = new JButton("������");
			messageLabel = new JLabel("�޼��� ����");
			toLabel = new JLabel("�޴� ���");
			content = new JLabel("����");

			sendMessage.addActionListener(this);
			giveMessage.addActionListener(this);
			complete.addActionListener(this);

			messageLabel.setBounds(0, 0, 100, 30);
			toLabel.setBounds(10, 30, 100, 30);
			content.setBounds(40, 70, 100, 30);
			scroll.setBounds(80, 70, 230, 160);
			toMessage.setBounds(80, 30, 230, 30);
			sendMessage.setBounds(330, 30, 150, 50);
			giveMessage.setBounds(330, 80, 150, 50);
			complete.setBounds(330, 150, 100, 80);

			messageText.setLineWrap(true);

			add(scroll);
			add(toMessage);
			add(sendMessage);
			add(giveMessage);
			add(complete);
			add(messageLabel);
			add(toLabel);
			add(content);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(sendMessage)) {
				new SendMessageManager();
			} else if (e.getSource().equals(giveMessage)) {
				new GiveMessageManager();
			} else if (e.getSource().equals(complete)) {
				if (messageText.getText().isEmpty() || toMessage.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "�� ĭ�� �Է��� �ּ���");
				} else if (UserDB.getID(toMessage.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "���� ���̵� �Դϴ�");
				} else {
					MessageDB.saveMESSAGE(toMessage.getText(), "admin", messageText.getText());
					JOptionPane.showMessageDialog(null, "���ۿϷ�");
					messageText.setText("");
					toMessage.setText("");
				}
			}
		}
	}

	// ���� ���� ������
//	class ReviseManager extends JFrame implements ActionListener {
//
//		JLabel title;
//		JLabel idLabel;
//		JLabel pwLabel;
//		JButton idChk;
//		JButton complete;
//		JButton exit;
//
//		JTextField idField;
//		JTextField pwField;
//		boolean idChkBl = false;
//
//		public ReviseManager() {
//
//			setTitle("������ ���� ����");
//			setBounds(700, 100, 500, 400);
//			setLayout(null);
//
//			title = new JLabel("������ ���� ����");
//			idLabel = new JLabel("ID");
//			pwLabel = new JLabel("PW");
//			idChk = new JButton("�ߺ�Ȯ��");
//			complete = new JButton("�Ϸ�");
//			exit = new JButton("������");
//			idField = new JTextField();
//			pwField = new JTextField();
//
//			title.setBounds(175, 0, 150, 50);
//			idLabel.setBounds(30, 100, 50, 50);
//			pwLabel.setBounds(30, 200, 50, 50);
//			idChk.setBounds(330, 100, 100, 50);
//			complete.setBounds(360, 300, 100, 50);
//			exit.setBounds(250, 300, 100, 50);
//			idField.setBounds(100, 100, 200, 50);
//			pwField.setBounds(100, 200, 200, 50);
//
//			idChk.addActionListener(this);
//			exit.addActionListener(this);
//			complete.addActionListener(this);
//
//			add(title);
//			add(idLabel);
//			add(pwLabel);
//			add(idChk);
//			add(complete);
//			add(exit);
//			add(idField);
//			add(pwField);
//
//			setVisible(true);
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//
//			String gettext = idField.getText();
//
//			if (e.getSource().equals(idChk)) {
//				if (idField.getText().equals("") || idField.getText().equals("")) {
//					JOptionPane.showMessageDialog(null, "��ĭ�� �Է��ϼ���");
//				} else if (!UserDB.getID(gettext).equals("")) {
//					JOptionPane.showMessageDialog(null, "�ߺ��� ID");
//				} else {
//					idChkBl = true;
//					JOptionPane.showMessageDialog(null, "��밡��");
//				}
//			} else if (e.getSource().equals(exit)) {
//				setVisible(false);
//			} else if (e.getSource().equals(complete)) {
//				if (idChkBl == true) {
//					UserDB.signupSUPERUSER(idField.getText(), pwField.getText());
//					JOptionPane.showMessageDialog(null, "�����Ϸ�");
//					setVisible(false);
//					idChkBl = false;
//				} else if (idChkBl == false) {
//					JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� �Ұ�");
//				}
//			}
//		}
//	}

	Vector<String> noticeTitleVt = new Vector<String>();

	// �������� �ۼ� ������
	class NoticeWrite extends JFrame implements ActionListener {

		JLabel titleLabel;
		JLabel noticeLable;
		JLabel writerLabel;
		JLabel writer;

		JButton complete;
		JTextArea titleText;
		JTextArea noticeText;

		public NoticeWrite() {

			setTitle("�������� �ۼ�");
			setBounds(500, 100, 700, 800);
			setLayout(null);

			titleLabel = new JLabel("����");
			noticeLable = new JLabel("��������");
			writerLabel = new JLabel("�ۼ���:");
			writer = new JLabel(userID);

			titleText = new JTextArea();
			noticeText = new JTextArea();
			noticeText.setLineWrap(true);
			complete = new JButton("�ۼ��Ϸ�");

			titleLabel.setBounds(50, 50, 50, 50);
			noticeLable.setBounds(50, 100, 100, 50);
			writerLabel.setBounds(50, 20, 50, 30);
			writer.setBounds(100, 20, 100, 30);

			titleText.setBounds(100, 60, 350, 30);
			noticeText.setBounds(50, 150, 600, 550);
			complete.setBounds(550, 50, 100, 50);

			complete.addActionListener(this);

			add(titleLabel);
			add(noticeLable);
			add(writerLabel);
			add(writer);

			add(titleText);
			add(noticeText);
			add(complete);

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(complete)) {
				if (titleText.getText().length() > 0 && noticeText.getText().length() > 0) {
					JOptionPane.showMessageDialog(null, "�������� ��� �Ϸ�");
					noticeTitleVt.add(titleText.getText());
					NoticeDB.saveNOTICE(titleText.getText(), noticeText.getText(), userID);

					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "�� ĭ�� �Է��� �ּ���");
				}
			}

		}
	}

	ArrayList<String> noticeTextArr = new ArrayList<String>();
	JComboBox noticeBox = new JComboBox<String>(noticeTitleVt);

	class NoticeReviseFrame extends JFrame implements ActionListener {

		JLabel titleReviseLabel;
		JLabel noticeReviseLabel;

		JLabel writeTime;
		JLabel reviseTime;
		JLabel writer;

		JLabel writerLabel;
		JLabel makeTimeLabel;
		JLabel modifTimeLabel;

		JLabel titleLabel;
		JLabel contentLabel;

		JTextArea titleReviseText;
		JTextArea noticeReviseText;

		JButton complete;
		String title;
		NoticeRevise noticeRevise;

		public NoticeReviseFrame(String title) {
			this.title = title;
			setTitle("�������� ����");
			setBounds(700, 100, 600, 700);
			setLayout(null);

			titleReviseText = new JTextArea();
			noticeReviseText = new JTextArea();
			writeTime = new JLabel();
			reviseTime = new JLabel();
			writer = new JLabel();
			complete = new JButton("�����Ϸ�");
			writerLabel = new JLabel("�ۼ��� : ");
			makeTimeLabel = new JLabel("�ۼ��ð� : ");
			modifTimeLabel = new JLabel("�����ð� : ");
			titleLabel = new JLabel("���� : ");
			contentLabel = new JLabel("����");

			titleReviseText.setBounds(150, 110, 300, 30);
			noticeReviseText.setBounds(150, 200, 300, 400);
			writeTime.setBounds(100, 0, 200, 30);
			reviseTime.setBounds(100, 50, 200, 30);
			writer.setBounds(500, 50, 100, 30);
			complete.setBounds(470, 550, 100, 50);

			writerLabel.setBounds(430, 50, 100, 30);
			makeTimeLabel.setBounds(30, 0, 100, 30);
			modifTimeLabel.setBounds(30, 50, 100, 30);
			titleLabel.setBounds(100, 110, 50, 30);
			contentLabel.setBounds(150, 160, 100, 30);

			complete.addActionListener(this);

			add(titleReviseText);
			add(noticeReviseText);
			add(writeTime);
			add(reviseTime);
			add(writer);
			add(complete);

			add(writerLabel);
			add(makeTimeLabel);
			add(modifTimeLabel);
			add(titleLabel);
			add(contentLabel);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			complete.setEnabled(false);
			String newTitle = titleReviseText.getText();
			String newContent = noticeReviseText.getText();
			System.out.println("�Ϸ�");
			NoticeDB.updateNOTICE(title, newTitle, newContent);

			noticeTitleVt = new Vector<String>();
			noticeArr = NoticeDB.getNOTICE();
			for (Notice notice : noticeArr) {
				noticeTitleVt.add(notice.title);
			}
			ntNoticeRevise.remove(noticeBox);
			noticeBox = new JComboBox<String>(noticeTitleVt);
			noticeBox.setBounds(75, 70, 350, 50);
			noticeBox.setBackground(Color.white);
			ntNoticeRevise.add(noticeBox);
			ntNoticeRevise.repaint();

			JOptionPane.showMessageDialog(null, "�����Ϸ�");
			setVisible(false);
		}
	}

	ArrayList<Notice> noticeArr = new ArrayList<Notice>();

	class NoticeRevise extends JFrame implements ActionListener {

		JButton noticeChk;
		JButton noticeDel;
		NoticeReviseFrame noticeReviseFrame;

		public NoticeRevise() {
			setTitle("�������� ����");
			setBounds(600, 100, 500, 300);
			setLayout(null);

			noticeChk = new JButton("����");
			noticeDel = new JButton("����");
			noticeChk.setBounds(370, 180, 100, 50);
			noticeDel.setBounds(260, 180, 100, 50);

			noticeTitleVt = new Vector<String>();
			noticeArr = NoticeDB.getNOTICE();
			noticeTitleVt.clear();
			for (Notice notice : noticeArr) {
				noticeTitleVt.add(notice.title);
			}
			noticeBox = new JComboBox<String>(noticeTitleVt);
			noticeChk.addActionListener(this);
			noticeDel.addActionListener(this);

			noticeBox.setBounds(75, 70, 350, 50);
			noticeBox.setBackground(Color.white);
			add(noticeChk);
			add(noticeDel);
			add(noticeBox);

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(noticeDel)) {
				Notice ntc = null;
				noticeTitleVt = new Vector<String>();
				noticeArr = NoticeDB.getNOTICE();
				for (Notice notice : noticeArr) {
					noticeTitleVt.add(notice.title);
				}
				for (Notice notice : noticeArr) {
					if (notice.title.equals(noticeTitleVt.get(noticeBox.getSelectedIndex()))) {
						ntc = notice;
						break;

					}
				}

				if (noticeTitleVt.size() != 0) {
					noticeTitleVt.remove(noticeBox.getSelectedIndex());
					NoticeDB.deleteNOTICE(ntc.title, ntc.content);
					JOptionPane.showMessageDialog(null, "�����Ϸ�");
					noticeTitleVt = new Vector<String>();
					noticeArr = NoticeDB.getNOTICE();
					for (Notice notice : noticeArr) {
						noticeTitleVt.add(notice.title);
					}
					remove(noticeBox);
					add(noticeBox = new JComboBox<String>(noticeTitleVt));
					noticeBox.setBounds(75, 70, 350, 50);
					noticeBox.setBackground(Color.white);
					revalidate();
					repaint();

				} else {
					JOptionPane.showMessageDialog(null, "���� �� ������ �����ϴ�");
				}

			} else if (e.getSource().equals(noticeChk)) {

				Notice ntc = null;
				noticeTitleVt = new Vector<String>();
				noticeArr = NoticeDB.getNOTICE();
				for (Notice notice : noticeArr) {
					noticeTitleVt.add(notice.title);
				}
				for (Notice notice : noticeArr) {
					if (notice.title.equals(noticeTitleVt.get(noticeBox.getSelectedIndex()))) {
						ntc = notice;
						noticeReviseFrame = new NoticeReviseFrame(ntc.title);
						noticeReviseFrame.titleReviseText.setText(ntc.title);
						noticeReviseFrame.noticeReviseText.setText(ntc.content);
						noticeReviseFrame.writeTime.setText(ntc.maketime);
						noticeReviseFrame.reviseTime.setText(ntc.modi_time);
						noticeReviseFrame.writer.setText(userID);
						noticeReviseFrame.setVisible(true);
						System.out.println("ã����");
						dispose();
						break;

					}
				}

			}

		}
	}

	class SendMessageManager extends JFrame implements ActionListener, MouseListener {
		MessagePanelManager messagePanelManager;

		JButton delete;
		JTable sendList;
		JScrollPane scroll;
		
		JLabel idSearchLabel;
		JButton search;
		JButton allMessage;
		JTextField searchText;

		String messageStr;
		String[][] arr2;
		String[] arr;

		public SendMessageManager() {

			setTitle("�����޼�����");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getFROM_MESSAGE("admin");

			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
				return;
			}

			arr = new String[] { "�������", "����", "�ð�" };

			sendList = new JTable(new NotEditTable(arr2, arr));
			scroll = new JScrollPane(sendList);
			search = new JButton("ã��");
			allMessage = new JButton("��ü");
			searchText = new JTextField();
			idSearchLabel = new JLabel("������� : ");

			delete = new JButton("����");

			scroll.setBounds(0, 150, 500, 500);
			delete.setBounds(390, 660, 100, 50);
			search.setBounds(360, 90, 100, 50);
			allMessage.setBounds(250, 90, 100, 50);
			searchText.setBounds(130, 30, 330, 50);
			idSearchLabel.setBounds(50, 30, 100, 50);

			delete.addActionListener(this);
			search.addActionListener(this);
			sendList.addMouseListener(this);
			allMessage.addActionListener(this);

			add(delete);
			add(scroll);
			add(idSearchLabel);
			add(searchText);
			add(search);
			add(allMessage);

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(delete)) {
				MessageDB.deleteSendMESSAGE("admin");
				JOptionPane.showMessageDialog(null, "�����Ϸ�");
				setVisible(false);
			}else if(e.getSource().equals(search)) {
				if (UserDB.getID(searchText.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "���� ���̵��Դϴ�");
				} else {
					remove(scroll);
					arr2 = MessageDB.getFROM_MESSAGE("admin", searchText.getText());
					arr = new String[] { "�������", "����", "�ð�" };
					if (arr2 == null) {
						JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
						return;
					}
					sendList = new JTable(new NotEditTable(arr2, arr));
					scroll = new JScrollPane(sendList);
					scroll.setBounds(0, 150, 500, 500);
					sendList.addMouseListener(this);
					add(scroll);
					revalidate();
					repaint();
				}
			}else if(e.getSource().equals(allMessage)) {
				remove(scroll);
				arr2 = MessageDB.getFROM_MESSAGE("admin");
				arr = new String[] { "�������", "����", "�ð�" };
				if (arr2 == null) {
					JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
					return;
				}
				sendList = new JTable(new NotEditTable(arr2, arr));
				scroll = new JScrollPane(sendList);
				scroll.setBounds(0, 150, 500, 500);
				sendList.addMouseListener(this);
				searchText.setText("");
				add(scroll);
				revalidate();
				repaint();
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource().equals(sendList)) {
				if (e.getClickCount() == 2) {
					SendMessageClickManager sendMessageClickManager = new SendMessageClickManager();
					sendMessageClickManager.writer.setText(arr2[sendList.getSelectedRow()][0]);
					sendMessageClickManager.content.setText(arr2[sendList.getSelectedRow()][1]);
					sendMessageClickManager.time.setText(arr2[sendList.getSelectedRow()][2]);
				}
			}

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
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class SendMessageClickManager extends JFrame {

		JLabel writerLabel;
		JLabel contentLabel;
		JLabel timeLabel;

		JLabel writer;
		JLabel time;
		JTextArea content;

		public SendMessageClickManager() {

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

	class GiveMessageManager extends JFrame implements ActionListener, MouseListener {

		JButton delete;
		JTable giveList;
		JScrollPane scroll;
		String[][] arr2;
		String[] arr;
		
		JLabel idSearchLabel;
		JButton search;
		JButton allMessage;
		JTextField searchText;

		public GiveMessageManager() {

			setTitle("�����޼�����");
			setBounds(600, 100, 515, 800);
			setLayout(null);

			arr2 = MessageDB.getTO_MESSAGE("admin");
			arr = new String[] { "�������", "����", "�ð�" };
			if (arr2 == null) {
				JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
				return;
			}
			giveList = new JTable(new NotEditTable(arr2, arr));
			scroll = new JScrollPane(giveList);
			
			search = new JButton("ã��");
			allMessage = new JButton("��ü");
			searchText = new JTextField();
			idSearchLabel = new JLabel("������� : ");

			delete = new JButton("����");

			delete.setBounds(390, 660, 100, 50);
			scroll.setBounds(0, 150, 500, 500);
			search.setBounds(360, 90, 100, 50);
			allMessage.setBounds(250, 90, 100, 50);
			searchText.setBounds(130, 30, 330, 50);
			idSearchLabel.setBounds(50, 30, 100, 50);

			delete.addActionListener(this);
			giveList.addMouseListener(this);
			search.addActionListener(this);
			allMessage.addActionListener(this);
			add(delete);
			add(scroll);
			add(idSearchLabel);
			add(searchText);
			add(search);
			add(allMessage);

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(delete)) {
			MessageDB.deleteGiveMESSAGE("admin");
			JOptionPane.showMessageDialog(null, "�����Ϸ�");
			setVisible(false);
			}else if(e.getSource().equals(search)) {
				if (UserDB.getID(searchText.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "���� ���̵��Դϴ�");
				} else {
					remove(scroll);
					arr2 = MessageDB.getTO_MESSAGE("admin", searchText.getText());
					arr = new String[] { "�������", "����", "�ð�" };
					if (arr2 == null) {
						JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
						return;
					}
					giveList = new JTable(new NotEditTable(arr2, arr));
					scroll = new JScrollPane(giveList);
					scroll.setBounds(0, 150, 500, 500);
					giveList.addMouseListener(this);
					add(scroll);
					revalidate();
					repaint();
				}
			}else if(e.getSource().equals(allMessage)) {
				remove(scroll);
				arr2 = MessageDB.getTO_MESSAGE("admin");
				arr = new String[] { "�������", "����", "�ð�" };
				if (arr2 == null) {
					JOptionPane.showMessageDialog(null, "�����޼������� ������ϴ�.");
					return;
				}
				giveList = new JTable(new NotEditTable(arr2, arr));
				scroll = new JScrollPane(giveList);
				scroll.setBounds(0, 150, 500, 500);
				giveList.addMouseListener(this);
				searchText.setText("");
				add(scroll);
				revalidate();
				repaint();
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getSource().equals(giveList)) {
				if (e.getClickCount() == 2) {
					GiveMessageClickManager giveMessageClickManager = new GiveMessageClickManager();
					giveMessageClickManager.writer.setText(arr2[giveList.getSelectedRow()][0]);
					giveMessageClickManager.content.setText(arr2[giveList.getSelectedRow()][1]);
					giveMessageClickManager.time.setText(arr2[giveList.getSelectedRow()][2]);
				}
			}

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
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class GiveMessageClickManager extends JFrame {

		JLabel writerLabel;
		JLabel contentLabel;
		JLabel timeLabel;

		JLabel writer;
		JLabel time;
		JTextArea content;

		public GiveMessageClickManager() {

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

}
