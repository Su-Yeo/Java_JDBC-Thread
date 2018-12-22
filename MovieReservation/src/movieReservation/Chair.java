package movieReservation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.LetItSnow;
import style.Style;
import style.Top;



public class Chair extends JFrame {
	
	JButton btn[] = new JButton[50];
	String id, MovieName, screenNum, playTime, playDate;
	int btnsize = btn.length;
	int sNum, seatNum;
	int count = 0;
	
	Chair c;
	Color default_color;
	
	public Chair(String MovieName, String screenNum, String playDate,String playTime) {
		new Style(800, 650, this);
//		setBounds(150, 150, 800, 650);
		setLayout(null);
		setTitle("영화 예매 시스템");
		c = this;
		setsNum(MovieName);
		this.MovieName = MovieName;
		this.screenNum = screenNum;
		this.playTime = playTime;
		this.playDate = playDate;
		
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		add(snow);
		snow.setBounds(0,0,800,650);
		Thread t1 = new Thread(snow);
		t1.start();
		
		JPanel pan1 = new JPanel();
		pan1.setBounds(0, 0, this.getWidth(), this.getHeight()-200-38);
		pan1.setLayout(null);
		pan1.setBackground(Color.WHITE);
		add(pan1);
		
		Top top_pan = new Top();
		pan1.add(top_pan);
		
		JPanel screen_pan = new JPanel(new FlowLayout(FlowLayout.CENTER,4,0));
		screen_pan.setBackground(new Color(13, 113, 62));
		
		JLabel lbl1 = new JLabel("screen");
		screen_pan.setBounds(250,40,300,40);
		lbl1.setOpaque(true);
		lbl1.setHorizontalAlignment(NORMAL);
		lbl1.setFont(new Font("HY엽서L", 0, 30));
		lbl1.setBackground(new Color(13, 113, 62));
		lbl1.setForeground(Color.WHITE);
		screen_pan.add(lbl1);
		pan1.add(screen_pan);
		
		
		JPanel pan3 = new JPanel();
		pan3.setBackground(Color.WHITE);
		pan3.setLayout(null);
		int pan3_x = (pan1.getX()+pan1.getWidth())/2-300;
		int pan3_y = (pan1.getY()+pan1.getHeight())/2-115;
		pan3.setBounds(pan3_x,pan3_y,600,300);
		pan3.setLayout(new GridLayout(5, 10,3,10));
		pan1.add(pan3);
		
		
		for(int i =0;i<btnsize;i++) {
			btn[i] = new JButton(String.valueOf(i+1));
			pan3.add(btn[i]);
			btn[i].addActionListener(setSeatNum);
			btn[i].setBackground(Color.WHITE);
			btn[i].setForeground(Color.BLACK);
			btn[i].setFont(new Font("HY엽서L", Font.BOLD,15) );
		}
		default_color=btn[0].getBackground();
		
		
		
		Bottom bottom_pan = new Bottom(MovieName, screenNum, playDate, playTime);
		bottom_pan.btnNext.addActionListener(reserve);
		bottom_pan.btnNext.setText("예약하기");
		bottom_pan.btnCancel.addActionListener(cancel);
		add(bottom_pan);
		
		disableSeat();
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	ActionListener cancel = new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Dispatcher.goTo("SelectSchedule"); 
			c.dispose();
		}
	};
	
	
	ActionListener reserve = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent act) {
			
			if(count ==0) {
				 UIManager UI=new UIManager();
				 UI.put("OptionPane.background", new Color(13, 113, 62));
				 UI.put("Panel.background", new Color(13, 113, 62));
				 UI.put("OptionPane.messageForeground", Color.WHITE);
				 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
				 UI.put("Button.background", new Color(238, 28, 37));
				 UI.put("Button.foreground", Color.WHITE);
				 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
				JOptionPane.showMessageDialog(null, "좌석을 선택해주세요", "경고", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			int reserveSeatNum[] = new int[50];
			for(int i = 0; i<50;i++) {
				if(btn[i].getBackground().equals(new Color(238, 28, 37))) {
					reserveSeatNum[i]= i+1;
				}else {
					reserveSeatNum[i]= 0;
				}
			}
			new MoviePayment(MovieName, screenNum,playTime, playDate, reserveSeatNum ,sNum, count);
			c.dispose();
		}
	};
	
	
	ActionListener setSeatNum = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent act) {
			Object oBtn = act.getSource();
			
			for (int i = 0; i<btnsize; i++) {
				if (btn[i] == oBtn) {
					seatNum = Integer.parseInt(btn[i].getText());
					if(btn[i].getBackground().equals(new Color(238, 28, 37))) {
						btn[i].setBackground(default_color);
						btn[i].setForeground(Color.black);
						count = count -1;
					}else {
						if(count >=5) {
							 UIManager UI=new UIManager();
							 UI.put("OptionPane.background", new Color(13, 113, 62));
							 UI.put("Panel.background", new Color(13, 113, 62));
							 UI.put("OptionPane.messageForeground", Color.WHITE);
							 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
							 UI.put("Button.background", new Color(238, 28, 37));
							 UI.put("Button.foreground", Color.WHITE);
							 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
							JOptionPane.showMessageDialog(null, "한번에 5자리까지만 예약가능합니다.", "경고", JOptionPane.PLAIN_MESSAGE);
							break;
						}
						btn[i].setBackground(new Color(238, 28, 37));
						btn[i].setForeground(Color.white);
						count = count +1;
					}
				}
			}
		}
	};
		
		
	public void setsNum(String MovieName) {
		Connection con = MyDB.getCon();
		
		String sql = "select sNum from Movie where title=?";
		try{
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, MovieName); //영화이름
			
			ResultSet rs = pt.executeQuery();
			
			rs.next();
			sNum = rs.getInt(1);
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
		
	
	public void disableSeat(){
		Connection con = MyDB.getCon();
		
		String sql = "select seatNum from reservation where sNum=? and screenNum=? and playtime=? and playdate=?";
	
		try{
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setInt(1,sNum); // movienum
			pt.setString(2, screenNum); //상영관
			pt.setString(3, playTime); //시간
			pt.setString(4, playDate); //날짜
			
			ResultSet rs = pt.executeQuery();
			
			while(rs.next()) {
				btn[rs.getInt(1)].setEnabled(false);
				btn[rs.getInt(1)].setBackground(new Color(51, 51, 51));
				btn[rs.getInt(1)].setForeground(Color.white);
			}
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
