import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Originated for CS161 in 2015 <br>
 * modified by garethhalladay on 9/21/17
 */
public class Project {
    /**
     * constants that can be changed for each programmer. Also used to set a minimum
     * for lines of code per days if the number of days set for the deadline is within
     * the range of the number of lines of code.
     */
    private static final int MINIMUM_LINES = 50, RANGE = 101;
    /**
     * Estimate of the number of lines of code the project will require.
     * We realize this is never known ahead of time,
     * but for this exercise we'll pretend.
     */
    private final int linesOfCode;
    /**
     * how many days have been given to complete the project
     */
    private final int deadline;
    /**
     * How many days the project takes until completion
     */
    private int daysWorkedSoFar;
    /**
     * The maximum number of employees on the project
     */
    private final int maxEmployeesOnProject;
    /**
     * employees associated with the project
     */
    private final List<Employee> employeesOnProject;
    /**
     * Random instance to assign lines of code to each programmer
     */
    private final Random rand;



    /**
     * Creates a new instance of a Project. This is constructor chaining. It calls the other constructor
     * with a new Random number each time.
     * @param linesOfCode lines of code needed for the project
     * @param deadline how many days assigned to complete the project
     * @param maxEmployees the maximum amount of employees you can assign to a project
     * @throws FileNotFoundException exception thrown if file is not found
     */
    public Project(int linesOfCode, int deadline, int maxEmployees) throws FileNotFoundException {
        this(linesOfCode, deadline, maxEmployees, new Random().nextLong());
    }

    /**
     *
     * @param linesOfCode lines of code needed for the project
     * @param deadline how many days assigned to complete the project
     * @param maxEmployees the maximum amount of employees you can assign to a project
     * @param seed allows us to have reproducible results
     * @throws FileNotFoundException exception thrown if file is not found
     */
    public Project(int linesOfCode, int deadline, int maxEmployees, long seed) throws FileNotFoundException {
        this.linesOfCode = linesOfCode;
        this.deadline = deadline;
        this.maxEmployeesOnProject = checkMaxEmployees(maxEmployees);
        this.employeesOnProject = new ArrayList<>();
        this.rand = new Random(seed);
        addEmployeesToProject();
    }

    private int checkMaxEmployees(int maxEmployees){
        if (maxEmployees < 2)
            throw new IllegalArgumentException("There must be at least two Employees on the project. One programmer and one tester.");
        return maxEmployees;
    }

    /**
     * Helps assigns programmers and testers to the project.
     * @return Lines of code needed per day
     */
    public int linesNeededPerDay(){
        int linesNeededPerDay = linesOfCode/deadline;
        // 50 is the minimum amount of
        return (linesNeededPerDay < MINIMUM_LINES) ? MINIMUM_LINES : linesNeededPerDay;
    }


    /**
     * Adds employees to project. At this point of time we are assuming that each project has an equal amount of
     * programmers and testers (which is generally not the case).
     */
    private void addEmployeesToProject() throws FileNotFoundException {
        Iterator<String> allEmployeeNames = addEmployeeNames("resources/employees.txt");
        int averageLinesCoded = 0;
        int averageLinesTested = 0;
        int maxProgrammers = maxEmployeesOnProject / 2;
        while(averageLinesCoded < linesNeededPerDay() && employeesOnProject.size() < maxProgrammers && allEmployeeNames.hasNext()) {
            Programmer p = addNewProgrammer(allEmployeeNames);
            averageLinesCoded += p.getAverageLinesOfCode();
            employeesOnProject.add(p);
        }
        while(employeesOnProject.size() < maxEmployeesOnProject && averageLinesTested < linesNeededPerDay() && allEmployeeNames.hasNext()) {
            Tester t = addNewTester(allEmployeeNames);
            averageLinesTested += t.getAverageLinesOfCode();
            employeesOnProject.add(t);
        }
    }

    /**
     * Helper method to add a new programmer to the current project
     * @return a programmer who can code anywhere between 50 and 150 lines of code
     */
    private Programmer addNewProgrammer(Iterator<String> employees){
        String[] name = employees.next().split(" ");
        return new Programmer(name[0], name[1],rand.nextInt(RANGE) + MINIMUM_LINES);
    }

    /**
     * Helper method to add a new tester to the current project
     * @return a tester who can code anywhere between 50 and 150 lines of code
     */
    private Tester addNewTester(Iterator<String> employees){
        String[] name = employees.next().split(" ");
        return new Tester(name[0], name[1],rand.nextInt(RANGE) + MINIMUM_LINES);
    }

    /**
     * Helper method that reads names from a file and assigns the names into
     * an Iterator
     * @param filename a list of employee names
     * @throws FileNotFoundException
     */
    private Iterator<String> addEmployeeNames(String filename) throws FileNotFoundException {
        // try with resources is a Java 7 feature that is very useful!
        List<String> allEmployees = new ArrayList<>();
        try(Scanner scan = new Scanner(new File(filename))){
            while(scan.hasNext()){
                allEmployees.add(scan.nextLine());
            }
        }
        // For fun, randomize the names so that it's different for each run
        Collections.shuffle(allEmployees, rand);
        return allEmployees.iterator();
    }

    /**
     * Each day, so long as the project is not complete (while there are still lines of code to write or test)
     * <ol>
     *     <li> add one day to the daysWorkedSoFar of the project (hint: check out the instance variables)
     *     <li> call each employee's work method. If the employee is a Programmer, add the result of the work
     *          method into the variable you created to count the number of lines coded. Else if the
     *          employee is a tester add the result of the work method into a variable for lines tested.
     * </ol>
     * hint: You will want to create local variables to keep track of the lines of code that have been written
     *       and tested. You will want to compare these local variables to the instance variable, linesOfCode.
     * <p>
     * If you finish the recitation early: Change your code to ensure that the testers never test more code
     * than the programmers have coded. I have made this problem easier
     * by adding the programmers to the ArrayList<Employee> first.
     */
    public void completeProject(){
    	int linesWritten = 0;
    	int linesTested = 0;
        while (linesWritten < linesOfCode || linesOfCode > linesTested) {
        	if(linesTested > linesWritten) {
        		break;
        	}
        	daysWorkedSoFar++;
        	
        	for (Employee e : employeesOnProject) {
        		int worked = e.work();
        		if(e instanceof Programmer) {
        			linesWritten += worked;	
        		} else {
        			linesTested += worked;
        		}
        	}
        }

    }

    /**
	 * A string representation of a particular project. The String should consist of the following:
	 * <ol>
	 *     <li> "Deadline: " + deadline, followed by a newline
	 *     <li> "Days required: " + daysWorkedSoFar, followed by a newline
	 *     <li> "Employees on Project:", followed by a newline
	 *     <li> Each employee's (toString) separated by a newline
	 * </ol>
	 * <p>
	 * Adding each employee to the string that you will return can be done in one of two ways:
	 * <ul>
	 *     <li> Loop through employeesOnProject array and add the employee's toString with a newline character
	 *     <li> Use the String.join method (Java 8) with the following arguments: a newline character and
	 *          employeesOnProject.stream().map(Object::toString).collect(Collectors.toList())) this creates
	 *          a list of Strings using the Employee toString method.
	 *          It's a little bit messy, but it gets the job done.
	 * </ul>
	 * WARNING: DO NOT PRINT IN THIS METHOD. The toString should only return the string representation of the class. <br><br>
	 * Example: <br>
	 * <p>
	 * Deadline: 15 <br>
	 * Days required: 16 <br>
	 * Employees on Project: <br>
	 * Programmer Carla Landon, average productivity: 140 lines <br>
	 * Programmer Carisa Belvin, average productivity: 131 lines <br>
	 * Programmer Laura Mccarty, average productivity: 113 lines <br>
	 * Tester Melisa Bucholz, average productivity: 80 lines <br>
	 * Tester Bernetta Reber, average productivity: 85 lines <br>
	 * Tester Tanisha Mckoy, average productivity: 81 lines <br>
	 * Tester Lorrie Coller, average productivity: 88 lines <br>
	 *
	 * @return the string representation of a project
	 */
	@Override
	public String toString(){
		String result = String.format("Deadline: %d\nDays required: %d\nEmployees on Project:\n", deadline, daysWorkedSoFar);
		
		result += String.join("\n", employeesOnProject.stream().map(Object::toString).collect(Collectors.toList()));
	    return result;
	}


    public static void main(String [] args) throws FileNotFoundException {
        Project p1 = new Project(5000, 15, 10, 10);
        p1.completeProject();
        System.out.println(p1);
        // Your code here
        System.out.println();
        
        Project p2 = new Project(4000, 14, 8, 8);
        p2.completeProject();
        System.out.println(p2);
        System.out.println();
        Project p3 = new Project(3000, 17, 8, 8);
        p3.completeProject();
        System.out.println(p3);

    }


}

