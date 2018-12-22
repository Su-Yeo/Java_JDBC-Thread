package manageMent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import login_main_etc.MyDB;
import style.MyComboBoxRenderer;
import manageMent.SearchPanel;
import style.Style;

public class UpdateDelPan extends JPanel {
	JTextField[] texts = new JTextField[9];
	JComboBox<String> comboGrade;
	SearchPanel sPan;
	JButton butUpdate = new JButton("수정");	
	JButton butDelete = new JButton("삭제");
	JTable table;
	Management frame;
	
	public UpdateDelPan(Management frame) {
		setLayout(new BorderLayout());
		sPan = new SearchPanel(frame);
		table  = sPan.table;
		
		table.addMouseListener(tableHandler);
		JPanel addPan = new JPanel(new GridLayout(4, 1));
		add(sPan, "Center");
		add(addPan, "South");
		
		JPanel pans[] = new JPanel[4];
		
		for (int i = 0; i <pans.length; i++) {
			pans[i] = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
			addPan.add(pans[i]);
			pans[i].setBackground(new Color(13, 113, 62));
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
		pans[3].add(butUpdate); pans[3].add(butDelete);
		butUpdate.addActionListener(butHandler);
		butDelete.addActionListener(butHandler);
		butUpdate.setBackground(new Color(51, 51, 51));
		butUpdate.setForeground(Color.white);
		butDelete.setBackground(new Color(51, 51, 51));
		butDelete.setForeground(Color.white);
		
		comboGrade.setRenderer(new MyComboBoxRenderer(13));
		new Style(comboGrade, 13, Color.WHITE,new Color(170, 18, 18));
	}
	
	public void UpdateSQL(){
		Connection con = MyDB.getCon();
		String sql = "update member set name=?, password=?, phone=?, jumin=?, email=?, grade=?, buycount=? "
				+ "where id=?";
		String phone = "";
		for(int i=3; i<=5;i++) {
			phone += texts[i].getText();
		}
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  texts[1].getText());
			pstmt.setString(2,  texts[2].getText());
			pstmt.setString(3,  phone);
			pstmt.setString(4,  texts[6].getText());
			pstmt.setString(5,  texts[7].getText());
			pstmt.setString(6, comboGrade.getSelectedItem().toString());
			if(Integer.parseInt(texts[8].getText().toString())>=10) {
				comboGrade.setSelectedIndex(2);
				pstmt.setString(6, comboGrade.getSelectedItem().toString());
			}else if(Integer.parseInt(texts[8].getText().toString())>=5) {
				comboGrade.setSelectedIndex(1);
				pstmt.setString(6, comboGrade.getSelectedItem().toString());
			} 
			pstmt.setInt(7, Integer.parseInt(texts[8].getText().toString()));
			pstmt.setString(8,  texts[0].getText());
			pstmt.executeUpdate();
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public void DeleteSQL() {
		Connection con = MyDB.getCon();
		String sql = "delete from member "
				+ "where id=?";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  texts[0].getText());
			
			pstmt.executeUpdate();
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	ActionListener butHandler = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object eBut = e.getSource();
			if(eBut == butUpdate) {
				UpdateSQL();
				sPan.execSQL();
				sPan.setTable();
				init();
			}else {
				new DeleteDialog(UpdateDelPan.this, frame);
//				DeleteSQL();
			}	
		}
	};

	MouseListener tableHandler = new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				int row = table.getSelectedRow();
				texts[0].setText(table.getValueAt(row, 0).toString());
				texts[1].setText(table.getValueAt(row, 1).toString());
				texts[2].setText(table.getValueAt(row, 2).toString());
				texts[3].setText(table.getValueAt(row, 3).toString().substring(0, 3));
				texts[4].setText(table.getValueAt(row, 3).toString().substring(3, 7));
				texts[5].setText(table.getValueAt(row, 3).toString().substring(7, 11));
				texts[6].setText(table.getValueAt(row, 4).toString());
				texts[7].setText(table.getValueAt(row, 5).toString());
				comboGrade.setSelectedItem(table.getValueAt(row, 6));
				texts[8].setText(table.getValueAt(row, 7).toString());
			}
		}
	};
	
	public void init() {
		for (int i=0; i < texts.length; i++) {
			texts[i].setText("");
		}
		
		comboGrade.setSelectedIndex(0);
	}
}
