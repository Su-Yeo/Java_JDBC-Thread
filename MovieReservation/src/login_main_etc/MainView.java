package login_main_etc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import movieReservation.PosterImage;
import style.LetItSnow;
import style.Style;

public class MainView extends JFrame {
	
	private String[] movieTitles= {"보헤미안 랩소디","국가부도의 날","성난 황소",
			"완벽한 타인","신비한 동물들과 그린델왈드의 범죄","투 프렌즈","베일리 어게인","거미줄에 걸린 소녀"};
	private JButton[] btnArr=new JButton[8];
	JButton reservationBtn;
	JButton resCheck;
	JButton logoutBtn;
	JButton foodResBtn;
	JButton userInforBtn;
	Thread t;
	MainView mv;
	Dispatcher d;
	JLabel upperText;
	
	public MainView() {
		ImageIcon tempIcon2=new ImageIcon("imgs/mainback.jpg");
		Image toChangeImage2 = tempIcon2.getImage();
		Image changedImage2 = toChangeImage2.getScaledInstance(800, 650, java.awt.Image.SCALE_SMOOTH);
		ImageIcon changedIcon2=new ImageIcon(changedImage2);
		JLabel backgroundImage=new JLabel(changedIcon2);
		add(backgroundImage);
		backgroundImage.setLayout(null);
		backgroundImage.setLocation(0,0);
		backgroundImage.setSize(800,650);
		
		this.mv=this;
		Font textFont=new Font("HY엽서L", Font.BOLD,20);
		JPanel panCenterLeft=new JPanel();
		PosterImage panCenterRight=new PosterImage();
		JPanel panUpperMenu=new JPanel();
		JPanel userInfor = new JPanel();
		
		backgroundImage.add(panUpperMenu);
		backgroundImage.add(panCenterLeft);
		backgroundImage.add(panCenterRight);
		backgroundImage.add(userInfor);
		
		panUpperMenu.setLayout(null);
		panCenterLeft.setLayout(null);
		panCenterRight.setLayout(null);
		userInfor.setLayout(new FlowLayout());
		
		TitledBorder tb=new TitledBorder(new LineBorder(new Color(170, 18, 18),2));
		panCenterRight.setBorder(tb);
		panUpperMenu.setOpaque(false);
		panUpperMenu.setSize(800, 50);
		panUpperMenu.setLocation(0,0);
		panCenterLeft.setOpaque(false);
		panCenterLeft.setSize(330, 600);
		panCenterLeft.setLocation(20,70);
		panCenterRight.setOpaque(false);
		panCenterRight.setSize(430, 480);
		panCenterRight.setLocation(350, 120);
		userInfor.setSize(450,40);
		userInfor.setLocation(330,70);
		userInfor.setOpaque(true);
		userInfor.setBackground(new Color(255,255,255));
		
		JLabel decoLine=new JLabel("------------  포스터  ------------");
		decoLine.setFont(textFont);
		panCenterRight.add(decoLine);
		decoLine.setLocation(12,0);
		decoLine.setSize(400,30);
		decoLine.setForeground(new Color(255,255,255));
		
		JLabel listText=new JLabel("----영화 목록----");
		listText.setFont(new Font("HY엽서L", Font.BOLD,30));
		listText.setBorder(tb);
		listText.setForeground(new Color(255,255,255));
		
		foodResBtn=new JButton("음식주문");
		foodResBtn.setFont(textFont);
		resCheck =new JButton("예약확인"); //reservation check
		resCheck.setFont(textFont);
		logoutBtn =new JButton("로그아웃"); //logout
		logoutBtn.setFont(textFont);
		resCheck.addActionListener(handler);
		logoutBtn.addActionListener(handler);
		foodResBtn.addActionListener(handler);
		

		panUpperMenu.add(listText);
		listText.setLocation(15,10);
		listText.setSize(310,40);
		
		panUpperMenu.add(resCheck);
		resCheck.setLocation(510,10);
		resCheck.setSize(120,30);
		resCheck.setBackground(new Color(29, 139, 21));
		panUpperMenu.add(logoutBtn);
		logoutBtn.setLocation(650,10);
		logoutBtn.setSize(120,30);
		logoutBtn.setBackground(new Color(170, 18, 18));
		panUpperMenu.add(foodResBtn);
		foodResBtn.setLocation(370,10);
		foodResBtn.setSize(120,30);
		foodResBtn.setBackground(new Color(170, 18, 18));
		
		upperText=new JLabel();
		userInforBtn=new JButton("내정보");
		userInforBtn.setBackground(new Color(29, 139, 21));
		String myStr=Dispatcher.loginMember.getId().toString()+"님 안녕하세요.";
		upperText.setText(myStr);
		upperText.setFont(textFont);
		upperText.setForeground(new Color(29, 139, 21));
		userInfor.setBorder(tb);
		userInfor.add(upperText);
		userInfor.add(userInforBtn);
		userInforBtn.addActionListener(handler);
		
		
		
		for(int i=0; i<btnArr.length; i++) {
			ImageIcon tempIcon=new ImageIcon("imgs/movie"+i+".jpg");
			Image toChangeImage = tempIcon.getImage();
			Image changedImage = toChangeImage.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			ImageIcon changedIcon=new ImageIcon(changedImage);
			btnArr[i]=new JButton(changedIcon);
			btnArr[i].addActionListener(handler);
			btnArr[i].setSize(100,100);
			btnArr[i].setName(movieTitles[i]); //버튼구별하기위해 설정
			btnArr[i].setFocusPainted(false);
			btnArr[i].setContentAreaFilled(false);
			if(i<4) {
				btnArr[i].setLocation(40,130*i);
			}else {
				btnArr[i].setLocation(160,130*(i-4));
			}
			panCenterLeft.add(btnArr[i]);
		}
		
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		backgroundImage.add(snow);
	    snow.setBounds(0,0,800,650);
	    Thread t1 = new Thread(snow);
	    t1.start();
		
		t=new Thread(panCenterRight);
		t.start();
		
		reservationBtn=new JButton("영화예매");
		reservationBtn.setFont(new Font("HY엽서L", Font.BOLD,40));
		reservationBtn.addActionListener(handler);
		reservationBtn.setSize(400,50);
		reservationBtn.setLocation(15,420);
		reservationBtn.setForeground(new Color(170, 18, 18));
		reservationBtn.setBackground(new Color(255,255,255));
		panCenterRight.add(reservationBtn);
		
		setTitle("영화 예매 시스템");
		new Style(800, 650, this);
//		setBounds(400,100,800,650);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	ActionListener handler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton o=(JButton)e.getSource();
			
			for(JButton temp:btnArr) {
				if(o==temp) {
					String index=o.getName();
					Connection conn=MyDB.getCon();
					String sql="select * from movie where title=?";
					try {
						String title=null;
						String price=null;
						String outline=null;
						String nation=null;
						Date openDate=null;
						String director=null;
						String actor=null;
						String limitAge=null;
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, index);
						ResultSet rs=pstmt.executeQuery();
						while(rs.next()) {
							title=rs.getString("title");
							price=rs.getString("price");
							outline=rs.getString("outline");
							nation=rs.getString("nation");
							openDate=rs.getDate("opendate");
							director=rs.getString("director");
							actor=rs.getString("actor");
							limitAge=String.valueOf(rs.getInt("limitAge"));
							if(limitAge.equals("0")) {
								limitAge="전체이용가";
							}
						}
						 UIManager UI=new UIManager();
						 UI.put("OptionPane.background", new Color(13, 113, 62));
						 UI.put("Panel.background", new Color(13, 113, 62));
						 UI.put("OptionPane.messageForeground", Color.WHITE);
						 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
						 UI.put("Button.background", new Color(238, 28, 37));
						 UI.put("Button.foreground", Color.WHITE);
						 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
						JOptionPane.showMessageDialog(null,
								"제목: "+title+"\n가격: "+price+"\n개요: "+outline+"\n국가: "+nation+"\n개봉일: "+openDate+
								"\n감독: "+director+"\n출연배우: "+actor+"\n등급: "+limitAge);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			if(o==reservationBtn) { //예약하기
				Dispatcher.goTo(mv,"toReservation");
				mv.dispose();
			}else if(o==resCheck) { //예약 확인
				Dispatcher.goTo(mv, "toCheckReservation");
				mv.dispose();
			}else if(o==logoutBtn) { //로그아웃
				Dispatcher.goTo(mv, "toLogout");
				mv.dispose();
			}else if(o==foodResBtn) { //음식주문
				Dispatcher.goTo(mv,"toResFood");
				mv.dispose();
			}else if(o==userInforBtn) { //내정보
				Dispatcher.goTo(mv,"toMyInfor");
				mv.dispose();
			}
		}
	};	
}