// Text Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Text  extends Primitive{

	private int x;
	private int y;
	String fontName;
	int fontSize;
	String text;
	int color;
	
	
	
	public Text(int x, int y, String s) {
	this.x = x;
	this.y = y;
	this.text = s;
	
	color = 0;
	
	}
	
	public void setFont(String name, int size) {
		
		this.fontName = name;
		this.fontSize = size;
	}
	
	public void setColor(int color) {
		this.color = color;
		
	}
	public void draw(UserInterface ui) {
		
		ui.setFont(fontName, fontSize);
		//ui.lineColor(color);
		//ui.fillColor(color);
		ui.textColor(color);
		ui.drawText(x, y, text);
		
		
	}
	
}
