package manageMent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import login_main_etc.MyDB;
import style.MyComboBoxRenderer;
import manageMent.SearchPanel;
import style.Style;

public class InsertPanel extends JPanel {
	JTextField[] texts = new JTextField[9];
	JComboBox<String> comboGrade;
	SearchPanel sPan;
	
	public InsertPanel(Management frame) {
		setLayout(new BorderLayout());
		JPanel addPan = new JPanel(new GridLayout(4, 1));
		sPan = new SearchPanel(frame);
		add(sPan, "Center");
		add(addPan, "South");

		JPanel pans[] = new JPanel[4];
		for (int i = 0; i <pans.length; i++) {
			pans[i] = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
			pans[i].setBackground(new Color(13, 113, 62));
			addPan.add(pans[i]);
		}
		
		
		String[] lblStrs = {"아이디", "이름", "비밀번호", "핸드폰", "-", "-", "생년월일", "이메일", "회원등급", "구매횟수"};
		JLabel[] lbls = new JLabel[lblStrs.length];
		
		for (int i = 0; i<lblStrs.length ;i++) {
			lbls[i] = new JLabel(lblStrs[i]);
			lbls[i].setFont(new Font("HY엽서L",Font.BOLD,20));
			lbls[i].setOpaque(true);
			lbls[i].setPreferredSize(new Dimension(90,20));
			lbls[i].setHorizontalAlignment(JLabel.RIGHT);
			if (lblStrs[i].equals("-")) {
				lbls[i].setPreferredSize(new Dimension(20,20));
				lbls[i].setHorizontalAlignment(JLabel.CENTER);
			}
			
			lbls[i].setForeground(Color.WHITE);
			lbls[i].setBackground(new Color(13, 113, 62));
		}
		
		for (int i = 0; i<texts.length ;i++) {
			if(i>=3 && i<=5) {
				texts[i] = new JTextField();
				texts[i].setPreferredSize(new Dimension(43,20));
				continue;
			}
			texts[i] = new JTextField(15);
		}
		
		
		String[] grade = {"브론즈","실버","골드","관리자"};
		
		comboGrade = new JComboBox<String>(grade);
		comboGrade.setPreferredSize(new Dimension(167,20));
		
		JButton butAdd = new JButton("회원추가");
		butAdd.setBackground(new Color(51, 51, 51));
		butAdd.setForeground(Color.white);
		butAdd.addActionListener(butHandler);
		
		pans[0].add(lbls[0]);pans[0].add(texts[0]);
		pans[0].add(lbls[1]);pans[0].add(texts[1]);
		
		pans[1].add(lbls[2]);pans[1].add(texts[2]);

		JPanel phone_pan = new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
		
		pans[1].add(lbls[3]);phone_pan.add(texts[3]);
		pans[1].add(phone_pan);
		phone_pan.add(lbls[4]);phone_pan.add(texts[4]);
		phone_pan.add(lbls[5]);phone_pan.add(texts[5]);
		
		
		pans[2].add(lbls[6]);pans[2].add(texts[6]);
		pans[2].add(lbls[7]);pans[2].add(texts[7]);
		
		pans[3].add(lbls[8]);pans[3].add(comboGrade);
		pans[3].add(lbls[9]);pans[3].add(texts[8]);
		pans[3].add(butAdd);
		//book테이블에 데이터 행 삽입하는 부분 부터 해야함
		
		comboGrade.setRenderer(new MyComboBoxRenderer(13));
		new Style(comboGrade, 13, Color.WHITE,new Color(170, 18, 18));
	}
	
	public void insertSQL(){
		Connection con = MyDB.getCon();
		String sql = "insert into member values(?,?,?,?,?,?,?,?)";
		String phone = "";
		for(int i=3; i<=5;i++) {
			phone += texts[i].getText();
		}
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  texts[0].getText().toString());
			pstmt.setString(2,  texts[1].getText().toString());
			pstmt.setString(3,  texts[2].getText().toString());
			pstmt.setString(4,  phone);
			pstmt.setString(5,  texts[6].getText().toString());
			pstmt.setString(6,  texts[7].getText().toString());
			pstmt.setString(7, comboGrade.getSelectedItem().toString());
			pstmt.setInt(8, Integer.parseInt(texts[8].getText().toString()));
			pstmt.executeUpdate();
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}finally {
			System.out.println(texts[0].getText());
		}
	}
	
	
	public int duplicateCheckSQL() {
		Connection con = MyDB.getCon();
		String sql = "SELECT count(*) FROM member where id = '"+ texts[0].getText()+ "'";
		int count = 1;
		try{
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			rs.next();
			count = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	ActionListener butHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int count = duplicateCheckSQL();
			if(count >= 1) {
				 UIManager UI=new UIManager();
				 UI.put("OptionPane.background", new Color(13, 113, 62));
				 UI.put("Panel.background", new Color(13, 113, 62));
				 UI.put("OptionPane.messageForeground", Color.WHITE);
				 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
				 UI.put("Button.background", new Color(238, 28, 37));
				 UI.put("Button.foreground", Color.WHITE);
				 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
				JOptionPane.showMessageDialog(null, "이미 생성된 아이디입니다.", "경고", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			insertSQL();
			sPan.execSQL();
			sPan.setTable();
		}
	};
}
