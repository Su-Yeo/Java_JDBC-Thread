package movieReservation;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import style.LetItSnow;
import style.Style;

public class SelectSchedule extends JFrame {
	
	public SelectSchedule() {
		JPanel center = new JPanel();
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		add(snow);
		snow.setBounds(0,0,800,650);
		Thread t1 = new Thread(snow);
		t1.start();
		GetList movieList = new GetList("영화","movie","title");
		GetList theaterList = new GetList("상영관",9);
		GetList dateList = new GetList("날짜",30);
		GetList timeList = new GetList("시간",8);
		showInfo selectedInfo = new showInfo(movieList,theaterList,dateList,timeList,this);
		selectedInfo.setPreferredSize(new Dimension(800,200));
		center.setLayout(new GridLayout(1, 4));
		center.add(movieList);
		center.add(theaterList);
		center.add(dateList);
		center.add(timeList);
		add(center,"Center");
		add(selectedInfo,"South");
		setTitle("영화 예매 시스템");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Style(800, 650, this);
		setVisible(true);
	}
}
