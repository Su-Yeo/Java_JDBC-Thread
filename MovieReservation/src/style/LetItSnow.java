package style;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class LetItSnow extends Board implements Runnable{
	
	ArrayList<Snow> snowList = new ArrayList<Snow>();
	Snow snow;
	
	public LetItSnow(int cnt) {
		Random random = new Random();
		for (int i = 0; i < cnt; i++) {
			int x=random.nextInt(799)+1;
			int y=random.nextInt(650)-650;
			int size=random.nextInt(8)+2; //랜덤 눈 크기
			snow = new Snow(size, x, y);
			snowList.add(snow);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < snowList.size(); i++) {
			g.setColor(Color.white);
            g.fillOval(snowList.get(i).getX(), snowList.get(i).getY(), snowList.get(i).getSize(), snowList.get(i).getSize());
        }
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			try {
				Thread.sleep(15);
				count = count +1;
				Random random = new Random();
				for(int i=0;i<snowList.size();i++){
					int sy = snowList.get(i).plusY(count%4);
					if(count >= 4) {count =0;} 
                    repaint();
                    if(sy>=650) {//눈 내려오면 다시 반복
						int x=random.nextInt(799)+1;
						int y=random.nextInt(650)-650;
						int size=random.nextInt(8)+2; //랜덤 눈 크기
						snowList.get(i).setSnow(size, x, y);
                    }
                }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}

}
