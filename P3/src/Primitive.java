/**
 * Created by me on 8/28/17
 */

public abstract class Primitive {
	public int color;
	public  boolean isFilled;
	
	
	public void setFilled(boolean isF) {
		
		if (isF) {
			this.isFilled = true;
		}
		else {
			this.isFilled = false;
		}
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public abstract void draw(UserInterface ui);
	

}