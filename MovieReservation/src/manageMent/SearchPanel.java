package manageMent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.MyComboBoxRenderer;
import style.Style;
import style.Top;

public class SearchPanel extends JPanel {
	JTextField textSearch;
	JComboBox<String> comboSearch;
	String[] comboStrs = {"아이디", "이름", "비밀번호","핸드폰", "생년월일", "이메일", "등급", "구매횟수"}; //2018-12-09 22:41 최우중 수정  등급, 구매횟수 추가
	String[] colNames = {"id","name","password","phone","jumin","email", "grade", "buycount"};
	String colName="name";
	DefaultTableModel model;
	public JTable table;
	String[] cols = {"아이디", "이름", "비밀번호", "핸드폰", "생년월일", "이메일", "등급", "구매횟수"};
	String[] id, name, password, phone, jumin, email, grade, buycount;
	String searchWord="";
	JButton btnSearch,back;
	Management frame;
	
	public SearchPanel(Management frame) {
		this.frame=frame;
		setLayout(new BorderLayout());
		JPanel panNorth = new JPanel();
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JScrollPane panScroll = null;
		
		back = new JButton("뒤로가기");
		back.addActionListener(butHandler);
		JLabel lbl = new JLabel("검색어:");
		textSearch = new JTextField(20);
		comboSearch = new JComboBox<>(comboStrs);
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(butHandler);
		
		Top top_pan = new Top();
	    Top top_pan2 = new Top();
	
	    jp.add(panNorth,"Center");
	    jp.add(top_pan,"North");
	    jp.add(top_pan2,"North");
	    add(jp,"North");
		
		panNorth.add(back);
		panNorth.add(lbl);
		panNorth.add(textSearch);
		panNorth.add(comboSearch);
		panNorth.add(btnSearch);
		panNorth.setBackground(new Color(13, 113, 62));
		
		model = new DefaultTableModel(cols, 0) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		table = new JTable(model);
		panScroll = new JScrollPane(table);
		panScroll.getViewport().setBackground(Color.WHITE);
		
		new Style(table);
		new Style(lbl,20);
		new Style(btnSearch,19,new Color(238, 28, 37),Color.WHITE);
		new Style(back,19,new Color(51, 51, 51),Color.WHITE);
		new Style(textSearch, 18);
		comboSearch.setRenderer(new MyComboBoxRenderer(15));
		new Style(comboSearch, 15, Color.WHITE,new Color(170, 18, 18));
		add(panScroll,"Center");
		execSQL();
		setTable();
	}
	
	public void execSQL() {
		Connection con = MyDB.getCon();
		String sql = "SELECT count(*) FROM member where " + colName+" like ?";
		try {
			PreparedStatement pt = con.prepareStatement(sql);
			pt.setString(1, "%"+searchWord+"%");
			ResultSet rs = pt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			id = new String[count];
			name = new String[count];
			password = new String[count];
			phone = new String[count];
			jumin = new String[count];
			email = new String[count];
			grade = new String[count]; //2018-12-09 20:41 최우중 추가
			buycount = new String[count]; //2018-12-09 20:41 최우중 추가
			sql = "SELECT id, name, password, phone, jumin, email, grade, buycount FROM member where " + colName+" like ?"; //2018-12-09 20:41 최우중 수정
			pt = con.prepareStatement(sql);
			pt.setString(1, "%"+searchWord+"%");
			rs = pt.executeQuery();
			int i = 0;
			while(rs.next()) {
				id[i] = rs.getString("id");
				name[i] = rs.getString("name");
				password[i] = rs.getString("password");
				phone[i] = rs.getString("phone");
				jumin[i] = rs.getString("jumin");
				email[i] = rs.getString("email");
				grade[i] = rs.getString("grade");//2018-12-09 20:41 최우중 추가
				buycount[i] = rs.getString("buycount");//2018-12-09 20:41 최우중 추가
				i++;
			}
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setTable() {
		model.setRowCount(id.length);
		for (int i = 0; i < id.length; i++) {
			table.setValueAt(id[i], i, 0);
			table.setValueAt(name[i], i, 1);
			table.setValueAt(password[i], i, 2);
			table.setValueAt(phone[i], i, 3);
			table.setValueAt(jumin[i], i, 4);
			table.setValueAt(email[i], i, 5);
			table.setValueAt(grade[i], i, 6); //2018-12-09 20:41 최우중 추가
			table.setValueAt(buycount[i], i, 7); //2018-12-09 20:41 최우중 추가
		}
	}
	
	ActionListener butHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== btnSearch) {
				searchWord = textSearch.getText();
				colName = colNames[comboSearch.getSelectedIndex()];
				execSQL();
				setTable();
			}else {
				Dispatcher.goTo(frame);
			}
		}
	};
}
