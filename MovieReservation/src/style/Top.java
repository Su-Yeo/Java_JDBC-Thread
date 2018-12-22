package style;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Top extends JPanel {
	public Top() {
		// TODO Auto-generated constructor stub
		ImageIcon imgicon = new ImageIcon("img/light.png");
		this.setLayout(new FlowLayout(FlowLayout.LEFT,4,0));
		this.setBackground(new Color(13, 113, 62));
		JLabel img[] = new JLabel[4];
		for (int i=0; i<4; i++) {
			img[i] = new JLabel(imgicon,JLabel.CENTER);
			img[i].setOpaque(true);
			img[i].setBackground(new Color(13, 113, 62));
			this.add(img[i]);
		}
		this.setBounds(0,0,800,30);
	}
}
