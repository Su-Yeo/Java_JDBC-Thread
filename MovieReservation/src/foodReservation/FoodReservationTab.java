package foodReservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import movieReservation.CheckReservationFrame;
import style.Style;
import style.Top;
import login_main_etc.Dispatcher;
import login_main_etc.MyDB;

public class FoodReservationTab extends JPanel{
	
	String[] cols= {"id","날짜","음식","가격"};
	String[] idArr;
	String[] timeArr;
	String[] foodArr;
	String[] priceArr;
	
	DefaultTableModel model;
	JTable table;
	CheckReservationFrame chk;
	
	public FoodReservationTab(CheckReservationFrame chk) {
		setLayout(new BorderLayout());
		this.chk = chk;
		model=new DefaultTableModel(cols,0) {
			@Override
			public boolean isCellEditable(int i1,int i2) {
				return false;
			}
		};
		table = new JTable(model);
		new Style(table);
		JScrollPane panScroll = new JScrollPane(table);
		table.setFont(new Font("굴림체", Font.BOLD, 20)); //JTable의 폰트 크기 조정
		table.setRowHeight(20);
		panScroll.getViewport().setBackground(Color.WHITE);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(400);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		
		Top top_pan = new Top();
	    JPanel jp = new JPanel();
	    jp.setBackground(new Color(13, 113, 62));
	    jp.add(top_pan,"North");
	    add(jp,"North");
		
		add(panScroll,"Center");
		JButton backBtn = new JButton("뒤로가기");
		new Style(backBtn, 25, new Color(13, 113, 62), Color.WHITE);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dispatcher.goTo(chk);
			}
		});
		add(backBtn,"South");
		try {
			execSQL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		setTable();
	}
	
	public void execSQL() throws SQLException {
		Connection conn=MyDB.getCon();
		
		//playdate타입 varchar라서 
		
		String sql="select count(*) from food where id=? and date_format(tdate,'%Y-%m-%d') >= date_format(now(),'%Y-%m-%d')";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, Dispatcher.loginMember.getId().toString());

		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int count=rs.getInt(1);
		idArr=new String[count];
		timeArr=new String[count];
		foodArr=new String[count];
		priceArr=new String[count];
		
		sql="select id,date_format(tdate,'%Y-%m-%d') as td,type,price from food where id=? and date_format(tdate,'%Y-%m-%d') >= date_format(now(),'%Y-%m-%d')";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,Dispatcher.loginMember.getId().toString());
		rs=pstmt.executeQuery();
		int idx=0;
		while(rs.next()) {
			idArr[idx]=rs.getString("id");
			timeArr[idx]=rs.getString("td");
			foodArr[idx]=rs.getString("type");
			priceArr[idx]=rs.getString("price");
			idx++;
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		if(conn!=null) {
			conn.close();
		}
	}
	
	public void setTable() {//테이블 초기화
		model.setRowCount(idArr.length); //테이블의 행을 데이터 갯수만큼 재 설정
		for(int i=0; i<idArr.length; i++) {
			table.setValueAt(idArr[i], i, 0); //(데이터,행,열)
			table.setValueAt(timeArr[i], i, 1);
			table.setValueAt(foodArr[i], i, 2);
			table.setValueAt(priceArr[i], i, 3);
		}
	}
}





















