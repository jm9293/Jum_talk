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
		
		// 파라미터 --> 0:일반유저, 1:점술가, 2:관리자 --> 로그인시 db 에서 해당아이디의 식별값 받아와서 로그인 유형 가르고 시작
		new AMainFrame(2);
	}
}

// 내가맡은파트 '예약'탭 (로그인시점 : 일반사용자, 점술가, 관리자) cardLayout()
class AMainFrame extends JFrame { // 전체프레임

	// DataBase
	String userID; // 로그인단에서 가지고 넘어올예정 일단임시
	String sellerID; // 로그인단에서 가지고 넘어올예정
	ArrayList<ArrayList<String>> seller; // 전체 점술가
	ArrayList<ArrayList<String>> loginSeller; // 현재접속한 점술가

	// MainFrame
	JButton btn_rsv, btn_chat, btn_coin, btn_set; // 탭 버튼
	CardLayout card = new CardLayout();
	BottomOutPanel bop; // card.show()

	// BottomOutPanel
	PanelRsv rsv;
	PanelChat chat;
	PanelCoin coin;
	PanelSet set;

	// 일반사용자 : PanelRsv
	InRsv0 inrsv0;
	InRsv1 inrsv1;
	InRsv2 inrsv2;

	// InRsv0() --> 예약 내부패널 0:일반사용자
	JButton btn;
	ArrayList<ImageIcon> imgArr;
	ArrayList<ArrayList<String>> sellerList;
	ArrayList<JButton> btnArr;

	// 일반사용자 : Review
	Map<String, String> reviewMap;

	// 일반사용자 : ActReview 700
	ArrayList<String> nomuserID;
	ArrayList<String> nomComent;
	ArrayList<Integer> nomPoint;
	String ttNom;

	// 일반사용자 : JumsulMenu
	JButton btnFace, btnSaju, btnLove, btnNewYear, btnCompany;

	// 일반사용자 : Reservation
	String userSelectTime; // 897
	String userResDay; // 사용자가 고른 예약날짜
	int rdayUser; // 사용자 고른 날짜 892
	int cntRsv;

	// 일반사용자 : RsvTimeFrame() 점술메뉴표 -> 예약창(예약시간 및 예약완료) line 768
	JComboBox dateListD; // 일반사용자가 고른 예약 날짜리스트 840
	ArrayList<JToggleButton> btnTime; // 일반사용자가 고른 예약시간 버튼 850
	String kindMenu;
	int minuscoin = 0;

	// 일반사용자 : Reservation Finish
	Map<String, Integer> rsvMap = new HashMap<String, Integer>(); // Map<점술메뉴명, 예약시간> line 900
	JFrame rsvFin; // 마지막 예약할건지 확인창
	JButton btnFinYes;
	JButton btnFinNo;

	// 점술가 : InRsv1
	JButton rsvTime, rsvMenu, showReview;
	InRsvProfile irp0;

	// 점술가 : 예약시간관리
	JFrame f1;
	JComboBox dateListSeller;
	ArrayList<JToggleButton> btnTimeSeller; // 점술가가 고른시간
	ArrayList<String> selectToggle; // 점술가 : 예약시간관리 , 토글버튼 선택된 예약시간관리 언제인지 저장
	int rdaySeller;
	String resDaySeller;

	// 점술가: 점술메뉴표관리(추가,수정)
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
	
	// 입력받아서 바꿀 메뉴표 497
	String changeMenuName;
	int changeMenuNum;

	// 점술가: 본인리뷰보기 512
	JFrame f3;

	// Inrsv2() : 관리자
	JButton btnNormal;
	JButton btnJeomsul;
//	JButton btnAcceptList;

	// 관리자 : 일반,점술가 회원내역 554
	JFrame f4, f5;
	JButton btn_ModNomInfo;
	JButton btn_ModSelInfo;
	ArrayList<JButton> modNomInfoArr;
	ArrayList<JButton> modSelInfoArr;
	
	// 개인정보수정    631
	int NomModClk;
	// 개인정보수정(점술가)   820
	int SelModClk;
	
	// 관리자 : 가입승인리스트
//	JFrame f6;
	
	// User identity 0:일반사용자 1:점술가 2:관리자
	int kind;

	public AMainFrame(int kind) 
	{
		setBounds(100, 100, 500, 800);
		setLayout(null);
		
		this.kind = kind; // 로그인 방식 체크 0:일반, 1:점술사, 2:관리자

		sellerID = UserDB.getSELLER().get(0).id;
		userID = UserDB.getNomUser().get(0).id;
		// sellerID = UserDB.getID("로그인단에서 받아올 점술가 아이디");
		// userID = UserDB.getID("로그인단에서 받아올 일반사용자 아이디");

		bop = new BottomOutPanel(kind);

		if (kind == 0) { // 일반사용자
			btn_rsv = new JButton("예약");
			btn_rsv.setName("예약");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		} else if (kind == 1) { // 점술가
			btn_rsv = new JButton("예약");
			btn_rsv.setName("예약관리");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		}

		else { // 관리자
			btn_rsv = new JButton("회원관리");
			btn_rsv.setName("회원관리");
			btn_rsv.setBounds(10, 10, 100, 70);
			btn_rsv.addActionListener(new Act());
		}

		btn_chat = new JButton("채팅");
		btn_chat.setName("채팅");
		btn_chat.setBounds(130, 10, 100, 70);
		btn_chat.addActionListener(new Act());

		btn_coin = new JButton("코인");
		btn_coin.setName("코인");
		btn_coin.setBounds(250, 10, 100, 70);
		btn_coin.addActionListener(new Act());

		btn_set = new JButton("설정");
		btn_set.setName("설정");
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
			// 탭선택 버튼
			if (e.getSource() == btn_rsv) {
				if (btn_rsv.getName() == "예약")
					card.show(bop, "예약");
				else if (btn_rsv.getName() == "예약관리")
					card.show(bop, "예약관리");
				else {
					card.show(bop, "회원관리");
				}
			} else if (e.getSource() == btn_chat) {
				card.show(bop, "채팅");
			} else if (e.getSource() == btn_coin) {
				card.show(bop, "코인");
			} else if (e.getSource() == btn_set) {
				card.show(bop, "설정");
			}

		}
	}

	class BottomOutPanel extends JPanel { // 하단 외부패널( card layout으로 바뀔 바깥패널)

		public BottomOutPanel(int kind) {
			setBounds(0, 100, 500, 670);
			setLayout(card);

			rsv = new PanelRsv(kind);
			chat = new PanelChat();
			coin = new PanelCoin();
			set = new PanelSet();

			// 여기서 card 설정해줌
			if (kind == 0)// 일반
				add("예약", rsv);
			else if (kind == 1)
				add("예약관리", rsv);
			else
				add("회원관리", rsv);

			add("채팅", chat);
			add("코인", coin);
			add("설정", set);

			setVisible(true);
		}
	}

	class PanelRsv extends JPanel { // '예약' 패널

		public PanelRsv(int kind) {
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setName("예약");

			if (kind == 0) { // 일반고객일때
				inrsv0 = new InRsv0();
				inrsv0.setBounds(0, 0, 485, 680); // 스크롤패널 스크롤바 보이기싫으면 width +15
				add(inrsv0);
			} else if (kind == 1) { // 점술가일때
				inrsv1 = new InRsv1();
				inrsv1.setBounds(0, 0, 485, 680);
				add(inrsv1);
			} else { // 관리자일때
				inrsv2 = new InRsv2();
				inrsv2.setBounds(0, 0, 485, 680);
				add(inrsv2);
			}

		}
	}

	class InRsv0 extends JScrollPane { // '예약'패널 내부 패널(0:일반고객화면)

		public InRsv0() {
			JPanel jp = new JPanel();

			btnArr = new ArrayList<JButton>();
			imgArr = new ArrayList<ImageIcon>(); // db에서 받아올 사진 arr

			sellerList = new ArrayList<ArrayList<String>>(); // db에서 받아올 점술사 name arr

			int aa = 0; // 카운트
			for (sellUser ss : UserDB.getSELLER()) { // 점술가 회원수에 맞게 알아서
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

			int cnt = 0; // horizontal 카운트
			for (int i = 0; i < sellerList.size(); i++) {
				ImageIcon imageicon = new ImageIcon("img/img01.jpg");// ImageIcon 변경할아이콘 = new ImageIcon("이미지.jpg");

				Image img = imageicon.getImage(); // ImageIcon을 Image로 변환.

				Image imgChange = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // (가로,세로, "");

				ImageIcon imgIcon = new ImageIcon(imgChange); // Image로 ImageIcon 생성 >> 최종
				imgArr.add(imgIcon);

				JLabel lbl = new JLabel(sellerList.get(i).get(1), imgIcon, JLabel.LEFT); /// 이름 사진
				lbl.setOpaque(true);
				lbl.setBackground(Color.WHITE);
				lbl.setBounds(0, 0 + cnt, 480, 150);

				cnt += 170;

				btn = new JButton("상세프로필");
				btn.setBounds(360, 60, 100, 30);

				// 상세프로필 새 창띄우기(사진,상세소개,전화번호,평점,[리뷰보기],[점술메뉴표,가격,시간])
				btn.addActionListener(new RsvAction());
				btnArr.add(btn); // 버튼들 Arr --->> 누가 클릭되었는지

				lbl.add(btnArr.get(i));
				jp.add(lbl);
			}
		}

	}

	class InRsv1 extends JPanel { // '예약'패널 내부 패널(1:점술사화면)

		public InRsv1() {
			setLayout(new GridLayout(3, 0));

			rsvTime = new JButton("예약 시간 관리");
			rsvTime.setBackground(Color.white);
			rsvTime.addActionListener(new RsvAction());
			rsvMenu = new JButton("예약 메뉴표 관리");
			rsvMenu.setBackground(Color.white);
			rsvMenu.addActionListener(new RsvAction());
			showReview = new JButton("본인 리뷰 보기");
			showReview.setBackground(Color.white);
			showReview.addActionListener(new RsvAction());

			add(rsvTime);
			add(rsvMenu);
			add(showReview);

		}
	}

	class InRsv2 extends JPanel { // '예약'패널 내부 패널(2:관리자화면)

		public InRsv2() {
			setLayout(new GridLayout(2, 0));

			btnNormal = new JButton("일반 회원내역");
			btnNormal.setBackground(Color.white);
			btnNormal.addActionListener(new RsvAction());
			btnJeomsul = new JButton("점술가 회원내역");
			btnJeomsul.setBackground(Color.white);
			btnJeomsul.addActionListener(new RsvAction());
//			btnAcceptList = new JButton("가입승인리스트(점술가)");
//			btnAcceptList.setBackground(Color.white);
//			btnAcceptList.addActionListener(new RsvAction());

			add(btnNormal);
			add(btnJeomsul);
//			add(btnAcceptList);
		}
	}

	class RsvAction implements ActionListener { // '예약'패널의 로그인 유형별 버튼리스너

		@Override
		public void actionPerformed(ActionEvent e) {

			// 일반고객
			if (kind == 0) {
				for (int i = 0; i < btnArr.size(); i++) {
					if (e.getSource().equals(btnArr.get(i))) { // 지금은 임시라 0
						// 여기서 클릭된 점술가가 누구인지 파라미터로 보내줘야함

						String chk = i + "";
						irp0 = new InRsvProfile(chk);
					}
				}
			}
			// 점술사
			else if (kind == 1) {
				if (e.getSource().equals(rsvTime)) { //// 예약시간관리
					f1 = new JFrame("예약시간관리창");
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
					String day = sdf.format(todayC.getTime()).substring(8, 10); // 오늘 날짜 01~31

					Vector<Integer> dayList = new Vector<Integer>();
					for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
						dayList.add(i);
					}

					dateListSeller = new JComboBox(dayList);
					dateListSeller.setBounds(20, 20, 80, 20);

					JLabel lbl_date = new JLabel(" 일");
					lbl_date.setBounds(100, 20, 20, 20);

					rdaySeller = (int) dateListSeller.getSelectedItem(); // 점술가가 누른 예약날짜
					resDaySeller = rdaySeller + ""; // string 캐스팅

					btnTimeSeller = new ArrayList<JToggleButton>();
					for (int i = 0; i < 24; i++) {
						JToggleButton bb = new JToggleButton(i + "");
						bb.setBackground(Color.white);
						btnTimeSeller.add(bb);
						p2.add(btnTimeSeller.get(i));
					}

					JButton btnSave = new JButton("저장");
					btnSave.setBounds(200, 10, 100, 50);
					btnSave.addActionListener(new ActSaveRes());

					p1.add(dateListSeller);
					p1.add(lbl_date);
					p3.add(btnSave);
					f1.add(p1);
					f1.add(p2);
					f1.add(p3);
					f1.setVisible(true);
				} else if (e.getSource().equals(rsvMenu)) { //// 예약메뉴관리 --> 메뉴번호를가지고 메뉴명 변경
					f2 = new JFrame("예약메뉴표관리창");
					f2.setBounds(400, 100, 500, 250);
					f2.setLayout(null);

					//// 여기서 db로 새로만들 메뉴표 올리거나 기존에 있는 메뉴표 수정할수있게 db연결
					lblAdd1 = new JLabel("점술메뉴명 : ");
					lblAdd1.setBounds(10, 30, 110, 30);
					lblAdd2 = new JLabel("바꿀 메뉴 번호 : ");
					lblAdd2.setBounds(10, 70, 110, 30);
					taAdd = new JTextArea();
					taAdd.setBounds(100, 30, 350, 30);
					taAdd.setBackground(Color.white);
					taAdd1 = new JTextArea();
					taAdd1.setBounds(100, 70, 350, 30);
					taAdd1.setBackground(Color.white);

					b1 = new JButton("예약메뉴표 수정"); // 예약메뉴표 수정
					b1.setBounds(170, 150, 150, 30);
					b1.addActionListener(new ActMenuModify());

					f2.add(lblAdd1);
					f2.add(lblAdd2);
					f2.add(taAdd);
					f2.add(taAdd1);
					f2.add(b1);

					f2.setVisible(true);
				} else { //// 점술가 : 본인리뷰보기
					f3 = new JFrame("본인리뷰보기");
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
						tt += "[" + UserDB.getNomUser().get(i).id + "] : " + com + "\n평점: [" + myReviewPoint.get(i)
								+ "] " + "\n\n";
						i++;
					}

					JTextArea t1 = new JTextArea();
					t1.setText(tt);
					t1.setLineWrap(true);
					t1.setFont(new Font("고딕", Font.BOLD, 15));
					t1.setEnabled(false);
					JScrollPane scp = new JScrollPane(t1);
					scp.setForeground(Color.BLACK);
					scp.setBounds(0, 0, 585, 665);

					f3.add(scp);
					f3.setVisible(true);
				}
			} else { //// 관리자
				if (e.getSource().equals(btnNormal)) {					
					f4 = new JFrame("일반 회원내역");
					f4.setBounds(400, 100, 500, 800);
					f4.setLayout(null);

					int cnt = 0;
					for (normalUser nm : UserDB.getNomUser()) { 
						JLabel lblMg = new JLabel();
						lblMg.setBounds(5, 5 + cnt, 490, 40);
						lblMg.setOpaque(true);
						lblMg.setBackground(Color.white);
						lblMg.setText("아이디 : " + nm.id + "     이름 : " + nm.name + "     전화번호 : " + nm.phone);
						
						modNomInfoArr = new ArrayList<JButton>();
						btn_ModNomInfo = new JButton("수정");
						btn_ModNomInfo.setBounds(410, 5, 60, 30);
						btn_ModNomInfo.addActionListener(new ReviseInfor());
						modNomInfoArr.add(btn_ModNomInfo);
						cnt += 50;
						lblMg.add(btn_ModNomInfo);
						f4.add(lblMg);
					}

					f4.setVisible(true);
					
				} else if (e.getSource().equals(btnJeomsul)) {
					f5 = new JFrame("점술가 회원내역");
					f5.setBounds(400, 100, 500, 800);
					f5.setLayout(null);

					int cnt = 0;
					for (sellUser sell : UserDB.getSELLER()) {
						JLabel lblMg = new JLabel();
						lblMg.setBounds(5, 5 + cnt, 490, 40);
						lblMg.setOpaque(true);
						lblMg.setBackground(Color.white);
						lblMg.setText("아이디: " + sell.id + "     이름: " + sell.name + "     전화번호: " + sell.phone);

						modSelInfoArr = new ArrayList<JButton>();
						btn_ModSelInfo = new JButton("수정");
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
//					f6 = new JFrame("가입승인리스트(점술가)");
//					f6.setBounds(400, 100, 500, 800);
//					f6.setLayout(null);
//					/////// DB 에서 가입승인리스트 테이블 null 체크해서 띄워주면될듯
//
//					f6.setVisible(true);
//				} 
			}

		}
	}

	class ReviseInfor implements ActionListener { // 개인정보 수정 프레임(신혁)

		JFrame jf;
		
		UserDB userDB;
		String userID;
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
					NomModClk = i; // 클릭된 버튼 인덱스저장
				}
			}

			jf = new JFrame();
			jf.setLayout(null);
			jf.setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀
			jf.setBounds(700, 100, 500, 700);

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

			helloText = new JTextField();
			pwField = new JPasswordField();
			phoneNumText = new JTextField();
			emailText = new JTextField();
			addressText = new JTextField();
			cardNumText = new JTextField();
			pwHintText = new JTextField();
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

			// 수정완료 버튼
			reviseGo = new JButton("수정 완료");
			// 개인정보 수정 프레임 나가기 버튼
			back = new JButton("나가기");

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

			for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
				information.get(i).setBounds(10, i * 60, 100, 50);
			}

			reviseGo.addActionListener(new ActModifyNomInfo()); // 수정완료 버튼 /////////////////고치는중
			back.addActionListener(new ActModifyNomInfo()); // 나가기 버튼

			jf.add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
			jf.add(back); // 개인정보 수정 프레임에 나가기 버튼 추가

			jf.add(helloText);
			jf.add(pwField);
			jf.add(phoneNumText);
			jf.add(emailText);
			jf.add(addressText);
			jf.add(cardNumText);
			jf.add(pwHintText);
			jf.add(hintAnswerText);

			for (JLabel jl : information) {
				jf.add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
			}

			jf.setVisible(true);

		}
		
		class ActModifyNomInfo implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(back)) { // 개인정보수정 프레임에서 나가기 버튼 클릭
					// 개인정보 수정 프레임 사라지기
					jf.setVisible(false);
				} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
					// 수정완료 팝업 띄운후 프레임 사라지기
					String temp = UserDB.getNomUser().get(NomModClk).id;	// 내가고른버튼(회원내역)의 아이디
					
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
					JOptionPane.showMessageDialog(null, "수정완료");
					jf.setVisible(false);

				}

			}
		}
		
		
		
	}

	class SellerReviseInfor implements ActionListener { // 점술가 정보 수정 프레임 (소비자 수정 프레임 상속)

		JFrame jf;
		
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
		JLabel workplaceName; // 점술가 사업장 이름
		JLabel workplaceAddress; // 점술가 사업장 주소
		ArrayList<JLabel> information; // 개인정보 리스트
		JButton reviseGo; // 개인정보 수정 완료 버튼
		JButton back; // 개인정보 수정 나가기 버튼

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

		UserDB userDB;

		String userID = "piano";

		
		@Override
		public void actionPerformed(ActionEvent e) {
			SelModClk = 0;
			for (int i = 0; i < modSelInfoArr.size(); i++) {
				if (e.getSource().equals(modSelInfoArr.get(i))) {
					SelModClk = i; // 클릭된 버튼 인덱스저장
				}
			}
			
			jf = new JFrame();
				jf.setLayout(null);				
				jf.setTitle("개인정보 수정"); // 개인정보 수정 프레임 타이틀
				jf.setBounds(700, 100, 500, 800);

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

				for (int i = 1; i < information.size(); i++) { // 개인정보 라벨 위치 잡기(리스트)
					information.get(i).setBounds(10, i * 60, 100, 50);
				}

				reviseGo.addActionListener(new ActModifySelInfo()); // 수정완료 버튼
				back.addActionListener(new ActModifySelInfo()); // 나가기 버튼

				jf.add(reviseGo); // 개인정보 수정 프레임에 수정완료 버튼 추가
				jf.add(back); // 개인정보 수정 프레임에 나가기 버튼 추가

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
					jf.add(jl); // 개인정보 수정 프레임에 개인정보 라벨 추가
				}

				jf.setVisible(true);
			

		}
		
		class ActModifySelInfo implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(back)) { // 개인정보수정 프레임에서 나가기 버튼 클릭
					// 개인정보 수정 프레임 사라지기
					jf.setVisible(false);
				} else if (e.getSource().equals(reviseGo)) { // 개인정보 수정 프레임에서 완료 버튼 클릭
					// 수정완료 팝업 띄운후 프레임 사라지기

					String temp = UserDB.getSELLER().get(SelModClk).id;	// 내가고른버튼(회원내역)의 아이디
					
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
					JOptionPane.showMessageDialog(null, "수정완료");
					jf.setVisible(false);

				}

			}
		}
		
		
	}

	class ActSaveRes implements ActionListener {// 점술가 : 스케쥴 저장버튼 처리 473

		@Override
		public void actionPerformed(ActionEvent e) {

			/*
			 * rdaySeller (int) resDaySeller (string) selectToggle (arrayList)
			 */
			// 토글버튼에서 선택된 예약시간
			selectToggle = new ArrayList<String>();
			for (int i = 0; i < btnTimeSeller.size(); i++) {
				if (btnTimeSeller.get(i).isSelected()) {
					selectToggle.add(i + "");
				}
			}

			// 스케줄저장처리하려면 일단
			// 지금
			// 내
			// 정확한
			// 로그인
			// userID를
			// 알아야함
//			ScheduleDB.makeSchedule(userID, date);		

			JOptionPane jp = new JOptionPane(); //// 여기서 디비로 예약정보 올려주면됌
			jp.showMessageDialog(f1, "스케줄이 저장되었습니다");
			f1.setVisible(false);
		}
	}

	class ActMenuModify implements ActionListener { // line 506 점술가 메뉴수정버튼

		@Override
		public void actionPerformed(ActionEvent e) {

			// 입력받아서 바꿀 메뉴표
			changeMenuName = taAdd.getText(); // 바꿀 점술메뉴명
			changeMenuNum = Integer.parseInt(taAdd1.getText()); // 바꿀 메뉴의 인덱스\

			if (MenuDB.getMENU(sellerID) != null) { // 이것도 다처리된건데 로그인단에서 식별된 아이디 가지고와야 해결가능함
				MenuDB.setMENU(sellerID, changeMenuNum, changeMenuName); // 메뉴번호는 1부터시작..2345
			} else {
				JOptionPane jp = new JOptionPane();
				jp.showMessageDialog(null, "수정할 메뉴가 없습니다");
			}

			JOptionPane jp = new JOptionPane(); //// 여기서 디비로 예약정보 올려주면됌
			jp.showMessageDialog(f1, "점술메뉴가 변경되었습니다");
			f2.setVisible(false);
		}
	}

	class InRsvProfile extends JFrame { // '예약'패널의 내부 패널의 상세프로필 창 -- 점술가를 골랏을때 (0:일반고객화면)

		public InRsvProfile(String whoSeller) { // 점술가중 누구의 상세프로필인지 파라미터로 accept
			int whoSell = Integer.parseInt(whoSeller);
			sellerID = UserDB.getSELLER().get(whoSell).id; // 선택된 점술사 아이디로 초기화

			setLayout(null);
			setBounds(400, 100, 500, 800);

			JPanel jp = new JPanel();
			JPanel jp2 = new JPanel();
			jp.setLayout(new GridLayout(5, 0));
			jp.setBounds(0, 0, 485, 330);
			jp2.setLayout(new GridLayout(6, 1));
			jp2.setBounds(0, 330, 485, 430);

			JLabel lbl1 = new JLabel("[사진]");
			JLabel lbl2 = new JLabel();
			lbl2.setText("[상세소개] : "+ UserDB.getPROFILE_TEXT(sellerID));
			JLabel lbl3 = new JLabel("전화번호");
			lbl3.setText("[전화번호] : "+ UserDB.getPHONE(sellerID));
			JLabel lbl4 = new JLabel();
			lbl4.setText("[평점] : "+ ReviewDB.getAVGPOINT(sellerID) + "");

			// db에서 받아올
			// String id, String coment, String maketime, int point
			// 아이디 리뷰내용 남긴시간(필요X) 평점
			nomuserID = new ArrayList<String>();
			nomComent = new ArrayList<String>();
			nomPoint = new ArrayList<Integer>();
			for (Review rv : ReviewDB.getREVIEW(sellerID)) {
				System.out.println(ReviewDB.getREVIEW(sellerID));
				nomComent.add(rv.coment);
				nomPoint.add(rv.point);
			}

			JButton btnReview = new JButton("리뷰 보기");
			btnReview.setBackground(Color.white);
			btnReview.addActionListener(new ActReview());

			JLabel lblMenu = new JLabel("-----------------------------------------------------"
					+ "점술메뉴표\n--------------------------------------------------");

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

	class ActReview implements ActionListener { // 내가고른 점술가의 리뷰보기 버튼클릭시!!

		@Override
		public void actionPerformed(ActionEvent e) {
			ttNom = "";
			int j = 0;
			for (String comm : nomComent) {
				ttNom += "아이디 : [" + ReviewDB.getREVIEW(sellerID).get(j).id + "]\n" + comm + "\n평점 : ["
						+ nomPoint.get(j) + "] " + "\n\n";
				j++;
			}

			JFrame f1 = new JFrame("리뷰리스트");
			f1.setLayout(null);
			f1.setBounds(800, 100, 600, 700);

			JTextArea t1 = new JTextArea(ttNom);
			t1.setLineWrap(true);
			t1.setFont(new Font("고딕", Font.BOLD, 15));
			t1.setEnabled(false);
			JScrollPane scp = new JScrollPane(t1);
			scp.setBounds(0, 0, 585, 665);

			f1.add(scp);
			f1.setVisible(true);
		}
	}

	class ActJumsulMenu implements ActionListener { // 관상사주... 중 뭐눌럿는지

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btnFace)) { // 관상일때
				cntRsv = 0;
				new RsvTimeFrame(0);
			} else if (e.getSource().equals(btnSaju)) { // 사주
				cntRsv = 0;
				new RsvTimeFrame(1);
			} else if (e.getSource().equals(btnLove)) { // 연애
				cntRsv = 0;
				new RsvTimeFrame(2);
			} else if (e.getSource().equals(btnNewYear)) { // 신년
				cntRsv = 0;
				new RsvTimeFrame(3);
			} else if (e.getSource().equals(btnCompany)) { // 직장
				cntRsv = 0;
				new RsvTimeFrame(4);
			}

		}
	}

	class RsvTimeFrame extends JFrame { // 점술메뉴표 -> 예약창(예약시간 및 예약완료)

		public RsvTimeFrame(int identity) {
			setBounds(400, 100, 515, 520);
			setLayout(null);

			if (identity == 0) { // 관상
				kindMenu = "관상";
				minuscoin = 500;
				reservation(kindMenu, minuscoin);
			} else if (identity == 1) { // 사주
				kindMenu = "사주풀이";
				minuscoin = 500;
				reservation(kindMenu, minuscoin);
			} else if (identity == 2) { // 연애
				kindMenu = "연애운세";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			} else if (identity == 3) { // 신년
				kindMenu = "신년운세";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			} else if (identity == 4) { // 직장
				kindMenu = "직장운세";
				minuscoin = 300;
				reservation(kindMenu, minuscoin);
			}

			setVisible(true);
		}

		void reservation(String kindMenu, int coin) { // 점술종류 파라미터값 받아옴

			// db에서 점술가가 예약 거부한 시간은 int로 받아와서 담아두고 그 시간만 버튼 비활성화 처리
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
			String day = sdf.format(todayC.getTime()).substring(8, 10); // 오늘 날짜 01~31

			Vector<Integer> dayList = new Vector<Integer>(); // 오늘 +2 일까지
			for (int i = Integer.parseInt(day); i <= Integer.parseInt(day) + 2; i++) {
				dayList.add(i);
			}

			dateListD = new JComboBox(dayList);
			dateListD.setBounds(20, 20, 80, 20);

			JLabel lbl_date = new JLabel(" 일");
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

			// 널체크해서 들어오면 불린하나만들어서 탈출할때 다펄스 트루로바꿔준것만 셋스케줄 방금만든거니까 펄스니까 이미갖고온걸 포문을 24까지돌려서
			JButton btnSave = new JButton("예약하기");
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

	class ActRsvFinish implements ActionListener { // 정말 예약할건지 마지막 확인버튼

		@Override
		public void actionPerformed(ActionEvent e) {

			rsvFin = new JFrame();
			rsvFin.setLayout(null);
			rsvFin.setBounds(700, 350, 410, 200);

			rdayUser = (int) dateListD.getSelectedItem(); // 사용자가 예약고른날짜
			userResDay = rdayUser + ""; // string
			System.out.println(userResDay);
			// 토글버튼에서 선택된 예약할 시간
			for (int i = 0; i < btnTime.size(); i++) {
				if (btnTime.get(i).isSelected()) {
					userSelectTime = (i + "");
				}
			}
			System.out.println(userSelectTime);
			rsvMap.put(userResDay, Integer.parseInt(userSelectTime)); // 예약 날짜, 예약된 시간 맵에 저장

			for (Entry<String, Integer> ee : rsvMap.entrySet()) {
				JLabel lbl = new JLabel();
				lbl.setBounds(20, 5, 410, 20);
				lbl.setOpaque(true);
				lbl.setText("점술메뉴 : [" + kindMenu + "]" + "     예약날짜 : [" + ee.getKey() + "일]" + "     예약시간 : ["
						+ ee.getValue() + "시]");
				JLabel lbl2 = new JLabel();
				lbl2.setBounds(130, 30, 410, 20);
				lbl2.setOpaque(true);
				lbl2.setText("예약하시겠습니까?");
				JLabel lbl3 = new JLabel();
				lbl3.setBounds(120, 55, 410, 20);
				lbl3.setOpaque(true);
				lbl3.setText(minuscoin + " 코인이 차감됩니다");

				rsvFin.add(lbl);
				rsvFin.add(lbl2);
				rsvFin.add(lbl3);
			}
			btnFinYes = new JButton("예");
			btnFinYes.setBounds(20, 100, 150, 50);
			btnFinYes.addActionListener(new ActFinishChk());
			btnFinNo = new JButton("아니오");
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
				
//				userResDay	// 사용자가 예약고른날짜 string
//				userSelectTime	// 고른시간 string
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
								
				JOptionPane jp = new JOptionPane(); //// 여기서 디비로 예약정보 올려주면됌
				jp.showMessageDialog(rsvFin, "예약이 완료되었습니다");
				rsvFin.setVisible(false);
			} else if (e.getSource().equals(btnFinNo)) {
				rsvFin.setVisible(false);
			}

		}
	}

	class PanelChat extends JPanel { // '채팅' 패널

		public PanelChat() {
			setName("채팅");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.green);
			JButton aa = new JButton("예시2");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

	class PanelCoin extends JPanel { // '코인' 패널

		public PanelCoin() {
			setName("코인");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.blue);
			JButton aa = new JButton("예시3");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

	class PanelSet extends JPanel { // 설정 패널

		public PanelSet() {
			setName("설정");
			setBounds(0, 0, 500, 670);
			setLayout(null);
			setBackground(Color.cyan);
			JButton aa = new JButton("예시4");
			aa.setBounds(0, 0, 500, 30);
			add(aa);
		}
	}

}