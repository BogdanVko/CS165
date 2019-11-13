// Tester Assignment
// Author: Bogdan A Vasilchenko
//   Date: Oct 8, 2019
//  Class: CS164
//  Email: vba@cs.colostate.edu
public class Tester extends Employee{

	public Tester(String firstName, String lastName, int averageLinesOfCode) {
		super(firstName, lastName, averageLinesOfCode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int work() {
		
		return super.randomBetween(getAverageLinesOfCode()*.75, getAverageLinesOfCode()*1.25);
	}

}
