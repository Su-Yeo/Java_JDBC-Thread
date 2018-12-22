package style;

import java.util.Random;

public class Snow {

	private int size, x, y;
	
	public Snow(int size, int x, int y) {
		super();
		this.size = size;
		this.x = x;
		this.y = y;
	}
	public void setSnow(int size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
	}
	public int getSize() {
		return size;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	Random random = new Random();
	public int plusY(int count){
        if(y<=650){
            y+=2;
            if(count == 0) {
	            if(random.nextInt(2)==0){
	                x+=random.nextInt(2);
	            }else{
	                x-=random.nextInt(2);
	            }
            }
        }
        return y;
    }
}
