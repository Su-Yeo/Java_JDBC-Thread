package foodReservation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import login_main_etc.Dispatcher;
import login_main_etc.MyDB;
import style.Style;

public class FoodPayment extends JFrame {
	
	public FoodPayment(FoodReservationFrame form,HashMap infor) {
		PayPanel pan=new PayPanel(infor, this, form);
		
		add(pan);
		setTitle("결제");
		new Style(450, 300, this);
//		setBounds(600,400,450,300);
		this.setResizable(false); //창크기 조절 못하게 
		setVisible(true);
	}
}

class PayPanel extends JPanel implements ActionListener{ 
	String food="";
	String[] foodsName= {"김밥","팝콘","떡볶이","핫도그","라면","사이다",
			"콜라","햄버거","감자튀김","피자","김치볶음밥",
			"만두","과자","건조오징어","아메리카노","와플","아이스크림","치킨"};
	int[] foodsPrice= {2000,3500,3000,2000,1500,1200,
			1200,4000,2500,9000,5000,
			3000,1300,2500,2500,2500,2000,7500};
	JLabel pwdLbl;
	JPasswordField pwdField;
	JButton inputBtn,cancelBtn;
	JTextArea foodInforArea;
	FoodPayment me;
	int sumPrice;
	FoodReservationFrame reservationform;
	
	public PayPanel(HashMap infor, FoodPayment me, FoodReservationFrame form) {
		this.me=me;
		this.reservationform=form;
		ImageIcon tempIcon=new ImageIcon("imgs/backgroundimg.jpg");
		Image toChangeImage = tempIcon.getImage();
		Image changedImage = toChangeImage.getScaledInstance(450, 300, java.awt.Image.SCALE_SMOOTH);
		ImageIcon changedIcon=new ImageIcon(changedImage);
		JLabel background=new JLabel(changedIcon);
		add(background);
		background.setLayout(new FlowLayout());
		this.setOpaque(false);
		Font textFont=new Font("HY엽서L", Font.BOLD,20);
		pwdLbl=new JLabel("Password");
		pwdLbl.setFont(textFont);
		pwdField=new JPasswordField();
		pwdField.setPreferredSize(new Dimension(140,20));
		inputBtn=new JButton("결제");
		inputBtn.setFont(new Font("HY엽서L", Font.BOLD,15));
		inputBtn.setBackground(new Color(170,18,18));
		cancelBtn=new JButton("취소");
		cancelBtn.setFont(new Font("HY엽서L", Font.BOLD,15));
		cancelBtn.setBackground(new Color(29, 139, 21));
		foodInforArea=new JTextArea(5,20);
		JScrollPane scrollPane = new JScrollPane(foodInforArea);
		foodInforArea.setEditable(false);
		foodInforArea.setFont(textFont);
		sumPrice=0;
		
		for(int i=0; i<18; i++) {
			if(infor.containsKey(foodsName[i])) {
				foodInforArea.append(foodsName[i]+": ");
				food+=foodsName[i]+":";
				foodInforArea.append(infor.get(foodsName[i]).toString()+"개\n");
				food+=infor.get(foodsName[i]).toString()+"개 ";
				for(int j=0; j<Integer.parseInt(infor.get(foodsName[i]).toString()); j++) {
					sumPrice+=foodsPrice[i];
				}
				foodInforArea.setCaretPosition(foodInforArea.getDocument().getLength());
			}
		}
		foodInforArea.append("총 금액: "+sumPrice+"원");
		background.add(scrollPane);
		background.add(pwdLbl);
		background.add(pwdField);
		background.add(inputBtn);
		background.add(cancelBtn);
		
		inputBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inputBtn) { //결제버튼
			if(pwdField.getText().isEmpty()) {
				 UIManager UI=new UIManager();
				 UI.put("OptionPane.background", new Color(13, 113, 62));
				 UI.put("Panel.background", new Color(13, 113, 62));
				 UI.put("OptionPane.messageForeground", Color.WHITE);
				 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
				 UI.put("Button.background", new Color(238, 28, 37));
				 UI.put("Button.foreground", Color.WHITE);
				 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "결제오류", JOptionPane.PLAIN_MESSAGE);
			}else {
				if(pwdField.getText().equals(Dispatcher.loginMember.getPassword())) {
					Connection conn=MyDB.getCon();
					String sql="insert into food(id,tdate,type,price) values(?,now(),?,?)";
					try {
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, Dispatcher.loginMember.getId());
						//pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
						pstmt.setString(2, food);
						pstmt.setInt(3,sumPrice);
						pstmt.executeUpdate();
						
						if(pstmt!=null) {
							pstmt.close();	
						}
						if(conn!=null) {
							conn.close();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					me.dispose();
					 UIManager UI=new UIManager();
					 UI.put("OptionPane.background", new Color(13, 113, 62));
					 UI.put("Panel.background", new Color(13, 113, 62));
					 UI.put("OptionPane.messageForeground", Color.WHITE);
					 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
					 UI.put("Button.background", new Color(238, 28, 37));
					 UI.put("Button.foreground", Color.WHITE);
					 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
					JOptionPane.showMessageDialog(null, "결제 되었습니다.", "결제", JOptionPane.PLAIN_MESSAGE);
					reservationform.dispose();
					Dispatcher.goTo(reservationform);
				}else {
					 UIManager UI=new UIManager();
					 UI.put("OptionPane.background", new Color(13, 113, 62));
					 UI.put("Panel.background", new Color(13, 113, 62));
					 UI.put("OptionPane.messageForeground", Color.WHITE);
					 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
					 UI.put("Button.background", new Color(238, 28, 37));
					 UI.put("Button.foreground", Color.WHITE);
					 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
					JOptionPane.showMessageDialog(null, "비밀번호를 재입력해주세요.", "결제 오류", JOptionPane.PLAIN_MESSAGE);
					pwdField.setText("");
					pwdField.requestFocus();
				}
			}
		}else if(e.getSource()==cancelBtn) { //취소버튼
			me.dispose();
		}
	}
}



























