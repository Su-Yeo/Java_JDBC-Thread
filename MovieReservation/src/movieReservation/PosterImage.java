package movieReservation;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PosterImage extends JPanel implements Runnable{

	private Image[] poster=new Image[8];
	private int posterIndex=0;
	
	public PosterImage() {	
		for(int i=0; i<poster.length; i++) {
			ImageIcon tempIcon=new ImageIcon("imgs/movie"+i+".jpg");
			Image toChangeImage = tempIcon.getImage();
			Image changedImage = toChangeImage.getScaledInstance(400, 380, java.awt.Image.SCALE_SMOOTH);
			poster[i]=changedImage;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(poster[posterIndex], 15, 30, this); 
	}

	@Override
	public void run() {
		while(true) {
			try {
				if(posterIndex==8) {
					posterIndex=0;
				}
				Thread.sleep(1500);
				repaint();
				posterIndex++;
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}



























