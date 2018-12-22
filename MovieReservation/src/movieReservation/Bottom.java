package movieReservation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import login_main_etc.MyDB;


public class Bottom extends JPanel {
	
	JButton btnNext = new JButton("결제 하기");
	JButton btnCancel = new JButton("←뒤로 가기");
	int sNum;
	public Bottom(String MovieName, String screenNum, String playDate,String playTime) {
//하단부분
		setBounds(0, 450-38, 800-16, 200);
		setLayout(null);
		setBackground(Color.BLACK);
		this.setBackground(new Color(13, 113, 62));
		
		ImageIcon snow_imgicon = new ImageIcon("imgs/snowman.png");
		JLabel snow_img = new JLabel(snow_imgicon,JLabel.RIGHT);
		snow_img.setBounds(425, 0, 200, 200);
		add(snow_img);
		
//결제하기 버튼
		btnNext = new JButton("결제 하기");
		btnNext.setBounds(625, 0, 157, 95);
		btnNext.setBackground(new Color(238, 28, 37));
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font("HY엽서L", Font.BOLD,19));
		add(btnNext);
		
//뒤로가기 버튼	
		btnCancel = new JButton("←뒤로 가기");
		btnCancel.setBackground(new Color(51, 51, 51));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(new Font("HY엽서L", Font.BOLD,19));
		btnCancel.setBounds(625, 105, 157, 95);
		
		add(btnCancel);
		
		JLabel imgLabel = new JLabel();
		JTextArea movieName_text = new JTextArea();
		String[] lblStrs = {"상영관","날짜","시간",""};
		JLabel[] lbls;
		JLabel[] texts = new JLabel[3];
	
		
//영화이미지추가부분
		setsNum(MovieName);
		String path = "imgs/movie"+(sNum-1)+".jpg";
		imgLabel.setText("");
		ImageIcon image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
		imgLabel.setIcon(image);
		imgLabel.setBounds(0, 0, 150, 200);
		add(imgLabel);
		
//영화이름 추가부분
		movieName_text.setText(MovieName);
		movieName_text.setBounds(160, 10, 160, 200);
		movieName_text.setBackground(new Color(13, 113, 62));
		movieName_text.setForeground(Color.WHITE);
		movieName_text.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		movieName_text.setLineWrap(true);
		add(movieName_text);
		

//상영관,날짜,시간 추가부분
		lbls = new JLabel[lblStrs.length];
		
		for (int i = 0; i < lblStrs.length; i++) {
			lbls[i] = new JLabel(lblStrs[i]);
			lbls[i].setOpaque(true);
			lbls[i].setBackground(new Color(13, 113, 62));
			lbls[i].setForeground(Color.WHITE);
			lbls[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
			lbls[i].setVisible(true);
		}
		lbls[3].setFont(new Font("맑은 고딕", Font.BOLD, 17));
		lbls[3].setVisible(true);
		
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JLabel();
			texts[i].setOpaque(true);
			texts[i].setBackground(new Color(13, 113, 62));
			texts[i].setForeground(Color.WHITE);
			texts[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
		}
		texts[0].setText(screenNum);
		texts[1].setText(playDate);
		texts[2].setText(playTime);
		JPanel pan4 = new JPanel();
		pan4.setBackground(new Color(13, 113, 62));
		pan4.setLayout(new GridLayout(3, 1, 0, 45));
		pan4.setBounds(320,0,150,200);
		add(pan4);
		
		JPanel jp[] = new JPanel[3];
		for (int i = 0; i < jp.length; i++) {
			jp[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			jp[i].setBackground(new Color(13, 113, 62));
			pan4.add(jp[i]);
		}
		jp[0].add(lbls[0]); jp[0].add(texts[0]);
		jp[1].add(lbls[1]); jp[1].add(texts[1]);
		jp[2].add(lbls[2]); jp[2].add(texts[2]);
		
		lbls[3].setBounds(470, 0, 200, 200);
		add(lbls[3]);
	}
	
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
}
