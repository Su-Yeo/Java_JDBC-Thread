package movieReservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.LetItSnow;
import style.Style;
import movieReservation.Bottom;
import movieReservation.Chair;
import style.Top;

public class MoviePayment extends JFrame {
	
	String MovieName, screenNum, playTime, playDate;
	String id = Dispatcher.loginMember.getId();
	int [] reserveSeatNum;
	int sNum, seatNum;
	int selectcount;
	MoviePayment p;
	
	JTextField cardNumber_txt[];
	JPasswordField cardpassword_txt;
	JTextField cardexpirationMonth_txt;
	JTextField cardexpirationDay_txt;
	JPasswordField cardBirthday_txt;
	
	public MoviePayment(String MovieName, String screenNum,String playTime, String playDate, int [] reserveSeatNum, int sNum, int selectcount) {
		// TODO Auto-generated constructor stub
		new Style(800, 650, this);
//		setBounds(150, 150, 800, 650);
		setLayout(null);
		setTitle("영화 예매 시스템");
		
		this.selectcount = selectcount;
		this.p = this;
		this.MovieName = MovieName;
		this.screenNum = screenNum;
		this.playTime = playTime;
		this.playDate = playDate;
		this.reserveSeatNum = reserveSeatNum;
		this.sNum = sNum;

//눈내리기
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		add(snow);
		snow.setBounds(0,0,800,650);
		Thread t1 = new Thread(snow);
		t1.start();
		
		
//상단바
		Top top_pan = new Top();
		add(top_pan);
		
		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 25, this.getWidth()-16, 34);
		pan1.setBackground(new Color(13, 113, 62));
		JLabel subject = new JLabel("결제",JLabel.CENTER);
		subject.setOpaque(true);
		subject.setFont(new Font("HY엽서L",Font.BOLD ,19));
		subject.setForeground(Color.WHITE);
		subject.setBackground(new Color(13, 113, 62));
		pan1.add(subject);
		add(pan1);
		
		
//우측 결제정보 부분
		JPanel pan3 = new JPanel();
		pan3.setBounds(this.getWidth()-206, 40, 190, 372);
		pan3.setLayout(null);
		pan3.setBackground(new Color(238,238,238));
		add(pan3);
		
		
//결제할금액 
		String grade = Dispatcher.loginMember.getGrade();
		int discount = 0; // 브론즈일시 0%
		switch(grade) {
		case "실버":
			discount = 5;
			break;
		case "골드":
			discount =10;
			break;
		}
		int cost = 6000 - (6000/100*discount);
		
		JPanel resultCost_pan = new JPanel();
		resultCost_pan.setBounds(20, 40, 150, 80);
		resultCost_pan.setLayout(new GridLayout(2,1));
		JLabel resultCost1_lbl = new JLabel("결제하실 금액",JLabel.CENTER);
		resultCost1_lbl.setOpaque(true);
		resultCost1_lbl.setBackground(Color.lightGray);
		resultCost1_lbl.setFont(new Font("HY엽서L",Font.BOLD,20));
		JLabel resultCost2_lbl = new JLabel((cost*selectcount)+"원",JLabel.RIGHT);
		resultCost2_lbl.setOpaque(true);
		resultCost2_lbl.setFont(new Font("HY엽서L",Font.BOLD,20));
		resultCost2_lbl.setBackground(new Color(238, 28, 37));
		resultCost2_lbl.setForeground(Color.white);
		resultCost_pan.add(resultCost1_lbl);
		resultCost_pan.add(resultCost2_lbl);
		pan3.add(resultCost_pan);
		
		
//선택한 좌석
		JPanel choiceSeat_pan = new JPanel();
		choiceSeat_pan.setBounds(20, 170, 150, 80);
		int count = 1;
		choiceSeat_pan.setLayout(new GridLayout(2,1));
		choiceSeat_pan.setOpaque(true);
		choiceSeat_pan.setBackground(Color.lightGray);
		JLabel choiceSeat1_lbl = new JLabel("선택한 좌석",JLabel.CENTER);
		choiceSeat1_lbl.setFont(new Font("HY엽서L",Font.BOLD,20));
		choiceSeat_pan.add(choiceSeat1_lbl);
		
		JLabel choiceSeat2_lbl[] = new JLabel[5];
		for(int i=0; i<50; i++) {
			if(reserveSeatNum[i] != 0) {
				count = count + 1;
				choiceSeat_pan.setLayout(new GridLayout(count,1));
				choiceSeat_pan.setBounds(20, 150, 150, 80+(20*(count-1)));
				choiceSeat2_lbl[count-2] = new JLabel(String.valueOf(reserveSeatNum[i]),JLabel.CENTER);
				choiceSeat2_lbl[count-2].setOpaque(true);
				choiceSeat2_lbl[count-2].setBackground(new Color(51, 51, 51));
				choiceSeat2_lbl[count-2].setFont(new Font("HY엽서L",Font.BOLD,20));
				choiceSeat2_lbl[count-2].setForeground(Color.white);
				choiceSeat_pan.add(choiceSeat2_lbl[count-2]);
			}
		}
		
		
		pan3.add(choiceSeat_pan);

		JPanel center_pan = new JPanel(null);
		center_pan.setBounds(0,40,800-206,372);
		center_pan.setBackground(Color.WHITE);
		add(center_pan);
		
// 카드정보table부분
		JPanel cardlbl_pan = new JPanel(new GridLayout(5,1,1,1));
		JPanel cardContent_pan = new JPanel(new GridLayout(5,1,1,1));
		cardlbl_pan.setBackground(Color.white);
		cardContent_pan.setBackground(Color.white);
//label부분
		
		JLabel cardKind_lbl = new JLabel("카드종류",JLabel.RIGHT);
		JLabel cardNumber_lbl = new JLabel("카드번호",JLabel.RIGHT);
		JLabel cardpassword_lbl = new JLabel("비밀번호",JLabel.RIGHT);
		JLabel cardexpiration_lbl = new JLabel("유효기간",JLabel.RIGHT);
		JLabel cardbirthday_lbl = new JLabel("법정생년월일",JLabel.RIGHT);
		
		cardlbl_pan.setBounds(50, 30, 100, 300);
		cardlbl_pan.add(cardKind_lbl);
		cardlbl_pan.add(cardNumber_lbl);
		cardlbl_pan.add(cardpassword_lbl);
		cardlbl_pan.add(cardexpiration_lbl);
		cardlbl_pan.add(cardbirthday_lbl);
		center_pan.add(cardlbl_pan);

		
// 카드종류 선택부분
		cardContent_pan.setBounds(160, 38, 400, 300);
		JPanel cardKind_pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Vector<String> cardKind_str = new Vector<String>();
		cardKind_str.add("삼성카드");
		cardKind_str.add("국민카드");
		cardKind_str.add("비씨카드");
		cardKind_str.add("현대카드");
		cardKind_str.add("롯데카드");
		JComboBox <String> cardKind_combo = new JComboBox<String>(cardKind_str);
		cardKind_combo.setPreferredSize(new Dimension(200,30));
		cardKind_pan.add(cardKind_combo);
		cardKind_pan.setBackground(Color.white);
		cardContent_pan.add(cardKind_pan);
		
		
//카드번호 입력부분
		cardNumber_txt = new JTextField[4];
		JLabel cardNumber_lbl2[] = new JLabel[4];
		JPanel cardNumber_pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		for (int i =0; i<4; i++) {
			cardNumber_txt[i] = new JTextField();
			cardNumber_txt[i].setPreferredSize(new Dimension(70, 30));
			cardNumber_lbl2[i] = new JLabel("-",JLabel.CENTER);
			cardNumber_pan.add(cardNumber_txt[i]);
			if(i==3) {
				break;
			}
			cardNumber_pan.add(cardNumber_lbl2[i]);
		}
		cardNumber_pan.setBackground(Color.white);
		cardContent_pan.add(cardNumber_pan);
		
		
		
//카드 비밀번호 입력부분
		JPanel cardpassword_pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cardpassword_txt = new JPasswordField();
		cardpassword_txt.setPreferredSize(new Dimension(90, 30));
		cardpassword_txt.setFont(new Font("", Font.PLAIN, 25));
		cardpassword_pan.add(cardpassword_txt);
		cardpassword_pan.setBackground(Color.white);
		cardContent_pan.add(cardpassword_pan);
		
		
		
//카드 유효기간 입력부분
		JPanel cardexpiration_pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cardexpirationMonth_txt = new JTextField();
		cardexpirationMonth_txt.setPreferredSize(new Dimension(90, 30));
		JLabel cardexpirationMonth_lbl = new JLabel("월");
		cardexpirationDay_txt = new JTextField();
		JLabel cardexpirationDay_lbl = new JLabel("년");
		cardexpirationDay_txt.setPreferredSize(new Dimension(90, 30));
		cardexpiration_pan.add(cardexpirationMonth_txt);
		cardexpiration_pan.add(cardexpirationMonth_lbl);
		cardexpiration_pan.add(cardexpirationDay_txt);
		cardexpiration_pan.add(cardexpirationDay_lbl);
		cardexpiration_pan.setBackground(Color.white);
		cardContent_pan.add(cardexpiration_pan);

		
//법정생년월일 입력부분
		JPanel cardBirthday_pan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cardBirthday_txt = new JPasswordField();
		cardBirthday_txt.setPreferredSize(new Dimension(140, 30));
		cardBirthday_txt.setFont(new Font("", Font.PLAIN, 25));
		JLabel cardBirthday_lbl = new JLabel("-");
		JPasswordField cardBirthdayDisable_txt = new JPasswordField("0000000");
		cardBirthdayDisable_txt.setPreferredSize(new Dimension(140, 30));
		cardBirthdayDisable_txt.setFont(new Font("", Font.PLAIN, 25));
		cardBirthdayDisable_txt.setEnabled(false);
		cardBirthday_pan.add(cardBirthday_txt);
		cardBirthday_pan.add(cardBirthday_lbl);
		cardBirthday_pan.add(cardBirthdayDisable_txt);
		cardBirthday_pan.setBackground(Color.white);
		cardContent_pan.add(cardBirthday_pan);
		
		
		Bottom bottom_pan = new Bottom(MovieName, screenNum, playDate, playTime);
		bottom_pan.btnNext.addActionListener(pay);
		bottom_pan.btnCancel.addActionListener(cancel);
		add(bottom_pan);
		
		center_pan.add(cardContent_pan);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	ActionListener cancel = new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			new Chair(MovieName, screenNum, playDate, playTime);
			p.dispose();
		}
	};
	
	
	ActionListener pay = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent act) {
			 UIManager UI=new UIManager();
			 UI.put("OptionPane.background", new Color(13, 113, 62));
			 UI.put("Panel.background", new Color(13, 113, 62));
			 UI.put("OptionPane.messageForeground", Color.WHITE);
			 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
			 UI.put("Button.background", new Color(238, 28, 37));
			 UI.put("Button.foreground", Color.WHITE);
			 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
			for(int i =0; i<4; i++) {
				if(cardNumber_txt[i].getText().length() <4) {
					JOptionPane.showMessageDialog(null, "카드번호를 정확히 입력해주세요", "경고", JOptionPane.PLAIN_MESSAGE);return;
				}
			}
			
			String pw = "";  
			char[] secret_pw = cardpassword_txt.getPassword(); 
			for(char cha : secret_pw){         
				pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";   
			}
	
			if(!pw.toString().equals(Dispatcher.loginMember.getPassword())) {
				JOptionPane.showMessageDialog(null, "비밀번호를 정확히 입력해주세요", "경고", JOptionPane.PLAIN_MESSAGE);return;
			}
			if(cardexpirationMonth_txt.getText().equals("") || cardexpirationDay_txt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "카드 유효기간을 입력해주세요", "경고", JOptionPane.PLAIN_MESSAGE);return;
			}
			
			String Birth = "";  
			char[] secret_Birth = cardBirthday_txt.getPassword(); 
			for(char cha : secret_Birth){         
				Birth += (Birth.equals("")) ? ""+cha+"" : ""+cha+"";   
			}
			if(Birth.length() <6) {
				JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해주세요", "경고", JOptionPane.PLAIN_MESSAGE);return;
			}
			
			
			for(int i = 0; i<50;i++) {
				if(reserveSeatNum[i] != 0) {
					seatNum = reserveSeatNum[i];
					insertReserve();
				}
			}
			UpdateBuycount();
			
			Dispatcher.goTo("MainView");
			p.dispose();
		}
	};
	
	public void UpdateBuycount() {
		Connection con = MyDB.getCon();
		String sql = "update member set grade=?, buycount=? "
				+ "where id=?";
		try{
			String grade = Dispatcher.loginMember.getGrade();
			int buyCount = Dispatcher.loginMember.getBuyCount();
			String id = Dispatcher.loginMember.getId();
			
			buyCount = buyCount + selectcount;
			
			if(buyCount >=5 && grade.equals("브론즈")) {
				grade = "실버";
			}
			if(buyCount >=10 && (grade.equals("브론즈") || grade.equals("실버"))) {
				grade = "골드";
			}
			Dispatcher.loginMember.setGrade(grade);
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  grade);
			pstmt.setInt(2,  buyCount);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public void insertReserve(){
		Connection con = MyDB.getCon();
		String sql = "insert into reservation values(?,?,?,?,?,?,?)";
		
		
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  0);
			pstmt.setString(2,  id);
			pstmt.setInt(3,  sNum);
			pstmt.setString(4,  screenNum);
			pstmt.setString(5,  playTime);
			pstmt.setInt(6, seatNum);
			pstmt.setString(7, playDate);
			pstmt.executeUpdate();
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
	}
		}
}
