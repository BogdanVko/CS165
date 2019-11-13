// Programmer Assignment
// Author: Bogdan A Vasilchenko
//   Date: Oct 8, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Programmer extends Employee{

	public Programmer(String firstName, String lastName, int averageLinesOfCode) {
		super(firstName, lastName, averageLinesOfCode);
	}
	@Override
	public int work() {
		
		return super.randomBetween(getAverageLinesOfCode()*.5, getAverageLinesOfCode()*1.50);
	}
	

}
