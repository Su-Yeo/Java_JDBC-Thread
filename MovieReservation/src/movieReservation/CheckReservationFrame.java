package movieReservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import foodReservation.FoodReservationTab;
import style.Style;

public class CheckReservationFrame extends JFrame{

	CheckReservation reservationForm;
	FoodReservationTab foodReservation;
	JTabbedPane tabs = new JTabbedPane(JTabbedPane.BOTTOM);
	
	public CheckReservationFrame() {
		reservationForm = new CheckReservation(this);
		foodReservation = new FoodReservationTab(this);
		
		tabs.setBackground(new Color(13, 113, 62));
		tabs.setForeground(Color.WHITE);
		tabs.setFont(new Font("HY엽서L",Font.BOLD,15));
		tabs.add("영화예약", reservationForm);
		tabs.add("음식예약", foodReservation);

		tabs.addMouseListener(tabHandler);
		add(tabs);
		
		UIManager.put("TabbedPane.selected", new Color(238, 28, 37));          
		SwingUtilities.updateComponentTreeUI(tabs);
		
		setTitle("영화 예매 시스템");
		new Style(800, 650, this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	MouseListener tabHandler = new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			try {
				switch (tabs.getSelectedIndex()) {
				case 0:
					reservationForm.execSQL();
					reservationForm.setTable();
					break;
				case 1:
					foodReservation.execSQL();
					foodReservation.setTable();
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	};
}