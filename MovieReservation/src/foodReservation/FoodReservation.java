package foodReservation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FoodReservation extends JPanel implements ActionListener{

	private JButton imageBtn;
	private JLabel foodNum;
	private JLabel foodTitle;
	JButton minusBtn;
	
	public FoodReservation(String foodName, int idx) {
		setLayout(new BorderLayout()); //North에는 음식이름, Center에는 이미지, South에는 +,-,갯수 버튼
		this.setBackground(new Color(255,255,255));
		ImageIcon tempIcon=new ImageIcon("foods/food"+idx+".jpg");
		Image toChangeImage = tempIcon.getImage();
		Image changedImage = toChangeImage.getScaledInstance(130, 110, java.awt.Image.SCALE_SMOOTH);
		ImageIcon changedIcon=new ImageIcon(changedImage);
		
		imageBtn=new JButton(changedIcon);
		imageBtn.setFocusPainted(false);
		imageBtn.setContentAreaFilled(false);
		imageBtn.setBorderPainted(true);
		imageBtn.addActionListener(this);
		
		foodTitle=new JLabel(foodName);
		foodNum=new JLabel("0");
		minusBtn=new JButton("-");
		minusBtn.setFont(new Font("HY엽서L", Font.PLAIN, 35));
		minusBtn.addActionListener(this);
		
		TitledBorder tb=new TitledBorder(new LineBorder(new Color(255,255,255),2));
		this.setBorder(tb);
		
		Font textFont=new Font("HY엽서L", Font.BOLD,20);
		foodTitle.setFont(textFont);
		foodNum.setFont(textFont);
		
		
		add("North", foodTitle);
		add("Center", imageBtn);
		add("South", foodNum);
		add("East", minusBtn);
	}
	
	public String getFoodName() {
		return foodTitle.getText();
	}
	
	public HashMap getFoodInfor() {
		HashMap<String,Integer> map=new HashMap<>();
		if(!foodNum.getText().toString().equals("0")) {
			map.put(foodTitle.getText(), Integer.parseInt(foodNum.getText()));
		}
		return map;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==imageBtn) {
			String tempNum=foodNum.getText();
			int num=Integer.parseInt(tempNum);
			num++;
			foodNum.setText(String.valueOf(num));
		}else {
			String tempNum=foodNum.getText();
			int num=Integer.parseInt(tempNum);
			num--;
			if(num<0) {
				num=0;
			}
			foodNum.setText(String.valueOf(num));
		}
	}
}



















