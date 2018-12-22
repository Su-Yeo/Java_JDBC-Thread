package style;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Text extends JPanel{

	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Font f1=new Font("HY엽서L", Font.BOLD, 30);
		g.setFont(f1);
		g.drawString("영화목록",90, 45);
	}
}
