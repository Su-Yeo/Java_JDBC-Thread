package movieReservation;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import login_main_etc.Dispatcher;

public class showInfo extends JPanel {
	JLabel[] texts = new JLabel[3];
	JButton selectSeat;
	JTable[] table = new JTable[4];
	JLabel imgLabel;
	String[] lblStrs = {"상영관","날짜","시간"};
	JLabel[] lbls;
	JTextArea textArea;
	SelectSchedule selectSchedule;
	
	public showInfo(GetList movieList,GetList theaterList,GetList dateList,GetList timeList,SelectSchedule s) {
		selectSchedule = s;
		setLayout(new GridLayout(1, 5));
		Font textFont=new Font("HY엽서L", Font.BOLD,19);
		table[0] = movieList.table;
		table[1] = theaterList.table;
		table[2] = dateList.table;
		table[3] = timeList.table;
		table[0].addMouseListener(tableHandler);
		table[1].addMouseListener(tableHandler1);
		table[2].addMouseListener(tableHandler2);
		table[3].addMouseListener(tableHandler3);
		
		setBackground(new Color(13, 113, 62));
		JPanel pans[] = new JPanel[3];
		imgLabel = new JLabel("영화선택",JLabel.CENTER);
		imgLabel.setOpaque(true);
		imgLabel.setBackground(new Color(13, 113, 62));
		imgLabel.setForeground(Color.WHITE);
		imgLabel.setFont(textFont);
		add(imgLabel);
		
		for (int i = 0; i < pans.length; i++) {
			pans[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			pans[i].setBackground(new Color(13, 113, 62));
		}
		
		lbls = new JLabel[lblStrs.length];
		
		for (int i = 0; i < lblStrs.length; i++) {
			lbls[i] = new JLabel(lblStrs[i]);
			lbls[i].setOpaque(true);
			lbls[i].setBackground(new Color(13, 113, 62));
			lbls[i].setForeground(Color.WHITE);
			lbls[i].setFont(textFont);
			lbls[i].setVisible(false);
		}
		
		for (int i = 0; i < texts.length; i++) {
			texts[i] = new JLabel();
			texts[i].setOpaque(true);
			texts[i].setBackground(new Color(13, 113, 62));
			texts[i].setForeground(Color.WHITE);
			texts[i].setFont(new Font("맑은 고딕", Font.BOLD, 18));
		}
		
		textArea = new JTextArea();
		textArea.setBackground(new Color(13, 113, 62));
		textArea.setForeground(Color.WHITE);
		textArea.setFont(textFont);
		textArea.setSize(150, 200);
		textArea.setLineWrap(true);
		pans[0].add(textArea);
		add(pans[0]);
		
		lbls[1].setText("상영관선택");
		lbls[1].setVisible(true);
		
		pans[1].setLayout(new GridLayout(3, 1, 0, 45));
		JPanel jp[] = new JPanel[3];
		for (int i = 0; i < jp.length; i++) {
			jp[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
			jp[i].setBackground(new Color(13, 113, 62));
			pans[1].add(jp[i]);
		}
		jp[0].add(lbls[0]); jp[0].add(texts[0]);
		jp[1].add(lbls[1]); jp[1].add(texts[1]);
		jp[2].add(lbls[2]); jp[2].add(texts[2]);
		add(pans[1]);
		
		ImageIcon i = new ImageIcon("imgs/snowman.png");
		JLabel img = new JLabel(i,JLabel.RIGHT);
		add(img);
		
		selectSeat = new JButton("좌석 선택 →");
		selectSeat.setBackground(new Color(238, 28, 37));
		selectSeat.setForeground(Color.WHITE);
		selectSeat.setFont(textFont);
		
		JButton cancel = new JButton("←뒤로 가기");
		cancel.setBackground(new Color(51, 51, 51));
		cancel.setForeground(Color.WHITE);
		cancel.setFont(textFont);
		
		pans[2].setLayout(new GridLayout(2,1,5,10));
		pans[2].add(selectSeat);
		pans[2].add(cancel);
		add(pans[2]);
		selectSeat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dispatcher d = new Dispatcher();
				 UIManager UI=new UIManager();
				 UI.put("OptionPane.background", new Color(13, 113, 62));
				 UI.put("Panel.background", new Color(13, 113, 62));
				 UI.put("OptionPane.messageForeground", Color.WHITE);
				 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
				 UI.put("Button.background", new Color(238, 28, 37));
				 UI.put("Button.foreground", Color.WHITE);
				 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
				if(textArea.getText().equals("")) {JOptionPane.showMessageDialog(null, "영화를 선택해주세요!", "선택해주세요", JOptionPane.PLAIN_MESSAGE);return;}
				for (int i = 0; i < texts.length; i++) {
					if(texts[i].getText().equals("")) {
						if(i==1) {JOptionPane.showMessageDialog(null, lblStrs[i]+"를 선택해주세요!","선택해주세요", JOptionPane.PLAIN_MESSAGE); return;}
						else{JOptionPane.showMessageDialog(null, lblStrs[i]+"을 선택해주세요!","선택해주세요", JOptionPane.PLAIN_MESSAGE); return;}
					}
				}
				Dispatcher.goTo(textArea, texts, selectSchedule);
			}
		});
		cancel.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dispatcher.goTo(s); 
				s.dispose();
			}
		});
	}
	
	
	
	MouseListener tableHandler = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int row = table[0].getSelectedRow();
				textArea.setText(table[0].getValueAt(row, 0).toString());
				String path = "imgs/movie"+row+".jpg";
				imgLabel.setText("");
				ImageIcon image = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
				imgLabel.setIcon(image);
			}
		}
	};
	MouseListener tableHandler1 = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int row = table[1].getSelectedRow();
				texts[0].setText(table[1].getValueAt(row, 0).toString());
				lbls[1].setText("일시 ");
				for (int i = 0; i < lblStrs.length; i++) {
					lbls[i].setVisible(true);
				}
			}
		}
	};
	MouseListener tableHandler2 = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int row = table[2].getSelectedRow();
				texts[1].setText(table[2].getValueAt(row, 0).toString());
				lbls[1].setText("일시 ");
				for (int i = 0; i < lblStrs.length; i++) {
					lbls[i].setVisible(true);
				}
			}
		}
	};
	MouseListener tableHandler3 = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==2) {
				int row = table[3].getSelectedRow();
				texts[2].setText(table[3].getValueAt(row, 0).toString());
				lbls[1].setText("일시 ");
				for (int i = 0; i < lblStrs.length; i++) {
					lbls[i].setVisible(true);
				}
			}
		}
	};
}
