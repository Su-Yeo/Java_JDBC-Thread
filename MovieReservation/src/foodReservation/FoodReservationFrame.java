package foodReservation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import login_main_etc.Dispatcher;
import style.Style;

public class FoodReservationFrame extends JFrame implements ActionListener {

	JButton payment;
	JButton back;
	String[] foodsName = { "김밥", "팝콘", "떡볶이", "핫도그", "라면", "사이다", "콜라", "햄버거", "감자튀김", "피자", "김치볶음밥", "만두", "과자",
			"건조오징어", "아메리카노", "와플", "아이스크림", "치킨" };
	FoodReservation[] foodResForm;

	public FoodReservationFrame() {

		setLayout(new GridLayout(5, 4));
		// this.getContentPane().setBackground(new Color(255,255,255));
		payment = new JButton("결제하기");
		payment.setFont(new Font("HY엽서L", Font.BOLD, 30));
		payment.setBackground(new Color(13, 113, 62));
		payment.setForeground(new Color(170, 18, 18));
		back = new JButton("뒤로가기");
		back.setFont(new Font("HY엽서L", Font.BOLD, 30));
		back.setBackground(new Color(170, 18, 18));
		back.setForeground(new Color(13, 113, 62));
		foodResForm = new FoodReservation[18];

		TitledBorder tb1=new TitledBorder(new LineBorder(new Color(13, 113, 62),2));
		TitledBorder tb2=new TitledBorder(new LineBorder(new Color(170, 18, 18),2));
		for (int i = 0; i < 18; i++) {
			foodResForm[i] = new FoodReservation(foodsName[i], i); // 이미지이름 인자전달
			if ((i >= 0 && i <= 3) || (i >= 8 && i <= 11) || (i >= 16 && i <= 17)) {
				if (i % 2 == 0) {
					foodResForm[i].setBorder(tb1);
					foodResForm[i].minusBtn.setBackground(new Color(13, 113, 62));
				}	
				else {
					foodResForm[i].setBorder(tb2);
					foodResForm[i].minusBtn.setBackground(new Color(170, 18, 18));
				}
			}else{
				if (i % 2 == 0) {
					foodResForm[i].setBorder(tb2);
					foodResForm[i].minusBtn.setBackground(new Color(170, 18, 18));
				}
				else {
					foodResForm[i].setBorder(tb1);
					foodResForm[i].minusBtn.setBackground(new Color(13, 113, 62));
				}
			}
			add(foodResForm[i]);
		}

		add(back);
		add(payment);
		payment.addActionListener(this);
		back.addActionListener(this);
		setTitle("영화 예매 시스템");
		new Style(800, 650, this);
//		setBounds(400, 100, 800, 650);
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FoodReservationFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == back) {
			Dispatcher.goTo(this); // 뒤로가기
			this.dispose();
		} else {
			HashMap<String, Integer> payMap = new HashMap<>();
			HashMap<String, Integer> map;
			for (int i = 0; i < 18; i++) {
				map = foodResForm[i].getFoodInfor();
				if (!map.isEmpty()) {
					int foodNum = map.get(foodResForm[i].getFoodName());
					payMap.put(foodResForm[i].getFoodName(), foodNum);
				}
			}
			Dispatcher.goTo(this, payMap); // 결제창띄우기
		}
	}

}
