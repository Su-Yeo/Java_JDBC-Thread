package movieReservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import login_main_etc.MyDB;
import style.Top;

public class GetList extends JPanel {
	DefaultTableModel model;
	DefaultTableCellRenderer renderer;
	JTable table;
	String[] names;

	public void MakePanel(String TitleN) {
		this.setBackground(Color.WHITE);
		
		setLayout(new BorderLayout());
		Font textFont=new Font("HY엽서L", Font.BOLD,25);
		JPanel jp = new JPanel();
		JLabel title = new JLabel(TitleN,JLabel.CENTER);
		title.setOpaque(true);
		title.setBackground(new Color(13, 113, 62));
		title.setForeground(Color.WHITE);
		title.setFont(textFont);
		jp.setLayout(new BorderLayout());
		Top top_pan = new Top();
		jp.add(top_pan,"North"); jp.add(title,"Center");
		add(jp,"North");
		
		JScrollPane panScroll = null;
		model = new DefaultTableModel(0, 1) {
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		
		renderer = new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if(!isSelected) {
					if(row%2==0) {
						cell.setForeground(new Color(170, 18, 18));
					}else {
						cell.setForeground(new Color(13, 113, 62));
					}
				}
				if(isSelected) {
					if(row%2==0) {
						table.setSelectionBackground(new Color(170, 18, 18));
						table.setSelectionForeground(Color.WHITE);
					}else {
						table.setSelectionBackground(new Color(13, 113, 62));
						table.setSelectionForeground(Color.WHITE);
					}
				}
				return cell;
			}
		};
		
		table = new JTable(model);
		table.setTableHeader(null);
		table.setShowHorizontalLines(false);
		table.setRowHeight(25);
		table.setFont(new Font("HY엽서L", Font.BOLD,19));
		table.setBackground(Color.WHITE);
		table.setDefaultRenderer(Object.class, renderer); 
		
		panScroll = new JScrollPane(table);
		add(panScroll,"Center");
	}
	
	public GetList(String TitleN, String tableN, String ColumnN) {
		MakePanel(TitleN);
		execSQL(tableN, ColumnN);
		setTable();
	}
	
	public GetList(String TitleN, int num) {
		MakePanel(TitleN);
		if(TitleN=="시간") setTime(num);
		else if(TitleN=="날짜") setDate(num);
		else setTheater(num);
	}
	
	public void execSQL(String tableN, String ColumnN) {
		Connection con = MyDB.getCon();
		String sql = "SELECT count(*) FROM "+tableN;
		try {
			PreparedStatement pt = con.prepareStatement(sql);
			ResultSet rs = pt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			names = new String[count];
			sql = "SELECT "+ColumnN+" FROM "+tableN;
			pt = con.prepareStatement(sql);
			rs = pt.executeQuery();
			int i = 0;
			while(rs.next()) {
				names[i] = rs.getString(ColumnN);
				i++;
			}
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setTable() {
		model.setRowCount(names.length);
		for (int i = 0; i < names.length; i++) {
			table.setValueAt(names[i], i, 0);
		}
	}
	
	public void setTheater(int num) {
		model.setRowCount(num);
		for (int i = 0; i < num; i++) {
			table.setValueAt(i+1, i, 0);
		}
	}
	
	public void setTime(int num) {
		model.setRowCount(num);
		int j=8;
		for (int i = 0; i <num; i++) {
			if(j<10) {table.setValueAt("0"+j+"~"+(j+2), i, 0);}
			else{table.setValueAt(j+"~"+(j+2), i, 0);}
			j+=2;
		}
	}
	
	public void setDate(int num) {
		model.setRowCount(num);
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
		SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i <num; i++) {
			table.setValueAt(today.format(cal.getTime()), i, 0);
		    cal.add(Calendar.DATE, 1);
		}
	}
}
