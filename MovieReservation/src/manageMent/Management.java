package manageMent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import manageMent.SearchPanel;
import style.Style;

public class Management extends JFrame {
	
	SearchPanel search;
	InsertPanel insert;
	UpdateDelPan update;
	JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM);
	
	public Management() {
		search = new SearchPanel(this);
		insert = new InsertPanel(this);
		update = new UpdateDelPan(this);
		
		tabs.setBackground(new Color(13, 113, 62));
		tabs.setForeground(Color.WHITE);
		tabs.setFont(new Font("HY엽서L",Font.BOLD,15));
		tabs.add("검색", search);
		tabs.add("추가", insert);
		tabs.add("수정 및 삭제", update);
		tabs.addMouseListener(tabHandler);
		add(tabs);
		
		UIManager.put("TabbedPane.selected", new Color(238, 28, 37));          
		SwingUtilities.updateComponentTreeUI(tabs);
		
		setTitle("회원 관리");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Style(800, 650, this);
//		setBounds(150, 150, 800, 650);
		setVisible(true);
	}
	
	MouseListener tabHandler = new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			
			switch (tabs.getSelectedIndex()) {
			case 0:
				search.execSQL();
				search.setTable();
				break;
			case 1:
				insert.sPan.execSQL();
				insert.sPan.setTable();
				break;
			case 2:
				update.sPan.execSQL();
				update.sPan.setTable();
				break;
			}
		};
	};
}

