package style;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class Board extends JPanel {

	public Board() {
		setLayout(new BorderLayout());
		setBackground(new Color(255, 0, 0,0));
		this.setOpaque(false);
	}
}
