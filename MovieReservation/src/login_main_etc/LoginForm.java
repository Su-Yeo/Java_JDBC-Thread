package login_main_etc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import style.LetItSnow;
import style.Style;

public class LoginForm extends JFrame implements ActionListener {
	Dispatcher d;
	Member member;
	private JTextField tf;
	private JPasswordField pf;
	private JButton login, join;
	JLabel pageText;

	public LoginForm() {
		setLayout(new FlowLayout());
		d = new Dispatcher();
		member = new Member();
		
		JPanel idPanel = new JPanel();
		JPanel passPanel = new JPanel();
		
		ImageIcon tempIcon=new ImageIcon("imgs/backgroundimg.jpg");
		Image toChangeImage = tempIcon.getImage();
		Image changedImage = toChangeImage.getScaledInstance(300, 210, java.awt.Image.SCALE_SMOOTH);
		ImageIcon changedIcon=new ImageIcon(changedImage);
		JLabel background=new JLabel(changedIcon);
		add(background);
		background.setLayout(new FlowLayout());
		idPanel.setBackground(new Color(255,255,255));
		passPanel.setBackground(new Color(255,255,255));
		tf = new JTextField(10);
		pf = new JPasswordField(10);
		
		TitledBorder tb=new TitledBorder(new LineBorder(new Color(170,18,18),2));
		Font textFont=new Font("HY엽서L", Font.BOLD,17);
		pageText = new JLabel("-------유한영화관-------");
		pageText.setForeground(new Color(255,255,255));
		pageText.setFont(textFont);
		JLabel deco = new JLabel("------------------------");
		deco.setForeground(new Color(255,255,255));
		deco.setFont(textFont);
		pageText.setBorder(tb);

		JLabel idLabel = new JLabel("       ID      : ");
		idLabel.setFont(textFont);
		idLabel.setForeground(new Color(170,18,18));
		JLabel passLabel = new JLabel("PASSWORD : ");
		passLabel.setFont(textFont);
		passLabel.setForeground(new Color(29, 139, 21));
		join = new JButton("회원가입");
		join.setFont(textFont);
		join.setBackground(new Color(170,18,18));
		join.addActionListener(this);
		login = new JButton("로그인");
		login.setFont(textFont);
		login.setBackground(new Color(29, 139, 21));
		login.addActionListener(this);

		idPanel.add(idLabel);
		idPanel.add(tf);
		
		passPanel.add(passLabel);
		passPanel.add(pf);
		
		background.add(pageText);
		background.add(idPanel);
		background.add(passPanel);
		background.add(deco);
		background.add(join);
		background.add(login);

//		setLayout(new FlowLayout());
		
		LetItSnow snow = new LetItSnow(30);//눈 갯수
		background.add(snow);
	    snow.setBounds(0,0,300,210);
	    Thread t1 = new Thread(snow);
	    t1.start();

		setTitle("LOGIN");

		new Style(300, 210, this);
//		setBounds(250, 250, 300, 210);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); //창크기 조절 못하게 
		setVisible(true);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login) {
			Connection conn = MyDB.getCon();
			String sql = "select * from member where id=?";
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, tf.getText());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) { // 아이디 존재
					String inputPwd = pf.getText();
					String pwd = rs.getString("password");
					if (pwd.equals(inputPwd)) { // 아이디와 비밀번호 둘다 옳다면
						member.setId(rs.getString("id"));
						member.setName(rs.getString("name"));
						member.setPassword(pwd);
						member.setPhone(rs.getString("phone"));
						member.setJumin(rs.getString("jumin"));
						member.setEmail(rs.getString("email"));
						member.setGrade(rs.getString("grade"));
						member.setBuyCount(rs.getInt("buycount"));
						if(rs.getString("id").equals("admin") && pwd.equals("1234")) { //관리자 한명은 일단 admin 1234로 고정
							d.goTo(this, member, "toAdminForm");
						}else if(rs.getString("grade").equals("관리자")){ //추가적으로 생길 수 있는 관리자
							d.goTo(this, member, "toAdminForm");
						}else {
							d.goTo(this, member);
						}
						
					} else {
						 UIManager UI=new UIManager();
						 UI.put("OptionPane.background", new Color(13, 113, 62));
						 UI.put("Panel.background", new Color(13, 113, 62));
						 UI.put("OptionPane.messageForeground", Color.WHITE);
						 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
						 UI.put("Button.background", new Color(238, 28, 37));
						 UI.put("Button.foreground", Color.WHITE);
						 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
						JOptionPane.showMessageDialog(null, "비밀번호 입력 오류", "오류", JOptionPane.PLAIN_MESSAGE);
					}
				} else { // 아이디 존재하지 않음
					 UIManager UI=new UIManager();
					 UI.put("OptionPane.background", new Color(13, 113, 62));
					 UI.put("Panel.background", new Color(13, 113, 62));
					 UI.put("OptionPane.messageForeground", Color.WHITE);
					 UI.put("OptionPane.messageFont", new Font("HY엽서L", Font.BOLD,18));
					 UI.put("Button.background", new Color(238, 28, 37));
					 UI.put("Button.foreground", Color.WHITE);
					 UI.put("Button.font", new Font("HY엽서L", Font.BOLD,18));
					JOptionPane.showMessageDialog(null, "아이디를 잘못 입력하셨습니다.", "오류", JOptionPane.PLAIN_MESSAGE);
				}
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}else { //회원가입 버튼 클릭
			Dispatcher.goTo(this);
		}
	}

	public static void main(String[] args) {
		new LoginForm();
	}
}
