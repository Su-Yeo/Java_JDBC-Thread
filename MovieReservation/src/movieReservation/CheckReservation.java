package movieReservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.Style;
import style.Top;

public class CheckReservation extends JPanel{
	
	String[] cols= {"id","제목","상영관","상영시간","날짜","좌석번호"};
	String[] idArr;
	String[] movieTitleArr;
	String[] screenNumArr;
	String[] playtimeArr;
	String[] playdateArr;
	String[] seatNumArr;
	
	DefaultTableModel model;
	JTable table;
	CheckReservationFrame chk;
	
	public CheckReservation(CheckReservationFrame chk) {
		this.chk = chk;
		setLayout(new BorderLayout());
		
		model=new DefaultTableModel(cols,0) {
			@Override
			public boolean isCellEditable(int i1,int i2) {
				return false;
			}
		};
		table = new JTable(model);
		Style s=new Style(table);
		JScrollPane panScroll = new JScrollPane(table);
		table.setFont(new Font("굴림체", Font.BOLD, 20)); //JTable의 폰트 크기 조정
		table.setRowHeight(20);
		resizeColumnWidth(table);
		add(panScroll,"Center");
		panScroll.getViewport().setBackground(Color.WHITE);
		
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
	
	public void resizeColumnWidth(JTable table) { 
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) { 
			int width = 50; // Min width 
			for (int row = 0; row < table.getRowCount(); row++) { 
				TableCellRenderer renderer = table.getCellRenderer(row, column); 
				Component comp = table.prepareRenderer(renderer, row, column); 
				width = Math.max(comp.getPreferredSize().width +1 , width); 
			} 
			columnModel.getColumn(column).setPreferredWidth(width);  
		}
	}

	
	public void execSQL() throws SQLException {
		Connection conn=MyDB.getCon();
		
		//playdate타입 varchar라서 
		
		String sql="select count(*) from reservation where id=? and playdate >= str_to_date(now(),'%Y-%m-%d')";
//		String sql="select count(*) from reservation where id=? and playdate >= date_format(now(),'%Y-%m-%d')";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, Dispatcher.loginMember.getId().toString());
//		pstmt.setString(1, "ksh");
		ResultSet rs=pstmt.executeQuery();
		rs.next();
		int count=rs.getInt(1);
		idArr=new String[count];
		movieTitleArr=new String[count];
		screenNumArr=new String[count];
		playtimeArr=new String[count];
		playdateArr=new String[count];
		seatNumArr=new String[count];
		
		sql="select id,sNum,screenNum,playtime,playdate,seatNum from reservation where id=? and playdate >= date_format(now(),'%Y-%m-%d')";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,Dispatcher.loginMember.getId().toString());
		rs=pstmt.executeQuery();
		int idx=0;
		while(rs.next()) {
			String sql2="select title from movie where sNum="+rs.getString("sNum");
			pstmt=conn.prepareStatement(sql2);
			ResultSet rs2=pstmt.executeQuery();
			rs2.next();
			
			idArr[idx]=rs.getString("id");
			movieTitleArr[idx]=rs2.getString("title");
			screenNumArr[idx]=rs.getString("screenNum");
			playtimeArr[idx]=rs.getString("playtime");
			playdateArr[idx]=rs.getString("playdate");
			seatNumArr[idx]=rs.getString("seatNum");
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
			table.setValueAt(movieTitleArr[i], i, 1);
			table.setValueAt(screenNumArr[i], i, 2);
			table.setValueAt(playtimeArr[i], i, 3);
			table.setValueAt(playdateArr[i], i, 4);
			table.setValueAt(seatNumArr[i], i, 5);
		}
	}
}





















