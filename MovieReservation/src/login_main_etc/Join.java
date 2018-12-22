package login_main_etc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.LetItSnow;
import style.Style;

public class Join extends JFrame {
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
	JPasswordField textPW = new JPasswordField();
	JComboBox comboYear = new JComboBox<>();
	JComboBox comboMonth = new JComboBox<>();
	JComboBox comboDay = new JComboBox<>();
	
	String telName[] = {"010","011","016","017","018","019"};
	JComboBox<String> comboTel1 = new JComboBox<String>(telName);
	JTextField textTel2 = new JTextField();
	JTextField textTel3 = new JTextField();
	
	JTextField textEmail1 = new JTextField();
	JTextField textEmail2 = new JTextField();
	
	JButton btnOk = new JButton("회원가입");
	JButton btnCancel = new JButton("취소");
	JButton idOverLap = new JButton("중복확인");
	
    JScrollPane scrollPane;
    ImageIcon icon;
	
	Join me;

	public Join() {
		setLayout(null);
		this.me=this;
		
		//배경이미지
		icon = new ImageIcon("imgs/1.jpg");
				
		JPanel background = new JPanel(null) {
		public void paintComponent(Graphics g) {

			g.drawImage(icon.getImage(), 0, 0, null);

		    setOpaque(false); //그림을 표시하게 설정,투명하게 조절
		    super.paintComponent(g);
		   }
		};
		        
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		
		//기본정보
		lblInfo.setBounds(50,0,180,130);
		lblInfo.setFont(new Font("SansSerif", Font.BOLD, 40));
		background.add(lblInfo);
		
		//이름
		lblName.setBounds(50,100,80,50);
		lblName.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblName);
		
		//아이디
		lblID.setBounds(50,170,80,50);
		lblID.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblID);
		
		//비밀번호
		lblPW.setBounds(50,240, 80,50);
		lblPW.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblPW);
		
		//생년월일
		lblBirth.setBounds(50,310, 80,50);
		lblBirth.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblBirth);
		
		//핸드폰
		lblTel.setBounds(50,380, 80,50);
		lblTel.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblTel);
		
		//이메일
		lblEmail.setBounds(50,450, 80,50);
		lblEmail.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblEmail);
		//----------------------------------------------------------------------
		
		//이름(텍스트)
		textName.setBounds(200,110,200,35);
		background.add(textName);
		
		//아이디(텍스트)
		textID.setBounds(200,180,200,35);
		background.add(textID);
		
		//비밀번호(텍스트)
		textPW.setBounds(200,255,200,35);
		background.add(textPW);
		
		//생년월일-년(콤보박스)
		comboYear.setFont(new Font("HY엽서L", Font.PLAIN, 16));
		comboYear.addItem("년");
		for(int i = 1960; i < 2019; i++)
		{
			comboYear.addItem(i);
		}
		comboYear.setBounds(200,320,80,30);
		background.add(comboYear);
		lblYear.setBounds(285,315,40,40);
		lblYear.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblYear);
		
		//생년월일-월(콤보박스)
		comboMonth.setFont(new Font("HY엽서L", Font.PLAIN, 16));
		comboMonth.addItem("월");
		for(int i = 1; i < 13; i++)
		{
			comboMonth.addItem(i);
		}
		comboMonth.setBounds(500,550,130,50);
		background.add(comboMonth);
		
		comboMonth.setBounds(315,320,80,32);
		background.add(comboMonth);
		lblMonth.setBounds(400,315,40,40);
		lblMonth.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblMonth);
		
		//생년월일-일(콤보박스)
		comboDay.setFont(new Font("HY엽서L", Font.PLAIN, 16));
		comboDay.addItem("일");
		for(int i = 1; i < 32; i++)
		{
			comboDay.addItem(i);
		}
		comboDay.setBounds(500,550,130,50);
		background.add(comboDay);
		
		comboDay.setBounds(440,320,80,32);
		background.add(comboDay);
		lblDay.setBounds(525,315,40,40);
		lblDay.setFont(new Font("HY엽서L", Font.PLAIN, 20));
		background.add(lblDay);
		
		//전화번호(콤보박스)
		comboTel1.setFont(new Font("HY엽서L", Font.PLAIN, 16));
		comboTel1.setBounds(200,390,80,30);
		background.add(comboTel1);
		lblTel1.setBounds(290,385,40,40);
		background.add(lblTel1);
		textTel2.setBounds(300,390,100,35);
		background.add(textTel2);
		lblTel2.setBounds(410,385,40,40);
		background.add(lblTel2);
		textTel3.setBounds(420,390,100,35);
		background.add(textTel3);
		
		//이메일(텍스트&라벨)lblEmail2
		textEmail1.setBounds(200,460,200,35);
		background.add(textEmail1);
		lblEmail2.setBounds(405,455,40,40);
		lblEmail2.setFont(new Font("HY엽서L", Font.BOLD, 20));
		background.add(lblEmail2);
		textEmail2.setBounds(430,460,200,35);
		background.add(textEmail2);
		
		//회원가입(버튼)
		btnOk.setBounds(230,520,130,50);
		btnOk.setFont(new Font("HY엽서L", Font.BOLD, 20));
		btnOk.setBackground(new Color(13,113,62));
		btnOk.setForeground(new Color(255,255,255));
		background.add(btnOk);
		
		//취소(버튼)
		btnCancel.setBounds(390,520,130,50);
		btnCancel.setFont(new Font("HY엽서L", Font.BOLD, 20));
		btnCancel.setBackground(new Color(51,51,51));
		btnCancel.setForeground(new Color(255,255,255));
		background.add(btnCancel);
		
		//아이디 중복(버튼)
		idOverLap.setBounds(410,183,100,30);
		idOverLap.setFont(new Font("HY엽서L", Font.BOLD, 15));
		idOverLap.setBackground(new Color(73,76,81));
		idOverLap.setForeground(new Color(255,255,255));
		background.add(idOverLap);
		
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		background.add(snow);
	    snow.setBounds(0,0,800,650);
	    Thread t1 = new Thread(snow);
	    t1.start();
		
		setTitle("영화 예매 시스템");
		new Style(800, 650, this);
//		setBounds(100, 100, 800, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		idOverLap.addActionListener(OverLap);	//중복확인
		btnOk.addActionListener(Join);
		btnCancel.addActionListener(Cancel);
	}
	
	boolean flag = false;
	ActionListener OverLap = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Connection con = MyDB.getCon();
			
			String ID = textID.getText();
			if(ID.equals(""))
			{
				JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				String sql = "select count(id) from member where id = ?";
				
				try {
					PreparedStatement pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, textID.getText());
					
					ResultSet rs = pstmt.executeQuery();
					
					rs.next();
					String getID = rs.getString(1);
					
					if(getID.equals("1"))
					{
						JOptionPane.showMessageDialog(null, "중복된 ID가 존재합니다.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{	
						JOptionPane.showMessageDialog(null, "해당 ID사용이 가능합니다.", "안내 메시지<성공>", JOptionPane.PLAIN_MESSAGE);
					}
					
					flag = true;
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	};
	
	ActionListener Join = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String Year = comboYear.getSelectedItem().toString();
			String Month = comboMonth.getSelectedItem().toString();
			String Day = comboDay.getSelectedItem().toString();
			String getName = textName.getText();
			String getPW = textPW.getText();
			String getTel2 = textTel2.getText();
			String getTel3 = textTel3.getText();
			String getEmail1 = textEmail1.getText();
			String getEmail2 = textEmail2.getText();

			//ID중복확인 버튼 미선택 시,
			if(flag == true)
			{
				if(getName.equals(""))
				{
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
				}
				else
				{
					if(getPW.equals(""))
					{
						JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						if(Year.equals("년"))
						{
							JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해주세요 - 년도", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
						}
						else
						{
							if(Month.equals("월"))
							{
								JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해주세요 - 월", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
							}
							else
							{
								if(Day.equals("일"))
								{
									JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해주세요 - 일", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
								}
								else
								{
									if(getTel2.equals("") || getTel3.equals(""))
									{
										JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
									}
									else
									{
										if(getEmail1.equals("") || getEmail2.equals(""))
										{
											JOptionPane.showMessageDialog(null, "이메일를 입력해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
										}
										else
										{
											JOptionPane.showMessageDialog(null, "회원가입을 축하드립니다.", "안내 메시지<성공>", JOptionPane.PLAIN_MESSAGE);
											InsertSQL();
											me.dispose();
											Dispatcher.goTo(me);
										}
									}
								}
							}
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "ID중복확인을 해주세요.", "안내 메시지<실패>", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	};
	
	ActionListener Cancel = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dispose();
			Dispatcher.goTo(me);
		}
	};
	
	public void InsertSQL() {
		Connection con = MyDB.getCon();
		
		//주민등록번호(6자리 초기화)
		String Year = comboYear.getSelectedItem().toString();	//콤보박스값 받아오기
		String Month = comboMonth.getSelectedItem().toString();
		String Day = comboDay.getSelectedItem().toString();
		String tempMonth = "0";
		String tempDay = "0";
		if(Month.length() == 1) {
			tempMonth += Month;
		}
		else
		{
			tempMonth = Month;
		}
		if(Day.length() == 1) {
			tempDay += Day;
		}
		else
		{
			tempDay = Day;
		}
		String jumin = Year.substring(2) + tempMonth + tempDay;		//주민등록번호 6자리 
		
		//전화번호
		String tel1 = comboTel1.getSelectedItem().toString();
		String phone = tel1 + textTel2.getText() + textTel3.getText();
		
		//이메일
		String email = textEmail1.getText() + "@" + textEmail2.getText();
		
		//ID , NAME, PASSWORD, PHONE, JUMIN, EMAIL, 등급, 예매횟수
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, textID.getText());
			pstmt.setString(2, textName.getText());
			pstmt.setString(3, textPW.getText());
			pstmt.setString(4, phone);
			pstmt.setString(5, jumin);
			pstmt.setString(6, email);
			pstmt.setString(7, "브론즈");
			pstmt.setInt(8, 0);
			
			pstmt.executeUpdate();
			
			if(con != null)
				con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
