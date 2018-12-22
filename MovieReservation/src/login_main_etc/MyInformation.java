package login_main_etc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import style.LetItSnow;
import style.Style;

public class MyInformation extends JFrame implements ActionListener{
	
	JLabel lblInfo = new JLabel("기본정보");
	JLabel lblName = new JLabel("이름");
	JLabel lblID = new JLabel("아이디");
	JLabel lblPW = new JLabel("비밀번호");
	JLabel lblBirth = new JLabel("생년월일");
	JLabel lblTel = new JLabel("전화번호");
	JLabel lblEmail = new JLabel("이메일");
	JLabel lblYear = new JLabel("년");
	JLabel lblMonth = new JLabel("월");
	JLabel lblDay = new JLabel("일");
	JLabel lblTel1 = new JLabel("-");
	JLabel lblTel2 = new JLabel("-");
	JLabel lblEmail2 = new JLabel("@");
	
	JTextField textName = new JTextField();
	JTextField textID = new JTextField();
	JTextField textPW = new JTextField();
	JComboBox comboYear = new JComboBox<>();
	JComboBox comboMonth = new JComboBox<>();
	JComboBox comboDay = new JComboBox<>();
	
	String telName[] = {"010","011","016","017","018","019"};
	JComboBox<String> comboTel1 = new JComboBox<String>(telName);
	JTextField textTel2 = new JTextField();
	JTextField textTel3 = new JTextField();
	
	JTextField textEmail1 = new JTextField();
	JTextField textEmail2 = new JTextField();
	
	JButton modifyBtn = new JButton("정보 수정");
	JButton withdrawalBtn = new JButton("회원 탈퇴");
	JButton backBtn=new JButton("뒤로가기");
	
	Connection conn;
	PreparedStatement pstmt;
	
	MyInformation me;
	
	public MyInformation() {
		
		conn=MyDB.getCon();
		String sql="select * from member where id=?";
		pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Dispatcher.loginMember.getId());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Dispatcher.loginMember.setId(rs.getString("id"));
				Dispatcher.loginMember.setName(rs.getString("name"));
				Dispatcher.loginMember.setPassword(rs.getString("password"));
				Dispatcher.loginMember.setPhone(rs.getString("phone"));
				Dispatcher.loginMember.setJumin(rs.getString("jumin"));
				Dispatcher.loginMember.setEmail(rs.getString("email"));
				Dispatcher.loginMember.setGrade(rs.getString("grade"));
				Dispatcher.loginMember.setBuyCount(rs.getInt("buycount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ImageIcon tempIcon2=new ImageIcon("imgs/mainback.jpg");
		Image toChangeImage2 = tempIcon2.getImage();
		Image changedImage2 = toChangeImage2.getScaledInstance(800, 650, java.awt.Image.SCALE_SMOOTH);
		ImageIcon changedIcon2=new ImageIcon(changedImage2);
		JLabel backgroundImage=new JLabel(changedIcon2);
		add(backgroundImage);
		
		backgroundImage.setLayout(null);
		backgroundImage.setLocation(0,0);
		backgroundImage.setSize(800,650);

		this.me=this;
		//기본정보
		lblInfo.setBounds(50,10,300,100);
		String myId=Dispatcher.loginMember.getId();
		lblInfo.setText(myId+"님의 기본 정보");
		lblInfo.setFont(new Font("HY엽서L", Font.BOLD, 25));
		lblInfo.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblInfo);
		
		JLabel decoLine=new JLabel("----------------------------------------");
		decoLine.setSize(650,30);
		decoLine.setLocation(50, 80);
		decoLine.setFont(new Font("HY엽서L",Font.BOLD,25));
		decoLine.setForeground(new Color(170,18,18));
		backgroundImage.add(decoLine);
		
		//이름
		lblName.setBounds(50,100,80,50);
		lblName.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblName.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblName);
		
		JLabel lblGrade = new JLabel("등 급: "+Dispatcher.loginMember.getGrade()); 
		lblGrade.setBounds(500, 100, 140, 50);
		lblGrade.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblGrade.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblGrade);
		
		//아이디
		lblID.setBounds(50,170,80,50);
		lblID.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblID.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblID);
		
		JLabel lblBuyCount = new JLabel("구매 횟수: " + Dispatcher.loginMember.getBuyCount());
		lblBuyCount.setBounds(500, 170, 140, 50);
		lblBuyCount.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblBuyCount.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblBuyCount);
		
		
		//비밀번호
		lblPW.setBounds(50,240, 80,50);
		lblPW.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblPW.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblPW);
		
		//생년월일
		lblBirth.setBounds(50,310, 80,50);
		lblBirth.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblBirth.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblBirth);
		
		//핸드폰
		lblTel.setBounds(50,380, 80,50);
		lblTel.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblTel.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblTel);
		
		//이메일
		lblEmail.setBounds(50,450, 80,50);
		lblEmail.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblEmail.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblEmail);
		//----------------------------------------------------------------------
		
		//이름(텍스트)
		textName.setBounds(200,110,200,35);
		textName.setText(Dispatcher.loginMember.getName().toString());
		textName.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textName.setForeground(new Color(29, 139, 21));
		textName.setEditable(true);
		backgroundImage.add(textName);
		
		
		//아이디(텍스트)
		textID.setBounds(200,180,200,35);
		textID.setText(Dispatcher.loginMember.getId().toString());
		textID.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textID.setForeground(new Color(29, 139, 21));
		textID.setEditable(false);
		backgroundImage.add(textID);
		
		//비밀번호(텍스트)
		textPW.setBounds(200,255,200,35);
		textPW.setText(Dispatcher.loginMember.getPassword().toString());
		textPW.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textPW.setForeground(new Color(29, 139, 21));
		textPW.setEditable(true);
		backgroundImage.add(textPW);
		
		//생년월일-년(콤보박스)
		String jumin=Dispatcher.loginMember.getJumin();
		String yearBirth=jumin.substring(0, 2);
		String tempBirth="19"+yearBirth;
		comboYear.setEnabled(false);
		for(int i = 1960; i < 2010; i++) //1960=0
		{
			comboYear.addItem(i);
		}
		comboYear.setSelectedIndex(Integer.parseInt(tempBirth)-1960);
		comboYear.setBounds(200,320,80,30);
		comboYear.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		comboYear.setForeground(new Color(29, 139, 21));
		backgroundImage.add(comboYear);
		lblYear.setBounds(285,315,40,40);
		lblYear.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblYear.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblYear);
		
		//생년월일-월(콤보박스)
		String monthBirth=jumin.substring(2, 4);
		String temp1=monthBirth.substring(0,1);
		comboMonth.setEnabled(false);
		for(int i = 1; i < 13; i++)
		{
			comboMonth.addItem(i);
		}
		if(temp1.equals("0")) {
			temp1=monthBirth.substring(1);
		}
		comboMonth.setSelectedIndex(Integer.parseInt(temp1)-1);
		comboMonth.setBounds(500,550,130,50);
		comboMonth.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		comboMonth.setForeground(new Color(29, 139, 21));
		backgroundImage.add(comboMonth);
		
		comboMonth.setBounds(315,320,80,32);
		backgroundImage.add(comboMonth);
		lblMonth.setBounds(400,315,40,40);
		lblMonth.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblMonth.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblMonth);
		
		//생년월일-일(콤보박스)
		String dayBirth=jumin.substring(4, 6);
		String temp=dayBirth.substring(0,1);
		comboDay.setEnabled(false);
		for(int i = 1; i < 32; i++)
		{
			comboDay.addItem(i);
		}
		if(temp.equals("0")) {
			temp=dayBirth.substring(1);
		}
		comboDay.setSelectedIndex(Integer.parseInt(temp)-1);
		comboDay.setBounds(440,320,80,32);
		comboDay.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		comboDay.setForeground(new Color(29, 139, 21));
		backgroundImage.add(comboDay);
		lblDay.setBounds(525,315,40,40);
		lblDay.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblDay.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblDay);
		
		String phone=Dispatcher.loginMember.getPhone();
		String phone1=phone.substring(0, 3);
		String phone2=phone.substring(3, 7);
		String phone3=phone.substring(7, 11);
		comboTel1.setBounds(200,390,80,30); 
		backgroundImage.add(comboTel1);
		int phIdx=0;
		if(phone1.equals("011")) {
			phIdx=1;
		}else if(phone1.equals("016")) {
			phIdx=2;
		}else if(phone1.equals("017")) {
			phIdx=3;
		}else if(phone1.equals("018")) {
			phIdx=4;
		}else if(phone1.equals("019")) {
			phIdx=5;
		}
		comboTel1.setSelectedIndex(phIdx);
		comboTel1.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		comboTel1.setForeground(new Color(29, 139, 21));
		lblTel1.setBounds(290,385,40,40);
		lblTel1.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblTel1.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblTel1);
		
		textTel2.setBounds(300,390,100,35); 
		backgroundImage.add(textTel2);
		textTel2.setText(phone2);
		textTel2.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textTel2.setForeground(new Color(29, 139, 21));
		lblTel2.setBounds(410,385,40,40);
		lblTel2.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		lblTel2.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblTel2);
		textTel3.setBounds(420,390,100,35);
		textTel3.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textTel3.setForeground(new Color(29, 139, 21));
		backgroundImage.add(textTel3);
		textTel3.setText(phone3);
	
		StringTokenizer st=new StringTokenizer(Dispatcher.loginMember.getEmail(), "@");
		textEmail1.setBounds(200,460,200,35);
		backgroundImage.add(textEmail1);
		textEmail1.setText(st.nextToken().toString());
		textEmail1.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textEmail1.setForeground(new Color(29, 139, 21));
		lblEmail2.setBounds(405,455,40,40);
		lblEmail2.setFont(new Font("HY엽서L", Font.BOLD, 20));
		lblEmail2.setForeground(new Color(170, 18, 18));
		backgroundImage.add(lblEmail2);
		textEmail2.setBounds(430,460,200,35);
		textEmail2.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		textEmail2.setForeground(new Color(29, 139, 21));
		backgroundImage.add(textEmail2);
		textEmail2.setText(st.nextToken().toString());
		
		backBtn.setBounds(30, 540, 120, 30);
		backBtn.setBackground(new Color(51,51,51));
		backBtn.setForeground(new Color(255,255,255));
		backgroundImage.add(backBtn);
		
		modifyBtn.setBounds(270,520,130,50);
		modifyBtn.setBackground(new Color(29, 139, 21));
		backgroundImage.add(modifyBtn);
		
		withdrawalBtn.setBounds(430,520,130,50);
		withdrawalBtn.setBackground(new Color(170, 18, 18));
		backgroundImage.add(withdrawalBtn);

		backBtn.addActionListener(this);
		modifyBtn.addActionListener(this);
		withdrawalBtn.addActionListener(this);
		
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		backgroundImage.add(snow);
	    snow.setBounds(0,0,800,650);
	    Thread t1 = new Thread(snow);
	    t1.start();
		
		setTitle("영화 예매 시스템");
		new Style(800, 650, this);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backBtn) {
			this.dispose();
			Dispatcher.goTo(this);
		}else if(e.getSource()==modifyBtn) {
			 UIManager UI=new UIManager();
			 UI.put("OptionPane.background", new Color(13, 113, 62));
			 UI.put("Panel.background", new Color(13, 113, 62));
			 UI.put("OptionPane.messageForeground", Color.WHITE);
			 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
			 UI.put("Button.background", new Color(238, 28, 37));
			 UI.put("Button.foreground", Color.WHITE);
			 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
			int result=JOptionPane.showConfirmDialog(null, "개인 정보를 수정하시겠습니까?", "정보 수정", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.CLOSED_OPTION) {
				
			}else if(result==JOptionPane.YES_OPTION) {
				conn=MyDB.getCon();
				String sql="update member set name=?, password=?, phone=?, email=? where id=?";
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, textName.getText());
					pstmt.setString(2, textPW.getText());
					String tel=comboTel1.getSelectedItem().toString();
					tel+=textTel2.getText().toString();
					tel+=textTel3.getText().toString();
					pstmt.setString(3,tel);
					String email=textEmail1.getText();
					email+="@";
					email+=textEmail2.getText();
					pstmt.setString(4, email);
					pstmt.setString(5, Dispatcher.loginMember.getId());
					pstmt.executeUpdate();
					if(pstmt!=null) {
						pstmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
					Dispatcher.loginMember.setName(textName.getText());
					Dispatcher.loginMember.setPassword(textPW.getText());
					Dispatcher.loginMember.setPhone(tel);
					Dispatcher.loginMember.setEmail(email);
					JOptionPane.showMessageDialog(null, "정보가 수정되었습니다.", "수정 완료", JOptionPane.PLAIN_MESSAGE);
					Dispatcher.goTo(this);
					this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}else {
				
			}
		}else if(e.getSource()==withdrawalBtn) {
			 UIManager UI=new UIManager();
			 UI.put("OptionPane.background", new Color(13, 113, 62));
			 UI.put("Panel.background", new Color(13, 113, 62));
			 UI.put("OptionPane.messageForeground", Color.WHITE);
			 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
			 UI.put("Button.background", new Color(238, 28, 37));
			 UI.put("Button.foreground", Color.WHITE);
			 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
			int result=JOptionPane.showConfirmDialog(null, "회원탈퇴를 하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				conn=MyDB.getCon();
				String sql="delete from member where id=?";
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, Dispatcher.loginMember.getId());
					pstmt.executeUpdate();
					if(pstmt!=null) {
						pstmt.close();
					}
					if(conn!=null) {
						conn.close();
					}
					JOptionPane.showMessageDialog(null, "회원 탈퇴되셨습니다.", "회원탈퇴 완료", JOptionPane.PLAIN_MESSAGE);
					Dispatcher.goTo(this, "toLoginForm");
					this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}