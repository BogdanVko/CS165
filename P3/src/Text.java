// Text Assignment
// Author: Bogdan A Vasilchenko
//   Date: Sep 23, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Text  extends Primitive{

	public int x;
	public int y;
	public String fontName;
	public int fontSize;
	public String text;
	
	
	
	
	public Text(int x, int y, String s) {
	this.x = x;
	this.y = y;
	this.text = s;
	
	
	
	}
	
	public void setFont(String name, int size) {
		
		this.fontName = name;
		this.fontSize = size;
	}
	
	
	public void draw(UserInterface ui) {
		
		ui.setFont(this.fontName, this.fontSize);
		
		ui.textColor(super.color);
		ui.drawText(x, y, text);
		
		
	}
	
}
